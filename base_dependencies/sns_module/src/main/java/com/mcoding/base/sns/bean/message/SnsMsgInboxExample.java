package com.mcoding.base.sns.bean.message;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mcoding.base.core.IExample;
import com.mcoding.base.core.PageView;
import com.mcoding.base.utils.json.JsonUtilsForMcoding;

public class SnsMsgInboxExample implements IExample<SnsMsgInbox> {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected PageView<SnsMsgInbox> pageView;

    public SnsMsgInboxExample() {
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
    public PageView<SnsMsgInbox> getPageView() {
        return pageView;
    }

    @Override
    public void setPageView(PageView<SnsMsgInbox> pageView) {
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

        public Criteria andMsgIdIsNull() {
            addCriterion("msg_id is null");
            return (Criteria) this;
        }

        public Criteria andMsgIdIsNotNull() {
            addCriterion("msg_id is not null");
            return (Criteria) this;
        }

        public Criteria andMsgIdEqualTo(Integer value) {
            addCriterion("msg_id =", value, "msgId");
            return (Criteria) this;
        }

        public Criteria andMsgIdNotEqualTo(Integer value) {
            addCriterion("msg_id <>", value, "msgId");
            return (Criteria) this;
        }

        public Criteria andMsgIdGreaterThan(Integer value) {
            addCriterion("msg_id >", value, "msgId");
            return (Criteria) this;
        }

        public Criteria andMsgIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("msg_id >=", value, "msgId");
            return (Criteria) this;
        }

        public Criteria andMsgIdLessThan(Integer value) {
            addCriterion("msg_id <", value, "msgId");
            return (Criteria) this;
        }

        public Criteria andMsgIdLessThanOrEqualTo(Integer value) {
            addCriterion("msg_id <=", value, "msgId");
            return (Criteria) this;
        }

        public Criteria andMsgIdIn(List<Integer> values) {
            addCriterion("msg_id in", values, "msgId");
            return (Criteria) this;
        }

        public Criteria andMsgIdNotIn(List<Integer> values) {
            addCriterion("msg_id not in", values, "msgId");
            return (Criteria) this;
        }

        public Criteria andMsgIdBetween(Integer value1, Integer value2) {
            addCriterion("msg_id between", value1, value2, "msgId");
            return (Criteria) this;
        }

        public Criteria andMsgIdNotBetween(Integer value1, Integer value2) {
            addCriterion("msg_id not between", value1, value2, "msgId");
            return (Criteria) this;
        }

        public Criteria andMsgTypeIsNull() {
            addCriterion("msg_type is null");
            return (Criteria) this;
        }

        public Criteria andMsgTypeIsNotNull() {
            addCriterion("msg_type is not null");
            return (Criteria) this;
        }

        public Criteria andMsgTypeEqualTo(Integer value) {
            addCriterion("msg_type =", value, "msgType");
            return (Criteria) this;
        }

        public Criteria andMsgTypeNotEqualTo(Integer value) {
            addCriterion("msg_type <>", value, "msgType");
            return (Criteria) this;
        }

        public Criteria andMsgTypeGreaterThan(Integer value) {
            addCriterion("msg_type >", value, "msgType");
            return (Criteria) this;
        }

        public Criteria andMsgTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("msg_type >=", value, "msgType");
            return (Criteria) this;
        }

        public Criteria andMsgTypeLessThan(Integer value) {
            addCriterion("msg_type <", value, "msgType");
            return (Criteria) this;
        }

        public Criteria andMsgTypeLessThanOrEqualTo(Integer value) {
            addCriterion("msg_type <=", value, "msgType");
            return (Criteria) this;
        }

        public Criteria andMsgTypeIn(List<Integer> values) {
            addCriterion("msg_type in", values, "msgType");
            return (Criteria) this;
        }

        public Criteria andMsgTypeNotIn(List<Integer> values) {
            addCriterion("msg_type not in", values, "msgType");
            return (Criteria) this;
        }

        public Criteria andMsgTypeBetween(Integer value1, Integer value2) {
            addCriterion("msg_type between", value1, value2, "msgType");
            return (Criteria) this;
        }

