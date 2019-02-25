package com.reechauto.usercenter.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reechauto.usercenter.auth.service.oauth2.ReechRedisTokenStore;
import com.reechauto.usercenter.common.resp.ResponseData;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class LogoutController {
	@Autowired
	public ReechRedisTokenStore reechRedisTokenStore;

	@Autowired
	private ConsumerTokenServices consumerTokenServices;

	@RequestMapping("/logout")
	public ResponseData logout() {
		OAuth2Authentication oAuth2Authentication = (OAuth2Authentication) SecurityContextHolder.getContext()
				.getAuthentication();
		OAuth2AccessToken oAuth2AccessToken = reechRedisTokenStore.getAccessToken(oAuth2Authentication);
		OAuth2RefreshToken oAuth2RefreshToken = oAuth2AccessToken.getRefreshToken();

		reechRedisTokenStore.removeAccessToken(oAuth2AccessToken);
		reechRedisTokenStore.removeRefreshToken(oAuth2RefreshToken);
		log.info("退出登录");
		return ResponseData.ok();
	}

	@RequestMapping(value = "/logout/exit")
	public ResponseData revokeToken(String access_token) {
		if (consumerTokenServices.revokeToken(access_token)) {
			return ResponseData.ok("注销成功");
		} else {
			return ResponseData.error("注销失败");
		}
	}

}
