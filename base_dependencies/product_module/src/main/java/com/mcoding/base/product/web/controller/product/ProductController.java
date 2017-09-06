package com.mcoding.base.product.web.controller.product;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.mcoding.base.product.bean.product.ProductImg;
import com.mcoding.base.product.bean.product.ProductImgExample;
import com.mcoding.base.product.bean.productPrice.ProductPrice;
import com.mcoding.base.product.bean.productPrice.ProductPriceExample;
import com.mcoding.base.product.service.product.ProductImgService;
import com.mcoding.base.product.service.productPrice.ProductPriceService;
import io.swagger.models.auth.In;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mcoding.base.core.CommonException;
import com.mcoding.base.core.Constant;
import com.mcoding.base.core.JsonResult;
import com.mcoding.base.core.PageView;
import com.mcoding.base.product.bean.product.Product;
import com.mcoding.base.product.bean.product.ProductExample;
import com.mcoding.base.product.service.product.ProductService;
import com.mcoding.base.ui.utils.StoreUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@Api(value = "产品")
@Controller
@RequestMapping("product")
public class ProductController {
	@Resource
	protected ProductService productService;

	@Resource
	protected ProductPriceService productPriceService;

	@Resource
	protected ProductImgService productImgService;

	@ApiIgnore
	@RequestMapping("service/toAddView")
	public ModelAndView toAddView() {
		return new ModelAndView("product/product/toAddView");
	}

	@ApiIgnore
	@RequestMapping("service/toMainView")
	public ModelAndView toMainView() {
		return new ModelAndView("product/product/toMainView");
	}

	@ApiIgnore
	@RequestMapping("service/toUpdateViewById")
	public ModelAndView toUpdateViewById(int id) {
		ModelAndView view = new ModelAndView();
		Product product = this.productService.queryObjById(id);
		view.addObject("product", product);

		ProductPriceExample priceExample = new ProductPriceExample();
		ProductPriceExample.Criteria criteria = priceExample.createCriteria();
		criteria.andProductIdEqualTo(id);
		List<ProductPrice> priceList = this.productPriceService.queryAllObjByExample(priceExample);

		ProductImgExample imgExample = new ProductImgExample();
		ProductImgExample.Criteria criteria1 = imgExample.createCriteria();
		criteria1.andProductIdEqualTo(id);
		List<ProductImg> imgList = this.productImgService.queryAllObjByExample(imgExample);

		view.addObject("priceList",priceList);
		view.addObject("imgList",imgList);
		view.setViewName("product/product/toAddView");
		return view;
	}

	@ApiOperation(httpMethod = "POST", value = "创建产品")
	@RequestMapping("service/create")
	@ResponseBody
	public JsonResult<Product> create(@RequestBody Product product) {
		JsonResult<Product> result = new JsonResult<>();
		Integer storeid = StoreUtils.getStoreFromThreadLocal().getId();
		product.setStoreId(storeid);
		this.productService.addObj(product);
		result.setData(product);
		result.setMsg("ok");
		result.setStatus("00");
		return result;
	}

	@ApiOperation(httpMethod = "POST", value = "编辑产品")
	@RequestMapping("service/edit")
	@ResponseBody
	public JsonResult<Product> edit(@RequestBody Product product) {
		if (product.getId() == null || product.getId() <= 0) {
			throw new CommonException("id 为空，保存失败");
		}
		JsonResult<Product> result = new JsonResult<>();
		this.productService.modifyObj(product);
		result.setData(product);
		result.setMsg("ok");
		result.setStatus("00");
		return result;
	}

	@ApiOperation(httpMethod = "POST", value = "删除产品")
	@RequestMapping("service/deleteById")
	@ResponseBody
	public JsonResult<String> deleteById(int id) {
		JsonResult<String> result = new JsonResult<>();
		this.productService.deleteObjById(id);
		result.setData(null);
		result.setMsg("ok");
		result.setStatus("00");
		return result;
	}

	@ApiOperation(httpMethod = "GET", value = "查询产品")
	@RequestMapping("service/findByPage")
	@ResponseBody
	public PageView<Product> findByPage(
			@ApiParam(name = "分页索引", defaultValue = "0") @RequestParam(defaultValue = "0") String iDisplayStart,
			@ApiParam(name = "每页的数量", defaultValue = "10") @RequestParam(defaultValue = "10") String iDisplayLength,
			@ApiParam(value = "查询条件") String sSearch) {
		PageView<Product> pageView = new PageView<>(iDisplayStart, iDisplayLength);
		ProductExample example = new ProductExample();
		example.setOrderByClause("id desc");
		example.setPageView(pageView);
		if (StringUtils.isNotBlank(sSearch)) {
			// TODO Auto-generated method stub
		}
		return this.productService.queryObjByPage(example);
	}	

	@ApiOperation(httpMethod = "GET", value = "按产品类目查询产品")
	@RequestMapping("service/findByCategory")
	@ResponseBody
	public PageView<Product> findByCategory(
			@ApiParam(name = "分页索引", defaultValue = "0") @RequestParam(defaultValue = "0") String iDisplayStart,
			@ApiParam(name = "每页的数量", defaultValue = "10") @RequestParam(defaultValue = "10") String iDisplayLength,
			@ApiParam(value = "产品类目") @RequestParam(defaultValue = "0") Integer categoryId) {
		PageView<Product> pageView = new PageView<>(iDisplayStart, iDisplayLength);
		ProductExample example = new ProductExample();
		ProductExample.Criteria criteria = example.createCriteria();
		criteria.andTypeEqualTo(categoryId);
		
		example.setOrderByClause("id desc");
		example.setPageView(pageView);
		
		return this.productService.queryObjByPage(example);
	}

	@ApiOperation(httpMethod = "GET", value = "根据条件查询产品")
	@RequestMapping("front/findConditionByPage")
	@ResponseBody
	public PageView<Product> findConditionByPage(
			@ApiParam(name = "分页索引", defaultValue = "1") @RequestParam(defaultValue = "1") int pageNo,
			@ApiParam(name = "每页的数量", defaultValue = "10") @RequestParam(defaultValue = "10") int pageSize,
			@RequestParam(defaultValue = "0") Integer categoryId,@RequestParam(defaultValue = "") String productName,HttpServletRequest request) {

		ProductExample example = new ProductExample();
		ProductExample.Criteria criteria = example.createCriteria();
		if(StringUtils.isNotBlank(productName)){
			criteria.andProductNameLike("%"+productName+"%");
		}
		Integer storeid = StoreUtils.getStoreFromThreadLocal().getId();
		if(storeid !=null){
			criteria.andStoreIdEqualTo(storeid);
		}
		criteria.andSaleStatusEqualTo(Constant.YES_INT);
		PageView<Product> pageView = new PageView<>(pageNo, pageSize);
		example.setPageView(pageView);

		String scene = "metrxmall";
		return this.productService.findConditionByPage(example, categoryId, scene);
		
//		pageView.setQueryResult(products);
//		return pageView;
	}
	
	@ApiOperation(httpMethod = "GET", value = "查询产品详情")
	@RequestMapping("front/findDetailById")
	@ResponseBody
	public JsonResult<Product> findDetailById(@ApiParam("产品id") int id) {
		String scene = "metrxmall";
		Product product = this.productService.queryProductDetailById(scene, id);
		
		JsonResult<Product> result = new JsonResult<>();
		result.setData(product);
		result.setStatus("00");
		result.setMsg("ok");
		return result;
	}

}