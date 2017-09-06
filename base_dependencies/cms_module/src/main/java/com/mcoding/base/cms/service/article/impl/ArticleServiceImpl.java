package com.mcoding.base.cms.service.article.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mcoding.base.cms.bean.article.Article;
import com.mcoding.base.cms.bean.article.ArticleExample;
import com.mcoding.base.cms.bean.collect.Collect;
import com.mcoding.base.cms.bean.collect.CollectExample;
import com.mcoding.base.cms.bean.favorite.Favorite;
import com.mcoding.base.cms.bean.favorite.FavoriteExample;
import com.mcoding.base.cms.bean.label.ArticleLabelRef;
import com.mcoding.base.cms.bean.label.ArticleLabelRefExample;
import com.mcoding.base.cms.persistence.article.ArticleMapper;
import com.mcoding.base.cms.persistence.label.ArticleLabelRefMapper;
import com.mcoding.base.cms.service.article.ArticleService;
import com.mcoding.base.cms.service.collect.CollectService;
import com.mcoding.base.cms.service.favorite.FavoriteService;
import com.mcoding.base.cms.service.label.LabelService;
import com.mcoding.base.cms.service.recommendarticle.RecommendArticleService;
import com.mcoding.base.core.PageView;
import com.mcoding.base.member.bean.member.Member;

/**
 * ArticleServiceImpl
 * 
 * @author acer
 * 
 */
@Service
public class ArticleServiceImpl implements ArticleService {

	@Resource
	protected ArticleMapper articleMapper;

	@Resource
	protected ArticleLabelRefMapper articleLabelRefMapper;

	@Resource
	protected LabelService labelService;

	@Resource
	protected RecommendArticleService recommendArticleService;
	
	@Autowired
	protected FavoriteService favoriteService;
	
	@Autowired
	protected CollectService collectService;

	@CacheEvict(value = { "article" }, allEntries = true)
	@Transactional
	@Override
	public void addObj(Article article) {
		articleMapper.insertSelective(article);

		if (CollectionUtils.isNotEmpty(article.getRecommendArticleList())) {
			recommendArticleService.addOrDeleteByArticleId(article.getId(),
					article.getRecommendArticleList());
		}

		if (CollectionUtils.isNotEmpty(article.getLabelList())) {
			labelService.addLabelOrModifyLabelHit(article.getId(),
					article.getStoreId(), article.getLabelList());
		}

	}

	@CacheEvict(value = { "article" }, allEntries = true)
	@Transactional
	@Override
	public void deleteObjById(int articleId) {
		articleMapper.deleteByPrimaryKey(articleId);
		this.recommendArticleService.deleteByArticleId(articleId);
	}

	@CacheEvict(value = { "article" }, allEntries = true)
	@Transactional
	@Override
	public void modifyObj(Article article) {
		articleMapper.updateByPrimaryKeySelective(article);
		recommendArticleService.addOrDeleteByArticleId(article.getId(),
				article.getRecommendArticleList());
		if (CollectionUtils.isNotEmpty(article.getLabelList())) {
			this.labelService.addLabelOrModifyLabelHit(article.getId(),
					article.getStoreId(), article.getLabelList());
		}
	}

	@Cacheable(value = "article", key = "'ArticleService_' + #root.methodName + '_'+ #example.toJson()")
	@Override
	public List<Article> queryAllObjByExample(ArticleExample example) {
		return articleMapper.selectByExample(example);
	}

	@Cacheable(value = "article", key = "'ArticleService_' + #root.methodName + '_' +#id")
	@Override
	public Article queryObjById(int id) {
		return articleMapper.selectByPrimaryKey(id);
	}

	@Cacheable(value = "article", key = "'ArticleService_' + #root.methodName + '_'+ #example.toJson()")
	@Override
	public PageView<Article> queryObjByPage(ArticleExample example) {
		PageView<Article> pageView = example.getPageView();
		if (pageView == null) {
			pageView = new PageView<Article>();
			pageView.setPageNo(1);
			pageView.setPageSize(10);
			example.setPageView(pageView);
		}
		List<Article> list = this.articleMapper.selectByExampleByPage(example);
		pageView.setQueryResult(list);
		return pageView;
	}

