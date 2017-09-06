<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- BEGIN PAGE CONTENT-->
<div class="row">
	<div class="col-md-12">
		<!-- BEGIN EXAMPLE TABLE PORTLET-->
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>部门列表<div id="divParentId" parentId="${parent.id}" />
				</div>
			</div>
			<div class="portlet-body">
				<div id="edit" class="table-toolbar">
					<div class="btn-group">
						<span id="addmemberDepartment" href="memberDepartment/service/toAddView?parentId=${parent.id}" class="ajaxify">
							<span class="btn green fileinput-button"> <i
								class="fa fa-plus"></i><span>添加部门</span>
						    </span>
						</span>
						<span id="addmemberDepartment" class="ajaxify"
						<c:choose>
						    <c:when test="${parent.departmentType == 100 }">href="memberDepartment/service/toMainView"</c:when>
						    <c:otherwise>href="memberDepartment/service/toDepartmentListView?parentId=${parent.parentId }"</c:otherwise>
					    </c:choose> >
							<span class="btn blue fileinput-button"><span>返回</span>
						    </span>
						</span>
					</div>
				</div>
				<div class="table-responsive">
					<table id="dataTable"
						class="table table-striped table-bordered table-hover"></table>
				</div>
				<!-- END EXAMPLE TABLE PORTLET-->
			</div>
		</div>
		<!-- END EXAMPLE TABLE PORTLET-->

	</div>
</div>


<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script src="${basePath}resources/js/custom/member/memberDepartment/departmentList.js"
	type="text/javascript"></script>
<!-- END PAGE LEVEL SCRIPTS -->

<script type="text/javascript">
    DataTableList.init();
</script>
