package com.mcoding.base.dst.web.controller.income;

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
import com.mcoding.base.dst.bean.income.DstIncomeOrder;
import com.mcoding.base.dst.bean.income.DstIncomeOrderExample;
import com.mcoding.base.dst.service.income.DstIncomeOrderService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import springfox.documentation.annotations.ApiIgnore;

@Api(value="会员分销收益表")
@Controller
@RequestMapping("dstIncomeOrder")
public class DstIncomeOrderController {
    @Resource
    protected DstIncomeOrderService dstIncomeOrderService;

    @ApiIgnore
    @RequestMapping("service/toAddView")
    public ModelAndView toAddView() {
        return new ModelAndView("order/dstIncomeOrder/toAddView");
    }

    @ApiIgnore
    @RequestMapping("service/toMainView")
    public ModelAndView toMainView() {
        return new ModelAndView("order/dstIncomeOrder/toMainView");
    }

    @ApiIgnore
    @RequestMapping("service/toUpdateViewById")
    public ModelAndView toUpdateViewById(int id) {
        ModelAndView view = new ModelAndView();
        DstIncomeOrder dstIncomeOrder = this.dstIncomeOrderService.queryObjById(id);
        view.addObject("dstIncomeOrder", dstIncomeOrder);
        view.setViewName("order/dstIncomeOrder/toAddView");
        return view;
    }

    @ApiOperation(httpMethod="POST", value="创建会员分销收益表")
    @RequestMapping("service/create")
    @ResponseBody
    public JsonResult<String> create(@RequestBody DstIncomeOrder dstIncomeOrder) {
        JsonResult<String> result = new JsonResult<>();
        this.dstIncomeOrderService.addObj(dstIncomeOrder);
        result.setData(null);
        result.setMsg("ok");
        result.setStatus("00");
        return result;
    }

    @ApiOperation(httpMethod="POST", value="编辑会员分销收益表")
    @RequestMapping("service/edit")
    @ResponseBody
    public JsonResult<String> edit(@RequestBody DstIncomeOrder dstIncomeOrder) {
        if (dstIncomeOrder.getId() == null || dstIncomeOrder.getId() <=0) {
            throw new CommonException("id 为空，保存失败");
        }
        JsonResult<String> result = new JsonResult<>();
        this.dstIncomeOrderService.modifyObj(dstIncomeOrder);
        result.setData(null);
        result.setMsg("ok");
        result.setStatus("00");
        return result;
    }

    @ApiOperation(httpMethod="POST", value="删除会员分销收益表")
    @RequestMapping("service/deleteById")
    @ResponseBody
    public JsonResult<String> deleteById(int id) {
        JsonResult<String> result = new JsonResult<>();
        this.dstIncomeOrderService.deleteObjById(id);
        result.setData(null);
        result.setMsg("ok");
        result.setStatus("00");
        return result;
    }

    @ApiOperation(httpMethod="GET", value="查询会员分销收益表")
    @RequestMapping("service/findByPage")
    @ResponseBody
    public PageView<DstIncomeOrder> findByPage(@ApiParam(value="分页索引",defaultValue="0") @RequestParam(defaultValue="0") String iDisplayStart, @ApiParam(value="每页的数量",defaultValue="10") @RequestParam(defaultValue="10") String iDisplayLength, @ApiParam(value="查询条件") String sSearch) {
        PageView<DstIncomeOrder> pageView = new PageView<>(iDisplayStart, iDisplayLength);
        DstIncomeOrderExample example = new DstIncomeOrderExample();
        example.setPageView(pageView);
        if (StringUtils.isNotBlank(sSearch)) {
            // TODO Auto-generated method stub
        }
        return this.dstIncomeOrderService.queryObjByPage(example);
    }
}