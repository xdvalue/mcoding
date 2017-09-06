package com.mcoding.comp.wechat.account.bean;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mcoding.base.core.IExample;
import com.mcoding.base.core.PageView;
import com.mcoding.base.utils.json.JsonUtilsForMcoding;

public class AccountConfigExample implements IExample<AccountConfig> {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected PageView<AccountConfig> pageView;

    public AccountConfigExample() {
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
    public PageView<AccountConfig> getPageView() {
        return pageView;
    }

    @Override
    public void setPageView(PageView<AccountConfig> pageView) {
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

        public Criteria andAccountTypeIsNull() {
            addCriterion("account_type is null");
            return (Criteria) this;
        }

        public Criteria andAccountTypeIsNotNull() {
            addCriterion("account_type is not null");
            return (Criteria) this;
        }

        public Criteria andAccountTypeEqualTo(Integer value) {
            addCriterion("account_type =", value, "accountType");
            return (Criteria) this;
        }

        public Criteria andAccountTypeNotEqualTo(Integer value) {
            addCriterion("account_type <>", value, "accountType");
            return (Criteria) this;
        }

        public Criteria andAccountTypeGreaterThan(Integer value) {
            addCriterion("account_type >", value, "accountType");
            return (Criteria) this;
        }

        public Criteria andAccountTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("account_type >=", value, "accountType");
            return (Criteria) this;
        }

        public Criteria andAccountTypeLessThan(Integer value) {
            addCriterion("account_type <", value, "accountType");
            return (Criteria) this;
        }

        public Criteria andAccountTypeLessThanOrEqualTo(Integer value) {
            addCriterion("account_type <=", value, "accountType");
            return (Criteria) this;
        }

        public Criteria andAccountTypeIn(List<Integer> values) {
            addCriterion("account_type in", values, "accountType");
            return (Criteria) this;
        }

        public Criteria andAccountTypeNotIn(List<Integer> values) {
            addCriterion("account_type not in", values, "accountType");
            return (Criteria) this;
        }

        public Criteria andAccountTypeBetween(Integer value1, Integer value2) {
            addCriterion("account_type between", value1, value2, "accountType");
            return (Criteria) this;
        }

        public Criteria andAccountTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("account_type not between", value1, value2, "accountType");
            return (Criteria) this;
        }

        public Criteria andCodeIsNull() {
            addCriterion("code is null");
            return (Criteria) this;
        }

        public Criteria andCodeIsNotNull() {
            addCriterion("code is not null");
            return (Criteria) this;
        }

