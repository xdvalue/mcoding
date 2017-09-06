package com.mcoding.base.cms.web.controller.recommendarticle;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mcoding.base.cms.bean.recommendarticle.RecommendArticle;
import com.mcoding.base.cms.bean.recommendarticle.RecommendArticleExample;
import com.mcoding.base.cms.service.recommendarticle.RecommendArticleService;
import com.mcoding.base.core.JsonResult;
import com.mcoding.base.core.PageView;

/**
 * RecommendArticleController
 * 
 * @author acer
 * 
 */
@Api("推荐文章")
@Controller
@RequestMapping("recommendArticle")
public class RecommendArticleController {

	@Autowired
	private RecommendArticleService recommendArticleService;

	@RequestMapping("service/addRecommendArticle")
	@ResponseBody
	public JsonResult<String> addArticle(
			@RequestBody RecommendArticle recommendArticle) {
		recommendArticleService.addObj(recommendArticle);
		JsonResult<String> result = new JsonResult<String>();
		result.setData(null);
		result.setMsg("ok");
		result.setStatus("00");
		return result;
	}

	@RequestMapping("service/findByArticleId")
	@ResponseBody
	public JsonResult<List<RecommendArticle>> findByArticleId(int articleId) {

		RecommendArticleExample example = new RecommendArticleExample();
		example.createCriteria().andRefIdEqualTo(articleId);

		List<RecommendArticle> list = this.recommendArticleService
				.queryAllObjByExample(example);

		JsonResult<List<RecommendArticle>> result = new JsonResult<>();
		result.setStatus("00");
		result.setMsg("ok");
		result.setData(list);
		return result;
	}

	/**
	 * 根据文章查推荐文章
	 * 
	 * @param iDisplayStart
	 * @param iDisplayLength
	 * @param articleId
	 * @return
	 */
	@ApiOperation(value = "根据文章查推荐文章", httpMethod = "GET")
	@RequestMapping("front/queryRecommendArticlePage")
	@ResponseBody
	public PageView<RecommendArticle> queryRecommendArticlePage(
			String iDisplayStart, String iDisplayLength, Integer articleId) {
		RecommendArticleExample example = new RecommendArticleExample();
		PageView<RecommendArticle> pageView = new PageView<RecommendArticle>(
				iDisplayStart, iDisplayLength);
		example.setPageView(pageView);
		example.createCriteria().andRefIdEqualTo(articleId);
		example.setOrderByClause(" create_time desc ");
		List<RecommendArticle> articleList = recommendArticleService
				.queryAllObjByExample(example);
		pageView.setQueryResult(articleList);
		return pageView;
	}

}