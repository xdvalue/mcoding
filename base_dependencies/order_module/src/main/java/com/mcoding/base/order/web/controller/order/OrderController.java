package com.mcoding.base.order.web.controller.order;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mcoding.base.core.CommonException;
import com.mcoding.base.core.Constant;
import com.mcoding.base.core.JsonResult;
import com.mcoding.base.core.PageView;
import com.mcoding.base.member.bean.member.Member;
import com.mcoding.base.member.bean.member.MemberAddress;
import com.mcoding.base.member.service.member.MemberAddressService;
import com.mcoding.base.member.service.member.MemberService;
import com.mcoding.base.member.service.wechat.StoreWxRefService;
import com.mcoding.base.order.bean.cart.ShoppingCart;
import com.mcoding.base.order.bean.cart.ShoppingCartExample;
import com.mcoding.base.order.bean.order.Order;
import com.mcoding.base.order.bean.order.OrderExample;
import com.mcoding.base.order.service.cart.ShoppingCartService;
import com.mcoding.base.order.service.order.OrderService;
import com.mcoding.base.order.service.order.event.OrderAccpectPresentEvent;
import com.mcoding.base.ui.bean.dictionary.DicGroupItem;
import com.mcoding.base.ui.bean.store.Store;
import com.mcoding.base.ui.utils.StoreUtils;
import com.mcoding.base.ui.utils.dictionary.DicGroupItemUtils;
import com.mcoding.base.ui.utils.spring.SpringContextHolder;
import com.mcoding.comp.wechat.account.bean.AccountConfig;
import com.mcoding.comp.wechat.account.utils.WxMpServiceUtils;
import com.mcoding.comp.wechat.common.WxConstant;
import com.mcoding.comp.wechat.pay.bean.WxMpOrder;
import com.mcoding.comp.wechat.pay.utils.WxPaySignUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.pay.request.WxPayUnifiedOrderRequest;
import springfox.documentation.annotations.ApiIgnore;

@Api(value="订单")
@Controller
@RequestMapping("order")
public class OrderController {
	
	private static Logger logger = LoggerFactory.getLogger(OrderController.class);
	
	@Value("#{sysConfig['mode.debug']}")
	private Boolean isDebugMode;
	
    @Resource
    protected OrderService orderService;
    
    @Autowired
    protected ShoppingCartService shoppingCartService;
    
    @Autowired
    protected StoreWxRefService storeWxRefService;
    
    @Autowired
    protected MemberAddressService memberAddressService;
    
    @Autowired
    protected MemberService memberService;
    
    @Autowired
    protected ThreadPoolTaskExecutor threadPool;
    
    @ApiIgnore
    @RequestMapping("service/toAddView")
    public ModelAndView toAddView() {
        return new ModelAndView("order/order/toAddView");
    }

    @ApiIgnore
    @RequestMapping("service/toMainView")
    public ModelAndView toMainView() {
        return new ModelAndView("order/order/toMainView");
    }

    @ApiIgnore
    @RequestMapping("service/toUpdateViewById")
    public ModelAndView toUpdateViewById(int id) {
        ModelAndView view = new ModelAndView();
        Order order = this.orderService.queryObjById(id);
        view.addObject("order", order);
        view.setViewName("order/order/toAddView");
        return view;
    }

    /*@ApiOperation(httpMethod="POST", value="创建订单")
    @RequestMapping("service/create")
    @ResponseBody
        JsonResult<String> result = new JsonResult<>();
Ω        this.orderService.addObj(order);
        result.setData(null);
        result.setMsg("ok");
        result.setStatus("00");
        return result;
    }*/

    /*@ApiOperation(httpMethod="POST", value="编辑订单")
    @RequestMapping("service/edit")
    @ResponseBody
    public JsonResult<String> edit(@RequestBody Order order) {
        if (order.getId() == null || order.getId() <=0) {
            throw new CommonException("id 为空，保存失败");
        }
        JsonResult<String> result = new JsonResult<>();
        this.orderService.modifyObj(order);
        result.setData(null);
        result.setMsg("ok");
        result.setStatus("00");
        return result;
    }*/

    @ApiOperation(httpMethod="POST", value="删除订单")
    @RequestMapping("service/deleteById")
    @ResponseBody
    public JsonResult<String> deleteById(int id) {
        JsonResult<String> result = new JsonResult<>();
        this.orderService.deleteObjById(id);
        result.setData(null);
        result.setMsg("ok");
        result.setStatus("00");
        return result;
    }

