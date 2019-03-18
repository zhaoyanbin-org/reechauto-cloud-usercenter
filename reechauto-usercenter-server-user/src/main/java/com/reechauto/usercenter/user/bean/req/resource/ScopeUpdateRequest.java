package com.reechauto.usercenter.user.bean.req.resource;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import com.reechauto.usercenter.user.bean.req.BaseRequest;

public class ScopeUpdateRequest extends BaseRequest {

    private static final long serialVersionUID = 1L;
	
    @NotNull(message = "id不可以为空")
    private Integer id;
    @NotBlank(message = "oldScope不可以为空")
    private String oldScope;
    @NotBlank(message = "newScope不可以为空")
    private String newScope;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
