package com.reechauto.usercenter.user.bean.req.clientDetails;

import javax.validation.constraints.NotBlank;
import com.reechauto.usercenter.user.bean.req.BaseRequest;

public class ScopeAddRequest extends BaseRequest {

    private static final long serialVersionUID = 1L;
	
	@NotBlank(message = "clientId不可以为空")
	private String clientId;
	@NotBlank(message = "scope不可以为空")
	private String scope;
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
}
