package com.mcoding.base.cms.service.recommendarticle;

import java.util.List;

import com.mcoding.base.cms.bean.recommendarticle.RecommendArticle;
import com.mcoding.base.cms.bean.recommendarticle.RecommendArticleExample;
import com.mcoding.base.core.BaseService;

/**
 * RecommendArticleService
 * 
 * @author acer
 * 
 */
public interface RecommendArticleService extends BaseService<RecommendArticle, RecommendArticleExample> {
	
	public void addOrDeleteByArticleId(int articleId, List<RecommendArticle> recommendArticleList);
	
	public void deleteByArticleId(int articleId);

}
