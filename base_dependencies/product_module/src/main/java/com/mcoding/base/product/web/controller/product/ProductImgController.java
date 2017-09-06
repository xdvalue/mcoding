package com.mcoding.base.product.web.controller.product;

import com.mcoding.base.core.CommonException;
import com.mcoding.base.core.JsonResult;
import com.mcoding.base.core.PageView;
import com.mcoding.base.product.bean.product.ProductImg;
import com.mcoding.base.product.bean.product.ProductImgExample;
import com.mcoding.base.product.service.product.ProductImgService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.lang.reflect.Array;
import java.util.List;
import javax.annotation.Resource;

import io.swagger.models.auth.In;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import springfox.documentation.annotations.ApiIgnore;

@Api(value="产品图片")
@Controller
@RequestMapping("productImg")
public class ProductImgController {
    @Resource
    protected ProductImgService productImgService;

    @ApiIgnore
    @RequestMapping("service/toAddView")
    public ModelAndView toAddView() {
        return new ModelAndView("productImg/productImg/toAddView");
    }

    @ApiIgnore
    @RequestMapping("service/toMainView")
    public ModelAndView toMainView() {
        return new ModelAndView("productImg/productImg/toMainView");
    }

    @ApiIgnore
    @RequestMapping("service/toUpdateViewById")
    public ModelAndView toUpdateViewById(int id) {
        ModelAndView view = new ModelAndView();
        ProductImg productImg = this.productImgService.queryObjById(id);
        view.addObject("productImg", productImg);
        view.setViewName("productImg/productImg/toAddView");
        return view;
    }

    @ApiIgnore
    @ApiOperation(httpMethod="POST", value="创建产品图片")
    @RequestMapping("service/create")
    @ResponseBody
    public JsonResult<String> create(@RequestBody ProductImg productImg) {
        JsonResult<String> result = new JsonResult<>();
        this.productImgService.addObj(productImg);
        result.setData(null);
        result.setMsg("ok");
        result.setStatus("00");
        return result;
    }

    @ApiIgnore
    @ApiOperation(httpMethod="POST", value="编辑产品图片")
    @RequestMapping("service/edit")
    @ResponseBody
    public JsonResult<String> edit(@RequestBody ProductImg productImg) {
        if (productImg.getId() == null || productImg.getId() <=0) {
            throw new CommonException("id 为空，保存失败");
        }
        JsonResult<String> result = new JsonResult<>();
        this.productImgService.modifyObj(productImg);
        result.setData(null);
        result.setMsg("ok");
        result.setStatus("00");
        return result;
    }

    @ApiIgnore
    @ApiOperation(httpMethod="POST", value="删除产品图片")
    @RequestMapping("service/deleteById")
    @ResponseBody
    public JsonResult<String> deleteById(int id) {
        JsonResult<String> result = new JsonResult<>();
        this.productImgService.deleteObjById(id);
        result.setData(null);
        result.setMsg("ok");
        result.setStatus("00");
        return result;
    }

    @ApiIgnore
    @ApiOperation(httpMethod="GET", value="查询产品图片")
    @RequestMapping("service/findByPage")
    @ResponseBody
    public PageView<ProductImg> findByPage(@ApiParam(value="分页索引",defaultValue="0") @RequestParam(defaultValue="0") String iDisplayStart, @ApiParam(value="每页的数量",defaultValue="10") @RequestParam(defaultValue="10") String iDisplayLength, @ApiParam(value="查询条件") String sSearch) {
        PageView<ProductImg> pageView = new PageView<>(iDisplayStart, iDisplayLength);
        ProductImgExample example = new ProductImgExample();
        example.setPageView(pageView);
        if (StringUtils.isNotBlank(sSearch)) {
            // TODO Auto-generated method stub
        }
        return this.productImgService.queryObjByPage(example);
    }

    @ApiIgnore
    @ApiOperation(httpMethod="POST", value="批量创建多张图片")
    @RequestMapping("service/createOrEditImgs")
    @ResponseBody
    public JsonResult<String> createOrEditImgs(@RequestBody ProductImg productImg) {
        JsonResult<String> result = new JsonResult<>();
        String[] urlArray = productImg.getUrls().split(",");
        ProductImgExample example = new ProductImgExample();
        ProductImgExample.Criteria criteria = example.createCriteria();
        criteria.andProductIdEqualTo(productImg.getProductId());
        this.productImgService.deleteObjByExample(example);
        for (String img : urlArray){
            productImg.setUrl(img);
            this.productImgService.addObj(productImg);
        }
        result.setData(null);
        result.setMsg("ok");
        result.setStatus("00");
        return result;
    }

    @ApiIgnore
    @ApiOperation(httpMethod="GET", value="查询产品的所有图片")
    @RequestMapping("service/findAll")
    @ResponseBody
    public List<ProductImg> findAll(Integer id) {
        if (id !=null) {
            ProductImgExample example = new ProductImgExample();
            ProductImgExample.Criteria criteria = example.createCriteria();
            criteria.andProductIdEqualTo(id);
            return this.productImgService.queryAllObjByExample(example);
        }
       return null;
    }
}