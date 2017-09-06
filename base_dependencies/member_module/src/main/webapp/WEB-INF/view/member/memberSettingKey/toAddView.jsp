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
						<c:when test="${memberSettingKey!=null}">修改会员配置</c:when>
						<c:otherwise>添加会员配置</c:otherwise>
					</c:choose>
				</div>
			</div>
			<div class="portlet-body form">
				<form action="#" id="memberSettingKeyForm" class="form-horizontal">
					<input type="hidden" name="id" value="${memberSettingKey.id}" />

					<div class="form-body">
						<div class="form-group"> 
						    <label class="col-md-3 control-label">id						    </label>
						    <div class="col-md-9">
						   	<input type="text" name="id" value="${memberSettingKey.id}"
						   		class="form-control input-inline input-medium"
						   		placeholder="请输入id" required>
						    </div>
						</div>
						<div class="form-group"> 
						    <label class="col-md-3 control-label">模块类型，100 sns模块，200会员模块						    </label>
						    <div class="col-md-9">
						   	<input type="text" name="moduleType" value="${memberSettingKey.moduleType}"
						   		class="form-control input-inline input-medium"
						   		placeholder="请输入模块类型，100 sns模块，200会员模块" required>
						    </div>
						</div>
						<div class="form-group"> 
						    <label class="col-md-3 control-label">配置名称						    </label>
						    <div class="col-md-9">
						   	<input type="text" name="settingName" value="${memberSettingKey.settingName}"
						   		class="form-control input-inline input-medium"
						   		placeholder="请输入配置名称" required>
						    </div>
						</div>
						<div class="form-group"> 
						    <label class="col-md-3 control-label">配置代码						    </label>
						    <div class="col-md-9">
						   	<input type="text" name="settingCode" value="${memberSettingKey.settingCode}"
						   		class="form-control input-inline input-medium"
						   		placeholder="请输入配置代码" required>
						    </div>
						</div>
						<div class="form-group"> 
						    <label class="col-md-3 control-label">配置默认值						    </label>
						    <div class="col-md-9">
						   	<input type="text" name="settingDefaultValue" value="${memberSettingKey.settingDefaultValue}"
						   		class="form-control input-inline input-medium"
						   		placeholder="请输入配置默认值" required>
						    </div>
						</div>
						<div class="form-group"> 
						    <label class="col-md-3 control-label">备注						    </label>
						    <div class="col-md-9">
						   	<input type="text" name="remark" value="${memberSettingKey.remark}"
						   		class="form-control input-inline input-medium"
						   		placeholder="请输入备注" required>
						    </div>
						</div>
						<div class="form-group"> 
						    <label class="col-md-3 control-label">创建时间						    </label>
						    <div class="col-md-9">
						   	<input type="text" name="createTime" value="${memberSettingKey.createTime}"
						   		class="form-control input-inline input-medium"
						   		placeholder="请输入创建时间" required>
						    </div>
						</div>

									
						<div class="form-actions">
							<div class="col-md-offset-3 col-md-9">
								<button
									<c:choose>
									<c:when test="${memberSettingKey!=null}">id="singleUpdate"</c:when>
									<c:otherwise>id="singleAdd"</c:otherwise>
								</c:choose>
									type="button" class="btn purple">
									<i class="fa fa-check"></i> 提 交
								</button>
								<button id="backListPage"
									href="memberSettingKey/service/toMainView.html" type="button"
									class="btn default ajaxify">返 回</button>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>

<script src="${basePath}resources/js/custom/member/memberSettingKey/add.js"
	type="text/javascript"></script>
<script type="text/javascript">
    DataTableList.init();
</script>
