package com.mcoding.base.cms.bean.recommendarticle;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mcoding.base.core.IExample;
import com.mcoding.base.core.PageView;
import com.mcoding.base.utils.json.JsonUtilsForMcoding;

/**
 * 推荐文章Example
 * 
 * @author acer
 * 
 */
public class RecommendArticleExample implements IExample<RecommendArticle> {

	protected String orderByClause;

	protected boolean distinct;

	protected List<Criteria> oredCriteria;

	protected PageView<RecommendArticle> pageView;

	public RecommendArticleExample() {
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

		public Criteria andRefIdIsNull() {
			addCriterion("ref_id is null");
			return (Criteria) this;
		}

		public Criteria andRefIdIsNotNull() {
			addCriterion("ref_id is not null");
			return (Criteria) this;
		}

		public Criteria andRefIdEqualTo(Integer value) {
			addCriterion("ref_id =", value, "refId");
			return (Criteria) this;
		}

		public Criteria andRefIdNotEqualTo(Integer value) {
			addCriterion("ref_id <>", value, "refId");
			return (Criteria) this;
		}

		public Criteria andRefIdGreaterThan(Integer value) {
			addCriterion("ref_id >", value, "refId");
			return (Criteria) this;
		}

		public Criteria andRefIdGreaterThanOrEqualTo(Integer value) {
			addCriterion("ref_id >=", value, "refId");
			return (Criteria) this;
		}

		public Criteria andRefIdLessThan(Integer value) {
			addCriterion("ref_id <", value, "refId");
			return (Criteria) this;
		}

		public Criteria andRefIdLessThanOrEqualTo(Integer value) {
			addCriterion("ref_id <=", value, "refId");
			return (Criteria) this;
		}

		public Criteria andRefIdIn(List<Integer> values) {
			addCriterion("ref_id in", values, "refId");
			return (Criteria) this;
		}

		public Criteria andRefIdNotIn(List<Integer> values) {
			addCriterion("ref_id not in", values, "refId");
			return (Criteria) this;
		}

		public Criteria andRefIdBetween(Integer value1, Integer value2) {
			addCriterion("ref_id between", value1, value2, "refId");
			return (Criteria) this;
		}

		public Criteria andRefIdNotBetween(Integer value1, Integer value2) {
			addCriterion("ref_id not between", value1, value2, "refId");
			return (Criteria) this;
		}

		public Criteria andArticleIdIsNull() {
			addCriterion("article_id is null");
			return (Criteria) this;
		}

		public Criteria andArticleIdIsNotNull() {
			addCriterion("article_id is not null");
			return (Criteria) this;
		}

		public Criteria andArticleIdEqualTo(Integer value) {
			addCriterion("article_id =", value, "articleId");
			return (Criteria) this;
		}

		public Criteria andArticleIdNotEqualTo(Integer value) {
			addCriterion("article_id <>", value, "articleId");
			return (Criteria) this;
		}

		public Criteria andArticleIdGreaterThan(Integer value) {
			addCriterion("article_id >", value, "articleId");
			return (Criteria) this;
		}

		public Criteria andArticleIdGreaterThanOrEqualTo(Integer value) {
			addCriterion("article_id >=", value, "articleId");
			return (Criteria) this;
		}

		public Criteria andArticleIdLessThan(Integer value) {
			addCriterion("article_id <", value, "articleId");
			return (Criteria) this;
		}

		public Criteria andArticleIdLessThanOrEqualTo(Integer value) {
			addCriterion("article_id <=", value, "articleId");
			return (Criteria) this;
		}

		public Criteria andArticleIdIn(List<Integer> values) {
			addCriterion("article_id in", values, "articleId");
			return (Criteria) this;
		}

		public Criteria andArticleIdNotIn(List<Integer> values) {
			addCriterion("article_id not in", values, "articleId");
			return (Criteria) this;
		}

		public Criteria andArticleIdBetween(Integer value1, Integer value2) {
			addCriterion("article_id between", value1, value2, "articleId");
			return (Criteria) this;
		}

		public Criteria andArticleIdNotBetween(Integer value1, Integer value2) {
			addCriterion("article_id not between", value1, value2, "articleId");
			return (Criteria) this;
		}

		public Criteria andArticleTitleIsNull() {
			addCriterion("article_title is null");
			return (Criteria) this;
		}

		public Criteria andArticleTitleIsNotNull() {
			addCriterion("article_title is not null");
			return (Criteria) this;
		}

		public Criteria andArticleTitleEqualTo(String value) {
			addCriterion("article_title =", value, "articleTitle");
			return (Criteria) this;
		}

		public Criteria andArticleTitleNotEqualTo(String value) {
			addCriterion("article_title <>", value, "articleTitle");
			return (Criteria) this;
		}

		public Criteria andArticleTitleGreaterThan(String value) {
			addCriterion("article_title >", value, "articleTitle");
			return (Criteria) this;
		}

		public Criteria andArticleTitleGreaterThanOrEqualTo(String value) {
			addCriterion("article_title >=", value, "articleTitle");
			return (Criteria) this;
		}

		public Criteria andArticleTitleLessThan(String value) {
			addCriterion("article_title <", value, "articleTitle");
			return (Criteria) this;
		}

		public Criteria andArticleTitleLessThanOrEqualTo(String value) {
			addCriterion("article_title <=", value, "articleTitle");
			return (Criteria) this;
		}

		public Criteria andArticleTitleLike(String value) {
			addCriterion("article_title like", value, "articleTitle");
			return (Criteria) this;
		}

		public Criteria andArticleTitleNotLike(String value) {
			addCriterion("article_title not like", value, "articleTitle");
			return (Criteria) this;
		}

		public Criteria andArticleTitleIn(List<String> values) {
			addCriterion("article_title in", values, "articleTitle");
			return (Criteria) this;
		}

		public Criteria andArticleTitleNotIn(List<String> values) {
			addCriterion("article_title not in", values, "articleTitle");
			return (Criteria) this;
		}

        public Criteria andArticleTitleBetween(String value1, String value2) {
            addCriterion("article_title between", value1, value2, "articleTitle");
            return (Criteria) this;
        }

        public Criteria andArticleTitleNotBetween(String value1, String value2) {
            addCriterion("article_title not between", value1, value2, "articleTitle");
            return (Criteria) this;
        }

		public Criteria andArticleStitleIsNull() {
			addCriterion("article_stitle is null");
			return (Criteria) this;
		}

		public Criteria andArticleStitleIsNotNull() {
			addCriterion("article_stitle is not null");
			return (Criteria) this;
		}

		public Criteria andArticleStitleEqualTo(String value) {
			addCriterion("article_stitle =", value, "articleStitle");
			return (Criteria) this;
		}

		public Criteria andArticleStitleNotEqualTo(String value) {
			addCriterion("article_stitle <>", value, "articleStitle");
			return (Criteria) this;
		}

		public Criteria andArticleStitleGreaterThan(String value) {
			addCriterion("article_stitle >", value, "articleStitle");
			return (Criteria) this;
		}

		public Criteria andArticleStitleGreaterThanOrEqualTo(String value) {
			addCriterion("article_stitle >=", value, "articleStitle");
			return (Criteria) this;
		}

		public Criteria andArticleStitleLessThan(String value) {
			addCriterion("article_stitle <", value, "articleStitle");
			return (Criteria) this;
		}

		public Criteria andArticleStitleLessThanOrEqualTo(String value) {
			addCriterion("article_stitle <=", value, "articleStitle");
			return (Criteria) this;
		}

		public Criteria andArticleStitleLike(String value) {
			addCriterion("article_stitle like", value, "articleStitle");
			return (Criteria) this;
		}

		public Criteria andArticleStitleNotLike(String value) {
			addCriterion("article_stitle not like", value, "articleStitle");
			return (Criteria) this;
		}

		public Criteria andArticleStitleIn(List<String> values) {
			addCriterion("article_stitle in", values, "articleStitle");
			return (Criteria) this;
		}

		public Criteria andArticleStitleNotIn(List<String> values) {
			addCriterion("article_stitle not in", values, "articleStitle");
			return (Criteria) this;
		}

        public Criteria andArticleStitleBetween(String value1, String value2) {
            addCriterion("article_stitle between", value1, value2, "articleStitle");
            return (Criteria) this;
        }

        public Criteria andArticleStitleNotBetween(String value1, String value2) {
            addCriterion("article_stitle not between", value1, value2, "articleStitle");
            return (Criteria) this;
        }

		public Criteria andArticleLabelIsNull() {
			addCriterion("article_label is null");
			return (Criteria) this;
		}

		public Criteria andArticleLabelIsNotNull() {
			addCriterion("article_label is not null");
			return (Criteria) this;
		}

		public Criteria andArticleLabelEqualTo(String value) {
			addCriterion("article_label =", value, "articleLabel");
			return (Criteria) this;
		}

		public Criteria andArticleLabelNotEqualTo(String value) {
			addCriterion("article_label <>", value, "articleLabel");
			return (Criteria) this;
		}

		public Criteria andArticleLabelGreaterThan(String value) {
			addCriterion("article_label >", value, "articleLabel");
			return (Criteria) this;
		}

		public Criteria andArticleLabelGreaterThanOrEqualTo(String value) {
			addCriterion("article_label >=", value, "articleLabel");
			return (Criteria) this;
		}

		public Criteria andArticleLabelLessThan(String value) {
			addCriterion("article_label <", value, "articleLabel");
			return (Criteria) this;
		}

		public Criteria andArticleLabelLessThanOrEqualTo(String value) {
			addCriterion("article_label <=", value, "articleLabel");
			return (Criteria) this;
		}

		public Criteria andArticleLabelLike(String value) {
			addCriterion("article_label like", value, "articleLabel");
			return (Criteria) this;
		}

		public Criteria andArticleLabelNotLike(String value) {
			addCriterion("article_label not like", value, "articleLabel");
			return (Criteria) this;
		}

		public Criteria andArticleLabelIn(List<String> values) {
			addCriterion("article_label in", values, "articleLabel");
			return (Criteria) this;
		}

        public Criteria andArticleLabelNotIn(List<String> values) {
            addCriterion("article_label not in", values, "articleLabel");
            return (Criteria) this;
        }

        public Criteria andArticleLabelBetween(String value1, String value2) {
            addCriterion("article_label between", value1, value2, "articleLabel");
            return (Criteria) this;
        }

        public Criteria andArticleLabelNotBetween(String value1, String value2) {
            addCriterion("article_label not between", value1, value2, "articleLabel");
            return (Criteria) this;
        }

		public Criteria andArticleAuthorIsNull() {
			addCriterion("article_author is null");
			return (Criteria) this;
		}

		public Criteria andArticleAuthorIsNotNull() {
			addCriterion("article_author is not null");
			return (Criteria) this;
		}

		public Criteria andArticleAuthorEqualTo(String value) {
			addCriterion("article_author =", value, "articleAuthor");
			return (Criteria) this;
		}

		public Criteria andArticleAuthorNotEqualTo(String value) {
			addCriterion("article_author <>", value, "articleAuthor");
			return (Criteria) this;
		}

		public Criteria andArticleAuthorGreaterThan(String value) {
			addCriterion("article_author >", value, "articleAuthor");
			return (Criteria) this;
		}

		public Criteria andArticleAuthorGreaterThanOrEqualTo(String value) {
			addCriterion("article_author >=", value, "articleAuthor");
			return (Criteria) this;
		}

		public Criteria andArticleAuthorLessThan(String value) {
			addCriterion("article_author <", value, "articleAuthor");
			return (Criteria) this;
		}

		public Criteria andArticleAuthorLessThanOrEqualTo(String value) {
			addCriterion("article_author <=", value, "articleAuthor");
			return (Criteria) this;
		}

		public Criteria andArticleAuthorLike(String value) {
			addCriterion("article_author like", value, "articleAuthor");
			return (Criteria) this;
		}

		public Criteria andArticleAuthorNotLike(String value) {
			addCriterion("article_author not like", value, "articleAuthor");
			return (Criteria) this;
		}

		public Criteria andArticleAuthorIn(List<String> values) {
			addCriterion("article_author in", values, "articleAuthor");
			return (Criteria) this;
		}

		public Criteria andArticleAuthorNotIn(List<String> values) {
			addCriterion("article_author not in", values, "articleAuthor");
			return (Criteria) this;
		}

        public Criteria andArticleAuthorBetween(String value1, String value2) {
            addCriterion("article_author between", value1, value2, "articleAuthor");
            return (Criteria) this;
        }

        public Criteria andArticleAuthorNotBetween(String value1, String value2) {
            addCriterion("article_author not between", value1, value2, "articleAuthor");
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

		public Criteria andArticleAbstractIsNull() {
			addCriterion("article_abstract is null");
			return (Criteria) this;
		}

		public Criteria andArticleAbstractIsNotNull() {
			addCriterion("article_abstract is not null");
			return (Criteria) this;
		}

		public Criteria andArticleAbstractEqualTo(String value) {
			addCriterion("article_abstract =", value, "articleAbstract");
			return (Criteria) this;
		}

		public Criteria andArticleAbstractNotEqualTo(String value) {
			addCriterion("article_abstract <>", value, "articleAbstract");
			return (Criteria) this;
		}

		public Criteria andArticleAbstractGreaterThan(String value) {
			addCriterion("article_abstract >", value, "articleAbstract");
			return (Criteria) this;
		}

		public Criteria andArticleAbstractGreaterThanOrEqualTo(String value) {
			addCriterion("article_abstract >=", value, "articleAbstract");
			return (Criteria) this;
		}

		public Criteria andArticleAbstractLessThan(String value) {
			addCriterion("article_abstract <", value, "articleAbstract");
			return (Criteria) this;
		}

		public Criteria andArticleAbstractLessThanOrEqualTo(String value) {
			addCriterion("article_abstract <=", value, "articleAbstract");
			return (Criteria) this;
		}

		public Criteria andArticleAbstractLike(String value) {
			addCriterion("article_abstract like", value, "articleAbstract");
			return (Criteria) this;
		}

		public Criteria andArticleAbstractNotLike(String value) {
			addCriterion("article_abstract not like", value, "articleAbstract");
			return (Criteria) this;
		}

		public Criteria andArticleAbstractIn(List<String> values) {
			addCriterion("article_abstract in", values, "articleAbstract");
			return (Criteria) this;
		}

		public Criteria andArticleAbstractNotIn(List<String> values) {
			addCriterion("article_abstract not in", values, "articleAbstract");
			return (Criteria) this;
		}

        public Criteria andArticleAbstractBetween(String value1, String value2) {
            addCriterion("article_abstract between", value1, value2, "articleAbstract");
            return (Criteria) this;
        }

        public Criteria andArticleAbstractNotBetween(String value1, String value2) {
            addCriterion("article_abstract not between", value1, value2, "articleAbstract");
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

		public Criteria andArticleUrlIsNull() {
			addCriterion("article_url is null");
			return (Criteria) this;
		}

		public Criteria andArticleUrlIsNotNull() {
			addCriterion("article_url is not null");
			return (Criteria) this;
		}

		public Criteria andArticleUrlEqualTo(String value) {
			addCriterion("article_url =", value, "articleUrl");
			return (Criteria) this;
		}

		public Criteria andArticleUrlNotEqualTo(String value) {
			addCriterion("article_url <>", value, "articleUrl");
			return (Criteria) this;
		}

		public Criteria andArticleUrlGreaterThan(String value) {
			addCriterion("article_url >", value, "articleUrl");
			return (Criteria) this;
		}

		public Criteria andArticleUrlGreaterThanOrEqualTo(String value) {
			addCriterion("article_url >=", value, "articleUrl");
			return (Criteria) this;
		}

		public Criteria andArticleUrlLessThan(String value) {
			addCriterion("article_url <", value, "articleUrl");
			return (Criteria) this;
		}

		public Criteria andArticleUrlLessThanOrEqualTo(String value) {
			addCriterion("article_url <=", value, "articleUrl");
			return (Criteria) this;
		}

		public Criteria andArticleUrlLike(String value) {
			addCriterion("article_url like", value, "articleUrl");
			return (Criteria) this;
		}

		public Criteria andArticleUrlNotLike(String value) {
			addCriterion("article_url not like", value, "articleUrl");
			return (Criteria) this;
		}

		public Criteria andArticleUrlIn(List<String> values) {
			addCriterion("article_url in", values, "articleUrl");
			return (Criteria) this;
		}

		public Criteria andArticleUrlNotIn(List<String> values) {
			addCriterion("article_url not in", values, "articleUrl");
			return (Criteria) this;
		}

		public Criteria andArticleUrlBetween(String value1, String value2) {
			addCriterion("article_url between", value1, value2, "articleUrl");
			return (Criteria) this;
		}

        public Criteria andArticleUrlNotBetween(String value1, String value2) {
            addCriterion("article_url not between", value1, value2, "articleUrl");
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

	@Override
	public PageView<RecommendArticle> getPageView() {
		return pageView;
	}

	@Override
	public void setPageView(PageView<RecommendArticle> pageView) {
		this.pageView = pageView;
	}

	@Override
	public String toJson() throws JsonProcessingException {
		return JsonUtilsForMcoding.writeValueAsString(this);
	}
}