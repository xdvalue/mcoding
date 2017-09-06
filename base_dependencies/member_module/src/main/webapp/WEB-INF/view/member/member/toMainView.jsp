<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="mcoding" uri="http://mcoding.cn/jsp/common"%>


<!-- BEGIN PAGE CONTENT-->
<div class="row">
	<div class="col-md-12">
		<!-- BEGIN EXAMPLE TABLE PORTLET-->
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>平台会员列表
				</div>
			</div>
			<div class="portlet-body">
				<div class="table-toolbar portlet-body form">
					<%-- <form action="#" id="memberSearch" class="form-horizontal ">
						<div class="form-group row ">
							<label class="col-md-1 control-label text-left">分类:</label>
							<div class="col-md-11">
								<mcoding:dicGroupCheckboxListTag dicGroupCode="member_industry"
									nameOfAllCheckbox="industry" />
							</div>
						</div>
						<div class="form-group row">
							<label class="col-md-1 control-label text-left"> 行业: </label>
							<div class="col-md-11">
								<mcoding:dicGroupCheckboxListTag dicGroupCode="member_industry"
									nameOfAllCheckbox="industry" />
							</div>
						</div>
						<div class="form-group row">
							<label class="col-md-1 control-label text-left"> 区域: </label>
							<div class="row" id="address">
								<div class="col-md-2">
									<select class="form-control prov" id="province" name="province">
										<option>省</option>
									</select>
								</div>
								<div class="col-md-2">
									<select class="form-control city" id="city" name="city">
										<option>市</option>
									</select>
								</div>
								<div class="col-md-2">
									<select class="form-control dist" id="district" name="district">
										<option>区</option>
									</select>
								</div>
							</div>
						</div>
						<div class="form-group row">
							<label class="col-md-1 control-label text-left"> 兴趣: </label>
							<div class="col-md-11">
								<mcoding:dicGroupCheckboxListTag dicGroupCode="member_interest"
									nameOfAllCheckbox="interest" />
							</div>
						</div>
					</form> --%>
					<!-- <div>
						<button type="submit" class="btn blue">查询</button>
						<button type="button" class="btn blue">添加会员</button>
					</div> -->
					<div class="btn-group">
						<span id="addSnsPost" href="member/service/toAddView" class="ajaxify">
							<span class="btn btn-sm green fileinput-button"> <i
								class="fa fa-plus"></i><span>添加会员</span>
							</span>
						</span>
					</div>
				</div>
				<div class="table-responsive">
					<table id="dataTable"
						class="table table-striped table-bordered table-hover"></table>
				</div>

			</div>
		</div>
		<!-- END EXAMPLE TABLE PORTLET-->
	</div>
</div>

<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script src="${basePath}resources/js/custom/member/member/main.js"
	type="text/javascript"></script>
<script src="${basePath}resources/js/cityselect/jquery.cityselect.js"
	type="text/javascript"></script>
<%--

<script src="${basePath}resources/js/main/jquery-labelauty.js"
	type="text/javascript"></script>
<!-- END PAGE LEVEL SCRIPTS -->
 --%>
<script type="text/javascript">
    DataTableList.init();
</script>
