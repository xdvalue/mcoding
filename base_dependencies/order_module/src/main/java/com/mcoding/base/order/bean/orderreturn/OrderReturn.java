package com.mcoding.base.order.bean.orderreturn;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

@ApiModel(value="退换货单")
public class OrderReturn implements Serializable {
	
//	############以下内容非自动生成，请不要覆盖##################
    /**退换货状态，申请中**/
	public static final Integer STATUS_APPLY = 2;
	/**退换货状态，已审核**/
	public static final Integer STATUS_AUDIT = 3;
	
	/**类型退货**/
	public static final Integer TYPE_RETURN_PRODUCT = 1;
    /**类型退款**/
	public static final Integer TYPE_RETURN_MONEY = 2;
//	##############################
	
	
    @ApiModelProperty("主键")
    private Integer id;

    @ApiModelProperty("店铺id")
    private Integer storeId;

    @ApiModelProperty("退换货的订单id")
    private Integer orderId;

    @ApiModelProperty("退换货的订单号")
    private String orderNo;

    @ApiModelProperty("会员id")
    private Integer memberId;

    @ApiModelProperty("会员名称")
    private String memberName;

    @ApiModelProperty("退换货类型，1换货，2退款")
    private Integer returnType;

    @ApiModelProperty("申请退换货的状态，2申请中，3审核通过")
    private Integer returnStatus;

    @ApiModelProperty("退货理由")
    private String reason;

    @ApiModelProperty("退款总金额")
    private Integer fee;

    @ApiModelProperty("申请时间")
    private Date applyTime;

    @ApiModelProperty("审核通过时间")
    private Date auditTime;

    @ApiModelProperty("退货物流商名称")
    private String deliveryName;

    @ApiModelProperty("退货物流单号")
    private String deliveryCode;

    @ApiModelProperty("备注")
    private String remark;

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

    public Integer getReturnType() {
        return returnType;
    }

    public void setReturnType(Integer returnType) {
        this.returnType = returnType;
    }

    public Integer getReturnStatus() {
        return returnStatus;
    }

    public void setReturnStatus(Integer returnStatus) {
        this.returnStatus = returnStatus;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
    }

    public Integer getFee() {
        return fee;
    }

    public void setFee(Integer fee) {
        this.fee = fee;
    }

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    public Date getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }

    public String getDeliveryName() {
        return deliveryName;
    }

    public void setDeliveryName(String deliveryName) {
        this.deliveryName = deliveryName == null ? null : deliveryName.trim();
    }

    public String getDeliveryCode() {
        return deliveryCode;
    }

    public void setDeliveryCode(String deliveryCode) {
        this.deliveryCode = deliveryCode == null ? null : deliveryCode.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}