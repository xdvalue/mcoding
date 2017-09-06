<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="m" uri="http://mcoding.cn/jsp/common" %>
<!-- BEGIN SAMPLE PORTLET CONFIGURATION MODAL FORM-->
<div class="modal fade" id="addRoleWin" tabindex="-1" role="dialog" aria-labelledby="addRoleWin" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                <h4 class="modal-title">增加角色</h4>
            </div>
            <div class="modal-body">
                <form id="roleAddForm" action="#" class="form-horizontal" role="form">
                    <div class="form-group">
                        <label class="control-label col-md-4">角色名称<span class="required">*</span></label>
                        <div class="col-md-8">
                            <input type="text" name="roleName" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-4">角色代号<span class="required">*</span></label>
                        <div class="col-md-8">
                            <input type="text" name="roleCode" class="form-control">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn default" data-dismiss="modal">取消</button>
                <button type="button" class="btn blue" id="saveRoleBut">保存</button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
<div class="modal fade" id="modifyRoleWin" tabindex="-1" role="dialog" aria-labelledby="modifyRoleWin" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                <h4 class="modal-title">修改角色</h4>
            </div>
            <div class="modal-body">
                <form id="roleModifyForm" action="#" class="form-horizontal" role="form">
                    <input type="hidden" id ="roleId" name="roleId" value="" />
                    <div class="form-group">
                        <label class="control-label col-md-4">角色名称<span class="required">*</span></label>
                        <div class="col-md-8">
                            <input type="text" id ="roleName" name="roleName" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-4">角色代号<span class="required">*</span></label>
                        <div class="col-md-8">
                            <input type="text" id ="roleCode" name="roleCode" class="form-control">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn default" data-dismiss="modal">取消</button>
                <button type="button" class="btn blue" id="modifyRoleBut">保存</button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
<!-- /.modal -->
<!-- END SAMPLE PORTLET CONFIGURATION MODAL FORM-->
<!-- BEGIN PAGE CONTENT-->
<div class="row">
    <div class="col-md-12">
        <!-- BEGIN EXAMPLE TABLE PORTLET-->
        <div class="portlet box light-grey">
            <div class="portlet-title">
                <div class="caption">
                    <i class="fa fa-globe"></i>角色列表
                </div>
            </div>
            <div class="portlet-body">
                <m:accessRight menuCode="roleManager" operCode="addRole">
                    <div class="table-toolbar">
                        <div class="btn-group">
                            <button id="addRole" class="btn green" data-toggle="modal" href="#addRoleWin">
                                <i class="fa fa-plus"></i>增加角色
                            </button>
                        </div>
                    </div>
                </m:accessRight>
                <table id="roleTable" class="table table-striped table-bordered table-hover">

                </table>
            </div>
        </div>
        <!-- END EXAMPLE TABLE PORTLET-->
        <div id="confirmWin" class="modal fade" tabindex="-1" data-backdrop="confirmWin" data-keyboard="false">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                        <h4 class="modal-title">确认删除角色</h4>
                    </div>
                    <div class="modal-body">
                        <input type="hidden" name="roleId" id="roleId" value=""/>
                        <p style="color: #8b0000;font-size: 15px;">
                            删除该角色会导致拥有该角色的用户无法访问相关页面, 您确定要删除该角色吗?
                        </p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" data-dismiss="modal" class="btn default">取消</button>
                        <button type="button" id="deleteRoleBut" class="btn green">确认</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- END PAGE CONTENT-->

<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script type="text/javascript" src="${basePath}resources/js/custom/base/role/roleManager.js"></script>
<!-- END PAGE LEVEL SCRIPTS -->
<script>
    System_Role.init();
</script>

