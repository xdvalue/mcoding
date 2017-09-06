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
					<i class="fa fa-globe"></i>标签列表
				</div>
			</div>
			<div class="portlet-body">
				<div id="edit" class="table-toolbar">
					<div class="btn-group">
						<span id="addProduct" href="label/service/action?actionType=A" class="ajaxify">
							<span class="btn green fileinput-button"> <i
								class="fa fa-plus"></i><span>添加标签</span>
						</span>
						</span>
					</div>
				</div>
				<div class="table-responsive">
					<table id="labelTable"
						class="table table-striped table-bordered table-hover"></table>
				</div>
				<!-- END EXAMPLE TABLE PORTLET-->
			</div>
		</div>
		<!-- END EXAMPLE TABLE PORTLET-->
	</div>
</div>


<div id="confirmWinLabel" class="modal fade" tabindex="-1"
	data-backdrop="confirmWin" data-keyboard="false">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true"></button>
				<h4 class="modal-title">删除标签提示！</h4>
			</div>
			<div class="modal-body">
				<input type="hidden" name="labelId" id="labelId" value="" />
				<p style="color: #8b0000; font-size: 15px;">是否删除该标签</p>
			</div>
			<div class="modal-footer">
				<button type="button" data-dismiss="modal" class="btn default">取消</button>
				<button type="button" id="deleteLabel" class="btn green">确认</button>
			</div>
		</div>
	</div>
</div>
<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script src="${basePath}resources/js/custom/cms/label/main.js"
	type="text/javascript"></script>
<!-- END PAGE LEVEL SCRIPTS -->

<script type="text/javascript">
    DataTableList.init();
</script>
