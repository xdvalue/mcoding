//产品列表
var RuleEngine_RuleEngineConfigList = function () {
	var tableId = "ruleEngineConfigTable";
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
			        $(nTd).append('<input type="checkbox" class="checkboxes" value="'+ oData.id +'"/>');
			        $(nTd).append('</td>');
			    }
			},
			{ "sTitle": "id", "mData" : "id"},
            { "sTitle": "规则名称", "mData": "ruleName"},
			{ "sTitle": "规则类型", "mData": "ruleTypeName"},
			{ "sTitle": "商品名称", "mData": "productName"},
			{
            	"sTitle": "折扣价",
            	"mDataProp" : "",
				"sDefaultContent" : "",
            	"sVisible" : false,
            	"fnCreatedCell" : function(nTd, sData, oData, iRow, iCol) {
            		if("" != oData.origPrice && null != oData.origPrice){
            			$(nTd).append("￥"+oData.origPrice/100);
            		}
            	}
            },
			{
            	"sTitle": "促销价",
            	"mDataProp" : "",
				"sDefaultContent" : "",
            	"sVisible" : false,
            	"fnCreatedCell" : function(nTd, sData, oData, iRow, iCol) {
            		if("" != oData.promoPrice && null != oData.promoPrice){
            			$(nTd).append("￥"+oData.promoPrice/100);
            		}
            	}
            },
			{ "sTitle": "促销价需购买数量", "mData": "purchaseNum"},
			{ "sTitle": "赠品","mData": "giftProductName"},
			{ "sTitle": "赠品数","mData": "giftNum"},
			{
            	"sTitle": "满减满赠购买额度",
            	"mDataProp" : "",
				"sDefaultContent" : "",
            	"sVisible" : false,
            	"fnCreatedCell" : function(nTd, sData, oData, iRow, iCol) {
            		if("" != oData.promoMinLimit && null != oData.promoMinLimit){
            			$(nTd).append("￥"+oData.promoMinLimit/100);
            		}
            	}
            },
			{
            	"sTitle": "满减优惠金额",
            	"mDataProp" : "",
				"sDefaultContent" : "",
            	"sVisible" : false,
            	"fnCreatedCell" : function(nTd, sData, oData, iRow, iCol) {
            		if("" != oData.returnBack && null != oData.returnBack){
            			$(nTd).append("￥"+oData.returnBack/100);
            		}
            	}
            },
            {
            	"sTitle": "邮费",
            	"mDataProp" : "",
				"sDefaultContent" : "",
            	"sVisible" : false,
            	"fnCreatedCell" : function(nTd, sData, oData, iRow, iCol) {
            		if("" != oData.freight && null != oData.freight){
            			$(nTd).append("￥"+oData.freight/100);
            		}
            	}
            },
			{
				"sTitle": "开始时间",
				"mDataProp" : "",
				"sDefaultContent" : "",
				"sVisible" : false,
				"fnCreatedCell" : function(nTd,sData, oData, iRow, iCol) {
					if("" != oData.startTime && null != oData.startTime){
						$(nTd).append(changeDateFormat(oData.startTime));
					}
				}
			},
			{
				"sTitle": "结束时间",
				"mDataProp" : "",
				"sDefaultContent" : "",
				"sVisible" : false,
				"fnCreatedCell" : function(nTd,sData, oData, iRow, iCol) {
					if("" != oData.endTime && null != oData.endTime){
						$(nTd).append(changeDateFormat(oData.endTime));
					}
				}
			},
			{
            	"sTitle": "品牌",
            	"mDataProp" : "",
            	"sDefaultContent" : "",
            	"sVisible" : false,
            	"fnCreatedCell" : function(nTd, sData, oData, iRow, iCol) {
            		if(oData.brandCode=="MRMJ"){
            			$(nTd).append("<label class='label label-success'>每日每加</label>");
            		}else if(oData.brandCode=="JLD"){
            			$(nTd).append("<label class='label label-primary'>健乐多</label>");
            		}
            		
            	}
            },
			{
            	"sTitle": "是否启用",
            	"mDataProp" : "",
            	"sDefaultContent" : "",
            	"sVisible" : false,
            	"fnCreatedCell" : function(nTd, sData, oData, iRow, iCol) {
            		if(oData.status=="1"){
            			$(nTd).append("<label class='label label-success'>是</label>");
            		}else{
            			$(nTd).append("<label class='label label-primary'>否</label>");
            		}
            		
            	}
            },
			{
				"sTitle": "创建时间",
				"mDataProp" : "",
				"sDefaultContent" : "",
				"sVisible" : false,
				"fnCreatedCell" : function(nTd,sData, oData, iRow, iCol) {
					$(nTd).append(changeDateFormat(oData.createTime));
				}
			},
			{
				"sTitle": "更新时间",
				"mDataProp" : "",
				"sDefaultContent" : "",
				"sVisible" : false,
				"fnCreatedCell" : function(nTd,sData, oData, iRow, iCol) {
					$(nTd).append(changeDateFormat(oData.updateTime));
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
            		$(nTd).append("<span style='margin: 0 5px 0 5px'><span class='btn btn-xs purple ajaxify' href='saveRuleEngineConfigView.html?id="+oData.id+"'><i class='fa fa-edit'></i> 编辑 </span>");
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
        var search = $("#ruleEngineConfigSearchForm").serializeArray();
        oTable = $('#'+tableId).DataTable({
        	"fnServerParams": function (aoData) {
        		$.each(search, function(index, value) {
					aoData.push({
						"name" : value.name,
						"value" : value.value
					})
				});
            },
            "sAjaxSource": "queryRuleEngineConfigByPage.html",
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
        
        
        if (jQuery().datepicker) {
        	$('.date-picker').datepicker({
        		format: 'yyyy-mm-dd',
                weekStart: 1,
                todayBtn: 'linked',
                rtl: App.isRTL(),
                language: "zh-CN",
                autoclose: true
            });
        }
        
        if (jQuery().datetimepicker) {
        	$(".datetime_picker").datetimepicker({
        		format: 'yyyy-mm-dd hh:ii:ss',
        		weekStart: 1,
        		todayBtn: 'linked',
        		rtl: App.isRTL(),
        		language: "zh-CN",
        		autoclose: true
        	});
        }     
    };
    
    var initToolBar = function(){
    	 //触发相关赋值
        $("#confirmWin").on("show.bs.modal", function(e){
            var id = $(e.relatedTarget).attr("id");
            $("#id").val('').val(id);
        });
        
        //删除
        $("#deleteBut").on("click", function(){
            var param = {id : $("#id").val()};
            $.ajax({
                type: "post",
                data: param,
                url: 'deleteRuleEngineConfigById.html',
                success: function (data) {
                    if (data.code == 0) {
                    } else {

                    }
                    $("#confirmWin").modal("hide");
                    //oTable.fnDeleteRow();
                    if(oTable){
                        oTable.fnDestroy();
                    }
                    //调用初始化右侧表格的方法
                    RuleEngine_RuleEngineConfigList.loadTableData();   
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
