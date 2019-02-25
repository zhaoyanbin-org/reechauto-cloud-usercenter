package com.reechauto.usercenter.common.oauth2.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.reechauto.usercenter.common.resp.ResponseData;

@Component("reechAuthenticationEntryPoint")
public class ReechAuthenticationEntryPoint implements AuthenticationEntryPoint {
	@Autowired
	private ObjectMapper objectMapper;

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		response.setStatus(HttpStatus.UNAUTHORIZED.value());
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().write(objectMapper
				.writeValueAsString(ResponseData.error(HttpStatus.UNAUTHORIZED.value(), authException.getMessage())));
	}

}
