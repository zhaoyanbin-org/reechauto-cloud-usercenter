package com.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.reechauto.usercenter.common.resp.ResponseData;

@FeignClient(name = "usercenter-server-user")
public interface ClientService {

	@PostMapping("/clientDetails/list")
	public ResponseData queryClientDetails(@RequestParam("start") Integer start,
			@RequestParam("pageNum") Integer pageNum, @RequestParam("ct") String ct, @RequestParam("cv") String cv,
			@RequestParam("timeStamp") Long timeStamp);

	@PostMapping("/clientDetails/add")
	public ResponseData addClientDetails(@RequestParam("clientId") String clientId,
			@RequestParam("clientSecret") String clientSecret, @RequestParam("resourceIds") String resourceIds,
			@RequestParam("scope") String scope, @RequestParam("authorizedGrantTypes") String authorizedGrantTypes,
			@RequestParam("webServerRedirectUri") String webServerRedirectUri,
			@RequestParam("authorities") String authorities,
			@RequestParam("accessTokenValidity") Integer accessTokenValidity,
			@RequestParam("refreshTokenValidity") Integer refreshTokenValidity,
			@RequestParam("additionalInformation") String additionalInformation,
			@RequestParam("autoapprove") String autoapprove, @RequestParam("ct") String ct,
			@RequestParam("cv") String cv, @RequestParam("timeStamp") Long timeStamp);

	@PostMapping("/clientDetails/del")
	public ResponseData delClientDetails(@RequestParam("clientId") String clientId, @RequestParam("ct") String ct,
			@RequestParam("cv") String cv, @RequestParam("timeStamp") Long timeStamp);

	@PostMapping("/clientDetails/update")
	public ResponseData updateClientDetails(@RequestParam("oldClientId") String oldClientId,
			@RequestParam("newClientId") String newClientId, @RequestParam("newResourceIds") String newResourceIds,
			@RequestParam("newScope") String newScope,
			@RequestParam("newAuthorizedGrantTypes") String newAuthorizedGrantTypes,
			@RequestParam("newWebServerRedirectUri") String newWebServerRedirectUri,
			@RequestParam("newAuthorities") String newAuthorities,
			@RequestParam("newAccessTokenValidity") Integer newAccessTokenValidity,
			@RequestParam("newRefreshTokenValidity") Integer newRefreshTokenValidity,
			@RequestParam("newAdditionalInformation") String newAdditionalInformation,
			@RequestParam("newAutoapprove") String newAutoapprove, @RequestParam("ct") String ct,
			@RequestParam("cv") String cv, @RequestParam("timeStamp") Long timeStamp);
}
