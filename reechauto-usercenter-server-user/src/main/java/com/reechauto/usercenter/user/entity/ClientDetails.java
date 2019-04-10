package com.reechauto.usercenter.user.entity;

import java.io.Serializable;

/**
* 绿驰汽车
* tableName: client_details
* @author zhaoyb
*/
public class ClientDetails implements Serializable {

    private String clientId;
    private String resourceIds;
    private String clientSecret;
    private String scope;
    private String authorizedGrantTypes;
    private String webServerRedirectUri;
    private String authorities;
    private Integer accessTokenValidity;
    private Integer refreshTokenValidity;
    /**
     * 可以用该字段来存储关于客户端的一些其他信息,JSON格式
     */
    private String additionalInformation;
    /**
     * 默认值为 'false', 可选值包括 'true','false', 'read','write',若该值为'true'或支持的scope值,则会跳过用户Approve的页面, 直接授权
     */
    private String autoapprove;
    private static final long serialVersionUID = 1L;

    public String getClientId() {
        return clientId;
    }
    public void setClientId(String clientId) {
        this.clientId = clientId;
    }
    public String getResourceIds() {
        return resourceIds;
    }
    public void setResourceIds(String resourceIds) {
        this.resourceIds = resourceIds;
    }
    public String getClientSecret() {
        return clientSecret;
    }
    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }
    public String getScope() {
        return scope;
    }
    public void setScope(String scope) {
        this.scope = scope;
    }
    public String getAuthorizedGrantTypes() {
        return authorizedGrantTypes;
    }
    public void setAuthorizedGrantTypes(String authorizedGrantTypes) {
        this.authorizedGrantTypes = authorizedGrantTypes;
    }
    public String getWebServerRedirectUri() {
        return webServerRedirectUri;
    }
    public void setWebServerRedirectUri(String webServerRedirectUri) {
        this.webServerRedirectUri = webServerRedirectUri;
    }
    public String getAuthorities() {
        return authorities;
    }
    public void setAuthorities(String authorities) {
        this.authorities = authorities;
    }
    public Integer getAccessTokenValidity() {
        return accessTokenValidity;
    }
    public void setAccessTokenValidity(Integer accessTokenValidity) {
        this.accessTokenValidity = accessTokenValidity;
    }
    public Integer getRefreshTokenValidity() {
        return refreshTokenValidity;
    }
    public void setRefreshTokenValidity(Integer refreshTokenValidity) {
        this.refreshTokenValidity = refreshTokenValidity;
    }
    public String getAdditionalInformation() {
        return additionalInformation;
    }
    public void setAdditionalInformation(String additionalInformation) {
        this.additionalInformation = additionalInformation;
    }
    public String getAutoapprove() {
        return autoapprove;
    }
    public void setAutoapprove(String autoapprove) {
        this.autoapprove = autoapprove;
    }
}