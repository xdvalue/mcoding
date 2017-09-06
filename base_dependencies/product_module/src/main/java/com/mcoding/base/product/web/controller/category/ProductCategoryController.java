package com.mcoding.base.product.web.controller.category;

import javax.annotation.Resource;

import com.mcoding.base.product.bean.product.Product;
import com.mcoding.base.ui.utils.StoreUtils;
import org.apache.commons.collections.CollectionUtils;
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
import com.mcoding.base.product.bean.category.ProductCategory;
import com.mcoding.base.product.bean.category.ProductCategoryExample;
import com.mcoding.base.product.service.category.ProductCategoryService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import springfox.documentation.annotations.ApiIgnore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(value="产品分类")
@Controller
@RequestMapping("productCategory")
public class ProductCategoryController {
    @Resource
    protected ProductCategoryService productCategoryService;

    @ApiIgnore
    @RequestMapping("service/toAddView")
    public ModelAndView toAddView() {
        return new ModelAndView("product/productCategory/toAddView");
    }

    @ApiIgnore
    @RequestMapping("service/toMainView")
    public ModelAndView toMainView() {
        return new ModelAndView("product/productCategory/toMainView");
    }

    @ApiIgnore
    @RequestMapping("service/toUpdateViewById")
    public ModelAndView toUpdateViewById(int id) {
        ModelAndView view = new ModelAndView();
        ProductCategory productCategory = this.productCategoryService.queryObjById(id);
        view.addObject("productCategory", productCategory);
        view.setViewName("product/productCategory/toAddView");
        return view;
    }

    @ApiOperation(httpMethod="POST", value="创建产品分类")
    @RequestMapping("service/create")
    @ResponseBody
    public JsonResult<String> create(@RequestBody ProductCategory productCategory) {
        JsonResult<String> result = new JsonResult<>();
        this.productCategoryService.addObj(productCategory);
        result.setData(null);
        result.setMsg("ok");
        result.setStatus("00");
        return result;
    }

    @ApiOperation(httpMethod="POST", value="编辑产品分类")
    @RequestMapping("service/edit")
    @ResponseBody
    public JsonResult<String> edit(@RequestBody ProductCategory productCategory) {
        if (productCategory.getId() == null || productCategory.getId() <=0) {
            throw new CommonException("id 为空，保存失败");
        }
        JsonResult<String> result = new JsonResult<>();
        this.productCategoryService.modifyObj(productCategory);
        result.setData(null);
        result.setMsg("ok");
        result.setStatus("00");
        return result;
    }

    @ApiOperation(httpMethod="POST", value="删除产品分类")
    @RequestMapping("service/deleteById")
    @ResponseBody
    public JsonResult<String> deleteById(int id) {
        JsonResult<String> result = new JsonResult<>();
        this.productCategoryService.deleteObjById(id);
        result.setData(null);
        result.setMsg("ok");
        result.setStatus("00");
        return result;
    }

    @ApiOperation(httpMethod="GET", value="查询产品分类")
    @RequestMapping("service/findByPage")
    @ResponseBody
    public PageView<ProductCategory> findByPage(@ApiParam(name="分页索引",defaultValue="0") @RequestParam(defaultValue="0") String iDisplayStart, @ApiParam(name="每页的数量",defaultValue="10") @RequestParam(defaultValue="10") String iDisplayLength, @ApiParam(value="查询条件") String sSearch) {
        PageView<ProductCategory> pageView = new PageView<>(iDisplayStart, iDisplayLength);
        ProductCategoryExample example = new ProductCategoryExample();
        example.setPageView(pageView);
        if (StringUtils.isNotBlank(sSearch)) {
            // TODO Auto-generated method stub
        }
        return this.productCategoryService.queryObjByPage(example);
    }

    @ApiOperation(httpMethod = "GET", value="根据parentid查询产品分类")
    @RequestMapping("service/findByParentId")
    @ResponseBody
    public JsonResult<List<ProductCategory>> findByParentId(int categoryParentId){
        JsonResult<List<ProductCategory>> result = new JsonResult<List<ProductCategory>>();
        Integer storeid =  StoreUtils.getStoreFromThreadLocal().getId();
        ProductCategoryExample example = new ProductCategoryExample();
        ProductCategoryExample.Criteria criteria = example.createCriteria();
        criteria.andCategoryParentIdEqualTo(categoryParentId);
        criteria.andStoreIdEqualTo(storeid);
        List<ProductCategory> list = this.productCategoryService.queryAllObjByExample(example);
        if(!CollectionUtils.isEmpty(list)){
            result.setData(list);
            result.setMsg("查询成功");
            result.setSize(list.size());
            result.setStatus("00");
        }
        return result;
    }
    
    @RequestMapping("service/findByParentAndChildren")
    @ResponseBody
    public JsonResult<ProductCategory> findByParentAndChildren(@RequestParam(defaultValue="0") int topParentId){
    	JsonResult<ProductCategory> result = new JsonResult<>();
    	result.setStatus("00");
    	result.setMsg("ok");
    	result.setData(this.productCategoryService.queryObjById(topParentId));
    	return result;
    }

//    @ApiOperation(httpMethod = "GET", value="查询所有分类与对应的子类")
//    @RequestMapping("service/findByParentAndChildren")
//    @ResponseBody
//    public JsonResult<List<Map<String,Object>>> findByParentAndChildren(){
//        JsonResult<List<Map<String,Object>>> result = new JsonResult<List<Map<String,Object>>>();
//        Integer storeid =  StoreUtils.getStoreFromThreadLocal().getId();
//
//        ProductCategoryExample example = new ProductCategoryExample();
//        ProductCategoryExample.Criteria criteria = example.createCriteria();
//        criteria.andStoreIdEqualTo(storeid).andCategoryParentIdEqualTo(0);
//        List<ProductCategory> parentlist = this.productCategoryService.queryAllObjByExample(example);
//
//        Map<String,Object> jsonObject = null;
//        List<Map<String,Object>> jsonList=new ArrayList<Map<String,Object>>();
//        if(!CollectionUtils.isEmpty(parentlist)){
//            for (ProductCategory category : parentlist){
//                jsonObject =new HashMap<String, Object>();
//                ProductCategoryExample example2 = new ProductCategoryExample();
//                ProductCategoryExample.Criteria criteria2 = example2.createCriteria();
//                criteria2.andCategoryParentIdEqualTo(category.getId());
//                List<ProductCategory> childrenlist = this.productCategoryService.queryAllObjByExample(example2);
//                jsonObject.put("categoryType",category.getCategoryName());
//                jsonObject.put("categoryTypeList",childrenlist);
//                jsonList.add(jsonObject);
//            }
//        }
//        result.setData(jsonList);
//        result.setMsg("查询成功");
//        result.setStatus("00");
//        return result;
//    }



}