package com.reechauto.usercenter.user.bean.req.clientDetails;

import javax.validation.constraints.NotBlank;

import com.reechauto.usercenter.user.bean.req.BaseRequest;

public class AuthorizedGrantTypesUpdateRequest extends BaseRequest {

	    private static final long serialVersionUID = 1L;
		
		@NotBlank(message = "clientId不可以为空")
		private String clientId;
		@NotBlank(message = "oldAuthorizedGrantType不可以为空")
		private String oldAuthorizedGrantType;
		@NotBlank(message = "newAuthorizedGrantType不可以为空")
		private String newAuthorizedGrantType;
		public String getClientId() {
			return clientId;
		}
		public void setClientId(String clientId) {
			this.clientId = clientId;
		}
		public String getOldAuthorizedGrantType() {
			return oldAuthorizedGrantType;
		}
		public void setOldAuthorizedGrantType(String oldAuthorizedGrantType) {
			this.oldAuthorizedGrantType = oldAuthorizedGrantType;
		}
		public String getNewAuthorizedGrantType() {
			return newAuthorizedGrantType;
		}
		public void setNewAuthorizedGrantType(String newAuthorizedGrantType) {
			this.newAuthorizedGrantType = newAuthorizedGrantType;
		}
}
