<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" type="text/css"
	href="${basePath}resources/metronic_v2.0.2/assets/plugins/bootstrap-markdown/css/bootstrap-markdown.min.css" />
<link rel="stylesheet" type="text/css"
	href="${basePath}resources/metronic_v2.0.2/assets/plugins/bootstrap-fileinput/bootstrap-fileinput.css" />

<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-reorder"></i>修改活动环节
				</div>
			</div>
			<div class="portlet-body form">
				<div class="row">
					<form action="#" id="programForm" class="form-horizontal">
						<div class="form-body">
							<input type="hidden" name="id" id="updateId"
								value="${activityProgram.id}" /> <input type="hidden"
								name="activityId" id="updateId"
								value="${activityProgram.activityId}" />

							<div class="form-group">
								<label class="col-md-3 control-label text-right">节目名称
									<span class="required">*</span>
								</label>
								<div class="col-md-9">
									<input type="text" name="title" id="inTitle"
										value="${activityProgram.title}"
										class="form-control input-inline input-medium"
										placeholder="请输入节目名称" required>
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-3 control-label text-right">活动主题<span
									class="required">*</span>
								</label>
								<div class="col-md-9">
									<input type="text" name="subject" id="subject"
										value="${activityProgram.subject}"
										class="form-control input-inline input-medium"
										placeholder="请输入活动主题" required>
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-3 control-label text-right">嘉宾名称
									<span class="required">*</span>
								</label>
								<div class="col-md-9">
									<input type="text" name="hostName" id="hostName"
										value="${activityProgram.hostName}"
										class="form-control input-inline input-medium"
										placeholder="请输入嘉宾名称" required>
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-3 control-label text-right">嘉宾所在公司<span
									class="required">*</span>
								</label>
								<div class="col-md-9">
									<input type="text" name="company" id="company"
										value="${activityProgram.company}"
										class="form-control input-inline input-medium"
										placeholder="请输入公司名称" required>
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-3 control-label text-right">顺序
									<span class="required">*</span>
								</label>
								<div class="col-md-9">
									<input type="text" name="sortNo" id="sortNo"
										value="${activityProgram.sortNo}"
										class="form-control input-inline input-medium"
										placeholder="请输入环节顺序">
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-3 control-label text-right">微信发送模板
									<span class="required">*</span>
								</label>
								<div class="col-md-9">
									<input type="checkbox" name="isWechatNotify"
										<c:choose>
											<c:when test="${activityProgram.isWechatNotify == 1}">checked="checked"</c:when>
										</c:choose>
										id="isWechatNotify" class="form-control input-inline"
										value="1">
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-3 control-label text-right">短信发送模板<span
									class="required">*</span>
								</label>
								<div class="col-md-9">
									<input type="checkbox"
										<c:choose>
											<c:when test="${activityProgram.isSmsNotify == 1}">checked="checked"</c:when>
										</c:choose>
										name="isSmsNotify" id="isSmsNotify" value="1"
										class="form-control input-inline">
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-md-3 text-right">开始时间<span
									class="required">*</span></label>
								<div class="col-md-5">
									<div class="input-group date form_datetime"
										data-date="2012-12-21T15:25:00Z">
										<input type="text" size="16" readonly class="form-control"
											value="${activityProgram.startTimeStr}" name="startTime">
										<span class="input-group-btn">
											<button class="btn default date-reset" type="button">
												<i class="fa fa-times"></i>
											</button>
										</span> <span class="input-group-btn">
											<button class="btn default date-set" type="button">
												<i class="fa fa-calendar"></i>
											</button>
										</span>
									</div>
									<!-- /input-group -->
								</div>
							</div>

							<div class="form-group">
								<label class="control-label col-md-3 text-right">结束时间
									<span class="required">*</span>
								</label>
								<div class="col-md-5">
									<div class="input-group date form_datetime"
										data-date="2012-12-21T15:25:00Z">
										<input type="text" size="16" readonly class="form-control"
											value="${activityProgram.endTimeStr}" name="endTime">
										<span class="input-group-btn">
											<button class="btn default date-reset" type="button">
												<i class="fa fa-times"></i>
											</button>
										</span> <span class="input-group-btn">
											<button class="btn default date-set" type="button">
												<i class="fa fa-calendar"></i>
											</button>
										</span>
									</div>
									<!-- /input-group -->
								</div>
							</div>
						</div>
					</form>
				</div>
				<div class="form-actions">
					<div class="col-md-offset-3 col-md-9">
						<button type="button" id="backListProgram"
							href="activity/service/detailInfoView.html?activityId=${activityProgram.activityId }"
							class="btn btn-default ajaxify" data-dismiss="modal">返回</button>
						<button type="button" id="btnProgram" class="btn btn-primary">提交</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<input type="hidden" id="basePath" value="${basePath}" />
<script
	src="${basePath}resources/metronic_v2.0.2/assets/plugins/bootstrap-markdown/js/bootstrap-markdown.js"
	type="text/javascript"></script>
<script type="text/javascript"
	src="${basePath}resources/metronic_v2.0.2/assets/plugins/bootstrap-fileinput/bootstrap-fileinput.js"></script>
<script
	src="${basePath}resources/js/custom/cms/activity/updateActivityProgram.js"
	type="text/javascript"></script>
<script type="text/javascript">
	ActivityProgramManager.init();
</script>