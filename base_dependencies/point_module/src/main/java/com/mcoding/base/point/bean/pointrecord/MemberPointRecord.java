package com.mcoding.base.point.bean.pointrecord;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

@ApiModel(value="会员积分记录")
public class MemberPointRecord implements Serializable {
    @ApiModelProperty("id")
    private Integer id;

    @ApiModelProperty("店铺id")
    private Integer storeId;

    @ApiModelProperty("会员id")
    private Integer memberId;

    @ApiModelProperty("会员名称")
    private String memberName;

    @ApiModelProperty("模块类型，100 sns模块的会员级别，200各模块通用的会员级别")
    private Integer moduleType;

    @ApiModelProperty("积分，正数添加，负数减少")
    private Integer point;

    @ApiModelProperty("积分来源类型,101:论坛发帖,102论坛评论")
    private Integer sourceType;

    @ApiModelProperty("积分来源的关联id，例如帖子id,评论id,订单id")
    private Integer sourceId;

    @ApiModelProperty("来源名称")
    private String sourceName;

    @ApiModelProperty("积分来源的具体内容")
    private String sourceValue;

    @ApiModelProperty("创建时间")
    private Date createTime;

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

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName == null ? null : memberName.trim();
    }

    public Integer getModuleType() {
        return moduleType;
    }

    public void setModuleType(Integer moduleType) {
        this.moduleType = moduleType;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public Integer getSourceType() {
        return sourceType;
    }

    public void setSourceType(Integer sourceType) {
        this.sourceType = sourceType;
    }

    public Integer getSourceId() {
        return sourceId;
    }

    public void setSourceId(Integer sourceId) {
        this.sourceId = sourceId;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName == null ? null : sourceName.trim();
    }

    public String getSourceValue() {
        return sourceValue;
    }

    public void setSourceValue(String sourceValue) {
        this.sourceValue = sourceValue == null ? null : sourceValue.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}