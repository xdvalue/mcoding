package com.mcoding.base.product.service.product;

import java.util.List;

import com.mcoding.base.core.BaseService;
import com.mcoding.base.product.bean.product.ProductImg;
import com.mcoding.base.product.bean.product.ProductImgExample;

public interface ProductImgService extends BaseService<ProductImg, ProductImgExample> {

	/**
	 * 根据产品id查询出产品的图片
	 * @param id
	 * @return
	 */
	public List<ProductImg> queryByProductId(int id);

	public void deleteObjByExample(ProductImgExample example);
}