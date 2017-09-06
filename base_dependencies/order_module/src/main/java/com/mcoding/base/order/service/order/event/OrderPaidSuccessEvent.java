package com.mcoding.base.order.service.order.event;

import org.springframework.context.ApplicationEvent;

import com.mcoding.base.order.bean.order.Order;

/**
 * 订单支付成功事件
 * @author hzy
 *
 */
public class OrderPaidSuccessEvent extends ApplicationEvent {

	public OrderPaidSuccessEvent(Order source) {
		super(source);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
