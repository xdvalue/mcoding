package com.mcoding.base.product.service.category;

import java.util.List;

import com.mcoding.base.core.BaseService;
import com.mcoding.base.product.bean.category.ProductCategory;
import com.mcoding.base.product.bean.category.ProductCategoryExample;

public interface ProductCategoryService extends BaseService<ProductCategory, ProductCategoryExample> {
	
	public List<ProductCategory> queryChildern(int parentId);
}