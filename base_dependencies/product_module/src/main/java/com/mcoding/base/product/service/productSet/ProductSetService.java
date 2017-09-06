package com.mcoding.base.product.service.productSet;

import java.util.List;

import com.mcoding.base.core.BaseService;
import com.mcoding.base.product.bean.productSet.ProductSet;
import com.mcoding.base.product.bean.productSet.ProductSetExample;

public interface ProductSetService extends BaseService<ProductSet, ProductSetExample> {
	
	/**
	 * 查询套餐底下的产品
	 * @param setId
	 * @return
	 */
	public List<ProductSet> queryByProductId(int setId);
}