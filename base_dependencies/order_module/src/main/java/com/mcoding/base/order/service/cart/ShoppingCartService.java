package com.mcoding.base.order.service.cart;

import java.util.List;

import com.mcoding.base.core.BaseService;
import com.mcoding.base.order.bean.cart.ShoppingCart;
import com.mcoding.base.order.bean.cart.ShoppingCartExample;
import com.mcoding.base.product.bean.product.Product;

public interface ShoppingCartService extends BaseService<ShoppingCart, ShoppingCartExample> {
	
	public void addProductIntoCart(Product product, int nums, int memberId);

	public List<ShoppingCart> findByMember(String scene, int memberId, int storeId);
	
	/**
	 * 清空购物车
	 * @param memberId
	 * @param storeId
	 */
	public void clearByMemberId(int memberId, int storeId);

	/**
	 * 批量更新购物车
	 * @param shopCartList
	 */
	public void updateCarts(List<ShoppingCart> shopCartList, int memberId);
}