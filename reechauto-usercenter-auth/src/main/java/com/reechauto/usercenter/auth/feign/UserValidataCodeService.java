package com.reechauto.usercenter.auth.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.reechauto.usercenter.common.resp.ResponseData;

@FeignClient(name = "usercenter-server-user")
public interface UserValidataCodeService {

	@PostMapping(value = "/code/verify")
	public ResponseData validataCode(@RequestParam(name = "deviceId", required = true) String deviceId,
			@RequestParam(name = "verifyCode", required = true) String verifyCode);

}
