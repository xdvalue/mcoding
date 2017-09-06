package com.mcoding.base.order.service.order.event;

import org.springframework.context.ApplicationEvent;

import com.mcoding.base.order.bean.order.Order;

/**
 * 创建订单成功的事件
 * @author hzy
 *
 */
public class OrderAddedSuccessEvent extends ApplicationEvent{

	public OrderAddedSuccessEvent(Order order) {
		super(order);
	}

	private static final long serialVersionUID = 1L;

}
