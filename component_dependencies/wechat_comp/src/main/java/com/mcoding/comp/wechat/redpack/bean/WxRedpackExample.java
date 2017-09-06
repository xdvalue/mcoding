package com.mcoding.comp.wechat.redpack.bean;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mcoding.base.core.IExample;
import com.mcoding.base.core.PageView;
import com.mcoding.base.utils.json.JsonUtilsForMcoding;
import java.util.ArrayList;
import java.util.List;

public class WxRedpackExample implements IExample<WxRedpack> {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected PageView<WxRedpack> pageView;

    public WxRedpackExample() {
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
    public PageView<WxRedpack> getPageView() {
        return pageView;
    }

    @Override
    public void setPageView(PageView<WxRedpack> pageView) {
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

        public Criteria andAccoutIdIsNull() {
            addCriterion("accout_id is null");
            return (Criteria) this;
        }

        public Criteria andAccoutIdIsNotNull() {
            addCriterion("accout_id is not null");
            return (Criteria) this;
        }

        public Criteria andAccoutIdEqualTo(Integer value) {
            addCriterion("accout_id =", value, "accoutId");
            return (Criteria) this;
        }

        public Criteria andAccoutIdNotEqualTo(Integer value) {
            addCriterion("accout_id <>", value, "accoutId");
            return (Criteria) this;
        }

        public Criteria andAccoutIdGreaterThan(Integer value) {
            addCriterion("accout_id >", value, "accoutId");
            return (Criteria) this;
        }

        public Criteria andAccoutIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("accout_id >=", value, "accoutId");
            return (Criteria) this;
        }

        public Criteria andAccoutIdLessThan(Integer value) {
            addCriterion("accout_id <", value, "accoutId");
            return (Criteria) this;
        }

        public Criteria andAccoutIdLessThanOrEqualTo(Integer value) {
            addCriterion("accout_id <=", value, "accoutId");
            return (Criteria) this;
        }

        public Criteria andAccoutIdIn(List<Integer> values) {
            addCriterion("accout_id in", values, "accoutId");
            return (Criteria) this;
        }

        public Criteria andAccoutIdNotIn(List<Integer> values) {
            addCriterion("accout_id not in", values, "accoutId");
            return (Criteria) this;
        }

        public Criteria andAccoutIdBetween(Integer value1, Integer value2) {
            addCriterion("accout_id between", value1, value2, "accoutId");
            return (Criteria) this;
        }

        public Criteria andAccoutIdNotBetween(Integer value1, Integer value2) {
            addCriterion("accout_id not between", value1, value2, "accoutId");
            return (Criteria) this;
        }

        public Criteria andAccountNameIsNull() {
            addCriterion("account_name is null");
            return (Criteria) this;
        }

        public Criteria andAccountNameIsNotNull() {
            addCriterion("account_name is not null");
            return (Criteria) this;
        }

        public Criteria andAccountNameEqualTo(String value) {
            addCriterion("account_name =", value, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameNotEqualTo(String value) {
            addCriterion("account_name <>", value, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameGreaterThan(String value) {
            addCriterion("account_name >", value, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameGreaterThanOrEqualTo(String value) {
            addCriterion("account_name >=", value, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameLessThan(String value) {
            addCriterion("account_name <", value, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameLessThanOrEqualTo(String value) {
            addCriterion("account_name <=", value, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameLike(String value) {
            addCriterion("account_name like", value, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameNotLike(String value) {
            addCriterion("account_name not like", value, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameIn(List<String> values) {
            addCriterion("account_name in", values, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameNotIn(List<String> values) {
            addCriterion("account_name not in", values, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameBetween(String value1, String value2) {
            addCriterion("account_name between", value1, value2, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameNotBetween(String value1, String value2) {
            addCriterion("account_name not between", value1, value2, "accountName");
            return (Criteria) this;
        }

        public Criteria andRedpackCodeIsNull() {
            addCriterion("redpack_code is null");
            return (Criteria) this;
        }

        public Criteria andRedpackCodeIsNotNull() {
            addCriterion("redpack_code is not null");
            return (Criteria) this;
        }

        public Criteria andRedpackCodeEqualTo(String value) {
            addCriterion("redpack_code =", value, "redpackCode");
            return (Criteria) this;
        }

        public Criteria andRedpackCodeNotEqualTo(String value) {
            addCriterion("redpack_code <>", value, "redpackCode");
            return (Criteria) this;
        }

        public Criteria andRedpackCodeGreaterThan(String value) {
            addCriterion("redpack_code >", value, "redpackCode");
            return (Criteria) this;
        }

        public Criteria andRedpackCodeGreaterThanOrEqualTo(String value) {
            addCriterion("redpack_code >=", value, "redpackCode");
            return (Criteria) this;
        }

        public Criteria andRedpackCodeLessThan(String value) {
            addCriterion("redpack_code <", value, "redpackCode");
            return (Criteria) this;
        }

        public Criteria andRedpackCodeLessThanOrEqualTo(String value) {
            addCriterion("redpack_code <=", value, "redpackCode");
            return (Criteria) this;
        }

        public Criteria andRedpackCodeLike(String value) {
            addCriterion("redpack_code like", value, "redpackCode");
            return (Criteria) this;
        }

        public Criteria andRedpackCodeNotLike(String value) {
            addCriterion("redpack_code not like", value, "redpackCode");
            return (Criteria) this;
        }

        public Criteria andRedpackCodeIn(List<String> values) {
            addCriterion("redpack_code in", values, "redpackCode");
            return (Criteria) this;
        }

        public Criteria andRedpackCodeNotIn(List<String> values) {
            addCriterion("redpack_code not in", values, "redpackCode");
            return (Criteria) this;
        }

        public Criteria andRedpackCodeBetween(String value1, String value2) {
            addCriterion("redpack_code between", value1, value2, "redpackCode");
            return (Criteria) this;
        }

        public Criteria andRedpackCodeNotBetween(String value1, String value2) {
            addCriterion("redpack_code not between", value1, value2, "redpackCode");
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

        public Criteria andSceneIdEqualTo(String value) {
            addCriterion("scene_id =", value, "sceneId");
            return (Criteria) this;
        }

        public Criteria andSceneIdNotEqualTo(String value) {
            addCriterion("scene_id <>", value, "sceneId");
            return (Criteria) this;
        }

        public Criteria andSceneIdGreaterThan(String value) {
            addCriterion("scene_id >", value, "sceneId");
            return (Criteria) this;
        }

        public Criteria andSceneIdGreaterThanOrEqualTo(String value) {
            addCriterion("scene_id >=", value, "sceneId");
            return (Criteria) this;
        }

        public Criteria andSceneIdLessThan(String value) {
            addCriterion("scene_id <", value, "sceneId");
            return (Criteria) this;
        }

        public Criteria andSceneIdLessThanOrEqualTo(String value) {
            addCriterion("scene_id <=", value, "sceneId");
            return (Criteria) this;
        }

        public Criteria andSceneIdLike(String value) {
            addCriterion("scene_id like", value, "sceneId");
            return (Criteria) this;
        }

        public Criteria andSceneIdNotLike(String value) {
            addCriterion("scene_id not like", value, "sceneId");
            return (Criteria) this;
        }

        public Criteria andSceneIdIn(List<String> values) {
            addCriterion("scene_id in", values, "sceneId");
            return (Criteria) this;
        }

        public Criteria andSceneIdNotIn(List<String> values) {
            addCriterion("scene_id not in", values, "sceneId");
            return (Criteria) this;
        }

        public Criteria andSceneIdBetween(String value1, String value2) {
            addCriterion("scene_id between", value1, value2, "sceneId");
            return (Criteria) this;
        }

        public Criteria andSceneIdNotBetween(String value1, String value2) {
            addCriterion("scene_id not between", value1, value2, "sceneId");
            return (Criteria) this;
        }

        public Criteria andActNameIsNull() {
            addCriterion("act_name is null");
            return (Criteria) this;
        }

        public Criteria andActNameIsNotNull() {
            addCriterion("act_name is not null");
            return (Criteria) this;
        }

        public Criteria andActNameEqualTo(String value) {
            addCriterion("act_name =", value, "actName");
            return (Criteria) this;
        }

        public Criteria andActNameNotEqualTo(String value) {
            addCriterion("act_name <>", value, "actName");
            return (Criteria) this;
        }

        public Criteria andActNameGreaterThan(String value) {
            addCriterion("act_name >", value, "actName");
            return (Criteria) this;
        }

        public Criteria andActNameGreaterThanOrEqualTo(String value) {
            addCriterion("act_name >=", value, "actName");
            return (Criteria) this;
        }

        public Criteria andActNameLessThan(String value) {
            addCriterion("act_name <", value, "actName");
            return (Criteria) this;
        }

        public Criteria andActNameLessThanOrEqualTo(String value) {
            addCriterion("act_name <=", value, "actName");
            return (Criteria) this;
        }

        public Criteria andActNameLike(String value) {
            addCriterion("act_name like", value, "actName");
            return (Criteria) this;
        }

        public Criteria andActNameNotLike(String value) {
            addCriterion("act_name not like", value, "actName");
            return (Criteria) this;
        }

        public Criteria andActNameIn(List<String> values) {
            addCriterion("act_name in", values, "actName");
            return (Criteria) this;
        }

        public Criteria andActNameNotIn(List<String> values) {
            addCriterion("act_name not in", values, "actName");
            return (Criteria) this;
        }

        public Criteria andActNameBetween(String value1, String value2) {
            addCriterion("act_name between", value1, value2, "actName");
            return (Criteria) this;
        }

        public Criteria andActNameNotBetween(String value1, String value2) {
            addCriterion("act_name not between", value1, value2, "actName");
            return (Criteria) this;
        }

        public Criteria andSendNameIsNull() {
            addCriterion("send_name is null");
            return (Criteria) this;
        }

        public Criteria andSendNameIsNotNull() {
            addCriterion("send_name is not null");
            return (Criteria) this;
        }

        public Criteria andSendNameEqualTo(String value) {
            addCriterion("send_name =", value, "sendName");
            return (Criteria) this;
        }

        public Criteria andSendNameNotEqualTo(String value) {
            addCriterion("send_name <>", value, "sendName");
            return (Criteria) this;
        }

        public Criteria andSendNameGreaterThan(String value) {
            addCriterion("send_name >", value, "sendName");
            return (Criteria) this;
        }

        public Criteria andSendNameGreaterThanOrEqualTo(String value) {
            addCriterion("send_name >=", value, "sendName");
            return (Criteria) this;
        }

        public Criteria andSendNameLessThan(String value) {
            addCriterion("send_name <", value, "sendName");
            return (Criteria) this;
        }

        public Criteria andSendNameLessThanOrEqualTo(String value) {
            addCriterion("send_name <=", value, "sendName");
            return (Criteria) this;
        }

        public Criteria andSendNameLike(String value) {
            addCriterion("send_name like", value, "sendName");
            return (Criteria) this;
        }

        public Criteria andSendNameNotLike(String value) {
            addCriterion("send_name not like", value, "sendName");
            return (Criteria) this;
        }

        public Criteria andSendNameIn(List<String> values) {
            addCriterion("send_name in", values, "sendName");
            return (Criteria) this;
        }

        public Criteria andSendNameNotIn(List<String> values) {
            addCriterion("send_name not in", values, "sendName");
            return (Criteria) this;
        }

        public Criteria andSendNameBetween(String value1, String value2) {
            addCriterion("send_name between", value1, value2, "sendName");
            return (Criteria) this;
        }

        public Criteria andSendNameNotBetween(String value1, String value2) {
            addCriterion("send_name not between", value1, value2, "sendName");
            return (Criteria) this;
        }

        public Criteria andWishingIsNull() {
            addCriterion("wishing is null");
            return (Criteria) this;
        }

        public Criteria andWishingIsNotNull() {
            addCriterion("wishing is not null");
            return (Criteria) this;
        }

        public Criteria andWishingEqualTo(String value) {
            addCriterion("wishing =", value, "wishing");
            return (Criteria) this;
        }

        public Criteria andWishingNotEqualTo(String value) {
            addCriterion("wishing <>", value, "wishing");
            return (Criteria) this;
        }

        public Criteria andWishingGreaterThan(String value) {
            addCriterion("wishing >", value, "wishing");
            return (Criteria) this;
        }

        public Criteria andWishingGreaterThanOrEqualTo(String value) {
            addCriterion("wishing >=", value, "wishing");
            return (Criteria) this;
        }

        public Criteria andWishingLessThan(String value) {
            addCriterion("wishing <", value, "wishing");
            return (Criteria) this;
        }

        public Criteria andWishingLessThanOrEqualTo(String value) {
            addCriterion("wishing <=", value, "wishing");
            return (Criteria) this;
        }

        public Criteria andWishingLike(String value) {
            addCriterion("wishing like", value, "wishing");
            return (Criteria) this;
        }

        public Criteria andWishingNotLike(String value) {
            addCriterion("wishing not like", value, "wishing");
            return (Criteria) this;
        }

        public Criteria andWishingIn(List<String> values) {
            addCriterion("wishing in", values, "wishing");
            return (Criteria) this;
        }

        public Criteria andWishingNotIn(List<String> values) {
            addCriterion("wishing not in", values, "wishing");
            return (Criteria) this;
        }

        public Criteria andWishingBetween(String value1, String value2) {
            addCriterion("wishing between", value1, value2, "wishing");
            return (Criteria) this;
        }

        public Criteria andWishingNotBetween(String value1, String value2) {
            addCriterion("wishing not between", value1, value2, "wishing");
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

        public Criteria andIsRandomIsNull() {
            addCriterion("is_random is null");
            return (Criteria) this;
        }

        public Criteria andIsRandomIsNotNull() {
            addCriterion("is_random is not null");
            return (Criteria) this;
        }

        public Criteria andIsRandomEqualTo(Integer value) {
            addCriterion("is_random =", value, "isRandom");
            return (Criteria) this;
        }

        public Criteria andIsRandomNotEqualTo(Integer value) {
            addCriterion("is_random <>", value, "isRandom");
            return (Criteria) this;
        }

        public Criteria andIsRandomGreaterThan(Integer value) {
            addCriterion("is_random >", value, "isRandom");
            return (Criteria) this;
        }

        public Criteria andIsRandomGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_random >=", value, "isRandom");
            return (Criteria) this;
        }

        public Criteria andIsRandomLessThan(Integer value) {
            addCriterion("is_random <", value, "isRandom");
            return (Criteria) this;
        }

        public Criteria andIsRandomLessThanOrEqualTo(Integer value) {
            addCriterion("is_random <=", value, "isRandom");
            return (Criteria) this;
        }

        public Criteria andIsRandomIn(List<Integer> values) {
            addCriterion("is_random in", values, "isRandom");
            return (Criteria) this;
        }

        public Criteria andIsRandomNotIn(List<Integer> values) {
            addCriterion("is_random not in", values, "isRandom");
            return (Criteria) this;
        }

        public Criteria andIsRandomBetween(Integer value1, Integer value2) {
            addCriterion("is_random between", value1, value2, "isRandom");
            return (Criteria) this;
        }

        public Criteria andIsRandomNotBetween(Integer value1, Integer value2) {
            addCriterion("is_random not between", value1, value2, "isRandom");
            return (Criteria) this;
        }

        public Criteria andQuotaLimitUpIsNull() {
            addCriterion("quota_limit_up is null");
            return (Criteria) this;
        }

        public Criteria andQuotaLimitUpIsNotNull() {
            addCriterion("quota_limit_up is not null");
            return (Criteria) this;
        }

        public Criteria andQuotaLimitUpEqualTo(Integer value) {
            addCriterion("quota_limit_up =", value, "quotaLimitUp");
            return (Criteria) this;
        }

        public Criteria andQuotaLimitUpNotEqualTo(Integer value) {
            addCriterion("quota_limit_up <>", value, "quotaLimitUp");
            return (Criteria) this;
        }

        public Criteria andQuotaLimitUpGreaterThan(Integer value) {
            addCriterion("quota_limit_up >", value, "quotaLimitUp");
            return (Criteria) this;
        }

        public Criteria andQuotaLimitUpGreaterThanOrEqualTo(Integer value) {
            addCriterion("quota_limit_up >=", value, "quotaLimitUp");
            return (Criteria) this;
        }

        public Criteria andQuotaLimitUpLessThan(Integer value) {
            addCriterion("quota_limit_up <", value, "quotaLimitUp");
            return (Criteria) this;
        }

        public Criteria andQuotaLimitUpLessThanOrEqualTo(Integer value) {
            addCriterion("quota_limit_up <=", value, "quotaLimitUp");
            return (Criteria) this;
        }

        public Criteria andQuotaLimitUpIn(List<Integer> values) {
            addCriterion("quota_limit_up in", values, "quotaLimitUp");
            return (Criteria) this;
        }

        public Criteria andQuotaLimitUpNotIn(List<Integer> values) {
            addCriterion("quota_limit_up not in", values, "quotaLimitUp");
            return (Criteria) this;
        }

        public Criteria andQuotaLimitUpBetween(Integer value1, Integer value2) {
            addCriterion("quota_limit_up between", value1, value2, "quotaLimitUp");
            return (Criteria) this;
        }

        public Criteria andQuotaLimitUpNotBetween(Integer value1, Integer value2) {
            addCriterion("quota_limit_up not between", value1, value2, "quotaLimitUp");
            return (Criteria) this;
        }

        public Criteria andQuotaLimitDownIsNull() {
            addCriterion("quota_limit_down is null");
            return (Criteria) this;
        }

        public Criteria andQuotaLimitDownIsNotNull() {
            addCriterion("quota_limit_down is not null");
            return (Criteria) this;
        }

        public Criteria andQuotaLimitDownEqualTo(Integer value) {
            addCriterion("quota_limit_down =", value, "quotaLimitDown");
            return (Criteria) this;
        }

        public Criteria andQuotaLimitDownNotEqualTo(Integer value) {
            addCriterion("quota_limit_down <>", value, "quotaLimitDown");
            return (Criteria) this;
        }

        public Criteria andQuotaLimitDownGreaterThan(Integer value) {
            addCriterion("quota_limit_down >", value, "quotaLimitDown");
            return (Criteria) this;
        }

        public Criteria andQuotaLimitDownGreaterThanOrEqualTo(Integer value) {
            addCriterion("quota_limit_down >=", value, "quotaLimitDown");
            return (Criteria) this;
        }

        public Criteria andQuotaLimitDownLessThan(Integer value) {
            addCriterion("quota_limit_down <", value, "quotaLimitDown");
            return (Criteria) this;
        }

        public Criteria andQuotaLimitDownLessThanOrEqualTo(Integer value) {
            addCriterion("quota_limit_down <=", value, "quotaLimitDown");
            return (Criteria) this;
        }

        public Criteria andQuotaLimitDownIn(List<Integer> values) {
            addCriterion("quota_limit_down in", values, "quotaLimitDown");
            return (Criteria) this;
        }

        public Criteria andQuotaLimitDownNotIn(List<Integer> values) {
            addCriterion("quota_limit_down not in", values, "quotaLimitDown");
            return (Criteria) this;
        }

        public Criteria andQuotaLimitDownBetween(Integer value1, Integer value2) {
            addCriterion("quota_limit_down between", value1, value2, "quotaLimitDown");
            return (Criteria) this;
        }

        public Criteria andQuotaLimitDownNotBetween(Integer value1, Integer value2) {
            addCriterion("quota_limit_down not between", value1, value2, "quotaLimitDown");
            return (Criteria) this;
        }

        public Criteria andReceiveLimitIsNull() {
            addCriterion("receive_limit is null");
            return (Criteria) this;
        }

        public Criteria andReceiveLimitIsNotNull() {
            addCriterion("receive_limit is not null");
            return (Criteria) this;
        }

        public Criteria andReceiveLimitEqualTo(Integer value) {
            addCriterion("receive_limit =", value, "receiveLimit");
            return (Criteria) this;
        }

        public Criteria andReceiveLimitNotEqualTo(Integer value) {
            addCriterion("receive_limit <>", value, "receiveLimit");
            return (Criteria) this;
        }

        public Criteria andReceiveLimitGreaterThan(Integer value) {
            addCriterion("receive_limit >", value, "receiveLimit");
            return (Criteria) this;
        }

        public Criteria andReceiveLimitGreaterThanOrEqualTo(Integer value) {
            addCriterion("receive_limit >=", value, "receiveLimit");
            return (Criteria) this;
        }

        public Criteria andReceiveLimitLessThan(Integer value) {
            addCriterion("receive_limit <", value, "receiveLimit");
            return (Criteria) this;
        }

        public Criteria andReceiveLimitLessThanOrEqualTo(Integer value) {
            addCriterion("receive_limit <=", value, "receiveLimit");
            return (Criteria) this;
        }

        public Criteria andReceiveLimitIn(List<Integer> values) {
            addCriterion("receive_limit in", values, "receiveLimit");
            return (Criteria) this;
        }

        public Criteria andReceiveLimitNotIn(List<Integer> values) {
            addCriterion("receive_limit not in", values, "receiveLimit");
            return (Criteria) this;
        }

        public Criteria andReceiveLimitBetween(Integer value1, Integer value2) {
            addCriterion("receive_limit between", value1, value2, "receiveLimit");
            return (Criteria) this;
        }

        public Criteria andReceiveLimitNotBetween(Integer value1, Integer value2) {
            addCriterion("receive_limit not between", value1, value2, "receiveLimit");
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