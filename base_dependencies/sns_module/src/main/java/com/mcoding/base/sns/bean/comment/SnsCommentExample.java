package com.mcoding.base.sns.bean.comment;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mcoding.base.core.IExample;
import com.mcoding.base.core.PageView;
import com.mcoding.base.utils.json.JsonUtilsForMcoding;

public class SnsCommentExample implements IExample<SnsComment> {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected PageView<SnsComment> pageView;

    public SnsCommentExample() {
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
    public PageView<SnsComment> getPageView() {
        return pageView;
    }

    @Override
    public void setPageView(PageView<SnsComment> pageView) {
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

        public Criteria andPostIdIsNull() {
            addCriterion("post_id is null");
            return (Criteria) this;
        }

        public Criteria andPostIdIsNotNull() {
            addCriterion("post_id is not null");
            return (Criteria) this;
        }

        public Criteria andPostIdEqualTo(Integer value) {
            addCriterion("post_id =", value, "postId");
            return (Criteria) this;
        }

        public Criteria andPostIdNotEqualTo(Integer value) {
            addCriterion("post_id <>", value, "postId");
            return (Criteria) this;
        }

        public Criteria andPostIdGreaterThan(Integer value) {
            addCriterion("post_id >", value, "postId");
            return (Criteria) this;
        }

        public Criteria andPostIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("post_id >=", value, "postId");
            return (Criteria) this;
        }

        public Criteria andPostIdLessThan(Integer value) {
            addCriterion("post_id <", value, "postId");
            return (Criteria) this;
        }

        public Criteria andPostIdLessThanOrEqualTo(Integer value) {
            addCriterion("post_id <=", value, "postId");
            return (Criteria) this;
        }

        public Criteria andPostIdIn(List<Integer> values) {
            addCriterion("post_id in", values, "postId");
            return (Criteria) this;
        }

        public Criteria andPostIdNotIn(List<Integer> values) {
            addCriterion("post_id not in", values, "postId");
            return (Criteria) this;
        }

        public Criteria andPostIdBetween(Integer value1, Integer value2) {
            addCriterion("post_id between", value1, value2, "postId");
            return (Criteria) this;
        }

        public Criteria andPostIdNotBetween(Integer value1, Integer value2) {
            addCriterion("post_id not between", value1, value2, "postId");
            return (Criteria) this;
        }

        public Criteria andOpenIdIsNull() {
            addCriterion("open_id is null");
            return (Criteria) this;
        }

        public Criteria andOpenIdIsNotNull() {
            addCriterion("open_id is not null");
            return (Criteria) this;
        }

        public Criteria andOpenIdEqualTo(String value) {
            addCriterion("open_id =", value, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdNotEqualTo(String value) {
            addCriterion("open_id <>", value, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdGreaterThan(String value) {
            addCriterion("open_id >", value, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdGreaterThanOrEqualTo(String value) {
            addCriterion("open_id >=", value, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdLessThan(String value) {
            addCriterion("open_id <", value, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdLessThanOrEqualTo(String value) {
            addCriterion("open_id <=", value, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdLike(String value) {
            addCriterion("open_id like", value, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdNotLike(String value) {
            addCriterion("open_id not like", value, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdIn(List<String> values) {
            addCriterion("open_id in", values, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdNotIn(List<String> values) {
            addCriterion("open_id not in", values, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdBetween(String value1, String value2) {
            addCriterion("open_id between", value1, value2, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdNotBetween(String value1, String value2) {
            addCriterion("open_id not between", value1, value2, "openId");
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

        public Criteria andMemberImgUrlIsNull() {
            addCriterion("member_img_url is null");
            return (Criteria) this;
        }

        public Criteria andMemberImgUrlIsNotNull() {
            addCriterion("member_img_url is not null");
            return (Criteria) this;
        }

        public Criteria andMemberImgUrlEqualTo(String value) {
            addCriterion("member_img_url =", value, "memberImgUrl");
            return (Criteria) this;
        }

        public Criteria andMemberImgUrlNotEqualTo(String value) {
            addCriterion("member_img_url <>", value, "memberImgUrl");
            return (Criteria) this;
        }

        public Criteria andMemberImgUrlGreaterThan(String value) {
            addCriterion("member_img_url >", value, "memberImgUrl");
            return (Criteria) this;
        }

        public Criteria andMemberImgUrlGreaterThanOrEqualTo(String value) {
            addCriterion("member_img_url >=", value, "memberImgUrl");
            return (Criteria) this;
        }

        public Criteria andMemberImgUrlLessThan(String value) {
            addCriterion("member_img_url <", value, "memberImgUrl");
            return (Criteria) this;
        }

        public Criteria andMemberImgUrlLessThanOrEqualTo(String value) {
            addCriterion("member_img_url <=", value, "memberImgUrl");
            return (Criteria) this;
        }

        public Criteria andMemberImgUrlLike(String value) {
            addCriterion("member_img_url like", value, "memberImgUrl");
            return (Criteria) this;
        }

        public Criteria andMemberImgUrlNotLike(String value) {
            addCriterion("member_img_url not like", value, "memberImgUrl");
            return (Criteria) this;
        }

        public Criteria andMemberImgUrlIn(List<String> values) {
            addCriterion("member_img_url in", values, "memberImgUrl");
            return (Criteria) this;
        }

        public Criteria andMemberImgUrlNotIn(List<String> values) {
            addCriterion("member_img_url not in", values, "memberImgUrl");
            return (Criteria) this;
        }

        public Criteria andMemberImgUrlBetween(String value1, String value2) {
            addCriterion("member_img_url between", value1, value2, "memberImgUrl");
            return (Criteria) this;
        }

        public Criteria andMemberImgUrlNotBetween(String value1, String value2) {
            addCriterion("member_img_url not between", value1, value2, "memberImgUrl");
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

        public Criteria andOperTypeIsNull() {
            addCriterion("oper_type is null");
            return (Criteria) this;
        }

        public Criteria andOperTypeIsNotNull() {
            addCriterion("oper_type is not null");
            return (Criteria) this;
        }

        public Criteria andOperTypeEqualTo(Integer value) {
            addCriterion("oper_type =", value, "operType");
            return (Criteria) this;
        }

        public Criteria andOperTypeNotEqualTo(Integer value) {
            addCriterion("oper_type <>", value, "operType");
            return (Criteria) this;
        }

        public Criteria andOperTypeGreaterThan(Integer value) {
            addCriterion("oper_type >", value, "operType");
            return (Criteria) this;
        }

        public Criteria andOperTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("oper_type >=", value, "operType");
            return (Criteria) this;
        }

        public Criteria andOperTypeLessThan(Integer value) {
            addCriterion("oper_type <", value, "operType");
            return (Criteria) this;
        }

        public Criteria andOperTypeLessThanOrEqualTo(Integer value) {
            addCriterion("oper_type <=", value, "operType");
            return (Criteria) this;
        }

        public Criteria andOperTypeIn(List<Integer> values) {
            addCriterion("oper_type in", values, "operType");
            return (Criteria) this;
        }

        public Criteria andOperTypeNotIn(List<Integer> values) {
            addCriterion("oper_type not in", values, "operType");
            return (Criteria) this;
        }

        public Criteria andOperTypeBetween(Integer value1, Integer value2) {
            addCriterion("oper_type between", value1, value2, "operType");
            return (Criteria) this;
        }

        public Criteria andOperTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("oper_type not between", value1, value2, "operType");
            return (Criteria) this;
        }

        public Criteria andIsEnabledIsNull() {
            addCriterion("is_enabled is null");
            return (Criteria) this;
        }

        public Criteria andIsEnabledIsNotNull() {
            addCriterion("is_enabled is not null");
            return (Criteria) this;
        }

        public Criteria andIsEnabledEqualTo(Boolean value) {
            addCriterion("is_enabled =", value, "isEnabled");
            return (Criteria) this;
        }

        public Criteria andIsEnabledNotEqualTo(Boolean value) {
            addCriterion("is_enabled <>", value, "isEnabled");
            return (Criteria) this;
        }

        public Criteria andIsEnabledGreaterThan(Boolean value) {
            addCriterion("is_enabled >", value, "isEnabled");
            return (Criteria) this;
        }

        public Criteria andIsEnabledGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_enabled >=", value, "isEnabled");
            return (Criteria) this;
        }

        public Criteria andIsEnabledLessThan(Boolean value) {
            addCriterion("is_enabled <", value, "isEnabled");
            return (Criteria) this;
        }

        public Criteria andIsEnabledLessThanOrEqualTo(Boolean value) {
            addCriterion("is_enabled <=", value, "isEnabled");
            return (Criteria) this;
        }

        public Criteria andIsEnabledIn(List<Boolean> values) {
            addCriterion("is_enabled in", values, "isEnabled");
            return (Criteria) this;
        }

        public Criteria andIsEnabledNotIn(List<Boolean> values) {
            addCriterion("is_enabled not in", values, "isEnabled");
            return (Criteria) this;
        }

        public Criteria andIsEnabledBetween(Boolean value1, Boolean value2) {
            addCriterion("is_enabled between", value1, value2, "isEnabled");
            return (Criteria) this;
        }

        public Criteria andIsEnabledNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_enabled not between", value1, value2, "isEnabled");
            return (Criteria) this;
        }

        public Criteria andIsCheckedIsNull() {
            addCriterion("is_checked is null");
            return (Criteria) this;
        }

        public Criteria andIsCheckedIsNotNull() {
            addCriterion("is_checked is not null");
            return (Criteria) this;
        }

        public Criteria andIsCheckedEqualTo(Boolean value) {
            addCriterion("is_checked =", value, "isChecked");
            return (Criteria) this;
        }

        public Criteria andIsCheckedNotEqualTo(Boolean value) {
            addCriterion("is_checked <>", value, "isChecked");
            return (Criteria) this;
        }

        public Criteria andIsCheckedGreaterThan(Boolean value) {
            addCriterion("is_checked >", value, "isChecked");
            return (Criteria) this;
        }

        public Criteria andIsCheckedGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_checked >=", value, "isChecked");
            return (Criteria) this;
        }

        public Criteria andIsCheckedLessThan(Boolean value) {
            addCriterion("is_checked <", value, "isChecked");
            return (Criteria) this;
        }

        public Criteria andIsCheckedLessThanOrEqualTo(Boolean value) {
            addCriterion("is_checked <=", value, "isChecked");
            return (Criteria) this;
        }

        public Criteria andIsCheckedIn(List<Boolean> values) {
            addCriterion("is_checked in", values, "isChecked");
            return (Criteria) this;
        }

        public Criteria andIsCheckedNotIn(List<Boolean> values) {
            addCriterion("is_checked not in", values, "isChecked");
            return (Criteria) this;
        }

        public Criteria andIsCheckedBetween(Boolean value1, Boolean value2) {
            addCriterion("is_checked between", value1, value2, "isChecked");
            return (Criteria) this;
        }

        public Criteria andIsCheckedNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_checked not between", value1, value2, "isChecked");
            return (Criteria) this;
        }

        public Criteria andCommentNumIsNull() {
            addCriterion("comment_num is null");
            return (Criteria) this;
        }

        public Criteria andCommentNumIsNotNull() {
            addCriterion("comment_num is not null");
            return (Criteria) this;
        }

        public Criteria andCommentNumEqualTo(Integer value) {
            addCriterion("comment_num =", value, "commentNum");
            return (Criteria) this;
        }

        public Criteria andCommentNumNotEqualTo(Integer value) {
            addCriterion("comment_num <>", value, "commentNum");
            return (Criteria) this;
        }

        public Criteria andCommentNumGreaterThan(Integer value) {
            addCriterion("comment_num >", value, "commentNum");
            return (Criteria) this;
        }

        public Criteria andCommentNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("comment_num >=", value, "commentNum");
            return (Criteria) this;
        }

        public Criteria andCommentNumLessThan(Integer value) {
            addCriterion("comment_num <", value, "commentNum");
            return (Criteria) this;
        }

        public Criteria andCommentNumLessThanOrEqualTo(Integer value) {
            addCriterion("comment_num <=", value, "commentNum");
            return (Criteria) this;
        }

        public Criteria andCommentNumIn(List<Integer> values) {
            addCriterion("comment_num in", values, "commentNum");
            return (Criteria) this;
        }

        public Criteria andCommentNumNotIn(List<Integer> values) {
            addCriterion("comment_num not in", values, "commentNum");
            return (Criteria) this;
        }

        public Criteria andCommentNumBetween(Integer value1, Integer value2) {
            addCriterion("comment_num between", value1, value2, "commentNum");
            return (Criteria) this;
        }

        public Criteria andCommentNumNotBetween(Integer value1, Integer value2) {
            addCriterion("comment_num not between", value1, value2, "commentNum");
            return (Criteria) this;
        }

        public Criteria andLikeNumIsNull() {
            addCriterion("like_num is null");
            return (Criteria) this;
        }

        public Criteria andLikeNumIsNotNull() {
            addCriterion("like_num is not null");
            return (Criteria) this;
        }

        public Criteria andLikeNumEqualTo(Integer value) {
            addCriterion("like_num =", value, "likeNum");
            return (Criteria) this;
        }

        public Criteria andLikeNumNotEqualTo(Integer value) {
            addCriterion("like_num <>", value, "likeNum");
            return (Criteria) this;
        }

        public Criteria andLikeNumGreaterThan(Integer value) {
            addCriterion("like_num >", value, "likeNum");
            return (Criteria) this;
        }

        public Criteria andLikeNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("like_num >=", value, "likeNum");
            return (Criteria) this;
        }

        public Criteria andLikeNumLessThan(Integer value) {
            addCriterion("like_num <", value, "likeNum");
            return (Criteria) this;
        }

        public Criteria andLikeNumLessThanOrEqualTo(Integer value) {
            addCriterion("like_num <=", value, "likeNum");
            return (Criteria) this;
        }

        public Criteria andLikeNumIn(List<Integer> values) {
            addCriterion("like_num in", values, "likeNum");
            return (Criteria) this;
        }

        public Criteria andLikeNumNotIn(List<Integer> values) {
            addCriterion("like_num not in", values, "likeNum");
            return (Criteria) this;
        }

        public Criteria andLikeNumBetween(Integer value1, Integer value2) {
            addCriterion("like_num between", value1, value2, "likeNum");
            return (Criteria) this;
        }

        public Criteria andLikeNumNotBetween(Integer value1, Integer value2) {
            addCriterion("like_num not between", value1, value2, "likeNum");
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

        public Criteria andParentCommentIdIsNull() {
            addCriterion("parent_comment_id is null");
            return (Criteria) this;
        }

        public Criteria andParentCommentIdIsNotNull() {
            addCriterion("parent_comment_id is not null");
            return (Criteria) this;
        }

        public Criteria andParentCommentIdEqualTo(Integer value) {
            addCriterion("parent_comment_id =", value, "parentCommentId");
            return (Criteria) this;
        }

        public Criteria andParentCommentIdNotEqualTo(Integer value) {
            addCriterion("parent_comment_id <>", value, "parentCommentId");
            return (Criteria) this;
        }

        public Criteria andParentCommentIdGreaterThan(Integer value) {
            addCriterion("parent_comment_id >", value, "parentCommentId");
            return (Criteria) this;
        }

        public Criteria andParentCommentIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("parent_comment_id >=", value, "parentCommentId");
            return (Criteria) this;
        }

        public Criteria andParentCommentIdLessThan(Integer value) {
            addCriterion("parent_comment_id <", value, "parentCommentId");
            return (Criteria) this;
        }

        public Criteria andParentCommentIdLessThanOrEqualTo(Integer value) {
            addCriterion("parent_comment_id <=", value, "parentCommentId");
            return (Criteria) this;
        }

        public Criteria andParentCommentIdIn(List<Integer> values) {
            addCriterion("parent_comment_id in", values, "parentCommentId");
            return (Criteria) this;
        }

        public Criteria andParentCommentIdNotIn(List<Integer> values) {
            addCriterion("parent_comment_id not in", values, "parentCommentId");
            return (Criteria) this;
        }

        public Criteria andParentCommentIdBetween(Integer value1, Integer value2) {
            addCriterion("parent_comment_id between", value1, value2, "parentCommentId");
            return (Criteria) this;
        }

        public Criteria andParentCommentIdNotBetween(Integer value1, Integer value2) {
            addCriterion("parent_comment_id not between", value1, value2, "parentCommentId");
            return (Criteria) this;
        }

        public Criteria andParentMemberIdIsNull() {
            addCriterion("parent_member_id is null");
            return (Criteria) this;
        }

        public Criteria andParentMemberIdIsNotNull() {
            addCriterion("parent_member_id is not null");
            return (Criteria) this;
        }

        public Criteria andParentMemberIdEqualTo(Integer value) {
            addCriterion("parent_member_id =", value, "parentMemberId");
            return (Criteria) this;
        }

        public Criteria andParentMemberIdNotEqualTo(Integer value) {
            addCriterion("parent_member_id <>", value, "parentMemberId");
            return (Criteria) this;
        }

        public Criteria andParentMemberIdGreaterThan(Integer value) {
            addCriterion("parent_member_id >", value, "parentMemberId");
            return (Criteria) this;
        }

        public Criteria andParentMemberIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("parent_member_id >=", value, "parentMemberId");
            return (Criteria) this;
        }

        public Criteria andParentMemberIdLessThan(Integer value) {
            addCriterion("parent_member_id <", value, "parentMemberId");
            return (Criteria) this;
        }

        public Criteria andParentMemberIdLessThanOrEqualTo(Integer value) {
            addCriterion("parent_member_id <=", value, "parentMemberId");
            return (Criteria) this;
        }

        public Criteria andParentMemberIdIn(List<Integer> values) {
            addCriterion("parent_member_id in", values, "parentMemberId");
            return (Criteria) this;
        }

        public Criteria andParentMemberIdNotIn(List<Integer> values) {
            addCriterion("parent_member_id not in", values, "parentMemberId");
            return (Criteria) this;
        }

        public Criteria andParentMemberIdBetween(Integer value1, Integer value2) {
            addCriterion("parent_member_id between", value1, value2, "parentMemberId");
            return (Criteria) this;
        }

        public Criteria andParentMemberIdNotBetween(Integer value1, Integer value2) {
            addCriterion("parent_member_id not between", value1, value2, "parentMemberId");
            return (Criteria) this;
        }

        public Criteria andParentMemberNameIsNull() {
            addCriterion("parent_member_name is null");
            return (Criteria) this;
        }

        public Criteria andParentMemberNameIsNotNull() {
            addCriterion("parent_member_name is not null");
            return (Criteria) this;
        }

        public Criteria andParentMemberNameEqualTo(String value) {
            addCriterion("parent_member_name =", value, "parentMemberName");
            return (Criteria) this;
        }

        public Criteria andParentMemberNameNotEqualTo(String value) {
            addCriterion("parent_member_name <>", value, "parentMemberName");
            return (Criteria) this;
        }

        public Criteria andParentMemberNameGreaterThan(String value) {
            addCriterion("parent_member_name >", value, "parentMemberName");
            return (Criteria) this;
        }

        public Criteria andParentMemberNameGreaterThanOrEqualTo(String value) {
            addCriterion("parent_member_name >=", value, "parentMemberName");
            return (Criteria) this;
        }

        public Criteria andParentMemberNameLessThan(String value) {
            addCriterion("parent_member_name <", value, "parentMemberName");
            return (Criteria) this;
        }

        public Criteria andParentMemberNameLessThanOrEqualTo(String value) {
            addCriterion("parent_member_name <=", value, "parentMemberName");
            return (Criteria) this;
        }

        public Criteria andParentMemberNameLike(String value) {
            addCriterion("parent_member_name like", value, "parentMemberName");
            return (Criteria) this;
        }

        public Criteria andParentMemberNameNotLike(String value) {
            addCriterion("parent_member_name not like", value, "parentMemberName");
            return (Criteria) this;
        }

        public Criteria andParentMemberNameIn(List<String> values) {
            addCriterion("parent_member_name in", values, "parentMemberName");
            return (Criteria) this;
        }

        public Criteria andParentMemberNameNotIn(List<String> values) {
            addCriterion("parent_member_name not in", values, "parentMemberName");
            return (Criteria) this;
        }

        public Criteria andParentMemberNameBetween(String value1, String value2) {
            addCriterion("parent_member_name between", value1, value2, "parentMemberName");
            return (Criteria) this;
        }

        public Criteria andParentMemberNameNotBetween(String value1, String value2) {
            addCriterion("parent_member_name not between", value1, value2, "parentMemberName");
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