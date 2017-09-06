package com.mcoding.base.order.bean.order;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="订单")
public class Order implements Serializable {
	
	//##########以下非自动生成，请勿覆盖#################
	
	/**默认邮费 18元**/
	public static final int DEFALUT_FREIGHT = 1800;
	/**订单状态：提交未支付**/
	public static final Integer PAY_STATUS_CREATED = 100;
	/**订单状态：已支付，未发货**/
	public static final Integer PAY_STATUS_PAID = 200;
	/**订单状态：已发货，未确认收货**/
	public static final Integer PAY_STATUS_SENT = 300;
	/**订单状态：已确认收货，订单结束**/
	public static final Integer PAY_STATUS_FINISHED = 400;
	/**订单状态：未支付，并取消订单**/
	public static final Integer PAY_STATUS_CANCEL = 500;
	/**订单状态：申请退换货**/
	public static final Integer PAY_STATUS_RETURNED = 600;
	
	/**支付类型：微信支付**/
	public static final String PAY_TYPE_WECHAT = "wechat";
	
	/**礼品赠送状态,无赠送**/
	public static final Integer PRESENT_STATUS_NONE = 0;
	
	/**礼品赠送状态,待接受**/
	public static final Integer PRESENT_STATUS_SENT = 1;
	
	/**礼品赠送状态,已经接受**/
	public static final Integer PRESENT_STATUS_ACCEPT = 2;

    /**Post到E3推送状态,无需推送**/
    public static final Integer POST_E3_STATUS_NO = 0;

    /**Post到E3推送状态,已推送**/
    public static final Integer POST_E3_STATUS_WAIT = 1;

    /**Post到E3推送状态,已推送**/
    public static final Integer POST_E3_STATUS_YES = 2;
	
	private List<OrderProduct> orderProductList;
	
	public List<OrderProduct> getOrderProductList() {
		return orderProductList;
	}
	
	public void setOrderProductList(List<OrderProduct> orderProductList) {
		this.orderProductList = orderProductList;
	}
	//###########################
	
	@ApiModelProperty("订单id")
    private Integer id;

    @ApiModelProperty("店铺id")
    private Integer storeId;

    @ApiModelProperty("订单编号")
    private String orderNo;

    @ApiModelProperty("第三方交易流水号 如：微信支付流水号")
    private String tradeNo;

    @ApiModelProperty("订单类型 0.微商城默认订单 1.积分商城全额兑换订单 2.积分商城加钱购订单 3.积分翻牌中奖订单")
    private Integer type;

    @ApiModelProperty("订单状态 100未支付 200已支付 300待发货 400已发货 500已完成 600退换货")
    private Integer status;

    @ApiModelProperty("礼物领取状态 0.无赠送，1待领取，2已领取")
    private Integer presentStatus;

    @ApiModelProperty("商品总数量")
    private Integer nums;

    @ApiModelProperty("订单总金额")
    private Integer amountTotal;

    @ApiModelProperty("快递费")
    private Integer freight;

    @ApiModelProperty("支付方式  wechatpay、alipay")
    private String payType;

    @ApiModelProperty("支付金额")
    private Integer amountPay;

    @ApiModelProperty("支付时间")
    private Date payTime;

    @ApiModelProperty("退换货和退款状态，0表示无申请，1退款，2退换货")
    private Integer returnStatus;

    @ApiModelProperty("会员ID")
    private Integer memberId;

    @ApiModelProperty("会员姓名")
    private String memberRealName;

    @ApiModelProperty("会员昵称")
    private String memberNickName;

    @ApiModelProperty("卡券id")
    private Integer cardId;

    @ApiModelProperty("卡券编号")
    private String cardCode;

    @ApiModelProperty("卡券类型名称")
    private String cardTypeName;

    @ApiModelProperty("优惠规则匹配减免金额")
    private Integer preferentialAmount;

    @ApiModelProperty("减免金额")
    private Integer amountReduce;

    @ApiModelProperty("快递名称")
    private String deliveryName;

