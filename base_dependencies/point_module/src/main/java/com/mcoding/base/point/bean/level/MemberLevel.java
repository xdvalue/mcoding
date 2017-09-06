package com.mcoding.base.point.bean.level;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

@ApiModel(value="会员级别")
public class MemberLevel implements Serializable {
    @ApiModelProperty("主键")
    private Integer id;

    @ApiModelProperty("店铺id")
    private Integer storeId;

    @ApiModelProperty("会员ID")
    private Integer memberId;

    @ApiModelProperty("会员总积分")
    private Integer totalPoint;

    @ApiModelProperty("模块类型，100 sns模块的会员级别，200各模块通用的会员级别")
    private Integer moduleType;

    @ApiModelProperty("会员级别")
    private Integer grade;

    @ApiModelProperty("会员级别名称")
    private String gradeName;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("更新时间")
    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public Integer getTotalPoint() {
        return totalPoint;
    }

    public void setTotalPoint(Integer totalPoint) {
        this.totalPoint = totalPoint;
    }

    public Integer getModuleType() {
        return moduleType;
    }

    public void setModuleType(Integer moduleType) {
        this.moduleType = moduleType;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName == null ? null : gradeName.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}