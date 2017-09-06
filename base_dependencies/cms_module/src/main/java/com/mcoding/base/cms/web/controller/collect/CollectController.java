package com.mcoding.base.cms.web.controller.collect;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mcoding.base.cms.bean.collect.Collect;
import com.mcoding.base.cms.bean.collect.CollectExample;
import com.mcoding.base.cms.service.collect.CollectService;
import com.mcoding.base.core.JsonResult;
import com.mcoding.base.member.bean.member.Member;
import com.mcoding.base.ui.utils.StoreUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * CollectController
 * 
 * @author acer
 * 
 */
@Api("收藏")
@Controller
@RequestMapping("collect")
public class CollectController {

	@Autowired
	private CollectService collectService;

	/**
	 * 收藏或者取消收藏
	 * 
	 * @param articleId
	 * @param status
	 * @param request
	 * @return
	 */
	@ApiOperation(value = "点赞或者取消点赞", httpMethod = "GET", notes = "status(0:收藏,1:取消收藏)")
	@RequestMapping("front/collectOrUncollect")
	@ResponseBody
	public JsonResult<String> collectOrUncollect(int articleId, int status,HttpSession session) {
		Member member = (Member) session.getAttribute("member");
		return collectService.collectOrUncollect(member.getId(), articleId, status,StoreUtils.getStoreIdFromThreadLocal());
	}

	/**
	 * 根据条件查询收藏
	 * 
	 * @param articleId
	 * @param request
	 * @return
	 */
	@ApiOperation(value = "根据条件查询收藏", httpMethod = "GET")
	@RequestMapping("front/findCollect")
	@ResponseBody
	public JsonResult<Collect> find(int articleId, HttpSession session) {
		Member member = (Member) session.getAttribute("member");
		Collect collect = findCollect(articleId,StoreUtils.getStoreIdFromThreadLocal(), member.getId());
		JsonResult<Collect> result = new JsonResult<Collect>();
		result.setData(collect);
		result.setMsg("ok");
		result.setStatus("00");
		return result;
	}

	/**
	 * 根据条件查询收藏
	 * 
	 * @param articleId
	 * @param storeId
	 * @param memberId
	 * @return
	 */
	public Collect findCollect(int articleId, int storeId, int memberId) {
		CollectExample example = new CollectExample();
		example.createCriteria().andArticleIdEqualTo(articleId)
				.andStoreIdEqualTo(storeId).andMemberIdEqualTo(memberId);
		List<Collect> list = collectService.queryAllObjByExample(example);
		if (CollectionUtils.isNotEmpty(list)) {
			return list.get(0);
		}
		return null;
	}
}