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
						<c:when test="${productCategory!=null}">修改产品分类</c:when>
						<c:otherwise>添加产品分类</c:otherwise>
					</c:choose>
				</div>
			</div>
			<div class="portlet-body form">
				<form action="#" id="productCategoryForm" class="form-horizontal">
					<input type="hidden" name="id" value="${productCategory.id}" />
					<input type="hidden" name="storeId" value="${sessionScope.storeId}" />
					<input type="hidden" id="parentIdHidden" value="${productCategory.categoryParentId}" />

					<div class="form-body">
						<div class="form-group">
						    <label class="col-md-3 control-label">类目名称						    </label>
						    <div class="col-md-9">
						   	<input type="text" name="categoryName" value="${productCategory.categoryName}"
						   		class="form-control input-inline input-medium"
						   		placeholder="请输入类目名称" required>
						    </div>
						</div>
						<div class="form-group">
						    <label class="col-md-3 control-label">请选择父类</label>
						    <div class="col-md-9">
								<select  class="form-control input-medium" name="categoryParentId" id="categoryParentId"></select>
						    </div>
						</div>

						<div class="form-actions">
							<div class="col-md-offset-3 col-md-9">
								<button
									<c:choose>
									<c:when test="${productCategory!=null}">id="singleUpdate"</c:when>
									<c:otherwise>id="singleAdd"</c:otherwise>
								</c:choose>
									type="button" class="btn purple">
									<i class="fa fa-check"></i> 提 交
								</button>
								<button id="backListPage"
									href="productCategory/service/toMainView.html" type="button"
									class="btn default ajaxify">返 回</button>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>

<script src="${basePath}resources/js/custom/product/productCategory/add.js"
	type="text/javascript"></script>
<script type="text/javascript">
    DataTableList.init();
</script>
