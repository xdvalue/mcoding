var System_RightManager = function () {
    var groupTreeId = "groupManagerTree";
    var oTableId = "memberOperateTab";
    var oTable = null;
    var groupTree = null;

    //初始化加载表格数据的表头定义
    var initTableHeaderInfo = function(){
        var aoColumns = [
            { "sTitle": "登陆账号", "mData": "loginName"},
            { "sTitle": "用户名称", "mData": "nickName" },
            { "sTitle": "手机号", "mData": "mobilePhone" },
            { "sTitle": "邮箱", "mData": "email" },
            { "sTitle": "创建时间",  "mData": "createTime" },
            { "sTitle": "更新时间",  "mData": "updateTime" }
        ];
        //渲染特殊的列(操作列)
        var aoColumnDefs =[];
        if(App.checkUserOperatorColumDisplay("rightManager", ["distributionRole", "userRightView"])){
            aoColumns.push({ "sTitle": "操作", "mData": null });
            aoColumnDefs = [
                {
                    "aTargets" : [6],
                    "bSearchable": false,
                    "bSortable": false,
                    mRender : function(vale, type, rowData){
                        var  txt = "";
                        if(App.checkUserOperRight("rightManager", "distributionRole")){
                            txt += '<span class="btn default btn-xs purple selectRole" data-target="#selectRole" ' +
                                'data-toggle="modal" href="navigateSelectRole?configUserId='+rowData.userId+'">';
                            txt +=     '<i class="fa fa-edit"></i> 分配角色';
                            txt +=     '</span>';
                        }
                        if(App.checkUserOperRight("rightManager", "userRightView")){
                           /*  txt +=     '<span style="margin: 0 5px 0 5px"></span>';
                             txt +=     '<span class="btn default btn-xs blue" data-target="#lookUserRightBut" ' +
                                 'data-toggle="modal" href="lookUserRight.html?configUserId='+rowData.userId+'">';
                             txt +=     '<i class="fa fa-eye"></i> 查看权限';
                             txt +=     '</span>';*/
                        }
                        return txt;
                    }
                }
            ];
        }
        return {
            aoColumns : aoColumns,
            aoColumnDefs : aoColumnDefs
        };
    };

    //加载右侧数据
    var loadTableData = function(orgId){
        var headerInfo = initTableHeaderInfo();
        oTable = $('#'+oTableId).DataTable({
    		"fnServerParams": function (aoData) {
    			if(orgId!=1){
    			 aoData.push({"name": "orgId", "value": orgId});
    			}
    		},    		
            "sAjaxSource": "queryUserByOrgIdData.html", 
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
            "bServerSide": true,
            "aoColumns": headerInfo.aoColumns,
            "aoColumnDefs" : headerInfo.aoColumnDefs,
            "aLengthMenu": [
                [10, 20, 30, 40, -1],
                [10, 20, 30, 40, 50]
            ],
            "iDisplayLength": 10,
            "oLanguage": {
                "sLengthMenu": "显示 _MENU_ 条记录",
                "sInfo": "当前显示 _START_ 到 _END_ 总共 _TOTAL_ 条记录",
                "sInfoEmpty" : "搜索结果为0条记录",
                "sZeroRecords" : "当前没有记录",
                "sSearch" : "搜索：",
                "oPaginate": {
                    "sPrevious": "Prev",
                    "sNext": "Next"
                }

            }
        });

    };

    //菜单树初始化
    var initGroupTree = function () {
        //jstree树的核心方法，加载树，增加，编辑，重命名，删除节点等
        groupTree = $("#"+groupTreeId).jstree({
            "core": {
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
        }).on('changed.jstree', function(e, data) {
            if (data && data.action == "select_node") { //选择树上的某个节点
                var orgId = data.node.id;
                if(oTable){
                    oTable.fnDestroy();
                }
                //调用初始化右侧表格的方法
                System_RightManager.loadTableData(orgId);
            }
        }).on("loaded.jstree", function (e, data) {
            var tem, treeData = data.instance._model.data;
            for(tem in treeData){
                var firstNode = treeData[tem];
                groupTree.jstree("open_node", firstNode.id);
                groupTree.jstree("select_node", firstNode.id);
                break;
            }
        }) ;


    };

    var initToolBar = function(){
        $("#selectRole").on("hidden.bs.modal", function() {
            $(this).removeData("bs.modal");
        }).on("show.bs.modal", function() {
            $(this).find("div[loadBar='ok']").hide();
        });
        $("#selectRole").delegate("button[id='saveRoleList']", "click", function(){
            var roleIds = $("#mySelect").val();
            if(roleIds){
                roleIds = $("#mySelect").val().join(",");
            }else{
                roleIds="";
            }
            var param = {
                userId : $("#userId").val(),
                roleIds : roleIds
            };
            $.ajax({
                type: "post",
                data: param,
                url: 'addUserRoleList',
                success: function (data) {
                    if (!data || !data.status || data.status != '00') {
                    	alert('添加失败');
                    	return;
                    } 
                    $("#selectRole").modal("hide");
                }
            });
        });

    };

    return {
        init : function () {
            initGroupTree();
            initToolBar();
        },
        loadTableData : loadTableData
    };
}();