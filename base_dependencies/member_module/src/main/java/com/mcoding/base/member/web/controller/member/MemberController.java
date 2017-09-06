package com.mcoding.base.member.web.controller.member;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.RandomUtils;
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
import com.mcoding.base.member.bean.member.MemberExample;
import com.mcoding.base.member.bean.member.MemberExample.Criteria;
import com.mcoding.base.member.service.member.MemberExtInfoService;
import com.mcoding.base.member.service.member.MemberService;
import com.mcoding.base.member.utils.MemberConstant;
import com.mcoding.base.ui.utils.common.Constant;
import com.mcoding.comp.sms.SmsManager;
import com.mcoding.comp.sms.SmsResponse;
import com.mcoding.comp.sms.utils.SmsConstanst;
import com.mcoding.comp.sms.utils.Supplier;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import springfox.documentation.annotations.ApiIgnore;

@Api("会员管理")
@Controller
@RequestMapping("member")
public class MemberController {

	@Resource
	protected MemberService memberService;
	
	@Resource
	protected MemberExtInfoService memberExtInfoService;
	
	/**
	 * 修改当前用户的资料
	 * @param member
	 * @return
	 * @throws Exception 
	 */
	@ApiOperation(value="请求获取手机验证码", httpMethod="GET")
	@RequestMapping("front/sendVerifyCode")
	@ResponseBody
	public JsonResult<String> sendVerifyCode(String phoneNum, HttpSession session) throws Exception{
		if (StringUtils.isBlank(phoneNum) || !phoneNum.matches(MemberConstant.PHONE_NUM_REGEX)) {
			throw new CommonException("手机的格式不正确，请重新输入.");
		}
		
		Date verifyTime = (Date) session.getAttribute("verifyTimeForMember");
		Date now = new Date();
		
		if(verifyTime != null && (now.getTime() - verifyTime.getTime()) < 5000){
			throw new CommonException("验证码请求太频繁了，请稍候再请求");
		}
		session.setAttribute("verifyTimeForMember", now);
		
		String verifyCode = String.valueOf(RandomUtils.nextInt(8999) + 1000) ;
		String content = "正在修改个人资料，验证码["+verifyCode+"]";
		
		SmsResponse smsResponse = SmsManager.getInstance(Supplier.BAY_YUE).sendSingleMsgWithoutReport(phoneNum, content);
//		SmsResponse smsResponse = SmsManager.getInstance(Supplier.BAI_XI).sendSingleMsgWithoutReport(phoneNum, content);
//		SmsResponse smsResponse = SmsManager.getInstance(Supplier.MAI_CHE).sendSingleMsgWithoutReport(phoneNum, content);
		if (!SmsConstanst.SUCCESS_RESPONSE_CODE.equals(smsResponse.getRespstatus())) {
			throw new CommonException("获取验证码失败，因为:" +smsResponse.getRespMsg());
		}
		session.setAttribute("verifyCodeForMember", verifyCode);
		session.setAttribute("newPhoneNum", phoneNum);
		
		JsonResult<String> result = new JsonResult<>();
		result.setData(null);
		result.setMsg("ok");
		result.setStatus("00");
		return result;
	}
	
