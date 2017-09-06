package com.mcoding.base.sns.web.controller.post;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mcoding.base.core.CommonException;
import com.mcoding.base.core.JsonResult;
import com.mcoding.base.core.PageView;
import com.mcoding.base.member.bean.member.Member;
import com.mcoding.base.member.service.member.MemberService;
import com.mcoding.base.sns.bean.module.SnsModule;
import com.mcoding.base.sns.bean.module.SnsModuleExample;
import com.mcoding.base.sns.bean.post.SnsPost;
import com.mcoding.base.sns.bean.post.SnsPostExample;
import com.mcoding.base.sns.bean.post.SnsPostExample.Criteria;
import com.mcoding.base.sns.service.module.SnsModuleService;
import com.mcoding.base.sns.service.post.SnsPostExtInfoService;
import com.mcoding.base.sns.service.post.SnsPostService;
import com.mcoding.base.ui.bean.store.Store;
import com.mcoding.base.ui.utils.common.Constant;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import springfox.documentation.annotations.ApiIgnore;

@Api("微社区发帖")
@Controller
@RequestMapping("snsPost")
public class SnsPostController {
	/**每次查询添加的点击率**/
	private static final int ADD_VIEW_NUM_FOR_EVERY_QUERY = 1;
	
	@Resource
	private SnsPostService postService;

	@Resource
	private SnsModuleService moduleService;
	
	@Resource
	private MemberService memberService;
	
	@Resource
	protected SnsPostExtInfoService snsPostExtInfoService;

	@Resource(name="defaultThreadPool")
	protected ThreadPoolTaskExecutor threadPoolTaskExecutor;

	@ApiIgnore
	@RequestMapping("service/toMainView")
	public ModelAndView toMainView(HttpSession session) {
		ModelAndView view = new ModelAndView();
		
		this.setInModelView(session, view);
		
		view.setViewName("sns/snsPost/toMainView");
		return view;
	}
	
	@RequestMapping("service/toAddView")
	public ModelAndView toAddView(HttpSession session) {
		ModelAndView mav = new ModelAndView("sns/snsPost/toAddView");
		this.setInModelView(session, mav);
		return mav;
	}

	@RequestMapping("service/toUpdateViewById")
	public ModelAndView toUpdateViewById(@RequestParam(required = true) int id, HttpSession session) {
		ModelAndView mav = new ModelAndView("sns/snsPost/toAddView");
		mav.addObject("snsPost", postService.queryObjById(id));
		mav.addObject("edit", "edit");
		
		this.setInModelView(session, mav);
		return mav;
	}
	
	private void setInModelView(HttpSession session, ModelAndView view){
		
		Integer storeId = (Integer) session.getAttribute("storeId");
		SnsModuleExample example = new SnsModuleExample();
		example.createCriteria().andStoreIdEqualTo(storeId).andStatusCodeEqualTo(Constant.YES_INT);
		List<SnsModule> snsModules = this.moduleService.queryAllObjByExample(example);
		
		view.addObject("snsModules", snsModules);
	}

	@ApiOperation(httpMethod = "POST", value = "编辑帖子")
	@RequestMapping("service/edit")
	@ResponseBody
	public JsonResult<String> edit(@RequestBody SnsPost snsPost) {
		postService.modifyObj(snsPost);

		JsonResult<String> result = new JsonResult<>();
		result.setStatus("00");
		result.setMsg("ok");
		return result;
	}

	@ApiOperation(httpMethod = "GET", value = "分页帖子模块")
	@RequestMapping("service/findByPage")
	@ResponseBody
	public PageView<SnsPost> findByPageForService(
			@ApiParam(name = "分页索引", defaultValue = "0") @RequestParam(defaultValue = "0") String iDisplayStart,
			@ApiParam(name = "每页的数量", defaultValue = "10") @RequestParam(defaultValue = "10") String iDisplayLength,
			@ApiParam(name = "查询条件") String sSearch,
			@ApiParam(name = "是否审核") @RequestParam(defaultValue = "-1") int isCheck,
			@ApiParam(name = "帖子类型") @RequestParam(defaultValue = "-1") int typeFlag) {
		
		PageView<SnsPost> pageView = new PageView<>(iDisplayStart, iDisplayLength);
		SnsPostExample postExample = new SnsPostExample();
		postExample.setPageView(pageView);

		Criteria postCriteria = postExample.createCriteria();
		if (isCheck >= 0) {
			postCriteria.andIsCheckEqualTo(isCheck);
		}
		if (typeFlag >= 0) {
			postCriteria.andTypeFlagEqualTo(typeFlag);
		}
		if (StringUtils.isNotEmpty(sSearch)) {
			postCriteria.andTitleLike("%" + sSearch + "%");
		}

		postExample.setOrderByClause("order_num DESC,create_time DESC");
		return postService.queryObjByPage(postExample);
	}

