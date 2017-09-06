package com.mcoding.base.dst.service;


import javax.annotation.Resource;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.mcoding.base.dst.service.income.DstIncomeOrderService;
import com.mcoding.base.order.bean.orderreturn.OrderReturn;
import com.mcoding.base.order.service.orderreturn.event.OrderReturnComfireEvent;

@Component
public class ReduceIncomeHanler implements ApplicationListener<OrderReturnComfireEvent> {

	@Resource
	protected DstIncomeOrderService dstIncomeOrderService;
	
	@Override
	public void onApplicationEvent(OrderReturnComfireEvent event) {
		OrderReturn orderReturn = (OrderReturn) event.getSource();
		
		Integer orderId = orderReturn.getOrderId();
		this.dstIncomeOrderService.refundIncome(orderId);
	}


}
