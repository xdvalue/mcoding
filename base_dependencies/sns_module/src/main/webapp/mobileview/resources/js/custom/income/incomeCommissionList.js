//月佣金明细列表
var income_incomecommissionList = function () {
	var tableId = "incomeCommissionTable";
	var oTable = null;
	//初始化加载表格数据的表头定义         
    var initTableHeaderInfo = function(){
    	   var aoColumns = [
    	                    { "sTitle": "ID",    "mData": "id"},
    	        			{ "sTitle": "会员ID", "mData": "memberid"},
    	        			{ "sTitle": "订单号",  "mData": "ext1"},
    	        			{ "sTitle": "会员姓名","mData": "membername"},
    	        			{
    	        				"sTitle": "创建时间",
    	        				"mDataProp" : "",
    	        				"sDefaultContent" : "",
    	        				"sVisible" : false,
    	        				"fnCreatedCell" : function(nTd,sData, oData, iRow, iCol) {
    	        					$(nTd).append(changeDateFormat(oData.createtime));
    	        				}
    	        			},
    	        			{
    	        				"sTitle": "修改时间",
    	        				"mDataProp" : "",
    	        				"sDefaultContent" : "",
    	        				"sVisible" : false,
    	        				"fnCreatedCell" : function(nTd,sData, oData, iRow, iCol) {
    	        					$(nTd).append(changeDateFormat(oData.updatetime));
    	        				}
    	        			},
    	        			{
    	        				"sTitle": "订单时间",
    	        				"mDataProp" : "",
    	        				"sDefaultContent" : "",
    	        				"sVisible" : false,
    	        				"fnCreatedCell" : function(nTd,sData, oData, iRow, iCol) {
    	        					$(nTd).append(changeDateFormat(oData.ordertime));
    	        				}
    	        			},
    	        			{ "sTitle": "OpenId","mData": "openid"},
    	        			{
    	                    	"sTitle": "结算标记",
    	                    	"mDataProp" : "",
    	                    	"sDefaultContent" : "",
    	                    	"sVisible" : false,
    	                    	"fnCreatedCell" : function(nTd, sData, oData, iRow, iCol) {
    	                    		if(oData.ext3==1){
    	                    			$(nTd).append("已结算");
    	                    		}else if(oData.ext3==2){
    	                    			$(nTd).append("未结算");
    	                    		}else{
    	                    			$(nTd).append("暂无");
    	                    		}
    	                    	}
    	                    },
    	                    {
    	                    	"sTitle": "佣金收入",
    	                    	"mDataProp" : "",
    	                    	"sDefaultContent" : "",
    	                    	"sVisible" : false,
    	                    	"fnCreatedCell" : function(nTd, sData, oData, iRow, iCol) {
    	                    			$(nTd).append(oData.commission/100+"元");
    	                    	}
    	                    },
    	                    {
    	                    	"sTitle": "订单金额",
    	                    	"mDataProp" : "",
    	                    	"sDefaultContent" : "",
    	                    	"sVisible" : false,
    	                    	"fnCreatedCell" : function(nTd, sData, oData, iRow, iCol) {
    	                    			$(nTd).append(oData.orderfee/100+"元");
    	                    			
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
            		$(nTd).append("<span style='margin: 0 5px 0 5px'><span class='btn btn-xs purple ajaxify'href='orderIdManager.html?orderid="+oData.orderid+"'><i class='fa fa-edit'></i> 订单明细 </span>");
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
            "sAjaxSource": "queryIncomeOrdercommissionData.html",    //刷新页面
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
                "sSearch" : "按会员姓名搜索：", 
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
    
   
 
    
    /**/
    var initToolBar = function(){
        //触发相关赋值
        $("#confirmWin").on("show.bs.modal", function(e){
            var id = $(e.relatedTarget).attr("id");
            $("#id").val('').val(id);
        });
        $("#updateBut").on("click", function(){
            var param = {id : $("#id").val()};
            $.ajax({
                type: "post",
                data: param,
                url: '',  					//月总佣金的URL
                success: function (data) {
                    if (data.code == 0) {
                    } else {
                    }
                    $("#confirmWin").modal("hide");
                    console.log(oTable);
                    if(oTable){
                        oTable.fnDestroy();
                    }
                    //调用初始化右侧表格的方法
                    income_incomecommissionList.loadTableData();   
                }
            });
        });
        
     
        /**
         * 导出佣金
         */
        $("#exportExcel").on("click", function(){
	    	if(!confirm("佣金导出时间有点久，确定要执行此操作？")){
	    		return;
	    	}

	    	var url = 'income/exportCommissionExcel.html';
	    	window.open(url);
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

