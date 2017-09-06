
var WxReplyNewsDataTableList = function () {
	var tableId = "dataTable";
	var oTable = null;
	
	//初始化加载表格数据的表头定义
    var initTableHeaderInfo = function(){
        var aoColumns = [
			{ "sTitle": "标题", "mData": "title"},
			{ "sTitle": "摘要", "mData": "summary"},
			{ "sTitle": "封面图片", "mData": "coverImg"},
			{ "sTitle": "连接地址", "mData": "url"},
			
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
        
        aoColumns.push({
        	"sTitle": "操作",
        	"mDataProp" : "",
        	"sDefaultContent" : "",
        	"sVisible" : false, 
        	"fnCreatedCell": function(nTd,sData, oData, iRow, iCol){
        	    var btnUpdateByIdHtml = "<span style='margin: 0 5px 0 5px'><span class='btn btn-xs purple ajaxify' href='wxMsgReplyNews/service/toUpdateViewById?id="+oData.id+"'><i class='fa fa-edit'></i> 更改 </span>";
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
            "sAjaxSource": "wxMsgReplyNews/service/findByPage",
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
    
    var deleteHandler = function(id){
    	bootbox.confirm("确认删除吗?", function(result) {
			if (!result) {
				return;
			}
			var url = 'wxMsgReplyNews/service/deleteById?id=' +id;
			
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

