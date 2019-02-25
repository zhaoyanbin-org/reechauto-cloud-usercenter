package com.reechauto.usercenter.user.service.code.processor;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.reechauto.usercenter.common.exception.ValidateCodeException;
import com.reechauto.usercenter.user.bean.code.ValidateCode;
import com.reechauto.usercenter.user.bean.code.ValidateCodeConstant;
import com.reechauto.usercenter.user.bean.enums.RandomType;
import com.reechauto.usercenter.user.service.code.generator.ValidateCodeGenerator;
import com.reechauto.usercenter.user.service.code.sender.MobileCodeSender;

@Service("mobileValidateCodeProcessor")
public class MobileValidateCodeProcessor implements ValidateCodeProcessor {

	@Autowired
	@Qualifier("validateCodeGenerator")
	private ValidateCodeGenerator validateCodeGenerator;

	@Autowired
	@Qualifier("defaultMobileCodeSender")
	private MobileCodeSender mobileCodeSender;

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Override
	public void create(String deviceId, RandomType randomType, int length, int expireIn) {
		ValidateCode validateCode = validateCodeGenerator.generate(randomType, length, expireIn, deviceId);
		mobileCodeSender.send(deviceId, validateCode);
	}

	@Override
	public boolean validate(String deviceId, String vcode) throws ValidateCodeException {
		String code = stringRedisTemplate.boundValueOps(ValidateCodeConstant.VALIDATE_CODE_SAVE_PATH + deviceId).get();
		if (StringUtils.isBlank(vcode)) {
			throw new ValidateCodeException("验证码的值不能为空");
		}
		if (StringUtils.isBlank(code)) {
			throw new ValidateCodeException("验证码已过期");
		}
		if (!StringUtils.equals(vcode, code)) {
			throw new ValidateCodeException("验证码不匹配");
		}
		stringRedisTemplate.delete(ValidateCodeConstant.VALIDATE_CODE_SAVE_PATH + deviceId);
		return true;
	}

}
