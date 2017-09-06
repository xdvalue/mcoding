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
						<c:when test="${dicGroup!=null}">修改字典组</c:when>
						<c:otherwise>添加字典组</c:otherwise>
					</c:choose>
				</div>
			</div>
			<div class="portlet-body form">
				<form action="#" id="dicGroupForm" class="form-horizontal">
					<input type="hidden" id="dicGroupId" name="id"
						value="${dicGroup.id}" />

					<div class="form-body">
						<div class="form-group">
							<label class="col-md-3 control-label">字典组名称 <span
								class="required">*</span>
							</label>
							<div class="col-md-9">
								<input type="text" name="name" value="${dicGroup.name}"
									class="form-control input-inline input-medium"
									placeholder="请输入名称" required>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">字典组编码 <span
								class="required">*</span>
							</label>
							<div class="col-md-4">
								<input type="text" name="code" value="${dicGroup.code}"
									class="form-control input-inline input-medium"
									placeholder="字典组编码(字母加下划线)">

							</div>
						</div>

						<div class="form-group">
							<label class="col-md-3 control-label">字典组描述 <span
								class="required">*</span>
							</label>
							<div class="col-md-4">
								<textarea name="description"
									class="form-control input-inline input-medium" rows="3"
									placeholder="字典组描述(最多250个字)">${dicGroup.description}</textarea>
							</div>
						</div>

						<div class="form-actions">
							<div class="col-md-offset-3 col-md-9">
								<button
									<c:choose>
									<c:when test="${dicGroup!=null}">id="singleUpdate"</c:when>
									<c:otherwise>id="singleAdd"</c:otherwise>
								</c:choose>
									type="button" class="btn purple">
									<i class="fa fa-check"></i> 提 交
								</button>
								<button id="backListPage"
									href="dicGroup/service/toListPageView.html" type="button"
									class="btn default ajaxify">返 回</button>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
<script src="${basePath}resources/js/custom/base/dictionary/dicGroup/add.js"
	type="text/javascript"></script>
<script type="text/javascript">
	DicGroupManager.init();
</script>

