//集团架构列表
var ORG_OrganizationList = function () {
	var tableId = "memberTable";
	var currtOrgId = null;
	var oTable = null;
	var menuJstree =null;
	
	//初始化加载表格数据的表头定义
    var initTableHeaderInfo = function(){
        var aoColumns = [
			{ 
				"sTitle": "",
				"mDataProp" : "",
				"sDefaultContent" : "",
				"sVisible" : false,
				"fnCreatedCell" : function(nTd,sData, oData, iRow, iCol) {
						$(nTd).append("<span class='row-details row-details-close'></span>");
						$(nTd).append("<input type='hidden' name='orgId' value="+oData.orgId+">");
				}
			},
			{ "sTitle": "编号",'sWidth':'5%', "mData": "orgId"},
			{ "sTitle": "部门名称", "mData": "orgName"},
			{ "sTitle": "所属父级部门", "mData": "parentOrgName"},
			{ "sTitle": "部门主管", "mData": "nickName"},
			{ 
				"sTitle": "部门状态",
				"mDataProp" : "",
				"sDefaultContent" : "",
				"sVisible" : false,
				"fnCreatedCell" : function(nTd,sData, oData, iRow, iCol) {
					if(oData.isDisabled == 0){
						$(nTd).append("<label class='label label-success'>启用<label>");
					}else{
						$(nTd).append("<label class='label label-danger'>禁用<label>");
					}
				}
			}
        ];
        //渲染特殊的列(操作列)
        var aoColumnDefs =[];
        if(App.checkUserOperatorColumDisplay("organizationList", ["modifyOrg", "enableOrg", "deleteOrg"])){
            aoColumns.push({
            	"sTitle": "操作",
            	"mDataProp" : "",
            	"sDefaultContent" : "",
            	"sVisible" : false, 
            	"fnCreatedCell": function(nTd,sData, oData, iRow, iCol){
            		var params='';
            		if(App.checkUserOperRight("organizationList", "modifyOrg")){
            			$(nTd).append("<span class='btn btn-xs purple ajaxify' href='modifyOrgPage.html?orgId="+oData.orgId+"'><i class='fa fa-edit'></i> 编辑 </span></span>  ");
            		}
                    if(App.checkUserOperRight("organizationList", "deleteOrg")){
                        $(nTd).append("<span style='margin: 0 5px 0 5px'></span><span class='btn default btn-xs black' href='#confirmWin' data-toggle='modal' orgId="+oData.orgId+"><i class='fa fa-trash-o'></i> 删除 </span></span>  ");
                    }
                    if(App.checkUserOperRight("organizationList", "enableOrg")){
                        if(oData.isDisabled == 0){
                            $(nTd).append("<span style='margin: 0 5px 0 5px'></span><span class='btn btn-xs red' href='#confirmEnableWin' data-toggle='modal' orgId='"+ oData.orgId+ "'><i class='fa fa-lock'></i> 禁用 </span>");
                        }else{
                            $(nTd).append("<span style='margin: 0 5px 0 5px'></span><span class='btn btn-xs green' enable='ok' orgId='"+ oData.orgId+ "'><i class='fa fa-key'></i> 启用 </span>");
                        }
                    }
                }
            });
        }

        return {
            aoColumns : aoColumns,
            aoColumnDefs : aoColumnDefs
        };
    };
    
    //树初始化
    var initOrganizationTree = function () {
        menuJstree = $("#organizationTree").jstree({
            "core": {
            	"initially_open" : [ "topic_root" ],
                "themes": {
                	 "responsive": true
                },
                "check_callback": true,
                'data': {
                    type: "post",
                    'state' : { 'opened' : true, 'selected' : true },
                    'url': function (node) {
                        return 'queryOrganizationTreeData.html';
                    },
                    'data': function (node) {
                        return { 'parentId' : node.id };
                    }
                },
                callback: function (node, tree_obj) {
                }
            },
            "types": {
                "default": {
                    "icon": "fa fa-group icon-warning icon-lg"
                },
                "file": {
                    "icon": "fa fa-user icon-warning icon-lg"
                }
            },
            "state": { "key": "demo2" }
			}).on('changed.jstree',function(e, data) {
				if (data && data.action == "select_node") { // 选择树上的某个节点
				  var orgId = data.node.id;
                  //更新增加分组的链接值
                  $("#addOrg").attr("href", "addOrgPage.html?orgId="+orgId);
				  //调用初始化右侧表格的方法
                  ORG_OrganizationList.loadTableData(orgId);
                }
            }).on("loaded.jstree", function (e, data) {
        	menuJstree.jstree("open_node", 1);//初始化时打开树
            menuJstree.jstree("select_node", 1);
        }) ;
    };
    
    //加载datatable表格数据
    var loadTableData = function(orgId){
    	currtOrgId = orgId;
        if(oTable){
            oTable.fnReloadAjax();
            return;
        }
        var headerInfo = initTableHeaderInfo();
        oTable = $('#'+tableId).DataTable({
            "fnServerParams": function (aoData) {
                aoData.push({"name": "orgId", "value": currtOrgId});
            },
            "sAjaxSource": "queryOrganizationData.html",
            "sAjaxDataProp": "queryResult",
            "bFilter" : true,
            "bInfo": true,
            "bJQueryUI": false,
            "bLengthChange": true,
            "bPaginate": true,
            "bProcessing": true,
            "bSort": true,
            "bSortClasses": true,
            "bStateSave": true,
            "bAutoWidth":true,
            "bSortCellsTop": true,
            "iTabIndex": 1,
            "sServerMethod": "POST",
            "bServerSide": false,
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

    //初始化相关事件函数
    var initButEvent = function(){
        //触发相关赋值
        $("#confirmWin").on("show.bs.modal", function(e){
            var orgId = $(e.relatedTarget).attr("orgId");
            $("#orgId").val('').val(orgId);
        });

        //触发相关赋值
        $("#confirmEnableWin").on("show.bs.modal", function(e){
            var orgId = $(e.relatedTarget).attr("orgId");
            $("#orgId").val('').val(orgId);
        });

        //注册删除确认操作
        $("#deleteOrgBut").on("click", function(){
            var param = {orgId : $("#orgId").val()};
            $.ajax({
                type : "post",
                dataType : "json",
                url : "deleteOrg.html",
                data : param,
                success : function(data){
                    if(data.code==0){
                        $("#confirmWin").modal("hide");
                        menuJstree.jstree("refresh");
                        oTable.fnReloadAjax();
                        //ORG_OrganizationList.loadTableData(currtOrgId);
                    }
                }
            })
        });

        //注册禁用确认操作
        $("#disableOrgBut").on("click", function(){
            var param = {orgId : $("#orgId").val()};
            $.ajax({
                type : "POST",
                url : "enableOrg.html",
                data : param,
                success : function(data) {
                    $("#confirmEnableWin").modal("hide");
                    if(data.code ==0){
                        oTable.fnReloadAjax();   //刷新datatable列表
                    }else{
                        bootbox.alert("禁用失败");
                    }
                }
            });
        });

        //启用相关事件
        $("#"+tableId).delegate("span[enable='ok']", "click", function(){
            var orgId = $(this).attr("orgId");
            var param = {
                orgId : orgId
            };
            $.ajax({
                type : "post",
                dataType : "json",
                url : "enableOrg.html",
                data : param,
                success : function(data) {
                    if(data.code==0){
                        oTable.fnReloadAjax(); //刷新datatable列表
                    }else{
                        bootbox.alert("启动失败");
                    }
                }
            })
        });

        //注册点击查看详情事件
        $('#'+tableId).on('click', ' tbody td .row-details', function () {
            var nTr = $(this).parents('tr')[0];
            //获取隐藏域的orgId值
            var orgId = $(this).parents('tr').find("input:hidden").val();
            if (oTable.fnIsOpen(nTr)){
                /* 关闭 */
                $(this).addClass("row-details-close").removeClass("row-details-open");
                oTable.fnClose(nTr);
            }else{
                /* 打开 */
                $(this).addClass("row-details-open").removeClass("row-details-close");
                ORG_OrganizationList.fnFormatDetails(oTable, nTr, orgId);
            }
        });
    };

    //加载详情的主要内容
    var fnFormatDetails = function(oTable, nTr, orgId){
       $.ajax({
	   		 type : "post",
	   		 dataType : "json",
	   		 data : {orgId : orgId},
	   		 url : "queryOrgDetail.html",
	   		 success : function(data){
                var sOut = '<table>';
                if(data.code ==0){
                    var org = data.data;
                    sOut +='<tr>' +
                        '<td>架构名称:</td>' +
                        '<td>'+org.orgName+'</td></tr>';
                    sOut += '<tr><td>创建时间:</td><td>'+changeDateFormat(org.createTime)+'</td><td>更新时间:</td><td>'+changeDateFormat(org.updateTime)+'</td></tr>';
                }
	   	        sOut += '</table>';
	   	        oTable.fnOpen( nTr, sOut, 'details');
	   		 }
	   });
    };

    return {
        init: function () {
            initOrganizationTree(); //初始化加载jstree团队架构
            initButEvent();
        },
        loadTableData: loadTableData,
        fnFormatDetails: fnFormatDetails
    };
}();
