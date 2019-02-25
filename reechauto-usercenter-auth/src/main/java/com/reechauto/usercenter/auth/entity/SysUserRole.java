package com.reechauto.usercenter.auth.entity;

import java.io.Serializable;
import java.util.Date;

/**
* 绿驰汽车
* tableName: sys_user_role
* @author zhaoyb
*/
public class SysUserRole implements Serializable {

	private Long userId;
    private String roleId;
    private Date createTime;
    private Long createBy;
    private Date updateTime;
    private Long updateBy;
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
	public Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public Long getCreateBy() {
        return createBy;
    }
    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }
    public Date getUpdateTime() {
        return updateTime;
    }
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    public Long getUpdateBy() {
        return updateBy;
    }
    public void setUpdateBy(Long updateBy) {
        this.updateBy = updateBy;
    }
}