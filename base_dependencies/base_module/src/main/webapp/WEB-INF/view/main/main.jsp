<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="m" uri="http://mcoding.cn/jsp/common" %>
<head>
    <link href="${basePath}resources/css/ranking.css" rel="stylesheet" type="text/css"/>
</head>
<!-- 首页主体部分-->
<!-- BEGIN DASHBOARD STATS -->
<m:accessRight menuCode="index" operCode="dataView">
<div class="row">
	<input type="hidden" id="userId" value="${userId}"/>
	<%-- <div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
		<div class="dashboard-stat green">
			<div class="visual">
				<i class="fa fa-user"></i>
			</div>
			<div class="details">
				<div class="number">
					 ${memberNum}
				</div>
				<div class="desc">
					有${memberNum}个会员
				</div>
			</div>
			<a class="more ajaxify" href="memberListPageView.html">
				 查看详情 <i class="m-icon-swapright m-icon-white"></i>
			</a>
		</div>
	</div>
	<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
		<div class="dashboard-stat blue">
			<div class="visual">
				<i class="fa fa-user"></i>
			</div>
			<div class="details">
				<div class="number">
					 ${pointNum}
				</div>
				<div class="desc">
					有${pointNum}条积分记录
				</div>
			</div>
			<a class="more ajaxify" href="memberPointListPageView.html">
				 查看详情 <i class="m-icon-swapright m-icon-white"></i>
			</a>
		</div>
	</div> --%>
	<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
		<div class="dashboard-stat yellow">
			<div class="visual">
				<i class="fa fa-tasks"></i>
			</div>
			<div class="details">
				<div class="number">
					<input type="hidden" id="tasklnum" value="${productNum}"/>
					${productNum}
				</div>
				<div class="desc">
					 录了${productNum}个商品
				</div>
			</div>
			<a class="more ajaxify" href="productList.html">
				 查看详情 <i class="m-icon-swapright m-icon-white"></i>
			</a>
		</div>
	</div>
</div>
</m:accessRight>

<!-- <div class="row ">
	<div class="col-md-6 col-sm-6">
		
		<div class="portlet box purple tasks-widget">
		<div class="portlet-title">
			<div class="caption">
				<i class="fa fa-file"></i>文件共享
			</div>
			<div class="actions">
				<div class="btn-group">
					<a class="btn btn-sm default" data-toggle="modal" href="#addFile">
						<i class="fa fa-plus"></i> 添加共享文件
					</a>
				</div>
			</div>
		</div>
		<div class="portlet-body">
			<div class="task-content">
				<div class="scroller" style="height: 405px;" data-always-visible="1" data-rail-visible1="1">
					START TASK LIST
					<ul class="task-list" id="fileShareList"></ul>
					END START TASK LIST
				</div>
			</div>
			<div class="task-footer">
				<span class="pull-right">
					<a class="ajaxify" href="fileCenterList.html">
						 查看更多
					</a> <i class="m-icon-swapright m-icon-gray"></i>
					 &nbsp;
				</span>
			</div>
		</div>
	</div>
	
	</div>
	<div class="col-md-6 col-sm-6">
		<div class="portlet">
	<div class="portlet-title line">
		<div class="caption">
			<i class="fa fa-comments"></i>留言
		</div>
		<div class="tools">
			<a href="" class="reload" id="reloadMessage">
			</a>
		</div>
	</div>
	<div class="portlet-body" id="chats">
		<div id="myChats" class="scroller" style="height: 375px;" data-always-visible="1" data-rail-visible1="1">
			<ul class="chats" id="listempty">
			</ul>
		</div>
		<div class="chat-form" >
		<form id="messageForm" onsubmit="messageSend();return false;">
			<div class="input-cont">
				<input id="messagecontent" name="messagecontent" class="form-control" type="text" placeholder="请输入您的留言消息..."/>
				<input id="messageType" name="messageType" class="form-control" type="hidden" value="0"/>
			</div>
			<div class="btn-cont" >
				<span class="arrow">
				</span>
				<button type="button" onClick="javascript:messageSend();" class="btn blue icn-only" id="sentMessageBtn">
					<i class="fa fa-check icon-white"></i>
				</button>
			</div>
		</form>
		</div>
	</div>
</div>
	</div>
</div> -->

<div class="clearfix"></div>

<div class="modal fade" id="selectpeople" tabindex="-1" role="dialog" aria-labelledby="selectpeople" aria-hidden="true" style="text-align:center;margin-top:10%">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
               <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                <h4 class="modal-title">通讯录查询</h4>
            </div>
            <div class="modal-body">
             <label class="control-label" style="float: left;">请输入名字：
              </label>
               <div class="col-md-7">
               <input type="text" id ="peopelName" name="peopelName" class="form-control">
               </div>
                <button type="button" class="btn default yellow" id="selectpeopelName">查询</button>
            </div>
            <div class="modal-footer">
            <table id="peoplelist" class="table table-striped table-bordered bootstrap-datatable">
			<thead>
				<tr>
	             <th>姓名</th>
	              <th>登陆账号</th>
	              <th>电话号码</th>
	              <th>QQ号码</th>
	              <th>邮箱</th>
	            </tr>
			</thead>
			<tbody></tbody>
            </table>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>

<%-- <script src="${basePath}resources/js/main/main.js" type="text/javascript"></script> --%>
<!-- END PAGE LEVEL SCRIPTS -->
<script type="text/javascript">
	//noticeList.init();
	/* $("#myChats").slimScroll({
		start: "buttom",
        allowPageScroll: true, 
        size: '7px',
        color: ($(this).attr("data-handle-color")  ? $(this).attr("data-handle-color") : '#bbb'),
        railColor: ($(this).attr("data-rail-color")  ? $(this).attr("data-rail-color") : '#eaeaea'),
        position: isRTL ? 'left' : 'right',
        height: height,
        alwaysVisible: ($(this).attr("data-always-visible") == "1" ? true : false),
        railVisible: ($(this).attr("data-rail-visible") == "1" ? true : false),
        disableFadeOut: true
    }); */
</script>