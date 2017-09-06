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
						<c:when test="${actionType == 'U'}">修改标签</c:when>
						<c:when test="${actionType == 'D'}">标签详情</c:when>
						<c:otherwise>添加标签</c:otherwise>
					</c:choose>
				</div>
			</div>
			<div class="portlet-body form">
				<form action="#" id="labelForm" class="form-horizontal">
					<input type="hidden" id="labelId" name="id" value="${label.id}" />
					<div class="form-body">
						<div class="form-group">
							<label class="col-md-3 control-label">
								标签名称 
								<span class="required">*</span>
							</label>
							<div class="col-md-9">
								<input type="text" name="mark" id="mark"
									value="${label.mark}"
									class="form-control input-inline input-medium"
									placeholder="请输入标签名称" required>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">
								标签类型 
								<span class="required">*</span>
							</label>
							<div class="col-md-2">
								<select class="form-control input-inline input-medium" id="type" name="type">
									<option value="">请选择</option>
								</select>
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-md-3 control-label">
								所属公司
								<span class="required">*</span>
							</label>
							<div class="col-md-2">
								<mcoding:dicGroupSelectTag dicGroupCode="store_id"
									id="storeId" name="storeId" selectedItemValue="${label.storeId}" required="true"/>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">
								排序 
								<span class="required">*</span>
							</label>
							<div class="col-md-9">
								<input type="text" name="seqNum" id="seqNum"
									value="${label.seqNum}"
									class="form-control input-inline input-medium"
									required>
							</div>
						</div>
						
						<div class="form-actions">
							<div class="col-md-offset-3 col-md-9">
								<c:if test="${actionType != 'D'}">
									<button
										<c:choose>
										<c:when test="${actionType=='U'}">id="labelUpdate"</c:when>
										<c:otherwise>id="labelAdd"</c:otherwise>
									</c:choose>
										type="button" class="btn purple">
										<i class="fa fa-check"></i> 提 交
									</button>
								</c:if>
								<button id="backLabelListPage" href="label/service/init" type="button" class="btn default ajaxify">返 回</button>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>


<script src="${basePath}resources/js/custom/cms/label/add.js"type="text/javascript"></script>
<script type="text/javascript">
	var actionType = "${actionType}";
	labelManager.init(actionType);
</script>
