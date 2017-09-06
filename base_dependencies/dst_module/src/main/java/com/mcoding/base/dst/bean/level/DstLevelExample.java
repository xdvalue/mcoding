package com.mcoding.base.dst.bean.level;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mcoding.base.core.IExample;
import com.mcoding.base.core.PageView;
import com.mcoding.base.utils.json.JsonUtilsForMcoding;
import java.util.ArrayList;
import java.util.List;

public class DstLevelExample implements IExample<DstLevel> {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected PageView<DstLevel> pageView;

    public DstLevelExample() {
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
    public PageView<DstLevel> getPageView() {
        return pageView;
    }

    @Override
    public void setPageView(PageView<DstLevel> pageView) {
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

        public Criteria andLevelNameIsNull() {
            addCriterion("level_name is null");
            return (Criteria) this;
        }

        public Criteria andLevelNameIsNotNull() {
            addCriterion("level_name is not null");
            return (Criteria) this;
        }

        public Criteria andLevelNameEqualTo(String value) {
            addCriterion("level_name =", value, "levelName");
            return (Criteria) this;
        }

        public Criteria andLevelNameNotEqualTo(String value) {
            addCriterion("level_name <>", value, "levelName");
            return (Criteria) this;
        }

        public Criteria andLevelNameGreaterThan(String value) {
            addCriterion("level_name >", value, "levelName");
            return (Criteria) this;
        }

        public Criteria andLevelNameGreaterThanOrEqualTo(String value) {
            addCriterion("level_name >=", value, "levelName");
            return (Criteria) this;
        }

        public Criteria andLevelNameLessThan(String value) {
            addCriterion("level_name <", value, "levelName");
            return (Criteria) this;
        }

        public Criteria andLevelNameLessThanOrEqualTo(String value) {
            addCriterion("level_name <=", value, "levelName");
            return (Criteria) this;
        }

        public Criteria andLevelNameLike(String value) {
            addCriterion("level_name like", value, "levelName");
            return (Criteria) this;
        }

        public Criteria andLevelNameNotLike(String value) {
            addCriterion("level_name not like", value, "levelName");
            return (Criteria) this;
        }

        public Criteria andLevelNameIn(List<String> values) {
            addCriterion("level_name in", values, "levelName");
            return (Criteria) this;
        }

        public Criteria andLevelNameNotIn(List<String> values) {
            addCriterion("level_name not in", values, "levelName");
            return (Criteria) this;
        }

        public Criteria andLevelNameBetween(String value1, String value2) {
            addCriterion("level_name between", value1, value2, "levelName");
            return (Criteria) this;
        }

        public Criteria andLevelNameNotBetween(String value1, String value2) {
            addCriterion("level_name not between", value1, value2, "levelName");
            return (Criteria) this;
        }

        public Criteria andLevelGradeIsNull() {
            addCriterion("level_grade is null");
            return (Criteria) this;
        }

        public Criteria andLevelGradeIsNotNull() {
            addCriterion("level_grade is not null");
            return (Criteria) this;
        }

        public Criteria andLevelGradeEqualTo(String value) {
            addCriterion("level_grade =", value, "levelGrade");
            return (Criteria) this;
        }

        public Criteria andLevelGradeNotEqualTo(String value) {
            addCriterion("level_grade <>", value, "levelGrade");
            return (Criteria) this;
        }

        public Criteria andLevelGradeGreaterThan(String value) {
            addCriterion("level_grade >", value, "levelGrade");
            return (Criteria) this;
        }

        public Criteria andLevelGradeGreaterThanOrEqualTo(String value) {
            addCriterion("level_grade >=", value, "levelGrade");
            return (Criteria) this;
        }

        public Criteria andLevelGradeLessThan(String value) {
            addCriterion("level_grade <", value, "levelGrade");
            return (Criteria) this;
        }

        public Criteria andLevelGradeLessThanOrEqualTo(String value) {
            addCriterion("level_grade <=", value, "levelGrade");
            return (Criteria) this;
        }

        public Criteria andLevelGradeLike(String value) {
            addCriterion("level_grade like", value, "levelGrade");
            return (Criteria) this;
        }

        public Criteria andLevelGradeNotLike(String value) {
            addCriterion("level_grade not like", value, "levelGrade");
            return (Criteria) this;
        }

        public Criteria andLevelGradeIn(List<String> values) {
            addCriterion("level_grade in", values, "levelGrade");
            return (Criteria) this;
        }

        public Criteria andLevelGradeNotIn(List<String> values) {
            addCriterion("level_grade not in", values, "levelGrade");
            return (Criteria) this;
        }

        public Criteria andLevelGradeBetween(String value1, String value2) {
            addCriterion("level_grade between", value1, value2, "levelGrade");
            return (Criteria) this;
        }

        public Criteria andLevelGradeNotBetween(String value1, String value2) {
            addCriterion("level_grade not between", value1, value2, "levelGrade");
            return (Criteria) this;
        }

        public Criteria andParentLevelIdIsNull() {
            addCriterion("parent_level_id is null");
            return (Criteria) this;
        }

        public Criteria andParentLevelIdIsNotNull() {
            addCriterion("parent_level_id is not null");
            return (Criteria) this;
        }

        public Criteria andParentLevelIdEqualTo(Integer value) {
            addCriterion("parent_level_id =", value, "parentLevelId");
            return (Criteria) this;
        }

        public Criteria andParentLevelIdNotEqualTo(Integer value) {
            addCriterion("parent_level_id <>", value, "parentLevelId");
            return (Criteria) this;
        }

        public Criteria andParentLevelIdGreaterThan(Integer value) {
            addCriterion("parent_level_id >", value, "parentLevelId");
            return (Criteria) this;
        }

        public Criteria andParentLevelIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("parent_level_id >=", value, "parentLevelId");
            return (Criteria) this;
        }

        public Criteria andParentLevelIdLessThan(Integer value) {
            addCriterion("parent_level_id <", value, "parentLevelId");
            return (Criteria) this;
        }

        public Criteria andParentLevelIdLessThanOrEqualTo(Integer value) {
            addCriterion("parent_level_id <=", value, "parentLevelId");
            return (Criteria) this;
        }

        public Criteria andParentLevelIdIn(List<Integer> values) {
            addCriterion("parent_level_id in", values, "parentLevelId");
            return (Criteria) this;
        }

        public Criteria andParentLevelIdNotIn(List<Integer> values) {
            addCriterion("parent_level_id not in", values, "parentLevelId");
            return (Criteria) this;
        }

        public Criteria andParentLevelIdBetween(Integer value1, Integer value2) {
            addCriterion("parent_level_id between", value1, value2, "parentLevelId");
            return (Criteria) this;
        }

        public Criteria andParentLevelIdNotBetween(Integer value1, Integer value2) {
            addCriterion("parent_level_id not between", value1, value2, "parentLevelId");
            return (Criteria) this;
        }

        public Criteria andIsEnableIsNull() {
            addCriterion("is_enable is null");
            return (Criteria) this;
        }

        public Criteria andIsEnableIsNotNull() {
            addCriterion("is_enable is not null");
            return (Criteria) this;
        }

        public Criteria andIsEnableEqualTo(Integer value) {
            addCriterion("is_enable =", value, "isEnable");
            return (Criteria) this;
        }

        public Criteria andIsEnableNotEqualTo(Integer value) {
            addCriterion("is_enable <>", value, "isEnable");
            return (Criteria) this;
        }

        public Criteria andIsEnableGreaterThan(Integer value) {
            addCriterion("is_enable >", value, "isEnable");
            return (Criteria) this;
        }

        public Criteria andIsEnableGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_enable >=", value, "isEnable");
            return (Criteria) this;
        }

        public Criteria andIsEnableLessThan(Integer value) {
            addCriterion("is_enable <", value, "isEnable");
            return (Criteria) this;
        }

        public Criteria andIsEnableLessThanOrEqualTo(Integer value) {
            addCriterion("is_enable <=", value, "isEnable");
            return (Criteria) this;
        }

        public Criteria andIsEnableIn(List<Integer> values) {
            addCriterion("is_enable in", values, "isEnable");
            return (Criteria) this;
        }

        public Criteria andIsEnableNotIn(List<Integer> values) {
            addCriterion("is_enable not in", values, "isEnable");
            return (Criteria) this;
        }

        public Criteria andIsEnableBetween(Integer value1, Integer value2) {
            addCriterion("is_enable between", value1, value2, "isEnable");
            return (Criteria) this;
        }

        public Criteria andIsEnableNotBetween(Integer value1, Integer value2) {
            addCriterion("is_enable not between", value1, value2, "isEnable");
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