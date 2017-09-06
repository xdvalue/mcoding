<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- BEGIN PAGE CONTENT-->

<div class="row">
    <div class="col-md-3">
        <div class="portlet yellow box">
            <div class="portlet-title">
                <div class="caption">
                    <i class="fa fa-cogs"></i>团队/集团架构
                </div>
            </div>
            <div class="portlet-body">
                <div id="groupManagerTree" class="tree-demo">
                </div>
            </div>
        </div>
    </div>
    <div class="col-md-9">
        <!-- BEGIN SAMPLE TABLE PORTLET-->
        <div class="portlet box blue">
            <div class="portlet-title">
                <div class="caption">
                    <i class="fa fa-globe"></i>组员列表
                </div>
            </div>
            <div class="portlet-body">
                <div class="table-responsive">
                    <table id="memberOperateTab" class="table table-striped table-bordered table-advance table-hover">

                    </table>
                </div>
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
        <!-- END SAMPLE TABLE PORTLET-->
    </div>
</div>
<!-- END PAGE CONTENT-->

<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script type="text/javascript" src="${basePath}resources/js/custom/base/role/rightManager.js"></script>
<!-- END PAGE LEVEL SCRIPTS -->
<script type="text/javascript">
    System_RightManager.init();
</script>

