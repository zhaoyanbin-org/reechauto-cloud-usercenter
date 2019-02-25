package com.reechauto.usercenter.user.service.code.generator;

import com.reechauto.usercenter.user.bean.code.ValidateCode;
import com.reechauto.usercenter.user.bean.enums.RandomType;

/**
 * 验证码生成器
 * @author zhaoyanbin
 *
 */
public interface ValidateCodeGenerator {
    ValidateCode generate(RandomType randomType,int length,int expireIn,String deviceId);
}
