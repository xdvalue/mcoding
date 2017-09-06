package com.mcoding.base.member.bean.member;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mcoding.base.core.IExample;
import com.mcoding.base.core.PageView;
import com.mcoding.base.utils.json.JsonUtilsForMcoding;

public class MemberFollowerExample implements IExample<MemberFollower> {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected PageView<MemberFollower> pageView;

    public MemberFollowerExample() {
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
    public PageView<MemberFollower> getPageView() {
        return pageView;
    }

    @Override
    public void setPageView(PageView<MemberFollower> pageView) {
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

        public Criteria andParentNameIsNull() {
            addCriterion("parent_name is null");
            return (Criteria) this;
        }

        public Criteria andParentNameIsNotNull() {
            addCriterion("parent_name is not null");
            return (Criteria) this;
        }

        public Criteria andParentNameEqualTo(String value) {
            addCriterion("parent_name =", value, "parentName");
            return (Criteria) this;
        }

        public Criteria andParentNameNotEqualTo(String value) {
            addCriterion("parent_name <>", value, "parentName");
            return (Criteria) this;
        }

        public Criteria andParentNameGreaterThan(String value) {
            addCriterion("parent_name >", value, "parentName");
            return (Criteria) this;
        }

        public Criteria andParentNameGreaterThanOrEqualTo(String value) {
            addCriterion("parent_name >=", value, "parentName");
            return (Criteria) this;
        }

        public Criteria andParentNameLessThan(String value) {
            addCriterion("parent_name <", value, "parentName");
            return (Criteria) this;
        }

        public Criteria andParentNameLessThanOrEqualTo(String value) {
            addCriterion("parent_name <=", value, "parentName");
            return (Criteria) this;
        }

        public Criteria andParentNameLike(String value) {
            addCriterion("parent_name like", value, "parentName");
            return (Criteria) this;
        }

        public Criteria andParentNameNotLike(String value) {
            addCriterion("parent_name not like", value, "parentName");
            return (Criteria) this;
        }

        public Criteria andParentNameIn(List<String> values) {
            addCriterion("parent_name in", values, "parentName");
            return (Criteria) this;
        }

        public Criteria andParentNameNotIn(List<String> values) {
            addCriterion("parent_name not in", values, "parentName");
            return (Criteria) this;
        }

        public Criteria andParentNameBetween(String value1, String value2) {
            addCriterion("parent_name between", value1, value2, "parentName");
            return (Criteria) this;
        }

        public Criteria andParentNameNotBetween(String value1, String value2) {
            addCriterion("parent_name not between", value1, value2, "parentName");
            return (Criteria) this;
        }

        public Criteria andParentHeadImgUrlIsNull() {
            addCriterion("parent_head_img_url is null");
            return (Criteria) this;
        }

        public Criteria andParentHeadImgUrlIsNotNull() {
            addCriterion("parent_head_img_url is not null");
            return (Criteria) this;
        }

        public Criteria andParentHeadImgUrlEqualTo(String value) {
            addCriterion("parent_head_img_url =", value, "parentHeadImgUrl");
            return (Criteria) this;
        }

        public Criteria andParentHeadImgUrlNotEqualTo(String value) {
            addCriterion("parent_head_img_url <>", value, "parentHeadImgUrl");
            return (Criteria) this;
        }

        public Criteria andParentHeadImgUrlGreaterThan(String value) {
            addCriterion("parent_head_img_url >", value, "parentHeadImgUrl");
            return (Criteria) this;
        }

        public Criteria andParentHeadImgUrlGreaterThanOrEqualTo(String value) {
            addCriterion("parent_head_img_url >=", value, "parentHeadImgUrl");
            return (Criteria) this;
        }

        public Criteria andParentHeadImgUrlLessThan(String value) {
            addCriterion("parent_head_img_url <", value, "parentHeadImgUrl");
            return (Criteria) this;
        }

        public Criteria andParentHeadImgUrlLessThanOrEqualTo(String value) {
            addCriterion("parent_head_img_url <=", value, "parentHeadImgUrl");
            return (Criteria) this;
        }

        public Criteria andParentHeadImgUrlLike(String value) {
            addCriterion("parent_head_img_url like", value, "parentHeadImgUrl");
            return (Criteria) this;
        }

        public Criteria andParentHeadImgUrlNotLike(String value) {
            addCriterion("parent_head_img_url not like", value, "parentHeadImgUrl");
            return (Criteria) this;
        }

        public Criteria andParentHeadImgUrlIn(List<String> values) {
            addCriterion("parent_head_img_url in", values, "parentHeadImgUrl");
            return (Criteria) this;
        }

        public Criteria andParentHeadImgUrlNotIn(List<String> values) {
            addCriterion("parent_head_img_url not in", values, "parentHeadImgUrl");
            return (Criteria) this;
        }

        public Criteria andParentHeadImgUrlBetween(String value1, String value2) {
            addCriterion("parent_head_img_url between", value1, value2, "parentHeadImgUrl");
            return (Criteria) this;
        }

        public Criteria andParentHeadImgUrlNotBetween(String value1, String value2) {
            addCriterion("parent_head_img_url not between", value1, value2, "parentHeadImgUrl");
            return (Criteria) this;
        }

        public Criteria andFollowerIdIsNull() {
            addCriterion("follower_id is null");
            return (Criteria) this;
        }

        public Criteria andFollowerIdIsNotNull() {
            addCriterion("follower_id is not null");
            return (Criteria) this;
        }

        public Criteria andFollowerIdEqualTo(Integer value) {
            addCriterion("follower_id =", value, "followerId");
            return (Criteria) this;
        }

        public Criteria andFollowerIdNotEqualTo(Integer value) {
            addCriterion("follower_id <>", value, "followerId");
            return (Criteria) this;
        }

        public Criteria andFollowerIdGreaterThan(Integer value) {
            addCriterion("follower_id >", value, "followerId");
            return (Criteria) this;
        }

        public Criteria andFollowerIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("follower_id >=", value, "followerId");
            return (Criteria) this;
        }

        public Criteria andFollowerIdLessThan(Integer value) {
            addCriterion("follower_id <", value, "followerId");
            return (Criteria) this;
        }

        public Criteria andFollowerIdLessThanOrEqualTo(Integer value) {
            addCriterion("follower_id <=", value, "followerId");
            return (Criteria) this;
        }

        public Criteria andFollowerIdIn(List<Integer> values) {
            addCriterion("follower_id in", values, "followerId");
            return (Criteria) this;
        }

        public Criteria andFollowerIdNotIn(List<Integer> values) {
            addCriterion("follower_id not in", values, "followerId");
            return (Criteria) this;
        }

        public Criteria andFollowerIdBetween(Integer value1, Integer value2) {
            addCriterion("follower_id between", value1, value2, "followerId");
            return (Criteria) this;
        }

        public Criteria andFollowerIdNotBetween(Integer value1, Integer value2) {
            addCriterion("follower_id not between", value1, value2, "followerId");
            return (Criteria) this;
        }

        public Criteria andFollowerNameIsNull() {
            addCriterion("follower_name is null");
            return (Criteria) this;
        }

        public Criteria andFollowerNameIsNotNull() {
            addCriterion("follower_name is not null");
            return (Criteria) this;
        }

        public Criteria andFollowerNameEqualTo(String value) {
            addCriterion("follower_name =", value, "followerName");
            return (Criteria) this;
        }

        public Criteria andFollowerNameNotEqualTo(String value) {
            addCriterion("follower_name <>", value, "followerName");
            return (Criteria) this;
        }

        public Criteria andFollowerNameGreaterThan(String value) {
            addCriterion("follower_name >", value, "followerName");
            return (Criteria) this;
        }

        public Criteria andFollowerNameGreaterThanOrEqualTo(String value) {
            addCriterion("follower_name >=", value, "followerName");
            return (Criteria) this;
        }

        public Criteria andFollowerNameLessThan(String value) {
            addCriterion("follower_name <", value, "followerName");
            return (Criteria) this;
        }

        public Criteria andFollowerNameLessThanOrEqualTo(String value) {
            addCriterion("follower_name <=", value, "followerName");
            return (Criteria) this;
        }

        public Criteria andFollowerNameLike(String value) {
            addCriterion("follower_name like", value, "followerName");
            return (Criteria) this;
        }

        public Criteria andFollowerNameNotLike(String value) {
            addCriterion("follower_name not like", value, "followerName");
            return (Criteria) this;
        }

        public Criteria andFollowerNameIn(List<String> values) {
            addCriterion("follower_name in", values, "followerName");
            return (Criteria) this;
        }

        public Criteria andFollowerNameNotIn(List<String> values) {
            addCriterion("follower_name not in", values, "followerName");
            return (Criteria) this;
        }

        public Criteria andFollowerNameBetween(String value1, String value2) {
            addCriterion("follower_name between", value1, value2, "followerName");
            return (Criteria) this;
        }

        public Criteria andFollowerNameNotBetween(String value1, String value2) {
            addCriterion("follower_name not between", value1, value2, "followerName");
            return (Criteria) this;
        }

        public Criteria andFollowerHeadImgUrlIsNull() {
            addCriterion("follower_head_img_url is null");
            return (Criteria) this;
        }

        public Criteria andFollowerHeadImgUrlIsNotNull() {
            addCriterion("follower_head_img_url is not null");
            return (Criteria) this;
        }

        public Criteria andFollowerHeadImgUrlEqualTo(String value) {
            addCriterion("follower_head_img_url =", value, "followerHeadImgUrl");
            return (Criteria) this;
        }

        public Criteria andFollowerHeadImgUrlNotEqualTo(String value) {
            addCriterion("follower_head_img_url <>", value, "followerHeadImgUrl");
            return (Criteria) this;
        }

        public Criteria andFollowerHeadImgUrlGreaterThan(String value) {
            addCriterion("follower_head_img_url >", value, "followerHeadImgUrl");
            return (Criteria) this;
        }

        public Criteria andFollowerHeadImgUrlGreaterThanOrEqualTo(String value) {
            addCriterion("follower_head_img_url >=", value, "followerHeadImgUrl");
            return (Criteria) this;
        }

        public Criteria andFollowerHeadImgUrlLessThan(String value) {
            addCriterion("follower_head_img_url <", value, "followerHeadImgUrl");
            return (Criteria) this;
        }

        public Criteria andFollowerHeadImgUrlLessThanOrEqualTo(String value) {
            addCriterion("follower_head_img_url <=", value, "followerHeadImgUrl");
            return (Criteria) this;
        }

        public Criteria andFollowerHeadImgUrlLike(String value) {
            addCriterion("follower_head_img_url like", value, "followerHeadImgUrl");
            return (Criteria) this;
        }

        public Criteria andFollowerHeadImgUrlNotLike(String value) {
            addCriterion("follower_head_img_url not like", value, "followerHeadImgUrl");
            return (Criteria) this;
        }

        public Criteria andFollowerHeadImgUrlIn(List<String> values) {
            addCriterion("follower_head_img_url in", values, "followerHeadImgUrl");
            return (Criteria) this;
        }

        public Criteria andFollowerHeadImgUrlNotIn(List<String> values) {
            addCriterion("follower_head_img_url not in", values, "followerHeadImgUrl");
            return (Criteria) this;
        }

        public Criteria andFollowerHeadImgUrlBetween(String value1, String value2) {
            addCriterion("follower_head_img_url between", value1, value2, "followerHeadImgUrl");
            return (Criteria) this;
        }

        public Criteria andFollowerHeadImgUrlNotBetween(String value1, String value2) {
            addCriterion("follower_head_img_url not between", value1, value2, "followerHeadImgUrl");
            return (Criteria) this;
        }

        public Criteria andFollowTimeIsNull() {
            addCriterion("follow_time is null");
            return (Criteria) this;
        }

        public Criteria andFollowTimeIsNotNull() {
            addCriterion("follow_time is not null");
            return (Criteria) this;
        }

        public Criteria andFollowTimeEqualTo(Date value) {
            addCriterion("follow_time =", value, "followTime");
            return (Criteria) this;
        }

        public Criteria andFollowTimeNotEqualTo(Date value) {
            addCriterion("follow_time <>", value, "followTime");
            return (Criteria) this;
        }

        public Criteria andFollowTimeGreaterThan(Date value) {
            addCriterion("follow_time >", value, "followTime");
            return (Criteria) this;
        }

        public Criteria andFollowTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("follow_time >=", value, "followTime");
            return (Criteria) this;
        }

        public Criteria andFollowTimeLessThan(Date value) {
            addCriterion("follow_time <", value, "followTime");
            return (Criteria) this;
        }

        public Criteria andFollowTimeLessThanOrEqualTo(Date value) {
            addCriterion("follow_time <=", value, "followTime");
            return (Criteria) this;
        }

        public Criteria andFollowTimeIn(List<Date> values) {
            addCriterion("follow_time in", values, "followTime");
            return (Criteria) this;
        }

        public Criteria andFollowTimeNotIn(List<Date> values) {
            addCriterion("follow_time not in", values, "followTime");
            return (Criteria) this;
        }

        public Criteria andFollowTimeBetween(Date value1, Date value2) {
            addCriterion("follow_time between", value1, value2, "followTime");
            return (Criteria) this;
        }

        public Criteria andFollowTimeNotBetween(Date value1, Date value2) {
            addCriterion("follow_time not between", value1, value2, "followTime");
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