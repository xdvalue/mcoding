package com.mcoding.base.ui.bean.attachment;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mcoding.base.core.PageView;
import com.mcoding.base.utils.json.JsonUtilsForMcoding;

public class AttachmentExample implements com.mcoding.base.core.IExample<Attachment>{
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;
    
    protected PageView<Attachment> pageView;

    public AttachmentExample() {
        oredCriteria = new ArrayList<Criteria>();
    }
    
    @Override
	public PageView<Attachment> getPageView() {
		return pageView;
	}

	@Override
	public void setPageView(PageView<Attachment> pageView) {
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

        public Criteria andFilePathIsNull() {
            addCriterion("file_path is null");
            return (Criteria) this;
        }

        public Criteria andFilePathIsNotNull() {
            addCriterion("file_path is not null");
            return (Criteria) this;
        }

        public Criteria andFilePathEqualTo(String value) {
            addCriterion("file_path =", value, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathNotEqualTo(String value) {
            addCriterion("file_path <>", value, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathGreaterThan(String value) {
            addCriterion("file_path >", value, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathGreaterThanOrEqualTo(String value) {
            addCriterion("file_path >=", value, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathLessThan(String value) {
            addCriterion("file_path <", value, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathLessThanOrEqualTo(String value) {
            addCriterion("file_path <=", value, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathLike(String value) {
            addCriterion("file_path like", value, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathNotLike(String value) {
            addCriterion("file_path not like", value, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathIn(List<String> values) {
            addCriterion("file_path in", values, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathNotIn(List<String> values) {
            addCriterion("file_path not in", values, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathBetween(String value1, String value2) {
            addCriterion("file_path between", value1, value2, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathNotBetween(String value1, String value2) {
            addCriterion("file_path not between", value1, value2, "filePath");
            return (Criteria) this;
        }

        public Criteria andFileNameIsNull() {
            addCriterion("file_name is null");
            return (Criteria) this;
        }

        public Criteria andFileNameIsNotNull() {
            addCriterion("file_name is not null");
            return (Criteria) this;
        }

        public Criteria andFileNameEqualTo(String value) {
            addCriterion("file_name =", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameNotEqualTo(String value) {
            addCriterion("file_name <>", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameGreaterThan(String value) {
            addCriterion("file_name >", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameGreaterThanOrEqualTo(String value) {
            addCriterion("file_name >=", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameLessThan(String value) {
            addCriterion("file_name <", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameLessThanOrEqualTo(String value) {
            addCriterion("file_name <=", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameLike(String value) {
            addCriterion("file_name like", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameNotLike(String value) {
            addCriterion("file_name not like", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameIn(List<String> values) {
            addCriterion("file_name in", values, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameNotIn(List<String> values) {
            addCriterion("file_name not in", values, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameBetween(String value1, String value2) {
            addCriterion("file_name between", value1, value2, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameNotBetween(String value1, String value2) {
            addCriterion("file_name not between", value1, value2, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileRenameIsNull() {
            addCriterion("file_rename is null");
            return (Criteria) this;
        }

        public Criteria andFileRenameIsNotNull() {
            addCriterion("file_rename is not null");
            return (Criteria) this;
        }

        public Criteria andFileRenameEqualTo(String value) {
            addCriterion("file_rename =", value, "fileRename");
            return (Criteria) this;
        }

        public Criteria andFileRenameNotEqualTo(String value) {
            addCriterion("file_rename <>", value, "fileRename");
            return (Criteria) this;
        }

        public Criteria andFileRenameGreaterThan(String value) {
            addCriterion("file_rename >", value, "fileRename");
            return (Criteria) this;
        }

        public Criteria andFileRenameGreaterThanOrEqualTo(String value) {
            addCriterion("file_rename >=", value, "fileRename");
            return (Criteria) this;
        }

        public Criteria andFileRenameLessThan(String value) {
            addCriterion("file_rename <", value, "fileRename");
            return (Criteria) this;
        }

        public Criteria andFileRenameLessThanOrEqualTo(String value) {
            addCriterion("file_rename <=", value, "fileRename");
            return (Criteria) this;
        }

        public Criteria andFileRenameLike(String value) {
            addCriterion("file_rename like", value, "fileRename");
            return (Criteria) this;
        }

        public Criteria andFileRenameNotLike(String value) {
            addCriterion("file_rename not like", value, "fileRename");
            return (Criteria) this;
        }

        public Criteria andFileRenameIn(List<String> values) {
            addCriterion("file_rename in", values, "fileRename");
            return (Criteria) this;
        }

        public Criteria andFileRenameNotIn(List<String> values) {
            addCriterion("file_rename not in", values, "fileRename");
            return (Criteria) this;
        }

        public Criteria andFileRenameBetween(String value1, String value2) {
            addCriterion("file_rename between", value1, value2, "fileRename");
            return (Criteria) this;
        }

        public Criteria andFileRenameNotBetween(String value1, String value2) {
            addCriterion("file_rename not between", value1, value2, "fileRename");
            return (Criteria) this;
        }

        public Criteria andFileSuffixIsNull() {
            addCriterion("file_suffix is null");
            return (Criteria) this;
        }

        public Criteria andFileSuffixIsNotNull() {
            addCriterion("file_suffix is not null");
            return (Criteria) this;
        }

        public Criteria andFileSuffixEqualTo(String value) {
            addCriterion("file_suffix =", value, "fileSuffix");
            return (Criteria) this;
        }

        public Criteria andFileSuffixNotEqualTo(String value) {
            addCriterion("file_suffix <>", value, "fileSuffix");
            return (Criteria) this;
        }

        public Criteria andFileSuffixGreaterThan(String value) {
            addCriterion("file_suffix >", value, "fileSuffix");
            return (Criteria) this;
        }

        public Criteria andFileSuffixGreaterThanOrEqualTo(String value) {
            addCriterion("file_suffix >=", value, "fileSuffix");
            return (Criteria) this;
        }

        public Criteria andFileSuffixLessThan(String value) {
            addCriterion("file_suffix <", value, "fileSuffix");
            return (Criteria) this;
        }

        public Criteria andFileSuffixLessThanOrEqualTo(String value) {
            addCriterion("file_suffix <=", value, "fileSuffix");
            return (Criteria) this;
        }

        public Criteria andFileSuffixLike(String value) {
            addCriterion("file_suffix like", value, "fileSuffix");
            return (Criteria) this;
        }

        public Criteria andFileSuffixNotLike(String value) {
            addCriterion("file_suffix not like", value, "fileSuffix");
            return (Criteria) this;
        }

        public Criteria andFileSuffixIn(List<String> values) {
            addCriterion("file_suffix in", values, "fileSuffix");
            return (Criteria) this;
        }

        public Criteria andFileSuffixNotIn(List<String> values) {
            addCriterion("file_suffix not in", values, "fileSuffix");
            return (Criteria) this;
        }

        public Criteria andFileSuffixBetween(String value1, String value2) {
            addCriterion("file_suffix between", value1, value2, "fileSuffix");
            return (Criteria) this;
        }

        public Criteria andFileSuffixNotBetween(String value1, String value2) {
            addCriterion("file_suffix not between", value1, value2, "fileSuffix");
            return (Criteria) this;
        }

        public Criteria andFileSizeIsNull() {
            addCriterion("file_size is null");
            return (Criteria) this;
        }

        public Criteria andFileSizeIsNotNull() {
            addCriterion("file_size is not null");
            return (Criteria) this;
        }

        public Criteria andFileSizeEqualTo(Double value) {
            addCriterion("file_size =", value, "fileSize");
            return (Criteria) this;
        }

        public Criteria andFileSizeNotEqualTo(Double value) {
            addCriterion("file_size <>", value, "fileSize");
            return (Criteria) this;
        }

        public Criteria andFileSizeGreaterThan(Double value) {
            addCriterion("file_size >", value, "fileSize");
            return (Criteria) this;
        }

        public Criteria andFileSizeGreaterThanOrEqualTo(Double value) {
            addCriterion("file_size >=", value, "fileSize");
            return (Criteria) this;
        }

        public Criteria andFileSizeLessThan(Double value) {
            addCriterion("file_size <", value, "fileSize");
            return (Criteria) this;
        }

        public Criteria andFileSizeLessThanOrEqualTo(Double value) {
            addCriterion("file_size <=", value, "fileSize");
            return (Criteria) this;
        }

        public Criteria andFileSizeIn(List<Double> values) {
            addCriterion("file_size in", values, "fileSize");
            return (Criteria) this;
        }

        public Criteria andFileSizeNotIn(List<Double> values) {
            addCriterion("file_size not in", values, "fileSize");
            return (Criteria) this;
        }

        public Criteria andFileSizeBetween(Double value1, Double value2) {
            addCriterion("file_size between", value1, value2, "fileSize");
            return (Criteria) this;
        }

        public Criteria andFileSizeNotBetween(Double value1, Double value2) {
            addCriterion("file_size not between", value1, value2, "fileSize");
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

        public Criteria andFileUrlIsNull() {
            addCriterion("file_url is null");
            return (Criteria) this;
        }

        public Criteria andFileUrlIsNotNull() {
            addCriterion("file_url is not null");
            return (Criteria) this;
        }

        public Criteria andFileUrlEqualTo(String value) {
            addCriterion("file_url =", value, "fileUrl");
            return (Criteria) this;
        }

        public Criteria andFileUrlNotEqualTo(String value) {
            addCriterion("file_url <>", value, "fileUrl");
            return (Criteria) this;
        }

        public Criteria andFileUrlGreaterThan(String value) {
            addCriterion("file_url >", value, "fileUrl");
            return (Criteria) this;
        }

        public Criteria andFileUrlGreaterThanOrEqualTo(String value) {
            addCriterion("file_url >=", value, "fileUrl");
            return (Criteria) this;
        }

        public Criteria andFileUrlLessThan(String value) {
            addCriterion("file_url <", value, "fileUrl");
            return (Criteria) this;
        }

        public Criteria andFileUrlLessThanOrEqualTo(String value) {
            addCriterion("file_url <=", value, "fileUrl");
            return (Criteria) this;
        }

        public Criteria andFileUrlLike(String value) {
            addCriterion("file_url like", value, "fileUrl");
            return (Criteria) this;
        }

        public Criteria andFileUrlNotLike(String value) {
            addCriterion("file_url not like", value, "fileUrl");
            return (Criteria) this;
        }

        public Criteria andFileUrlIn(List<String> values) {
            addCriterion("file_url in", values, "fileUrl");
            return (Criteria) this;
        }

        public Criteria andFileUrlNotIn(List<String> values) {
            addCriterion("file_url not in", values, "fileUrl");
            return (Criteria) this;
        }

        public Criteria andFileUrlBetween(String value1, String value2) {
            addCriterion("file_url between", value1, value2, "fileUrl");
            return (Criteria) this;
        }

        public Criteria andFileUrlNotBetween(String value1, String value2) {
            addCriterion("file_url not between", value1, value2, "fileUrl");
            return (Criteria) this;
        }

        public Criteria andFileSaveDesIsNull() {
            addCriterion("file_save_des is null");
            return (Criteria) this;
        }

        public Criteria andFileSaveDesIsNotNull() {
            addCriterion("file_save_des is not null");
            return (Criteria) this;
        }

        public Criteria andFileSaveDesEqualTo(Integer value) {
            addCriterion("file_save_des =", value, "fileSaveDes");
            return (Criteria) this;
        }

        public Criteria andFileSaveDesNotEqualTo(Integer value) {
            addCriterion("file_save_des <>", value, "fileSaveDes");
            return (Criteria) this;
        }

        public Criteria andFileSaveDesGreaterThan(Integer value) {
            addCriterion("file_save_des >", value, "fileSaveDes");
            return (Criteria) this;
        }

        public Criteria andFileSaveDesGreaterThanOrEqualTo(Integer value) {
            addCriterion("file_save_des >=", value, "fileSaveDes");
            return (Criteria) this;
        }

        public Criteria andFileSaveDesLessThan(Integer value) {
            addCriterion("file_save_des <", value, "fileSaveDes");
            return (Criteria) this;
        }

        public Criteria andFileSaveDesLessThanOrEqualTo(Integer value) {
            addCriterion("file_save_des <=", value, "fileSaveDes");
            return (Criteria) this;
        }

        public Criteria andFileSaveDesIn(List<Integer> values) {
            addCriterion("file_save_des in", values, "fileSaveDes");
            return (Criteria) this;
        }

        public Criteria andFileSaveDesNotIn(List<Integer> values) {
            addCriterion("file_save_des not in", values, "fileSaveDes");
            return (Criteria) this;
        }

        public Criteria andFileSaveDesBetween(Integer value1, Integer value2) {
            addCriterion("file_save_des between", value1, value2, "fileSaveDes");
            return (Criteria) this;
        }

        public Criteria andFileSaveDesNotBetween(Integer value1, Integer value2) {
            addCriterion("file_save_des not between", value1, value2, "fileSaveDes");
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