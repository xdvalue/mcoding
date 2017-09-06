//员工通讯录
var memberPoint_memberPointList = function () {
	var tableId = "memberPointTable";
	var oTable = null;
	
	//初始化加载表格数据的表头定义
    var initTableHeaderInfo = function(){
        var aoColumns = [
			{ "sTitle": "编号", "sWidth" : "1%", "mData": "memberPointId"},
			{ "sTitle": "手机号码", "sWidth" : "5%", "mData": "mobilePhone"},
			{ "sTitle": "积分值", "sWidth" : "5%", "mData": "points"},
			{ "sTitle": "防伪码", "sWidth" : "5%", "mData": "fakeCheckCode"},
			{ "sTitle": "产品码", "sWidth" : "5%", "mData": "relatedTransactionNo"},
			{
            	"sTitle": "积分类型",
            	"sWidth" : "5%",
            	"mDataProp" : "",
				"sDefaultContent" : "",
            	"sVisible" : false,
            	"fnCreatedCell" : function(nTd, sData, oData, iRow, iCol) {
            		if(oData.pointsType=="A"){
            			$(nTd).append("<label class='label label-info'>防伪码积分</label>");
            		}else if(oData.pointsType=="B"){
            			$(nTd).append("<label class='label label-info'>完善资料奖励积分</label>");
            		}else if(oData.pointsType=="C"){
            			$(nTd).append("<label class='label label-info'>签到积分</label>");
            		}else{
            			$(nTd).append("<label class='label label-info'>其他积分</label>");
            		}
            	}
            },
            {
            	"sTitle": "品牌",
            	"sWidth" : "5%",
            	"mDataProp" : "",
            	"sDefaultContent" : "",
            	"sVisible" : false,
            	"fnCreatedCell" : function(nTd, sData, oData, iRow, iCol) {
            		if(oData.brandCode=="MRMJ"){
            			$(nTd).append("<label class='label label-success'>每日每加</label>");
            		}else if(oData.brandCode=="JLD"){
            			$(nTd).append("<label class='label label-danger'>健乐多</label>");
            		}else{
            			$(nTd).append("<label class='label label-info'>暂无</label>");
            		}
            	}
            },
            {
            	"sTitle": "积分时间",
            	"sWidth" : "5%",
            	"mDataProp" : "",
				"sDefaultContent" : "",
            	"sVisible" : false,
            	"fnCreatedCell" : function(nTd, sData, oData, iRow, iCol) {
            			$(nTd).append(changeDateFormat(oData.addDate));
            	}
            }
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
            		if(App.checkUserOperRight("memberList", "deleteMemberPoint")){
            			$(nTd).append("<span style='margin: 0 5px 0 5px'><span class='btn default btn-xs black' data-toggle='modal' href='#confirmWin' id="+oData.fakeCheckCode+"><i class='fa fa-trash-o'></i> 删除 </span>");
            		}
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
        		aoData.push({"name": "mobilePhone", "value": $("#mobilePhone").val()});
        		aoData.push({"name": "pointsType", "value": $("#pointsType").val()});
        		aoData.push({"name": "fakeCheckCode", "value": $("#fakeCheckCode").val()});
        		aoData.push({"name": "brandCode", "value": $("#brandCode").val()});
        	},
            "sAjaxSource": "queryMemberPointData.html",
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
    
    $('#btnSearch').click(function(){
    	oTable.fnDestroy();
    	memberPoint_memberPointList.loadTableData();
     });
    
    $('#btnReset').click(function(){
		document.getElementById("mobilePhone").value="";	  	
		document.getElementById("fullName").value="";	  	
		document.getElementById("fakeCheckCode").value="";	  	
		document.getElementById("brandCode").value="";	  	
    });

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
    };

    //加载用户详情函数
    var fnFormatDetails = function(oTable, nTr, userId)
    {
       $.ajax({
	   		 type : "post",
	   		 dataType : "json",
	   		 data : {userId : userId},
	   		 url : "queryUserDetail.html",
	   		 success : function(data){
                 var sOut = '<table>';
                 if(data.code ==0){
                     var user = data.data;
                     sOut +='<tr>' +
                         '<td>用户姓名:</td>' +
                         '<td>'+user.nickName+'</td>' +
                         '<td>登录账号:</td>' +
                         '<td>'+user.loginName+'</td>' +
                         '<td>手机号码:</td>' +
                         '<td>'+(user.mobilePhone==null ? "" : user.mobilePhone) +'</td>' +
                         '<td>邮箱:</td>'+
                         '<td>'+(user.email==null ? "" : user.email) +'</td></tr>';
                     sOut += '<tr><td>创建时间:</td><td>'+ user.createTime +'</td><td>更新时间:</td><td>'+user.updateTime+'</td></tr>';
                 }
                 sOut += '</table>';
                 oTable.fnOpen( nTr, sOut, 'details');
	   		 }
	   	});
    };
    
    var initToolBar = function(){
    	//触发相关赋值
        $("#confirmWin").on("show.bs.modal", function(e){
            var id = $(e.relatedTarget).attr("id");
            $("#id").val('').val(id);
        });
        //删除
        $("#deleteBut").on("click", function(){
            var param = {id : $("#id").val()};
            $.ajax({
                type: "post",
                data: param,
                url: 'deleteMemberPoint.html',
                success: function (data) {
                    if (data.code == 0) {
                    	oTable.fnReloadAjax();
                        bootbox.alert("删除成功");
                        $("#confirmWin").modal("hide");
                    } else {

                    }
                    $("#confirmWin").modal("hide");
                    //调用初始化右侧表格的方法
                    //memberPoint_memberPointList.loadTableData();   
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

