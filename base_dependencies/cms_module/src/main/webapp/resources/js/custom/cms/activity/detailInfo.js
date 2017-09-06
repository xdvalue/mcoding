/**
 * 
 */
var DetailInfo = function() {

	var activityId = $('#detailInfoId').text();
	var oTable = null;

	function initActivityProgramList() {
		var tableId = 'programListTable';
		var headerInfo = [{
		    "sTitle": "节目名称",
		    "mData": "title"
		},
		{
		    "sTitle": "活动主题",
		    "mData": "hostName"
		},
		{
		    "sTitle": "嘉宾名称",
		    "mData": "hostName"
		},
		{
		    "sTitle": "公司",
		    "mData": "company"
		},
		{
		    "sTitle": "顺序",
		    "mData": "sortNo"
		},
		{
		    "sTitle": "微信发送状态",
		    "mDataProp": "",
		    "sDefaultContent": "",
		    "sVisible": false,
		    "fnCreatedCell": function(nTd, sData, oData, iRow, iCol) {
		        if (oData.isWechatNotify == 1) {
		            $(nTd).append("<label class='label label-success'>开<label>");
		        } else {
		            $(nTd).append("<label class='label label-danger'>关<label>");
		        }
		    }
		},
		{
		    "sTitle": "短信发送状态",
		    "mDataProp": "",
		    "sDefaultContent": "",
		    "sVisible": false,
		    "fnCreatedCell": function(nTd, sData, oData, iRow, iCol) {
		        if (oData.isSmsNotify == 1) {
		            $(nTd).append("<label class='label label-success'>开<label>");
		        } else {
		            $(nTd).append("<label class='label label-danger'>关<label>");
		        }
		    }
		},
		{
		    "sTitle": "开始时间",
		    "sDefaultContent": "",
		    "fnCreatedCell": function(nTd, sData, oData, iRow, iCol) {
		        var startTime = new Date(oData.startTime);
		        $(nTd).append(startTime.format('hh:mm'));
		    }
		},
		{
		    "sTitle": "结束时间",
		    "sDefaultContent": "",
		    "fnCreatedCell": function(nTd, sData, oData, iRow, iCol) {
		        var startTime = new Date(oData.endTime);
		        $(nTd).append(startTime.format('hh:mm'));
		    }
		},
		{
		    "sTitle": "操作",
		    "sDefaultContent": "",
		    "fnCreatedCell": function(nTd, sData, oData, iRow, iCol) {
		        $(nTd).append("<span style='margin: 0 5px 0 5px'><span class='btn btn-xs purple ajaxify' href='activityProgram/service/toUpdateActivity?activityProgramId=" + oData.id + "'><i class='fa fa-edit'></i> 编辑 </span>");
		        $(nTd).append("<span style='margin: 0 5px 0 5px'></span><span class='btn btn-xs red' href='#confirmWin' data-toggle='modal' id='" + oData.id + "'><i class='fa fa-lock'></i>删除</span>");
		    }
		}];

		var ajaxUrl = "activityProgram/service/findByActivityId?activityId="
				+ activityId;
		initTable(tableId, headerInfo, ajaxUrl);
	}
	;

	function initOrderInfo() {
		var headerInfo = [{
		    "sTitle": "订单时间",
		    "mDataProp": "",
		    "sDefaultContent": "",
		    "sVisible": false,
		    "fnCreatedCell": function(nTd, sData, oData, iRow, iCol) {
		        if (!oData || !oData.createTime) {
		            return;
		        }
		        $(nTd).append(getDateTimeStr(oData.createTime));
		    }
		},
		{
		    "sTitle": "会员名称",
		    "mData": "memberName"
		},
		{
		    "sTitle": "项目名称",
		    "mData": "projectName"
		},
		{
		    "sTitle": "订单金额",
		    "mData": "amountTotal"
		},
		{
		    "sTitle": "减免金额",
		    "mData": "amountReduce"
		},
		{
		    "sTitle": "优惠金额",
		    "mData": "amountDiscount"
		},
		{
		    "sTitle": "折扣",
		    "mData": "discount"
		},
		{
		    "sTitle": "支付金额",
		    "mData": "amountPay"
		},
		{
		    "sTitle": "支付方式",
		    "mData": "incomeStr"
		},
		{
		    "sTitle": "负责员工",
		    "mData": "employeeName"
		}];

		var ajaxUrl = "storeForDashboard/findOrderInfo.html?storeId=" + storeId;
		initTable(tableId, headerInfo, ajaxUrl);
		}
	
		function initMemberInfo() {
		    var headerInfo = [
	            { "sTitle": "编号", "mData": "wxMemberId" },
	            { "sTitle": "名称", "mData": "name" },
	            { "sTitle": "电话", "sDefaultContent": "",  
	            	"fnCreatedCell": function(nTd, sData, oData, iRow, iCol) {
	            		var memberExtInfo = JSON.parse(oData.memberExtInfo);
	            		$(nTd).append(memberExtInfo.mobile); } },
	            { "sTitle": "性别", "sDefaultContent": "",  
	            	"fnCreatedCell": function(nTd, sData, oData, iRow, iCol) {
	            		var statusStr = '';
	            		if (oData.gender == 1) { statusStr = '男'; } 
	            		else if (oData.gender == 2) { statusStr = '女'; }
	            		$(nTd).append(statusStr); } },
	            { "sTitle": "email", "sDefaultContent": "",  
	            	"fnCreatedCell": function(nTd, sData, oData, iRow, iCol) {
	            		var memberExtInfo = JSON.parse(oData.memberExtInfo);
	            		var statusStr = memberExtInfo.email;
	            		$(nTd).append(statusStr); } },
	            { "sTitle": "公司名称", "mData": "companyName" }];

		var ajaxUrl = "memberRef/service/queryApplyMenber?activityId=" + activityId;
		initTable("applyMemberTable", headerInfo, ajaxUrl);
	}

	function initTable(tableId, headerInfo, ajaxUrl) {
		$('#' + tableId).empty();
		oTable = $('#' + tableId).DataTable({
			"fnServerParams" : function(aoData) {
				// aoData.push({"name": "orgId", "value": currtOrgId});
			},
			"sAjaxSource" : ajaxUrl,
			"sAjaxDataProp" : "queryResult",
			"bFilter" : false,
			"bInfo" : true,
			"bJQueryUI" : true,
			"bLengthChange" : true,
			"bPaginate" : true,
			"bProcessing" : true,
			"bSort" : false,
			"bSortClasses" : true,
			"bStateSave" : false,
			"bAutoWidth" : true,
			"bSortCellsTop" : true,
			"iTabIndex" : 1,
			"sServerMethod" : "POST",
            "aoColumnDefs" : [ {
        		sDefaultContent : '',
        		aTargets : [ '_all' ]
        	} ],
			"bServerSide" : true,
			"aoColumns" : headerInfo,
			"aLengthMenu" : [ [ 10, 20, 30, 40, 50 ], [ 10, 20, 30, 40, 50 ] ],
			"iDisplayLength" : 10,
			"oLanguage" : {
				"sProcessing" : "努力加载中...",
				"sLengthMenu" : "显示 _MENU_ 条记录",
				"sInfoEmpty" : "搜索结果为0条记录",
				"sInfoFiltered" : "(从 _MAX_ 条记录中过滤出)",
				"sZeroRecords" : "没有您要搜索的内容",
				"sSearch" : "搜索：",
				"sInfo" : "当前显示 _START_ 到 _END_ 总共 _TOTAL_ 条记录",
				"oPaginate" : {
					"sFirst" : "首页",
					"sPrevious" : "上一页",
					"sNext" : "下一页",
					"sLast" : "末页"
				}
			}
		});
	}
	;

	var addProgram = function() {
		
		var programForm = $("#programForm");
		if (!programForm.valid()) {
			return;
		}

		var requestURL = "activityProgram/service/create";
		var tips = "增加失败!";

		var param = programForm.serializeArray();
		param = serializeObject(param);

		var StrstartTime = param.startTime;
		StrstartTime = StrstartTime.replace(/-/g, '/');
		var dateStart = new Date(StrstartTime);
		var iStartTime = dateStart;
		param.startTime = iStartTime;

		var EndTime = param.endTime;
		EndTime = EndTime.replace(/-/g, '/');
		var dateEnd = new Date(EndTime);
		var iEndTime = dateEnd;
		param.endTime = iEndTime;
		
		if(param.endTime <= param.startTime){
			bootbox.alert("结束时间不能小于开始时间");
			return;
		}

		$.ajax({
			type : "POST",
			url : requestURL,
			data : JSON.stringify(param),
			dataType : "json",
			contentType : "application/json",
			success : function(data) {
				$('#myLink').modal('hide');
				if (!data || data.status != '00') {
					bootbox.alert(tips + '原因是' + data.msg);
					return;
				}
				$('#programListTable').DataTable().fnDraw();
				
				return;
			},
			error : function() {
				bootbox.alert(tips);
				return;
			}
		});
	};

	function getDateTimeStr(nDate) {
		if (!nDate) {
			return '';
		}
		var entryTime = new Date(nDate);
		var year = entryTime.getUTCFullYear();
		var month = entryTime.getMonth() + 1;
		var day = entryTime.getDate();
		return year + "-" + month + "-" + day;
	}

	// 初始化按钮事件
	function initEvent() {
		$("#confirmWin").on("show.bs.modal", function(e) {
			var id = $(e.relatedTarget).attr("id");
			$("#id").val('').val(id);
		});
		$("#myModal").on("show.bs.modal", function(e) {
			$("#updateId").val('').val(activityId);
		});
		$('#liStoreEmployeeList').click(function() {
			oTable.fnDestroy();
			initActivityProgramList();
		});
		$('#liStoreIncomeList').click(function() {
			oTable.fnDestroy();
			initOrderInfo();
		});
		$('#liStoreMemberList').click(function() {
			oTable.fnDestroy();
			initMemberInfo();
		});
		$("#addprogram").on("click", function() {
			addProgram();
		});
		$("#deleteActivityProgram").on("click", function() {
			var param = {
				activityProgramId : $("#id").val()
			};
			$.ajax({
				type : "POST",
				dataType : "json",
				url : "activityProgram/service/delete",
				data : param,
				success : function(data) {
					$("#confirmWin").modal("hide");
					if (data.status == '00') {
						bootbox.alert("删除成功！");
						oTable.fnReloadAjax(); // 刷新datatable列表
					} else {
						bootbox.alert("删除失败！");
					}
				}
			});
		});

	}

	var handleDatetimePicker = function() {
		$(".form_datetime").datetimepicker({
			format : "yyyy-mm-dd hh:ii:ss",
			autoclose : true,
			todayBtn : true,
			startDate : new Date(),
			pickerPosition : "bottom-left",
			minuteStep : 10
		});
	};

	return {
		init : function() {
			initEvent();
			initActivityProgramList();
			initMemberInfo();
			handleDatetimePicker();
		}
	};
}();