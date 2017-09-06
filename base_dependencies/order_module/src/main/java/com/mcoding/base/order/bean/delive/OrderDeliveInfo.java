package com.mcoding.base.order.bean.delive;

import java.io.Serializable;

/**
 * 订单的物流更新信息
 * @author hzy
 *
 */
public class OrderDeliveInfo implements Serializable {

	private static final long serialVersionUID = 1L;
	private String orderNo;
	private String deliverName;
	private String deliverCode;

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getDeliverName() {
		return deliverName;
	}

	public void setDeliverName(String deliverName) {
		this.deliverName = deliverName;
	}

	public String getDeliverCode() {
		return deliverCode;
	}

	public void setDeliverCode(String deliverCode) {
		this.deliverCode = deliverCode;
	}

}
