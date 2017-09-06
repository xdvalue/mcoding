package com.mcoding.base.member.web.controller.member;

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
import com.mcoding.base.member.bean.member.MemberAddress;
import com.mcoding.base.member.bean.member.MemberAddressExample;
import com.mcoding.base.member.service.member.MemberAddressService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(value="地址")
@Controller
@RequestMapping("memberAddress")
public class MemberAddressController {
    @Resource
    protected MemberAddressService memberAddressService;

    /*@ApiIgnore
    @RequestMapping("service/toAddView")
    public ModelAndView toAddView() {
        return new ModelAndView("wechat/memberAddress/toAddView");
    }

    @ApiIgnore
    @RequestMapping("service/toMainView")
    public ModelAndView toMainView() {
        return new ModelAndView("wechat/memberAddress/toMainView");
    }

    @ApiIgnore
    @RequestMapping("service/toUpdateViewById")
    public ModelAndView toUpdateViewById(int id) {
        ModelAndView view = new ModelAndView();
        MemberAddress memberAddress = this.memberAddressService.queryObjById(id);
        view.addObject("memberAddress", memberAddress);
        view.setViewName("wechat/memberAddress/toAddView");
        return view;
    }*/

    @ApiOperation(httpMethod="POST", value="创建地址")
    @RequestMapping("front/create")
    @ResponseBody
    public JsonResult<String> create(@RequestBody MemberAddress memberAddress, HttpSession session) {
    	Member member = (Member) session.getAttribute("member");
    	memberAddress.setMemberId(member.getId());
    	
    	this.memberAddressService.addObj(memberAddress);
        
        JsonResult<String> result = new JsonResult<>();
        result.setData(null);
        result.setMsg("ok");
        result.setStatus("00");
        return result;
    }

    @ApiOperation(httpMethod="POST", value="编辑地址")
    @RequestMapping("front/edit")
    @ResponseBody
    public JsonResult<String> edit(@RequestBody MemberAddress memberAddress) {
        if (memberAddress.getId() == null || memberAddress.getId() <=0) {
            throw new CommonException("id 为空，保存失败");
        }
        
        memberAddress.setMemberId(null);
        this.memberAddressService.modifyObj(memberAddress);
        
        JsonResult<String> result = new JsonResult<>();
        result.setData(null);
        result.setMsg("ok");
        result.setStatus("00");
        return result;
    }

    @ApiOperation(httpMethod="GET", value="删除地址")
    @RequestMapping(value={"service/deleteById","front/deleteById"})
    @ResponseBody
    public JsonResult<String> deleteById(int id) {
        JsonResult<String> result = new JsonResult<>();
        this.memberAddressService.deleteObjById(id);
        result.setData(null);
        result.setMsg("ok");
        result.setStatus("00");
        return result;
    }

    @ApiOperation(httpMethod="GET", value="查询地址")
    @RequestMapping("service/findByPage")
    @ResponseBody
    public PageView<MemberAddress> findByPage(@ApiParam(name="分页索引",defaultValue="0") @RequestParam(defaultValue="0") String iDisplayStart, @ApiParam(name="每页的数量",defaultValue="10") @RequestParam(defaultValue="10") String iDisplayLength, @ApiParam(value="查询条件") String sSearch) {
        PageView<MemberAddress> pageView = new PageView<>(iDisplayStart, iDisplayLength);
        MemberAddressExample example = new MemberAddressExample();
        example.setPageView(pageView);
        if (StringUtils.isNotBlank(sSearch)) {
            // TODO Auto-generated method stub
        }
        return this.memberAddressService.queryObjByPage(example);
    }
    
    @ApiOperation(httpMethod="GET", value="查询当前用户的地址")
    @RequestMapping("front/findByCurrentMember")
    @ResponseBody
    public PageView<MemberAddress> findByCurrentMember(
    		@ApiParam(name="分页索引",defaultValue="0") @RequestParam(defaultValue="0") String iDisplayStart, 
    		@ApiParam(name="每页的数量",defaultValue="10") @RequestParam(defaultValue="10") String iDisplayLength,
    		HttpSession session) {
    	Member member = (Member) session.getAttribute("member");
    	
    	PageView<MemberAddress> pageView = new PageView<>(iDisplayStart, iDisplayLength);
    	MemberAddressExample example = new MemberAddressExample();
    	example.setPageView(pageView);
    	
    	example.createCriteria().andMemberIdEqualTo(member.getId());
    	example.setOrderByClause("is_default DESC, id DESC");
    	
    	return this.memberAddressService.queryObjByPage(example);
    }
    
    @ApiOperation(httpMethod="GET", value="查询地址详情")
    @RequestMapping("front/findById")
    @ResponseBody
    public JsonResult<MemberAddress> findById(int id) {
		JsonResult<MemberAddress> result = new JsonResult<>();
		result.setStatus("00");
		result.setMsg("ok");
		result.setData(this.memberAddressService.queryObjById(id));
		return result;
	}
}