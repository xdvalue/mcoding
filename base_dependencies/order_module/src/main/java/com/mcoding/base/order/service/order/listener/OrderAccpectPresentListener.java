package com.mcoding.base.order.service.order.listener;

import java.util.Date;

import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.mcoding.base.member.bean.member.Member;
import com.mcoding.base.member.service.member.MemberService;
import com.mcoding.base.member.service.wechat.StoreWxRefService;
import com.mcoding.base.order.bean.order.Order;
import com.mcoding.base.order.bean.order.OrderProduct;
import com.mcoding.base.order.service.order.event.OrderAccpectPresentEvent;
import com.mcoding.base.order.utils.OrderConstant;
import com.mcoding.base.ui.bean.store.Store;
import com.mcoding.base.ui.service.store.StoreService;
import com.mcoding.comp.wechat.account.bean.AccountConfig;
import com.mcoding.comp.wechat.msg.utils.WxMpTemplateMsgUtils;

@Component
public class OrderAccpectPresentListener implements ApplicationListener<OrderAccpectPresentEvent>{

	@Autowired
	protected StoreWxRefService storeWxRefService;
	@Autowired
	protected MemberService memberService;
	@Autowired
	protected StoreService storeService;
	
	@Override
	public void onApplicationEvent(OrderAccpectPresentEvent event) {
		Order order = (Order) event.getSource();
		AccountConfig account = storeWxRefService.queryWxAccountByStoreId(order.getStoreId());
		Member member = memberService.queryObjById(order.getMemberId());
		
		String receiverOpenid = member.getWxMember().getWxOpenid();
		
		Store store = storeService.queryObjById(order.getStoreId());
		String serverName = store.getStoreDomain().split(";")[0];
		if (!serverName.matches(".+?\\/$")) {
			serverName = serverName + "/";
		}
		
		StringBuffer productName = new StringBuffer();
		for(int i=0; i<order.getOrderProductList().size(); i++){
			OrderProduct orderProduct = order.getOrderProductList().get(i);
			productName.append(orderProduct.getProductName()).append(" x").append(orderProduct.getNums());
			productName.append(",");
		}
		
		String url = serverName + "mMall/order_detail.html?orderId=" + order.getId();
		String keyword1 = order.getAddressReveiver();
		String keyword2 = productName.toString();
		String keyword3 = DateFormatUtils.format(new Date(), "yyyy-MM-dd hh:mm");
		String keyword4 = null;
		String keyword5 = null;
		
		try {
			WxMpTemplateMsgUtils.sendWxMpTemplateMessage(account, OrderConstant.WX_TPL_MSG_ORDER_ACCPECT, receiverOpenid, url, 
					keyword1, keyword2, keyword3, keyword4, keyword5);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