    @ApiModelProperty("快递号")
    private String deliveryCode;

    @ApiModelProperty("收货地址ID")
    private Integer addressId;

    @ApiModelProperty("详细地址")
    private String address;

    @ApiModelProperty("手机号码")
    private String addressPhone;

    @ApiModelProperty("收货人姓名")
    private String addressReveiver;

    @ApiModelProperty("收货地址所在省市县地区信息")
    private String addressRegson;

    @ApiModelProperty("推送E3状态 0.无需推送 1.待推送 2.已推送")
    private Integer postE3Status;

    @ApiModelProperty("下单时间")
    private Date addTime;

    @ApiModelProperty("发货时间")
    private Date deliveryTime;

    @ApiModelProperty("确认收货时间")
    private Date receiveTime;

    @ApiModelProperty("退款时间")
    private Date returnTime;

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

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo == null ? null : tradeNo.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getPresentStatus() {
        return presentStatus;
    }

    public void setPresentStatus(Integer presentStatus) {
        this.presentStatus = presentStatus;
    }

    public Integer getNums() {
        return nums;
    }

    public void setNums(Integer nums) {
        this.nums = nums;
    }

    public Integer getAmountTotal() {
        return amountTotal;
    }

    public void setAmountTotal(Integer amountTotal) {
        this.amountTotal = amountTotal;
    }

    public Integer getFreight() {
        return freight;
    }

    public void setFreight(Integer freight) {
        this.freight = freight;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType == null ? null : payType.trim();
    }

    public Integer getAmountPay() {
        return amountPay;
    }

    public void setAmountPay(Integer amountPay) {
        this.amountPay = amountPay;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public Integer getReturnStatus() {
        return returnStatus;
    }

    public void setReturnStatus(Integer returnStatus) {
        this.returnStatus = returnStatus;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getMemberRealName() {
        return memberRealName;
    }

    public void setMemberRealName(String memberRealName) {
        this.memberRealName = memberRealName == null ? null : memberRealName.trim();
    }

    public String getMemberNickName() {
        return memberNickName;
    }

    public void setMemberNickName(String memberNickName) {
        this.memberNickName = memberNickName == null ? null : memberNickName.trim();
    }

    public Integer getCardId() {
        return cardId;
    }

    public void setCardId(Integer cardId) {
        this.cardId = cardId;
    }

    public String getCardCode() {
        return cardCode;
    }

    public void setCardCode(String cardCode) {
        this.cardCode = cardCode == null ? null : cardCode.trim();
    }

    public String getCardTypeName() {
        return cardTypeName;
    }

    public void setCardTypeName(String cardTypeName) {
        this.cardTypeName = cardTypeName == null ? null : cardTypeName.trim();
    }

    public Integer getPreferentialAmount() {
        return preferentialAmount;
    }

    public void setPreferentialAmount(Integer preferentialAmount) {
        this.preferentialAmount = preferentialAmount;
    }

    public Integer getAmountReduce() {
        return amountReduce;
    }

    public void setAmountReduce(Integer amountReduce) {
        this.amountReduce = amountReduce;
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

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getAddressPhone() {
        return addressPhone;
    }

    public void setAddressPhone(String addressPhone) {
        this.addressPhone = addressPhone == null ? null : addressPhone.trim();
    }

    public String getAddressReveiver() {
        return addressReveiver;
    }

    public void setAddressReveiver(String addressReveiver) {
        this.addressReveiver = addressReveiver == null ? null : addressReveiver.trim();
    }

    public String getAddressRegson() {
        return addressRegson;
    }

    public void setAddressRegson(String addressRegson) {
        this.addressRegson = addressRegson == null ? null : addressRegson.trim();
    }

    public Integer getPostE3Status() {
        return postE3Status;
    }

    public void setPostE3Status(Integer postE3Status) {
        this.postE3Status = postE3Status;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Date getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(Date deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public Date getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(Date receiveTime) {
        this.receiveTime = receiveTime;
    }

    public Date getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(Date returnTime) {
        this.returnTime = returnTime;
    }
}