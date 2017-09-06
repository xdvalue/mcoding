//员工通讯录
var member_memberJoinRecordList = function () {
	var tableId = "memberTable";
	var oTable = null;
	
	//初始化加载表格数据的表头定义
    var initTableHeaderInfo = function(){
        var aoColumns = [
			{ "sTitle": "申请人", "sWidth" : "5%", "mData": "membername"},
			{ "sTitle": "申请级别", "sWidth" : "5%", "mData": "levelname"},
			{ "sTitle": "推荐人", "sWidth" : "5%", "mData": "parentname"},
            { "sTitle": "推荐人级别","sWidth" : "5%", "mData": "parentlevelname"},
            { "sTitle": "申请进度","sWidth" : "5%", "sDefaultContent" : "",
            	"fnCreatedCell": function(nTd, sData, oData, iRow, iCol){
            		switch(oData.status){
            		case "100" :
            			$(nTd).append("申请中");
            			break;
            		case "101" :
            			$(nTd).append("已通过");
            			break;
            		default :
            			$(nTd).append("未知");
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
            		if(oData.status == '101'){
            			return;
            		}
            		
            		if(App.checkUserOperRight("memberJoinRecordList", "confirmMemberJoin")){
            			$(nTd).append("<span class='btn default btn-xs black' data-toggle='modal' " +
            					"href='#confirmWin' enable='ok' " + "id='"+ oData.id+"' >" +
                        "<i class='fa fa-check'></i> 通过 </span>");
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
        oTable = $('#'+tableId).DataTable({
            "sAjaxSource": "memberJoinRecord/queryMemberJoinRecordData.html",
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
                "sSearch" : "搜索会员名称", 
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
    	member_memberList.loadTableData();
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
        	var param = {recordId : $("#id").val()};
            $.ajax({
                type : "post",
                dataType : "json",
                url : "memberJoinRecord/confirmMemeberJoin.html",
                data : param,
                success : function(data) {
                    if(data.status=='00'){
                        oTable.fnReloadAjax();
                        bootbox.alert("通过成功");
                        $("#confirmWin").modal("hide");
                    }else{
                        bootbox.alert("通过失败");
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

