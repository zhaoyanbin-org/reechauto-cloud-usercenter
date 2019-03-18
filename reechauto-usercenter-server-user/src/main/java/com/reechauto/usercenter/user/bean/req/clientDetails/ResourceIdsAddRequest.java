package com.reechauto.usercenter.user.bean.req.clientDetails;

import javax.validation.constraints.NotBlank;
import com.reechauto.usercenter.user.bean.req.BaseRequest;

public class ResourceIdsAddRequest extends BaseRequest {

	private static final long serialVersionUID = 1L;
	
	@NotBlank(message = "clientId不可以为空")
	private String clientId;
	@NotBlank(message = "resourceId不可以为空")
	private String resourceId;
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getResourceId() {
		return resourceId;
	}
	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}
	
}
