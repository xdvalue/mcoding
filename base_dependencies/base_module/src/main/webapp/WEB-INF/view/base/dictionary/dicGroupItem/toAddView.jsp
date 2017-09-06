<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-reorder"></i>
					<c:choose>
						<c:when test="${dicGroupItem!=null}">修改 ${dicGroup.name }子项</c:when>
						<c:otherwise>添加 ${dicGroup.name } 子项</c:otherwise>
					</c:choose>
				</div>
			</div>
			<div class="portlet-body form">
				<form action="#" id="dicGroupItemForm" class="form-horizontal">
				    <input type="hidden" id="dicGroupId" name="groupId"
						value="${dicGroup.id}" />
					<input type="hidden" id="dicGroupItemId" name="id"
						value="${dicGroupItem.id}" />

					<div class="form-body">
						<div class="form-group">
							<label class="col-md-3 control-label">子项名称 <span
								class="required">*</span>
							</label>
							<div class="col-md-9">
								<input type="text" name="name"
									value="${dicGroupItem.name}"
									class="form-control input-inline input-medium"
									placeholder="请输入名称" required>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">子项编码 <span
								class="required">*</span>
							</label>
							<div class="col-md-4">
								<input type="text" name="code"
									value="${dicGroupItem.code}" class="form-control input-inline input-medium"
									placeholder="字典组子项(字母加下划线)">
								
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">子项的值 <span
								class="required">*</span>
							</label>
							<div class="col-md-4">
								<input type="text" name="value"
									value="${dicGroupItem.value}" class="form-control input-inline input-medium"
									placeholder="字典组子项 值 ">
								
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-md-3 control-label">子项的描述 <span
								class="required">*</span>
							</label>
							<div class="col-md-4">
								<textarea name="description"
									class="form-control input-inline input-medium" rows="3"
									placeholder="字典组子项 描述(最多250个字)">${dicGroupItem.description}</textarea>
							</div>
						</div>

						<div class="form-actions">
							<div class="col-md-offset-3 col-md-9">
								<button
									<c:choose>
									<c:when test="${dicGroupItem!=null}">id="singleUpdate"</c:when>
									<c:otherwise>id="singleAdd"</c:otherwise>
								</c:choose>
									type="button" class="btn purple">
									<i class="fa fa-check"></i> 提 交
								</button>
								<button id="backListPage"
									href="dicGroupItem/service/toListPageView.html?dicGroupId=${dicGroup.id }" type="button"
									class="btn default ajaxify">返 回</button>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
<script src="${basePath}resources/js/custom/base/dictionary/dicGroupItem/add.js"
	type="text/javascript"></script>
<script type="text/javascript">
    DicGroupManager.init();
</script>

