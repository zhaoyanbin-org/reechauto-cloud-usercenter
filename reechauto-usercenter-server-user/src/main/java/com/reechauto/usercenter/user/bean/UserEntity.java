package com.reechauto.usercenter.user.bean;

import java.io.Serializable;
import java.util.List;

import com.reechauto.usercenter.user.entity.UserAccount;
import com.reechauto.usercenter.user.entity.UserDetails;

public class UserEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	private UserDetails userDetails;
	private List<UserAccount> userAccount;

	public UserDetails getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(UserDetails userDetails) {
		this.userDetails = userDetails;
	}

	public List<UserAccount> getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(List<UserAccount> userAccount) {
		this.userAccount = userAccount;
	}

}
