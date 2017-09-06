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
						<c:when test="${dstMemberLevel!=null}">修改会员-分销商关联表</c:when>
						<c:otherwise>添加会员-分销商关联表</c:otherwise>
					</c:choose>
				</div>
			</div>
			<div class="portlet-body form">
				<form action="#" id="dstMemberLevelForm" class="form-horizontal">
					<input type="hidden" name="id" value="${dstMemberLevel.id}" />

					<div class="form-body">
						<div class="form-group"> 
						    <label class="col-md-3 control-label">分销商id						    </label>
						    <div class="col-md-9">
						   	<input type="text" name="memberId" value="${dstMemberLevel.memberId}"
						   		class="form-control input-inline input-medium"
						   		placeholder="请输入分销商id" required>
						    </div>
						</div>
						<div class="form-group"> 
						    <label class="col-md-3 control-label">分销商名称						    </label>
						    <div class="col-md-9">
						   	<input type="text" name="memberName" value="${dstMemberLevel.memberName}"
						   		class="form-control input-inline input-medium"
						   		placeholder="请输入分销商名称" required>
						    </div>
						</div>
						<div class="form-group"> 
						    <label class="col-md-3 control-label">分销商级别id						    </label>
						    <div class="col-md-9">
						   	<input type="text" name="levelId" value="${dstMemberLevel.levelId}"
						   		class="form-control input-inline input-medium"
						   		placeholder="请输入分销商级别id" required>
						    </div>
						</div>
						<div class="form-group"> 
						    <label class="col-md-3 control-label">级别名称						    </label>
						    <div class="col-md-9">
						   	<input type="text" name="levelName" value="${dstMemberLevel.levelName}"
						   		class="form-control input-inline input-medium"
						   		placeholder="请输入级别名称" required>
						    </div>
						</div>
						<div class="form-group"> 
						    <label class="col-md-3 control-label">上级分销商id						    </label>
						    <div class="col-md-9">
						   	<input type="text" name="parentMemberId" value="${dstMemberLevel.parentMemberId}"
						   		class="form-control input-inline input-medium"
						   		placeholder="请输入上级分销商id" required>
						    </div>
						</div>
						<div class="form-group"> 
						    <label class="col-md-3 control-label">上级分销商名称						    </label>
						    <div class="col-md-9">
						   	<input type="text" name="parentMemberName" value="${dstMemberLevel.parentMemberName}"
						   		class="form-control input-inline input-medium"
						   		placeholder="请输入上级分销商名称" required>
						    </div>
						</div>
						<div class="form-group"> 
						    <label class="col-md-3 control-label">是否可用						    </label>
						    <div class="col-md-9">
						   	<input type="text" name="isEnable" value="${dstMemberLevel.isEnable}"
						   		class="form-control input-inline input-medium"
						   		placeholder="请输入是否可用" required>
						    </div>
						</div>

									
						<div class="form-actions">
							<div class="col-md-offset-3 col-md-9">
								<button
									<c:choose>
									<c:when test="${dstMemberLevel!=null}">id="singleUpdate"</c:when>
									<c:otherwise>id="singleAdd"</c:otherwise>
								</c:choose>
									type="button" class="btn purple">
									<i class="fa fa-check"></i> 提 交
								</button>
								<button id="backListPage"
									href="dstMemberLevel/service/toMainView.html" type="button"
									class="btn default ajaxify">返 回</button>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>

<script src="${basePath}resources/js/custom/dst/dstMemberLevel/add.js"
	type="text/javascript"></script>
<script type="text/javascript">
    DataTableList.init();
</script>
