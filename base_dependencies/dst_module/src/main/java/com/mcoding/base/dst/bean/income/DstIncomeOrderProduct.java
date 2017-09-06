package com.mcoding.base.dst.bean.income;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

@ApiModel(value="佣金明细")
public class DstIncomeOrderProduct implements Serializable {
    @ApiModelProperty("主键")
    private Integer id;

    @ApiModelProperty("经销商id")
    private Integer memberId;

    @ApiModelProperty("经销商名称")
    private String memberName;

    @ApiModelProperty("经销商级别id")
    private Integer levelId;

    @ApiModelProperty("经销商级别名称")
    private String levelName;

    @ApiModelProperty("订单id")
    private Integer orderId;

    @ApiModelProperty("产品id")
    private Integer productId;

    @ApiModelProperty("产品名称")
    private String productName;

    @ApiModelProperty("产品数量")
    private Integer productCount;

    @ApiModelProperty("收入id")
    private Integer incomeOrderId;

    @ApiModelProperty("产品总佣金")
    private Integer incomeTotal;

    @ApiModelProperty("返利总积分")
    private Integer pointTotal;

    @ApiModelProperty("创建时间")
    private Date createTime;

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

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName == null ? null : memberName.trim();
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

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName == null ? null : productName.trim();
    }

    public Integer getProductCount() {
        return productCount;
    }

    public void setProductCount(Integer productCount) {
        this.productCount = productCount;
    }

    public Integer getIncomeOrderId() {
        return incomeOrderId;
    }

    public void setIncomeOrderId(Integer incomeOrderId) {
        this.incomeOrderId = incomeOrderId;
    }

    public Integer getIncomeTotal() {
        return incomeTotal;
    }

    public void setIncomeTotal(Integer incomeTotal) {
        this.incomeTotal = incomeTotal;
    }

    public Integer getPointTotal() {
        return pointTotal;
    }

    public void setPointTotal(Integer pointTotal) {
        this.pointTotal = pointTotal;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}