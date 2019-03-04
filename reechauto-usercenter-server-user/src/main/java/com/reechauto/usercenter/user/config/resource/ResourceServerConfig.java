package com.reechauto.usercenter.user.config.resource;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.reechauto.usercenter.user.filter.ValidateCodeSecurityConfig;

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
	private ValidateCodeSecurityConfig validateCodeSecurityConfig;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean(name="objectMapper")
	@Primary
	public ObjectMapper objectMapper() {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
		objectMapper.setTimeZone(TimeZone.getTimeZone("GMT+8"));
		objectMapper.setSerializationInclusion(Include.ALWAYS);
		// 反序列化的时候如果多了其他属性,不抛出异常
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		// 如果是空对象的时候,不抛异常
		objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		return objectMapper;
	}

	
	@Bean
    public RemoteTokenServices remoteTokenServices() {
        RemoteTokenServices remoteTokenServices = new RemoteTokenServices();
        return remoteTokenServices;
    }


	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		log.info("用户中心微服务的ResourceId：==>" + resourceServerProperties.getResourceId());
		resources.resourceId(resourceServerProperties.getResourceId());
		resources.accessDeniedHandler(reechAccessDeniedHandler).authenticationEntryPoint(reechAuthenticationEntryPoint);
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
        .exceptionHandling().accessDeniedHandler(reechAccessDeniedHandler).authenticationEntryPoint(reechAuthenticationEntryPoint)
		.and()
		.authorizeRequests()
		.antMatchers("/code/**").permitAll()
		.antMatchers("/api/**").hasAuthority("ROLE_ADMIN")
		.antMatchers("/reech/depart").access("#oauth2.hasScope('abc')")
		.anyRequest().authenticated();
		
		 http.apply(validateCodeSecurityConfig);
	}

}
