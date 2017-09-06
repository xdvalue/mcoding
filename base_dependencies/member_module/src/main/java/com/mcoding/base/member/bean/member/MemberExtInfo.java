package com.mcoding.base.member.bean.member;

import com.mcoding.base.ui.bean.member.IMemberExtInfo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="会员拓展信息")
public class MemberExtInfo implements IMemberExtInfo {
    private Integer id;

    @ApiModelProperty("会员id")
    private Integer memberId;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("手机号码")
    private String mobile;

    @ApiModelProperty("qq")
    private String qq;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("会员所属行业")
    private Integer industry;

    @ApiModelProperty("兴趣")
    private Integer interest;

    @ApiModelProperty("所属单位部门id")
    private Integer departmentId;

    @ApiModelProperty("所属部门名称")
    private String departmentName;

    @ApiModelProperty("所属公司名称")
    private String companyName;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq == null ? null : qq.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getIndustry() {
        return industry;
    }

    public void setIndustry(Integer industry) {
        this.industry = industry;
    }

    public Integer getInterest() {
        return interest;
    }

    public void setInterest(Integer interest) {
        this.interest = interest;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName == null ? null : departmentName.trim();
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }
}