package com.mcoding.base.product.bean.productPrice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mcoding.base.core.IExample;
import com.mcoding.base.core.PageView;
import com.mcoding.base.utils.json.JsonUtilsForMcoding;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProductPriceExample implements IExample<ProductPrice> {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected PageView<ProductPrice> pageView;

    public ProductPriceExample() {
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
    public PageView<ProductPrice> getPageView() {
        return pageView;
    }

    @Override
    public void setPageView(PageView<ProductPrice> pageView) {
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

        public Criteria andValueIsNull() {
            addCriterion("value is null");
            return (Criteria) this;
        }

        public Criteria andValueIsNotNull() {
            addCriterion("value is not null");
            return (Criteria) this;
        }

        public Criteria andValueEqualTo(Integer value) {
            addCriterion("value =", value, "value");
            return (Criteria) this;
        }

        public Criteria andValueNotEqualTo(Integer value) {
            addCriterion("value <>", value, "value");
            return (Criteria) this;
        }

        public Criteria andValueGreaterThan(Integer value) {
            addCriterion("value >", value, "value");
            return (Criteria) this;
        }

        public Criteria andValueGreaterThanOrEqualTo(Integer value) {
            addCriterion("value >=", value, "value");
            return (Criteria) this;
        }

        public Criteria andValueLessThan(Integer value) {
            addCriterion("value <", value, "value");
            return (Criteria) this;
        }

        public Criteria andValueLessThanOrEqualTo(Integer value) {
            addCriterion("value <=", value, "value");
            return (Criteria) this;
        }

        public Criteria andValueIn(List<Integer> values) {
            addCriterion("value in", values, "value");
            return (Criteria) this;
        }

        public Criteria andValueNotIn(List<Integer> values) {
            addCriterion("value not in", values, "value");
            return (Criteria) this;
        }

        public Criteria andValueBetween(Integer value1, Integer value2) {
            addCriterion("value between", value1, value2, "value");
            return (Criteria) this;
        }

        public Criteria andValueNotBetween(Integer value1, Integer value2) {
            addCriterion("value not between", value1, value2, "value");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(Integer value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(Integer value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(Integer value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(Integer value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(Integer value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<Integer> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<Integer> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(Integer value1, Integer value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("type not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andSceneIdIsNull() {
            addCriterion("scene_id is null");
            return (Criteria) this;
        }

        public Criteria andSceneIdIsNotNull() {
            addCriterion("scene_id is not null");
            return (Criteria) this;
        }

        public Criteria andSceneIdEqualTo(Integer value) {
            addCriterion("scene_id =", value, "sceneId");
            return (Criteria) this;
        }

        public Criteria andSceneIdNotEqualTo(Integer value) {
            addCriterion("scene_id <>", value, "sceneId");
            return (Criteria) this;
        }

        public Criteria andSceneIdGreaterThan(Integer value) {
            addCriterion("scene_id >", value, "sceneId");
            return (Criteria) this;
        }

        public Criteria andSceneIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("scene_id >=", value, "sceneId");
            return (Criteria) this;
        }

        public Criteria andSceneIdLessThan(Integer value) {
            addCriterion("scene_id <", value, "sceneId");
            return (Criteria) this;
        }

        public Criteria andSceneIdLessThanOrEqualTo(Integer value) {
            addCriterion("scene_id <=", value, "sceneId");
            return (Criteria) this;
        }

        public Criteria andSceneIdIn(List<Integer> values) {
            addCriterion("scene_id in", values, "sceneId");
            return (Criteria) this;
        }

        public Criteria andSceneIdNotIn(List<Integer> values) {
            addCriterion("scene_id not in", values, "sceneId");
            return (Criteria) this;
        }

        public Criteria andSceneIdBetween(Integer value1, Integer value2) {
            addCriterion("scene_id between", value1, value2, "sceneId");
            return (Criteria) this;
        }

        public Criteria andSceneIdNotBetween(Integer value1, Integer value2) {
            addCriterion("scene_id not between", value1, value2, "sceneId");
            return (Criteria) this;
        }

        public Criteria andSceneNameIsNull() {
            addCriterion("scene_name is null");
            return (Criteria) this;
        }

        public Criteria andSceneNameIsNotNull() {
            addCriterion("scene_name is not null");
            return (Criteria) this;
        }

        public Criteria andSceneNameEqualTo(String value) {
            addCriterion("scene_name =", value, "sceneName");
            return (Criteria) this;
        }

        public Criteria andSceneNameNotEqualTo(String value) {
            addCriterion("scene_name <>", value, "sceneName");
            return (Criteria) this;
        }

        public Criteria andSceneNameGreaterThan(String value) {
            addCriterion("scene_name >", value, "sceneName");
            return (Criteria) this;
        }

        public Criteria andSceneNameGreaterThanOrEqualTo(String value) {
            addCriterion("scene_name >=", value, "sceneName");
            return (Criteria) this;
        }

        public Criteria andSceneNameLessThan(String value) {
            addCriterion("scene_name <", value, "sceneName");
            return (Criteria) this;
        }

        public Criteria andSceneNameLessThanOrEqualTo(String value) {
            addCriterion("scene_name <=", value, "sceneName");
            return (Criteria) this;
        }

        public Criteria andSceneNameLike(String value) {
            addCriterion("scene_name like", value, "sceneName");
            return (Criteria) this;
        }

        public Criteria andSceneNameNotLike(String value) {
            addCriterion("scene_name not like", value, "sceneName");
            return (Criteria) this;
        }

        public Criteria andSceneNameIn(List<String> values) {
            addCriterion("scene_name in", values, "sceneName");
            return (Criteria) this;
        }

        public Criteria andSceneNameNotIn(List<String> values) {
            addCriterion("scene_name not in", values, "sceneName");
            return (Criteria) this;
        }

        public Criteria andSceneNameBetween(String value1, String value2) {
            addCriterion("scene_name between", value1, value2, "sceneName");
            return (Criteria) this;
        }

        public Criteria andSceneNameNotBetween(String value1, String value2) {
            addCriterion("scene_name not between", value1, value2, "sceneName");
            return (Criteria) this;
        }

        public Criteria andReferIdIsNull() {
            addCriterion("refer_id is null");
            return (Criteria) this;
        }

        public Criteria andReferIdIsNotNull() {
            addCriterion("refer_id is not null");
            return (Criteria) this;
        }

        public Criteria andReferIdEqualTo(Integer value) {
            addCriterion("refer_id =", value, "referId");
            return (Criteria) this;
        }

        public Criteria andReferIdNotEqualTo(Integer value) {
            addCriterion("refer_id <>", value, "referId");
            return (Criteria) this;
        }

        public Criteria andReferIdGreaterThan(Integer value) {
            addCriterion("refer_id >", value, "referId");
            return (Criteria) this;
        }

        public Criteria andReferIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("refer_id >=", value, "referId");
            return (Criteria) this;
        }

        public Criteria andReferIdLessThan(Integer value) {
            addCriterion("refer_id <", value, "referId");
            return (Criteria) this;
        }

        public Criteria andReferIdLessThanOrEqualTo(Integer value) {
            addCriterion("refer_id <=", value, "referId");
            return (Criteria) this;
        }

        public Criteria andReferIdIn(List<Integer> values) {
            addCriterion("refer_id in", values, "referId");
            return (Criteria) this;
        }

        public Criteria andReferIdNotIn(List<Integer> values) {
            addCriterion("refer_id not in", values, "referId");
            return (Criteria) this;
        }

        public Criteria andReferIdBetween(Integer value1, Integer value2) {
            addCriterion("refer_id between", value1, value2, "referId");
            return (Criteria) this;
        }

        public Criteria andReferIdNotBetween(Integer value1, Integer value2) {
            addCriterion("refer_id not between", value1, value2, "referId");
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