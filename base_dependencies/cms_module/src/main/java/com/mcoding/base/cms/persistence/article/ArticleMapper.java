package com.mcoding.base.cms.persistence.article;

import java.util.List;
import java.util.Map;

import com.mcoding.base.cms.bean.article.Article;
import com.mcoding.base.cms.bean.article.ArticleExample;
import com.mcoding.base.core.IMapper;

/**
 * ArticleMapper
 * 
 * @author acer
 * 
 */
public interface ArticleMapper extends IMapper<Article, ArticleExample>{

	int updateCount(Map<String, Object> map);

	List<Article> selectByPage(Map<String, Object> map);

	List<Article> selectFavoriteByPage(Map<String, Object> map);

	List<Article> selectCollectByPage(Map<String, Object> map);

	List<Article> selectLikeByPage(Map<String, Object> map);

}