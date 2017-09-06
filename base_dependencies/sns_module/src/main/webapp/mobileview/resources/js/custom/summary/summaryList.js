/**
 任务和项目的  状态、颜色、色值：
 0 提出需求，蓝色， #57b5e3
 1 暫停或延期，红色，#ed4e2a
 2 进行中，绿色，#3cc051
 3 已完成，黄色，#fcb322
 4 已取消，灰色，#999
 5 过期未提交 紫色 #6d1b81
 6 审核中 紫色 #6d1b81

 任务和项目的重要级别：
 红色：重要紧急
 黄色：一般重要
 绿色：一般处理
 蓝色：不重要
 */
var userId = App.getLoginUserId();
var oTable = null;
var Summary_SummaryList = function () {
   
    //初始化加载表格数据的表头定义
    var initTableHeaderInfo = function(){
        var aoColumns = [
			/*{ 
				"sTitle": "",
				"sWidth" : "10px",
				"mDataProp" : "",
				"sDefaultContent" : "",//此列默认值为""，以防数据中没有此值，DataTables加载数据的时候报错
				"sVisible" : false,
				"fnCreatedCell" : function(nTd,sData, oData, iRow, iCol) {
						
							$(nTd).append("<span class='row-details row-details-close'></span>");
							$(nTd).append("<input type='hidden' id='taskId' value="+oData.id+">");
							
						
				}
			}, */            
           /* {
                "sTitle": "项目状态",
                "mDataProp" : "",
                "sDefaultContent" : "",//此列默认值为""，以防数据中没有此值，DataTables加载数据的时候报错
                "sVisible" : false,
                "fnCreatedCell" : function(nTd, sData, oData, iRow, iCol) {
                    if(oData.summaryLevel == "0"){
                        $(nTd).append("<span class='label label-info'>提出需求</label>");
                    }else if(oData.summaryLevel == "1"){
                        $(nTd).append("<label class='label label-danger'>暫停或延期</label>");
                    }else{
                        $(nTd).append("<span class='label label-primary'>审核中</span>");
                    }
                }
            },*/
			{ "sTitle": "姓名", "mData": "nickName" },
            { "sTitle": "时间", "mData": "summaryStart" },
            {
                "sTitle": "类型",
                "mDataProp" : "",
                "sDefaultContent" : "",//此列默认值为""，以防数据中没有此值，DataTables加载数据的时候报错
                "sVisible" : false,
                "fnCreatedCell" : function(nTd, sData, oData, iRow, iCol) {
                    if(oData.summaryLevel == "1"){
                        $(nTd).append("<span class='label label-danger'>日</label>");
                    }else if(oData.summaryLevel == "2"){
                        $(nTd).append("<label class='label label-danger'>周</label>");
                    }else if(oData.summaryLevel == "3"){
                        $(nTd).append("<label class='label label-danger'>月</label>");
                    }else if(oData.summaryLevel == "4"){
                        $(nTd).append("<label class='label label-danger'>季</label>");
                    }else if(oData.summaryLevel == "5"){
                        $(nTd).append("<label class='label label-danger'>年</label>");
                    }else{
                        $(nTd).append("<span class='label label-danger'>日</span>");
                    }
                    if(oData.summaryType == "1"){
                        $(nTd).append("计划");
                    }else if(oData.summaryType == "2"){
                        $(nTd).append("总结");
                    }else if(oData.summaryType == "3"){
                        $(nTd).append("分享");
                    }else{
                        $(nTd).append("总结");
                    }
                    
                }
            },
            { "sTitle": "内容",  "mData": "summaryText" }
        ];

        return {
            aoColumns : aoColumns,
        };
    };

    //加载datatable表格数据
    var loadTableData = function(){
    	 var tableId = "summaryListTab";
        var headerInfo = initTableHeaderInfo();
        if(oTable){
            oTable.fnDestroy();
        }
        oTable = $('#'+tableId).DataTable({
    		"fnServerParams": function (aoData) {
    			 aoData.push({"name": "startDate", "value": $("#startDate").val()});
    			 aoData.push({"name": "endDate", "value": $("#endDate").val()});
    			 aoData.push({"name": "sSearch", "value": $("#sSearch").val()});
    			 aoData.push({"name": "summaryType", "value": $("#summaryType").val()});
    		},    	
            "sAjaxSource": "querysummaryListView.html",
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
            "iDisplayLength": 20,
            "oLanguage": {
            	"sProcessing" : "努力加载中...",
                "sLengthMenu": "显示 _MENU_ 条记录",
                "sInfoEmpty" : "搜索结果为0条记录",
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
    //重新加载
    $('#goSearch').click(function(){
    	Summary_SummaryList.loadTableData();
     });
	   
	   function clean(formname){
		   	 $(':input',formname)  
		   	 .not(':button, :submit, :reset, :hidden')  
		   	 .val('')  
		   	 .removeAttr('checked')  
		   	 .removeAttr('selected'); 
		   };
    
    return {
        init: function () {
            loadTableData();
        },
        loadTableData : loadTableData,
    };
}();

