package com.reechauto.usercenter.auth.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.reechauto.usercenter.common.resp.ResponseData;



@FeignClient(name = "server-sms")
public interface FeignSmsService {

	@PostMapping(value = "/sms/sendSmsMeg")
	public ResponseData sendSms(@RequestParam("clientId") String clientId,@RequestParam("smsType") String smsType,
			@RequestParam("receiveMobile") String receiveMobile,@RequestParam("templateId") String templateId,
			@RequestParam("signName") String signName,@RequestParam("isVoice") String isVoice,String sendParam);
	
	
	
	
	
}
