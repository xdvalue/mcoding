//二维码标签
var qrcode_qrcodeLabel = function () {
	var tableId = "tableId";
	var oTable = null;
	
	//初始化加载表格数据的表头定义
    var initTableHeaderInfo = function(){
        var aoColumns = [
			{ "sTitle": "id", "sWidth" : "1%", "mData": "id"},
			{ "sTitle": "键值","sWidth" : "1%", "mData": "key"},
			{ "sTitle": "名称", "sWidth" : "8%", "mData": "name"},
			/*{ "sTitle": "二维码图片地址","sWidth" : "5%", "mData": "imgurl"}*/
			{ "sTitle": "二维码图片",
				"mDataProp" : "",
				"sDefaultContent" : "",
            	"sWidth" : "5%",
				"sVisible" : false,
				"fnCreatedCell" : function(nTd,sData, oData, iRow, iCol) {
						$(nTd).append("<img src='"+oData.imgurl+"'  style='width:40px;height:40px' onMouseOver='showimg(this)'>");
						
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
            		if(App.checkUserOperRight("qrcodeLabelList", "editQRCodeLabel")){
            			$(nTd).append("<span style='margin: 0 5px 0 5px'><span class='btn btn-xs purple ajaxify'" +
            					" href='addQRCodeLabelView.html?id="+oData.id+"'><i class='fa fa-edit'></i> 编辑 </span>");
            		}
            		/*if(App.checkUserOperRight("qrcodeLabelList", "downloadTmpQrcode")){
            			$(nTd).append("<span style='margin: 0 5px 0 5px'><span class='btn btn-xs purple ajaxify'" +
            					" href='createQRCodeLabelView.html?id="+oData.id+"'><i class='fa fa-edit'></i> 生成临时二维码 </span>");
            		}*/
            		if(App.checkUserOperRight("qrcodeLabelList", "downloadTmpQrcode")){
            			$(nTd).append("<span style='margin: 0 5px 0 5px'><span class='btn btn-xs purple ajaxify'" +
            					" href='WechatAPI/downloadTmpQrcode.html?sceneId="+oData.id+"&&expire_seconds="+oData.ext+"'><i class='fa fa-edit'></i> 生成临时二维码 </span>");
            		}
            		/*if(App.checkUserOperRight("qrcodeLabelList", "downloadPerQrcode")){
            			$(nTd).append("<span style='margin: 0 5px 0 5px'><span class='btn btn-xs purple ajaxify'" +
            					" href='WechatAPI/downloadPerQrcode.html?sceneId="+oData.id+"'></i> 生成永久二维码 </span>");
            		}*/
            		if(App.checkUserOperRight("qrcodeLabelList", "deleteQRCodeLabel")){
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
        oTable = $('#'+tableId).DataTable({
            "sAjaxSource": "queryQRCodeLabelData.html",
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
    	qrcode_qrcodeLabel.loadTableData();
     });
    
    $('#btnReset').click(function(){
		 	
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
                url : "deleteQRCodeLabel.html",
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

/**生成临时二维码*/
function downloadTmpQrcode(){
	$.ajax({
        type: "POST",
        url: "WechatAPI/downloadTmpQrcode.html",
        data: {sceneId:5},
        dataType: "json",
        success: function (data) {
            if(data.code==0){
            	alert("操作成功");
            }
        }
    });
}
/**生成永久二维码*/
function downloadPerQrcode(){
	
}

/**放大缩小图片*/
function showimg(e){
	//$(e).css({"width":"auto","height":"auto"});
	$("#imgdiv").show();
	$("#bigimg").attr("src",$(e).attr("src"));
}
function hideimg(){
	$("#imgdiv").hide();
}