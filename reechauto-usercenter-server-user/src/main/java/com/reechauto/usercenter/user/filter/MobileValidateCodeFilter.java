package com.reechauto.usercenter.user.filter;

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

import com.reechauto.usercenter.common.exception.ValidateCodeException;
import com.reechauto.usercenter.user.bean.ReechAuthConstant;
import com.reechauto.usercenter.user.service.validatecode.processor.CodeProcessor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component("mobileValidateCodeFilter")
public class MobileValidateCodeFilter extends OncePerRequestFilter implements InitializingBean {
	@Autowired
	private AuthenticationFailureHandler reechAuthenticationFailureHandler;
	@Autowired
	private CodeProcessor codeProcessor;

	private AntPathMatcher pathMatcher = new AntPathMatcher();
	private Set<String> urlSet = new HashSet<String>();

	@Override
	public void afterPropertiesSet() throws ServletException {
		super.afterPropertiesSet();
		addUrlToMap(ReechAuthConstant.MOBILE_REGISTER_URL);
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
						String deviceId = request.getParameter(ReechAuthConstant.PARAMETER_NAME_ON_VALIDATE_DEVICEID_MOBILE);
						String verifyCode = request.getParameter(ReechAuthConstant.PARAMETER_NAME_ON_VALIDATE_VCODE_MOBILE);

						boolean flag = codeProcessor.validate(deviceId, verifyCode);
						if (!flag) {
							reechAuthenticationFailureHandler.onAuthenticationFailure(request, response,
									new ValidateCodeException("验证失败"));
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
