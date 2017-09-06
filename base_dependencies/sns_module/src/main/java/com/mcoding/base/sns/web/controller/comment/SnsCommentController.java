package com.mcoding.base.sns.web.controller.comment;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mcoding.base.core.CommonException;
import com.mcoding.base.core.JsonResult;
import com.mcoding.base.core.PageView;
import com.mcoding.base.member.bean.member.Member;
import com.mcoding.base.sns.bean.comment.SnsComment;
import com.mcoding.base.sns.bean.comment.SnsCommentExample;
import com.mcoding.base.sns.service.comment.SnsCommentService;
import com.mcoding.base.ui.utils.common.Constant;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api("微社区评论")
@Controller
@RequestMapping("snsComment")
public class SnsCommentController {

	@Resource
	private SnsCommentService commentService;

	@ApiOperation(value = "评论/回复", httpMethod = "POST")
	@RequestMapping(value = "/front/create")
	@ResponseBody
	public JsonResult<String> create(HttpSession session, @RequestBody SnsComment comment) {
		String openId = (String) session.getAttribute("openid");
		if (StringUtils.isBlank(openId)) {
			throw new CommonException("获取当前用户信息失败，请刷新重试。谢谢！");
		}
		
		Integer storeId = (Integer) session.getAttribute("storeId");
		Member member = (Member) session.getAttribute("member");
		Integer postId = comment.getPostId();

		if (postId == null || postId <= 0) {
			throw new CommonException("帖子Id不能为空");
		}

		comment.setOpenId(openId);
		comment.setMemberId(member.getId());
		comment.setMemberName(member.getName());
		comment.setMemberImgUrl(member.getHeadImgUrl());
		comment.setStoreId(storeId);

//		commentService.addObj(comment);
		commentService.comment(comment, postId, member);

		JsonResult<String> result = new JsonResult<>();
		result.setStatus("00");
		result.setMsg("ok");
		return result;
	}

	@ApiOperation(value = "分页查询评论", httpMethod = "GET")
	@RequestMapping(value = "/front/findCommentByPage")
	@ResponseBody
	public JsonResult<PageView<SnsComment>> findCommentByPage(HttpSession session,
			@ApiParam(value = "帖子id", required = true) @RequestParam(required = true) int postId,
			@ApiParam(value = "页码", required = true) @RequestParam(defaultValue = "0") int pageNo,
			@ApiParam(value = "每页大小", required = true) @RequestParam(defaultValue = "10") int pageSize) {
		SnsCommentExample example = new SnsCommentExample();
		SnsCommentExample.Criteria cri1 = example.createCriteria();
		cri1.andPostIdEqualTo(postId);
		cri1.andParentCommentIdEqualTo(Constant.NO_INT);
		cri1.andIsEnabledEqualTo(true);
		cri1.andIsCheckedEqualTo(true);
		
		SnsCommentExample.Criteria cri2 = example.or();
		cri2.andPostIdEqualTo(postId);
		cri2.andParentCommentIdIsNull();
		cri2.andIsEnabledEqualTo(true);
		cri2.andIsCheckedEqualTo(true);

		PageView<SnsComment> pageView = new PageView<>(pageNo, pageSize);
		example.setPageView(pageView);
		example.setOrderByClause("create_time ASC");
		pageView = commentService.queryObjByPage(example);

		JsonResult<PageView<SnsComment>> result = new JsonResult<>();
		result.setStatus("00");
		result.setMsg("ok");
		result.setData(pageView);

		return result;
	}

	@ApiOperation(value = "给评论点赞,或者取消点赞", httpMethod = "POST")
	@RequestMapping(value = "/front/likeComment")
	@ResponseBody
	public JsonResult<String> likeComment(HttpSession session,
			@ApiParam(value = "评论ID", required = true) @RequestParam(required = true) int commentId,
			@ApiParam(value = "点赞参数，1为点赞，0为取消点赞", required = true) @RequestParam(required = true) int isLike) {
		Integer storeId = (Integer) session.getAttribute("storeId");
		Integer memberId = (Integer) session.getAttribute("memberId");

		Map<String, Object> resultMap = null;
		if (Constant.YES_INT.equals(isLike)) {
			resultMap = commentService.addOrRemoveLike(storeId, memberId, commentId, true);
		} else {
			resultMap = commentService.addOrRemoveLike(storeId, memberId, commentId, false);
		}

		JsonResult<String> result = new JsonResult<>();
		result.setStatus((String) resultMap.get("resultCode"));
		result.setMsg((String) resultMap.get("resultDesc"));

		return result;
	}
}
