package com.reechauto.cloud.provider.model;

import java.io.Serializable;
import java.util.Date;

/**
* 绿驰汽车
* tableName: mail_template
* @author zhaoyb
*/
public class MailTemplate implements Serializable {

    private String templateId;
    private String templateName;
    private Integer clientId;
    /**
     * 创建时间
     */
    private Date createTime;
    private Date modifyTime;
    private String content;
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
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
}