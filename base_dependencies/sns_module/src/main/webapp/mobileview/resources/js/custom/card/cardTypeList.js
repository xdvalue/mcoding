//产品列表
var tableList = function () {
	var tableId = "tableList";
	var oTable = null;
	
	//初始化加载表格数据的表头定义
    var initTableHeaderInfo = function(){
        var aoColumns = [
            { "sTitle": "ID", "mData": "id"},
            { "sTitle": "名称", "mData": "name"},
            { "sTitle": "关联微信的cardId", "mData": "cardid"},
            { "sTitle": "卡券前缀编号", "mData": "code"},
            {
            	"sTitle": "类型",
            	"mDataProp" : "",
            	"sDefaultContent" : "",
            	"sVisible" : false,
            	"fnCreatedCell" : function(nTd, sData, oData, iRow, iCol) {
            		if(oData.cardtype=="GIFT"){
            			$(nTd).append("<label class='label label-danger'>礼品券</label>");
            		}else if(oData.cardtype=="ACTIVITY"){
            			$(nTd).append("<label class='label label-danger'>活动券</label>");
	            	}else if(oData.cardtype=="CASH"){
            			$(nTd).append("<label class='label label-danger'>现金券</label>");
	            	}else if(oData.cardtype=="GROUPON"){
            			$(nTd).append("<label class='label label-danger'>团购券</label>");
	            	}else if(oData.cardtype=="DISCOUNT"){
            			$(nTd).append("<label class='label label-danger'>折扣券</label>");
	            	}else{
	            		$(nTd).append("<label class='label label-danger'>其他</label>");
	            	}
            		
            	}
            },
            {
            	"sTitle": "是否有效",
            	"mDataProp" : "",
            	"sDefaultContent" : "",
            	"sVisible" : false,
            	"fnCreatedCell" : function(nTd, sData, oData, iRow, iCol) {
            		if(oData.isvalid=="0"){
            			$(nTd).append("<label class='label label-danger'>无效</label>");       	
            		}else{
            			$(nTd).append("<label class='label label-success'>有效</label>");
            		}
            		
            	}
            },
            {
            	"sTitle": "有效期类型",
            	"mDataProp" : "",
            	"sDefaultContent" : "",
            	"sVisible" : false,
            	"fnCreatedCell" : function(nTd, sData, oData, iRow, iCol) {
            		if(oData.timetype=="TIME_RANGE"){
            			$(nTd).append("<label class='label label-danger'>固定时间区间</label>");       	
            		}else if(oData.timetype="FIX_TERM"){
            			$(nTd).append("<label class='label label-success'>固定时长</label>");
            		}else{
            			$(nTd).append("<label class='label label-success'>无</label>");
            		}
            		
            	}
            },
            { "sTitle": "发卡数", "mData": "cardcount"},
            { "sTitle": "剩余卡数", "mData": "cardquantity"},
            {
            	"sTitle": "创建时间",
            	"mDataProp" : "",
            	"sDefaultContent" : "",
            	"sVisible" : false,
            	"fnCreatedCell" : function(nTd,sData, oData, iRow, iCol) {
            		$(nTd).append((oData.createtime==null ? "" :changeDateFormat(oData.createtime)));
            	}
            },
            {
            	"sTitle": "有效开始时间",
            	"mDataProp" : "",
            	"sDefaultContent" : "",
            	"sVisible" : false,
            	"fnCreatedCell" : function(nTd,sData, oData, iRow, iCol) {
            		$(nTd).append((oData.begintime==null ? "" :changeDateFormat(oData.begintime)));
            	}
            },
            {
            	"sTitle": "有效结束时间",
            	"mDataProp" : "",
            	"sDefaultContent" : "",
            	"sVisible" : false,
            	"fnCreatedCell" : function(nTd,sData, oData, iRow, iCol) {
            		$(nTd).append((oData.endtime==null ? "" :changeDateFormat(oData.endtime)));
            	}
            },
            {
            	"sTitle": "有效天数",
            	"mDataProp" : "",
            	"sDefaultContent" : "",
            	"sVisible" : false,
            	"fnCreatedCell" : function(nTd,sData, oData, iRow, iCol) {
            		$(nTd).append((oData.fixedterm==null ? "" :oData.fixedterm+"天"));
            	}
            },
            {
				"sTitle": "领券后开始有效时间",
				"mDataProp" : "",
				"sDefaultContent" : "",
				"sVisible" : false,
				"fnCreatedCell" : function(nTd,sData, oData, iRow, iCol) {
					$(nTd).append((oData.fixedbegintime==null ? "" :changeDateFormat(oData.fixedbegintime)));
				}
			},
			{
				"sTitle": "最低订单金额",
				"mDataProp" : "",
				"sDefaultContent" : "",
				"sVisible" : false,
				"fnCreatedCell" : function(nTd,sData, oData, iRow, iCol) {
					$(nTd).append((oData.leastcost==null ? "" :oData.leastcost/100+"元"));
				}
			},
			{
				"sTitle": "减免金额",
				"mDataProp" : "",
				"sDefaultContent" : "",
				"sVisible" : false,
				"fnCreatedCell" : function(nTd,sData, oData, iRow, iCol) {
					$(nTd).append((oData.reducecost==null ? "" :oData.reducecost/100+"元"));
				}
			},
            { "sTitle": "折扣", "mData": "discount"},
			{ "sTitle": "关联产品id", "mData": "productid"},
			{ "sTitle": "产品名称", "mData": "productname"},
			{ "sTitle": "优惠详情", "mData": "defaultDetail"}
        ];
        //渲染特殊的列(操作列)
        var aoColumnDefs =[];
            aoColumns.push({
            	"sTitle": "操作",
            	"mDataProp" : "",
            	"sDefaultContent" : "",
            	"sVisible" : false, 
            	"fnCreatedCell": function(nTd,sData, oData, iRow, iCol){
            		$(nTd).append("<span style='margin: 0 5px 0 5px'><span class='btn default btn-xs black' data-toggle='modal' href='#confirmWin' id="+oData.id+"><i class='fa fa-trash-o'></i> 禁用 </span>");
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
        var search = $("#SearchForm").serializeArray();
        oTable = $('#'+tableId).DataTable({
        	"fnServerParams": function (aoData) {
        		$.each(search, function(index, value){
        			aoData.push({"name": value.name, "value": value.value})
                });
            },
            "sAjaxSource": "cardType/findByPage.html",
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
    	//保存
	    $("#inputSearch").on("click", function(){
	    	oTable.fnDestroy();
	    	loadTableData();	
	    	clean();
	    });
    	
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
    	income_incomeProductList.loadTableData();  
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
    	income_incomeProductList.loadTableData();  
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
                url: 'deleteIncomeProduct.html',
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
                    income_incomeProductList.loadTableData();   
                }
            });
        });
        

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

