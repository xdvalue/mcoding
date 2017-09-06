//员工通讯录
var Banner_BannerList = function () {
	var tableId = "bannerTable";
	var oTable = null;
	
	//初始化加载表格数据的表头定义
    var initTableHeaderInfo = function(){
        var aoColumns = [
            { "sTitle": "标题", "mData": "title"},
			{ "sTitle": "顺序", "mData": "orderno"},
			{ "sTitle": "URL链接地址", "mData": "link"},
			{ "sTitle": "品牌", "sDefaultContent" : "", "fnCreatedCell": function(nTd, sData, oData, iRow, iCol){
			    $(nTd).append(convertOrderBrand(oData.brandcode));
    	    }
			},
			{ "sTitle": "是否启用", "sDefaultContent" : "", "fnCreatedCell": function(nTd, sData, oData, iRow, iCol){
			    switch(oData.isvalid){
    		    case 1 :
    			    $(nTd).append("启用");
    			    break;
    		    case 0 :
    			    $(nTd).append("禁用");
    			    break;
    		    }
    	    }
			},
			{ "sTitle": "商城类型", "sDefaultContent" : "", "fnCreatedCell": function(nTd, sData, oData, iRow, iCol){
				$(nTd).append(convertMalltype(oData.malltype));
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
            		$(nTd).append("<span style='margin: 0 5px 0 5px'><span class='btn btn-xs purple ajaxify' href='banner/addBannerView.html?bannerId="+oData.id+"'><i class='fa fa-edit'></i> 编辑 </span>");
            		$(nTd).append("<span style='margin: 0 5px 0 5px'><span class='btn default btn-xs black' data-toggle='modal' href='#confirmWin' id="+oData.id+"><i class='fa fa-trash-o'></i> 删除 </span>");
            	}
            });

        return {
            aoColumns : aoColumns,
            aoColumnDefs : aoColumnDefs
        };
    };
    
    
    //加载datatable表格数据
    var loadTableData = function(){
//    	console.log(oTable);
        var headerInfo = initTableHeaderInfo();
        oTable = $('#'+tableId).DataTable({
        	"fnServerParams": function (aoData) {
                //aoData.push({"name": "orgId", "value": currtOrgId});
            },
            "sAjaxSource": "banner/getBannerList.html",
            "sAjaxDataProp": "queryResult",
            "bFilter" : false,
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
            var id = $(e.relatedTarget).attr("id");
            $("#id").val('').val(id);
        });
        //删除
        $("#deleteBut").on("click", function(){
            var param = {bannerId : $("#id").val()};
            $.ajax({
                type: "post",
                data: param,
                url: 'banner/deleteBanner.html',
                success: function (data) {
                    if (data.code == 0) {
                    } else {

                    }
                    $("#confirmWin").modal("hide");
                    //oTable.fnDeleteRow();
                    console.log(oTable);
                    if(oTable){
                        oTable.fnDestroy();
                    }
                    //调用初始化右侧表格的方法
                    Banner_BannerList.loadTableData();   
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

