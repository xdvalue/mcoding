//员工通讯录
var member_memberList = function () {
	var tableId = "memberTable";
	var oTable = null;
	
	//初始化加载表格数据的表头定义
    var initTableHeaderInfo = function(){
        var aoColumns = [
            { "sTitle": "编号", "sWidth" : "1%", "mData": "memberId"},
			{ "sTitle": "会员名称", "sWidth" : "5%", "mData": "fullName"},
			{ "sTitle": "手机号码", "sWidth" : "5%", "mData": "mobilePhone"},
			{ "sTitle": "积分总额", "sWidth" : "5%", "mData": "pointSum"},
			{
            	"sTitle": "性别",
            	"sWidth" : "5%",
            	"mDataProp" : "",
				"sDefaultContent" : "",
            	"sVisible" : false,
            	"fnCreatedCell" : function(nTd, sData, oData, iRow, iCol) {
            		if(oData.gender=="男" || oData.gender=="man"){
            			$(nTd).append("男");
            		}else if(oData.gender=="女" || oData.gender=="woman"){
            			$(nTd).append("女");
            		}
            		else{
            			$(nTd).append("暂无");
            		}
            	}
            },
           {
            	"sTitle": "注册来源",
            	"mDataProp" : "",
				"sDefaultContent" : "",
            	"sWidth" : "5%",
            	"sVisible" : false,
            	"fnCreatedCell" : function(nTd, sData, oData, iRow, iCol) {
            		if(oData.enrollChannel=="WEIXIN"){
            			$(nTd).append("<label class='label label-info'>微信</label>");
            		}else if(oData.enrollChannel=="GW"){
            			$(nTd).append("<label class='label label-info'>官网</label>");
            		}else if(oData.enrollChannel=="ACTIVITY"){
            			$(nTd).append("<label class='label label-info'>工伤福利申请</label>");
            		}else if(oData.enrollChannel=="SCANCODE"){
            			$(nTd).append("<label class='label label-info'>瓶盖扫码</label>");
            		}else if(oData.enrollChannel=="MOTHERSDAY"){
            			$(nTd).append("<label class='label label-info'>母亲节礼盒申请</label>");
            		}else if(oData.enrollChannel=="RUN"){
            			$(nTd).append("<label class='label label-info'>跑步</label>");
            		}else{
            			$(nTd).append("<label class='label label-info'>暂无</label>");
            		}
            	}
            },
			{ 
				"sTitle": "会员类型",
				"mDataProp" : "",
				"sDefaultContent" : "",
            	"sWidth" : "7%",
				"sVisible" : false,
				"fnCreatedCell" : function(nTd,sData, oData, iRow, iCol) {
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
				"sTitle": "注册时间",
				"mDataProp" : "",
				"sDefaultContent" : "",
            	"sWidth" : "5%",
				"sVisible" : false,
				"fnCreatedCell" : function(nTd,sData, oData, iRow, iCol) {
						$(nTd).append(changeDateFormat(oData.createTime));
				}
			},
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
            		$(nTd).append("<span class='btn btn-xs purple black' data-toggle='modal' " +
        					"href='#confirmWin2' enable='ok' " + "id='"+ oData.memberId +"'>" +
                    "<i class='fa fa-edit'></i> 详情 </span>&nbsp;&nbsp;");
            		if(App.checkUserOperRight("memberList", "deleteMember")){
            			$(nTd).append("<span class='btn default btn-xs black' data-toggle='modal' " +
            					"href='#confirmWin' enable='ok' " + "id='"+ oData.mobilePhone +"'brandCode="+oData.brandCode+">" +
                        "<i class='fa fa-trash-o'></i> 删除 </span>");
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
        var search = $("#searchForm").serializeArray();
        oTable = $('#'+tableId).DataTable({
        	"fnServerParams": function (aoData) {
        		$.each(search, function(index, value){
        			aoData.push({"name": value.name, "value": value.value})
                });
        	},
            "sAjaxSource": "queryMemberData.html",
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
                "sSearch" : "搜索手机号码", 
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
    	member_memberList.loadTableData();
     });
    
    $('#btnReset').click(function(){
		document.getElementById("mobilePhone").value="";	  	
    });


    var initToolBar = function(){
    	//触发相关赋值
        $("#confirmWin").on("show.bs.modal", function(e){
            var id = $(e.relatedTarget).attr("id");
            $("#id").val('').val(id);
            var brandCode = $(e.relatedTarget).attr("brandCode");
            $("#brandCode").val('').val(brandCode);
        });
    	//删除事件
        $("#deleteBut").on("click", function(){
        	var param = {teplId : $("#id").val(),brandCode : $("#brandCode").val()};
            $.ajax({
                type : "post",
                dataType : "json",
                url : "deleteMember.html",
                data : param,
                success : function(data) {
                    if(data.code==0){
                        oTable.fnReloadAjax();
                        bootbox.alert("删除成功");
                        $("#confirmWin").modal("hide");
                    }else{
                        bootbox.alert("删除失败");
                    }
                }
            });
        });
        
      //触发相关赋值
        $("#confirmWin2").on("show.bs.modal", function(e){
            var id = $(e.relatedTarget).attr("id");
            $("#id").val('').val(id);
            console.log(id);
            var param = {id : id};
            $.ajax({
                type : "post",
                dataType : "json",
                url : "merriplusApi/getMemberDetail.html",
                data : param,
                success : function(data) {
                    if(data.status=='00'){
                    	var sOut = '<table id="user" class="table table-bordered table-striped">';
                    		sOut +='<tbody>';
                    		sOut +='<tr>' +
								'<td style="width:10%">会员名称</td>'+
								'<td style="width:20%">'+(data.data.fullName==null ? "" :data.data.fullName)+'</td>'+
								'<td style="width:10%">手机号码</td>'+
								'<td style="width:20%">'+data.data.mobilePhone+'</td>'+
								'<td style="width:10%">会员类型</td>'+
								'<td style="width:20%">'+data.data.memberType+'</td>'+
								'</tr>';
                    		sOut +='<tr>' +
                    		'<td style="width:10%">微信openid</td>'+
                    		'<td style="width:20%">'+data.data.openid+'</td>'+
                    		'<td style="width:10%">生日</td>'+
                    		'<td style="width:20%">'+data.data.birthday+'</td>'+
                    		'<td style="width:10%">性别</td>'+
                    		'<td style="width:20%">'+data.data.gender+'</td>'+
                    		'</tr>';
                    		sOut +='<tr>' +
                    		'<td style="width:10%">注册来源</td>'+
                    		'<td style="width:20%">'+data.data.enrollChannel+'</td>'+
                    		'<td style="width:10%">所属品牌</td>'+
                    		'<td style="width:20%">'+data.data.brandCode+'</td>'+
                    		'<td style="width:10%">关注问题</td>'+
                    		'<td style="width:20%">'+data.data.concerns+'</td>'+
                    		'</tr>';
                    		sOut +='<tr>' +
                    		'<td style="width:10%">关注身边的人</td>'+
                    		'<td style="width:20%">'+data.data.concernsPerson+'</td>'+
                    		'<td style="width:10%">健康问题</td>'+
                    		'<td style="width:20%">'+data.data.healthProblem+'</td>'+
                    		'<td style="width:10%">职位</td>'+
                    		'<td style="width:20%">'+data.data.position+'</td>'+
                    		'</tr>';
                    		sOut +='<tr>' +
                    		'<td style="width:10%">备注1</td>'+
                    		'<td style="width:20%">'+data.data.ext1+'</td>'+
                    		'<td style="width:10%">备注2</td>'+
                    		'<td style="width:20%">'+data.data.ext2+'</td>'+
                    		'<td style="width:10%">备注3</td>'+
                    		'<td style="width:20%">'+data.data.ext3+'</td>'+
                    		'</tr>';
                    		sOut +='<tr>' +
                    		'<td style="width:10%">积分总额</td>'+
                    		'<td style="width:20%">'+data.data.pointSum+'</td>'+
                    		'<td style="width:10%">创建时间</td>'+
                    		'<td style="width:20%">'+changeDateFormat(data.data.createTime)+'</td>'+
                    		'<td style="width:10%">更新时间</td>'+
                    		'<td style="width:20%">'+changeDateFormat(data.data.updateTime)+'</td>'+
                    		'</tr>';
                    		sOut +='<tr>' +
                    		'<td style="width:10%">其他</td>'+
                    		'<td style="width:20%">'+data.data.activitySymptom+'</td>'+
                    		'<td style="width:10%"></td>'+
                    		'<td style="width:20%"></td>'+
                    		'<td style="width:10%"</td>'+
                    		'<td style="width:20%"></td>'+
                    		'</tr>';
			                sOut +='</tbody>';
			                sOut +='</table>';
							sOut += '</table>';
    					$("#modal-body").empty().append(sOut);
                        /*oTable.fnReloadAjax();
                        bootbox.alert("删除成功");
                        $("#confirmWin").modal("hide");*/
                    }else{
                        //bootbox.alert("删除失败");
                    }
                }
            });
        });
    };

    return {
        init: function () {
            loadTableData();
            initToolBar();
        },
        loadTableData: loadTableData
    };
}();

