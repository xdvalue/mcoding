//帖子列表
var DataTableList = function () {
	var tableId = "dataTable";
	var oTable = null;
	var selectIds =[];
	
	//初始化加载表格数据的表头定义
    var initTableHeaderInfo = function(){
        var aoColumns = [
            { "sTitle": "日期", "mData": "logindate"},
			{ "sTitle": "新增会员数", "mData": "newUser"}
        ];
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
        	"fnDrawCallback":function(){
//        		initSwitch();
        		selectIds = [];
        	},
        	"fnServerParams": function (aoData) {
        		var endTime = $('.endTime').val();
    			var startTime = $('.startTime').val();
    			if (!(!startTime || startTime == "" || startTime === undefined)) {
    				startTime = startTime.replace(/-/g, '/');
    				startTime = new Date(startTime);
    			}
    			if (!(!endTime || endTime == "" || endTime === undefined)) {
    				endTime = endTime.replace(/-/g, '/');
    				endTime = new Date(endTime);
    			}
    			if(startTime && startTime != ''){
    				aoData.push({ "name" : "startTime" , "value" : startTime.getTime()});
    			}
    			if(endTime && endTime != ''){
    				aoData.push({ "name" : "endTime" , "value" : endTime.getTime()});
    			}
    			
            },
            "sAjaxSource": "snsStatistical/service/newUserByDay",
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
                [10, 20, 30, 40, 50],
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
    
    var handleDatetimePicker = function() {
		$(".form_datetime").datetimepicker({
			format : "yyyy-mm-dd",
			autoclose : true,
			todayBtn : true,
			minView: 2,
			pickerPosition : "bottom-left"
		});
    }
    
    var initEvent = function() {
		/*$('#select_dateTimeType').change(function() {
			oTable.fnDraw();
		});*/
		$('#search').click(function() {
			var startTime = $("#startTime").val();
			var endTime = $("#endTime").val();

			if (!startTime || startTime == "" || startTime === undefined) {
				bootbox.alert("开始时间不能为空");
				return;
			}
			if (!endTime || endTime == "" || endTime === undefined) {
				bootbox.alert("结束时间不能为空");
				return;
			}
			endTime = endTime.replace(/-/g, '/');
			endTime = new Date(endTime);

			startTime = startTime.replace(/-/g, '/');
			startTime = new Date(startTime);

			if (startTime >= endTime) {
				bootbox.alert("结束时间不能小于开始时间");
				return;
			}
			oTable.fnDraw();
		});
	};
    
    return {
        init: function () {
//        	initToolBar();
        	handleDatetimePicker();
        	initEvent();
            loadTableData();
        }
    };
}();

