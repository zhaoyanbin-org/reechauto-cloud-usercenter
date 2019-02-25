package com.reechauto.usercenter.auth.exception.filter;

import java.io.IOException;
import java.util.Base64;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.reechauto.usercenter.auth.service.oauth2.ReechClientDetailsService;
import com.reechauto.usercenter.common.resp.ResponseData;

/**
 * AuthorizationServerConfig中配制
 * @author zhaoyanbin
 *
 */
@Component
public class CoustomBasicAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private ReechClientDetailsService clientDetailsService;
	@Autowired
	private ObjectMapper objectMapper;
	private PathMatcher pathMatcher = new AntPathMatcher();

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		if (!pathMatcher.match("/oauth/token", request.getRequestURI()) || !request.getParameter("grant_type").equals("password")) {
			filterChain.doFilter(request, response);
			return;
		}

		String[] clientDetails = this.isHasClientDetails(request);

		if (clientDetails == null) {
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().write(objectMapper
					.writeValueAsString(ResponseData.error(HttpStatus.UNAUTHORIZED.value(), "请求头中未包含client的信息")));
			return;
		}

		this.handle(request, response, clientDetails, filterChain);

	}

	private void handle(HttpServletRequest request, HttpServletResponse response, String[] clientDetails,
			FilterChain filterChain) throws IOException, ServletException {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null && authentication.isAuthenticated()) {
			filterChain.doFilter(request, response);
			return;
		}

		ClientDetails details = this.clientDetailsService.loadClientByClientId(clientDetails[0]);
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
				request.getParameter("client_id"), request.getParameter("client_secret"), details.getAuthorities());

		SecurityContextHolder.getContext().setAuthentication(token);
		filterChain.doFilter(request, response);
	}

	// 判断请求头中是否包含client信息，不包含返回false
	private String[] isHasClientDetails(HttpServletRequest request) {
		String[] params = null;
		String header = request.getHeader(HttpHeaders.AUTHORIZATION);
		if (header != null) {
			String basic = header.substring(0, 5);
			if (basic.toLowerCase().contains("basic")) {
				String tmp = header.substring(6);
				String defaultClientDetails = new String(Base64.getDecoder().decode(tmp));
				String[] clientArrays = defaultClientDetails.split(":");
				if (clientArrays.length != 2) {
					return params;
				} else {
					params = clientArrays;
				}
			}
		}

		String id = request.getParameter("client_id");
		String secret = request.getParameter("client_secret");
		if (header == null && id != null) {
			params = new String[] { id, secret };
		}
		return params;
	}

//	public ClientDetailsService getClientDetailsService() {
//		return clientDetailsService;
//	}
//
//	public void setClientDetailsService(ReechClientDetailsService clientDetailsService) {
//		this.clientDetailsService = clientDetailsService;
//	}

}