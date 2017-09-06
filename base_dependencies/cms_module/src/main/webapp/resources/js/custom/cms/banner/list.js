var DataTableList = function() {
	var tableId = "dataTable";
	var oTable = null;

	// 初始化加载表格数据的表头定义
	var initTableHeaderInfo = function() {
		var aoColumns = [ {
			"sTitle" : "标题",
			"mData" : "title"
		}, {
			"sTitle" : "提示文字",
			"mData" : "imgAlt"
		}, {
			"sTitle" : "排序",
			"mData" : "sort"
		}, {
			"sTitle" : "图片访问地址",
			"mData" : "imgUrl"
		}, {
			"sTitle" : "图片链接地址",
			"mData" : "aUrl"
		},  {
			"sTitle": "状态",
			"mDataProp" : "",
			"sDefaultContent" : "",
			"sVisible" : false,
			"fnCreatedCell" : function(nTd, sData, oData, iRow, iCol) {
				if(oData.isEnable == 1){
					$(nTd).append("<label class='label label-success'>启用<label>");
				}else if(oData.isEnable == 0){
					$(nTd).append("<label class='label label-danger'>禁用<label>");
				}
			}
		},{
			"sTitle": "发布时间",
			"mDataProp" : "",
			"sDefaultContent" : "",
			"sVisible" : false,
			"fnCreatedCell" : function(nTd, sData, oData, iRow, iCol) {
				var createTime = moment(oData.create);
				$(nTd).append(createTime.format('YYYY-MM-DD HH:mm:ss'));
			}
		}, ];

		aoColumns.push({
        	"sTitle": "操作",
        	"mDataProp" : "",
        	"sDefaultContent" : "",
        	"sVisible" : false, 
        	"fnCreatedCell": function(nTd,sData, oData, iRow, iCol){
        		$(nTd).append("<span style='margin: 0 5px 0 5px'><span class='btn btn-xs purple ajaxify' href='banner/service/toUpdateViewById?id="+ oData.id+ "'><i class='fa fa-edit'></i>编辑 </span>");
        		if(App.checkUserOperRight("employeeList", "modifyEmp")){
        			if(oData.isEnable == 1){
                		$(nTd).append("<span style='margin: 0 5px 0 5px'></span><span class='btn btn-xs red' href='#imageWin' data-toggle='modal' bannerId='"+ oData.id+ "'><i class='fa fa-lock'></i> 禁用 </span>");
                	}else{
                		$(nTd).append("<span style='margin: 0 5px 0 5px'></span><span class='btn btn-xs green' enable='ok' bannerId='"+ oData.id+ "'><i class='fa fa-key'></i> 启用 </span>");
                	}
        		}
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
			"sAjaxSource" : "banner/service/findByPage",
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
	
	var initPageEvent = function(){
		$("#imageWin").on("show.bs.modal", function(e){
            var bannerId = $(e.relatedTarget).attr("bannerId");
            $("#bannerId").val('').val(bannerId);
        });
		 $("#"+tableId).delegate("span[enable='ok']", "click", function(){
			 debugger;
	            var param = {
	                    bannerId : $(this).attr("bannerId")
	            };
	            $.ajax({
	            	type : "POST",
	                dataType : "json",
	                url : "banner/service/enableBanner",
	                data : param,
	                success : function(data) {
	                	if (data || data.status == '00') {
	                        oTable.fnReloadAjax(); //刷新datatable列表
	                    }else{
	                        bootbox.alert("启动失败");
	                    }
	                }
	            })
	        });
	}


	var initToolBar = function() {
		// 启用和禁用
        $("#disableBut").on("click", function(){
        	debugger;
            var param = {
                bannerId : $("#bannerId").val()
            };
            $.ajax({
            	type : "POST",
                dataType : "json",
                url : "banner/service/enableBanner",
                data : param,
                success : function(data) {
                    $("#imageWin").modal("hide");
                	if (!data || data.status != '00') {
    					bootbox.alert(tips + '原因是' + data.msg);
    					return;
    				}
                    oTable.fnReloadAjax(); 
    				return;
                }
            });
        });
	};

	return {
		init : function() {
			loadTableData();
			initPageEvent();
			initToolBar()
		}
	};
}();
