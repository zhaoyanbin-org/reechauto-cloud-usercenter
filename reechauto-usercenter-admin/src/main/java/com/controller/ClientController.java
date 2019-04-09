package com.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.bean.grantTypes.GrantTypes;
import com.feign.ClientService;
import com.mapper.ResourceScopeMapper;
import com.mapper.ResourceServerMapper;
import com.model.ResourceScope;
import com.model.ResourceScopeExample;
import com.model.ResourceServer;
import com.model.ResourceServerExample;
import com.reechauto.usercenter.common.resp.ResponseData;

@RestController
public class ClientController {

	@Autowired
	private ClientService clientService;
	@Autowired
	private ResourceServerMapper resourceServerMapper;
	@Autowired
	private ResourceScopeMapper resourceScopeMapper;

	@RequestMapping(value = "/clientDetails/query", method = RequestMethod.GET)
	public Map<String, Object> queryClientDetails(@RequestParam String query,
			@RequestParam(required = false, defaultValue = "1") Integer page,
			@RequestParam(required = false, defaultValue = "10") Integer limit) {
		ResponseData responseData = clientService.queryClientDetails((page - 1) * limit, limit, "ct", "cv", 0L);
		Map<String, Object> result = new HashMap<String, Object>();
		System.out.println(responseData.getData().get("total"));
		System.out.println(responseData.getData().get("context"));
		System.out.println(responseData.getCode());
		result.put("count", responseData.getData().get("total"));
		result.put("data", responseData.getData().get("context"));
		result.put("code", 1000);
		result.put("message", responseData.getMessage());
		return result;
	}

	@RequestMapping(value = "/resourceServers/query", method = RequestMethod.POST)
	public Map<String, Object> queryResourceServers() {
		Map<String, Object> result = new HashMap<String, Object>();
		ResourceServerExample example = new ResourceServerExample();
		List<ResourceServer> list = resourceServerMapper.selectByExample(example);
		result.put("count", list.size());
		result.put("data", list);
		result.put("code", 1000);
		result.put("message", "OK");
		return result;
	}

	@RequestMapping(value = "/resourceScopes/query", method = RequestMethod.POST)
	public Map<String, Object> queryResourceScopes() {
		Map<String, Object> result = new HashMap<String, Object>();
		ResourceScopeExample example = new ResourceScopeExample();
		List<ResourceScope> list = resourceScopeMapper.selectByExample(example);
		result.put("count", list.size());
		result.put("data", list);
		result.put("code", 1000);
		result.put("message", "OK");
		return result;
	}

	@RequestMapping(value = "/grantTypes/query", method = RequestMethod.POST)
	public Map<String, Object> queryGrantTypes() {
		Map<String, Object> result = new HashMap<String, Object>();

		List<GrantTypes> list = new ArrayList<>();
		list.add(new GrantTypes("authorization_code", "授权码"));
		list.add(new GrantTypes("password", "密码"));
		list.add(new GrantTypes("client_credentials", "客户端模式"));
		list.add(new GrantTypes("implicit", "简化"));
		list.add(new GrantTypes("refresh_token", "刷新"));
		list.add(new GrantTypes("mobile", "手机"));
		result.put("count", list.size());
		result.put("data", list);
		result.put("code", 1000);
		result.put("message", "OK");
		return result;
	}

	@RequestMapping(value = "/clientDetails/add", method = RequestMethod.POST)
	public ResponseData add(String clientId, String clientSecret, String resourceIds, String scope,
			String authorizedGrantTypes, String webServerRedirectUri, String authorities, Integer accessTokenValidity,
			Integer refreshTokenValidity, String additionalInformation, String autoapprove) {
		System.out.println("7777777777777777777777777777777777777777777777777");
		if (StringUtils.isBlank(clientId)) {
			throw new RuntimeException("客户端ID不能为空");
		}
		if (StringUtils.isBlank(clientSecret)) {
			throw new RuntimeException("客户端密码不能为空");
		}
		ResponseData responseData = clientService.addClientDetails(clientId, clientSecret, resourceIds, scope,
				authorizedGrantTypes, webServerRedirectUri, authorities, accessTokenValidity, refreshTokenValidity,
				additionalInformation, autoapprove, "ct", "cv", 0L);
		System.out.println("88888888888888888888888888888888888888888888888888888888");
		System.out.println(responseData.getCode() + responseData.getMessage());
		return responseData;
	}

	@RequestMapping(value = "/clientDetails/del", method = RequestMethod.POST)
	public ResponseData del(String clientId) {
		if (StringUtils.isBlank(clientId)) {
			throw new RuntimeException("客户端ID不能为空");
		}

		ResponseData responseData = clientService.delClientDetails(clientId, "ct", "cv", 0L);
		return responseData;
	}
	@RequestMapping(value = "/clientDetails/update", method = RequestMethod.POST)
	public ResponseData update(String oldClientId,String newClientId, String newResourceIds, String newScope,
			String newAuthorizedGrantTypes, String newWebServerRedirectUri, String newAuthorities, Integer newAccessTokenValidity,
			Integer newRefreshTokenValidity, String newAdditionalInformation, String newAutoapprove) {
		System.out.println("7777777777777777777777777777777777777777777777777");
		if (StringUtils.isBlank(oldClientId)) {
			throw new RuntimeException("原客户端ID不能为空");
		}
		ResponseData responseData = clientService.updateClientDetails(oldClientId,newClientId,  newResourceIds, newScope,
				newAuthorizedGrantTypes, newWebServerRedirectUri, newAuthorities, newAccessTokenValidity, newRefreshTokenValidity,
				newAdditionalInformation, newAutoapprove, "ct", "cv", 0L);
		System.out.println("88888888888888888888888888888888888888888888888888888888");
		System.out.println(responseData.getCode() + responseData.getMessage());
		return responseData;
	}
}
