package com.mcoding.base.product.service.productPrice;

import com.mcoding.base.product.bean.productPrice.ProductPrice;
import com.mcoding.base.product.bean.productScene.ProductScene;

/**
 * 价格处理规则
 * @author hzy
 *
 */
public abstract class ProductPriceRule {
	
	private ProductScene scene;
	
	public ProductPriceRule(ProductScene scene) {
		this.scene = scene;
	}
	
	public ProductScene getScene() {
		return scene;
	}

	public void setScene(ProductScene scene) {
		this.scene = scene;
	}

	/**
	 * 处理
	 * @param price
	 * @return
	 */
	public abstract ProductPrice handle(ProductPrice price);

}
