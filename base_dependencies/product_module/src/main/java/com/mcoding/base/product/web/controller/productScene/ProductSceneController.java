package com.mcoding.base.product.web.controller.productScene;

import com.mcoding.base.core.CommonException;
import com.mcoding.base.core.JsonResult;
import com.mcoding.base.core.PageView;
import com.mcoding.base.product.bean.productScene.ProductScene;
import com.mcoding.base.product.bean.productScene.ProductSceneExample;
import com.mcoding.base.product.service.productScene.ProductSceneService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import javax.annotation.Resource;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@Api(value="产品场景")
@Controller
@RequestMapping("productScene")
public class ProductSceneController {
    @Resource
    protected ProductSceneService productSceneService;

    @ApiIgnore
    @RequestMapping("service/toAddView")
    public ModelAndView toAddView() {
        return new ModelAndView("sns/productScene/toAddView");
    }

    @ApiIgnore
    @RequestMapping("service/toMainView")
    public ModelAndView toMainView() {
        return new ModelAndView("sns/productScene/toMainView");
    }

    @ApiIgnore
    @RequestMapping("service/toUpdateViewById")
    public ModelAndView toUpdateViewById(int id) {
        ModelAndView view = new ModelAndView();
        ProductScene productScene = this.productSceneService.queryObjById(id);
        view.addObject("productScene", productScene);
        view.setViewName("sns/productScene/toAddView");
        return view;
    }

    @ApiOperation(httpMethod = "POST", value = "创建产品场景")
    @RequestMapping("service/create")
    @ResponseBody
    public JsonResult<String> create(@RequestBody ProductScene productScene) {
        JsonResult<String> result = new JsonResult<>();
        this.productSceneService.addObj(productScene);
        result.setData(null);
        result.setMsg("ok");
        result.setStatus("00");
        return result;
    }

    @ApiOperation(httpMethod = "POST", value = "编辑产品场景")
    @RequestMapping("service/edit")
    @ResponseBody
    public JsonResult<String> edit(@RequestBody ProductScene productScene) {
        if (productScene.getId() == null || productScene.getId() <= 0) {
            throw new CommonException("id 为空，保存失败");
        }
        JsonResult<String> result = new JsonResult<>();
        this.productSceneService.modifyObj(productScene);
        result.setData(null);
        result.setMsg("ok");
        result.setStatus("00");
        return result;
    }

    @ApiOperation(httpMethod = "POST", value = "删除产品场景")
    @RequestMapping("service/deleteById")
    @ResponseBody
    public JsonResult<String> deleteById(int id) {
        JsonResult<String> result = new JsonResult<>();
        this.productSceneService.deleteObjById(id);
        result.setData(null);
        result.setMsg("ok");
        result.setStatus("00");
        return result;
    }

    @ApiOperation(httpMethod = "GET", value = "查询产品场景")
    @RequestMapping("service/findByPage")
    @ResponseBody
    public PageView<ProductScene> findByPage(@ApiParam(name = "分页索引", defaultValue = "0") @RequestParam(defaultValue = "0") String iDisplayStart, @ApiParam(name = "每页的数量", defaultValue = "10") @RequestParam(defaultValue = "10") String iDisplayLength, @ApiParam(value = "查询条件") String sSearch) {
        PageView<ProductScene> pageView = new PageView<>(iDisplayStart, iDisplayLength);
        ProductSceneExample example = new ProductSceneExample();
        example.setPageView(pageView);
        if (StringUtils.isNotBlank(sSearch)) {
            // TODO Auto-generated method stub
        }
        return this.productSceneService.queryObjByPage(example);
    }

    @ApiIgnore
    @ApiOperation(httpMethod = "GET", value = "查询所有产品场景")
    @RequestMapping("service/findAll")
    @ResponseBody
    public List<ProductScene> findAll() {
        ProductSceneExample example = new ProductSceneExample();
        return this.productSceneService.queryAllObjByExample(example);
    }
}