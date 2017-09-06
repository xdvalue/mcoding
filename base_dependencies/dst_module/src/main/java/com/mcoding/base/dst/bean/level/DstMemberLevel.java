package com.mcoding.base.dst.bean.level;

import java.util.Date;

import com.mcoding.base.ui.bean.member.IMemberExtInfo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="会员-分销商关联表")
public class DstMemberLevel implements IMemberExtInfo {
    @ApiModelProperty("主键")
    private Integer id;

    @ApiModelProperty("分销商级别id")
    private Integer levelId;

    @ApiModelProperty("级别名称")
    private String levelName;

    @ApiModelProperty("分销商id")
    private Integer memberId;

    @ApiModelProperty("分销商名称")
    private String memberName;

    @ApiModelProperty("上级分销商id")
    private Integer parentMemberId;

    @ApiModelProperty("上级分销商名称")
    private String parentMemberName;

    @ApiModelProperty("是否可用")
    private Integer isEnable;

    @ApiModelProperty("申请当经销商的时间")
    private Date applyTime;

    @ApiModelProperty("成为下线的时间")
    private Date createTime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLevelId() {
        return levelId;
    }

    public void setLevelId(Integer levelId) {
        this.levelId = levelId;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName == null ? null : levelName.trim();
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName == null ? null : memberName.trim();
    }

    public Integer getParentMemberId() {
        return parentMemberId;
    }

    public void setParentMemberId(Integer parentMemberId) {
        this.parentMemberId = parentMemberId;
    }

    public String getParentMemberName() {
        return parentMemberName;
    }

    public void setParentMemberName(String parentMemberName) {
        this.parentMemberName = parentMemberName == null ? null : parentMemberName.trim();
    }

    public Integer getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(Integer isEnable) {
        this.isEnable = isEnable;
    }

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}