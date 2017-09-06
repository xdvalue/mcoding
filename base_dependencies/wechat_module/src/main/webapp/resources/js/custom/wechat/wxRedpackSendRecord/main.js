
var DataTableList = function () {
	var tableId = "dataTable";
	var oTable = null;
	
	//初始化加载表格数据的表头定义
    var initTableHeaderInfo = function(){
        var aoColumns = [
			{ "sTitle": "商户名称(发送者)", "mData": "sendName"},
			{ "sTitle": "活动名称", "mData": "actName"},
			{ "sTitle": "接收人", "mData": "memberName"},
			{ "sTitle": "红包金额(元)", "mData": "", "sDefaultContent":"","sVisible":false, 
				"fnCreatedCell":function(nTd,sData, oData, iRow, iCol){
					$(nTd).append(oData.totalAmount/100);
            }},
			{ "sTitle": "祝福语", "mData": "wishing"},
			{ "sTitle": "红包状态", "mData": "", "sDefaultContent":"","sVisible":false, 
				"fnCreatedCell":function(nTd,sData, oData, iRow, iCol){
					$(nTd).append(getStatus(oData.status));
            }},
			{ "sTitle": "发送时间", "mData": "", "sDefaultContent":"","sVisible":false, 
				"fnCreatedCell":function(nTd,sData, oData, iRow, iCol){
					$(nTd).append(moment(oData.sendTime).format('YYYY-MM-DD HH:mm'));
            }},
            { "sTitle": "领取时间", "mData": "", "sDefaultContent":"","sVisible":false, 
				"fnCreatedCell":function(nTd,sData, oData, iRow, iCol){
					var receiveTime = '';
					if(oData.receiveTime!=null && oData.receiveTime) receiveTime = moment(oData.receiveTime).format('YYYY-MM-DD HH:mm');
					$(nTd).append(receiveTime)
            }},
			{ "sTitle": "备注", "mData": "remark"},
			
        ];
        
        aoColumns.push({
        	"sTitle": "操作",
        	"mDataProp" : "",
        	"sDefaultContent" : "",
        	"sVisible" : false, 
        	"fnCreatedCell": function(nTd,sData, oData, iRow, iCol){
        		if(oData.status != 'FAILED' && oData.status != 'UNSENT' && oData.status != 'REFUND'){
        			return;
        		}
        		
        		var btnDelteByIdHtml = "<span class='btn default btn-xs black' ><i class='fa fa-trash-o'></i>重发";
        		var btnDelteByIdObj = $('<span>', {
        			style : "margin: 0 5px 0 5px",
        			click: function(){ sendRedpackHandler(oData.id); }
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
    
    var setQueryParams = function(aoData){
    	var params = [];
    	params.push({"name": "month", "value": $('#queryMonth').val()});
    	params.push({"name": "status", "value": $('#queryStatus').val()});

    	$.each(aoData, function(i, item){
			if(item.name == 'iDisplayStart' || item.name == 'iDisplayLength'){
				params.push(item);
			}
		});
		aoData.splice(0, aoData.length);
		$.each(params, function(i, item){
			aoData.push(item);
		});
    }
    
    //加载datatable表格数据
    var loadTableData = function(){
        var headerInfo = initTableHeaderInfo();
        oTable = $('#'+tableId).DataTable({
        	"fnServerParams": setQueryParams,
            "sAjaxSource": "wxRedpackSendRecord/service/findByPage",
            "sAjaxDataProp": "queryResult",
            "bFilter" : false,
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
    
    var sendRedpackHandler = function(id){
    	bootbox.confirm("确认重发吗?", function(result) {
			if (!result) {
				return;
			}
			var url = 'wxRedpackSendRecord/service/sendRedpack?recordId=' +id;
			
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
    
    var reload = function(){
    	oTable.fnReloadAjax();
    	countStatus();
    }
    
    var countStatus = function(){
    	var url = 'wxRedpackSendRecord/service/countStatus?month=' + $('#queryMonth').val();
		
		$.getJSON(url, function(json){
			if(json && json.status == '00'){
				$('#lb_unsent').text(json.data.UNSENT);
				$('#lb_sent').text(json.data.SENT);
				$('#lb_received').text(json.data.RECEIVED);
				$('#lb_refund').text(json.data.REFUND);
				$('#lb_failed').text(json.data.FAILED);
				return;
			}else{
				return bootbox.alert("数据获取失败,请刷新后重试");
			}
		});
    }
    
    var getStatus = function(status){
//    	SENDING:发放中 ，SENT:已发放待领取 ，FAILED：发放失败 ，RECEIVED:已领取 ，RFUND_ING:退款中 ，REFUND:已退款", "mData": "status"},
    	if(status == 'SENT'){
    		return '已发放';
    	}else if(status == 'FAILED'){
    		return '发放失败';
    	}else if(status == 'RECEIVED'){
    		return '已领取';
    	}else if(status == 'RFUND_ING'){
    		return '退款中';
    	}else if(status == 'REFUND'){
    		return '已退款';
    	}else if(status == 'UNSENT'){
    		return '待发送';
    	}else{
    		return '未知';
    	}
    }
 
    return {
        init: function () {
        	$(".form_datetime").datetimepicker({
        		format : "yyyy-mm",
        		autoclose : true,
        		pickerPosition : "bottom-left",
        		startView:'year',
        		maxView:'year',
        		minView:'decade',
        		todayBtn:true,
        		initialDate:new Date()
        	});
            loadTableData();
            countStatus();
        },
        reload: reload
    };
}();

