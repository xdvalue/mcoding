<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="m" uri="http://mcoding.cn/jsp/common" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!-- BEGIN PAGE CONTENT-->
<div class="row">
    <div class="col-md-12">
        <!-- BEGIN EXAMPLE TABLE PORTLET-->
        <div class="portlet box blue">
            <div class="portlet-title">
                <div class="caption">
                    <i class="fa fa-globe"></i>用户列表
                </div>
            </div>
            <div class="portlet-body">
                <m:accessRight menuCode="employeeList" operCode="addEmp">
                    <div id="edit" class="table-toolbar">
                        <div class="btn-group">
                            <span id="addEmp" href="addEmpPage.html" class="ajaxify">
                                <span class="btn green fileinput-button">
                                    <i class="fa fa-plus"></i><span>添加用户</span>
                                </span>
                            </span>
                        </div>
                    </div>
                </m:accessRight>
                <div class="table-responsive">
                    <table id="memberTable" class="table table-striped table-bordered table-hover"></table>
                </div>
                <!-- END EXAMPLE TABLE PORTLET-->
                <div id="confirmWin" class="modal fade" tabindex="-1" data-backdrop="confirmWin" data-keyboard="false">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                                <h4 class="modal-title">确认禁用用户</h4>
                            </div>
                            <div class="modal-body">
                                <input type="hidden" name="userId1" id="userId1" value=""/>
                                <p style="color: #8b0000;font-size: 15px;">
                                    禁用会导致用户无法登录系统, 您确定要禁用该用户吗?
                                </p>
                            </div>
                            <div class="modal-footer">
                                <button type="button" data-dismiss="modal" class="btn default">取消</button>
                                <button type="button" id="disableEmpBut" class="btn green">确认</button>
                            </div>
                        </div>
                    </div>
                </div>
                
                <!-- 分配角色 -->
		        <div class="modal fade" id="selectRole" role="basic" aria-hidden="true">
		            <div class="page-loading page-loading-boxed" loadBar="ok">
		                <img src="${basePath}resources/metronic_v2.0.2/assets/img/loading-spinner-grey.gif" alt="" class="loading">
		                <span>
		                    &nbsp;&nbsp;加载数据中...
		                </span>
		            </div>
		            <div class="modal-dialog">
		                <div class="modal-content">
		                </div>
		            </div>
		        </div>
                
            </div>
        </div>
        <!-- END EXAMPLE TABLE PORTLET-->
 </div>
</div>

<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script src="${basePath}resources/js/custom/base/organization/contactList.js" type="text/javascript"></script>
<!-- END PAGE LEVEL SCRIPTS -->

<script type="text/javascript">
    ORG_ContactList.init();
</script>
      