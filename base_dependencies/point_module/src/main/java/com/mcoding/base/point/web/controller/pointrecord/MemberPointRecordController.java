package com.mcoding.base.point.web.controller.pointrecord;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
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
import com.mcoding.base.point.bean.pointrecord.MemberPointRecord;
import com.mcoding.base.point.bean.pointrecord.MemberPointRecordExample;
import com.mcoding.base.point.service.pointrecord.MemberPointRecordService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import springfox.documentation.annotations.ApiIgnore;

@Api(value="会员积分记录")
@Controller
@RequestMapping("memberPointRecord")
public class MemberPointRecordController {
    @Resource
    protected MemberPointRecordService memberPointRecordService;

    /*@ApiIgnore
    @RequestMapping("service/toAddView")
    public ModelAndView toAddView() {
        return new ModelAndView("sns/memberPointRecord/toAddView");
    }*/

    @ApiIgnore
    @RequestMapping("service/toMainView")
    public ModelAndView toMainView() {
        return new ModelAndView("sns/memberPointRecord/toMainView");
    }

    /*@ApiIgnore
    @RequestMapping("service/toUpdateViewById")
    public ModelAndView toUpdateViewById(int id) {
        ModelAndView view = new ModelAndView();
        MemberPointRecord memberPointRecord = this.memberPointRecordService.queryObjById(id);
        view.addObject("memberPointRecord", memberPointRecord);
        view.setViewName("sns/memberPointRecord/toUpdateView");
        return view;
    }*/

/*    @ApiOperation(httpMethod="POST", value="创建会员积分记录")
    @RequestMapping("service/create")
    @ResponseBody
    public JsonResult<String> create(@RequestBody MemberPointRecord memberPointRecord) {
        JsonResult<String> result = new JsonResult<>();
        this.memberPointRecordService.addObj(memberPointRecord);
        result.setData(null);
        result.setMsg("ok");
        result.setStatus("00");
        return result;
    }

    @ApiOperation(httpMethod="POST", value="编辑会员积分记录")
    @RequestMapping("service/edit")
    @ResponseBody
    public JsonResult<String> edit(@RequestBody MemberPointRecord memberPointRecord) {
        if (memberPointRecord.getId() == null || memberPointRecord.getId() <=0) {
            throw new CommonException("id 为空，保存失败");
        }
        JsonResult<String> result = new JsonResult<>();
        this.memberPointRecordService.modifyObj(memberPointRecord);
        result.setData(null);
        result.setMsg("ok");
        result.setStatus("00");
        return result;
    }

    @ApiOperation(httpMethod="POST", value="删除会员积分记录")
    @RequestMapping("service/deleteById")
    @ResponseBody
    public JsonResult<String> deleteById(int id) {
        JsonResult<String> result = new JsonResult<>();
        this.memberPointRecordService.deleteObjById(id);
        result.setData(null);
        result.setMsg("ok");
        result.setStatus("00");
        return result;
    }*/

    @ApiOperation(httpMethod="GET", value="查询会员积分记录")
    @RequestMapping(value={"service/findByPage", "front/findByPage"})
    @ResponseBody
    public PageView<MemberPointRecord> findByPage(
    		@ApiParam(name="分页索引",defaultValue="0") @RequestParam(defaultValue="0") String iDisplayStart, 
    		@ApiParam(name="每页的数量",defaultValue="10") @RequestParam(defaultValue="10") String iDisplayLength, 
    		@ApiParam(value="查询条件") String moduleType) {
        PageView<MemberPointRecord> pageView = new PageView<>(iDisplayStart, iDisplayLength);
        MemberPointRecordExample example = new MemberPointRecordExample();
        example.setPageView(pageView);
        if (StringUtils.isNotBlank(moduleType) && StringUtils.isNumeric(moduleType)) {
        	example.createCriteria().andModuleTypeEqualTo(Integer.valueOf(moduleType));
        }
        return this.memberPointRecordService.queryObjByPage(example);
    }
    
    @ApiOperation(httpMethod="GET", value="查询当前登录会员的积分记录")
    @RequestMapping(value={"service/findCurrentMemberPointByPage", "front/findCurrentMemberPointByPage"})
    @ResponseBody
    public PageView<MemberPointRecord> findCurrentMemberPointByPage(
    		@ApiParam(name="分页索引",defaultValue="1") @RequestParam(defaultValue="1") int pageNo,
    		@ApiParam(name="每页的数量",defaultValue="10") @RequestParam(defaultValue="10") int pageSize,
    		@ApiParam(value="查询条件") String moduleType, HttpServletRequest request) {
    	Member member = (Member) request.getSession().getAttribute("member");
    	
        PageView<MemberPointRecord> pageView = new PageView<>(pageNo, pageSize);
        MemberPointRecordExample example = new MemberPointRecordExample();
        example.setPageView(pageView);
        
        MemberPointRecordExample.Criteria cri = example.createCriteria();
        cri.andMemberIdEqualTo(member.getId());
        if (StringUtils.isNotBlank(moduleType) && StringUtils.isNumeric(moduleType)) {
        	cri.andModuleTypeEqualTo(Integer.valueOf(moduleType));
        }
        example.setOrderByClause("create_time DESC");
        return this.memberPointRecordService.queryObjByPage(example);
    }
}