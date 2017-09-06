package com.mcoding.base.cms.service.article;

import java.util.List;

import com.mcoding.base.cms.bean.article.Article;
import com.mcoding.base.cms.bean.article.ArticleExample;
import com.mcoding.base.core.BaseService;
import com.mcoding.base.core.PageView;
import com.mcoding.base.member.bean.member.Member;

/**
 * ArticleService
 * 
 * @author acer
 * 
 */
public interface ArticleService extends BaseService<Article, ArticleExample> {

	public void updateArticleState(int articleId, int state);
	
	/**
	 * 更新点击量
	 * @param articleId
	 * @param clickCount
	 */
	public void updateClickCount(Integer articleId, Integer clickCount);

	/**
	 * 根据文章Id查询猜你喜欢文章
	 * 
	 * @param iDisplayStart
	 * @param iDisplayLength
	 * @param articleId
	 * @param storeId
	 * @return
	 */
	public PageView<Article> selectLikeByPage(String iDisplayStart,
			String iDisplayLength, Integer articleId, Integer storeId);


	/**
	 * 查询点赞或点衰的文章列表
	 * @param member
	 * @param type
	 * @param example
	 * @return
	 */
	public PageView<Article> selectFavoriteByPage(Member member, int type, int pageNo, int pageSize, int storeId);

	/**
	 * 查询收藏文章列表
	 * @param member
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public PageView<Article> selectCollectByPage(Member member, int pageNo, int pageSize, int storeId);

	/**
	 * 根据标签查询文章
	 * @param labels
	 * @param pageNo
	 * @param pageSize
	 * @param storeIdFromThreadLocal
	 * @return
	 */
	public PageView<Article> queryArticleByLabel(List<String> labels, int pageNo, int pageSize, int storeId);
}
