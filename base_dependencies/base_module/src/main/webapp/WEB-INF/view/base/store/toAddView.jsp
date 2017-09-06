<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="mcoding" uri="http://mcoding.cn/jsp/common"%>

<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-reorder"></i>
					<c:choose>
						<c:when test="${store!=null}">修改店铺</c:when>
						<c:otherwise>添加店铺</c:otherwise>
					</c:choose>
				</div>
			</div>
			<div class="portlet-body form">
				<form action="#" id="storeForm" class="form-horizontal">
					<input type="hidden" id="storeId" name="id" value="${store.id}" />

					<div class="form-body">
						<div class="form-group">
							<label class="col-md-3 control-label">店铺名称 <span
								class="required">*</span>
							</label>
							<div class="col-md-9">
								<input type="text" name="storeName" value="${store.storeName}"
									class="form-control input-inline input-medium"
									placeholder="请输入名称" required>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">店铺等级 <span
								class="required">*</span>
							</label>
							<div class="col-md-4">
							    <mcoding:dicGroupSelectTag dicGroupCode="store_grade" 
								  name="gradeId" selectedItemValue="${store.gradeId}" required="true"/>
							</div>
						</div>

						<div class="form-group">
							<label class="col-md-3 control-label">店长id <span
								class="required">*</span>
							</label>
							<div class="col-md-4">
							    <input type="text" name="memberId" value="${store.memberId}"
									class="form-control input-inline input-medium"
									placeholder="店长id">							
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">店长名称 <span
								class="required">*</span>
							</label>
							<div class="col-md-4">
							    <input type="text" name="memberName" value="${store.memberName}"
									class="form-control input-inline input-medium"  readonly="readonly"
									placeholder="店长名称">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">店铺分类 <span
								class="required">*</span>
							</label>
							<div class="col-md-4">
							    <mcoding:dicGroupSelectTag dicGroupCode="store_category" 
								  name="scId" selectedItemValue="${store.scId}"/>
							    <%-- <input type="text" name="scId" value="${store.scId}"
									class="form-control input-inline input-medium"
									placeholder="店铺分类"> --%>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">店铺域名 <span
								class="required">*</span>
							</label>
							<div class="col-md-4">
							    <input type="text" name="storeDomain" value="${store.storeDomain}"
									class="form-control input-inline input-medium"
									placeholder="店铺域名">
							</div>
						</div>


						<div class="form-actions">
							<div class="col-md-offset-3 col-md-9">
								<button
									<c:choose>
									<c:when test="${store!=null}">id="singleUpdate"</c:when>
									<c:otherwise>id="singleAdd"</c:otherwise>
								</c:choose>
									type="button" class="btn purple">
									<i class="fa fa-check"></i> 提 交
								</button>
								<button id="backListPage" href="store/service/toMainView.html"
									type="button" class="btn default ajaxify">返 回</button>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
<script src="${basePath}resources/js/custom/base/store/add.js"
	type="text/javascript"></script>
<script type="text/javascript">
	DatatableManager.init();
</script>

