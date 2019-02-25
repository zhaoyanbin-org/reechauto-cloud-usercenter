package com.reechauto.usercenter.auth.service.validatecode.processor;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reechauto.usercenter.auth.service.validatecode.bean.CodeBean;
import com.reechauto.usercenter.auth.service.validatecode.bean.CodeType;
import com.reechauto.usercenter.auth.service.validatecode.generator.CodeGenerator;
import com.reechauto.usercenter.common.exception.ValidateCodeException;

@Service("codeProcessor")
public class CodeProcessorImpl implements CodeProcessor {
	@Autowired
	private CodeGenerator codeGenerator;

	@Override
	public CodeBean create(int count, CodeType codeType, int expireId, String deviceId)
			throws Exception {
		count = count <= 0 ? 4 : count;
		codeType = codeType == null ? CodeType.LettersAndNumbers : codeType;
		expireId = expireId <= 0 ? 60 : expireId;
		if (StringUtils.isBlank(deviceId)) {
			throw new ValidateCodeException("设备ID不允许为空！");
		}
		CodeBean bean = codeGenerator.generate(count, codeType, expireId, deviceId);
		return bean;
	}

	@Override
	public boolean validate(String deviceId, String verifyCode) throws Exception {
		if (StringUtils.isBlank(deviceId)) {
			throw new ValidateCodeException("设备ID不允许为空！");
		}
		if (StringUtils.isBlank(verifyCode)) {
			throw new ValidateCodeException("验证码的值不能为空");
		}
		String code = codeGenerator.queryCode(deviceId);
		if (StringUtils.isBlank(code)) {
			throw new ValidateCodeException("验证码已过期");
		}
		if (!StringUtils.equals(verifyCode, code)) {
			throw new ValidateCodeException("验证码不匹配");
		}
		codeGenerator.deleteCode(deviceId);
		return true;
	}

}
