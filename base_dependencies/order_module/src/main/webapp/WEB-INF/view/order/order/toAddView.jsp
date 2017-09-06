<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="mcoding" uri="http://mcoding.com/jsp/common"%>

<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-reorder"></i>
					<c:choose>
						<c:when test="${order!=null}">修改订单</c:when>
						<c:otherwise>添加订单</c:otherwise>
					</c:choose>
				</div>
			</div>
			<div class="portlet-body form">
				<form action="#" id="orderForm" class="form-horizontal">
					<input type="hidden" name="id" value="${order.id}" />

					<div class="form-body">
						<div class="form-group"> 
						    <label class="col-md-3 control-label">订单id						    </label>
						    <div class="col-md-9">
						   	<input type="text" name="id" value="${order.id}"
						   		class="form-control input-inline input-medium"
						   		placeholder="请输入订单id" required>
						    </div>
						</div>
						<div class="form-group"> 
						    <label class="col-md-3 control-label">订单总金额						    </label>
						    <div class="col-md-9">
						   	<input type="text" name="amount" value="${order.amount}"
						   		class="form-control input-inline input-medium"
						   		placeholder="请输入订单总金额" required>
						    </div>
						</div>
						<div class="form-group"> 
						    <label class="col-md-3 control-label">店铺id						    </label>
						    <div class="col-md-9">
						   	<input type="text" name="storeId" value="${order.storeId}"
						   		class="form-control input-inline input-medium"
						   		placeholder="请输入店铺id" required>
						    </div>
						</div>
						<div class="form-group"> 
						    <label class="col-md-3 control-label">快递费						    </label>
						    <div class="col-md-9">
						   	<input type="text" name="freight" value="${order.freight}"
						   		class="form-control input-inline input-medium"
						   		placeholder="请输入快递费" required>
						    </div>
						</div>
						<div class="form-group"> 
						    <label class="col-md-3 control-label">商品总数量						    </label>
						    <div class="col-md-9">
						   	<input type="text" name="nums" value="${order.nums}"
						   		class="form-control input-inline input-medium"
						   		placeholder="请输入商品总数量" required>
						    </div>
						</div>
						<div class="form-group"> 
						    <label class="col-md-3 control-label">订单状态 0未支付 1已支付 2待发货 3已发货 4已完成						    </label>
						    <div class="col-md-9">
						   	<input type="text" name="status" value="${order.status}"
						   		class="form-control input-inline input-medium"
						   		placeholder="请输入订单状态 0未支付 1已支付 2待发货 3已发货 4已完成" required>
						    </div>
						</div>
						<div class="form-group"> 
						    <label class="col-md-3 control-label">订单类型 0.微商城默认订单 1.积分商城全额兑换订单 2.积分商城加钱购订单 3.积分翻牌中奖订单						    </label>
						    <div class="col-md-9">
						   	<input type="text" name="type" value="${order.type}"
						   		class="form-control input-inline input-medium"
						   		placeholder="请输入订单类型 0.微商城默认订单 1.积分商城全额兑换订单 2.积分商城加钱购订单 3.积分翻牌中奖订单" required>
						    </div>
						</div>
						<div class="form-group"> 
						    <label class="col-md-3 control-label">支付方式  wechatpay、alipay						    </label>
						    <div class="col-md-9">
						   	<input type="text" name="payType" value="${order.payType}"
						   		class="form-control input-inline input-medium"
						   		placeholder="请输入支付方式  wechatpay、alipay" required>
						    </div>
						</div>
						<div class="form-group"> 
						    <label class="col-md-3 control-label">礼物领取状态 0.待领取 1.已领取						    </label>
						    <div class="col-md-9">
						   	<input type="text" name="presentStatus" value="${order.presentStatus}"
						   		class="form-control input-inline input-medium"
						   		placeholder="请输入礼物领取状态 0.待领取 1.已领取" required>
						    </div>
						</div>
						<div class="form-group"> 
						    <label class="col-md-3 control-label">退换货和退款状态 1退款 2退换货						    </label>
						    <div class="col-md-9">
						   	<input type="text" name="returnStatus" value="${order.returnStatus}"
						   		class="form-control input-inline input-medium"
						   		placeholder="请输入退换货和退款状态 1退款 2退换货" required>
						    </div>
						</div>
						<div class="form-group"> 
						    <label class="col-md-3 control-label">会员ID						    </label>
						    <div class="col-md-9">
						   	<input type="text" name="memberId" value="${order.memberId}"
						   		class="form-control input-inline input-medium"
						   		placeholder="请输入会员ID" required>
						    </div>
						</div>
						<div class="form-group"> 
						    <label class="col-md-3 control-label">会员姓名						    </label>
						    <div class="col-md-9">
						   	<input type="text" name="memberRealName" value="${order.memberRealName}"
						   		class="form-control input-inline input-medium"
						   		placeholder="请输入会员姓名" required>
						    </div>
						</div>
						<div class="form-group"> 
						    <label class="col-md-3 control-label">会员昵称						    </label>
						    <div class="col-md-9">
						   	<input type="text" name="memberNickName" value="${order.memberNickName}"
						   		class="form-control input-inline input-medium"
						   		placeholder="请输入会员昵称" required>
						    </div>
						</div>
						<div class="form-group"> 
						    <label class="col-md-3 control-label">订单编号						    </label>
						    <div class="col-md-9">
						   	<input type="text" name="orderNo" value="${order.orderNo}"
						   		class="form-control input-inline input-medium"
						   		placeholder="请输入订单编号" required>
						    </div>
						</div>
						<div class="form-group"> 
						    <label class="col-md-3 control-label">第三方交易流水号 如：微信支付流水号						    </label>
						    <div class="col-md-9">
						   	<input type="text" name="tradeNo" value="${order.tradeNo}"
						   		class="form-control input-inline input-medium"
						   		placeholder="请输入第三方交易流水号 如：微信支付流水号" required>
						    </div>
						</div>
						<div class="form-group"> 
						    <label class="col-md-3 control-label">卡券id						    </label>
						    <div class="col-md-9">
						   	<input type="text" name="cardId" value="${order.cardId}"
						   		class="form-control input-inline input-medium"
						   		placeholder="请输入卡券id" required>
						    </div>
						</div>
						<div class="form-group"> 
						    <label class="col-md-3 control-label">card_code						    </label>
						    <div class="col-md-9">
						   	<input type="text" name="cardCode" value="${order.cardCode}"
						   		class="form-control input-inline input-medium"
						   		placeholder="请输入card_code" required>
						    </div>
						</div>
						<div class="form-group"> 
						    <label class="col-md-3 control-label">card_type_name						    </label>
						    <div class="col-md-9">
						   	<input type="text" name="cardTypeName" value="${order.cardTypeName}"
						   		class="form-control input-inline input-medium"
						   		placeholder="请输入card_type_name" required>
						    </div>
						</div>
						<div class="form-group"> 
						    <label class="col-md-3 control-label">减免金额						    </label>
						    <div class="col-md-9">
						   	<input type="text" name="amountReduce" value="${order.amountReduce}"
						   		class="form-control input-inline input-medium"
						   		placeholder="请输入减免金额" required>
						    </div>
						</div>
						<div class="form-group"> 
						    <label class="col-md-3 control-label">加钱购金额						    </label>
						    <div class="col-md-9">
						   	<input type="text" name="plusAmount" value="${order.plusAmount}"
						   		class="form-control input-inline input-medium"
						   		placeholder="请输入加钱购金额" required>
						    </div>
						</div>
						<div class="form-group"> 
						    <label class="col-md-3 control-label">优惠规则匹配减免金额						    </label>
						    <div class="col-md-9">
						   	<input type="text" name="preferentialAmount" value="${order.preferentialAmount}"
						   		class="form-control input-inline input-medium"
						   		placeholder="请输入优惠规则匹配减免金额" required>
						    </div>
						</div>
						<div class="form-group"> 
						    <label class="col-md-3 control-label">快递名称						    </label>
						    <div class="col-md-9">
						   	<input type="text" name="deliveryName" value="${order.deliveryName}"
						   		class="form-control input-inline input-medium"
						   		placeholder="请输入快递名称" required>
						    </div>
						</div>
						<div class="form-group"> 
						    <label class="col-md-3 control-label">快递号						    </label>
						    <div class="col-md-9">
						   	<input type="text" name="deliveryCode" value="${order.deliveryCode}"
						   		class="form-control input-inline input-medium"
						   		placeholder="请输入快递号" required>
						    </div>
						</div>
						<div class="form-group"> 
						    <label class="col-md-3 control-label">收货地址ID						    </label>
						    <div class="col-md-9">
						   	<input type="text" name="addressId" value="${order.addressId}"
						   		class="form-control input-inline input-medium"
						   		placeholder="请输入收货地址ID" required>
						    </div>
						</div>
						<div class="form-group"> 
						    <label class="col-md-3 control-label">详细地址						    </label>
						    <div class="col-md-9">
						   	<input type="text" name="address" value="${order.address}"
						   		class="form-control input-inline input-medium"
						   		placeholder="请输入详细地址" required>
						    </div>
						</div>
						<div class="form-group"> 
						    <label class="col-md-3 control-label">手机号码						    </label>
						    <div class="col-md-9">
						   	<input type="text" name="addressPhone" value="${order.addressPhone}"
						   		class="form-control input-inline input-medium"
						   		placeholder="请输入手机号码" required>
						    </div>
						</div>
						<div class="form-group"> 
						    <label class="col-md-3 control-label">收货人姓名						    </label>
						    <div class="col-md-9">
						   	<input type="text" name="addressReveiver" value="${order.addressReveiver}"
						   		class="form-control input-inline input-medium"
						   		placeholder="请输入收货人姓名" required>
						    </div>
						</div>
						<div class="form-group"> 
						    <label class="col-md-3 control-label">收货地址所在省市县地区信息						    </label>
						    <div class="col-md-9">
						   	<input type="text" name="addressRegson" value="${order.addressRegson}"
						   		class="form-control input-inline input-medium"
						   		placeholder="请输入收货地址所在省市县地区信息" required>
						    </div>
						</div>
						<div class="form-group"> 
						    <label class="col-md-3 control-label">推送E3状态 0.无需推送 1.待推送 2.已推送						    </label>
						    <div class="col-md-9">
						   	<input type="text" name="postE3Status" value="${order.postE3Status}"
						   		class="form-control input-inline input-medium"
						   		placeholder="请输入推送E3状态 0.无需推送 1.待推送 2.已推送" required>
						    </div>
						</div>
						<div class="form-group"> 
						    <label class="col-md-3 control-label">佣金统计状态 0.无需统计 1待统计 2已统计						    </label>
						    <div class="col-md-9">
						   	<input type="text" name="incomeStatus" value="${order.incomeStatus}"
						   		class="form-control input-inline input-medium"
						   		placeholder="请输入佣金统计状态 0.无需统计 1待统计 2已统计" required>
						    </div>
						</div>
						<div class="form-group"> 
						    <label class="col-md-3 control-label">下单时间						    </label>
						    <div class="col-md-9">
						   	<input type="text" name="addTime" value="${order.addTime}"
						   		class="form-control input-inline input-medium"
						   		placeholder="请输入下单时间" required>
						    </div>
						</div>
						<div class="form-group"> 
						    <label class="col-md-3 control-label">支付时间						    </label>
						    <div class="col-md-9">
						   	<input type="text" name="payTime" value="${order.payTime}"
						   		class="form-control input-inline input-medium"
						   		placeholder="请输入支付时间" required>
						    </div>
						</div>
						<div class="form-group"> 
						    <label class="col-md-3 control-label">发货时间						    </label>
						    <div class="col-md-9">
						   	<input type="text" name="deliveryTime" value="${order.deliveryTime}"
						   		class="form-control input-inline input-medium"
						   		placeholder="请输入发货时间" required>
						    </div>
						</div>
						<div class="form-group"> 
						    <label class="col-md-3 control-label">确认收货时间						    </label>
						    <div class="col-md-9">
						   	<input type="text" name="receiveTime" value="${order.receiveTime}"
						   		class="form-control input-inline input-medium"
						   		placeholder="请输入确认收货时间" required>
						    </div>
						</div>
						<div class="form-group"> 
						    <label class="col-md-3 control-label">退款时间						    </label>
						    <div class="col-md-9">
						   	<input type="text" name="returnTime" value="${order.returnTime}"
						   		class="form-control input-inline input-medium"
						   		placeholder="请输入退款时间" required>
						    </div>
						</div>

									
						<div class="form-actions">
							<div class="col-md-offset-3 col-md-9">
								<button
									<c:choose>
									<c:when test="${order!=null}">id="singleUpdate"</c:when>
									<c:otherwise>id="singleAdd"</c:otherwise>
								</c:choose>
									type="button" class="btn purple">
									<i class="fa fa-check"></i> 提 交
								</button>
								<button id="backListPage"
									href="order/service/toMainView.html" type="button"
									class="btn default ajaxify">返 回</button>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>

<script src="${basePath}resources/js/custom/order/order/add.js"
	type="text/javascript"></script>
<script type="text/javascript">
    DataTableList.init();
</script>
