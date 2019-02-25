package com.reechauto.usercenter.auth.service.authentication.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import com.reechauto.usercenter.auth.service.ReechUserDetailsService;
import com.reechauto.usercenter.auth.service.authentication.filter.MobileAuthenticationFilter;
import com.reechauto.usercenter.auth.service.authentication.provider.MobileAuthenticationProvider;

/**
 * @author zhaoyb
 *
 */
@Component
public class MobileSecurityConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

	@Autowired
	private AuthenticationFailureHandler reechAuthenticationFailureHandler;
	@Autowired
	private AuthenticationSuccessHandler mobileAuthenticationSuccessHandler;
	@Autowired
	@Qualifier("reechUserDetailsService")
	private ReechUserDetailsService reechUserDetailsService;
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		MobileAuthenticationFilter mobileAuthenticationFilter = new MobileAuthenticationFilter();
		mobileAuthenticationFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
		mobileAuthenticationFilter.setAuthenticationSuccessHandler(mobileAuthenticationSuccessHandler);
		mobileAuthenticationFilter.setAuthenticationFailureHandler(reechAuthenticationFailureHandler);
		
		MobileAuthenticationProvider mobileAuthenticationProvider =new MobileAuthenticationProvider();
		mobileAuthenticationProvider.setUserDetailsService(reechUserDetailsService);
		
		http.authenticationProvider(mobileAuthenticationProvider)
		.addFilterAfter(mobileAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
	}
}
