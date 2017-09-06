
var System_MenuManager = function () {
	var oTable = null;
	var menuTreeId = "menuManagerTree";
	var oTableId = "menuOperateTab";
	var menuTree = null;

	//初始化加载表格数据的表头定义
    var initTableHeaderInfo = function(){
    	 var aoColumns = [
              { "sTitle": "操作名称", "mData": "operName","":"operName"},
              { "sTitle": "操作代码", "mData": "operCode" },
              { "sTitle": "操作请求地址", "mData": "operURL" },
              { "sTitle": "创建时间",  "mData": "createTime" },
              { "sTitle": "更新时间",  "mData": "updateTime" }
          ];
    	var aoColumnDefs =[];
	    if(App.checkUserOperatorColumDisplay("menuModuleConf", ["deleteOperator", "modifyOperator"])){
	    	aoColumns.push({
	    		"sTitle": "操作",
	    		"mDataProp" : "",
	    		"sDefaultContent" : "",
	    		"sVisible" : false, 
	    		"fnCreatedCell": function(nTd,sData, oData, iRow, iCol){
        		var params = oData.operId+','+oData.menuId+',"'+oData.operName+'","'+oData.operCode+'","'+oData.operURL+'"';
        		if(App.checkUserOperRight("menuModuleConf", "modifyOperator")){
    	    		 $(nTd).append("<a class='btn btn-xs purple' data-toggle='modal' href='#editBtn' onclick='System_MenuManager.loadOperationData("+params+");'><i class='fa fa-edit'></i> 编辑 </span></a>  ");
    	    	 }
    	    	 if(App.checkUserOperRight("menuModuleConf", "deleteOperator")){
    	    		 $(nTd).append("<a class='btn btn-xs red' href='#' onclick='System_MenuManager.deleteButton("+oData.operId+");'><i class='fa fa-trash-o'></i>删除 </a>");
    	    	 }
        	}
	      });
    	}
        return {
            aoColumns : aoColumns,
            aoColumnDefs : aoColumnDefs
        };
    };
        
    //加载右侧数据
    var loadTableData = function(menuId){
    	var headerInfo = initTableHeaderInfo();
        oTable = $('#'+oTableId).DataTable({
            "fnServerParams": function (aoData) {
                aoData.push({"name": "menuId", "value": menuId});
            },
            "sAjaxSource": "queryMenuOperatorList",
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
            "aoColumns": headerInfo.aoColumns,
            "aoColumnDefs" : headerInfo.aoColumnDefs,
            "sServerMethod": "POST",
            //"sPaginationType": "full_numbers",
            "bServerSide": false,
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

    //初始化右键菜单的栏目列表
    var initMenuItem = function(){
    	var menuItems = {};
		if(App.checkUserOperRight("menuModuleConf", "addMenu")){
    		menuItems.create = {
                    "separator_before": false,
                    "separator_after": true,
                    "_disabled": false,
                    "label": "新建菜单",
                    "action": function (data) {
                    /*	var inst = $.jstree.reference(data.reference),
                        obj = inst.get_node(data.reference);
                        var inst = $.jstree.reference(data.reference),
                        obj = inst.get_node(data.reference);
                        inst.create_node(obj, {}, "last", function (new_node) {
                            setTimeout(function () {
                                inst.edit(new_node);
                            }, 0);
                        }); */
                    	//只增加下一级
                    	
                    	 var inst = $.jstree.reference(data.reference),
                         obj = inst.get_node(data.reference);
                   //    inst.edit(obj);
                    	 $("#parentId").attr("value",obj.id);
                         $("#menuType").attr("value",obj.original.menuType);
                         changeType();              
                         	
                    }
                };
    	}
		if(App.checkUserOperRight("menuModuleConf", "modifyMenu")){
    		menuItems.rename = {
                    "separator_before": false,
                    "separator_after": false,
                    "_disabled": false,
                    "label": "修改菜单",
                    "action": function (data) {
                        var inst = $.jstree.reference(data.reference),
                            obj = inst.get_node(data.reference);
                      //    inst.edit(obj);
                        $("#parentId").attr("value",obj.parent);
                        $("#menuType").attr("value",obj.original.menuType);
                        $("#menuId").attr("value",obj.id);
                        $("#menuName").attr("value",obj.original.menuName);
                        $("#menuURL").attr("value",obj.original.menuURL);
                        $("#sortNo").attr("value",obj.original.sortNo);
                        $("#menuCode").attr("value",obj.original.menuCode);
                    	$("#editMenu").modal("show"); 
                    }
                };
    	}
		if(App.checkUserOperRight("menuModuleConf", "deleteMenu")){
    		menuItems.remove = {
                    "separator_before": false,
                    "icon": false,
                    "separator_after": false,
                    "_disabled": false,
                    "label": "删除菜单",
                    "action": function (data) {
                    	 var inst = $.jstree.reference(data.reference),
                          obj = inst.get_node(data.reference);
                    	  var id = obj.id;
                    	  /*   $.ajax({
                    			type : "POST",
                    			url : "./deleteMenu.html",
                    			data : {"menuId":id},
                    			success : function(data) {  
                    			},
                    			error : function(err) {
                    			}
                    		});*/
                    	
                    	if(confirm("是否删除菜单，按下确认后操作成功，否则点击取消。")){ 
                    		$.ajax({
                                type: "post",
                                data : {
                                	 "menuId":id
                    			},
                                url: 'deleteMenu',
                                success: function (data) {
                                    oTable.fnReloadAjax();//刷新datatable列表
                                    menuTree.jstree("refresh");//刷新左边树
                                }
                            });
                    		return true; 
                    		} 
                    		return false; 
                    }
                };
    	}
    	return menuItems;
    };

    //初始化树的上下文菜单
    var initTreeContextMenu = function(){
        $.jstree.defaults.contextmenu.items = function (o) {
    		if(App.checkUserOperatorColumDisplay("menuModuleConf", ["deleteMenu", "modifyMenu", "addMenu"])){
    			return initMenuItem();
    		}else{
    			return {};
    		}
        };
    };

    //菜单树初始化
    var initMenuTree = function () {
        initTreeContextMenu();
        
        
        //jstree树的核心方法，加载树，增加，编辑，重命名，删除节点等
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
                var parent = data.node.original.parentId;
                if(oTable){
                    oTable.fnDestroy();
                }
                $("#menuId2").attr("value",menuId);  
                if(parent!=""&&parent!=0){
                	 $("#edit").attr("style","display:block");
                }else{
                	 $("#edit").attr("style","display:none");
                }             
                //调用初始化右侧表格的方法
                System_MenuManager.loadTableData(menuId);
            }
        }).on('loaded.jstree',function(e, data) {
        	menuTree.jstree("open_node", 1);//初始化时打开树
        	System_MenuManager.loadTableData(null);
        });
    };

    //注册相关按钮事件
    var initBindEvent = function(){
        //删除按钮
        /*$("#deleteBut").on("click", function(){
         var id = $(e.relatedTarget).attr("operId");
         var menuId = $(e.relatedTarget).attr("menuId");
         $.ajax({
         type: "post",
         data: id,
         url: 'deleteOperator.html',
         success: function (data) {
         if(oTable){
         oTable.fnDestroy();
         }
         //调用初始化右侧表格的方法
         System_MenuManager.loadTableData(menuId);
         }
         });
         });*/

    	  var options = {
    		        errorElement: 'span',
    		        errorClass: 'help-block',
    		        focusInvalid: false,
    		        ignore: "",
    		        rules: {
    		        	operName: {
    		                required: true,
    		                maxlength: 120,
    		                minlength :1
    		            },
    		            operCode: {
    		                required: true,
    		                maxlength: 120,
    		                minlength :1
    		            },
    		            operURL: {
    		                required: true,
    		                maxlength: 120,
    		                minlength :1
    		            },
    		            menuName: {
    		                required: true,
    		                maxlength: 120,
    		                minlength :1
    		            },
    		            menuCode: {
  		                required: true,
    		                maxlength: 120,
    		                minlength :1
  		            },
  		            menuURL: {
  		                required: true,
    		                maxlength: 120,
    		                minlength :1
  		            },
    		            sortNo: {
    		                required: true,
    		                digits:true
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
      	
    	
    	
    	
    	
    	
    	
        //编辑按钮
        $("#edtiBtnBtn").on("click", function(){
        	$("#form").validate(options);
        	if(!$("#form").valid()){
    	        return false;
    	    }
/*            //验证部分
            var operName = $("#operName").val();
            var operCode= $("#operCode").val();
            var operURL= $("#operURL").val();
            if(operName==""||operCode==""||operURL==""){
                $("#checkMessage").html("<font style='color:red'>请确保必要信息填写完整后再提交</font>");
                return;
            }
*/
        	   var url1 ;
               var operId = $("#operId").val();
               var menuId = $("#menuId2").val();
               if(operId==null||operId==""){  //新增的情况
                   url1 ="addOperator";
               }else{  //更新的情况
                   url1="modifyOperator";
               }
            $.ajax({
                type : "POST",
                url : url1,
                data : $("#form").serialize(),
                success : function(data) {  //data是调用函数后返回的东西如果 data是一个对象 那么直接data.属性							拿出来 即可
                    $("#editBtn").modal("hide");
                    oTable.fnReloadAjax();//刷新datatable列表
                    menuTree.jstree("refresh");//刷新左边树
                    cleanForm('#form');
                },
                error : function(err) {
                    alert(err.msg);
                    cleanForm('#form');
                }
            });
            cleanForm('#form');
        });

        //编辑菜单
        $("#edtiMenuBtn").on("click", function(){
        	  var url1 ;
              var menuId = $("#menuId").val();
              $("#form2").validate(options);
          	if(!$("#form2").valid()){
      	        return false;
      	    }
             /* //验证部分
              var menuName = $("#menuName").val();
              var sortNo= $("#sortNo").val();
              var menuCode= $("#menuCode").val();
              var menuURL= $("#menuURL").val();
              if(menuName==""||sortNo==""||menuCode==""||menuURL==""){
                  $("#checkMsg").html("<font style='color:red'>请确保必要信息填写完整后再提交</font>");
                  return;
              }*/

            if(menuId==null||menuId==""){  //新建情况
                url1 ="addMenu";
            }else{  //更新情况
                url1="modifyMenu";
            }
            $.ajax({
                type : "POST",
                url : url1,
                data : $("#form2").serialize(),
                success : function(data) {  //data是调用函数后返回的东                      					如果 data是一个对象 那么直接data.属性							拿出来 即可
                    $("#editMenu").modal("hide");
                    oTable.fnReloadAjax();//刷新datatable列表
                    menuTree.jstree("refresh");//刷新左边树
                    //调用初始化右侧表格的方法
                    //System_MenuManager.loadTableData(menuId);
                    cleanForm('#form2');

                },
                error : function(err) {
                    alert(err.msg);
                    cleanForm('#form2');
                }

            });
            cleanForm('#form2');

        });
    };

    //清除编辑相关原来的值
    var cleanForm = function(formname){
        $(':input',formname)
            .not('#menuId2')
            .not('#parent')
            .not(':button, :submit, :reset, :hidden')
            .val('')
            .removeAttr('checked')
            .removeAttr('selected');
    };

    //点击编辑时，加载按钮数据
    var loadOperationData = function(operId,menuId,operName,operCode,operURL){
        $("#operId").attr("value",operId);
        $("#menuId2").attr("value",menuId);
        $("#operName").attr("value",operName);
        $("#operCode").attr("value",operCode);
        $("#operURL").attr("value",operURL);
        /* $("#operName").val('').val(operName);
         $("#operCode").val('').val(operCode);
         $("#operURL").val('').val(operURL); */
    };

    //删除按钮调用函数
    var deleteButton = function(id){
        if(confirm("是否删除按钮，按下确认后操作成功，否则点击取消。")){
            $.ajax({
                type: "post",
                data : {
                    "id" : id
                },
                url: 'deleteOperator',
                success: function (data) {
                    oTable.fnReloadAjax();//刷新datatable列表
                }
            });
            return true;
        }
        return false;
    };
    
    return {
        init : function () {
            initMenuTree();
            initBindEvent();
        },
        loadTableData : loadTableData,
        cleanForm : cleanForm,
        deleteButton : deleteButton,
        loadOperationData : loadOperationData
    };
}();





