package com.reechauto.usercenter.user.bean.req.resource;

import javax.validation.constraints.NotBlank;
import com.reechauto.usercenter.user.bean.req.BaseRequest;

public class ResourceScopeAddRequest extends BaseRequest {

	private static final long serialVersionUID = 1L;
	
	@NotBlank(message = "范围ID不可以为空")
	private String scope;
	@NotBlank(message = "范围说明不可以为空")
	private String scopeTips;
	@NotBlank(message = "资源服务器ID不可以为空")
	private String resourceId;
	
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
	public String getScopeTips() {
		return scopeTips;
	}
	public void setScopeTips(String scopeTips) {
		this.scopeTips = scopeTips;
	}
	public String getResourceId() {
		return resourceId;
	}
	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}
	
}
