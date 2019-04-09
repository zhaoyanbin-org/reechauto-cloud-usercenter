package com.bean.req.clientDetails;

import javax.validation.constraints.NotBlank;
import com.bean.req.BaseRequest;


public class AuthorizedGrantTypesAddRequest extends BaseRequest {

    private static final long serialVersionUID = 1L;
	
	@NotBlank(message = "clientId不可以为空")
	private String clientId;
	@NotBlank(message = "authorizedGrantType不可以为空")
	private String authorizedGrantType;
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getAuthorizedGrantType() {
		return authorizedGrantType;
	}
	public void setAuthorizedGrantType(String authorizedGrantType) {
		this.authorizedGrantType = authorizedGrantType;
	}
}
