
var DataTableList = function () {
	var tableId = "dataTable";
	var oTable = null;
	
	//初始化加载表格数据的表头定义
    var initTableHeaderInfo = function(){
        var aoColumns = [
			{ "sTitle": "订单id", "mData": "orderId"},
			{ "sTitle": "订单号", "mData": "orderNo"},
			{ "sTitle": "会员名称", "mData": "memberName"},
			{ "sTitle": "类型", "mDataProp" : "", "sDefaultContent" : "", "sVisible" : false, "fnCreatedCell":function(nTd,sData, oData, iRow, iCol){
			    if(oData.returnType == 1){
					$(nTd).append('换货');
				}else{
					$(nTd).append('退款');
				}
			}},
			{ "sTitle": "状态", "mDataProp" : "", "sDefaultContent" : "", "sVisible" : false, "fnCreatedCell":function(nTd,sData, oData, iRow, iCol){
			    if(oData.returnStatus == 2){
					$(nTd).append('申请中');
				}else{
					$(nTd).append('审核通过');
				}
			}},
			{ "sTitle": "退款", "mDataProp" : "", "sDefaultContent" : "", "sVisible" : false, "fnCreatedCell":function(nTd,sData, oData, iRow, iCol){
				$(nTd).append(oData.fee/100);
			}},
			{ "sTitle": "退货理由", "mData": "reason"},
			{ "sTitle": "申请时间", "mDataProp" : "", "sDefaultContent" : "", "sVisible" : false, "fnCreatedCell":function(nTd,sData, oData, iRow, iCol){
				if(oData.applyTime) $(nTd).append(moment(oData.applyTime).format('YYYY-MM-DD HH:mm'));
			}},
			{ "sTitle": "审核时间", "mDataProp" : "", "sDefaultContent" : "", "sVisible" : false, "fnCreatedCell":function(nTd,sData, oData, iRow, iCol){
				if(oData.auditTime) $(nTd).append(moment(oData.auditTime).format('YYYY-MM-DD HH:mm'));
			}},
			{ "sTitle": "退货物流商名称", "mData": "deliveryName"},
			{ "sTitle": "退货物流单号", "mData": "deliveryCode"},
			{ "sTitle": "备注", "mData": "remark"},
			
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
        		var isEnableValue = oData.returnStatus == 2 ? '审核' : '取消审核';
        		var setIsEnableValue = oData.returnStatus == 2 ? 3 : 2;
        		var btnSetIsEnableHtml = "<span class='btn btn-xs purple'><i class='fa fa-edit'></i> "+isEnableValue;
        		var btnSetIsEnableObj = $('<span>', {
        			style : "margin: 0 5px 0 5px",
        			click : function(){disableHandler(oData.id, setIsEnableValue); }
        		});
        		btnSetIsEnableObj.append(btnSetIsEnableHtml);
        		btnSetIsEnableObj.appendTo($(nTd));
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
            "sAjaxSource": "orderReturn/service/findByPage",
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
    
    var disableHandler = function(id, status){
    	var msg = status != 2 ?  "确认审核吗?" : "确认取消审核吗?";
    	
    	bootbox.confirm(msg, function(result) {
    		if (!result) {
				return;
			}
			var url = 'orderReturn/service/comfireAudit?orderReturnId=' +id+'&status=' + status;
			
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
    
    var showDetail = function(icon, iRow, oData){
    	var tr = oTable.fnGetNodes(iRow);
    	if(oTable.fnIsOpen(tr)){
    		oTable.fnClose(tr);
    		$(icon).addClass("row-details-close").removeClass("row-details-open");
    		
    	}else{
            var url = 'order/service/findDetailById?id=' +oData.orderId;
			
			$.getJSON(url, function(json){
				if(json && json.status == '00'){
    		        oTable.fnOpen(tr, getDetailHtml(json.data));
					$(icon).addClass("row-details-open").removeClass("row-details-close");
					return;
				}else{
					return bootbox.alert("查询失败,请刷新后重试");
				}
			});
    		
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
                '<td style="font-weight:bold;">订单信息:</td>'+
                '<td>下单时间：'+moment(oData.addTime).format('YYYY-MM-DD HH:mm') +'&nbsp;&nbsp;&nbsp;&nbsp;付款时间：'+moment(oData.payTime).format('YYYY-MM-DD HH:mm')+'&nbsp;&nbsp;&nbsp;&nbsp;订单状态：'+getStatusStr(oData.status)+'</td>'+
            '</tr>'+
    	    '<tr>'+
    	        '<td style="font-weight:bold;">收货信息：</td>'+
    	        '<td style="padding-right:-30px;">'+oData.addressReveiver+'，&nbsp;'+oData.addressPhone+'，&nbsp;'+oData.addressRegson+'&nbsp;'+oData.address+'</td>'+
    	    '</tr>'+ productHtml +
//    	     '<tr>'+
//    	         '<td style="font-weight:bold;">订单信息:</td>'+
//    	         '<td>订单来源：微信</td></tr><tr><td style="font-weight:bold;">优惠信息:</td>'+
//    	         '<td>优惠类型：&nbsp;&nbsp;&nbsp;&nbsp;优惠券码：&nbsp;&nbsp;&nbsp;&nbsp;现金券类型：&nbsp;&nbsp;&nbsp;&nbsp;卡券减免金额：￥0&nbsp;&nbsp;&nbsp;&nbsp;营销规则优惠总金额：￥0</td>'+
//    	     '</tr>'+
    	 '</tbody></table>';
    	
    	return html;
    };
    
    var getStatusStr = function(status){
    	switch(status){
    	case 100:
    		return '待支付';
    	case 200:
    		return '待发货';
    	case 300:
    		return '待收货';
    	case 400:
    		return '已完成';
    	case 500:
    		return '已取消';
    	case 600:
    		return '申请退换货';
    	}
    }
 
    return {
        init: function () {
            loadTableData();
        }
    };
}();

