package com.mcoding.base.order.service.order.event;

import org.springframework.context.ApplicationEvent;

import com.mcoding.base.order.bean.order.Order;

/**
 * 订单发货成功事件
 * @author hzy
 *
 */
public class OrderSentSuccessEvent extends ApplicationEvent {

	public OrderSentSuccessEvent(Order order) {
		super(order);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
