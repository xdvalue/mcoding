package com.mcoding.base.member.web.controller.setting;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mcoding.base.member.service.setting.MemberSettingValueService;

import io.swagger.annotations.Api;

@Api(value="会员配置项目的值")
@Controller
@RequestMapping("memberSettingValue")
public class MemberSettingValueController {
    @Resource
    protected MemberSettingValueService memberSettingValueService;
    
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