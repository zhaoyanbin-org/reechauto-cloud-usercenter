package com.reechauto.usercenter.auth.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.reechauto.usercenter.auth.service.validatecode.bean.CodeBean;
import com.reechauto.usercenter.auth.service.validatecode.bean.CodeType;
import com.reechauto.usercenter.auth.service.validatecode.processor.CodeProcessor;
import com.reechauto.usercenter.auth.service.validatecode.processor.ImageProcessor;
import com.reechauto.usercenter.auth.service.validatecode.processor.MobileProcessor;
import com.reechauto.usercenter.common.resp.ResponseData;
import com.reechauto.usercenter.common.utils.code.IdGenerator;

@RestController
@RequestMapping("nologin")
public class NoLoginController {
	@Autowired
	private CodeProcessor codeProcessor;
	@Autowired
	private ImageProcessor imageProcessor;
	@Autowired
	private MobileProcessor mobileProcessor;

	@PostMapping("/mobile/send")
	public ResponseData mobileSend(@RequestParam(name = "mobile", required = true) String mobile,
			@RequestParam(name = "codeType", required = false, defaultValue = "numbers") String codeType,
			@RequestParam(name = "count", required = false, defaultValue = "4") int count,
			@RequestParam(name = "expireId", required = false, defaultValue = "60") int expireId) throws Exception {
		CodeBean codeBean = codeProcessor.create(count, CodeType.get(codeType), expireId, mobile);
		mobileProcessor.send(codeBean, "templateId");
		return ResponseData.ok();
	}

	@GetMapping(value = "/image/vcode", produces = "image/jpeg")
	public void imageCode(HttpServletResponse resp,
			@RequestParam(name = "codeType", required = false, defaultValue = "numbers") String codeType,
			@RequestParam(name = "count", required = false, defaultValue = "4") int count,
			@RequestParam(name = "expireId", required = false, defaultValue = "60") int expireId) throws Exception {
		String deviceId = IdGenerator.uuid();
		CodeBean codeBean = codeProcessor.create(count, CodeType.get(codeType), expireId, deviceId);
		imageProcessor.send(codeBean, resp);
	}

}
