package com.reechauto.usercenter.user.bean.req.resource;

import javax.validation.constraints.NotNull;
import com.reechauto.usercenter.user.bean.req.BaseRequest;

public class ResourceScopeUpdateRequest extends BaseRequest {
    private static final long serialVersionUID = 1L;
	
    @NotNull(message = "id不可以为空")
    private Integer id;
	private String scope;
	private String scopeTips;
	private String resourceId;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
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
