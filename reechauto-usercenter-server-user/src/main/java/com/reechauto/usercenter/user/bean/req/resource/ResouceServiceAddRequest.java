package com.reechauto.usercenter.user.bean.req.resource;

import javax.validation.constraints.NotBlank;

import com.reechauto.usercenter.user.bean.req.BaseRequest;

public class ResouceServiceAddRequest extends BaseRequest {
	private static final long serialVersionUID = 1L;

	@NotBlank(message = "资源服务器ID不可以为空")
	private String resourceId;
	@NotBlank(message = "资源服务器名称不可以为空")
	private String resourceName;

	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

}
