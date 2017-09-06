package com.mcoding.base.product.service.productPoint;

import com.mcoding.base.core.BaseService;
import com.mcoding.base.product.bean.productPoint.ProductPoint;
import com.mcoding.base.product.bean.productPoint.ProductPointExample;

public interface ProductPointService extends BaseService<ProductPoint, ProductPointExample> {
	
	public ProductPoint queryByProductId(int productId);
}