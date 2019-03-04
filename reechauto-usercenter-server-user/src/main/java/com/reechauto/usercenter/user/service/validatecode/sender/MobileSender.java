package com.reechauto.usercenter.user.service.validatecode.sender;

public interface MobileSender {
	void send(String phone, String code,String templateId);
}
