/**
 * 
 */
var RelyNewsManager = (function(){
	
	var newsList = [];
	var replyNewsInputVal = $("#replyNewsInput").val();
	if(replyNewsInputVal){
		newsList = replyNewsInputVal.split(/\s*,\s*/);
	}
	
	var reload = function(){
		$('#replyNewsInput').val(newsList.join());
	}
	
	var indexOf = function(newsId){
		var index = -1;
		$.each(newsList, function(i, n){
			if(n == newsId){
				index = i;
				return false;
			}
		});
		return index;
	};
	
	var contain = function(newsId){
		return indexOf(newsId) >= 0;
	};
	
	var add = function(newsId){
		if(!contain(newsId)) newsList.push(newsId);
		reload();
	};
	
	var remove = function(newsId){
		var index = indexOf(newsId);
		if(contain(newsId)) newsList.splice(index, 1);
		reload();
	}
	
	return {
		addNews : add,
		removeNews: remove,
		contain: contain,
		reload:reload
	}
	
})();

var WxReplyNewsAddViewManager = (function(){
	
	var data = {
		accountId : $('#accountIdInput').val(),
		accountOriginId: $('#accountOriginIdInput').val()
	};
	
	var load = function(replyNewsId){
		debugger;
		var url = 'wxMsgReplyNews/service/toAddView';
		var params = {originId: data.accountOriginId};
		if(replyNewsId){
			url = 'wxMsgReplyNews/service/toUpdateViewById';
			params.id = replyNewsId;
		}
		
		$.get(url, params, function(html){
	          $('#wxMsgReplyNewsAddViewModalBody').empty();
	          $('#wxMsgReplyNewsAddViewModalBody').append(html);
	          
	          imageUpload({
	  			  imageUploadBtn :"imgUrlButton",
			      showUrlId :  "imgUrlInput"
			  });
	          
	          $('#wxMsgReplyNewsAddViewModal').modal('show');
	    }, 'html');
	};
	
    var createOrEdit = function() {
		
		var form = $("#wxMsgReplyNewsForm");
		if (!form.valid()) {
			return;
		}

		var tips = "增加失败!";
		var param = form.serializeArray();
		param = serializeObject(param);

		var requestURL = "wxMsgReplyNews/service/create";
		if(param.id){
			requestURL = "wxMsgReplyNews/service/edit";
		}
		

		$.ajax({
			type : "POST",
			url : requestURL,
			data : JSON.stringify(param),
			dataType : "json",
			contentType : "application/json",
			success : function(data) {
				if (!data || data.status != '00') {
					bootbox.alert(tips + '原因是' + data.msg);
					return;
				}
				debugger;
				WxReplyNewsDataTableList.reload();
		        $('#wxMsgReplyNewsAddViewModal').modal('hide');
				return;
			},
			error : function() {
				bootbox.alert(tips);
				return;
			}
		});
	};
	
	return {
		load: load,
		createOrEdit: createOrEdit
	}
})();


var WxReplyNewsDataTableList = function () {
	var tableId = "dataTable";
	var oTable = null;
	
	//初始化加载表格数据的表头定义
    var initTableHeaderInfo = function(){
        var aoColumns = [
        	{ "sTitle": "选择", 
				"mDataProp" : "",
				"sDefaultContent" : "",
				"sVisible" : false, 
				"fnCreatedCell":function(nTd,sData, oData, iRow, iCol){
					
					var isSelected = RelyNewsManager.contain(oData.id);
					var checkboxObj = $('<input>', {
						type: "checkbox",
						checked: isSelected,
						change : function(){
							
							var isChecked = $(this).attr('checked');
							if(isChecked && isChecked == 'checked'){
								RelyNewsManager.addNews(oData.id);
							}else{
								RelyNewsManager.removeNews(oData.id);
							}
						}
	        		});
					$(nTd).append(checkboxObj);
				}
            },
			{ "sTitle": "id", "mData": "id"},
			{ "sTitle": "标题", "mData": "title"},
			{ "sTitle": "摘要", "mData": "summary"},
			{ "sTitle": "封面图片", 
				"mDataProp" : "",
				"sDefaultContent" : "",
				"sVisible" : false, 
				"fnCreatedCell":function(nTd,sData, oData, iRow, iCol){
					$(nTd).append('<img src="'+oData.coverImg +'" style="width:78px;"  />');
				}
            },
			{ "sTitle": "连接地址", "mData": "url"},
			
        ];
        
        aoColumns.push({
        	"sTitle": "操作",
        	"mDataProp" : "",
        	"sDefaultContent" : "",
        	"sVisible" : false, 
        	"fnCreatedCell": function(nTd,sData, oData, iRow, iCol){
        		var btnUpdateByIdHtml = "<span class='btn btn-xs purple' href='wxMsgReplyNews/service/toUpdateViewById?id="+oData.id+"'><i class='fa fa-edit'></i> 更改 ";
        		var btnUpdateByIdObj = $('<span>', {
        			style : "margin: 0 5px 0 5px",
        			click: function(){ WxReplyNewsAddViewManager.load(oData.id); }
        		});
        		btnUpdateByIdObj.append(btnUpdateByIdHtml);
        		btnUpdateByIdObj.appendTo($(nTd));
        		
        		var btnDelteByIdHtml = "<span class='btn default btn-xs black' ><i class='fa fa-trash-o'></i>删除";
        		var btnDelteByIdObj = $('<span>', {
        			style : "margin: 0 5px 0 5px",
        			click: function(){ deleteHandler(oData.id); }
        		});
        		btnDelteByIdObj.append(btnDelteByIdHtml);
        		btnDelteByIdObj.appendTo($(nTd));
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
        	"fnServerParams": function (aoData) {
                //aoData.push({"name": "orgId", "value": currtOrgId});
            },
            "sAjaxSource": "wxMsgReplyNews/service/findByPage",
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
    
    var deleteHandler = function(id){
    	bootbox.confirm("确认删除吗?", function(result) {
			if (!result) {
				return;
			}
			var url = 'wxMsgReplyNews/service/deleteById?id=' +id;
			
			$.getJSON(url, function(json){
				if(json && json.status == '00'){
					bootbox.alert("操作成功");
					oTable.fnReloadAjax();
					return;
				}else{
					return bootbox.alert("操作失败,请刷新后重试");
				}
			});
		});
    };
 
    return {
        init: function () {
            loadTableData();
        },
        reload: function(){
        	oTable.fnReloadAjax();
        }
    };
}();

