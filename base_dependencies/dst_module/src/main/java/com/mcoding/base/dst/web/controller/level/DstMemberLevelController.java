package com.mcoding.base.dst.web.controller.level;

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
import com.mcoding.base.dst.bean.level.DstMemberLevel;
import com.mcoding.base.dst.bean.level.DstMemberLevelExample;
import com.mcoding.base.dst.service.level.DstMemberLevelService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import springfox.documentation.annotations.ApiIgnore;

@Api(value="会员-分销商关联表")
@Controller
@RequestMapping("dstMemberLevel")
public class DstMemberLevelController {
    @Resource
    protected DstMemberLevelService dstMemberLevelService;

    @ApiIgnore
    @RequestMapping("service/toAddView")
    public ModelAndView toAddView() {
        return new ModelAndView("dst/dstMemberLevel/toAddView");
    }

    @ApiIgnore
    @RequestMapping("service/toMainView")
    public ModelAndView toMainView() {
        return new ModelAndView("dst/dstMemberLevel/toMainView");
    }

    @ApiIgnore
    @RequestMapping("service/toUpdateViewById")
    public ModelAndView toUpdateViewById(int id) {
        ModelAndView view = new ModelAndView();
        DstMemberLevel dstMemberLevel = this.dstMemberLevelService.queryObjById(id);
        view.addObject("dstMemberLevel", dstMemberLevel);
        view.setViewName("dst/dstMemberLevel/toAddView");
        return view;
    }

    @ApiOperation(httpMethod="POST", value="创建会员-分销商关联表")
    @RequestMapping("service/create")
    @ResponseBody
    public JsonResult<String> create(@RequestBody DstMemberLevel dstMemberLevel) {
        JsonResult<String> result = new JsonResult<>();
        this.dstMemberLevelService.addObj(dstMemberLevel);
        result.setData(null);
        result.setMsg("ok");
        result.setStatus("00");
        return result;
    }

    @ApiOperation(httpMethod="POST", value="编辑会员-分销商关联表")
    @RequestMapping("service/edit")
    @ResponseBody
    public JsonResult<String> edit(@RequestBody DstMemberLevel dstMemberLevel) {
        if (dstMemberLevel.getId() == null || dstMemberLevel.getId() <=0) {
            throw new CommonException("id 为空，保存失败");
        }
        JsonResult<String> result = new JsonResult<>();
        this.dstMemberLevelService.modifyObj(dstMemberLevel);
        result.setData(null);
        result.setMsg("ok");
        result.setStatus("00");
        return result;
    }

    @ApiOperation(httpMethod="POST", value="删除会员-分销商关联表")
    @RequestMapping("service/deleteById")
    @ResponseBody
    public JsonResult<String> deleteById(int id) {
        JsonResult<String> result = new JsonResult<>();
        this.dstMemberLevelService.deleteObjById(id);
        result.setData(null);
        result.setMsg("ok");
        result.setStatus("00");
        return result;
    }

    @ApiOperation(httpMethod="GET", value="查询会员-分销商关联表")
    @RequestMapping("service/findByPage")
    @ResponseBody
    public PageView<DstMemberLevel> findByPage(@ApiParam(value="分页索引",defaultValue="0") @RequestParam(defaultValue="0") String iDisplayStart, @ApiParam(value="每页的数量",defaultValue="10") @RequestParam(defaultValue="10") String iDisplayLength, @ApiParam(value="查询条件") String sSearch) {
        PageView<DstMemberLevel> pageView = new PageView<>(iDisplayStart, iDisplayLength);
        DstMemberLevelExample example = new DstMemberLevelExample();
        example.setPageView(pageView);
        if (StringUtils.isNotBlank(sSearch)) {
            // TODO Auto-generated method stub
        }
        return this.dstMemberLevelService.queryObjByPage(example);
    }
    
}