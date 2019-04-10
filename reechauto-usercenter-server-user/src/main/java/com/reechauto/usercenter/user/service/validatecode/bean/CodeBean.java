package com.reechauto.usercenter.user.service.validatecode.bean;

import java.time.LocalDateTime;

public class CodeBean {
	private String code;
	private LocalDateTime expireTime;
	private String deviceId;

	public CodeBean(String code, int expireIn, String deviceId) {
		this.code = code;
		this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
		this.deviceId = deviceId;
	}

	public CodeBean(String code, LocalDateTime expireTime, String deviceId) {
		this.code = code;
		this.expireTime = expireTime;
		this.deviceId = deviceId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public LocalDateTime getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(LocalDateTime expireTime) {
		this.expireTime = expireTime;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	/**
	 * 是否过期
	 * 
	 * @return
	 */
	public boolean isExpried() {
		return LocalDateTime.now().isAfter(getExpireTime());
	}
}
