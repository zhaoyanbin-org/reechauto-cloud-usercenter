package com.reechauto.usercenter.auth.exception.custom;

import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;


@JsonSerialize(using = CustomOAuthExceptionJacksonSerializer.class)
public class CustomOAuth2Exception extends OAuth2Exception {

	private static final long serialVersionUID = 1L;
	public CustomOAuth2Exception(String msg, Throwable t) {
        super(msg, t);
    }
    public CustomOAuth2Exception(String msg) {
        super(msg);
    }
}
