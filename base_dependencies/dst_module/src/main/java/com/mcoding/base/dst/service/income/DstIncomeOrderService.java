package com.mcoding.base.dst.service.income;

import java.util.List;

import com.mcoding.base.core.BaseService;
import com.mcoding.base.dst.bean.income.DstIncomeOrder;
import com.mcoding.base.dst.bean.income.DstIncomeOrderExample;
import com.mcoding.base.dst.bean.income.DstIncomeOrderProduct;
import com.mcoding.base.member.bean.member.Member;
import com.mcoding.base.order.bean.order.Order;

public interface DstIncomeOrderService extends BaseService<DstIncomeOrder, DstIncomeOrderExample> {

	/**
	 * 添加佣金
	 * @param order 
	 * @param incomeOrderProducts
	 * @param parent
	 * @return 
	 */
	public DstIncomeOrder addIncome(Order order, List<DstIncomeOrderProduct> incomeOrderProducts, Member parent);

	/**
	 * 佣金退费
	 * @param orderId
	 */
	public void refundIncome(int orderId);
	
}