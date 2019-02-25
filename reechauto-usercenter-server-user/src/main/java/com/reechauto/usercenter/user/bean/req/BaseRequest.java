package com.reechauto.usercenter.user.bean.req;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class BaseRequest implements Serializable {
	private static final long serialVersionUID = 1L;
	@NotBlank(message = "客户端类型不可以为空")
	private String ct;
	@NotBlank(message = "客户端版本不可以为空")
	private String cv;
	@NotNull(message = "时间戳不可以为NULL")
	private Long timeStamp;

	public String getCt() {
		return ct;
	}

	public void setCt(String ct) {
		this.ct = ct;
	}

	public String getCv() {
		return cv;
	}

	public void setCv(String cv) {
		this.cv = cv;
	}

	public Long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Long timeStamp) {
		this.timeStamp = timeStamp;
	}

}
