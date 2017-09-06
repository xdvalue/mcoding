package com.mcoding.base.auth.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mcoding.base.core.IExample;
import com.mcoding.base.core.PageView;

public class UserRoleExample implements IExample<UserRole>{
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;
    
    protected PageView<UserRole> pageView;

    public UserRoleExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public PageView<UserRole> getPageView() {
		return pageView;
	}

	public void setPageView(PageView<UserRole> pageView) {
		this.pageView = pageView;
	}
	
	@Override
	public String toJson() throws JsonProcessingException {
		ObjectMapper objectMapper = Jackson2ObjectMapperBuilder.json().build();
		return objectMapper.writeValueAsString(this);
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

        public Criteria andRightIdIsNull() {
            addCriterion("rightId is null");
            return (Criteria) this;
        }

        public Criteria andRightIdIsNotNull() {
            addCriterion("rightId is not null");
            return (Criteria) this;
        }

        public Criteria andRightIdEqualTo(Integer value) {
            addCriterion("rightId =", value, "rightId");
            return (Criteria) this;
        }

        public Criteria andRightIdNotEqualTo(Integer value) {
            addCriterion("rightId <>", value, "rightId");
            return (Criteria) this;
        }

        public Criteria andRightIdGreaterThan(Integer value) {
            addCriterion("rightId >", value, "rightId");
            return (Criteria) this;
        }

        public Criteria andRightIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("rightId >=", value, "rightId");
            return (Criteria) this;
        }

        public Criteria andRightIdLessThan(Integer value) {
            addCriterion("rightId <", value, "rightId");
            return (Criteria) this;
        }

        public Criteria andRightIdLessThanOrEqualTo(Integer value) {
            addCriterion("rightId <=", value, "rightId");
            return (Criteria) this;
        }

        public Criteria andRightIdIn(List<Integer> values) {
            addCriterion("rightId in", values, "rightId");
            return (Criteria) this;
        }

        public Criteria andRightIdNotIn(List<Integer> values) {
            addCriterion("rightId not in", values, "rightId");
            return (Criteria) this;
        }

        public Criteria andRightIdBetween(Integer value1, Integer value2) {
            addCriterion("rightId between", value1, value2, "rightId");
            return (Criteria) this;
        }

        public Criteria andRightIdNotBetween(Integer value1, Integer value2) {
            addCriterion("rightId not between", value1, value2, "rightId");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("userId is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("userId is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(Integer value) {
            addCriterion("userId =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(Integer value) {
            addCriterion("userId <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(Integer value) {
            addCriterion("userId >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("userId >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(Integer value) {
            addCriterion("userId <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("userId <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<Integer> values) {
            addCriterion("userId in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<Integer> values) {
            addCriterion("userId not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(Integer value1, Integer value2) {
            addCriterion("userId between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("userId not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andRoleIdIsNull() {
            addCriterion("roleId is null");
            return (Criteria) this;
        }

        public Criteria andRoleIdIsNotNull() {
            addCriterion("roleId is not null");
            return (Criteria) this;
        }

        public Criteria andRoleIdEqualTo(Integer value) {
            addCriterion("roleId =", value, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdNotEqualTo(Integer value) {
            addCriterion("roleId <>", value, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdGreaterThan(Integer value) {
            addCriterion("roleId >", value, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("roleId >=", value, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdLessThan(Integer value) {
            addCriterion("roleId <", value, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdLessThanOrEqualTo(Integer value) {
            addCriterion("roleId <=", value, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdIn(List<Integer> values) {
            addCriterion("roleId in", values, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdNotIn(List<Integer> values) {
            addCriterion("roleId not in", values, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdBetween(Integer value1, Integer value2) {
            addCriterion("roleId between", value1, value2, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdNotBetween(Integer value1, Integer value2) {
            addCriterion("roleId not between", value1, value2, "roleId");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("createTime is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("createTime is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("createTime =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("createTime <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("createTime >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("createTime >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("createTime <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("createTime <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("createTime in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("createTime not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("createTime between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("createTime not between", value1, value2, "createTime");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

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