	@ApiOperation(value = "帖子置顶,或者取消置顶", httpMethod = "POST")
	@RequestMapping(value = "/service/upTopById")
	@ResponseBody
	public JsonResult<String> upTopById(HttpSession session,
			@ApiParam(value = "帖子ID", required = true) @RequestParam(required = true) int postId,
			@ApiParam(value = "置顶参数，1为首页置顶，0为模板内置顶", required = true) Integer isHomeUp,
			@ApiParam(value = "置顶参数，1为置顶，0为取消置顶", required = true) @RequestParam(required = true) int isUp) {
		Integer storeId = (Integer) session.getAttribute("storeId");

		if (Constant.YES_INT.equals(isUp)) {
			if(Constant.YES_INT.equals(isHomeUp)){
				this.postService.upOrDownTopInHome(storeId, postId, true);
			}else{
				this.postService.upOrDownTop(storeId, postId, true);
			}

		} else if (Constant.NO_INT.equals(isUp)) {
			if(Constant.YES_INT.equals(isHomeUp)){
				this.postService.upOrDownTopInHome(storeId, postId, false);
			}else{
				this.postService.upOrDownTop(storeId, postId, false);
			}

		} else {
			throw new CommonException("isUp参数异常");
		}

		JsonResult<String> result = new JsonResult<>();
		result.setStatus("00");
		result.setMsg("ok");
		return result;
	}
	
	@ApiOperation(value = "发帖", httpMethod = "POST")
	@RequestMapping("service/createForDashboard")
	@ResponseBody
	public JsonResult<String> createForDashboard(HttpSession session, @RequestBody SnsPost snsPost) {
		if (snsPost.getModuleId() == null || snsPost.getModuleId() == 0) {
			throw new CommonException("请选择要发表的模块");
		}
		
		if (StringUtils.isBlank(snsPost.getContent()) || snsPost.getContent().length() < 20) {
			throw new CommonException("帖子最少字数是20");
		}
		
		Member member = null;
		if (snsPost.getMemeberId() != null) {
			member = this.memberService.queryObjById(snsPost.getMemeberId());
		}else if(snsPost.getMember() != null && snsPost.getMember().getId() != null){
			member = this.memberService.queryObjById(snsPost.getMember().getId());
		}
		
		if (member == null) {
			throw new CommonException("作者信息不能为空");
		}
		
		Store store = (Store) session.getAttribute("store");
		
		snsPost.setStoreId(store.getId());
		snsPost.setStoreName(store.getStoreName());
		snsPost.setMemeberId(member.getId());
		snsPost.setMemberName(member.getName());
		snsPost.setMemberImgUrl(member.getHeadImgUrl());
		snsPost.setIsCheck(Constant.YES_INT);
		if(member.getWxMember()!=null){
			snsPost.setOpenId(member.getWxMember().getWxOpenid());
		}
		
//		 postService.addObj(snsPost);
		postService.post(snsPost, member);

		JsonResult<String> result = new JsonResult<>();
		result.setStatus("00");
		result.setMsg("ok");
		return result;
	}

	@ApiOperation(value = "发帖", httpMethod = "POST")
	@RequestMapping("front/post")
	@ResponseBody
	public JsonResult<String> create(HttpSession session, @RequestBody SnsPost snsPost) {
		if (snsPost.getModuleId() == null || snsPost.getModuleId() == 0) {
			throw new CommonException("请选择要发表的模块");
		}
		
		if (StringUtils.isBlank(snsPost.getContent()) || snsPost.getContent().length() < 20) {
			throw new CommonException("帖子最少字数是20");
		}
		String openId = (String) session.getAttribute("openid");
		if (StringUtils.isBlank(openId)) {
			throw new CommonException("获取当前用户信息失败，请刷新重试。谢谢！");
		}
		
		Store store = (Store) session.getAttribute("store");
		Member member = (Member) session.getAttribute("member");

		snsPost.setStoreId(store.getId());
		snsPost.setStoreName(store.getStoreName());
		snsPost.setOpenId(openId);
		snsPost.setMemeberId(member.getId());
		snsPost.setMemberName(member.getName());
		snsPost.setMemberImgUrl(member.getHeadImgUrl());
		snsPost.setIsCheck(Constant.YES_INT);
//		 postService.addObj(snsPost);
		postService.post(snsPost, member);

		JsonResult<String> result = new JsonResult<>();
		result.setStatus("00");
		result.setMsg("ok");
		return result;
	}