    @ApiOperation(httpMethod="GET", value="查询订单")
    @RequestMapping("service/findByPage")
    @ResponseBody
    public PageView<Order> findByPage(
    		@ApiParam(value="分页索引",defaultValue="0") @RequestParam(defaultValue="0") String iDisplayStart, 
    		@ApiParam(value="每页的数量",defaultValue="10") @RequestParam(defaultValue="10") String iDisplayLength, 
    		@ApiParam(value="订单状态") @RequestParam(defaultValue="0") String status,
    		@ApiParam(value="查询条件") String sSearch,
    		@ApiParam(value="订单号") String orderNo,
    		@ApiParam(value="第三方支付号") String tradeNo,
    		@ApiParam(value="会员昵称") String memberNickName,
    		@ApiParam(value="会员收货电话") String addressPhone,
    		@ApiParam(value="快递单号") String deliveryCode,
    		@ApiParam(value="快递单号") String startDate,
    		@ApiParam(value="快递单号") String endDate
    		) throws ParseException {
        PageView<Order> pageView = new PageView<>(iDisplayStart, iDisplayLength);
        OrderExample example = new OrderExample();
        example.setPageView(pageView);
        
        OrderExample.Criteria criteria = example.createCriteria();
        criteria.andStoreIdEqualTo(StoreUtils.getStoreFromThreadLocal().getId());
        if (StringUtils.isNotBlank(status) && StringUtils.isNumeric(status) && !status.equals("0")) {
			criteria.andStatusEqualTo(Integer.valueOf(status));
		}
        
        if (StringUtils.isNotBlank(orderNo)) {
			criteria.andOrderNoLike(orderNo + "%");
		}
        if (StringUtils.isNotBlank(tradeNo)) {
			criteria.andTradeNoLike(tradeNo + "%");
		}
        if (StringUtils.isNotBlank(memberNickName)) {
			criteria.andMemberNickNameLike(memberNickName + "%");
		}
        if (StringUtils.isNotBlank(addressPhone)) {
			criteria.andAddressPhoneLike(addressPhone + "%");
		}
        if (StringUtils.isNotBlank(deliveryCode)) {
			criteria.andDeliveryCodeLike(deliveryCode + "%");
		}
        if (StringUtils.isNotBlank(startDate) && startDate.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}")) {
			criteria.andAddTimeGreaterThanOrEqualTo(DateUtils.parseDate(startDate, new String[]{"yyyy-MM-dd hh:mm:ss"}));
		}
        if (StringUtils.isNotBlank(endDate) && endDate.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}")) {
        	criteria.andAddTimeLessThanOrEqualTo(DateUtils.parseDate(endDate, new String[]{"yyyy-MM-dd hh:mm:ss"}));
        }
        if (StringUtils.isNotBlank(sSearch)) {
        	criteria.andMemberNickNameLike("%"+sSearch+"%");
        }
        return this.orderService.queryObjByPage(example);
    }
    
    
    /**
     * 订单预览，从购物车页面提交订单
     * @param session
     * @return
     */
    @ApiOperation(httpMethod="GET", value="订单预处理(购物车结算)，就是初步匹配规则，并生成订单")
    @RequestMapping("front/orderPreview")
    @ResponseBody
    public JsonResult<Order> orderPreview(HttpSession session){
    	Member member = (Member) session.getAttribute("member");
    	String scene = "metrxmall";
    	
    	ShoppingCartExample example = new ShoppingCartExample();
    	example.createCriteria()
    	       .andMemberIdEqualTo(member.getId())
    	       .andStoreIdEqualTo(StoreUtils.getStoreFromThreadLocal().getId());
    	
    	List<ShoppingCart> shoppingCartList = this.shoppingCartService.findByMember(scene, member.getId(), StoreUtils.getStoreFromThreadLocal().getId());
    	if (CollectionUtils.isEmpty(shoppingCartList)) {
			throw new CommonException("购物车为空，无法提交订单");
		}
    	
    	Order order = this.orderService.preveiw(scene, shoppingCartList, member);
    	
    	JsonResult<Order> result = new JsonResult<>();
    	result.setStatus("00");
    	result.setMsg("ok");
    	result.setData(order);
    	
    	return result;
    }
    
    @ApiOperation(httpMethod="GET", value="提交订单(通过购物车)")
    @RequestMapping("front/submitOrder")
    @ResponseBody
    public JsonResult<Order> submitOrder(
    		@ApiParam(value="是否赠送好友，1是，0否，默认0") @RequestParam(defaultValue="0") int isPresent, 
    		@ApiParam(value="地址id") Integer addressId, HttpSession session){
    	Order order = this.orderPreview(session).getData();
    	
    	if (Constant.YES_INT.equals(isPresent)) {
			order.setPresentStatus(Constant.YES_INT);
			
		}else{
			if (addressId == null) {
				throw new CommonException("请提交收货地址");
			}
			MemberAddress address = this.memberAddressService.queryObjById(addressId);
			
			if (address == null) {
				throw new CommonException("收货地址id不存在，请刷新重试。");
			}
			
			order.setAddressId(addressId);
			order.setAddressRegson(address.getRegson());
			order.setAddressPhone(address.getPhone());
			order.setAddressReveiver(address.getReceiver());
			order.setAddress(address.getAddress());
			order.setPresentStatus(Constant.NO_INT);
		}
    	
    	
    	this.orderService.addOrder(order);
    	
    	JsonResult<Order> result = new JsonResult<>();
    	result.setStatus("00");
    	result.setMsg("ok");
    	result.setData(order);
    	
    	return result;
    }
    
    /**
     * 订单预览，从产品页面，点击立即购买
     * @param productId
     * @param nums
     * @return
     */
    @ApiOperation(httpMethod="GET", value="订单预处理(立即购买)，就是初步匹配规则，并生成订单")
    @RequestMapping("front/orderPreviewByProduct")
    @ResponseBody
    public JsonResult<Order> orderPreviewByProduct(int productId, int nums, HttpSession session){
    	Member member = (Member) session.getAttribute("member");
    	
    	String scene = "metrxmall";
    	Order order = this.orderService.preveiw(scene, productId, nums, member);
    	
    	session.setAttribute("productIdForOrder", productId);
    	session.setAttribute("numsForOrder", nums);
    	
    	JsonResult<Order> result = new JsonResult<>();
    	result.setStatus("00");
    	result.setMsg("ok");
    	result.setData(order);
    	
    	return result;
    }
    
    @ApiOperation(httpMethod="GET", value="提交订单(立即购买)")
    @RequestMapping("front/submitOrderByProduct")
    @ResponseBody
    public JsonResult<Order> submitOrderByProduct(
    		@ApiParam(value="是否赠送好友，1是，0否，默认0") @RequestParam(defaultValue="0") int isPresent, 
    		@ApiParam(value="地址id") Integer addressId, HttpSession session){
    	Integer productId = (Integer) session.getAttribute("productIdForOrder");
    	Integer nums = (Integer) session.getAttribute("numsForOrder");
    	if (productId == null || nums == null) {
			throw new CommonException("系统异常，请重新购买");
		}
    	
    	Order order = this.orderPreviewByProduct(productId, nums, session).getData();
    	
    	if (Constant.YES_INT.equals(isPresent)) {
			order.setPresentStatus(Order.PRESENT_STATUS_SENT);
			
		}else{
			if (addressId == null) {
				throw new CommonException("请提交收货地址");
			}
			
			MemberAddress address = this.memberAddressService.queryObjById(addressId);
			if (address == null) {
				throw new CommonException("收货地址id不存在，请刷新重试。");
			}
			
			order.setAddressId(addressId);
			order.setAddressRegson(address.getRegson());
			order.setAddressPhone(address.getPhone());
			order.setAddressReveiver(address.getReceiver());
			order.setAddress(address.getAddress());
			order.setPresentStatus(Order.PRESENT_STATUS_NONE);
		}
    	
    	this.orderService.addOrder(order);
    	
    	JsonResult<Order> result = new JsonResult<>();
    	result.setStatus("00");
    	result.setMsg("ok");
    	result.setData(order);
    	
    	return result;
    }
    
    @ApiOperation(httpMethod="GET", value="获取订单微信支付的js params")
    @RequestMapping("front/getPayParams")
    @ResponseBody
    public JsonResult<Map<String, String>> getPayParams(int orderId) throws UnknownHostException, IllegalArgumentException, IllegalAccessException, WxErrorException{
    	Order order = this.orderService.queryObjById(orderId);
    	
    	if (order == null) {
			throw new CommonException("订单不存在，请重新查询");
		}
    	
    	Store store = StoreUtils.getStoreFromThreadLocal();
    	AccountConfig accountConfig = this.storeWxRefService.queryWxAccountByStoreId(store.getId());
    	
    	logger.debug("get pay js param:store["+store.getStoreName()+"], wxAccount["+accountConfig.getName()+"]");
    	
    	WxMpService wxMpservice = WxMpServiceUtils.getWxMpServiceByAccount(accountConfig);
    	
    	WxMpOrder wxMpOrder = new WxMpOrder();
    	wxMpOrder.setProductBody(store.getStoreName() + ":购物交易");
    	wxMpOrder.setOutTradeNo(order.getOrderNo());
    	wxMpOrder.setTotalFee(order.getAmountPay());
    	
    	if (isDebugMode != null && isDebugMode) {
    		//如果是调试模式
			wxMpOrder.setTotalFee(1);
		}
    	
    	wxMpOrder.setOpenId(this.memberService.queryObjById(order.getMemberId()).getWxMember().getWxOpenid());
    	
    	WxPayUnifiedOrderRequest outMsg = WxPaySignUtils.fromWxOrder(wxMpOrder);
		outMsg.setDeviceInfo(WxConstant.DEVICE_INFO_WEB);
		outMsg.setTradeType(WxConstant.TRADE_TYPE_JSAPI);
		outMsg.setSpbillCreateIp(InetAddress.getLocalHost().getHostAddress());

		Map<String, String> jsPayParams = wxMpservice.getPayService().getPayInfo(outMsg);
    	
    	JsonResult<Map<String, String>> result = new JsonResult<>();
    	result.setStatus("00");
    	result.setMsg("ok");
    	result.setData(jsPayParams);
    	
    	return result;
    }
    
    @ApiOperation(httpMethod="GET", value="确认收货")
    @RequestMapping("front/confirmReceipt")
    @ResponseBody
    public JsonResult<String> confirmReceipt(int orderId, HttpSession session){
    	Member member = (Member) session.getAttribute("member");
    	
    	Order order = this.orderService.queryObjById(orderId);
    	if (!order.getMemberId().equals(member.getId())) {
			throw new CommonException("确认收货的订单有误，请重新登录后重试。");
		}
    	
    	if(!order.getStatus().equals(Order.PAY_STATUS_SENT)){
    		throw new CommonException("该订单还没有发货，请不要急着确认收货。");
    	}
    	
    	Order tmp = new Order();
    	tmp.setId(order.getId());
    	tmp.setStatus(Order.PAY_STATUS_FINISHED);
    	this.orderService.modifyObj(tmp);
    			
    	JsonResult<String> result = new JsonResult<>();
    	result.setStatus("00");
    	result.setMsg("ok");
    	result.setData("ok");
    	
    	return result;
    }
    
    @ApiOperation(httpMethod="GET", value="取消订单")
    @RequestMapping("front/cancle")
    @ResponseBody
    public JsonResult<String> cancle(int orderId, HttpSession session){
    	Member member = (Member) session.getAttribute("member");
    	
    	Order order = this.orderService.queryObjById(orderId);
    	if (!order.getMemberId().equals(member.getId())) {
    		throw new CommonException("确认收货的订单有误，请重新登录后重试。");
    	}
    	
    	if(!order.getStatus().equals(Order.PAY_STATUS_CREATED)){
    		throw new CommonException("订单在未支付的情况下才能取消。而该订单目前的状态，无法取消订单");
    	}
    	
    	Order tmp = new Order();
    	tmp.setId(order.getId());
    	tmp.setStatus(Order.PAY_STATUS_CANCEL);
    	this.orderService.modifyObj(tmp);
    	
    	JsonResult<String> result = new JsonResult<>();
    	result.setStatus("00");
    	result.setMsg("ok");
    	result.setData("ok");
    	
    	return result;
    }

    @ApiOperation(httpMethod="GET", value="查询当前会员的订单")
    @RequestMapping("front/findByCurrentMember")
    @ResponseBody
    public PageView<Order> findByCurrentMember(
    		@ApiParam(value="分页索引",defaultValue="1") @RequestParam(defaultValue="1") int pageNo,
    		@ApiParam(value="每页的数量",defaultValue="10") @RequestParam(defaultValue="10") int pageSize,
    		@ApiParam(value="订单状态:0所有订单,100未支付,200已支付,300待收货,400已收货,500取消订单,600退换货",defaultValue="0") int orderStatus,
    		HttpSession session){
    	
    	if (!String.valueOf(orderStatus).matches("[123456]00")) {
    		orderStatus = 0;
		}
    	
    	Member member = (Member) session.getAttribute("member");
    	OrderExample orderExample = new OrderExample();
    	OrderExample.Criteria cri = orderExample.createCriteria();
        
    	cri.andMemberIdEqualTo(member.getId())
           .andStoreIdEqualTo(StoreUtils.getStoreFromThreadLocal().getId());
    	
    	if (orderStatus != 0) {
    		cri.andStatusEqualTo(orderStatus);
		}
    	
        orderExample.setOrderByClause("add_time DESC");
        orderExample.setPageView(new PageView<Order>(pageNo, pageSize));
    	
    	return this.orderService.queryObjByPage(orderExample);
    }
    
    /**
     * 查询订单详情
     * @param id
     * @return
     */
    @ApiOperation(httpMethod="GET", value="查询当前会员的订单")
    @RequestMapping(value={"front/findDetailById","service/findDetailById"})
    @ResponseBody
    public JsonResult<Order> findDetailById(int id, HttpSession session){
    	Order order = this.orderService.queryObjById(id);
    	
    	if (order == null) {
			throw new CommonException("订单不存在，请重新查询");
		}
    	
//    	Member member = (Member) session.getAttribute("member");
//    	if (!order.getMemberId().equals(member.getId()) ) {
//    		throw new CommonException("该订单并不属于当前用户，请刷新重试");
//		}
    	
    	JsonResult<Order> result = new JsonResult<>();
    	result.setStatus("00");
    	
    	DicGroupItem item = DicGroupItemUtils.getDicGroupItemByValue("order_pay_status", String.valueOf(order.getStatus()));
    	if (item != null) {
    		result.setMsg(item.getName());
		}
    	result.setData(order);
    	
    	return result;
    }
    
    /**
     * 
     * @param orderId
     * @param addressId
     * @param session
     * @return
     */
    @ApiOperation(httpMethod="GET", value="接受赠送")
    @RequestMapping(value={"front/acceptPresent"})
    @ResponseBody
    public JsonResult<String> acceptPresent(
    		@ApiParam(value="赠送的单子") int orderId, 
    		@ApiParam(value="接受赠送后，填写的地址id") int addressId, HttpSession session){
    	Member member = (Member) session.getAttribute("member");
    	if (member == null) {
			throw new CommonException("请授权登录后重试。");
		}
    	
    	MemberAddress address = this.memberAddressService.queryObjById(addressId);
    	if (address == null) {
    		throw new CommonException("收货地址id不存在，请刷新重试。");
    	}
    	
    	Order order = this.orderService.queryObjById(orderId);
    	if (order == null) {
    		throw new CommonException("订单id不存在，请刷新后重试");
    	}
    	if (Order.PRESENT_STATUS_ACCEPT.equals(order.getPresentStatus())) {
    		throw new CommonException("该订单已经被接收了，请刷新页面");
		}
    	if (Order.PRESENT_STATUS_NONE.equals(order.getPresentStatus())) {
			throw new CommonException("该订单不是赠送订单，请刷新页面重试");
		}
    	if (!Order.PRESENT_STATUS_SENT.equals(order.getPresentStatus())) {
    		throw new CommonException("系统异常，查询不到订单的赠送状态，请联系客服处理");
		}

//    	Order order = new Order();
//    	order.setId(order.getId());
    	order.setPresentStatus(Order.PRESENT_STATUS_ACCEPT);
    	
    	order.setAddressId(addressId);
    	order.setAddressRegson(address.getRegson());
    	order.setAddressPhone(address.getPhone());
    	order.setAddressReveiver(address.getReceiver());
    	order.setAddress(address.getAddress());
		
		this.orderService.modifyObj(order);
		
		SpringContextHolder.getApplicationContext().publishEvent(new OrderAccpectPresentEvent(order));
		
		JsonResult<String> result = new JsonResult<>();
		result.setMsg("ok");
		result.setStatus("00");
		return result;
    }
}