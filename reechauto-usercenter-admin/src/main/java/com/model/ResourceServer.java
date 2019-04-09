package com.model;

import java.io.Serializable;

/**
* 绿驰汽车
* tableName: resource_server
* @author zhaoyb
*/
public class ResourceServer implements Serializable {

    /**
     * 微服务ID
     */
    private String resourceId;
    /**
     * 微服务名称
     */
    private String resourceName;
    private static final long serialVersionUID = 1L;

    public String getResourceId() {
        return resourceId;
    }
    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }
    public String getResourceName() {
        return resourceName;
    }
    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }
}