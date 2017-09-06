<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="mcoding" uri="http://mcoding.cn/jsp/common"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-reorder"></i>
					<c:choose>
						<c:when test="${actionType == 'U'}">修改模块</c:when>
						<c:when test="${actionType == 'D'}">模块详情</c:when>
						<c:otherwise>添加模块</c:otherwise>
					</c:choose>
				</div>
			</div>
			<div class="portlet-body form">
				<form action="#" id="moduleForm" class="form-horizontal">
					<input type="hidden" id="moduleId" name="id" value="${module.id}" />
					<div class="form-body">
						<div class="form-group">
							<label class="col-md-3 control-label">
								模块名称 
								<span class="required">*</span>
							</label>
							<div class="col-md-9">
								<input type="text" name="name" id="name"
									value="${module.name}"
									class="form-control input-inline input-medium"
									placeholder="请输入模块名称" required>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">
								模块编码 
								<span class="required">*</span>
							</label>
							<div class="col-md-9">
								<input type="text" name="code" id="code"
									value="${module.code}"
									class="form-control input-inline input-medium"
									placeholder="请输入模块编码" required>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">
								样式 
							</label>
							<div class="col-md-9">
								<input type="text" name="style" id="style"
									value="${module.style}"
									class="form-control input-inline input-medium"
									placeholder="请输入样式 ">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">
								跳转链接 
							</label>
							<div class="col-md-9">
								<input type="text" name="link" id="link"
									value="${module.link}"
									class="form-control input-inline input-medium"
									placeholder="请输入跳转链接">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">
								事件 
							</label>
							<div class="col-md-9">
								<input type="text" name="event" id="event"
									value="${module.event}"
									class="form-control input-inline input-medium"
									placeholder="请输入事件">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">
								所属公司
								<span class="required">*</span>
							</label>
							<div class="col-md-2">
								<mcoding:dicGroupSelectTag dicGroupCode="store_id"
									id="storeId" name="storeId" selectedItemValue="${module.storeId}" required="true"/>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">
								排序 
								<span class="required">*</span>
							</label>
							<div class="col-md-9">
								<input type="text" name="seqNum" id="seqNum"
									value="${module.seqNum}"
									class="form-control input-inline input-medium"
									required>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">
								父级模块
								<span class="required">*</span>
							</label>
							<div class="col-md-2">
								<select class="form-control input-inline input-medium" id="parentId" name="parentId">
									<c:if test="${module.id==null}">
										<option value="0" selected="selected">顶级模块</option>
									</c:if>
									<c:if test="${module.parentId ==0}">
											<option value="0" selected="selected">顶级模块</option>
									</c:if>
									<c:if test="${module.id != null && module.parentId !=0}">
											<option value="0" selected="selected">顶级模块</option>
									</c:if>
									<c:forEach items="${modules}" var="m">
									<c:choose>
										<c:when test="${module.id!=null && module.parentId == m.id}">
											<option value="${m.id}" selected="selected">${m.name}</option>
										</c:when>
										<c:otherwise>
											<option value="${m.id}">${m.name}</option>
										</c:otherwise>
									</c:choose>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-3">
								图片地址
							</label>
							<div class="col-md-9">
								<input type="text" name="imgUrl" id="imgUrl"
									value="${module.imgUrl}"
									style="float:left;"
									class="form-control input-inline input-medium">
								<p style="display: inline-block;width: 60px; height: 33px;line-height:33px;text-align:center; background: #e5e5e5;color:#333333;font-size:14px; position: relative;">上 传
									<input type="file"  onchange="moduleManager.fileUpLoad(this)" style="position:absolute;top:0;left:0;opacity:0;width:60px;height:33px;">
								</p>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-3">
								描述
							</label>
							<div class="col-md-9">
								<textarea id="description" class="form-control"  name = "description" style="width: 395px; height: 300px">${module.description}</textarea>
							</div>
						</div>
						<div class="form-actions">
							<div class="col-md-offset-3 col-md-9">
								<c:if test="${actionType != 'D'}">
									<button
										<c:choose>
										<c:when test="${actionType=='U'}">id="moduleUpdate"</c:when>
										<c:otherwise>id="moduleAdd"</c:otherwise>
									</c:choose>
										type="button" class="btn purple">
										<i class="fa fa-check"></i> 提 交
									</button>
								</c:if>
								<button id="backModuleListPage" href="module/service/init" type="button" class="btn default ajaxify">返 回</button>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>


<script src="${basePath}resources/js/custom/cms/module/add.js"type="text/javascript"></script>
<script type="text/javascript">
	var actionType = "${actionType}";
	moduleManager.init(actionType);
</script>
