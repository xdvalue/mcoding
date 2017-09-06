<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- BEGIN HEADER -->
<div class="header navbar navbar-fixed-top">
    <!-- BEGIN TOP NAVIGATION BAR -->
    <div class="header-inner">
        <!-- BEGIN LOGO -->
        <a class="navbar-brand" href="index.html" style="margin-top:5px;">
            <img src="${basePath}resources/images/top_logo2.png" alt="logo" class="img-responsive" style="margin-top:-13px;max-width:130%;"/>
        </a>
        <!-- END LOGO -->
        <!-- BEGIN RESPONSIVE MENU TOGGLER -->
        <a href="javascript:;" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <img src="${basePath}resources/metronic_v2.0.2/assets/img/menu-toggler.png"/>
        </a>
        <!-- END RESPONSIVE MENU TOGGLER -->
        <!-- BEGIN TOP NAVIGATION MENU -->
        <ul class="nav navbar-nav pull-right">
            <!-- BEGIN USER LOGIN DROPDOWN -->
            <li class="dropdown user">
                <input type="hidden" id="xingrunLoginUserId" value="${userId}">
                <input type="hidden" id="loginUserIsManager" value="${loginUserIsManager}">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="true">
                    <img alt="" src="${basePath}resources/images/manlogo.png" width="30px"/>
                    <span class="username">${nickName}</span>
                    <i class="fa fa-angle-down"></i>
                </a>
                <ul class="dropdown-menu">
                    <li>
                        <a class="ajaxify" href="userProfile.html">
                            <i class="fa fa-user"></i> 个人资料中心
                        </a>
                    </li>
                    <li>
                        <a class="ajaxify" href="changePwd.html">
                            <i class="fa fa-key"></i> 更改密码
                        </a>
                    </li>
                    <li class="divider">
                    </li>
                    <li>
                        <a href="loginout.html">
                            <i class="fa fa-key"></i> 退出
                        </a>
                    </li>
                </ul>
            </li>
            <!-- END USER LOGIN DROPDOWN -->
        </ul>
        <!-- END TOP NAVIGATION MENU -->
    </div>
    <!-- END TOP NAVIGATION BAR -->
</div>
<!-- END HEADER -->
<div class="clearfix">
</div>
