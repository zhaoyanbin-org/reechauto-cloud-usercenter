package com.reechauto.usercenter.auth.entity.user;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class ReechUser implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long userId;
	private String password;
	private String realName;
	private String nickName;
	private String sex;// (男/女/保密)
	private Date birthday;
	private String imgUrl;
	private String city;
	private String userStatus;// 用户状态：OK,LOCK
	private String isDel;// Y/N(已删除/未删除)
	private List<ReechUserAccount> accountInfo;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

	public String getIsDel() {
		return isDel;
	}

	public void setIsDel(String isDel) {
		this.isDel = isDel;
	}

	public List<ReechUserAccount> getAccountInfo() {
		return accountInfo;
	}

	public void setAccountInfo(List<ReechUserAccount> accountInfo) {
		this.accountInfo = accountInfo;
	}

}
