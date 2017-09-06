package com.mcoding.base.cms.web.controller.article;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mcoding.base.cms.bean.article.Article;
import com.mcoding.base.cms.bean.article.ArticleExample;
import com.mcoding.base.cms.bean.article.ArticleExample.Criteria;
import com.mcoding.base.cms.service.article.ArticleService;
import com.mcoding.base.core.JsonResult;
import com.mcoding.base.core.PageView;
import com.mcoding.base.member.bean.member.Member;
import com.mcoding.base.ui.utils.StoreUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import springfox.documentation.annotations.ApiIgnore;

/**
 * ArticleController
 * 
 * @author acer
 * 
 */
@Api("文章管理")
@Controller
@RequestMapping("article")
public class ArticleController {

	@Resource
	protected ArticleService articleService;
	
	@ApiOperation(value = "文章页面跳转", httpMethod = "GET")
	@RequestMapping("service/toListPageView")
	public ModelAndView toListPageView() {
		ModelAndView mv = new ModelAndView("cms/article/listPageView");
		return mv;
	}

	@ApiIgnore
	@RequestMapping("service/toAddView")
	public ModelAndView toAddView() {
		ModelAndView view = new ModelAndView();
		view.setViewName("cms/article/toAddView");
		return view;
	}

	@ApiIgnore
	@RequestMapping("service/toUpdateViewById")
	public ModelAndView toUpdateViewById(int id) {
		ModelAndView view = new ModelAndView();

		Article article = this.articleService.queryObjById(id);

		view.addObject("article", article);
		view.setViewName("cms/article/toAddView");
		return view;
	}

	@ApiOperation(value = "添加文章", httpMethod = "POST")
	@RequestMapping("service/create")
	@ResponseBody
	public JsonResult<String> create(@RequestBody Article article) {
		article.setArticleState(Article.STATE_PUBLISHED);
		this.articleService.addObj(article);
		JsonResult<String> result = new JsonResult<>();
		result.setData(null);
		result.setMsg("ok");
		result.setStatus("00");
		return result;
	}

	@ApiOperation(value = "修改文章", httpMethod = "POST")
	@RequestMapping("service/edit")
	@ResponseBody
	public JsonResult<String> edit(@RequestBody Article article) {
		this.articleService.modifyObj(article);
		JsonResult<String> result = new JsonResult<>();
		result.setData(null);
		result.setMsg("ok");
		result.setStatus("00");
		return result;
	}

	/**
	 * 修改文章状态
	 * 
	 * @param articleId
	 * @param state
	 *            1-草稿、2-待审核、3-已发布、4-回收站
	 * @return
	 */
	@ApiOperation(value = "修改文章的状态", httpMethod = "POST")
	@RequestMapping("service/setArticleState")
	@ResponseBody
	public JsonResult<String> setArticleState(
			@ApiParam(name = "文章状态：1-草稿、2-待审核、3-已发布、4-回收站", required = true) int state, int articleId) {

		Article article = new Article();
		article.setId(articleId);
		article.setArticleState(state);

		// this.articleService.modifyObj(article);
		this.articleService.updateArticleState(articleId, state);

		JsonResult<String> result = new JsonResult<>();
		result.setData(null);
		result.setMsg("ok");
		result.setStatus("00");
		return result;
	}

	@ApiOperation(value = "删除文章", httpMethod = "POST")
	@RequestMapping("service/delete")
	@ResponseBody
	public JsonResult<String> delete(int articleId) {
		JsonResult<String> result = new JsonResult<String>();
		this.articleService.deleteObjById(articleId);
		result.setData(null);
		result.setMsg("ok");
		result.setStatus("00");
		return result;
	}

	@ApiOperation(value = "根据文章ID查询文章", httpMethod = "GET")
	@RequestMapping("service/findByArticleId")
	@ResponseBody
	public JsonResult<Article> findByArticleId(int articleId) {
		JsonResult<Article> result = new JsonResult<Article>();
		Article activity = this.articleService.queryObjById(articleId);
		result.setData(activity);
		result.setMsg("ok");
		result.setStatus("00");
		return result;
	}

