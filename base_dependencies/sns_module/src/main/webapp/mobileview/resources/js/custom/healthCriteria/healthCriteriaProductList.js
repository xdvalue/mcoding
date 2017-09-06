//产品列表
var health_ProductList = function () {
	var tableId = "healthProductTable";
	var oTable = null;
	
	//初始化加载表格数据的表头定义
    var initTableHeaderInfo = function(){
        var aoColumns = [
			{
			    "sTitle":'<th class="table-checkbox"><input type="checkbox" class="group-checkable" data-set="#'+tableId+' .checkboxes"/></th>',
			    "bSortable": false,
			    "bSearchable": false,
			    "mDataProp" : "",
			    "sDefaultContent" : "",
			    "sVisible" : false,
			    "fnCreatedCell" : function(nTd, sData, oData, iRow, iCol) {
			        $(nTd).append('<td class="table-checkbox">');
			        var flag = false;
			        if(checkvalue!=""){
			        	 var productids=checkvalue.split(",");
							for(var i=0;i<productids.length;i++){
								if(oData.productId==productids[i]){
									flag = true;
								}
							}
			        }
					if(flag){
						$(nTd).append('<input type="checkbox" class="checkboxes" value="'+ oData.productId +'" onblur="getCheck()" checked/>');
					}else{
						$(nTd).append('<input type="checkbox" class="checkboxes" value="'+ oData.productId +'" onblur="getCheck()"/>');
					}
					$(nTd).append('</td>');
			    }
			},
			//{ "sTitle": "图片","mData": "productCoverImg"},
			{
				"sTitle": "商品类型",
				"mDataProp" : "",
				"sDefaultContent" : "",
				"sVisible" : false,
				"fnCreatedCell" : function(nTd, sData, oData, iRow, iCol) {
					$(nTd).append(convertProductCoverImg(oData.productCoverImg));
				}
			},
			{ "sTitle": "产品名称","mData": "productName"}
        ];
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
            "sAjaxSource": "queryProductData.html?productType=商品",
            "sAjaxDataProp": "queryResult",
            "bFilter" : true,
            "bInfo": true,
            "bJQueryUI": true,
            "bLengthChange": true,
            "bPaginate": true,
            "bProcessing": true,
            "bSort": false,
            "bSortClasses": true,
            "bStateSave": true,
            "bAutoWidth":false,
            "bSortCellsTop": true,
            "iTabIndex": 1,
            "sServerMethod": "POST",
            "bServerSide": true,
            "aoColumns": headerInfo.aoColumns,
            "aLengthMenu": [
                [10, 20, 30, 40, 50],
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
    
    //产品类型
    var convertProductType = function(productType){
    	var html = '';
    	if(productType == "商品"){
    		html = "<label class='label label-success'>商品</label>";
    	}else if(productType == "赠品"){
    		html = "<label class='label label-primary'>赠品</label>";
    	}else{
    		html = "<label class='label label-info'>无</label>";
    	}
    	return html;
    };
    
    /**产品图品*/
    var convertProductCoverImg = function(productCoverImg){
    	var html = "<img src='"+productCoverImg+"' style='width:70px;height:70px'>";
    	return html;
    };

    return {
        init: function () {
            loadTableData();
            initPageEvent();
        },
        loadTableData:loadTableData
    };
}();

var checkvalue="";
function getCheck(){
	var str = checkvalue;
	$('#healthProductTable' + ' tbody tr .checkboxes:checked').each(function() {
		if($(this).is(':checked')){
			str += $(this).val() + ",";
		}
	});
	checkvalue=str;
}
