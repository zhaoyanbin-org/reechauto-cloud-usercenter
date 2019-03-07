package com.reechauto.usercenter.user.bean.req.resource;

import javax.validation.constraints.NotNull;
import com.reechauto.usercenter.user.bean.req.BaseRequest;

public class ResourceScopeDeleteRequest extends BaseRequest {
   
	private static final long serialVersionUID = 1L;
	@NotNull(message = "id不可以为空")
	private Integer id;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	 
}
