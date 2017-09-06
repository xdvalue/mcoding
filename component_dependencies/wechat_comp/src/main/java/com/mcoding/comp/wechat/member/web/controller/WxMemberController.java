package com.mcoding.comp.wechat.member.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mcoding.base.core.Constant;
import com.mcoding.base.core.JsonResult;
import com.mcoding.comp.wechat.account.bean.AccountConfig;
import com.mcoding.comp.wechat.account.utils.WxAccountConfigUtils;
import com.mcoding.comp.wechat.account.utils.WxMpServiceUtils;
import com.mcoding.comp.wechat.member.bean.WxMember;
import com.mcoding.comp.wechat.member.service.WxMemberService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpUser;

@Api("微信会员资料")
@RequestMapping("wxMember")
@Controller
public class WxMemberController {
	
	@Autowired
	private WxMemberService wxMemberService;
	
	@ApiOperation(value="查询微信会员是否关注公众号")
	@RequestMapping("front/isSubscribe")
	@ResponseBody
	public JsonResult<Integer> isSubscribe(@ApiParam(value="微信会员id") int wxMemberId) throws WxErrorException{
		WxMember wxMember = wxMemberService.queryObjById(wxMemberId);
		
		AccountConfig accountConfig = WxAccountConfigUtils.getByOrginId(wxMember.getWxAccountOriginId());
		WxMpService wxMpService = WxMpServiceUtils.getWxMpServiceByAccount(accountConfig);
		
		WxMpUser wxUserInfo = wxMpService.getUserService().userInfo(wxMember.getWxOpenid(), null);
		Integer isSubscribe = Constant.NO_INT;
		if (wxUserInfo != null) {
			isSubscribe = wxUserInfo.getSubscribe()? Constant.YES_INT : Constant.NO_INT;
		}else{
			isSubscribe = Constant.NO_INT;
		}
		
		JsonResult<Integer> result = new JsonResult<>();
		result.setData(isSubscribe);
		result.setMsg("ok");
		result.setStatus("00");
		return result;
		
	}
	

}
