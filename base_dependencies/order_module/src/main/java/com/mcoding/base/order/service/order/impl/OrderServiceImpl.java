package com.mcoding.base.order.service.order.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.mcoding.base.core.CommonException;
import com.mcoding.base.core.Constant;
import com.mcoding.base.core.PageView;
import com.mcoding.base.member.bean.member.Member;
import com.mcoding.base.order.bean.cart.ShoppingCart;
import com.mcoding.base.order.bean.delive.OrderDeliveInfo;
import com.mcoding.base.order.bean.order.Order;
import com.mcoding.base.order.bean.order.OrderExample;
import com.mcoding.base.order.bean.order.OrderProduct;
import com.mcoding.base.order.persistence.order.OrderMapper;
import com.mcoding.base.order.persistence.order.OrderProductMapper;
import com.mcoding.base.order.service.order.OrderService;
import com.mcoding.base.order.service.order.event.OrderAddedSuccessEvent;
import com.mcoding.base.order.service.order.event.OrderPaidSuccessEvent;
import com.mcoding.base.order.service.order.event.OrderSentSuccessEvent;
import com.mcoding.base.product.bean.product.Product;
import com.mcoding.base.product.service.product.ProductService;
import com.mcoding.base.ui.utils.StoreUtils;
import com.mcoding.base.ui.utils.spring.SpringContextHolder;
import com.mcoding.comp.wechat.pay.bean.WxMpXmlInOrderQueryMessage;
import com.mcoding.comp.wechat.pay.service.WechatPayNotifyService;

@Service("orderService")
public class OrderServiceImpl implements OrderService, WechatPayNotifyService {
	
