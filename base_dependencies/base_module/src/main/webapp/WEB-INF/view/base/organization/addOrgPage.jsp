<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row">
    <div class="col-md-12 ">
        <!-- BEGIN SAMPLE FORM PORTLET-->
        <div class="portlet box blue">
            <div class="portlet-title">
                <div class="caption">
                    <i class="fa fa-reorder"></i>
                    <c:choose>
                        <c:when test="${editOrg!=null}">修改组织架构</c:when>
                        <c:otherwise>新建组织架构</c:otherwise>
                    </c:choose>
                </div>
            </div>
            <div class="portlet-body form">
                <form action="#" id="orgForm" class="form-horizontal">
                    <input type="hidden" id="editOrgId" name="orgId" value="${editOrg.orgId}">
                    <div class="form-body">
                        <div class="form-group">
                            <label class="col-md-3 control-label">架构名称
                                <span class="required">*</span>
                            </label>
                            <div class="col-md-9">
                                <input type="text" name="orgName" value="${editOrg.orgName}"
                                       class="form-control input-inline input-medium" placeholder="请输入架构名称" />
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-md-3 control-label">所属上级架构
                                <span class="required">*</span>
                            </label>

                            <div class="col-md-9">
                                <div>
                                    <input type="hidden" name="parentId" value="${parentOrg.orgId}" readonly/>
                                    <input type="text" name="parentOrgName" value="${parentOrg.orgName}"
                                           class="form-control input-inline input-medium" readonly />
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-md-3">地区选择:
                                <span class="required">*</span>
                            </label>
                            <div class="col-md-9">
                                <div class="margin-bottom-5">
                                    <div class="margin-bottom-5" style="float: left;">
                                        <select class="btn dropdown-toggle selectpicker btn default" style="width:155px;"
                                                data-style="btn-primary" name="regionProvId" id="regionProvId">
                                            <option value=''>请选省份/地区</option>
                                            <c:choose>
                                                <c:when test="${editOrg != null}">
                                                    <c:forEach items="${provRegions}" var="prov">
                                                        <c:choose>
                                                            <c:when test="${editOrg.regionProvId == prov.id}">
                                                                <option value="${prov.id}" selected>${prov.name}</option>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <option value="${prov.id}">${prov.name}</option>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </c:forEach>
                                                </c:when>
                                                <c:otherwise>
                                                    <c:forEach items="${provRegions}" var="prov">
                                                        <c:choose>
                                                            <c:when test="${parentOrg.regionProvId == prov.id}">
                                                                <option value="${prov.id}" selected>${prov.name}</option>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <option value="${prov.id}">${prov.name}</option>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </c:forEach>
                                                </c:otherwise>
                                            </c:choose>
                                        </select>
                                    </div>
                                    <div class="margin-bottom-5" style="float: left;margin-left: 10px;">
                                        <input type="hidden" id="editRegionCityId" value="${editOrg.regionCityId}"/>
                                        <select class="btn dropdown-toggle selectpicker btn default" style="width:155px;"
                                                name="regionCityId" id="regionCityId">
                                        </select>
                                    </div>
                                    <div class="margin-bottom-5" style="float: left; margin-left: 10px;">
                                        <input type="hidden" id="editRegionAreaId" value="${editOrg.regionAreaId}"/>
                                        <select class="btn dropdown-toggle selectpicker btn default" style="width:155px;"
                                                name="regionAreaId" id="regionAreaId">
                                        </select>
                                    </div>
                                </div>
                            </div>
                        </div>

                       <%-- <div class="form-group">
                            <label class="col-md-3 control-label">组织架构主管
                                <span class="required">*</span>
                            </label>
                            <div class="col-md-9">
                                <select class="form-control" style="width:155px;" name="orgManagerId" id="orgManagerId">
                                    <option value=''>${editOrg.nickName}</option>
                                </select>
                                <c:if test="${editOrg == null}">
                                    <input type="button" class="btn purple" id="addNewOrgLeader"
                                           style="margin-left:165px;margin-top:-50px;" value="新建主管"/>
                                </c:if>
                            </div>
                        </div>--%>
                        <%--<div style="display:none;" id="addOrgLeader">
                            <div class="form-group">
                                <label class="col-md-3 control-label">主管职位类型
                                    <span class="required">*</span>
                                </label>
                                <div class="col-md-4">
                                    <select class="form-control" style="width:155px;" name="levelType" id="levelType">
                                        <option value=''>请选职位类型</option>
                                        <option value="manage">管理类型</option>
                                        <option value="sell">销售类型</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label">主管职位级别
                                    <span class="required">*</span>
                                </label>
                                <div class="col-md-4">
                                    <select class="form-control" style="width:155px;" name="levelId" id="levelId">
                                    </select>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-md-3 control-label">主管登录账户
                                    <span class="required">*</span>
                                </label>
                                <div class="col-md-4">
                                    <input type="text" name="loginName" id="loginName"
                                           data-required="1" class="form-control input-inline input-medium"
                                           placeholder="请输入主管登录账户">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-md-3 control-label">主管登录昵称
                                    <span class="required">*</span>
                                </label>
                                <div class="col-md-4">
                                    <input type="text" name="nickName" id="nickName"
                                           data-required="1" class="form-control input-inline input-medium"
                                           placeholder="请输入主管登录昵称">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-md-3 control-label">主管登录密码
                                    <span class="required">*</span>
                                </label>
                                <div class="col-md-4">
                                    <input type="password" name="password" data-required="1"
                                           class="form-control input-inline input-medium" placeholder="请输入主管登录密码">
                                </div>
                            </div>
                        </div>--%>

                        <div class="form-actions fluid">
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="col-md-offset-3 col-md-9">
                                        <button type="button" id="addOrgBut"  class="btn purple"><i class="fa fa-check"></i>提交</button>
                                        <a id="backListPage" href="orgManager.html" type="button"
                                           class="btn default ajaxify">返回</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<script src="${basePath}resources/js/custom/base/organization/organization.js" type="text/javascript"></script>
<script>
    ORG_OrganizationManager.init();
</script>