
var DataTableList = function () {
	var tableId = "dataTable";
	var oTable = null;
	
	//初始化加载表格数据的表头定义
    var initTableHeaderInfo = function(){
        var aoColumns = [
			{ "sTitle": "任务名称", "mData": "jobName"},
			{ "sTitle": "job唯一代码", "mData": "jobCode"},
			{ "sTitle": "描述", "mData": "description"},
			{ "sTitle": "完整类名称", "mData": "jobClass"},
			{ "sTitle": "方法名称", "mData": "jobMethod"},
			{
				"sTitle": "job状态", 
				"mDataProp" : "",
				"sDefaultContent" : "",	
				"sVisible" : false, 
				"fnCreatedCell":function(nTd,sData, oData, iRow, iCol){
					if(oData.jobState == 1){
						$(nTd).append('正常运行');
						
					}else if(oData.jobState == 0){
						
						$(nTd).append('停止运行');
					}else {
						$(nTd).append('运行异常');
					}
				}
			},
			{ "sTitle": "Corn表达式", "mData": "cronExpression"},
			{
				"sTitle": "是否启用", 
				"mDataProp" : "",
				"sDefaultContent" : "",	
				"sVisible" : false, 
				"fnCreatedCell":function(nTd,sData, oData, iRow, iCol){
					if(oData.isConcurrent == 1){
						$(nTd).append('启用');
					}else{
						$(nTd).append('禁用');
					}
				}
			},
			{
				"sTitle": "并发运行", 
				"mDataProp" : "",
				"sDefaultContent" : "",	
				"sVisible" : false, 
				"fnCreatedCell":function(nTd,sData, oData, iRow, iCol){
					if(oData.isConcurrent == 1){
						$(nTd).append('多线程并行');
					}else{
						$(nTd).append('单线程');
					}
				}
			},
			
			//	操作模板
			//	{
			//		"sTitle": "", 
			//		"mDataProp" : "",
			//		"sDefaultContent" : "",	
			//		"sVisible" : false, 
			//		"fnCreatedCell":function(nTd,sData, oData, iRow, iCol){
			//			if(oData.orderNum == 0){
			//				$(nTd).append('XXXX');
			//			}else{
			//				$(nTd).append('OOOO');
			//			}
			//		}
			//	}
			
        ];
        
        aoColumns.unshift({
        	"sTitle": "操作",
        	"mDataProp" : "",
        	"sDefaultContent" : "",
        	"sVisible" : false, 
        	"fnCreatedCell": function(nTd,sData, oData, iRow, iCol){
        		var btnRunNowByIdHtml = "<span class='btn default btn-xs green' >执行一次";
        		var btnRunNowByIdObj = $('<span>', {
        			style : "margin: 0 5px 0 5px",
        			click: function(){ runNowHandler(oData.id); }
        		});
        		btnRunNowByIdObj.append(btnRunNowByIdHtml);
        		btnRunNowByIdObj.appendTo($(nTd));
        		
        		var btnValue = '';
        		var btnHandler = null;
        		
        		if(oData.isEnable && oData.isEnable == 1){
        			btnValue = '停止';
        			btnHandler = pauseJobHandler;
        		}else{
        			btnValue = '启动';
        			btnHandler = resumeJobHandler;
        		}
        		
        		var btnPauseByIdHtml = "<span class='btn default btn-xs green' >" + btnValue;
        		var btnPauseByIdObj = $('<span>', {
        			style : "margin: 0 5px 0 5px",
        			click: function(){ btnHandler(oData.id); }
        		});
        		btnPauseByIdObj.append(btnPauseByIdHtml);
        		btnPauseByIdObj.appendTo($(nTd));
        		
        	    var btnUpdateByIdHtml = "<span style='margin: 0 5px 0 5px'><span class='btn btn-xs purple ajaxify' href='scheduleJob/service/toUpdateViewById?id="+oData.id+"'><i class='fa fa-edit'></i> 更改 </span>";
        		var btnUpdateByIdObj = $(btnUpdateByIdHtml).appendTo($(nTd));
        		
        		var btnDelteByIdHtml = "<span class='btn default btn-xs black' ><i class='fa fa-trash-o'></i>删除";
        		var btnDelteByIdObj = $('<span>', {
        			style : "margin: 0 5px 0 5px",
        			click: function(){ deleteHandler(oData.id); }
        		});
        		btnDelteByIdObj.append(btnDelteByIdHtml);
        		btnDelteByIdObj.appendTo($(nTd));
        	}
        });
        
        //渲染特殊的列(操作列)
        var aoColumnDefs =[];
        return {
            aoColumns : aoColumns,
            aoColumnDefs : aoColumnDefs
        };
    };
    
    
    //加载datatable表格数据
    var loadTableData = function(){
        var headerInfo = initTableHeaderInfo();
        oTable = $('#'+tableId).DataTable({
        	"fnServerParams": function (aoData) {
                //aoData.push({"name": "orgId", "value": currtOrgId});
            },
            "sAjaxSource": "scheduleJob/service/findByPage",
            "sAjaxDataProp": "queryResult",
            "bFilter" : true,
            "bInfo": true,
            "bJQueryUI": true,
            "bLengthChange": true,
            "bPaginate": true,
            "bProcessing": true,
            "bSort": false,
            "bSortClasses": true,
            "bStateSave": false,
            "bAutoWidth":true,
            "bSortCellsTop": true,
            "iTabIndex": 1,
            "sServerMethod": "POST",
            "bServerSide": true,
            "aoColumns": headerInfo.aoColumns,
//            "aoSearchCols": [null, null, {"sSearch":"myfilter"}, null, null, null, null, null, null],
            "aLengthMenu": [
                [10, 20, 30, 40, -1],
                [10, 20, 30, 40, 50]
            ],
            "iDisplayLength": 10,
            "oLanguage": {
            	"sProcessing" : "努力加载中...",
                "sLengthMenu": "显示 _MENU_ 条记录",
                "sInfoEmpty" : "搜索结果为0条记录",
                "sInfoFiltered": "(从 _MAX_ 条记录中过滤出)",
                "sZeroRecords" : "没有您要搜索的内容", 
                "sSearch" : "搜索：", 
                "sInfo": "当前显示 _START_ 到 _END_ 总共 _TOTAL_ 条记录",
                "oPaginate": {
                    "sFirst":"首页",
                    "sPrevious": "上一页",
                    "sNext": "下一页",
                    "sLast":"末页"
                }
            }
        });
    };
    
    var runNowHandler = function(id){
    	bootbox.confirm("确认执行吗?", function(result) {
			if (!result) {
				return;
			}
			var url = 'scheduleJob/service/runJobNow?id=' +id;
			
			$.getJSON(url, function(json){
				if(json && json.status == '00'){
					bootbox.alert("操作成功");
					oTable.fnReloadAjax();
					return;
				}else{
					return bootbox.alert("操作失败,原因是:" + json.msg);
				}
			});
		});
    }
    
    var resumeJobHandler = function(id){
    	bootbox.confirm("确认启动吗?", function(result) {
			if (!result) {
				return;
			}
			var url = 'scheduleJob/service/resumeJob?id=' +id;
			
			$.getJSON(url, function(json){
				if(json && json.status == '00'){
					bootbox.alert("操作成功");
					oTable.fnReloadAjax();
					return;
				}else{
					return bootbox.alert("操作失败,原因是:" + json.msg);
				}
			});
		});
    }
    
    var pauseJobHandler = function(id){
    	bootbox.confirm("确认暂停吗?", function(result) {
			if (!result) {
				return;
			}
			var url = 'scheduleJob/service/pauseJob?id=' +id;
			
			$.getJSON(url, function(json){
				if(json && json.status == '00'){
					bootbox.alert("操作成功");
					oTable.fnReloadAjax();
					return;
				}else{
					return bootbox.alert("操作失败,原因是:" + json.msg);
				}
			});
		});
    }
    
    var deleteHandler = function(id){
    	bootbox.confirm("确认删除吗?", function(result) {
			if (!result) {
				return;
			}
			var url = 'scheduleJob/service/deleteById?id=' +id;
			
			$.getJSON(url, function(json){
				if(json && json.status == '00'){
					bootbox.alert("操作成功");
					oTable.fnReloadAjax();
					return;
				}else{
					return bootbox.alert("操作失败,请刷新后重试");
				}
			});
		});
    };
 
    return {
        init: function () {
            loadTableData();
        }
    };
}();

