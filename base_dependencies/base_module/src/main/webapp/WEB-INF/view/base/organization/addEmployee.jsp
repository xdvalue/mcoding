<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="row">
    <div class="col-md-12 ">
        <div class="portlet box blue">
            <div class="portlet-title">
                <div class="caption">
                    <i class="fa fa-reorder"></i>
                    <c:choose>
                        <c:when test="${editUser!=null}">修改用户</c:when>
                        <c:otherwise>增加用户</c:otherwise>
                    </c:choose>
                </div>
            </div>
            <div class="portlet-body form">
                <form action="#" id="employeeForm" class="form-horizontal">
                    <input type="hidden" id="userId" name="userId" value="${editUser.userId}"/>
                    <input type="hidden" id="editUserLevelId" value="${userLevel.levelId}"/>
                    <div class="form-body">
                        <div class="form-group">
                            <label class="col-md-3 control-label">登陆账户
                                <span class="required">*</span>
                            </label>
                            <div class="col-md-9" id="login">
                                <input type="text" name="loginName" id="loginName" value="${editUser.loginName}"
                                        <c:if test="${editUser != null}">readonly</c:if>
                                       class="form-control input-inline input-medium" placeholder="请输入登陆账户">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-md-3 control-label">登陆昵称
                                <span class="required">*</span>
                            </label>
                            <div class="col-md-4">
                                <input type="text" name="nickName" id="nickName" value="${editUser.nickName}"
                                       class="form-control input-inline input-medium" placeholder="请输入登陆昵称">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">登陆密码
                                <span class="required">*</span>
                            </label>
                            <div class="col-md-4">
                                <input type="password" name="password" id="password" value="${editUser.password}"
                                       class="form-control input-inline input-medium" placeholder="请输入登陆密码">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">性别</label>
                            <div class="col-md-9">
                                <div class="radio-list">
                                    <label class="radio-inline">
                                    <input type="radio" name="gender"  value="男" <c:if test="${editUser.gender==null||editUser.gender=='男'}">checked</c:if>> 男 </label>
                                    <label class="radio-inline">
                                    <input type="radio" name="gender"  value="女" <c:if test="${editUser.gender=='女'}">checked</c:if>> 女 </label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">邮&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;箱
                            </label>
                            <div class="col-md-4">
                                <input type="text" name="email" id="email" value="${editUser.email}"
                                       class="form-control input-inline input-medium" placeholder="请输入你的邮箱(*选填)">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-md-3 control-label">手&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;机
                            </label>
                            <div class="col-md-4">
                                <input type="text" name="mobilePhone" id="mobilePhone" value="${editUser.mobilePhone}"
                                       class="form-control input-inline input-medium" placeholder="请输入手机号码(*选填)">
                            </div>
                        </div>
                        <!-- 默认设置组织ID为1 -->
						<input type="hidden" name='orgId' value='1'/>
                        <%-- <div class="form-group">
                            <label class="col-md-3 control-label">所在部门
                                <span class="required">*</span>
                            </label>
                            <div class="col-md-4">
                                <select class="form-control input-medium" name="orgId" id="orgId" readOnly>
                                    <option value="${org.orgId}" selected>${org.orgName}</option>
                                </select>
                            </div>
                        </div> --%>

                    <div class="form-actions fluid">
                        <div class="col-md-offset-3 col-md-9">
                            <button id="singleAddEmp" type="button" class="btn purple">
                                <i class="fa fa-check"></i> 提 交
                            </button>
                            <c:if test="${editUser == null}">
                                <button id="nextAddEmp" type="button" class="btn green">
                                    <i class="fa fa-check"></i> 提交并且增加下一位用户
                                </button>
                            </c:if>
                            <button id="backListPage" href="employeeContact.html" type="button" class="btn default ajaxify">
                                返 回
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<script src="${basePath}resources/js/custom/base/organization/addEmployee.js" type="text/javascript"></script>
<script type="text/javascript">
    ORG_EmpManager.init();
</script>
