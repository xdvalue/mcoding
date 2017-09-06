package com.mcoding.base.cms.service.recommendarticle.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mcoding.base.cms.bean.recommendarticle.RecommendArticle;
import com.mcoding.base.cms.bean.recommendarticle.RecommendArticleExample;
import com.mcoding.base.cms.persistence.recommendarticle.RecommendArticleMapper;
import com.mcoding.base.cms.service.recommendarticle.RecommendArticleService;
import com.mcoding.base.core.PageView;

/**
 * RecommendArticleServiceImpl
 * 
 * @author acer
 * 
 */
@Service
public class RecommendArticleServiceImpl implements RecommendArticleService {

	@Resource
	protected RecommendArticleMapper recommendArticleMapper;

	@Override
	public void addObj(RecommendArticle recommendArticle) {
		recommendArticleMapper.insertSelective(recommendArticle);
	}

	@Override
	public void deleteObjById(int recommendArticleId) {
		recommendArticleMapper.deleteByPrimaryKey(recommendArticleId);
	}

	@Override
	public void modifyObj(RecommendArticle recommendArticle) {
		recommendArticleMapper.updateByPrimaryKeySelective(recommendArticle);
	}

	@Override
	public List<RecommendArticle> queryAllObjByExample(
			RecommendArticleExample recommendArticleExample) {
		return recommendArticleMapper.selectByExample(recommendArticleExample);
	}

	@Override
	public RecommendArticle queryObjById(int recommendArticleId) {
		return recommendArticleMapper.selectByPrimaryKey(recommendArticleId);
	}

	@Override
	public PageView<RecommendArticle> queryObjByPage(
			RecommendArticleExample example) {
		PageView<RecommendArticle> pageView = example.getPageView();
		if (pageView == null) {
			pageView = new PageView<RecommendArticle>();
			pageView.setPageNo(1);
			pageView.setPageSize(10);
			example.setPageView(pageView);
		}
		List<RecommendArticle> list = recommendArticleMapper
				.selectByExampleByPage(example);
		pageView.setQueryResult(list);
		return pageView;
	}

	@Transactional
	@Override
	public void addOrDeleteByArticleId(int articleId,
			List<RecommendArticle> recommendArticleList) {
		RecommendArticleExample example = new RecommendArticleExample();
		example.createCriteria().andRefIdEqualTo(articleId);
		this.recommendArticleMapper.deleteByExample(example);

		for (int i = 0; CollectionUtils.isNotEmpty(recommendArticleList)
				&& i < recommendArticleList.size(); i++) {
			recommendArticleList.get(i).setRefId(articleId);
			this.recommendArticleMapper.insertSelective(recommendArticleList
					.get(i));
		}
	}

	@Override
	public void deleteByArticleId(int articleId) {
		RecommendArticleExample example = new RecommendArticleExample();
		example.createCriteria().andRefIdEqualTo(articleId);
		this.recommendArticleMapper.deleteByExample(example);
	}

}
