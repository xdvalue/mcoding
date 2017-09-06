package com.mcoding.base.product.web.controller.productPoint;

import com.mcoding.base.core.CommonException;
import com.mcoding.base.core.JsonResult;
import com.mcoding.base.core.PageView;
import com.mcoding.base.product.bean.productPoint.ProductPoint;
import com.mcoding.base.product.bean.productPoint.ProductPointExample;
import com.mcoding.base.product.service.productPoint.ProductPointService;
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

@Api(value="产品积分")
@Controller
@RequestMapping("productPoint")
public class ProductPointController {
    @Resource
    protected ProductPointService productPointService;

    @ApiIgnore
    @RequestMapping("service/toAddView")
    public ModelAndView toAddView() {
        return new ModelAndView("wechat/productPoint/toAddView");
    }

    @ApiIgnore
    @RequestMapping("service/toMainView")
    public ModelAndView toMainView() {
        return new ModelAndView("wechat/productPoint/toMainView");
    }

    @ApiIgnore
    @RequestMapping("service/toUpdateViewById")
    public ModelAndView toUpdateViewById(int id) {
        ModelAndView view = new ModelAndView();
        ProductPoint productPoint = this.productPointService.queryObjById(id);
        view.addObject("productPoint", productPoint);
        view.setViewName("wechat/productPoint/toAddView");
        return view;
    }

    @ApiOperation(httpMethod="POST", value="创建产品积分")
    @RequestMapping("service/create")
    @ResponseBody
    public JsonResult<String> create(@RequestBody ProductPoint productPoint) {
        JsonResult<String> result = new JsonResult<>();
        this.productPointService.addObj(productPoint);
        result.setData(null);
        result.setMsg("ok");
        result.setStatus("00");
        return result;
    }

    @ApiOperation(httpMethod="POST", value="编辑产品积分")
    @RequestMapping("service/edit")
    @ResponseBody
    public JsonResult<String> edit(@RequestBody ProductPoint productPoint) {
        if (productPoint.getId() == null || productPoint.getId() <=0) {
            throw new CommonException("id 为空，保存失败");
        }
        JsonResult<String> result = new JsonResult<>();
        this.productPointService.modifyObj(productPoint);
        result.setData(null);
        result.setMsg("ok");
        result.setStatus("00");
        return result;
    }

    @ApiOperation(httpMethod="POST", value="删除产品积分")
    @RequestMapping("service/deleteById")
    @ResponseBody
    public JsonResult<String> deleteById(int id) {
        JsonResult<String> result = new JsonResult<>();
        this.productPointService.deleteObjById(id);
        result.setData(null);
        result.setMsg("ok");
        result.setStatus("00");
        return result;
    }

    @ApiOperation(httpMethod="GET", value="查询产品积分")
    @RequestMapping("service/findByPage")
    @ResponseBody
    public PageView<ProductPoint> findByPage(@ApiParam(name="分页索引",defaultValue="0") @RequestParam(defaultValue="0") String iDisplayStart, @ApiParam(name="每页的数量",defaultValue="10") @RequestParam(defaultValue="10") String iDisplayLength, @ApiParam(value="查询条件") String sSearch) {
        PageView<ProductPoint> pageView = new PageView<>(iDisplayStart, iDisplayLength);
        ProductPointExample example = new ProductPointExample();
        example.setPageView(pageView);
        if (StringUtils.isNotBlank(sSearch)) {
            // TODO Auto-generated method stub
        }
        return this.productPointService.queryObjByPage(example);
    }
}