	@ApiOperation(value = "分页查询文章", httpMethod = "GET")
	@RequestMapping("service/findByPage")
	@ResponseBody
	public PageView<Article> findByPage(HttpServletRequest request,
			@ApiParam(value="文章状态：1-草稿、2-待审核、3-已发布、4-回收站。不传则查询全部") Integer articleState,
			@ApiParam(value="数据索引", defaultValue="1") @RequestParam(defaultValue="1") String iDisplayStart, 
			@ApiParam(value="每页的数量", defaultValue="10") @RequestParam(defaultValue="10")String iDisplayLength) {

		String search = request.getParameter("sSearch");

		PageView<Article> pageView = new PageView<Article>(iDisplayStart, iDisplayLength);
		ArticleExample example = new ArticleExample();
		example.setPageView(pageView);
		example.setOrderByClause("publish_time DESC, create_time DESC");

		Criteria cri = example.createCriteria();
		cri.andStoreIdEqualTo(StoreUtils.getStoreIdFromThreadLocal());
		if (StringUtils.isNotBlank(search)) {
			cri.andTitleLike("%" + search + "%");
		}
		if (articleState != null && articleState != 0) {
			cri.andArticleStateEqualTo(articleState);
		}
		return this.articleService.queryObjByPage(example);
	}

	@ApiOperation(value = "根据文章ID查询文章", httpMethod = "GET")
	@RequestMapping("front/findByArticleId")
	@ResponseBody
	public JsonResult<Article> findArticleById(int articleId) {
		JsonResult<Article> result = new JsonResult<Article>();
		Article activity = this.articleService.queryObjById(articleId);
		articleService.updateClickCount(articleId, 1);// 更新文章点击量
		result.setData(activity);
		result.setMsg("ok");
		result.setStatus("00");
		return result;
	}

	/**
	 * 根据条件查询文章
	 * 
	 * @param iDisplayStart
	 * @param iDisplayLength
	 * @param pramType
	 * @param pramValue
	 * @return
	 */
	@ApiOperation(value = "根据条件查询文章", httpMethod = "GET")
	@RequestMapping("front/findArticleByPage")
	@ResponseBody
	public PageView<Article> findArticleByPage(HttpSession session,
			@ApiParam(value="页码", defaultValue="1") @RequestParam(defaultValue="1") Integer pageNo,
			@ApiParam(value="每页的数量",defaultValue="10") @RequestParam(defaultValue="10")Integer pageSize) {

		ArticleExample articleExample = new ArticleExample();
		articleExample.createCriteria()
		              .andArticleStateEqualTo(Article.STATE_PUBLISHED)
		              .andStoreIdEqualTo(StoreUtils.getStoreIdFromThreadLocal());
		
		articleExample.setPageView(new PageView<Article>(pageNo, pageSize));
		articleExample.setOrderByClause("create_time DESC");
		
		return this.articleService.queryObjByPage(articleExample);
	}

	/**
	 * 查询点赞或点衰列表
	 * 
	 * @param iDisplayStart
	 * @param iDisplayLength
	 * @param type
	 *            类型（0：赞，1：衰）
	 * @return
	 */
	@ApiOperation(value = "查询点赞或点衰列表", httpMethod = "GET", notes = "type 类型（0：赞，1：衰）")
	@RequestMapping("front/findFavoriteArticleByPage")
	@ResponseBody
	public PageView<Article> findFavoriteArticleByPage(HttpSession session,
			@ApiParam(value="类型（0：赞，1：衰") int type,
			@ApiParam(value="页码", defaultValue="1") @RequestParam(defaultValue="1") Integer pageNo,
			@ApiParam(value="每页的数量",defaultValue="10") @RequestParam(defaultValue="10")Integer pageSize) {
		
		Member member = (Member) session.getAttribute("member");
		return articleService.selectFavoriteByPage(member, type, pageNo, pageSize, StoreUtils.getStoreIdFromThreadLocal());
	}

	/**
	 * 查询收藏文章列表
	 * 
	 * @param iDisplayStart
	 * @param iDisplayLength
	 * @return
	 */
	@ApiOperation(value = "查询收藏文章列表", httpMethod = "GET")
	@RequestMapping("front/findCollectArticleByPage")
	@ResponseBody
	public PageView<Article> findCollectArticleByPage(HttpSession session,
			@ApiParam(value="页码", defaultValue="1") @RequestParam(defaultValue="1") Integer pageNo,
			@ApiParam(value="每页的数量",defaultValue="10") @RequestParam(defaultValue="10")Integer pageSize) {
		Member member = (Member) session.getAttribute("member");
		
		return articleService.selectCollectByPage(member, pageNo, pageSize, StoreUtils.getStoreIdFromThreadLocal());
	}

