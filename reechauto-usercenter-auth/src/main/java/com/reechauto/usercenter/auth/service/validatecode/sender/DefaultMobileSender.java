package com.reechauto.usercenter.auth.service.validatecode.sender;

import org.springframework.stereotype.Service;

@Service("mobileSender")
public class DefaultMobileSender implements MobileSender {

	@Override
	public void send(String phone, String code,String templateId) {
		System.out.println("向手机号：" + phone + "，发送验证码：" + code+",模板Id是"+templateId);
	}

}
