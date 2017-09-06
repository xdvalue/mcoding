//产品列表
var Product_SetProductList = function () {
	var tableId = "productSetTable";
	var oTable = null;
	
	//初始化加载表格数据的表头定义
    var initTableHeaderInfo = function(){
        var aoColumns = [
            { "sTitle": "ID", "mData": "productId"},
			{ "sTitle": "产品名称","mData": "productName"},
			{
            	"sTitle": "原价",
            	"mDataProp" : "",
				"sDefaultContent" : "",
            	"sVisible" : false,
            	"fnCreatedCell" : function(nTd, sData, oData, iRow, iCol) {
					$(nTd).append("￥"+oData.originalPrice/100);
            	}
            },
            {
            	"sTitle": "折扣价",
            	"mDataProp" : "",
				"sDefaultContent" : "",
            	"sVisible" : false,
            	"fnCreatedCell" : function(nTd, sData, oData, iRow, iCol) {
					$(nTd).append("￥"+oData.discountPrice/100);
            	}
            },
            {
            	"sTitle": "商品类型",
            	"mDataProp" : "",
            	"sDefaultContent" : "",
            	"sVisible" : false,
            	"fnCreatedCell" : function(nTd, sData, oData, iRow, iCol) {
            		$(nTd).append("<label class='label label-success'>套餐<label>");
            	}
            },
			{ "sTitle": "套餐子产品","mData": "productContent"},
        ];
        //渲染特殊的列(操作列)
        var aoColumnDefs =[];
            aoColumns.push({
            	"sTitle": "操作",
            	"mDataProp" : "",
            	"sDefaultContent" : "",
            	"sVisible" : false, 
            	"fnCreatedCell": function(nTd,sData, oData, iRow, iCol){
            		$(nTd).append("<span style='margin: 0 5px 0 5px'><span class='btn btn-xs purple ajaxify' href='toUpdateProductSet.html?productId="+oData.productId+"&brandCode="+oData.brandCode+"'><i class='fa fa-edit'></i> 编辑 </span>");
            	}
            });

        return {
            aoColumns : aoColumns,
            aoColumnDefs : aoColumnDefs
        };
    };
    
    var initToolbar = function(){
        //查询
	    $("#btnSearch").on("click", function(){
	    	oTable.fnDestroy();
	    	loadTableData();
	    });
    };
    
    
    //加载datatable表格数据
    var loadTableData = function(){
    	console.log(oTable);
        var headerInfo = initTableHeaderInfo();
        var search = $("#searchForm").serializeArray();
        oTable = $('#'+tableId).DataTable({
        	"fnServerParams": function (aoData) {
        		$.each(search, function(index, value){
        			aoData.push({"name": value.name, "value": value.value})
                });
        	},
            "sAjaxSource": "querySetData.html",
            "sAjaxDataProp": "queryResult",
            "bFilter" : true,
            "bInfo": true,
            "bJQueryUI": true,
            "bLengthChange": true,
            "bPaginate": true,
            "bProcessing": true,
            "bSort": false,
            "bSortClasses": true,
            "bStateSave": true,
            "bAutoWidth":false,
            "bSortCellsTop": true,
            "iTabIndex": 1,
            "sServerMethod": "POST",
            "bServerSide": true,
            "aoColumns": headerInfo.aoColumns,
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
    
    return {
        init: function () {
            loadTableData();
            initToolbar();
        },
        loadTableData:loadTableData
    };
}();