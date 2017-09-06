var Role_ConfigManager = function () {
    var menuTreeId = "configMenuManagerTree";
    var oTableId = "configOperateTab";
    var oTable = null;
    var menuTree = null;
    //加载右侧数据
    var loadTableData = function(roleId ,menuId){
        oTable = $('#'+oTableId).DataTable({
            "fnServerParams": function (aoData) {
                aoData.push({"name": "roleId", "value": roleId});
                aoData.push({"name": "menuId", "value": menuId});
            },
            "sAjaxSource": "queryRoleOperatorList",
            "sAjaxDataProp": "queryResult",
            "bFilter" : false,
            "bInfo": false,
            "bJQueryUI": false,
            "bLengthChange": false,
            "bPaginate": false,
            "bProcessing": true,
            "bSort": false,
            "bSortClasses": true,
            "bStateSave": true,
            "bAutoWidth":true,
            "bSortCellsTop": false,
            "iTabIndex": 1,
            "sServerMethod": "POST",
            "bServerSide": false,
            fnInitComplete : initSwitch,
            "aoColumns": [
                { "sTitle": "菜单栏目名称", "mData": "menuName"},
                { "sTitle": "操作名称", "mData": "operName"},
                { "sTitle": "操作代码", "mData": "operCode" },
                { "sTitle": "是否授权",  "mData": null }
            ],
            "aoColumnDefs" : [
                {
                    "aTargets" : [3],
                    "bSearchable": false,
                    "bSortable": false,
                    mRender : function(vale, type, rowData){
                        var txt = '<input type="checkbox" authorized="'+ rowData.authorized +'" operId="'+ rowData.operId +'" menuId="'+ rowData.menuId +'"class="make-switch" checked data-on="info" data-off="success">';
                        return txt;
                    }
                }
            ],
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

    //初始化滑动条
    var initSwitch = function(){
        $('.make-switch').bootstrapSwitch("init");
        $('.make-switch').bootstrapSwitch("setOnLabel", '是');
        $('.make-switch').bootstrapSwitch("setOffLabel", '否');
        $('.make-switch').each(function(){
            var authorized = $(this).attr("authorized")==='true' ? true:false;
            $(this).bootstrapSwitch("setState", authorized);
        });
        $('.make-switch').on('switch-change', function (e, data) {
            var param = {
                "roleId": $("#configRoleId").val(),
                "menuId": $(e.currentTarget).attr("menuId"),
                "operId": $(e.currentTarget).attr("operId"),
                "authorized": data.value
            };
            $.ajax({
                type: "POST",
                data: param,
                url: "authorizedOperator",
                success: function (data) {
                    if (data.status == '00') {

                    }
                }
            });
        });
    };

    //菜单树初始化
    var initMenuTree = function () {
        menuTree = $("#"+menuTreeId).jstree({
            "core": {
                "themes": {
                    "responsive": true
                },
                "check_callback": true,
                'data': {
                    type: "post",
                    'state' : { 'opened' : true, 'selected' : true },
                    'contentType':'application/json',
                    'url': function (node) {
                        return 'queryMenuTreeData';
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
            "state": { "key": "demo2" },
            "plugins" : ["contextmenu", "types"]
        }).on('changed.jstree',function(e, data) {
            if (data && data.action == "select_node") { //选择树上的某个节点
                var menuId = data.node.id;
                var roleId = $("#configRoleId").val();
                if(oTable){
                    oTable.fnDestroy();
                }
                //调用初始化右侧表格的方法
                Role_ConfigManager.loadTableData(roleId, menuId);
            }
        }).on("loaded.jstree", function (e, data) {
            menuTree.jstree("open_node", 1);
            menuTree.jstree("select_node", 1);
        });
    };

    return {
        init : function () {
            initMenuTree();
        },
        loadTableData : loadTableData
    };
}();