<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" type="text/css"
	href="${basePath}resources/metronic_v2.0.2/assets/plugins/bootstrap-markdown/css/bootstrap-markdown.min.css" />
<link rel="stylesheet" type="text/css"
	href="${basePath}resources/metronic_v2.0.2/assets/plugins/bootstrap-fileinput/bootstrap-fileinput.css" />

<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-reorder"></i>
					<c:choose>
						<c:when test="${activity!=null}">修改活动</c:when>
						<c:otherwise>添加活动</c:otherwise>
					</c:choose>
				</div>
			</div>
			<div class="portlet-body form">
				<form action="#" id="activityForm" class="form-horizontal">
					<input type="hidden" id="inActivityId" name="id"
						value="${activity.id}" />

					<div class="form-body">
						<div class="form-group">
							<label class="col-md-3 control-label">活动名称 <span
								class="required">*</span>
							</label>
							<div class="col-md-9">
								<input type="text" name="name" id="name"
									value="${activity.name}"
									class="form-control input-inline input-medium"
									placeholder="请输入活动名称" required>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">活动简介 <span
								class="required">*</span>
							</label>
							<div class="col-md-4">
								<!-- <input type="text" id="introduction" name="introduction"
									value="" class="form-control input-inline input-medium"
									placeholder="活动简介(最多250个字)"> -->
								<textarea name="introduction"
									class="form-control input-inline input-medium" rows="3"
									placeholder="活动简介(最多250个字)">${activity.name}</textarea>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">活动地址 <span
								class="required">*</span>
							</label>
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
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">详细地址 <span
								class="required">*</span>
							</label>
							<div class="col-md-4">
								<input type="text" id="address" name="address"
									value="${activity.address}"
									class="form-control input-inline input-medium"
									placeholder="详细地址(最多250个字)">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">活动人数上限 <span
								class="required">*</span>
							</label>
							<div class="col-md-9">
								<input type="text" name="memberLimit" id="memberLimit"
									value="${activity.memberLimit}"
									class="form-control input-inline input-medium"
									placeholder="请输入活动人数上限">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">活动报名费用<span
								class="required">*</span>
							</label>
							<div class="col-md-9">
								<input type="text" name="price" id="loginTel"
									value="${activity.price}"
									class="form-control input-inline input-medium"
									placeholder="活动报名费用">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">公布时间<span
								class="required">*</span>
							</label>
							<div class="col-md-5">
								<div class="input-group date form_datetime">
									<input type="text" size="10" readonly class="form-control"
										name="publicTime" value="${activity.executeTime}"> <span
										class="input-group-btn">
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
							<label class="col-md-3 control-label">截止时间<span
								class="required">*</span>
							</label>
							<div class="col-md-5">
								<div class="input-group date form_datetime">
									<input type="text" size="10" readonly class="form-control"
										name="registrationDeadline"
										value="${activity.registrationDeadline}"> <span
										class="input-group-btn">
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
							<label class="control-label col-md-3">开展时间<span
								class="required">*</span></label>
							<div class="col-md-5">
								<div class="input-group date form_datetime">
									<input type="text" size="10" readonly class="form-control"
										name="executeTime" value="${activity.executeTime}">
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

						<div class="form-actions">
							<div class="col-md-offset-3 col-md-9">
								<button
									<c:choose>
									<c:when test="${activity!=null}">id="singleUpdate"</c:when>
									<c:otherwise>id="singleAdd"</c:otherwise>
								</c:choose>
									type="button" class="btn purple">
									<i class="fa fa-check"></i> 提 交
								</button>
								<button id="backListPage"
									href="activity/service/toListPageView.html" type="button"
									class="btn default ajaxify">返 回</button>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
<input type="hidden" id="basePath" value="${basePath}" />
<script
	src="${basePath}resources/metronic_v2.0.2/assets/plugins/bootstrap-markdown/js/bootstrap-markdown.js"
	type="text/javascript"></script>
<script type="text/javascript"
	src="${basePath}resources/metronic_v2.0.2/assets/plugins/bootstrap-fileinput/bootstrap-fileinput.js"></script>
<script src="${basePath}resources/js/cityselect/jquery.cityselect.js"
	type="text/javascript"></script>
<script src="${basePath}resources/js/custom/cms/activity/add.js"
	type="text/javascript"></script>
<script type="text/javascript">
	ActivityManager.init();
</script>

