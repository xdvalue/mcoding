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
					<i class="fa fa-globe"></i>帖子列表
				</div>
			</div>
			<div class="portlet-body">
				<!-- <div id="edit" class="table-toolbar">
					<form id="searchForm" class="form-horizontal" action="#">
						<div class="col-md-3">
							<select name="isCheck" class="form-control input-medium">
								<option value="">请选择审核状态</option>
								<option value="1">已审核</option>
								<option value="0">未审核</option>
							</select>
						</div>
						<div class="col-md-3">
							<select name="typeFlag"
								class="form-control input-medium">
								<option value="">请选择帖子类型</option>
								<option value="1">普通帖</option>
								<option value="2">活动贴</option>
								<option value="3">精品帖</option>
							</select>
						</div>
					</form>
					<div class="col-md-3">
						<button id="searchBtn"
							class="btn btn-sm red filter-submit margin-bottom">
							<i class="fa fa-search"></i>查看
						</button>
						<button id="checkBtn"
							class="btn btn-sm red filter-submit margin-bottom">
							批量审核
						</button>
						<button id="uncheckBtn"
							class="btn btn-sm red filter-submit margin-bottom">
							批量取消审核
						</button>
					</div>
				</div> -->

				<div id="add" class="table-toolbar">
					<div class="btn-group">
						<span id="addSnsPost" href="snsPost/service/toAddView" class="ajaxify">
							<span class="btn btn-sm green fileinput-button"> <i
								class="fa fa-plus"></i><span>添加帖子</span>
							</span>
						</span>
						<span id="checkBtn" >
							<span class="btn btn-sm red"> 
                                <span>批量审核</span>
							</span>
						</span>
						<span id="uncheckBtn">
							<span class="btn btn-sm red">
                                <span>批量取消审核</span>
							</span>
						</span>
						<span id="chooseModule_btn" data-toggle='modal' href='#chooseModule_modal'>
							<span class="btn btn-sm red">
                                <span>更改模块</span>
							</span>
						</span>
					</div>
					<div style="float:right;">
							<span class="red">
                                                            总点击量：<span id="postViewCount">0</span>
							</span>
						</<div >
				</div>
				<div class="table-responsive">
					<table id="dataTable"
						class="table table-striped table-bordered table-hover"></table>
				</div>
				<!-- END EXAMPLE TABLE PORTLET-->
			</div>
		</div>
		<!-- END EXAMPLE TABLE PORTLET-->
		
		<div id="chooseModule_modal" class="modal fade" tabindex="-1" data-backdrop="confirmWin" data-keyboard="false">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                        <h4 class="modal-title">选择模块</h4>
                    </div>
                    <div class="modal-body">
                        <select class="form-control" id="chooseModule_select">
                            <option value="-1">请选择</option>
                            <option value="1">首页</option>
                            <c:forEach items="${snsModules }" var="snsModule">
                                <option value="${snsModule.id}">${snsModule.name }</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="modal-footer">
                        <button type="button" data-dismiss="modal" class="btn default">取消</button>
                        <button type="button" id="chooseModule_confirm_btn" class="btn green">确认</button>
                    </div>
                </div>
            </div>
        </div>

	</div>
</div>

<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script src="${basePath}resources/js/custom/sns/snsPost/main.js"
	type="text/javascript"></script>
<!-- END PAGE LEVEL SCRIPTS -->

<script type="text/javascript">
	DataTableList.init();
</script>
