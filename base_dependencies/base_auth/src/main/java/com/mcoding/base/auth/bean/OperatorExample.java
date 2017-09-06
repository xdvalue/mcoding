package com.mcoding.base.auth.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mcoding.base.core.IExample;
import com.mcoding.base.core.PageView;

public class OperatorExample implements IExample<Operator>{
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;
    
    protected PageView<Operator> pageView;

    public OperatorExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public PageView<Operator> getPageView() {
		return pageView;
	}

	public void setPageView(PageView<Operator> pageView) {
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

        public Criteria andOperIdIsNull() {
            addCriterion("operId is null");
            return (Criteria) this;
        }

        public Criteria andOperIdIsNotNull() {
            addCriterion("operId is not null");
            return (Criteria) this;
        }

        public Criteria andOperIdEqualTo(Integer value) {
            addCriterion("operId =", value, "operId");
            return (Criteria) this;
        }

        public Criteria andOperIdNotEqualTo(Integer value) {
            addCriterion("operId <>", value, "operId");
            return (Criteria) this;
        }

        public Criteria andOperIdGreaterThan(Integer value) {
            addCriterion("operId >", value, "operId");
            return (Criteria) this;
        }

        public Criteria andOperIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("operId >=", value, "operId");
            return (Criteria) this;
        }

        public Criteria andOperIdLessThan(Integer value) {
            addCriterion("operId <", value, "operId");
            return (Criteria) this;
        }

        public Criteria andOperIdLessThanOrEqualTo(Integer value) {
            addCriterion("operId <=", value, "operId");
            return (Criteria) this;
        }

        public Criteria andOperIdIn(List<Integer> values) {
            addCriterion("operId in", values, "operId");
            return (Criteria) this;
        }

        public Criteria andOperIdNotIn(List<Integer> values) {
            addCriterion("operId not in", values, "operId");
            return (Criteria) this;
        }

        public Criteria andOperIdBetween(Integer value1, Integer value2) {
            addCriterion("operId between", value1, value2, "operId");
            return (Criteria) this;
        }

        public Criteria andOperIdNotBetween(Integer value1, Integer value2) {
            addCriterion("operId not between", value1, value2, "operId");
            return (Criteria) this;
        }

        public Criteria andOperNameIsNull() {
            addCriterion("operName is null");
            return (Criteria) this;
        }

        public Criteria andOperNameIsNotNull() {
            addCriterion("operName is not null");
            return (Criteria) this;
        }

        public Criteria andOperNameEqualTo(String value) {
            addCriterion("operName =", value, "operName");
            return (Criteria) this;
        }

        public Criteria andOperNameNotEqualTo(String value) {
            addCriterion("operName <>", value, "operName");
            return (Criteria) this;
        }

        public Criteria andOperNameGreaterThan(String value) {
            addCriterion("operName >", value, "operName");
            return (Criteria) this;
        }

        public Criteria andOperNameGreaterThanOrEqualTo(String value) {
            addCriterion("operName >=", value, "operName");
            return (Criteria) this;
        }

        public Criteria andOperNameLessThan(String value) {
            addCriterion("operName <", value, "operName");
            return (Criteria) this;
        }

        public Criteria andOperNameLessThanOrEqualTo(String value) {
            addCriterion("operName <=", value, "operName");
            return (Criteria) this;
        }

        public Criteria andOperNameLike(String value) {
            addCriterion("operName like", value, "operName");
            return (Criteria) this;
        }

        public Criteria andOperNameNotLike(String value) {
            addCriterion("operName not like", value, "operName");
            return (Criteria) this;
        }

        public Criteria andOperNameIn(List<String> values) {
            addCriterion("operName in", values, "operName");
            return (Criteria) this;
        }

        public Criteria andOperNameNotIn(List<String> values) {
            addCriterion("operName not in", values, "operName");
            return (Criteria) this;
        }

        public Criteria andOperNameBetween(String value1, String value2) {
            addCriterion("operName between", value1, value2, "operName");
            return (Criteria) this;
        }

        public Criteria andOperNameNotBetween(String value1, String value2) {
            addCriterion("operName not between", value1, value2, "operName");
            return (Criteria) this;
        }

        public Criteria andOperCodeIsNull() {
            addCriterion("operCode is null");
            return (Criteria) this;
        }

        public Criteria andOperCodeIsNotNull() {
            addCriterion("operCode is not null");
            return (Criteria) this;
        }

        public Criteria andOperCodeEqualTo(String value) {
            addCriterion("operCode =", value, "operCode");
            return (Criteria) this;
        }

        public Criteria andOperCodeNotEqualTo(String value) {
            addCriterion("operCode <>", value, "operCode");
            return (Criteria) this;
        }

        public Criteria andOperCodeGreaterThan(String value) {
            addCriterion("operCode >", value, "operCode");
            return (Criteria) this;
        }

        public Criteria andOperCodeGreaterThanOrEqualTo(String value) {
            addCriterion("operCode >=", value, "operCode");
            return (Criteria) this;
        }

        public Criteria andOperCodeLessThan(String value) {
            addCriterion("operCode <", value, "operCode");
            return (Criteria) this;
        }

