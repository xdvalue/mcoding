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
					<i class="fa fa-globe"></i>微信账号表列表
				</div>
			</div>
			<div class="portlet-body">
				<div id="edit" class="table-toolbar">
					<div class="btn-group">
						<span id="addaccountConfig" href="accountConfig/service/toAddView" class="ajaxify">
							<span class="btn green fileinput-button"> <i
								class="fa fa-plus"></i><span>添加微信账号表</span>
						</span>
						</span>
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
<script src="${basePath}resources/js/custom/wechat/accountConfig/main.js"
	type="text/javascript"></script>
<!-- END PAGE LEVEL SCRIPTS -->

<script type="text/javascript">
    DataTableList.init();
</script>
