package com.reechauto.usercenter.user.bean.req.user;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import com.reechauto.usercenter.user.bean.req.BaseRequest;

public class UserRegisterReq extends BaseRequest {
	private static final long serialVersionUID = 1L;

	@NotBlank(message = "注册帐号不可以为空")
	//@Pattern(regexp = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z_]{4,10}$", message = "帐号不能由数字开头，应由4-10位的字母,数字,下划线组成")
	private String accountNum;
    //@Pattern(regexp = "(^$)|(^((?:19|20)\\d\\d)-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$)", message = "日期格式为yyyy-MM-dd")
	//@Pattern(regexp = "^1[345678][0-9]{9}$", message = "手机格式不正确")
	@Pattern(regexp = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,20}$", message = "密码由6-20位的字母和数字组成")
	private String password;

	public String getAccountNum() {
		return accountNum;
	}

	public void setAccountNum(String accountNum) {
		this.accountNum = accountNum;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
