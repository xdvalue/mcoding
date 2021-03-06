package com.mcoding.base.member.bean.department;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

@ApiModel(value="会员_所属部门表")
public class MemberDepartment implements Serializable {
	
	//############以下不是自动生成的代码,请不要覆盖##############
	/**类型，公司 100**/
	public static final Integer TYPE_COMPANY = 100;
	/**类型，部门 200**/
	public static final Integer TYPE_DEPARTMENT = 200;
	//############end##############
	
    @ApiModelProperty("主键")
    private Integer id;

    @ApiModelProperty("上级部门id")
    private Integer parentId;

    @ApiModelProperty("部门所属公司Id")
    private Integer companyId;

    @ApiModelProperty("部门所属公司名称")
    private String companyName;

    @ApiModelProperty("部门名称")
    private String departmentName;

    @ApiModelProperty("类型，100表示公司，200表示部门")
    private Integer departmentType;

    @ApiModelProperty("显示的logo")
    private String logoImgUrl;

    @ApiModelProperty("总裁")
    private String president;

    @ApiModelProperty("总裁电话")
    private String presidentTel;

    @ApiModelProperty("助理")
    private String assistant;

    @ApiModelProperty("助理电话")
    private String assistantTel;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName == null ? null : departmentName.trim();
    }

    public Integer getDepartmentType() {
        return departmentType;
    }

    public void setDepartmentType(Integer departmentType) {
        this.departmentType = departmentType;
    }

    public String getLogoImgUrl() {
        return logoImgUrl;
    }

    public void setLogoImgUrl(String logoImgUrl) {
        this.logoImgUrl = logoImgUrl == null ? null : logoImgUrl.trim();
    }

    public String getPresident() {
        return president;
    }

    public void setPresident(String president) {
        this.president = president == null ? null : president.trim();
    }

    public String getPresidentTel() {
        return presidentTel;
    }

    public void setPresidentTel(String presidentTel) {
        this.presidentTel = presidentTel == null ? null : presidentTel.trim();
    }

    public String getAssistant() {
        return assistant;
    }

    public void setAssistant(String assistant) {
        this.assistant = assistant == null ? null : assistant.trim();
    }

    public String getAssistantTel() {
        return assistantTel;
    }

    public void setAssistantTel(String assistantTel) {
        this.assistantTel = assistantTel == null ? null : assistantTel.trim();
    }
}