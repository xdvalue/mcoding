package com.mcoding.base.order.service.order.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.mcoding.base.order.bean.order.Order;
import com.mcoding.base.order.service.cart.ShoppingCartService;
import com.mcoding.base.order.service.order.event.OrderAddedSuccessEvent;

@Component
public class OrderAddedSucessListener implements ApplicationListener<OrderAddedSuccessEvent>{
	
	@Autowired
	protected ShoppingCartService shoppingCartService;

	@Override
	public void onApplicationEvent(OrderAddedSuccessEvent event) {
		Order order = (Order) event.getSource();
		int memberId = order.getMemberId();
		int storeId = order.getStoreId();

		//清理购物车
		this.shoppingCartService.clearByMemberId(memberId, storeId);
	}

}
