﻿//员工通讯录
var seckill_seckillResultList = function () {
	var tableId = "tableId";
	var oTable = null;
	
	//初始化加载表格数据的表头定义
    var initTableHeaderInfo = function(){
        var aoColumns = [
             			{ "sTitle": "秒杀活动id", "sWidth" : "1%", "mData": "seckillid"},
            			{ "sTitle": "openid", "sWidth" : "1%", "mData": "openid"},
            			{ "sTitle": "昵称", "sWidth" : "1%", "mData": "nickname"},
            			{ "sTitle": "头像",
            				"mDataProp" : "",
            				"sDefaultContent" : "",
                        	"sWidth" : "2%",
            				"sVisible" : false,
            				"fnCreatedCell" : function(nTd,sData, oData, iRow, iCol) {
            					$(nTd).append("<img src='"+ oData.headimgurl +"'>");
            				}
            			},
            			{ "sTitle": "秒得数量", "sWidth" : "1%", "mData": "num"},
            			{ "sTitle": "产品ID", "sWidth" : "1%", "mData": "productid"},
            			
            	//		{ "sTitle": "订单ID", "sWidth" : "1%", "mData": "orderid"},
            			{
            				"sTitle": "订单ID",
            				"mDataProp" : "",
            				"sDefaultContent" : "",
                        	"sWidth" : "1%",
            				"sVisible" : false,
            				"fnCreatedCell" : function(nTd,sData, oData, iRow, iCol) {
            					if(oData.orderid > 0){
            						$(nTd).append(oData.orderid);
            					} else {
            						$(nTd).append("尚未提交订单");
            					}
            				//	$(nTd).append("<c:if test='${oData.orderid } > 0'>" + oData.orderid + "</c:if> <c:if test='${oData.orderid } <= 0'>尚未提交订单</c:if>");
            				}
            			},
            			
            			{ "sTitle": "产品名", "sWidth" : "1%", "mData": "productname"},
            			{
            				"sTitle": "创建时间",
            				"mDataProp" : "",
            				"sDefaultContent" : "",
                        	"sWidth" : "5%",
            				"sVisible" : false,
            				"fnCreatedCell" : function(nTd,sData, oData, iRow, iCol) {
            					$(nTd).append(changeDateFormat(oData.createtime));
            				}
            			}
        ];

            //绑定日历
            if (jQuery().datepicker) {
            	$('.date-picker').datepicker({
            		format: 'yyyy-mm-dd',
                    weekStart: 1,
                    todayBtn: 'linked',
                    rtl: App.isRTL(),
                    language: "zh-CN",
                    autoclose: true
                });
            }
            
            if (jQuery().datetimepicker) {
            	$(".datetime_picker").datetimepicker({
            		format: 'yyyy-mm-dd hh:ii:ss',
            		weekStart: 1,
            		todayBtn: 'linked',
            		rtl: App.isRTL(),
            		language: "zh-CN",
            		autoclose: true
            	});
            }
            
        return {
            aoColumns : aoColumns
        };
    };
    
    
    //加载datatable表格数据
    var loadTableData = function(){
        var headerInfo = initTableHeaderInfo();
        var seckillId = $('#seckillId').val();
        oTable = $('#'+tableId).DataTable({
            "sAjaxSource": "querySeckillResultData.html?id="+seckillId,
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
                "sSearch" : "搜索", 
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
    
    $('#btnSearch').click(function(){
    	oTable.fnDestroy();
    	seckill_seckillList.loadTableData();
     });
    
    $("#statusSearch").on("click", function(){
    	oTable.fnDestroy();
    	loadTableData();	
    	clean();
    });
    

    var initToolBar = function(){
    	//触发相关赋值
        $("#confirmWin").on("show.bs.modal", function(e){
            var id = $(e.relatedTarget).attr("id");
            $("#id").val('').val(id);
            var brandCode = $(e.relatedTarget).attr("brandCode");
            $("#brandCode").val('').val(brandCode);
        });
    };

    return {
        init: function () {
            loadTableData();
            initToolBar();
        },
        loadTableData: loadTableData
    };
}();

