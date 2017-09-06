//押宝游戏
var mallgame_gamblingList = function() {
	var tableId = "tableId";
	var oTable = null;

	// 初始化加载表格数据的表头定义
	var initTableHeaderInfo = function() {
		var aoColumns = [
				{
					"sTitle" : "id",
					"sWidth" : "1%",
					"mData" : "id"
				},
				{
					"sTitle" : "游戏名称",
					"sWidth" : "1%",
					"mData" : "gameName"
				},
				{
					"sTitle" : "开始时间",
					"sWidth" : "1%",
					"mData" : "startTime"
				},
				{
					"sTitle" : "结束时间",
					"sWidth" : "1%",
					"mData" : "endTime"
				},
				{
					"sTitle" : "总积分",
					"sWidth" : "1%",
					"mData" : "gainPoint"
				},
				{
					"sTitle" : "游戏状态",
					"mDataProp" : "",
					"sDefaultContent" : "",
					"sWidth" : "2%",
					"sVisible" : false,
					"fnCreatedCell" : function(nTd, sData, oData, iRow, iCol) {
						var gameStatus = oData.gameStatus;
						if (gameStatus == "0") {
							$(nTd).append("活动已经结束");
						} else {
							$(nTd).append("活动正在进行");
						}
					}
				},
				{
					"sTitle" : "产品ID",
					"sWidth" : "1%",
					"mData" : "productId"
				},
				{
					"sTitle": "品牌",
					"mDataProp" : "",
					'sWidth':'7%',
					"sDefaultContent" : "",
					"sVisible" : false,
					"fnCreatedCell" : function(nTd,sData, oData, iRow, iCol) {
						$(nTd).append("<label class='label label-success'>"+convertOrderBrand(oData.brandCode)+"</label>");
					}
				},
				{
					"sTitle" : "产品名称",
					"sWidth" : "1%",
					"mData" : "productName"
				},
				{
					"sTitle" : "产品图片",
					"mDataProp" : "",
					"sDefaultContent" : "",
					"sWidth" : "2%",
					"sVisible" : false,
					"fnCreatedCell" : function(nTd, sData, oData, iRow, iCol) {
						$(nTd).append(
								"<img src='" + oData.productImg
										+ "' width='70%'>");
					}
				}, {
					"sTitle" : "产品数量",
					"sWidth" : "1%",
					"mData" : "nums"
				} ];
		// 渲染特殊的列(操作列)
		var aoColumnDefs = [];
		aoColumns
				.push({
					"sTitle" : "操作",
					"mDataProp" : "",
					"sDefaultContent" : "",
					"sWidth" : "5%",
					"sVisible" : false,
					"fnCreatedCell" : function(nTd, sData, oData, iRow, iCol) {
						if (App.checkUserOperRight("gamblingStakeList", "editGamblingStake")) {
							$(nTd)
									.append(
											"<span style='margin: 0 5px 0 5px'><span class='btn btn-xs purple ajaxify'"
													+ " href='addGamblingStakeView.html?id="
													+ oData.id
													+ "'><i class='fa fa-edit'></i> 编辑 </span>");
							$(nTd)
									.append(
											"<span style='margin: 0 5px 0 5px'><span class='btn btn-xs purple ajaxify'"
													+ " href='getStakeRecordsPageView.html?id="
													+ oData.id
													+ "'><i class='fa fa-edit'></i> 查看押宝状态 </span>");
						}

					}
				});

		return {
			aoColumns : aoColumns,
			aoColumnDefs : aoColumnDefs
		};
	};

	// 加载datatable表格数据
	var loadTableData = function() {
		var headerInfo = initTableHeaderInfo();
		var search = $("#seckillSearchForm").serializeArray();
		oTable = $('#' + tableId).DataTable({
			"fnServerParams" : function(aoData) {
				$.each(search, function(index, value) {
					aoData.push({
						"name" : value.name,
						"value" : value.value
					})
				});
			},
			"sAjaxSource" : "queryGamblingStakeData.html",
			"sAjaxDataProp" : "queryResult",
			"bFilter" : true,
			"bInfo" : true,
			"bJQueryUI" : true,
			"bLengthChange" : true,
			"bPaginate" : true,
			"bProcessing" : true,
			"bSort" : false,
			"bSortClasses" : true,
			"bStateSave" : true,
			"bAutoWidth" : false,
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
		mallgame_gamblingList.loadTableData();
		
	});

	$("#statusSearch").on("click", function() {
		oTable.fnDestroy();
		loadTableData();
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

	// 游戏类型转换
	var convertGameType = function(gameType) {
		var html = '';
		if (gameType == "0") {
			html = "<label class='label label-danger'>问答游戏</label>";
		} else if (gameType == "1") {
			html = "<label class='label label-danger'>摇一摇游戏</label>";
		} else if (gameType == "2") {
			html = "<label class='label label-danger'>其它游戏</label>";
		} else {
			html = "<label class='label label-primary'>暂无</label>";
		}
		return html;
	};

	return {
		init : function() {
			loadTableData();
			$("#tableId td").css({"text-algin":"center","vertical-align": "middle"});
			initToolBar();
		},
		loadTableData : loadTableData
	};
}();
