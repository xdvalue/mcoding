package com.mcoding.base.member.bean.wechat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mcoding.base.core.IExample;
import com.mcoding.base.core.PageView;
import com.mcoding.base.utils.json.JsonUtilsForMcoding;
import java.util.ArrayList;
import java.util.List;

public class StoreWxRefExample implements IExample<StoreWxRef> {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected PageView<StoreWxRef> pageView;

    public StoreWxRefExample() {
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
    public PageView<StoreWxRef> getPageView() {
        return pageView;
    }

    @Override
    public void setPageView(PageView<StoreWxRef> pageView) {
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

        public Criteria andStoreIdIsNull() {
            addCriterion("store_id is null");
            return (Criteria) this;
        }

        public Criteria andStoreIdIsNotNull() {
            addCriterion("store_id is not null");
            return (Criteria) this;
        }

        public Criteria andStoreIdEqualTo(Integer value) {
            addCriterion("store_id =", value, "storeId");
            return (Criteria) this;
        }

        public Criteria andStoreIdNotEqualTo(Integer value) {
            addCriterion("store_id <>", value, "storeId");
            return (Criteria) this;
        }

        public Criteria andStoreIdGreaterThan(Integer value) {
            addCriterion("store_id >", value, "storeId");
            return (Criteria) this;
        }

        public Criteria andStoreIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("store_id >=", value, "storeId");
            return (Criteria) this;
        }

        public Criteria andStoreIdLessThan(Integer value) {
            addCriterion("store_id <", value, "storeId");
            return (Criteria) this;
        }

        public Criteria andStoreIdLessThanOrEqualTo(Integer value) {
            addCriterion("store_id <=", value, "storeId");
            return (Criteria) this;
        }

        public Criteria andStoreIdIn(List<Integer> values) {
            addCriterion("store_id in", values, "storeId");
            return (Criteria) this;
        }

        public Criteria andStoreIdNotIn(List<Integer> values) {
            addCriterion("store_id not in", values, "storeId");
            return (Criteria) this;
        }

        public Criteria andStoreIdBetween(Integer value1, Integer value2) {
            addCriterion("store_id between", value1, value2, "storeId");
            return (Criteria) this;
        }

        public Criteria andStoreIdNotBetween(Integer value1, Integer value2) {
            addCriterion("store_id not between", value1, value2, "storeId");
            return (Criteria) this;
        }

        public Criteria andStoreNameIsNull() {
            addCriterion("store_name is null");
            return (Criteria) this;
        }

        public Criteria andStoreNameIsNotNull() {
            addCriterion("store_name is not null");
            return (Criteria) this;
        }

        public Criteria andStoreNameEqualTo(String value) {
            addCriterion("store_name =", value, "storeName");
            return (Criteria) this;
        }

        public Criteria andStoreNameNotEqualTo(String value) {
            addCriterion("store_name <>", value, "storeName");
            return (Criteria) this;
        }

        public Criteria andStoreNameGreaterThan(String value) {
            addCriterion("store_name >", value, "storeName");
            return (Criteria) this;
        }

        public Criteria andStoreNameGreaterThanOrEqualTo(String value) {
            addCriterion("store_name >=", value, "storeName");
            return (Criteria) this;
        }

        public Criteria andStoreNameLessThan(String value) {
            addCriterion("store_name <", value, "storeName");
            return (Criteria) this;
        }

        public Criteria andStoreNameLessThanOrEqualTo(String value) {
            addCriterion("store_name <=", value, "storeName");
            return (Criteria) this;
        }

        public Criteria andStoreNameLike(String value) {
            addCriterion("store_name like", value, "storeName");
            return (Criteria) this;
        }

        public Criteria andStoreNameNotLike(String value) {
            addCriterion("store_name not like", value, "storeName");
            return (Criteria) this;
        }

        public Criteria andStoreNameIn(List<String> values) {
            addCriterion("store_name in", values, "storeName");
            return (Criteria) this;
        }

        public Criteria andStoreNameNotIn(List<String> values) {
            addCriterion("store_name not in", values, "storeName");
            return (Criteria) this;
        }

        public Criteria andStoreNameBetween(String value1, String value2) {
            addCriterion("store_name between", value1, value2, "storeName");
            return (Criteria) this;
        }

        public Criteria andStoreNameNotBetween(String value1, String value2) {
            addCriterion("store_name not between", value1, value2, "storeName");
            return (Criteria) this;
        }

        public Criteria andWxAccountIdIsNull() {
            addCriterion("wx_account_id is null");
            return (Criteria) this;
        }

        public Criteria andWxAccountIdIsNotNull() {
            addCriterion("wx_account_id is not null");
            return (Criteria) this;
        }

        public Criteria andWxAccountIdEqualTo(Integer value) {
            addCriterion("wx_account_id =", value, "wxAccountId");
            return (Criteria) this;
        }

        public Criteria andWxAccountIdNotEqualTo(Integer value) {
            addCriterion("wx_account_id <>", value, "wxAccountId");
            return (Criteria) this;
        }

        public Criteria andWxAccountIdGreaterThan(Integer value) {
            addCriterion("wx_account_id >", value, "wxAccountId");
            return (Criteria) this;
        }

        public Criteria andWxAccountIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("wx_account_id >=", value, "wxAccountId");
            return (Criteria) this;
        }

        public Criteria andWxAccountIdLessThan(Integer value) {
            addCriterion("wx_account_id <", value, "wxAccountId");
            return (Criteria) this;
        }

        public Criteria andWxAccountIdLessThanOrEqualTo(Integer value) {
            addCriterion("wx_account_id <=", value, "wxAccountId");
            return (Criteria) this;
        }

