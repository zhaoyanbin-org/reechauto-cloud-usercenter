package com.reechauto.usercenter.auth.service.validatecode.generator;

import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import com.reechauto.usercenter.auth.entity.constant.ReechAuthConstant;
import com.reechauto.usercenter.auth.service.validatecode.bean.CodeBean;
import com.reechauto.usercenter.auth.service.validatecode.bean.CodeType;

/**
 * 验证码生成器
 * 
 * @author zhaoyanbin
 *
 */
@Component("codeGenerator")
public class CodeGenerator {
	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	/**
	 * 
	 * @param count    长度
	 * @param codeType 类型
	 * @param expireId 过期时间
	 * @param deviceId 设备ID
	 * @return
	 */
	public CodeBean generate(int count, CodeType codeType, int expireId, String deviceId) {
		String code = getRandomStr(count, codeType);
		stringRedisTemplate.boundValueOps(ReechAuthConstant.VALIDATE_CODE_SAVE_PATH + deviceId).set(code, expireId,
				TimeUnit.SECONDS);
		return new CodeBean(code, expireId, deviceId);
	}
	
	/**
	 * 
	 * @param deviceId 设备Id
	 * @return
	 */
	public String queryCode(String deviceId) {
		return stringRedisTemplate.boundValueOps(ReechAuthConstant.VALIDATE_CODE_SAVE_PATH + deviceId).get();
	}
	
	/**
	 * 删除验证码
	 * @param deviceId
	 * @return
	 */
	public boolean deleteCode(String deviceId) {
		return stringRedisTemplate.delete(ReechAuthConstant.VALIDATE_CODE_SAVE_PATH + deviceId);
	}

	private String getRandomStr(int count, CodeType codeType) {
		switch (codeType) {
		case Letters:
			return RandomStringUtils.random(count, true, false);
		case Numbers:
			return RandomStringUtils.random(count, false, true);
		default:
			return RandomStringUtils.random(count, true, true);
		}
	}
}
