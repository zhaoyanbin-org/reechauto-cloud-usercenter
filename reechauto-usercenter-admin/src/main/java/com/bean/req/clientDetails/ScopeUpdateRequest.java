package com.bean.req.clientDetails;

import javax.validation.constraints.NotBlank;
import com.bean.req.BaseRequest;

public class ScopeUpdateRequest extends BaseRequest {

	    private static final long serialVersionUID = 1L;
		
		@NotBlank(message = "clientId不可以为空")
		private String clientId;
		@NotBlank(message = "oldScope不可以为空")
		private String oldScope;
		@NotBlank(message = "newScope不可以为空")
		private String newScope;
		public String getClientId() {
			return clientId;
		}
		public void setClientId(String clientId) {
			this.clientId = clientId;
		}
		public String getOldScope() {
			return oldScope;
		}
		public void setOldScope(String oldScope) {
			this.oldScope = oldScope;
		}
		public String getNewScope() {
			return newScope;
		}
		public void setNewScope(String newScope) {
			this.newScope = newScope;
		}
}
