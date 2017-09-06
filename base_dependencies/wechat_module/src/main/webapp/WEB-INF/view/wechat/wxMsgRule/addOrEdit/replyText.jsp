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
						    <label class="col-md-3 control-label">回复的文本						    </label>
						    <div class="col-md-9">
						   	<textarea type="text" name="replyContent"
						   	    rows="5"
						   		class="form-control input-inline input-medium"
						   		placeholder="请输入回复的文本" >${wxMsgRule.replyContent}</textarea>
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
</div>
</div>

<script src="${basePath}resources/js/custom/wechat/wxMsgRule/addOrEdit/common.js"
	type="text/javascript"></script>
<script type="text/javascript">
	DataTableList.init();
</script>
