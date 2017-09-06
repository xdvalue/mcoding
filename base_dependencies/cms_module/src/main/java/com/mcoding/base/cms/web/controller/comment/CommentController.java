package com.mcoding.base.cms.web.controller.comment;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mcoding.base.cms.bean.comment.Comment;
import com.mcoding.base.cms.bean.comment.CommentData;
import com.mcoding.base.cms.service.comment.CommentService;
import com.mcoding.base.core.JsonResult;
import com.mcoding.base.member.bean.member.Member;
import com.mcoding.base.ui.utils.StoreUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * CommentController
 * 
 * @author acer
 * 
 */
@Api("评论管理")
@Controller
@RequestMapping("comment")
public class CommentController {

	@Autowired
	private CommentService commentService;

	@ApiOperation(value = "添加评论", httpMethod = "POST", notes = "Comment.operType(类型0：评论1：回复)")
	@RequestMapping("front/create")
	@ResponseBody
	public JsonResult<String> create(@RequestBody Comment comment,HttpServletRequest request) {
		JsonResult<String> result = new JsonResult<>();
		Member member = (Member) request.getSession().getAttribute("member");
		comment.setStoreId(StoreUtils.getStoreIdFromThreadLocal());
		comment.setOpenid(member.getWxMember().getWxOpenid());
		comment.setMemberId(member.getId());
		comment.setCreateTime(new Date());
		
		if(StringUtils.isNotBlank(member.getWxMember().getWxNickname())){
			comment.setNickname(member.getWxMember().getWxNickname());
		}else{
			comment.setNickname(member.getName());
		}
		if(StringUtils.isNotBlank(member.getHeadImgUrl())){
			comment.setHeadimgurl(member.getHeadImgUrl());
		}else{
			comment.setHeadimgurl(member.getWxMember().getWxHeadimgurl());
		}
		
		this.commentService.addObj(comment);
		result.setData(null);
		result.setMsg("ok");
		result.setStatus("00");
		return result;
	}

	@ApiOperation(value = "获取文章的评论", httpMethod = "POST")
	@RequestMapping("front/getCommentsByArticleId")
	@ResponseBody
	public List<CommentData> getCommentsByArticleId(Integer articleId,
			HttpServletRequest request) {
		
		Member member = (Member) request.getSession().getAttribute("member");
		return commentService.getCommentsByArticleId(articleId,StoreUtils.getStoreIdFromThreadLocal(), member.getId());
	}

}