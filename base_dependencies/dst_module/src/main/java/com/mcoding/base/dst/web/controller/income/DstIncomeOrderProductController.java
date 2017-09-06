package com.mcoding.base.dst.web.controller.income;

import com.mcoding.base.core.CommonException;
import com.mcoding.base.core.JsonResult;
import com.mcoding.base.core.PageView;
import com.mcoding.base.dst.bean.income.DstIncomeOrderProduct;
import com.mcoding.base.dst.bean.income.DstIncomeOrderProductExample;
import com.mcoding.base.dst.service.income.DstIncomeOrderProductService;

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

@Api(value="佣金明细")
@Controller
@RequestMapping("dstIncomeOrderProduct")
public class DstIncomeOrderProductController {
    @Resource
    protected DstIncomeOrderProductService dstIncomeOrderProductService;

    @ApiIgnore
    @RequestMapping("service/toAddView")
    public ModelAndView toAddView() {
        return new ModelAndView("order/dstIncomeOrderProduct/toAddView");
    }

    @ApiIgnore
    @RequestMapping("service/toMainView")
    public ModelAndView toMainView() {
        return new ModelAndView("order/dstIncomeOrderProduct/toMainView");
    }

    @ApiIgnore
    @RequestMapping("service/toUpdateViewById")
    public ModelAndView toUpdateViewById(int id) {
        ModelAndView view = new ModelAndView();
        DstIncomeOrderProduct dstIncomeOrderProduct = this.dstIncomeOrderProductService.queryObjById(id);
        view.addObject("dstIncomeOrderProduct", dstIncomeOrderProduct);
        view.setViewName("order/dstIncomeOrderProduct/toAddView");
        return view;
    }

    @ApiOperation(httpMethod="POST", value="创建佣金明细")
    @RequestMapping("service/create")
    @ResponseBody
    public JsonResult<String> create(@RequestBody DstIncomeOrderProduct dstIncomeOrderProduct) {
        JsonResult<String> result = new JsonResult<>();
        this.dstIncomeOrderProductService.addObj(dstIncomeOrderProduct);
        result.setData(null);
        result.setMsg("ok");
        result.setStatus("00");
        return result;
    }

    @ApiOperation(httpMethod="POST", value="编辑佣金明细")
    @RequestMapping("service/edit")
    @ResponseBody
    public JsonResult<String> edit(@RequestBody DstIncomeOrderProduct dstIncomeOrderProduct) {
        if (dstIncomeOrderProduct.getId() == null || dstIncomeOrderProduct.getId() <=0) {
            throw new CommonException("id 为空，保存失败");
        }
        JsonResult<String> result = new JsonResult<>();
        this.dstIncomeOrderProductService.modifyObj(dstIncomeOrderProduct);
        result.setData(null);
        result.setMsg("ok");
        result.setStatus("00");
        return result;
    }

    @ApiOperation(httpMethod="POST", value="删除佣金明细")
    @RequestMapping("service/deleteById")
    @ResponseBody
    public JsonResult<String> deleteById(int id) {
        JsonResult<String> result = new JsonResult<>();
        this.dstIncomeOrderProductService.deleteObjById(id);
        result.setData(null);
        result.setMsg("ok");
        result.setStatus("00");
        return result;
    }

    @ApiOperation(httpMethod="GET", value="查询佣金明细")
    @RequestMapping("service/findByPage")
    @ResponseBody
    public PageView<DstIncomeOrderProduct> findByPage(@ApiParam(value="分页索引",defaultValue="0") @RequestParam(defaultValue="0") String iDisplayStart, @ApiParam(value="每页的数量",defaultValue="10") @RequestParam(defaultValue="10") String iDisplayLength, @ApiParam(value="查询条件") String sSearch) {
        PageView<DstIncomeOrderProduct> pageView = new PageView<>(iDisplayStart, iDisplayLength);
        DstIncomeOrderProductExample example = new DstIncomeOrderProductExample();
        example.setPageView(pageView);
        if (StringUtils.isNotBlank(sSearch)) {
            // TODO Auto-generated method stub
        }
        return this.dstIncomeOrderProductService.queryObjByPage(example);
    }
}