var ArticleShareForm_articleShare = function() {
	var tableId = "articleShareTable";
	var oTable = null;

	// 初始化加载表格数据的表头定义
	var initTableHeaderInfo = function() {
		var aoColumns = [
				{
					"sTitle" : '<th class="table-checkbox"><input type="checkbox" class="group-checkable" data-set="#'
							+ tableId + ' .checkboxes"/></th>',
					"bSortable" : false,
					"bSearchable" : false,
					"mDataProp" : "",
					"sDefaultContent" : "",
					"sVisible" : false,
					"fnCreatedCell" : function(nTd, sData, oData, iRow, iCol) {
						$(nTd).append('<td class="table-checkbox">');
						$(nTd).append(
								'<input type="checkbox" class="checkboxes" value="'
										+ oData.id + '"/>');
						$(nTd).append('</td>');
					}
				}, 
				{ "sTitle" : "分享者openId", "mData" : "shareopenid"}, 
				{ "sTitle" : "阅读者openId", "mData" : "readeropenid"}, 
				{ "sTitle" : "返利积分", "mData" : "point"}, 
				{ "sTitle" : "排名", "mData" : "ranking"}, 
				{ "sTitle" : "文章id", "mData" : "articleid"}, 
				/*{
	            	"sTitle": "是否阅读到最后",
	            	"mDataProp" : "",
	            	"sDefaultContent" : "",
	            	"sVisible" : false,
	            	"fnCreatedCell" : function(nTd, sData, oData, iRow, iCol) {
	            		if(oData.isreadfinish==1){
	            			$(nTd).append("<label class='label label-success'>是</label>");
	            		}else{
	            			$(nTd).append("<label class='label label-danger'>否</label>");
	            		}
	            		
	            	}
	            },
				{ "sTitle" : "阅读时间", "mData" : "readtime"}, */
				{ "sTitle" : "创建时间",
					"mDataProp" : "",
					"sDefaultContent" : "",
					"sVisible" : false,
					"fnCreatedCell" : function(nTd, sData, oData, iRow, iCol) {
						$(nTd).append(changeDateFormat(oData.createtime));
					}
				}, {
					"sTitle" : "更新时间",
					"mDataProp" : "",
					"sDefaultContent" : "",
					"sVisible" : false,
					"fnCreatedCell" : function(nTd, sData, oData, iRow, iCol) {
						$(nTd).append(changeDateFormat(oData.updatetime));
					}
				} ];
		// 渲染特殊的列(操作列)
		var aoColumnDefs = [];
//		aoColumns.push({
//			"sTitle" : "操作",
//			"mDataProp" : "",
//			"sDefaultContent" : "",
//			"sVisible" : false,
//			"fnCreatedCell" : function(nTd, sData, oData, iRow, iCol) {
//				 $(nTd).append("<span style='margin: 0 5px 0 5px'><span class='btn btn-xs purple ajaxify' href='addArticleDefView.html?id="+oData.id+"'><i class='fa fa-edit'></i> 编辑 </span>");
//				 $(nTd).append("<span style='margin: 0 5px 0 5px'><span class='btn default btn-xs black' data-toggle='modal' href='#confirmWin' id="+oData.id+"><i class='fa fa-trash-o'></i> 删除 </span>");
//			}
//		});

		return {
			aoColumns : aoColumns,
			aoColumnDefs : aoColumnDefs
		};
	};

	// 加载表格数据
	var loadTableData = function() {
		var headerInfo = initTableHeaderInfo();
		var search = $("#articleShareSearchForm").serializeArray();
		console.log(search);
		oTable = $('#' + tableId).DataTable({
			"fnServerParams" : function(aoData) {
				$.each(search, function(index, value) {
					aoData.push({
						"name" : value.name,
						"value" : value.value
					})
				});
			},
			"sAjaxSource" : "queryArticleShareByPage.html",
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
			"bAutoWidth" : true,
			"bSortCellsTop" : true,
			"iTabIndex" : 1,
			"sServerMethod" : "POST",
			"bServerSide" : true,
			"aoColumns" : headerInfo.aoColumns,
			"aoColumnDefs" : headerInfo.aoColumnDefs,
			"iDisplayLength" : 10,
			"oLanguage" : {
				"sLengthMenu" : "显示 _MENU_ 条记录",
				"sInfo" : "当前显示 _START_ 到 _END_ 总共 _TOTAL_ 条记录",
				"sInfoEmpty" : "搜索结果为0条记录",
				"sZeroRecords" : "当前没有记录",
				"sSearch" : "搜索：",
				"oPaginate" : {
					"sFirst" : "首页",
					"sPrevious" : "上一页",
					"sNext" : "下一页",
					"sLast" : "末页"
				}
			}
		});
	};
	
	
	//查询
    $("#articleShareSearch").on("click", function(){
    	oTable.fnDestroy();
    	loadTableData();	
    	clean();
    });

	var initToolBar = function() {

		// 清空input框等
		function clean() {
			$(':input').not(':text', ':button, :submit, :reset, :hidden').val(
					'').removeAttr('checked').removeAttr('selected');
		};

		jQuery('#' + tableId).on('change', '.group-checkable', function() {
			var set = jQuery(this).attr("data-set");
			var checked = jQuery(this).is(":checked");
			jQuery(set).each(function() {
				if (checked) {
					$(this).attr("checked", true);
					$(this).parents('tr').addClass("active");
				} else {
					$(this).attr("checked", false);
					$(this).parents('tr').removeClass("active");
				}
			});
			jQuery.uniform.update(set);
		});


	};


	return {
		init : function() {
			loadTableData();
			initToolBar();
		},
		loadTableData : loadTableData,
	};
}();