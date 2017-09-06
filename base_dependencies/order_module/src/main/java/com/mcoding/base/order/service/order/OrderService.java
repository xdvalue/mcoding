package com.mcoding.base.order.service.order;

import java.util.List;

import com.mcoding.base.core.PageView;
import com.mcoding.base.member.bean.member.Member;
import com.mcoding.base.order.bean.cart.ShoppingCart;
import com.mcoding.base.order.bean.delive.OrderDeliveInfo;
import com.mcoding.base.order.bean.order.Order;
import com.mcoding.base.order.bean.order.OrderExample;

public interface OrderService {
	
//	public void addObj(Order t);
	public void deleteObjById(int id);
	
	public void modifyObj(Order t);
	
	public Order queryObjById(int id);
	
	public List<Order> queryAllObjByExample(OrderExample example);
	
	public PageView<Order> queryObjByPage(OrderExample example);
	
	/**
	 * 订单预处理
	 * @param productList
	 * @param memberId
	 * @return
	 */
	public Order preveiw(String scene, List<ShoppingCart> shopCartList, Member member);

	/**
	 * 订单预览
	 * @param scene
	 * @param productId
	 * @param nums
	 * @param member
	 * @return
	 */
	public Order preveiw(String scene, int productId, int nums, Member member);

    /**
     * 创建订单
     * @param order
     */
	public void addOrder(Order order);
	
	/**
	 * 发货
	 * @param orderId
	 * @param deliveryName
	 * @param deliverCode
	 */
	public void deliver(Order order, String deliveryName, String deliverCode);
	
	/**
	 * 批量发货
	 * @param deliveInfoList
	 */
	public void deliver(List<OrderDeliveInfo> deliveInfoList);
	
}