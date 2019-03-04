package com.reechauto.cloud.provider.model;

import java.io.Serializable;
import java.util.Date;

/**
* 绿驰汽车
* tableName: sms_record
* @author zhaoyb
*/
public class SmsRecord implements Serializable {

    private Long id;
    private String clientId;
    /**
     * 短信的类型，如注册、登陆、验证、通知等
     */
    private String smsType;
    /**
     * 接收手机
     */
    private String receiveMobile;
    /**
     * 是否是语音发送
     */
    private String isVoice;
    /**
     * 模板id
     */
    private String templateId;
    /**
     * 发送参数
     */
    private String sendParam;
    /**
     * 发送内容
     */
    private String sendContext;
    /**
     * 发送时间
     */
    private Date sendTime;
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
    private String signName;
    /**
     * 短信发送服务的提供者，如阿里云，腾讯云等
     */
    private String serviceProvider;
    /**
     * 已发送/未发送
     */
    private String status;
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
    public String getSmsType() {
        return smsType;
    }
    public void setSmsType(String smsType) {
        this.smsType = smsType;
    }
    public String getReceiveMobile() {
        return receiveMobile;
    }
    public void setReceiveMobile(String receiveMobile) {
        this.receiveMobile = receiveMobile;
    }
    public String getIsVoice() {
        return isVoice;
    }
    public void setIsVoice(String isVoice) {
        this.isVoice = isVoice;
    }
    public String getTemplateId() {
        return templateId;
    }
    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }
    public String getSendParam() {
        return sendParam;
    }
    public void setSendParam(String sendParam) {
        this.sendParam = sendParam;
    }
    public String getSendContext() {
        return sendContext;
    }
    public void setSendContext(String sendContext) {
        this.sendContext = sendContext;
    }
    public Date getSendTime() {
        return sendTime;
    }
    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
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
    public String getSignName() {
        return signName;
    }
    public void setSignName(String signName) {
        this.signName = signName;
    }
    public String getServiceProvider() {
        return serviceProvider;
    }
    public void setServiceProvider(String serviceProvider) {
        this.serviceProvider = serviceProvider;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}