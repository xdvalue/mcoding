package com.mcoding.base.member.web.controller.test;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mcoding.base.core.JsonResult;
import com.mcoding.base.member.bean.member.Member;
import com.mcoding.base.member.service.member.MemberService;
import com.mcoding.base.ui.bean.store.Store;
import com.mcoding.base.ui.service.store.StoreService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.chanjar.weixin.mp.bean.result.WxMpUser;

@Api("测试用接口")
@Controller
@RequestMapping("test")
public class Test {
	
	@Resource
	protected MemberService memberService;
	
	@Resource
	protected StoreService storeService;

//	@MemberPointable(using=CommonMemberPointRuleHandler.class,targetMemberIndex=0,pointSourceIndex=1)
	@ApiOperation(value="设置用户账号", httpMethod="GET")
	@RequestMapping("setMemberInSession")
	@ResponseBody
	public JsonResult<String> setMemberInSession(int memberId, HttpSession session){
		Member me = this.memberService.queryObjById(memberId);// 测试数据id
		
//		System.out.println("----------------"+me.getCreateTime()+"---------------------------------");
		session.setAttribute("member", me);
		session.setAttribute("memberId", me.getId());
		if(me.getWxMember() != null){
			session.setAttribute("openid", me.getWxMember().getWxOpenid());
		}else{
			session.setAttribute("openid", "test_open_id");
		}
		session.setAttribute("wxMpUser", new WxMpUser());
		
		session.setAttribute("isGetWxUserInfo", "true");
		
		JsonResult<String> result = new JsonResult<>();
		result.setSize(0);
		result.setMsg("ok");
		result.setStatus("00");
		return result;
	}
	
	@ApiOperation(value="设置storeId", httpMethod="GET")
	@RequestMapping("setStoreInSession")
	@ResponseBody
	public JsonResult<String> setStoreInSession(int storeId, HttpSession session){
		Store store = this.storeService.queryObjById(storeId);
		session.setAttribute("store", store);
		session.setAttribute("storeId", storeId);
		
		
		JsonResult<String> result = new JsonResult<>();
		result.setSize(0);
		result.setMsg("ok");
		result.setStatus("00");
		return result;
	}
}
