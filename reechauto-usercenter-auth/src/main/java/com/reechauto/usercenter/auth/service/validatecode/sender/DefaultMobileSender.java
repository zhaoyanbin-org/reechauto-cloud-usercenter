package com.reechauto.usercenter.auth.service.validatecode.sender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reechauto.usercenter.auth.service.SmsService;

@Service("mobileSender")
public class DefaultMobileSender implements MobileSender {

	@Autowired
	private SmsService smsService;
	@Override
	public void send(String phone, String code,String templateId) {
		System.out.println("向手机号：" + phone + "，发送验证码：" + code+",模板Id是"+templateId);
		smsService.sendMessage("userCenter", "advice", phone, templateId, "绿驰汽车", "N", "{\"code\":\""+code+"\"}");
	}

}
