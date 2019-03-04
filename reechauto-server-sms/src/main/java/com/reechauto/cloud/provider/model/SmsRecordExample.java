package com.reechauto.cloud.provider.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SmsRecordExample {

    protected String orderByClause;
    protected boolean distinct;
    protected List<Criteria> oredCriteria;
    protected Integer limitStart;
    protected Integer offset;

    public SmsRecordExample() {
        oredCriteria = new ArrayList<Criteria>();
    }
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }
    public String getOrderByClause() {
        return orderByClause;
    }
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }
    public boolean isDistinct() {
        return distinct;
    }
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }
    public void setLimitStart(Integer limitStart) {
        this.limitStart=limitStart;
    }
    public Integer getLimitStart() {
        return limitStart;
    }
    public void setOffset(Integer offset) {
        this.offset=offset;
    }
    public Integer getOffset() {
        return offset;
    }

    /**
    * 绿驰汽车
    * tableName: sms_record
    * @author zhaoyb
    */
    protected abstract static class GeneratedCriteria {

        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }
        public boolean isValid() {
            return criteria.size() > 0;
        }
        public List<Criterion> getAllCriteria() {
            return criteria;
        }
        public List<Criterion> getCriteria() {
            return criteria;
        }
        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }
        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }
        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }
        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }
        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }
        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }
        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }
        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }
        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }
        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }
        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }
        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }
        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }
        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }
        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }
        public Criteria andClientIdIsNull() {
            addCriterion("client_id is null");
            return (Criteria) this;
        }
        public Criteria andClientIdIsNotNull() {
            addCriterion("client_id is not null");
            return (Criteria) this;
        }
        public Criteria andClientIdEqualTo(String value) {
            addCriterion("client_id =", value, "clientId");
            return (Criteria) this;
        }
        public Criteria andClientIdNotEqualTo(String value) {
            addCriterion("client_id <>", value, "clientId");
            return (Criteria) this;
        }
        public Criteria andClientIdGreaterThan(String value) {
            addCriterion("client_id >", value, "clientId");
            return (Criteria) this;
        }
        public Criteria andClientIdGreaterThanOrEqualTo(String value) {
            addCriterion("client_id >=", value, "clientId");
            return (Criteria) this;
        }
        public Criteria andClientIdLessThan(String value) {
            addCriterion("client_id <", value, "clientId");
            return (Criteria) this;
        }
        public Criteria andClientIdLessThanOrEqualTo(String value) {
            addCriterion("client_id <=", value, "clientId");
            return (Criteria) this;
        }
        public Criteria andClientIdLike(String value) {
            addCriterion("client_id like", value, "clientId");
            return (Criteria) this;
        }
        public Criteria andClientIdNotLike(String value) {
            addCriterion("client_id not like", value, "clientId");
            return (Criteria) this;
        }
        public Criteria andClientIdIn(List<String> values) {
            addCriterion("client_id in", values, "clientId");
            return (Criteria) this;
        }
        public Criteria andClientIdNotIn(List<String> values) {
            addCriterion("client_id not in", values, "clientId");
            return (Criteria) this;
        }
        public Criteria andClientIdBetween(String value1, String value2) {
            addCriterion("client_id between", value1, value2, "clientId");
            return (Criteria) this;
        }
        public Criteria andClientIdNotBetween(String value1, String value2) {
            addCriterion("client_id not between", value1, value2, "clientId");
            return (Criteria) this;
        }
        public Criteria andSmsTypeIsNull() {
            addCriterion("sms_type is null");
            return (Criteria) this;
        }
        public Criteria andSmsTypeIsNotNull() {
            addCriterion("sms_type is not null");
            return (Criteria) this;
        }
        public Criteria andSmsTypeEqualTo(String value) {
            addCriterion("sms_type =", value, "smsType");
            return (Criteria) this;
        }
        public Criteria andSmsTypeNotEqualTo(String value) {
            addCriterion("sms_type <>", value, "smsType");
            return (Criteria) this;
        }
        public Criteria andSmsTypeGreaterThan(String value) {
            addCriterion("sms_type >", value, "smsType");
            return (Criteria) this;
        }
        public Criteria andSmsTypeGreaterThanOrEqualTo(String value) {
            addCriterion("sms_type >=", value, "smsType");
            return (Criteria) this;
        }
        public Criteria andSmsTypeLessThan(String value) {
            addCriterion("sms_type <", value, "smsType");
            return (Criteria) this;
        }
        public Criteria andSmsTypeLessThanOrEqualTo(String value) {
            addCriterion("sms_type <=", value, "smsType");
            return (Criteria) this;
        }
        public Criteria andSmsTypeLike(String value) {
            addCriterion("sms_type like", value, "smsType");
            return (Criteria) this;
        }
        public Criteria andSmsTypeNotLike(String value) {
            addCriterion("sms_type not like", value, "smsType");
            return (Criteria) this;
        }
        public Criteria andSmsTypeIn(List<String> values) {
            addCriterion("sms_type in", values, "smsType");
            return (Criteria) this;
        }
        public Criteria andSmsTypeNotIn(List<String> values) {
            addCriterion("sms_type not in", values, "smsType");
            return (Criteria) this;
        }
        public Criteria andSmsTypeBetween(String value1, String value2) {
            addCriterion("sms_type between", value1, value2, "smsType");
            return (Criteria) this;
        }
        public Criteria andSmsTypeNotBetween(String value1, String value2) {
            addCriterion("sms_type not between", value1, value2, "smsType");
            return (Criteria) this;
        }
        public Criteria andReceiveMobileIsNull() {
            addCriterion("receive_mobile is null");
            return (Criteria) this;
        }
        public Criteria andReceiveMobileIsNotNull() {
            addCriterion("receive_mobile is not null");
            return (Criteria) this;
        }
        public Criteria andReceiveMobileEqualTo(String value) {
            addCriterion("receive_mobile =", value, "receiveMobile");
            return (Criteria) this;
        }
        public Criteria andReceiveMobileNotEqualTo(String value) {
            addCriterion("receive_mobile <>", value, "receiveMobile");
            return (Criteria) this;
        }
        public Criteria andReceiveMobileGreaterThan(String value) {
            addCriterion("receive_mobile >", value, "receiveMobile");
            return (Criteria) this;
        }
        public Criteria andReceiveMobileGreaterThanOrEqualTo(String value) {
            addCriterion("receive_mobile >=", value, "receiveMobile");
            return (Criteria) this;
        }
        public Criteria andReceiveMobileLessThan(String value) {
            addCriterion("receive_mobile <", value, "receiveMobile");
            return (Criteria) this;
        }
        public Criteria andReceiveMobileLessThanOrEqualTo(String value) {
            addCriterion("receive_mobile <=", value, "receiveMobile");
            return (Criteria) this;
        }
        public Criteria andReceiveMobileLike(String value) {
            addCriterion("receive_mobile like", value, "receiveMobile");
            return (Criteria) this;
        }
        public Criteria andReceiveMobileNotLike(String value) {
            addCriterion("receive_mobile not like", value, "receiveMobile");
            return (Criteria) this;
        }
        public Criteria andReceiveMobileIn(List<String> values) {
            addCriterion("receive_mobile in", values, "receiveMobile");
            return (Criteria) this;
        }
        public Criteria andReceiveMobileNotIn(List<String> values) {
            addCriterion("receive_mobile not in", values, "receiveMobile");
            return (Criteria) this;
        }
        public Criteria andReceiveMobileBetween(String value1, String value2) {
            addCriterion("receive_mobile between", value1, value2, "receiveMobile");
            return (Criteria) this;
        }
        public Criteria andReceiveMobileNotBetween(String value1, String value2) {
            addCriterion("receive_mobile not between", value1, value2, "receiveMobile");
            return (Criteria) this;
        }
        public Criteria andIsVoiceIsNull() {
            addCriterion("is_voice is null");
            return (Criteria) this;
        }
        public Criteria andIsVoiceIsNotNull() {
            addCriterion("is_voice is not null");
            return (Criteria) this;
        }
        public Criteria andIsVoiceEqualTo(String value) {
            addCriterion("is_voice =", value, "isVoice");
            return (Criteria) this;
        }
        public Criteria andIsVoiceNotEqualTo(String value) {
            addCriterion("is_voice <>", value, "isVoice");
            return (Criteria) this;
        }
        public Criteria andIsVoiceGreaterThan(String value) {
            addCriterion("is_voice >", value, "isVoice");
            return (Criteria) this;
        }
        public Criteria andIsVoiceGreaterThanOrEqualTo(String value) {
            addCriterion("is_voice >=", value, "isVoice");
            return (Criteria) this;
        }
        public Criteria andIsVoiceLessThan(String value) {
            addCriterion("is_voice <", value, "isVoice");
            return (Criteria) this;
        }
        public Criteria andIsVoiceLessThanOrEqualTo(String value) {
            addCriterion("is_voice <=", value, "isVoice");
            return (Criteria) this;
        }
        public Criteria andIsVoiceLike(String value) {
            addCriterion("is_voice like", value, "isVoice");
            return (Criteria) this;
        }
        public Criteria andIsVoiceNotLike(String value) {
            addCriterion("is_voice not like", value, "isVoice");
            return (Criteria) this;
        }
        public Criteria andIsVoiceIn(List<String> values) {
            addCriterion("is_voice in", values, "isVoice");
            return (Criteria) this;
        }
        public Criteria andIsVoiceNotIn(List<String> values) {
            addCriterion("is_voice not in", values, "isVoice");
            return (Criteria) this;
        }
        public Criteria andIsVoiceBetween(String value1, String value2) {
            addCriterion("is_voice between", value1, value2, "isVoice");
            return (Criteria) this;
        }
        public Criteria andIsVoiceNotBetween(String value1, String value2) {
            addCriterion("is_voice not between", value1, value2, "isVoice");
            return (Criteria) this;
        }
        public Criteria andTemplateIdIsNull() {
            addCriterion("template_id is null");
            return (Criteria) this;
        }
        public Criteria andTemplateIdIsNotNull() {
            addCriterion("template_id is not null");
            return (Criteria) this;
        }
        public Criteria andTemplateIdEqualTo(String value) {
            addCriterion("template_id =", value, "templateId");
            return (Criteria) this;
        }
        public Criteria andTemplateIdNotEqualTo(String value) {
            addCriterion("template_id <>", value, "templateId");
            return (Criteria) this;
        }
        public Criteria andTemplateIdGreaterThan(String value) {
            addCriterion("template_id >", value, "templateId");
            return (Criteria) this;
        }
        public Criteria andTemplateIdGreaterThanOrEqualTo(String value) {
            addCriterion("template_id >=", value, "templateId");
            return (Criteria) this;
        }
        public Criteria andTemplateIdLessThan(String value) {
            addCriterion("template_id <", value, "templateId");
            return (Criteria) this;
        }
        public Criteria andTemplateIdLessThanOrEqualTo(String value) {
            addCriterion("template_id <=", value, "templateId");
            return (Criteria) this;
        }
        public Criteria andTemplateIdLike(String value) {
            addCriterion("template_id like", value, "templateId");
            return (Criteria) this;
        }
        public Criteria andTemplateIdNotLike(String value) {
            addCriterion("template_id not like", value, "templateId");
            return (Criteria) this;
        }
        public Criteria andTemplateIdIn(List<String> values) {
            addCriterion("template_id in", values, "templateId");
            return (Criteria) this;
        }
        public Criteria andTemplateIdNotIn(List<String> values) {
            addCriterion("template_id not in", values, "templateId");
            return (Criteria) this;
        }
        public Criteria andTemplateIdBetween(String value1, String value2) {
            addCriterion("template_id between", value1, value2, "templateId");
            return (Criteria) this;
        }
        public Criteria andTemplateIdNotBetween(String value1, String value2) {
            addCriterion("template_id not between", value1, value2, "templateId");
            return (Criteria) this;
        }
        public Criteria andSendParamIsNull() {
            addCriterion("send_param is null");
            return (Criteria) this;
        }
        public Criteria andSendParamIsNotNull() {
            addCriterion("send_param is not null");
            return (Criteria) this;
        }
        public Criteria andSendParamEqualTo(String value) {
            addCriterion("send_param =", value, "sendParam");
            return (Criteria) this;
        }
        public Criteria andSendParamNotEqualTo(String value) {
            addCriterion("send_param <>", value, "sendParam");
            return (Criteria) this;
        }
        public Criteria andSendParamGreaterThan(String value) {
            addCriterion("send_param >", value, "sendParam");
            return (Criteria) this;
        }
        public Criteria andSendParamGreaterThanOrEqualTo(String value) {
            addCriterion("send_param >=", value, "sendParam");
            return (Criteria) this;
        }
        public Criteria andSendParamLessThan(String value) {
            addCriterion("send_param <", value, "sendParam");
            return (Criteria) this;
        }
        public Criteria andSendParamLessThanOrEqualTo(String value) {
            addCriterion("send_param <=", value, "sendParam");
            return (Criteria) this;
        }
        public Criteria andSendParamLike(String value) {
            addCriterion("send_param like", value, "sendParam");
            return (Criteria) this;
        }
        public Criteria andSendParamNotLike(String value) {
            addCriterion("send_param not like", value, "sendParam");
            return (Criteria) this;
        }
        public Criteria andSendParamIn(List<String> values) {
            addCriterion("send_param in", values, "sendParam");
            return (Criteria) this;
        }
        public Criteria andSendParamNotIn(List<String> values) {
            addCriterion("send_param not in", values, "sendParam");
            return (Criteria) this;
        }
        public Criteria andSendParamBetween(String value1, String value2) {
            addCriterion("send_param between", value1, value2, "sendParam");
            return (Criteria) this;
        }
        public Criteria andSendParamNotBetween(String value1, String value2) {
            addCriterion("send_param not between", value1, value2, "sendParam");
            return (Criteria) this;
        }
        public Criteria andSendContextIsNull() {
            addCriterion("send_context is null");
            return (Criteria) this;
        }
        public Criteria andSendContextIsNotNull() {
            addCriterion("send_context is not null");
            return (Criteria) this;
        }
        public Criteria andSendContextEqualTo(String value) {
            addCriterion("send_context =", value, "sendContext");
            return (Criteria) this;
        }
        public Criteria andSendContextNotEqualTo(String value) {
            addCriterion("send_context <>", value, "sendContext");
            return (Criteria) this;
        }
        public Criteria andSendContextGreaterThan(String value) {
            addCriterion("send_context >", value, "sendContext");
            return (Criteria) this;
        }
        public Criteria andSendContextGreaterThanOrEqualTo(String value) {
            addCriterion("send_context >=", value, "sendContext");
            return (Criteria) this;
        }
        public Criteria andSendContextLessThan(String value) {
            addCriterion("send_context <", value, "sendContext");
            return (Criteria) this;
        }
        public Criteria andSendContextLessThanOrEqualTo(String value) {
            addCriterion("send_context <=", value, "sendContext");
            return (Criteria) this;
        }
        public Criteria andSendContextLike(String value) {
            addCriterion("send_context like", value, "sendContext");
            return (Criteria) this;
        }
        public Criteria andSendContextNotLike(String value) {
            addCriterion("send_context not like", value, "sendContext");
            return (Criteria) this;
        }
        public Criteria andSendContextIn(List<String> values) {
            addCriterion("send_context in", values, "sendContext");
            return (Criteria) this;
        }
        public Criteria andSendContextNotIn(List<String> values) {
            addCriterion("send_context not in", values, "sendContext");
            return (Criteria) this;
        }
        public Criteria andSendContextBetween(String value1, String value2) {
            addCriterion("send_context between", value1, value2, "sendContext");
            return (Criteria) this;
        }
        public Criteria andSendContextNotBetween(String value1, String value2) {
            addCriterion("send_context not between", value1, value2, "sendContext");
            return (Criteria) this;
        }
        public Criteria andSendTimeIsNull() {
            addCriterion("send_time is null");
            return (Criteria) this;
        }
        public Criteria andSendTimeIsNotNull() {
            addCriterion("send_time is not null");
            return (Criteria) this;
        }
        public Criteria andSendTimeEqualTo(Date value) {
            addCriterion("send_time =", value, "sendTime");
            return (Criteria) this;
        }
        public Criteria andSendTimeNotEqualTo(Date value) {
            addCriterion("send_time <>", value, "sendTime");
            return (Criteria) this;
        }
        public Criteria andSendTimeGreaterThan(Date value) {
            addCriterion("send_time >", value, "sendTime");
            return (Criteria) this;
        }
        public Criteria andSendTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("send_time >=", value, "sendTime");
            return (Criteria) this;
        }
        public Criteria andSendTimeLessThan(Date value) {
            addCriterion("send_time <", value, "sendTime");
            return (Criteria) this;
        }
        public Criteria andSendTimeLessThanOrEqualTo(Date value) {
            addCriterion("send_time <=", value, "sendTime");
            return (Criteria) this;
        }
        public Criteria andSendTimeIn(List<Date> values) {
            addCriterion("send_time in", values, "sendTime");
            return (Criteria) this;
        }
        public Criteria andSendTimeNotIn(List<Date> values) {
            addCriterion("send_time not in", values, "sendTime");
            return (Criteria) this;
        }
        public Criteria andSendTimeBetween(Date value1, Date value2) {
            addCriterion("send_time between", value1, value2, "sendTime");
            return (Criteria) this;
        }
        public Criteria andSendTimeNotBetween(Date value1, Date value2) {
            addCriterion("send_time not between", value1, value2, "sendTime");
            return (Criteria) this;
        }
        public Criteria andGatewaySendTimeIsNull() {
            addCriterion("gateway_send_time is null");
            return (Criteria) this;
        }
        public Criteria andGatewaySendTimeIsNotNull() {
            addCriterion("gateway_send_time is not null");
            return (Criteria) this;
        }
        public Criteria andGatewaySendTimeEqualTo(Date value) {
            addCriterion("gateway_send_time =", value, "gatewaySendTime");
            return (Criteria) this;
        }
        public Criteria andGatewaySendTimeNotEqualTo(Date value) {
            addCriterion("gateway_send_time <>", value, "gatewaySendTime");
            return (Criteria) this;
        }
        public Criteria andGatewaySendTimeGreaterThan(Date value) {
            addCriterion("gateway_send_time >", value, "gatewaySendTime");
            return (Criteria) this;
        }
        public Criteria andGatewaySendTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("gateway_send_time >=", value, "gatewaySendTime");
            return (Criteria) this;
        }
        public Criteria andGatewaySendTimeLessThan(Date value) {
            addCriterion("gateway_send_time <", value, "gatewaySendTime");
            return (Criteria) this;
        }
        public Criteria andGatewaySendTimeLessThanOrEqualTo(Date value) {
            addCriterion("gateway_send_time <=", value, "gatewaySendTime");
            return (Criteria) this;
        }
        public Criteria andGatewaySendTimeIn(List<Date> values) {
            addCriterion("gateway_send_time in", values, "gatewaySendTime");
            return (Criteria) this;
        }
        public Criteria andGatewaySendTimeNotIn(List<Date> values) {
            addCriterion("gateway_send_time not in", values, "gatewaySendTime");
            return (Criteria) this;
        }
        public Criteria andGatewaySendTimeBetween(Date value1, Date value2) {
            addCriterion("gateway_send_time between", value1, value2, "gatewaySendTime");
            return (Criteria) this;
        }
        public Criteria andGatewaySendTimeNotBetween(Date value1, Date value2) {
            addCriterion("gateway_send_time not between", value1, value2, "gatewaySendTime");
            return (Criteria) this;
        }
        public Criteria andGatewaySendStatusIsNull() {
            addCriterion("gateway_send_status is null");
            return (Criteria) this;
        }
        public Criteria andGatewaySendStatusIsNotNull() {
            addCriterion("gateway_send_status is not null");
            return (Criteria) this;
        }
        public Criteria andGatewaySendStatusEqualTo(String value) {
            addCriterion("gateway_send_status =", value, "gatewaySendStatus");
            return (Criteria) this;
        }
        public Criteria andGatewaySendStatusNotEqualTo(String value) {
            addCriterion("gateway_send_status <>", value, "gatewaySendStatus");
            return (Criteria) this;
        }
        public Criteria andGatewaySendStatusGreaterThan(String value) {
            addCriterion("gateway_send_status >", value, "gatewaySendStatus");
            return (Criteria) this;
        }
        public Criteria andGatewaySendStatusGreaterThanOrEqualTo(String value) {
            addCriterion("gateway_send_status >=", value, "gatewaySendStatus");
            return (Criteria) this;
        }
        public Criteria andGatewaySendStatusLessThan(String value) {
            addCriterion("gateway_send_status <", value, "gatewaySendStatus");
            return (Criteria) this;
        }
        public Criteria andGatewaySendStatusLessThanOrEqualTo(String value) {
            addCriterion("gateway_send_status <=", value, "gatewaySendStatus");
            return (Criteria) this;
        }
        public Criteria andGatewaySendStatusLike(String value) {
            addCriterion("gateway_send_status like", value, "gatewaySendStatus");
            return (Criteria) this;
        }
        public Criteria andGatewaySendStatusNotLike(String value) {
            addCriterion("gateway_send_status not like", value, "gatewaySendStatus");
            return (Criteria) this;
        }
        public Criteria andGatewaySendStatusIn(List<String> values) {
            addCriterion("gateway_send_status in", values, "gatewaySendStatus");
            return (Criteria) this;
        }
        public Criteria andGatewaySendStatusNotIn(List<String> values) {
            addCriterion("gateway_send_status not in", values, "gatewaySendStatus");
            return (Criteria) this;
        }
        public Criteria andGatewaySendStatusBetween(String value1, String value2) {
            addCriterion("gateway_send_status between", value1, value2, "gatewaySendStatus");
            return (Criteria) this;
        }
        public Criteria andGatewaySendStatusNotBetween(String value1, String value2) {
            addCriterion("gateway_send_status not between", value1, value2, "gatewaySendStatus");
            return (Criteria) this;
        }
        public Criteria andGatewaySendRetIsNull() {
            addCriterion("gateway_send_ret is null");
            return (Criteria) this;
        }
        public Criteria andGatewaySendRetIsNotNull() {
            addCriterion("gateway_send_ret is not null");
            return (Criteria) this;
        }
        public Criteria andGatewaySendRetEqualTo(String value) {
            addCriterion("gateway_send_ret =", value, "gatewaySendRet");
            return (Criteria) this;
        }
        public Criteria andGatewaySendRetNotEqualTo(String value) {
            addCriterion("gateway_send_ret <>", value, "gatewaySendRet");
            return (Criteria) this;
        }
        public Criteria andGatewaySendRetGreaterThan(String value) {
            addCriterion("gateway_send_ret >", value, "gatewaySendRet");
            return (Criteria) this;
        }
        public Criteria andGatewaySendRetGreaterThanOrEqualTo(String value) {
            addCriterion("gateway_send_ret >=", value, "gatewaySendRet");
            return (Criteria) this;
        }
        public Criteria andGatewaySendRetLessThan(String value) {
            addCriterion("gateway_send_ret <", value, "gatewaySendRet");
            return (Criteria) this;
        }
        public Criteria andGatewaySendRetLessThanOrEqualTo(String value) {
            addCriterion("gateway_send_ret <=", value, "gatewaySendRet");
            return (Criteria) this;
        }
        public Criteria andGatewaySendRetLike(String value) {
            addCriterion("gateway_send_ret like", value, "gatewaySendRet");
            return (Criteria) this;
        }
        public Criteria andGatewaySendRetNotLike(String value) {
            addCriterion("gateway_send_ret not like", value, "gatewaySendRet");
            return (Criteria) this;
        }
        public Criteria andGatewaySendRetIn(List<String> values) {
            addCriterion("gateway_send_ret in", values, "gatewaySendRet");
            return (Criteria) this;
        }
        public Criteria andGatewaySendRetNotIn(List<String> values) {
            addCriterion("gateway_send_ret not in", values, "gatewaySendRet");
            return (Criteria) this;
        }
        public Criteria andGatewaySendRetBetween(String value1, String value2) {
            addCriterion("gateway_send_ret between", value1, value2, "gatewaySendRet");
            return (Criteria) this;
        }
        public Criteria andGatewaySendRetNotBetween(String value1, String value2) {
            addCriterion("gateway_send_ret not between", value1, value2, "gatewaySendRet");
            return (Criteria) this;
        }
        public Criteria andSignNameIsNull() {
            addCriterion("sign_name is null");
            return (Criteria) this;
        }
        public Criteria andSignNameIsNotNull() {
            addCriterion("sign_name is not null");
            return (Criteria) this;
        }
        public Criteria andSignNameEqualTo(String value) {
            addCriterion("sign_name =", value, "signName");
            return (Criteria) this;
        }
        public Criteria andSignNameNotEqualTo(String value) {
            addCriterion("sign_name <>", value, "signName");
            return (Criteria) this;
        }
        public Criteria andSignNameGreaterThan(String value) {
            addCriterion("sign_name >", value, "signName");
            return (Criteria) this;
        }
        public Criteria andSignNameGreaterThanOrEqualTo(String value) {
            addCriterion("sign_name >=", value, "signName");
            return (Criteria) this;
        }
        public Criteria andSignNameLessThan(String value) {
            addCriterion("sign_name <", value, "signName");
            return (Criteria) this;
        }
        public Criteria andSignNameLessThanOrEqualTo(String value) {
            addCriterion("sign_name <=", value, "signName");
            return (Criteria) this;
        }
        public Criteria andSignNameLike(String value) {
            addCriterion("sign_name like", value, "signName");
            return (Criteria) this;
        }
        public Criteria andSignNameNotLike(String value) {
            addCriterion("sign_name not like", value, "signName");
            return (Criteria) this;
        }
        public Criteria andSignNameIn(List<String> values) {
            addCriterion("sign_name in", values, "signName");
            return (Criteria) this;
        }
        public Criteria andSignNameNotIn(List<String> values) {
            addCriterion("sign_name not in", values, "signName");
            return (Criteria) this;
        }
        public Criteria andSignNameBetween(String value1, String value2) {
            addCriterion("sign_name between", value1, value2, "signName");
            return (Criteria) this;
        }
        public Criteria andSignNameNotBetween(String value1, String value2) {
            addCriterion("sign_name not between", value1, value2, "signName");
            return (Criteria) this;
        }
        public Criteria andServiceProviderIsNull() {
            addCriterion("service_provider is null");
            return (Criteria) this;
        }
        public Criteria andServiceProviderIsNotNull() {
            addCriterion("service_provider is not null");
            return (Criteria) this;
        }
        public Criteria andServiceProviderEqualTo(String value) {
            addCriterion("service_provider =", value, "serviceProvider");
            return (Criteria) this;
        }
        public Criteria andServiceProviderNotEqualTo(String value) {
            addCriterion("service_provider <>", value, "serviceProvider");
            return (Criteria) this;
        }
        public Criteria andServiceProviderGreaterThan(String value) {
            addCriterion("service_provider >", value, "serviceProvider");
            return (Criteria) this;
        }
        public Criteria andServiceProviderGreaterThanOrEqualTo(String value) {
            addCriterion("service_provider >=", value, "serviceProvider");
            return (Criteria) this;
        }
        public Criteria andServiceProviderLessThan(String value) {
            addCriterion("service_provider <", value, "serviceProvider");
            return (Criteria) this;
        }
        public Criteria andServiceProviderLessThanOrEqualTo(String value) {
            addCriterion("service_provider <=", value, "serviceProvider");
            return (Criteria) this;
        }
        public Criteria andServiceProviderLike(String value) {
            addCriterion("service_provider like", value, "serviceProvider");
            return (Criteria) this;
        }
        public Criteria andServiceProviderNotLike(String value) {
            addCriterion("service_provider not like", value, "serviceProvider");
            return (Criteria) this;
        }
        public Criteria andServiceProviderIn(List<String> values) {
            addCriterion("service_provider in", values, "serviceProvider");
            return (Criteria) this;
        }
        public Criteria andServiceProviderNotIn(List<String> values) {
            addCriterion("service_provider not in", values, "serviceProvider");
            return (Criteria) this;
        }
        public Criteria andServiceProviderBetween(String value1, String value2) {
            addCriterion("service_provider between", value1, value2, "serviceProvider");
            return (Criteria) this;
        }
        public Criteria andServiceProviderNotBetween(String value1, String value2) {
            addCriterion("service_provider not between", value1, value2, "serviceProvider");
            return (Criteria) this;
        }
        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }
        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }
        public Criteria andStatusEqualTo(String value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }
        public Criteria andStatusNotEqualTo(String value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }
        public Criteria andStatusGreaterThan(String value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }
        public Criteria andStatusGreaterThanOrEqualTo(String value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }
        public Criteria andStatusLessThan(String value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }
        public Criteria andStatusLessThanOrEqualTo(String value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }
        public Criteria andStatusLike(String value) {
            addCriterion("status like", value, "status");
            return (Criteria) this;
        }
        public Criteria andStatusNotLike(String value) {
            addCriterion("status not like", value, "status");
            return (Criteria) this;
        }
        public Criteria andStatusIn(List<String> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }
        public Criteria andStatusNotIn(List<String> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }
        public Criteria andStatusBetween(String value1, String value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }
        public Criteria andStatusNotBetween(String value1, String value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }
    }

    /**
    * 绿驰汽车
    * tableName: sms_record
    * @author zhaoyb
    */
    public static class Criteria extends GeneratedCriteria {


        protected Criteria() {
            super();
        }
    }

    /**
    * 绿驰汽车
    * tableName: sms_record
    * @author zhaoyb
    */
    public static class Criterion {

        private String condition;
        private Object value;
        private Object secondValue;
        private boolean noValue;
        private boolean singleValue;
        private boolean betweenValue;
        private boolean listValue;
        private String typeHandler;

        public String getCondition() {
            return condition;
        }
        public Object getValue() {
            return value;
        }
        public Object getSecondValue() {
            return secondValue;
        }
        public boolean isNoValue() {
            return noValue;
        }
        public boolean isSingleValue() {
            return singleValue;
        }
        public boolean isBetweenValue() {
            return betweenValue;
        }
        public boolean isListValue() {
            return listValue;
        }
        public String getTypeHandler() {
            return typeHandler;
        }
        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }
        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }
        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }
        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }
        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}