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
						<c:when test="${memberDepartment!=null}">修改</c:when>
						<c:otherwise>添加</c:otherwise>
					</c:choose>
				</div>
			</div>
			<div class="portlet-body form">
				<form action="#" id="memberDepartmentForm" class="form-horizontal">
					<input type="hidden" name="id" value="${memberDepartment.id}" />
					<c:choose>
						<c:when test="${parent!=null}">
						    <input type="hidden" name="parentId" value="${parent.id}" />
						    <input type="hidden" name="departmentType" value="200" />
						    <input type="hidden" name="companyName" value="${parent.companyName}" />
						    <input type="hidden" name="companyId" value="${parent.companyId}" />
						</c:when>
						<c:otherwise>
						    <input type="hidden" name="parentId" value="0" />
						    <input type="hidden" name="departmentType" value="100" />
						</c:otherwise>
					</c:choose>

					<div class="form-body">
						<div class="form-group"> 
						    <label class="col-md-3 control-label">名称<span
						   	class="required">*</span> 
						    </label>
						    <div class="col-md-9">
						   	<input type="text" name="departmentName" value="${memberDepartment.departmentName}"
						   		class="form-control input-inline input-medium"
						   		placeholder="请输入名称" required>
						    </div>
						</div>
						<c:if test="${parent==null}">
						<div class="form-group"> 
						    <label class="col-md-3 control-label">总裁<span class="required">*</span> 
						    </label>
						    <div class="col-md-9">
						   	<input type="text" name="president" value="${memberDepartment.president}"
						   		class="form-control input-inline input-medium"
						   		placeholder="请输入名称" required>
						    </div>
						</div>
						<div class="form-group"> 
						    <label class="col-md-3 control-label">总裁电话<span class="required">*</span> 
						    </label>
						    <div class="col-md-9">
						   	<input type="text" name="presidentTel" value="${memberDepartment.presidentTel}"
						   		class="form-control input-inline input-medium"
						   		placeholder="请输入名称" required>
						    </div>
						</div>
						<div class="form-group"> 
						    <label class="col-md-3 control-label">助理<span class="required">*</span> 
						    </label>
						    <div class="col-md-9">
						   	<input type="text" name="assistant" value="${memberDepartment.assistant}"
						   		class="form-control input-inline input-medium"
						   		placeholder="请输入名称" required>
						    </div>
						</div>
						<div class="form-group"> 
						    <label class="col-md-3 control-label">助理电话<span class="required">*</span> 
						    </label>
						    <div class="col-md-9">
						   	<input type="text" name="assistantTel" value="${memberDepartment.assistantTel}"
						   		class="form-control input-inline input-medium"
						   		placeholder="请输入名称" required>
						    </div>
						</div>
						</c:if>
						<div class="form-group"> 
						    <label class="col-md-3 control-label">显示的logoUrl
						    </label>
						    <div class="col-md-4">
								<div class="input-group">
									<input type="text" name="logoImgUrl" id="imgUrlInput" value="${memberDepartment.logoImgUrl}"
										class="form-control input-inline input-medium" placeholder="logo "
										readonly="readonly" /> <span class="input-group-btn">
										<button class="btn blue" id="imgUrlButton" type="button">选择图片</button>
									</span>
								</div>
							</div>
						</div>

									
						<div class="form-actions">
							<div class="col-md-offset-3 col-md-9">
								<button
									<c:choose>
									<c:when test="${memberDepartment!=null}">id="singleUpdate"</c:when>
									<c:otherwise>id="singleAdd"</c:otherwise>
								</c:choose>
									type="button" class="btn purple">
									<i class="fa fa-check"></i> 提 交
								</button>
								<button id="backListPage" type="button" class="btn default ajaxify"
								<c:choose>
						            <c:when test="${parent==null}"> href="memberDepartment/service/toMainView.html" </c:when>
						            <c:otherwise> href="memberDepartment/service/toDepartmentListView?parentId=${parent.id }" </c:otherwise>
					            </c:choose>
								>返 回</button>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript"
	src="${basePath}resources/js/common/kindeditor.js"></script>
<script src="${basePath}resources/js/custom/member/memberDepartment/add.js"
	type="text/javascript"></script>
<script type="text/javascript">
    DataTableList.init();
</script>
