/**
 * 
 */
(function($){
	var oTable = null;
	var options = null;
	
	$.fn.extend({
        "AutoReplyWin": function (_options) {
        	var defaultOpts = {
            	elm : $(this),
            	selectKey : '',
            	selectData: null,
            	onchange: function(){}
            }
            options = $.extend({}, defaultOpts, _options);
        	
        	init(options);
    		return {
			    show: function(_selectKey){ options.selectKey = _selectKey; $('#keywordWin').modal('show'); oTable.fnReloadAjax();},
			    hide: function(){ $('#keywordWin').modal('hide'); },
			    getValue: function(){return options.selectKey;}
		    };
        }
    });
    
    var init = function(options){
    	var html = 
    	'<div class="modal fade" tabindex="-1" data-backdrop="static" data-keyboard="false" id="keywordWin">' +
    	'	<div class="modal-dialog">'+
		'       <div class="modal-content">'+
		'	        <div class="modal-body"><table id="keywordtable" class="table table-striped table-bordered table-hover"></table></div>'+
		'	        <div class="modal-footer">'+
		'		        <button type="button" data-dismiss="modal" class="btn default">取消</button>'+
		'		        <button type="button" id="keywordWinSubmitBtn" class="btn green" >提交</button>'+
		'	        </div>'+
		'       </div>'+
	    '    </div>'+
        '</div>';
    	$(options.elm).replaceWith(html);
    	
    	loadTableData(options);
    	
    	$('#keywordWinSubmitBtn').click(function(){
        	debugger;
        	options.onchange(options.selectKey);
        	$('#keywordWin').modal('hide');
        });
    }
    
    
  //初始化加载表格数据的表头定义
    var loadTableData = function(options){
    	var aoColumns = [
        	{
    			"sTitle": "选择",
    			"mDataProp" : "",
    			"sDefaultContent" : "",
    			"sVisible" : false, 
    			"fnCreatedCell":function(nTd,sData, oData, iRow, iCol){
    				var isSelected = options.selectKey == oData.keywords? true: false;
    				var checkboxObj = $('<input>', {
    					type: "radio",
						checked: isSelected,
    					name: "selectedAutoReply",
    					change : function(){
    						options.selectKey = oData.keywords;
    						options.selectData = oData;
    						options.onchange(oData);
    					}
            		});
    				$(nTd).append(checkboxObj);
    			}
        	},{
    			"sTitle": "关键字",
    			"mDataProp" : "",
    			"sDefaultContent" : "",
    			"sVisible" : false, 
    			"fnCreatedCell":function(nTd,sData, oData, iRow, iCol){
    				$(nTd).append(oData.keywords);
    			}
        	},{
        		"sTitle": "回复类型",
        		"mDataProp" : "",
        		"sDefaultContent" : "",
        		"sVisible" : false, 
        		"fnCreatedCell":function(nTd,sData, oData, iRow, iCol){
        			$(nTd).append(oData.replyType == '1' ? '文本': '图文');
        		}
        	},{
        		"sTitle": "自动回复",
        		"mDataProp" : "",
        		"sDefaultContent" : "",
        		"sVisible" : false, 
        		"fnCreatedCell":function(nTd,sData, oData, iRow, iCol){
        			if(oData.replyContent && oData.replyContent.length>10){
        				$(nTd).append(oData.replyContent.substring(0, 10));
        			}else{
        				$(nTd).append(oData.replyContent)
        			}
        		}
        	},{
        		"sTitle": "回复类型",
        		"mDataProp" : "",
        		"sDefaultContent" : "",
        		"sVisible" : false, 
        		"fnCreatedCell":function(nTd,sData, oData, iRow, iCol){
        			$(nTd).append(oData.matchType == '100' ? '完全匹配': '模糊匹配');
        		}
        	}
        ];
    	
        oTable = $('#keywordtable').DataTable({
        	"fnServerParams": function (aoData) {
                //aoData.push({"name": "orgId", "value": currtOrgId});
            },
            "sAjaxSource": "wxMsgAutoReply/service/findByPage?originId=" + $('#originId').val(),
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
            "aoColumns": aoColumns,
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
    
})(jQuery);
