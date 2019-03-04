package com.reechauto.cloud.provider.model;

import java.io.Serializable;
import java.util.Date;

/**
* 绿驰汽车
* tableName: mail_record
* @author zhaoyb
*/
public class MailRecord implements Serializable {

    private Long id;
    private String clientId;
    /**
     * 通道
     */
    private String channel;
    /**
     * 邮件主题
     */
    private String sendSubject;
    /**
     * 邮件模板
     */
    private String templateId;
    /**
     * 发送的方式，HtmlEmail/TextEmail
     */
    private String sendPattern;
    /**
     * 发送状态，成功/失败
     */
    private String status;
    /**
     * 发送时间
     */
    private Date sendTime;
    /**
     * 是否用模板，true/false
     */
    private String isTemplate;
    /**
     * 网关发送时间
     */
    private Date gatewaySendTime;
    /**
     * 网关发送状态
     */
    private String gatewaySendStatus;
    /**
     * 网关发送结果
     */
    private String gatewaySendRet;
    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getClientId() {
        return clientId;
    }
    public void setClientId(String clientId) {
        this.clientId = clientId;
    }
    public String getChannel() {
        return channel;
    }
    public void setChannel(String channel) {
        this.channel = channel;
    }
    public String getSendSubject() {
        return sendSubject;
    }
    public void setSendSubject(String sendSubject) {
        this.sendSubject = sendSubject;
    }
    public String getTemplateId() {
        return templateId;
    }
    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }
    public String getSendPattern() {
        return sendPattern;
    }
    public void setSendPattern(String sendPattern) {
        this.sendPattern = sendPattern;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public Date getSendTime() {
        return sendTime;
    }
    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }
    public String getIsTemplate() {
        return isTemplate;
    }
    public void setIsTemplate(String isTemplate) {
        this.isTemplate = isTemplate;
    }
    public Date getGatewaySendTime() {
        return gatewaySendTime;
    }
    public void setGatewaySendTime(Date gatewaySendTime) {
        this.gatewaySendTime = gatewaySendTime;
    }
    public String getGatewaySendStatus() {
        return gatewaySendStatus;
    }
    public void setGatewaySendStatus(String gatewaySendStatus) {
        this.gatewaySendStatus = gatewaySendStatus;
    }
    public String getGatewaySendRet() {
        return gatewaySendRet;
    }
    public void setGatewaySendRet(String gatewaySendRet) {
        this.gatewaySendRet = gatewaySendRet;
    }
}