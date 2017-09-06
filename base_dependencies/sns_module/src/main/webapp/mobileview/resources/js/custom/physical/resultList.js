var Physical_ResultList = function () {
	var tableId = "resultListTable";
	var oTable = null;
	
	//初始化加载表格数据的表头定义
    var initTableHeaderInfo = function(){
    	var aoColumns = [
 			{ "sTitle": "ID", "mData": "id"},
 			{ "sTitle": "会员ID", "mData": "memberid"},
 			{ "sTitle": "体检报告文件ID", "mData": "physicalfileid"},
 			{ "sTitle": "识别结果", "mData": "resultrecognize"},
 			{ "sTitle": "综合得分", "mData": "score"},
 			{ 
				"sTitle": "创建时间",
				"mDataProp" : "",
				"sDefaultContent" : "",
				"sVisible" : false,
				"fnCreatedCell" : function(nTd,sData, oData, iRow, iCol) {
					$(nTd).append(changeDateFormat(oData.createtime));
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
             		$(nTd).append("<span style='margin: 0 5px 0 5px'><span class='btn btn-xs purple ajaxify'" +
         					" href='physicalEditResultView?id="+oData.id+"'><i class='fa fa-edit'></i> 编辑 </span>");
                 }
             });

         return {
             aoColumns : aoColumns,
             aoColumnDefs : aoColumnDefs
         };
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
            "sAjaxSource": "physicalQueryResultList",
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

    //初始化界面相关事件
    var initPageEvent = function(){
        //注册点击查看详情事件
        $('#'+tableId).on('click', ' tbody td .row-details', function () {
            var nTr = $(this).parents('tr')[0];
            //获取隐藏域的userId值
            var userId = $(this).parents('tr').find("input:hidden").val();
            if (oTable.fnIsOpen(nTr)){
                /* 关闭 */
                $(this).addClass("row-details-close").removeClass("row-details-open");
                oTable.fnClose(nTr);
            }else{
                /* 打开 */
                $(this).addClass("row-details-open").removeClass("row-details-close");
                ORG_ContactList.fnFormatDetails(oTable, nTr, userId);
            }
        });
        
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
        
        //批量设置可兑换礼品的操作
        $("#setGiftsBut").on("click", function(){
    		var str = [];
    		//var len=seachboxs.length;
			$('#'+tableId +' tbody tr .checkboxes:checked').each(function() {
				this.checked = !this.checked;
				str += $(this).val()+",";
				console.log(str)
			});
			
    		if (str == "") {
	    		alert("请至少选择一个产品");
	    		return;
    		}else{
    			str=str.substring(0,str.length-1);
    		}
           $.ajax({
                type: "post",
                data: {
                	"productId":str,
                	"giftStatus":0
                },
                url: 'setGifts.html',
                success: function (data) { 
                }               
            });
            bootbox.alert("操作成功");                                
        	oTable.fnDestroy();
        	Product_ProductList.loadTableData();
        });
        
      //批量设置为非礼品的操作
        $("#cancelGiftsBut").on("click", function(){
    		var str = [];
			$('#'+tableId +' tbody tr .checkboxes:checked').each(function() {
				this.checked = !this.checked;
				str += $(this).val()+",";
				console.log(str)
			});
			
    		if (str == "") {
	    		alert("请至少选择一个产品");
	    		return;
    		}else{
    			str=str.substring(0,str.length-1);
    		}
           $.ajax({
                type: "post",
                data: {
                	"productId":str,
                	"giftStatus":1
                },
                url: 'setGifts.html',
                success: function (data) { 
                }               
            });
            bootbox.alert("操作成功");                                
        	oTable.fnDestroy();
        	Product_ProductList.loadTableData();
        });
        
    };
    
  //批量开启优惠价的操作
    $("#openDiscountPriceBut").on("click", function(){
		var str = [];
		$('#'+tableId +' tbody tr .checkboxes:checked').each(function() {
			this.checked = !this.checked;
			str += $(this).val()+",";
			console.log(str)
		});
		
		if (str == "") {
    		alert("请至少选择一个产品");
    		return;
		}else{
			str=str.substring(0,str.length-1);
		}
       $.ajax({
            type: "post",
            data: {
            	"productId":str,
            	"isOpenDiscountPrice":1
            },
            url: 'setGifts.html',
            success: function (data) { 
            }               
        });
        bootbox.alert("操作成功");                                
    	oTable.fnDestroy();
    	Product_ProductList.loadTableData();  
    });
    //批量关闭优惠价的操作
    $("#closeDiscountPriceBut").on("click", function(){
    	var str = [];
    	$('#'+tableId +' tbody tr .checkboxes:checked').each(function() {
    		this.checked = !this.checked;
    		str += $(this).val()+",";
    		console.log(str)
    	});
    	
    	if (str == "") {
    		alert("请至少选择一个产品");
    		return;
    	}else{
    		str=str.substring(0,str.length-1);
    	}
    	$.ajax({
    		type: "post",
    		data: {
    			"productId":str,
    			"isOpenDiscountPrice":0
    		},
    		url: 'setGifts.html',
    		success: function (data) { 
    		}               
    	});
    	bootbox.alert("操作成功");                                
    	oTable.fnDestroy();
    	Product_ProductList.loadTableData();  
    });

   //批量上架
    $("#batchGrounding").on("click", function(){
		var str = [];
		//var len=seachboxs.length;
		$('#'+tableId +' tbody tr .checkboxes:checked').each(function() {
			this.checked = !this.checked;
			str += $(this).val()+",";
			console.log(str)
		});
		
		if (str == "") {
    		alert("请至少选择一个产品");
    		return;
		}else{
			str=str.substring(0,str.length-1);
		}
       $.ajax({
            type: "post",
            data: {
            	"productId":str,
            	"isSale":0
            },
            url: 'batchGroundingOrNot.html',
            success: function (data) { 
            }               
        });
        bootbox.alert("操作成功");                                
    	oTable.fnDestroy();
    	Product_ProductList.loadTableData();
    });
    
    //批量下架
    $("#batchUndercarriage").on("click", function(){
    	var str = [];
    	/*//var len=seachboxs.length;
    	$('#'+tableId +' tbody tr .checkboxes:checked').each(function() {
    		this.checked = !this.checked;
    		str += $(this).val()+",";
    		console.log(str)
    	});*/
    	str = $("#preferredProductId").val();
    	if (str == "") {
    		alert("请至少选择一个产品");
    		return;
    	}else{
    		str=str.substring(0,str.length-1);
    	}
    	$.ajax({
    		type: "post",
    		data: {
    			"productId":str,
    			"isSale":1
    		},
    		url: 'batchGroundingOrNot.html',
    		success: function (data) { 
    		}               
    	});
    	bootbox.alert("操作成功");                                
    	oTable.fnDestroy();
    	Product_ProductList.loadTableData();
    });
    
    //加载用户详情函数
    var fnFormatDetails = function(oTable, nTr, userId)
    {
       $.ajax({
	   		 type : "post",
	   		 dataType : "json",
	   		 data : {userId : userId},
	   		 url : "queryUserDetail.html",
	   		 success : function(data){
                 var sOut = '<table>';
                 if(data.code ==0){
                     var user = data.data;
                     sOut +='<tr>' +
                         '<td>用户姓名:</td>' +
                         '<td>'+user.nickName+'</td>' +
                         '<td>登录账号:</td>' +
                         '<td>'+user.loginName+'</td>' +
                         '<td>手机号码:</td>' +
                         '<td>'+(user.mobilePhone==null ? "" : user.mobilePhone) +'</td>' +
                         '<td>邮箱:</td>'+
                         '<td>'+(user.email==null ? "" : user.email) +'</td></tr>';
                     sOut += '<tr><td>创建时间:</td><td>'+ user.createTime +'</td><td>更新时间:</td><td>'+user.updateTime+'</td></tr>';
                 }
                 sOut += '</table>';
                 oTable.fnOpen( nTr, sOut, 'details');
	   		 }
	   	});
    };
    
    var initToolBar = function(){
        //触发相关赋值
        $("#confirmWin").on("show.bs.modal", function(e){
            var id = $(e.relatedTarget).attr("id");
            $("#id").val('').val(id);
        });
        //删除
        $("#deleteBut").on("click", function(){
            var param = {id : $("#id").val()};
            $.ajax({
                type: "post",
                data: param,
                url: 'deleteProduct.html',
                success: function (data) {
                    if (data.code == 0) {
                    } else {

                    }
                    $("#confirmWin").modal("hide");
                    //oTable.fnDeleteRow();
                    console.log(oTable);
                    if(oTable){
                        oTable.fnDestroy();
                    }
                    //调用初始化右侧表格的方法
                    Product_ProductList.loadTableData();   
                }
            });
        });
        
        //查询
	    $("#btnSearch").on("click", function(){
	    	oTable.fnDestroy();
	    	loadTableData();
	    	clean();
	    });
        
        //触发相关赋值
        $("#confirmWin2").on("show.bs.modal", function(e){
            var id = $(e.relatedTarget).attr("id");
            $("#id").val('').val(id);
            $.getJSON("getIncomeProductByProductId.html?productId="+id,function(data) {
            	 $("#level1").val('').val(data.data.level1/100);
            	 $("#level2").val('').val(data.data.level2/100);
            	 $("#level3").val('').val(data.data.level3/100);
            	 $("#level4").val('').val(data.data.level4/100);
            });
        });
        //加盟商提成
        $("#saveBut").on("click", function(){
            var param = $("#incomeProductForm").serialize() + "&productid="+$("#id").val();
            $.ajax({
                type: "post",
                data: param,
                url: 'addIncomeProduct.html',
                success: function (data) {
                    if (data.code == 0) {
                    	bootbox.alert("增加或编辑成功!");
                    } else {
                    	bootbox.alert("增加或编辑失败!");
                    }
                    $("#confirmWin2").modal("hide");
                    if(oTable){
                        oTable.fnDestroy();
                    }
                    //调用初始化右侧表格的方法
                    Product_ProductList.loadTableData();   
                }
            });
        });

    };
    
    //产品类型
    var convertProductType = function(productType){
    	var html = '';
    	if(productType == "商品"){
    		html = "<label class='label label-success'>商品</label>";
    	}else if(productType == "赠品"){
    		html = "<label class='label label-primary'>赠品</label>";
    	}else{
    		html = "<label class='label label-info'>无</label>";
    	}
    	return html;
    };

    return {
        init: function () {
            loadTableData();
            initPageEvent();
            initToolBar();
        },
        fnFormatDetails : fnFormatDetails,
        loadTableData:loadTableData
    };
}();

//修改价格
function updatePrice(type,productId,oldprice){
	var price = $("#originalPriceInput"+productId).val();
	//var ex = /^\d+$/;
	//var ex = /^[0-9]+(.[0-9]{1,2})?$/;
	if(type==2){
		price = $("#discountPriceInput"+productId).val();
	}else if(type==3){
		price = $("#microMallPriceInput"+productId).val();
	}
	//if (!ex.test(price)) {
	  if(isNaN(price)){
		   alert("请输入数字");
		   if(type==2){
				price = $("#discountPriceInput"+productId).val(oldprice);
			}else if(type==3){
				price = $("#microMallPriceInput"+productId).val(oldprice);
			}else{
				$("#originalPriceInput"+productId).val(oldprice);
			}
		   return;
	}
	if(price==oldprice){
		return;
	}
	price=Math.round(price*100);
	$.ajax({
		type: "post",
		data: {
			"productId":productId,
			"priceType":type,
			"price":price
		},
		url: 'updatePrice.html',
		success: function (data) {
			if(data.code=="0"){
				alert("修改成功");
			}
		}               
	});
}

//修改价格
function updateSequence(productId,productSequence){
	var productSequence = $("#productSequenceInput"+productId).val();
	var ex = /^\d+$/;
	if (!ex.test(productSequence)) {
		   alert("请输入整数");
		   return
	}
	$.ajax({
		type: "post",
		data: {
			"productId":productId,
			"productSequence":productSequence
		},
		url: 'updateProductSequence.html',
		success: function (data) {
			if(data.code=="0"){
				alert("修改成功");
			}
		}               
	});
}

var checkvalue="";
function getCheck(){
	var str = checkvalue;
	$('#productTable' + ' tbody tr .checkboxes:checked').each(function() {
		if($(this).is(':checked')){
			str += $(this).val() + ",";
		}
	});
	checkvalue=str;
}

function setProduct(e){
	if($(e).is(':checked')){//选中
		var oldvalue=$("#preferredProductId").val();
		$("#preferredProductId").val(oldvalue+$(e).val()+",");
	}else{
		var oldvalue=$("#preferredProductId").val();
		var values = oldvalue.split(",");
		for(var i=0;i<values.length;i++){
			if($(e).val()==values[i]){
				values.splice(i,1);
			}
		}
		$("#preferredProductId").val(values);
	}
}