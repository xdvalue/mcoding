<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="m" uri="http://mcoding.cn/jsp/common" %>
<!-- BEGIN PAGE CONTENT-->
<div class="row">
    <div class="col-md-3">
        <div class="portlet yellow box">
            <div class="portlet-title">
                <div class="caption">
                    <i class="fa fa-cogs"></i>公司架构
                </div>
            </div>
            <div class="portlet-body">
                <div id="organizationTree" class="tree-demo">
                </div>
            </div>
        </div>
    </div>
    <div class="col-md-9">
        <!-- BEGIN EXAMPLE TABLE PORTLET-->
        <div class="portlet box blue">
            <div class="portlet-title">
                <div class="caption">
                    <i class="fa fa-globe"></i>架构列表
                </div>
            </div>
            <div class="portlet-body">
                <m:accessRight menuCode="organizationList" operCode="addOrg">
                    <div id="edit" class="table-toolbar">
                        <div class="btn-group">
                            <span id="addOrg" href="addOrgPage.html" class="ajaxify">
                                <span class="btn green fileinput-button">
                                    <i class="fa fa-plus"></i><span>添加架构</span>
                                </span>
                            </span>
                        </div>
                    </div>
                </m:accessRight>

                <div class="table-responsive">
                    <table id="memberTable" class="table table-striped table-bordered table-hover"></table>
                </div>

                <!-- 删除提示框 -->
                <div id="confirmWin" class="modal fade" tabindex="-1" data-backdrop="confirmWin" data-keyboard="false">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                                <h4 class="modal-title">确认删除架构</h4>
                            </div>
                            <div class="modal-body">
                                <input type="hidden" name="orgId" id="orgId" value=""/>
                                <p style="color: #8b0000;font-size: 15px;">
                                    删除该架构会导致该架构下的用户无法登录系统, 您确定要删除该架构吗?
                                </p>
                            </div>
                            <div class="modal-footer">
                                <button type="button" data-dismiss="modal" class="btn default">取消</button>
                                <button type="button" id="deleteOrgBut" class="btn green">确认</button>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- 禁用确认框-->
                <div id="confirmEnableWin" class="modal fade" tabindex="-1" data-backdrop="confirmEnableWin" data-keyboard="false">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                                <h4 class="modal-title">确认禁用架构</h4>
                            </div>
                            <div class="modal-body">
                                <input type="hidden" name="orgId" id="orgId" value=""/>
                                <p style="color: #8b0000;font-size: 15px;">
                                    禁用会导致属于该架构(包括子架构)下的所有员工无法登录系统, 您确定要禁用该架构吗?
                                </p>
                            </div>
                            <div class="modal-footer">
                                <button type="button" data-dismiss="modal" class="btn default">取消</button>
                                <button type="button" id="disableOrgBut" class="btn green">确认</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- END EXAMPLE TABLE PORTLET-->
    </div>
</div>

<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script src="${basePath}resources/js/custom/base/organization/organizationList.js" type="text/javascript"></script>
<!-- END PAGE LEVEL SCRIPTS -->

<script type="text/javascript">
    ORG_OrganizationList.init();
</script>
      