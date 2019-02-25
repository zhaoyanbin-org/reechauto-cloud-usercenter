package com.reechauto.usercenter.auth.entity;

import java.io.Serializable;

/**
* 绿驰汽车
* tableName: sys_role
* @author zhaoyb
*/
public class SysRole implements Serializable {

    /**
     * 角色ID
     */
    private String roleId;
    /**
     * 角色名称
     */
    private String roleName;
    
    private static final long serialVersionUID = 1L;

    public String getRoleId() {
        return roleId;
    }
    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
    public String getRoleName() {
        return roleName;
    }
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}