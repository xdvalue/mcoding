package com.mcoding.base.member.bean.setting;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mcoding.base.core.IExample;
import com.mcoding.base.core.PageView;
import com.mcoding.base.utils.json.JsonUtilsForMcoding;

public class MemberSettingKeyExample implements IExample<MemberSettingKey> {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected PageView<MemberSettingKey> pageView;

    public MemberSettingKeyExample() {
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

    @Override
    public PageView<MemberSettingKey> getPageView() {
        return pageView;
    }

    @Override
    public void setPageView(PageView<MemberSettingKey> pageView) {
        this.pageView = pageView;
    }

    public String toJson() throws JsonProcessingException {
        return JsonUtilsForMcoding.writeValueAsString(this);
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

        public Criteria andModuleTypeIsNull() {
            addCriterion("module_type is null");
            return (Criteria) this;
        }

        public Criteria andModuleTypeIsNotNull() {
            addCriterion("module_type is not null");
            return (Criteria) this;
        }

        public Criteria andModuleTypeEqualTo(Integer value) {
            addCriterion("module_type =", value, "moduleType");
            return (Criteria) this;
        }

        public Criteria andModuleTypeNotEqualTo(Integer value) {
            addCriterion("module_type <>", value, "moduleType");
            return (Criteria) this;
        }

        public Criteria andModuleTypeGreaterThan(Integer value) {
            addCriterion("module_type >", value, "moduleType");
            return (Criteria) this;
        }

        public Criteria andModuleTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("module_type >=", value, "moduleType");
            return (Criteria) this;
        }

        public Criteria andModuleTypeLessThan(Integer value) {
            addCriterion("module_type <", value, "moduleType");
            return (Criteria) this;
        }

        public Criteria andModuleTypeLessThanOrEqualTo(Integer value) {
            addCriterion("module_type <=", value, "moduleType");
            return (Criteria) this;
        }

        public Criteria andModuleTypeIn(List<Integer> values) {
            addCriterion("module_type in", values, "moduleType");
            return (Criteria) this;
        }

        public Criteria andModuleTypeNotIn(List<Integer> values) {
            addCriterion("module_type not in", values, "moduleType");
            return (Criteria) this;
        }

        public Criteria andModuleTypeBetween(Integer value1, Integer value2) {
            addCriterion("module_type between", value1, value2, "moduleType");
            return (Criteria) this;
        }

        public Criteria andModuleTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("module_type not between", value1, value2, "moduleType");
            return (Criteria) this;
        }

        public Criteria andSettingNameIsNull() {
            addCriterion("setting_name is null");
            return (Criteria) this;
        }

        public Criteria andSettingNameIsNotNull() {
            addCriterion("setting_name is not null");
            return (Criteria) this;
        }

        public Criteria andSettingNameEqualTo(String value) {
            addCriterion("setting_name =", value, "settingName");
            return (Criteria) this;
        }

        public Criteria andSettingNameNotEqualTo(String value) {
            addCriterion("setting_name <>", value, "settingName");
            return (Criteria) this;
        }

        public Criteria andSettingNameGreaterThan(String value) {
            addCriterion("setting_name >", value, "settingName");
            return (Criteria) this;
        }

        public Criteria andSettingNameGreaterThanOrEqualTo(String value) {
            addCriterion("setting_name >=", value, "settingName");
            return (Criteria) this;
        }

        public Criteria andSettingNameLessThan(String value) {
            addCriterion("setting_name <", value, "settingName");
            return (Criteria) this;
        }

        public Criteria andSettingNameLessThanOrEqualTo(String value) {
            addCriterion("setting_name <=", value, "settingName");
            return (Criteria) this;
        }

        public Criteria andSettingNameLike(String value) {
            addCriterion("setting_name like", value, "settingName");
            return (Criteria) this;
        }

        public Criteria andSettingNameNotLike(String value) {
            addCriterion("setting_name not like", value, "settingName");
            return (Criteria) this;
        }

        public Criteria andSettingNameIn(List<String> values) {
            addCriterion("setting_name in", values, "settingName");
            return (Criteria) this;
        }

        public Criteria andSettingNameNotIn(List<String> values) {
            addCriterion("setting_name not in", values, "settingName");
            return (Criteria) this;
        }

        public Criteria andSettingNameBetween(String value1, String value2) {
            addCriterion("setting_name between", value1, value2, "settingName");
            return (Criteria) this;
        }

        public Criteria andSettingNameNotBetween(String value1, String value2) {
            addCriterion("setting_name not between", value1, value2, "settingName");
            return (Criteria) this;
        }

        public Criteria andSettingCodeIsNull() {
            addCriterion("setting_code is null");
            return (Criteria) this;
        }

