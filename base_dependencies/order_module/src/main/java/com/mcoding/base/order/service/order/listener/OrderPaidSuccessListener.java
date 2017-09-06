package com.mcoding.base.order.service.order.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.mcoding.base.member.bean.member.Member;
import com.mcoding.base.member.service.member.MemberService;
import com.mcoding.base.member.service.wechat.StoreWxRefService;
import com.mcoding.base.order.bean.order.Order;
import com.mcoding.base.order.service.order.event.OrderPaidSuccessEvent;
import com.mcoding.base.order.utils.OrderConstant;
import com.mcoding.base.ui.bean.store.Store;
import com.mcoding.base.ui.service.store.StoreService;
import com.mcoding.comp.wechat.account.bean.AccountConfig;
import com.mcoding.comp.wechat.msg.utils.WxMpTemplateMsgUtils;

@Component
public class OrderPaidSuccessListener implements ApplicationListener<OrderPaidSuccessEvent> {
	
	@Autowired
	protected StoreWxRefService storeWxRefService;
	@Autowired
	protected MemberService memberService;
	@Autowired
	protected StoreService storeService;

	@Override
	public void onApplicationEvent(OrderPaidSuccessEvent event) {
		
		Order order = (Order) event.getSource();
		AccountConfig account = storeWxRefService.queryWxAccountByStoreId(order.getStoreId());
		Member member = memberService.queryObjById(order.getMemberId());
		
		String receiverOpenid = member.getWxMember().getWxOpenid();
		
		Store store = storeService.queryObjById(order.getStoreId());
		String serverName = store.getStoreDomain().split(";")[0];
		if (!serverName.matches(".+?\\/$")) {
			serverName = serverName + "/";
		}
		
		StringBuffer addressBuffer = new StringBuffer();
		if (Order.PRESENT_STATUS_SENT.equals(order.getPresentStatus())) {
			addressBuffer.append("待礼品接收人填写");
		}else{
			addressBuffer.append(order.getAddressRegson())
			             .append(order.getAddress()).append(",")
			             .append(order.getAddressReveiver()).append(",")
			             .append(order.getAddressPhone());
		}
		
		String url = serverName + "mMall/order_detail.html?orderId=" + order.getId();
		String keyword1 = String.valueOf(order.getAmountPay() / 100.0) + "元";
		String keyword2 = "点击后查看详情";
		String keyword3 = addressBuffer.toString();
		String keyword4 = order.getOrderNo();
		String keyword5 = null;
		
		try {
//			WxMpTemplateMsgUtils.sendWxMpTemplateMessage(account, OrderConstant.WX_TPL_MSG_ORDER_PAID, receiverOpenid, 
//					first, remark, url, color, keyword1, keyword2, keyword3, keyword4, keyword5);
			WxMpTemplateMsgUtils.sendWxMpTemplateMessage(account, OrderConstant.WX_TPL_MSG_ORDER_PAID, receiverOpenid, url, 
					keyword1, keyword2, keyword3, keyword4, keyword5);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
