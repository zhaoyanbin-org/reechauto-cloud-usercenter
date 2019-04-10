package com.reechauto.usercenter.user.bean.req.code;

import javax.validation.constraints.NotBlank;

import com.reechauto.usercenter.user.bean.req.BaseRequest;

public class MobileCodeVerifyRequest extends BaseRequest {
	private static final long serialVersionUID = 1L;

	@NotBlank(message = "发送手机号不可以为空")
	private String mobile;
	@NotBlank(message = "验证码不可以为空")
	private String vcode;

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getVcode() {
		return vcode;
	}

	public void setVcode(String vcode) {
		this.vcode = vcode;
	}

}
