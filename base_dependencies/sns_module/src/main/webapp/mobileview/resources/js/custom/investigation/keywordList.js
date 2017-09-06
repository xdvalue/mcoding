//产品列表
var DTExpertList = function () {
	var tableId = "articleDefTable";
	var oTable = null;
	
	//初始化加载表格数据的表头定义
    var initTableHeaderInfo = function(){
        var aoColumns = [
			{ "sTitle": "id", "mData" : "keyword.id"},
            { "sTitle": "关键词", "mData": "keyword.keyword"},
            //{ "sTitle": "权重", "mData": "keyword.weight"},
            { "sTitle": "分值", "mData": "keyword.score"},
            { "sTitle": "饮食建议文字", "mData": "keyword.dietaryAdv"},
            {
				"sTitle" : "饮食建议图标",
				"mDataProp" : "",
				"sDefaultContent" : "",
				"sWidth" : "10%",
				"sVisible" : false,
				"fnCreatedCell" : function(nTd, sData, oData, iRow, iCol) {
					$(nTd).append("<img src='" + oData.keyword.dietaryImgUrl+ "' width='70%'>");
				}
			},
			{ "sTitle": "运动建议文字", "mData": "keyword.exercisesAdv"},
			{
				"sTitle" : "运动建议图标",
				"mDataProp" : "",
				"sDefaultContent" : "",
				"sWidth" : "10%",
				"sVisible" : false,
				"fnCreatedCell" : function(nTd, sData, oData, iRow, iCol) {
					$(nTd).append("<img src='" + oData.keyword.exercisesImgUrl+ "' width='70%'>");
				}
			},
			{ "sTitle": "综合建议文字", "mData": "keyword.healthAdv"},
			{
				"sTitle" : "综合建议图标",
				"mDataProp" : "",
				"sDefaultContent" : "",
				"sWidth" : "10%",
				"sVisible" : false,
				"fnCreatedCell" : function(nTd, sData, oData, iRow, iCol) {
					$(nTd).append("<img src='" + oData.keyword.healthImgUrl+ "' width='70%'>");
				}
			},
			{
				"sTitle" : "推荐产品",
				"mDataProp" : "",
				"sDefaultContent" : "",
				"sWidth" : "45%",
				"sVisible" : false,
				"fnCreatedCell" : function(nTd, sData, oData, iRow, iCol) {
					$.each(oData.recommends, function(n, value) {
						//"  <span class='label label-primary'>营养素</span> :" + value.recommendNutrient+"<br><br>"
						$(nTd).append(value.productName+"<br><br>");
					});
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
            		$(nTd).append("<span style='margin: 0 5px 0 5px'><span class='btn btn-xs purple ajaxify' href='page/updateKeywordPage.html?id="+oData.keyword.id+"'><i class='fa fa-edit'></i> 编辑 </span>");
            		$(nTd).append("<span style='margin: 0 5px 0 5px'><span class='btn default btn-xs black' data-toggle='modal' href='#confirmWin' id="+oData.keyword.id+"><i class='fa fa-trash-o'></i> 删除 </span>");
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
//        var search = $("#articleDefSearchForm").serializeArray();
//        console.log(search);
        oTable = $('#'+tableId).DataTable({
//        	"fnServerParams": function (aoData) {
//        		$.each(search, function(index, value) {
//					aoData.push({
//						"name" : value.name,
//						"value" : value.value
//					})
//				});
//            },
            "sAjaxSource": "manager/queryKeywordByPage.html",
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
            "bAutoWidth":false,
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
    

    //初始化界面相关事件
    var initPageEvent = function(){
        //注册点击查看详情事件
        $('#'+tableId).on('click', ' tbody td .row-details', function () {
            var nTr = $(this).parents('tr')[0];
            //获取隐藏域的userId值
            var userId = $(this).parents('tr').find("input:hidden").val();
            if (oTable.fnIsOpen(nTr)){
                /* 关闭 */
                $(this).addClass("row-details-close").removeClass("row-details-open");
                oTable.fnClose(nTr);
            }else{
                /* 打开 */
                $(this).addClass("row-details-open").removeClass("row-details-close");
                ORG_ContactList.fnFormatDetails(oTable, nTr, userId);
            }
        });
        
        jQuery('#'+tableId).on('change', '.group-checkable', function(){
            var set = jQuery(this).attr("data-set");
            var checked = jQuery(this).is(":checked");
            jQuery(set).each(function () {
                if (checked) {
                    $(this).attr("checked", true);
                    $(this).parents('tr').addClass("active");
                } else {
                    $(this).attr("checked", false);
                    $(this).parents('tr').removeClass("active");
                }
            });
            jQuery.uniform.update(set);
        });
        
    };
    
    var initToolBar = function(){
    	// 触发相关赋值
		$("#confirmWin").on("show.bs.modal", function(e) {
			var id = $(e.relatedTarget).attr("id");
			$("#id").val('').val(id);
		});
    	
        //删除
        $("#deleteBut").on("click", function(){
            var param = {id : $("#id").val()};
            $.ajax({
                type: "post",
                data: param,
                url: 'manager/deleteKeyword.html',
                success: function (data) {
                    if (data.code == 0) {
                    	oTable.fnReloadAjax();
						bootbox.alert("删除成功");
						$("#confirmWin").modal("hide");
                    } else {
                    	bootbox.alert("删除失败");
                    }
                }
            });
        });
        
        //清空input框等
     	function clean() {
            $(':input').not(':text',':button, :submit, :reset, :hidden').val('').removeAttr('checked') .removeAttr('selected');
       };
       
     //查询
       $("#articleDefSearch").on("click", function(){
       	oTable.fnDestroy();
       	loadTableData();	
       	clean();
       });

    };
    
  
    

    return {
        init: function () {
            loadTableData();
            initPageEvent();
            initToolBar();
        },
        loadTableData:loadTableData
    };
}();
