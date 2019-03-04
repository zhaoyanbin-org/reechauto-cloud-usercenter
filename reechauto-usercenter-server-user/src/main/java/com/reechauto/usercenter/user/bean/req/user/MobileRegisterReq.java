package com.reechauto.usercenter.user.bean.req.user;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import com.reechauto.usercenter.user.bean.req.BaseRequest;

public class MobileRegisterReq extends BaseRequest {
	private static final long serialVersionUID = 1L;

	@Pattern(regexp = "^1[345678][0-9]{9}$", message = "手机格式不正确")
	private String mobile;
	@NotBlank(message = "验证码不可以为空")
	private String vcode;
	@Pattern(regexp = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,20}$", message = "密码由6-20位的字母和数字组成")
	private String password;

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
