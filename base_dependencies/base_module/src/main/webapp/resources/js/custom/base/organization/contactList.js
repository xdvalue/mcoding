//员工通讯录
var ORG_ContactList = function () {
	var tableId = "memberTable";
	var oTable = null;
	
	//初始化加载表格数据的表头定义
    var initTableHeaderInfo = function(){
        var aoColumns = [
			{ 
				"sTitle": "",
				"mDataProp" : "",
				"sDefaultContent" : "",//此列默认值为""，以防数据中没有此值，DataTables加载数据的时候报错
				"sVisible" : false,
				"fnCreatedCell" : function(nTd,sData, oData, iRow, iCol) {
						$(nTd).append("<span class='row-details row-details-close'></span>");
						$(nTd).append("<input type='hidden' name='userId' value="+oData.userId+">");
				}
			},
			{ "sTitle": "登录账号", "mData": "loginName"},
			{ "sTitle": "真实姓名", "mData": "nickName"},
			{
				"sTitle": "用户状态",
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
        if(App.checkUserOperatorColumDisplay("employeeList", ["modifyEmp", "disableEmp"])){
            aoColumns.push({
            	"sTitle": "操作",
            	"mDataProp" : "",
            	"sDefaultContent" : "",
            	"sVisible" : false, 
            	"fnCreatedCell": function(nTd,sData, oData, iRow, iCol){
            		if(App.checkUserOperRight("rightManager", "distributionRole")){
            			$(nTd).append("<span class='btn default btn-xs purple selectRole' data-target='#selectRole' data-toggle='modal' href='navigateSelectRole?configUserId="+oData.userId+"'><i class='fa fa-edit'></i> 分配角色</span>");
            		}
            		if(App.checkUserOperRight("employeeList", "modifyEmp")){
            			$(nTd).append("<span style='margin: 0 5px 0 5px'><span class='btn btn-xs purple ajaxify' href='addEmpPage.html?editUserId="+oData.userId+"'><i class='fa fa-edit'></i> 编辑 </span>");
            		}
            		if(App.checkUserOperRight("employeeList", "modifyEmp")){
            			if(oData.isDisabled == 0){
                    		$(nTd).append("<span style='margin: 0 5px 0 5px'></span><span class='btn btn-xs red' href='#confirmWin' data-toggle='modal' userId='"+ oData.userId+ "'><i class='fa fa-lock'></i> 禁用 </span>");
                    	}else{
                    		$(nTd).append("<span style='margin: 0 5px 0 5px'></span><span class='btn btn-xs green' enable='ok' userId='"+ oData.userId+ "'><i class='fa fa-key'></i> 启用 </span>");
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
    
    
    //加载datatable表格数据
    var loadTableData = function(){
        if(oTable){
            oTable.fnReloadAjax();
            return;
        }
        var headerInfo = initTableHeaderInfo();
        oTable = $('#'+tableId).DataTable({
            "sAjaxSource": "queryUserByOrgIdData",
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

    //初始化界面相关事件
    var initPageEvent = function(){
        //触发相关赋值
        $("#confirmWin").on("show.bs.modal", function(e){
            var userId = $(e.relatedTarget).attr("userId");
            $("#userId1").val('').val(userId);
        });

        $("#management").on("show.bs.modal", function(e){
            var userId = $(e.relatedTarget).attr("userId");
            
           $.ajax({
        	   type : "post",
        	   dataType : "json",
        	   url : "queryUserDetail",
        	   data : {"userId" : userId},
        	   success : function(data) {
        		   var user = data.data;
        		 $("#nickName").val('').val(user.nickName);
        		 var entrytiem= changeDateFormat(user.entrytime);
        		 $("#entrytime").val('').val(entrytiem);
        		 $("#userstatus").val('').val(user.userstatus);
        		 $("#levelName").val('').val(user.levelName);
        		 $("#userId").val('').val(userId);
        	   }
           })
        });
        
        $("#userstatus1").change(function(){
          var userstatus2= $("#userstatus1").val();
          $("#userstatus").val('').val(userstatus2);
        });
        
        $("#levelId").click(function(){
        	var selectleve= $("#levelId").find("option:selected").text();
            $("#levelName").val('').val(selectleve);
          });
        
        
        $("#updateemple").on("click", function(){
            var userId=$("#userId").val();
            var userstatus=$("#userstatus").val();
            var levelId=$("#levelId").val();
            var requestURL = "updataemple.html";
            $.ajax({
                type: "POST",
                url: requestURL,
                data: {"userId" :userId,"userstatus" : userstatus,"levelId" : levelId},
                success: function (msg) {
                	 bootbox.alert("员工信息保存成功");
                	 $("#management").modal("hide");
                         oTable.fnReloadAjax();   
                }
            });
        });
        
      
        
        $("#disableEmpBut").on("click", function(){
            var param = {
                userId : $("#userId1").val()
            };
            $.ajax({
            	type : "POST",
                dataType : "json",
                url : "disableEmp",
                data : param,
                success : function(data) {
                    $("#confirmWin").modal("hide");
                    if(data && data.status=='00'){
                        oTable.fnReloadAjax();       //刷新datatable列表
                    }else{
                        bootbox.alert("禁用失败");
                    }
                }
            });
        });

        //启用相关事件
        $("#"+tableId).delegate("span[enable='ok']", "click", function(){
            var userId = $(this).attr("userId");
            var param = {
                userId : userId
            };
            $.ajax({
                type : "post",
                dataType : "json",
                url : "disableEmp",
                data : param,
                success : function(data) {
                    if(data && data.status=='00'){
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
    };

    //加载用户详情函数
    var fnFormatDetails = function(oTable, nTr, userId)
    {
       $.ajax({
	   		 type : "post",
	   		 dataType : "json",
	   		 data : {userId : userId},
	   		 url : "queryUserDetail",
	   		 success : function(data){
	   			 var sOut = $('<table>');
	   			 if(data.status != '00'){
	   				return oTable.fnOpen( nTr, sOut, 'details');
	   			 }
	   			 var user = data.data;
	   			 var innerHtml = 
	   				'<tr>' +
                      '<td>用户姓名:</td>' + '<td>'+user.nickName+'</td>' +
                      '<td>登录账号:</td>' + '<td>'+user.loginName+'</td>' +
                      '<td>手机号码:</td>' + '<td>'+(user.mobilePhone==null ? "" : user.mobilePhone) +'</td>' +
                      '<td>邮箱:</td>'+ '<td>'+(user.email==null ? "" : user.email) +'</td></tr>' +
                      '<tr><td>创建时间:</td><td>'+ moment(user.createTime).format('YYYY-MM-DD') +'</td><td>更新时间:</td><td>'+moment(user.updateTime).format('YYYY-MM-DD')+'</td></tr>';
	   			 sOut.append(innerHtml);
                 oTable.fnOpen( nTr, sOut, 'details');
	   		 }
	   	});
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
        init: function () {
            loadTableData();
            initPageEvent();
            initToolBar();
        },
        fnFormatDetails : fnFormatDetails
    };
}();

