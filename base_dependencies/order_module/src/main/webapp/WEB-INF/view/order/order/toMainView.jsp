<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<style>
<!--
.query_col{
    padding-left: 15px;
    margin-bottom: 10px;
}
.query_col input {
    width:100%;
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
					<i class="fa fa-globe"></i>订单列表
				</div>
			</div>
			<div class="portlet-body">
				<div  class="row">
                     <div class="col-md-6 query_col">
						    <div class="input-group input-daterange"
								data-date-format="yyyy-mm-dd hh:ii">
								<input id="startDate" name="startDate" type="text"
									class="form-control form_datetime" name="from"
									 <fmt:parseDate value="${today}" var="date1" pattern="yyyy-MM-dd"/>
									  value="<fmt:formatDate type="date" pattern="yyyy-MM-dd HH:mm:ss" value="${date1}"/>"
									placeholder="下单时间（开始）"> <span class="input-group-addon">至</span>
								<input id="endDate" name="endDate" type="text"
									class="form-control form_datetime" name="to"
									<fmt:parseDate value="${nextDate}" var="date2" pattern="yyyy-MM-dd" />
									value="<fmt:formatDate type="date" value="${date2}" pattern="yyyy-MM-dd HH:mm:ss" />"
									placeholder="下单时间（结束）">
							</div>
					</div>
               </div>
               <div class="row">
                    <div class="col-md-3 query_col" >
                         <input type="text" name="orderNo" id="orderNoInput" 
                         class="form-control input-inline" placeholder="请输入订单编号">
                    </div>
					<div class="col-md-3 query_col">
                         <input type="text" name="tradeNo" id="tradeNoInput" class="form-control input-inline" placeholder="交易流水号">
                     </div>
                     <div class="col-md-3 query_col">
                         <input type="tel" name="deliveryCode" id="deliveryCodeInput" class="form-control input-inline" placeholder="输入物流单号">
                     </div>
               </div>
               <div class="row">
                   <div class="col-md-3 query_col">
                         <input type="text" name="memberNickName" id="memberNickNameInput" class="form-control input-inline" placeholder="输入姓名">
                    </div>
                	<div class="col-md-3 query_col" >
                         <input type="tel" name="addressPhone" id="addressPhoneInput" class="form-control input-inline" placeholder="输入收货人手机号">
                     </div>
                	<!-- <div class="col-md-3 query_col" >
                         <input type="text" name="openid" id="openidInput" class="form-control input-inline" placeholder="输入openid">
                     </div> -->
				</div>
				<div class="table-toolbar">
				    <div class="btn-group" id="searchBtn">
						<span>
							<span class="btn green fileinput-button"> <i
								class="fa fa-search"></i><span>查询</span>
						    </span>
						</span>
					</div>
				</div>
				<div class="tab-content">
                    <ul class="nav nav-tabs" id="orderListTab">
                    		<li status="0" process="" class="active"><a href="#taskListTable" data-toggle="tab"  > 所有</a></li>
							<li status="100" process=""><a href="#taskListTable" data-toggle="tab">未付款</a></li>		
							<li status="200" process=""><a href="#taskListTable" data-toggle="tab">已付款(待发货)</a></li>		
							<li status="300" process=""><a href="#taskListTable" data-toggle="tab">已发货(待收货)</a></li>		
							<li status="400" process=""><a href="#taskListTable" data-toggle="tab">已完成(已收货)</a></li>	
							<li status="500" process=""><a href="#taskListTable" data-toggle="tab">取消订单</a></li>	
                    </ul>
                    <div id="taskListTable"  class="table-responsive">
					    <table id="dataTable" class="table table-striped table-bordered table-hover"></table>
				    </div>
                </div>
				
				<!-- END EXAMPLE TABLE PORTLET-->
			</div>
		</div>
		<!-- END EXAMPLE TABLE PORTLET-->

	</div>
</div>


<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script src="${basePath}resources/js/custom/order/order/main.js"
	type="text/javascript"></script>
<!-- END PAGE LEVEL SCRIPTS -->

<script type="text/javascript">
    DataTableList.init();
</script>
