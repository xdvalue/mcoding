<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="mcoding" uri="http://mcoding.com/jsp/common"%>

<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-reorder"></i>
					<c:choose>
						<c:when test="${wxMsgReplyNews!=null}">修改自定义图文消息</c:when>
						<c:otherwise>添加自定义图文消息</c:otherwise>
					</c:choose>
				</div>
			</div>
			<div class="portlet-body form">
				<form action="#" id="wxMsgReplyNewsForm" class="form-horizontal">
					<input type="hidden" name="id" value="${wxMsgReplyNews.id}" />
					<input type="hidden" name="accountId" value="${wxMsgReplyNews.accountId}" />
					<input type="hidden" name="accountOriginId" value="${wxMsgReplyNews.accountOriginId}" />

					<div class="form-body">
						<div class="form-group"> 
						    <label class="col-md-3 control-label">标题						    </label>
						    <div class="col-md-9">
						   	<input type="text" name="title" value="${wxMsgReplyNews.title}"
						   		class="form-control input-inline input-medium"
						   		placeholder="请输入标题" required>
						    </div>
						</div>
						<div class="form-group"> 
						    <label class="col-md-3 control-label">摘要						    </label>
						    <div class="col-md-9">
						   	<input type="text" name="summary" value="${wxMsgReplyNews.summary}"
						   		class="form-control input-inline input-medium"
						   		placeholder="请输入摘要" required>
						    </div>
						</div>
						<%-- <div class="form-group"> 
						    <label class="col-md-3 control-label">封面图片						    </label>
						    <div class="col-md-9">
						   	<input type="text" name="coverImg" value="${wxMsgReplyNews.coverImg}"
						   		class="form-control input-inline input-medium"
						   		placeholder="请输入封面图片" required>
						    </div>
						</div> --%>
						<div class="form-group">
							<label class="col-md-3 control-label">封面图片<span
								class="required">*</span>
							</label>
							<div class="col-md-4">
								<div class="input-group">
									<input type="text" name="coverImg" id="imgUrlInput" value="${wxMsgReplyNews.coverImg}"
										class="form-control input-inline input-medium" placeholder="轮播图图片连接(32x31.672) "
										readonly="readonly" required /> 
									<span class="input-group-btn">
										<button class="btn blue" id="imgUrlButton" type="button">选择图片</button>
									</span>
								</div>
							</div>
							
						</div>
						<div class="form-group"> 
						    <label class="col-md-3 control-label">连接地址						    </label>
						    <div class="col-md-9">
						   	<input type="text" name="url" value="${wxMsgReplyNews.url}"
						   		class="form-control input-inline input-medium"
						   		placeholder="请输入连接地址" required>
						    </div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
