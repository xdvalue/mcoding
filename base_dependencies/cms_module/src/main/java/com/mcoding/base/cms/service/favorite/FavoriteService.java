package com.mcoding.base.cms.service.favorite;

import com.mcoding.base.cms.bean.favorite.Favorite;
import com.mcoding.base.cms.bean.favorite.FavoriteExample;
import com.mcoding.base.core.BaseService;
import com.mcoding.base.core.JsonResult;

/**
 * FavoriteService
 * 
 * @author acer
 * 
 */
public interface FavoriteService extends BaseService<Favorite, FavoriteExample> {
	/**
	 * 点赞、取消赞、点衰、取消衰
	 * 
	 * @param map
	 */
	public JsonResult<String> likeOrDislike(Integer type, Integer status,
			Integer memberId, Integer articleId, Integer storeId);
}