	/**
	 * 修改当前用户的资料
	 * @param member
	 * @return
	 */
	@ApiOperation(value="完善当前的会员信息", httpMethod="GET")
	@RequestMapping("front/editCurrentMember")
	@ResponseBody
	public JsonResult<String> editCurrentMember(@RequestBody Member member, String verifyCode, HttpSession session){
		Member memberInSession = (Member) session.getAttribute("member");
		if (memberInSession == null || (memberInSession.getId() == null || memberInSession.getId() == 0)) {
			throw new CommonException("获取用户信息失败，请重新进入公众号登陆。");
		}
		
//		Member origin = (Member) session.getAttribute("member");
		
		if(StringUtils.isBlank(memberInSession.getMobilephone())
				|| (StringUtils.isNotBlank(member.getMobilephone()) && !memberInSession.getMobilephone().equals(member.getMobilephone()))){
			String newPhone = (String) session.getAttribute("newPhoneNum");
			if (!newPhone.equals(member.getMobilephone()) ) {
				throw new CommonException("提交的电话号码，与验证的号码不一致，请重新输入");
			}
			this.checkVerifyCode(session, verifyCode);
		}
		
		
		Integer memberId = memberInSession.getId();
		member.setId(memberId);
		
		JsonResult<String> result = new JsonResult<>();
		this.memberService.modifyObj(member);
		member = this.memberService.queryObjById(memberId);
		session.setAttribute("member", member);
		result.setData(null);
		result.setMsg("ok");
		result.setStatus("00");
		return result;
	}
	
	private void checkVerifyCode(HttpSession session, String verifyCode){
		String verifyCodeForMember = (String) session.getAttribute("verifyCodeForMember");
		Date verifyTime = (Date) session.getAttribute("verifyTimeForMember");
		
		if (StringUtils.isBlank(verifyCodeForMember) || StringUtils.isBlank(verifyCode) || !verifyCodeForMember.equals(verifyCode)) {
			throw new CommonException("验证码不正确，请重试");
		}
		
		if(verifyTime != null ){
			Date now = new Date();
			long expiredTime = verifyTime.getTime() + 120 * 1000;
			if(now.getTime() > expiredTime){
				throw new CommonException("时间已超过2分钟，验证码已过期,请重新请求验证码");
			}
		}
	}
	
	/**
	 * 获取当前会员信息
	 */
	@ApiOperation(value="获取当前的会员信息", httpMethod="GET")
	@RequestMapping("front/findCurrentMember")
	@ResponseBody
	public JsonResult<Member> findCurrentMember(HttpServletRequest request) {
		JsonResult<Member> result = new JsonResult<>();

		// 获取session里面保存的对象
		Member memberInSession = (Member) request.getSession().getAttribute("member");
		if (memberInSession == null || (memberInSession.getId() == null || memberInSession.getId() == 0)) {
			throw new CommonException("获取用户信息失败，请重新进入公众号登陆。");
		}
		Integer id = memberInSession.getId();

		Member member = this.memberService.queryObjById(id);
		result.setData(member);
		result.setMsg("ok");
		result.setStatus("00");

		return result;
	}
	
	@ApiOperation(httpMethod="GET", value="根据openid登录")
	@RequestMapping("front/loginByOpenid")
	@ResponseBody
	public JsonResult<Member> loginByOpenId(String openid, HttpSession session){
		Member member = this.memberService.queryByOpenId(openid);
		if (member == null) {
			throw new CommonException("获取用户信息失败，请跳转授权url。");
		}
		
		session.setAttribute("member", member);
		
		JsonResult<Member> result = new JsonResult<>();
		result.setData(member);
		result.setMsg("ok");
		result.setStatus("00");

		return result;
	}

	/**
	 * 查询会员列表信息
	 * @param typeMember 会员分类
	 * @param industry 行业
	 * @param interest 兴趣
	 * @param province 
	 * @param city
	 * @param district
	 * @param pageNo
	 * @param pageSize
	 * @param sSearch
	 * @return
	 */
	@ApiOperation(value="分页查看会员列表", httpMethod="GET")
	@RequestMapping("service/findByPage")
	@ResponseBody
	public PageView<Member> findByPage(
			@RequestParam(value = "type", required=false) List<Integer> typeMember,
			@RequestParam(value = "industry", required=false) List<Integer> industry, 
			@RequestParam(value = "interest", required=false) List<Integer> interest,
			@RequestParam(defaultValue="0") String iDisplayStart, 
			@RequestParam(defaultValue="10") String iDisplayLength,
			String province, String city, String district, String sSearch) {
		MemberExample exampleOne = new MemberExample();
		PageView<Member> pageView = new PageView<>(iDisplayStart, iDisplayLength);
		exampleOne.setPageView(pageView);
		
		if (StringUtils.isNotBlank(sSearch)) {
			Criteria cri1 = exampleOne.createCriteria();
			cri1.andNameLike(sSearch + "%");
			Criteria cri2 = exampleOne.or();
			cri2.andTrueNameLike(sSearch +"%");
			if (StringUtils.isNumeric(sSearch)) {
				exampleOne.or().andMobilephoneLike(sSearch + "%");
			}
		}
		
		exampleOne.setOrderByClause("create_time DESC");
		return this.memberService.queryObjByPage(exampleOne);
		
	}

