<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="mcoding" uri="http://mcoding.cn/jsp/common"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-reorder"></i>
					<c:choose>
						<c:when test="${actionType == 'U'}">修改模板</c:when>
						<c:when test="${actionType == 'D'}">模板详情</c:when>
						<c:otherwise>添加模板</c:otherwise>
					</c:choose>
				</div>
			</div>
			<div class="portlet-body form">
				<form action="#" id="templateForm" class="form-horizontal">
					<input type="hidden" id="templateId" name="id" value="${template.id}" />
					<div class="form-body">
						<div class="form-group">
							<label class="col-md-3 control-label">
								模板名称 
								<span class="required">*</span>
							</label>
							<div class="col-md-9">
								<input type="text" name="templateName" id="templateName"
									value="${template.templateName}"
									class="form-control input-inline input-medium"
									placeholder="请输入模板名称" required>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">
								模板地址 
								<span class="required">*</span>
							</label>
							<div class="col-md-9">
								<input type="text" name="templateUrl" id="templateUrl"
									value="${template.templateUrl}"
									class="form-control input-inline input-medium"
									placeholder="请输入模板地址" required>
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-md-3 control-label">
								所属公司
								<span class="required">*</span>
							</label>
							<div class="col-md-2">
								<mcoding:dicGroupSelectTag dicGroupCode="store_id"
									id="storeId" name="storeId" selectedItemValue="${template.storeId}" required="true"/>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">
								描述
							</label>
							<div class="col-md-9">
								<textarea id="description" class="form-control"  name = "description" style="width: 395px; height: 300px">${module.description}</textarea>
							</div>
						</div>
						
						<div class="form-actions">
							<div class="col-md-offset-3 col-md-9">
								<c:if test="${actionType != 'D'}">
									<button
										<c:choose>
										<c:when test="${actionType=='U'}">id="templateUpdate"</c:when>
										<c:otherwise>id="templateAdd"</c:otherwise>
									</c:choose>
										type="button" class="btn purple">
										<i class="fa fa-check"></i> 提 交
									</button>
								</c:if>
								<button id="backTemplateListPage" href="template/service/init" type="button" class="btn default ajaxify">返 回</button>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>


<script src="${basePath}resources/js/custom/cms/template/add.js"type="text/javascript"></script>
<script type="text/javascript">
	var actionType = "${actionType}";
	templateManager.init(actionType);
</script>