        public Criteria andWxAccountIdIn(List<Integer> values) {
            addCriterion("wx_account_id in", values, "wxAccountId");
            return (Criteria) this;
        }

        public Criteria andWxAccountIdNotIn(List<Integer> values) {
            addCriterion("wx_account_id not in", values, "wxAccountId");
            return (Criteria) this;
        }

        public Criteria andWxAccountIdBetween(Integer value1, Integer value2) {
            addCriterion("wx_account_id between", value1, value2, "wxAccountId");
            return (Criteria) this;
        }

        public Criteria andWxAccountIdNotBetween(Integer value1, Integer value2) {
            addCriterion("wx_account_id not between", value1, value2, "wxAccountId");
            return (Criteria) this;
        }

        public Criteria andWxAccountNameIsNull() {
            addCriterion("wx_account_name is null");
            return (Criteria) this;
        }

        public Criteria andWxAccountNameIsNotNull() {
            addCriterion("wx_account_name is not null");
            return (Criteria) this;
        }

        public Criteria andWxAccountNameEqualTo(String value) {
            addCriterion("wx_account_name =", value, "wxAccountName");
            return (Criteria) this;
        }

        public Criteria andWxAccountNameNotEqualTo(String value) {
            addCriterion("wx_account_name <>", value, "wxAccountName");
            return (Criteria) this;
        }

        public Criteria andWxAccountNameGreaterThan(String value) {
            addCriterion("wx_account_name >", value, "wxAccountName");
            return (Criteria) this;
        }

        public Criteria andWxAccountNameGreaterThanOrEqualTo(String value) {
            addCriterion("wx_account_name >=", value, "wxAccountName");
            return (Criteria) this;
        }

        public Criteria andWxAccountNameLessThan(String value) {
            addCriterion("wx_account_name <", value, "wxAccountName");
            return (Criteria) this;
        }

        public Criteria andWxAccountNameLessThanOrEqualTo(String value) {
            addCriterion("wx_account_name <=", value, "wxAccountName");
            return (Criteria) this;
        }

        public Criteria andWxAccountNameLike(String value) {
            addCriterion("wx_account_name like", value, "wxAccountName");
            return (Criteria) this;
        }

        public Criteria andWxAccountNameNotLike(String value) {
            addCriterion("wx_account_name not like", value, "wxAccountName");
            return (Criteria) this;
        }

        public Criteria andWxAccountNameIn(List<String> values) {
            addCriterion("wx_account_name in", values, "wxAccountName");
            return (Criteria) this;
        }

        public Criteria andWxAccountNameNotIn(List<String> values) {
            addCriterion("wx_account_name not in", values, "wxAccountName");
            return (Criteria) this;
        }

        public Criteria andWxAccountNameBetween(String value1, String value2) {
            addCriterion("wx_account_name between", value1, value2, "wxAccountName");
            return (Criteria) this;
        }

        public Criteria andWxAccountNameNotBetween(String value1, String value2) {
            addCriterion("wx_account_name not between", value1, value2, "wxAccountName");
            return (Criteria) this;
        }

        public Criteria andWxAccountOriginIdIsNull() {
            addCriterion("wx_account_origin_id is null");
            return (Criteria) this;
        }

        public Criteria andWxAccountOriginIdIsNotNull() {
            addCriterion("wx_account_origin_id is not null");
            return (Criteria) this;
        }

        public Criteria andWxAccountOriginIdEqualTo(String value) {
            addCriterion("wx_account_origin_id =", value, "wxAccountOriginId");
            return (Criteria) this;
        }

        public Criteria andWxAccountOriginIdNotEqualTo(String value) {
            addCriterion("wx_account_origin_id <>", value, "wxAccountOriginId");
            return (Criteria) this;
        }

        public Criteria andWxAccountOriginIdGreaterThan(String value) {
            addCriterion("wx_account_origin_id >", value, "wxAccountOriginId");
            return (Criteria) this;
        }

        public Criteria andWxAccountOriginIdGreaterThanOrEqualTo(String value) {
            addCriterion("wx_account_origin_id >=", value, "wxAccountOriginId");
            return (Criteria) this;
        }

        public Criteria andWxAccountOriginIdLessThan(String value) {
            addCriterion("wx_account_origin_id <", value, "wxAccountOriginId");
            return (Criteria) this;
        }

        public Criteria andWxAccountOriginIdLessThanOrEqualTo(String value) {
            addCriterion("wx_account_origin_id <=", value, "wxAccountOriginId");
            return (Criteria) this;
        }

        public Criteria andWxAccountOriginIdLike(String value) {
            addCriterion("wx_account_origin_id like", value, "wxAccountOriginId");
            return (Criteria) this;
        }

        public Criteria andWxAccountOriginIdNotLike(String value) {
            addCriterion("wx_account_origin_id not like", value, "wxAccountOriginId");
            return (Criteria) this;
        }

        public Criteria andWxAccountOriginIdIn(List<String> values) {
            addCriterion("wx_account_origin_id in", values, "wxAccountOriginId");
            return (Criteria) this;
        }

        public Criteria andWxAccountOriginIdNotIn(List<String> values) {
            addCriterion("wx_account_origin_id not in", values, "wxAccountOriginId");
            return (Criteria) this;
        }

        public Criteria andWxAccountOriginIdBetween(String value1, String value2) {
            addCriterion("wx_account_origin_id between", value1, value2, "wxAccountOriginId");
            return (Criteria) this;
        }

        public Criteria andWxAccountOriginIdNotBetween(String value1, String value2) {
            addCriterion("wx_account_origin_id not between", value1, value2, "wxAccountOriginId");
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