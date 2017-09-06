package com.mcoding.comp.wechat.msg.bean;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mcoding.base.core.IExample;
import com.mcoding.base.core.PageView;
import com.mcoding.base.utils.json.JsonUtilsForMcoding;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WxMsgRuleExample implements IExample<WxMsgRule> {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected PageView<WxMsgRule> pageView;

    public WxMsgRuleExample() {
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
    public PageView<WxMsgRule> getPageView() {
        return pageView;
    }

    @Override
    public void setPageView(PageView<WxMsgRule> pageView) {
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

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
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

        public Criteria andHandlersIsNull() {
            addCriterion("handlers is null");
            return (Criteria) this;
        }

        public Criteria andHandlersIsNotNull() {
            addCriterion("handlers is not null");
            return (Criteria) this;
        }

        public Criteria andHandlersEqualTo(String value) {
            addCriterion("handlers =", value, "handlers");
            return (Criteria) this;
        }

        public Criteria andHandlersNotEqualTo(String value) {
            addCriterion("handlers <>", value, "handlers");
            return (Criteria) this;
        }

        public Criteria andHandlersGreaterThan(String value) {
            addCriterion("handlers >", value, "handlers");
            return (Criteria) this;
        }

        public Criteria andHandlersGreaterThanOrEqualTo(String value) {
            addCriterion("handlers >=", value, "handlers");
            return (Criteria) this;
        }

        public Criteria andHandlersLessThan(String value) {
            addCriterion("handlers <", value, "handlers");
            return (Criteria) this;
        }

        public Criteria andHandlersLessThanOrEqualTo(String value) {
            addCriterion("handlers <=", value, "handlers");
            return (Criteria) this;
        }

        public Criteria andHandlersLike(String value) {
            addCriterion("handlers like", value, "handlers");
            return (Criteria) this;
        }

        public Criteria andHandlersNotLike(String value) {
            addCriterion("handlers not like", value, "handlers");
            return (Criteria) this;
        }

        public Criteria andHandlersIn(List<String> values) {
            addCriterion("handlers in", values, "handlers");
            return (Criteria) this;
        }

        public Criteria andHandlersNotIn(List<String> values) {
            addCriterion("handlers not in", values, "handlers");
            return (Criteria) this;
        }

        public Criteria andHandlersBetween(String value1, String value2) {
            addCriterion("handlers between", value1, value2, "handlers");
            return (Criteria) this;
        }

        public Criteria andHandlersNotBetween(String value1, String value2) {
            addCriterion("handlers not between", value1, value2, "handlers");
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

        public Criteria andIsDefaultIsNull() {
            addCriterion("is_default is null");
            return (Criteria) this;
        }

        public Criteria andIsDefaultIsNotNull() {
            addCriterion("is_default is not null");
            return (Criteria) this;
        }

        public Criteria andIsDefaultEqualTo(Integer value) {
            addCriterion("is_default =", value, "isDefault");
            return (Criteria) this;
        }

        public Criteria andIsDefaultNotEqualTo(Integer value) {
            addCriterion("is_default <>", value, "isDefault");
            return (Criteria) this;
        }

        public Criteria andIsDefaultGreaterThan(Integer value) {
            addCriterion("is_default >", value, "isDefault");
            return (Criteria) this;
        }

        public Criteria andIsDefaultGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_default >=", value, "isDefault");
            return (Criteria) this;
        }

        public Criteria andIsDefaultLessThan(Integer value) {
            addCriterion("is_default <", value, "isDefault");
            return (Criteria) this;
        }

        public Criteria andIsDefaultLessThanOrEqualTo(Integer value) {
            addCriterion("is_default <=", value, "isDefault");
            return (Criteria) this;
        }

        public Criteria andIsDefaultIn(List<Integer> values) {
            addCriterion("is_default in", values, "isDefault");
            return (Criteria) this;
        }

        public Criteria andIsDefaultNotIn(List<Integer> values) {
            addCriterion("is_default not in", values, "isDefault");
            return (Criteria) this;
        }

        public Criteria andIsDefaultBetween(Integer value1, Integer value2) {
            addCriterion("is_default between", value1, value2, "isDefault");
            return (Criteria) this;
        }

        public Criteria andIsDefaultNotBetween(Integer value1, Integer value2) {
            addCriterion("is_default not between", value1, value2, "isDefault");
            return (Criteria) this;
        }

        public Criteria andIsSycnIsNull() {
            addCriterion("is_sycn is null");
            return (Criteria) this;
        }

        public Criteria andIsSycnIsNotNull() {
            addCriterion("is_sycn is not null");
            return (Criteria) this;
        }

        public Criteria andIsSycnEqualTo(Integer value) {
            addCriterion("is_sycn =", value, "isSycn");
            return (Criteria) this;
        }

        public Criteria andIsSycnNotEqualTo(Integer value) {
            addCriterion("is_sycn <>", value, "isSycn");
            return (Criteria) this;
        }

        public Criteria andIsSycnGreaterThan(Integer value) {
            addCriterion("is_sycn >", value, "isSycn");
            return (Criteria) this;
        }

        public Criteria andIsSycnGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_sycn >=", value, "isSycn");
            return (Criteria) this;
        }

        public Criteria andIsSycnLessThan(Integer value) {
            addCriterion("is_sycn <", value, "isSycn");
            return (Criteria) this;
        }

        public Criteria andIsSycnLessThanOrEqualTo(Integer value) {
            addCriterion("is_sycn <=", value, "isSycn");
            return (Criteria) this;
        }

        public Criteria andIsSycnIn(List<Integer> values) {
            addCriterion("is_sycn in", values, "isSycn");
            return (Criteria) this;
        }

        public Criteria andIsSycnNotIn(List<Integer> values) {
            addCriterion("is_sycn not in", values, "isSycn");
            return (Criteria) this;
        }

        public Criteria andIsSycnBetween(Integer value1, Integer value2) {
            addCriterion("is_sycn between", value1, value2, "isSycn");
            return (Criteria) this;
        }

        public Criteria andIsSycnNotBetween(Integer value1, Integer value2) {
            addCriterion("is_sycn not between", value1, value2, "isSycn");
            return (Criteria) this;
        }

        public Criteria andFromUserNameIsNull() {
            addCriterion("from_user_name is null");
            return (Criteria) this;
        }

        public Criteria andFromUserNameIsNotNull() {
            addCriterion("from_user_name is not null");
            return (Criteria) this;
        }

        public Criteria andFromUserNameEqualTo(String value) {
            addCriterion("from_user_name =", value, "fromUserName");
            return (Criteria) this;
        }

        public Criteria andFromUserNameNotEqualTo(String value) {
            addCriterion("from_user_name <>", value, "fromUserName");
            return (Criteria) this;
        }

        public Criteria andFromUserNameGreaterThan(String value) {
            addCriterion("from_user_name >", value, "fromUserName");
            return (Criteria) this;
        }

        public Criteria andFromUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("from_user_name >=", value, "fromUserName");
            return (Criteria) this;
        }

        public Criteria andFromUserNameLessThan(String value) {
            addCriterion("from_user_name <", value, "fromUserName");
            return (Criteria) this;
        }

        public Criteria andFromUserNameLessThanOrEqualTo(String value) {
            addCriterion("from_user_name <=", value, "fromUserName");
            return (Criteria) this;
        }

        public Criteria andFromUserNameLike(String value) {
            addCriterion("from_user_name like", value, "fromUserName");
            return (Criteria) this;
        }

        public Criteria andFromUserNameNotLike(String value) {
            addCriterion("from_user_name not like", value, "fromUserName");
            return (Criteria) this;
        }

        public Criteria andFromUserNameIn(List<String> values) {
            addCriterion("from_user_name in", values, "fromUserName");
            return (Criteria) this;
        }

        public Criteria andFromUserNameNotIn(List<String> values) {
            addCriterion("from_user_name not in", values, "fromUserName");
            return (Criteria) this;
        }

        public Criteria andFromUserNameBetween(String value1, String value2) {
            addCriterion("from_user_name between", value1, value2, "fromUserName");
            return (Criteria) this;
        }

        public Criteria andFromUserNameNotBetween(String value1, String value2) {
            addCriterion("from_user_name not between", value1, value2, "fromUserName");
            return (Criteria) this;
        }

        public Criteria andMsgTypeIsNull() {
            addCriterion("msg_type is null");
            return (Criteria) this;
        }

        public Criteria andMsgTypeIsNotNull() {
            addCriterion("msg_type is not null");
            return (Criteria) this;
        }

        public Criteria andMsgTypeEqualTo(String value) {
            addCriterion("msg_type =", value, "msgType");
            return (Criteria) this;
        }

        public Criteria andMsgTypeNotEqualTo(String value) {
            addCriterion("msg_type <>", value, "msgType");
            return (Criteria) this;
        }

        public Criteria andMsgTypeGreaterThan(String value) {
            addCriterion("msg_type >", value, "msgType");
            return (Criteria) this;
        }

        public Criteria andMsgTypeGreaterThanOrEqualTo(String value) {
            addCriterion("msg_type >=", value, "msgType");
            return (Criteria) this;
        }

        public Criteria andMsgTypeLessThan(String value) {
            addCriterion("msg_type <", value, "msgType");
            return (Criteria) this;
        }

        public Criteria andMsgTypeLessThanOrEqualTo(String value) {
            addCriterion("msg_type <=", value, "msgType");
            return (Criteria) this;
        }

        public Criteria andMsgTypeLike(String value) {
            addCriterion("msg_type like", value, "msgType");
            return (Criteria) this;
        }

        public Criteria andMsgTypeNotLike(String value) {
            addCriterion("msg_type not like", value, "msgType");
            return (Criteria) this;
        }

        public Criteria andMsgTypeIn(List<String> values) {
            addCriterion("msg_type in", values, "msgType");
            return (Criteria) this;
        }

        public Criteria andMsgTypeNotIn(List<String> values) {
            addCriterion("msg_type not in", values, "msgType");
            return (Criteria) this;
        }

        public Criteria andMsgTypeBetween(String value1, String value2) {
            addCriterion("msg_type between", value1, value2, "msgType");
            return (Criteria) this;
        }

        public Criteria andMsgTypeNotBetween(String value1, String value2) {
            addCriterion("msg_type not between", value1, value2, "msgType");
            return (Criteria) this;
        }

        public Criteria andContentIsNull() {
            addCriterion("content is null");
            return (Criteria) this;
        }

        public Criteria andContentIsNotNull() {
            addCriterion("content is not null");
            return (Criteria) this;
        }

        public Criteria andContentEqualTo(String value) {
            addCriterion("content =", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotEqualTo(String value) {
            addCriterion("content <>", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentGreaterThan(String value) {
            addCriterion("content >", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentGreaterThanOrEqualTo(String value) {
            addCriterion("content >=", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLessThan(String value) {
            addCriterion("content <", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLessThanOrEqualTo(String value) {
            addCriterion("content <=", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLike(String value) {
            addCriterion("content like", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotLike(String value) {
            addCriterion("content not like", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentIn(List<String> values) {
            addCriterion("content in", values, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotIn(List<String> values) {
            addCriterion("content not in", values, "content");
            return (Criteria) this;
        }

        public Criteria andContentBetween(String value1, String value2) {
            addCriterion("content between", value1, value2, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotBetween(String value1, String value2) {
            addCriterion("content not between", value1, value2, "content");
            return (Criteria) this;
        }

        public Criteria andMsgStartTimeIsNull() {
            addCriterion("msg_start_time is null");
            return (Criteria) this;
        }

        public Criteria andMsgStartTimeIsNotNull() {
            addCriterion("msg_start_time is not null");
            return (Criteria) this;
        }

        public Criteria andMsgStartTimeEqualTo(Date value) {
            addCriterion("msg_start_time =", value, "msgStartTime");
            return (Criteria) this;
        }

        public Criteria andMsgStartTimeNotEqualTo(Date value) {
            addCriterion("msg_start_time <>", value, "msgStartTime");
            return (Criteria) this;
        }

        public Criteria andMsgStartTimeGreaterThan(Date value) {
            addCriterion("msg_start_time >", value, "msgStartTime");
            return (Criteria) this;
        }

        public Criteria andMsgStartTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("msg_start_time >=", value, "msgStartTime");
            return (Criteria) this;
        }

        public Criteria andMsgStartTimeLessThan(Date value) {
            addCriterion("msg_start_time <", value, "msgStartTime");
            return (Criteria) this;
        }

        public Criteria andMsgStartTimeLessThanOrEqualTo(Date value) {
            addCriterion("msg_start_time <=", value, "msgStartTime");
            return (Criteria) this;
        }

        public Criteria andMsgStartTimeIn(List<Date> values) {
            addCriterion("msg_start_time in", values, "msgStartTime");
            return (Criteria) this;
        }

        public Criteria andMsgStartTimeNotIn(List<Date> values) {
            addCriterion("msg_start_time not in", values, "msgStartTime");
            return (Criteria) this;
        }

        public Criteria andMsgStartTimeBetween(Date value1, Date value2) {
            addCriterion("msg_start_time between", value1, value2, "msgStartTime");
            return (Criteria) this;
        }

        public Criteria andMsgStartTimeNotBetween(Date value1, Date value2) {
            addCriterion("msg_start_time not between", value1, value2, "msgStartTime");
            return (Criteria) this;
        }

        public Criteria andMsgEndTimeIsNull() {
            addCriterion("msg_end_time is null");
            return (Criteria) this;
        }

        public Criteria andMsgEndTimeIsNotNull() {
            addCriterion("msg_end_time is not null");
            return (Criteria) this;
        }

        public Criteria andMsgEndTimeEqualTo(Date value) {
            addCriterion("msg_end_time =", value, "msgEndTime");
            return (Criteria) this;
        }

        public Criteria andMsgEndTimeNotEqualTo(Date value) {
            addCriterion("msg_end_time <>", value, "msgEndTime");
            return (Criteria) this;
        }

        public Criteria andMsgEndTimeGreaterThan(Date value) {
            addCriterion("msg_end_time >", value, "msgEndTime");
            return (Criteria) this;
        }

        public Criteria andMsgEndTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("msg_end_time >=", value, "msgEndTime");
            return (Criteria) this;
        }

        public Criteria andMsgEndTimeLessThan(Date value) {
            addCriterion("msg_end_time <", value, "msgEndTime");
            return (Criteria) this;
        }

        public Criteria andMsgEndTimeLessThanOrEqualTo(Date value) {
            addCriterion("msg_end_time <=", value, "msgEndTime");
            return (Criteria) this;
        }

        public Criteria andMsgEndTimeIn(List<Date> values) {
            addCriterion("msg_end_time in", values, "msgEndTime");
            return (Criteria) this;
        }

        public Criteria andMsgEndTimeNotIn(List<Date> values) {
            addCriterion("msg_end_time not in", values, "msgEndTime");
            return (Criteria) this;
        }

        public Criteria andMsgEndTimeBetween(Date value1, Date value2) {
            addCriterion("msg_end_time between", value1, value2, "msgEndTime");
            return (Criteria) this;
        }

        public Criteria andMsgEndTimeNotBetween(Date value1, Date value2) {
            addCriterion("msg_end_time not between", value1, value2, "msgEndTime");
            return (Criteria) this;
        }

        public Criteria andEventIsNull() {
            addCriterion("event is null");
            return (Criteria) this;
        }

        public Criteria andEventIsNotNull() {
            addCriterion("event is not null");
            return (Criteria) this;
        }

        public Criteria andEventEqualTo(String value) {
            addCriterion("event =", value, "event");
            return (Criteria) this;
        }

        public Criteria andEventNotEqualTo(String value) {
            addCriterion("event <>", value, "event");
            return (Criteria) this;
        }

        public Criteria andEventGreaterThan(String value) {
            addCriterion("event >", value, "event");
            return (Criteria) this;
        }

        public Criteria andEventGreaterThanOrEqualTo(String value) {
            addCriterion("event >=", value, "event");
            return (Criteria) this;
        }

        public Criteria andEventLessThan(String value) {
            addCriterion("event <", value, "event");
            return (Criteria) this;
        }

        public Criteria andEventLessThanOrEqualTo(String value) {
            addCriterion("event <=", value, "event");
            return (Criteria) this;
        }

        public Criteria andEventLike(String value) {
            addCriterion("event like", value, "event");
            return (Criteria) this;
        }

        public Criteria andEventNotLike(String value) {
            addCriterion("event not like", value, "event");
            return (Criteria) this;
        }

        public Criteria andEventIn(List<String> values) {
            addCriterion("event in", values, "event");
            return (Criteria) this;
        }

        public Criteria andEventNotIn(List<String> values) {
            addCriterion("event not in", values, "event");
            return (Criteria) this;
        }

        public Criteria andEventBetween(String value1, String value2) {
            addCriterion("event between", value1, value2, "event");
            return (Criteria) this;
        }

        public Criteria andEventNotBetween(String value1, String value2) {
            addCriterion("event not between", value1, value2, "event");
            return (Criteria) this;
        }

        public Criteria andEventKeyIsNull() {
            addCriterion("event_key is null");
            return (Criteria) this;
        }

        public Criteria andEventKeyIsNotNull() {
            addCriterion("event_key is not null");
            return (Criteria) this;
        }

        public Criteria andEventKeyEqualTo(String value) {
            addCriterion("event_key =", value, "eventKey");
            return (Criteria) this;
        }

        public Criteria andEventKeyNotEqualTo(String value) {
            addCriterion("event_key <>", value, "eventKey");
            return (Criteria) this;
        }

        public Criteria andEventKeyGreaterThan(String value) {
            addCriterion("event_key >", value, "eventKey");
            return (Criteria) this;
        }

        public Criteria andEventKeyGreaterThanOrEqualTo(String value) {
            addCriterion("event_key >=", value, "eventKey");
            return (Criteria) this;
        }

        public Criteria andEventKeyLessThan(String value) {
            addCriterion("event_key <", value, "eventKey");
            return (Criteria) this;
        }

        public Criteria andEventKeyLessThanOrEqualTo(String value) {
            addCriterion("event_key <=", value, "eventKey");
            return (Criteria) this;
        }

        public Criteria andEventKeyLike(String value) {
            addCriterion("event_key like", value, "eventKey");
            return (Criteria) this;
        }

        public Criteria andEventKeyNotLike(String value) {
            addCriterion("event_key not like", value, "eventKey");
            return (Criteria) this;
        }

        public Criteria andEventKeyIn(List<String> values) {
            addCriterion("event_key in", values, "eventKey");
            return (Criteria) this;
        }

        public Criteria andEventKeyNotIn(List<String> values) {
            addCriterion("event_key not in", values, "eventKey");
            return (Criteria) this;
        }

        public Criteria andEventKeyBetween(String value1, String value2) {
            addCriterion("event_key between", value1, value2, "eventKey");
            return (Criteria) this;
        }

        public Criteria andEventKeyNotBetween(String value1, String value2) {
            addCriterion("event_key not between", value1, value2, "eventKey");
            return (Criteria) this;
        }

        public Criteria andMatchTypeIsNull() {
            addCriterion("match_type is null");
            return (Criteria) this;
        }

        public Criteria andMatchTypeIsNotNull() {
            addCriterion("match_type is not null");
            return (Criteria) this;
        }

        public Criteria andMatchTypeEqualTo(Integer value) {
            addCriterion("match_type =", value, "matchType");
            return (Criteria) this;
        }

        public Criteria andMatchTypeNotEqualTo(Integer value) {
            addCriterion("match_type <>", value, "matchType");
            return (Criteria) this;
        }

        public Criteria andMatchTypeGreaterThan(Integer value) {
            addCriterion("match_type >", value, "matchType");
            return (Criteria) this;
        }

        public Criteria andMatchTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("match_type >=", value, "matchType");
            return (Criteria) this;
        }

        public Criteria andMatchTypeLessThan(Integer value) {
            addCriterion("match_type <", value, "matchType");
            return (Criteria) this;
        }

        public Criteria andMatchTypeLessThanOrEqualTo(Integer value) {
            addCriterion("match_type <=", value, "matchType");
            return (Criteria) this;
        }

        public Criteria andMatchTypeIn(List<Integer> values) {
            addCriterion("match_type in", values, "matchType");
            return (Criteria) this;
        }

        public Criteria andMatchTypeNotIn(List<Integer> values) {
            addCriterion("match_type not in", values, "matchType");
            return (Criteria) this;
        }

        public Criteria andMatchTypeBetween(Integer value1, Integer value2) {
            addCriterion("match_type between", value1, value2, "matchType");
            return (Criteria) this;
        }

        public Criteria andMatchTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("match_type not between", value1, value2, "matchType");
            return (Criteria) this;
        }

        public Criteria andReplyContentRefIdIsNull() {
            addCriterion("reply_content_ref_id is null");
            return (Criteria) this;
        }

        public Criteria andReplyContentRefIdIsNotNull() {
            addCriterion("reply_content_ref_id is not null");
            return (Criteria) this;
        }

        public Criteria andReplyContentRefIdEqualTo(Integer value) {
            addCriterion("reply_content_ref_id =", value, "replyContentRefId");
            return (Criteria) this;
        }

        public Criteria andReplyContentRefIdNotEqualTo(Integer value) {
            addCriterion("reply_content_ref_id <>", value, "replyContentRefId");
            return (Criteria) this;
        }

        public Criteria andReplyContentRefIdGreaterThan(Integer value) {
            addCriterion("reply_content_ref_id >", value, "replyContentRefId");
            return (Criteria) this;
        }

        public Criteria andReplyContentRefIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("reply_content_ref_id >=", value, "replyContentRefId");
            return (Criteria) this;
        }

        public Criteria andReplyContentRefIdLessThan(Integer value) {
            addCriterion("reply_content_ref_id <", value, "replyContentRefId");
            return (Criteria) this;
        }

        public Criteria andReplyContentRefIdLessThanOrEqualTo(Integer value) {
            addCriterion("reply_content_ref_id <=", value, "replyContentRefId");
            return (Criteria) this;
        }

        public Criteria andReplyContentRefIdIn(List<Integer> values) {
            addCriterion("reply_content_ref_id in", values, "replyContentRefId");
            return (Criteria) this;
        }

        public Criteria andReplyContentRefIdNotIn(List<Integer> values) {
            addCriterion("reply_content_ref_id not in", values, "replyContentRefId");
            return (Criteria) this;
        }

        public Criteria andReplyContentRefIdBetween(Integer value1, Integer value2) {
            addCriterion("reply_content_ref_id between", value1, value2, "replyContentRefId");
            return (Criteria) this;
        }

        public Criteria andReplyContentRefIdNotBetween(Integer value1, Integer value2) {
            addCriterion("reply_content_ref_id not between", value1, value2, "replyContentRefId");
            return (Criteria) this;
        }

        public Criteria andPriorityIsNull() {
            addCriterion("priority is null");
            return (Criteria) this;
        }

        public Criteria andPriorityIsNotNull() {
            addCriterion("priority is not null");
            return (Criteria) this;
        }

        public Criteria andPriorityEqualTo(Integer value) {
            addCriterion("priority =", value, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityNotEqualTo(Integer value) {
            addCriterion("priority <>", value, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityGreaterThan(Integer value) {
            addCriterion("priority >", value, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityGreaterThanOrEqualTo(Integer value) {
            addCriterion("priority >=", value, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityLessThan(Integer value) {
            addCriterion("priority <", value, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityLessThanOrEqualTo(Integer value) {
            addCriterion("priority <=", value, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityIn(List<Integer> values) {
            addCriterion("priority in", values, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityNotIn(List<Integer> values) {
            addCriterion("priority not in", values, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityBetween(Integer value1, Integer value2) {
            addCriterion("priority between", value1, value2, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityNotBetween(Integer value1, Integer value2) {
            addCriterion("priority not between", value1, value2, "priority");
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