package com.mcoding.base.cms.bean.article;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mcoding.base.core.IExample;
import com.mcoding.base.core.PageView;
import com.mcoding.base.utils.json.JsonUtilsForMcoding;

public class ArticleExample implements IExample<Article>{
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;
    
    protected PageView<Article> pageView;
    
    public ArticleExample() {
        oredCriteria = new ArrayList<Criteria>();
    }
    
    @Override
   	public PageView<Article> getPageView() {
   		return pageView;
   	}

   	@Override
   	public void setPageView(PageView<Article> pageView) {
   		this.pageView = pageView;
   	}

   	@Override
   	public String toJson() throws JsonProcessingException {
   		return JsonUtilsForMcoding.writeValueAsString(this);
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

        public Criteria andTitleIsNull() {
            addCriterion("title is null");
            return (Criteria) this;
        }

        public Criteria andTitleIsNotNull() {
            addCriterion("title is not null");
            return (Criteria) this;
        }

        public Criteria andTitleEqualTo(String value) {
            addCriterion("title =", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotEqualTo(String value) {
            addCriterion("title <>", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThan(String value) {
            addCriterion("title >", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThanOrEqualTo(String value) {
            addCriterion("title >=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThan(String value) {
            addCriterion("title <", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThanOrEqualTo(String value) {
            addCriterion("title <=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLike(String value) {
            addCriterion("title like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotLike(String value) {
            addCriterion("title not like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleIn(List<String> values) {
            addCriterion("title in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotIn(List<String> values) {
            addCriterion("title not in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleBetween(String value1, String value2) {
            addCriterion("title between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotBetween(String value1, String value2) {
            addCriterion("title not between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andSubTitleIsNull() {
            addCriterion("sub_title is null");
            return (Criteria) this;
        }

        public Criteria andSubTitleIsNotNull() {
            addCriterion("sub_title is not null");
            return (Criteria) this;
        }

        public Criteria andSubTitleEqualTo(String value) {
            addCriterion("sub_title =", value, "subTitle");
            return (Criteria) this;
        }

        public Criteria andSubTitleNotEqualTo(String value) {
            addCriterion("sub_title <>", value, "subTitle");
            return (Criteria) this;
        }

        public Criteria andSubTitleGreaterThan(String value) {
            addCriterion("sub_title >", value, "subTitle");
            return (Criteria) this;
        }

        public Criteria andSubTitleGreaterThanOrEqualTo(String value) {
            addCriterion("sub_title >=", value, "subTitle");
            return (Criteria) this;
        }

        public Criteria andSubTitleLessThan(String value) {
            addCriterion("sub_title <", value, "subTitle");
            return (Criteria) this;
        }

        public Criteria andSubTitleLessThanOrEqualTo(String value) {
            addCriterion("sub_title <=", value, "subTitle");
            return (Criteria) this;
        }

        public Criteria andSubTitleLike(String value) {
            addCriterion("sub_title like", value, "subTitle");
            return (Criteria) this;
        }

        public Criteria andSubTitleNotLike(String value) {
            addCriterion("sub_title not like", value, "subTitle");
            return (Criteria) this;
        }

        public Criteria andSubTitleIn(List<String> values) {
            addCriterion("sub_title in", values, "subTitle");
            return (Criteria) this;
        }

        public Criteria andSubTitleNotIn(List<String> values) {
            addCriterion("sub_title not in", values, "subTitle");
            return (Criteria) this;
        }

        public Criteria andSubTitleBetween(String value1, String value2) {
            addCriterion("sub_title between", value1, value2, "subTitle");
            return (Criteria) this;
        }

        public Criteria andSubTitleNotBetween(String value1, String value2) {
            addCriterion("sub_title not between", value1, value2, "subTitle");
            return (Criteria) this;
        }

        public Criteria andTypeIdIsNull() {
            addCriterion("type_id is null");
            return (Criteria) this;
        }

        public Criteria andTypeIdIsNotNull() {
            addCriterion("type_id is not null");
            return (Criteria) this;
        }

        public Criteria andTypeIdEqualTo(Integer value) {
            addCriterion("type_id =", value, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdNotEqualTo(Integer value) {
            addCriterion("type_id <>", value, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdGreaterThan(Integer value) {
            addCriterion("type_id >", value, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("type_id >=", value, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdLessThan(Integer value) {
            addCriterion("type_id <", value, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdLessThanOrEqualTo(Integer value) {
            addCriterion("type_id <=", value, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdIn(List<Integer> values) {
            addCriterion("type_id in", values, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdNotIn(List<Integer> values) {
            addCriterion("type_id not in", values, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdBetween(Integer value1, Integer value2) {
            addCriterion("type_id between", value1, value2, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdNotBetween(Integer value1, Integer value2) {
            addCriterion("type_id not between", value1, value2, "typeId");
            return (Criteria) this;
        }

        public Criteria andModuleIdIsNull() {
            addCriterion("module_id is null");
            return (Criteria) this;
        }

        public Criteria andModuleIdIsNotNull() {
            addCriterion("module_id is not null");
            return (Criteria) this;
        }

        public Criteria andModuleIdEqualTo(Integer value) {
            addCriterion("module_id =", value, "moduleId");
            return (Criteria) this;
        }

        public Criteria andModuleIdNotEqualTo(Integer value) {
            addCriterion("module_id <>", value, "moduleId");
            return (Criteria) this;
        }

        public Criteria andModuleIdGreaterThan(Integer value) {
            addCriterion("module_id >", value, "moduleId");
            return (Criteria) this;
        }

        public Criteria andModuleIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("module_id >=", value, "moduleId");
            return (Criteria) this;
        }

        public Criteria andModuleIdLessThan(Integer value) {
            addCriterion("module_id <", value, "moduleId");
            return (Criteria) this;
        }

        public Criteria andModuleIdLessThanOrEqualTo(Integer value) {
            addCriterion("module_id <=", value, "moduleId");
            return (Criteria) this;
        }

        public Criteria andModuleIdIn(List<Integer> values) {
            addCriterion("module_id in", values, "moduleId");
            return (Criteria) this;
        }

        public Criteria andModuleIdNotIn(List<Integer> values) {
            addCriterion("module_id not in", values, "moduleId");
            return (Criteria) this;
        }

        public Criteria andModuleIdBetween(Integer value1, Integer value2) {
            addCriterion("module_id between", value1, value2, "moduleId");
            return (Criteria) this;
        }

        public Criteria andModuleIdNotBetween(Integer value1, Integer value2) {
            addCriterion("module_id not between", value1, value2, "moduleId");
            return (Criteria) this;
        }

        public Criteria andOriginIsNull() {
            addCriterion("origin is null");
            return (Criteria) this;
        }

        public Criteria andOriginIsNotNull() {
            addCriterion("origin is not null");
            return (Criteria) this;
        }

        public Criteria andOriginEqualTo(String value) {
            addCriterion("origin =", value, "origin");
            return (Criteria) this;
        }

        public Criteria andOriginNotEqualTo(String value) {
            addCriterion("origin <>", value, "origin");
            return (Criteria) this;
        }

        public Criteria andOriginGreaterThan(String value) {
            addCriterion("origin >", value, "origin");
            return (Criteria) this;
        }

        public Criteria andOriginGreaterThanOrEqualTo(String value) {
            addCriterion("origin >=", value, "origin");
            return (Criteria) this;
        }

        public Criteria andOriginLessThan(String value) {
            addCriterion("origin <", value, "origin");
            return (Criteria) this;
        }

        public Criteria andOriginLessThanOrEqualTo(String value) {
            addCriterion("origin <=", value, "origin");
            return (Criteria) this;
        }

        public Criteria andOriginLike(String value) {
            addCriterion("origin like", value, "origin");
            return (Criteria) this;
        }

        public Criteria andOriginNotLike(String value) {
            addCriterion("origin not like", value, "origin");
            return (Criteria) this;
        }

        public Criteria andOriginIn(List<String> values) {
            addCriterion("origin in", values, "origin");
            return (Criteria) this;
        }

        public Criteria andOriginNotIn(List<String> values) {
            addCriterion("origin not in", values, "origin");
            return (Criteria) this;
        }

        public Criteria andOriginBetween(String value1, String value2) {
            addCriterion("origin between", value1, value2, "origin");
            return (Criteria) this;
        }

        public Criteria andOriginNotBetween(String value1, String value2) {
            addCriterion("origin not between", value1, value2, "origin");
            return (Criteria) this;
        }

        public Criteria andOriginAddressIsNull() {
            addCriterion("origin_address is null");
            return (Criteria) this;
        }

        public Criteria andOriginAddressIsNotNull() {
            addCriterion("origin_address is not null");
            return (Criteria) this;
        }

        public Criteria andOriginAddressEqualTo(String value) {
            addCriterion("origin_address =", value, "originAddress");
            return (Criteria) this;
        }

        public Criteria andOriginAddressNotEqualTo(String value) {
            addCriterion("origin_address <>", value, "originAddress");
            return (Criteria) this;
        }

        public Criteria andOriginAddressGreaterThan(String value) {
            addCriterion("origin_address >", value, "originAddress");
            return (Criteria) this;
        }

        public Criteria andOriginAddressGreaterThanOrEqualTo(String value) {
            addCriterion("origin_address >=", value, "originAddress");
            return (Criteria) this;
        }

        public Criteria andOriginAddressLessThan(String value) {
            addCriterion("origin_address <", value, "originAddress");
            return (Criteria) this;
        }

        public Criteria andOriginAddressLessThanOrEqualTo(String value) {
            addCriterion("origin_address <=", value, "originAddress");
            return (Criteria) this;
        }

        public Criteria andOriginAddressLike(String value) {
            addCriterion("origin_address like", value, "originAddress");
            return (Criteria) this;
        }

        public Criteria andOriginAddressNotLike(String value) {
            addCriterion("origin_address not like", value, "originAddress");
            return (Criteria) this;
        }

        public Criteria andOriginAddressIn(List<String> values) {
            addCriterion("origin_address in", values, "originAddress");
            return (Criteria) this;
        }

        public Criteria andOriginAddressNotIn(List<String> values) {
            addCriterion("origin_address not in", values, "originAddress");
            return (Criteria) this;
        }

        public Criteria andOriginAddressBetween(String value1, String value2) {
            addCriterion("origin_address between", value1, value2, "originAddress");
            return (Criteria) this;
        }

        public Criteria andOriginAddressNotBetween(String value1, String value2) {
            addCriterion("origin_address not between", value1, value2, "originAddress");
            return (Criteria) this;
        }

        public Criteria andAuthorIsNull() {
            addCriterion("author is null");
            return (Criteria) this;
        }

        public Criteria andAuthorIsNotNull() {
            addCriterion("author is not null");
            return (Criteria) this;
        }

        public Criteria andAuthorEqualTo(String value) {
            addCriterion("author =", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorNotEqualTo(String value) {
            addCriterion("author <>", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorGreaterThan(String value) {
            addCriterion("author >", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorGreaterThanOrEqualTo(String value) {
            addCriterion("author >=", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorLessThan(String value) {
            addCriterion("author <", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorLessThanOrEqualTo(String value) {
            addCriterion("author <=", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorLike(String value) {
            addCriterion("author like", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorNotLike(String value) {
            addCriterion("author not like", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorIn(List<String> values) {
            addCriterion("author in", values, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorNotIn(List<String> values) {
            addCriterion("author not in", values, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorBetween(String value1, String value2) {
            addCriterion("author between", value1, value2, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorNotBetween(String value1, String value2) {
            addCriterion("author not between", value1, value2, "author");
            return (Criteria) this;
        }

        public Criteria andSummaryIsNull() {
            addCriterion("summary is null");
            return (Criteria) this;
        }

        public Criteria andSummaryIsNotNull() {
            addCriterion("summary is not null");
            return (Criteria) this;
        }

        public Criteria andSummaryEqualTo(String value) {
            addCriterion("summary =", value, "summary");
            return (Criteria) this;
        }

        public Criteria andSummaryNotEqualTo(String value) {
            addCriterion("summary <>", value, "summary");
            return (Criteria) this;
        }

        public Criteria andSummaryGreaterThan(String value) {
            addCriterion("summary >", value, "summary");
            return (Criteria) this;
        }

        public Criteria andSummaryGreaterThanOrEqualTo(String value) {
            addCriterion("summary >=", value, "summary");
            return (Criteria) this;
        }

        public Criteria andSummaryLessThan(String value) {
            addCriterion("summary <", value, "summary");
            return (Criteria) this;
        }

        public Criteria andSummaryLessThanOrEqualTo(String value) {
            addCriterion("summary <=", value, "summary");
            return (Criteria) this;
        }

        public Criteria andSummaryLike(String value) {
            addCriterion("summary like", value, "summary");
            return (Criteria) this;
        }

        public Criteria andSummaryNotLike(String value) {
            addCriterion("summary not like", value, "summary");
            return (Criteria) this;
        }

        public Criteria andSummaryIn(List<String> values) {
            addCriterion("summary in", values, "summary");
            return (Criteria) this;
        }

        public Criteria andSummaryNotIn(List<String> values) {
            addCriterion("summary not in", values, "summary");
            return (Criteria) this;
        }

        public Criteria andSummaryBetween(String value1, String value2) {
            addCriterion("summary between", value1, value2, "summary");
            return (Criteria) this;
        }

        public Criteria andSummaryNotBetween(String value1, String value2) {
            addCriterion("summary not between", value1, value2, "summary");
            return (Criteria) this;
        }

        public Criteria andCoverImageIsNull() {
            addCriterion("cover_image is null");
            return (Criteria) this;
        }

        public Criteria andCoverImageIsNotNull() {
            addCriterion("cover_image is not null");
            return (Criteria) this;
        }

        public Criteria andCoverImageEqualTo(String value) {
            addCriterion("cover_image =", value, "coverImage");
            return (Criteria) this;
        }

        public Criteria andCoverImageNotEqualTo(String value) {
            addCriterion("cover_image <>", value, "coverImage");
            return (Criteria) this;
        }

        public Criteria andCoverImageGreaterThan(String value) {
            addCriterion("cover_image >", value, "coverImage");
            return (Criteria) this;
        }

        public Criteria andCoverImageGreaterThanOrEqualTo(String value) {
            addCriterion("cover_image >=", value, "coverImage");
            return (Criteria) this;
        }

        public Criteria andCoverImageLessThan(String value) {
            addCriterion("cover_image <", value, "coverImage");
            return (Criteria) this;
        }

        public Criteria andCoverImageLessThanOrEqualTo(String value) {
            addCriterion("cover_image <=", value, "coverImage");
            return (Criteria) this;
        }

        public Criteria andCoverImageLike(String value) {
            addCriterion("cover_image like", value, "coverImage");
            return (Criteria) this;
        }

        public Criteria andCoverImageNotLike(String value) {
            addCriterion("cover_image not like", value, "coverImage");
            return (Criteria) this;
        }

        public Criteria andCoverImageIn(List<String> values) {
            addCriterion("cover_image in", values, "coverImage");
            return (Criteria) this;
        }

        public Criteria andCoverImageNotIn(List<String> values) {
            addCriterion("cover_image not in", values, "coverImage");
            return (Criteria) this;
        }

        public Criteria andCoverImageBetween(String value1, String value2) {
            addCriterion("cover_image between", value1, value2, "coverImage");
            return (Criteria) this;
        }

        public Criteria andCoverImageNotBetween(String value1, String value2) {
            addCriterion("cover_image not between", value1, value2, "coverImage");
            return (Criteria) this;
        }

        public Criteria andThumbnailIsNull() {
            addCriterion("thumbnail is null");
            return (Criteria) this;
        }

        public Criteria andThumbnailIsNotNull() {
            addCriterion("thumbnail is not null");
            return (Criteria) this;
        }

        public Criteria andThumbnailEqualTo(String value) {
            addCriterion("thumbnail =", value, "thumbnail");
            return (Criteria) this;
        }

        public Criteria andThumbnailNotEqualTo(String value) {
            addCriterion("thumbnail <>", value, "thumbnail");
            return (Criteria) this;
        }

        public Criteria andThumbnailGreaterThan(String value) {
            addCriterion("thumbnail >", value, "thumbnail");
            return (Criteria) this;
        }

        public Criteria andThumbnailGreaterThanOrEqualTo(String value) {
            addCriterion("thumbnail >=", value, "thumbnail");
            return (Criteria) this;
        }

        public Criteria andThumbnailLessThan(String value) {
            addCriterion("thumbnail <", value, "thumbnail");
            return (Criteria) this;
        }

        public Criteria andThumbnailLessThanOrEqualTo(String value) {
            addCriterion("thumbnail <=", value, "thumbnail");
            return (Criteria) this;
        }

        public Criteria andThumbnailLike(String value) {
            addCriterion("thumbnail like", value, "thumbnail");
            return (Criteria) this;
        }

        public Criteria andThumbnailNotLike(String value) {
            addCriterion("thumbnail not like", value, "thumbnail");
            return (Criteria) this;
        }

        public Criteria andThumbnailIn(List<String> values) {
            addCriterion("thumbnail in", values, "thumbnail");
            return (Criteria) this;
        }

        public Criteria andThumbnailNotIn(List<String> values) {
            addCriterion("thumbnail not in", values, "thumbnail");
            return (Criteria) this;
        }

        public Criteria andThumbnailBetween(String value1, String value2) {
            addCriterion("thumbnail between", value1, value2, "thumbnail");
            return (Criteria) this;
        }

        public Criteria andThumbnailNotBetween(String value1, String value2) {
            addCriterion("thumbnail not between", value1, value2, "thumbnail");
            return (Criteria) this;
        }

        public Criteria andPublishTimeIsNull() {
            addCriterion("publish_time is null");
            return (Criteria) this;
        }

        public Criteria andPublishTimeIsNotNull() {
            addCriterion("publish_time is not null");
            return (Criteria) this;
        }

        public Criteria andPublishTimeEqualTo(Date value) {
            addCriterion("publish_time =", value, "publishTime");
            return (Criteria) this;
        }

        public Criteria andPublishTimeNotEqualTo(Date value) {
            addCriterion("publish_time <>", value, "publishTime");
            return (Criteria) this;
        }

        public Criteria andPublishTimeGreaterThan(Date value) {
            addCriterion("publish_time >", value, "publishTime");
            return (Criteria) this;
        }

        public Criteria andPublishTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("publish_time >=", value, "publishTime");
            return (Criteria) this;
        }

        public Criteria andPublishTimeLessThan(Date value) {
            addCriterion("publish_time <", value, "publishTime");
            return (Criteria) this;
        }

        public Criteria andPublishTimeLessThanOrEqualTo(Date value) {
            addCriterion("publish_time <=", value, "publishTime");
            return (Criteria) this;
        }

        public Criteria andPublishTimeIn(List<Date> values) {
            addCriterion("publish_time in", values, "publishTime");
            return (Criteria) this;
        }

        public Criteria andPublishTimeNotIn(List<Date> values) {
            addCriterion("publish_time not in", values, "publishTime");
            return (Criteria) this;
        }

        public Criteria andPublishTimeBetween(Date value1, Date value2) {
            addCriterion("publish_time between", value1, value2, "publishTime");
            return (Criteria) this;
        }

        public Criteria andPublishTimeNotBetween(Date value1, Date value2) {
            addCriterion("publish_time not between", value1, value2, "publishTime");
            return (Criteria) this;
        }

        public Criteria andClickCountIsNull() {
            addCriterion("click_count is null");
            return (Criteria) this;
        }

        public Criteria andClickCountIsNotNull() {
            addCriterion("click_count is not null");
            return (Criteria) this;
        }

        public Criteria andClickCountEqualTo(Integer value) {
            addCriterion("click_count =", value, "clickCount");
            return (Criteria) this;
        }

        public Criteria andClickCountNotEqualTo(Integer value) {
            addCriterion("click_count <>", value, "clickCount");
            return (Criteria) this;
        }

        public Criteria andClickCountGreaterThan(Integer value) {
            addCriterion("click_count >", value, "clickCount");
            return (Criteria) this;
        }

        public Criteria andClickCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("click_count >=", value, "clickCount");
            return (Criteria) this;
        }

        public Criteria andClickCountLessThan(Integer value) {
            addCriterion("click_count <", value, "clickCount");
            return (Criteria) this;
        }

        public Criteria andClickCountLessThanOrEqualTo(Integer value) {
            addCriterion("click_count <=", value, "clickCount");
            return (Criteria) this;
        }

        public Criteria andClickCountIn(List<Integer> values) {
            addCriterion("click_count in", values, "clickCount");
            return (Criteria) this;
        }

        public Criteria andClickCountNotIn(List<Integer> values) {
            addCriterion("click_count not in", values, "clickCount");
            return (Criteria) this;
        }

        public Criteria andClickCountBetween(Integer value1, Integer value2) {
            addCriterion("click_count between", value1, value2, "clickCount");
            return (Criteria) this;
        }

        public Criteria andClickCountNotBetween(Integer value1, Integer value2) {
            addCriterion("click_count not between", value1, value2, "clickCount");
            return (Criteria) this;
        }

        public Criteria andLikeCountIsNull() {
            addCriterion("like_count is null");
            return (Criteria) this;
        }

        public Criteria andLikeCountIsNotNull() {
            addCriterion("like_count is not null");
            return (Criteria) this;
        }

        public Criteria andLikeCountEqualTo(Integer value) {
            addCriterion("like_count =", value, "likeCount");
            return (Criteria) this;
        }

        public Criteria andLikeCountNotEqualTo(Integer value) {
            addCriterion("like_count <>", value, "likeCount");
            return (Criteria) this;
        }

        public Criteria andLikeCountGreaterThan(Integer value) {
            addCriterion("like_count >", value, "likeCount");
            return (Criteria) this;
        }

        public Criteria andLikeCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("like_count >=", value, "likeCount");
            return (Criteria) this;
        }

        public Criteria andLikeCountLessThan(Integer value) {
            addCriterion("like_count <", value, "likeCount");
            return (Criteria) this;
        }

        public Criteria andLikeCountLessThanOrEqualTo(Integer value) {
            addCriterion("like_count <=", value, "likeCount");
            return (Criteria) this;
        }

        public Criteria andLikeCountIn(List<Integer> values) {
            addCriterion("like_count in", values, "likeCount");
            return (Criteria) this;
        }

        public Criteria andLikeCountNotIn(List<Integer> values) {
            addCriterion("like_count not in", values, "likeCount");
            return (Criteria) this;
        }

        public Criteria andLikeCountBetween(Integer value1, Integer value2) {
            addCriterion("like_count between", value1, value2, "likeCount");
            return (Criteria) this;
        }

        public Criteria andLikeCountNotBetween(Integer value1, Integer value2) {
            addCriterion("like_count not between", value1, value2, "likeCount");
            return (Criteria) this;
        }

        public Criteria andDislikeCountIsNull() {
            addCriterion("dislike_count is null");
            return (Criteria) this;
        }

        public Criteria andDislikeCountIsNotNull() {
            addCriterion("dislike_count is not null");
            return (Criteria) this;
        }

        public Criteria andDislikeCountEqualTo(Integer value) {
            addCriterion("dislike_count =", value, "dislikeCount");
            return (Criteria) this;
        }

        public Criteria andDislikeCountNotEqualTo(Integer value) {
            addCriterion("dislike_count <>", value, "dislikeCount");
            return (Criteria) this;
        }

        public Criteria andDislikeCountGreaterThan(Integer value) {
            addCriterion("dislike_count >", value, "dislikeCount");
            return (Criteria) this;
        }

        public Criteria andDislikeCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("dislike_count >=", value, "dislikeCount");
            return (Criteria) this;
        }

        public Criteria andDislikeCountLessThan(Integer value) {
            addCriterion("dislike_count <", value, "dislikeCount");
            return (Criteria) this;
        }

        public Criteria andDislikeCountLessThanOrEqualTo(Integer value) {
            addCriterion("dislike_count <=", value, "dislikeCount");
            return (Criteria) this;
        }

        public Criteria andDislikeCountIn(List<Integer> values) {
            addCriterion("dislike_count in", values, "dislikeCount");
            return (Criteria) this;
        }

        public Criteria andDislikeCountNotIn(List<Integer> values) {
            addCriterion("dislike_count not in", values, "dislikeCount");
            return (Criteria) this;
        }

        public Criteria andDislikeCountBetween(Integer value1, Integer value2) {
            addCriterion("dislike_count between", value1, value2, "dislikeCount");
            return (Criteria) this;
        }

        public Criteria andDislikeCountNotBetween(Integer value1, Integer value2) {
            addCriterion("dislike_count not between", value1, value2, "dislikeCount");
            return (Criteria) this;
        }

        public Criteria andSortNoIsNull() {
            addCriterion("sort_no is null");
            return (Criteria) this;
        }

        public Criteria andSortNoIsNotNull() {
            addCriterion("sort_no is not null");
            return (Criteria) this;
        }

        public Criteria andSortNoEqualTo(Integer value) {
            addCriterion("sort_no =", value, "sortNo");
            return (Criteria) this;
        }

        public Criteria andSortNoNotEqualTo(Integer value) {
            addCriterion("sort_no <>", value, "sortNo");
            return (Criteria) this;
        }

        public Criteria andSortNoGreaterThan(Integer value) {
            addCriterion("sort_no >", value, "sortNo");
            return (Criteria) this;
        }

        public Criteria andSortNoGreaterThanOrEqualTo(Integer value) {
            addCriterion("sort_no >=", value, "sortNo");
            return (Criteria) this;
        }

        public Criteria andSortNoLessThan(Integer value) {
            addCriterion("sort_no <", value, "sortNo");
            return (Criteria) this;
        }

        public Criteria andSortNoLessThanOrEqualTo(Integer value) {
            addCriterion("sort_no <=", value, "sortNo");
            return (Criteria) this;
        }

        public Criteria andSortNoIn(List<Integer> values) {
            addCriterion("sort_no in", values, "sortNo");
            return (Criteria) this;
        }

        public Criteria andSortNoNotIn(List<Integer> values) {
            addCriterion("sort_no not in", values, "sortNo");
            return (Criteria) this;
        }

        public Criteria andSortNoBetween(Integer value1, Integer value2) {
            addCriterion("sort_no between", value1, value2, "sortNo");
            return (Criteria) this;
        }

        public Criteria andSortNoNotBetween(Integer value1, Integer value2) {
            addCriterion("sort_no not between", value1, value2, "sortNo");
            return (Criteria) this;
        }

        public Criteria andCommentFlagIsNull() {
            addCriterion("comment_flag is null");
            return (Criteria) this;
        }

        public Criteria andCommentFlagIsNotNull() {
            addCriterion("comment_flag is not null");
            return (Criteria) this;
        }

        public Criteria andCommentFlagEqualTo(Integer value) {
            addCriterion("comment_flag =", value, "commentFlag");
            return (Criteria) this;
        }

        public Criteria andCommentFlagNotEqualTo(Integer value) {
            addCriterion("comment_flag <>", value, "commentFlag");
            return (Criteria) this;
        }

        public Criteria andCommentFlagGreaterThan(Integer value) {
            addCriterion("comment_flag >", value, "commentFlag");
            return (Criteria) this;
        }

        public Criteria andCommentFlagGreaterThanOrEqualTo(Integer value) {
            addCriterion("comment_flag >=", value, "commentFlag");
            return (Criteria) this;
        }

        public Criteria andCommentFlagLessThan(Integer value) {
            addCriterion("comment_flag <", value, "commentFlag");
            return (Criteria) this;
        }

        public Criteria andCommentFlagLessThanOrEqualTo(Integer value) {
            addCriterion("comment_flag <=", value, "commentFlag");
            return (Criteria) this;
        }

        public Criteria andCommentFlagIn(List<Integer> values) {
            addCriterion("comment_flag in", values, "commentFlag");
            return (Criteria) this;
        }

        public Criteria andCommentFlagNotIn(List<Integer> values) {
            addCriterion("comment_flag not in", values, "commentFlag");
            return (Criteria) this;
        }

        public Criteria andCommentFlagBetween(Integer value1, Integer value2) {
            addCriterion("comment_flag between", value1, value2, "commentFlag");
            return (Criteria) this;
        }

        public Criteria andCommentFlagNotBetween(Integer value1, Integer value2) {
            addCriterion("comment_flag not between", value1, value2, "commentFlag");
            return (Criteria) this;
        }

        public Criteria andVerifyAdminNameIsNull() {
            addCriterion("verify_admin_name is null");
            return (Criteria) this;
        }

        public Criteria andVerifyAdminNameIsNotNull() {
            addCriterion("verify_admin_name is not null");
            return (Criteria) this;
        }

        public Criteria andVerifyAdminNameEqualTo(String value) {
            addCriterion("verify_admin_name =", value, "verifyAdminName");
            return (Criteria) this;
        }

        public Criteria andVerifyAdminNameNotEqualTo(String value) {
            addCriterion("verify_admin_name <>", value, "verifyAdminName");
            return (Criteria) this;
        }

        public Criteria andVerifyAdminNameGreaterThan(String value) {
            addCriterion("verify_admin_name >", value, "verifyAdminName");
            return (Criteria) this;
        }

        public Criteria andVerifyAdminNameGreaterThanOrEqualTo(String value) {
            addCriterion("verify_admin_name >=", value, "verifyAdminName");
            return (Criteria) this;
        }

        public Criteria andVerifyAdminNameLessThan(String value) {
            addCriterion("verify_admin_name <", value, "verifyAdminName");
            return (Criteria) this;
        }

        public Criteria andVerifyAdminNameLessThanOrEqualTo(String value) {
            addCriterion("verify_admin_name <=", value, "verifyAdminName");
            return (Criteria) this;
        }

        public Criteria andVerifyAdminNameLike(String value) {
            addCriterion("verify_admin_name like", value, "verifyAdminName");
            return (Criteria) this;
        }

        public Criteria andVerifyAdminNameNotLike(String value) {
            addCriterion("verify_admin_name not like", value, "verifyAdminName");
            return (Criteria) this;
        }

        public Criteria andVerifyAdminNameIn(List<String> values) {
            addCriterion("verify_admin_name in", values, "verifyAdminName");
            return (Criteria) this;
        }

        public Criteria andVerifyAdminNameNotIn(List<String> values) {
            addCriterion("verify_admin_name not in", values, "verifyAdminName");
            return (Criteria) this;
        }

        public Criteria andVerifyAdminNameBetween(String value1, String value2) {
            addCriterion("verify_admin_name between", value1, value2, "verifyAdminName");
            return (Criteria) this;
        }

        public Criteria andVerifyAdminNameNotBetween(String value1, String value2) {
            addCriterion("verify_admin_name not between", value1, value2, "verifyAdminName");
            return (Criteria) this;
        }

        public Criteria andVerifyTimeIsNull() {
            addCriterion("verify_time is null");
            return (Criteria) this;
        }

        public Criteria andVerifyTimeIsNotNull() {
            addCriterion("verify_time is not null");
            return (Criteria) this;
        }

        public Criteria andVerifyTimeEqualTo(Date value) {
            addCriterion("verify_time =", value, "verifyTime");
            return (Criteria) this;
        }

        public Criteria andVerifyTimeNotEqualTo(Date value) {
            addCriterion("verify_time <>", value, "verifyTime");
            return (Criteria) this;
        }

        public Criteria andVerifyTimeGreaterThan(Date value) {
            addCriterion("verify_time >", value, "verifyTime");
            return (Criteria) this;
        }

        public Criteria andVerifyTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("verify_time >=", value, "verifyTime");
            return (Criteria) this;
        }

        public Criteria andVerifyTimeLessThan(Date value) {
            addCriterion("verify_time <", value, "verifyTime");
            return (Criteria) this;
        }

        public Criteria andVerifyTimeLessThanOrEqualTo(Date value) {
            addCriterion("verify_time <=", value, "verifyTime");
            return (Criteria) this;
        }

        public Criteria andVerifyTimeIn(List<Date> values) {
            addCriterion("verify_time in", values, "verifyTime");
            return (Criteria) this;
        }

        public Criteria andVerifyTimeNotIn(List<Date> values) {
            addCriterion("verify_time not in", values, "verifyTime");
            return (Criteria) this;
        }

        public Criteria andVerifyTimeBetween(Date value1, Date value2) {
            addCriterion("verify_time between", value1, value2, "verifyTime");
            return (Criteria) this;
        }

        public Criteria andVerifyTimeNotBetween(Date value1, Date value2) {
            addCriterion("verify_time not between", value1, value2, "verifyTime");
            return (Criteria) this;
        }

        public Criteria andArticleStateIsNull() {
            addCriterion("article_state is null");
            return (Criteria) this;
        }

        public Criteria andArticleStateIsNotNull() {
            addCriterion("article_state is not null");
            return (Criteria) this;
        }

        public Criteria andArticleStateEqualTo(Integer value) {
            addCriterion("article_state =", value, "articleState");
            return (Criteria) this;
        }

        public Criteria andArticleStateNotEqualTo(Integer value) {
            addCriterion("article_state <>", value, "articleState");
            return (Criteria) this;
        }

        public Criteria andArticleStateGreaterThan(Integer value) {
            addCriterion("article_state >", value, "articleState");
            return (Criteria) this;
        }

        public Criteria andArticleStateGreaterThanOrEqualTo(Integer value) {
            addCriterion("article_state >=", value, "articleState");
            return (Criteria) this;
        }

        public Criteria andArticleStateLessThan(Integer value) {
            addCriterion("article_state <", value, "articleState");
            return (Criteria) this;
        }

        public Criteria andArticleStateLessThanOrEqualTo(Integer value) {
            addCriterion("article_state <=", value, "articleState");
            return (Criteria) this;
        }

        public Criteria andArticleStateIn(List<Integer> values) {
            addCriterion("article_state in", values, "articleState");
            return (Criteria) this;
        }

        public Criteria andArticleStateNotIn(List<Integer> values) {
            addCriterion("article_state not in", values, "articleState");
            return (Criteria) this;
        }

        public Criteria andArticleStateBetween(Integer value1, Integer value2) {
            addCriterion("article_state between", value1, value2, "articleState");
            return (Criteria) this;
        }

        public Criteria andArticleStateNotBetween(Integer value1, Integer value2) {
            addCriterion("article_state not between", value1, value2, "articleState");
            return (Criteria) this;
        }

        public Criteria andPublishTypeIsNull() {
            addCriterion("publish_type is null");
            return (Criteria) this;
        }

        public Criteria andPublishTypeIsNotNull() {
            addCriterion("publish_type is not null");
            return (Criteria) this;
        }

        public Criteria andPublishTypeEqualTo(Integer value) {
            addCriterion("publish_type =", value, "publishType");
            return (Criteria) this;
        }

        public Criteria andPublishTypeNotEqualTo(Integer value) {
            addCriterion("publish_type <>", value, "publishType");
            return (Criteria) this;
        }

        public Criteria andPublishTypeGreaterThan(Integer value) {
            addCriterion("publish_type >", value, "publishType");
            return (Criteria) this;
        }

        public Criteria andPublishTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("publish_type >=", value, "publishType");
            return (Criteria) this;
        }

        public Criteria andPublishTypeLessThan(Integer value) {
            addCriterion("publish_type <", value, "publishType");
            return (Criteria) this;
        }

        public Criteria andPublishTypeLessThanOrEqualTo(Integer value) {
            addCriterion("publish_type <=", value, "publishType");
            return (Criteria) this;
        }

        public Criteria andPublishTypeIn(List<Integer> values) {
            addCriterion("publish_type in", values, "publishType");
            return (Criteria) this;
        }

        public Criteria andPublishTypeNotIn(List<Integer> values) {
            addCriterion("publish_type not in", values, "publishType");
            return (Criteria) this;
        }

        public Criteria andPublishTypeBetween(Integer value1, Integer value2) {
            addCriterion("publish_type between", value1, value2, "publishType");
            return (Criteria) this;
        }

        public Criteria andPublishTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("publish_type not between", value1, value2, "publishType");
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

        public Criteria andTagIsNull() {
            addCriterion("tag is null");
            return (Criteria) this;
        }

        public Criteria andTagIsNotNull() {
            addCriterion("tag is not null");
            return (Criteria) this;
        }

        public Criteria andTagEqualTo(String value) {
            addCriterion("tag =", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagNotEqualTo(String value) {
            addCriterion("tag <>", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagGreaterThan(String value) {
            addCriterion("tag >", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagGreaterThanOrEqualTo(String value) {
            addCriterion("tag >=", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagLessThan(String value) {
            addCriterion("tag <", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagLessThanOrEqualTo(String value) {
            addCriterion("tag <=", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagLike(String value) {
            addCriterion("tag like", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagNotLike(String value) {
            addCriterion("tag not like", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagIn(List<String> values) {
            addCriterion("tag in", values, "tag");
            return (Criteria) this;
        }

        public Criteria andTagNotIn(List<String> values) {
            addCriterion("tag not in", values, "tag");
            return (Criteria) this;
        }

        public Criteria andTagBetween(String value1, String value2) {
            addCriterion("tag between", value1, value2, "tag");
            return (Criteria) this;
        }

        public Criteria andTagNotBetween(String value1, String value2) {
            addCriterion("tag not between", value1, value2, "tag");
            return (Criteria) this;
        }

        public Criteria andUrlIsNull() {
            addCriterion("url is null");
            return (Criteria) this;
        }

        public Criteria andUrlIsNotNull() {
            addCriterion("url is not null");
            return (Criteria) this;
        }

        public Criteria andUrlEqualTo(String value) {
            addCriterion("url =", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotEqualTo(String value) {
            addCriterion("url <>", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlGreaterThan(String value) {
            addCriterion("url >", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlGreaterThanOrEqualTo(String value) {
            addCriterion("url >=", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlLessThan(String value) {
            addCriterion("url <", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlLessThanOrEqualTo(String value) {
            addCriterion("url <=", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlLike(String value) {
            addCriterion("url like", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotLike(String value) {
            addCriterion("url not like", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlIn(List<String> values) {
            addCriterion("url in", values, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotIn(List<String> values) {
            addCriterion("url not in", values, "url");
            return (Criteria) this;
        }

        public Criteria andUrlBetween(String value1, String value2) {
            addCriterion("url between", value1, value2, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotBetween(String value1, String value2) {
            addCriterion("url not between", value1, value2, "url");
            return (Criteria) this;
        }

        public Criteria andCommentCountIsNull() {
            addCriterion("comment_count is null");
            return (Criteria) this;
        }

        public Criteria andCommentCountIsNotNull() {
            addCriterion("comment_count is not null");
            return (Criteria) this;
        }

        public Criteria andCommentCountEqualTo(Integer value) {
            addCriterion("comment_count =", value, "commentCount");
            return (Criteria) this;
        }

        public Criteria andCommentCountNotEqualTo(Integer value) {
            addCriterion("comment_count <>", value, "commentCount");
            return (Criteria) this;
        }

        public Criteria andCommentCountGreaterThan(Integer value) {
            addCriterion("comment_count >", value, "commentCount");
            return (Criteria) this;
        }

        public Criteria andCommentCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("comment_count >=", value, "commentCount");
            return (Criteria) this;
        }

        public Criteria andCommentCountLessThan(Integer value) {
            addCriterion("comment_count <", value, "commentCount");
            return (Criteria) this;
        }

        public Criteria andCommentCountLessThanOrEqualTo(Integer value) {
            addCriterion("comment_count <=", value, "commentCount");
            return (Criteria) this;
        }

        public Criteria andCommentCountIn(List<Integer> values) {
            addCriterion("comment_count in", values, "commentCount");
            return (Criteria) this;
        }

        public Criteria andCommentCountNotIn(List<Integer> values) {
            addCriterion("comment_count not in", values, "commentCount");
            return (Criteria) this;
        }

        public Criteria andCommentCountBetween(Integer value1, Integer value2) {
            addCriterion("comment_count between", value1, value2, "commentCount");
            return (Criteria) this;
        }

        public Criteria andCommentCountNotBetween(Integer value1, Integer value2) {
            addCriterion("comment_count not between", value1, value2, "commentCount");
            return (Criteria) this;
        }

        public Criteria andShareCountIsNull() {
            addCriterion("share_count is null");
            return (Criteria) this;
        }

        public Criteria andShareCountIsNotNull() {
            addCriterion("share_count is not null");
            return (Criteria) this;
        }

        public Criteria andShareCountEqualTo(Integer value) {
            addCriterion("share_count =", value, "shareCount");
            return (Criteria) this;
        }

        public Criteria andShareCountNotEqualTo(Integer value) {
            addCriterion("share_count <>", value, "shareCount");
            return (Criteria) this;
        }

        public Criteria andShareCountGreaterThan(Integer value) {
            addCriterion("share_count >", value, "shareCount");
            return (Criteria) this;
        }

        public Criteria andShareCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("share_count >=", value, "shareCount");
            return (Criteria) this;
        }

        public Criteria andShareCountLessThan(Integer value) {
            addCriterion("share_count <", value, "shareCount");
            return (Criteria) this;
        }

        public Criteria andShareCountLessThanOrEqualTo(Integer value) {
            addCriterion("share_count <=", value, "shareCount");
            return (Criteria) this;
        }

        public Criteria andShareCountIn(List<Integer> values) {
            addCriterion("share_count in", values, "shareCount");
            return (Criteria) this;
        }

        public Criteria andShareCountNotIn(List<Integer> values) {
            addCriterion("share_count not in", values, "shareCount");
            return (Criteria) this;
        }

        public Criteria andShareCountBetween(Integer value1, Integer value2) {
            addCriterion("share_count between", value1, value2, "shareCount");
            return (Criteria) this;
        }

        public Criteria andShareCountNotBetween(Integer value1, Integer value2) {
            addCriterion("share_count not between", value1, value2, "shareCount");
            return (Criteria) this;
        }

        public Criteria andUnverifyReasonIsNull() {
            addCriterion("unverify_reason is null");
            return (Criteria) this;
        }

        public Criteria andUnverifyReasonIsNotNull() {
            addCriterion("unverify_reason is not null");
            return (Criteria) this;
        }

        public Criteria andUnverifyReasonEqualTo(String value) {
            addCriterion("unverify_reason =", value, "unverifyReason");
            return (Criteria) this;
        }

        public Criteria andUnverifyReasonNotEqualTo(String value) {
            addCriterion("unverify_reason <>", value, "unverifyReason");
            return (Criteria) this;
        }

        public Criteria andUnverifyReasonGreaterThan(String value) {
            addCriterion("unverify_reason >", value, "unverifyReason");
            return (Criteria) this;
        }

        public Criteria andUnverifyReasonGreaterThanOrEqualTo(String value) {
            addCriterion("unverify_reason >=", value, "unverifyReason");
            return (Criteria) this;
        }

        public Criteria andUnverifyReasonLessThan(String value) {
            addCriterion("unverify_reason <", value, "unverifyReason");
            return (Criteria) this;
        }

        public Criteria andUnverifyReasonLessThanOrEqualTo(String value) {
            addCriterion("unverify_reason <=", value, "unverifyReason");
            return (Criteria) this;
        }

        public Criteria andUnverifyReasonLike(String value) {
            addCriterion("unverify_reason like", value, "unverifyReason");
            return (Criteria) this;
        }

        public Criteria andUnverifyReasonNotLike(String value) {
            addCriterion("unverify_reason not like", value, "unverifyReason");
            return (Criteria) this;
        }

        public Criteria andUnverifyReasonIn(List<String> values) {
            addCriterion("unverify_reason in", values, "unverifyReason");
            return (Criteria) this;
        }

        public Criteria andUnverifyReasonNotIn(List<String> values) {
            addCriterion("unverify_reason not in", values, "unverifyReason");
            return (Criteria) this;
        }

        public Criteria andUnverifyReasonBetween(String value1, String value2) {
            addCriterion("unverify_reason between", value1, value2, "unverifyReason");
            return (Criteria) this;
        }

        public Criteria andUnverifyReasonNotBetween(String value1, String value2) {
            addCriterion("unverify_reason not between", value1, value2, "unverifyReason");
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