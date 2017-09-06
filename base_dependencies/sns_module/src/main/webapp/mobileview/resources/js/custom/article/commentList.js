//评论列表
var commentList = function () {
	var tableId = "commentTableId";
	var oTable = null;
	
	//初始化加载表格数据的表头定义
    var initTableHeaderInfo = function(){
        var aoColumns = [
			{ "sTitle": "id", "mData" : "id"},
            { "sTitle": "文章ID", "mData": "articleId"},
            { "sTitle": "会员ID", "mData": "memberId"},
            { "sTitle": "会员名称", "mData": "memberName"},
//            {
//            	"sTitle": "会员头像",
//            	"mDataProp" : "",
//            	"sDefaultContent" : "",
//            	"sVisible" : false,
//            	"fnCreatedCell" : function(nTd, sData, oData, iRow, iCol) {
//            		$(nTd).append("<img src='" + oData.memberImg + "' width='10%'>");
//            	}
//            },
			{ "sTitle": "评论内容", "mData": "content"},
			{
            	"sTitle": "创建时间",
            	"mDataProp" : "",
            	"sDefaultContent" : "",
            	"sVisible" : false,
            	"fnCreatedCell" : function(nTd, sData, oData, iRow, iCol) {
            		$(nTd).append(changeDateFormat(oData.createTime));
            	}
            },
			{
            	"sTitle": "评论或者回复",
            	"mDataProp" : "",
            	"sDefaultContent" : "",
            	"sVisible" : false,
            	"fnCreatedCell" : function(nTd, sData, oData, iRow, iCol) {
            		if(oData.pid==0){
            			$(nTd).append("评论");
            		}else{
            			$(nTd).append("回复");
            		}
            	}
            },
            {
            	"sTitle": "是否屏蔽",
            	"mDataProp" : "",
            	"sDefaultContent" : "",
            	"sVisible" : false,
            	"fnCreatedCell" : function(nTd, sData, oData, iRow, iCol) {
            		if(oData.isEnable==1){
            			$(nTd).append("<label class='label label-success'>显示</label>");
            		}else{
            			$(nTd).append("<label class='label label-danger'>屏蔽</label>");
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
            	"sVisible" : false, 
            	"fnCreatedCell": function(nTd,sData, oData, iRow, iCol){
            		$(nTd).append("<span style='margin: 0 5px 0 5px'><span class='btn default btn-xs black' data-toggle='modal' href='#disableConfirmWin' id="+oData.id+"><i class='fa fa-trash-o'></i> 屏蔽 </span>");
            		$(nTd).append("<span style='margin: 0 5px 0 5px'><span class='btn btn-xs purple' data-toggle='modal' href='#enableConfirmWin' id="+oData.id+"><i class='fa fa-edit'></i> 恢复 </span>");
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
            "sAjaxSource": "page/queryCommentByPage.html",
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
                "sSearch" : "搜索评论内容：", 
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
    
    var initEnableToolBar = function(){
    	$("#enableConfirmWin").on("show.bs.modal", function(e) {
			var id = $(e.relatedTarget).attr("id");
			$("#id").val('').val(id);
		});
    	
    	//恢复评论绑定
		$("#enableBut").on("click", function(){
            var param = {id : $("#id").val()};
            $.ajax({
                type: "post",
                data: param,
                url: 'page/enabledCommentByCommentId.html',
                success: function (data) {
                    if (data.code == 0) {
                    	oTable.fnReloadAjax();
						bootbox.alert("解除屏蔽成功");
						$("#enableConfirmWin").modal("hide");
                    } else {
                    	bootbox.alert("解除屏蔽失败");
                    }
                }
            });
        });
    }
    
    var initDisableToolBar = function(){
    	// 触发相关赋值
		$("#disableConfirmWin").on("show.bs.modal", function(e) {
			var id = $(e.relatedTarget).attr("id");
			$("#id").val('').val(id);
		});
        //屏蔽触发事件
        $("#deleteBut").on("click", function(){
            var param = {id : $("#id").val()};
            $.ajax({
                type: "post",
                data: param,
                url: 'page/disabledCommentByCommentId.html',
                success: function (data) {
                    if (data.code == 0) {
                    	oTable.fnReloadAjax();
						bootbox.alert("屏蔽成功");
						$("#disableConfirmWin").modal("hide");
                    } else {
                    	bootbox.alert("屏蔽失败");
                    }
                }
            });
        });
    }
    
    return {
        init: function () {
            loadTableData();
            initEnableToolBar();
            initDisableToolBar();
        }
    };
}();
