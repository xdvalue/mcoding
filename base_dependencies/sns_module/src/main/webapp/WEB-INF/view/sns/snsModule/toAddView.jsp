<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="mcoding" uri="http://mcoding.cn/jsp/common"%>

<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-reorder"></i>
					<c:choose>
						<c:when test="${snsModule!=null}">修改模块</c:when>
						<c:otherwise>添加模块</c:otherwise>
					</c:choose>
				</div>
			</div>
			<div class="portlet-body form">
				<form action="#" id="snsModuleForm" class="form-horizontal">
					<input type="hidden" id="snsModuleId" name="id"
						value="${snsModule.id}" />

					<div class="form-body">
						<div class="form-group">
							<label class="col-md-3 control-label">模块名称 <span
								class="required">*</span>
							</label>
							<div class="col-md-9">
								<input type="text" name="name" value="${snsModule.name}"
									class="form-control input-inline input-medium"
									placeholder="请输入名称" required>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">模块图片连接 <span
								class="required">*</span>
							</label>
							<div class="col-md-4">
							    <div class="input-group">
							       <input type="text" name="imgUrl" id="imgUrlInput" value="${snsModule.imgUrl}"
								        class="form-control input-inline input-medium" placeholder="模块图片连接(32x31.672) ">
								    <span class="input-group-btn">
									    <button class="btn blue" id="imgUrlButton" type="button">选择图片</button>
								    </span>
							    </div>
							</div>
						</div>

						<div class="form-group">
							<label class="col-md-3 control-label">模块顺序 <span
								class="required">*</span>
							</label>
							<div class="col-md-4">
							    <input type="text" name="seqNum" value="${snsModule.seqNum}"
									class="form-control input-inline input-medium"
									placeholder="模块顺序(倒序排行，数字越大越靠前) ">
							</div>
						</div>

						<div class="form-actions">
							<div class="col-md-offset-3 col-md-9">
								<button
									<c:choose>
									<c:when test="${snsModule!=null}">id="singleUpdate"</c:when>
									<c:otherwise>id="singleAdd"</c:otherwise>
								</c:choose>
									type="button" class="btn purple">
									<i class="fa fa-check"></i> 提 交
								</button>
								<button id="backListPage"
									href="snsModule/service/toMainView.html" type="button"
									class="btn default ajaxify">返 回</button>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript"
	src="${basePath}resources/js/common/kindeditor.js"></script>
<script src="${basePath}resources/js/custom/sns/snsModule/add.js"
	type="text/javascript"></script>
<script type="text/javascript">
    SnsModuleManager.init();
</script>

