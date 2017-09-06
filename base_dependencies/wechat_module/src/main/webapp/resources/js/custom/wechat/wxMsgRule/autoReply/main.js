/**
 * 
 */
var DataTableList = function () {
	var tableId = "dataTable";
	var oTable = null;
	
	//初始化加载表格数据的表头定义
    var initTableHeaderInfo = function(){
        var aoColumns = [
        	{ "sTitle": "id", "mData": "id"},
        	{
				"sTitle": "关键字",
				"mDataProp" : "",
				"sDefaultContent" : "",
				"sVisible" : false, 
				"fnCreatedCell":function(nTd,sData, oData, iRow, iCol){
					var content = JSON.parse(oData.content);
					$(nTd).append(content.join(','));
				}
        	},{
        		"sTitle": "匹配模式",
        		"mDataProp" : "",
        		"sDefaultContent" : "",
        		"sVisible" : false, 
        		"fnCreatedCell":function(nTd,sData, oData, iRow, iCol){
        			if(oData.matchType == '200'){
        				$(nTd).append('模糊匹配');
        			}else{
        				$(nTd).append('完全匹配');
        			}
        		}
        	},{
        		"sTitle": "匹配模式",
        		"mDataProp" : "",
        		"sDefaultContent" : "",
        		"sVisible" : false, 
        		"fnCreatedCell":function(nTd,sData, oData, iRow, iCol){
        			if(oData.handlers =='com.mcoding.comp.wechat.msg.handler.ReplyNewsHandler'){ 
    		        	$(nTd).append('图文'); 
    		        }else if(oData.handlers =='com.mcoding.comp.wechat.msg.handler.ReplyTextHandler'){
    		        	$(nTd).append('文本'); 
    		        }
        		}
        	},
        	{ "sTitle": "回复内容", "mData": "replyContent"},
        ];
        
        aoColumns.unshift({
        	"sTitle": "操作",
        	"mDataProp" : "",
        	"sDefaultContent" : "",
        	"sVisible" : false, 
        	"fnCreatedCell": function(nTd,sData, oData, iRow, iCol){
        		
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
            "sAjaxSource": "wxMsgRule/service/findAutoReplys?originId="+$('#originId').val(),
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
			var url = 'accountConfig/service/deleteById?id=' +id;
			
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

