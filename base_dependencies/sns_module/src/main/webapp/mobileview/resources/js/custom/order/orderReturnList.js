var Orderform_order = function () {
    var tableId = "orderTable";
    var oTable = null;

    //初始化加载表格数据的表头定义
    var initTableHeaderInfo = function(){
        var aoColumns = [
	        /*{
	             "sTitle":'<th class="table-checkbox"><input type="checkbox" class="group-checkable" data-set="#'+tableId+' .checkboxes"/></th>',
	             "bSortable": false,
	             "bSearchable": false,
	             'sWidth':'2%',
	             "mDataProp" : "",
	             "sDefaultContent" : "",
	             "sVisible" : false,
	             "fnCreatedCell" : function(nTd, sData, oData, iRow, iCol) {
	                 $(nTd).append('<td class="table-checkbox">');
	                 if(oData.process=="5"){
	                     $(nTd).append('<input type="checkbox" class="checkboxes" value="'+ oData.id +'"/>');
	                 }else{	                	
	                	 $(nTd).append('');
	                 }
	                 $(nTd).append('</td>');
	             }
	        },*/
            {
				"sTitle": "",
				"mDataProp" : "",
				'sWidth':'2%',
				"sDefaultContent" : "",//此列默认值为""，以防数据中没有此值，DataTables加载数据的时候报错
				"sVisible" : false,
				"fnCreatedCell" : function(nTd,sData, oData, iRow, iCol) {
						$(nTd).append("<span class='row-details row-details-close'></span>");
						$(nTd).append("<input type='hidden' name='orderId' value="+oData.orderid+">");
				}
			},
            { "sTitle": "订单号", "mData": "ext1"},
            { "sTitle": "退货单号",'sWidth':'4%', "mData": "ext2" },
            {
            	"sTitle": "退换货类型",
            	"mDataProp" : "",
            	'sWidth':'7%',
            	"sDefaultContent" : "",
            	"sVisible" : false,
            	"fnCreatedCell" : function(nTd,sData, oData, iRow, iCol) {
            		if(oData.ordertype==1){
            			$(nTd).append("<label class='label label-info'>退换货</label>");
            		}else{
            			$(nTd).append("<label class='label label-info'>退款</label>");
            		}
            		
            	}
            },
			{
				"sTitle": "退换货状态",
				"mDataProp" : "",
				'sWidth':'7%',
				"sDefaultContent" : "",
				"sVisible" : false,
				"fnCreatedCell" : function(nTd,sData, oData, iRow, iCol) {
					if(oData.returnstatus=="apply"){
            			$(nTd).append("<label class='label label-danger'>申请中</label>");
            		}else{
            			$(nTd).append("<label class='label label-success'>完成退货</label>");
            		}
				}
			},
			{
				"sTitle": "退货原因",
				"mDataProp" : "",
				"sDefaultContent" : "",
				"sVisible" : false,
				"fnCreatedCell" : function(nTd,sData, oData, iRow, iCol) {
					$(nTd).append(oData.returnreason);
				}
			},
            { "sTitle": "审核备注",'sWidth':'4%', "mData": "ext3" },
			{
				"sTitle": "审核通过时间",
				'sWidth':'5%',
				"mDataProp" : "",
				"sDefaultContent" : "",
				"sVisible" : false,
				"fnCreatedCell" : function(nTd,sData, oData, iRow, iCol) {
					$(nTd).append((oData.audittime==null ? "" :changeDateFormat(oData.audittime)));
				}
			}
        ];
        //渲染特殊的列(操作列)
        var aoColumnDefs =[];
            aoColumns.push({
            	"sTitle": "操作",
            	"mDataProp" : "",
            	"sDefaultContent" : "",
            	"sVisible" : false, 
            	"fnCreatedCell": function(nTd,sData, oData, iRow, iCol){
            		if(oData.returnstatus=="apply"){
            			$(nTd).append("<span class='btn btn-xs purple black' data-toggle='modal' " +
            					"href='#confirmWin2' enable='ok' " + "id='"+ oData.id +"'>" +
                        "<i class='fa fa-edit'></i> 审核 </span>");
            		}
            		
                }
            });

        return {
            aoColumns : aoColumns,
            aoColumnDefs : aoColumnDefs
        };
    };
    


    //加载表格数据
    var loadTableData = function(){
        var headerInfo = initTableHeaderInfo();
        oTable = $('#'+tableId).DataTable({
        	"fnServerParams": function (aoData) {
        		aoData.push({"name": "status", "value": $("#status").val()}),
        		aoData.push({"name": "outNo", "value": $("#outNo").val()}),
                aoData.push({"name": "yourName", "value": $("#yourName").val()}),
                aoData.push({"name": "telPhone", "value": $("#telPhone").val()})
        	},
            "sAjaxSource": "queryOrderRuturnByPage.html",
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
            "bAutoWidth":true,
            "bSortCellsTop": true,
            "iTabIndex": 1,
            "sServerMethod": "POST",
            "bServerSide": true,
            "aoColumns": headerInfo.aoColumns,
            "aoColumnDefs" : headerInfo.aoColumnDefs,
            "iDisplayLength": 10,
            "oLanguage": {
                "sLengthMenu": "显示 _MENU_ 条记录",
                "sInfo": "当前显示 _START_ 到 _END_ 总共 _TOTAL_ 条记录",
                "sInfoEmpty" : "搜索结果为0条记录",
                "sZeroRecords" : "当前没有记录",
                "sSearch" : "搜索：",
                "oPaginate": {
                    "sFirst":"首页",
                    "sPrevious": "上一页",
                    "sNext": "下一页",
                    "sLast":"末页"
                }
            }
        });
    };

    
	//修改价格按钮
    var editPriceBtn = function(btn){  	 
    	 var $price = $(btn).prev('input');
         if($price.attr("readonly")=="readonly"){
         	$(btn).html("确定");
         	$price.attr("readonly",false);
         	}else{
        	 if(isNaN($price.val())){
        		return false;
        	}
         	$price.attr("readonly",true);
         	$(btn).html("修改");
         	$.ajax({
                 type: "post",
                 data: {
                 	 fee : $price.val()*100,
                 	 id  : $(btn).attr("dealId") 
                 },
                 url: 'modifyOrder.html',
                 success: function (data) {
                    oTable.fnDestroy();
                    Orderform_order.loadTableData();
                 }
             });
         }
     
    };    
    	
  
    var initToolBar = function(){
/*    	$(".table-toolbar").hide();
    	//高级筛选按钮
    	$("#SearchBut").on("click", function(){
    		$(".table-toolbar").show();
    	});
*/    	
    	
    	  //退款确认model框
        $("#confirmWin").on("show.bs.modal", function(e){
            var orderFormId = $(e.relatedTarget).attr("orderFormId");
            $("#orderFormId").val('').val(orderFormId);
        });
        
        
        //确认退款按钮出发时间
        $("#confirmBtn").on("click", function(){
        	 $.ajax({
                 type : "post",
                 dataType : "json",
                 url : "refund.html",
                 data : { 
                	 id : $("#orderFormId").val(),
                	 process : 7,
                 	 status : 2  //退款状态
                 },
                 success : function(data){
                 	 $("#confirmWin").modal("hide");
                 	 oTable.fnReloadAjax();       //刷新datatable列表
                 }
             })
        	
        });
        
        
    	
    //筛选tab页点击事件
  	  $("#orderListTab").delegate('li', 'click', function(){
  		$("#process").attr("value",$(this).attr("process"));
  		$("#status").attr("value",$(this).attr("status"));
			if(oTable && $(this).attr("class")=="active"){
	             return false;
	        }
			oTable.fnDestroy();
        	loadTableData(); 
        });
    	
  	  //清空input框等
  	 function clean() {
         $(':input').not(':text',':button, :submit, :reset, :hidden').val('').removeAttr('checked') .removeAttr('selected');
    };
  	  
  	  //绑定日历
      if (jQuery().timepicker) {
      	$('.timepicker').timepicker({
      		  template: 'dropdown' ,
              showMeridian: false,
              autoclose: true,
              language: "zh-CN",
              defaultTime:""
          });
      }
  	  
    	
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
        
        //订单发货
        $("#confirmWin2").on("show.bs.modal", function(e){ //触发相关赋值
            var id = $(e.relatedTarget).attr("id");
            $("#id").val('').val(id);
        });
        $("#confirmBut").on("click", function(){
        	var param = {orderReturnId : $("#id").val(),ext3 : $("#ext3").val()};
        	
            $.ajax({
                type : "post",
                dataType : "json",
                url : "orderReturn/finishOrderReturnById.html",
                data : param,
                success : function(data) {
                	console.log(data.status);
                    if(data.status=="00"){
                        oTable.fnReloadAjax();
                        bootbox.alert("审核成功");
                        $("#confirmWin2").modal("hide");
                    }else{
                        bootbox.alert("审核失败");
                    }
                }
            });
        });
    	
	   jQuery('#'+tableId+' .group-checkable').change(function () {
            var set = jQuery(this).attr("data-set");
            var checked = jQuery(this).is(":checked");
            jQuery(set).each(function () {
                if (checked) {
                    $(this).attr("checked", true);
                    $(this).parents('tr').addClass("active");
                } else {
                    $(this).attr("checked", false);
                    $(this).parents('tr').removeClass("active");
                }
            });
            jQuery.uniform.update(set);
        });
        
    	//确认订单操作
        $("#finishOrderBut").on("click", function(){
           /* var orderIds = getSelectOrderIds();*/
    		
    		var str = [];
			$('#'+tableId +' tbody tr .checkboxes:checked').each(function() {
				this.checked = !this.checked;
				str += $(this).val()+",";
			});
    		if (str == "") {
    		alert("请至少选择一个");
    		return;
    		}
            $.ajax({
                type: "post",
                data: {
                	"orderId":str
                },
                url: 'finishOrder.html',
                success: function (data) { 
                }               
            });
            bootbox.alert("确认订单成功!");                                
        	oTable.fnDestroy();
	    	loadTableData();  
        });
        
	    jQuery('#'+tableId).on('change', 'tbody tr .checkboxes', function(){
	    	$(this).parents('tr').toggleClass("active");
	    });
	    	
		
	
	    //保存
	    $("#statusSearch").on("click", function(){
	    	oTable.fnDestroy();
	    	loadTableData();	
	    	clean();
	    });
	        
	    	
		//注册点击查看详情事件
	    $('#'+tableId).on('click', ' tbody td .row-details', function () {
	        var nTr = $(this).parents('tr')[0];
	        //获取隐藏域的userId值
	        var orderId = $(this).parents('tr').find("input:hidden").val();
	        if (oTable.fnIsOpen(nTr)){
	            /* 关闭 */
	            $(this).addClass("row-details-close").removeClass("row-details-open");
	            oTable.fnClose(nTr);
	        }else{
	            /* 打开 */
	            $(this).addClass("row-details-open").removeClass("row-details-close");
	            Orderform_order.fnFormatDetails(oTable, nTr, orderId);
	        }
	    });
	    	
	
	    $("#exportExcel").on("click", function(){
	    	var payType = $("#payType").val();
	    	var startTime = $("#startTime").val();
	    	var endTime = $("#endTime").val();
	    	var startDate = $("#startDate").val();
	    	var endDate = $("#endDate").val();
	    	var merchantId = $("#merchantId").val();
	    	var province = $("#provinceId").val();
	    	var city = $("#regionCityId").val();
	    	var area = $("#regionAreaId").val();
	    	var process = $("#process").val();
	    	var status = $("#status").val();
	    	if(confirm("订单导出时间有点久，确定要执行此操作？")){ 	    		
					$.ajax({
						type : "POST",
						url : "excel.html",
						data : {
						"payType":payType,
						"startTime":startTime,
						"endTime":endTime,
						"endDate":endDate,
						"startDate":startDate,
						"merchantId":merchantId,
						"province":province,
						"city":city,
						"area":area,
						"process":process,
						"status":status
					    },
						success : function(msg) {
							$("#aaa").html("<a id='bbb' href='./resources/download/excel/"+msg+"_订单统计表.xls'></a>");
							var d = document.getElementById("bbb");
							d.click();
						},
						error : function(err) {
							//isSignAlert(msg);
						}
					});
					return true; 
					}
	    	        return false; 
	         });
		};
    
    
    //加载用户详情函数
    var fnFormatDetails = function(oTable, nTr, orderId)
    {
       $.ajax({
	   		 type : "post",
	   		 dataType : "json",
	   		 data : {orderId : orderId},
	   		 url : "merriplusApi/getOrderInfoByOrderId.html",
	   		 success : function(data){
                 var sOut = '<table>';
                 if(data.status =="00"){
                     var orderInfo = data.data.orderInfo;
	                 var addressInfo= data.data.addressInfo;
	                 var orderProductsInfo= data.data.orderProductsInfo;
                     sOut +='<tr>' +
                         '<td style="font-weight:bold;">收货信息:</td>' +
                         '<td style="padding-right:30px;">'+addressInfo.name+'，&nbsp;'+addressInfo.phone+'，&nbsp;'+addressInfo.address+'</td></tr>';
                     sOut += '<tr><td style="font-weight:bold;">商品信息:</td>';
                      $.each(orderProductsInfo, function(index, item){
                      	      sOut +='<td style="float:left;padding-right:30px;"><img src="'+ item.productcoverimg +'" width=45px height=45px style="border-radius:10px;"/> '+
                      	      		  '<span>'+item.productname+'，'+item.nums+'份，￥'+item.price/100 + '；' +'</span></td>';
                      });
                      sOut += '<tr><td style="font-weight:bold;">订单信息:</td>'+
                      	  '<td>下单时间：'+(orderInfo.addtime==null ? "" :changeDateFormat(orderInfo.addtime))+
                    	  '&nbsp;&nbsp;&nbsp;&nbsp;付款时间：'+
                        	(orderInfo.paytime==null ? "" :  changeDateFormat(orderInfo.paytime)) +
                        	'&nbsp;&nbsp;&nbsp;&nbsp;支付类型：'+
                        	(orderInfo.paytype==null ? "" : convertPayType(orderInfo.paytype)) +
                        	'&nbsp;&nbsp;&nbsp;&nbsp;订单状态：'+(orderInfo.paystatus==null ? "" :  orderInfo.paystatus) +'</td></tr>';
                      sOut += '<tr>'+
                      	'<td style="font-weight:bold;">其他信息:</td>' +
                      	'<td>品牌：'+(orderInfo.orderbrand==null ? "" :orderInfo.orderbrand)+
                      	'&nbsp;&nbsp;&nbsp;&nbsp;交易流水号：'+
                      	(orderInfo.thirdno==null ? "" :  orderInfo.thirdno) +
                      	'&nbsp;&nbsp;&nbsp;&nbsp;订单来源：'+(orderInfo.orderpayresource==null ? "" :  orderInfo.orderpayresource) +'</td></tr>';
                 }
                 sOut += '</table>';
                 oTable.fnOpen( nTr, sOut, 'details');
	   		 }
	   	});
    }
    
    //付款状态转换
    var convertPayStatus = function(payStatus){
        var html = '';
        if(payStatus == "0"){
            html = "<label class='label label-info'>未付款</label>";
        }else if(payStatus == "1"){
            html = "<label class='label label-success'>已付款</label>";
        }else if(payStatus == "2"){
            html = "<label class='label label-danger'>已退款</label>";
        }else{
            html = "<label class='label label-primary'>未支付，已自动取消</label>";
        }
        return html;
    };
    
    //付款类型转换
    var convertPayType = function(payType){
        var html = '';
        if(payType == "微信支付"){
            html = "<label class='label label-danger'>微信支付</label>";
        }else if(payType == "支付宝"){
            html = "<label class='label label-danger'>支付宝</label>";
        }else{
            html = "<label class='label label-primary'>暂无支付方式</label>";
        }
        return html;
    };
    
    //订单状态转换
    var convertProcessStatus = function(processStatus){
        var html = '';
        if(processStatus == "-1"){
            html = "<label class='label label-info'>已取消</label>";
        }else if(processStatus == "0"){
            html = "<label class='label label-info'>未支付</label>";
        }else if(processStatus == "1"){
            html = "<label class='label label-success'>已支付</label>";
        }else if(processStatus == "2"){
            html = "<label class='label label-success'>已完成</label>";
        }else if(processStatus == "3"){
            html = "<label class='label label-success'>已退款</label>";
        }else if(processStatus == "4"){
            html = "<label class='label label-success'>取消订单</label>";
        }else{
            html = "<label class='label label-primary'>暂无状态</label>";
        }
        return html;
    };
    
    //订单来源
    var convertOrderSource = function(orderSource){
    	var html = '';
    	if(orderSource == "微信"){
    		html = "<label>微信公众平台</label>";
    	}else if(orderSource == "支付宝"){
    		html = "<label>支付宝钱包服务窗</label>";
    	}else{
    		html = "<label>无</label>";
    	}
    	return html;
    };

    return {
        init: function () {
           loadTableData();
           initToolBar();
        },
        loadTableData:loadTableData,
        fnFormatDetails:fnFormatDetails,
        editPriceBtn : editPriceBtn
    };
}();