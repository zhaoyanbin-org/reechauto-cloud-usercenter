package com.reechauto.usercenter.user.bean.req.code;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import com.reechauto.usercenter.user.bean.req.BaseRequest;

public class MobileCodeSendRequest extends BaseRequest {
	private static final long serialVersionUID = 1L;

	@NotBlank(message="发送手机号不可以为空")
	private String mobile;
	@NotBlank(message="验证码类型不可以为空(Letters,Numbers,LettersAndNumbers)")
	private String randomType;
	@Min(value=1,message="验证码最小长度为1")
	private int length;
	@Min(value=10,message="验证码过期时间不能小于10秒")
	private int expireIn;

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getRandomType() {
		return randomType;
	}

	public void setRandomType(String randomType) {
		this.randomType = randomType;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getExpireIn() {
		return expireIn;
	}

	public void setExpireIn(int expireIn) {
		this.expireIn = expireIn;
	}

}
