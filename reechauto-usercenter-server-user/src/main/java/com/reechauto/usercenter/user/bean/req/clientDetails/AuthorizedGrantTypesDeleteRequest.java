package com.reechauto.usercenter.user.bean.req.clientDetails;

import javax.validation.constraints.NotBlank;

import com.reechauto.usercenter.user.bean.req.BaseRequest;

public class AuthorizedGrantTypesDeleteRequest extends BaseRequest {

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