	private static Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);
	
    @Resource
    protected OrderMapper orderMapper;
    
    @Autowired
    protected OrderProductMapper orderProductMapper;
    
    @Autowired
    protected ProductService productService;

    @CacheEvict(value={"order"}, allEntries=true)
    @Override
    public void deleteObjById(int id) {
        this.orderMapper.deleteByPrimaryKey(id);
    }

    @CacheEvict(value={"order"}, allEntries=true)
    @Override
    public void modifyObj(Order t) {
        if (t.getId() == null || t.getId() ==0) {
            throw new NullPointerException("id 为空，无法更新");
        }
        this.orderMapper.updateByPrimaryKeySelective(t);
    }

    @Cacheable(value="order", key="'OrderService_' + #root.methodName + '_' +#id")
    @Override
    public Order queryObjById(int id) {
        return this.orderMapper.selectByPrimaryKey(id);
    }

    @Cacheable(value="order", key="'OrderService_' + #root.methodName + '_'+ #example.toJson()")
    @Override
    public List<Order> queryAllObjByExample(OrderExample example) {
        return this.orderMapper.selectByExample(example);
    }

    @Cacheable(value="order", key="'OrderService_' + #root.methodName + '_'+ #example.toJson()")
    @Override
    public PageView<Order> queryObjByPage(OrderExample example) {
        PageView<Order> pageView = example.getPageView();
        if (pageView == null) {
            pageView = new PageView<>(1, 10);
            example.setPageView(pageView);
        }
        pageView.setQueryResult(this.orderMapper.selectByExampleByPage(example));
        return pageView;
    }

	@Override
	public Order preveiw(String scene, List<ShoppingCart> shopCartList, Member member) {
		Order order = new Order();
		order.setMemberId(member.getId());
		order.setMemberNickName(member.getName());
		order.setMemberRealName(member.getTrueName());
		order.setStoreId(StoreUtils.getStoreFromThreadLocal().getId());

		int amountTotal = 0;
		int nums = 0;
		List<OrderProduct> orderProductList = new ArrayList<>();
		for(int i=0; CollectionUtils.isNotEmpty(shopCartList) && i<shopCartList.size(); i++ ){
			Product product = shopCartList.get(i).getProduct();
			if (product == null) {
				throw new CommonException("产品不存在，请重新购买");
			}
			if(!Constant.YES_INT.equals(product.getSaleStatus())){
				String productName = StringUtils.defaultIfBlank(product.getProductName(), "");
				throw new CommonException("产品["+productName+"]已经下架，请重新购买");
				
			}
			
			int prize = product.getPriceList().get(0).getValue();
			nums = nums + shopCartList.get(i).getNums();
			amountTotal = amountTotal + prize * shopCartList.get(i).getNums();
			
			OrderProduct orderProduct = new OrderProduct();
			orderProduct.setNums(shopCartList.get(i).getNums());
			orderProduct.setProductId(product.getId());
			orderProduct.setProductName(product.getProductName());
			orderProduct.setProductImg(product.getCoverImg());
			orderProduct.setProductPrice(prize);
			orderProduct.setStoreId(StoreUtils.getStoreFromThreadLocal().getId());
			orderProductList.add(orderProduct);
		}
		
		order.setAmountTotal(amountTotal);
		order.setAmountPay(amountTotal);
		order.setAmountReduce(0);
		order.setNums(nums);
		order.setOrderProductList(orderProductList);
		
		order.setFreight(Order.DEFALUT_FREIGHT);
		order.setDeliveryName("顺丰");
		
		return order;
	}
	
	@Override
	public Order preveiw(String scene, int productId, int nums, Member member) {
		Product product = this.productService.queryProductDetailById(scene, productId);
		if (product == null) {
			throw new CommonException("产品不存在，请重新购买");
		}
		String productName = StringUtils.defaultIfBlank(product.getProductName(), "");
		if(!Constant.YES_INT.equals(product.getSaleStatus())){
			throw new CommonException("产品["+productName+"]已经下架，请重新购买");
		}
		if (CollectionUtils.isEmpty(product.getPriceList())) {
			throw new CommonException("产品["+productName+"]还没有标价，无法放入购物喝");
		}
		int prize = product.getPriceList().get(0).getValue();
		
		Order order = new Order();
		order.setMemberId(member.getId());
		order.setMemberNickName(member.getName());
		order.setMemberRealName(member.getTrueName());
		order.setStoreId(StoreUtils.getStoreFromThreadLocal().getId());
		int amountTotal = prize * nums;
		
		order.setAmountTotal(amountTotal);
		order.setAmountPay(amountTotal);
		order.setAmountReduce(0);
		order.setNums(nums);
		order.setFreight(0); //默认包邮
		
//		order.setFreight(Order.DEFALUT_FREIGHT);
//		order.setDeliveryName("顺丰");
		
		List<OrderProduct> orderProductList = new ArrayList<>();
		OrderProduct orderProduct = new OrderProduct();
		orderProduct.setNums(nums);
		orderProduct.setProductId(product.getId());
		orderProduct.setProductName(product.getProductName());
		orderProduct.setProductImg(product.getCoverImg());
		orderProduct.setProductPrice(prize);
		orderProduct.setStoreId(StoreUtils.getStoreFromThreadLocal().getId());
		orderProductList.add(orderProduct);
		order.setOrderProductList(orderProductList);
		
		return order;
	}

	@CacheEvict(value={"order"}, allEntries=true)
	@Override
	public void addOrder(Order order) {
		Date now = new Date();
		order.setPayType(Order.PAY_TYPE_WECHAT);
		order.setStatus(Order.PAY_STATUS_CREATED);
		order.setAddTime(now);
		order.setOrderNo(DateFormatUtils.format(now, "yyyyMMddHHmmssSSS") + "_" + StringUtils.leftPad(String.valueOf(RandomUtils.nextInt(1000)), 4, "0"));
		
		this.orderMapper.insertSelective(order);
		for(int i=0; i<order.getOrderProductList().size(); i++){
			OrderProduct orderProduct = order.getOrderProductList().get(i);
			orderProduct.setOrderId(order.getId());
			this.orderProductMapper.insertSelective(orderProduct);
		}
		
		//触发添加成功的事件
		SpringContextHolder.getApplicationContext().publishEvent(new OrderAddedSuccessEvent(order));
	}

	/**
	 * 处理微信的订单回调，并更新订单状态为已支付
	 */
	@Override
	public void handlePaidNotifyMsg(WxMpXmlInOrderQueryMessage inMsg) {
		OrderExample orderExample = new OrderExample();
		orderExample.createCriteria().andOrderNoEqualTo(inMsg.getOutTradeNo());
		
		OrderService orderservice = SpringContextHolder.getOneBean(OrderService.class);
		List<Order> list = orderservice.queryAllObjByExample(orderExample);
		if (CollectionUtils.isEmpty(list)) {
			throw new NullPointerException("数据库不存在单号为["+inMsg.getOutTradeNo()+"]的单子，回调失败。");
		}
		
		if (list.size() > 1) {
			throw new IllegalArgumentException("数据库存在多个单号为["+inMsg.getOutTradeNo()+"]的单子，回调失败。");
		}
		
		if (Order.PAY_STATUS_PAID.equals(list.get(0).getStatus())) {
			return;
		}
		
		Order tmp = new Order();
		tmp.setStatus(Order.PAY_STATUS_PAID);
		tmp.setPayTime(new Date());
		tmp.setTradeNo(inMsg.getTransactionId());
		tmp.setId(list.get(0).getId());
		
		orderservice.modifyObj(tmp);
		
		//触发添加成功的事件
		OrderPaidSuccessEvent event = new OrderPaidSuccessEvent(this.queryObjById(tmp.getId()));
		SpringContextHolder.getApplicationContext().publishEvent(event);
	}

	@CacheEvict(value={"order"}, allEntries=true)
	@Override
	public void deliver(Order order, String deliveryName, String deliverCode) {
//		Order tmpOrder = new Order();
//		tmpOrder.setId(order.getId());
		order.setDeliveryName(deliveryName);
		order.setDeliveryCode(deliverCode);
		order.setStatus(Order.PAY_STATUS_SENT);
		
		this.modifyObj(order);
		
		SpringContextHolder.getApplicationContext().publishEvent(new OrderSentSuccessEvent(order)); 
	}

	@CacheEvict(value={"order"}, allEntries=true)
	@Override
	public void deliver(List<OrderDeliveInfo> deliveInfoList) {
		if (CollectionUtils.isEmpty(deliveInfoList)) {
			return;
		}
		
		for(int i=0; i<deliveInfoList.size(); i++){
			OrderDeliveInfo deliveInfo = deliveInfoList.get(i);
			String orderNo = deliveInfo.getOrderNo();
			if (StringUtils.isBlank(orderNo)) {
				logger.warn("订单发货异常：订单号为空");
				continue;
			}

			OrderExample example = new OrderExample();
			example.createCriteria().andOrderNoEqualTo(orderNo);
			
			List<Order> list = this.orderMapper.selectByExample(example);
			if (CollectionUtils.isEmpty(list)) {
				logger.warn("订单发货异常：订单号["+orderNo+"],找不到该订单");
				continue;
			}
			
			this.deliver(list.get(0), deliveInfo.getDeliverName(), deliveInfo.getDeliverCode());
			
//			Order tmp
			
//			Order order = new Order();
//			order.setDeliveryName(deliveInfoList.get(i).getDeliverName());
//			order.setDeliveryCode(deliveInfoList.get(i).getDeliverCode());
//			order.setStatus(Order.PAY_STATUS_SENT);
			
//			this.orderMapper.updateByExampleSelective(order, example);
		}
	}


}