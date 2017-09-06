package com.mcoding.base.dst.bean.income;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mcoding.base.core.IExample;
import com.mcoding.base.core.PageView;
import com.mcoding.base.utils.json.JsonUtilsForMcoding;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DstIncomeOrderExample implements IExample<DstIncomeOrder> {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected PageView<DstIncomeOrder> pageView;

    public DstIncomeOrderExample() {
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
    public PageView<DstIncomeOrder> getPageView() {
        return pageView;
    }

    @Override
    public void setPageView(PageView<DstIncomeOrder> pageView) {
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

        public Criteria andMemberIdIsNull() {
            addCriterion("member_id is null");
            return (Criteria) this;
        }

        public Criteria andMemberIdIsNotNull() {
            addCriterion("member_id is not null");
            return (Criteria) this;
        }

        public Criteria andMemberIdEqualTo(Integer value) {
            addCriterion("member_id =", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdNotEqualTo(Integer value) {
            addCriterion("member_id <>", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdGreaterThan(Integer value) {
            addCriterion("member_id >", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("member_id >=", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdLessThan(Integer value) {
            addCriterion("member_id <", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdLessThanOrEqualTo(Integer value) {
            addCriterion("member_id <=", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdIn(List<Integer> values) {
            addCriterion("member_id in", values, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdNotIn(List<Integer> values) {
            addCriterion("member_id not in", values, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdBetween(Integer value1, Integer value2) {
            addCriterion("member_id between", value1, value2, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdNotBetween(Integer value1, Integer value2) {
            addCriterion("member_id not between", value1, value2, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberNameIsNull() {
            addCriterion("member_name is null");
            return (Criteria) this;
        }

        public Criteria andMemberNameIsNotNull() {
            addCriterion("member_name is not null");
            return (Criteria) this;
        }

        public Criteria andMemberNameEqualTo(String value) {
            addCriterion("member_name =", value, "memberName");
            return (Criteria) this;
        }

        public Criteria andMemberNameNotEqualTo(String value) {
            addCriterion("member_name <>", value, "memberName");
            return (Criteria) this;
        }

        public Criteria andMemberNameGreaterThan(String value) {
            addCriterion("member_name >", value, "memberName");
            return (Criteria) this;
        }

        public Criteria andMemberNameGreaterThanOrEqualTo(String value) {
            addCriterion("member_name >=", value, "memberName");
            return (Criteria) this;
        }

        public Criteria andMemberNameLessThan(String value) {
            addCriterion("member_name <", value, "memberName");
            return (Criteria) this;
        }

        public Criteria andMemberNameLessThanOrEqualTo(String value) {
            addCriterion("member_name <=", value, "memberName");
            return (Criteria) this;
        }

        public Criteria andMemberNameLike(String value) {
            addCriterion("member_name like", value, "memberName");
            return (Criteria) this;
        }

        public Criteria andMemberNameNotLike(String value) {
            addCriterion("member_name not like", value, "memberName");
            return (Criteria) this;
        }

        public Criteria andMemberNameIn(List<String> values) {
            addCriterion("member_name in", values, "memberName");
            return (Criteria) this;
        }

        public Criteria andMemberNameNotIn(List<String> values) {
            addCriterion("member_name not in", values, "memberName");
            return (Criteria) this;
        }

        public Criteria andMemberNameBetween(String value1, String value2) {
            addCriterion("member_name between", value1, value2, "memberName");
            return (Criteria) this;
        }

        public Criteria andMemberNameNotBetween(String value1, String value2) {
            addCriterion("member_name not between", value1, value2, "memberName");
            return (Criteria) this;
        }

        public Criteria andLevelIdIsNull() {
            addCriterion("level_id is null");
            return (Criteria) this;
        }

        public Criteria andLevelIdIsNotNull() {
            addCriterion("level_id is not null");
            return (Criteria) this;
        }

        public Criteria andLevelIdEqualTo(Integer value) {
            addCriterion("level_id =", value, "levelId");
            return (Criteria) this;
        }

        public Criteria andLevelIdNotEqualTo(Integer value) {
            addCriterion("level_id <>", value, "levelId");
            return (Criteria) this;
        }

        public Criteria andLevelIdGreaterThan(Integer value) {
            addCriterion("level_id >", value, "levelId");
            return (Criteria) this;
        }

        public Criteria andLevelIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("level_id >=", value, "levelId");
            return (Criteria) this;
        }

        public Criteria andLevelIdLessThan(Integer value) {
            addCriterion("level_id <", value, "levelId");
            return (Criteria) this;
        }

        public Criteria andLevelIdLessThanOrEqualTo(Integer value) {
            addCriterion("level_id <=", value, "levelId");
            return (Criteria) this;
        }

        public Criteria andLevelIdIn(List<Integer> values) {
            addCriterion("level_id in", values, "levelId");
            return (Criteria) this;
        }

        public Criteria andLevelIdNotIn(List<Integer> values) {
            addCriterion("level_id not in", values, "levelId");
            return (Criteria) this;
        }

        public Criteria andLevelIdBetween(Integer value1, Integer value2) {
            addCriterion("level_id between", value1, value2, "levelId");
            return (Criteria) this;
        }

        public Criteria andLevelIdNotBetween(Integer value1, Integer value2) {
            addCriterion("level_id not between", value1, value2, "levelId");
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

        public Criteria andOrderIdIsNull() {
            addCriterion("order_id is null");
            return (Criteria) this;
        }

        public Criteria andOrderIdIsNotNull() {
            addCriterion("order_id is not null");
            return (Criteria) this;
        }

        public Criteria andOrderIdEqualTo(Integer value) {
            addCriterion("order_id =", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotEqualTo(Integer value) {
            addCriterion("order_id <>", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdGreaterThan(Integer value) {
            addCriterion("order_id >", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("order_id >=", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLessThan(Integer value) {
            addCriterion("order_id <", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLessThanOrEqualTo(Integer value) {
            addCriterion("order_id <=", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdIn(List<Integer> values) {
            addCriterion("order_id in", values, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotIn(List<Integer> values) {
            addCriterion("order_id not in", values, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdBetween(Integer value1, Integer value2) {
            addCriterion("order_id between", value1, value2, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotBetween(Integer value1, Integer value2) {
            addCriterion("order_id not between", value1, value2, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderNoIsNull() {
            addCriterion("order_no is null");
            return (Criteria) this;
        }

        public Criteria andOrderNoIsNotNull() {
            addCriterion("order_no is not null");
            return (Criteria) this;
        }

        public Criteria andOrderNoEqualTo(String value) {
            addCriterion("order_no =", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoNotEqualTo(String value) {
            addCriterion("order_no <>", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoGreaterThan(String value) {
            addCriterion("order_no >", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoGreaterThanOrEqualTo(String value) {
            addCriterion("order_no >=", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoLessThan(String value) {
            addCriterion("order_no <", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoLessThanOrEqualTo(String value) {
            addCriterion("order_no <=", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoLike(String value) {
            addCriterion("order_no like", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoNotLike(String value) {
            addCriterion("order_no not like", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoIn(List<String> values) {
            addCriterion("order_no in", values, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoNotIn(List<String> values) {
            addCriterion("order_no not in", values, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoBetween(String value1, String value2) {
            addCriterion("order_no between", value1, value2, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoNotBetween(String value1, String value2) {
            addCriterion("order_no not between", value1, value2, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderMemberIdIsNull() {
            addCriterion("order_member_id is null");
            return (Criteria) this;
        }

        public Criteria andOrderMemberIdIsNotNull() {
            addCriterion("order_member_id is not null");
            return (Criteria) this;
        }

        public Criteria andOrderMemberIdEqualTo(Integer value) {
            addCriterion("order_member_id =", value, "orderMemberId");
            return (Criteria) this;
        }

        public Criteria andOrderMemberIdNotEqualTo(Integer value) {
            addCriterion("order_member_id <>", value, "orderMemberId");
            return (Criteria) this;
        }

        public Criteria andOrderMemberIdGreaterThan(Integer value) {
            addCriterion("order_member_id >", value, "orderMemberId");
            return (Criteria) this;
        }

        public Criteria andOrderMemberIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("order_member_id >=", value, "orderMemberId");
            return (Criteria) this;
        }

        public Criteria andOrderMemberIdLessThan(Integer value) {
            addCriterion("order_member_id <", value, "orderMemberId");
            return (Criteria) this;
        }

        public Criteria andOrderMemberIdLessThanOrEqualTo(Integer value) {
            addCriterion("order_member_id <=", value, "orderMemberId");
            return (Criteria) this;
        }

        public Criteria andOrderMemberIdIn(List<Integer> values) {
            addCriterion("order_member_id in", values, "orderMemberId");
            return (Criteria) this;
        }

        public Criteria andOrderMemberIdNotIn(List<Integer> values) {
            addCriterion("order_member_id not in", values, "orderMemberId");
            return (Criteria) this;
        }

        public Criteria andOrderMemberIdBetween(Integer value1, Integer value2) {
            addCriterion("order_member_id between", value1, value2, "orderMemberId");
            return (Criteria) this;
        }

        public Criteria andOrderMemberIdNotBetween(Integer value1, Integer value2) {
            addCriterion("order_member_id not between", value1, value2, "orderMemberId");
            return (Criteria) this;
        }

        public Criteria andOrderMemberNameIsNull() {
            addCriterion("order_member_name is null");
            return (Criteria) this;
        }

        public Criteria andOrderMemberNameIsNotNull() {
            addCriterion("order_member_name is not null");
            return (Criteria) this;
        }

        public Criteria andOrderMemberNameEqualTo(String value) {
            addCriterion("order_member_name =", value, "orderMemberName");
            return (Criteria) this;
        }

        public Criteria andOrderMemberNameNotEqualTo(String value) {
            addCriterion("order_member_name <>", value, "orderMemberName");
            return (Criteria) this;
        }

        public Criteria andOrderMemberNameGreaterThan(String value) {
            addCriterion("order_member_name >", value, "orderMemberName");
            return (Criteria) this;
        }

        public Criteria andOrderMemberNameGreaterThanOrEqualTo(String value) {
            addCriterion("order_member_name >=", value, "orderMemberName");
            return (Criteria) this;
        }

        public Criteria andOrderMemberNameLessThan(String value) {
            addCriterion("order_member_name <", value, "orderMemberName");
            return (Criteria) this;
        }

        public Criteria andOrderMemberNameLessThanOrEqualTo(String value) {
            addCriterion("order_member_name <=", value, "orderMemberName");
            return (Criteria) this;
        }

        public Criteria andOrderMemberNameLike(String value) {
            addCriterion("order_member_name like", value, "orderMemberName");
            return (Criteria) this;
        }

        public Criteria andOrderMemberNameNotLike(String value) {
            addCriterion("order_member_name not like", value, "orderMemberName");
            return (Criteria) this;
        }

        public Criteria andOrderMemberNameIn(List<String> values) {
            addCriterion("order_member_name in", values, "orderMemberName");
            return (Criteria) this;
        }

        public Criteria andOrderMemberNameNotIn(List<String> values) {
            addCriterion("order_member_name not in", values, "orderMemberName");
            return (Criteria) this;
        }

        public Criteria andOrderMemberNameBetween(String value1, String value2) {
            addCriterion("order_member_name between", value1, value2, "orderMemberName");
            return (Criteria) this;
        }

        public Criteria andOrderMemberNameNotBetween(String value1, String value2) {
            addCriterion("order_member_name not between", value1, value2, "orderMemberName");
            return (Criteria) this;
        }

        public Criteria andOrderAmountTotalIsNull() {
            addCriterion("order_amount_total is null");
            return (Criteria) this;
        }

        public Criteria andOrderAmountTotalIsNotNull() {
            addCriterion("order_amount_total is not null");
            return (Criteria) this;
        }

        public Criteria andOrderAmountTotalEqualTo(Integer value) {
            addCriterion("order_amount_total =", value, "orderAmountTotal");
            return (Criteria) this;
        }

        public Criteria andOrderAmountTotalNotEqualTo(Integer value) {
            addCriterion("order_amount_total <>", value, "orderAmountTotal");
            return (Criteria) this;
        }

        public Criteria andOrderAmountTotalGreaterThan(Integer value) {
            addCriterion("order_amount_total >", value, "orderAmountTotal");
            return (Criteria) this;
        }

        public Criteria andOrderAmountTotalGreaterThanOrEqualTo(Integer value) {
            addCriterion("order_amount_total >=", value, "orderAmountTotal");
            return (Criteria) this;
        }

        public Criteria andOrderAmountTotalLessThan(Integer value) {
            addCriterion("order_amount_total <", value, "orderAmountTotal");
            return (Criteria) this;
        }

        public Criteria andOrderAmountTotalLessThanOrEqualTo(Integer value) {
            addCriterion("order_amount_total <=", value, "orderAmountTotal");
            return (Criteria) this;
        }

        public Criteria andOrderAmountTotalIn(List<Integer> values) {
            addCriterion("order_amount_total in", values, "orderAmountTotal");
            return (Criteria) this;
        }

        public Criteria andOrderAmountTotalNotIn(List<Integer> values) {
            addCriterion("order_amount_total not in", values, "orderAmountTotal");
            return (Criteria) this;
        }

        public Criteria andOrderAmountTotalBetween(Integer value1, Integer value2) {
            addCriterion("order_amount_total between", value1, value2, "orderAmountTotal");
            return (Criteria) this;
        }

        public Criteria andOrderAmountTotalNotBetween(Integer value1, Integer value2) {
            addCriterion("order_amount_total not between", value1, value2, "orderAmountTotal");
            return (Criteria) this;
        }

        public Criteria andOrderAmountPayIsNull() {
            addCriterion("order_amount_pay is null");
            return (Criteria) this;
        }

        public Criteria andOrderAmountPayIsNotNull() {
            addCriterion("order_amount_pay is not null");
            return (Criteria) this;
        }

        public Criteria andOrderAmountPayEqualTo(Integer value) {
            addCriterion("order_amount_pay =", value, "orderAmountPay");
            return (Criteria) this;
        }

        public Criteria andOrderAmountPayNotEqualTo(Integer value) {
            addCriterion("order_amount_pay <>", value, "orderAmountPay");
            return (Criteria) this;
        }

        public Criteria andOrderAmountPayGreaterThan(Integer value) {
            addCriterion("order_amount_pay >", value, "orderAmountPay");
            return (Criteria) this;
        }

        public Criteria andOrderAmountPayGreaterThanOrEqualTo(Integer value) {
            addCriterion("order_amount_pay >=", value, "orderAmountPay");
            return (Criteria) this;
        }

        public Criteria andOrderAmountPayLessThan(Integer value) {
            addCriterion("order_amount_pay <", value, "orderAmountPay");
            return (Criteria) this;
        }

        public Criteria andOrderAmountPayLessThanOrEqualTo(Integer value) {
            addCriterion("order_amount_pay <=", value, "orderAmountPay");
            return (Criteria) this;
        }

        public Criteria andOrderAmountPayIn(List<Integer> values) {
            addCriterion("order_amount_pay in", values, "orderAmountPay");
            return (Criteria) this;
        }

        public Criteria andOrderAmountPayNotIn(List<Integer> values) {
            addCriterion("order_amount_pay not in", values, "orderAmountPay");
            return (Criteria) this;
        }

        public Criteria andOrderAmountPayBetween(Integer value1, Integer value2) {
            addCriterion("order_amount_pay between", value1, value2, "orderAmountPay");
            return (Criteria) this;
        }

        public Criteria andOrderAmountPayNotBetween(Integer value1, Integer value2) {
            addCriterion("order_amount_pay not between", value1, value2, "orderAmountPay");
            return (Criteria) this;
        }

        public Criteria andIncomeAmountTotalIsNull() {
            addCriterion("income_amount_total is null");
            return (Criteria) this;
        }

        public Criteria andIncomeAmountTotalIsNotNull() {
            addCriterion("income_amount_total is not null");
            return (Criteria) this;
        }

        public Criteria andIncomeAmountTotalEqualTo(Integer value) {
            addCriterion("income_amount_total =", value, "incomeAmountTotal");
            return (Criteria) this;
        }

        public Criteria andIncomeAmountTotalNotEqualTo(Integer value) {
            addCriterion("income_amount_total <>", value, "incomeAmountTotal");
            return (Criteria) this;
        }

        public Criteria andIncomeAmountTotalGreaterThan(Integer value) {
            addCriterion("income_amount_total >", value, "incomeAmountTotal");
            return (Criteria) this;
        }

        public Criteria andIncomeAmountTotalGreaterThanOrEqualTo(Integer value) {
            addCriterion("income_amount_total >=", value, "incomeAmountTotal");
            return (Criteria) this;
        }

        public Criteria andIncomeAmountTotalLessThan(Integer value) {
            addCriterion("income_amount_total <", value, "incomeAmountTotal");
            return (Criteria) this;
        }

        public Criteria andIncomeAmountTotalLessThanOrEqualTo(Integer value) {
            addCriterion("income_amount_total <=", value, "incomeAmountTotal");
            return (Criteria) this;
        }

        public Criteria andIncomeAmountTotalIn(List<Integer> values) {
            addCriterion("income_amount_total in", values, "incomeAmountTotal");
            return (Criteria) this;
        }

        public Criteria andIncomeAmountTotalNotIn(List<Integer> values) {
            addCriterion("income_amount_total not in", values, "incomeAmountTotal");
            return (Criteria) this;
        }

        public Criteria andIncomeAmountTotalBetween(Integer value1, Integer value2) {
            addCriterion("income_amount_total between", value1, value2, "incomeAmountTotal");
            return (Criteria) this;
        }

        public Criteria andIncomeAmountTotalNotBetween(Integer value1, Integer value2) {
            addCriterion("income_amount_total not between", value1, value2, "incomeAmountTotal");
            return (Criteria) this;
        }

        public Criteria andPointAmountTotalIsNull() {
            addCriterion("point_amount_total is null");
            return (Criteria) this;
        }

        public Criteria andPointAmountTotalIsNotNull() {
            addCriterion("point_amount_total is not null");
            return (Criteria) this;
        }

        public Criteria andPointAmountTotalEqualTo(Integer value) {
            addCriterion("point_amount_total =", value, "pointAmountTotal");
            return (Criteria) this;
        }

        public Criteria andPointAmountTotalNotEqualTo(Integer value) {
            addCriterion("point_amount_total <>", value, "pointAmountTotal");
            return (Criteria) this;
        }

        public Criteria andPointAmountTotalGreaterThan(Integer value) {
            addCriterion("point_amount_total >", value, "pointAmountTotal");
            return (Criteria) this;
        }

        public Criteria andPointAmountTotalGreaterThanOrEqualTo(Integer value) {
            addCriterion("point_amount_total >=", value, "pointAmountTotal");
            return (Criteria) this;
        }

        public Criteria andPointAmountTotalLessThan(Integer value) {
            addCriterion("point_amount_total <", value, "pointAmountTotal");
            return (Criteria) this;
        }

        public Criteria andPointAmountTotalLessThanOrEqualTo(Integer value) {
            addCriterion("point_amount_total <=", value, "pointAmountTotal");
            return (Criteria) this;
        }

        public Criteria andPointAmountTotalIn(List<Integer> values) {
            addCriterion("point_amount_total in", values, "pointAmountTotal");
            return (Criteria) this;
        }

        public Criteria andPointAmountTotalNotIn(List<Integer> values) {
            addCriterion("point_amount_total not in", values, "pointAmountTotal");
            return (Criteria) this;
        }

        public Criteria andPointAmountTotalBetween(Integer value1, Integer value2) {
            addCriterion("point_amount_total between", value1, value2, "pointAmountTotal");
            return (Criteria) this;
        }

        public Criteria andPointAmountTotalNotBetween(Integer value1, Integer value2) {
            addCriterion("point_amount_total not between", value1, value2, "pointAmountTotal");
            return (Criteria) this;
        }

        public Criteria andIncomeTypeIsNull() {
            addCriterion("income_type is null");
            return (Criteria) this;
        }

        public Criteria andIncomeTypeIsNotNull() {
            addCriterion("income_type is not null");
            return (Criteria) this;
        }

        public Criteria andIncomeTypeEqualTo(Integer value) {
            addCriterion("income_type =", value, "incomeType");
            return (Criteria) this;
        }

        public Criteria andIncomeTypeNotEqualTo(Integer value) {
            addCriterion("income_type <>", value, "incomeType");
            return (Criteria) this;
        }

        public Criteria andIncomeTypeGreaterThan(Integer value) {
            addCriterion("income_type >", value, "incomeType");
            return (Criteria) this;
        }

        public Criteria andIncomeTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("income_type >=", value, "incomeType");
            return (Criteria) this;
        }

        public Criteria andIncomeTypeLessThan(Integer value) {
            addCriterion("income_type <", value, "incomeType");
            return (Criteria) this;
        }

        public Criteria andIncomeTypeLessThanOrEqualTo(Integer value) {
            addCriterion("income_type <=", value, "incomeType");
            return (Criteria) this;
        }

        public Criteria andIncomeTypeIn(List<Integer> values) {
            addCriterion("income_type in", values, "incomeType");
            return (Criteria) this;
        }

        public Criteria andIncomeTypeNotIn(List<Integer> values) {
            addCriterion("income_type not in", values, "incomeType");
            return (Criteria) this;
        }

        public Criteria andIncomeTypeBetween(Integer value1, Integer value2) {
            addCriterion("income_type between", value1, value2, "incomeType");
            return (Criteria) this;
        }

        public Criteria andIncomeTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("income_type not between", value1, value2, "incomeType");
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