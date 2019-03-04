package com.reechauto.usercenter.user.service.validatecode.sender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.stereotype.Service;

import com.reechauto.usercenter.user.service.validatecode.feign.FeignSmsService;

@Service("mobileSender")
public class DefaultMobileSender implements MobileSender {
	@Autowired
	private ResourceServerProperties resourceServerProperties;
	@Autowired
	private FeignSmsService feignSmsService;
	@Override
	public void send(String phone, String code,String templateId) {
		System.out.println("向手机号：" + phone + "，发送验证码：" + code+",模板Id是"+templateId);
		feignSmsService.sendSms(resourceServerProperties.getClientId(), "advice", phone, templateId, "绿驰汽车", "N", "{\"code\":\""+code+"\"}");
	}

}
