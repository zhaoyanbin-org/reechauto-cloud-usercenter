package com.reechauto.usercenter.user.entity;

import java.util.ArrayList;
import java.util.List;

public class ResourceScopeExample {

    protected String orderByClause;
    protected boolean distinct;
    protected List<Criteria> oredCriteria;
    protected Integer limitStart;
    protected Integer offset;

    public ResourceScopeExample() {
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
    * tableName: resource_scope
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
        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }
        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }
        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }
        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }
        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }
        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }
        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }
        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }
        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }
        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }
        public Criteria andScopeIsNull() {
            addCriterion("scope is null");
            return (Criteria) this;
        }
        public Criteria andScopeIsNotNull() {
            addCriterion("scope is not null");
            return (Criteria) this;
        }
        public Criteria andScopeEqualTo(String value) {
            addCriterion("scope =", value, "scope");
            return (Criteria) this;
        }
        public Criteria andScopeNotEqualTo(String value) {
            addCriterion("scope <>", value, "scope");
            return (Criteria) this;
        }
        public Criteria andScopeGreaterThan(String value) {
            addCriterion("scope >", value, "scope");
            return (Criteria) this;
        }
        public Criteria andScopeGreaterThanOrEqualTo(String value) {
            addCriterion("scope >=", value, "scope");
            return (Criteria) this;
        }
        public Criteria andScopeLessThan(String value) {
            addCriterion("scope <", value, "scope");
            return (Criteria) this;
        }
        public Criteria andScopeLessThanOrEqualTo(String value) {
            addCriterion("scope <=", value, "scope");
            return (Criteria) this;
        }
        public Criteria andScopeLike(String value) {
            addCriterion("scope like", value, "scope");
            return (Criteria) this;
        }
        public Criteria andScopeNotLike(String value) {
            addCriterion("scope not like", value, "scope");
            return (Criteria) this;
        }
        public Criteria andScopeIn(List<String> values) {
            addCriterion("scope in", values, "scope");
            return (Criteria) this;
        }
        public Criteria andScopeNotIn(List<String> values) {
            addCriterion("scope not in", values, "scope");
            return (Criteria) this;
        }
        public Criteria andScopeBetween(String value1, String value2) {
            addCriterion("scope between", value1, value2, "scope");
            return (Criteria) this;
        }
        public Criteria andScopeNotBetween(String value1, String value2) {
            addCriterion("scope not between", value1, value2, "scope");
            return (Criteria) this;
        }
        public Criteria andScopeTipsIsNull() {
            addCriterion("scope_tips is null");
            return (Criteria) this;
        }
        public Criteria andScopeTipsIsNotNull() {
            addCriterion("scope_tips is not null");
            return (Criteria) this;
        }
        public Criteria andScopeTipsEqualTo(String value) {
            addCriterion("scope_tips =", value, "scopeTips");
            return (Criteria) this;
        }
        public Criteria andScopeTipsNotEqualTo(String value) {
            addCriterion("scope_tips <>", value, "scopeTips");
            return (Criteria) this;
        }
        public Criteria andScopeTipsGreaterThan(String value) {
            addCriterion("scope_tips >", value, "scopeTips");
            return (Criteria) this;
        }
        public Criteria andScopeTipsGreaterThanOrEqualTo(String value) {
            addCriterion("scope_tips >=", value, "scopeTips");
            return (Criteria) this;
        }
        public Criteria andScopeTipsLessThan(String value) {
            addCriterion("scope_tips <", value, "scopeTips");
            return (Criteria) this;
        }
        public Criteria andScopeTipsLessThanOrEqualTo(String value) {
            addCriterion("scope_tips <=", value, "scopeTips");
            return (Criteria) this;
        }
        public Criteria andScopeTipsLike(String value) {
            addCriterion("scope_tips like", value, "scopeTips");
            return (Criteria) this;
        }
        public Criteria andScopeTipsNotLike(String value) {
            addCriterion("scope_tips not like", value, "scopeTips");
            return (Criteria) this;
        }
        public Criteria andScopeTipsIn(List<String> values) {
            addCriterion("scope_tips in", values, "scopeTips");
            return (Criteria) this;
        }
        public Criteria andScopeTipsNotIn(List<String> values) {
            addCriterion("scope_tips not in", values, "scopeTips");
            return (Criteria) this;
        }
        public Criteria andScopeTipsBetween(String value1, String value2) {
            addCriterion("scope_tips between", value1, value2, "scopeTips");
            return (Criteria) this;
        }
        public Criteria andScopeTipsNotBetween(String value1, String value2) {
            addCriterion("scope_tips not between", value1, value2, "scopeTips");
            return (Criteria) this;
        }
        public Criteria andResourceIdIsNull() {
            addCriterion("resource_id is null");
            return (Criteria) this;
        }
        public Criteria andResourceIdIsNotNull() {
            addCriterion("resource_id is not null");
            return (Criteria) this;
        }
        public Criteria andResourceIdEqualTo(String value) {
            addCriterion("resource_id =", value, "resourceId");
            return (Criteria) this;
        }
        public Criteria andResourceIdNotEqualTo(String value) {
            addCriterion("resource_id <>", value, "resourceId");
            return (Criteria) this;
        }
        public Criteria andResourceIdGreaterThan(String value) {
            addCriterion("resource_id >", value, "resourceId");
            return (Criteria) this;
        }
        public Criteria andResourceIdGreaterThanOrEqualTo(String value) {
            addCriterion("resource_id >=", value, "resourceId");
            return (Criteria) this;
        }
        public Criteria andResourceIdLessThan(String value) {
            addCriterion("resource_id <", value, "resourceId");
            return (Criteria) this;
        }
        public Criteria andResourceIdLessThanOrEqualTo(String value) {
            addCriterion("resource_id <=", value, "resourceId");
            return (Criteria) this;
        }
        public Criteria andResourceIdLike(String value) {
            addCriterion("resource_id like", value, "resourceId");
            return (Criteria) this;
        }
        public Criteria andResourceIdNotLike(String value) {
            addCriterion("resource_id not like", value, "resourceId");
            return (Criteria) this;
        }
        public Criteria andResourceIdIn(List<String> values) {
            addCriterion("resource_id in", values, "resourceId");
            return (Criteria) this;
        }
        public Criteria andResourceIdNotIn(List<String> values) {
            addCriterion("resource_id not in", values, "resourceId");
            return (Criteria) this;
        }
        public Criteria andResourceIdBetween(String value1, String value2) {
            addCriterion("resource_id between", value1, value2, "resourceId");
            return (Criteria) this;
        }
        public Criteria andResourceIdNotBetween(String value1, String value2) {
            addCriterion("resource_id not between", value1, value2, "resourceId");
            return (Criteria) this;
        }
    }

    /**
    * 绿驰汽车
    * tableName: resource_scope
    * @author zhaoyb
    */
    public static class Criteria extends GeneratedCriteria {


        protected Criteria() {
            super();
        }
    }

    /**
    * 绿驰汽车
    * tableName: resource_scope
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