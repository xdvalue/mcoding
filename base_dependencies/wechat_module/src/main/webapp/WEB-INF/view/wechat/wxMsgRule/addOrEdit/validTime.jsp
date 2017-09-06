<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="mcoding" uri="http://mcoding.com/jsp/common"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
						<c:when test="${wxMsgRule.id!=null}">修改微信消息规则</c:when>
						<c:otherwise>添加微信消息规则</c:otherwise>
					</c:choose>
				</div>
			</div>
			<div class="portlet-body form">
				<form action="#" id="wxMsgRuleForm" class="form-horizontal">
					<div class="form-body">
					    <!-- <div class="form-group">
							<label class="col-md-3 control-label">有效期开始时间 </label>
							<div class="col-md-5" id="birthday">
								<div data-date-format="yyyy-mm-dd" data-date="10/11/2012" class="input-inline date-picker input-daterange">
				<input type="text" size="12" name="from" class="form-control input-inline" placeholder="开始时间"  />
				<span class="small"> 到 </span>
				<input type="text" size="12" name="to" class="form-control input-inline" placeholder="结束时间"  />
			</div>
							</div>
						</div> -->
						<div class="form-group">
							<label class="col-md-3 control-label">有效期开始时间 </label>
							<div class="col-md-5">
								<div class="input-group date form_datetime">
									<input type="text" size="10" class="form-control"
										name="msgStartTime"
										value="<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${wxMsgRule.msgStartTime }"/>">
									<span class="input-group-btn">
										<button class="btn default date-reset" type="button">
											<i class="fa fa-times"></i>
										</button>
									</span> <span class="input-group-btn">
										<button class="btn default date-set" type="button">
											<i class="fa fa-calendar"></i>
										</button>
									</span>
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">有效器截止时间 </label>
							<div class="col-md-5">
							    
								<div class="input-group date form_datetime date-picker">
								
									<input type="text" size="10" class="form-control"
										name="msgEndTime"
										value="<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${wxMsgRule.msgEndTime }"/>">
									<span class="input-group-btn">
										<button class="btn default date-reset" type="button">
											<i class="fa fa-times"></i>
										</button>
									</span> <span class="input-group-btn">
										<button class="btn default date-set" type="button">
											<i class="fa fa-calendar"></i>
										</button>
									</span>
								</div>
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
								保存
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
