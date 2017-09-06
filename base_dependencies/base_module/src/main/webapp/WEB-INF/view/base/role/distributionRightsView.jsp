<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- BEGIN PAGE CONTENT-->
<div class="row">
    <div class="col-md-12">
        <div class="portlet green box">
            <div class="portlet-title">
                <div class="caption">
                    <i class="fa fa-key"></i>【${role.roleName}】 权限配置
                    <input type="hidden" id="configRoleId" value="${role.roleId}" />
                </div>
            </div>

        </div>
    </div>
</div>
<div class="portlet-body">
    <div class="table-toolbar">
        <div class="btn-group">
            <button id="returnPage" class="btn green ajaxify" href="navigateRole.html">
                <i class="fa fa-share"></i>返回
            </button>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-md-3">
        <div class="portlet yellow box">
            <div class="portlet-title">
                <div class="caption">
                    <i class="fa fa-cogs"></i>菜单模块架构
                </div>
            </div>
            <div class="portlet-body">
                <div id="configMenuManagerTree" class="tree-demo">
                </div>
            </div>
        </div>
    </div>
    <div class="col-md-9">
        <!-- BEGIN SAMPLE TABLE PORTLET-->
        <div class="portlet box blue">
            <div class="portlet-title">
                <div class="caption">
                    <i class="fa fa-globe"></i>菜单栏目对应页面的操作权限配置
                </div>
            </div>
            <div class="portlet-body">
                <div class="table-responsive">
                    <table id="configOperateTab" class="table table-striped table-bordered table-advance table-hover">

                    </table>
                </div>
            </div>
        </div>
        <!-- END SAMPLE TABLE PORTLET-->
    </div>
</div>
<!-- END PAGE CONTENT-->

<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script type="text/javascript" src="${basePath}resources/js/custom/base/role/roleConfManager.js"></script>
<!-- END PAGE LEVEL SCRIPTS -->
<script>
    Role_ConfigManager.init();
</script>

