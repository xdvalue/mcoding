<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="mcoding" uri="http://mcoding.cn/jsp/common"%>
<link rel="stylesheet" type="text/css"
	href="${basePath}resources/metronic_v2.0.2/assets/plugins/jquery-tags-input/jquery.tagsinput.css" />
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-reorder"></i>
					<c:choose>
						<c:when test="${banner!=null}">修改轮播图片</c:when>
						<c:otherwise>增加轮播图片</c:otherwise>
					</c:choose>
				</div>
			</div>
			<div class="portlet-body form">
				<form action="#" id="bannerForm" class="form-horizontal">
					<input type="hidden" name="id" id="bannerId" value="${banner.id}" />
					<div class="form-body">
						<div class="form-group">
							<label class="col-md-3 control-label">标题 <span
								class="required">*</span>
							</label>
							<div class="col-md-9">
								<input type="text" name="title" id="title"
									value="${banner.title}" required
									class="form-control input-inline input-medium"
									placeholder="请输入标题">
							</div>
						</div>

						<div class="form-group">
							<label class="col-md-3 control-label">提示文字 </label>
							<div class="col-md-2">
								<input type="text" name="imgAlt" id="imgAlt"
									value="${banner.imgAlt}"
									class="form-control input-inline input-medium"
									placeholder="提示文字">
							</div>
						</div>

						<div class="form-group">
							<label class="col-md-3 control-label">图片排序</label><span
								class="required">*</span>
							<div class="col-md-2">
								<input type="text" name="sort" id="sort"
									value="${banner.sort}" required
									class="form-control input-inline input-medium"
									placeholder="请输入图片排序">
							</div>
						</div>


						<div class="form-group">
							<label class="col-md-3 control-label">图片超链接地址 </label><span
								class="required">*</span>
							<div class="col-md-2">
								<input type="text" name="aUrl" id="aUrl"
									value="${banner.aUrl}" required
									class="form-control input-inline input-medium"
									placeholder="请输入图片超链接地址">
							</div>
						</div>


						<div class="form-group">
							<label class="col-md-3 control-label">选择首页图片 </label>
							<div class="col-md-4">
								<div class="input-group">
									<input type="text" name="imgUrl" id="imgUrl"
										value="${banner.imgUrl}"
										class="form-control input-inline input-medium"
										readonly="readonly" /> <span class="input-group-btn">
										<button class="btn blue" id="imageButton" type="button">选择图片</button>
									</span>
								</div>
							</div> 
						</div>
					</div>
				</form>
			</div>
		</div>
		<div class="form-actions fluid">
			<div class="col-md-offset-3 col-md-9">
				<button
					<c:choose>
					    <c:when test="${banner!=null}">id="singleUpdate"</c:when>
						<c:otherwise>id="singleAdd"</c:otherwise>
					</c:choose>
					type="button" class="btn purple">
					<i class="fa fa-check"></i> 提 交
				</button>
				<button id="backListPage" href="banner/service/toListPageView.html"
					type="button" class="btn default ajaxify">返 回</button>
			</div>
		</div>

	</div>
</div>
<script type="text/javascript"
	src="${basePath}resources/js/common/kindeditor.js"></script>
<script type="text/javascript"
	src="${basePath}resources/js/custom/cms/banner/add.js"></script>
<script type="text/javascript">
	BannerManager.init();
</script>