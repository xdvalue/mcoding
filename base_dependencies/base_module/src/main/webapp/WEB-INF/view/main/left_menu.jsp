<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!-- BEGIN SIDEBAR -->
<div class="page-sidebar-wrapper">
    <div class="page-sidebar navbar-collapse collapse">
        <!-- add "navbar-no-scroll" class to disable the scrolling of the sidebar menu -->
        <!-- BEGIN SIDEBAR MENU -->
        <ul class="page-sidebar-menu" data-auto-scroll="true" data-slide-speed="100">
            <li>
				<!-- BEGIN SIDEBAR TOGGLER BUTTON -->
				<div class="sidebar-toggler hidden-sm hidden-xs">
				</div>
				<!-- BEGIN SIDEBAR TOGGLER BUTTON -->
			</li>
			<li>
				<!-- BEGIN RESPONSIVE QUICK SEARCH FORM -->
				<form class="sidebar-search" action="extra_search.html" method="POST">
					<div class="form-container">
						<!-- <div class="input-box">
							<a href="javascript:;" class="remove">
							</a>
							<input type="text" placeholder="搜索..."/>
							<input type="button" class="submit" value=" "/>
						</div> -->
					</div>
				</form>
				<!-- END RESPONSIVE QUICK SEARCH FORM -->
			</li>

            <c:forEach var="menu" items="${menuTree}">
                <li>
                    <c:choose>
                        <c:when test="${menu.menuCode=='index'}">
                            <li class="start active">
                                <a class="ajaxify start" href="${menu.menuURL}">
                                <i class="fa fa-home"></i>
                                <span class="title">${menu.menuName}</span>
                                <span class="selected"></span>
                                </a>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <a href="javascript:;">
                                <i class="fa fa-sitemap"></i>
                                <span class="title">${menu.menuName}</span>
                                <span class="arrow"></span>
                            </a>
                         </c:otherwise>
                    </c:choose>
                    <c:if test="${menu.children != null and fn:length(menu.children)>0}">
                        <ul class="sub-menu">
                            <c:forEach var="subMenu" items="${menu.children}">
                                
                                <li>
                                    <a class="ajaxify" href="${subMenu.menuURL}">
                                        <i class="fa fa-tags"></i>
                                        ${subMenu.menuName}
                                    </a>
                                </li>
                            </c:forEach>
                        </ul>
                    </c:if>
                </li>
            </c:forEach>
        </ul>
        <!-- END SIDEBAR MENU -->
    </div>
</div>
<!-- END SIDEBAR -->