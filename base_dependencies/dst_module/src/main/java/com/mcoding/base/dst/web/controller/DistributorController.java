package com.mcoding.base.dst.web.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mcoding.base.core.CommonException;
import com.mcoding.base.core.JsonResult;
import com.mcoding.base.dst.bean.level.DstLevel;
import com.mcoding.base.dst.bean.level.DstMemberLevel;
import com.mcoding.base.dst.bean.promotion.DstPromotProductPoster;
import com.mcoding.base.dst.service.level.DstLevelService;
import com.mcoding.base.dst.service.level.DstMemberLevelService;
import com.mcoding.base.dst.service.promotion.DistributorQrcodePromoter;
import com.mcoding.base.dst.service.promotion.DstPromotProductPosterService;
import com.mcoding.base.dst.service.promotion.ProductPromoter;
import com.mcoding.base.dst.utils.DstUtils;
import com.mcoding.base.member.bean.member.Member;
import com.mcoding.base.member.service.member.MemberService;
import com.mcoding.base.member.service.wechat.StoreWxRefService;
import com.mcoding.base.ui.utils.StoreUtils;
import com.mcoding.base.ui.web.controller.attachment.AttachmentController;
import com.mcoding.comp.wechat.account.bean.AccountConfig;
import com.mcoding.comp.wechat.qrcode.bean.WxQrcode;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.chanjar.weixin.common.exception.WxErrorException;

@Api("经销商管理")
@Controller
@RequestMapping("distributor")
public class DistributorController {

	@Resource
	protected DstMemberLevelService dstMemberLevelService;

	@Resource
	protected MemberService memberService;

	@Resource
	protected DstLevelService dstLevelService;

	@Resource
	protected DstPromotProductPosterService dstPromotProductPosterService;

	@Resource
	protected DistributorQrcodePromoter qrcodePromoter;

	@Resource
	protected ProductPromoter productPromoter;

	@Resource
	protected StoreWxRefService storeWxRefService;
	
	@Resource
	protected AttachmentController attachmentController;

	@ApiOperation(httpMethod = "GET", value = "根据级别，申请经销商")
	@RequestMapping("service/applyByLevel")
	@ResponseBody
	public JsonResult<String> applyDstributorByLevel(int memberId, int levelId) {
		Member member = this.memberService.queryObjById(memberId);
		if (member == null) {
			throw new CommonException("会员不存在");
		}
		DstLevel dstLevel = this.dstLevelService.queryObjById(levelId);
		if (dstLevel == null) {
			throw new CommonException("经销商级别不存在");
		}
		this.dstMemberLevelService.applyForDistributor(member, dstLevel);

		JsonResult<String> result = new JsonResult<>();
		result.setData("ok");
		result.setMsg("ok");
		result.setStatus("00");
		return result;
	}

	@ApiOperation(httpMethod = "GET", value = "根据上级，申请经销商")
	@RequestMapping("front/applyByParent")
	@ResponseBody
	public JsonResult<String> applyDstributorByParent(HttpSession session) {
		Member member = (Member) session.getAttribute("member");
		if (member == null) {
			throw new CommonException("会员不存在");
		}
		Member parent = this.dstMemberLevelService.queryParentDistributor(member.getId());
		if (parent == null) {
			throw new CommonException("该会员还没有确定上级的经销商");
		}
		this.dstMemberLevelService.applyForDistributor(member, parent);
		
		member = this.memberService.queryObjById(member.getId());
		session.setAttribute("member", member);
		JsonResult<String> result = new JsonResult<>();
		result.setData("ok");
		result.setMsg("ok");
		result.setStatus("00");
		return result;
	}

	@ApiOperation(httpMethod="GET", value="获取经销商个人推广二维码")
	@RequestMapping("front/getPromoteQrcode")
	@ResponseBody
	public JsonResult<String> getPromotQrcode(HttpSession session) throws WxErrorException, IOException {
		Member member = (Member) session.getAttribute("member");

		DstMemberLevel memberLevel = (DstMemberLevel) member.getMemberExtInfoMap().get("distributor");
		if (memberLevel == null || memberLevel.getLevelId() == null) {
			throw new CommonException("该会员还没有经销商的级别资料");
		}

		AccountConfig account = this.storeWxRefService.queryWxAccountByStoreId(StoreUtils.getStoreIdFromThreadLocal());
		WxQrcode wxQrcode = this.qrcodePromoter.createQrcode(member, account);

		JsonResult<String> result = new JsonResult<>();
		result.setData(wxQrcode.getImgUrl());
		result.setMsg("ok");
		result.setStatus("00");
		return result;
	}

	@ApiOperation(httpMethod="GET", value="获取经销商个人推广二维码")
	@RequestMapping("front/getProductPromoteQrcode")
	@ResponseBody
	public JsonResult<String> getProductPromoteQrcode(int postId, HttpSession session) {
		Member member = (Member) session.getAttribute("member");

		DstMemberLevel memberLevel = (DstMemberLevel) member.getMemberExtInfoMap().get("distributor");
		if (memberLevel == null || memberLevel.getLevelId() == null) {
			throw new CommonException("该会员还没有经销商的级别资料");
		}

		DstPromotProductPoster poster = this.dstPromotProductPosterService.queryObjById(postId);
		if (poster == null) {
			throw new CommonException("不存在该产品的海报");
		}

		JsonResult<String> result = new JsonResult<>();
		result.setData("distributor/front/promoteQrcode_" + member.getId() + "_" + poster.getId() + ".jpg");
		result.setMsg("ok");
		result.setStatus("00");
		return result;
	}

	@RequestMapping("front/promoteQrcode_{memberId}_{posterId}.jpg")
	public void getPromoteQrcode(HttpServletResponse response, HttpServletRequest request,
			@PathVariable(value = "memberId") int memberId, @PathVariable(value = "posterId") int posterId){

		try {
			if (!this.attachmentController.isExpired(request, response)) {
				response.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
				return;
			}

			Member distributor = this.memberService.queryObjById(memberId);
			DstPromotProductPoster poster = this.dstPromotProductPosterService.queryObjById(posterId);
			if (distributor ==null || poster == null) {
				response.setStatus(HttpServletResponse.SC_NOT_FOUND); // 返回404状态码
				return;
			}
			// 设置下载文件名, 设置expire
			this.attachmentController.setRespHeaderCache(response);
			
			this.productPromoter.createQrcode(distributor, poster, response.getOutputStream());
			
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(HttpServletResponse.SC_EXPECTATION_FAILED);
		}
	}
	
	@RequestMapping("front/toProductPromotion/{productId}/{distributorId}")
	public ModelAndView toProductPromotionl(HttpSession session, 
			@PathVariable(value="distributorId") int distributorId, 
			@PathVariable(value="productId") int productId){
		Member member = (Member) session.getAttribute("member");
		
		ModelAndView view = new ModelAndView();
		view.setViewName("redirect:" + DstUtils.createProductInfoUrl(productId));
		
		if (member.getId().equals(distributorId)) {
			return view;
		}
		
		DstMemberLevel level = this.dstMemberLevelService.queryByMemberId(member.getId());
		if (level != null && level.getParentMemberId() != null) {
			return view;
		}
		
		Member parentMember = this.memberService.queryObjById(distributorId);
		this.dstMemberLevelService.recruitNewMember(member, parentMember);
		
		return view;
	}
}
