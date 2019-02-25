package com.reechauto.usercenter.auth.service.authentication.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.Assert;

import com.reechauto.usercenter.auth.entity.constant.ReechAuthConstant;
import com.reechauto.usercenter.auth.service.authentication.token.MobileAuthenticationToken;

public class MobileAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

	public static final String REECHAUTO_MOBILE_KEY = "mobile";

	private String mobileParameter = REECHAUTO_MOBILE_KEY;
	private boolean postOnly = true;

	public MobileAuthenticationFilter() {
		super(new AntPathRequestMatcher(ReechAuthConstant.REECHLOGIN_MOBILE_URL, "POST"));
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		if (postOnly && !request.getMethod().equals("POST")) {
			throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
		}
		String mobile = obtainMobile(request);

		if (mobile == null) {
			mobile = "";
		}
		mobile = mobile.trim();
		MobileAuthenticationToken authRequest = new MobileAuthenticationToken(mobile);
		setDetails(request, authRequest);
		return this.getAuthenticationManager().authenticate(authRequest);
	}

	protected String obtainMobile(HttpServletRequest request) {
		return request.getParameter(mobileParameter);
	}

	protected void setDetails(HttpServletRequest request, MobileAuthenticationToken authRequest) {
		authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
	}

	public void setMobileParameter(String mobileParameter) {
		Assert.hasText(mobileParameter, "Username parameter must not be empty or null");
		this.mobileParameter = mobileParameter;
	}

	public void setPostOnly(boolean postOnly) {
		this.postOnly = postOnly;
	}

	public final String getMobileParameter() {
		return mobileParameter;
	}

}