	@ApiOperation(value = "查找当前用户的帖子", httpMethod = "GET")
	@RequestMapping("front/findPostByCurrentMember")
	@ResponseBody
	public JsonResult<PageView<SnsPost>> findPostByCurrentMember(HttpSession session,
			@ApiParam(value = "页码") @RequestParam(required = true) int pageNo,
			@ApiParam(value = "每页大小") @RequestParam(required = true) int pageSize) {
		Integer storeId = (Integer) session.getAttribute("storeId");
		Integer memberId = (Integer) session.getAttribute("memberId");

		SnsPostExample example = new SnsPostExample();
		PageView<SnsPost> pageView = new PageView<>(pageNo, pageSize);
		example.setPageView(pageView);

		example.createCriteria().andStoreIdEqualTo(storeId).andMemeberIdEqualTo(memberId);
		example.setOrderByClause("id DESC");

		JsonResult<PageView<SnsPost>> result = new JsonResult<>();
		result.setMsg("ok");
		result.setStatus("00");
		result.setData(this.postService.queryObjByPage(example));

		return result;

	}

	@ApiOperation(value = "分页查询帖子", httpMethod = "POST")
	@RequestMapping("/front/findPostByPage")
	@ResponseBody
	public JsonResult<PageView<SnsPost>> findPostByPage(HttpSession session,
			@ApiParam(value = "页码") @RequestParam(required = true) int pageNo,
			@ApiParam(value = "每页大小") @RequestParam(required = true) int pageSize,
			@ApiParam(value = "模块id,如果不分模块，则传0，或不传") @RequestParam(defaultValue = "0") int moduleId) {
		Integer storeId = (Integer) session.getAttribute("storeId");
		Integer memberId = (Integer) session.getAttribute("memberId");
		PageView<SnsPost> pageView = new PageView<>(pageNo, pageSize);
		
		if (moduleId <=0) {
			pageView = this.findPostByPageInHome(pageView, storeId, memberId);
		}else{
			pageView = this.findPostByPageInModules(pageView, moduleId, storeId, memberId);
		}

		JsonResult<PageView<SnsPost>> result = new JsonResult<>();
		result.setData(pageView);
		result.setStatus("00");
		result.setMsg("ok");

		return result;
	}
	
	private PageView<SnsPost> findPostByPageInHome(PageView<SnsPost> pageView, int storeId, int memberId){
		SnsPostExample postExample = new SnsPostExample();
		postExample.setPageView(pageView);
		
		SnsPostExample.Criteria cri = postExample.createCriteria();
		cri.andStoreIdEqualTo(storeId);
		cri.andIsCheckEqualTo(Constant.YES_INT);
		
		Criteria cri2 = postExample.or();
		cri2.andStoreIdEqualTo(storeId);
		cri2.andMemeberIdEqualTo(memberId);
		
		postExample.setOrderByClause("order_in_home DESC,reply_time DESC");
		return postService.queryObjByPage(postExample);
	}
	
	private PageView<SnsPost> findPostByPageInModules(PageView<SnsPost> pageView, int moduleId, int storeId, int memberId){
		List<Integer> moduleIds = new ArrayList<>();
		if (moduleId > 0) {
			// 查询所有子模块
			List<SnsModule> childModules = moduleService.queryChildModulesById(storeId, moduleId);
			if (CollectionUtils.isNotEmpty(childModules)) {
				for (SnsModule cm : childModules) {
					moduleIds.add(cm.getId());
				}
			}
			moduleIds.add(moduleId);
		}
		
		SnsPostExample postExample = new SnsPostExample();
		postExample.setPageView(pageView);
		
		SnsPostExample.Criteria cri = postExample.createCriteria();
		cri.andStoreIdEqualTo(storeId);
		cri.andIsCheckEqualTo(Constant.YES_INT);
		cri.andModuleIdIn(moduleIds);
		
		Criteria cri2 = postExample.or();
		cri2.andStoreIdEqualTo(storeId);
		cri2.andMemeberIdEqualTo(memberId);
		cri2.andModuleIdIn(moduleIds);
		
		postExample.setOrderByClause("order_num DESC,reply_time DESC");
		
		return postService.queryObjByPage(postExample);
	}

