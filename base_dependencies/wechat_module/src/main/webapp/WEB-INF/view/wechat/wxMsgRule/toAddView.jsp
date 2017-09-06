<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="mcoding" uri="http://mcoding.com/jsp/common"%>
<c:if test="${wxMsgRule == null }">
<style>
<!--
.unselected{
    display:none;
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
						<c:when test="${wxMsgRule!=null}">修改微信消息规则</c:when>
						<c:otherwise>添加微信消息规则</c:otherwise>
					</c:choose>
				</div>
			</div>
			<div class="portlet-body form">
				<form action="#" id="wxMsgRuleForm" class="form-horizontal">
					<input type="hidden" name="id" value="${wxMsgRule.id}" />
					<input type="hidden" name="wxAccountId" value="${account.id}" />
					<input type="hidden" name="wxAccountCode" value="${account.code}" />

					<div class="form-body">
					    <div class="form-group"> 
						    <label class="col-md-3 control-label">规则名称						    </label>
						    <div class="col-md-9">
						   	<input type="text" name="name" value="${wxMsgRule.name}"
						   		class="form-control input-inline input-medium"
						   		placeholder="填写规则名称" required>
						    </div>
						</div>
					    <div class="form-group"> 
						    <label class="col-md-3 control-label">发送者openid						    </label>
						    <div class="col-md-9">
						   	<input type="text" name="fromUserName" value="${wxMsgRule.fromUserName}"
						   		class="form-control input-inline input-medium"
						   		placeholder="可不填，默认不限">
						    </div>
						</div>
						<div class="form-group"> 
						    <label class="col-md-3 control-label">收到的消息类型						    </label>
						    <div class="col-md-9">
						   	<mcoding:dicGroupSelectTag 
								    dicGroupCode="wx_msg_type" 
								    id="msgType" 
								    name="msgType" 
								    selectedItemValue="${wxMsgRule.msgType}"/>
						    </div>
						</div>
						<div id="fg-content" class="form-group unselected"   > 
						    <label class="col-md-3 control-label">匹配的内容						    </label>
						    <div class="col-md-9">
						   	<input type="text" name="content" value="${wxMsgRule.content}"
						   		class="form-control input-inline input-medium"
						   		placeholder="可不填，默认不限">
						    </div>
						</div>
						<div id="fg-event" class="form-group unselected"  > 
						    <label class="col-md-3 control-label">事件类型						    </label>
						    <div class="col-md-9">
						   	<mcoding:dicGroupSelectTag 	
								    dicGroupCode="wx_msg_event_type" 
								    id="event" 
								    name="event" 
								    selectedItemValue="${wxMsgRule.event}"/>
						    </div>
						</div>
						<div id="fg-eventKey" class="form-group unselected"  > 
						    <label class="col-md-3 control-label">事件KEY值						    </label>
						    <div class="col-md-9">
						   	<input type="text" id="eventKey" name="eventKey" value="${wxMsgRule.eventKey}"
						   		class="form-control input-inline input-medium"
						   		placeholder="可不填，默认不限">
						    </div>
						</div>
						<div id="fg-matchType" class="form-group unselected"  > 
						    <label class="col-md-3 control-label">匹配类型						    </label>
						    <div class="col-md-9">
						   	<mcoding:dicGroupSelectTag 	
								    dicGroupCode="wx_msg_match_type" 
								    id="matchType" 
								    name="matchType" 
								    selectedItemValue="${wxMsgRule.matchType}"/>
						    </div>
						</div>
						<div class="form-group"> 
						    <label class="col-md-3 control-label">是否启用						    </label>
						    <div class="col-md-2">
						        <select name="isEnable" class="form-control input-inline input-medium">
                                    <option value="1">是</option>
                                    <option value="0" <c:if test="${0 == wxMsgRule.isEnable }">selected</c:if> >否</option>
                                </select>
						    </div>
						</div>
						<div class="form-group"> 
						    <label class="col-md-3 control-label">回复的文本						    </label>
						    <div class="col-md-9">
						   	<input type="text" name="replyContent" value="${wxMsgRule.replyContent}"
						   		class="form-control input-inline input-medium"
						   		placeholder="请输入回复的文本">
						    </div>
						</div>
						<div class="form-group"  > 
						    <label class="col-md-3 control-label">优先级(从高到低) </label>
						    <div class="col-md-9">
						   	<input type="text" name="priority" value="${wxMsgRule.priority}"
						   		class="form-control input-inline input-medium"
						   		placeholder="可不填，默认0">
						    </div>
						</div>
						<div id="fg-handlers" class="form-group"  > 
						    <label class="col-md-3 control-label">其他规则处理器						    </label>
						    <div class="col-md-9">
						   	<input type="text" name="handlers" value="${wxMsgRule.handlers}"
						   		class="form-control input-inline input-medium"
						   		placeholder="可不填，默认回复文本">
						    </div>
						</div>
									
						<div class="form-actions">
							<div class="col-md-offset-3 col-md-9">
								<button
									<c:choose>
									<c:when test="${wxMsgRule!=null}">id="singleUpdate"</c:when>
									<c:otherwise>id="singleAdd"</c:otherwise>
								</c:choose>
									type="button" class="btn purple">
									<i class="fa fa-check"></i> 提 交
								</button>
								<button id="backListPage"
									href="wxMsgRule/service/toMainView?accountId=${account.id }" type="button"
									class="btn default ajaxify">返 回</button>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>

<script src="${basePath}resources/js/custom/wechat/wxMsgRule/add.js"
	type="text/javascript"></script>
<script type="text/javascript">
    DataTableList.init();
</script>
