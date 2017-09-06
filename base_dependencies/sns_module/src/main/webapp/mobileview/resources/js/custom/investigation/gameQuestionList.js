
var game_gameTitleList = function() {
	var tableId = "tableId";
	var oTable = null;

	// 初始化加载表格数据的表头定义
	var initTableHeaderInfo = function() {
		var aoColumns = [
				{
					"sTitle" : "id",
					"sWidth" : "1%",
					"mData" : "question.id"
				},
				{
					"sTitle" : "调查题目",
					"sWidth" : "1%",
					"mData" : "question.question"
				},
				{
					"sTitle" : "题目归属",
					"mDataProp" : "",
					"sDefaultContent" : "",
					"sWidth" : "5%",
					"sVisible" : false,
					"fnCreatedCell" : function(nTd, sData, oData, iRow, iCol) {
						if (oData.question.questionflag == "0") {
							$(nTd).append("<label class='label label-danger'>A套题目<br/></label>");
						} else if(oData.question.questionflag == "1") {
							$(nTd).append("<label class='label label-success'>B套题目<br/></label>");
						}
					}
				},
				{
					"sTitle" : "题目类型",
					"mDataProp" : "",
					"sDefaultContent" : "",
					"sWidth" : "5%",
					"sVisible" : false,
					"fnCreatedCell" : function(nTd, sData, oData, iRow, iCol) {
						if (oData.question.questiontype == "1") {
							$(nTd).append("<label class='label label-warning'>多&nbsp;选<br/></label>");
						} else {
							$(nTd).append("<label class='label label-primary'>单&nbsp;选<br/></label>");
						}
					}
				},
				{
					"sTitle" : "题目选项",
					"mDataProp" : "",
					"sDefaultContent" : "",
					"sWidth" : "5%",
					"sVisible" : false,
					"fnCreatedCell" : function(nTd, sData, oData, iRow, iCol) {
						$.each(oData.options, function(n, value) {
							$(nTd).append(
									String.fromCharCode(65+n) + "  " + value.optioncontent
											+ "<br>");
						});
					}
				},
				{
					"sTitle" : "子问题状态",
					"mDataProp" : "",
					"sDefaultContent" : "",
					"sWidth" : "5%",
					"sVisible" : false,
					"fnCreatedCell" : function(nTd, sData, oData, iRow, iCol) {
						var hasChildResult="";
						$.each(oData.options, function(n, value) {
							if(value.childquestionstatus==1){
								hasChildResult+="<label class='label label-danger'>选项" + String.fromCharCode(65+n) + "有子问题</label><br/><br/>";
							}
						});
						if(hasChildResult != ""){
							$(nTd).append(hasChildResult);
						}else{
							$(nTd).append("<label class='label label-success'>无子问题<br/></label>");
						}
					}
				},
				{
					"sTitle" : "题目描述",
					"sWidth" : "8%",
					"mData" : "question.description"
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
						if (App.checkUserOperRight("gameQuestionList",
								"editGameQuestion")) {
							$(nTd)
									.append(
											"<span style='margin: 0 5px 0 5px'><span class='btn btn-xs purple ajaxify'"
													+ " href='updateInvestigationQuestionView.html?id="
													+ oData.question.id
													+ "'><i class='fa fa-edit'></i> 编辑 </span>");
						}
						if (App.checkUserOperRight("gameQuestionList",
								"deleteGameQuestion")) {
							$(nTd)
									.append(
											"<span class='btn default btn-xs black' data-toggle='modal' "
													+ "href='#confirmWin' enable='ok' "
													+ "id='"
													+ oData.question.id
													+ "'>"
													+ "<i class='fa fa-trash-o'></i> 删除 </span>");
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
		var search = $("#questionSearchForm").serializeArray();
		oTable = $('#' + tableId).DataTable({
			"fnServerParams": function (aoData) {
        		$.each(search, function(index, value){
        			aoData.push({"name": value.name, "value": value.value})
                });
        	},
			"sAjaxSource" : "queryInvestigationQuestionByPage.html",
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
			"bAutoWidth" : false,
			"bSortCellsTop" : true,
			"iTabIndex" : 1,
			"sServerMethod" : "POST",
			"bServerSide" : true,
			"aoColumns" : headerInfo.aoColumns,
			"aLengthMenu" : [ [ 10, 15, 20, 25, 30, -1 ],
							[ 10, 15, 20, 25, 30 ] ],
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
		game_gameQuestionList.loadTableData();
	});

	$('#btnReset').click(function() {
		document.getElementById("mobilePhone").value = "";
	});
	
	$("#statusSearch").on("click", function(){
    	oTable.fnDestroy();
    	loadTableData();	
    	clean();
    });

	var initToolBar = function() {
		// 触发相关赋值
		$("#confirmWin").on("show.bs.modal", function(e) {
			var id = $(e.relatedTarget).attr("id");
			$("#id").val('').val(id);
		});
		// 删除事件
		$("#deleteBut").on("click", function() {
			var param = {
				questionId : $("#id").val()
			};
			$.ajax({
				type : "post",
				dataType : "json",
				url : "deleteInvestigationQuestionByQuesionId.html",
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
