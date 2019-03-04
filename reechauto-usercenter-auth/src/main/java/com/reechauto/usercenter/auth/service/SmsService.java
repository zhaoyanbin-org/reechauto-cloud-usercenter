package com.reechauto.usercenter.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reechauto.usercenter.auth.feign.FeignSmsService;
import com.reechauto.usercenter.common.resp.ResponseData;


@Service
public class SmsService {
	
	@Autowired
	private FeignSmsService feignSmsService;

	
	public ResponseData sendMessage(String clientId,String smsType,String receiveMobile,String templateId,String signName,String isVoice,String sendParam) {
		
		
		return feignSmsService.sendSms(clientId,smsType,receiveMobile,templateId,signName,isVoice,sendParam);
	}
	
	
	
}
