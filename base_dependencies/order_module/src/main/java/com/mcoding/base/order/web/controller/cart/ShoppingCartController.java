package com.mcoding.base.order.web.controller.cart;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mcoding.base.core.CommonException;
import com.mcoding.base.core.JsonResult;
import com.mcoding.base.member.bean.member.Member;
import com.mcoding.base.order.bean.cart.ShoppingCart;
import com.mcoding.base.order.service.cart.ShoppingCartService;
import com.mcoding.base.product.bean.product.Product;
import com.mcoding.base.product.service.product.ProductService;
import com.mcoding.base.ui.utils.StoreUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(value="购物车")
@Controller
@RequestMapping("shoppingCart")
public class ShoppingCartController {
    @Resource
    protected ShoppingCartService shoppingCartService;
    
    @Autowired
    protected ProductService productService;
    
    /**
     * 添加产品到购物车
     * @return
     */
    @ApiOperation(httpMethod="GET",value="添加产品到购物车")
    @RequestMapping("front/addProductIntoCart")
    @ResponseBody
    public JsonResult<String> addProductIntoCart(
    		@ApiParam(value="产品id") int productId, 
    		@ApiParam(value="产品数量,正数是加，负数是减,计算后的总数不能为0") int nums,
    		HttpSession session){
    	
    	Member member = (Member) session.getAttribute("member");

		String scene = "metrxmall";
    	Product product = this.productService.queryProductDetailById(scene, productId);
    	this.shoppingCartService.addProductIntoCart(product, nums, member.getId());
    	
    	JsonResult<String> result = new JsonResult<>();
    	result.setData("ok");
    	result.setMsg("ok");
    	result.setStatus("00");
    	return result;
    	
    }
    
    /**
     * 添加产品到购物车
     * @return
     */
    @ApiOperation(httpMethod="GET",value="添加产品到购物车")
    @RequestMapping("front/updateAll")
    @ResponseBody
    public JsonResult<String> updateAll(@RequestBody List<ShoppingCart> shopCartList,
    		HttpSession session){
    	
    	Member member = (Member) session.getAttribute("member");
    	this.shoppingCartService.updateCarts(shopCartList, member.getId());
    	
    	JsonResult<String> result = new JsonResult<>();
    	result.setData("ok");
    	result.setMsg("ok");
    	result.setStatus("00");
    	return result;
    	
    }
    
    @ApiOperation(httpMethod="GET",value="删除多个产品的购物车,如果id为0，则清空购物车")
    @RequestMapping("front/deleteById")
    @ResponseBody
    public JsonResult<String> deleteById(@ApiParam(value="购物车的id,例如 1,2,3",name="ids") Integer[] ids, HttpSession session){

    	if (ArrayUtils.isEmpty(ids)) {
			throw new CommonException("请选择需要清除的产品");
		}
    	
    	if (ids.length == 1 && ids[0].equals(0)) {
    		Member member =(Member) session.getAttribute("member");
        	this.shoppingCartService.clearByMemberId(member.getId(), StoreUtils.getStoreFromThreadLocal().getId());
        	
		}
    	
    	for(int i=0; i<ids.length; i++){
    		this.shoppingCartService.deleteObjById(ids[i]);
    	}
    	
    	JsonResult<String> result = new JsonResult<>();
    	result.setData("ok");
    	result.setMsg("ok");
    	result.setStatus("00");
    	return result;
    }
    
    /*@ApiOperation(httpMethod="GET",value="清空当前用户的购物车")
    @RequestMapping("front/clearByCurrentMember")
    @ResponseBody
    public JsonResult<String> clearByCurrentMember(HttpSession session){
    	Member member =(Member) session.getAttribute("member");
    	this.shoppingCartService.clearByMemberId(member.getId(), StoreUtils.getStoreFromThreadLocal().getId());
    	
    	JsonResult<String> result = new JsonResult<>();
    	result.setData("ok");
    	result.setMsg("ok");
    	result.setStatus("00");
    	return result;
    }*/
    
