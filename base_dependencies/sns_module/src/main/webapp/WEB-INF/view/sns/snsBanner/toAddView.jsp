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
						<c:when test="${snsBanner!=null}">修改轮播图</c:when>
						<c:otherwise>添加轮播图</c:otherwise>
					</c:choose>
				</div>
			</div>
			<div class="portlet-body form">
				<form action="#" id="snsBannerForm" class="form-horizontal">
					<input type="hidden" id="snsBannerId" name="id"
						value="${snsBanner.id}" />

					<div class="form-body">
						<div class="form-group">
							<label class="col-md-3 control-label">轮播图名称 <span
								class="required">*</span>
							</label>
							<div class="col-md-9">
								<input type="text" name="title" value="${snsBanner.title}"
									class="form-control input-inline input-medium"
									placeholder="请输入名称" required>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">图片地址 <span
								class="required">*</span>
							</label>
							<div class="col-md-4">
								<div class="input-group">
									<input type="text" name="imgUrl" id="imgUrlInput" value="${snsBanner.imgUrl}"
										class="form-control input-inline input-medium" placeholder="轮播图图片连接(32x31.672) "
										readonly="readonly" /> 
									<span class="input-group-btn">
										<button class="btn blue" id="imgUrlButton" type="button">选择图片</button>
									</span>
								</div>
							</div>
							
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">外链连接 <span
								class="required">*</span>
							</label>
							<div class="col-md-4">
								<input type="text" name="aUrl" value="${snsBanner.aUrl}"
									class="form-control input-inline input-medium" >

							</div>
						</div>

						<div class="form-group">
							<label class="col-md-3 control-label">轮播图顺序 <span
								class="required">*</span>
							</label>
							<div class="col-md-4">
							    <input type="text" name="sort" value="${snsBanner.sort}"
									class="form-control input-inline input-medium"
									placeholder="轮播图顺序(倒序排行，数字越大越靠前) ">
							</div>
						</div>

						<div class="form-actions">
							<div class="col-md-offset-3 col-md-9">
								<button
									<c:choose>
									<c:when test="${snsBanner!=null}">id="singleUpdate"</c:when>
									<c:otherwise>id="singleAdd"</c:otherwise>
								</c:choose>
									type="button" class="btn purple">
									<i class="fa fa-check"></i> 提 交
								</button>
								<button id="backListPage"
									href="snsBanner/service/toMainView.html" type="button"
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
<script src="${basePath}resources/js/custom/sns/snsBanner/add.js"
	type="text/javascript"></script>
	
<script type="text/javascript">
    SnsModuleManager.init();
</script>

