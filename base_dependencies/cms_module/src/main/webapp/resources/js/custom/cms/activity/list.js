//员工通讯录
var DataTableList = function () {
	var tableId = "dataTable";
	var oTable = null;
	
	//初始化加载表格数据的表头定义
    var initTableHeaderInfo = function(){
        var aoColumns = [
            { "sTitle": "活动名称", "mData": "name"},
            { "sTitle": "活动状态", "sDefaultContent" : "", 
            	"fnCreatedCell" : function(nTd, sData, oData, iRow, iCol) {
					var statusStr = '';
					if(oData.status == 1) { statusStr = '筹备'; }
					else if(oData.status == 2) { statusStr = '公布'; }
					else if(oData.status == 3) { statusStr = '结束'; }
					$(nTd).append(statusStr); } },
			{ "sTitle": "活动地区", "mData": "name"},
			{ "sTitle": "报名上限", "mData": "memberLimit"},
			{ "sTitle": "截止时间", "sDefaultContent" : "", 
				"fnCreatedCell" : function(nTd, sData, oData, iRow, iCol) { 
					$(nTd).append(getDateTimeStr(oData.registrationDeadline)); } },
			{ "sTitle": "开展时间", "sDefaultContent" : "",
				"fnCreatedCell" : function(nTd, sData, oData, iRow, iCol) {
					$(nTd).append(getDateTimeStr(oData.executeTime)); } }, 
			{ "sTitle": "公布时间",
				"sDefaultContent" : "",
				"fnCreatedCell" : function(nTd, sData, oData, iRow, iCol) {
					$(nTd).append(getDateTimeStr(oData.publicTime)); } }, 
			{ "sTitle": "创建时间", "sDefaultContent" : "",
				"fnCreatedCell" : function(nTd, sData, oData, iRow, iCol) {
					$(nTd).append(getDateTimeStr(oData.createTime)); } } ];
        
        aoColumns.push({
        	"sTitle": "操作",
        	"mDataProp" : "",
        	"sDefaultContent" : "",
        	"sVisible" : false, 
        	"fnCreatedCell": function(nTd,sData, oData, iRow, iCol){
        		var articleId = 0;
        		if(oData.articleId){
        			articleId = oData.articleId;
        		}
        		$(nTd).append("<span style='margin: 0 5px 0 5px'><span class='btn btn-xs purple ajaxify' href='activity/service/toActivityById?activityId="+oData.id+"'><i class='fa fa-edit'></i> 更改 </span>");
        		$(nTd).append("<span style='margin: 0 5px 0 5px'><span class='btn default btn-xs black ajaxify' href='activity/service/detailInfoView.html?activityId="+oData.id+"&articleId="+articleId+"'><i class='fa fa-search'></i>详情</span>");
        		
        		var statusStr = '';
				if(oData.status == 1){
					$(nTd).append("<span style='margin: 0 5px 0 5px'></span><span class='btn btn-xs green' onclick = DataTableList.changeStatus("+oData.id+",'release',"+articleId+")><i class='fa fa-key'></i> 发布 </span>");
				}else if(oData.status == 2){
					$(nTd).append("<span style='margin: 0 5px 0 5px'></span><span class='btn btn-xs green' onclick = DataTableList.changeStatus("+oData.id+",'end',"+articleId+")><i class='fa fa-key'></i> 结束 </span>");
				}else if(oData.status == 3){
					$(nTd).append("<span style='margin: 0 5px 0 5px'></span><span class='btn btn-xs green' onclick = DataTableList.changeStatus("+oData.id+",'republish',"+articleId+")><i class='fa fa-key'></i> 重新发布 </span>");
				}
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
            "sAjaxSource": "activity/service/findByPage",
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
            "aoColumnDefs" : [ {
        		sDefaultContent : '',
        		aTargets : [ '_all' ]
        	} ],
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
    
    function getDateTimeStr(oDate){
		if(!oDate){
			return;
		}
		var time = new Date(oDate);
		return time.format('yyyy-MM-dd hh:mm');
	}
    
    //触发相关赋值
    var initToolBar = function(){

    };

    return {
        init: function () {
            loadTableData();
            initToolBar();
        },
        changeStatus: function (activityId,opType,articleId){
	    	var param = {
	    			activityId:activityId,
	    			opType:opType,
	    			articleId:articleId
	            };
	            $.ajax({
	            	type : "POST",
	                dataType : "json",
	                url : "activity/service/changeStatus",
	                data : param,
	                success : function(data) {
	                	if (data.status == '00') {
	                		if(opType == "release" || opType == "republish"){
	                			bootbox.alert("操作成功！</br> http://www.coding.mobi/cms_module/mobileview/activity/activity_detail.html?activityId="+activityId);
	                		}else{
	                			bootbox.alert("操作成功！");
	                		}
							oTable.fnReloadAjax(); // 刷新datatable列表
						} else {
							bootbox.alert("操作失败！");
						}
	                }
	            });
        },
        loadTableData:loadTableData
    };
}();

