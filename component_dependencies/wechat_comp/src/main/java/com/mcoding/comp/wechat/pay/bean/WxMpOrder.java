package com.mcoding.comp.wechat.pay.bean;

import java.util.Date;

/**
 * 创建统一订单时，需要的参数
 * @author hzy
 *
 */
public class WxMpOrder {

	/**
	 * 商品描述,必填。例：腾讯充值中心-QQ会员充值
	 */
	private String productBody;
	/**
	 * 商户订单号,必填。
	 */
	private String outTradeNo;
	/**
	 * 总金额,必填。
	 */
	private Integer totalFee;
	/**
	 * 商品详情，非必填。
	 */
	private String productDetail;
	/**
	 * 附加数据,非必填。
	 */
	private String attach;
	/**
	 * 货币类型,非必填。
	 */
	private String feeType;
	/**
	 * 交易起始时间,非必填。
	 */
	private Date timeStart;
	/**
	 * 交易结束时间,非必填。
	 */
	private Date timeExpire;
	/**
	 * 商品标记,非必填。
	 */
	private String goodsTag;
	/**
	 * 用户标识,trade_type=JSAPI时（即公众号支付），此参数必传。
	 */
	private String openId;

	public String getProductBody() {
		return productBody;
	}

	public void setProductBody(String productBody) {
		this.productBody = productBody;
	}

	public String getProductDetail() {
		return productDetail;
	}

	public void setProductDetail(String productDetail) {
		this.productDetail = productDetail;
	}

	public String getAttach() {
		return attach;
	}

	public void setAttach(String attach) {
		this.attach = attach;
	}

	public String getOutTradeNo() {
		return outTradeNo;
	}

	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}

	public String getFeeType() {
		return feeType;
	}

	public void setFeeType(String feeType) {
		this.feeType = feeType;
	}

	public Date getTimeStart() {
		return timeStart;
	}

	public void setTimeStart(Date timeStart) {
		this.timeStart = timeStart;
	}

	public Date getTimeExpire() {
		return timeExpire;
	}

	public void setTimeExpire(Date timeExpire) {
		this.timeExpire = timeExpire;
	}

	public String getGoodsTag() {
		return goodsTag;
	}

	public void setGoodsTag(String goodsTag) {
		this.goodsTag = goodsTag;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public Integer getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(Integer totalFee) {
		this.totalFee = totalFee;
	}

}
