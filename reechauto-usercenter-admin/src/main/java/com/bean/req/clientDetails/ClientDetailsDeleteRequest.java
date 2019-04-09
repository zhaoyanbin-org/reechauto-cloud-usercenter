package com.bean.req.clientDetails;

import javax.validation.constraints.NotBlank;
import com.bean.req.BaseRequest;

public class ClientDetailsDeleteRequest extends BaseRequest {

	private static final long serialVersionUID = 1L;
	
	@NotBlank(message = "客户端ID不可以为空")
	private String clientId;

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	
}
