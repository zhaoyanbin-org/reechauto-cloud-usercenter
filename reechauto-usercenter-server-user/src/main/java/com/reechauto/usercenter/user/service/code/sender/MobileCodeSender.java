package com.reechauto.usercenter.user.service.code.sender;

import com.reechauto.usercenter.user.bean.code.ValidateCode;

public interface MobileCodeSender {
	void send(String mobile, ValidateCode validateCode);
}
