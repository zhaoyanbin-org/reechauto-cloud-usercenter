package com.bean.req.clientDetails;

import javax.validation.constraints.NotBlank;
import com.bean.req.BaseRequest;

public class ClientDetailsUpdateRequest extends BaseRequest {

	private static final long serialVersionUID = 1L;
	
	@NotBlank(message = "oldClientId不可以为空")
	private String oldClientId;
	private String newClientId;
	private String newResourceIds;
	private String newClientSecret;
	private String newScope;
	private String newAuthorizedGrantTypes;
	private String newWebServerRedirectUri;
	private String newAuthorities;
	private Integer newAccessTokenValidity;
	private Integer newRefreshTokenValidity;
	private String newAdditionalInformation;
	private String newAutoapprove;
	public String getOldClientId() {
		return oldClientId;
	}
	public void setOldClientId(String oldClientId) {
		this.oldClientId = oldClientId;
	}
	public String getNewClientId() {
		return newClientId;
	}
	public void setNewClientId(String newClientId) {
		this.newClientId = newClientId;
	}
	public String getNewResourceIds() {
		return newResourceIds;
	}
	public void setNewResourceIds(String newResourceIds) {
		this.newResourceIds = newResourceIds;
	}
	public String getNewClientSecret() {
		return newClientSecret;
	}
	public void setNewClientSecret(String newClientSecret) {
		this.newClientSecret = newClientSecret;
	}
	public String getNewScope() {
		return newScope;
	}
	public void setNewScope(String newScope) {
		this.newScope = newScope;
	}
	public String getNewAuthorizedGrantTypes() {
		return newAuthorizedGrantTypes;
	}
	public void setNewAuthorizedGrantTypes(String newAuthorizedGrantTypes) {
		this.newAuthorizedGrantTypes = newAuthorizedGrantTypes;
	}
	public String getNewWebServerRedirectUri() {
		return newWebServerRedirectUri;
	}
	public void setNewWebServerRedirectUri(String newWebServerRedirectUri) {
		this.newWebServerRedirectUri = newWebServerRedirectUri;
	}
	public String getNewAuthorities() {
		return newAuthorities;
	}
	public void setNewAuthorities(String newAuthorities) {
		this.newAuthorities = newAuthorities;
	}
	public Integer getNewAccessTokenValidity() {
		return newAccessTokenValidity;
	}
	public void setNewAccessTokenValidity(Integer newAccessTokenValidity) {
		this.newAccessTokenValidity = newAccessTokenValidity;
	}
	public Integer getNewRefreshTokenValidity() {
		return newRefreshTokenValidity;
	}
	public void setNewRefreshTokenValidity(Integer newRefreshTokenValidity) {
		this.newRefreshTokenValidity = newRefreshTokenValidity;
	}
	public String getNewAdditionalInformation() {
		return newAdditionalInformation;
	}
	public void setNewAdditionalInformation(String newAdditionalInformation) {
		this.newAdditionalInformation = newAdditionalInformation;
	}
	public String getNewAutoapprove() {
		return newAutoapprove;
	}
	public void setNewAutoapprove(String newAutoapprove) {
		this.newAutoapprove = newAutoapprove;
	}
	
}
