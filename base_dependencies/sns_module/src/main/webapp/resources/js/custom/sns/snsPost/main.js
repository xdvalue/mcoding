//帖子列表
var DataTableList = function () {
	var tableId = "dataTable";
	var oTable = null;
	var selectIds =[];
	
	//初始化加载表格数据的表头定义
    var initTableHeaderInfo = function(){
        var aoColumns = [
            { "sTitle": "选择", 
				"mDataProp" : "",
				"sDefaultContent" : "",
				"sVisible" : false, 
				"fnCreatedCell":function(nTd,sData, oData, iRow, iCol){
					
					var checkboxObj = $('<input>', {
						type: "checkbox",
						change : function(){ 
							var isChecked = $(this).attr('checked');
							if(isChecked && isChecked == 'checked'){
								addIntoselectIds(oData.id);
							}else{
								removeFormSelectId(oData.id);
							}
						}
	        		});
					$(nTd).append(checkboxObj);
				}
            },
            { "sTitle": "id", "mData": "id"},
			{ "sTitle": "标题", "mData": "title"},
			{ "sTitle": "版块", "mData": "moduleName"},
			{ "sTitle": "作者", "mData": "memberName"},
			{
				"sTitle": "浏览量", 
				"mDataProp" : "",
				"sDefaultContent" : "",
				"sVisible" : false, 
				"fnCreatedCell":function(nTd,sData, oData, iRow, iCol){
					var btnEditHtml = '<span>'+oData.snsPostExtInfo.viewNum+' </span><i class="fa fa-plus"></i>';
	        		var btnEditObj = $('<span>', {
	        			'class' : 'btn btn-sm green',
	        			click:function(){addPageViewCount(oData.id)}
	        		});
	        		btnEditObj.append(btnEditHtml);
	        		btnEditObj.appendTo($(nTd));
//					$(nTd).append('<span class="btn btn-sm green"></span>');
				}
			},
			{ "sTitle": "评论数", "mData": "snsPostExtInfo.commentNum"},
			{ "sTitle": "点赞数", "mData": "snsPostExtInfo.likeNum"},
			{
				"sTitle": "帖子类型", 
				"mDataProp" : "",
				"sDefaultContent" : "",
				"sVisible" : false, 
				"fnCreatedCell":function(nTd,sData, oData, iRow, iCol){
					if(oData.typeFlag == 1){
						$(nTd).append('普通贴');
					}else if(oData.typeFlag == 2){
						$(nTd).append('活动贴');
					}else if(oData.typeFlag == 3){
						$(nTd).append('精品贴');
					}
				}
			},
			{
				"sTitle": "审核状态", 
				"mDataProp" : "",
				"sDefaultContent" : "",
				"sVisible" : false, 
				"fnCreatedCell":function(nTd,sData, oData, iRow, iCol){
					if(oData.isCheck && oData.isCheck==1){
						$(nTd).append('已审核');
					}else{
						$(nTd).append('未审核');
					}
				}
			},
			{
				"sTitle": "置顶状态", 
				"mDataProp" : "",
				"sDefaultContent" : "",
				"sVisible" : false, 
				"fnCreatedCell":function(nTd,sData, oData, iRow, iCol){
					if(oData.orderNum == 0){
						$(nTd).append('未置顶');
					}else{
						$(nTd).append('已置顶');
					}
				}
			},
			{
				"sTitle": "首页置顶", 
				"mDataProp" : "",
				"sDefaultContent" : "",
				"sVisible" : false, 
				"fnCreatedCell":function(nTd,sData, oData, iRow, iCol){
					if(oData.orderInHome == 0){
						$(nTd).append('未置顶');
					}else{
						$(nTd).append('已置顶');
					}
				}
			}
			
        ];
        
        aoColumns.unshift({
        	"sTitle": "操作",
        	"mDataProp" : "",
        	"sDefaultContent" : "",
        	"sVisible" : false, 
        	"fnCreatedCell": function(nTd,sData, oData, iRow, iCol){
        		var btnEditHtml = "<span class='btn btn-xs green ajaxify' href='snsPost/service/toUpdateViewById?id="+oData.id+"'><i class='fa fa-edit'></i>编辑</span> ";
        		var btnEditObj = $('<span>', {
        			style : "margin: 0 5px 0 5px"
        		});
        		btnEditObj.append(btnEditHtml);
        		btnEditObj.appendTo($(nTd));
        		
        		var setIsUpTopValue = '-1';
        		var btnSetIsUpTopHtml = null;
        		if(oData.orderNum==0){
        			btnSetIsUpTopHtml = "<span class='btn btn-xs purple'><i class='fa fa-edit'></i> 置顶";
        			setIsUpTopValue = 1;
        		}else{
        			btnSetIsUpTopHtml = "<span class='btn btn-xs red'><i class='fa fa-edit'></i> 取顶";
        			setIsUpTopValue = 0;
        		}
        		
        		var btnSetIsUpTopObj = $('<span>', {
        			style : "margin: 0 5px 0 5px",
        			click : function(){upTopHandler(oData.id, setIsUpTopValue); }
        		});
        		btnSetIsUpTopObj.append(btnSetIsUpTopHtml);
        		btnSetIsUpTopObj.appendTo($(nTd));
        		
        		var setIsUpTopInHomeValue = '-1';
        		var btnSetIsUpTopInHomeHtml = null;
        		if(oData.orderInHome==0){
        			btnSetIsUpTopInHomeHtml = "<span class='btn btn-xs purple'><i class='fa fa-edit'></i> 首置顶";
        			setIsUpTopInHomeValue = 1;
        		}else{
        			btnSetIsUpTopInHomeHtml = "<span class='btn btn-xs red'><i class='fa fa-edit'></i> 首取顶";
        			setIsUpTopInHomeValue = 0;
        		}
        		
        		var btnSetIsUpTopInHomeObj = $('<span>', {
        			style : "margin: 0 5px 0 5px",
        			click : function(){upTopHandler(oData.id, setIsUpTopInHomeValue, 1); }
        		});
        		btnSetIsUpTopInHomeObj.append(btnSetIsUpTopInHomeHtml);
        		btnSetIsUpTopInHomeObj.appendTo($(nTd));
        		
        		var essencePostvalue = '';
        		var isEssence = 1;
        		var btnSetIsEssenceHtml = null;
        		if(oData.typeFlag == 3){
        			btnSetIsEssenceHtml = "<span class='btn btn-xs red'><i class='fa fa-edit'></i> 取精";
        			isEssence = 0;
        		}else if(oData.typeFlag == 1){
        			btnSetIsEssenceHtml = "<span class='btn btn-xs purple'><i class='fa fa-edit'></i> 精华";
        			isEssence = 1;
        		}
        		var btnSetIsEssenceObj = $('<span>', {
        			style : "margin: 0 5px 0 5px",
        			click : function(){essencePostHandler(oData.id, isEssence); }
        		});
        		btnSetIsEssenceObj.append(btnSetIsEssenceHtml);
        		btnSetIsEssenceObj.appendTo($(nTd));
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
        	"fnDrawCallback":function(){
//        		initSwitch();
        		selectIds = [];
        	},
        	"fnServerParams": function (aoData) {
        		var search = $("#searchForm").serializeArray();
        		$.each(search, function(index, value) {
					aoData.push({
						"name" : value.name,
						"value" : value.value
					});
				});
            },
            "sAjaxSource": "snsPost/service/findByPage",
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
    
    var initSwitch = function(){
    	debugger;
    	$(".make-switch").bootstrapSwitch({
    		onSwitchChange:function(event, state) {
    			debugger;
                console.log(event.target); // DOM element
            }
    	});
    };
    
    var addIntoselectIds = function(id){
    	if(selectIds.indexOf(id) != -1){
    		return;
    	}
    	selectIds.push(id);
    }
    
    var removeFormSelectId = function(id){
    	debugger;
    	if(selectIds.indexOf(id) == -1){
    		return;
    	}
    	selectIds = selectIds.filter(function(item){
    		return item != id;
    	});
    }
    
    var upTopHandler = function(snsPostId,isUp, isHomeUp){
    	console.log(isUp);
    	var msg = isUp > 0 ? "确认置顶吗？":"确认取消置顶吗？";
    	bootbox.confirm(msg,function(result){
    		if(!result){
    			return;
    		}
    		
    		var url = 'snsPost/service/upTopById?postId='+snsPostId+'&isUp='+isUp;
    		if(isHomeUp) url = url + '&isHomeUp=1';
    		$.getJSON(url,function(json){
    			if(json&&json.status=='00'){
    				bootbox.alert("操作成功");
    				oTable.fnReloadAjax();
    				return;
    			}else{
    				return bootbox.alert("操作失败,请刷新后重试");
    			}
    		});
    	});
    }
    
    var essencePostHandler = function(snsPostId, isEssence){
    	var url = 'snsPost/service/setPostAsEssence';
		$.getJSON(url, {ids: snsPostId, isEssence : isEssence}, function(json){
			if(json&&json.status=='00'){
				bootbox.alert("操作成功");
				oTable.fnReloadAjax();
				return;
			}else{
				return bootbox.alert("操作失败,请刷新后重试");
			}
		});
    };
    
    var setCheckHandler = function(snsPostId, isEnable){
    	var msg = isEnable == 1 ? "确认通过审核吗?": "确认取消审核吗?"
    	
    	bootbox.confirm(msg, function(result) {
    		if (!result) {
				return;
			}
			var url = 'snsPost/service/setIsEnableById?id=' +snsPostId+'&isEnable=' + isEnable;
			
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
    
   var deleteHandler = function(snsPostId){
    	bootbox.confirm("确认删除吗?", function(result) {
			if (!result) {
				return;
			}
			var url = 'snsPost/service/deleteById?id=' +snsPostId;
			
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
    
    var initToolBar = function(){
    	$("#checkBtn").on("click", function(){
    		if(!selectIds || selectIds.length==0){
    			bootbox.alert("请选择帖子");
    			return;
    		}
    		var url = 'snsPost/service/setIsCheck';
    		$.getJSON(url, {ids : selectIds.join(','), isCheck: 1 }, function(json){
				if(json && json.status == '00'){
					bootbox.alert("操作成功");
					oTable.fnReloadAjax();
					return;
				}else{
					return bootbox.alert("操作失败,请刷新后重试");
				}
			});
        });
    	$("#uncheckBtn").on("click", function(){
    		if(!selectIds || selectIds.length==0){
    			bootbox.alert("请选择帖子");
    			return;
    		}
    		var url = 'snsPost/service/setIsCheck';
    		$.getJSON(url, {ids : selectIds.join(','), isCheck: 0 }, function(json){
				if(json && json.status == '00'){
					bootbox.alert("操作成功");
					oTable.fnReloadAjax();
					return;
				}else{
					return bootbox.alert("操作失败,请刷新后重试");
				}
			});
        });
    	$('#chooseModule_confirm_btn').on('click', function(){
    		if(!selectIds || selectIds.length==0){
    			bootbox.alert("请选择帖子");
    			return;
    		}
    		debugger;
    		var moduleId = $('#chooseModule_select').val();
    		if(!moduleId|| isNaN(moduleId) || moduleId<=0){
    			bootbox.alert("请选择正确的模板");
    			return;
    		}
    		var url = 'snsPost/service/changePostModule';
    		$.getJSON(url, {ids : selectIds.join(','), moduleId: moduleId }, function(json){
				if(json && json.status == '00'){
					oTable.fnReloadAjax();
					$('#chooseModule_modal').modal("hide");
					bootbox.alert("操作成功");
					return;
				}else{
					return bootbox.alert("操作失败,请刷新后重试");
				}
			});
    		
    	});
    	
    }
    
    function addPageViewCount(postId){
    	bootbox.prompt("请输入增加的点击量", function(countNum) {
            if (!countNum) {
            	return;
            } else {
                
            	var url = 'snsPost/service/addPostViewCount';
            	$.getJSON(url, {postId: postId, addCount: countNum}, function(json){
        			if(json && json.status == '00'){
        				oTable.fnReloadAjax();
        				bootbox.alert("操作成功");
        				return;
        			}else{
        				return bootbox.alert("操作失败,请刷新后重试。原因:" + json.msg);
        			}
        		});
            	
            }
        });
    }
    
    function initPageViewCount(){
    	var url = 'snsStatistical/service/postViewCount';
    	$.getJSON(url, function(json){
			if(json && json.status == '00'){
				oTable.fnReloadAjax();
				$('#postViewCount').html(json.data);
				return;
			}else{
				return bootbox.alert("操作失败,请刷新后重试");
			}
		});
    }
    
    return {
        init: function () {
        	initToolBar();
            loadTableData();
            initPageViewCount();
        }
    };
}();

