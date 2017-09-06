package com.mcoding.base.order.service.order.event;

import org.springframework.context.ApplicationEvent;

import com.mcoding.base.order.bean.order.Order;

/**
 * 朋友接受赠送事件
 * @author hzy
 *
 */
public class OrderAccpectPresentEvent extends ApplicationEvent{

	public OrderAccpectPresentEvent(Order order) {
		super(order);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
