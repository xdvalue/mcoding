package com.mcoding.base.sns.bean.post;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mcoding.base.core.IExample;
import com.mcoding.base.core.PageView;
import com.mcoding.base.utils.json.JsonUtilsForMcoding;

public class SnsPostImgExample implements IExample<SnsPostImg> {

	protected String orderByClause;

	protected boolean distinct;

	protected List<Criteria> oredCriteria;

	protected PageView<SnsPostImg> pageView;

	public SnsPostImgExample() {
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

		public Criteria andImgUrlIsNull() {
			addCriterion("img_url is null");
			return (Criteria) this;
		}

		public Criteria andImgUrlIsNotNull() {
			addCriterion("img_url is not null");
			return (Criteria) this;
		}

		public Criteria andImgUrlEqualTo(String value) {
			addCriterion("img_url =", value, "imgUrl");
			return (Criteria) this;
		}

		public Criteria andImgUrlNotEqualTo(String value) {
			addCriterion("img_url <>", value, "imgUrl");
			return (Criteria) this;
		}

		public Criteria andImgUrlGreaterThan(String value) {
			addCriterion("img_url >", value, "imgUrl");
			return (Criteria) this;
		}

		public Criteria andImgUrlGreaterThanOrEqualTo(String value) {
			addCriterion("img_url >=", value, "imgUrl");
			return (Criteria) this;
		}

		public Criteria andImgUrlLessThan(String value) {
			addCriterion("img_url <", value, "imgUrl");
			return (Criteria) this;
		}

		public Criteria andImgUrlLessThanOrEqualTo(String value) {
			addCriterion("img_url <=", value, "imgUrl");
			return (Criteria) this;
		}

		public Criteria andImgUrlLike(String value) {
			addCriterion("img_url like", value, "imgUrl");
			return (Criteria) this;
		}

		public Criteria andImgUrlNotLike(String value) {
			addCriterion("img_url not like", value, "imgUrl");
			return (Criteria) this;
		}

		public Criteria andImgUrlIn(List<String> values) {
			addCriterion("img_url in", values, "imgUrl");
			return (Criteria) this;
		}

		public Criteria andImgUrlNotIn(List<String> values) {
			addCriterion("img_url not in", values, "imgUrl");
			return (Criteria) this;
		}

		public Criteria andImgUrlBetween(String value1, String value2) {
			addCriterion("img_url between", value1, value2, "imgUrl");
			return (Criteria) this;
		}

		public Criteria andImgUrlNotBetween(String value1, String value2) {
			addCriterion("img_url not between", value1, value2, "imgUrl");
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

	@Override
	public PageView<SnsPostImg> getPageView() {
		return pageView;
	}

	@Override
	public void setPageView(PageView<SnsPostImg> pageView) {
		this.pageView = pageView;
	}

	@Override
	public String toJson() throws JsonProcessingException {
		return JsonUtilsForMcoding.writeValueAsString(this);
	}
}