	@ApiOperation(value = "查询特殊帖列表", httpMethod = "GET")
	@RequestMapping("/front/querySpecialPostByPage")
	@ResponseBody
	public JsonResult<PageView<SnsPost>> querySpecialPostByPage(HttpServletRequest request, String sSearch,
			@ApiParam(value = "页码") @RequestParam(required = true) int pageNo,
			@ApiParam(value = "每页大小") @RequestParam(required = true) int pageSize,
			@ApiParam(value = "特殊帖子分类id,查询所有，则传0；查询活动贴，则传2；查询精品贴，则传3") @RequestParam(required = true) int typeFlag) {
//		Integer storeId = (Integer) session.getAttribute("storeId");
		Integer storeId = (Integer) request.getSession().getAttribute("storeId");
//		logger.info("storeId:" + storeId + ", sessionid" + request.getSession().getId());
		
		SnsPostExample example = new SnsPostExample();
		PageView<SnsPost> pageView = new PageView<>(pageNo, pageSize);
		example.setPageView(pageView);

		List<Integer> typeFlags = new ArrayList<>();

		if (SnsPost.TYPE_FLAG_ACTIVITY == typeFlag) {
			typeFlags.add(SnsPost.TYPE_FLAG_ACTIVITY);

		} else if (SnsPost.TYPE_FLAG_BOUTIQUE == typeFlag) {
			typeFlags.add(SnsPost.TYPE_FLAG_BOUTIQUE);

		} else if (Constant.NO_INT == typeFlag) {
			typeFlags.add(SnsPost.TYPE_FLAG_ACTIVITY);
			typeFlags.add(SnsPost.TYPE_FLAG_BOUTIQUE);
		} else {
			throw new CommonException("typeFlag参数异常");
		}
//
		Criteria cri = example.createCriteria();
		cri.andStoreIdEqualTo(storeId);
		if (StringUtils.isNotBlank(sSearch)) {
			cri.andTitleLike(sSearch + "%");
		}
		cri.andTypeFlagIn(typeFlags);
		example.setOrderByClause("update_time DESC");

		JsonResult<PageView<SnsPost>> result = new JsonResult<>();
		result.setData(postService.queryObjByPage(example));
		result.setStatus("00");
		result.setMsg("ok");

		return result;
	}

	@ApiOperation(value = "分页查询当前用户评论过的帖子", httpMethod = "GET")
	@RequestMapping("/front/findCommentedPostByPage")
	@ResponseBody
	public JsonResult<PageView<SnsPost>> findCommentedPostByPage(HttpSession session,
			@ApiParam(value = "页码") @RequestParam(required = true) int pageNo,
			@ApiParam(value = "每页大小") @RequestParam(required = true) int pageSize) {
		Integer storeId = (Integer) session.getAttribute("storeId");
		Integer memberId = (Integer) session.getAttribute("memberId");


		JsonResult<PageView<SnsPost>> result = new JsonResult<>();
		result.setData(postService.queryByMemberComment(memberId, storeId, pageNo, pageSize));
		result.setStatus("00");
		result.setMsg("ok");

		return result;
	}

	@ApiOperation(value = "查询帖子详细信息", httpMethod = "GET")
	@RequestMapping("/front/findPostById")
	@ResponseBody
	public JsonResult<SnsPost> findPostById(@ApiParam(value = "帖子ID") final int id) {
		
		JsonResult<SnsPost> result = new JsonResult<>();
		result.setData(postService.queryObjById(id));
		result.setStatus("00");
		result.setMsg("ok");
		
		threadPoolTaskExecutor.execute(new Runnable() {

			@Override
			public void run() {
				SnsPostController.this.snsPostExtInfoService.addViewNum(id, ADD_VIEW_NUM_FOR_EVERY_QUERY);
			}
		});
		return result;
	}

	@ApiOperation(value = "给帖子点赞,或者取消点赞", httpMethod = "POST")
	@RequestMapping(value = "/front/likePost")
	@ResponseBody
	public JsonResult<String> likePost(HttpSession session,
			@ApiParam(value = "帖子ID") @RequestParam(required = true) int postId,
			@ApiParam(value = "点赞参数，1为点赞，0为取消点赞") @RequestParam(required = true) int isLike) {
		Integer storeId = (Integer) session.getAttribute("storeId");
		Integer memberId = (Integer) session.getAttribute("memberId");

		if (Constant.YES_INT.equals(isLike)) {
			this.postService.addOrRemoveLike(storeId, memberId, postId, true);

		} else if (Constant.NO_INT.equals(isLike)) {
			this.postService.addOrRemoveLike(storeId, memberId, postId, false);

		} else {
			throw new CommonException("isLike参数异常");
		}

		JsonResult<String> result = new JsonResult<>();
		result.setStatus("00");
		result.setMsg("ok");
//		result.setStatus((String) resultMap.get("resultCode"));
//		result.setMsg((String) resultMap.get("resultDesc"));
		return result;
	}

