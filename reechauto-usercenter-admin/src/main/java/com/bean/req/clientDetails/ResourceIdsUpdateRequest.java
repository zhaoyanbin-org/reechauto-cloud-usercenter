package com.bean.req.clientDetails;

import javax.validation.constraints.NotBlank;
import com.bean.req.BaseRequest;

public class ResourceIdsUpdateRequest extends BaseRequest {

    private static final long serialVersionUID = 1L;
	
	@NotBlank(message = "clientId不可以为空")
	private String clientId;
	@NotBlank(message = "oldResourceId不可以为空")
	private String oldResourceId;
	@NotBlank(message = "newResourceIds不可以为空")
	private String newResourceId;
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
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
}
