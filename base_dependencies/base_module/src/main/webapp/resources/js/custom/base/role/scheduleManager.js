var System_Schedule = function () {
    var tableId = "scheduleTable";
    var oTable = null;

    //初始化加载表格数据的表头定义
    var initTableHeaderInfo = function(){
        var aoColumns = [
            { "sTitle": "任务名称", "mData": "jobName"},
            { "sTitle": "任务类完整名称", "mData": "jobClass" },
            { "sTitle": "任务运行方法名称", "mData": "jobMethod" },
            { "sTitle": "任务运行周期表达式", "mData": "cronExpression" },
            { "sTitle": "是否允许并发执行",
              "mData": null,
                "sDefaultContent" : "",
                "sVisible" : false,
                "fnCreatedCell" : function(nTd,sData, oData, iRow, iCol) {
                    if(oData.concurrent == true){
                        $(nTd).append("<label class='label label-success'>允许<label>");
                    }else{
                        $(nTd).append("<label class='label label-danger'>不允许<label>");
                    }
                }
            },
            { "sTitle": "任务状态",
                "mData": null,
                "sDefaultContent" : "",
                "sVisible" : false,
                "fnCreatedCell" : function(nTd,sData, oData, iRow, iCol) {
                    if(oData.jobState == true){
                        $(nTd).append("<label class='label label-success'>运行中<label>");
                    }else{
                        $(nTd).append("<label class='label label-danger'>停止运行<label>");
                    }
                }
            },
            { "sTitle": "创建时间", "mData": "createTime" },
            { "sTitle": "更新时间", "mData": "updateTime" }
        ];
        //渲染特殊的列(操作列)
        var aoColumnDefs =[];
        if(App.checkUserOperatorColumDisplay("scheduleManager", ["launchSchedule", "modifySchedule", "deleteSchedule"])){
            aoColumns.push({ "sTitle": "操作", "mData": null });
            aoColumnDefs = [
                {
                    "aTargets" : [8],
                    "bSearchable": false,
                    "bSortable": false,
                    mRender : function(vale, type, rowData){
                        var  txt = "";
                        if(App.checkUserOperRight("scheduleManager", "launchSchedule")){
                            txt += '<span class="btn default btn-xs purple">';
                            if(rowData.jobState == true){
                                txt += '<i class="fa fa-lock"></i> 停止定时任务';
                            }else{
                                txt += '<i class="fa fa-key"></i> 启动定时任务';
                            }
                            txt +=     '</span>';
                        }
                        if(App.checkUserOperRight("scheduleManager", "modifySchedule")){
                            txt +=     '<span style="margin: 0 5px 0 5px"></span>';
                            txt +=     '<span class="btn default btn-xs purple" data-toggle="modal" href="#modifyScheduleWin"' +
                                ' jobName='+ rowData.jobName +' jobClass='+ rowData.jobClass +' jobMethod='+ rowData.jobMethod + ' jobState='+ rowData.jobState +
                                ' cronExpression="'+ rowData.cronExpression +'" concurrent='+ rowData.concurrent +' jobId='+rowData.jobId+'>';
                            txt +=     '<i class="fa fa-edit"></i> 编辑';
                            txt +=     '</span>';
                        }
                       /* if(App.checkUserOperRight("scheduleManager", "deleteSchedule")){
                            txt +=     '<span style="margin: 0 5px 0 5px"></span>';
                            txt +=     '<span class="btn default btn-xs black" data-toggle="modal" href="#confirmWin" roleId='+rowData.jobId+'>';
                            txt +=     '<i class="fa fa-trash-o"></i> 删除';
                            txt +=     '</span>';
                        }*/

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
            "sAjaxSource": "queryXRScheduleByPage.html",
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
            "bServerSide": false,
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

    //初始化滑动条
    var initSwitch = function(){
        console.log("=====================================");
        //$('.make-switch').bootstrapSwitch("destroy");
        //$('.make-switch').bootstrapSwitch("init");
        $('.make-switch').bootstrapSwitch("setOnLabel", '是');
        $('.make-switch').bootstrapSwitch("setOffLabel", '否');
        /*$('.make-switch').each(function(){
            var authorized = $(this).attr("authorized")==='true' ? true:false;
            $(this).bootstrapSwitch("setState", authorized);
        });*/
        $('.make-switch').on('switch-change', function (e, data) {
            /*var param = {
                "roleId": $("#configRoleId").val(),
                "menuId": $(e.currentTarget).attr("menuId"),
                "operId": $(e.currentTarget).attr("operId"),
                "authorized": data.value
            };
            $.ajax({
                type: "POST",
                data: param,
                url: "authorizedOperator.html",
                success: function (data) {
                    if (data.code == 0) {

                    }
                }
            });*/
        });
    };

    var initToolBar = function(){
        //触发相关赋值
        $("#confirmWin").on("show.bs.modal", function(e){
            var jobId = $(e.relatedTarget).attr("jobId");
            $("#jobId").val('').val(jobId);
        });

        //触发相关赋值
        $("#modifyScheduleWin").on("show.bs.modal", function(e){
            var clickSelf = $(e.relatedTarget);
            var jobId = clickSelf.attr("jobId");
            var jobName = clickSelf.attr("jobName");
            var jobClass = clickSelf.attr("jobClass");
            var jobMethod = clickSelf.attr("jobMethod");
            var cronExpression = clickSelf.attr("cronExpression");
            var concurrent = clickSelf.attr("concurrent");
            var jobState = clickSelf.attr("jobState");
            $("#jobId").val('').val(jobId);
            $("#jobName").val('').val(jobName);
            $("#jobClass").val('').val(jobClass);
            $("#jobMethod").val('').val(jobMethod);
            $("#cronExpression").val('').val(cronExpression);
            $("#concurrent").val('').val(concurrent);
            $("#jobState").val('').val(jobState);
        });

        //保存
        $("#saveScheduleBut").on("click", function(){
            if(!$("#scheduleAddForm").valid()){
                return;
            }
            var param = $("#scheduleAddForm").serialize();
            $.ajax({
                type: "post",
                data: param,
                url: 'addSchedule.html',
                success: function (data) {
                    if (data.code == 0) {
                    } else {

                    }
                    $("#addScheduleWin").modal("hide");
                    //oTable.fnDeleteRow();
                    if(oTable){
                        oTable.fnDestroy();
                    }
                    //调用初始化右侧表格的方法
                    System_Schedule.loadTableData();
                }
            });
        });

        var options = {
            errorElement: 'span',
            errorClass: 'help-block',
            focusInvalid: false,
            ignore: "",
            rules: {
                jobName: {
                    minlength: 2,
                    required: true
                },
                jobClass: {
                    minlength: 2,
                    required: true
                },
                jobMethod: {
                    minlength: 2,
                    required: true
                },
                cronExpression: {
                    minlength: 13,
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
        $("#scheduleAddForm").validate(options);
        //验证修改角色表单
        $("#scheduleModifyForm").validate(options);

        //修改
        $("#modifyScheduleBut").on("click", function(){
            if(!$("#scheduleModifyForm").valid()){
                return;
            }
            var param = $("#scheduleModifyForm").serialize();
            $.ajax({
                type: "post",
                data: param,
                url: 'modifySchedule.html',
                success: function (data) {
                    if (data.code == 0) {
                    } else {

                    }
                    $("#modifyScheduleWin").modal("hide");
                    if(oTable){
                        oTable.fnDestroy();
                    }
                    //调用初始化右侧表格的方法
                    System_Schedule.loadTableData();
                }
            });
        });

        //删除
        $("#deleteScheduleBut").on("click", function(){
            var param = {roleId : $("#jobId").val()};
            $.ajax({
                type: "post",
                data: param,
                url: 'deleteSchedule.html',
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
                    System_Schedule.loadTableData();
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