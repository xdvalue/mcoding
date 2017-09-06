var System_Role = function () {
    var tableId = "roleTable";
    var oTable = null;

    //初始化加载表格数据的表头定义
    var initTableHeaderInfo = function(){
        var aoColumns = [
            { "sTitle": "角色名称", "mData": "roleName"},
            { "sTitle": "角色代号", "mData": "roleCode" },
            { "sTitle": "角色创建者", "mData": "nickName" },
            { "sTitle": "创建时间", "mData": "createTime" },
            { "sTitle": "更新时间", "mData": "updateTime" }
        ];
        //渲染特殊的列(操作列)
        var aoColumnDefs =[];
        if(App.checkUserOperatorColumDisplay("roleManager", ["distributionRightView", "modifyRole", "deleteRole"])){
            aoColumns.push({ "sTitle": "操作", "mData": null });
            aoColumnDefs = [
                {
                    "aTargets" : [5],
                    "bSearchable": false,
                    "bSortable": false,
                    mRender : function(vale, type, rowData){
                        var  txt = "";
                        var loginUserId = App.getLoginUserId();
                        if(!App.isManagerLoginUser() && loginUserId != rowData.createUserId){
                          return txt;
                        }
                        if(App.checkUserOperRight("roleManager", "distributionRightView")){
                            txt += '<span class="btn default btn-xs purple ajaxify" href="distributionRightsView.html?roleId='+rowData.roleId+'">';
                            txt +=     '<i class="fa fa-key"></i> 权限配置';
                            txt +=     '</span>';
                        }
                        if(App.checkUserOperRight("roleManager", "modifyRole")){
                            if(rowData.roleId != "1"){
                                txt +=     '<span style="margin: 0 5px 0 5px"></span>';
                                txt +=     '<span class="btn default btn-xs purple" data-toggle="modal" href="#modifyRoleWin"' +
                                    'roleName='+ rowData.roleName +' roleCode='+ rowData.roleCode +' roleId='+rowData.roleId+'>';
                                txt +=     '<i class="fa fa-edit"></i> 编辑';
                                txt +=     '</span>';
                            }
                        }
                        if(App.checkUserOperRight("roleManager", "deleteRole")){
                            if(rowData.roleId != "1"){
                                txt +=     '<span style="margin: 0 5px 0 5px"></span>';
                                txt +=     '<span class="btn default btn-xs black" data-toggle="modal" href="#confirmWin" roleId='+rowData.roleId+'>';
                                txt +=     '<i class="fa fa-trash-o"></i> 删除';
                                txt +=     '</span>';
                            }
                        }
                        return txt;
                    }
                }
            ]
        }

        return {
            aoColumns : aoColumns,
            aoColumnDefs : aoColumnDefs
        };
    };

    //加载表格数据
    var loadTableData = function(){
        var headerInfo = initTableHeaderInfo();
        oTable = $('#'+tableId).DataTable({
            "sAjaxSource": "queryAllRoleByPage",
            "sAjaxDataProp": "queryResult",
            "bFilter" : false,
            "bInfo": true,
            "bJQueryUI": false,
            "bLengthChange": false,
            "bPaginate": true,
            "bProcessing": true,
            "bSort": false,
            "bSortClasses": true,
            "bStateSave": true,
            "bAutoWidth":true,
            "bSortCellsTop": true,
            "iTabIndex": 1,
            "sServerMethod": "POST",
            //"sPaginationType": "full_numbers",
            "bServerSide": true,
            "aoColumns": headerInfo.aoColumns,
            "aoColumnDefs" : headerInfo.aoColumnDefs,
            "iDisplayLength": 10,
            "oLanguage": {
                "sLengthMenu": "显示 _MENU_ 条记录",
                "sInfo": "当前显示 _START_ 到 _END_ 总共 _TOTAL_ 条记录",
                "sInfoEmpty" : "搜索结果为0条记录",
                "sZeroRecords" : "当前没有记录",
                "sSearch" : "搜索：",
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
        $("#addRoleWin").on("show.bs.modal", function(e){
            $(this).find("input").val('');
        });

        //触发相关赋值
        $("#confirmWin").on("show.bs.modal", function(e){
            var roleId = $(e.relatedTarget).attr("roleId");
            $("#roleId").val('').val(roleId);
        });

        //触发相关赋值
        $("#modifyRoleWin").on("show.bs.modal", function(e){
            var clickSelf = $(e.relatedTarget);
            var roleId = clickSelf.attr("roleId");
            var roleName = clickSelf.attr("roleName");
            var roleCode = clickSelf.attr("roleCode");
            $("#roleId").val('').val(roleId);
            $("#roleName").val('').val(roleName);
            $("#roleCode").val('').val(roleCode);
        });

        //保存
        $("#saveRoleBut").on("click", function(){
            if(!$("#roleAddForm").valid()){
                return;
            }
            var param = $("#roleAddForm").serialize();
            $.ajax({
                type: "post",
                data: param,
                url: 'addRole',
                success: function (data) {
                	if(!data || !data.status || data.status != '00'){
                		alert('添加失败');
                		return;
                	}
                	
                    $("#addRoleWin").modal("hide");
                    //oTable.fnDeleteRow();
                    if(oTable){
                        oTable.fnDestroy();
                    }
                    //调用初始化右侧表格的方法
                    System_Role.loadTableData();
                }
            });
        });

        var options = {
            errorElement: 'span',
            errorClass: 'help-block',
            focusInvalid: false,
            ignore: "",
            rules: {
                roleName: {
                    minlength: 2,
                    required: true
                },
                roleCode: {
                    minlength: 2,
                    required: true
                }
            },
            highlight: function (element) {
                $(element).closest('.form-group').addClass('has-error');
            },
            unhighlight: function (element) {
                $(element)
                    .closest('.form-group').removeClass('has-error');
            },
            success: function (label) {
                label.closest('.form-group').removeClass('has-error');
            }
        }

        //验证增加角色表单
        $("#roleAddForm").validate(options);
        //验证修改角色表单
        $("#roleModifyForm").validate(options);

        //修改
        $("#modifyRoleBut").on("click", function(){
            if(!$("#roleModifyForm").valid()){
                return;
            }
            var param = $("#roleModifyForm").serialize();
            $.ajax({
                type: "post",
                data: param,
                url: 'modifyRole',
                success: function (data) {
                    if (!data || !data.status || data.status != '00') {
                    	alert('修改失败');
                    	return; 
                    } 
                    $("#modifyRoleWin").modal("hide");
                    if(oTable){
                        oTable.fnDestroy();
                    }
                    //调用初始化右侧表格的方法
                    System_Role.loadTableData();
                }
            });
        });

        //删除
        $("#deleteRoleBut").on("click", function(){
            var param = {roleId : $("#roleId").val()};
            $.ajax({
                type: "post",
                data: param,
                url: 'deleteRole',
                success: function (data) {
                    if (!data || !data.status || data.status != '00') {
                    	alert('删除失败');
                    	return;
                    } 
                    $("#confirmWin").modal("hide");
                    //oTable.fnDeleteRow();
                    if(oTable){
                        oTable.fnDestroy();
                    }
                    //调用初始化右侧表格的方法
                    System_Role.loadTableData();
                }
            });
        });
    };

    return {
        init: function () {
           loadTableData();
           initToolBar();
        },
        loadTableData:loadTableData
    };
}();