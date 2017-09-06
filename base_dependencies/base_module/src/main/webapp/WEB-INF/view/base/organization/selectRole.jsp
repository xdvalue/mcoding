<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
    <h4 class="modal-title">分配角色</h4>
</div>
<div class="modal-body">
    <div class="row">
        <div class="col-md-12">
            <form id="roleListForm" class="form-horizontal" action="#">
                <div class="form-body">
                    <div class="form-group">
                        <label class="control-label col-md-2"></label>
                        <input type="hidden" id="userId"  name="userId" value="${configUserId}" />
                        <div class="col-md-9">
                            <div style="font-size: 14px;margin-bottom: 10px;">
                                <span>系统角色列表</span>
                                <span style="margin-left: 116px;">用户【${nickName}】角色列表</span>
                            </div>
                            <select multiple="multiple" class="multi-select" id="mySelect" name="mySelect">
                                <c:forEach items="${roleList}" var="role" >
                                    <option ${role.selected} value="${role.roleId}">${role.roleName}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<div class="modal-footer">
    <button type="button" class="btn default" data-dismiss="modal">取消</button>
    <button id="saveRoleList" type="button" class="btn blue">保存</button>
</div>

<script type="text/javascript">
    $('#mySelect').multiSelect();
</script>