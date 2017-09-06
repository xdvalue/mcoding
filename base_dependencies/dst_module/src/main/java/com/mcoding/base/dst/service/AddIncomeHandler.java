package com.mcoding.base.dst.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.mcoding.base.dst.bean.income.DstIncomeOrder;
import com.mcoding.base.dst.bean.income.DstIncomeOrderProduct;
import com.mcoding.base.dst.bean.income.DstIncomeProduct;
import com.mcoding.base.dst.bean.level.DstMemberLevel;
import com.mcoding.base.dst.service.income.DstIncomeOrderService;
import com.mcoding.base.dst.service.income.DstIncomeProductService;
import com.mcoding.base.dst.service.level.DstMemberLevelService;
import com.mcoding.base.member.bean.member.Member;
import com.mcoding.base.member.service.member.MemberService;
import com.mcoding.base.member.service.wechat.StoreWxRefService;
import com.mcoding.base.order.bean.order.Order;
import com.mcoding.base.order.bean.order.OrderProduct;
import com.mcoding.base.order.service.order.event.OrderPaidSuccessEvent;

@Component
public class AddIncomeHandler implements ApplicationListener<OrderPaidSuccessEvent>{

	@Autowired
	protected MemberService memberService;

	@Autowired
	protected StoreWxRefService storeWxRefService;

	@Resource
	protected DstMemberLevelService dstMemberLevelService;
	
	@Resource
	protected DstIncomeProductService dstIncomeProductService;
	
	@Resource
	protected DstIncomeOrderService dstIncomeOrderService;
	
	@Transactional
	@Override
	public void onApplicationEvent(OrderPaidSuccessEvent event) {
		Order order = (Order) event.getSource();
		
		//1、找到会员所在的上级
		Member distributor = null;
		DstMemberLevel distributorLevel = this.dstMemberLevelService.queryByMemberId(order.getMemberId());
		
		if (distributorLevel.getLevelId() != null) {
			//如果下单人是分销商，就从下单人开始
			distributor = this.memberService.queryObjById(order.getMemberId());
		}else{
			//如果下单人不是分销商，但是上级是，那就从上级开始
			distributor = this.dstMemberLevelService.queryParentDistributor(order.getMemberId());
			if (distributor != null) {
				distributorLevel = this.dstMemberLevelService.queryByMemberId(distributor.getId());
			}
		}
		
		List<DstIncomeOrder> incomeOrderList = new ArrayList<>();
		while (distributor != null) {
			//2、找到分销商的级别
			if (distributorLevel == null) {
				break;
			}
			
			List<DstIncomeOrderProduct> incomeOrderProducts = new ArrayList<>();
			for(OrderProduct product: order.getOrderProductList()){
				//3、找到订单产品，找到该经销商级别，该产品的佣金
				DstIncomeProduct incomeProduct = this.dstIncomeProductService.queryByLevelId(distributorLevel.getLevelId(), product.getProductId());
				
				DstIncomeOrderProduct incomeOrderProduct = new DstIncomeOrderProduct();
				incomeOrderProduct.setOrderId(order.getId());
				incomeOrderProduct.setMemberId(distributor.getId());
				incomeOrderProduct.setMemberName(distributor.getName());
				incomeOrderProduct.setLevelId(distributorLevel.getLevelId());
				incomeOrderProduct.setLevelName(distributorLevel.getLevelName());
				incomeOrderProduct.setProductId(product.getProductId());
				incomeOrderProduct.setProductName(product.getProductName());
				incomeOrderProduct.setProductCount(product.getNums());
				
				if (incomeProduct == null || incomeProduct.getIncome() == null || incomeProduct.getIncome() <= 0) {
					incomeOrderProduct.setIncomeTotal(0);
				}else{
					incomeOrderProduct.setIncomeTotal(incomeProduct.getIncome() * product.getNums());
				}
				
				if (incomeProduct == null || incomeProduct.getIncome() == null || incomeProduct.getIncome() <= 0) {
					incomeOrderProduct.setPointTotal(0);
				}else{
					incomeOrderProduct.setPointTotal(incomeProduct.getPoint() * product.getNums());
				}
				incomeOrderProducts.add(incomeOrderProduct);
			}
			
			//4、添加佣金记录
			DstIncomeOrder incomeOrder = this.dstIncomeOrderService.addIncome(order, incomeOrderProducts, distributor);
			incomeOrderList.add(incomeOrder);
			
			//6、找到下一个上级，继续统计
			distributor = this.dstMemberLevelService.queryParentDistributor(distributor.getId());
            //7、找到下一个上级的分销商级别
			if (distributor != null) {
				distributorLevel = this.dstMemberLevelService.queryByMemberId(distributor.getId());
			} 
		}
		
		//TODO 发送模板消息
		if (CollectionUtils.isEmpty(incomeOrderList)) {
			return;
		}
		
		/*String keyword1 = "";
		String keyword2 = "";
		String keyword3 = "";
		String keyword4 = "";
		String keyword5 = "";
		String templateType = "";
		String url = "";
		
		AccountConfig account = storeWxRefService.queryWxAccountByStoreId(order.getStoreId());
		for(DstIncomeOrder incomeOrder : incomeOrderList){
			Member receiver = this.memberService.queryObjById(incomeOrder.getMemberId());
			String receiverOpenid = receiver.getWxMember().getWxAccountOriginId();
			
			try {
				WxMpTemplateMsgUtils.sendWxMpTemplateMessage(account, templateType, receiverOpenid, url, keyword1, keyword2, keyword3, keyword4, keyword5);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}*/
	}

}
