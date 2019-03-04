package com.reechauto.cloud.provider.model;

import java.io.Serializable;
import java.util.Date;

/**
* 绿驰汽车
* tableName: sms_template
* @author zhaoyb
*/
public class SmsTemplate implements Serializable {

    /**
     * 模板ID
     */
    private String templateId;
    /**
     * 模板名称
     */
    private String templateName;
    /**
     * 模板
     */
    private String content;
    /**
     * clientId
     */
    private Integer clientId;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date modifyTime;
    private static final long serialVersionUID = 1L;

    public String getTemplateId() {
        return templateId;
    }
    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }
    public String getTemplateName() {
        return templateName;
    }
    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public Integer getClientId() {
        return clientId;
    }
    public void setClientId(Integer clientId) {
        this.clientId = clientId;
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