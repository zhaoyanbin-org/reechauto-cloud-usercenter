package com.reechauto.usercenter.user.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reechauto.usercenter.common.resp.ResponseData;
import com.reechauto.usercenter.user.bean.enums.RandomType;
import com.reechauto.usercenter.user.bean.req.code.MobileCodeSendRequest;
import com.reechauto.usercenter.user.bean.req.code.MobileCodeVerifyRequest;
import com.reechauto.usercenter.user.service.code.processor.ValidateCodeProcessor;

@RestController
public class CodeController extends BaseController {
	@Autowired
	@Qualifier("mobileValidateCodeProcessor")
	private ValidateCodeProcessor mobileValidateCodeProcessor;

	@PostMapping("/moblie/send")
	public ResponseData sendMobileCode(@Valid MobileCodeSendRequest req, BindingResult result) {
		mobileValidateCodeProcessor.create(req.getMobile(), RandomType.get(req.getRandomType()), req.getLength(), req.getExpireIn());
		return ResponseData.ok("短信已发送");

	}

	@PostMapping("/moblie/verify")
	public ResponseData verifyMobileCode(@Valid MobileCodeVerifyRequest req, BindingResult result) {
		boolean flag = mobileValidateCodeProcessor.validate(req.getMobile(), req.getVcode());
		if (flag) {
			return ResponseData.ok("短信已发送");
		} else {
			return ResponseData.error("验证码错误");
		}

	}

}
