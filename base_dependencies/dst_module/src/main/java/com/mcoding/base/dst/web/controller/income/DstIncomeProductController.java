package com.mcoding.base.dst.web.controller.income;

import com.mcoding.base.core.CommonException;
import com.mcoding.base.core.JsonResult;
import com.mcoding.base.core.PageView;
import com.mcoding.base.dst.bean.income.DstIncomeProduct;
import com.mcoding.base.dst.bean.income.DstIncomeProductExample;
import com.mcoding.base.dst.service.income.DstIncomeProductService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.List;
import javax.annotation.Resource;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import springfox.documentation.annotations.ApiIgnore;

@Api(value="产品-分销商级别关联表")
@Controller
@RequestMapping("dstIncomeProduct")
public class DstIncomeProductController {
    @Resource
    protected DstIncomeProductService dstIncomeProductService;

    @ApiIgnore
    @RequestMapping("service/toAddView")
    public ModelAndView toAddView() {
        return new ModelAndView("order/dstIncomeProduct/toAddView");
    }

    @ApiIgnore
    @RequestMapping("service/toMainView")
    public ModelAndView toMainView() {
        return new ModelAndView("order/dstIncomeProduct/toMainView");
    }

    @ApiIgnore
    @RequestMapping("service/toUpdateViewById")
    public ModelAndView toUpdateViewById(int id) {
        ModelAndView view = new ModelAndView();
        DstIncomeProduct dstIncomeProduct = this.dstIncomeProductService.queryObjById(id);
        view.addObject("dstIncomeProduct", dstIncomeProduct);
        view.setViewName("order/dstIncomeProduct/toAddView");
        return view;
    }

    @ApiOperation(httpMethod="POST", value="创建产品-分销商级别关联表")
    @RequestMapping("service/create")
    @ResponseBody
    public JsonResult<String> create(@RequestBody DstIncomeProduct dstIncomeProduct) {
        JsonResult<String> result = new JsonResult<>();
        this.dstIncomeProductService.addObj(dstIncomeProduct);
        result.setData(null);
        result.setMsg("ok");
        result.setStatus("00");
        return result;
    }

    @ApiOperation(httpMethod="POST", value="编辑产品-分销商级别关联表")
    @RequestMapping("service/edit")
    @ResponseBody
    public JsonResult<String> edit(@RequestBody DstIncomeProduct dstIncomeProduct) {
        if (dstIncomeProduct.getId() == null || dstIncomeProduct.getId() <=0) {
            throw new CommonException("id 为空，保存失败");
        }
        JsonResult<String> result = new JsonResult<>();
        this.dstIncomeProductService.modifyObj(dstIncomeProduct);
        result.setData(null);
        result.setMsg("ok");
        result.setStatus("00");
        return result;
    }

    @ApiOperation(httpMethod="POST", value="删除产品-分销商级别关联表")
    @RequestMapping("service/deleteById")
    @ResponseBody
    public JsonResult<String> deleteById(int id) {
        JsonResult<String> result = new JsonResult<>();
        this.dstIncomeProductService.deleteObjById(id);
        result.setData(null);
        result.setMsg("ok");
        result.setStatus("00");
        return result;
    }

    @ApiOperation(httpMethod="GET", value="查询产品-分销商级别关联表")
    @RequestMapping("service/findByPage")
    @ResponseBody
    public PageView<DstIncomeProduct> findByPage(@ApiParam(value="分页索引",defaultValue="0") @RequestParam(defaultValue="0") String iDisplayStart, @ApiParam(value="每页的数量",defaultValue="10") @RequestParam(defaultValue="10") String iDisplayLength, @ApiParam(value="查询条件") String sSearch) {
        PageView<DstIncomeProduct> pageView = new PageView<>(iDisplayStart, iDisplayLength);
        DstIncomeProductExample example = new DstIncomeProductExample();
        example.setPageView(pageView);
        if (StringUtils.isNotBlank(sSearch)) {
            // TODO Auto-generated method stub
        }
        return this.dstIncomeProductService.queryObjByPage(example);
    }
}