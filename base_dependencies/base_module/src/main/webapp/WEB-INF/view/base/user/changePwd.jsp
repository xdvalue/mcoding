<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="${basePath}resources/metronic_v2.0.2/assets/plugins/jquery-validation/dist/jquery.validate.min.js"></script>

<div class="row">
				<div class="col-md-12 ">
					<!-- BEGIN SAMPLE FORM PORTLET-->
					<div class="portlet box blue">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-reorder"></i> 更改密码
							</div>
						</div>
						<div class="portlet-body form">
								<form id="changPwdForm" class="form-horizontal" onSubmit="checkPwd();">
									<div class="form-body">
										<div class="form-group">
											<div class="col-md-9">
												<div id="checkMessage">
											 		<!-- <button class="close" data-close="alert"></button> -->
										    	</div>
									    	</div>
								    	</div>
										<div class="form-group">
											<label class="col-md-3 control-label">原密码
											<span class="required">*</span>
											</label>
											<div class="col-md-9">
											<input class="form-control" style="width:155px;" type="password"  id="oldPwd" name="oldPwd" placeholder="原密码" />
											</div>
										</div>
										
										<div class="form-group">
											<label class="col-md-3 control-label">新密码
											<span class="required">*</span>
											</label>
											<div class="col-md-9">
											<input class="form-control" style="width:155px;" type="password" id="password" name="password" placeholder="请输入新密码" />
											</div>
										</div>
										
										<div class="form-group">
											<label class="col-md-3 control-label">再次输入新密码
											<span class="required">*</span>
											</label>
											<div class="col-md-9">
											<input class="form-control" style="width:155px;" type="password"  id="password2" name="password2" placeholder="再次输入新密码"/>
											</div>
										</div>
								</div>	
								<div class="form-actions fluid">
									<div class="row">
										<div class="col-md-12">
											<div class="col-md-offset-3 col-md-9">
											<button id="changPwdBtn" type="button" class="btn purple">
											<i class="fa fa-check"></i> 提交</button>
											<a id="backListPage" href="main.html"  type="button" class="btn default ajaxify">返回</a>
											</div>
										</div>
									</div>
								</div>
								</form>
							</div>
						</div>
					</div>
				</div>
<script src="${basePath}resources/js/custom/base/user/userPwd.js" type="text/javascript"></script>
<script type="text/javascript">
	User_PwdMgr.init();
</script>