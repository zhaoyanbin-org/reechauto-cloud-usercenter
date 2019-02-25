package com.reechauto.usercenter.user.service.code.processor;

import com.reechauto.usercenter.common.exception.ValidateCodeException;
import com.reechauto.usercenter.user.bean.enums.RandomType;

public interface ValidateCodeProcessor {

	/**
	 * 创建校验码
	 * @param deviceId
	 * @param randomType
	 * @param length
	 * @param expireIn
	 */
    void create(String deviceId,RandomType randomType, int length, int expireIn);


    /**
     * 校验验证码
     * @param deviceId
     * @param vcode
     */
    boolean validate(String deviceId,String vcode) throws ValidateCodeException;

}
