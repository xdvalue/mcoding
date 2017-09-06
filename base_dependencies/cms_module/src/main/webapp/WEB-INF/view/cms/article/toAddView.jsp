<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="mcoding" uri="http://mcoding.cn/jsp/common"%>
<link rel="stylesheet" type="text/css"
	href="${basePath}resources/metronic_v2.0.2/assets/plugins/jquery-tags-input/jquery.tagsinput.css" />
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
		
		<c:if test="${param.source != 'activity'}">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-reorder"></i>
					<c:choose>
						<c:when test="${actionType == 'U'}">修改文章</c:when>
						<c:when test="${actionType == 'D'}">文章详情</c:when>
						<c:otherwise>添加文章</c:otherwise>
					</c:choose>
				</div>
			</div>
		</c:if>
			<div class="portlet-body form">
				<form action="#" id="articleForm" class="form-horizontal">
				    <input type="hidden" name="id" id="articleId" value="${article.id}" />
					<div class="form-body">
						<div class="form-group">
							<label class="col-md-3 control-label">标题 <span
								class="required">*</span>
							</label>
							<div class="col-md-9">
								<input type="text" name="title" id="title"
									value="${article.title}" required
									class="form-control input-inline input-medium"
									placeholder="请输入文章标题">
							</div>
						</div>

						<div class="form-group">
							<label class="col-md-3 control-label">副标题 </label>
							<div class="col-md-2">
								<input type="text" name="subTitle" id="subTitle"
									value="${article.subTitle}"
									class="form-control input-inline input-medium"
									placeholder="请输入文章副标题">
							</div>
						</div>

						<div class="form-group">
							<label class="col-md-3 control-label">作者</label><span
								class="required">*</span>
							<div class="col-md-2">
								<input type="text" name="author" id="author"
									value="${article.author}" required
									class="form-control input-inline input-medium"
									placeholder="请输入文章作者">
							</div>
						</div>

						<div class="form-group">
							<label class="col-md-3 control-label">内容 </label><span
								class="required">*</span>
							<div class="col-md-9">
								<textarea id="contentKindEditor" name="content" required>
												${article.content}
											</textarea>
							</div>
						</div>

						<div class="form-group">
							<label class="col-md-3 control-label">摘要 </label>
							<div class="col-md-9">
								<textarea id="summaryKindEditor" name="summary">
												${article.summary}
											</textarea>
							</div>
						</div>

						<div class="form-group">
							<label class="col-md-3 control-label">文章标签 </label>
							<div class="col-md-9">
								<input id="tag" name="tag" type="text" class="form-control tags"
									value="${article.tag}" />
							</div>
						</div>
						<div class="form-group" id="commonTagFormGroup" style="display: none">
							<label class="col-md-3 control-label">热门标签</label>
							<div class=" col-md-9 clearfix" id="commonTag"></div>
						</div>

						<%-- <div class="form-group">
							<label class="col-md-3 control-label">返利积分</label>
							<div class="col-md-2">
								<input type="text" name="point" id="point"
									value="${article.point}"
									class="form-control input-inline input-medium"
									placeholder="请输入返利积分">
							</div>
						</div> --%>



						<!-- <div class="form-group">
							<label class="control-label col-md-3">产品推荐</label>
							<div class="col-md-4">
								<select id="recProductArrayId" name="recProductArray"
									class="form-control input-medium select2me" multiple
									data-placeholder="请选择推荐产品">
								</select>
							</div>
						</div> -->
						
						<div class="form-group">
							<label class="col-md-3 control-label">文章显示类型</label>
							<div class="col-md-2">
								<mcoding:dicGroupSelectTag dicGroupCode="artcle_type" id="typeId"
								name="typeId" selectedItemValue="${article.typeId}"/>
							</div>
						</div>

						<div class="form-group">
							<label class="col-md-3 control-label">文章发布来源</label>
							<div class="col-md-2">
								<mcoding:dicGroupSelectTag dicGroupCode="article_origin" id="articleOrigin"
								name="publishType" selectedItemValue="${article.publishType}"/>
							</div>
						</div>
						
						<div class="form-group" id="originFormGroup" style="display:none">
							<label class="col-md-3 control-label">文章引用来源 
							</label>
							<div class="col-md-9">
								<input type="text" name="origin" id="origin"
									value="${article.origin}"
									class="form-control input-inline input-medium"
									placeholder="请输入文章标题">
							</div>
						</div>
						<div class="form-group" id="originAddressFormGroup" style="display:none">
							<label class="col-md-3 control-label">文章源链接
							</label>
							<div class="col-md-9">
								<input type="text" name="originAddress" id="originAddress"
									value="${article.originAddress}"
									class="form-control input-inline input-medium"
									placeholder="请输入文章标题">
							</div>
						</div>

						<div class="form-group">
							<label class="control-label col-md-3">所属模块</label>
							<div class="col-md-4">
								<mcoding:dicGroupSelectTag dicGroupCode="cms_module" 
								  name="moduleId" selectedItemValue="${article.moduleId}"/>
							</div>
						</div>

						<div class="form-group">
							<label class="col-md-3 control-label">所属公司</label>
							<div class="col-md-2">
								<mcoding:dicGroupSelectTag dicGroupCode="store_id"
									name="storeId" selectedItemValue="${article.storeId}"/>
							</div>
						</div>

						<%-- <div class="form-group">
							<label class="control-label col-md-3">文章属性</label>
							<div class="col-md-4">
								<select class="form-control input-medium select2me"
									name="articleProps" id="flagId" multiple
									data-placeholder="请选择文章属性">
									<optgroup label="文章属性">
										<option value="0"
											<c:if test="${article.rec == '1'}">selected</c:if>>推荐</option>
										<option value="1"
											<c:if test="${article.headlines == '1'}">selected</c:if>>头条</option>
										<option value="2"
											<c:if test="${article.slide == '1'}">selected</c:if>>幻灯片</option>
										<option value="3"
											<c:if test="${article.hide == '1'}">selected</c:if>>隐藏</option>
									</optgroup>
								</select>
							</div>
						</div> --%>

						<div class="form-group">
							<label class="col-md-3 control-label">封面图片 </label>
							<div class="col-md-4">
								<div class="input-group">
									<input type="text" name="coverImage" id="coverImage" value="${article.coverImage}"
										class="form-control input-inline input-medium"
										readonly="readonly" /> <span class="input-group-btn">
										<button class="btn blue" id="imageButton" type="button">选择图片</button>
									</span>
								</div>
							</div>
						</div>

						<div class="form-group">
							<label class="col-md-3 control-label">缩略图片 </label>
							<div class="col-md-4">
								<div class="input-group">
									<input type="text" name="thumbnail" id="thumbnail" value="${article.thumbnail}"
									class="form-control input-inline input-medium" readonly="readonly" /> 
									    <span
										class="input-group-btn">
										<button class="btn blue" id="litpicButton" type="button">缩略图片</button>
									</span>
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">推荐文章 </label>
							<div class="col-md-9">
								<input id="recommandArticleTagB" type="text"
									class="form-control tags" value="" /> <span
									class="input-group-btn">
									<button class="btn blue" id="imageButton" type="button"
										data-toggle="modal" href="#myModal">选择文章</button>
								</span>
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
					    <c:when test="${article!=null}">id="singleUpdate"</c:when>
						<c:otherwise>id="singleAdd"</c:otherwise>
					</c:choose>
					type="button" class="btn purple">
					<i class="fa fa-check"></i> 提 交
				</button>
				<button id="backListPage" href="article/service/toListPageView.html"
					type="button" class="btn default ajaxify">返 回</button>
			</div>
		</div>

		<div id="myModal" class="modal fade" role="dialog" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true"></button>
						<h4 class="modal-title">选择推荐文章</h4>
					</div>
					<div class="modal-body">
						<form action="#" class="form-horizontal">
							<div class="form-group">
								<label class="col-md-2 control-label">所选文章 </label>
								<div class="col-md-9">
									<input type="text" class="form-control tags" value="" id="recommandArticleTagA" readOnly />
								</div>
							</div>
						</form>
						<div class="table-responsive">
							<table id="storeDataTable"
								class="table table-striped table-bordered table-hover"></table>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript"
	src="${basePath}resources/js/common/kindeditor.js"></script>
<script type="text/javascript"
	src="${basePath}resources/metronic_v2.0.2/assets/plugins/jquery-tags-input/jquery.tagsinput.min.js"></script>
<script type="text/javascript"
	src="${basePath}resources/js/custom/cms/article/RecommendArticleManger.js"></script>
<script type="text/javascript"
	src="${basePath}resources/js/custom/cms/article/TagManger.js"></script>
<script type="text/javascript"
	src="${basePath}resources/js/custom/cms/article/add.js"></script>
<script type="text/javascript">
	ArticleManager.init();
</script>