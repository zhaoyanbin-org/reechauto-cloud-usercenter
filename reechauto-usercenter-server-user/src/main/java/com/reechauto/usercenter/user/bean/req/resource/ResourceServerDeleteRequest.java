package com.reechauto.usercenter.user.bean.req.resource;

import javax.validation.constraints.NotBlank;

import com.reechauto.usercenter.user.bean.req.BaseRequest;

public class ResourceServerDeleteRequest extends BaseRequest {

	private static final long serialVersionUID = 1L;

	@NotBlank(message = "资源服务器ID不可以为空")
	private String resourceId;
	
	public String getResourceId() {
		return resourceId;
	}
	
	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}
	
}
