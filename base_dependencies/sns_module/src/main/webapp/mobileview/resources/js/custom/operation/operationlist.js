var System_operation = function () {
    var tableId = "operationTable";
    var oTable = null;
    
    //初始化加载表格数据的表头定义
    var initTableHeaderInfo = function(){
        var aoColumns = [
            { "sTitle": "用户名",  "mData": "operateMan"},
            { "sTitle": "操作时间", "mData": "operateTime" },
            { "sTitle": "操作动作", "mData": "operateName" },
        ];
        //渲染特殊的列(操作列)
        var aoColumnDefs =[];
        if(App.checkUserOperatorColumDisplay("operationList", ["deleteMember"])){
            aoColumns.push({
            	"sTitle": "操作",
            	"mDataProp" : "",
                "bSortable": false,
            	"sDefaultContent" : "",
            	"sVisible" : false, 
            	"fnCreatedCell": function(nTd,sData, oData, iRow, iCol){
            		if(App.checkUserOperRight("operationList", "deleteMember")){
            			$(nTd).append("<span class='btn default btn-xs black' enable='ok' " +
                                "groupId='"+ oData.id +"'>" +
                                "<i class='fa fa-trash-o'></i> 删除 </span>");
            		}
                }
            });
        }

        return {
            aoColumns : aoColumns,
            aoColumnDefs : aoColumnDefs,
        };
    };
   
    //加载表格数据
    var loadTableData = function(){
    	 if(oTable){
             oTable.fnReloadAjax();
             return;
         }
        var headerInfo = initTableHeaderInfo();
    	oTable = $('#'+tableId).DataTable({
        	"sAjaxSource": "operationviwe.html",
            "sAjaxDataProp": "queryResult",
            "bFilter" : true,
            "bInfo": true,
            "bJQueryUI": false,
            "bLengthChange": true,
            "bPaginate": true,
            "bProcessing": true,
            "bSort": true,
            "bSortClasses": true,
            "bStateSave": true,
            "bAutoWidth":true,
            "bSortCellsTop": true,
            "iTabIndex": 1,
            "sServerMethod": "POST",
            "bServerSide": false,
            "fnServerParams": function (aoData,searchType) {
                aoData.push({"name": "Starttime", "value": $("#Starttime").val()});
                aoData.push({"name": "Endtime", "value":$("#Endtime").val()});
            },
            "aoColumns": headerInfo.aoColumns,
           "aoColumnDefs" : headerInfo.aoColumnDefs,
            "iDisplayLength": 10,
            "oLanguage": { //国际化配置
	 			 "sProcessing" : "数据加载中...",  
	             "sLengthMenu" : "显示 _MENU_ 条/页",  
	             "sZeroRecords" : "没有您要搜索的内容",  
	             "sInfo" : "从 _START_ 到  _END_ 条记录 总记录数为 _TOTAL_ 条",  
	             "sInfoEmpty" : "记录数为0",  
	             "sInfoFiltered" : "(全部记录数 _MAX_ 条)",  
	             "sInfoPostFix" : "",  
	             "sSearch" : "搜索：",  
	             "sUrl" : "",  
	             "oPaginate": {  
	             	   "sFirst": "首页",  
	             	    "sPrevious": "上一页",
	             	    "sNext": "下一页",  
	             	    "sLast": "尾页" 
	             }
            }
        });
    };
    
    //开启查询框
    /*$("#hidenAllChilden").hide();
    $("#btnHide").click(function(){
	    $(this).html($("#hidenAllChilden").is(":hidden") ? "<i class='icon-minus green'></i>&nbsp;隐藏筛选条件" : "<i class='icon-plus green'></i>&nbsp;开启筛选条件");
        $("#hidenAllChilden").slideToggle();
  	});*/
    
  //绑定日历
    if (jQuery().datepicker) {
        $('.date-picker').datepicker({
            rtl: App.isRTL(),
            language : "zh-CN",
            autoclose: true
        });
    }
    
   $('#operationSearch').click(function(){
	   System_operation.loadTableData();
    });
   
   $('#btnReset').click(function(){
		document.getElementById("Starttime").value="";	  	
  		document.getElementById("Endtime").value="";
    });
   
   $("#"+tableId).delegate("span[enable='ok']", "click", function(){
       var groupId = $(this).attr("groupId");
       var param = {
    		   groupId : groupId
       };
       $.ajax({
           type : "post",
           dataType : "json",
           url : "deleteoperation.html",
           data : param,
           success : function(data) {
               if(data !=null){
            	   bootbox.alert("删除成功");
            	   oTable.fnReloadAjax();
               }else{
                   bootbox.alert("删除失败");
               }
           }
       });
   });
   
    return {
        init: function () {
        	loadTableData();
        },
        loadTableData: loadTableData
    };
}();
    