package com.reechauto.usercenter.auth.service.oauth2;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

import com.reechauto.usercenter.auth.entity.user.BaseUserDetail;
import com.reechauto.usercenter.auth.entity.user.ReechUser;

public class SecurityUtils {

	/**
	 * 获取当前登录人信息
	 * @return
	 */
	public static ReechUser getCurrentUser() {
		if (SecurityContextHolder.getContext() == null
				|| SecurityContextHolder.getContext().getAuthentication() == null) {
			return null;
		} else if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof BaseUserDetail) {
			BaseUserDetail details = (BaseUserDetail) SecurityContextHolder.getContext().getAuthentication()
					.getPrincipal();
			return details.getReechUser();
		}
		return null;
	}

	/**
	 * 获取当前登录人ID
	 * @return
	 */
	public static Long getCurrentUserId() {
		ReechUser user = getCurrentUser();
		if (user != null) {
			return user.getUserId();
		} else {
			return null;
		}
	}

	/**
	 * 获取当前登录用户的CilentId
	 * @return
	 */
	public static String getCurrentClientId() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (isOAuth2Authentication()) {
			OAuth2Authentication oauth2Auth = (OAuth2Authentication) authentication;
			return oauth2Auth.getOAuth2Request().getClientId();
		}
		return "null";
	}


	private static boolean isOAuth2Authentication() {
		if (SecurityContextHolder.getContext() == null
				|| SecurityContextHolder.getContext().getAuthentication() == null) {
			return false;
		}
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth instanceof OAuth2Authentication) {
			return true;
		} else {
			return false;
		}

	}

}
