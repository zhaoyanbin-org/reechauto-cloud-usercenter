package com.reechauto.cloud.provider.model;

import java.io.Serializable;

/**
* 绿驰汽车
* tableName: mail_record
* @author zhaoyb
*/
public class MailRecordWithBLOBs extends MailRecord implements Serializable {

    /**
     * 收件人
     */
    private String recipientsTo;
    /**
     * 抄送
     */
    private String recipientsCc;
    /**
     * 密抄
     */
    private String recipientsBcc;
    /**
     * 邮件内容
     */
    private String sendContext;
    private static final long serialVersionUID = 1L;

    public String getRecipientsTo() {
        return recipientsTo;
    }
    public void setRecipientsTo(String recipientsTo) {
        this.recipientsTo = recipientsTo;
    }
    public String getRecipientsCc() {
        return recipientsCc;
    }
    public void setRecipientsCc(String recipientsCc) {
        this.recipientsCc = recipientsCc;
    }
    public String getRecipientsBcc() {
        return recipientsBcc;
    }
    public void setRecipientsBcc(String recipientsBcc) {
        this.recipientsBcc = recipientsBcc;
    }
    public String getSendContext() {
        return sendContext;
    }
    public void setSendContext(String sendContext) {
        this.sendContext = sendContext;
    }
}