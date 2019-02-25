package com.reechauto.usercenter.auth.config.security;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.reechauto.usercenter.auth.entity.constant.ReechAuthConstant;
import com.reechauto.usercenter.auth.service.ReechUserDetailsService;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	public ReechUserDetailsService reechUserDetailsService;
	
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
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		AuthenticationManager manager = super.authenticationManagerBean();
		return manager;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(reechUserDetailsService).passwordEncoder(passwordEncoder());
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.requestMatchers().antMatchers("/oauth/**",ReechAuthConstant.REECHLOGIN_LOGINPAGE_URL,ReechAuthConstant.REECHLOGIN_LOGINPROCESSING_URL)
         .and()
		.authorizeRequests()
		.antMatchers(ReechAuthConstant.REECHLOGIN_LOGINPAGE_URL,ReechAuthConstant.REECHLOGIN_LOGINPROCESSING_URL).permitAll()
		.antMatchers("/oauth/**").authenticated()
		.and()
		.formLogin().loginPage(ReechAuthConstant.REECHLOGIN_LOGINPAGE_URL).loginProcessingUrl(ReechAuthConstant.REECHLOGIN_LOGINPROCESSING_URL);
	}
	
	@Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers
		("/swagger-ui.html/**", "/webjars/**",
                		"/swagger-resources/**", "/v2/api-docs/**",
                		"/swagger-resources/configuration/ui/**", "/swagger-resources/configuration/security/**",
                		"/images/**");
    }

}
