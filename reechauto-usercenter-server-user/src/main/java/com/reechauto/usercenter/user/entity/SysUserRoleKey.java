package com.reechauto.usercenter.user.entity;

import java.io.Serializable;

/**
* 绿驰汽车
* tableName: sys_user_role
* @author zhaoyb
*/
public class SysUserRoleKey implements Serializable {

    private Long userId;
    private String roleId;
    private static final long serialVersionUID = 1L;

    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public String getRoleId() {
        return roleId;
    }
    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
}