<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="mcoding" uri="http://mcoding.cn/jsp/common"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%  
String path = request.getContextPath();  
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
%> 
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-reorder"></i>
					<c:choose>
						<c:when test="${snsPost!=null}">修改帖子</c:when>
						<c:otherwise>添加帖子</c:otherwise>
					</c:choose>
				</div>
			</div>
			<div class="portlet-body form">
				<form action="#" id="snsPostForm" class="form-horizontal">
					<input type="hidden" id="snsPostId" name="id" value="${snsPost.id}" />

					<div class="form-body">
						<div class="form-group">
							<label class="col-md-3 control-label">帖子标题<span
								class="required">*</span>
							</label>
							<div class="col-md-9">
								<input type="text" name="title" value="${snsPost.title}"
									class="form-control input-inline input-xlarge" placeholder="请输入帖子标题" required>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">帖子作者id<span
								class="required">*</span>
							</label>
							<div class="col-md-9">
								<input type="text" name="memeberId" value="${snsPost.memeberId}"
									class="form-control input-inline input-xlarge" placeholder="帖子作者id"  required
									<c:if test="${snsPost!=null && snsPost.memeberId !=null }"> readonly="readonly" </c:if>
									>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">帖子作者名称<span
								class="required">*</span>
							</label>
							<div class="col-md-9">
								<input type="text" name="memberName" value="${snsPost.memberName}"
									class="form-control input-inline input-xlarge" placeholder="帖子作者名称">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">帖子头像连接<span
								class="required">*</span>
							</label>
							<div class="col-md-9">
								<input type="text" name="memberImgUrl" value="${snsPost.memberImgUrl}"
									class="form-control input-inline input-xlarge" placeholder="帖子作者头像">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">帖子模块<span
								class="required">*</span>
							</label>
							<div class="col-md-9">
								<select class="form-control input-inline input-xlarge" id="chooseModule_select" name="moduleId">
                                    <option value="1">首页</option>
                                    <c:forEach items="${snsModules }" var="snsModule">
                                        <c:choose>
                                            <c:when test="${snsModule.id == snsPost.moduleId }">
                                                <option selected="selected" value="${snsModule.id}">${snsModule.name }</option>
                                            </c:when>
                                            <c:otherwise>
                                                <option value="${snsModule.id}">${snsModule.name }</option>
                                            </c:otherwise>
                                        </c:choose>
                                        
                                    </c:forEach>
                        </select>
							</div>
						</div>
						<div class="form-group" id="formGroupContent">
							<label class="col-md-3 control-label">帖子内容<span
								class="required">*</span>
							</label>
							<div class="col-md-4">
								<textarea id="contentKindEditor" name="content" placeholder="请输入帖子内容">
									${snsPost.content}</textarea>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">创建时间 </label>
							<div class="col-md-5" id="birthday">
								<div class="input-group date form_datetime">
									<input type="text" size="10" class="form-control"
										name="createTime"
										value="<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${snsPost.createTime }"/>">
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
						
						<c:forEach var="item" items="${snsPost.postImgs}">
						    <div class="form-group"> 
			                    <label class="col-md-3 control-label">上传的图片<span class="required">*</span></label>
		                        <div class="col-md-4"><div class="input-group">
		                            <input type="text" name="postImg" value="${item.imgUrl }" class="form-control input-inline input-medium" placeholder="选择图片 " readonly="readonly" /> 
			                    <span class="input-group-btn">
			                        <button class="btn green" data-toggle="modal" href="#postImage_${item.id }"  type="button">预览图片</button>
			                        <button class="btn red" type="button">删除图片</button>
			                    </span>
		                        </div></div>
		                        <div id="postImage_${item.id }" class="modal fade modal-scroll" tabindex="-1" data-replace="true">
								    <div class="modal-dialog"><div class="modal-content"><div class="modal-body">
								        <img src="<%=basePath %>${item.imgUrl }">
									</div>
									<div class="modal-footer"><button type="button" data-dismiss="modal" class="btn">关闭</button></div>
									</div></div>
							    </div>
	                        </div>
                        </c:forEach>

						<div class="form-group" id="formGroupUploadImage">
							<label class="col-md-3 control-label">上传的图片<span
								class="required">*</span>
							</label>
							<div class="col-md-4">
							<div class="input-group">
							    <input type="text" name="imgUrl" value=""
										class="form-control input-inline input-medium" placeholder="选择图片 "
										readonly="readonly" /> 
								<span class="input-group-btn">
								    <button class="btn blue" id="uploadImgBtn" type="button">添加图片</button>
								</span>
							</div>
							</div>
						</div>
						
						<div class="form-actions">
							<div class="col-md-offset-3 col-md-9">
								<button
									<c:choose>
									<c:when test="${snsPost!=null}">id="singleUpdate"</c:when>
									<c:otherwise>id="singleAdd"</c:otherwise>
								</c:choose>
									type="button" class="btn purple">
									<i class="fa fa-check"></i> 提 交
								</button>
								<button id="backListPage"
									href="snsPost/service/toMainView.html" type="button"
									class="btn default ajaxify">返 回</button>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	<div id="memberManagerModal"/>
</div>

<script src="${basePath}resources/js/custom/member/plugin/memberManagerModal.js" 
	type="text/javascript"></script>
<script src="${basePath}resources/js/common/kindeditor.js" 
	type="text/javascript"></script>
<script src="${basePath}resources/js/custom/sns/snsPost/add.js"
	type="text/javascript"></script>
<script type="text/javascript">
    SnsPostManager.init();
    var basePath = "<%=basePath %>";
    $("#memberManagerModal").memberManager();
</script>