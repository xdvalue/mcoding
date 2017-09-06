package com.mcoding.base.cms.web.controller.favorite;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mcoding.base.cms.bean.favorite.Favorite;
import com.mcoding.base.cms.bean.favorite.FavoriteExample;
import com.mcoding.base.cms.service.favorite.FavoriteService;
import com.mcoding.base.core.JsonResult;
import com.mcoding.base.member.bean.member.Member;
import com.mcoding.base.ui.utils.StoreUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * FavoriteController
 * 
 * @author acer
 * 
 */
@Api("点赞点衰记录")
@Controller
@RequestMapping("favorite")
public class FavoriteController {

	@Autowired
	protected FavoriteService favoriteService;

	/**
	 * 点赞、取消赞、点衰、取消衰
	 * 
	 * @param type
	 * @param status
	 * @param memberId
	 * @param articleId
	 * @param storeId
	 * @return
	 */
	@ApiOperation(value = "点赞、取消赞、点衰、取消衰", httpMethod = "POST", notes = "点赞(type=0,status=0),取消点赞(type=0,status=1),点衰(type=1,status=0),取消点衰(type=1,status=1)")
	@RequestMapping("front/likeOrDislike")
	@ResponseBody
	public JsonResult<String> likeOrDislike(int type, int status,
			int articleId, HttpServletRequest request) {
		Member member = (Member) request.getSession().getAttribute("member");
		return favoriteService.likeOrDislike(type, status, member.getId(), articleId, StoreUtils.getStoreIdFromThreadLocal());
	}

	/**
	 * 根据条件查询favorite
	 * 
	 * @param memberId
	 * @param articleId
	 * @param type
	 *            (0:赞,1:衰)
	 * @return status(0:有效,1失效)
	 */
	@ApiOperation(value = "根据条件查询favorite", httpMethod = "GET", notes = "type(0:赞,1:衰),return status(0:有效,1:取消)")
	@RequestMapping("front/findByFavorite")
	@ResponseBody
	public JsonResult<Favorite> find(int articleId, int type,
			HttpServletRequest request) {
		Member member = (Member) request.getSession().getAttribute("member");
		Favorite favorite = findFavorite(member.getId(),
				articleId, type, StoreUtils.getStoreIdFromThreadLocal());
		JsonResult<Favorite> result = new JsonResult<Favorite>();
		result.setData(favorite);
		result.setMsg("ok");
		result.setStatus("00");
		return result;
	}

	/**
	 * 根据条件查询favorite
	 * 
	 * @param memberId
	 * @param articleId
	 * @param type
	 * @return
	 */
	public Favorite findFavorite(int memberId, int articleId, int type,
			int storeId) {
		FavoriteExample example = new FavoriteExample();
		example.createCriteria().andStoreIdEqualTo(storeId)
				.andMemberIdEqualTo(memberId).andArticleIdEqualTo(articleId)
				.andTypeEqualTo(type);
		List<Favorite> list = favoriteService.queryAllObjByExample(example);
		if (CollectionUtils.isNotEmpty(list)) {
			return list.get(0);
		}
		return null;

	}
}