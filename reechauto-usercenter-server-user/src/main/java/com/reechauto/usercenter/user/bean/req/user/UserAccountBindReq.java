package com.reechauto.usercenter.user.bean.req.user;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.reechauto.usercenter.user.bean.req.BaseRequest;

public class UserAccountBindReq extends BaseRequest {
	private static final long serialVersionUID = 1L;

	@NotNull(message = "用户ID不可以为NULL")
	private Long userId;

	@NotBlank(message = "注册帐号不可以为空")
	private String accountNum;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getAccountNum() {
		return accountNum;
	}

	public void setAccountNum(String accountNum) {
		this.accountNum = accountNum;
	}

}
