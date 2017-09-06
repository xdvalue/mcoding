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
						<c:when test="${orderProduct!=null}">修改订单--商品--关联</c:when>
						<c:otherwise>添加订单--商品--关联</c:otherwise>
					</c:choose>
				</div>
			</div>
			<div class="portlet-body form">
				<form action="#" id="orderProductForm" class="form-horizontal">
					<input type="hidden" name="id" value="${orderProduct.id}" />

					<div class="form-body">
						<div class="form-group"> 
						    <label class="col-md-3 control-label">订单商品关联ID						    </label>
						    <div class="col-md-9">
						   	<input type="text" name="id" value="${orderProduct.id}"
						   		class="form-control input-inline input-medium"
						   		placeholder="请输入订单商品关联ID" required>
						    </div>
						</div>
						<div class="form-group"> 
						    <label class="col-md-3 control-label">订单id						    </label>
						    <div class="col-md-9">
						   	<input type="text" name="orderId" value="${orderProduct.orderId}"
						   		class="form-control input-inline input-medium"
						   		placeholder="请输入订单id" required>
						    </div>
						</div>
						<div class="form-group"> 
						    <label class="col-md-3 control-label">商品id						    </label>
						    <div class="col-md-9">
						   	<input type="text" name="productId" value="${orderProduct.productId}"
						   		class="form-control input-inline input-medium"
						   		placeholder="请输入商品id" required>
						    </div>
						</div>
						<div class="form-group"> 
						    <label class="col-md-3 control-label">商品名称						    </label>
						    <div class="col-md-9">
						   	<input type="text" name="productName" value="${orderProduct.productName}"
						   		class="form-control input-inline input-medium"
						   		placeholder="请输入商品名称" required>
						    </div>
						</div>
						<div class="form-group"> 
						    <label class="col-md-3 control-label">商品单价或加钱购金额						    </label>
						    <div class="col-md-9">
						   	<input type="text" name="productPrice" value="${orderProduct.productPrice}"
						   		class="form-control input-inline input-medium"
						   		placeholder="请输入商品单价或加钱购金额" required>
						    </div>
						</div>
						<div class="form-group"> 
						    <label class="col-md-3 control-label">单个商品数量						    </label>
						    <div class="col-md-9">
						   	<input type="text" name="nums" value="${orderProduct.nums}"
						   		class="form-control input-inline input-medium"
						   		placeholder="请输入单个商品数量" required>
						    </div>
						</div>
						<div class="form-group"> 
						    <label class="col-md-3 control-label">加钱购积分						    </label>
						    <div class="col-md-9">
						   	<input type="text" name="plusPoint" value="${orderProduct.plusPoint}"
						   		class="form-control input-inline input-medium"
						   		placeholder="请输入加钱购积分" required>
						    </div>
						</div>

									
						<div class="form-actions">
							<div class="col-md-offset-3 col-md-9">
								<button
									<c:choose>
									<c:when test="${orderProduct!=null}">id="singleUpdate"</c:when>
									<c:otherwise>id="singleAdd"</c:otherwise>
								</c:choose>
									type="button" class="btn purple">
									<i class="fa fa-check"></i> 提 交
								</button>
								<button id="backListPage"
									href="orderProduct/service/toMainView.html" type="button"
									class="btn default ajaxify">返 回</button>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>

<script src="${basePath}resources/js/custom/order/orderProduct/add.js"
	type="text/javascript"></script>
<script type="text/javascript">
    DataTableList.init();
</script>
