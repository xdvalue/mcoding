<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="mcoding" uri="http://mcoding.com/jsp/common"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-reorder"></i>
					<c:choose>
						<c:when test="${snsMsg!=null}">修改消息</c:when>
						<c:otherwise>添加消息</c:otherwise>
					</c:choose>
				</div>
			</div>
			<div class="portlet-body form">
				<form action="#" id="snsMsgForm" class="form-horizontal">
					<input type="hidden" name="id" value="${snsMsg.id}" />
					<input type="hidden" name="type" value="100" />
					<input type="hidden" name="isEnable" value="1" />
					<input type="hidden" name="storeId" value="<%=request.getSession().getAttribute("storeId") %>" />

					<div class="form-body">
						<div class="form-group"> 
						    <label class="col-md-3 control-label">标题						    </label>
						    <div class="col-md-9">
						   	<input type="text" name="title" value="${snsMsg.title}"
						   		class="form-control input-inline input-medium"
						   		placeholder="请输入标题" required>
						    </div>
						</div>
						<div class="form-group"> 
						    <label class="col-md-3 control-label">摘要						    </label>
						    <div class="col-md-9">
						    <textarea class="form-control " maxlength="225"  rows="2"  name="summary"
						    placeholder="摘要不要超出50个字">${snsMsg.summary}</textarea>
						   	<%-- <input type="text" name="summary" value="${snsMsg.summary}"
						   		class="form-control input-inline input-medium"
						   		placeholder="请输入摘要" required>--%>
						    </div> 
						</div>
						<div class="form-group"> 
						    <label class="col-md-3 control-label">消息内容						    </label>
						    <div class="col-md-4">
								<textarea id="contentKindEditor" name="content" placeholder="请输入消息内容">
									${snsMsg.content}</textarea>
							</div>
						   <%-- 	<input type="text" name="content" value="${snsMsg.content}"
						   		class="form-control input-inline input-medium"
						   		placeholder="请输入消息内容" required>
						    </div> --%>
						</div>
						<div class="form-group"> 
						    <label class="col-md-3 control-label">外链的连接						    </label>
						    <div class="col-md-9">
						   	<input type="text" name="url" value="${snsMsg.url}"
						   		class="form-control input-inline input-medium"
						   		placeholder="请输入外链的连接" required>
						    </div>
						</div>
						<div class="form-group"> 
						    <label class="col-md-3 control-label">有效截止时间						    </label>
						    <div class="col-md-5" id="birthday">
								<div class="input-group date form_datetime">
									<input type="text" size="10" class="form-control" name="enableTime"
										value="<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${snsMsg.enableTime }"/>" />
										
									<span class="input-group-btn">
										<button class="btn default date-reset" type="button"><i class="fa fa-times"></i>
										</button>
									</span> <span class="input-group-btn">
										<button class="btn default date-set" type="button"><i class="fa fa-calendar"></i>
										</button>
									</span>
								</div>
							</div>
						    <%-- <div class="col-md-9">
						   	<input type="text" name="enableTime" value="${snsMsg.enableTime}"
						   		class="form-control input-inline input-medium"
						   		placeholder="请输入有效截止时间" required>
						    </div> --%>
						</div>

									
						<div class="form-actions">
							<div class="col-md-offset-3 col-md-9">
								<button
									<c:choose>
									<c:when test="${snsMsg!=null}">id="singleUpdate"</c:when>
									<c:otherwise>id="singleAdd"</c:otherwise>
								</c:choose>
									type="button" class="btn purple">
									<i class="fa fa-check"></i> 提 交
								</button>
								<button id="backListPage"
									href="snsMsg/service/toMainView.html" type="button"
									class="btn default ajaxify">返 回</button>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
<script src="${basePath}resources/js/common/kindeditor.js" 
	type="text/javascript"></script>
<script src="${basePath}resources/js/custom/sns/snsMsg/add.js"
	type="text/javascript"></script>
<script type="text/javascript">
    DataTableList.init();
</script>