	/**
	 * 根据文章Id查询猜你喜欢文章
	 * 
	 * @param iDisplayStart
	 * @param iDisplayLength
	 * @param articleId
	 * @param request
	 * @return
	 */
	@ApiOperation(value = "根据文章Id查询猜你喜欢文章", httpMethod = "GET")
	@RequestMapping("front/selectLikeByPage")
	@ResponseBody
	public PageView<Article> selectLikeByPage(String iDisplayStart, String iDisplayLength, Integer articleId,
			HttpServletRequest request) {
		return articleService.selectLikeByPage(iDisplayStart, iDisplayLength, articleId, StoreUtils.getStoreIdFromThreadLocal());
	}

	/**
	 * 根据标签查询文章（多个标签使用，号分隔）
	 * 
	 * @param iDisplayStart
	 * @param iDisplayLength
	 * @param labels
	 * @param request
	 * @return
	 */
	@ApiOperation(value = "根据标签查询文章（多个标签使用，号分隔）", httpMethod = "GET")
	@RequestMapping("front/selectLabelByPage")
	@ResponseBody
	public PageView<Article> findArticlePageByLabel(HttpSession session,
			@ApiParam(value="文章标签,多个标签使用，号分隔", defaultValue="1") String labels,
			@ApiParam(value="页码", defaultValue="1") @RequestParam(defaultValue="1") Integer pageNo,
			@ApiParam(value="每页的数量",defaultValue="10") @RequestParam(defaultValue="10")Integer pageSize) {

		if (StringUtils.isBlank(labels)) {
			return this.findArticleByPage(session, pageNo, pageSize);
		}
		List<String> labelList = Arrays.asList(labels.split(",")); 
		return articleService.queryArticleByLabel(labelList, pageNo, pageSize, StoreUtils.getStoreIdFromThreadLocal());
	}

	@ApiOperation(value = "根据模块ID查询文章", httpMethod = "GET")
	@RequestMapping("front/selectModuleByPage")
	@ResponseBody
	public PageView<Article> selectModuleByPage(HttpSession session,
			@ApiParam(value="模块id，不传就查询所有文章") Integer moduleId,
			@ApiParam(value="页码", defaultValue="1") @RequestParam(defaultValue="1") Integer pageNo,
			@ApiParam(value="每页的数量",defaultValue="10") @RequestParam(defaultValue="10")Integer pageSize) {

		if (moduleId == null || moduleId <=0) {
			return this.findArticleByPage(session, pageNo, pageSize);
		}
		
		ArticleExample example = new ArticleExample();
		example.createCriteria()
		       .andStoreIdEqualTo(StoreUtils.getStoreIdFromThreadLocal())
		       .andModuleIdEqualTo(moduleId);
		
		example.setPageView(new PageView<Article>(pageNo, pageSize));
		example.setOrderByClause("sort_no DESC, create_time DESC, click_count DESC ");

		return this.articleService.queryObjByPage(example);
	}

	@ApiOperation(value = "根据文章类型查询文章", httpMethod = "GET")
	@RequestMapping("front/selectTypeByPage")
	@ResponseBody
	public PageView<Article> selectTypeByPage(HttpSession session,
			@ApiParam(value="文章类型，如果为空返回所有文章") Integer typeId,
			@ApiParam(value="页码", defaultValue="1") @RequestParam(defaultValue="1") Integer pageNo,
			@ApiParam(value="每页的数量",defaultValue="10") @RequestParam(defaultValue="10")Integer pageSize) {
		
		if (typeId == null || typeId <=0) {
			return this.findArticleByPage(session, pageNo, pageSize);
		}
		
		ArticleExample example = new ArticleExample();
		example.createCriteria()
		       .andStoreIdEqualTo(StoreUtils.getStoreIdFromThreadLocal())
		       .andTypeIdEqualTo(typeId);
		
		example.setPageView(new PageView<Article>(pageNo, pageSize));
		example.setOrderByClause("sort_no DESC, create_time DESC, click_count DESC ");

		return this.articleService.queryObjByPage(example);
		
	}
}