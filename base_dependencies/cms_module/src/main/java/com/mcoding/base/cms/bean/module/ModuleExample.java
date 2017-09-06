package com.mcoding.base.cms.bean.module;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mcoding.base.core.IExample;
import com.mcoding.base.core.PageView;
import com.mcoding.base.utils.json.JsonUtilsForMcoding;

/**
 * ModuleExample
 * 
 * @author acer
 * 
 */
public class ModuleExample implements IExample<Module> {

	protected String orderByClause;

	protected boolean distinct;

	protected List<Criteria> oredCriteria;

	protected PageView<Module> pageView;

	public ModuleExample() {
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

		public Criteria andStatusIsNull() {
			addCriterion("status is null");
			return (Criteria) this;
		}

		public Criteria andStatusIsNotNull() {
			addCriterion("status is not null");
			return (Criteria) this;
		}

		public Criteria andStatusEqualTo(Integer value) {
			addCriterion("status =", value, "status");
			return (Criteria) this;
		}

		public Criteria andStatusNotEqualTo(Integer value) {
			addCriterion("status <>", value, "status");
			return (Criteria) this;
		}

		public Criteria andStatusGreaterThan(Integer value) {
			addCriterion("status >", value, "status");
			return (Criteria) this;
		}

		public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
			addCriterion("status >=", value, "status");
			return (Criteria) this;
		}

		public Criteria andStatusLessThan(Integer value) {
			addCriterion("status <", value, "status");
			return (Criteria) this;
		}

		public Criteria andStatusLessThanOrEqualTo(Integer value) {
			addCriterion("status <=", value, "status");
			return (Criteria) this;
		}

		public Criteria andStatusIn(List<Integer> values) {
			addCriterion("status in", values, "status");
			return (Criteria) this;
		}

		public Criteria andStatusNotIn(List<Integer> values) {
			addCriterion("status not in", values, "status");
			return (Criteria) this;
		}

		public Criteria andStatusBetween(Integer value1, Integer value2) {
			addCriterion("status between", value1, value2, "status");
			return (Criteria) this;
		}

		public Criteria andStatusNotBetween(Integer value1, Integer value2) {
			addCriterion("status not between", value1, value2, "status");
			return (Criteria) this;
		}

		public Criteria andStyleIsNull() {
			addCriterion("style is null");
			return (Criteria) this;
		}

		public Criteria andStyleIsNotNull() {
			addCriterion("style is not null");
			return (Criteria) this;
		}

		public Criteria andStyleEqualTo(String value) {
			addCriterion("style =", value, "style");
			return (Criteria) this;
		}

		public Criteria andStyleNotEqualTo(String value) {
			addCriterion("style <>", value, "style");
			return (Criteria) this;
		}

		public Criteria andStyleGreaterThan(String value) {
			addCriterion("style >", value, "style");
			return (Criteria) this;
		}

		public Criteria andStyleGreaterThanOrEqualTo(String value) {
			addCriterion("style >=", value, "style");
			return (Criteria) this;
		}

		public Criteria andStyleLessThan(String value) {
			addCriterion("style <", value, "style");
			return (Criteria) this;
		}

		public Criteria andStyleLessThanOrEqualTo(String value) {
			addCriterion("style <=", value, "style");
			return (Criteria) this;
		}

		public Criteria andStyleLike(String value) {
			addCriterion("style like", value, "style");
			return (Criteria) this;
		}

		public Criteria andStyleNotLike(String value) {
			addCriterion("style not like", value, "style");
			return (Criteria) this;
		}

		public Criteria andStyleIn(List<String> values) {
			addCriterion("style in", values, "style");
			return (Criteria) this;
		}

		public Criteria andStyleNotIn(List<String> values) {
			addCriterion("style not in", values, "style");
			return (Criteria) this;
		}

		public Criteria andStyleBetween(String value1, String value2) {
			addCriterion("style between", value1, value2, "style");
			return (Criteria) this;
		}

		public Criteria andStyleNotBetween(String value1, String value2) {
			addCriterion("style not between", value1, value2, "style");
			return (Criteria) this;
		}

		public Criteria andLinkIsNull() {
			addCriterion("link is null");
			return (Criteria) this;
		}

		public Criteria andLinkIsNotNull() {
			addCriterion("link is not null");
			return (Criteria) this;
		}

		public Criteria andLinkEqualTo(String value) {
			addCriterion("link =", value, "link");
			return (Criteria) this;
		}

