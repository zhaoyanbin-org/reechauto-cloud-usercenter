package com.reechauto.usercenter.auth.service.validatecode.sender;

public interface MobileSender {
	void send(String phone, String code,String templateId);
}
