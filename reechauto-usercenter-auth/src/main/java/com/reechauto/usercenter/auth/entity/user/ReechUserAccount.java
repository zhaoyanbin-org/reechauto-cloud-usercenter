package com.reechauto.usercenter.auth.entity.user;

import java.io.Serializable;

public class ReechUserAccount implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String accountNum;
	private String accountType;
	private Long userId;

	public String getAccountNum() {
		return accountNum;
	}

	public void setAccountNum(String accountNum) {
		this.accountNum = accountNum;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

}
