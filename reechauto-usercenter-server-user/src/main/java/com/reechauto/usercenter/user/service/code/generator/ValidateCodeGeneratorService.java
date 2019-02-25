package com.reechauto.usercenter.user.service.code.generator;

import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.reechauto.usercenter.user.bean.code.ValidateCode;
import com.reechauto.usercenter.user.bean.code.ValidateCodeConstant;
import com.reechauto.usercenter.user.bean.enums.RandomType;

@Service("validateCodeGenerator")
public class ValidateCodeGeneratorService implements ValidateCodeGenerator {
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	

	@Override
	public ValidateCode generate(RandomType randomType, int length, int expireIn,String deviceId) {
		if (randomType == null) {
			randomType = RandomType.LettersAndNumbers;
		}
		if (length <= 0) {
			length = 4;
		}
		if (expireIn <= 0) {
			expireIn = 60;
		}
		String code = getRandomStr(length, randomType);
		stringRedisTemplate.boundValueOps(ValidateCodeConstant.VALIDATE_CODE_SAVE_PATH+deviceId).set(code, expireIn,TimeUnit.SECONDS);
		return new ValidateCode(code, expireIn);
	}
	
	private String getRandomStr(int count, RandomType randomType) {
		switch (randomType) {
		case Letters:
			return RandomStringUtils.random(count, true, false);
		case Numbers:
			return RandomStringUtils.random(count, false, true);
		default:
			return RandomStringUtils.random(count, true, true);
		}
	}

}
