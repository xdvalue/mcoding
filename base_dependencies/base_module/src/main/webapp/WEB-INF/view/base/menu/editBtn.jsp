<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>

<div id="editBtn" class="modal fade bs-modal-lg" tabindex="-1"
	data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true"></button>
				<h4 class="modal-title">添加/编辑按钮</h4>
			</div>
			<div class="modal-body">
				<div class="form-body">
					<div class="row">
						<div class="col-md-12"></div>
						<form id="form" class="form-horizontal">   
					          	<div class="form-group">
									<label class="col-md-3 control-label">按钮名称：
									<span class="required">*</span>
									</label>
									<div class="col-md-8">
										<input type="text" id="operName" name="operName" data-required="1" class="form-control input-inline input-medium" >
										<span class="help-inline" style="position: absolute;right: 150px;top:0px;">
										</span>
									</div>
								</div>
								
								<div class="form-group">
									<label class="col-md-3 control-label">页面按钮的操作码：
									<span class="required">*</span>
									</label>
									<div class="col-md-8">
										<input type="text" id="operCode" name="operCode" data-required="1" class="form-control input-inline input-medium"  value="${operator.operCode}" >
										<span class="help-inline" style="position: absolute;right: 150px;top:0px;">
										</span>
									</div>
								</div>
										
						          <div class="form-group">
											<label class="col-md-3 control-label">按钮请求地址:
											<span class="required">*</span>
											</label>
											<div class="col-md-8">
												<input type="text" name="operURL" id="operURL" data-required="1" class="form-control input-inline input-medium" placeholder="URL地址" value="${operator.operURL}"  >
												<span class="help-inline" style="position: absolute;right: 175px;top:0px;">
												</span>
											</div>
										</div>
										
								<div class="form-group">
								<div class="col-md-8" id="checkMsg">
									<input type="hidden"  id="operId" name="operId">
						   			<input type="hidden"  id="menuId2" name="menuId" data-required="1" class="form-control input-inline input-medium" />
								</div>
								</div>
								<div class="form-group">
								<div class="col-md-8" id="checkMessage"></div>
				  			    </div>
				  			<div class="modal-footer">
								<button id="edtiBtnBtn" type="button" class="btn blue" >保存</button>
								<button type="button" data-dismiss="modal" class="btn btn-default" onclick="System_MenuManager.cleanForm();">关闭</button>
					        </div>
					   </form>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>


