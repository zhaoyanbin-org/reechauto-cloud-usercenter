package com.bean.grantTypes;

import java.io.Serializable;


public class GrantTypes implements Serializable {

	private static final long serialVersionUID = 1L;
	private String grantTypes;
	private String meaning;
	public String getGrantTypes() {
		return grantTypes;
	}
	public void setGrantTypes(String grantTypes) {
		this.grantTypes = grantTypes;
	}
	public String getMeaning() {
		return meaning;
	}
	public void setMeaning(String meaning) {
		this.meaning = meaning;
	}
	public GrantTypes(String grantTypes, String meaning) {
		super();
		this.grantTypes = grantTypes;
		this.meaning = meaning;
	}
}
