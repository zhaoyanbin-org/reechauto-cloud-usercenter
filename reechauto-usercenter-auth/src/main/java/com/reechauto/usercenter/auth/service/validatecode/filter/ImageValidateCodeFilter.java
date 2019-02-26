package com.reechauto.usercenter.auth.service.validatecode.filter;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import com.reechauto.usercenter.auth.entity.constant.ReechAuthConstant;
import com.reechauto.usercenter.auth.service.validatecode.processor.CodeProcessor;
import com.reechauto.usercenter.common.exception.ValidateCodeException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component("imageValidateCodeFilter")
public class ImageValidateCodeFilter extends OncePerRequestFilter implements InitializingBean {
	@Autowired
	private AuthenticationFailureHandler reechAuthenticationFailureHandler;
	@Autowired
	private CodeProcessor codeProcessor;

	private AntPathMatcher pathMatcher = new AntPathMatcher();

	private Set<String> urlSet = new HashSet<String>();

	@Override
	public void afterPropertiesSet() throws ServletException {
		super.afterPropertiesSet();
		addUrlToMap(ReechAuthConstant.VALIDATE_URL_IMAGE);
	}

	protected void addUrlToMap(String urlString) {
		if (StringUtils.isNotBlank(urlString)) {
			String[] urls = StringUtils.splitByWholeSeparatorPreserveAllTokens(urlString, ",");
			for (String url : urls) {
				urlSet.add(url);
			}
		}
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		if (!StringUtils.equalsIgnoreCase(request.getMethod(), "get")) {
			for (String url : urlSet) {
				if (pathMatcher.match(url, request.getRequestURI())) {
					try {
						String deviceId = request
								.getParameter(ReechAuthConstant.PARAMETER_NAME_ON_VALIDATE_DEVICEID_IMAGE);
						String verifyCode = request
								.getParameter(ReechAuthConstant.PARAMETER_NAME_ON_VALIDATE_VCODE_IMAGE);
						boolean flag = codeProcessor.validate(deviceId, verifyCode);
						if (!flag) {
							reechAuthenticationFailureHandler.onAuthenticationFailure(request, response,
									new ValidateCodeException("验证不成功"));
							return;
						}
						log.info("验证码校验通过");
					} catch (Exception exception) {
						reechAuthenticationFailureHandler.onAuthenticationFailure(request, response,
								new ValidateCodeException(exception.getMessage()));
						return;
					}
				}
			}
		}

		filterChain.doFilter(request, response);
	}

}
