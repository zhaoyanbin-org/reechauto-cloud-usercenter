package com.reechauto.usercenter.user.entity;

import java.io.Serializable;
import java.util.Date;

/**
* 绿驰汽车
* tableName: user_details
* @author zhaoyb
*/
public class UserDetails implements Serializable {

    /**
     * 用户ID
     */
    private Long userId;
    private String password;
    /**
     * 真实名称
     */
    private String realName;
    /**
     * 用户昵称
     */
    private String nickName;
    /**
     * (男/女/保密)
     */
    private String sex;
    /**
     * 生日
     */
    private Date birthday;
    /**
     * 头像url
     */
    private String imgUrl;
    /**
     * 城市
     */
    private String city;
    /**
     * 用户状态：OK,LOCK
     */
    private String userStatus;
    /**
     * Y/N(已删除/未删除)
     */
    private String isDel;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date modifyTime;
    private static final long serialVersionUID = 1L;

    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getRealName() {
        return realName;
    }
    public void setRealName(String realName) {
        this.realName = realName;
    }
    public String getNickName() {
        return nickName;
    }
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
    public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public Date getBirthday() {
        return birthday;
    }
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
    public String getImgUrl() {
        return imgUrl;
    }
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getUserStatus() {
        return userStatus;
    }
    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }
    public String getIsDel() {
        return isDel;
    }
    public void setIsDel(String isDel) {
        this.isDel = isDel;
    }
    public Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public Date getModifyTime() {
        return modifyTime;
    }
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}