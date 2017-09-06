package com.mcoding.base.dst.bean.income;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

@ApiModel(value="会员分销收益表")
public class DstIncomeOrder implements Serializable {
	
//	###########请不要覆盖##############
	/**类型，收入**/
	public static final Integer TYPE_INCOME = 1;
	/**类型，退款**/
	public static final Integer TYPE_REFUND = 2;
//	#########################
	
    @ApiModelProperty("主键")
    private Integer id;

    @ApiModelProperty("获取佣金的会员id")
    private Integer memberId;

    @ApiModelProperty("获取佣金的用户名")
    private String memberName;

    @ApiModelProperty("经销商级别")
    private Integer levelId;

    @ApiModelProperty("经销商级别名称")
    private String levelName;

    @ApiModelProperty("订单id")
    private Integer orderId;

    @ApiModelProperty("订单编号")
    private String orderNo;

    @ApiModelProperty("订单会员id")
    private Integer orderMemberId;

    @ApiModelProperty("订单会员名")
    private String orderMemberName;

    @ApiModelProperty("订单总价格")
    private Integer orderAmountTotal;

    @ApiModelProperty("订单总支付价格")
    private Integer orderAmountPay;

    @ApiModelProperty("订单总佣金")
    private Integer incomeAmountTotal;

    @ApiModelProperty("返利总积分")
    private Integer pointAmountTotal;

    @ApiModelProperty("收入类型：1订单产生收入，2退货单产生的负收入")
    private Integer incomeType;

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

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    public Integer getOrderMemberId() {
        return orderMemberId;
    }

    public void setOrderMemberId(Integer orderMemberId) {
        this.orderMemberId = orderMemberId;
    }

    public String getOrderMemberName() {
        return orderMemberName;
    }

    public void setOrderMemberName(String orderMemberName) {
        this.orderMemberName = orderMemberName == null ? null : orderMemberName.trim();
    }

    public Integer getOrderAmountTotal() {
        return orderAmountTotal;
    }

    public void setOrderAmountTotal(Integer orderAmountTotal) {
        this.orderAmountTotal = orderAmountTotal;
    }

    public Integer getOrderAmountPay() {
        return orderAmountPay;
    }

    public void setOrderAmountPay(Integer orderAmountPay) {
        this.orderAmountPay = orderAmountPay;
    }

    public Integer getIncomeAmountTotal() {
        return incomeAmountTotal;
    }

    public void setIncomeAmountTotal(Integer incomeAmountTotal) {
        this.incomeAmountTotal = incomeAmountTotal;
    }

    public Integer getPointAmountTotal() {
        return pointAmountTotal;
    }

    public void setPointAmountTotal(Integer pointAmountTotal) {
        this.pointAmountTotal = pointAmountTotal;
    }

    public Integer getIncomeType() {
        return incomeType;
    }

    public void setIncomeType(Integer incomeType) {
        this.incomeType = incomeType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}