<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<style>
<!--
.query_col {
	padding-left: 15px;
	margin-bottom: 10px;
}

.query_col input {
	width: 100%;
}
-->
</style>

<!-- BEGIN PAGE CONTENT-->
<div class="row">
	<div class="col-md-12">

		<!-- BEGIN EXAMPLE TABLE PORTLET-->
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>微信红包列表(
					待发送:<span id="lb_unsent">0</span>,
					已发送:<span id="lb_sent">0</span>,
					已领取:<span id="lb_received">0</span>,
					已退还:<span id="lb_refund">0</span>,
				    发送失败:<span id="lb_failed">0</span>)
				</div>
			</div>

			<div class="portlet-body">
				<div class="row">
					<div class="col-md-3 query_col">
						<input type="text" class="form-control input-inline form_datetime"
							id="queryMonth" onchange="DataTableList.reload();"
							value=" <fmt:formatDate type="date" pattern="yyyy-MM" value="${nowdate}"/>   "
							placeholder="查询月份">
					</div>
					<div class="col-md-3 query_col">
						<select class="form-control" id="queryStatus" onchange="DataTableList.reload();">
							<option value="">请选择状态</option>
							<option value="UNSENT">待发送</option>
							<option value="SENT">已发送</option>
							<option value="RECEIVED">已领取</option>
							<option value="REFUND">已退款</option>
							<option value="FAILED">发送失败</option>
						</select>
					</div>
				</div>
				<div class="table-responsive">
					<table id="dataTable"
						class="table table-striped table-bordered table-hover"></table>
				</div>
				<!-- END EXAMPLE TABLE PORTLET-->
			</div>
		</div>
		<!-- END EXAMPLE TABLE PORTLET-->

	</div>
</div>


<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script
	src="${basePath}resources/js/custom/wechat/wxRedpackSendRecord/main.js"
	type="text/javascript"></script>
<!-- END PAGE LEVEL SCRIPTS -->

<script type="text/javascript">
	DataTableList.init();
</script>
