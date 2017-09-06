package com.mcoding.base.cms.service.collect;

import com.mcoding.base.cms.bean.collect.Collect;
import com.mcoding.base.cms.bean.collect.CollectExample;
import com.mcoding.base.core.BaseService;
import com.mcoding.base.core.JsonResult;

/**
 * CollectService
 * 
 * @author acer
 * 
 */
public interface CollectService extends BaseService<Collect, CollectExample> {

	/**
	 * 收藏取消收藏
	 * 
	 * @param memberId
	 * @param articleId
	 * @param status
	 * @param storeId
	 * @return
	 */
	JsonResult<String> collectOrUncollect(Integer memberId, Integer articleId,
			Integer status, Integer storeId);
}
