package com.mcoding.base.dst.service.income;

import com.mcoding.base.core.BaseService;
import com.mcoding.base.dst.bean.income.DstIncomeProduct;
import com.mcoding.base.dst.bean.income.DstIncomeProductExample;

public interface DstIncomeProductService extends BaseService<DstIncomeProduct, DstIncomeProductExample> {
	
	/**
	 * 根据经销商级别，找出产品佣金
	 * @param levelId
	 * @return
	 */
	public DstIncomeProduct queryByLevelId(int levelId, int productId);
}