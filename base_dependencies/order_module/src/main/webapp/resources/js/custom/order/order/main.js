
var DataTableList = function () {
	var tableId = "dataTable";
	var oTable = null;
	
	//初始化加载表格数据的表头定义
    var initTableHeaderInfo = function(){
        var aoColumns = [
        	{ "sTitle": "订单编号", "mData": "orderNo"},
        	{ "sTitle": "会员昵称", "mData": "memberNickName"},
        	{ "sTitle": "商品总数量", "mData": "nums"},
			{ "sTitle": "订单总金额", "mData": "amountTotal"},
			{ "sTitle": "快递费", "mData": "freight"},
			{ "sTitle": "订单状态", "mData": "status"},
			{ "sTitle": "支付方式", "mData": "payType"},
			{ "sTitle": "第三方交易流水号", "mData": "tradeNo"},
			{ "sTitle": "收货省市县", "mData": "addressRegson"},
			{ "sTitle": "详细地址", "mData": "address"},
			{ "sTitle": "手机号码", "mData": "addressPhone"},
			{ "sTitle": "收货人姓名", "mData": "addressReveiver"},
			{ "sTitle": "推送E3状态", "mData": "postE3Status"},
			{ "sTitle": "下单时间", "mData": "", "sDefaultContent":"","sVisible":false, 
				"fnCreatedCell":function(nTd,sData, oData, iRow, iCol){
					$(nTd).append(moment(oData.addTime).format('YYYY-MM-DD HH:mm'));
            }},
			{ "sTitle": "支付时间", "mData": "", "sDefaultContent":"","sVisible":false, 
				"fnCreatedCell":function(nTd,sData, oData, iRow, iCol){
					$(nTd).append(moment(oData.payTime).format('YYYY-MM-DD HH:mm'));
            }},
			{ "sTitle": "发货时间", "mData": "", "sDefaultContent":"","sVisible":false, 
				"fnCreatedCell":function(nTd,sData, oData, iRow, iCol){
					$(nTd).append(moment(oData.deliveryTime).format('YYYY-MM-DD HH:mm'));
            }},
			{ "sTitle": "确认收货时间", "mData": "", "sDefaultContent":"","sVisible":false, 
				"fnCreatedCell":function(nTd,sData, oData, iRow, iCol){
					$(nTd).append(moment(oData.receiveTime).format('YYYY-MM-DD HH:mm'));
            }},
			{ "sTitle": "退款时间", "mData": "", "sDefaultContent":"","sVisible":false, 
				"fnCreatedCell":function(nTd,sData, oData, iRow, iCol){
					$(nTd).append(moment(oData.returnTime).format('YYYY-MM-DD HH:mm'));
            }},
        ];
        
        aoColumns.unshift({
        	"sTitle": "详情",
        	"mDataProp" : "",
        	"sDefaultContent" : "",
        	"sVisible" : false, 
        	"fnCreatedCell":function(nTd,sData, oData, iRow, iCol){
        		var btnDelteByIdObj = $('<span>', {
        			"class":"row-details row-details-close",
        			click: function(){ showDetail(this, iRow, oData);}
        		});
        		btnDelteByIdObj.appendTo($(nTd));
        	}
        });
        
        aoColumns.push({
        	"sTitle": "操作",
        	"mDataProp" : "",
        	"sDefaultContent" : "",
        	"sVisible" : false, 
        	"fnCreatedCell": function(nTd,sData, oData, iRow, iCol){
//        	    var btnUpdateByIdHtml = "<span style='margin: 0 5px 0 5px'><span class='btn btn-xs purple ajaxify' href='order/service/toUpdateViewById?id="+oData.id+"'><i class='fa fa-edit'></i> 更改 </span>";
//        		var btnUpdateByIdObj = $(btnUpdateByIdHtml).appendTo($(nTd));
//        		
//        		var btnDelteByIdHtml = "<span class='btn default btn-xs black' ><i class='fa fa-trash-o'></i>删除";
//        		var btnDelteByIdObj = $('<span>', {
//        			style : "margin: 0 5px 0 5px",
//        			click: function(){ deleteHandler(oData.id); }
//        		});
//        		btnDelteByIdObj.append(btnDelteByIdHtml);
//        		btnDelteByIdObj.appendTo($(nTd));
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
        		var tmp = [];
        		$.each(aoData, function(i, item){
        			if(item.name == 'iDisplayStart' || item.name == 'iDisplayLength'){
        				tmp.push(item);
        			}
        		});
        		aoData.splice(0, aoData.length);
        		$.each(getQueryParams(), function(i, item){
        			aoData.push(item);
        		});
        		
        		$.each(tmp, function(i, item){
        			aoData.push(item);
        		});
            },
            "sAjaxSource": "order/service/findByPage",
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
    
    var showDetail = function(icon, iRow, oData){
    	var tr = oTable.fnGetNodes(iRow);
    	if(!oTable.fnIsOpen(tr)){
    		oTable.fnOpen(tr, getDetailHtml(oData));
    		$(icon).addClass("row-details-open").removeClass("row-details-close");
    	}else{
    		oTable.fnClose(tr);
    		$(icon).addClass("row-details-close").removeClass("row-details-open");
    	}
    }
    
    var getDetailHtml = function(oData){
    	var productHtml ='';
    	$.each(oData.orderProductList, function(i ,item){
    		productHtml = productHtml + '<tr>'+
	        '<td style="font-weight:bold;">商品信息:</td>'+
	        '<td style="float:left;padding-right:20px;"><span>'+item.productName+'，'+item.nums+'份，￥'+item.productPrice *item.nums/100 +'；</span></td>'+
	     '</tr>';
    	});
    	
    	var html =  
    	'<table><tbody>'+
    	    '<tr>'+
    	        '<td style="font-weight:bold;">顾客信息:</td>' +
    	        '<td style="padding-right:-30px;">'+oData.memberNickName+'，&nbsp;'+oData.memberRealName+'&nbsp;</td>'+
    	    '</tr>'+
    	    '<tr>'+
    	        '<td style="font-weight:bold;">收货信息：</td>'+
    	        '<td style="padding-right:-30px;">'+oData.addressReveiver+'，&nbsp;'+oData.addressPhone+'，&nbsp;'+oData.addressRegson+'&nbsp;'+oData.address+'</td>'+
    	    '</tr>'+ productHtml +
//    	    '<tr>'+
//    	         '<td style="font-weight:bold;">订单信息:</td>'+
//    	         '<td>订单来源：微信</td>'+
//    	         '<td>下单时间：'+moment(oData.addTime).format('YYYY-MM-DD HH:mm') +'&nbsp;&nbsp;&nbsp;&nbsp;付款时间：'+moment(oData.payTime).format('YYYY-MM-DD HH:mm')+'&nbsp;&nbsp;&nbsp;&nbsp;支付类型：<label class="label label-danger">微信支付</label>&nbsp;&nbsp;&nbsp;&nbsp;订单状态：已发货</td>
//    	    '</tr>'+
//    	    '<tr>'+
//    	         '<td style="font-weight:bold;">优惠信息:</td>'+
//    	         '<td>优惠类型：&nbsp;&nbsp;&nbsp;&nbsp;优惠券码：&nbsp;&nbsp;&nbsp;&nbsp;现金券类型：&nbsp;&nbsp;&nbsp;&nbsp;卡券减免金额：￥0&nbsp;&nbsp;&nbsp;&nbsp;营销规则优惠总金额：￥0</td>'+
//    	     '</tr>'+
    	 '</tbody></table>';
    	
    	return html;
    }
    
    var getQueryParams = function(){
    	var params = [];
    	params.push({"name": "status", "value": $('#orderListTab .active').attr('status')});
    	
    	params.push({"name": "orderNo", "value": $('#orderNoInput').val()});
    	params.push({"name": "tradeNo", "value": $('#tradeNoInput').val()});
    	params.push({"name": "memberNickName", "value": $('#memberNickNameInput').val()});
    	params.push({"name": "addressPhone", "value": $('#addressPhoneInput').val()});
    	params.push({"name": "deliveryCode", "value": $('#deliveryCodeInput').val()});
    	params.push({"name": "startDate", "value": $('#startDate').val()});
    	params.push({"name": "endDate", "value": $('#endDate').val()});
//    	params.push({"name": "openid", "value": $('#openidInput').val()})
    	
    	return params;
    };
    
    var initEvent = function(){
    	$('#searchBtn').click(function(){
    		oTable.fnReloadAjax();
    	});
    	
    	$('#orderListTab li').on('shown.bs.tab', function(e){
    		oTable.fnReloadAjax();
    		
    	});
    	
    	$(".form_datetime").datetimepicker({
    		format : "yyyy-mm-dd hh:ii:ss",
    		autoclose : true,
    		todayBtn : true,
    		pickerPosition : "bottom-left",
    		minuteStep : 1
    	});
    };
    
    return {
        init: function () {
            loadTableData();
            initEvent();
        }
    };
}();

