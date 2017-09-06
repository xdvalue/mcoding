package com.mcoding.base.product.bean.product;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mcoding.base.core.IExample;
import com.mcoding.base.core.PageView;
import com.mcoding.base.utils.json.JsonUtilsForMcoding;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProductExample implements IExample<Product> {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected PageView<Product> pageView;

    public ProductExample() {
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
    public PageView<Product> getPageView() {
        return pageView;
    }

    @Override
    public void setPageView(PageView<Product> pageView) {
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

        public Criteria andProductNameIsNull() {
            addCriterion("product_name is null");
            return (Criteria) this;
        }

        public Criteria andProductNameIsNotNull() {
            addCriterion("product_name is not null");
            return (Criteria) this;
        }

        public Criteria andProductNameEqualTo(String value) {
            addCriterion("product_name =", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameNotEqualTo(String value) {
            addCriterion("product_name <>", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameGreaterThan(String value) {
            addCriterion("product_name >", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameGreaterThanOrEqualTo(String value) {
            addCriterion("product_name >=", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameLessThan(String value) {
            addCriterion("product_name <", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameLessThanOrEqualTo(String value) {
            addCriterion("product_name <=", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameLike(String value) {
            addCriterion("product_name like", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameNotLike(String value) {
            addCriterion("product_name not like", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameIn(List<String> values) {
            addCriterion("product_name in", values, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameNotIn(List<String> values) {
            addCriterion("product_name not in", values, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameBetween(String value1, String value2) {
            addCriterion("product_name between", value1, value2, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameNotBetween(String value1, String value2) {
            addCriterion("product_name not between", value1, value2, "productName");
            return (Criteria) this;
        }

        public Criteria andBarCodeIsNull() {
            addCriterion("bar_code is null");
            return (Criteria) this;
        }

        public Criteria andBarCodeIsNotNull() {
            addCriterion("bar_code is not null");
            return (Criteria) this;
        }

        public Criteria andBarCodeEqualTo(String value) {
            addCriterion("bar_code =", value, "barCode");
            return (Criteria) this;
        }

        public Criteria andBarCodeNotEqualTo(String value) {
            addCriterion("bar_code <>", value, "barCode");
            return (Criteria) this;
        }

        public Criteria andBarCodeGreaterThan(String value) {
            addCriterion("bar_code >", value, "barCode");
            return (Criteria) this;
        }

        public Criteria andBarCodeGreaterThanOrEqualTo(String value) {
            addCriterion("bar_code >=", value, "barCode");
            return (Criteria) this;
        }

        public Criteria andBarCodeLessThan(String value) {
            addCriterion("bar_code <", value, "barCode");
            return (Criteria) this;
        }

        public Criteria andBarCodeLessThanOrEqualTo(String value) {
            addCriterion("bar_code <=", value, "barCode");
            return (Criteria) this;
        }

        public Criteria andBarCodeLike(String value) {
            addCriterion("bar_code like", value, "barCode");
            return (Criteria) this;
        }

        public Criteria andBarCodeNotLike(String value) {
            addCriterion("bar_code not like", value, "barCode");
            return (Criteria) this;
        }

        public Criteria andBarCodeIn(List<String> values) {
            addCriterion("bar_code in", values, "barCode");
            return (Criteria) this;
        }

        public Criteria andBarCodeNotIn(List<String> values) {
            addCriterion("bar_code not in", values, "barCode");
            return (Criteria) this;
        }

        public Criteria andBarCodeBetween(String value1, String value2) {
            addCriterion("bar_code between", value1, value2, "barCode");
            return (Criteria) this;
        }

        public Criteria andBarCodeNotBetween(String value1, String value2) {
            addCriterion("bar_code not between", value1, value2, "barCode");
            return (Criteria) this;
        }

        public Criteria andFakeCodeIsNull() {
            addCriterion("fake_code is null");
            return (Criteria) this;
        }

        public Criteria andFakeCodeIsNotNull() {
            addCriterion("fake_code is not null");
            return (Criteria) this;
        }

        public Criteria andFakeCodeEqualTo(String value) {
            addCriterion("fake_code =", value, "fakeCode");
            return (Criteria) this;
        }

        public Criteria andFakeCodeNotEqualTo(String value) {
            addCriterion("fake_code <>", value, "fakeCode");
            return (Criteria) this;
        }

        public Criteria andFakeCodeGreaterThan(String value) {
            addCriterion("fake_code >", value, "fakeCode");
            return (Criteria) this;
        }

        public Criteria andFakeCodeGreaterThanOrEqualTo(String value) {
            addCriterion("fake_code >=", value, "fakeCode");
            return (Criteria) this;
        }

        public Criteria andFakeCodeLessThan(String value) {
            addCriterion("fake_code <", value, "fakeCode");
            return (Criteria) this;
        }

        public Criteria andFakeCodeLessThanOrEqualTo(String value) {
            addCriterion("fake_code <=", value, "fakeCode");
            return (Criteria) this;
        }

        public Criteria andFakeCodeLike(String value) {
            addCriterion("fake_code like", value, "fakeCode");
            return (Criteria) this;
        }

        public Criteria andFakeCodeNotLike(String value) {
            addCriterion("fake_code not like", value, "fakeCode");
            return (Criteria) this;
        }

        public Criteria andFakeCodeIn(List<String> values) {
            addCriterion("fake_code in", values, "fakeCode");
            return (Criteria) this;
        }

        public Criteria andFakeCodeNotIn(List<String> values) {
            addCriterion("fake_code not in", values, "fakeCode");
            return (Criteria) this;
        }

        public Criteria andFakeCodeBetween(String value1, String value2) {
            addCriterion("fake_code between", value1, value2, "fakeCode");
            return (Criteria) this;
        }

        public Criteria andFakeCodeNotBetween(String value1, String value2) {
            addCriterion("fake_code not between", value1, value2, "fakeCode");
            return (Criteria) this;
        }

        public Criteria andNumberCodeIsNull() {
            addCriterion("number_code is null");
            return (Criteria) this;
        }

        public Criteria andNumberCodeIsNotNull() {
            addCriterion("number_code is not null");
            return (Criteria) this;
        }

        public Criteria andNumberCodeEqualTo(String value) {
            addCriterion("number_code =", value, "numberCode");
            return (Criteria) this;
        }

        public Criteria andNumberCodeNotEqualTo(String value) {
            addCriterion("number_code <>", value, "numberCode");
            return (Criteria) this;
        }

        public Criteria andNumberCodeGreaterThan(String value) {
            addCriterion("number_code >", value, "numberCode");
            return (Criteria) this;
        }

        public Criteria andNumberCodeGreaterThanOrEqualTo(String value) {
            addCriterion("number_code >=", value, "numberCode");
            return (Criteria) this;
        }

        public Criteria andNumberCodeLessThan(String value) {
            addCriterion("number_code <", value, "numberCode");
            return (Criteria) this;
        }

        public Criteria andNumberCodeLessThanOrEqualTo(String value) {
            addCriterion("number_code <=", value, "numberCode");
            return (Criteria) this;
        }

        public Criteria andNumberCodeLike(String value) {
            addCriterion("number_code like", value, "numberCode");
            return (Criteria) this;
        }

        public Criteria andNumberCodeNotLike(String value) {
            addCriterion("number_code not like", value, "numberCode");
            return (Criteria) this;
        }

        public Criteria andNumberCodeIn(List<String> values) {
            addCriterion("number_code in", values, "numberCode");
            return (Criteria) this;
        }

        public Criteria andNumberCodeNotIn(List<String> values) {
            addCriterion("number_code not in", values, "numberCode");
            return (Criteria) this;
        }

        public Criteria andNumberCodeBetween(String value1, String value2) {
            addCriterion("number_code between", value1, value2, "numberCode");
            return (Criteria) this;
        }

        public Criteria andNumberCodeNotBetween(String value1, String value2) {
            addCriterion("number_code not between", value1, value2, "numberCode");
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

        public Criteria andLabelIsNull() {
            addCriterion("label is null");
            return (Criteria) this;
        }

        public Criteria andLabelIsNotNull() {
            addCriterion("label is not null");
            return (Criteria) this;
        }

        public Criteria andLabelEqualTo(Integer value) {
            addCriterion("label =", value, "label");
            return (Criteria) this;
        }

        public Criteria andLabelNotEqualTo(Integer value) {
            addCriterion("label <>", value, "label");
            return (Criteria) this;
        }

        public Criteria andLabelGreaterThan(Integer value) {
            addCriterion("label >", value, "label");
            return (Criteria) this;
        }

        public Criteria andLabelGreaterThanOrEqualTo(Integer value) {
            addCriterion("label >=", value, "label");
            return (Criteria) this;
        }

        public Criteria andLabelLessThan(Integer value) {
            addCriterion("label <", value, "label");
            return (Criteria) this;
        }

        public Criteria andLabelLessThanOrEqualTo(Integer value) {
            addCriterion("label <=", value, "label");
            return (Criteria) this;
        }

        public Criteria andLabelIn(List<Integer> values) {
            addCriterion("label in", values, "label");
            return (Criteria) this;
        }

        public Criteria andLabelNotIn(List<Integer> values) {
            addCriterion("label not in", values, "label");
            return (Criteria) this;
        }

        public Criteria andLabelBetween(Integer value1, Integer value2) {
            addCriterion("label between", value1, value2, "label");
            return (Criteria) this;
        }

        public Criteria andLabelNotBetween(Integer value1, Integer value2) {
            addCriterion("label not between", value1, value2, "label");
            return (Criteria) this;
        }

        public Criteria andSloganIsNull() {
            addCriterion("slogan is null");
            return (Criteria) this;
        }

        public Criteria andSloganIsNotNull() {
            addCriterion("slogan is not null");
            return (Criteria) this;
        }

        public Criteria andSloganEqualTo(String value) {
            addCriterion("slogan =", value, "slogan");
            return (Criteria) this;
        }

        public Criteria andSloganNotEqualTo(String value) {
            addCriterion("slogan <>", value, "slogan");
            return (Criteria) this;
        }

        public Criteria andSloganGreaterThan(String value) {
            addCriterion("slogan >", value, "slogan");
            return (Criteria) this;
        }

        public Criteria andSloganGreaterThanOrEqualTo(String value) {
            addCriterion("slogan >=", value, "slogan");
            return (Criteria) this;
        }

        public Criteria andSloganLessThan(String value) {
            addCriterion("slogan <", value, "slogan");
            return (Criteria) this;
        }

        public Criteria andSloganLessThanOrEqualTo(String value) {
            addCriterion("slogan <=", value, "slogan");
            return (Criteria) this;
        }

        public Criteria andSloganLike(String value) {
            addCriterion("slogan like", value, "slogan");
            return (Criteria) this;
        }

        public Criteria andSloganNotLike(String value) {
            addCriterion("slogan not like", value, "slogan");
            return (Criteria) this;
        }

        public Criteria andSloganIn(List<String> values) {
            addCriterion("slogan in", values, "slogan");
            return (Criteria) this;
        }

        public Criteria andSloganNotIn(List<String> values) {
            addCriterion("slogan not in", values, "slogan");
            return (Criteria) this;
        }

        public Criteria andSloganBetween(String value1, String value2) {
            addCriterion("slogan between", value1, value2, "slogan");
            return (Criteria) this;
        }

        public Criteria andSloganNotBetween(String value1, String value2) {
            addCriterion("slogan not between", value1, value2, "slogan");
            return (Criteria) this;
        }

        public Criteria andCoverImgIsNull() {
            addCriterion("cover_img is null");
            return (Criteria) this;
        }

        public Criteria andCoverImgIsNotNull() {
            addCriterion("cover_img is not null");
            return (Criteria) this;
        }

        public Criteria andCoverImgEqualTo(String value) {
            addCriterion("cover_img =", value, "coverImg");
            return (Criteria) this;
        }

        public Criteria andCoverImgNotEqualTo(String value) {
            addCriterion("cover_img <>", value, "coverImg");
            return (Criteria) this;
        }

        public Criteria andCoverImgGreaterThan(String value) {
            addCriterion("cover_img >", value, "coverImg");
            return (Criteria) this;
        }

        public Criteria andCoverImgGreaterThanOrEqualTo(String value) {
            addCriterion("cover_img >=", value, "coverImg");
            return (Criteria) this;
        }

        public Criteria andCoverImgLessThan(String value) {
            addCriterion("cover_img <", value, "coverImg");
            return (Criteria) this;
        }

        public Criteria andCoverImgLessThanOrEqualTo(String value) {
            addCriterion("cover_img <=", value, "coverImg");
            return (Criteria) this;
        }

        public Criteria andCoverImgLike(String value) {
            addCriterion("cover_img like", value, "coverImg");
            return (Criteria) this;
        }

        public Criteria andCoverImgNotLike(String value) {
            addCriterion("cover_img not like", value, "coverImg");
            return (Criteria) this;
        }

        public Criteria andCoverImgIn(List<String> values) {
            addCriterion("cover_img in", values, "coverImg");
            return (Criteria) this;
        }

        public Criteria andCoverImgNotIn(List<String> values) {
            addCriterion("cover_img not in", values, "coverImg");
            return (Criteria) this;
        }

        public Criteria andCoverImgBetween(String value1, String value2) {
            addCriterion("cover_img between", value1, value2, "coverImg");
            return (Criteria) this;
        }

        public Criteria andCoverImgNotBetween(String value1, String value2) {
            addCriterion("cover_img not between", value1, value2, "coverImg");
            return (Criteria) this;
        }

        public Criteria andSequenceIsNull() {
            addCriterion("sequence is null");
            return (Criteria) this;
        }

        public Criteria andSequenceIsNotNull() {
            addCriterion("sequence is not null");
            return (Criteria) this;
        }

        public Criteria andSequenceEqualTo(Integer value) {
            addCriterion("sequence =", value, "sequence");
            return (Criteria) this;
        }

        public Criteria andSequenceNotEqualTo(Integer value) {
            addCriterion("sequence <>", value, "sequence");
            return (Criteria) this;
        }

        public Criteria andSequenceGreaterThan(Integer value) {
            addCriterion("sequence >", value, "sequence");
            return (Criteria) this;
        }

        public Criteria andSequenceGreaterThanOrEqualTo(Integer value) {
            addCriterion("sequence >=", value, "sequence");
            return (Criteria) this;
        }

        public Criteria andSequenceLessThan(Integer value) {
            addCriterion("sequence <", value, "sequence");
            return (Criteria) this;
        }

        public Criteria andSequenceLessThanOrEqualTo(Integer value) {
            addCriterion("sequence <=", value, "sequence");
            return (Criteria) this;
        }

        public Criteria andSequenceIn(List<Integer> values) {
            addCriterion("sequence in", values, "sequence");
            return (Criteria) this;
        }

        public Criteria andSequenceNotIn(List<Integer> values) {
            addCriterion("sequence not in", values, "sequence");
            return (Criteria) this;
        }

        public Criteria andSequenceBetween(Integer value1, Integer value2) {
            addCriterion("sequence between", value1, value2, "sequence");
            return (Criteria) this;
        }

        public Criteria andSequenceNotBetween(Integer value1, Integer value2) {
            addCriterion("sequence not between", value1, value2, "sequence");
            return (Criteria) this;
        }

        public Criteria andSaleStatusIsNull() {
            addCriterion("sale_status is null");
            return (Criteria) this;
        }

        public Criteria andSaleStatusIsNotNull() {
            addCriterion("sale_status is not null");
            return (Criteria) this;
        }

        public Criteria andSaleStatusEqualTo(Integer value) {
            addCriterion("sale_status =", value, "saleStatus");
            return (Criteria) this;
        }

        public Criteria andSaleStatusNotEqualTo(Integer value) {
            addCriterion("sale_status <>", value, "saleStatus");
            return (Criteria) this;
        }

        public Criteria andSaleStatusGreaterThan(Integer value) {
            addCriterion("sale_status >", value, "saleStatus");
            return (Criteria) this;
        }

        public Criteria andSaleStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("sale_status >=", value, "saleStatus");
            return (Criteria) this;
        }

        public Criteria andSaleStatusLessThan(Integer value) {
            addCriterion("sale_status <", value, "saleStatus");
            return (Criteria) this;
        }

        public Criteria andSaleStatusLessThanOrEqualTo(Integer value) {
            addCriterion("sale_status <=", value, "saleStatus");
            return (Criteria) this;
        }

        public Criteria andSaleStatusIn(List<Integer> values) {
            addCriterion("sale_status in", values, "saleStatus");
            return (Criteria) this;
        }

        public Criteria andSaleStatusNotIn(List<Integer> values) {
            addCriterion("sale_status not in", values, "saleStatus");
            return (Criteria) this;
        }

        public Criteria andSaleStatusBetween(Integer value1, Integer value2) {
            addCriterion("sale_status between", value1, value2, "saleStatus");
            return (Criteria) this;
        }

        public Criteria andSaleStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("sale_status not between", value1, value2, "saleStatus");
            return (Criteria) this;
        }

        public Criteria andGiftExchangeStatusIsNull() {
            addCriterion("gift_exchange_status is null");
            return (Criteria) this;
        }

        public Criteria andGiftExchangeStatusIsNotNull() {
            addCriterion("gift_exchange_status is not null");
            return (Criteria) this;
        }

        public Criteria andGiftExchangeStatusEqualTo(String value) {
            addCriterion("gift_exchange_status =", value, "giftExchangeStatus");
            return (Criteria) this;
        }

        public Criteria andGiftExchangeStatusNotEqualTo(String value) {
            addCriterion("gift_exchange_status <>", value, "giftExchangeStatus");
            return (Criteria) this;
        }

        public Criteria andGiftExchangeStatusGreaterThan(String value) {
            addCriterion("gift_exchange_status >", value, "giftExchangeStatus");
            return (Criteria) this;
        }

        public Criteria andGiftExchangeStatusGreaterThanOrEqualTo(String value) {
            addCriterion("gift_exchange_status >=", value, "giftExchangeStatus");
            return (Criteria) this;
        }

        public Criteria andGiftExchangeStatusLessThan(String value) {
            addCriterion("gift_exchange_status <", value, "giftExchangeStatus");
            return (Criteria) this;
        }

        public Criteria andGiftExchangeStatusLessThanOrEqualTo(String value) {
            addCriterion("gift_exchange_status <=", value, "giftExchangeStatus");
            return (Criteria) this;
        }

        public Criteria andGiftExchangeStatusLike(String value) {
            addCriterion("gift_exchange_status like", value, "giftExchangeStatus");
            return (Criteria) this;
        }

        public Criteria andGiftExchangeStatusNotLike(String value) {
            addCriterion("gift_exchange_status not like", value, "giftExchangeStatus");
            return (Criteria) this;
        }

        public Criteria andGiftExchangeStatusIn(List<String> values) {
            addCriterion("gift_exchange_status in", values, "giftExchangeStatus");
            return (Criteria) this;
        }

        public Criteria andGiftExchangeStatusNotIn(List<String> values) {
            addCriterion("gift_exchange_status not in", values, "giftExchangeStatus");
            return (Criteria) this;
        }

        public Criteria andGiftExchangeStatusBetween(String value1, String value2) {
            addCriterion("gift_exchange_status between", value1, value2, "giftExchangeStatus");
            return (Criteria) this;
        }

        public Criteria andGiftExchangeStatusNotBetween(String value1, String value2) {
            addCriterion("gift_exchange_status not between", value1, value2, "giftExchangeStatus");
            return (Criteria) this;
        }

        public Criteria andSetStatusIsNull() {
            addCriterion("set_status is null");
            return (Criteria) this;
        }

        public Criteria andSetStatusIsNotNull() {
            addCriterion("set_status is not null");
            return (Criteria) this;
        }

        public Criteria andSetStatusEqualTo(Integer value) {
            addCriterion("set_status =", value, "setStatus");
            return (Criteria) this;
        }

        public Criteria andSetStatusNotEqualTo(Integer value) {
            addCriterion("set_status <>", value, "setStatus");
            return (Criteria) this;
        }

        public Criteria andSetStatusGreaterThan(Integer value) {
            addCriterion("set_status >", value, "setStatus");
            return (Criteria) this;
        }

        public Criteria andSetStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("set_status >=", value, "setStatus");
            return (Criteria) this;
        }

        public Criteria andSetStatusLessThan(Integer value) {
            addCriterion("set_status <", value, "setStatus");
            return (Criteria) this;
        }

        public Criteria andSetStatusLessThanOrEqualTo(Integer value) {
            addCriterion("set_status <=", value, "setStatus");
            return (Criteria) this;
        }

        public Criteria andSetStatusIn(List<Integer> values) {
            addCriterion("set_status in", values, "setStatus");
            return (Criteria) this;
        }

        public Criteria andSetStatusNotIn(List<Integer> values) {
            addCriterion("set_status not in", values, "setStatus");
            return (Criteria) this;
        }

        public Criteria andSetStatusBetween(Integer value1, Integer value2) {
            addCriterion("set_status between", value1, value2, "setStatus");
            return (Criteria) this;
        }

        public Criteria andSetStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("set_status not between", value1, value2, "setStatus");
            return (Criteria) this;
        }

        public Criteria andLimitStatusIsNull() {
            addCriterion("limit_status is null");
            return (Criteria) this;
        }

        public Criteria andLimitStatusIsNotNull() {
            addCriterion("limit_status is not null");
            return (Criteria) this;
        }

        public Criteria andLimitStatusEqualTo(Integer value) {
            addCriterion("limit_status =", value, "limitStatus");
            return (Criteria) this;
        }

        public Criteria andLimitStatusNotEqualTo(Integer value) {
            addCriterion("limit_status <>", value, "limitStatus");
            return (Criteria) this;
        }

        public Criteria andLimitStatusGreaterThan(Integer value) {
            addCriterion("limit_status >", value, "limitStatus");
            return (Criteria) this;
        }

        public Criteria andLimitStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("limit_status >=", value, "limitStatus");
            return (Criteria) this;
        }

        public Criteria andLimitStatusLessThan(Integer value) {
            addCriterion("limit_status <", value, "limitStatus");
            return (Criteria) this;
        }

        public Criteria andLimitStatusLessThanOrEqualTo(Integer value) {
            addCriterion("limit_status <=", value, "limitStatus");
            return (Criteria) this;
        }

        public Criteria andLimitStatusIn(List<Integer> values) {
            addCriterion("limit_status in", values, "limitStatus");
            return (Criteria) this;
        }

        public Criteria andLimitStatusNotIn(List<Integer> values) {
            addCriterion("limit_status not in", values, "limitStatus");
            return (Criteria) this;
        }

        public Criteria andLimitStatusBetween(Integer value1, Integer value2) {
            addCriterion("limit_status between", value1, value2, "limitStatus");
            return (Criteria) this;
        }

        public Criteria andLimitStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("limit_status not between", value1, value2, "limitStatus");
            return (Criteria) this;
        }

        public Criteria andLimitQuotaIsNull() {
            addCriterion("limit_quota is null");
            return (Criteria) this;
        }

        public Criteria andLimitQuotaIsNotNull() {
            addCriterion("limit_quota is not null");
            return (Criteria) this;
        }

        public Criteria andLimitQuotaEqualTo(Integer value) {
            addCriterion("limit_quota =", value, "limitQuota");
            return (Criteria) this;
        }

        public Criteria andLimitQuotaNotEqualTo(Integer value) {
            addCriterion("limit_quota <>", value, "limitQuota");
            return (Criteria) this;
        }

        public Criteria andLimitQuotaGreaterThan(Integer value) {
            addCriterion("limit_quota >", value, "limitQuota");
            return (Criteria) this;
        }

        public Criteria andLimitQuotaGreaterThanOrEqualTo(Integer value) {
            addCriterion("limit_quota >=", value, "limitQuota");
            return (Criteria) this;
        }

        public Criteria andLimitQuotaLessThan(Integer value) {
            addCriterion("limit_quota <", value, "limitQuota");
            return (Criteria) this;
        }

        public Criteria andLimitQuotaLessThanOrEqualTo(Integer value) {
            addCriterion("limit_quota <=", value, "limitQuota");
            return (Criteria) this;
        }

        public Criteria andLimitQuotaIn(List<Integer> values) {
            addCriterion("limit_quota in", values, "limitQuota");
            return (Criteria) this;
        }

        public Criteria andLimitQuotaNotIn(List<Integer> values) {
            addCriterion("limit_quota not in", values, "limitQuota");
            return (Criteria) this;
        }

        public Criteria andLimitQuotaBetween(Integer value1, Integer value2) {
            addCriterion("limit_quota between", value1, value2, "limitQuota");
            return (Criteria) this;
        }

        public Criteria andLimitQuotaNotBetween(Integer value1, Integer value2) {
            addCriterion("limit_quota not between", value1, value2, "limitQuota");
            return (Criteria) this;
        }

        public Criteria andLimitBuyQuotaIsNull() {
            addCriterion("limit_buy_quota is null");
            return (Criteria) this;
        }

        public Criteria andLimitBuyQuotaIsNotNull() {
            addCriterion("limit_buy_quota is not null");
            return (Criteria) this;
        }

        public Criteria andLimitBuyQuotaEqualTo(Integer value) {
            addCriterion("limit_buy_quota =", value, "limitBuyQuota");
            return (Criteria) this;
        }

        public Criteria andLimitBuyQuotaNotEqualTo(Integer value) {
            addCriterion("limit_buy_quota <>", value, "limitBuyQuota");
            return (Criteria) this;
        }

        public Criteria andLimitBuyQuotaGreaterThan(Integer value) {
            addCriterion("limit_buy_quota >", value, "limitBuyQuota");
            return (Criteria) this;
        }

        public Criteria andLimitBuyQuotaGreaterThanOrEqualTo(Integer value) {
            addCriterion("limit_buy_quota >=", value, "limitBuyQuota");
            return (Criteria) this;
        }

        public Criteria andLimitBuyQuotaLessThan(Integer value) {
            addCriterion("limit_buy_quota <", value, "limitBuyQuota");
            return (Criteria) this;
        }

        public Criteria andLimitBuyQuotaLessThanOrEqualTo(Integer value) {
            addCriterion("limit_buy_quota <=", value, "limitBuyQuota");
            return (Criteria) this;
        }

        public Criteria andLimitBuyQuotaIn(List<Integer> values) {
            addCriterion("limit_buy_quota in", values, "limitBuyQuota");
            return (Criteria) this;
        }

        public Criteria andLimitBuyQuotaNotIn(List<Integer> values) {
            addCriterion("limit_buy_quota not in", values, "limitBuyQuota");
            return (Criteria) this;
        }

        public Criteria andLimitBuyQuotaBetween(Integer value1, Integer value2) {
            addCriterion("limit_buy_quota between", value1, value2, "limitBuyQuota");
            return (Criteria) this;
        }

        public Criteria andLimitBuyQuotaNotBetween(Integer value1, Integer value2) {
            addCriterion("limit_buy_quota not between", value1, value2, "limitBuyQuota");
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