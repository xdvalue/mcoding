//产品列表
var MallGame_MallGameResultList = function () {
	var tableId = "mallGameResultTable";
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
            { "sTitle": "游戏id", "mData": "gameid"},
            { "sTitle": "游戏名称", "mData": "gamename"},
			{ "sTitle": "手机号", "mData": "mobilephone"},
			{ "sTitle": "微信openid", "mData": "openid"},
			{ "sTitle": "微信昵称", "mData": "nickname"},
			{
            	"sTitle": "是否中奖",
            	"mDataProp" : "",
            	"sDefaultContent" : "",
            	"sVisible" : false,
            	"fnCreatedCell" : function(nTd, sData, oData, iRow, iCol) {
            		if(oData.islottery=="1"){
            			$(nTd).append("<label class='label label-success'>中奖</label>");
            		}else{
            			$(nTd).append("<label class='label label-primary'>未中奖</label>");
            		}
            		
            	}
            },
			{
            	"sTitle": "是否已下单",
            	"mDataProp" : "",
            	"sDefaultContent" : "",
            	"sVisible" : false,
            	"fnCreatedCell" : function(nTd, sData, oData, iRow, iCol) {
            		if(oData.isorder=="1"){
            			$(nTd).append("<label class='label label-success'>已下单</label>");
            		}else{
            			$(nTd).append("<label class='label label-primary'>未下单</label>");
            		}
            		
            	}
            },
			{ "sTitle": "产品id", "mData": "productid"},
			{ "sTitle": "产品名称", "mData": "productname"},
			{ "sTitle": "产品图片", "mData": "productcoverimg"},
			{ "sTitle": "赠品id", "mData": "giftid"},
			{ "sTitle": "可获得的积分", "mData": "gainpoint"},
			{ "sTitle": "数量", "mData": "nums"},
			{
            	"sTitle": "品牌",
            	"mDataProp" : "",
            	"sDefaultContent" : "",
            	"sVisible" : false,
            	"fnCreatedCell" : function(nTd, sData, oData, iRow, iCol) {
            		if(oData.brandcode=="MRMJ"){
            			$(nTd).append("<label class='label label-success'>每日每加</label>");
            		}else if(oData.brandcode=="JLD"){
            			$(nTd).append("<label class='label label-primary'>健乐多</label>");
            		}
            		
            	}
            },
            {
				"sTitle": "抽奖日期",
				"mDataProp" : "",
				"sDefaultContent" : "",
				"sVisible" : false,
				"fnCreatedCell" : function(nTd,sData, oData, iRow, iCol) {
					if("" != oData.drawdate && null != oData.drawdate){
						$(nTd).append(changeDateFormat(oData.drawdate));
					}
				}
			},
			{
				"sTitle": "创建时间",
				"mDataProp" : "",
				"sDefaultContent" : "",
				"sVisible" : false,
				"fnCreatedCell" : function(nTd,sData, oData, iRow, iCol) {
					if("" != oData.createtime && null != oData.createtime){
						$(nTd).append(changeDateFormat(oData.createtime));
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
//            		$(nTd).append("<span style='margin: 0 5px 0 5px'><span class='btn default btn-xs black' data-toggle='modal' href='#confirmWin' id="+oData.id+"><i class='fa fa-trash-o'></i> 删除 </span>");
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
        var search = $("#mallGameResultSearchForm").serializeArray();
        oTable = $('#'+tableId).DataTable({
        	"fnServerParams": function (aoData) {
        		$.each(search, function(index, value) {
					aoData.push({
						"name" : value.name,
						"value" : value.value
					})
				});
            },
            "sAjaxSource": "queryMallGameResultByPage.html",
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
        
        
        //清空input框等
     	function clean() {
            $(':input').not(':text',':button, :submit, :reset, :hidden').val('').removeAttr('checked') .removeAttr('selected');
            $("#mallGameResultSearchForm").find('input').attr('value','');
    		$("#mallGameResultSearchForm").find('select').attr('value','');
       };
       
     //查询
       $("#mallGameResultSearch").on("click", function(){
    	   oTable.fnDestroy();
    	   loadTableData();	
//    	   clean();
       });
       

       $("#clearSearch").on("click", function(){
    	   clean();
    	   oTable.fnDestroy();
    	   loadTableData();	
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
