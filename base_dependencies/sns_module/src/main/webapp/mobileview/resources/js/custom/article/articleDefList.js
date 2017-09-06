//文章列表
var Article_ArticleDefList = function () {
	var tableId = "articleDefTable";
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
			{ "sTitle": "文章id", "mData" : "id"},
            { "sTitle": "标题", "mData": "title"},
			{ "sTitle": "作者", "mData": "author"},
			{
				"sTitle": "更新时间",
				"mDataProp" : "",
				"sDefaultContent" : "",
				"sVisible" : false,
				"fnCreatedCell" : function(nTd,sData, oData, iRow, iCol) {
					$(nTd).append(changeDateFormat(oData.updatetime));
				}
			},
			{
				"sTitle": "文章属性",
				"mDataProp" : "",
				"sDefaultContent" : "",
				"sVisible" : false,
				"fnCreatedCell" : function(nTd,sData, oData, iRow, iCol) {
					if(oData.headlines=="1"){
						$(nTd).append("<label class='label label-info'>头条</label>&nbsp");
					}
					if(oData.rec=="1"){
						$(nTd).append("<label class='label label-primary'>推荐</label>&nbsp");
					}
					if(oData.slide=="1"){
						$(nTd).append("<label class='label label-warning'>幻灯片</label>&nbsp");						
					}
					if(oData.hide=="1"){
						$(nTd).append("<label class='label label-success'>隐藏</label>&nbsp");
					}
				}
			},
			{
				"sTitle": "审核状态",
				"mDataProp" : "",
				"sDefaultContent" : "",
				"sVisible" : false,
				"fnCreatedCell" : function(nTd,sData, oData, iRow, iCol) {
					if(oData.audit=="1"){
						$(nTd).append("<label class='label label-success'>已审核</label>");						
					}else{
						$(nTd).append("<label class='label label-danger'>未审核</label>");
					}
				}
			},
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
            		}else if(oData.brandcode=="DT"){
            			$(nTd).append("<label class='label label-danger'>数据价值</label>");
            		}else if(oData.brandcode=="NAIL"){
            			$(nTd).append("<label class='label label-warning'>印奈儿</label>");
            		}else if(oData.brandcode=="NM"){
            			$(nTd).append("<label class='label label-info'>牛么</label>");
            		}
            	}
            },
            { "sTitle": "返利积分", "mData": "point"},
			{ "sTitle": "点赞数","mData": "likenums"},
			{ "sTitle": "阅读数","mData": "readnums"},
			{ "sTitle": "推荐文章ID","mData": "recommend"}
        ];
        //渲染特殊的列(操作列)
        var aoColumnDefs =[];
            aoColumns.push({
            	"sTitle": "操作",
            	"mDataProp" : "",
            	"sDefaultContent" : "",
            	"sVisible" : false, 
            	"fnCreatedCell": function(nTd,sData, oData, iRow, iCol){
            		$(nTd).append("<span style='margin: 0 5px 0 5px'><span class='btn btn-xs purple ajaxify' href='addArticleDefView.html?id="+oData.id+"'><i class='fa fa-edit'></i> 编辑 </span>");
            		$(nTd).append("<span style='margin: 0 5px 0 5px'><span class='btn btn-xs yellow ajaxify' href='articleShareList.html?id="+oData.id+"'><i class='fa fa-search'></i> 查看 </span>");
            		$(nTd).append("<span style='margin: 0 5px 0 5px'><span class='btn btn-xs red' href='#confirmWin' data-toggle='modal' id='"+oData.id+"'><i class='fa fa-lock'></i> 删除 </span>");
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
        oTable = $('#'+tableId).DataTable({
        	"fnServerParams": function (aoData) {
        		$.each(search, function(index, value) {
					aoData.push({
						"name" : value.name,
						"value" : value.value
					})
				});
            },
            "sAjaxSource": "queryArticleDefData.html",
            "sAjaxDataProp": "queryResult",
            "bFilter" : true,
            "bInfo": true,
            "bJQueryUI": true,
            "bLengthChange": true,
            "bPaginate": true,
            "bProcessing": true,
            "bSort": true,
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
                		//alert("234");
                	    //存在且是function
                		//initSelectedArticleDefs();
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
    	  
        $("#confirmWin").on("show.bs.modal", function(e){
        	debugger;
            var articleId = $(e.relatedTarget).attr("id");
            $("#id").val('').val(articleId);
        });
        //删除
        $("#deleteBut").on("click", function(){
        	debugger;
            var param = {id : $("#id").val()};
            $.ajax({
                type: "post",
                data: param,
                url: 'deleteArticleDef.html',
                success: function (data) {
                    if (data.code == 0) {
                    } else {

                    }
                    $("#confirmWin").modal("hide");
                    if(oTable){
                        oTable.fnDestroy();
                    }
                    //调用初始化右侧表格的方法
                    Article_ArticleDefList.loadTableData();   
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
