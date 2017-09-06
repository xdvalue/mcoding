<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<div class="row">
	<div class="col-md-12 ">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-reorder"></i> 查看会员详情信息
				</div>
			</div>
			<div class="portlet-body form">
				<form action="#" id="MemberForm" class="form-horizontal">
					<input type="hidden" id="recordId" name="recordId" value="${member.id}" />
					<input type="hidden" id="provinceValue" value="${member.memberExtInfo.province}" />
					<input type="hidden" id="cityValue" value="${member.memberExtInfo.city}" />
					<input type="hidden" id="districtValue" value="${member.memberExtInfo.district}" />

					<div class="form-body">
						<div class="form-group">
							<label class="col-md-3 control-label">用户真实名称 </label>
							<div class="col-md-9" id="name">
								<input type="text" name="name" id="name" value="${member.name}"
									placeholder="请输入用户真实名称"
									class="form-control input-inline input-medium">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">用户昵称 </label>
							<div class="col-md-9" id="trueName">
								<input type="text" name="trueName" id="trueName"
									value="${member.trueName}" placeholder="请输入用户名称"
									class="form-control input-inline input-medium">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">登录账号 </label>
							<div class="col-md-9" id="nenberLoginName">
								<input type="text" name="loginName" id="loginName"
									value="${member.loginName}"
									class="form-control input-inline input-medium">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">性别</label>
							<div class="col-md-9" id="memberGender">
								<label> <input type="radio" name="gender"
									<c:if test="${member.memberExtInfo.gender ==1 }">checked</c:if>
									data-labelauty="男">
								</label> <label> <input type="radio" name="gender"
									<c:if test="${member.memberExtInfo.gender ==0 }">checked</c:if>
									data-labelauty="女">
								</label>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">用户状态</label>
							<div class="col-md-9" id="memberisEnable">
								<label> <input type="radio" name="isEnable"
									<c:if test="${member.isEnable ==0 }">checked</c:if>
									data-labelauty="启用">
								</label> <label> <input type="radio" name="isEnable"
									<c:if test="${member.isEnable ==1 }">checked</c:if>
									data-labelauty="禁用">
								</label>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">生日 </label>
							<div class="col-md-5" id="birthday">
								<div class="input-group date form_datetime">
									<input type="text" size="10" readonly class="form-control"
										name="lastLoginTime"
										value="<fmt:formatDate pattern="yyyy-MM-dd" value="${member.memberExtInfo.birthday }"/>">
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
						<div class="form-group">
							<label class="col-md-3 control-label">所在地址</label>
							<div class="row" id="address">
								<div class="col-md-2">
									<select class="form-control prov" id="province" name="province">
										<option>省</option>
									</select>
								</div>
								<div class="col-md-2">
									<select class="form-control city" id="city" name="city">
										<option>市</option>
									</select>
								</div>
								<div class="col-md-2">
									<select class="form-control dist" id="district" name="district">
										<option>区</option>
									</select>
								</div>
								<div class="col-md-2">
									<input type="text" name="address" id="address"
										value="${member.memberExtInfo.address}"
										class="form-control input-inline" placeholder="请输入详细地址">
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">注册时间 </label>
							<div class="col-md-5">
								<div id="createTime">
									<input type="text" size="10" readonly class="form-control"
										name="createTime" readonly
										value='<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${member.createTime }"/>'>
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">最后登录时间 </label>
							<div class="col-md-5">
								<div id="lastLoginTime">
									<input type="text" size="10" readonly class="form-control"
										name="lastLoginTime"
										value="<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${member.lastLoginTime }"/>">
								</div>
							</div>
						</div>
						<div class="form-actions fluid">
							<div class="col-md-offset-3 col-md-9">
								<button id="through" type="button" class="btn purple">
									<i class="fa fa-check"></i> 修改
								</button>
								<button id="backListPage" href="member/service/memberList.html"
									type="button" class="btn default ajaxify">返 回</button>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
<script src="${basePath}resources/js/custom/member/memberEdit.js"
	type="text/javascript"></script>
<script src="${basePath}resources/js/main/jquery-labelauty.js"
	type="text/javascript"></script>
<script src="${basePath}resources/js/cityselect/jquery.cityselect.js"
	type="text/javascript"></script>
<script type="text/javascript">
	ORG_MemberManager.init();
	setTimeout(function() {
		$(function() {
			$(':input').labelauty();
		});
	}, 300);
</script>
