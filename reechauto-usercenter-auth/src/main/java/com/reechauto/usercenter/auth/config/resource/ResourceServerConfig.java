package com.reechauto.usercenter.auth.config.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;

import com.reechauto.usercenter.auth.filter.ValidateCodeSecurityConfig;
import com.reechauto.usercenter.auth.service.authentication.config.MobileSecurityConfigurer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
	@Autowired
	private ResourceServerProperties resourceServerProperties;
	@Autowired
	private AccessDeniedHandler reechAccessDeniedHandler;
	@Autowired
	private AuthenticationEntryPoint reechAuthenticationEntryPoint;
	@Autowired
	private MobileSecurityConfigurer mobileSecurityConfigurer;
	@Autowired
	private ValidateCodeSecurityConfig validateCodeSecurityConfig;


	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		log.info("授权服务器的ResourceId：==>" + resourceServerProperties.getResourceId());
		resources.resourceId(resourceServerProperties.getResourceId());
		resources.accessDeniedHandler(reechAccessDeniedHandler).authenticationEntryPoint(reechAuthenticationEntryPoint);
	}


	@Override
	public void configure(HttpSecurity http) throws Exception {
		 http.requestMatchers().antMatchers("/mobile/token","/logout/**","/user","/userinfo","/user2")
         .and()
         .exceptionHandling().accessDeniedHandler(reechAccessDeniedHandler).authenticationEntryPoint(reechAuthenticationEntryPoint)
         .and()
         .authorizeRequests()
         //.antMatchers("/nologin/**").permitAll()
         .anyRequest().authenticated();
		 
		 http.apply(mobileSecurityConfigurer);
		 http.apply(validateCodeSecurityConfig);
	}

}
