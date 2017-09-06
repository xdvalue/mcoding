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
					<i class="fa fa-globe"></i>每日发帖量
				</div>
			</div>
			<div class="portlet-body">

				<div class="table-toolbar">
					<form id="searchForm" class="form-horizontal" action="#">
						<div class="col-md-5">
							开始时间 
							<span class="input-group date form_datetime"> 
							    <input id="startTime" type="text" size="10" readonly class="startTime form-control" name="startTime"> 
							    <span class="input-group-btn">
							        <button class="btn default date-reset" type="button">
										<i class="fa fa-times"></i>
									</button>
							    </span>
							    <span class="input-group-btn">
									<button class="btn default date-set" type="button">
										<i class="fa fa-calendar"></i>
									</button>
							    </span>
							</span>
						</div>
						<div class="col-md-5">
							结束时间 
							<span class="input-group date form_datetime"> 
							    <input id="endTime" type="text" size="10" readonly class="endTime form-control" name="endTime"> 
							    <span class="input-group-btn">
								    <button class="btn default date-reset" type="button">
									    <i class="fa fa-times"></i>
									</button>
							    </span>
							    <span class="input-group-btn">
									<button class="btn default date-set" type="button">
										<i class="fa fa-calendar"></i>
									</button>
							    </span>
							</span>
						</div>
						<button id="search" type="button" class="btn green" style="margin-top: 15px;">查询</button>
					</form>
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
<script src="${basePath}resources/js/custom/sns/snsStatistical/commentCountForDayView.js"
	type="text/javascript"></script>
<!-- END PAGE LEVEL SCRIPTS -->

<script type="text/javascript">
	DataTableList.init();
</script>
