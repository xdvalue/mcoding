<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="m" uri="http://mcoding.cn/jsp/common" %>
<!-- BEGIN PAGE CONTENT-->
<div class="row">
    <div class="col-md-3">
        <div class="portlet yellow box">
            <div class="portlet-title">
                <div class="caption">
                    <i class="fa fa-cogs"></i>菜单模块管理
                </div>
            </div>
            <div class="portlet-body">
                <div id="menuManagerTree" class="tree-demo">
                </div>
                <div class="alert alert-success no-margin margin-top-10">
                    注意! 打开或右键点击操作
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
            <div class="portlet-body" >
            
             <m:accessRight menuCode="menuModuleConf" operCode="addOperator">
                <div id="edit" class="table-toolbar" style="display:none;" >
                    <div class="btn-group">
                    <a data-toggle="modal" href="#editBtn" onclick="cleanForm('#form')" >
                        <button class="btn green" id="sample_editable_1_new" >
                            <i class="fa fa-plus"></i> 增加页面操作配置
                        </button>
				    </a>
                    </div>
                </div>
              </m:accessRight>  
                
                <div class="table-responsive">
                    <table id="menuOperateTab" class="table table-striped table-bordered table-advance table-hover">
                    </table>
                </div>
            </div>
        </div>
        <!-- END SAMPLE TABLE PORTLET-->
    </div>
</div>
<!-- END PAGE CONTENT-->
<jsp:include page="editMenu.jsp"/>
<jsp:include page="editBtn.jsp"/>

<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script type="text/javascript" src="${basePath}resources/js/custom/base/menu/menuManager.js"></script>
<!-- END PAGE LEVEL SCRIPTS -->
<script>
    System_MenuManager.init();
</script>



