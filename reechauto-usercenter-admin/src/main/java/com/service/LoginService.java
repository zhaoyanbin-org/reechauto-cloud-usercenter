package com.service;

import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import com.common.Constants;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.feign.AuthorizationService;
import com.google.gson.internal.LinkedTreeMap;
import com.reechauto.usercenter.common.resp.ResponseData;
import com.reechauto.usercenter.common.resp.ToeknBean;
import com.reechauto.usercenter.common.utils.gson.GsonUtil;
import com.reechauto.usercenter.common.utils.str.BasicTokenUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class LoginService {
	@Autowired
	private ResourceServerProperties resourceServerProperties;
	@Autowired
	private AuthorizationService authorizationService;
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	public ResponseData loginByPassword(String username, String password) throws UnsupportedEncodingException, JsonProcessingException {
		String authorizationStr = BasicTokenUtil.authorizationBasic(resourceServerProperties.getClientId(),
				resourceServerProperties.getClientSecret());
		ToeknBean token = authorizationService.login(authorizationStr, "password", username, password);
		redisTemplate.opsForValue().set(Constants.SESSION_LOGIN_TOKEN_KEY, token, token.getExpires_in(), TimeUnit.SECONDS);
		Object userObj = authorizationService.userInfo(BasicTokenUtil.authorizationBearer(token.getAccess_token()));
		ResponseData serverUser = GsonUtil.GsonToBean(GsonUtil.GsonString(userObj), ResponseData.class);
		if (1000 != serverUser.getCode()) {
			log.info("获取远程用户信息失败");
			throw new RuntimeException("获取远程用户信息失败");
		}
		Map<String, Object> user = (LinkedTreeMap<String, Object>) serverUser.getData().get("context");
		ObjectMapper mapper = new ObjectMapper();
		String userToString = mapper.writeValueAsString(user);
		redisTemplate.opsForValue().set(Constants.SESSION_LOGIN_USER_KEY, userToString, token.getExpires_in(), TimeUnit.SECONDS);
		return ResponseData.ok();
	}
	
}
