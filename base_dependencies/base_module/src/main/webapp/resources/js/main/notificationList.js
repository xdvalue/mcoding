/**
 * 初始化公告列表的数据
 */
var userId = App.getLoginUserId();
var oTable = null;
var notificationList = function () {
	var tableId = "notificationTable";
    
	//初始化加载表格数据的表头定义
    var initTableHeaderInfo = function(receiveUserId){
        var aoColumns = [
            { "sTitle": "编号", "mData": "notificationsId"},
            { "sTitle": "公告标题", "mData": "noticeTitle"},
            {
                "sTitle": "公告状态",
                "mDataProp" : "",
                "sDefaultContent" : "",//此列默认值为""，以防数据中没有此值，DataTables加载数据的时候报错
                "sVisible" : false,
                "fnCreatedCell" : function(nTd, sData, oData, iRow, iCol) {
                    if(oData.notificationsStatus == 0 || oData.notificationsStatus == 2){
                        $(nTd).append("<label class='label label-danger'>未读</label>");
                    }else if(oData.notificationsStatus == 1){
                        $(nTd).append("<label class='label label-success'>已读<label>");
                    }
                }
            },
            { "sTitle": "公告发送人", "mData": "nickName" }
            /*{ "sTitle": "公告发布时间", "mData": "createTime" },*/
        ];
        //渲染特殊的列(操作列)
        var aoColumnDefs =[];
        
            aoColumns.push({ "sTitle": "操作",
                "mDataProp" : "",
                "sDefaultContent" : "",
                "sVisible" : false,
                "fnCreatedCell":function(nTd,sData, oData, iRow, iCol){
                	$(nTd).append("<span class='btn btn-xs purple ajaxify'  href='queryNotificationsType.html?notificationsId="+oData.notificationsId+"' style='margin-left: 10px'><i class='fa fa-tasks'></i>公告详情 </span>");
                }
            });
            aoColumnDefs = [];

        return {
            aoColumns : aoColumns,
            aoColumnDefs : aoColumnDefs
        };
    };

    //加载datatable表格数据
    var loadTableData = function(receiveUserId){
    	/*tasklist.loadTaskCount(projId);  
        var pId = null;
        if(projId==null||projId==""){
        	pId = $("#projId").val();
        }else{   
        	pId = projId;
        }*/
        var headerInfo = initTableHeaderInfo();
        oTable = $('#'+tableId).DataTable({
        	/*"fnServerParams": function (aoData) {
                aoData.push({"name": "projId", "value": pId});                
            },*/
            "sAjaxSource": "queryNotiByReceUserId.html",
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
            "aoColumns": headerInfo.aoColumns,
            "aLengthMenu": [
                [10, 20, 30, 40, -1],
                [10, 20, 30, 40, 50]
            ],
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
    
    return {
        init: function () {
            loadTableData();
        },
        
        loadTableData : loadTableData

    };
}();
