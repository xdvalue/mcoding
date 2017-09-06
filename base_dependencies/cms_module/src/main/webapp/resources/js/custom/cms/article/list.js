var DataTableList = function() {
	var tableId = "dataTable";
	var oTable = null;

	// 初始化加载表格数据的表头定义
	var initTableHeaderInfo = function() {
		var aoColumns = [ 
		    { "sTitle" : "标题", "mData" : "title" }, 
		    { "sTitle" : "副标题", "mData" : "subTitle" }, 
		    { "sTitle" : "作者", "mData" : "author" }, 
			{ "sTitle": "文章显示类型", "mDataProp" : "", "sDefaultContent" : "", "sVisible" : false,
				"fnCreatedCell" : function(nTd, sData, oData, iRow, iCol) {
					if(!oData.typeId || !oData.typeId == 1){ $(nTd).append("普通文章"); }
					else if(oData.typeId == 2){ $(nTd).append("首页轮播图"); } } }, 
			{
			"sTitle": "文章状态",
			"mDataProp" : "",
			"sDefaultContent" : "",
			"sVisible" : false,
			"fnCreatedCell" : function(nTd, sData, oData, iRow, iCol) {
				if(oData.articleState == 1){
					$(nTd).append("<label class='label label-success'>草稿<label>");
				}else if(oData.articleState == 2){
					$(nTd).append("<label class='label label-success'>待审核<label>");
				}else if(oData.articleState == 3){
					$(nTd).append("<label class='label label-success'>已发布<label>");
				}else if(oData.articleState == 4){
					$(nTd).append("<label class='label label-danger'>回收站<label>");
				}
			}
		},
		{ "sTitle" : "点击量",
		  "mData" : "clickCount"}, {
			"sTitle" : "点赞",
			"mData" : "likeCount"
		}, {
			"sTitle": "发布时间",
			"mDataProp" : "",
			"sDefaultContent" : "",
			"sVisible" : false,
			"fnCreatedCell" : function(nTd, sData, oData, iRow, iCol) {
				var publishTime = moment(oData.publishTime);
				$(nTd).append(publishTime.format('YYYY-MM-DD HH:mm:ss'));
			}
		}, ];

		aoColumns
				.push({
					"sTitle" : "操作",
					"mDataProp" : "",
					"sDefaultContent" : "",
					"sVisible" : false,
					"fnCreatedCell" : function(nTd, sData, oData, iRow, iCol) {
						var articleId = 0;
						if (oData.articleId) {
							articleId = oData.articleId;
						}
						if (oData.articleState == 1 || oData.articleState == 2 || oData.articleState == 4) {
							$(nTd).append("<span style='margin: 0 5px 0 5px'></span><span class='btn btn-xs purple articlePublish' articleId='"+ oData.id+ "'><i class='fa fa-edit'></i>  发布</span>");
						} else {
							$(nTd).append("<span style='margin: 0 5px 0 5px'></span><span class='btn btn-xs purple articleUnPublish' articleId='"+ oData.id+ "'><i class='fa fa-edit'></i> 撤销 </span>");
						}
						$(nTd).append("<span style='margin: 0 5px 0 5px'><span class='btn btn-xs purple ajaxify' href='article/service/toUpdateViewById?id="+ oData.id+ "'><i class='fa fa-edit'></i>查看详情 </span>");
					}
				});

		// 渲染特殊的列(操作列)
		var aoColumnDefs = [];
		return {
			aoColumns : aoColumns,
			aoColumnDefs : aoColumnDefs
		};
	};

	// 加载datatable表格数据
	var loadTableData = function() {
		var headerInfo = initTableHeaderInfo();
		oTable = $('#' + tableId).DataTable({
			"fnServerParams" : function(aoData) {
				// aoData.push({"name": "orgId", "value": currtOrgId});
			},
			"sAjaxSource" : "article/service/findByPage",
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
			// "aoSearchCols": [null, null, {"sSearch":"myfilter"}, null, null,
			// null, null, null, null],
			"aLengthMenu" : [ [ 10, 20, 30, 40, -1 ], [ 10, 20, 30, 40, 50 ] ],
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
	};

	var setArticleState = function(articleId, state) {
		var param = {
			articleId : articleId,
			state : state
		};
		$.getJSON("article/service/setArticleState", param, function(json) {
			if (!json || json.status != '00') {
				return bootbox.alert("操作失败,请刷新后重试");
			}
			oTable.fnReloadAjax();
			bootbox.alert("操作成功");
		});
	}

	var initToolBar = function() {
		$("#" + tableId).delegate(".articlePublish", "click", function(e) {
			bootbox.confirm("确认发布吗?", function(result) {
				if (!result) {
					return;
				}
				var articleId = $(e.target).attr('articleId');
				var state = 3;
				setArticleState(articleId, state);
			});
		});

		$("#" + tableId).delegate(".articleUnPublish", "click", function(e) {
			bootbox.confirm("确认撤销吗?", function(result) {
				if (!result) {
					return;
				}
				var articleId = $(e.target).attr('articleId');
				var state = 4;
				setArticleState(articleId, state);
			});
		});
		
	};

	return {
		init : function() {
			loadTableData();
			initToolBar();
		}
	};
}();
