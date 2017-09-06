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
import com.mcoding.base.dst.bean.level.DstLevel;
import com.mcoding.base.dst.bean.level.DstLevelExample;
import com.mcoding.base.dst.service.level.DstLevelService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import springfox.documentation.annotations.ApiIgnore;

@Api(value="分销商级别")
@Controller
@RequestMapping("dstLevel")
public class DstLevelController {
    @Resource
    protected DstLevelService dstLevelService;

    @ApiIgnore
    @RequestMapping("service/toAddView")
    public ModelAndView toAddView() {
        return new ModelAndView("dst/dstLevel/toAddView");
    }

    @ApiIgnore
    @RequestMapping("service/toMainView")
    public ModelAndView toMainView() {
        return new ModelAndView("dst/dstLevel/toMainView");
    }

    @ApiIgnore
    @RequestMapping("service/toUpdateViewById")
    public ModelAndView toUpdateViewById(int id) {
        ModelAndView view = new ModelAndView();
        DstLevel dstLevel = this.dstLevelService.queryObjById(id);
        view.addObject("dstLevel", dstLevel);
        view.setViewName("dst/dstLevel/toAddView");
        return view;
    }

    @ApiOperation(httpMethod="POST", value="创建分销商级别")
    @RequestMapping("service/create")
    @ResponseBody
    public JsonResult<String> create(@RequestBody DstLevel dstLevel) {
        JsonResult<String> result = new JsonResult<>();
        this.dstLevelService.addObj(dstLevel);
        result.setData(null);
        result.setMsg("ok");
        result.setStatus("00");
        return result;
    }

    @ApiOperation(httpMethod="POST", value="编辑分销商级别")
    @RequestMapping("service/edit")
    @ResponseBody
    public JsonResult<String> edit(@RequestBody DstLevel dstLevel) {
        if (dstLevel.getId() == null || dstLevel.getId() <=0) {
            throw new CommonException("id 为空，保存失败");
        }
        JsonResult<String> result = new JsonResult<>();
        this.dstLevelService.modifyObj(dstLevel);
        result.setData(null);
        result.setMsg("ok");
        result.setStatus("00");
        return result;
    }

    @ApiOperation(httpMethod="POST", value="删除分销商级别")
    @RequestMapping("service/deleteById")
    @ResponseBody
    public JsonResult<String> deleteById(int id) {
        JsonResult<String> result = new JsonResult<>();
        this.dstLevelService.deleteObjById(id);
        result.setData(null);
        result.setMsg("ok");
        result.setStatus("00");
        return result;
    }

    @ApiOperation(httpMethod="GET", value="查询分销商级别")
    @RequestMapping("service/findByPage")
    @ResponseBody
    public PageView<DstLevel> findByPage(@ApiParam(value="分页索引",defaultValue="0") @RequestParam(defaultValue="0") String iDisplayStart, @ApiParam(value="每页的数量",defaultValue="10") @RequestParam(defaultValue="10") String iDisplayLength, @ApiParam(value="查询条件") String sSearch) {
        PageView<DstLevel> pageView = new PageView<>(iDisplayStart, iDisplayLength);
        DstLevelExample example = new DstLevelExample();
        example.setPageView(pageView);
        if (StringUtils.isNotBlank(sSearch)) {
            // TODO Auto-generated method stub
        }
        return this.dstLevelService.queryObjByPage(example);
    }
    
    
}