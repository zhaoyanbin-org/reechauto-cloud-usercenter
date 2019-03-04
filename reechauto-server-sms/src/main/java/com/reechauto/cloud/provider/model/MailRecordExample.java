package com.reechauto.cloud.provider.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MailRecordExample {

    protected String orderByClause;
    protected boolean distinct;
    protected List<Criteria> oredCriteria;
    protected Integer limitStart;
    protected Integer offset;

    public MailRecordExample() {
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
    * tableName: mail_record
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
        public Criteria andChannelIsNull() {
            addCriterion("channel is null");
            return (Criteria) this;
        }
        public Criteria andChannelIsNotNull() {
            addCriterion("channel is not null");
            return (Criteria) this;
        }
        public Criteria andChannelEqualTo(String value) {
            addCriterion("channel =", value, "channel");
            return (Criteria) this;
        }
        public Criteria andChannelNotEqualTo(String value) {
            addCriterion("channel <>", value, "channel");
            return (Criteria) this;
        }
        public Criteria andChannelGreaterThan(String value) {
            addCriterion("channel >", value, "channel");
            return (Criteria) this;
        }
        public Criteria andChannelGreaterThanOrEqualTo(String value) {
            addCriterion("channel >=", value, "channel");
            return (Criteria) this;
        }
        public Criteria andChannelLessThan(String value) {
            addCriterion("channel <", value, "channel");
            return (Criteria) this;
        }
        public Criteria andChannelLessThanOrEqualTo(String value) {
            addCriterion("channel <=", value, "channel");
            return (Criteria) this;
        }
        public Criteria andChannelLike(String value) {
            addCriterion("channel like", value, "channel");
            return (Criteria) this;
        }
        public Criteria andChannelNotLike(String value) {
            addCriterion("channel not like", value, "channel");
            return (Criteria) this;
        }
        public Criteria andChannelIn(List<String> values) {
            addCriterion("channel in", values, "channel");
            return (Criteria) this;
        }
        public Criteria andChannelNotIn(List<String> values) {
            addCriterion("channel not in", values, "channel");
            return (Criteria) this;
        }
        public Criteria andChannelBetween(String value1, String value2) {
            addCriterion("channel between", value1, value2, "channel");
            return (Criteria) this;
        }
        public Criteria andChannelNotBetween(String value1, String value2) {
            addCriterion("channel not between", value1, value2, "channel");
            return (Criteria) this;
        }
        public Criteria andSendSubjectIsNull() {
            addCriterion("send_subject is null");
            return (Criteria) this;
        }
        public Criteria andSendSubjectIsNotNull() {
            addCriterion("send_subject is not null");
            return (Criteria) this;
        }
        public Criteria andSendSubjectEqualTo(String value) {
            addCriterion("send_subject =", value, "sendSubject");
            return (Criteria) this;
        }
        public Criteria andSendSubjectNotEqualTo(String value) {
            addCriterion("send_subject <>", value, "sendSubject");
            return (Criteria) this;
        }
        public Criteria andSendSubjectGreaterThan(String value) {
            addCriterion("send_subject >", value, "sendSubject");
            return (Criteria) this;
        }
        public Criteria andSendSubjectGreaterThanOrEqualTo(String value) {
            addCriterion("send_subject >=", value, "sendSubject");
            return (Criteria) this;
        }
        public Criteria andSendSubjectLessThan(String value) {
            addCriterion("send_subject <", value, "sendSubject");
            return (Criteria) this;
        }
        public Criteria andSendSubjectLessThanOrEqualTo(String value) {
            addCriterion("send_subject <=", value, "sendSubject");
            return (Criteria) this;
        }
        public Criteria andSendSubjectLike(String value) {
            addCriterion("send_subject like", value, "sendSubject");
            return (Criteria) this;
        }
        public Criteria andSendSubjectNotLike(String value) {
            addCriterion("send_subject not like", value, "sendSubject");
            return (Criteria) this;
        }
        public Criteria andSendSubjectIn(List<String> values) {
            addCriterion("send_subject in", values, "sendSubject");
            return (Criteria) this;
        }
        public Criteria andSendSubjectNotIn(List<String> values) {
            addCriterion("send_subject not in", values, "sendSubject");
            return (Criteria) this;
        }
        public Criteria andSendSubjectBetween(String value1, String value2) {
            addCriterion("send_subject between", value1, value2, "sendSubject");
            return (Criteria) this;
        }
        public Criteria andSendSubjectNotBetween(String value1, String value2) {
            addCriterion("send_subject not between", value1, value2, "sendSubject");
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
        public Criteria andSendPatternIsNull() {
            addCriterion("send_pattern is null");
            return (Criteria) this;
        }
        public Criteria andSendPatternIsNotNull() {
            addCriterion("send_pattern is not null");
            return (Criteria) this;
        }
        public Criteria andSendPatternEqualTo(String value) {
            addCriterion("send_pattern =", value, "sendPattern");
            return (Criteria) this;
        }
        public Criteria andSendPatternNotEqualTo(String value) {
            addCriterion("send_pattern <>", value, "sendPattern");
            return (Criteria) this;
        }
        public Criteria andSendPatternGreaterThan(String value) {
            addCriterion("send_pattern >", value, "sendPattern");
            return (Criteria) this;
        }
        public Criteria andSendPatternGreaterThanOrEqualTo(String value) {
            addCriterion("send_pattern >=", value, "sendPattern");
            return (Criteria) this;
        }
        public Criteria andSendPatternLessThan(String value) {
            addCriterion("send_pattern <", value, "sendPattern");
            return (Criteria) this;
        }
        public Criteria andSendPatternLessThanOrEqualTo(String value) {
            addCriterion("send_pattern <=", value, "sendPattern");
            return (Criteria) this;
        }
        public Criteria andSendPatternLike(String value) {
            addCriterion("send_pattern like", value, "sendPattern");
            return (Criteria) this;
        }
        public Criteria andSendPatternNotLike(String value) {
            addCriterion("send_pattern not like", value, "sendPattern");
            return (Criteria) this;
        }
        public Criteria andSendPatternIn(List<String> values) {
            addCriterion("send_pattern in", values, "sendPattern");
            return (Criteria) this;
        }
        public Criteria andSendPatternNotIn(List<String> values) {
            addCriterion("send_pattern not in", values, "sendPattern");
            return (Criteria) this;
        }
        public Criteria andSendPatternBetween(String value1, String value2) {
            addCriterion("send_pattern between", value1, value2, "sendPattern");
            return (Criteria) this;
        }
        public Criteria andSendPatternNotBetween(String value1, String value2) {
            addCriterion("send_pattern not between", value1, value2, "sendPattern");
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
        public Criteria andIsTemplateIsNull() {
            addCriterion("is_template is null");
            return (Criteria) this;
        }
        public Criteria andIsTemplateIsNotNull() {
            addCriterion("is_template is not null");
            return (Criteria) this;
        }
        public Criteria andIsTemplateEqualTo(String value) {
            addCriterion("is_template =", value, "isTemplate");
            return (Criteria) this;
        }
        public Criteria andIsTemplateNotEqualTo(String value) {
            addCriterion("is_template <>", value, "isTemplate");
            return (Criteria) this;
        }
        public Criteria andIsTemplateGreaterThan(String value) {
            addCriterion("is_template >", value, "isTemplate");
            return (Criteria) this;
        }
        public Criteria andIsTemplateGreaterThanOrEqualTo(String value) {
            addCriterion("is_template >=", value, "isTemplate");
            return (Criteria) this;
        }
        public Criteria andIsTemplateLessThan(String value) {
            addCriterion("is_template <", value, "isTemplate");
            return (Criteria) this;
        }
        public Criteria andIsTemplateLessThanOrEqualTo(String value) {
            addCriterion("is_template <=", value, "isTemplate");
            return (Criteria) this;
        }
        public Criteria andIsTemplateLike(String value) {
            addCriterion("is_template like", value, "isTemplate");
            return (Criteria) this;
        }
        public Criteria andIsTemplateNotLike(String value) {
            addCriterion("is_template not like", value, "isTemplate");
            return (Criteria) this;
        }
        public Criteria andIsTemplateIn(List<String> values) {
            addCriterion("is_template in", values, "isTemplate");
            return (Criteria) this;
        }
        public Criteria andIsTemplateNotIn(List<String> values) {
            addCriterion("is_template not in", values, "isTemplate");
            return (Criteria) this;
        }
        public Criteria andIsTemplateBetween(String value1, String value2) {
            addCriterion("is_template between", value1, value2, "isTemplate");
            return (Criteria) this;
        }
        public Criteria andIsTemplateNotBetween(String value1, String value2) {
            addCriterion("is_template not between", value1, value2, "isTemplate");
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
    }

    /**
    * 绿驰汽车
    * tableName: mail_record
    * @author zhaoyb
    */
    public static class Criteria extends GeneratedCriteria {


        protected Criteria() {
            super();
        }
    }

    /**
    * 绿驰汽车
    * tableName: mail_record
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