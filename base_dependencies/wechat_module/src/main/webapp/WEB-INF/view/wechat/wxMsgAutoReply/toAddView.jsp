<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="mcoding" uri="http://mcoding.com/jsp/common"%>
<%@ page import="com.mcoding.base.utils.json.JsonUtilsForMcoding" %>
<%@ page import="org.apache.commons.lang.StringUtils" %>
<%@ page import="com.mcoding.comp.wechat.msg.bean.WxMsgAutoReply" %>

<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-reorder"></i>
					<c:choose>
						<c:when test="${wxMsgAutoReply!=null}">修改自动回复的消息规则</c:when>
						<c:otherwise>添加自动回复的消息规则</c:otherwise>
					</c:choose>
				</div>
			</div>
			<div class="portlet-body form">
				<form action="#" id="wxMsgAutoReplyForm" class="form-horizontal">
					<input type="hidden" name="id" value="${wxMsgAutoReply.id}" />
					<input id="accountOriginIdInput" type="hidden" name="wxAccountOriginId" value="${account.originId}" />

					<div class="form-body">
						<div class="form-group"> 
						    <label class="col-md-3 control-label">关键字						    </label>
						    <div class="col-md-9">
						   	<input type="text" name="keywords" value="${wxMsgAutoReply.keywords }"
						   		class="form-control input-inline input-medium"
						   		placeholder="请输入关键字" required>
						    </div>
						</div>
						<div id="fg-matchType" class="form-group unselected"  > 
						    <label class="col-md-3 control-label">匹配模式</label>
						    <div class="col-md-9">
						   	<mcoding:dicGroupSelectTag 	
								    dicGroupCode="wx_msg_match_type" 
								    id="matchType" 
								    name="matchType" 
								    selectedItemValue="${wxMsgAutoReply.matchType}"/>
						    </div>
						</div>
						<div class="form-group"> 
						    <label class="col-md-3 control-label">回复类型</label>
						    <div class="col-md-9">
						    <select id="replyType" 
						        name="replyType" 
						        class="form-control input-inline input-medium"
						        onChange="DataTableList.replyTypeChange(this);">
						        <option value="">请选择</option>
						        <option  value="1" <c:if test="${wxMsgAutoReply.replyType == 1}">selected="selected"</c:if>  >文本</option>
						        <option value="2" <c:if test="${wxMsgAutoReply.replyType == 2}">selected="selected"</c:if> >图文</option>
						    </select>
						    </div>
						</div>
						<div class="form-group"> 
						    <label class="col-md-3 control-label">自动回复的内容						    </label>
						    <div class="col-md-9">
						    <textarea type="text" name="replyContent" id="replyNewsInput"
						   	    rows="5"
						   		class="form-control input-inline input-medium"
						   		required
						   		placeholder="请输入回复的文本" >${wxMsgAutoReply.replyContent}</textarea>
						    </div>
						</div>
						<div class="form-group"> 
						    <label class="col-md-3 control-label">优先级</label>
						    <div class="col-md-9">
						   	<input type="text" name="priority" value="${wxMsgAutoReply.priority}"
						   		class="form-control input-inline input-medium"
						   		placeholder="请输入优先级(数字越大，优先级越高)" required>
						    </div>
						</div>

									
						<div class="form-actions">
							<div class="col-md-offset-3 col-md-9">
								<button
									<c:choose>
									<c:when test="${wxMsgAutoReply!=null}">id="singleUpdate"</c:when>
									<c:otherwise>id="singleAdd"</c:otherwise>
								</c:choose>
									type="button" class="btn purple">
									<i class="fa fa-check"></i> 提 交
								</button>
								<button id="backListPage"
									href="wxMsgAutoReply/service/toMainView?originId=${account.originId}" type="button"
									class="btn default ajaxify">返 回</button>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	
	<div class="col-md-12" id="replyNewsBlock" style="display:none;">
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

<script src="${basePath}resources/js/common/kindeditor.js" type="text/javascript"></script>
<script src="${basePath}resources/js/custom/wechat/wxMsgRule/addOrEdit/replyNews.js" type="text/javascript"></script>
<script src="${basePath}resources/js/custom/wechat/wxMsgAutoReply/add.js" type="text/javascript"></script>
<script type="text/javascript">
    WxReplyNewsDataTableList.init();
    DataTableList.init();
</script>