	@ApiOperation(value = "给帖子点衰,或者取消点衰", httpMethod = "POST")
	@RequestMapping(value = "/front/disLikePost")
	@ResponseBody
	public JsonResult<String> disLikePost(HttpSession session,
			@ApiParam(value = "帖子ID") @RequestParam(required = true) int postId,
			@ApiParam(value = "点衰参数，1为点衰，0为取消点衰") @RequestParam(required = true) int isDisLike) {
		Integer storeId = (Integer) session.getAttribute("storeId");
		Integer memberId = (Integer) session.getAttribute("memberId");

		if (Constant.YES_INT.equals(isDisLike)) {
			this.postService.addOrRemoveDislike(storeId, memberId, postId, true);

		} else if (Constant.NO_INT.equals(isDisLike)) {
			this.postService.addOrRemoveDislike(storeId, memberId, postId, false);

		} else {
			throw new CommonException("isDisLike参数异常");
		}

		JsonResult<String> result = new JsonResult<>();
		result.setStatus("00");
		result.setMsg("ok");
		return result;
	}
	
	@ApiOperation(value = "审核或取消审核帖子", httpMethod = "GET")
	@RequestMapping(value = "/service/setIsCheck")
	@ResponseBody
	public JsonResult<String> setIsCheck(String ids, int isCheck){
		if (!Constant.YES_INT.equals(isCheck) && !Constant.NO_INT.equals(isCheck)) {
			throw new CommonException("参数异常，请刷新后重试");
		}
		
		String[] idArray = ids.split("\\s*,\\s*");
		for (int i = 0; i < idArray.length; i++) {
			this.postService.checkPost(Integer.valueOf(idArray[i]), isCheck);
		}
		
		JsonResult<String> result = new JsonResult<>();
		result.setStatus("00");
		result.setMsg("ok");
		return result;
	}
	
	@ApiOperation(value = "审核或取消审核帖子", httpMethod = "GET")
	@RequestMapping(value = "/service/setPostAsEssence")
	@ResponseBody
	public JsonResult<String> setPostAsEssence(String ids, int isEssence){
		if (!Constant.YES_INT.equals(isEssence) && !Constant.NO_INT.equals(isEssence)) {
			throw new CommonException("参数异常，请刷新后重试");
		}
		String[] idArray = ids.split("\\s*,\\s*");
		for (int i = 0; i < idArray.length; i++) {
			this.postService.setEssencePost(Integer.valueOf(idArray[i]), isEssence);
		}
		
		JsonResult<String> result = new JsonResult<>();
		result.setStatus("00");
		result.setMsg("ok");
		return result;
	}
	
	@ApiOperation(value = "转移帖子", httpMethod = "GET")
	@RequestMapping(value = "/service/changePostModule")
	@ResponseBody
	public JsonResult<String> changePostModule(String ids, int moduleId){
		if (StringUtils.isBlank(ids) || moduleId<=0) {
			throw new CommonException("参数异常，请刷新后重试");
		}
		
		String[] idArray = ids.split("\\s*,\\s*");
		List<Integer> idList = new ArrayList<>();
		for(int i=0; i<idArray.length; i++){
			idList.add(Integer.valueOf(idArray[i]));
		}
		
		if (CollectionUtils.isEmpty(idList)) {
			throw new CommonException("参数异常，请刷新后重试");
		}
		
		SnsModule snsModule = this.moduleService.queryObjById(moduleId);
		if (snsModule == null) {
			throw new CommonException("参数异常，该模块不存在,请刷新后重试");
		}
		
		this.postService.mvToModule(idList, snsModule);
		
		JsonResult<String> result = new JsonResult<>();
		result.setStatus("00");
		result.setMsg("ok");
		return result;
	}

	@RequestMapping("service/addPostViewCount")
	@ResponseBody
	public JsonResult<String> addPostViewCount(int postId, int addCount){
		if (addCount <= 0) {
			throw new CommonException("要添加的点击率不正确,不能少于0");
		}
		snsPostExtInfoService.addViewNum(postId, addCount);
		
		JsonResult<String> result = new JsonResult<>();
		result.setStatus("00");
		result.setMsg("ok");
		return result;
	}
}