        public Criteria andMsgTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("msg_type not between", value1, value2, "msgType");
            return (Criteria) this;
        }

        public Criteria andReceiverIdIsNull() {
            addCriterion("receiver_id is null");
            return (Criteria) this;
        }

        public Criteria andReceiverIdIsNotNull() {
            addCriterion("receiver_id is not null");
            return (Criteria) this;
        }

        public Criteria andReceiverIdEqualTo(Integer value) {
            addCriterion("receiver_id =", value, "receiverId");
            return (Criteria) this;
        }

        public Criteria andReceiverIdNotEqualTo(Integer value) {
            addCriterion("receiver_id <>", value, "receiverId");
            return (Criteria) this;
        }

        public Criteria andReceiverIdGreaterThan(Integer value) {
            addCriterion("receiver_id >", value, "receiverId");
            return (Criteria) this;
        }

        public Criteria andReceiverIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("receiver_id >=", value, "receiverId");
            return (Criteria) this;
        }

        public Criteria andReceiverIdLessThan(Integer value) {
            addCriterion("receiver_id <", value, "receiverId");
            return (Criteria) this;
        }

        public Criteria andReceiverIdLessThanOrEqualTo(Integer value) {
            addCriterion("receiver_id <=", value, "receiverId");
            return (Criteria) this;
        }

        public Criteria andReceiverIdIn(List<Integer> values) {
            addCriterion("receiver_id in", values, "receiverId");
            return (Criteria) this;
        }

        public Criteria andReceiverIdNotIn(List<Integer> values) {
            addCriterion("receiver_id not in", values, "receiverId");
            return (Criteria) this;
        }

        public Criteria andReceiverIdBetween(Integer value1, Integer value2) {
            addCriterion("receiver_id between", value1, value2, "receiverId");
            return (Criteria) this;
        }

        public Criteria andReceiverIdNotBetween(Integer value1, Integer value2) {
            addCriterion("receiver_id not between", value1, value2, "receiverId");
            return (Criteria) this;
        }

        public Criteria andIsReadIsNull() {
            addCriterion("is_read is null");
            return (Criteria) this;
        }

        public Criteria andIsReadIsNotNull() {
            addCriterion("is_read is not null");
            return (Criteria) this;
        }

        public Criteria andIsReadEqualTo(Integer value) {
            addCriterion("is_read =", value, "isRead");
            return (Criteria) this;
        }

        public Criteria andIsReadNotEqualTo(Integer value) {
            addCriterion("is_read <>", value, "isRead");
            return (Criteria) this;
        }

        public Criteria andIsReadGreaterThan(Integer value) {
            addCriterion("is_read >", value, "isRead");
            return (Criteria) this;
        }

        public Criteria andIsReadGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_read >=", value, "isRead");
            return (Criteria) this;
        }

        public Criteria andIsReadLessThan(Integer value) {
            addCriterion("is_read <", value, "isRead");
            return (Criteria) this;
        }

        public Criteria andIsReadLessThanOrEqualTo(Integer value) {
            addCriterion("is_read <=", value, "isRead");
            return (Criteria) this;
        }

        public Criteria andIsReadIn(List<Integer> values) {
            addCriterion("is_read in", values, "isRead");
            return (Criteria) this;
        }

        public Criteria andIsReadNotIn(List<Integer> values) {
            addCriterion("is_read not in", values, "isRead");
            return (Criteria) this;
        }

        public Criteria andIsReadBetween(Integer value1, Integer value2) {
            addCriterion("is_read between", value1, value2, "isRead");
            return (Criteria) this;
        }

        public Criteria andIsReadNotBetween(Integer value1, Integer value2) {
            addCriterion("is_read not between", value1, value2, "isRead");
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

        public Criteria andReadTimeIsNull() {
            addCriterion("read_time is null");
            return (Criteria) this;
        }

        public Criteria andReadTimeIsNotNull() {
            addCriterion("read_time is not null");
            return (Criteria) this;
        }

        public Criteria andReadTimeEqualTo(Date value) {
            addCriterion("read_time =", value, "readTime");
            return (Criteria) this;
        }

        public Criteria andReadTimeNotEqualTo(Date value) {
            addCriterion("read_time <>", value, "readTime");
            return (Criteria) this;
        }

        public Criteria andReadTimeGreaterThan(Date value) {
            addCriterion("read_time >", value, "readTime");
            return (Criteria) this;
        }

        public Criteria andReadTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("read_time >=", value, "readTime");
            return (Criteria) this;
        }

        public Criteria andReadTimeLessThan(Date value) {
            addCriterion("read_time <", value, "readTime");
            return (Criteria) this;
        }

        public Criteria andReadTimeLessThanOrEqualTo(Date value) {
            addCriterion("read_time <=", value, "readTime");
            return (Criteria) this;
        }

        public Criteria andReadTimeIn(List<Date> values) {
            addCriterion("read_time in", values, "readTime");
            return (Criteria) this;
        }

        public Criteria andReadTimeNotIn(List<Date> values) {
            addCriterion("read_time not in", values, "readTime");
            return (Criteria) this;
        }

        public Criteria andReadTimeBetween(Date value1, Date value2) {
            addCriterion("read_time between", value1, value2, "readTime");
            return (Criteria) this;
        }

        public Criteria andReadTimeNotBetween(Date value1, Date value2) {
            addCriterion("read_time not between", value1, value2, "readTime");
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