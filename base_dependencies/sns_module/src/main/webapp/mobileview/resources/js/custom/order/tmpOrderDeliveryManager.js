var Orderform_tmpOrderDelivery = function () {
    var tableId = "orderTable";
    var oTable = null;

    //初始化加载表格数据的表头定义
    var initTableHeaderInfo = function(){
        var aoColumns = [
            { "sTitle": "订单号", "mData": "orderno"},
            { "sTitle": "物流单号", "mData": "deliveryorderno"},
            { "sTitle": "物流名称", "mData": "deliveryname"},
            {
            	"sTitle": "是否已更新",
            	"mDataProp" : "",
            	'sWidth':'7%',
            	"sDefaultContent" : "",
            	"sVisible" : false,
            	"fnCreatedCell" : function(nTd,sData, oData, iRow, iCol) {
            		if(oData.isupdate==0){
            			$(nTd).append("<label class='label label-info'>待更新</label>");
            		}else{
            			$(nTd).append("<label class='label label-success'>已更新</label>");
            		}
            	}
            }
        ];

        return {
            aoColumns : aoColumns
        };
    };
    


    //加载表格数据
    var loadTableData = function(){
        var headerInfo = initTableHeaderInfo();
        var search = $("#orderSearchForm").serializeArray();
        oTable = $('#'+tableId).DataTable({
        	"fnServerParams": function (aoData) {
        		$.each(search, function(index, value){
        			aoData.push({"name": value.name, "value": value.value})
                });
        	},
            "sAjaxSource": "queryTmpOrderDeliveryData.html",
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
    	
        
        //订单发货
        $("#confirmWin2").on("show.bs.modal", function(e){ //触发相关赋值
            var id = $(e.relatedTarget).attr("id");
            $("#id").val('').val(id);
        });
        $("#confirmBut").on("click", function(){
        	var param = {orderId : $("#id").val()};
        	console.log(param);
            $.ajax({
                type : "post",
                dataType : "json",
                url : "merriplusApi/sendOrder.html",
                data : param,
                success : function(data) {
                    if(data.status=="00"){
                        oTable.fnReloadAjax();
                        bootbox.alert("发货成功");
                        $("#confirmWin2").modal("hide");
                    }else{
                        bootbox.alert("发货失败");
                    }
                }
            });
        });
    	
	   //jQuery('#'+tableId+' .group-checkable').change(function () {
	   jQuery('#'+tableId).on('change', '.group-checkable', function(){
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
        
    	//批量发货操作
        $("#sendOrderBut").on("click", function(){
            var orderIds = getSelectOrderIds();
    		
    		var str = [];
    		//var len=seachboxs.length;
			$('#'+tableId +' tbody tr .checkboxes:checked').each(function() {
				this.checked = !this.checked;
				str += $(this).val()+",";
				console.log(str)
			});
			//s=s.substring(0,s.Length-1)；
    		if (str == "") {
    		alert("请至少选择一个");
    		return;
    		}
            $.ajax({
                type: "post",
                data: {
                	"orderId":str
                },
                url: 'batchSendOrder.html',
                success: function (data) { 
                }               
            });
            bootbox.alert("所有订单已发货成功!");                                
        	oTable.fnDestroy();
	    	loadTableData();  
        });
        
	    jQuery('#'+tableId).on('change', 'tbody tr .checkboxes', function(){
	    	console.log($(this).parents('tr'))
	    	$(this).parents('tr').toggleClass("active");
	    });
	    	
		
	
	    //保存
	    $("#submitForm").on("click", function(){
	    	var readFile = $("#readFile").val();
	    	alert(readFile);
	    	$.ajax({
				type : "POST",
				url : "order/importOrderExcel.html",
				dataType:"file",
				data : {
				  "readFile":readFile
			    },
				success : function(msg) {
					bootbox.alert("发货成功!");
				},
				error : function(err) {
					//isSignAlert(msg);
				}
			});
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
    
    

    return {
        init: function () {
           loadTableData();
           initToolBar();
        },
        loadTableData:loadTableData,
    };
}();