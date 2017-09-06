package com.mcoding.base.auth.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mcoding.base.core.IExample;
import com.mcoding.base.core.PageView;

public class MenuExample implements IExample<Menu>{
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;
    
    protected PageView<Menu> pageView;

    public MenuExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public PageView<Menu> getPageView() {
		return pageView;
	}

	public void setPageView(PageView<Menu> pageView) {
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

        public Criteria andParentIdIsNull() {
            addCriterion("parentId is null");
            return (Criteria) this;
        }

        public Criteria andParentIdIsNotNull() {
            addCriterion("parentId is not null");
            return (Criteria) this;
        }

        public Criteria andParentIdEqualTo(Integer value) {
            addCriterion("parentId =", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotEqualTo(Integer value) {
            addCriterion("parentId <>", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdGreaterThan(Integer value) {
            addCriterion("parentId >", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("parentId >=", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdLessThan(Integer value) {
            addCriterion("parentId <", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdLessThanOrEqualTo(Integer value) {
            addCriterion("parentId <=", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdIn(List<Integer> values) {
            addCriterion("parentId in", values, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotIn(List<Integer> values) {
            addCriterion("parentId not in", values, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdBetween(Integer value1, Integer value2) {
            addCriterion("parentId between", value1, value2, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotBetween(Integer value1, Integer value2) {
            addCriterion("parentId not between", value1, value2, "parentId");
            return (Criteria) this;
        }

        public Criteria andMenuTypeIsNull() {
            addCriterion("menuType is null");
            return (Criteria) this;
        }

        public Criteria andMenuTypeIsNotNull() {
            addCriterion("menuType is not null");
            return (Criteria) this;
        }

        public Criteria andMenuTypeEqualTo(String value) {
            addCriterion("menuType =", value, "menuType");
            return (Criteria) this;
        }

        public Criteria andMenuTypeNotEqualTo(String value) {
            addCriterion("menuType <>", value, "menuType");
            return (Criteria) this;
        }

        public Criteria andMenuTypeGreaterThan(String value) {
            addCriterion("menuType >", value, "menuType");
            return (Criteria) this;
        }

        public Criteria andMenuTypeGreaterThanOrEqualTo(String value) {
            addCriterion("menuType >=", value, "menuType");
            return (Criteria) this;
        }

        public Criteria andMenuTypeLessThan(String value) {
            addCriterion("menuType <", value, "menuType");
            return (Criteria) this;
        }

        public Criteria andMenuTypeLessThanOrEqualTo(String value) {
            addCriterion("menuType <=", value, "menuType");
            return (Criteria) this;
        }

        public Criteria andMenuTypeLike(String value) {
            addCriterion("menuType like", value, "menuType");
            return (Criteria) this;
        }

        public Criteria andMenuTypeNotLike(String value) {
            addCriterion("menuType not like", value, "menuType");
            return (Criteria) this;
        }

        public Criteria andMenuTypeIn(List<String> values) {
            addCriterion("menuType in", values, "menuType");
            return (Criteria) this;
        }

        public Criteria andMenuTypeNotIn(List<String> values) {
            addCriterion("menuType not in", values, "menuType");
            return (Criteria) this;
        }

        public Criteria andMenuTypeBetween(String value1, String value2) {
            addCriterion("menuType between", value1, value2, "menuType");
            return (Criteria) this;
        }

        public Criteria andMenuTypeNotBetween(String value1, String value2) {
            addCriterion("menuType not between", value1, value2, "menuType");
            return (Criteria) this;
        }

        public Criteria andMenuNameIsNull() {
            addCriterion("menuName is null");
            return (Criteria) this;
        }

        public Criteria andMenuNameIsNotNull() {
            addCriterion("menuName is not null");
            return (Criteria) this;
        }

        public Criteria andMenuNameEqualTo(String value) {
            addCriterion("menuName =", value, "menuName");
            return (Criteria) this;
        }

        public Criteria andMenuNameNotEqualTo(String value) {
            addCriterion("menuName <>", value, "menuName");
            return (Criteria) this;
        }

        public Criteria andMenuNameGreaterThan(String value) {
            addCriterion("menuName >", value, "menuName");
            return (Criteria) this;
        }

        public Criteria andMenuNameGreaterThanOrEqualTo(String value) {
            addCriterion("menuName >=", value, "menuName");
            return (Criteria) this;
        }

        public Criteria andMenuNameLessThan(String value) {
            addCriterion("menuName <", value, "menuName");
            return (Criteria) this;
        }

        public Criteria andMenuNameLessThanOrEqualTo(String value) {
            addCriterion("menuName <=", value, "menuName");
            return (Criteria) this;
        }

        public Criteria andMenuNameLike(String value) {
            addCriterion("menuName like", value, "menuName");
            return (Criteria) this;
        }

        public Criteria andMenuNameNotLike(String value) {
            addCriterion("menuName not like", value, "menuName");
            return (Criteria) this;
        }

        public Criteria andMenuNameIn(List<String> values) {
            addCriterion("menuName in", values, "menuName");
            return (Criteria) this;
        }

        public Criteria andMenuNameNotIn(List<String> values) {
            addCriterion("menuName not in", values, "menuName");
            return (Criteria) this;
        }

        public Criteria andMenuNameBetween(String value1, String value2) {
            addCriterion("menuName between", value1, value2, "menuName");
            return (Criteria) this;
        }

        public Criteria andMenuNameNotBetween(String value1, String value2) {
            addCriterion("menuName not between", value1, value2, "menuName");
            return (Criteria) this;
        }

        public Criteria andMenuCodeIsNull() {
            addCriterion("menuCode is null");
            return (Criteria) this;
        }

        public Criteria andMenuCodeIsNotNull() {
            addCriterion("menuCode is not null");
            return (Criteria) this;
        }

        public Criteria andMenuCodeEqualTo(String value) {
            addCriterion("menuCode =", value, "menuCode");
            return (Criteria) this;
        }

        public Criteria andMenuCodeNotEqualTo(String value) {
            addCriterion("menuCode <>", value, "menuCode");
            return (Criteria) this;
        }

        public Criteria andMenuCodeGreaterThan(String value) {
            addCriterion("menuCode >", value, "menuCode");
            return (Criteria) this;
        }

        public Criteria andMenuCodeGreaterThanOrEqualTo(String value) {
            addCriterion("menuCode >=", value, "menuCode");
            return (Criteria) this;
        }

        public Criteria andMenuCodeLessThan(String value) {
            addCriterion("menuCode <", value, "menuCode");
            return (Criteria) this;
        }

        public Criteria andMenuCodeLessThanOrEqualTo(String value) {
            addCriterion("menuCode <=", value, "menuCode");
            return (Criteria) this;
        }

        public Criteria andMenuCodeLike(String value) {
            addCriterion("menuCode like", value, "menuCode");
            return (Criteria) this;
        }

        public Criteria andMenuCodeNotLike(String value) {
            addCriterion("menuCode not like", value, "menuCode");
            return (Criteria) this;
        }

        public Criteria andMenuCodeIn(List<String> values) {
            addCriterion("menuCode in", values, "menuCode");
            return (Criteria) this;
        }

        public Criteria andMenuCodeNotIn(List<String> values) {
            addCriterion("menuCode not in", values, "menuCode");
            return (Criteria) this;
        }

        public Criteria andMenuCodeBetween(String value1, String value2) {
            addCriterion("menuCode between", value1, value2, "menuCode");
            return (Criteria) this;
        }

        public Criteria andMenuCodeNotBetween(String value1, String value2) {
            addCriterion("menuCode not between", value1, value2, "menuCode");
            return (Criteria) this;
        }

        public Criteria andMenuURLIsNull() {
            addCriterion("menuURL is null");
            return (Criteria) this;
        }

        public Criteria andMenuURLIsNotNull() {
            addCriterion("menuURL is not null");
            return (Criteria) this;
        }

        public Criteria andMenuURLEqualTo(String value) {
            addCriterion("menuURL =", value, "menuURL");
            return (Criteria) this;
        }

        public Criteria andMenuURLNotEqualTo(String value) {
            addCriterion("menuURL <>", value, "menuURL");
            return (Criteria) this;
        }

        public Criteria andMenuURLGreaterThan(String value) {
            addCriterion("menuURL >", value, "menuURL");
            return (Criteria) this;
        }

        public Criteria andMenuURLGreaterThanOrEqualTo(String value) {
            addCriterion("menuURL >=", value, "menuURL");
            return (Criteria) this;
        }

        public Criteria andMenuURLLessThan(String value) {
            addCriterion("menuURL <", value, "menuURL");
            return (Criteria) this;
        }

        public Criteria andMenuURLLessThanOrEqualTo(String value) {
            addCriterion("menuURL <=", value, "menuURL");
            return (Criteria) this;
        }

        public Criteria andMenuURLLike(String value) {
            addCriterion("menuURL like", value, "menuURL");
            return (Criteria) this;
        }

        public Criteria andMenuURLNotLike(String value) {
            addCriterion("menuURL not like", value, "menuURL");
            return (Criteria) this;
        }

        public Criteria andMenuURLIn(List<String> values) {
            addCriterion("menuURL in", values, "menuURL");
            return (Criteria) this;
        }

        public Criteria andMenuURLNotIn(List<String> values) {
            addCriterion("menuURL not in", values, "menuURL");
            return (Criteria) this;
        }

        public Criteria andMenuURLBetween(String value1, String value2) {
            addCriterion("menuURL between", value1, value2, "menuURL");
            return (Criteria) this;
        }

        public Criteria andMenuURLNotBetween(String value1, String value2) {
            addCriterion("menuURL not between", value1, value2, "menuURL");
            return (Criteria) this;
        }

        public Criteria andSortNoIsNull() {
            addCriterion("sortNo is null");
            return (Criteria) this;
        }

        public Criteria andSortNoIsNotNull() {
            addCriterion("sortNo is not null");
            return (Criteria) this;
        }

        public Criteria andSortNoEqualTo(Integer value) {
            addCriterion("sortNo =", value, "sortNo");
            return (Criteria) this;
        }

        public Criteria andSortNoNotEqualTo(Integer value) {
            addCriterion("sortNo <>", value, "sortNo");
            return (Criteria) this;
        }

        public Criteria andSortNoGreaterThan(Integer value) {
            addCriterion("sortNo >", value, "sortNo");
            return (Criteria) this;
        }

        public Criteria andSortNoGreaterThanOrEqualTo(Integer value) {
            addCriterion("sortNo >=", value, "sortNo");
            return (Criteria) this;
        }

        public Criteria andSortNoLessThan(Integer value) {
            addCriterion("sortNo <", value, "sortNo");
            return (Criteria) this;
        }

        public Criteria andSortNoLessThanOrEqualTo(Integer value) {
            addCriterion("sortNo <=", value, "sortNo");
            return (Criteria) this;
        }

        public Criteria andSortNoIn(List<Integer> values) {
            addCriterion("sortNo in", values, "sortNo");
            return (Criteria) this;
        }

        public Criteria andSortNoNotIn(List<Integer> values) {
            addCriterion("sortNo not in", values, "sortNo");
            return (Criteria) this;
        }

        public Criteria andSortNoBetween(Integer value1, Integer value2) {
            addCriterion("sortNo between", value1, value2, "sortNo");
            return (Criteria) this;
        }

        public Criteria andSortNoNotBetween(Integer value1, Integer value2) {
            addCriterion("sortNo not between", value1, value2, "sortNo");
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

        public Criteria andMenuLogoIsNull() {
            addCriterion("menuLogo is null");
            return (Criteria) this;
        }

        public Criteria andMenuLogoIsNotNull() {
            addCriterion("menuLogo is not null");
            return (Criteria) this;
        }

        public Criteria andMenuLogoEqualTo(String value) {
            addCriterion("menuLogo =", value, "menuLogo");
            return (Criteria) this;
        }

        public Criteria andMenuLogoNotEqualTo(String value) {
            addCriterion("menuLogo <>", value, "menuLogo");
            return (Criteria) this;
        }

        public Criteria andMenuLogoGreaterThan(String value) {
            addCriterion("menuLogo >", value, "menuLogo");
            return (Criteria) this;
        }

        public Criteria andMenuLogoGreaterThanOrEqualTo(String value) {
            addCriterion("menuLogo >=", value, "menuLogo");
            return (Criteria) this;
        }

        public Criteria andMenuLogoLessThan(String value) {
            addCriterion("menuLogo <", value, "menuLogo");
            return (Criteria) this;
        }

        public Criteria andMenuLogoLessThanOrEqualTo(String value) {
            addCriterion("menuLogo <=", value, "menuLogo");
            return (Criteria) this;
        }

        public Criteria andMenuLogoLike(String value) {
            addCriterion("menuLogo like", value, "menuLogo");
            return (Criteria) this;
        }

        public Criteria andMenuLogoNotLike(String value) {
            addCriterion("menuLogo not like", value, "menuLogo");
            return (Criteria) this;
        }

        public Criteria andMenuLogoIn(List<String> values) {
            addCriterion("menuLogo in", values, "menuLogo");
            return (Criteria) this;
        }

        public Criteria andMenuLogoNotIn(List<String> values) {
            addCriterion("menuLogo not in", values, "menuLogo");
            return (Criteria) this;
        }

        public Criteria andMenuLogoBetween(String value1, String value2) {
            addCriterion("menuLogo between", value1, value2, "menuLogo");
            return (Criteria) this;
        }

        public Criteria andMenuLogoNotBetween(String value1, String value2) {
            addCriterion("menuLogo not between", value1, value2, "menuLogo");
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