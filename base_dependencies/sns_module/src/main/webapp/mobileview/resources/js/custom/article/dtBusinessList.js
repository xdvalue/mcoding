//产品列表
var DTExpertList = function () {
	var tableId = "articleDefTable";
	var oTable = null;
	
	//初始化加载表格数据的表头定义
    var initTableHeaderInfo = function(){
        var aoColumns = [
			{ "sTitle": "id", "mData" : "id"},
			{ "sTitle": "业务名称", "mData": "name"},
            {
            	"sTitle": "背景图像",
            	"mDataProp" : "",
            	"sDefaultContent" : "",
            	"sVisible" : false,
            	"fnCreatedCell" : function(nTd, sData, oData, iRow, iCol) {
            		$(nTd).append("<img src='" + oData.bgimg + "' width='30%'>");
            	}
            },
			{ "sTitle": "超链接", "mData": "href"},
			{ "sTitle": "描述", "mData": "description"}
        ];
        //渲染特殊的列(操作列)
        var aoColumnDefs =[];
            aoColumns.push({
            	"sTitle": "操作",
            	"mDataProp" : "",
            	"sDefaultContent" : "",
            	"sVisible" : false, 
            	"fnCreatedCell": function(nTd,sData, oData, iRow, iCol){
            		$(nTd).append("<span style='margin: 0 5px 0 5px'><span class='btn btn-xs purple ajaxify' href='page/addDTBusinessPage.html?id="+oData.id+"'><i class='fa fa-edit'></i> 编辑 </span>");
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
        var headerInfo = initTableHeaderInfo();
        var search = $("#articleDefSearchForm").serializeArray();
        console.log(search);
        oTable = $('#'+tableId).DataTable({
        	"fnServerParams": function (aoData) {
        		$.each(search, function(index, value) {
					aoData.push({
						"name" : value.name,
						"value" : value.value
					})
				});
            },
            "sAjaxSource": "management/queryDTBusinessByPage.html",
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
            },
            
            "fnInitComplete": function(oSettings, json) {
            	//初始化完毕，recommend不为undefined，说明为修改文章，设置相关行选中
                if("undefined" != $("#recommend").val()){
                	/*var recommends=$("#recommend").val().split(",");
                	$('#articleDefTable'+' tbody tr .checkboxes').each(function() {
                		for(var i=0;i<recommends.length;i++){
                			console.log("recommend:"+recommends[i]);
                			if($(this).val()==recommends[i]){
                				this.checked="checked";
                			}
                		}
                	});*/
                	//编辑时带出选中文章的信息，方法位于addArticleDef.js
                	if( typeof initSelectedArticleDefs === 'function' ){
                	    //存在且是function
                		initSelectedArticleDefs();
                	}
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
                url: 'management/deleteDTBusinessById.html',
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
