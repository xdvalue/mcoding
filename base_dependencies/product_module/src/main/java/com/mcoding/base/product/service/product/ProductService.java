package com.mcoding.base.product.service.product;

import com.mcoding.base.core.BaseService;
import com.mcoding.base.core.PageView;
import com.mcoding.base.product.bean.product.Product;
import com.mcoding.base.product.bean.product.ProductExample;

public interface ProductService extends BaseService<Product, ProductExample> {
	
	/**
	 * 根据查询条件，产品分类，分页查询产品
	 * @param example
	 * @param categoryId
	 * @return
	 */
    public PageView<Product> findConditionByPage(ProductExample example, Integer categoryId);
    
    /**
	 * 根据查询条件，产品分类，场景code，分页查询产品，产品中带有产品价格，产品图片
	 * @param example
	 * @param categoryId
	 * @return
	 */
    public PageView<Product> findConditionByPage(ProductExample example, Integer categoryId, String scene);
    
    /**
     * 查询产品的所有详情，包括产品的 价格，图片
     * @param scene
     * @param id
     * @return
     */
    public Product queryProductDetailById(String scene, int id);
    
}