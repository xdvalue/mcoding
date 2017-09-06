var DataTableList = function () {
	var tableId = "dataTable";
	var oTable = null;
	
	//初始化加载表格数据的表头定义
    var initTableHeaderInfo = function(){
        var aoColumns = [
            { "sTitle": "名称", "mData": "title"},
			{ "sTitle": "顺序", "mData": "sort"},
			{ "sTitle": "图片地址", "mData": "imgUrl"},
			{ "sTitle": "外链地址", "mData": "aUrl"},
			{
				"sTitle": "状态", 
				"mDataProp" : "",
				"sDefaultContent" : "",
				"sVisible" : false, 
				"fnCreatedCell":function(nTd,sData, oData, iRow, iCol){
					if(oData.isEnable == 1){
						$(nTd).append('已审核');
					}else{
						$(nTd).append('未通过');
					}
				}
			},
        ];
        
        aoColumns.push({
        	"sTitle": "操作",
        	"mDataProp" : "",
        	"sDefaultContent" : "",
        	"sVisible" : false, 
        	"fnCreatedCell": function(nTd,sData, oData, iRow, iCol){
        		var snsBannerId = 0;
        		var isEnableValue = '未知';
        		var setIsEnableValue = '-1';
        		
        		if(oData.id){
        			snsBannerId = oData.id;
        		}
        		
        		if(oData.isEnable == 1){
        			isEnableValue = '禁用';
        			setIsEnableValue = 0;
        		}else{
        			isEnableValue = '启用';
        			setIsEnableValue = 1;
        		}
        		
        		var btnSetIsEnableHtml = "<span class='btn btn-xs purple'><i class='fa fa-edit'></i> "+isEnableValue;
        		var btnSetIsEnableObj = $('<span>', {
        			style : "margin: 0 5px 0 5px",
        			click : function(){disableHandler(snsBannerId, setIsEnableValue); }
        		});
        		btnSetIsEnableObj.append(btnSetIsEnableHtml);
        		btnSetIsEnableObj.appendTo($(nTd));
        		
        		var btnUpdateByIdHtml = "<span style='margin: 0 5px 0 5px'><span class='btn btn-xs purple ajaxify' href='snsBanner/service/toUpdateViewById?id="+oData.id+"'><i class='fa fa-edit'></i> 更改 </span>";
        		var btnUpdateByIdObj = $(btnUpdateByIdHtml).appendTo($(nTd));
        		
        		var btnDelteByIdHtml = "<span class='btn default btn-xs black' ><i class='fa fa-trash-o'></i>删除";
        		var btnDelteByIdObj = $('<span>', {
        			style : "margin: 0 5px 0 5px",
        			click: function(){ deleteHandler(snsBannerId); }
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
            "sAjaxSource": "snsBanner/service/findByPage",
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
    
    var disableHandler = function(snsBanner, isEnable){
    	var msg = isEnable == 1 ?  "确认启用吗?" : "确认禁用吗?";
    	
    	bootbox.confirm(msg, function(result) {
    		if (!result) {
				return;
			}
			var url = 'snsBanner/service/setIsEnableById?id=' +snsBanner+'&isEnable=' + isEnable;
			
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
    
   var deleteHandler = function(snsBannerId){
    	bootbox.confirm("确认删除吗?", function(result) {
			if (!result) {
				return;
			}
			var url = 'snsBanner/service/deleteById?id=' +snsBannerId;
			
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
    
    
//    var initToolBar = function(){
//        //触发相关赋值
//        
//        $("#confirmWin").on("show.bs.modal", function(e){
//            var snsBannerId = $(e.relatedTarget).attr("snsBannerId");
//            $("#snsBannerId").val('').val(snsBannerId);
//        });
//        
//        $("#deleteBut").on("click", function(){
//            var url = 'snsBanner/service/deleteById';
//            
//            var snsBannerId = $("#snsBannerId").val();
//            var param = {
//            	id : snsBannerId
//            };
//            $.ajax({
//            	type : "POST",
//                dataType : "json",
//                url : url,
//                data : param,
//                success : function(data) {
//                	$("#confirmWin").modal("hide");
//                	if(data ==null || data.status !='00'){
//                		bootbox.alert("禁用失败");
//                		return;
//                	}
//                	oTable.fnReloadAjax(); 
//                }
//            });
//        });
//
//    };

    return {
        init: function () {
            loadTableData();
//            initToolBar();
        }
    };
}();

