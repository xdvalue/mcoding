package com.mcoding.base.order.web.controller.orderreturn;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mcoding.base.core.CommonException;
import com.mcoding.base.core.JsonResult;
import com.mcoding.base.core.PageView;
import com.mcoding.base.member.bean.member.Member;
import com.mcoding.base.order.bean.orderreturn.OrderReturn;
import com.mcoding.base.order.bean.orderreturn.OrderReturnExample;
import com.mcoding.base.order.service.order.OrderService;
import com.mcoding.base.order.service.orderreturn.OrderReturnService;
import com.mcoding.base.ui.utils.StoreUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import springfox.documentation.annotations.ApiIgnore;

@Api(value="退换货单")
@Controller
@RequestMapping("orderReturn")
public class OrderReturnController {
    @Resource
    protected OrderReturnService orderReturnService;
    
    @Autowired
    protected OrderService orderService;

    @ApiIgnore
    @RequestMapping("service/toAddView")
    public ModelAndView toAddView() {
        return new ModelAndView("order/orderReturn/toAddView");
    }

    @ApiIgnore
    @RequestMapping("service/toMainView")
    public ModelAndView toMainView() {
        return new ModelAndView("order/orderReturn/toMainView");
    }

    @ApiIgnore
    @RequestMapping("service/toUpdateViewById")
    public ModelAndView toUpdateViewById(int id) {
        ModelAndView view = new ModelAndView();
        OrderReturn orderReturn = this.orderReturnService.queryObjById(id);
        view.addObject("orderReturn", orderReturn);
        view.setViewName("order/orderReturn/toAddView");
        return view;
    }

    /*@ApiOperation(httpMethod="POST", value="创建退换货单")
    @RequestMapping("service/create")
    @ResponseBody
    public JsonResult<String> create(@RequestBody OrderReturn orderReturn) {
        JsonResult<String> result = new JsonResult<>();
        this.orderReturnService.addObj(orderReturn);
        result.setData(null);
        result.setMsg("ok");
        result.setStatus("00");
        return result;
    }*/

    /*@ApiOperation(httpMethod="POST", value="编辑退换货单")
    @RequestMapping("service/edit")
    @ResponseBody
    public JsonResult<String> edit(@RequestBody OrderReturn orderReturn) {
        if (orderReturn.getId() == null || orderReturn.getId() <=0) {
            throw new CommonException("id 为空，保存失败");
        }
        JsonResult<String> result = new JsonResult<>();
        this.orderReturnService.modifyObj(orderReturn);
        result.setData(null);
        result.setMsg("ok");
        result.setStatus("00");
        return result;
    }*/

    /*@ApiOperation(httpMethod="POST", value="删除退换货单")
    @RequestMapping("service/deleteById")
    @ResponseBody
    public JsonResult<String> deleteById(int id) {
        JsonResult<String> result = new JsonResult<>();
        this.orderReturnService.deleteObjById(id);
        result.setData(null);
        result.setMsg("ok");
        result.setStatus("00");
        return result;
    }*/

    @ApiOperation(httpMethod="GET", value="查询退换货单")
    @RequestMapping("service/findByPage")
    @ResponseBody
    public PageView<OrderReturn> findByPage(
    		@ApiParam(value="分页索引",defaultValue="0") @RequestParam(defaultValue="0") String iDisplayStart, 
    		@ApiParam(value="每页的数量",defaultValue="10") @RequestParam(defaultValue="10") String iDisplayLength, 
    		@ApiParam(value="查询条件") String sSearch) {
        PageView<OrderReturn> pageView = new PageView<>(iDisplayStart, iDisplayLength);
        OrderReturnExample example = new OrderReturnExample();
        example.setPageView(pageView);
        if (StringUtils.isNotBlank(sSearch)) {
            // TODO Auto-generated method stub
        }
        return this.orderReturnService.queryObjByPage(example);
    }
    
    @ApiOperation(httpMethod="GET", value="查询当前用户的退换货单")
    @RequestMapping("front/findByCurrentMember")
    @ResponseBody
    public PageView<OrderReturn> findByCurrentMember(HttpSession session) {
		Member member = (Member) session.getAttribute("member");
		OrderReturnExample example = new OrderReturnExample();
		example.createCriteria()
		       .andMemberIdEqualTo(member.getId())
		       .andStoreIdEqualTo(StoreUtils.getStoreFromThreadLocal().getId());
		
		example.setOrderByClause("apply_time DESC");
		
		return this.orderReturnService.queryObjByPage(example);
	}
    
    /**
     * 申请订单退款
     * @param orderId
     * @return
     */
    @ApiOperation(httpMethod="POST", value="申请退货")
    @RequestMapping("front/applyOrderReturn")
    @ResponseBody
    public JsonResult<OrderReturn> applyOrderReturn(
    		@ApiParam(value="退货参数,必须包含orderId, deliveryName,deliveryCode,reason")@RequestBody OrderReturn orderReturn, HttpSession session){
    	
    	if (orderReturn == null || orderReturn.getOrderId() == null) {
			throw new CommonException("缺少订单id的参数");
		}
    	if(StringUtils.isBlank(orderReturn.getDeliveryName())){
    		throw new CommonException("请填写订单的退货的物流商家");
    	}
    	if(StringUtils.isBlank(orderReturn.getDeliveryCode())){
    		throw new CommonException("请填写订单的退货的物流单号");
    	}
    	if(StringUtils.isBlank(orderReturn.getReason())){
    		throw new CommonException("请填写退货的理由");
    	}
    	
    	this.orderReturnService.apply(orderReturn, (Member) session.getAttribute("member"));
    	
    	JsonResult<OrderReturn> result = new JsonResult<>();
    	result.setStatus("00");
    	result.setMsg("ok");
    	result.setData(orderReturn);
    	return result;
    }
    
    /**
     * 申请退货
     * @return
     */
    @ApiOperation(httpMethod="GET", value="退货审核通过")
    @RequestMapping("service/comfireAudit")
    @ResponseBody
    public JsonResult<String> comfireAudit(int orderReturnId, int status){
    	OrderReturn orderReturn = this.orderReturnService.queryObjById(orderReturnId);
    	if (orderReturn == null) {
			throw new CommonException("该退货单不存在，请刷新后重试");
		}
    	
    	this.orderReturnService.comfireAudit(orderReturnId);
    	
    	JsonResult<String> result = new JsonResult<>();
    	result.setStatus("00");
    	result.setMsg("ok");
    	return result;
    }
    
    @ApiOperation(httpMethod="GET", value="根据订单id查询退换货")
    @RequestMapping("front/findByOrderId")
    @ResponseBody
    public JsonResult<OrderReturn> findByOrderId(int orderId){
    	OrderReturnExample example = new OrderReturnExample();
    	example.createCriteria().andOrderIdEqualTo(orderId);
    	
    	List<OrderReturn> list = this.orderReturnService.queryAllObjByExample(example);
    	
    	JsonResult<OrderReturn> result = new JsonResult<>();
    	if (CollectionUtils.isNotEmpty(list)) {
			result.setData(list.get(0));
		}
    	result.setStatus("00");
    	result.setMsg("ok");
    	return result;
    }
}