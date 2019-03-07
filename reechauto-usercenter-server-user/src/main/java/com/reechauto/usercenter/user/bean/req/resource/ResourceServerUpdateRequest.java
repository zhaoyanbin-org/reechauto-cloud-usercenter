package com.reechauto.usercenter.user.bean.req.resource;

import javax.validation.constraints.NotBlank;

import com.reechauto.usercenter.user.bean.req.BaseRequest;

public class ResourceServerUpdateRequest extends BaseRequest {

	private static final long serialVersionUID = 1L;

	@NotBlank(message = "被修改资源服务器ID不可以为空")
	private String oldResourceId;
	@NotBlank(message = "新设置资源服务器ID不可以为空")
	private String newResourceId;
	@NotBlank(message = "新设置资源服务器名称不可以为空")
	private String newResourceName;
	public String getOldResourceId() {
		return oldResourceId;
	}
	public void setOldResourceId(String oldResourceId) {
		this.oldResourceId = oldResourceId;
	}
	public String getNewResourceId() {
		return newResourceId;
	}
	public void setNewResourceId(String newResourceId) {
		this.newResourceId = newResourceId;
	}
	public String getNewResourceName() {
		return newResourceName;
	}
	public void setNewResourceName(String newResourceName) {
		this.newResourceName = newResourceName;
	}
	
	
}
