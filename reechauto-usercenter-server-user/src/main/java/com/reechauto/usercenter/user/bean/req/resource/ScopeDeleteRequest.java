package com.reechauto.usercenter.user.bean.req.resource;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import com.reechauto.usercenter.user.bean.req.BaseRequest;

public class ScopeDeleteRequest extends BaseRequest {

    private static final long serialVersionUID = 1L;
	
    @NotNull(message = "id不可以为空")
    private Integer id;
	@NotBlank(message = "范围ID不可以为空")
	private String scope;
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
}
