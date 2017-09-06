package com.mcoding.base.member.web.controller.setting;

import javax.annotation.Resource;

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
import com.mcoding.base.member.bean.setting.MemberSettingKey;
import com.mcoding.base.member.bean.setting.MemberSettingKeyExample;
import com.mcoding.base.member.service.setting.MemberSettingKeyService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import springfox.documentation.annotations.ApiIgnore;

@Api(value="会员配置项目")
@Controller
@RequestMapping("memberSettingKey")
public class MemberSettingKeyController {
    @Resource
    protected MemberSettingKeyService memberSettingKeyService;

    @ApiIgnore
    @RequestMapping("service/toAddView")
    public ModelAndView toAddView() {
        return new ModelAndView("member/memberSettingKey/toAddView");
    }

    @ApiIgnore
    @RequestMapping("service/toMainView")
    public ModelAndView toMainView() {
        return new ModelAndView("member/memberSettingKey/toMainView");
    }

    @ApiIgnore
    @RequestMapping("service/toUpdateViewById")
    public ModelAndView toUpdateViewById(int id) {
        ModelAndView view = new ModelAndView();
        MemberSettingKey memberSettingKey = this.memberSettingKeyService.queryObjById(id);
        view.addObject("memberSettingKey", memberSettingKey);
        view.setViewName("member/memberSettingKey/toAddView");
        return view;
    }

    @ApiOperation(httpMethod="POST", value="创建会员配置")
    @RequestMapping("service/create")
    @ResponseBody
    public JsonResult<String> create(@RequestBody MemberSettingKey memberSettingKey) {
        JsonResult<String> result = new JsonResult<>();
        this.memberSettingKeyService.addObj(memberSettingKey);
        result.setData(null);
        result.setMsg("ok");
        result.setStatus("00");
        return result;
    }

    @ApiOperation(httpMethod="POST", value="编辑会员配置")
    @RequestMapping("service/edit")
    @ResponseBody
    public JsonResult<String> edit(@RequestBody MemberSettingKey memberSettingKey) {
        if (memberSettingKey.getId() == null || memberSettingKey.getId() <=0) {
            throw new CommonException("id 为空，保存失败");
        }
        JsonResult<String> result = new JsonResult<>();
        this.memberSettingKeyService.modifyObj(memberSettingKey);
        result.setData(null);
        result.setMsg("ok");
        result.setStatus("00");
        return result;
    }

    @ApiOperation(httpMethod="POST", value="删除会员配置")
    @RequestMapping("service/deleteById")
    @ResponseBody
    public JsonResult<String> deleteById(int id) {
        JsonResult<String> result = new JsonResult<>();
        this.memberSettingKeyService.deleteObjById(id);
        result.setData(null);
        result.setMsg("ok");
        result.setStatus("00");
        return result;
    }

    @ApiOperation(httpMethod="GET", value="查询会员配置")
    @RequestMapping("service/findByPage")
    @ResponseBody
    public PageView<MemberSettingKey> findByPage(@ApiParam(name="分页索引",defaultValue="0") @RequestParam(defaultValue="0") String iDisplayStart, @ApiParam(name="每页的数量",defaultValue="10") @RequestParam(defaultValue="10") String iDisplayLength, @ApiParam(value="查询条件") String sSearch) {
        PageView<MemberSettingKey> pageView = new PageView<>(iDisplayStart, iDisplayLength);
        MemberSettingKeyExample example = new MemberSettingKeyExample();
        example.setPageView(pageView);
        if (StringUtils.isNotBlank(sSearch)) {
            // TODO Auto-generated method stub
        }
        return this.memberSettingKeyService.queryObjByPage(example);
    }
}