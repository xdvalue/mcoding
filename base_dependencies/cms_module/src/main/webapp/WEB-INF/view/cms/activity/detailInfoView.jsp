<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- BEGIN PAGE CONTENT-->
<div class="row">
	<div class="col-md-12">
		<!-- BEGIN EXAMPLE TABLE PORTLET-->
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>活动详情
				</div>
			</div>
			<div class="portlet-body">
				<div id="edit" class="table-toolbar">
					<div class="btn-group">
						<span id="addProduct" href="activity/service/toListPageView"
							class="ajaxify"> <span class="btn green fileinput-button">
								<i class="fa fa-plus"></i><span>返回</span>
						</span>
						</span>
					</div>
				</div>

				<div id="detailInfoId" style="display: none;">${detailInfoId}</div>
				<div class="tabbable-custom">
					<!-- 订单状态筛选的标签页 -->
					<div class="tab-content">
						<ul class="nav nav-tabs" id="orderListTab">
							<!-- <li class="active"><a href="#taskListTable"
								data-toggle="tab">所有</a></li> -->
							<li id="activityProgramList" class="active"><a
								href="#programListTab" data-toggle="tab">活动环节</a></li>
							<li id="activityArticle"><a href="#listTable"
								data-toggle="tab">活动文章</a></li>
							<li id="activityMemberList"><a href="#applyMember"
								data-toggle="tab">报名人员</a></li>
						</ul>
						<div id="programListTab" style="border: 0px;"
							class="tab-pane active">
							<!-- 订单列表 -->
							<div id="edit" class="table-toolbar">
								<div class="btn-group">
									<span id="btnAddActivityProgram" data-toggle="modal"
										data-target="#myLink"> <span
										class="btn green fileinput-button"> <i
											class="fa fa-plus"></i><span>添加环节</span>
									</span>
									</span>
								</div>
							</div>
							<table id="programListTable"
								class="table table-striped table-bordered table-hover"></table>
						</div>
						<div id="listTable" style="border: 0px;">
							<!-- 订单列表 -->
							<jsp:include page="../article/toAddView.jsp">
								<jsp:param value="activity" name="source"/>
							</jsp:include>
						</div>
						<div id = "applyMember" style="border: 0pxl" class ="tab-pane">
							<table id="applyMemberTable"
								class="table table-striped table-bordered table-hover"></table>
						</div>
					</div>
				</div>
				<!-- END EXAMPLE TABLE PORTLET-->
			</div>
		</div>
		<!-- END EXAMPLE TABLE PORTLET-->

	</div>
</div>


<!-- Modal -->
<div class="modal fade" id="myLink" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myModalLabel">添加环节</h4>
			</div>
			<div class="modal-body">
				<div class="row">
					<form action="#" id="programForm" class="form-horizontal">
						<div class="form-body">
							<input type="hidden" name="activityId" id="inActivityId" value="${detailInfoId }" />
							<div class="form-group">
								<label class="col-md-3 control-label text-right">节目名称 <span
									class="required">*</span>
								</label>
								<div class="col-md-9">
									<input type="text" name="title" id="inTitle" value=""
										class="form-control input-inline input-medium"
										placeholder="请输入节目名称" required>
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-3 control-label text-right">活动主题<span
									class="required">*</span>
								</label>
								<div class="col-md-9">
									<input type="text" name="subject" id="subject" value=""
										class="form-control input-inline input-medium"
										placeholder="请输入活动主题" required>
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-3 control-label text-right">嘉宾名称 <span
									class="required">*</span>
								</label>
								<div class="col-md-9">
									<input type="text" name="hostName" id="hostName" value=""
										class="form-control input-inline input-medium"
										placeholder="请输入嘉宾名称" required>
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-3 control-label text-right">嘉宾所在公司<span
									class="required">*</span>
								</label>
								<div class="col-md-9">
									<input type="text" name="company" id="company" value=""
										class="form-control input-inline input-medium"
										placeholder="请输入公司名称" required>
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-3 control-label text-right">顺序 <span
									class="required">*</span>
								</label>
								<div class="col-md-9">
									<input type="text" name="sortNo" id="sortNo" value=""
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
										id="isWechatNotify" class="form-control input-inline"
										value="1" placeholder="请输入环节顺序">
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-3 control-label text-right">短信发送模板<span
									class="required">*</span>
								</label>
								<div class="col-md-9">
									<input type="checkbox" name="isSmsNotify" id="isSmsNotify"
										value="1" class="form-control input-inline"
										placeholder="请输入环节顺序">
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-md-3 text-right">开始时间<span
									class="required">*</span></label>
								<div class="col-md-7">
									<div class="input-group date form_datetime"
										data-date="2012-12-21T15:25:00Z">
										<input type="text" size="16" readonly class="form-control"
											name="startTime"> <span class="input-group-btn">
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
								<div class="col-md-7">
									<div class="input-group date form_datetime"
										data-date="2012-12-21T15:25:00Z">
										<input type="text" size="16" readonly class="form-control"
											name="endTime"> <span class="input-group-btn">
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
				<div class="modal-footer">
					<button type="button" href="" class="btn btn-default"
						data-dismiss="modal">关闭</button>
					<button type="button" id="addprogram" class="btn btn-primary">提交</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
</div>

<div id="confirmWin" class="modal fade" tabindex="-1"
	data-backdrop="confirmWin" data-keyboard="false">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true"></button>
				<h4 class="modal-title">删除活动环节提示！</h4>
			</div>
			<div class="modal-body">
				<input type="hidden" name="id" id="id" value="" />
				<p style="color: #8b0000; font-size: 15px;">是否删除该活动环节</p>
			</div>
			<div class="modal-footer">
				<button type="button" data-dismiss="modal" class="btn default">取消</button>
				<button type="button" id="deleteActivityProgram" class="btn green">确认</button>
			</div>
		</div>
	</div>
</div>
<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script
	src="${basePath}resources/metronic_v2.0.2/assets/plugins/bootstrap-markdown/js/bootstrap-markdown.js"
	type="text/javascript"></script>
<script src="${basePath}resources/js/custom/cms/activity/detailInfo.js"
	type="text/javascript"></script>
<!-- END PAGE LEVEL SCRIPTS -->

<script type="text/javascript">
	DetailInfo.init();
</script>