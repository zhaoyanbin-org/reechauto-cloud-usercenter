package com.reechauto.usercenter.auth.service.validatecode.processor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reechauto.usercenter.auth.service.validatecode.bean.CodeBean;
import com.reechauto.usercenter.auth.service.validatecode.sender.MobileSender;
import com.reechauto.usercenter.common.exception.ValidateCodeException;

@Service("mobileProcessor")
public class MobileProcessor{
	@Autowired
	private MobileSender mobileSender;

	public void send(CodeBean bean, String templateId) throws ValidateCodeException {
		mobileSender.send(bean.getDeviceId(), bean.getCode(),templateId);
	}

}
