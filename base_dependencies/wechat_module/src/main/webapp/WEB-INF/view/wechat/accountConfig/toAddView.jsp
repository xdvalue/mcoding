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
						<c:when test="${accountConfig!=null}">修改微信账号表</c:when>
						<c:otherwise>添加微信账号表</c:otherwise>
					</c:choose>
				</div>
			</div>
			<div class="portlet-body form">
				<form action="#" id="accountConfigForm" class="form-horizontal">
					<input type="hidden" name="id" value="${accountConfig.id}" />

						<div class="form-group"> 
						    <label class="col-md-3 control-label">名字<span
						   	class="required">*</span> 
						    </label>
						    <div class="col-md-9">
						   	<input type="text" name="name" value="${accountConfig.name}"
						   		class="form-control input-inline input-medium"
						   		placeholder="请输入公众号 名字" required>
						    </div>
						</div>
						<div class="form-group"> 
						    <label class="col-md-3 control-label">类型<span
						   	class="required">*</span> 
						    </label>
						    <div class="col-md-9">
						     <mcoding:dicGroupSelectTag dicGroupCode="wx_account_type" 
								  name="accountType" selectedItemValue="${accountConfig.accountType}" required="true"/>
						    </div>
						</div>
						<div class="form-group"> 
						    <label class="col-md-3 control-label">微信编号<span
						   	class="required">*</span> 
						    </label>
						    <div class="col-md-9">
						   	<input type="text" name="code" value="${accountConfig.code}"
						   		class="form-control input-inline input-medium"
						   		placeholder="请输入微信号" required>
						    </div>
						</div>
						<div class="form-group"> 
						    <label class="col-md-3 control-label">原始id<span
						   	class="required">*</span> 
						    </label>
						    <div class="col-md-9">
						   	<input type="text" name="originId" value="${accountConfig.originId}"
						   		class="form-control input-inline input-medium"
						   		placeholder="请输入公众号 原始id" required>
						    </div>
						</div>
						<div class="form-group"> 
						    <label class="col-md-3 control-label">app_id<span
						   	class="required">*</span> 
						    </label>
						    <div class="col-md-9">
						   	<input type="text" name="appId" value="${accountConfig.appId}"
						   		class="form-control input-inline input-medium"
						   		placeholder="请输入app_id" required>
						    </div>
						</div>
						<div class="form-group"> 
						    <label class="col-md-3 control-label">app_secret<span
						   	class="required">*</span> 
						    </label>
						    <div class="col-md-9">
						   	<input type="text" name="appSecret" value="${accountConfig.appSecret}"
						   		class="form-control input-inline input-medium"
						   		placeholder="请输入app_secret" required>
						    </div>
						</div>
						<div class="form-group"> 
						    <label class="col-md-3 control-label">token<span
						   	class="required">*</span> 
						    </label>
						    <div class="col-md-9">
						   	<input type="text" name="token" value="${accountConfig.token}"
						   		class="form-control input-inline input-medium"
						   		placeholder="请输入token" required>
						    </div>
						</div>
						<div class="form-group"> 
						    <label class="col-md-3 control-label">aes_key<span
						   	class="required">*</span> 
						    </label>
						    <div class="col-md-9">
						   	<input type="text" name="aesKey" value="${accountConfig.aesKey}"
						   		class="form-control input-inline input-medium"
						   		placeholder="请输入aes_key">
						    </div>
						</div>
						<div class="form-group"> 
						    <label class="col-md-3 control-label">加密模式<span
						   	class="required">*</span> 
						    </label>
						    <div class="col-md-9">
						    <mcoding:dicGroupSelectTag dicGroupCode="wx_encrypt_type" 
								  name="encryptType" selectedItemValue="${accountConfig.encryptType}"/>
						   	
						    </div>
						</div>
						<div class="form-group"> 
						    <label class="col-md-3 control-label">支持微信支付<span
						   	class="required">*</span> 
						    </label>
						    <div class="col-md-9">
						   	<input type="checkbox" name="isPayEnable" id="ckIsPayEnable" <c:if test="${accountConfig.isPayEnable == 1}">checked=true</c:if>
						   		class="form-control input-inline input-medium" >
						    </div>
						</div>
						<div class="form-group"> 
						    <label class="col-md-3 control-label">商户id<span
						   	class="required">*</span> 
						    </label>
						    <div class="col-md-9">
						   	<input type="text" name="mchId" value="${accountConfig.mchId}"
						   		class="form-control input-inline input-medium"
						   		placeholder="请输入微信支付，商户id">
						    </div>
						</div>
						<div class="form-group"> 
						    <label class="col-md-3 control-label">商户key<span
						   	class="required">*</span> 
						    </label>
						    <div class="col-md-9">
						   	<input type="text" name="mchKey" value="${accountConfig.mchKey}"
						   		class="form-control input-inline input-medium"
						   		placeholder="请输入微信支付,商户key">
						    </div>
						</div>
						<div class="form-group"> 
						    <label class="col-md-3 control-label">公众号服务器域名<span
						   	class="required">*</span> 
						    </label>
						    <div class="col-md-9">
						   	<input type="text" name="domain" value="${accountConfig.domain}"
						   		class="form-control input-inline input-medium"
						   		placeholder="请输入公众号服务器域名" required>
						    </div>
						</div>
						<div class="form-group"> 
						    <label class="col-md-3 control-label">支付后的回调url<span
						   	class="required">*</span> 
						    </label>
						    <div class="col-md-9">
						   	<input type="text" name="wxpayNotifyUrl" value="${accountConfig.wxpayNotifyUrl}"
						   		class="form-control input-inline input-medium"
						   		placeholder="支付后的回调url">
						    </div>
						</div>
						<div class="form-group"> 
						    <label class="col-md-3 control-label">商户API证书路径<span
						   	class="required">*</span> 
						    </label>
						    <div class="col-md-9">
						   	<input type="text" name="certPath" value="${accountConfig.certPath}"
						   		class="form-control input-inline input-medium"
						   		placeholder="商户API证书路径">
						    </div>
						</div>
									
						<div class="form-actions">
							<div class="col-md-offset-3 col-md-9">
								<button
									<c:choose>
									<c:when test="${accountConfig!=null}">id="singleUpdate"</c:when>
									<c:otherwise>id="singleAdd"</c:otherwise>
								</c:choose>
									type="button" class="btn purple">
									<i class="fa fa-check"></i> 提 交
								</button>
								<button id="backListPage"
									href="accountConfig/service/toMainView.html" type="button"
									class="btn default ajaxify">返 回</button>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>

<script src="${basePath}resources/js/custom/wechat/accountConfig/add.js"
	type="text/javascript"></script>
<script type="text/javascript">
    DataTableList.init();
</script>
