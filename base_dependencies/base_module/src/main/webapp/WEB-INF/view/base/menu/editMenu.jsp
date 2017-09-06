<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>

<div id="editMenu" class="modal fade bs-modal-lg" tabindex="-1"
	data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true"></button>
				<h4 class="modal-title">添加/编辑菜单</h4>
			</div>
			<div class="modal-body">
				<div class="form-body">
					<div class="row">
						<div class="col-md-12"></div>
						<form id="form2" class="form-horizontal">
					   <input type="hidden"  Id="menuId" name="menuId" data-required="1" class="form-control input-inline input-medium"  >
					   <input type="hidden"  Id="parentId" name="parentId" data-required="1" class="form-control input-inline input-medium"  >   
					          	<div class="form-group">
											<label class="col-md-3 control-label">菜单名称：
											<span class="required">*</span>
											</label>
											<div class="col-md-8">
												<input type="text" id="menuName" name="menuName" data-required="1" class="form-control input-inline input-medium"  placeholder="请输入菜单名称">
												<span class="help-inline" style="position: absolute;right: 150px;top:0px;">
												</span>
											</div>
										</div>
										
								     <div class="form-group">
											<label class="col-md-3 control-label">菜单类型：
											<span class="required">*</span>
											</label>
											<div class="col-md-8">
											<input type="text" id="menuType" name="menuType" data-required="1" class="form-control input-inline input-medium" readonly>
											</div>
										</div>
										
										 <div class="form-group">
											<label class="col-md-3 control-label">排序:
											<span class="required">*</span>
											</label>
											<div class="col-md-8">
												<input type="text" name="sortNo" id="sortNo" data-required="1" class="form-control input-inline input-medium" placeholder="排序"  >
												<span class="help-inline" style="position: absolute;right: 175px;top:0px;">
												</span>
											</div>
										</div>
										
										
										 <div class="form-group">
											<label class="col-md-3 control-label">菜单栏目代码:
											<span class="required">*</span>
											</label>
											<div class="col-md-8">
												<input id="menuCode" type="text" name="menuCode"  data-required="1" class="form-control input-inline input-medium"   >
												<span class="help-inline" style="position: absolute;right: 175px;top:0px;">
												</span>
											</div>
										</div>
										
						          <div class="form-group">
											<label class="col-md-3 control-label">菜单请求地址:
											<span class="required">*</span>
											</label>
											<div class="col-md-8">
												<input id="menuURL" type="text" name="menuURL"  data-required="1" class="form-control input-inline input-medium" placeholder="URL地址"  >
												<span class="help-inline" style="position: absolute;right: 175px;top:0px;">
												</span>
											</div>
										</div>
										
										<div class="form-group">
										<div class="col-md-8" id="checkMsg">
										</div>
										</div>
									
							
						  			<div class="modal-footer">
										<button id="edtiMenuBtn" type="button" class="btn blue" >保存</button>
										<button type="button" id="closeModal" data-dismiss="modal" class="btn btn-default" onclick="System_MenuManager.cleanForm();">关闭</button>
							       </div>
					   </form>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
    var menuId =$("#menuId").val();
    function changeType(){
    var menuType =$("#menuType").val();
    if(menuId==""){   //无ID即未新增的情况下
      if(menuType=="module"){
        $("#menuType").attr("value","menu");
        $("#editMenu").modal("show"); 
     }else if(menuType=="root"){
        $("#menuType").attr("value","module");
        $("#menuURL").attr("value","#");
        $("#menuURL").attr("onlyread","true");
        $("#editMenu").modal("show"); 
     }else{
         alert("不能增加下级！");
     }
     $("#sortNo").attr("value","0");
    }   
  }
</script>
