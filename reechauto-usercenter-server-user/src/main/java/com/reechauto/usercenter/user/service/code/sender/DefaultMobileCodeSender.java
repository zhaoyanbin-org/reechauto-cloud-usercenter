package com.reechauto.usercenter.user.service.code.sender;

import org.springframework.stereotype.Service;

import com.reechauto.usercenter.user.bean.code.ValidateCode;

@Service("defaultMobileCodeSender")
public class DefaultMobileCodeSender implements MobileCodeSender{

	@Override
	public void send(String mobile, ValidateCode validateCode) {
		System.out.println("向手机号：" + mobile + "，发送验证码：" + validateCode.getCode()+",过期时间为："+validateCode.getExpireTime());
	}

}
