package com.reechauto.usercenter.auth.entity;

import java.io.Serializable;

/**
* 绿驰汽车
* tableName: resource_scope
* @author zhaoyb
*/
public class ResourceScope implements Serializable {

    private Integer id;
    /**
     * 范围ID
     */
    private String scope;
    /**
     * 范围说明
     */
    private String scopeTips;
    /**
     * 所属微服务ID
     */
    private String resourceId;
    private static final long serialVersionUID = 1L;

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
    public String getScopeTips() {
        return scopeTips;
    }
    public void setScopeTips(String scopeTips) {
        this.scopeTips = scopeTips;
    }
    public String getResourceId() {
        return resourceId;
    }
    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }
}