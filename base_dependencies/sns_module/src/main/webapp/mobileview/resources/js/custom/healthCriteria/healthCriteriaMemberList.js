//发样检测结果
var health_healthCriteriaMemberList = function () {
	var tableId = "tableId";
	var oTable = null;
	
	//初始化加载表格数据的表头定义
    var initTableHeaderInfo = function(){
        var aoColumns = [
			{ "sTitle": "id", "sWidth" : "1%", "mData": "id"},
			{ "sTitle": "姓名","sWidth" : "1%", "mData": "membername"},
			{ "sTitle": "手机号码", "sWidth" : "8%", "mData": "mobilephone"},
			{
				"sTitle": "创建时间",
				"mDataProp" : "",
				"sDefaultContent" : "",
				"sVisible" : false,
				"fnCreatedCell" : function(nTd,sData, oData, iRow, iCol) {
					$(nTd).append(changeDateFormat(oData.createdate));
				}
			},
			{
				"sTitle": "更新时间",
				"mDataProp" : "",
				"sDefaultContent" : "",
				"sVisible" : false,
				"fnCreatedCell" : function(nTd,sData, oData, iRow, iCol) {
					$(nTd).append(changeDateFormat(oData.updatedate));
				}
			},
			{ "sTitle": "健康判定","sWidth" : "5%", "mData": "determine"},
			{
				"sTitle": "结果状态",
				"mDataProp" : "",
				"sDefaultContent" : "",
				"sWidth" : "1%",
				"sVisible" : false,
				"fnCreatedCell" : function(nTd,sData, oData, iRow, iCol) {
					if(oData.resultStatus==0){
						$(nTd).append("<label class='label label-success'>未完成</label>");						
					}else{
						$(nTd).append("<label class='label label-warning'>已完成</label>");
					}
					
				}
			}
        ];
        //渲染特殊的列(操作列)
        var aoColumnDefs =[];
            aoColumns.push({
            	"sTitle": "操作",
            	"mDataProp" : "",
            	"sDefaultContent" : "",
            	"sWidth" : "5%",
            	"sVisible" : false, 
            	"fnCreatedCell": function(nTd,sData, oData, iRow, iCol){
            		if(App.checkUserOperRight("healthCriteriaMemberList", "editHealthCriteriaMember")){
            			$(nTd).append("<span style='margin: 0 5px 0 5px'><span class='btn btn-xs purple ajaxify'" +
            					" href='addHealthCriteriaMemberView.html?id="+oData.id+"'><i class='fa fa-edit'></i> 编辑 </span>");
            		}
            		if(App.checkUserOperRight("healthCriteriaMemberList", "deleteHealthCriteriaMember")){
            			$(nTd).append("<span class='btn default btn-xs black' data-toggle='modal' " +
            					"href='#confirmWin' enable='ok' " + "id='"+ oData.id +"'>" +
                        "<i class='fa fa-trash-o'></i> 删除 </span>");
            		}
            		 
                }
            });

        return {
            aoColumns : aoColumns,
            aoColumnDefs : aoColumnDefs
        };
    };
    
    
    //加载datatable表格数据
    var loadTableData = function(){
        var headerInfo = initTableHeaderInfo();
        var search = $("#healthSearchForm").serializeArray();
        oTable = $('#'+tableId).DataTable({
        	"fnServerParams": function (aoData) {
        		$.each(search, function(index, value) {
					aoData.push({
						"name" : value.name,
						"value" : value.value
					})
				});
            },
            "sAjaxSource": "queryHealthCriteriaMemberData.html",
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
                "sSearch" : "搜索", 
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
    
    $('#btnSearch').click(function(){
    	oTable.fnDestroy();
    	health_healthCriteriaMemberList.loadTableData();
     });
    
    $('#btnReset').click(function(){
		document.getElementById("mobilePhone").value="";	  	
    });


    var initToolBar = function(){
    	//触发相关赋值
        $("#confirmWin").on("show.bs.modal", function(e){
            var id = $(e.relatedTarget).attr("id");
            $("#id").val('').val(id);
        });
    	//删除事件
        $("#deleteBut").on("click", function(){
        	var param = {teplId : $("#id").val()};
            $.ajax({
                type : "post",
                dataType : "json",
                url : "deleteHealthCriteriaMember.html",
                data : param,
                success : function(data) {
                    if(data.code==0){
                        oTable.fnReloadAjax();
                        bootbox.alert("删除成功");
                        $("#confirmWin").modal("hide");
                    }else{
                        bootbox.alert("删除失败");
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
        loadTableData: loadTableData
    };
}();

