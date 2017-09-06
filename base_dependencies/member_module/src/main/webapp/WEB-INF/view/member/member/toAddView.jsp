<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<div class="row">
	<div class="col-md-12 ">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-reorder"></i>
					<c:choose>
						<c:when test="${member!=null}">修改</c:when>
						<c:otherwise>添加</c:otherwise>
					</c:choose>
				</div>
			</div>
			<div class="portlet-body form">
				<form action="#" id="memberForm" class="form-horizontal">
				    <input name="id" value="${member.id }" type="hidden" />

					<div class="form-body">
						<div class="form-group">
							<label class="col-md-3 control-label">用户昵称 </label>
							<div class="col-md-9" >
								<input type="text" name="name" placeholder="请输入用户名称" required
									value="${member.name}" class="form-control input-inline input-medium">
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-md-3 control-label">头像图片 <span
								class="required">*</span>
							</label>
							<div class="col-md-4">
							    <div class="input-group">
							       <input type="text" name="headImgUrl" id="imgUrlInput" required value="${member.headImgUrl }"
								        class="form-control input-inline input-medium" placeholder="头像图片 ">
								    <span class="input-group-btn">
									    <button class="btn blue" id="imgUrlButton" type="button">选择图片</button>
								    </span>
							    </div>
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-md-3 control-label">性别 </label>
							<div class="col-md-4" id=gender>
								<select name="gender" class="form-control" required id="chooseModule_select">
                                    <option value="-1">请选择</option>
                                    <option value="1" <c:if test="${member.gender==1}"> selected </c:if> >男</option>
                                    <option value="2" <c:if test="${member.gender==2}"> selected </c:if> >女</option>
                                </select>
							</div>
						</div>
						
						<!-- <div class="form-group">
							<label class="col-md-3 control-label">生日 </label>
							<div class="col-md-5" id="birthdayTime">
								<div class="input-group date form_datetime">
									<input type="text" size="10" readonly class="form-control"
										name="birthday" >
									<span class="input-group-btn">
										<button class="btn default date-reset" type="button">
											<i class="fa fa-times"></i>
										</button>s
									</span> <span class="input-group-btn">
										<button class="btn default date-set" type="button">
											<i class="fa fa-calendar"></i>
										</button>
									</span>
								</div>
							</div>
						</div> -->
						
						<div class="form-actions">
							<div class="col-md-offset-3 col-md-9">
								<button
									<c:choose>
									<c:when test="${member!=null}">id="singleUpdate"</c:when>
									<c:otherwise>id="singleAdd"</c:otherwise>
								</c:choose>
									type="button" class="btn purple">
									<i class="fa fa-check"></i> 提 交
								</button>
								<button id="backListPage" type="button" class="btn default ajaxify"
								<c:choose>
						            <c:when test="${parent==null}"> href="member/service/toMainView.html" </c:when>
						            <c:otherwise> href="member/service/toDepartmentListView?parentId=${parent.id }" </c:otherwise>
					            </c:choose>
								>返 回</button>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
<script src="${basePath}resources/js/custom/member/member/add.js"
	type="text/javascript"></script>
<script src="${basePath}resources/js/cityselect/jquery.cityselect.js"
	type="text/javascript"></script>
<script src="${basePath}resources/js/common/kindeditor.js" 
	type="text/javascript"></script>	
<script type="text/javascript">
    DataTableList.init();
	
</script>
