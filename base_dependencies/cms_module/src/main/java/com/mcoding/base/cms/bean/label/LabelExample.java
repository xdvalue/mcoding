package com.mcoding.base.cms.bean.label;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mcoding.base.core.IExample;
import com.mcoding.base.core.PageView;
import com.mcoding.base.utils.json.JsonUtilsForMcoding;

/**
 * 标签Example
 * 
 * @author acer
 * 
 */
public class LabelExample implements IExample<Label> {
	protected String orderByClause;

	protected boolean distinct;

	protected List<Criteria> oredCriteria;

	protected PageView<Label> pageView;

	public LabelExample() {
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

		public Criteria andMarkIsNull() {
			addCriterion("mark is null");
			return (Criteria) this;
		}

		public Criteria andMarkIsNotNull() {
			addCriterion("mark is not null");
			return (Criteria) this;
		}

		public Criteria andMarkEqualTo(String value) {
			addCriterion("mark =", value, "mark");
			return (Criteria) this;
		}

		public Criteria andMarkNotEqualTo(String value) {
			addCriterion("mark <>", value, "mark");
			return (Criteria) this;
		}

		public Criteria andMarkGreaterThan(String value) {
			addCriterion("mark >", value, "mark");
			return (Criteria) this;
		}

		public Criteria andMarkGreaterThanOrEqualTo(String value) {
			addCriterion("mark >=", value, "mark");
			return (Criteria) this;
		}

		public Criteria andMarkLessThan(String value) {
			addCriterion("mark <", value, "mark");
			return (Criteria) this;
		}

		public Criteria andMarkLessThanOrEqualTo(String value) {
			addCriterion("mark <=", value, "mark");
			return (Criteria) this;
		}

		public Criteria andMarkLike(String value) {
			addCriterion("mark like", value, "mark");
			return (Criteria) this;
		}

		public Criteria andMarkNotLike(String value) {
			addCriterion("mark not like", value, "mark");
			return (Criteria) this;
		}

		public Criteria andMarkIn(List<String> values) {
			addCriterion("mark in", values, "mark");
			return (Criteria) this;
		}

		public Criteria andMarkNotIn(List<String> values) {
			addCriterion("mark not in", values, "mark");
			return (Criteria) this;
		}

		public Criteria andMarkBetween(String value1, String value2) {
			addCriterion("mark between", value1, value2, "mark");
			return (Criteria) this;
		}

		public Criteria andMarkNotBetween(String value1, String value2) {
			addCriterion("mark not between", value1, value2, "mark");
			return (Criteria) this;
		}

		public Criteria andHitIsNull() {
			addCriterion("hit is null");
			return (Criteria) this;
		}

		public Criteria andHitIsNotNull() {
			addCriterion("hit is not null");
			return (Criteria) this;
		}

		public Criteria andHitEqualTo(Integer value) {
			addCriterion("hit =", value, "hit");
			return (Criteria) this;
		}

		public Criteria andHitNotEqualTo(Integer value) {
			addCriterion("hit <>", value, "hit");
			return (Criteria) this;
		}

		public Criteria andHitGreaterThan(Integer value) {
			addCriterion("hit >", value, "hit");
			return (Criteria) this;
		}

		public Criteria andHitGreaterThanOrEqualTo(Integer value) {
			addCriterion("hit >=", value, "hit");
			return (Criteria) this;
		}

		public Criteria andHitLessThan(Integer value) {
			addCriterion("hit <", value, "hit");
			return (Criteria) this;
		}

		public Criteria andHitLessThanOrEqualTo(Integer value) {
			addCriterion("hit <=", value, "hit");
			return (Criteria) this;
		}

		public Criteria andHitIn(List<Integer> values) {
			addCriterion("hit in", values, "hit");
			return (Criteria) this;
		}

		public Criteria andHitNotIn(List<Integer> values) {
			addCriterion("hit not in", values, "hit");
			return (Criteria) this;
		}

		public Criteria andHitBetween(Integer value1, Integer value2) {
			addCriterion("hit between", value1, value2, "hit");
			return (Criteria) this;
		}

		public Criteria andHitNotBetween(Integer value1, Integer value2) {
			addCriterion("hit not between", value1, value2, "hit");
			return (Criteria) this;
		}

		public Criteria andSeqNumIsNull() {
			addCriterion("seq_num is null");
			return (Criteria) this;
		}

		public Criteria andSeqNumIsNotNull() {
			addCriterion("seq_num is not null");
			return (Criteria) this;
		}

		public Criteria andSeqNumEqualTo(Integer value) {
			addCriterion("seq_num =", value, "seqNum");
			return (Criteria) this;
		}

		public Criteria andSeqNumNotEqualTo(Integer value) {
			addCriterion("seq_num <>", value, "seqNum");
			return (Criteria) this;
		}

		public Criteria andSeqNumGreaterThan(Integer value) {
			addCriterion("seq_num >", value, "seqNum");
			return (Criteria) this;
		}

		public Criteria andSeqNumGreaterThanOrEqualTo(Integer value) {
			addCriterion("seq_num >=", value, "seqNum");
			return (Criteria) this;
		}

		public Criteria andSeqNumLessThan(Integer value) {
			addCriterion("seq_num <", value, "seqNum");
			return (Criteria) this;
		}

		public Criteria andSeqNumLessThanOrEqualTo(Integer value) {
			addCriterion("seq_num <=", value, "seqNum");
			return (Criteria) this;
		}

		public Criteria andSeqNumIn(List<Integer> values) {
			addCriterion("seq_num in", values, "seqNum");
			return (Criteria) this;
		}

		public Criteria andSeqNumNotIn(List<Integer> values) {
			addCriterion("seq_num not in", values, "seqNum");
			return (Criteria) this;
		}

		public Criteria andSeqNumBetween(Integer value1, Integer value2) {
			addCriterion("seq_num between", value1, value2, "seqNum");
			return (Criteria) this;
		}

		public Criteria andSeqNumNotBetween(Integer value1, Integer value2) {
			addCriterion("seq_num not between", value1, value2, "seqNum");
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

	@Override
	public PageView<Label> getPageView() {
		return pageView;
	}

	@Override
	public void setPageView(PageView<Label> pageView) {
		this.pageView = pageView;
	}

	@Override
	public String toJson() throws JsonProcessingException {
		return JsonUtilsForMcoding.writeValueAsString(this);
	}
}