        public Criteria andSettingCodeIsNotNull() {
            addCriterion("setting_code is not null");
            return (Criteria) this;
        }

        public Criteria andSettingCodeEqualTo(String value) {
            addCriterion("setting_code =", value, "settingCode");
            return (Criteria) this;
        }

        public Criteria andSettingCodeNotEqualTo(String value) {
            addCriterion("setting_code <>", value, "settingCode");
            return (Criteria) this;
        }

        public Criteria andSettingCodeGreaterThan(String value) {
            addCriterion("setting_code >", value, "settingCode");
            return (Criteria) this;
        }

        public Criteria andSettingCodeGreaterThanOrEqualTo(String value) {
            addCriterion("setting_code >=", value, "settingCode");
            return (Criteria) this;
        }

        public Criteria andSettingCodeLessThan(String value) {
            addCriterion("setting_code <", value, "settingCode");
            return (Criteria) this;
        }

        public Criteria andSettingCodeLessThanOrEqualTo(String value) {
            addCriterion("setting_code <=", value, "settingCode");
            return (Criteria) this;
        }

        public Criteria andSettingCodeLike(String value) {
            addCriterion("setting_code like", value, "settingCode");
            return (Criteria) this;
        }

        public Criteria andSettingCodeNotLike(String value) {
            addCriterion("setting_code not like", value, "settingCode");
            return (Criteria) this;
        }

        public Criteria andSettingCodeIn(List<String> values) {
            addCriterion("setting_code in", values, "settingCode");
            return (Criteria) this;
        }

        public Criteria andSettingCodeNotIn(List<String> values) {
            addCriterion("setting_code not in", values, "settingCode");
            return (Criteria) this;
        }

        public Criteria andSettingCodeBetween(String value1, String value2) {
            addCriterion("setting_code between", value1, value2, "settingCode");
            return (Criteria) this;
        }

        public Criteria andSettingCodeNotBetween(String value1, String value2) {
            addCriterion("setting_code not between", value1, value2, "settingCode");
            return (Criteria) this;
        }

        public Criteria andSettingDefaultValueIsNull() {
            addCriterion("setting_default_value is null");
            return (Criteria) this;
        }

        public Criteria andSettingDefaultValueIsNotNull() {
            addCriterion("setting_default_value is not null");
            return (Criteria) this;
        }

        public Criteria andSettingDefaultValueEqualTo(String value) {
            addCriterion("setting_default_value =", value, "settingDefaultValue");
            return (Criteria) this;
        }

        public Criteria andSettingDefaultValueNotEqualTo(String value) {
            addCriterion("setting_default_value <>", value, "settingDefaultValue");
            return (Criteria) this;
        }

        public Criteria andSettingDefaultValueGreaterThan(String value) {
            addCriterion("setting_default_value >", value, "settingDefaultValue");
            return (Criteria) this;
        }

        public Criteria andSettingDefaultValueGreaterThanOrEqualTo(String value) {
            addCriterion("setting_default_value >=", value, "settingDefaultValue");
            return (Criteria) this;
        }

        public Criteria andSettingDefaultValueLessThan(String value) {
            addCriterion("setting_default_value <", value, "settingDefaultValue");
            return (Criteria) this;
        }

        public Criteria andSettingDefaultValueLessThanOrEqualTo(String value) {
            addCriterion("setting_default_value <=", value, "settingDefaultValue");
            return (Criteria) this;
        }

        public Criteria andSettingDefaultValueLike(String value) {
            addCriterion("setting_default_value like", value, "settingDefaultValue");
            return (Criteria) this;
        }

        public Criteria andSettingDefaultValueNotLike(String value) {
            addCriterion("setting_default_value not like", value, "settingDefaultValue");
            return (Criteria) this;
        }

        public Criteria andSettingDefaultValueIn(List<String> values) {
            addCriterion("setting_default_value in", values, "settingDefaultValue");
            return (Criteria) this;
        }

        public Criteria andSettingDefaultValueNotIn(List<String> values) {
            addCriterion("setting_default_value not in", values, "settingDefaultValue");
            return (Criteria) this;
        }

        public Criteria andSettingDefaultValueBetween(String value1, String value2) {
            addCriterion("setting_default_value between", value1, value2, "settingDefaultValue");
            return (Criteria) this;
        }

        public Criteria andSettingDefaultValueNotBetween(String value1, String value2) {
            addCriterion("setting_default_value not between", value1, value2, "settingDefaultValue");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("remark is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("remark is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("remark =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("remark <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("remark >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("remark >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("remark <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("remark <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("remark like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("remark not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("remark in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("remark not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("remark between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("remark not between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
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