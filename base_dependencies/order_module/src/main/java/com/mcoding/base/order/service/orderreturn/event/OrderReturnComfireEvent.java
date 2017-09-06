package com.mcoding.base.order.service.orderreturn.event;

import org.springframework.context.ApplicationEvent;

import com.mcoding.base.order.bean.orderreturn.OrderReturn;

public class OrderReturnComfireEvent extends ApplicationEvent {

	public OrderReturnComfireEvent(OrderReturn source) {
		super(source);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