	/**
	 * 更新点击量
	 */
	@CacheEvict(value = { "article" }, allEntries = true)
	@Override
	public void updateClickCount(Integer articleId, Integer clickCount) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("articleId", articleId);
		map.put("clickCount", clickCount);
		articleMapper.updateCount(map);
	}

	@Override
	public PageView<Article> selectFavoriteByPage(Member member, int type, int pageNo, int pageSize, int storeId) {
		if (member == null || member.getId()==null) {
			throw new NullPointerException("会员信息为空，无法查询他点赞的文章");
		}
		FavoriteExample favoriteExample = new FavoriteExample();
		favoriteExample.createCriteria().andMemberIdEqualTo(member.getId()).andTypeEqualTo(type);
		
		List<Favorite> list = this.favoriteService.queryAllObjByExample(favoriteExample);
		List<Integer> articleIds = new ArrayList<>();
		for(int i=0; i<list.size(); i++){
			articleIds.add(list.get(i).getArticleId());
		}
		
		if (CollectionUtils.isEmpty(articleIds)) {
			return new PageView<Article>(pageNo, pageSize);
		}
		
		ArticleExample articleExample = new ArticleExample();
		articleExample.createCriteria()
		              .andIdIn(articleIds)
		              .andArticleStateEqualTo(Article.STATE_PUBLISHED)
		              .andStoreIdEqualTo(storeId);
		articleExample.setPageView(new PageView<Article>(pageNo, pageSize));
		
		articleExample.setOrderByClause("sort_no DESC, create_time DESC, click_count DESC ");
		return this.queryObjByPage(articleExample);
	}
	
	@Override
	public PageView<Article> selectCollectByPage(Member member, int pageNo, int pageSize, int storeId) {
		if (member == null || member.getId()==null) {
			throw new NullPointerException("会员信息为空，无法查询他点赞的文章");
		}
		CollectExample collectExample = new CollectExample();
		collectExample.createCriteria()
		              .andMemberIdEqualTo(member.getId())
		              .andStoreIdEqualTo(storeId);
		
		List<Collect> collects = this.collectService.queryAllObjByExample(collectExample);
		List<Integer> articleIds = new ArrayList<>();
		for(int i=0; i<collects.size(); i++){
			articleIds.add(collects.get(i).getArticleId());
		}
		
		if (CollectionUtils.isEmpty(articleIds)) {
			return new PageView<Article>(pageNo, pageSize);
		}
		
		ArticleExample articleExample = new ArticleExample();
		articleExample.createCriteria()
		              .andIdIn(articleIds)
		              .andStoreIdEqualTo(storeId)
		              .andArticleStateEqualTo(Article.STATE_PUBLISHED);
		articleExample.setPageView(new PageView<Article>(pageNo, pageSize));
		articleExample.setOrderByClause("sort_no DESC, create_time DESC, click_count DESC ");
		return this.queryObjByPage(articleExample);
	}
	
	@Override
	public PageView<Article> queryArticleByLabel(List<String> labels, int pageNo, int pageSize, int storeId) {
		ArticleLabelRefExample labelExample = new ArticleLabelRefExample();
		for(int i=0; i<labels.size(); i++){
			labelExample.or().andLableMarkEqualTo(labels.get(i)).andStoreIdEqualTo(storeId);
		}
		List<ArticleLabelRef> refList = this.articleLabelRefMapper.selectByExample(labelExample);
		List<Integer> articleIds = new ArrayList<>();
		for(int i=0; i<refList.size(); i++){
			articleIds.add(refList.get(i).getArticleId());
		}
		
		if (CollectionUtils.isEmpty(articleIds)) {
			return new PageView<Article>(pageNo, pageSize);
		}
		
		ArticleExample articleExample = new ArticleExample();
		articleExample.createCriteria()
		              .andIdIn(articleIds)
		              .andStoreIdEqualTo(storeId)
		              .andArticleStateEqualTo(Article.STATE_PUBLISHED);
		articleExample.setPageView(new PageView<Article>(pageNo, pageSize));
		articleExample.setOrderByClause("sort_no DESC, create_time DESC, click_count DESC ");
		return this.queryObjByPage(articleExample);
	}


	@Cacheable(value = "article", key = "'ArticleService_' + #root.methodName + '_' +#iDisplayStart+'_'+#iDisplayLength+'_'+#articleId+'_'+#storeId")
	@Override
	public PageView<Article> selectLikeByPage(String iDisplayStart,
			String iDisplayLength, Integer articleId, Integer storeId) {
		PageView<Article> pageView = new PageView<Article>(iDisplayStart,
				iDisplayLength);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageView", pageView);
		map.put("storeId", storeId);
		if (null != articleId) {
			map.put("articleId", articleId);
			Article article = articleMapper.selectByPrimaryKey(articleId);
			if (null != article && StringUtils.isNotBlank(article.getTag())) {
				map.put("labels", article.getTag().split(","));
			}
		}
		pageView.setQueryResult(articleMapper.selectLikeByPage(map));
		return pageView;
	}

	@CacheEvict(value = { "article" }, allEntries = true)
	@Override
	public void updateArticleState(int articleId, int state) {
		Article tmp = new Article();
		tmp.setId(articleId);
		tmp.setArticleState(state);
		this.articleMapper.updateByPrimaryKeySelective(tmp);
	}

}