    @ApiOperation(httpMethod="GET",value="查询当前用户的购物车")
    @RequestMapping("front/findByCurrentMember")
    @ResponseBody
    public JsonResult<List<ShoppingCart>> findByCurrentMember(HttpSession session){
    	
    	Member member =(Member) session.getAttribute("member");
    	String scene = "metrxmall";
    	
    	List<ShoppingCart> list = this.shoppingCartService.findByMember(scene, member.getId(), StoreUtils.getStoreFromThreadLocal().getId());
    	
    	/*int amountTotal = 0;
    	int nums = 0;
    	
    	for(int i=0; CollectionUtils.isNotEmpty(list) && i<list.size(); i++){
    		amountTotal = amountTotal + list.get(i).getProduct().getPriceList().get(0).getValue();
    		nums = nums + list.get(i).getNums();
    	}
*/    	
    	JsonResult<List<ShoppingCart>> result = new JsonResult<>();
    	result.setData(list);
    	result.setMsg("ok");
    	result.setStatus("00");
    	result.setSize(list.size());
    	return result;
    	
    }
    

    /*@ApiIgnore
    @RequestMapping("service/toAddView")
    public ModelAndView toAddView() {
        return new ModelAndView("wechat/shoppingCart/toAddView");
    }

    @ApiOperation(httpMethod="GET", value="查询购物车")
    @RequestMapping("service/findByPage")
    @ResponseBody
    public PageView<ShoppingCart> findByPage(
    		@ApiParam(name="分页索引",defaultValue="0") @RequestParam(defaultValue="0") String iDisplayStart, 
    		@ApiParam(name="每页的数量",defaultValue="10") @RequestParam(defaultValue="10") String iDisplayLength, 
    		@ApiParam(value="查询条件") String sSearch) {
    	PageView<ShoppingCart> pageView = new PageView<>(iDisplayStart, iDisplayLength);
    	ShoppingCartExample example = new ShoppingCartExample();
    	example.setPageView(pageView);
    	if (StringUtils.isNotBlank(sSearch)) {
    		// TODO Auto-generated method stub
    	}
    	return this.shoppingCartService.queryObjByPage(example);
    }
    @ApiIgnore
    @RequestMapping("service/toMainView")
    public ModelAndView toMainView() {
        return new ModelAndView("wechat/shoppingCart/toMainView");
    }

    @ApiIgnore
    @RequestMapping("service/toUpdateViewById")
    public ModelAndView toUpdateViewById(int id) {
        ModelAndView view = new ModelAndView();
        ShoppingCart shoppingCart = this.shoppingCartService.queryObjById(id);
        view.addObject("shoppingCart", shoppingCart);
        view.setViewName("wechat/shoppingCart/toAddView");
        return view;
    }

    @ApiOperation(httpMethod="POST", value="创建购物车")
    @RequestMapping("service/create")
    @ResponseBody
    public JsonResult<String> create(@RequestBody ShoppingCart shoppingCart) {
        JsonResult<String> result = new JsonResult<>();
        this.shoppingCartService.addObj(shoppingCart);
        result.setData(null);
        result.setMsg("ok");
        result.setStatus("00");
        return result;
    }

    @ApiOperation(httpMethod="POST", value="编辑购物车")
    @RequestMapping("service/edit")
    @ResponseBody
    public JsonResult<String> edit(@RequestBody ShoppingCart shoppingCart) {
        if (shoppingCart.getId() == null || shoppingCart.getId() <=0) {
            throw new CommonException("id 为空，保存失败");
        }
        JsonResult<String> result = new JsonResult<>();
        this.shoppingCartService.modifyObj(shoppingCart);
        result.setData(null);
        result.setMsg("ok");
        result.setStatus("00");
        return result;
    }

    @ApiOperation(httpMethod="POST", value="删除购物车")
    @RequestMapping("service/deleteById")
    @ResponseBody
    public JsonResult<String> deleteById(int id) {
        JsonResult<String> result = new JsonResult<>();
        this.shoppingCartService.deleteObjById(id);
        result.setData(null);
        result.setMsg("ok");
        result.setStatus("00");
        return result;
    }

    @ApiOperation(httpMethod="GET", value="查询购物车")
    @RequestMapping("service/findByPage")
    @ResponseBody
    public PageView<ShoppingCart> findByPage(@ApiParam(name="分页索引",defaultValue="0") @RequestParam(defaultValue="0") String iDisplayStart, @ApiParam(name="每页的数量",defaultValue="10") @RequestParam(defaultValue="10") String iDisplayLength, @ApiParam(value="查询条件") String sSearch) {
        PageView<ShoppingCart> pageView = new PageView<>(iDisplayStart, iDisplayLength);
        ShoppingCartExample example = new ShoppingCartExample();
        example.setPageView(pageView);
        if (StringUtils.isNotBlank(sSearch)) {
            // TODO Auto-generated method stub
        }
        return this.shoppingCartService.queryObjByPage(example);
    }*/
}