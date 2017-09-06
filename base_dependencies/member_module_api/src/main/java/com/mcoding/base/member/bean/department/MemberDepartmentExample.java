package com.mcoding.base.member.bean.department;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mcoding.base.ui.persistence.common.IExample;
import com.mcoding.base.core.PageView;
import com.mcoding.base.ui.utils.json.JsonUtilsForMcoding;
import java.util.ArrayList;
import java.util.List;

public class MemberDepartmentExample implements IExample<MemberDepartment> {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected PageView<MemberDepartment> pageView;

    public MemberDepartmentExample() {
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
    public PageView<MemberDepartment> getPageView() {
        return pageView;
    }

    @Override
    public void setPageView(PageView<MemberDepartment> pageView) {
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

        public Criteria andCompanyIdIsNull() {
            addCriterion("company_id is null");
            return (Criteria) this;
        }

        public Criteria andCompanyIdIsNotNull() {
            addCriterion("company_id is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyIdEqualTo(Integer value) {
            addCriterion("company_id =", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdNotEqualTo(Integer value) {
            addCriterion("company_id <>", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdGreaterThan(Integer value) {
            addCriterion("company_id >", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("company_id >=", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdLessThan(Integer value) {
            addCriterion("company_id <", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdLessThanOrEqualTo(Integer value) {
            addCriterion("company_id <=", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdIn(List<Integer> values) {
            addCriterion("company_id in", values, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdNotIn(List<Integer> values) {
            addCriterion("company_id not in", values, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdBetween(Integer value1, Integer value2) {
            addCriterion("company_id between", value1, value2, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdNotBetween(Integer value1, Integer value2) {
            addCriterion("company_id not between", value1, value2, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyNameIsNull() {
            addCriterion("company_name is null");
            return (Criteria) this;
        }

        public Criteria andCompanyNameIsNotNull() {
            addCriterion("company_name is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyNameEqualTo(String value) {
            addCriterion("company_name =", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameNotEqualTo(String value) {
            addCriterion("company_name <>", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameGreaterThan(String value) {
            addCriterion("company_name >", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameGreaterThanOrEqualTo(String value) {
            addCriterion("company_name >=", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameLessThan(String value) {
            addCriterion("company_name <", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameLessThanOrEqualTo(String value) {
            addCriterion("company_name <=", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameLike(String value) {
            addCriterion("company_name like", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameNotLike(String value) {
            addCriterion("company_name not like", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameIn(List<String> values) {
            addCriterion("company_name in", values, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameNotIn(List<String> values) {
            addCriterion("company_name not in", values, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameBetween(String value1, String value2) {
            addCriterion("company_name between", value1, value2, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameNotBetween(String value1, String value2) {
            addCriterion("company_name not between", value1, value2, "companyName");
            return (Criteria) this;
        }

        public Criteria andDepartmentNameIsNull() {
            addCriterion("department_name is null");
            return (Criteria) this;
        }

        public Criteria andDepartmentNameIsNotNull() {
            addCriterion("department_name is not null");
            return (Criteria) this;
        }

        public Criteria andDepartmentNameEqualTo(String value) {
            addCriterion("department_name =", value, "departmentName");
            return (Criteria) this;
        }

        public Criteria andDepartmentNameNotEqualTo(String value) {
            addCriterion("department_name <>", value, "departmentName");
            return (Criteria) this;
        }

        public Criteria andDepartmentNameGreaterThan(String value) {
            addCriterion("department_name >", value, "departmentName");
            return (Criteria) this;
        }

        public Criteria andDepartmentNameGreaterThanOrEqualTo(String value) {
            addCriterion("department_name >=", value, "departmentName");
            return (Criteria) this;
        }

        public Criteria andDepartmentNameLessThan(String value) {
            addCriterion("department_name <", value, "departmentName");
            return (Criteria) this;
        }

        public Criteria andDepartmentNameLessThanOrEqualTo(String value) {
            addCriterion("department_name <=", value, "departmentName");
            return (Criteria) this;
        }

        public Criteria andDepartmentNameLike(String value) {
            addCriterion("department_name like", value, "departmentName");
            return (Criteria) this;
        }

        public Criteria andDepartmentNameNotLike(String value) {
            addCriterion("department_name not like", value, "departmentName");
            return (Criteria) this;
        }

        public Criteria andDepartmentNameIn(List<String> values) {
            addCriterion("department_name in", values, "departmentName");
            return (Criteria) this;
        }

        public Criteria andDepartmentNameNotIn(List<String> values) {
            addCriterion("department_name not in", values, "departmentName");
            return (Criteria) this;
        }

        public Criteria andDepartmentNameBetween(String value1, String value2) {
            addCriterion("department_name between", value1, value2, "departmentName");
            return (Criteria) this;
        }

        public Criteria andDepartmentNameNotBetween(String value1, String value2) {
            addCriterion("department_name not between", value1, value2, "departmentName");
            return (Criteria) this;
        }

        public Criteria andDepartmentTypeIsNull() {
            addCriterion("department_type is null");
            return (Criteria) this;
        }

        public Criteria andDepartmentTypeIsNotNull() {
            addCriterion("department_type is not null");
            return (Criteria) this;
        }

        public Criteria andDepartmentTypeEqualTo(Integer value) {
            addCriterion("department_type =", value, "departmentType");
            return (Criteria) this;
        }

        public Criteria andDepartmentTypeNotEqualTo(Integer value) {
            addCriterion("department_type <>", value, "departmentType");
            return (Criteria) this;
        }

        public Criteria andDepartmentTypeGreaterThan(Integer value) {
            addCriterion("department_type >", value, "departmentType");
            return (Criteria) this;
        }

        public Criteria andDepartmentTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("department_type >=", value, "departmentType");
            return (Criteria) this;
        }

        public Criteria andDepartmentTypeLessThan(Integer value) {
            addCriterion("department_type <", value, "departmentType");
            return (Criteria) this;
        }

        public Criteria andDepartmentTypeLessThanOrEqualTo(Integer value) {
            addCriterion("department_type <=", value, "departmentType");
            return (Criteria) this;
        }

        public Criteria andDepartmentTypeIn(List<Integer> values) {
            addCriterion("department_type in", values, "departmentType");
            return (Criteria) this;
        }

        public Criteria andDepartmentTypeNotIn(List<Integer> values) {
            addCriterion("department_type not in", values, "departmentType");
            return (Criteria) this;
        }

        public Criteria andDepartmentTypeBetween(Integer value1, Integer value2) {
            addCriterion("department_type between", value1, value2, "departmentType");
            return (Criteria) this;
        }

        public Criteria andDepartmentTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("department_type not between", value1, value2, "departmentType");
            return (Criteria) this;
        }

        public Criteria andLogoImgUrlIsNull() {
            addCriterion("logo_img_url is null");
            return (Criteria) this;
        }

        public Criteria andLogoImgUrlIsNotNull() {
            addCriterion("logo_img_url is not null");
            return (Criteria) this;
        }

        public Criteria andLogoImgUrlEqualTo(String value) {
            addCriterion("logo_img_url =", value, "logoImgUrl");
            return (Criteria) this;
        }

        public Criteria andLogoImgUrlNotEqualTo(String value) {
            addCriterion("logo_img_url <>", value, "logoImgUrl");
            return (Criteria) this;
        }

        public Criteria andLogoImgUrlGreaterThan(String value) {
            addCriterion("logo_img_url >", value, "logoImgUrl");
            return (Criteria) this;
        }

        public Criteria andLogoImgUrlGreaterThanOrEqualTo(String value) {
            addCriterion("logo_img_url >=", value, "logoImgUrl");
            return (Criteria) this;
        }

        public Criteria andLogoImgUrlLessThan(String value) {
            addCriterion("logo_img_url <", value, "logoImgUrl");
            return (Criteria) this;
        }

        public Criteria andLogoImgUrlLessThanOrEqualTo(String value) {
            addCriterion("logo_img_url <=", value, "logoImgUrl");
            return (Criteria) this;
        }

        public Criteria andLogoImgUrlLike(String value) {
            addCriterion("logo_img_url like", value, "logoImgUrl");
            return (Criteria) this;
        }

        public Criteria andLogoImgUrlNotLike(String value) {
            addCriterion("logo_img_url not like", value, "logoImgUrl");
            return (Criteria) this;
        }

        public Criteria andLogoImgUrlIn(List<String> values) {
            addCriterion("logo_img_url in", values, "logoImgUrl");
            return (Criteria) this;
        }

        public Criteria andLogoImgUrlNotIn(List<String> values) {
            addCriterion("logo_img_url not in", values, "logoImgUrl");
            return (Criteria) this;
        }

        public Criteria andLogoImgUrlBetween(String value1, String value2) {
            addCriterion("logo_img_url between", value1, value2, "logoImgUrl");
            return (Criteria) this;
        }

        public Criteria andLogoImgUrlNotBetween(String value1, String value2) {
            addCriterion("logo_img_url not between", value1, value2, "logoImgUrl");
            return (Criteria) this;
        }

        public Criteria andPresidentIsNull() {
            addCriterion("president is null");
            return (Criteria) this;
        }

        public Criteria andPresidentIsNotNull() {
            addCriterion("president is not null");
            return (Criteria) this;
        }

        public Criteria andPresidentEqualTo(String value) {
            addCriterion("president =", value, "president");
            return (Criteria) this;
        }

        public Criteria andPresidentNotEqualTo(String value) {
            addCriterion("president <>", value, "president");
            return (Criteria) this;
        }

        public Criteria andPresidentGreaterThan(String value) {
            addCriterion("president >", value, "president");
            return (Criteria) this;
        }

        public Criteria andPresidentGreaterThanOrEqualTo(String value) {
            addCriterion("president >=", value, "president");
            return (Criteria) this;
        }

        public Criteria andPresidentLessThan(String value) {
            addCriterion("president <", value, "president");
            return (Criteria) this;
        }

        public Criteria andPresidentLessThanOrEqualTo(String value) {
            addCriterion("president <=", value, "president");
            return (Criteria) this;
        }

        public Criteria andPresidentLike(String value) {
            addCriterion("president like", value, "president");
            return (Criteria) this;
        }

        public Criteria andPresidentNotLike(String value) {
            addCriterion("president not like", value, "president");
            return (Criteria) this;
        }

        public Criteria andPresidentIn(List<String> values) {
            addCriterion("president in", values, "president");
            return (Criteria) this;
        }

        public Criteria andPresidentNotIn(List<String> values) {
            addCriterion("president not in", values, "president");
            return (Criteria) this;
        }

        public Criteria andPresidentBetween(String value1, String value2) {
            addCriterion("president between", value1, value2, "president");
            return (Criteria) this;
        }

        public Criteria andPresidentNotBetween(String value1, String value2) {
            addCriterion("president not between", value1, value2, "president");
            return (Criteria) this;
        }

        public Criteria andPresidentTelIsNull() {
            addCriterion("president_tel is null");
            return (Criteria) this;
        }

        public Criteria andPresidentTelIsNotNull() {
            addCriterion("president_tel is not null");
            return (Criteria) this;
        }

        public Criteria andPresidentTelEqualTo(String value) {
            addCriterion("president_tel =", value, "presidentTel");
            return (Criteria) this;
        }

        public Criteria andPresidentTelNotEqualTo(String value) {
            addCriterion("president_tel <>", value, "presidentTel");
            return (Criteria) this;
        }

        public Criteria andPresidentTelGreaterThan(String value) {
            addCriterion("president_tel >", value, "presidentTel");
            return (Criteria) this;
        }

        public Criteria andPresidentTelGreaterThanOrEqualTo(String value) {
            addCriterion("president_tel >=", value, "presidentTel");
            return (Criteria) this;
        }

        public Criteria andPresidentTelLessThan(String value) {
            addCriterion("president_tel <", value, "presidentTel");
            return (Criteria) this;
        }

        public Criteria andPresidentTelLessThanOrEqualTo(String value) {
            addCriterion("president_tel <=", value, "presidentTel");
            return (Criteria) this;
        }

        public Criteria andPresidentTelLike(String value) {
            addCriterion("president_tel like", value, "presidentTel");
            return (Criteria) this;
        }

        public Criteria andPresidentTelNotLike(String value) {
            addCriterion("president_tel not like", value, "presidentTel");
            return (Criteria) this;
        }

        public Criteria andPresidentTelIn(List<String> values) {
            addCriterion("president_tel in", values, "presidentTel");
            return (Criteria) this;
        }

        public Criteria andPresidentTelNotIn(List<String> values) {
            addCriterion("president_tel not in", values, "presidentTel");
            return (Criteria) this;
        }

        public Criteria andPresidentTelBetween(String value1, String value2) {
            addCriterion("president_tel between", value1, value2, "presidentTel");
            return (Criteria) this;
        }

        public Criteria andPresidentTelNotBetween(String value1, String value2) {
            addCriterion("president_tel not between", value1, value2, "presidentTel");
            return (Criteria) this;
        }

        public Criteria andAssistantIsNull() {
            addCriterion("assistant is null");
            return (Criteria) this;
        }

        public Criteria andAssistantIsNotNull() {
            addCriterion("assistant is not null");
            return (Criteria) this;
        }

        public Criteria andAssistantEqualTo(String value) {
            addCriterion("assistant =", value, "assistant");
            return (Criteria) this;
        }

        public Criteria andAssistantNotEqualTo(String value) {
            addCriterion("assistant <>", value, "assistant");
            return (Criteria) this;
        }

        public Criteria andAssistantGreaterThan(String value) {
            addCriterion("assistant >", value, "assistant");
            return (Criteria) this;
        }

        public Criteria andAssistantGreaterThanOrEqualTo(String value) {
            addCriterion("assistant >=", value, "assistant");
            return (Criteria) this;
        }

        public Criteria andAssistantLessThan(String value) {
            addCriterion("assistant <", value, "assistant");
            return (Criteria) this;
        }

        public Criteria andAssistantLessThanOrEqualTo(String value) {
            addCriterion("assistant <=", value, "assistant");
            return (Criteria) this;
        }

        public Criteria andAssistantLike(String value) {
            addCriterion("assistant like", value, "assistant");
            return (Criteria) this;
        }

        public Criteria andAssistantNotLike(String value) {
            addCriterion("assistant not like", value, "assistant");
            return (Criteria) this;
        }

        public Criteria andAssistantIn(List<String> values) {
            addCriterion("assistant in", values, "assistant");
            return (Criteria) this;
        }

        public Criteria andAssistantNotIn(List<String> values) {
            addCriterion("assistant not in", values, "assistant");
            return (Criteria) this;
        }

        public Criteria andAssistantBetween(String value1, String value2) {
            addCriterion("assistant between", value1, value2, "assistant");
            return (Criteria) this;
        }

        public Criteria andAssistantNotBetween(String value1, String value2) {
            addCriterion("assistant not between", value1, value2, "assistant");
            return (Criteria) this;
        }

        public Criteria andAssistantTelIsNull() {
            addCriterion("assistant_tel is null");
            return (Criteria) this;
        }

        public Criteria andAssistantTelIsNotNull() {
            addCriterion("assistant_tel is not null");
            return (Criteria) this;
        }

        public Criteria andAssistantTelEqualTo(String value) {
            addCriterion("assistant_tel =", value, "assistantTel");
            return (Criteria) this;
        }

        public Criteria andAssistantTelNotEqualTo(String value) {
            addCriterion("assistant_tel <>", value, "assistantTel");
            return (Criteria) this;
        }

        public Criteria andAssistantTelGreaterThan(String value) {
            addCriterion("assistant_tel >", value, "assistantTel");
            return (Criteria) this;
        }

        public Criteria andAssistantTelGreaterThanOrEqualTo(String value) {
            addCriterion("assistant_tel >=", value, "assistantTel");
            return (Criteria) this;
        }

        public Criteria andAssistantTelLessThan(String value) {
            addCriterion("assistant_tel <", value, "assistantTel");
            return (Criteria) this;
        }

        public Criteria andAssistantTelLessThanOrEqualTo(String value) {
            addCriterion("assistant_tel <=", value, "assistantTel");
            return (Criteria) this;
        }

        public Criteria andAssistantTelLike(String value) {
            addCriterion("assistant_tel like", value, "assistantTel");
            return (Criteria) this;
        }

        public Criteria andAssistantTelNotLike(String value) {
            addCriterion("assistant_tel not like", value, "assistantTel");
            return (Criteria) this;
        }

        public Criteria andAssistantTelIn(List<String> values) {
            addCriterion("assistant_tel in", values, "assistantTel");
            return (Criteria) this;
        }

        public Criteria andAssistantTelNotIn(List<String> values) {
            addCriterion("assistant_tel not in", values, "assistantTel");
            return (Criteria) this;
        }

        public Criteria andAssistantTelBetween(String value1, String value2) {
            addCriterion("assistant_tel between", value1, value2, "assistantTel");
            return (Criteria) this;
        }

        public Criteria andAssistantTelNotBetween(String value1, String value2) {
            addCriterion("assistant_tel not between", value1, value2, "assistantTel");
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