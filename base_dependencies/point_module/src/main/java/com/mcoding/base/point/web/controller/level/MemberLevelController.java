package com.mcoding.base.point.web.controller.level;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mcoding.base.core.CommonException;
import com.mcoding.base.core.JsonResult;
import com.mcoding.base.point.bean.level.MemberLevel;
import com.mcoding.base.point.service.level.MemberLevelService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

@Api(value="会员级别")
@Controller
@RequestMapping("memberLevel")
public class MemberLevelController {
    @Resource
    protected MemberLevelService memberLevelService;

    @ApiIgnore
    @RequestMapping("service/toAddView")
    public ModelAndView toAddView() {
        return new ModelAndView("sns/memberLevel/toAddView");
    }

    @ApiIgnore
    @RequestMapping("service/toMainView")
    public ModelAndView toMainView() {
        return new ModelAndView("sns/memberLevel/toMainView");
    }

    /*@ApiIgnore
    @RequestMapping("service/toUpdateViewById")
    public ModelAndView toUpdateViewById(int id) {
        ModelAndView view = new ModelAndView();
        MemberLevel memberLevel = this.memberLevelService.queryObjById(id);
        view.addObject("memberLevel", memberLevel);
        view.setViewName("sns/memberLevel/toUpdateView");
        return view;
    }

    @ApiOperation(httpMethod="POST", value="创建会员级别")
    @RequestMapping("service/create")
    @ResponseBody
    public JsonResult<String> create(@RequestBody MemberLevel memberLevel) {
        JsonResult<String> result = new JsonResult<>();
        this.memberLevelService.addObj(memberLevel);
        result.setData(null);
        result.setMsg("ok");
        result.setStatus("00");
        return result;
    }*/

    @ApiOperation(httpMethod="POST", value="编辑会员级别")
    @RequestMapping("service/edit")
    @ResponseBody
    public JsonResult<String> edit(@RequestBody MemberLevel memberLevel) {
        if (memberLevel.getId() == null || memberLevel.getId() <=0) {
            throw new CommonException("id 为空，保存失败");
        }
        JsonResult<String> result = new JsonResult<>();
        this.memberLevelService.modifyObj(memberLevel);
        result.setData(null);
        result.setMsg("ok");
        result.setStatus("00");
        return result;
    }

    @ApiOperation(httpMethod="POST", value="删除会员级别")
    @RequestMapping("service/deleteById")
    @ResponseBody
    public JsonResult<String> deleteById(int id) {
        JsonResult<String> result = new JsonResult<>();
        this.memberLevelService.deleteObjById(id);
        result.setData(null);
        result.setMsg("ok");
        result.setStatus("00");
        return result;
    }

    /*@ApiOperation(httpMethod="GET", value="查询会员级别")
    @RequestMapping("service/findByPage")
    @ResponseBody
    public PageView<MemberLevel> findByPage(@ApiParam(name="分页索引",defaultValue="0") @RequestParam(defaultValue="0") String iDisplayStart, @ApiParam(name="每页的数量",defaultValue="10") @RequestParam(defaultValue="10") String iDisplayLength, @ApiParam(value="查询条件") String sSearch) {
        PageView<MemberLevel> pageView = new PageView<>(iDisplayStart, iDisplayLength);
        MemberLevelExample example = new MemberLevelExample();
        example.setPageView(pageView);
        if (StringUtils.isNotBlank(sSearch)) {
            // TODO Auto-generated method stub
        }
        return this.memberLevelService.queryObjByPage(example);
    }*/
}