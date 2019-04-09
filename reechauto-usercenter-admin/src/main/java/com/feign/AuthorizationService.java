package com.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import com.reechauto.usercenter.common.resp.ToeknBean;


@FeignClient(name="usercenter-auth")
public interface AuthorizationService {

	@PostMapping("/oauth/token")
    public ToeknBean login(@RequestHeader("Authorization")String authorization,@RequestParam(name="grant_type",required=true,defaultValue="password") String grant_type,@RequestParam("username") String username,@RequestParam("password") String password);
	
	
	@PostMapping("/mobile/token")
    public ToeknBean loginByCode(@RequestHeader("Authorization")String authorization,@RequestParam("mobile") String mobile,@RequestParam("vcode") String vcode);
	
	
	@PostMapping("/userinfo")
    public Object userInfo(@RequestHeader("Authorization")String authorization);
	
}
