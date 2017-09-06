package com.mcoding.base.order.service.order;

import java.util.List;

import com.mcoding.base.core.BaseService;
import com.mcoding.base.order.bean.order.OrderProduct;
import com.mcoding.base.order.bean.order.OrderProductExample;

public interface OrderProductService extends BaseService<OrderProduct, OrderProductExample> {
	
	public List<OrderProduct> queryByOrderId(int orderId);
}