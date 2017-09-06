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
						<c:when test="${wxQrcode!=null}">修改微信二维码</c:when>
						<c:otherwise>添加微信二维码</c:otherwise>
					</c:choose>
				</div>
			</div>
			<div class="portlet-body form">
				<form action="#" id="wxQrcodeForm" class="form-horizontal">
					<input type="hidden" name="id" value="${wxQrcode.id}" />
					<input type="hidden" name="accountId" value="${account.id}" />
					<input type="hidden" name="accountOriginId" value="${account.originId}" />

					<div class="form-body">
						<div class="form-group"> 
						    <label class="col-md-3 control-label">二维码名字						    </label>
						    <div class="col-md-9">
						   	<input type="text" name="name" value="${wxQrcode.name}"
						   		class="form-control input-inline input-medium"
						   		placeholder="请输入二维码名字" required>
						    </div>
						</div>
						<div class="form-group"> 
						    <label class="col-md-3 control-label">二维码类型</label>
						    <div class="col-md-9">
						   	<mcoding:dicGroupSelectTag 
								    dicGroupCode="wx_qrcode_type" 
								    name="type" 
								    selectedItemValue="${wxQrcode.type}"
								    required="true"
								    />
						    </div>
						</div>
						<c:if test="${wxQrcode==null}">
						<div class="form-group"> 
						    <label class="col-md-3 control-label">临时二维码有效时长(最长30天)</label>
						    <div class="col-md-9">
						   	<input type="text" name="validHours"
						   		class="form-control input-inline input-medium"
						   		placeholder="请输入有效的时长，单位小时。不填，默认30天">
						    </div>
						</div>
						</c:if>
						<div class="form-group"> 
						    <label class="col-md-3 control-label">关键词</label>
						    <div class="col-md-9">
						   	<textarea type="text" name="replyContent" id="replyNewsInput"
						   	    rows="5"
						   		class="form-control input-inline input-medium"
						   		placeholder="请输入回复的文本" >${wxMsgRule.replyContent}</textarea>
						    </div>
						</div>
									
						<div class="form-actions">
							<div class="col-md-offset-3 col-md-9">
								<button
									<c:choose>
									<c:when test="${wxQrcode!=null}">id="singleUpdate"</c:when>
									<c:otherwise>id="singleAdd"</c:otherwise>
								</c:choose>
									type="button" class="btn purple">
									<i class="fa fa-check"></i> 提 交
								</button>
								<button id="backListPage"
									href="wxQrcode/service/toMainView.html?originId=${account.originId}" type="button"
									class="btn default ajaxify">返 回</button>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	
</div>

<script src="${basePath}resources/js/custom/wechat/wxQrcode/add.js" type="text/javascript"></script>
<script type="text/javascript">
    DataTableList.init();
</script>
