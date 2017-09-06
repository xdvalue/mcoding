//员工通讯录
var seckill_seckillList = function () {
	var tableId = "tableId";
	var oTable = null;
	
	//初始化加载表格数据的表头定义
    var initTableHeaderInfo = function(){
        var aoColumns = [
			{ "sTitle": "id", "sWidth" : "1%", "mData": "id"},
			{ "sTitle": "产品ID","sWidth" : "1%", "mData": "productid"},
			{ "sTitle": "产品名称","sWidth" : "1%", "mData": "productname"},
			{ "sTitle": "库存","sWidth" : "1%", "mData": "sku"},
			{ "sTitle": "拍下所需积分","sWidth" : "1%", "mData": "needpoint"},
			{ "sTitle": "拍下数量","sWidth" : "1%", "mData": "ordernum"},
			{ "sTitle": "活动状态",
				"mDataProp" : "",
				"sDefaultContent" : "",
            	"sWidth" : "2%",
				"sVisible" : false,
				"fnCreatedCell" : function(nTd,sData, oData, iRow, iCol) {
					if(oData.status == 'notReady'){
						$(nTd).append("未准备");
					}
					if(oData.status == 'ready'){
						$(nTd).append("准备中");
					}
					if(oData.status == 'playing'){
						$(nTd).append("进行中");
					}
					if(oData.status == 'end'){
						$(nTd).append("已结束");
					}
				}
			},
			{
				"sTitle": "开始时间",
				"mDataProp" : "",
				"sDefaultContent" : "",
            	"sWidth" : "5%",
				"sVisible" : false,
				"fnCreatedCell" : function(nTd,sData, oData, iRow, iCol) {
					$(nTd).append(changeDateFormat(oData.starttime));
				}
			},
			{
				"sTitle": "结束时间",
				"mDataProp" : "",
				"sDefaultContent" : "",
            	"sWidth" : "5%",
				"sVisible" : false,
				"fnCreatedCell" : function(nTd,sData, oData, iRow, iCol) {
					$(nTd).append(changeDateFormat(oData.endtime));
				}
			}
        ];
        //渲染特殊的列(操作列)
        var aoColumnDefs =[];
            aoColumns.push({
            	"sTitle": "操作",
            	"mDataProp" : "",
            	"sDefaultContent" : "",
            	"sWidth" : "5%",
            	"sVisible" : false, 
            	"fnCreatedCell": function(nTd,sData, oData, iRow, iCol){
            		//$(nTd).append('<button class="btn popovers" data-trigger="hover" data-placement="top" data-content="Popover body goes here! Popover body goes here!" data-original-title="Popover in top">Top</button>');
        			$(nTd).append("<span style='margin: 0 5px 0 5px'><span class='btn btn-xs purple ajaxify'" +
        					" href='addSeckillView.html?id="+oData.id+"'><i class='fa fa-edit'></i> 编辑 </span>");
        			$(nTd).append("<span style='margin: 0 5px 0 5px'><span class='btn btn-xs purple ajaxify'" +
        					" href='seckillResultPageView.html?id="+oData.id+"'><i class='fa fa-edit'></i> 查看用户 </span>");
                }
            });

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
            aoColumns : aoColumns,
            aoColumnDefs : aoColumnDefs
        };
    };
    
    
    //加载datatable表格数据
    var loadTableData = function(){
        var headerInfo = initTableHeaderInfo();
        var search = $("#seckillSearchForm").serializeArray();
        oTable = $('#'+tableId).DataTable({
        	"fnServerParams": function (aoData) {
        		$.each(search, function(index, value){
        			aoData.push({"name": value.name, "value": value.value})
                });
        	},
            "sAjaxSource": "querySeckillData.html",
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