        public Criteria andOperCodeLessThanOrEqualTo(String value) {
            addCriterion("operCode <=", value, "operCode");
            return (Criteria) this;
        }

        public Criteria andOperCodeLike(String value) {
            addCriterion("operCode like", value, "operCode");
            return (Criteria) this;
        }

        public Criteria andOperCodeNotLike(String value) {
            addCriterion("operCode not like", value, "operCode");
            return (Criteria) this;
        }

        public Criteria andOperCodeIn(List<String> values) {
            addCriterion("operCode in", values, "operCode");
            return (Criteria) this;
        }

        public Criteria andOperCodeNotIn(List<String> values) {
            addCriterion("operCode not in", values, "operCode");
            return (Criteria) this;
        }

        public Criteria andOperCodeBetween(String value1, String value2) {
            addCriterion("operCode between", value1, value2, "operCode");
            return (Criteria) this;
        }

        public Criteria andOperCodeNotBetween(String value1, String value2) {
            addCriterion("operCode not between", value1, value2, "operCode");
            return (Criteria) this;
        }

        public Criteria andOperURLIsNull() {
            addCriterion("operURL is null");
            return (Criteria) this;
        }

        public Criteria andOperURLIsNotNull() {
            addCriterion("operURL is not null");
            return (Criteria) this;
        }

        public Criteria andOperURLEqualTo(String value) {
            addCriterion("operURL =", value, "operURL");
            return (Criteria) this;
        }

        public Criteria andOperURLNotEqualTo(String value) {
            addCriterion("operURL <>", value, "operURL");
            return (Criteria) this;
        }

        public Criteria andOperURLGreaterThan(String value) {
            addCriterion("operURL >", value, "operURL");
            return (Criteria) this;
        }

        public Criteria andOperURLGreaterThanOrEqualTo(String value) {
            addCriterion("operURL >=", value, "operURL");
            return (Criteria) this;
        }

        public Criteria andOperURLLessThan(String value) {
            addCriterion("operURL <", value, "operURL");
            return (Criteria) this;
        }

        public Criteria andOperURLLessThanOrEqualTo(String value) {
            addCriterion("operURL <=", value, "operURL");
            return (Criteria) this;
        }

        public Criteria andOperURLLike(String value) {
            addCriterion("operURL like", value, "operURL");
            return (Criteria) this;
        }

        public Criteria andOperURLNotLike(String value) {
            addCriterion("operURL not like", value, "operURL");
            return (Criteria) this;
        }

        public Criteria andOperURLIn(List<String> values) {
            addCriterion("operURL in", values, "operURL");
            return (Criteria) this;
        }

        public Criteria andOperURLNotIn(List<String> values) {
            addCriterion("operURL not in", values, "operURL");
            return (Criteria) this;
        }

        public Criteria andOperURLBetween(String value1, String value2) {
            addCriterion("operURL between", value1, value2, "operURL");
            return (Criteria) this;
        }

        public Criteria andOperURLNotBetween(String value1, String value2) {
            addCriterion("operURL not between", value1, value2, "operURL");
            return (Criteria) this;
        }

        public Criteria andMenuIdIsNull() {
            addCriterion("menuId is null");
            return (Criteria) this;
        }

        public Criteria andMenuIdIsNotNull() {
            addCriterion("menuId is not null");
            return (Criteria) this;
        }

        public Criteria andMenuIdEqualTo(Integer value) {
            addCriterion("menuId =", value, "menuId");
            return (Criteria) this;
        }

        public Criteria andMenuIdNotEqualTo(Integer value) {
            addCriterion("menuId <>", value, "menuId");
            return (Criteria) this;
        }

        public Criteria andMenuIdGreaterThan(Integer value) {
            addCriterion("menuId >", value, "menuId");
            return (Criteria) this;
        }

        public Criteria andMenuIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("menuId >=", value, "menuId");
            return (Criteria) this;
        }

        public Criteria andMenuIdLessThan(Integer value) {
            addCriterion("menuId <", value, "menuId");
            return (Criteria) this;
        }

        public Criteria andMenuIdLessThanOrEqualTo(Integer value) {
            addCriterion("menuId <=", value, "menuId");
            return (Criteria) this;
        }

        public Criteria andMenuIdIn(List<Integer> values) {
            addCriterion("menuId in", values, "menuId");
            return (Criteria) this;
        }

        public Criteria andMenuIdNotIn(List<Integer> values) {
            addCriterion("menuId not in", values, "menuId");
            return (Criteria) this;
        }

        public Criteria andMenuIdBetween(Integer value1, Integer value2) {
            addCriterion("menuId between", value1, value2, "menuId");
            return (Criteria) this;
        }

        public Criteria andMenuIdNotBetween(Integer value1, Integer value2) {
            addCriterion("menuId not between", value1, value2, "menuId");
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

        public Criteria andUpdateTimeIsNull() {
            addCriterion("updateTime is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("updateTime is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("updateTime =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("updateTime <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("updateTime >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("updateTime >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("updateTime <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("updateTime <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("updateTime in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("updateTime not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("updateTime between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("updateTime not between", value1, value2, "updateTime");
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