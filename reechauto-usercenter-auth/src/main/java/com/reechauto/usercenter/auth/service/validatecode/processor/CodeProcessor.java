package com.reechauto.usercenter.auth.service.validatecode.processor;

import com.reechauto.usercenter.auth.service.validatecode.bean.CodeBean;
import com.reechauto.usercenter.auth.service.validatecode.bean.CodeType;

public interface CodeProcessor {
	/**
	 * 创建校验码
	 * 
	 * @param count
	 * @param codeType
	 * @param expireId
	 * @param deviceId
	 */
	CodeBean create(int count, CodeType codeType, int expireId, String deviceId) throws Exception;

	/**
	 * 校验验证码
	 * 
	 * @param deviceId
	 * @param verifyCode
	 */
	boolean validate(String deviceId, String verifyCode) throws Exception;

}
