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
						<c:when test="${orderReturn!=null}">修改退换货单</c:when>
						<c:otherwise>添加退换货单</c:otherwise>
					</c:choose>
				</div>
			</div>
			<div class="portlet-body form">
				<form action="#" id="orderReturnForm" class="form-horizontal">
					<input type="hidden" name="id" value="${orderReturn.id}" />

					<div class="form-body">
						<div class="form-group"> 
						    <label class="col-md-3 control-label">主键						    </label>
						    <div class="col-md-9">
						   	<input type="text" name="id" value="${orderReturn.id}"
						   		class="form-control input-inline input-medium"
						   		placeholder="请输入主键" required>
						    </div>
						</div>
						<div class="form-group"> 
						    <label class="col-md-3 control-label">店铺id						    </label>
						    <div class="col-md-9">
						   	<input type="text" name="storeId" value="${orderReturn.storeId}"
						   		class="form-control input-inline input-medium"
						   		placeholder="请输入店铺id" required>
						    </div>
						</div>
						<div class="form-group"> 
						    <label class="col-md-3 control-label">退换货的订单id						    </label>
						    <div class="col-md-9">
						   	<input type="text" name="orderId" value="${orderReturn.orderId}"
						   		class="form-control input-inline input-medium"
						   		placeholder="请输入退换货的订单id" required>
						    </div>
						</div>
						<div class="form-group"> 
						    <label class="col-md-3 control-label">退换货的订单号						    </label>
						    <div class="col-md-9">
						   	<input type="text" name="orderNo" value="${orderReturn.orderNo}"
						   		class="form-control input-inline input-medium"
						   		placeholder="请输入退换货的订单号" required>
						    </div>
						</div>
						<div class="form-group"> 
						    <label class="col-md-3 control-label">会员id						    </label>
						    <div class="col-md-9">
						   	<input type="text" name="memberId" value="${orderReturn.memberId}"
						   		class="form-control input-inline input-medium"
						   		placeholder="请输入会员id" required>
						    </div>
						</div>
						<div class="form-group"> 
						    <label class="col-md-3 control-label">会员名称						    </label>
						    <div class="col-md-9">
						   	<input type="text" name="memberName" value="${orderReturn.memberName}"
						   		class="form-control input-inline input-medium"
						   		placeholder="请输入会员名称" required>
						    </div>
						</div>
						<div class="form-group"> 
						    <label class="col-md-3 control-label">退换货类型，1换货，2退款						    </label>
						    <div class="col-md-9">
						   	<input type="text" name="returnType" value="${orderReturn.returnType}"
						   		class="form-control input-inline input-medium"
						   		placeholder="请输入退换货类型，1换货，2退款" required>
						    </div>
						</div>
						<div class="form-group"> 
						    <label class="col-md-3 control-label">申请退换货的状态，2申请中，3审核通过						    </label>
						    <div class="col-md-9">
						   	<input type="text" name="returnStatus" value="${orderReturn.returnStatus}"
						   		class="form-control input-inline input-medium"
						   		placeholder="请输入申请退换货的状态，2申请中，3审核通过" required>
						    </div>
						</div>
						<div class="form-group"> 
						    <label class="col-md-3 control-label">退货理由						    </label>
						    <div class="col-md-9">
						   	<input type="text" name="reason" value="${orderReturn.reason}"
						   		class="form-control input-inline input-medium"
						   		placeholder="请输入退货理由" required>
						    </div>
						</div>
						<div class="form-group"> 
						    <label class="col-md-3 control-label">退款总金额						    </label>
						    <div class="col-md-9">
						   	<input type="text" name="fee" value="${orderReturn.fee}"
						   		class="form-control input-inline input-medium"
						   		placeholder="请输入退款总金额" required>
						    </div>
						</div>
						<div class="form-group"> 
						    <label class="col-md-3 control-label">申请时间						    </label>
						    <div class="col-md-9">
						   	<input type="text" name="applyTime" value="${orderReturn.applyTime}"
						   		class="form-control input-inline input-medium"
						   		placeholder="请输入申请时间" required>
						    </div>
						</div>
						<div class="form-group"> 
						    <label class="col-md-3 control-label">审核通过时间						    </label>
						    <div class="col-md-9">
						   	<input type="text" name="auditTime" value="${orderReturn.auditTime}"
						   		class="form-control input-inline input-medium"
						   		placeholder="请输入审核通过时间" required>
						    </div>
						</div>
						<div class="form-group"> 
						    <label class="col-md-3 control-label">退货物流商名称						    </label>
						    <div class="col-md-9">
						   	<input type="text" name="deliveryName" value="${orderReturn.deliveryName}"
						   		class="form-control input-inline input-medium"
						   		placeholder="请输入退货物流商名称" required>
						    </div>
						</div>
						<div class="form-group"> 
						    <label class="col-md-3 control-label">退货物流单号						    </label>
						    <div class="col-md-9">
						   	<input type="text" name="deliveryCode" value="${orderReturn.deliveryCode}"
						   		class="form-control input-inline input-medium"
						   		placeholder="请输入退货物流单号" required>
						    </div>
						</div>
						<div class="form-group"> 
						    <label class="col-md-3 control-label">备注						    </label>
						    <div class="col-md-9">
						   	<input type="text" name="remark" value="${orderReturn.remark}"
						   		class="form-control input-inline input-medium"
						   		placeholder="请输入备注" required>
						    </div>
						</div>

									
						<div class="form-actions">
							<div class="col-md-offset-3 col-md-9">
								<button
									<c:choose>
									<c:when test="${orderReturn!=null}">id="singleUpdate"</c:when>
									<c:otherwise>id="singleAdd"</c:otherwise>
								</c:choose>
									type="button" class="btn purple">
									<i class="fa fa-check"></i> 提 交
								</button>
								<button id="backListPage"
									href="orderReturn/service/toMainView.html" type="button"
									class="btn default ajaxify">返 回</button>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>

<script src="${basePath}resources/js/custom/order/orderReturn/add.js"
	type="text/javascript"></script>
<script type="text/javascript">
    DataTableList.init();
</script>
