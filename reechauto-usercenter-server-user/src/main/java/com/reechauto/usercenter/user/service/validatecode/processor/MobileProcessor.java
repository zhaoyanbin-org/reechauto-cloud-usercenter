package com.reechauto.usercenter.user.service.validatecode.processor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reechauto.usercenter.common.exception.ValidateCodeException;
import com.reechauto.usercenter.user.service.validatecode.bean.CodeBean;
import com.reechauto.usercenter.user.service.validatecode.sender.MobileSender;

@Service("mobileProcessor")
public class MobileProcessor{
	@Autowired
	private MobileSender mobileSender;

	public void send(CodeBean bean, String templateId) throws ValidateCodeException {
		mobileSender.send(bean.getDeviceId(), bean.getCode(),templateId);
	}

}
