<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="mcoding" uri="http://mcoding.com/jsp/common"%>
<c:if test="${wxMsgRule == null }">
	<style>
<!--
.unselected {
	display: none;
}
-->
</style>
</c:if>

<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-reorder"></i>
					<c:choose>
						<c:when test="${wxMsgRule.id !=null }">修改微信消息规则</c:when>
						<c:otherwise>添加微信消息规则</c:otherwise>
					</c:choose>
				</div>
			</div>
			<div class="portlet-body form">
				<form action="#" id="wxMsgRuleForm" class="form-horizontal">
					<div class="form-body">
						<div class="form-group"> 
						    <label class="col-md-3 control-label">回复的图文</label>
						    <div class="col-md-9">
						   	<input type="text" name="replyContent" id="replyNewsInput" value="${wxMsgRule.replyContent}"
						   		class="form-control input-inline input-medium"
						   		placeholder="回复的图文" readonly="readonly">
						    </div>
						</div>
					</div>
					<div class="form-actions">
						<div class="col-md-offset-3 col-md-9">
							<button 
							    href="wxMsgRule/service/toAddOEditView/0?originId=${account.originId }&id=${wxMsgRule.id}"
								type="button" class="btn purple ajaxify">
								上一步
							</button>
							<button id="nextBtn" 
							    nextUrl="wxMsgRule/service/toAddOEditView/1?originId=${account.originId }"
								type="button" class="btn green">
								下一步
							</button>
							<button
							    href="wxMsgRule/service/toAddView/msgType"
								type="button" class="btn orange">
								跳过
							</button>
							<button id="backListPage"
								href="wxMsgRule/service/toAddOEditView/-1?originId=${account.originId }"
								type="button" class="btn red ajaxify">取消</button>
							<button id="ajaxBtn" style="display:none;" class="ajaxify"></button>
						</div>
					</div>
			</div>
			</form>
		</div>
	</div>
	<div class="col-md-12">
		<!-- BEGIN EXAMPLE TABLE PORTLET-->
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>图文消息列表
				</div>
			</div>
			<div class="portlet-body">
				<div id="edit" class="table-toolbar">
					<div class="btn-group">
						<span id="addwxMsgReplyNews" onclick="WxReplyNewsAddViewManager.load();">
							<span class="btn green fileinput-button"> <i
								class="fa fa-plus"></i><span>添加自定义图文消息</span>
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

<div class="modal fade" tabindex="-1" data-backdrop="static" data-keyboard="false" id="wxMsgReplyNewsAddViewModal">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-body" id="wxMsgReplyNewsAddViewModalBody">
				
			</div>
			<div class="modal-footer">
				<button type="button" data-dismiss="modal" class="btn default">取消</button>
				<button type="button" class="btn green" onclick="WxReplyNewsAddViewManager.createOrEdit();" >提交</button>
			</div>
		</div>
	</div>
</div>
<input type="hidden" id="accountIdInput" value="${account.id }" />
<input type="hidden" id="accountOriginIdInput" value="${account.originId }" />

<script src="${basePath}resources/js/common/kindeditor.js" type="text/javascript"></script>
<script src="${basePath}resources/js/custom/wechat/wxMsgRule/addOrEdit/common.js" type="text/javascript"></script>
<script src="${basePath}resources/js/custom/wechat/wxMsgRule/addOrEdit/replyNews.js" type="text/javascript"></script>
<script type="text/javascript">
	DataTableList.init();
	WxReplyNewsDataTableList.init();
</script>
