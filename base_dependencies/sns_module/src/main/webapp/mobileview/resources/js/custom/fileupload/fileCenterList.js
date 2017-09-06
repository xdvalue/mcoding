//文件共享中心
var File_ShareManager = function () {
	var tableId = "shareFileTable";
	var oTable = null;

	//初始化加载表格数据的表头定义
    var initTableHeaderInfo = function(){
        var aoColumns = [
			{ "sTitle": "文件标题",
              "mDataProp": "",
              "sDefaultContent" : "",
              "fnCreatedCell": function(nTd, sData, oData, iRow, iCol) {
                      $(nTd).append("<a target='_blank' href='"+oData.upFullpath+"'>"+oData.upTitle+"</a>");
              }
            },
			{ "sTitle": "文件类型",
              "mDataProp": "",
              "sDefaultContent" : "",
              "fnCreatedCell": function(nTd, sData, oData, iRow, iCol) {
                  if(oData.upType == "file"){
                      $(nTd).append("<label class='label label-danger'>文件类型</label>");
                  }else if(oData.upType == "image"){
                      $(nTd).append("<label class='label label-success'>图片类型<label>");
                  }else{
                      $(nTd).append("<label class='label label-info'>其他文件类型</label>");
                  }
              }
            },
            { "sTitle": "文件上传时间",
                "mDataProp": "",
                "sDefaultContent" : "",
                "fnCreatedCell": function(nTd, sData, oData, iRow, iCol) {
                        $(nTd).append(changeDateFormat(oData.upDatatime));
                }
              }
        ];
        //渲染特殊的列(操作列)
        /*var aoColumnDefs =[];
        if(App.checkUserOperatorColumDisplay("templateManager", ["confTemplate", "modifyTemplate", "deleteTemplate"])){
            aoColumns.push({
                "sTitle": "操作",
                "mDataProp" : "",
                "bSortable": false,
                "sDefaultContent" : "",
                "sVisible" : false,
                "fnCreatedCell": function(nTd, sData, oData, iRow, iCol){
                    if(App.checkUserOperRight("templateManager", "confTemplate")){
                        $(nTd).append('<span class="btn default btn-xs purple ajaxify" href="navigateProjTemplateConfig.html?teplId='+oData.teplId +'">' +
                            '<i class="fa fa-key"></i> 配置模板 </span>');
                    }
                    if(App.checkUserOperRight("templateManager", "modifyTemplate")){
                        $(nTd).append('<span style="margin: 0 5px 0 5px"></span>');
                        $(nTd).append("<span class='btn default btn-xs purple' data-target='#addTemplateDialog' data-toggle='modal' " +
                            "teplId='"+ oData.teplId +"' teplTitle='"+ oData.teplTitle +"' teplName='" + oData.teplName + "' teplType='" + oData.teplType +"'>" +
                            "<i class='fa fa-edit'></i> 编辑 </span>");
                    }
                    if(App.checkUserOperRight("templateManager", "deleteTemplate")){
                        $(nTd).append('<span style="margin: 0 5px 0 5px"></span>');
                        $(nTd).append("<span class='btn default btn-xs black' enable='ok' " + "teplId='"+ oData.teplId +"'>" +
                            "<i class='fa fa-trash-o'></i> 删除 </span>");
                    }
                }
            });
        }*/

        return {
            aoColumns : aoColumns
            //aoColumnDefs : aoColumnDefs
        };
    };

    //加载datatable表格数据
    var loadTableData = function(){
        if(oTable){
            oTable.fnDestroy();
            $('#'+tableId).find("thead").remove();
        }
        var headerInfo = initTableHeaderInfo();
        oTable = $('#'+tableId).DataTable({
            "sAjaxSource": "queryAllShareFileByPage.html",
            "sAjaxDataProp": "queryResult",
            "bFilter" : true,
            "bInfo": true,
            "bJQueryUI": false,
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
            "aLengthMenu": [
                [10, 20, 30, 40, -1],
                [10, 20, 30, 40, 50]
            ],
            "iDisplayLength": 10,
            "oLanguage": {
            	"sProcessing" : "努力加载中...",
                "sLengthMenu": "显示 _MENU_ 条记录",
                "sInfoEmpty" : "搜索结果为0条记录",
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

    //初始化相关事件
    /*var initButEvent = function(){
        //触发相关赋值
        $("#addTemplateDialog").on("show.bs.modal", function(e){
            var clickSelf = $(e.relatedTarget);
            var formTitle = $("#FormTitle").text("增加项目模板");
            var teplId = clickSelf.attr("teplId");
            var teplTitle = clickSelf.attr("teplTitle");
            var teplName = clickSelf.attr("teplName");
            var teplType = clickSelf.attr("teplType");
            $("#teplId").val('').val(teplId);
            $("#teplTitle").val('').val(teplTitle);
            $("#teplName").val('').val(teplName);
            $("#teplType").val('').val(teplType);
            if(teplId){
                formTitle.text("修改项目模板");
            }
        });

        //保存分组
        $("#FormSubmit").on("click", function(){
            var form = $("#FormBody");
            if(!form.valid()){
                return;
            }
            var param = form.serialize();
            var requestURL = "addProjTemplate.html";

            if($("#teplId").val()!=''){
                requestURL = "modifyProjTemplate.html";
            }
            $.ajax({
                type : "post",
                dataType : "json",
                url : requestURL,
                data : param,
                success : function(data) {
                    if(data.code==0){
                        oTable.fnReloadAjax();
                        $("#addTemplateDialog").modal("hide");
                    }else{
                        bootbox.alert("增加分组失败");
                    }
                }
            });
        });

        //注册删除事件
        $("#"+tableId).delegate("span[enable='ok']", "click", function(){
            var teplId = $(this).attr("teplId");
            var param = {
                teplId : teplId
            };
            $.ajax({
                type : "post",
                dataType : "json",
                url : "deleteProjTemplate.html",
                data : param,
                success : function(data) {
                    if(data.code==0){
                        oTable.fnReloadAjax();
                    }else{
                        bootbox.alert("删除失败");
                    }
                }
            });
        });

        //绑定校验
        var options = {
            errorElement: 'span',
            errorClass: 'help-block',
            focusInvalid: false,
            ignore: "",
            rules: {
                teplTitle: {
                    minlength: 2,
                    maxlength: 24,
                    required: true
                },
                teplName: {
                    minlength: 2,
                    maxlength: 24,
                    required: true
                },
                teplType: {
                    required: true
                }
            },
            highlight: function (element) {
                $(element).closest('.form-group').addClass('has-error');
            },
            unhighlight: function (element) {
                $(element).closest('.form-group').removeClass('has-error');
            },
            success: function (label) {
                label.closest('.form-group').removeClass('has-error');
            }
        };

        //验证增加角色表单
        $("#FormBody").validate(options);
    };*/

    return {
        init: function () {
            loadTableData();
            //initButEvent();
        },
        loadTableData: loadTableData
    };
}();


//文件共享中心
var File_myManager = function () {
	var tableId = "myFileTable";
	var oTable = null;
	
	//初始化加载表格数据的表头定义
	var initTableHeaderInfo = function(){
		var aoColumns = [
		                 { "sTitle": "文件标题",
		                	 "mDataProp": "",
		                	 "sDefaultContent" : "",
		                	 "fnCreatedCell": function(nTd, sData, oData, iRow, iCol) {
		                		 $(nTd).append("<a href='"+oData.upFullpath+"'>"+oData.upTitle+"</a>");
		                	 }
		                 },
		                 { "sTitle": "上传时间", "mData": "upDatatime"},
		                 { "sTitle": "文件类型",
		                	 "mDataProp": "",
		                	 "sDefaultContent" : "",
		                	 "fnCreatedCell": function(nTd, sData, oData, iRow, iCol) {
		                		 if(oData.upType == "file"){
		                			 $(nTd).append("<label class='label label-danger'>文件</label>");
		                		 }else if(oData.upType == "image"){
		                			 $(nTd).append("<label class='label label-success'>图片<label>");
		                		 }else{
		                			 $(nTd).append("<label class='label label-info'>其他文件</label>");
		                		 }
		                	 }
		                 }
		                 ];
		return {
			aoColumns : aoColumns
			//aoColumnDefs : aoColumnDefs
		};
	};
	
	//加载datatable表格数据
	var loadTableData = function(){
		if(oTable){
			oTable.fnDestroy();
			$('#'+tableId).find("thead").remove();
		}
		var headerInfo = initTableHeaderInfo();
		oTable = $('#'+tableId).DataTable({
			"sAjaxSource": "queryfileUploadData.html",
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
            "iDisplayLength": 20,
            "oLanguage": {
            	"sProcessing" : "努力加载中...",
                "sLengthMenu": "显示 _MENU_ 条记录",
                "sInfoEmpty" : "搜索结果为0条记录",
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
	
	return {
		init: function () {
			loadTableData();
		},
		loadTableData: loadTableData
	};
}();
