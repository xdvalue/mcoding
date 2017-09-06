//图文列表
var wechatNews_wechatNewsList = function () {
	var tableId = "wechatNewsTable";
	var oTable = null;
	
	//初始化加载表格数据的表头定义
    var initTableHeaderInfo = function(){
        var aoColumns = [
			{
			    "sTitle":'<th class="table-checkbox"><input type="checkbox" class="group-checkable" data-set="#'+tableId+' .checkboxes"/></th>',
			    "sWidth" : "1%",
			    "bSortable": false,
			    "bSearchable": false,
			    "mDataProp" : "",
			    "sDefaultContent" : "",
			    "sVisible" : false,
			    "fnCreatedCell" : function(nTd, sData, oData, iRow, iCol) {
			    	//var a = $("#title").val();//编辑时默认选择
			    	var a = $("#newsorder").val();//编辑时默认选择
			    	if(a==""){
			    		a=$("#new_newsid").val();//提交并返回
			    	}
			    	if(a!=""){
			    		newsIds=a.split(",");
			    	}
			        $(nTd).append('<td class="table-checkbox">');
			        var flag = false;
			        if(checkvalue!=""){//翻页时默认选择
			        	 var ids=checkvalue.split(",");
							for(var i=0;i<ids.length;i++){
								if(oData.id==ids[i]){
									flag = true;
								}
							}
			        }
			        if(a!=""){
			        	for(var i=0;i<newsIds.length;i++){
							if(oData.id==newsIds[i]){
								flag = true;
							}
						}
			        }
					if(flag){
						$(nTd).append('<input type="checkbox" class="checkboxes" value="'+ oData.id +'" onblur="getCheck()" onclick="setNewsOrder(this)" checked/>');
					}else{
						$(nTd).append('<input type="checkbox" class="checkboxes" value="'+ oData.id +'" onblur="getCheck()" onclick="setNewsOrder(this)"/>');
					}
					$(nTd).append('</td>');
			    }
			},
			{ "sTitle": "id", "sWidth" : "1%", "mData": "id"},
			{ "sTitle": "标题", "sWidth" : "1%", "mData": "title"},
			{ "sTitle": "摘要", "sWidth" : "1%", "mData": "content"},
			{
				"sTitle": "封面图片",
				'sClass': "myscc",
				'sWidth': "15%",
				"mDataProp" : "",
				"sDefaultContent" : "",
				"sVisible" : false,
				"fnCreatedCell" : function(nTd, sData, oData, iRow, iCol) {
					$(nTd).append(convertImg(oData.image));
				}
			}/*,
			{ "sTitle": "创建时间", "sWidth" : "1%", "mData": "createdate"},
			{ "sTitle": "创建时间", "sWidth" : "1%", "mData": "updatedate"}*/
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
            		/*if(App.checkUserOperRight("wechatReplyList", "editWechatReply")){
            			$(nTd).append("<span style='margin: 0 5px 0 5px'><span class='btn btn-xs purple ajaxify'" +
            					" href='addWechatReplyView.html?id="+oData.id+"'><i class='fa fa-edit'></i> 编辑 </span>");
            		}*/
        			$(nTd).append("<span class='btn default btn-xs black' data-toggle='modal' " +
        					"href='#confirmWin' enable='ok' " + "id='"+ oData.id +"'>" +
                    "<i class='fa fa-trash-o'></i>删除 </span>");
            		 
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
        	"fnServerParams": function (aoData) {
                //aoData.push({"name": "orgId", "value": currtOrgId});
            },
            "sAjaxSource": "queryWechatNewsData.html",
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
                url : "deleteWechatNews.html",
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
   
    
    /**封面图片*/
    var convertImg = function(imgage){
    	var html = "<img src='"+imgage+"' style='width:30%'>";
    	return html;
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

var checkvalue="";
function getCheck(){
	var str = checkvalue;
	$('#wechatNewsTable' + ' tbody tr .checkboxes:checked').each(function() {
		if($(this).is(':checked')){
			str += $(this).val() + ",";
		}
	});
	checkvalue=str;
	
	
}

function setNewsOrder(e){
	//图文顺序
	if($(e).is(':checked')){//选中
		var oldvalue=$("#newsorder").val();
		$("#newsorder").val(oldvalue+$(e).val()+",");
	}else{
		var oldvalue=$("#newsorder").val();
		var values = oldvalue.split(",");
		for(var i=0;i<values.length;i++){
			if($(e).val()==values[i]){
				values.splice(i,1);
			}
		}
		$("#newsorder").val(values);
	}
	
}

/**默认选中的素材*/
function setDefaultNews(){
	var newsIds=$("#title").val().split(",");
	//console.info("checkboxes length:"+$('#wechatNewsTable'+' tbody tr .checkboxes').length);
	$('#wechatNewsTable'+' tbody tr .checkboxes').each(function() {
		for(var i=0;i<newsIds.length;i++){
			console.info($(this).val());
			if($(this).val()==newsIds[i]){
				this.checked="checked";
			}
		}
	});
}