	/**
	 * 添加会员
	 * 
	 * @param member
	 * @return
	 */
	@ApiOperation(value="添加会员", httpMethod="POST")
	@RequestMapping("service/create")
	@ResponseBody
	public JsonResult<String> create(@ApiParam(value="会员信息") @RequestBody Member member) {
		JsonResult<String> result = new JsonResult<>();
		member.setIsEnable(1);
		this.memberService.addObj(member);
		result.setData(null);
		result.setMsg("ok");
		result.setStatus("00");
		return result;
	}

	/**
	 * 修改会员
	 * 
	 * @param member
	 * @return
	 */
	@ApiOperation(value="修改会员", httpMethod="POST", notes="注意必须带有会员的ID")
	@RequestMapping(value={"service/edit"})
	@ResponseBody
	public JsonResult<String> edit(@RequestBody Member member) {
		JsonResult<String> result = new JsonResult<>();
		this.memberService.modifyObj(member);
		result.setData(null);
		result.setMsg("ok");
		result.setStatus("00");
		return result;
	}

	/**
	 * 查看会员详情
	 * 
	 * @param memberId
	 * @return
	 */
	@ApiOperation(value="查看会员详情", httpMethod="GET")
	@RequestMapping("service/findByMemberId")
	@ResponseBody
	public JsonResult<Member> findByMemberId(int memberId) {
		JsonResult<Member> result = new JsonResult<>();
		Member member = this.memberService.queryObjById(memberId);
		result.setData(member);
		result.setMsg("ok");
		result.setStatus("00");
		return result;
	}
	
	@ApiOperation(value="禁用会员", httpMethod="GET")
	@RequestMapping("service/setIsEnableById")
	@ResponseBody
	public JsonResult<String> disableMember(int id, int isEnable) {
		JsonResult<String> result = new JsonResult<String>();
		Member member = this.memberService.queryObjById(id);
		if (member == null) {
			throw new CommonException("找不到该会员信息，请刷新重试");
		}
		Member m = new Member();
		if (Constant.YES_INT.equals(isEnable)) {
			m.setIsEnable(Constant.YES_INT);
		}else{
			m.setIsEnable(Constant.NO_INT);
		}
		m.setId(member.getId());
		this.memberService.modifyObj(m);
		result.setMsg("ok");
		result.setStatus("00");
		return result;
	}

	
	// 设置访问页面
	@ApiIgnore
	@RequestMapping("/service/toMainView")
	@ResponseBody
	public ModelAndView toNewMemberListForView() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("member/member/toMainView");
		return mav;
	}

	// 查看会员详情页面
	@ApiIgnore
	@RequestMapping("/service/toUpdateViewById")
	@ResponseBody
	public ModelAndView toUpdateViewById(int memberId) {
		ModelAndView mav = new ModelAndView();
		Member member = this.memberService.queryObjById(memberId);
		mav.addObject("member", member);
		mav.setViewName("member/member/toAddView");
		return mav;
	}

	// 添加会员页面
	@ApiOperation(value="添加会员页面", httpMethod="GET")
	@RequestMapping("/service/toAddView")
	@ResponseBody
	public ModelAndView toAddView() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("member/member/toAddView");
		return mav;
	}

}
