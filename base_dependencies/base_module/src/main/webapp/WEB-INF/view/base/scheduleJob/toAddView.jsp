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
						<c:when test="${scheduleJob!=null}">修改定时任务</c:when>
						<c:otherwise>添加定时任务</c:otherwise>
					</c:choose>
				</div>
			</div>
			<div class="portlet-body form">
				<form action="#" id="scheduleJobForm" class="form-horizontal">
					<input type="hidden" name="id" value="${scheduleJob.id}" />

					<div class="form-body">
						<div class="form-group"> 
						    <label class="col-md-3 control-label">名称						    </label>
						    <div class="col-md-9">
						   	<input type="text" name="jobName" value="${scheduleJob.jobName}"
						   		class="form-control input-inline input-medium"
						   		placeholder="请输入定时任务名称" required>
						    </div>
						</div>
						<div class="form-group"> 
						    <label class="col-md-3 control-label">job唯一代码						    </label>
						    <div class="col-md-9">
						   	<input type="text" name="jobCode" value="${scheduleJob.jobCode}"
						   		class="form-control input-inline input-medium"
						   		placeholder="请输入job唯一代码" required>
						    </div>
						</div>
						<div class="form-group"> 
						    <label class="col-md-3 control-label">描述						    </label>
						    <div class="col-md-9">
						   	<input type="text" name="description" value="${scheduleJob.description}"
						   		class="form-control input-inline input-medium"
						   		placeholder="请输入description" required>
						    </div>
						</div>
						<div class="form-group"> 
						    <label class="col-md-3 control-label">完整类名称						    </label>
						    <div class="col-md-9">
						   	<input type="text" name="jobClass" value="${scheduleJob.jobClass}"
						   		class="form-control input-inline input-medium"
						   		placeholder="请输入定时任务完整类名称" required>
						    </div>
						</div>
						<div class="form-group"> 
						    <label class="col-md-3 control-label">任务方法名称						    </label>
						    <div class="col-md-9">
						   	<input type="text" name="jobMethod" value="${scheduleJob.jobMethod}"
						   		class="form-control input-inline input-medium"
						   		placeholder="请输入定时任务方法名称" required>
						    </div>
						</div>
						<div class="form-group"> 
						    <label class="col-md-3 control-label">定时任务的Corn表达式						    </label>
						    <div class="col-md-9">
						   	<input type="text" name="cronExpression" value="${scheduleJob.cronExpression}"
						   		class="form-control input-inline input-medium"
						   		placeholder="请输入定时任务的Corn表达式" required>
						    </div>
						</div>
						<div class="form-group"> 
						    <label class="col-md-3 control-label">能否并发运行，1可以，0不可以						    </label>
						    <div class="col-md-9">
						   	<input type="text" name="isConcurrent" value="${scheduleJob.isConcurrent}"
						   		class="form-control input-inline input-medium"
						   		placeholder="请输入能否并发运行，1可以，0不可以" required>
						    </div>
						</div>
									
						<div class="form-actions">
							<div class="col-md-offset-3 col-md-9">
								<button
									<c:choose>
									<c:when test="${scheduleJob!=null}">id="singleUpdate"</c:when>
									<c:otherwise>id="singleAdd"</c:otherwise>
								</c:choose>
									type="button" class="btn purple">
									<i class="fa fa-check"></i> 提 交
								</button>
								<button id="backListPage"
									href="scheduleJob/service/toMainView.html" type="button"
									class="btn default ajaxify">返 回</button>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>

<script src="${basePath}resources/js/custom/base/scheduleJob/add.js"
	type="text/javascript"></script>
<script type="text/javascript">
    DataTableList.init();
</script>
