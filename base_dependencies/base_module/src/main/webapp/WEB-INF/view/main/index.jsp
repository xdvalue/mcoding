<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en" class="no-js">
<!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
    <meta charset="utf-8"/>
<title>熹鼎科技-SCRM运营平台</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="width=device-width, initial-scale=1" name="viewport"/>
    <meta content="" name="description"/>
    <meta content="" name="author"/>
    <!-- BEGIN GLOBAL MANDATORY STYLES-->
    <link href="${basePath}resources/css/member.css" rel="stylesheet" type="text/css"/>
    <link href="${basePath}resources/metronic_v2.0.2/assets/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
    <link href="${basePath}resources/metronic_v2.0.2/assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <%--<link href="${basePath}resources/metronic_v2.0.2/assets/plugins/uniform/css/uniform.default.css" rel="stylesheet" type="text/css"/>--%>
    <!-- END GLOBAL MANDATORY STYLES -->
    
    <!-- BEGIN PAGE LEVEL PLUGIN STYLES -->
    <link href="${basePath}resources/metronic_v2.0.2/assets/plugins/bootstrap-switch/css/bootstrap-switch.min.css" rel="stylesheet" type="text/css"/>
    <link href="${basePath}resources/metronic_v2.0.2/assets/plugins/bootstrap-select/bootstrap-select.min.css" rel="stylesheet" type="text/css"/>
    <link href="${basePath}resources/metronic_v2.0.2/assets/plugins/bootstrap-timepicker/css/bootstrap-timepicker.min.css" rel="stylesheet" type="text/css" />
    <%-- <link href="${basePath}resources/metronic_v2.0.2/assets/plugins/bootstrap-datepicker/css/datepicker.css" rel="stylesheet" type="text/css"/>
    <link href="${basePath}resources/metronic_v2.0.2/assets/plugins/bootstrap-daterangepicker/daterangepicker-bs3.css" rel="stylesheet" type="text/css"/> --%>
    <link href="${basePath}resources/metronic_v2.0.2/assets/plugins/bootstrap-datetimepicker/css/datetimepicker.css" rel="stylesheet" type="text/css" />
    
    <link href="${basePath}resources/metronic_v2.0.2/assets/plugins/gritter/css/jquery.gritter.css" rel="stylesheet" type="text/css"/>
    <link href="${basePath}resources/metronic_v2.0.2/assets/plugins/jquery-multi-select/css/multi-select.css" rel="stylesheet" type="text/css"/>
    <link href="${basePath}resources/metronic_v2.0.2/assets/plugins/jstree/dist/themes/default/style.min.css" rel="stylesheet" type="text/css"/>
    
    <%-- <link href="${basePath}resources/metronic_v2.0.2/assets/plugins/fullcalendar/fullcalendar/fullcalendar.css" rel="stylesheet" type="text/css"/> --%>
    <%-- <link href="${basePath}resources/metronic_v2.0.2/assets/plugins/jqvmap/jqvmap/jqvmap.css" rel="stylesheet" type="text/css"/> --%>
    <%-- <link href="${basePath}resources/metronic_v2.0.2/assets/plugins/jquery-easy-pie-chart/jquery.easy-pie-chart.css" rel="stylesheet" type="text/css"/> --%>
    
    <%-- <link href="${basePath}resources/metronic_v2.0.2/assets/plugins/select2/select2.css"  rel="stylesheet" type="text/css" />
    <link href="${basePath}resources/metronic_v2.0.2/assets/plugins/select2/select2-metronic.css" rel="stylesheet" type="text/css" /> --%>
    
    <link href="${basePath}resources/metronic_v2.0.2/assets/plugins/data-tables/DT_bootstrap.css" rel="stylesheet" />
    <!-- END PAGE LEVEL PLUGIN STYLES -->

    <!-- BEGIN THEME STYLES -->
    <link href="${basePath}resources/metronic_v2.0.2/assets/css/style-metronic.css" rel="stylesheet" type="text/css"/>
    <link href="${basePath}resources/metronic_v2.0.2/assets/css/style.css" rel="stylesheet" type="text/css"/>
    <link href="${basePath}resources/metronic_v2.0.2/assets/css/style-responsive.css" rel="stylesheet" type="text/css"/>
    <link href="${basePath}resources/metronic_v2.0.2/assets/css/plugins.css" rel="stylesheet" type="text/css"/>
    <link href="${basePath}resources/metronic_v2.0.2/assets/css/themes/default.css" rel="stylesheet" type="text/css" id="style_color"/>
    
    <link href="${basePath}resources/metronic_v2.0.2/assets/css/pages/tasks.css" rel="stylesheet" type="text/css"/>
    <link href="${basePath}resources/metronic_v2.0.2/assets/css/print.css" rel="stylesheet" type="text/css" media="print"/>
    <link href="${basePath}resources/metronic_v2.0.2/assets/css/custom.css" rel="stylesheet" type="text/css"/>
    <link href="${basePath}resources/metronic_v2.0.2/assets/css/pages/profile.css" rel="stylesheet" type="text/css"/>
    
    <!-- END THEME STYLES -->
    <!-- BEGIN ligerUI STYLES -->
    <link href="${basePath}resources/js/ligerUI_1.2.3/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
    <link href="${basePath}resources/js/ligerUI_1.2.3/skins/Gray2014/css/all.css" rel="stylesheet" type="text/css" />
    <!-- END ligerUI STYLES -->
    
    <!-- custom Control -->
    <%-- <link rel="stylesheet" href="${basePath}resources/css/jquery-labelauty.css"> --%>

    <!-- <link rel="shortcut icon" href="favicon.ico"/> -->
    <link rel="icon" type="image/png" href="${basePath}resources/images/pagelogo.png"/>
    
    <!-- kindeditor-start -->
    <link rel="stylesheet" href="${basePath}resources/js/kindeditor/themes/default/default.css" />
    <!-- kindeditor-end -->
    
</head>
<!-- END HEAD -->

<!-- BEGIN BODY -->
<body class="page-header-fixed">
    <!-- 首页布局的页头模块 -->
    <jsp:include page="header.jsp"/>

    <!-- BEGIN CONTAINER -->
    <div class="page-container">
        <jsp:include page="left_menu.jsp"/>
        <!-- BEGIN CONTENT -->
        <div class="page-content-wrapper">
            <div class="page-content">
                <div class="clearfix"></div>
                <div class="page-content-body">
                    <!-- ajax 动态加载内容区域 -->
                </div>
            </div>
        </div>
        <!-- END CONTENT -->
    </div>
    <!-- END CONTAINER -->

    <!--首页布局的页脚模块-->
    <jsp:include page="footer.jsp"/>
</body>
<!-- END BODY -->
</html>