        public Criteria andCodeEqualTo(String value) {
            addCriterion("code =", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotEqualTo(String value) {
            addCriterion("code <>", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeGreaterThan(String value) {
            addCriterion("code >", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeGreaterThanOrEqualTo(String value) {
            addCriterion("code >=", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLessThan(String value) {
            addCriterion("code <", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLessThanOrEqualTo(String value) {
            addCriterion("code <=", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLike(String value) {
            addCriterion("code like", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotLike(String value) {
            addCriterion("code not like", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeIn(List<String> values) {
            addCriterion("code in", values, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotIn(List<String> values) {
            addCriterion("code not in", values, "code");
            return (Criteria) this;
        }

        public Criteria andCodeBetween(String value1, String value2) {
            addCriterion("code between", value1, value2, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotBetween(String value1, String value2) {
            addCriterion("code not between", value1, value2, "code");
            return (Criteria) this;
        }

        public Criteria andOriginIdIsNull() {
            addCriterion("origin_id is null");
            return (Criteria) this;
        }

        public Criteria andOriginIdIsNotNull() {
            addCriterion("origin_id is not null");
            return (Criteria) this;
        }

        public Criteria andOriginIdEqualTo(String value) {
            addCriterion("origin_id =", value, "originId");
            return (Criteria) this;
        }

        public Criteria andOriginIdNotEqualTo(String value) {
            addCriterion("origin_id <>", value, "originId");
            return (Criteria) this;
        }

        public Criteria andOriginIdGreaterThan(String value) {
            addCriterion("origin_id >", value, "originId");
            return (Criteria) this;
        }

        public Criteria andOriginIdGreaterThanOrEqualTo(String value) {
            addCriterion("origin_id >=", value, "originId");
            return (Criteria) this;
        }

        public Criteria andOriginIdLessThan(String value) {
            addCriterion("origin_id <", value, "originId");
            return (Criteria) this;
        }

        public Criteria andOriginIdLessThanOrEqualTo(String value) {
            addCriterion("origin_id <=", value, "originId");
            return (Criteria) this;
        }

        public Criteria andOriginIdLike(String value) {
            addCriterion("origin_id like", value, "originId");
            return (Criteria) this;
        }

        public Criteria andOriginIdNotLike(String value) {
            addCriterion("origin_id not like", value, "originId");
            return (Criteria) this;
        }

        public Criteria andOriginIdIn(List<String> values) {
            addCriterion("origin_id in", values, "originId");
            return (Criteria) this;
        }

        public Criteria andOriginIdNotIn(List<String> values) {
            addCriterion("origin_id not in", values, "originId");
            return (Criteria) this;
        }

        public Criteria andOriginIdBetween(String value1, String value2) {
            addCriterion("origin_id between", value1, value2, "originId");
            return (Criteria) this;
        }

        public Criteria andOriginIdNotBetween(String value1, String value2) {
            addCriterion("origin_id not between", value1, value2, "originId");
            return (Criteria) this;
        }

        public Criteria andAppIdIsNull() {
            addCriterion("app_id is null");
            return (Criteria) this;
        }

        public Criteria andAppIdIsNotNull() {
            addCriterion("app_id is not null");
            return (Criteria) this;
        }

        public Criteria andAppIdEqualTo(String value) {
            addCriterion("app_id =", value, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdNotEqualTo(String value) {
            addCriterion("app_id <>", value, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdGreaterThan(String value) {
            addCriterion("app_id >", value, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdGreaterThanOrEqualTo(String value) {
            addCriterion("app_id >=", value, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdLessThan(String value) {
            addCriterion("app_id <", value, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdLessThanOrEqualTo(String value) {
            addCriterion("app_id <=", value, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdLike(String value) {
            addCriterion("app_id like", value, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdNotLike(String value) {
            addCriterion("app_id not like", value, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdIn(List<String> values) {
            addCriterion("app_id in", values, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdNotIn(List<String> values) {
            addCriterion("app_id not in", values, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdBetween(String value1, String value2) {
            addCriterion("app_id between", value1, value2, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdNotBetween(String value1, String value2) {
            addCriterion("app_id not between", value1, value2, "appId");
            return (Criteria) this;
        }

        public Criteria andAppSecretIsNull() {
            addCriterion("app_secret is null");
            return (Criteria) this;
        }

        public Criteria andAppSecretIsNotNull() {
            addCriterion("app_secret is not null");
            return (Criteria) this;
        }

        public Criteria andAppSecretEqualTo(String value) {
            addCriterion("app_secret =", value, "appSecret");
            return (Criteria) this;
        }

        public Criteria andAppSecretNotEqualTo(String value) {
            addCriterion("app_secret <>", value, "appSecret");
            return (Criteria) this;
        }

        public Criteria andAppSecretGreaterThan(String value) {
            addCriterion("app_secret >", value, "appSecret");
            return (Criteria) this;
        }

        public Criteria andAppSecretGreaterThanOrEqualTo(String value) {
            addCriterion("app_secret >=", value, "appSecret");
            return (Criteria) this;
        }

        public Criteria andAppSecretLessThan(String value) {
            addCriterion("app_secret <", value, "appSecret");
            return (Criteria) this;
        }

        public Criteria andAppSecretLessThanOrEqualTo(String value) {
            addCriterion("app_secret <=", value, "appSecret");
            return (Criteria) this;
        }

        public Criteria andAppSecretLike(String value) {
            addCriterion("app_secret like", value, "appSecret");
            return (Criteria) this;
        }

        public Criteria andAppSecretNotLike(String value) {
            addCriterion("app_secret not like", value, "appSecret");
            return (Criteria) this;
        }

        public Criteria andAppSecretIn(List<String> values) {
            addCriterion("app_secret in", values, "appSecret");
            return (Criteria) this;
        }

        public Criteria andAppSecretNotIn(List<String> values) {
            addCriterion("app_secret not in", values, "appSecret");
            return (Criteria) this;
        }

        public Criteria andAppSecretBetween(String value1, String value2) {
            addCriterion("app_secret between", value1, value2, "appSecret");
            return (Criteria) this;
        }

        public Criteria andAppSecretNotBetween(String value1, String value2) {
            addCriterion("app_secret not between", value1, value2, "appSecret");
            return (Criteria) this;
        }

        public Criteria andTokenIsNull() {
            addCriterion("token is null");
            return (Criteria) this;
        }

        public Criteria andTokenIsNotNull() {
            addCriterion("token is not null");
            return (Criteria) this;
        }

        public Criteria andTokenEqualTo(String value) {
            addCriterion("token =", value, "token");
            return (Criteria) this;
        }

        public Criteria andTokenNotEqualTo(String value) {
            addCriterion("token <>", value, "token");
            return (Criteria) this;
        }

        public Criteria andTokenGreaterThan(String value) {
            addCriterion("token >", value, "token");
            return (Criteria) this;
        }

        public Criteria andTokenGreaterThanOrEqualTo(String value) {
            addCriterion("token >=", value, "token");
            return (Criteria) this;
        }

        public Criteria andTokenLessThan(String value) {
            addCriterion("token <", value, "token");
            return (Criteria) this;
        }

        public Criteria andTokenLessThanOrEqualTo(String value) {
            addCriterion("token <=", value, "token");
            return (Criteria) this;
        }

        public Criteria andTokenLike(String value) {
            addCriterion("token like", value, "token");
            return (Criteria) this;
        }

        public Criteria andTokenNotLike(String value) {
            addCriterion("token not like", value, "token");
            return (Criteria) this;
        }

        public Criteria andTokenIn(List<String> values) {
            addCriterion("token in", values, "token");
            return (Criteria) this;
        }

        public Criteria andTokenNotIn(List<String> values) {
            addCriterion("token not in", values, "token");
            return (Criteria) this;
        }

        public Criteria andTokenBetween(String value1, String value2) {
            addCriterion("token between", value1, value2, "token");
            return (Criteria) this;
        }

        public Criteria andTokenNotBetween(String value1, String value2) {
            addCriterion("token not between", value1, value2, "token");
            return (Criteria) this;
        }

        public Criteria andAesKeyIsNull() {
            addCriterion("aes_key is null");
            return (Criteria) this;
        }

        public Criteria andAesKeyIsNotNull() {
            addCriterion("aes_key is not null");
            return (Criteria) this;
        }

        public Criteria andAesKeyEqualTo(String value) {
            addCriterion("aes_key =", value, "aesKey");
            return (Criteria) this;
        }

        public Criteria andAesKeyNotEqualTo(String value) {
            addCriterion("aes_key <>", value, "aesKey");
            return (Criteria) this;
        }

        public Criteria andAesKeyGreaterThan(String value) {
            addCriterion("aes_key >", value, "aesKey");
            return (Criteria) this;
        }

        public Criteria andAesKeyGreaterThanOrEqualTo(String value) {
            addCriterion("aes_key >=", value, "aesKey");
            return (Criteria) this;
        }

        public Criteria andAesKeyLessThan(String value) {
            addCriterion("aes_key <", value, "aesKey");
            return (Criteria) this;
        }

        public Criteria andAesKeyLessThanOrEqualTo(String value) {
            addCriterion("aes_key <=", value, "aesKey");
            return (Criteria) this;
        }

        public Criteria andAesKeyLike(String value) {
            addCriterion("aes_key like", value, "aesKey");
            return (Criteria) this;
        }

        public Criteria andAesKeyNotLike(String value) {
            addCriterion("aes_key not like", value, "aesKey");
            return (Criteria) this;
        }

        public Criteria andAesKeyIn(List<String> values) {
            addCriterion("aes_key in", values, "aesKey");
            return (Criteria) this;
        }

        public Criteria andAesKeyNotIn(List<String> values) {
            addCriterion("aes_key not in", values, "aesKey");
            return (Criteria) this;
        }

        public Criteria andAesKeyBetween(String value1, String value2) {
            addCriterion("aes_key between", value1, value2, "aesKey");
            return (Criteria) this;
        }

        public Criteria andAesKeyNotBetween(String value1, String value2) {
            addCriterion("aes_key not between", value1, value2, "aesKey");
            return (Criteria) this;
        }

        public Criteria andEncryptTypeIsNull() {
            addCriterion("encrypt_type is null");
            return (Criteria) this;
        }

        public Criteria andEncryptTypeIsNotNull() {
            addCriterion("encrypt_type is not null");
            return (Criteria) this;
        }

        public Criteria andEncryptTypeEqualTo(Integer value) {
            addCriterion("encrypt_type =", value, "encryptType");
            return (Criteria) this;
        }

        public Criteria andEncryptTypeNotEqualTo(Integer value) {
            addCriterion("encrypt_type <>", value, "encryptType");
            return (Criteria) this;
        }

        public Criteria andEncryptTypeGreaterThan(Integer value) {
            addCriterion("encrypt_type >", value, "encryptType");
            return (Criteria) this;
        }

        public Criteria andEncryptTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("encrypt_type >=", value, "encryptType");
            return (Criteria) this;
        }

        public Criteria andEncryptTypeLessThan(Integer value) {
            addCriterion("encrypt_type <", value, "encryptType");
            return (Criteria) this;
        }

        public Criteria andEncryptTypeLessThanOrEqualTo(Integer value) {
            addCriterion("encrypt_type <=", value, "encryptType");
            return (Criteria) this;
        }

        public Criteria andEncryptTypeIn(List<Integer> values) {
            addCriterion("encrypt_type in", values, "encryptType");
            return (Criteria) this;
        }

        public Criteria andEncryptTypeNotIn(List<Integer> values) {
            addCriterion("encrypt_type not in", values, "encryptType");
            return (Criteria) this;
        }

        public Criteria andEncryptTypeBetween(Integer value1, Integer value2) {
            addCriterion("encrypt_type between", value1, value2, "encryptType");
            return (Criteria) this;
        }

        public Criteria andEncryptTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("encrypt_type not between", value1, value2, "encryptType");
            return (Criteria) this;
        }

        public Criteria andIsPayEnableIsNull() {
            addCriterion("is_pay_enable is null");
            return (Criteria) this;
        }

        public Criteria andIsPayEnableIsNotNull() {
            addCriterion("is_pay_enable is not null");
            return (Criteria) this;
        }

        public Criteria andIsPayEnableEqualTo(Integer value) {
            addCriterion("is_pay_enable =", value, "isPayEnable");
            return (Criteria) this;
        }

        public Criteria andIsPayEnableNotEqualTo(Integer value) {
            addCriterion("is_pay_enable <>", value, "isPayEnable");
            return (Criteria) this;
        }

        public Criteria andIsPayEnableGreaterThan(Integer value) {
            addCriterion("is_pay_enable >", value, "isPayEnable");
            return (Criteria) this;
        }

        public Criteria andIsPayEnableGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_pay_enable >=", value, "isPayEnable");
            return (Criteria) this;
        }

        public Criteria andIsPayEnableLessThan(Integer value) {
            addCriterion("is_pay_enable <", value, "isPayEnable");
            return (Criteria) this;
        }

        public Criteria andIsPayEnableLessThanOrEqualTo(Integer value) {
            addCriterion("is_pay_enable <=", value, "isPayEnable");
            return (Criteria) this;
        }

        public Criteria andIsPayEnableIn(List<Integer> values) {
            addCriterion("is_pay_enable in", values, "isPayEnable");
            return (Criteria) this;
        }

        public Criteria andIsPayEnableNotIn(List<Integer> values) {
            addCriterion("is_pay_enable not in", values, "isPayEnable");
            return (Criteria) this;
        }

        public Criteria andIsPayEnableBetween(Integer value1, Integer value2) {
            addCriterion("is_pay_enable between", value1, value2, "isPayEnable");
            return (Criteria) this;
        }

        public Criteria andIsPayEnableNotBetween(Integer value1, Integer value2) {
            addCriterion("is_pay_enable not between", value1, value2, "isPayEnable");
            return (Criteria) this;
        }

        public Criteria andMchIdIsNull() {
            addCriterion("mch_id is null");
            return (Criteria) this;
        }

        public Criteria andMchIdIsNotNull() {
            addCriterion("mch_id is not null");
            return (Criteria) this;
        }

        public Criteria andMchIdEqualTo(String value) {
            addCriterion("mch_id =", value, "mchId");
            return (Criteria) this;
        }

        public Criteria andMchIdNotEqualTo(String value) {
            addCriterion("mch_id <>", value, "mchId");
            return (Criteria) this;
        }

        public Criteria andMchIdGreaterThan(String value) {
            addCriterion("mch_id >", value, "mchId");
            return (Criteria) this;
        }

        public Criteria andMchIdGreaterThanOrEqualTo(String value) {
            addCriterion("mch_id >=", value, "mchId");
            return (Criteria) this;
        }

        public Criteria andMchIdLessThan(String value) {
            addCriterion("mch_id <", value, "mchId");
            return (Criteria) this;
        }

        public Criteria andMchIdLessThanOrEqualTo(String value) {
            addCriterion("mch_id <=", value, "mchId");
            return (Criteria) this;
        }

        public Criteria andMchIdLike(String value) {
            addCriterion("mch_id like", value, "mchId");
            return (Criteria) this;
        }

        public Criteria andMchIdNotLike(String value) {
            addCriterion("mch_id not like", value, "mchId");
            return (Criteria) this;
        }

        public Criteria andMchIdIn(List<String> values) {
            addCriterion("mch_id in", values, "mchId");
            return (Criteria) this;
        }

        public Criteria andMchIdNotIn(List<String> values) {
            addCriterion("mch_id not in", values, "mchId");
            return (Criteria) this;
        }

        public Criteria andMchIdBetween(String value1, String value2) {
            addCriterion("mch_id between", value1, value2, "mchId");
            return (Criteria) this;
        }

        public Criteria andMchIdNotBetween(String value1, String value2) {
            addCriterion("mch_id not between", value1, value2, "mchId");
            return (Criteria) this;
        }

        public Criteria andMchKeyIsNull() {
            addCriterion("mch_key is null");
            return (Criteria) this;
        }

        public Criteria andMchKeyIsNotNull() {
            addCriterion("mch_key is not null");
            return (Criteria) this;
        }

        public Criteria andMchKeyEqualTo(String value) {
            addCriterion("mch_key =", value, "mchKey");
            return (Criteria) this;
        }

        public Criteria andMchKeyNotEqualTo(String value) {
            addCriterion("mch_key <>", value, "mchKey");
            return (Criteria) this;
        }

        public Criteria andMchKeyGreaterThan(String value) {
            addCriterion("mch_key >", value, "mchKey");
            return (Criteria) this;
        }

        public Criteria andMchKeyGreaterThanOrEqualTo(String value) {
            addCriterion("mch_key >=", value, "mchKey");
            return (Criteria) this;
        }

        public Criteria andMchKeyLessThan(String value) {
            addCriterion("mch_key <", value, "mchKey");
            return (Criteria) this;
        }

        public Criteria andMchKeyLessThanOrEqualTo(String value) {
            addCriterion("mch_key <=", value, "mchKey");
            return (Criteria) this;
        }

        public Criteria andMchKeyLike(String value) {
            addCriterion("mch_key like", value, "mchKey");
            return (Criteria) this;
        }

        public Criteria andMchKeyNotLike(String value) {
            addCriterion("mch_key not like", value, "mchKey");
            return (Criteria) this;
        }

        public Criteria andMchKeyIn(List<String> values) {
            addCriterion("mch_key in", values, "mchKey");
            return (Criteria) this;
        }

        public Criteria andMchKeyNotIn(List<String> values) {
            addCriterion("mch_key not in", values, "mchKey");
            return (Criteria) this;
        }

        public Criteria andMchKeyBetween(String value1, String value2) {
            addCriterion("mch_key between", value1, value2, "mchKey");
            return (Criteria) this;
        }

        public Criteria andMchKeyNotBetween(String value1, String value2) {
            addCriterion("mch_key not between", value1, value2, "mchKey");
            return (Criteria) this;
        }

        public Criteria andDomainIsNull() {
            addCriterion("domain is null");
            return (Criteria) this;
        }

        public Criteria andDomainIsNotNull() {
            addCriterion("domain is not null");
            return (Criteria) this;
        }

        public Criteria andDomainEqualTo(String value) {
            addCriterion("domain =", value, "domain");
            return (Criteria) this;
        }

        public Criteria andDomainNotEqualTo(String value) {
            addCriterion("domain <>", value, "domain");
            return (Criteria) this;
        }

        public Criteria andDomainGreaterThan(String value) {
            addCriterion("domain >", value, "domain");
            return (Criteria) this;
        }

        public Criteria andDomainGreaterThanOrEqualTo(String value) {
            addCriterion("domain >=", value, "domain");
            return (Criteria) this;
        }

        public Criteria andDomainLessThan(String value) {
            addCriterion("domain <", value, "domain");
            return (Criteria) this;
        }

        public Criteria andDomainLessThanOrEqualTo(String value) {
            addCriterion("domain <=", value, "domain");
            return (Criteria) this;
        }

        public Criteria andDomainLike(String value) {
            addCriterion("domain like", value, "domain");
            return (Criteria) this;
        }

        public Criteria andDomainNotLike(String value) {
            addCriterion("domain not like", value, "domain");
            return (Criteria) this;
        }

        public Criteria andDomainIn(List<String> values) {
            addCriterion("domain in", values, "domain");
            return (Criteria) this;
        }

        public Criteria andDomainNotIn(List<String> values) {
            addCriterion("domain not in", values, "domain");
            return (Criteria) this;
        }

        public Criteria andDomainBetween(String value1, String value2) {
            addCriterion("domain between", value1, value2, "domain");
            return (Criteria) this;
        }

        public Criteria andDomainNotBetween(String value1, String value2) {
            addCriterion("domain not between", value1, value2, "domain");
            return (Criteria) this;
        }

        public Criteria andWxpayNotifyUrlIsNull() {
            addCriterion("wxpay_notify_url is null");
            return (Criteria) this;
        }

        public Criteria andWxpayNotifyUrlIsNotNull() {
            addCriterion("wxpay_notify_url is not null");
            return (Criteria) this;
        }

        public Criteria andWxpayNotifyUrlEqualTo(String value) {
            addCriterion("wxpay_notify_url =", value, "wxpayNotifyUrl");
            return (Criteria) this;
        }

        public Criteria andWxpayNotifyUrlNotEqualTo(String value) {
            addCriterion("wxpay_notify_url <>", value, "wxpayNotifyUrl");
            return (Criteria) this;
        }

        public Criteria andWxpayNotifyUrlGreaterThan(String value) {
            addCriterion("wxpay_notify_url >", value, "wxpayNotifyUrl");
            return (Criteria) this;
        }

        public Criteria andWxpayNotifyUrlGreaterThanOrEqualTo(String value) {
            addCriterion("wxpay_notify_url >=", value, "wxpayNotifyUrl");
            return (Criteria) this;
        }

        public Criteria andWxpayNotifyUrlLessThan(String value) {
            addCriterion("wxpay_notify_url <", value, "wxpayNotifyUrl");
            return (Criteria) this;
        }

        public Criteria andWxpayNotifyUrlLessThanOrEqualTo(String value) {
            addCriterion("wxpay_notify_url <=", value, "wxpayNotifyUrl");
            return (Criteria) this;
        }

        public Criteria andWxpayNotifyUrlLike(String value) {
            addCriterion("wxpay_notify_url like", value, "wxpayNotifyUrl");
            return (Criteria) this;
        }

        public Criteria andWxpayNotifyUrlNotLike(String value) {
            addCriterion("wxpay_notify_url not like", value, "wxpayNotifyUrl");
            return (Criteria) this;
        }

        public Criteria andWxpayNotifyUrlIn(List<String> values) {
            addCriterion("wxpay_notify_url in", values, "wxpayNotifyUrl");
            return (Criteria) this;
        }

        public Criteria andWxpayNotifyUrlNotIn(List<String> values) {
            addCriterion("wxpay_notify_url not in", values, "wxpayNotifyUrl");
            return (Criteria) this;
        }

        public Criteria andWxpayNotifyUrlBetween(String value1, String value2) {
            addCriterion("wxpay_notify_url between", value1, value2, "wxpayNotifyUrl");
            return (Criteria) this;
        }

        public Criteria andWxpayNotifyUrlNotBetween(String value1, String value2) {
            addCriterion("wxpay_notify_url not between", value1, value2, "wxpayNotifyUrl");
            return (Criteria) this;
        }

        public Criteria andCertPathIsNull() {
            addCriterion("cert_path is null");
            return (Criteria) this;
        }

        public Criteria andCertPathIsNotNull() {
            addCriterion("cert_path is not null");
            return (Criteria) this;
        }

        public Criteria andCertPathEqualTo(String value) {
            addCriterion("cert_path =", value, "certPath");
            return (Criteria) this;
        }

        public Criteria andCertPathNotEqualTo(String value) {
            addCriterion("cert_path <>", value, "certPath");
            return (Criteria) this;
        }

        public Criteria andCertPathGreaterThan(String value) {
            addCriterion("cert_path >", value, "certPath");
            return (Criteria) this;
        }

        public Criteria andCertPathGreaterThanOrEqualTo(String value) {
            addCriterion("cert_path >=", value, "certPath");
            return (Criteria) this;
        }

        public Criteria andCertPathLessThan(String value) {
            addCriterion("cert_path <", value, "certPath");
            return (Criteria) this;
        }

        public Criteria andCertPathLessThanOrEqualTo(String value) {
            addCriterion("cert_path <=", value, "certPath");
            return (Criteria) this;
        }

        public Criteria andCertPathLike(String value) {
            addCriterion("cert_path like", value, "certPath");
            return (Criteria) this;
        }

        public Criteria andCertPathNotLike(String value) {
            addCriterion("cert_path not like", value, "certPath");
            return (Criteria) this;
        }

        public Criteria andCertPathIn(List<String> values) {
            addCriterion("cert_path in", values, "certPath");
            return (Criteria) this;
        }

        public Criteria andCertPathNotIn(List<String> values) {
            addCriterion("cert_path not in", values, "certPath");
            return (Criteria) this;
        }

        public Criteria andCertPathBetween(String value1, String value2) {
            addCriterion("cert_path between", value1, value2, "certPath");
            return (Criteria) this;
        }

        public Criteria andCertPathNotBetween(String value1, String value2) {
            addCriterion("cert_path not between", value1, value2, "certPath");
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