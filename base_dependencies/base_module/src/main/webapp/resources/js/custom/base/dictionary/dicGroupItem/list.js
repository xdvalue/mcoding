//员工通讯录
var DataTableList = function () {
	var tableId = "dataTable";
	var oTable = null;
	
	//初始化加载表格数据的表头定义
    var initTableHeaderInfo = function(){
        var aoColumns = [
            { "sTitle": "名称", "mData": "name"},
			{ "sTitle": "代码", "mData": "code"},
			{ "sTitle": "描述", "mData": "description"},
			{ "sTitle": "值", "mData": "value"}
        ];
        
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
        		$(nTd).append("<span style='margin: 0 5px 0 5px'><span class='btn btn-xs purple ajaxify' href='dicGroupItem/service/toUpdateViewById?id="+oData.id+"'><i class='fa fa-edit'></i> 更改 </span>");
        		$(nTd).append("<span style='margin: 0 5px 0 5px'><span class='btn default btn-xs black' href='#confirmWin' data-toggle='modal' dicGroupId='"+oData.id+"'><i class='fa  fa-trash-o'></i>删除</span>");
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
        var dicGroupId = $('#divDicGroupId').attr('value');
        var url = "dicGroupItem/service/findByPage?dicGroupId=" + dicGroupId;
        oTable = $('#'+tableId).DataTable({
        	"fnServerParams": function (aoData) {
                //aoData.push({"name": "orgId", "value": currtOrgId});
            },
            "sAjaxSource": url,
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
    
    var initToolBar = function(){
        //触发相关赋值
        
        $("#confirmWin").on("show.bs.modal", function(e){
            var dicGroupId = $(e.relatedTarget).attr("dicGroupId");
            $("#dicGroupItemId").val('').val(dicGroupId);
        });
        
        $("#deleteBut").on("click", function(){
            var url = 'dicGroupItem/service/deleteById';
            
            var dicGroupItemId = $("#dicGroupItemId").val();
            var param = {
            	id : dicGroupItemId
            };
            $.ajax({
            	type : "POST",
                dataType : "json",
                url : url,
                data : param,
                success : function(data) {
                	$("#confirmWin").modal("hide");
                	if(data ==null || data.status !='00'){
                		bootbox.alert("禁用失败");
                		return;
                	}
                	oTable.fnReloadAjax(); 
                }
            });
        });

    };

    return {
        init: function () {
            loadTableData();
            initToolBar();
        }
    };
}();

