package com.reechauto.usercenter.user.entity;

import java.io.Serializable;
import java.util.Date;

/**
* 绿驰汽车
* tableName: user_account
* @author zhaoyb
*/
public class UserAccount implements Serializable {

    /**
     * 帐号
     */
    private String accountNum;
    /**
     * account普通帐号,mobile:手机,email:邮箱，idcard:身份证号
     */
    private String accountType;
    /**
     * 帐号来源，默认系统
     */
    private String accountFrom;
    /**
     * 默认为0
     */
    private String pid;
    /**
     * 用户ID
     */
    private Long userId;
    private Date createTime;
    private static final long serialVersionUID = 1L;

    public String getAccountNum() {
        return accountNum;
    }
    public void setAccountNum(String accountNum) {
        this.accountNum = accountNum;
    }
    public String getAccountType() {
        return accountType;
    }
    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }
    public String getAccountFrom() {
        return accountFrom;
    }
    public void setAccountFrom(String accountFrom) {
        this.accountFrom = accountFrom;
    }
    public String getPid() {
        return pid;
    }
    public void setPid(String pid) {
        this.pid = pid;
    }
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}