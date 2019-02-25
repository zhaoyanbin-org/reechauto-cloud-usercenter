package com.reechauto.usercenter.common.oauth2.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.reechauto.usercenter.common.resp.ResponseData;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author zhaoyanbin
 *
 */
@Slf4j
@Component("reechAuthenticationFailureHandler")
public class ReechAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
	@Autowired
	private ObjectMapper objectMapper;

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		
		log.info("ReechAutoAuthenctiationFailureHandler");

		response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().write(objectMapper.writeValueAsString(ResponseData.error(HttpStatus.INTERNAL_SERVER_ERROR.value(),exception.getMessage())));

	}

}
