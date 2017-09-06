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
						<c:when test="${departmentAddress!=null}">修改公司地址</c:when>
						<c:otherwise>添加公司地址</c:otherwise>
					</c:choose>
				</div>
			</div>
			<div class="portlet-body form">
				<form action="#" id="departmentAddressForm" class="form-horizontal">
					<input type="hidden" name="id" value="${departmentAddress.id}" />

					<div class="form-body">
						<div class="form-group"> 
						    <label class="col-md-3 control-label">地址id						    </label>
						    <div class="col-md-9">
						   	<input type="text" name="id" value="${departmentAddress.id}"
						   		class="form-control input-inline input-medium"
						   		placeholder="请输入地址id" required>
						    </div>
						</div>
						<div class="form-group"> 
						    <label class="col-md-3 control-label">公司id						    </label>
						    <div class="col-md-9">
						   	<input type="text" name="companyId" value="${departmentAddress.companyId}"
						   		class="form-control input-inline input-medium"
						   		placeholder="请输入公司id" required>
						    </div>
						</div>
						<div class="form-group"> 
						    <label class="col-md-3 control-label">收货人姓名						    </label>
						    <div class="col-md-9">
						   	<input type="text" name="receiver" value="${departmentAddress.receiver}"
						   		class="form-control input-inline input-medium"
						   		placeholder="请输入收货人姓名" required>
						    </div>
						</div>
						<div class="form-group"> 
						    <label class="col-md-3 control-label">手机号码						    </label>
						    <div class="col-md-9">
						   	<input type="text" name="phone" value="${departmentAddress.phone}"
						   		class="form-control input-inline input-medium"
						   		placeholder="请输入手机号码" required>
						    </div>
						</div>
						<div class="form-group"> 
						    <label class="col-md-3 control-label">省市县地区 空格隔开						    </label>
						    <div class="col-md-9">
						   	<input type="text" name="regson" value="${departmentAddress.regson}"
						   		class="form-control input-inline input-medium"
						   		placeholder="请输入省市县地区 空格隔开" required>
						    </div>
						</div>
						<div class="form-group"> 
						    <label class="col-md-3 control-label">详细地址						    </label>
						    <div class="col-md-9">
						   	<input type="text" name="address" value="${departmentAddress.address}"
						   		class="form-control input-inline input-medium"
						   		placeholder="请输入详细地址" required>
						    </div>
						</div>
						<div class="form-group"> 
						    <label class="col-md-3 control-label">是否默认地址，0不是，1是						    </label>
						    <div class="col-md-9">
						   	<input type="text" name="isDefault" value="${departmentAddress.isDefault}"
						   		class="form-control input-inline input-medium"
						   		placeholder="请输入是否默认地址，0不是，1是" required>
						    </div>
						</div>

									
						<div class="form-actions">
							<div class="col-md-offset-3 col-md-9">
								<button
									<c:choose>
									<c:when test="${departmentAddress!=null}">id="singleUpdate"</c:when>
									<c:otherwise>id="singleAdd"</c:otherwise>
								</c:choose>
									type="button" class="btn purple">
									<i class="fa fa-check"></i> 提 交
								</button>
								<button id="backListPage"
									href="departmentAddress/service/toMainView.html" type="button"
									class="btn default ajaxify">返 回</button>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>

<script src="${basePath}resources/js/custom/member/departmentAddress/add.js"
	type="text/javascript"></script>
<script type="text/javascript">
    DataTableList.init();
</script>
