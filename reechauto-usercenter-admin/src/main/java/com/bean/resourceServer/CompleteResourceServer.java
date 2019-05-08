package com.bean.resourceServer;

import java.io.Serializable;
import java.util.List;
import com.model.ResourceScope;

public class CompleteResourceServer implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String resourceId;
	
	private List<ResourceScope> scopes;

	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

	public List<ResourceScope> getScopes() {
		return scopes;
	}

	public void setScopes(List<ResourceScope> scopes) {
		this.scopes = scopes;
	}
	
}
