package com.reechauto.usercenter.auth.service.authentication.provider;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.reechauto.usercenter.auth.service.authentication.token.MobileAuthenticationToken;

public class MobileAuthenticationProvider implements AuthenticationProvider {

	private UserDetailsService userDetailsService;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		MobileAuthenticationToken authenticationToken = (MobileAuthenticationToken)authentication;
		UserDetails user = userDetailsService.loadUserByUsername((String) authenticationToken.getPrincipal());
		
		if(user == null) {
			throw new InternalAuthenticationServiceException("无法获取用户信息");
		}
		
		MobileAuthenticationToken mobileAuthenticationToken = new MobileAuthenticationToken(user,user.getAuthorities());
		mobileAuthenticationToken.setDetails(authenticationToken.getAuthorities());
		return mobileAuthenticationToken;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return MobileAuthenticationToken.class.isAssignableFrom(authentication);
	}

	public UserDetailsService getUserDetailsService() {
		return userDetailsService;
	}

	public void setUserDetailsService(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

	
}
