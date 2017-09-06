//员工通讯录
var DataTableList = function () {
	var tableId = "templateTable";
	var oTable = null;
	
	//初始化加载表格数据的表头定义
    var initTableHeaderInfo = function(){
        var aoColumns = [
            { "sTitle": "模板名称", "mData": "templateName"},
            {
				"sTitle": "类型",
				"sDefaultContent" : "",
				"fnCreatedCell" : function(nTd, sData, oData, iRow, iCol) {
					var statusStr = '';
					if(oData.status == 0){
						statusStr = '有效';
					}else if(oData.status == 1){
						statusStr = '无效';
					}
					$(nTd).append(statusStr);
				}
			},
			{ "sTitle": "所属公司", "mData": "storeId"},
			{ "sTitle": "模板地址", "mData": "templateUrl"},
			{
				"sTitle": "创建时间",
				"sDefaultContent" : "",
				"fnCreatedCell" : function(nTd, sData, oData, iRow, iCol) {
					$(nTd).append(getDateTimeStr(oData.createTime));
				}
			},{
				"sTitle": "更新时间",
				"sDefaultContent" : "",
				"fnCreatedCell" : function(nTd, sData, oData, iRow, iCol) {
					$(nTd).append(getDateTimeStr(oData.updateTime));
				}
			}
        ];
        
        aoColumns.push({
        	"sTitle": "操作",
        	"mDataProp" : "",
        	"sDefaultContent" : "",
        	"sVisible" : false, 
        	"fnCreatedCell": function(nTd,sData, oData, iRow, iCol){
        		$(nTd).append("<span style='margin: 0 5px 0 5px'><span class='btn default btn-xs black ajaxify' href='template/service/action?templateId="+oData.id+"&actionType=D'><i class='fa fa-search'></i>详情</span>");
				$(nTd).append("<span style='margin: 0 5px 0 5px'><span class='btn btn-xs purple ajaxify' href='template/service/action?templateId="+oData.id+"&actionType=U'><i class='fa fa-edit'></i> 更改 </span>");
				$(nTd).append("<span style='margin: 0 5px 0 5px'></span><span class='btn btn-xs red' href='#confirmWinTemplate' data-toggle='modal' templateId='"+oData.id + "'><i class='fa fa-lock'></i>删除</span>");
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
        	"fnServerParams": function (sSource,aoData, fnCallback) {
        	//aoData.push({"name": "orgId", "value": currtOrgId});
            },
            "sAjaxSource": "template/service/findByPage",
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
    
    function getDateTimeStr(oDate){
		if(!oDate){
			return;
		}
		var time = new Date(oDate);
		return time.format('yyyy-MM-dd hh:mm');
	}

    var initToolBar = function(){
        //触发相关赋值
        
        $("#confirmWinTemplate").on("show.bs.modal", function(e){
        	debugger;
            var templateId = $(e.relatedTarget).attr("templateId");
            $("#templateId").val('').val(templateId);
        });
        
        $("#deleteTemplate").on("click", function() {
			var param = {
				templateId : $("#templateId").val()
			};
			$.ajax({
				type : "POST",
				dataType : "json",
				url : "template/service/delete",
				data : param,
				success : function(data) {
					$("#confirmWinTemplate").modal("hide");
					if (data.status == '00') {
						bootbox.alert("删除成功！");
						oTable.fnReloadAjax(); // 刷新datatable列表
					} else {
						bootbox.alert("删除失败！");
					}
				}
			});
		});

    };
    

    return {
        init: function () {
            loadTableData();
            initToolBar();
        },
        loadTableData:loadTableData
    };
}();