		public Criteria andLinkNotEqualTo(String value) {
			addCriterion("link <>", value, "link");
			return (Criteria) this;
		}

		public Criteria andLinkGreaterThan(String value) {
			addCriterion("link >", value, "link");
			return (Criteria) this;
		}

		public Criteria andLinkGreaterThanOrEqualTo(String value) {
			addCriterion("link >=", value, "link");
			return (Criteria) this;
		}

		public Criteria andLinkLessThan(String value) {
			addCriterion("link <", value, "link");
			return (Criteria) this;
		}

		public Criteria andLinkLessThanOrEqualTo(String value) {
			addCriterion("link <=", value, "link");
			return (Criteria) this;
		}

		public Criteria andLinkLike(String value) {
			addCriterion("link like", value, "link");
			return (Criteria) this;
		}

		public Criteria andLinkNotLike(String value) {
			addCriterion("link not like", value, "link");
			return (Criteria) this;
		}

		public Criteria andLinkIn(List<String> values) {
			addCriterion("link in", values, "link");
			return (Criteria) this;
		}

		public Criteria andLinkNotIn(List<String> values) {
			addCriterion("link not in", values, "link");
			return (Criteria) this;
		}

		public Criteria andLinkBetween(String value1, String value2) {
			addCriterion("link between", value1, value2, "link");
			return (Criteria) this;
		}

		public Criteria andLinkNotBetween(String value1, String value2) {
			addCriterion("link not between", value1, value2, "link");
			return (Criteria) this;
		}

		public Criteria andEventIsNull() {
			addCriterion("event is null");
			return (Criteria) this;
		}

		public Criteria andEventIsNotNull() {
			addCriterion("event is not null");
			return (Criteria) this;
		}

		public Criteria andEventEqualTo(String value) {
			addCriterion("event =", value, "event");
			return (Criteria) this;
		}

		public Criteria andEventNotEqualTo(String value) {
			addCriterion("event <>", value, "event");
			return (Criteria) this;
		}

		public Criteria andEventGreaterThan(String value) {
			addCriterion("event >", value, "event");
			return (Criteria) this;
		}

		public Criteria andEventGreaterThanOrEqualTo(String value) {
			addCriterion("event >=", value, "event");
			return (Criteria) this;
		}

		public Criteria andEventLessThan(String value) {
			addCriterion("event <", value, "event");
			return (Criteria) this;
		}

		public Criteria andEventLessThanOrEqualTo(String value) {
			addCriterion("event <=", value, "event");
			return (Criteria) this;
		}

		public Criteria andEventLike(String value) {
			addCriterion("event like", value, "event");
			return (Criteria) this;
		}

		public Criteria andEventNotLike(String value) {
			addCriterion("event not like", value, "event");
			return (Criteria) this;
		}

		public Criteria andEventIn(List<String> values) {
			addCriterion("event in", values, "event");
			return (Criteria) this;
		}

		public Criteria andEventNotIn(List<String> values) {
			addCriterion("event not in", values, "event");
			return (Criteria) this;
		}

		public Criteria andEventBetween(String value1, String value2) {
			addCriterion("event between", value1, value2, "event");
			return (Criteria) this;
		}

		public Criteria andEventNotBetween(String value1, String value2) {
			addCriterion("event not between", value1, value2, "event");
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

        public Criteria andDescriptionIsNull() {
            addCriterion("description is null");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNotNull() {
            addCriterion("description is not null");
            return (Criteria) this;
        }

        public Criteria andDescriptionEqualTo(String value) {
            addCriterion("description =", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotEqualTo(String value) {
            addCriterion("description <>", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThan(String value) {
            addCriterion("description >", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("description >=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThan(String value) {
            addCriterion("description <", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThanOrEqualTo(String value) {
            addCriterion("description <=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLike(String value) {
            addCriterion("description like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotLike(String value) {
            addCriterion("description not like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionIn(List<String> values) {
            addCriterion("description in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotIn(List<String> values) {
            addCriterion("description not in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionBetween(String value1, String value2) {
            addCriterion("description between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotBetween(String value1, String value2) {
            addCriterion("description not between", value1, value2, "description");
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
	public PageView<Module> getPageView() {
		return pageView;
	}

	@Override
	public void setPageView(PageView<Module> pageView) {
		this.pageView = pageView;
	}

	@Override
	public String toJson() throws JsonProcessingException {
		return JsonUtilsForMcoding.writeValueAsString(this);
	}
}