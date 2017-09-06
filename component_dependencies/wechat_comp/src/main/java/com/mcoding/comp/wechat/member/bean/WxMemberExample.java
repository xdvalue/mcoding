package com.mcoding.comp.wechat.member.bean;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mcoding.base.core.IExample;
import com.mcoding.base.core.PageView;
import com.mcoding.base.utils.json.JsonUtilsForMcoding;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WxMemberExample implements IExample<WxMember> {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected PageView<WxMember> pageView;

    public WxMemberExample() {
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
    public PageView<WxMember> getPageView() {
        return pageView;
    }

    @Override
    public void setPageView(PageView<WxMember> pageView) {
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

        public Criteria andWxOpenidIsNull() {
            addCriterion("wx_openid is null");
            return (Criteria) this;
        }

        public Criteria andWxOpenidIsNotNull() {
            addCriterion("wx_openid is not null");
            return (Criteria) this;
        }

        public Criteria andWxOpenidEqualTo(String value) {
            addCriterion("wx_openid =", value, "wxOpenid");
            return (Criteria) this;
        }

        public Criteria andWxOpenidNotEqualTo(String value) {
            addCriterion("wx_openid <>", value, "wxOpenid");
            return (Criteria) this;
        }

        public Criteria andWxOpenidGreaterThan(String value) {
            addCriterion("wx_openid >", value, "wxOpenid");
            return (Criteria) this;
        }

        public Criteria andWxOpenidGreaterThanOrEqualTo(String value) {
            addCriterion("wx_openid >=", value, "wxOpenid");
            return (Criteria) this;
        }

        public Criteria andWxOpenidLessThan(String value) {
            addCriterion("wx_openid <", value, "wxOpenid");
            return (Criteria) this;
        }

        public Criteria andWxOpenidLessThanOrEqualTo(String value) {
            addCriterion("wx_openid <=", value, "wxOpenid");
            return (Criteria) this;
        }

        public Criteria andWxOpenidLike(String value) {
            addCriterion("wx_openid like", value, "wxOpenid");
            return (Criteria) this;
        }

        public Criteria andWxOpenidNotLike(String value) {
            addCriterion("wx_openid not like", value, "wxOpenid");
            return (Criteria) this;
        }

        public Criteria andWxOpenidIn(List<String> values) {
            addCriterion("wx_openid in", values, "wxOpenid");
            return (Criteria) this;
        }

        public Criteria andWxOpenidNotIn(List<String> values) {
            addCriterion("wx_openid not in", values, "wxOpenid");
            return (Criteria) this;
        }

        public Criteria andWxOpenidBetween(String value1, String value2) {
            addCriterion("wx_openid between", value1, value2, "wxOpenid");
            return (Criteria) this;
        }

        public Criteria andWxOpenidNotBetween(String value1, String value2) {
            addCriterion("wx_openid not between", value1, value2, "wxOpenid");
            return (Criteria) this;
        }

        public Criteria andWxNicknameIsNull() {
            addCriterion("wx_nickname is null");
            return (Criteria) this;
        }

        public Criteria andWxNicknameIsNotNull() {
            addCriterion("wx_nickname is not null");
            return (Criteria) this;
        }

        public Criteria andWxNicknameEqualTo(String value) {
            addCriterion("wx_nickname =", value, "wxNickname");
            return (Criteria) this;
        }

        public Criteria andWxNicknameNotEqualTo(String value) {
            addCriterion("wx_nickname <>", value, "wxNickname");
            return (Criteria) this;
        }

        public Criteria andWxNicknameGreaterThan(String value) {
            addCriterion("wx_nickname >", value, "wxNickname");
            return (Criteria) this;
        }

        public Criteria andWxNicknameGreaterThanOrEqualTo(String value) {
            addCriterion("wx_nickname >=", value, "wxNickname");
            return (Criteria) this;
        }

        public Criteria andWxNicknameLessThan(String value) {
            addCriterion("wx_nickname <", value, "wxNickname");
            return (Criteria) this;
        }

        public Criteria andWxNicknameLessThanOrEqualTo(String value) {
            addCriterion("wx_nickname <=", value, "wxNickname");
            return (Criteria) this;
        }

        public Criteria andWxNicknameLike(String value) {
            addCriterion("wx_nickname like", value, "wxNickname");
            return (Criteria) this;
        }

        public Criteria andWxNicknameNotLike(String value) {
            addCriterion("wx_nickname not like", value, "wxNickname");
            return (Criteria) this;
        }

        public Criteria andWxNicknameIn(List<String> values) {
            addCriterion("wx_nickname in", values, "wxNickname");
            return (Criteria) this;
        }

        public Criteria andWxNicknameNotIn(List<String> values) {
            addCriterion("wx_nickname not in", values, "wxNickname");
            return (Criteria) this;
        }

        public Criteria andWxNicknameBetween(String value1, String value2) {
            addCriterion("wx_nickname between", value1, value2, "wxNickname");
            return (Criteria) this;
        }

        public Criteria andWxNicknameNotBetween(String value1, String value2) {
            addCriterion("wx_nickname not between", value1, value2, "wxNickname");
            return (Criteria) this;
        }

        public Criteria andWxSexIsNull() {
            addCriterion("wx_sex is null");
            return (Criteria) this;
        }

        public Criteria andWxSexIsNotNull() {
            addCriterion("wx_sex is not null");
            return (Criteria) this;
        }

        public Criteria andWxSexEqualTo(Integer value) {
            addCriterion("wx_sex =", value, "wxSex");
            return (Criteria) this;
        }

        public Criteria andWxSexNotEqualTo(Integer value) {
            addCriterion("wx_sex <>", value, "wxSex");
            return (Criteria) this;
        }

        public Criteria andWxSexGreaterThan(Integer value) {
            addCriterion("wx_sex >", value, "wxSex");
            return (Criteria) this;
        }

        public Criteria andWxSexGreaterThanOrEqualTo(Integer value) {
            addCriterion("wx_sex >=", value, "wxSex");
            return (Criteria) this;
        }

        public Criteria andWxSexLessThan(Integer value) {
            addCriterion("wx_sex <", value, "wxSex");
            return (Criteria) this;
        }

        public Criteria andWxSexLessThanOrEqualTo(Integer value) {
            addCriterion("wx_sex <=", value, "wxSex");
            return (Criteria) this;
        }

        public Criteria andWxSexIn(List<Integer> values) {
            addCriterion("wx_sex in", values, "wxSex");
            return (Criteria) this;
        }

        public Criteria andWxSexNotIn(List<Integer> values) {
            addCriterion("wx_sex not in", values, "wxSex");
            return (Criteria) this;
        }

        public Criteria andWxSexBetween(Integer value1, Integer value2) {
            addCriterion("wx_sex between", value1, value2, "wxSex");
            return (Criteria) this;
        }

        public Criteria andWxSexNotBetween(Integer value1, Integer value2) {
            addCriterion("wx_sex not between", value1, value2, "wxSex");
            return (Criteria) this;
        }

        public Criteria andWxCityIsNull() {
            addCriterion("wx_city is null");
            return (Criteria) this;
        }

        public Criteria andWxCityIsNotNull() {
            addCriterion("wx_city is not null");
            return (Criteria) this;
        }

        public Criteria andWxCityEqualTo(String value) {
            addCriterion("wx_city =", value, "wxCity");
            return (Criteria) this;
        }

        public Criteria andWxCityNotEqualTo(String value) {
            addCriterion("wx_city <>", value, "wxCity");
            return (Criteria) this;
        }

        public Criteria andWxCityGreaterThan(String value) {
            addCriterion("wx_city >", value, "wxCity");
            return (Criteria) this;
        }

        public Criteria andWxCityGreaterThanOrEqualTo(String value) {
            addCriterion("wx_city >=", value, "wxCity");
            return (Criteria) this;
        }

        public Criteria andWxCityLessThan(String value) {
            addCriterion("wx_city <", value, "wxCity");
            return (Criteria) this;
        }

        public Criteria andWxCityLessThanOrEqualTo(String value) {
            addCriterion("wx_city <=", value, "wxCity");
            return (Criteria) this;
        }

        public Criteria andWxCityLike(String value) {
            addCriterion("wx_city like", value, "wxCity");
            return (Criteria) this;
        }

        public Criteria andWxCityNotLike(String value) {
            addCriterion("wx_city not like", value, "wxCity");
            return (Criteria) this;
        }

        public Criteria andWxCityIn(List<String> values) {
            addCriterion("wx_city in", values, "wxCity");
            return (Criteria) this;
        }

        public Criteria andWxCityNotIn(List<String> values) {
            addCriterion("wx_city not in", values, "wxCity");
            return (Criteria) this;
        }

        public Criteria andWxCityBetween(String value1, String value2) {
            addCriterion("wx_city between", value1, value2, "wxCity");
            return (Criteria) this;
        }

        public Criteria andWxCityNotBetween(String value1, String value2) {
            addCriterion("wx_city not between", value1, value2, "wxCity");
            return (Criteria) this;
        }

        public Criteria andWxCountryIsNull() {
            addCriterion("wx_country is null");
            return (Criteria) this;
        }

        public Criteria andWxCountryIsNotNull() {
            addCriterion("wx_country is not null");
            return (Criteria) this;
        }

        public Criteria andWxCountryEqualTo(String value) {
            addCriterion("wx_country =", value, "wxCountry");
            return (Criteria) this;
        }

        public Criteria andWxCountryNotEqualTo(String value) {
            addCriterion("wx_country <>", value, "wxCountry");
            return (Criteria) this;
        }

        public Criteria andWxCountryGreaterThan(String value) {
            addCriterion("wx_country >", value, "wxCountry");
            return (Criteria) this;
        }

        public Criteria andWxCountryGreaterThanOrEqualTo(String value) {
            addCriterion("wx_country >=", value, "wxCountry");
            return (Criteria) this;
        }

        public Criteria andWxCountryLessThan(String value) {
            addCriterion("wx_country <", value, "wxCountry");
            return (Criteria) this;
        }

        public Criteria andWxCountryLessThanOrEqualTo(String value) {
            addCriterion("wx_country <=", value, "wxCountry");
            return (Criteria) this;
        }

        public Criteria andWxCountryLike(String value) {
            addCriterion("wx_country like", value, "wxCountry");
            return (Criteria) this;
        }

        public Criteria andWxCountryNotLike(String value) {
            addCriterion("wx_country not like", value, "wxCountry");
            return (Criteria) this;
        }

        public Criteria andWxCountryIn(List<String> values) {
            addCriterion("wx_country in", values, "wxCountry");
            return (Criteria) this;
        }

        public Criteria andWxCountryNotIn(List<String> values) {
            addCriterion("wx_country not in", values, "wxCountry");
            return (Criteria) this;
        }

        public Criteria andWxCountryBetween(String value1, String value2) {
            addCriterion("wx_country between", value1, value2, "wxCountry");
            return (Criteria) this;
        }

        public Criteria andWxCountryNotBetween(String value1, String value2) {
            addCriterion("wx_country not between", value1, value2, "wxCountry");
            return (Criteria) this;
        }

        public Criteria andWxProvinceIsNull() {
            addCriterion("wx_province is null");
            return (Criteria) this;
        }

        public Criteria andWxProvinceIsNotNull() {
            addCriterion("wx_province is not null");
            return (Criteria) this;
        }

        public Criteria andWxProvinceEqualTo(String value) {
            addCriterion("wx_province =", value, "wxProvince");
            return (Criteria) this;
        }

        public Criteria andWxProvinceNotEqualTo(String value) {
            addCriterion("wx_province <>", value, "wxProvince");
            return (Criteria) this;
        }

        public Criteria andWxProvinceGreaterThan(String value) {
            addCriterion("wx_province >", value, "wxProvince");
            return (Criteria) this;
        }

        public Criteria andWxProvinceGreaterThanOrEqualTo(String value) {
            addCriterion("wx_province >=", value, "wxProvince");
            return (Criteria) this;
        }

        public Criteria andWxProvinceLessThan(String value) {
            addCriterion("wx_province <", value, "wxProvince");
            return (Criteria) this;
        }

        public Criteria andWxProvinceLessThanOrEqualTo(String value) {
            addCriterion("wx_province <=", value, "wxProvince");
            return (Criteria) this;
        }

        public Criteria andWxProvinceLike(String value) {
            addCriterion("wx_province like", value, "wxProvince");
            return (Criteria) this;
        }

        public Criteria andWxProvinceNotLike(String value) {
            addCriterion("wx_province not like", value, "wxProvince");
            return (Criteria) this;
        }

        public Criteria andWxProvinceIn(List<String> values) {
            addCriterion("wx_province in", values, "wxProvince");
            return (Criteria) this;
        }

        public Criteria andWxProvinceNotIn(List<String> values) {
            addCriterion("wx_province not in", values, "wxProvince");
            return (Criteria) this;
        }

        public Criteria andWxProvinceBetween(String value1, String value2) {
            addCriterion("wx_province between", value1, value2, "wxProvince");
            return (Criteria) this;
        }

        public Criteria andWxProvinceNotBetween(String value1, String value2) {
            addCriterion("wx_province not between", value1, value2, "wxProvince");
            return (Criteria) this;
        }

        public Criteria andWxLanguageIsNull() {
            addCriterion("wx_language is null");
            return (Criteria) this;
        }

        public Criteria andWxLanguageIsNotNull() {
            addCriterion("wx_language is not null");
            return (Criteria) this;
        }

        public Criteria andWxLanguageEqualTo(String value) {
            addCriterion("wx_language =", value, "wxLanguage");
            return (Criteria) this;
        }

        public Criteria andWxLanguageNotEqualTo(String value) {
            addCriterion("wx_language <>", value, "wxLanguage");
            return (Criteria) this;
        }

        public Criteria andWxLanguageGreaterThan(String value) {
            addCriterion("wx_language >", value, "wxLanguage");
            return (Criteria) this;
        }

        public Criteria andWxLanguageGreaterThanOrEqualTo(String value) {
            addCriterion("wx_language >=", value, "wxLanguage");
            return (Criteria) this;
        }

        public Criteria andWxLanguageLessThan(String value) {
            addCriterion("wx_language <", value, "wxLanguage");
            return (Criteria) this;
        }

        public Criteria andWxLanguageLessThanOrEqualTo(String value) {
            addCriterion("wx_language <=", value, "wxLanguage");
            return (Criteria) this;
        }

        public Criteria andWxLanguageLike(String value) {
            addCriterion("wx_language like", value, "wxLanguage");
            return (Criteria) this;
        }

        public Criteria andWxLanguageNotLike(String value) {
            addCriterion("wx_language not like", value, "wxLanguage");
            return (Criteria) this;
        }

        public Criteria andWxLanguageIn(List<String> values) {
            addCriterion("wx_language in", values, "wxLanguage");
            return (Criteria) this;
        }

        public Criteria andWxLanguageNotIn(List<String> values) {
            addCriterion("wx_language not in", values, "wxLanguage");
            return (Criteria) this;
        }

        public Criteria andWxLanguageBetween(String value1, String value2) {
            addCriterion("wx_language between", value1, value2, "wxLanguage");
            return (Criteria) this;
        }

        public Criteria andWxLanguageNotBetween(String value1, String value2) {
            addCriterion("wx_language not between", value1, value2, "wxLanguage");
            return (Criteria) this;
        }

        public Criteria andWxHeadimgurlIsNull() {
            addCriterion("wx_headimgurl is null");
            return (Criteria) this;
        }

        public Criteria andWxHeadimgurlIsNotNull() {
            addCriterion("wx_headimgurl is not null");
            return (Criteria) this;
        }

        public Criteria andWxHeadimgurlEqualTo(String value) {
            addCriterion("wx_headimgurl =", value, "wxHeadimgurl");
            return (Criteria) this;
        }

        public Criteria andWxHeadimgurlNotEqualTo(String value) {
            addCriterion("wx_headimgurl <>", value, "wxHeadimgurl");
            return (Criteria) this;
        }

        public Criteria andWxHeadimgurlGreaterThan(String value) {
            addCriterion("wx_headimgurl >", value, "wxHeadimgurl");
            return (Criteria) this;
        }

        public Criteria andWxHeadimgurlGreaterThanOrEqualTo(String value) {
            addCriterion("wx_headimgurl >=", value, "wxHeadimgurl");
            return (Criteria) this;
        }

        public Criteria andWxHeadimgurlLessThan(String value) {
            addCriterion("wx_headimgurl <", value, "wxHeadimgurl");
            return (Criteria) this;
        }

        public Criteria andWxHeadimgurlLessThanOrEqualTo(String value) {
            addCriterion("wx_headimgurl <=", value, "wxHeadimgurl");
            return (Criteria) this;
        }

        public Criteria andWxHeadimgurlLike(String value) {
            addCriterion("wx_headimgurl like", value, "wxHeadimgurl");
            return (Criteria) this;
        }

        public Criteria andWxHeadimgurlNotLike(String value) {
            addCriterion("wx_headimgurl not like", value, "wxHeadimgurl");
            return (Criteria) this;
        }

        public Criteria andWxHeadimgurlIn(List<String> values) {
            addCriterion("wx_headimgurl in", values, "wxHeadimgurl");
            return (Criteria) this;
        }

        public Criteria andWxHeadimgurlNotIn(List<String> values) {
            addCriterion("wx_headimgurl not in", values, "wxHeadimgurl");
            return (Criteria) this;
        }

        public Criteria andWxHeadimgurlBetween(String value1, String value2) {
            addCriterion("wx_headimgurl between", value1, value2, "wxHeadimgurl");
            return (Criteria) this;
        }

        public Criteria andWxHeadimgurlNotBetween(String value1, String value2) {
            addCriterion("wx_headimgurl not between", value1, value2, "wxHeadimgurl");
            return (Criteria) this;
        }

        public Criteria andWxSubscribeIsNull() {
            addCriterion("wx_subscribe is null");
            return (Criteria) this;
        }

        public Criteria andWxSubscribeIsNotNull() {
            addCriterion("wx_subscribe is not null");
            return (Criteria) this;
        }

        public Criteria andWxSubscribeEqualTo(Integer value) {
            addCriterion("wx_subscribe =", value, "wxSubscribe");
            return (Criteria) this;
        }

        public Criteria andWxSubscribeNotEqualTo(Integer value) {
            addCriterion("wx_subscribe <>", value, "wxSubscribe");
            return (Criteria) this;
        }

        public Criteria andWxSubscribeGreaterThan(Integer value) {
            addCriterion("wx_subscribe >", value, "wxSubscribe");
            return (Criteria) this;
        }

        public Criteria andWxSubscribeGreaterThanOrEqualTo(Integer value) {
            addCriterion("wx_subscribe >=", value, "wxSubscribe");
            return (Criteria) this;
        }

        public Criteria andWxSubscribeLessThan(Integer value) {
            addCriterion("wx_subscribe <", value, "wxSubscribe");
            return (Criteria) this;
        }

        public Criteria andWxSubscribeLessThanOrEqualTo(Integer value) {
            addCriterion("wx_subscribe <=", value, "wxSubscribe");
            return (Criteria) this;
        }

        public Criteria andWxSubscribeIn(List<Integer> values) {
            addCriterion("wx_subscribe in", values, "wxSubscribe");
            return (Criteria) this;
        }

        public Criteria andWxSubscribeNotIn(List<Integer> values) {
            addCriterion("wx_subscribe not in", values, "wxSubscribe");
            return (Criteria) this;
        }

        public Criteria andWxSubscribeBetween(Integer value1, Integer value2) {
            addCriterion("wx_subscribe between", value1, value2, "wxSubscribe");
            return (Criteria) this;
        }

        public Criteria andWxSubscribeNotBetween(Integer value1, Integer value2) {
            addCriterion("wx_subscribe not between", value1, value2, "wxSubscribe");
            return (Criteria) this;
        }

        public Criteria andWxSubscribeTimeIsNull() {
            addCriterion("wx_subscribe_time is null");
            return (Criteria) this;
        }

        public Criteria andWxSubscribeTimeIsNotNull() {
            addCriterion("wx_subscribe_time is not null");
            return (Criteria) this;
        }

        public Criteria andWxSubscribeTimeEqualTo(Date value) {
            addCriterion("wx_subscribe_time =", value, "wxSubscribeTime");
            return (Criteria) this;
        }

        public Criteria andWxSubscribeTimeNotEqualTo(Date value) {
            addCriterion("wx_subscribe_time <>", value, "wxSubscribeTime");
            return (Criteria) this;
        }

        public Criteria andWxSubscribeTimeGreaterThan(Date value) {
            addCriterion("wx_subscribe_time >", value, "wxSubscribeTime");
            return (Criteria) this;
        }

        public Criteria andWxSubscribeTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("wx_subscribe_time >=", value, "wxSubscribeTime");
            return (Criteria) this;
        }

        public Criteria andWxSubscribeTimeLessThan(Date value) {
            addCriterion("wx_subscribe_time <", value, "wxSubscribeTime");
            return (Criteria) this;
        }

        public Criteria andWxSubscribeTimeLessThanOrEqualTo(Date value) {
            addCriterion("wx_subscribe_time <=", value, "wxSubscribeTime");
            return (Criteria) this;
        }

        public Criteria andWxSubscribeTimeIn(List<Date> values) {
            addCriterion("wx_subscribe_time in", values, "wxSubscribeTime");
            return (Criteria) this;
        }

        public Criteria andWxSubscribeTimeNotIn(List<Date> values) {
            addCriterion("wx_subscribe_time not in", values, "wxSubscribeTime");
            return (Criteria) this;
        }

        public Criteria andWxSubscribeTimeBetween(Date value1, Date value2) {
            addCriterion("wx_subscribe_time between", value1, value2, "wxSubscribeTime");
            return (Criteria) this;
        }

        public Criteria andWxSubscribeTimeNotBetween(Date value1, Date value2) {
            addCriterion("wx_subscribe_time not between", value1, value2, "wxSubscribeTime");
            return (Criteria) this;
        }

        public Criteria andWxUnionidIsNull() {
            addCriterion("wx_unionid is null");
            return (Criteria) this;
        }

        public Criteria andWxUnionidIsNotNull() {
            addCriterion("wx_unionid is not null");
            return (Criteria) this;
        }

        public Criteria andWxUnionidEqualTo(String value) {
            addCriterion("wx_unionid =", value, "wxUnionid");
            return (Criteria) this;
        }

        public Criteria andWxUnionidNotEqualTo(String value) {
            addCriterion("wx_unionid <>", value, "wxUnionid");
            return (Criteria) this;
        }

        public Criteria andWxUnionidGreaterThan(String value) {
            addCriterion("wx_unionid >", value, "wxUnionid");
            return (Criteria) this;
        }

        public Criteria andWxUnionidGreaterThanOrEqualTo(String value) {
            addCriterion("wx_unionid >=", value, "wxUnionid");
            return (Criteria) this;
        }

        public Criteria andWxUnionidLessThan(String value) {
            addCriterion("wx_unionid <", value, "wxUnionid");
            return (Criteria) this;
        }

        public Criteria andWxUnionidLessThanOrEqualTo(String value) {
            addCriterion("wx_unionid <=", value, "wxUnionid");
            return (Criteria) this;
        }

        public Criteria andWxUnionidLike(String value) {
            addCriterion("wx_unionid like", value, "wxUnionid");
            return (Criteria) this;
        }

        public Criteria andWxUnionidNotLike(String value) {
            addCriterion("wx_unionid not like", value, "wxUnionid");
            return (Criteria) this;
        }

        public Criteria andWxUnionidIn(List<String> values) {
            addCriterion("wx_unionid in", values, "wxUnionid");
            return (Criteria) this;
        }

        public Criteria andWxUnionidNotIn(List<String> values) {
            addCriterion("wx_unionid not in", values, "wxUnionid");
            return (Criteria) this;
        }

        public Criteria andWxUnionidBetween(String value1, String value2) {
            addCriterion("wx_unionid between", value1, value2, "wxUnionid");
            return (Criteria) this;
        }

        public Criteria andWxUnionidNotBetween(String value1, String value2) {
            addCriterion("wx_unionid not between", value1, value2, "wxUnionid");
            return (Criteria) this;
        }

        public Criteria andWxRemarkIsNull() {
            addCriterion("wx_remark is null");
            return (Criteria) this;
        }

        public Criteria andWxRemarkIsNotNull() {
            addCriterion("wx_remark is not null");
            return (Criteria) this;
        }

        public Criteria andWxRemarkEqualTo(String value) {
            addCriterion("wx_remark =", value, "wxRemark");
            return (Criteria) this;
        }

        public Criteria andWxRemarkNotEqualTo(String value) {
            addCriterion("wx_remark <>", value, "wxRemark");
            return (Criteria) this;
        }

        public Criteria andWxRemarkGreaterThan(String value) {
            addCriterion("wx_remark >", value, "wxRemark");
            return (Criteria) this;
        }

        public Criteria andWxRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("wx_remark >=", value, "wxRemark");
            return (Criteria) this;
        }

        public Criteria andWxRemarkLessThan(String value) {
            addCriterion("wx_remark <", value, "wxRemark");
            return (Criteria) this;
        }

        public Criteria andWxRemarkLessThanOrEqualTo(String value) {
            addCriterion("wx_remark <=", value, "wxRemark");
            return (Criteria) this;
        }

        public Criteria andWxRemarkLike(String value) {
            addCriterion("wx_remark like", value, "wxRemark");
            return (Criteria) this;
        }

        public Criteria andWxRemarkNotLike(String value) {
            addCriterion("wx_remark not like", value, "wxRemark");
            return (Criteria) this;
        }

        public Criteria andWxRemarkIn(List<String> values) {
            addCriterion("wx_remark in", values, "wxRemark");
            return (Criteria) this;
        }

        public Criteria andWxRemarkNotIn(List<String> values) {
            addCriterion("wx_remark not in", values, "wxRemark");
            return (Criteria) this;
        }

        public Criteria andWxRemarkBetween(String value1, String value2) {
            addCriterion("wx_remark between", value1, value2, "wxRemark");
            return (Criteria) this;
        }

        public Criteria andWxRemarkNotBetween(String value1, String value2) {
            addCriterion("wx_remark not between", value1, value2, "wxRemark");
            return (Criteria) this;
        }

        public Criteria andWxGroupidIsNull() {
            addCriterion("wx_groupid is null");
            return (Criteria) this;
        }

        public Criteria andWxGroupidIsNotNull() {
            addCriterion("wx_groupid is not null");
            return (Criteria) this;
        }

        public Criteria andWxGroupidEqualTo(Integer value) {
            addCriterion("wx_groupid =", value, "wxGroupid");
            return (Criteria) this;
        }

        public Criteria andWxGroupidNotEqualTo(Integer value) {
            addCriterion("wx_groupid <>", value, "wxGroupid");
            return (Criteria) this;
        }

        public Criteria andWxGroupidGreaterThan(Integer value) {
            addCriterion("wx_groupid >", value, "wxGroupid");
            return (Criteria) this;
        }

        public Criteria andWxGroupidGreaterThanOrEqualTo(Integer value) {
            addCriterion("wx_groupid >=", value, "wxGroupid");
            return (Criteria) this;
        }

        public Criteria andWxGroupidLessThan(Integer value) {
            addCriterion("wx_groupid <", value, "wxGroupid");
            return (Criteria) this;
        }

        public Criteria andWxGroupidLessThanOrEqualTo(Integer value) {
            addCriterion("wx_groupid <=", value, "wxGroupid");
            return (Criteria) this;
        }

        public Criteria andWxGroupidIn(List<Integer> values) {
            addCriterion("wx_groupid in", values, "wxGroupid");
            return (Criteria) this;
        }

        public Criteria andWxGroupidNotIn(List<Integer> values) {
            addCriterion("wx_groupid not in", values, "wxGroupid");
            return (Criteria) this;
        }

        public Criteria andWxGroupidBetween(Integer value1, Integer value2) {
            addCriterion("wx_groupid between", value1, value2, "wxGroupid");
            return (Criteria) this;
        }

        public Criteria andWxGroupidNotBetween(Integer value1, Integer value2) {
            addCriterion("wx_groupid not between", value1, value2, "wxGroupid");
            return (Criteria) this;
        }

        public Criteria andWxFirstSubscribeTimeIsNull() {
            addCriterion("wx_first_subscribe_time is null");
            return (Criteria) this;
        }

        public Criteria andWxFirstSubscribeTimeIsNotNull() {
            addCriterion("wx_first_subscribe_time is not null");
            return (Criteria) this;
        }

        public Criteria andWxFirstSubscribeTimeEqualTo(Date value) {
            addCriterion("wx_first_subscribe_time =", value, "wxFirstSubscribeTime");
            return (Criteria) this;
        }

        public Criteria andWxFirstSubscribeTimeNotEqualTo(Date value) {
            addCriterion("wx_first_subscribe_time <>", value, "wxFirstSubscribeTime");
            return (Criteria) this;
        }

        public Criteria andWxFirstSubscribeTimeGreaterThan(Date value) {
            addCriterion("wx_first_subscribe_time >", value, "wxFirstSubscribeTime");
            return (Criteria) this;
        }

        public Criteria andWxFirstSubscribeTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("wx_first_subscribe_time >=", value, "wxFirstSubscribeTime");
            return (Criteria) this;
        }

        public Criteria andWxFirstSubscribeTimeLessThan(Date value) {
            addCriterion("wx_first_subscribe_time <", value, "wxFirstSubscribeTime");
            return (Criteria) this;
        }

        public Criteria andWxFirstSubscribeTimeLessThanOrEqualTo(Date value) {
            addCriterion("wx_first_subscribe_time <=", value, "wxFirstSubscribeTime");
            return (Criteria) this;
        }

        public Criteria andWxFirstSubscribeTimeIn(List<Date> values) {
            addCriterion("wx_first_subscribe_time in", values, "wxFirstSubscribeTime");
            return (Criteria) this;
        }

        public Criteria andWxFirstSubscribeTimeNotIn(List<Date> values) {
            addCriterion("wx_first_subscribe_time not in", values, "wxFirstSubscribeTime");
            return (Criteria) this;
        }

        public Criteria andWxFirstSubscribeTimeBetween(Date value1, Date value2) {
            addCriterion("wx_first_subscribe_time between", value1, value2, "wxFirstSubscribeTime");
            return (Criteria) this;
        }

        public Criteria andWxFirstSubscribeTimeNotBetween(Date value1, Date value2) {
            addCriterion("wx_first_subscribe_time not between", value1, value2, "wxFirstSubscribeTime");
            return (Criteria) this;
        }

        public Criteria andWxSubscribeKeyIsNull() {
            addCriterion("wx_subscribe_key is null");
            return (Criteria) this;
        }

        public Criteria andWxSubscribeKeyIsNotNull() {
            addCriterion("wx_subscribe_key is not null");
            return (Criteria) this;
        }

        public Criteria andWxSubscribeKeyEqualTo(String value) {
            addCriterion("wx_subscribe_key =", value, "wxSubscribeKey");
            return (Criteria) this;
        }

        public Criteria andWxSubscribeKeyNotEqualTo(String value) {
            addCriterion("wx_subscribe_key <>", value, "wxSubscribeKey");
            return (Criteria) this;
        }

        public Criteria andWxSubscribeKeyGreaterThan(String value) {
            addCriterion("wx_subscribe_key >", value, "wxSubscribeKey");
            return (Criteria) this;
        }

        public Criteria andWxSubscribeKeyGreaterThanOrEqualTo(String value) {
            addCriterion("wx_subscribe_key >=", value, "wxSubscribeKey");
            return (Criteria) this;
        }

        public Criteria andWxSubscribeKeyLessThan(String value) {
            addCriterion("wx_subscribe_key <", value, "wxSubscribeKey");
            return (Criteria) this;
        }

        public Criteria andWxSubscribeKeyLessThanOrEqualTo(String value) {
            addCriterion("wx_subscribe_key <=", value, "wxSubscribeKey");
            return (Criteria) this;
        }

        public Criteria andWxSubscribeKeyLike(String value) {
            addCriterion("wx_subscribe_key like", value, "wxSubscribeKey");
            return (Criteria) this;
        }

        public Criteria andWxSubscribeKeyNotLike(String value) {
            addCriterion("wx_subscribe_key not like", value, "wxSubscribeKey");
            return (Criteria) this;
        }

        public Criteria andWxSubscribeKeyIn(List<String> values) {
            addCriterion("wx_subscribe_key in", values, "wxSubscribeKey");
            return (Criteria) this;
        }

        public Criteria andWxSubscribeKeyNotIn(List<String> values) {
            addCriterion("wx_subscribe_key not in", values, "wxSubscribeKey");
            return (Criteria) this;
        }

        public Criteria andWxSubscribeKeyBetween(String value1, String value2) {
            addCriterion("wx_subscribe_key between", value1, value2, "wxSubscribeKey");
            return (Criteria) this;
        }

        public Criteria andWxSubscribeKeyNotBetween(String value1, String value2) {
            addCriterion("wx_subscribe_key not between", value1, value2, "wxSubscribeKey");
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