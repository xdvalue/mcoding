package com.mcoding.base.product.web.controller.productPrice;

import com.mcoding.base.core.CommonException;
import com.mcoding.base.core.JsonResult;
import com.mcoding.base.core.PageView;
import com.mcoding.base.product.bean.productPrice.ProductPrice;
import com.mcoding.base.product.bean.productPrice.ProductPriceExample;
import com.mcoding.base.product.service.productPrice.ProductPriceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import java.util.List;

@Api(value="产品价格")
@Controller
@RequestMapping("productPrice")
public class ProductPriceController {
    @Resource
    protected ProductPriceService productPriceService;

    @ApiIgnore
    @RequestMapping("service/toAddView")
    public ModelAndView toAddView() {
        return new ModelAndView("sns/productPrice/toAddView");
    }

    @ApiIgnore
    @RequestMapping("service/toMainView")
    public ModelAndView toMainView() {
        return new ModelAndView("sns/productPrice/toMainView");
    }

    @ApiIgnore
    @RequestMapping("service/toUpdateViewById")
    public ModelAndView toUpdateViewById(int id) {
        ModelAndView view = new ModelAndView();
        ProductPrice productPrice = this.productPriceService.queryObjById(id);
        view.addObject("productPrice", productPrice);
        view.setViewName("sns/productPrice/toAddView");
        return view;
    }

    @ApiOperation(httpMethod="POST", value="创建产品价格")
    @RequestMapping("service/create")
    @ResponseBody
    public JsonResult<String> create(@RequestBody ProductPrice productPrice) {
        JsonResult<String> result = new JsonResult<>();
        this.productPriceService.addObj(productPrice);
        result.setData(null);
        result.setMsg("ok");
        result.setStatus("00");
        return result;
    }

    @ApiOperation(httpMethod="POST", value="编辑产品价格")
    @RequestMapping("service/edit")
    @ResponseBody
    public JsonResult<String> edit(@RequestBody ProductPrice productPrice) {
        if (productPrice.getId() == null || productPrice.getId() <=0) {
            throw new CommonException("id 为空，保存失败");
        }
        JsonResult<String> result = new JsonResult<>();
        this.productPriceService.modifyObj(productPrice);
        result.setData(null);
        result.setMsg("ok");
        result.setStatus("00");
        return result;
    }

    @ApiOperation(httpMethod="POST", value="删除产品价格")
    @RequestMapping("service/deleteById")
    @ResponseBody
    public JsonResult<String> deleteById(int id) {
        JsonResult<String> result = new JsonResult<>();
        this.productPriceService.deleteObjById(id);
        result.setData(null);
        result.setMsg("ok");
        result.setStatus("00");
        return result;
    }

    @ApiOperation(httpMethod="GET", value="查询产品价格")
    @RequestMapping("service/findByPage")
    @ResponseBody
    public PageView<ProductPrice> findByPage(@ApiParam(name="分页索引",defaultValue="0") @RequestParam(defaultValue="0") String iDisplayStart, @ApiParam(name="每页的数量",defaultValue="10") @RequestParam(defaultValue="10") String iDisplayLength, @ApiParam(value="查询条件") String sSearch) {
        PageView<ProductPrice> pageView = new PageView<>(iDisplayStart, iDisplayLength);
        ProductPriceExample example = new ProductPriceExample();
        example.setPageView(pageView);
        if (StringUtils.isNotBlank(sSearch)) {
            // TODO Auto-generated method stub
        }
        return this.productPriceService.queryObjByPage(example);
    }

    @ApiOperation(httpMethod="GET", value="查询产品价格详细")
    @RequestMapping("service/findByProductId")
    @ResponseBody
    public JsonResult<List<ProductPrice>> findByProductId(@ApiParam("产品id") int productId) {
        JsonResult<List<ProductPrice>> result = new JsonResult<>();
        ProductPriceExample example = new ProductPriceExample();
        ProductPriceExample.Criteria criteria = example.createCriteria();
        criteria.andProductIdEqualTo(productId);
        List<ProductPrice> list = this.productPriceService.queryAllObjByExample(example);
        if(list.size()>0){
            result.setData(list);
            result.setMsg("ok");
            result.setStatus("00");
        }else{
            result.setData(null);
            result.setMsg("error");
            result.setStatus("01");
        }
        return result;
    }
}