package com.mcoding.base.sns.web.controller.setting;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mcoding.base.core.JsonResult;
import com.mcoding.base.member.bean.setting.MemberSettingValue;
import com.mcoding.base.member.service.setting.MemberSettingValueService;
import com.mcoding.base.sns.utils.SnsConstant;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(value="会员配置项目的值")
@Controller
@RequestMapping("snsMemberSettingValue")
public class SnsMemberSettingController {
    @Resource
    protected MemberSettingValueService memberSettingValueService;
    
    @ApiOperation(httpMethod="POST", value="修改改会员配置")
    @RequestMapping("front/changeMemberSetting")
    @ResponseBody
    public JsonResult<String> changeMemberSetting(HttpSession session,
    		@RequestParam(required=true) @ApiParam(name="修改配置的编码") String code, 
    		@RequestParam(required=true) @ApiParam(name="修改配置的值")String value ){
    	Integer memberId = (Integer) session.getAttribute("memberId");
    	Integer storeId = (Integer) session.getAttribute("storeId");
    	
    	this.memberSettingValueService.changeOneMemberSettingValue(memberId, code, value, SnsConstant.CODE_MODULE_TYPE_SNS , storeId);
    	
    	JsonResult<String> result = new JsonResult<>();
    	result.setData(null);	
        result.setMsg("ok");
        result.setStatus("00");
        return result;
    }
    
    @ApiOperation(httpMethod="GET", value="查询会员配置")
    @RequestMapping("front/findCurrentMemberSetting")
    @ResponseBody
    public JsonResult<Map<String, String>> findCurrentMemberSetting(HttpSession session){
    	Integer memberId = (Integer) session.getAttribute("memberId");
    	Integer storeId = (Integer) session.getAttribute("storeId");
    	
    	List<MemberSettingValue> list = this.memberSettingValueService.queryObjByMemberId(memberId, storeId, SnsConstant.CODE_MODULE_TYPE_SNS);
    	
    	Map<String, String> map = new HashMap<>();
    	for(int i=0; CollectionUtils.isNotEmpty(list) && i<list.size(); i++){
    		map.put(list.get(i).getSettingKeyCode(), list.get(i).getSettingValue());
    	}
    	
    	JsonResult<Map<String, String>> result = new JsonResult<>();
    	result.setData(map);
        result.setMsg("ok");
        result.setStatus("00");
        return result;
    }

    /*@ApiIgnore
    @RequestMapping("service/toAddView")
    public ModelAndView toAddView() {
        return new ModelAndView("member/memberSettingValue/toAddView");
    }

    @ApiIgnore
    @RequestMapping("service/toMainView")
    public ModelAndView toMainView() {
        return new ModelAndView("member/memberSettingValue/toMainView");
    }

    @ApiIgnore
    @RequestMapping("service/toUpdateViewById")
    public ModelAndView toUpdateViewById(int id) {
        ModelAndView view = new ModelAndView();
        MemberSettingValue memberSettingValue = this.memberSettingValueService.queryObjById(id);
        view.addObject("memberSettingValue", memberSettingValue);
        view.setViewName("member/memberSettingValue/toAddView");
        return view;
    }

    @ApiOperation(httpMethod="POST", value="创建t_member_setting_value")
    @RequestMapping("service/create")
    @ResponseBody
    public JsonResult<String> create(@RequestBody MemberSettingValue memberSettingValue) {
        JsonResult<String> result = new JsonResult<>();
        this.memberSettingValueService.addObj(memberSettingValue);
        result.setData(null);
        result.setMsg("ok");
        result.setStatus("00");
        return result;
    }

    @ApiOperation(httpMethod="POST", value="编辑t_member_setting_value")
    @RequestMapping("service/edit")
    @ResponseBody
    public JsonResult<String> edit(@RequestBody MemberSettingValue memberSettingValue) {
        if (memberSettingValue.getId() == null || memberSettingValue.getId() <=0) {
            throw new CommonException("id 为空，保存失败");
        }
        JsonResult<String> result = new JsonResult<>();
        this.memberSettingValueService.modifyObj(memberSettingValue);
        result.setData(null);
        result.setMsg("ok");
        result.setStatus("00");
        return result;
    }

    @ApiOperation(httpMethod="POST", value="删除t_member_setting_value")
    @RequestMapping("service/deleteById")
    @ResponseBody
    public JsonResult<String> deleteById(int id) {
        JsonResult<String> result = new JsonResult<>();
        this.memberSettingValueService.deleteObjById(id);
        result.setData(null);
        result.setMsg("ok");
        result.setStatus("00");
        return result;
    }

    @ApiOperation(httpMethod="GET", value="查询t_member_setting_value")
    @RequestMapping("service/findByPage")
    @ResponseBody
    public PageView<MemberSettingValue> findByPage(@ApiParam(name="分页索引",defaultValue="0") @RequestParam(defaultValue="0") String iDisplayStart, @ApiParam(name="每页的数量",defaultValue="10") @RequestParam(defaultValue="10") String iDisplayLength, @ApiParam(value="查询条件") String sSearch) {
        PageView<MemberSettingValue> pageView = new PageView<>(iDisplayStart, iDisplayLength);
        MemberSettingValueExample example = new MemberSettingValueExample();
        example.setPageView(pageView);
        if (StringUtils.isNotBlank(sSearch)) {
            // TODO Auto-generated method stub
        }
        return this.memberSettingValueService.queryObjByPage(example);
    }*/
    
}