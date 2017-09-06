package com.mcoding.base.ui.bean.region;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mcoding.base.core.IExample;
import com.mcoding.base.core.PageView;
import com.mcoding.base.utils.json.JsonUtilsForMcoding;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RegionExample implements IExample<Region> {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected PageView<Region> pageView;

    public RegionExample() {
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
    public PageView<Region> getPageView() {
        return pageView;
    }

    @Override
    public void setPageView(PageView<Region> pageView) {
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

        public Criteria andRegionIdIsNull() {
            addCriterion("region_id is null");
            return (Criteria) this;
        }

        public Criteria andRegionIdIsNotNull() {
            addCriterion("region_id is not null");
            return (Criteria) this;
        }

        public Criteria andRegionIdEqualTo(Integer value) {
            addCriterion("region_id =", value, "regionId");
            return (Criteria) this;
        }

        public Criteria andRegionIdNotEqualTo(Integer value) {
            addCriterion("region_id <>", value, "regionId");
            return (Criteria) this;
        }

        public Criteria andRegionIdGreaterThan(Integer value) {
            addCriterion("region_id >", value, "regionId");
            return (Criteria) this;
        }

        public Criteria andRegionIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("region_id >=", value, "regionId");
            return (Criteria) this;
        }

        public Criteria andRegionIdLessThan(Integer value) {
            addCriterion("region_id <", value, "regionId");
            return (Criteria) this;
        }

        public Criteria andRegionIdLessThanOrEqualTo(Integer value) {
            addCriterion("region_id <=", value, "regionId");
            return (Criteria) this;
        }

        public Criteria andRegionIdIn(List<Integer> values) {
            addCriterion("region_id in", values, "regionId");
            return (Criteria) this;
        }

        public Criteria andRegionIdNotIn(List<Integer> values) {
            addCriterion("region_id not in", values, "regionId");
            return (Criteria) this;
        }

        public Criteria andRegionIdBetween(Integer value1, Integer value2) {
            addCriterion("region_id between", value1, value2, "regionId");
            return (Criteria) this;
        }

        public Criteria andRegionIdNotBetween(Integer value1, Integer value2) {
            addCriterion("region_id not between", value1, value2, "regionId");
            return (Criteria) this;
        }

        public Criteria andParentIdIsNull() {
            addCriterion("parent_id is null");
            return (Criteria) this;
        }

        public Criteria andParentIdIsNotNull() {
            addCriterion("parent_id is not null");
            return (Criteria) this;
        }

        public Criteria andParentIdEqualTo(Integer value) {
            addCriterion("parent_id =", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotEqualTo(Integer value) {
            addCriterion("parent_id <>", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdGreaterThan(Integer value) {
            addCriterion("parent_id >", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("parent_id >=", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdLessThan(Integer value) {
            addCriterion("parent_id <", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdLessThanOrEqualTo(Integer value) {
            addCriterion("parent_id <=", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdIn(List<Integer> values) {
            addCriterion("parent_id in", values, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotIn(List<Integer> values) {
            addCriterion("parent_id not in", values, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdBetween(Integer value1, Integer value2) {
            addCriterion("parent_id between", value1, value2, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotBetween(Integer value1, Integer value2) {
            addCriterion("parent_id not between", value1, value2, "parentId");
            return (Criteria) this;
        }

        public Criteria andRegionNameIsNull() {
            addCriterion("region_name is null");
            return (Criteria) this;
        }

        public Criteria andRegionNameIsNotNull() {
            addCriterion("region_name is not null");
            return (Criteria) this;
        }

        public Criteria andRegionNameEqualTo(String value) {
            addCriterion("region_name =", value, "regionName");
            return (Criteria) this;
        }

        public Criteria andRegionNameNotEqualTo(String value) {
            addCriterion("region_name <>", value, "regionName");
            return (Criteria) this;
        }

        public Criteria andRegionNameGreaterThan(String value) {
            addCriterion("region_name >", value, "regionName");
            return (Criteria) this;
        }

        public Criteria andRegionNameGreaterThanOrEqualTo(String value) {
            addCriterion("region_name >=", value, "regionName");
            return (Criteria) this;
        }

        public Criteria andRegionNameLessThan(String value) {
            addCriterion("region_name <", value, "regionName");
            return (Criteria) this;
        }

        public Criteria andRegionNameLessThanOrEqualTo(String value) {
            addCriterion("region_name <=", value, "regionName");
            return (Criteria) this;
        }

        public Criteria andRegionNameLike(String value) {
            addCriterion("region_name like", value, "regionName");
            return (Criteria) this;
        }

        public Criteria andRegionNameNotLike(String value) {
            addCriterion("region_name not like", value, "regionName");
            return (Criteria) this;
        }

        public Criteria andRegionNameIn(List<String> values) {
            addCriterion("region_name in", values, "regionName");
            return (Criteria) this;
        }

        public Criteria andRegionNameNotIn(List<String> values) {
            addCriterion("region_name not in", values, "regionName");
            return (Criteria) this;
        }

        public Criteria andRegionNameBetween(String value1, String value2) {
            addCriterion("region_name between", value1, value2, "regionName");
            return (Criteria) this;
        }

        public Criteria andRegionNameNotBetween(String value1, String value2) {
            addCriterion("region_name not between", value1, value2, "regionName");
            return (Criteria) this;
        }

        public Criteria andRegionTypeIsNull() {
            addCriterion("region_type is null");
            return (Criteria) this;
        }

        public Criteria andRegionTypeIsNotNull() {
            addCriterion("region_type is not null");
            return (Criteria) this;
        }

        public Criteria andRegionTypeEqualTo(Boolean value) {
            addCriterion("region_type =", value, "regionType");
            return (Criteria) this;
        }

        public Criteria andRegionTypeNotEqualTo(Boolean value) {
            addCriterion("region_type <>", value, "regionType");
            return (Criteria) this;
        }

        public Criteria andRegionTypeGreaterThan(Boolean value) {
            addCriterion("region_type >", value, "regionType");
            return (Criteria) this;
        }

        public Criteria andRegionTypeGreaterThanOrEqualTo(Boolean value) {
            addCriterion("region_type >=", value, "regionType");
            return (Criteria) this;
        }

        public Criteria andRegionTypeLessThan(Boolean value) {
            addCriterion("region_type <", value, "regionType");
            return (Criteria) this;
        }

        public Criteria andRegionTypeLessThanOrEqualTo(Boolean value) {
            addCriterion("region_type <=", value, "regionType");
            return (Criteria) this;
        }

        public Criteria andRegionTypeIn(List<Boolean> values) {
            addCriterion("region_type in", values, "regionType");
            return (Criteria) this;
        }

        public Criteria andRegionTypeNotIn(List<Boolean> values) {
            addCriterion("region_type not in", values, "regionType");
            return (Criteria) this;
        }

        public Criteria andRegionTypeBetween(Boolean value1, Boolean value2) {
            addCriterion("region_type between", value1, value2, "regionType");
            return (Criteria) this;
        }

        public Criteria andRegionTypeNotBetween(Boolean value1, Boolean value2) {
            addCriterion("region_type not between", value1, value2, "regionType");
            return (Criteria) this;
        }

        public Criteria andAgencyIdIsNull() {
            addCriterion("agency_id is null");
            return (Criteria) this;
        }

        public Criteria andAgencyIdIsNotNull() {
            addCriterion("agency_id is not null");
            return (Criteria) this;
        }

        public Criteria andAgencyIdEqualTo(Short value) {
            addCriterion("agency_id =", value, "agencyId");
            return (Criteria) this;
        }

        public Criteria andAgencyIdNotEqualTo(Short value) {
            addCriterion("agency_id <>", value, "agencyId");
            return (Criteria) this;
        }

        public Criteria andAgencyIdGreaterThan(Short value) {
            addCriterion("agency_id >", value, "agencyId");
            return (Criteria) this;
        }

        public Criteria andAgencyIdGreaterThanOrEqualTo(Short value) {
            addCriterion("agency_id >=", value, "agencyId");
            return (Criteria) this;
        }

        public Criteria andAgencyIdLessThan(Short value) {
            addCriterion("agency_id <", value, "agencyId");
            return (Criteria) this;
        }

        public Criteria andAgencyIdLessThanOrEqualTo(Short value) {
            addCriterion("agency_id <=", value, "agencyId");
            return (Criteria) this;
        }

        public Criteria andAgencyIdIn(List<Short> values) {
            addCriterion("agency_id in", values, "agencyId");
            return (Criteria) this;
        }

        public Criteria andAgencyIdNotIn(List<Short> values) {
            addCriterion("agency_id not in", values, "agencyId");
            return (Criteria) this;
        }

        public Criteria andAgencyIdBetween(Short value1, Short value2) {
            addCriterion("agency_id between", value1, value2, "agencyId");
            return (Criteria) this;
        }

        public Criteria andAgencyIdNotBetween(Short value1, Short value2) {
            addCriterion("agency_id not between", value1, value2, "agencyId");
            return (Criteria) this;
        }

        public Criteria andRegionSnIsNull() {
            addCriterion("region_sn is null");
            return (Criteria) this;
        }

        public Criteria andRegionSnIsNotNull() {
            addCriterion("region_sn is not null");
            return (Criteria) this;
        }

        public Criteria andRegionSnEqualTo(String value) {
            addCriterion("region_sn =", value, "regionSn");
            return (Criteria) this;
        }

        public Criteria andRegionSnNotEqualTo(String value) {
            addCriterion("region_sn <>", value, "regionSn");
            return (Criteria) this;
        }

        public Criteria andRegionSnGreaterThan(String value) {
            addCriterion("region_sn >", value, "regionSn");
            return (Criteria) this;
        }

        public Criteria andRegionSnGreaterThanOrEqualTo(String value) {
            addCriterion("region_sn >=", value, "regionSn");
            return (Criteria) this;
        }

        public Criteria andRegionSnLessThan(String value) {
            addCriterion("region_sn <", value, "regionSn");
            return (Criteria) this;
        }

        public Criteria andRegionSnLessThanOrEqualTo(String value) {
            addCriterion("region_sn <=", value, "regionSn");
            return (Criteria) this;
        }

        public Criteria andRegionSnLike(String value) {
            addCriterion("region_sn like", value, "regionSn");
            return (Criteria) this;
        }

        public Criteria andRegionSnNotLike(String value) {
            addCriterion("region_sn not like", value, "regionSn");
            return (Criteria) this;
        }

        public Criteria andRegionSnIn(List<String> values) {
            addCriterion("region_sn in", values, "regionSn");
            return (Criteria) this;
        }

        public Criteria andRegionSnNotIn(List<String> values) {
            addCriterion("region_sn not in", values, "regionSn");
            return (Criteria) this;
        }

        public Criteria andRegionSnBetween(String value1, String value2) {
            addCriterion("region_sn between", value1, value2, "regionSn");
            return (Criteria) this;
        }

        public Criteria andRegionSnNotBetween(String value1, String value2) {
            addCriterion("region_sn not between", value1, value2, "regionSn");
            return (Criteria) this;
        }

        public Criteria andBuildinIsNull() {
            addCriterion("buildin is null");
            return (Criteria) this;
        }

        public Criteria andBuildinIsNotNull() {
            addCriterion("buildin is not null");
            return (Criteria) this;
        }

        public Criteria andBuildinEqualTo(Boolean value) {
            addCriterion("buildin =", value, "buildin");
            return (Criteria) this;
        }

        public Criteria andBuildinNotEqualTo(Boolean value) {
            addCriterion("buildin <>", value, "buildin");
            return (Criteria) this;
        }

        public Criteria andBuildinGreaterThan(Boolean value) {
            addCriterion("buildin >", value, "buildin");
            return (Criteria) this;
        }

        public Criteria andBuildinGreaterThanOrEqualTo(Boolean value) {
            addCriterion("buildin >=", value, "buildin");
            return (Criteria) this;
        }

        public Criteria andBuildinLessThan(Boolean value) {
            addCriterion("buildin <", value, "buildin");
            return (Criteria) this;
        }

        public Criteria andBuildinLessThanOrEqualTo(Boolean value) {
            addCriterion("buildin <=", value, "buildin");
            return (Criteria) this;
        }

        public Criteria andBuildinIn(List<Boolean> values) {
            addCriterion("buildin in", values, "buildin");
            return (Criteria) this;
        }

        public Criteria andBuildinNotIn(List<Boolean> values) {
            addCriterion("buildin not in", values, "buildin");
            return (Criteria) this;
        }

        public Criteria andBuildinBetween(Boolean value1, Boolean value2) {
            addCriterion("buildin between", value1, value2, "buildin");
            return (Criteria) this;
        }

        public Criteria andBuildinNotBetween(Boolean value1, Boolean value2) {
            addCriterion("buildin not between", value1, value2, "buildin");
            return (Criteria) this;
        }

        public Criteria andLastchangedIsNull() {
            addCriterion("lastchanged is null");
            return (Criteria) this;
        }

        public Criteria andLastchangedIsNotNull() {
            addCriterion("lastchanged is not null");
            return (Criteria) this;
        }

        public Criteria andLastchangedEqualTo(Date value) {
            addCriterion("lastchanged =", value, "lastchanged");
            return (Criteria) this;
        }

        public Criteria andLastchangedNotEqualTo(Date value) {
            addCriterion("lastchanged <>", value, "lastchanged");
            return (Criteria) this;
        }

        public Criteria andLastchangedGreaterThan(Date value) {
            addCriterion("lastchanged >", value, "lastchanged");
            return (Criteria) this;
        }

        public Criteria andLastchangedGreaterThanOrEqualTo(Date value) {
            addCriterion("lastchanged >=", value, "lastchanged");
            return (Criteria) this;
        }

        public Criteria andLastchangedLessThan(Date value) {
            addCriterion("lastchanged <", value, "lastchanged");
            return (Criteria) this;
        }

        public Criteria andLastchangedLessThanOrEqualTo(Date value) {
            addCriterion("lastchanged <=", value, "lastchanged");
            return (Criteria) this;
        }

        public Criteria andLastchangedIn(List<Date> values) {
            addCriterion("lastchanged in", values, "lastchanged");
            return (Criteria) this;
        }

        public Criteria andLastchangedNotIn(List<Date> values) {
            addCriterion("lastchanged not in", values, "lastchanged");
            return (Criteria) this;
        }

        public Criteria andLastchangedBetween(Date value1, Date value2) {
            addCriterion("lastchanged between", value1, value2, "lastchanged");
            return (Criteria) this;
        }

        public Criteria andLastchangedNotBetween(Date value1, Date value2) {
            addCriterion("lastchanged not between", value1, value2, "lastchanged");
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