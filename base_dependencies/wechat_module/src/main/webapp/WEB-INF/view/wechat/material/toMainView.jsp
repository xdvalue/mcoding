<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- 
<LINK href="resources/css/wechat/base2fde18.css" rel="stylesheet" type="text/css">
<LINK href="resources/css/wechat/media_list2f12f7.css" rel="stylesheet" type="text/css">
 -->
<style>
<!--
.mix-grid .mix {
    position: relative;
    overflow: hidden;
    margin-bottom: 15px;
}
-->
</style>

<!-- BEGIN PAGE CONTENT-->
<div class="row">
	<div class="col-md-12">
		<!-- BEGIN EXAMPLE TABLE PORTLET-->
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>素材管理
				</div>
			</div>
			<div class="portlet-body">
			    <ul class="nav nav-tabs">
			        <li class="active" data-stage="-1"><a href="#tab_b" data-toggle="tab">图片</a></li>
			    </ul>
			    <div class="tab-content">
                    
				    <div class="tab-pane table-toolbar active" id="tab_b">
					    <div class="row mix-grid">
							<div class="col-md-3 col-sm-3 mix">
							    <img class="img-responsive" src="resources/images/img1.jpg" alt="">
							</div>
							<div class="col-md-3 col-sm-3 mix">
							    <img class="img-responsive" src="resources/images/img1.jpg" alt="">
							</div>
							<div class="col-md-3 col-sm-3 mix">
							    <img class="img-responsive" src="resources/images/img1.jpg" alt="">
							</div>
							<div class="col-md-3 col-sm-3 mix">
							    <img class="img-responsive" src="resources/images/img1.jpg" alt="">
							</div>
							<div class="col-md-3 col-sm-3 mix">
							    <img class="img-responsive" src="resources/images/img1.jpg" alt="">
							</div>
						</div>
						<button class="btn green btn-block" id="imgPageNum" data-page-num="0">更多</button>
				    </div>
				    <%-- <div class="tab-pane table-toolbar" id="tab_c">
					    <div class="btn-group">
					    	<span href="capitalProjectItem/service/toAddView?capitalProjectId=${capitalProject.id}" class="ajaxify">
						    	<span class="btn green fileinput-button"> <i
							    	class="fa fa-plus"></i><span>添加语音</span>
						        </span>
    						</span>
					    </div>
					    <div class="table-responsive">
					        <table id="dataTableC" class="table table-striped table-bordered table-hover"></table>
				        </div>
				    </div>
				    <div class="tab-pane table-toolbar" id="tab_d">
					    <div class="btn-group">
					    	<span href="capitalProjectItem/service/toAddView?capitalProjectId=${capitalProject.id}" class="ajaxify">
						    	<span class="btn green fileinput-button"> <i
							    	class="fa fa-plus"></i><span>添加视频</span>
						        </span>
    						</span>
					    </div>
					    <div class="table-responsive">
					        <table id="dataTableD" class="table table-striped table-bordered table-hover"></table>
				        </div>
				    </div> --%>
                </div>
				
				<!-- END EXAMPLE TABLE PORTLET-->
			</div>
		</div>
		<input type="hidden" value="${account.originId }" id="originId">
		<!-- END EXAMPLE TABLE PORTLET-->

	</div>
</div>


<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script src="${basePath}resources/js/custom/wechat/material/main.js"
	type="text/javascript"></script>
<!-- END PAGE LEVEL SCRIPTS -->

<script type="text/javascript">
    DataTableList.init();
</script>
