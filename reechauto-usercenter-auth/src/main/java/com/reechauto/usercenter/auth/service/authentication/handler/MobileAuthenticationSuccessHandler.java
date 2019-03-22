package com.reechauto.usercenter.auth.service.authentication.handler;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.TokenRequest;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.reechauto.usercenter.auth.service.oauth2.ClientVerifyService;
import com.reechauto.usercenter.common.utils.json.JsonUtils;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Component("mobileAuthenticationSuccessHandler")
public class MobileAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

	public static final String GRANTTYPE = "mobile";
	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private ClientVerifyService clientVerifyService;

	@Autowired
	private AuthorizationServerTokenServices authorizationServerTokenServices;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws ServletException, IOException {

		ClientDetails clientDetails = clientVerifyService.verifyClientHeader(request,GRANTTYPE);

		TokenRequest tokenRequest = new TokenRequest(new HashMap<>(), clientDetails.getClientId(), clientDetails.getScope(), GRANTTYPE);
		OAuth2Request oAuth2Request = tokenRequest.createOAuth2Request(clientDetails);
		OAuth2Authentication oAuth2Authentication = new OAuth2Authentication(oAuth2Request, authentication);
		OAuth2AccessToken token = authorizationServerTokenServices.createAccessToken(oAuth2Authentication);

		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().write(objectMapper.writeValueAsString(token));
		log.info("token={}", JsonUtils.toJson(token));

	}

}
