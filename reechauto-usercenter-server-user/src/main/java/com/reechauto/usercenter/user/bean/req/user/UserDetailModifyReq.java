package com.reechauto.usercenter.user.bean.req.user;

import javax.validation.constraints.NotNull;

import com.reechauto.usercenter.user.bean.req.BaseRequest;

public class UserDetailModifyReq extends BaseRequest {
	private static final long serialVersionUID = 1L;

	@NotNull(message = "用户ID不可以为NULL")
	private Long userId;
	/**
	 * 真实名称
	 */
	private String realName;
	/**
	 * 用户昵称
	 */
	private String nickName;
	/**
	 * (男/女/保密)
	 */
	private String sex;
	/**
	 * 生日
	 */
	private String birthday;
	/**
	 * 头像url
	 */
	private String imgUrl;
	/**
	 * 城市
	 */
	private String city;
	/**
	 * 用户状态：OK,LOCK
	 */
	private String userStatus;
	/**
	 * Y/N(已删除/未删除)
	 */
	private String isDel;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
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

}
