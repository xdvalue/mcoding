var game_gameMemberResultList = function() {
	var tableId = "tableId";
	var oTable = null;

	// 初始化加载表格数据的表头定义
	var initTableHeaderInfo = function() {
		var aoColumns = [
				{
					"sTitle" : "游戏ID",
					"sWidth" : "1%",
					"mData" : "gameid"
				},
				{
					"sTitle" : "游戏名称",
					"sWidth" : "1%",
					"mData" : "gamename"
				},
				{
					"sTitle" : "奖品ID",
					"sWidth" : "1%",
					"mData" : "giftid"
				},
				{
					"sTitle" : "产品ID",
					"sWidth" : "1%",
					"mData" : "productid"
				},
				{
					"sTitle" : "产品名称",
					"sWidth" : "1%",
					"mData" : "productname"
				},
				{
					"sTitle" : "中奖者昵称",
					"sWidth" : "1%",
					"mData" : "winner"
				},
				{
					"sTitle" : "中奖者open",
					"sWidth" : "1%",
					"mData" : "openid"
				},
				{
					"sTitle" : "领奖情况",
					"mDataProp" : "",
					"sDefaultContent" : "",
					"sWidth" : "1%",
					"sVisible" : false,
					"fnCreatedCell" : function(nTd, sData, oData, iRow, iCol) {
						$(nTd).append(
								"<label class='label label-primary'>"
										+ (oData.isOrder == null ? "未领奖"
												: "已领奖") + "</label>");

					}
				} ];

		return {
			aoColumns : aoColumns
		};
	};

	// 加载datatable表格数据
	var loadTableData = function() {
		var headerInfo = initTableHeaderInfo();
		var search = $("#searchForm").serializeArray();
		oTable = $('#' + tableId).DataTable({
			"fnServerParams" : function(aoData) {
				$.each(search, function(index, value) {
					aoData.push({
						"name" : value.name,
						"value" : value.value
					});
				});
			},
			"sAjaxSource" : "queryGamblingStakeWinnerList.html",
			"sAjaxDataProp" : "queryResult",
			"bFilter" : true,
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
			"bServerSide" : true,
			"aoColumns" : headerInfo.aoColumns,
			"aLengthMenu" : [ [ 10, 20, 30, 40, -1 ], [ 10, 20, 30, 40, 50 ] ],
			"iDisplayLength" : 10,
			"oLanguage" : {
				"sProcessing" : "努力加载中...",
				"sLengthMenu" : "显示 _MENU_ 条记录",
				"sInfoEmpty" : "搜索结果为0条记录",
				"sInfoFiltered" : "(从 _MAX_ 条记录中过滤出)",
				"sZeroRecords" : "没有您要搜索的内容",
				"sSearch" : "搜索",
				"sInfo" : "当前显示 _START_ 到 _END_ 总共 _TOTAL_ 条记录",
				"oPaginate" : {
					"sFirst" : "首页",
					"sPrevious" : "上一页",
					"sNext" : "下一页",
					"sLast" : "末页"
				}
			}
		});
	};

	$('#btnSearch').click(function() {
		oTable.fnDestroy();
		game_gameMemberResultList.loadTableData();
	});

	$('#btnReset').click(function() {
		document.getElementById("mobilePhone").value = "";
	});

	var initToolBar = function() {
		// 触发相关赋值
		$("#confirmWin").on("show.bs.modal", function(e) {
			var id = $(e.relatedTarget).attr("id");
			$("#id").val('').val(id);
			var brandCode = $(e.relatedTarget).attr("brandCode");
			$("#brandCode").val('').val(brandCode);
		});
		// 删除事件
		$("#deleteBut").on("click", function() {
			var param = {
				teplId : $("#id").val(),
				brandCode : $("#brandCode").val()
			};
			$.ajax({
				type : "post",
				dataType : "json",
				url : "deleteMember.html",
				data : param,
				success : function(data) {
					if (data.code == 0) {
						oTable.fnReloadAjax();
						bootbox.alert("删除成功");
						$("#confirmWin").modal("hide");
					} else {
						bootbox.alert("删除失败");
					}
				}
			});
		});
	};

	return {
		init : function() {
			loadTableData();
			initToolBar();
		},
		loadTableData : loadTableData
	};
}();
