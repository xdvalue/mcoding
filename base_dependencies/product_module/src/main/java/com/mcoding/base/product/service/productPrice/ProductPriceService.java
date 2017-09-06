package com.mcoding.base.product.service.productPrice;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import com.mcoding.base.core.BaseService;
import com.mcoding.base.product.bean.productPrice.ProductPrice;
import com.mcoding.base.product.bean.productPrice.ProductPriceExample;

public interface ProductPriceService extends BaseService<ProductPrice, ProductPriceExample> {
	
	/**
	 * 根据场景 和产品id 查找产品规则
	 * @param sceneCode 场景代码
	 * @param productId 产品id
	 * @return
	 * @throws ClassNotFoundException 
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public List<ProductPrice> findProductPriceByScene(String sceneCode, int productId) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException;
	
}