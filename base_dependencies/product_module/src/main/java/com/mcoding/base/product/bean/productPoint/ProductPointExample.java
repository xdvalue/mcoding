package com.mcoding.base.product.bean.productPoint;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mcoding.base.core.IExample;
import com.mcoding.base.core.PageView;
import com.mcoding.base.utils.json.JsonUtilsForMcoding;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProductPointExample implements IExample<ProductPoint> {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected PageView<ProductPoint> pageView;

    public ProductPointExample() {
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
    public PageView<ProductPoint> getPageView() {
        return pageView;
    }

    @Override
    public void setPageView(PageView<ProductPoint> pageView) {
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

        public Criteria andProductIdIsNull() {
            addCriterion("product_id is null");
            return (Criteria) this;
        }

        public Criteria andProductIdIsNotNull() {
            addCriterion("product_id is not null");
            return (Criteria) this;
        }

        public Criteria andProductIdEqualTo(Integer value) {
            addCriterion("product_id =", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotEqualTo(Integer value) {
            addCriterion("product_id <>", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdGreaterThan(Integer value) {
            addCriterion("product_id >", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("product_id >=", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdLessThan(Integer value) {
            addCriterion("product_id <", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdLessThanOrEqualTo(Integer value) {
            addCriterion("product_id <=", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdIn(List<Integer> values) {
            addCriterion("product_id in", values, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotIn(List<Integer> values) {
            addCriterion("product_id not in", values, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdBetween(Integer value1, Integer value2) {
            addCriterion("product_id between", value1, value2, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotBetween(Integer value1, Integer value2) {
            addCriterion("product_id not between", value1, value2, "productId");
            return (Criteria) this;
        }

        public Criteria andProductEarnPointIsNull() {
            addCriterion("product_earn_point is null");
            return (Criteria) this;
        }

        public Criteria andProductEarnPointIsNotNull() {
            addCriterion("product_earn_point is not null");
            return (Criteria) this;
        }

        public Criteria andProductEarnPointEqualTo(Integer value) {
            addCriterion("product_earn_point =", value, "productEarnPoint");
            return (Criteria) this;
        }

        public Criteria andProductEarnPointNotEqualTo(Integer value) {
            addCriterion("product_earn_point <>", value, "productEarnPoint");
            return (Criteria) this;
        }

        public Criteria andProductEarnPointGreaterThan(Integer value) {
            addCriterion("product_earn_point >", value, "productEarnPoint");
            return (Criteria) this;
        }

        public Criteria andProductEarnPointGreaterThanOrEqualTo(Integer value) {
            addCriterion("product_earn_point >=", value, "productEarnPoint");
            return (Criteria) this;
        }

        public Criteria andProductEarnPointLessThan(Integer value) {
            addCriterion("product_earn_point <", value, "productEarnPoint");
            return (Criteria) this;
        }

        public Criteria andProductEarnPointLessThanOrEqualTo(Integer value) {
            addCriterion("product_earn_point <=", value, "productEarnPoint");
            return (Criteria) this;
        }

        public Criteria andProductEarnPointIn(List<Integer> values) {
            addCriterion("product_earn_point in", values, "productEarnPoint");
            return (Criteria) this;
        }

        public Criteria andProductEarnPointNotIn(List<Integer> values) {
            addCriterion("product_earn_point not in", values, "productEarnPoint");
            return (Criteria) this;
        }

        public Criteria andProductEarnPointBetween(Integer value1, Integer value2) {
            addCriterion("product_earn_point between", value1, value2, "productEarnPoint");
            return (Criteria) this;
        }

        public Criteria andProductEarnPointNotBetween(Integer value1, Integer value2) {
            addCriterion("product_earn_point not between", value1, value2, "productEarnPoint");
            return (Criteria) this;
        }

        public Criteria andExchangePointIsNull() {
            addCriterion("exchange_point is null");
            return (Criteria) this;
        }

        public Criteria andExchangePointIsNotNull() {
            addCriterion("exchange_point is not null");
            return (Criteria) this;
        }

        public Criteria andExchangePointEqualTo(Integer value) {
            addCriterion("exchange_point =", value, "exchangePoint");
            return (Criteria) this;
        }

        public Criteria andExchangePointNotEqualTo(Integer value) {
            addCriterion("exchange_point <>", value, "exchangePoint");
            return (Criteria) this;
        }

        public Criteria andExchangePointGreaterThan(Integer value) {
            addCriterion("exchange_point >", value, "exchangePoint");
            return (Criteria) this;
        }

        public Criteria andExchangePointGreaterThanOrEqualTo(Integer value) {
            addCriterion("exchange_point >=", value, "exchangePoint");
            return (Criteria) this;
        }

        public Criteria andExchangePointLessThan(Integer value) {
            addCriterion("exchange_point <", value, "exchangePoint");
            return (Criteria) this;
        }

        public Criteria andExchangePointLessThanOrEqualTo(Integer value) {
            addCriterion("exchange_point <=", value, "exchangePoint");
            return (Criteria) this;
        }

        public Criteria andExchangePointIn(List<Integer> values) {
            addCriterion("exchange_point in", values, "exchangePoint");
            return (Criteria) this;
        }

        public Criteria andExchangePointNotIn(List<Integer> values) {
            addCriterion("exchange_point not in", values, "exchangePoint");
            return (Criteria) this;
        }

        public Criteria andExchangePointBetween(Integer value1, Integer value2) {
            addCriterion("exchange_point between", value1, value2, "exchangePoint");
            return (Criteria) this;
        }

        public Criteria andExchangePointNotBetween(Integer value1, Integer value2) {
            addCriterion("exchange_point not between", value1, value2, "exchangePoint");
            return (Criteria) this;
        }

        public Criteria andPlusPricePointIsNull() {
            addCriterion("plus_price_point is null");
            return (Criteria) this;
        }

        public Criteria andPlusPricePointIsNotNull() {
            addCriterion("plus_price_point is not null");
            return (Criteria) this;
        }

        public Criteria andPlusPricePointEqualTo(Integer value) {
            addCriterion("plus_price_point =", value, "plusPricePoint");
            return (Criteria) this;
        }

        public Criteria andPlusPricePointNotEqualTo(Integer value) {
            addCriterion("plus_price_point <>", value, "plusPricePoint");
            return (Criteria) this;
        }

        public Criteria andPlusPricePointGreaterThan(Integer value) {
            addCriterion("plus_price_point >", value, "plusPricePoint");
            return (Criteria) this;
        }

        public Criteria andPlusPricePointGreaterThanOrEqualTo(Integer value) {
            addCriterion("plus_price_point >=", value, "plusPricePoint");
            return (Criteria) this;
        }

        public Criteria andPlusPricePointLessThan(Integer value) {
            addCriterion("plus_price_point <", value, "plusPricePoint");
            return (Criteria) this;
        }

        public Criteria andPlusPricePointLessThanOrEqualTo(Integer value) {
            addCriterion("plus_price_point <=", value, "plusPricePoint");
            return (Criteria) this;
        }

        public Criteria andPlusPricePointIn(List<Integer> values) {
            addCriterion("plus_price_point in", values, "plusPricePoint");
            return (Criteria) this;
        }

        public Criteria andPlusPricePointNotIn(List<Integer> values) {
            addCriterion("plus_price_point not in", values, "plusPricePoint");
            return (Criteria) this;
        }

        public Criteria andPlusPricePointBetween(Integer value1, Integer value2) {
            addCriterion("plus_price_point between", value1, value2, "plusPricePoint");
            return (Criteria) this;
        }

        public Criteria andPlusPricePointNotBetween(Integer value1, Integer value2) {
            addCriterion("plus_price_point not between", value1, value2, "plusPricePoint");
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

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
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