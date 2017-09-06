//消息回复
var wechat_wechatReply = function () {
	var tableId = "tableId";
	var oTable = null;
	
	//初始化加载表格数据的表头定义
    var initTableHeaderInfo = function(){
        var aoColumns = [
			{ "sTitle": "id", "sWidth" : "1%", "mData": "id"},
			/*{ "sTitle": "用户名","sWidth" : "1%", "mData": "username"},*/
			{ "sTitle": "关键字", "sWidth" : "8%", "mData": "keyword"},
			/*{ "sTitle": "标题","sWidth" : "5%", "mData": "title"},
			{ "sTitle": "回复内容","sWidth" : "5%", "mData": "content"},
			{ "sTitle": "图片地址","sWidth" : "5%", "mData": "image"},
			{ "sTitle": "新闻消息地址","sWidth" : "5%", "mData": "newsMsgUrl"},*/
			{ "sTitle": "消息类型","sWidth" : "5%", "mData": "msgType"},
			{ "sTitle": "品牌","sWidth" : "5%", "mData": "brandCode"}
			
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
            		if(App.checkUserOperRight("wechatReplyList", "editWechatReply")){
            			$(nTd).append("<span style='margin: 0 5px 0 5px'><span class='btn btn-xs purple ajaxify'" +
            					" href='addWechatReplyView.html?id="+oData.id+"'><i class='fa fa-edit'></i> 编辑 </span>");
            		}
            		if(App.checkUserOperRight("wechatReplyList", "deleteWechatReply")){
            			$(nTd).append("<span class='btn default btn-xs black' data-toggle='modal' " +
            					"href='#confirmWin' enable='ok' " + "id='"+ oData.id +"'>" +
                        "<i class='fa fa-trash-o'></i> 删除 </span>");
            		}
            		/*if(App.checkUserOperRight("wechatReplyList", "sendWechatReply")){
            			$(nTd).append("<span style='margin: 0 5px 0 5px'><span class='btn btn-xs purple ajaxify'" +
            					" href='sendWechatReply.html?id="+oData.id+"'><i class='fa fa-edit'></i> 一键群发 </span>");
            		}*/
            		if(App.checkUserOperRight("wechatReplyList", "sendWechatReply")){
            			$(nTd).append("<span class='btn default btn-xs black' data-toggle='modal' " +
            					"href='#confirmWin2' enable='ok' " + "id='"+ oData.id +"'>" +
                        "<i class='fa fa-trash-o'></i>一键群发</span>");
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
            "sAjaxSource": "wechat/queryWechatReplyData.html",
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
                "sSearch" : "搜索", 
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
    	health_healthCriteriaMemberList.loadTableData();
     });
    
    $('#btnReset').click(function(){
		document.getElementById("mobilePhone").value="";	  	
    });


    var initToolBar = function(){
    	//触发相关赋值
        $("#confirmWin").on("show.bs.modal", function(e){
            var id = $(e.relatedTarget).attr("id");
            $("#id").val('').val(id);
        });
        $("#confirmWin2").on("show.bs.modal", function(e){
        	var id = $(e.relatedTarget).attr("id");
        	$("#id").val('').val(id);
        });
    	//删除事件
        $("#deleteBut").on("click", function(){
        	var param = {teplId : $("#id").val()};
            $.ajax({
                type : "post",
                dataType : "json",
                url : "deleteWechatReply.html",
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
        //群发事件
        $("#sendBut").on("click", function(){
        	var param = {teplId : $("#id").val()};
        	$.ajax({
        		type : "post",
        		dataType : "json",
        		url : "sendWechatReply.html",
        		data : param,
        		success : function(data) {
        			if(data.code==0){
        				oTable.fnReloadAjax();
        				bootbox.alert("群发成功");
        				$("#confirmWin2").modal("hide");
        			}else{
        				bootbox.alert("群发失败");
        			}
        		}
        	});
        });
    };
    /* setTextAreaValue();
      //筛选tab页点击事件
	  $("#orderListTab").delegate('li', 'click', function(){
		$("#process").attr("value",$(this).attr("process"));
		$("#status").attr("value",$(this).attr("status"));
			if(oTable && $(this).attr("class")=="active"){
	             return false;
	        }
			oTable.fnDestroy();
			var status = $(this).attr("status");
			if(status=="消息自动回复"){
				$("#_aaa").show();
				$("#keywordauto").hide();
			}else if(status=="被添加自动回复"){
				$("#_aaa").show();
				$("#keywordauto").hide();
			}else{
				$("#keywordauto").show();
				$("#_aaa").hide();
			}
			setTextAreaValue(status);
			loadTableData();
      });*/

	  /*$("#singleAdd").on("click", function(){
          var submitForm = $("#submitForm");
          if(!callMyvalidate()){
          	return;
          }
          var requestURL = "addWechatReply.html";
          var tips = "增加失败!";
          var tips1 = "增加成功!";
          if($("#replyid").val() != ''){
              requestURL = "addWechatReply.html";
              tips = "修改失败!";
              tips1 = "修改成功!";
          }
          var param = submitForm.serialize();
          param=param+"&id="+$("#replyid").val()
          $.ajax({
              type: "POST",
              url: requestURL,
              data: param,
              dataType: "json",
              success: function (data) {
                  if (data.code == 0) {
                	  $("#replyid").val(data.data);
                	  bootbox.alert(tips1);
                	  //$("#backListPage").click();
                  }else{
                      bootbox.alert(tips);
                  }
              }
          });
      });*/
	  
    return {
        init: function () {
            loadTableData();
            initToolBar();
        },
        loadTableData: loadTableData
    };
}();

function callMyvalidate(){
	var status = "被添加自动回复";
	var content = $("#content").val().trim();
	$("li[name='newsli']").each(function(){
		if($(this).hasClass("active")){
			status = $(this).attr("status");
		}
	});
	if(status=="被添加自动回复"){
		if(content==""){
			alert("请输入回复内容");
			return false;
		}
	}
	if(status=="消息自动回复"){
		$("#keyword").val("nulltext");
		$("#title").val("消息自动回复");
		$("#msgType").val("text");
	}else{
		$("#keyword").val("1");
		$("#title").val("被添加自动回复");
		$("#msgType").val("text");
	}
	return true;
}

//设置被添加自动回复和消息自动回复页签的值
function setTextAreaValue(status){
	var keyword = "1";
	if(!status){
		status = "被添加自动回复";
		$("li[name='newsli']").each(function(){
			if($(this).hasClass("active")){
				status = $(this).attr("status");
			}
		});
	}
	if(status=="消息自动回复"){
		keyword = "nulltext";
	}
	$.ajax({
        type: "POST",
        url: "queryReplyByKeyword.html?keyword="+keyword,
        dataType: "json",
        success: function (data) {
            if (data.code == 0) {
            	if(data.data!=null){
            		//alert(data.data.keyword);
            		$("#replyid").val(data.data.id);
            		$("#keyword").val(data.data.keyword);
            		$("#title").val(data.data.title);
            		$("#content").val(data.data.content);
            		//kindEditorCreate("kindEditor","wechatnews");
            		//$("#product").html("<textarea name='content' id='kindEditor' style='width:100px;height:100px'  >"+data.data.content+"</textarea>");
            		//console.info($("#kindEditor").val());
            	}else{
            		$("#replyid").val("");
            		$("#keyword").val("");
            		$("#title").val("");
            		$("#content").val("");
            		//kindEditorCreate("kindEditor","wechatnews");
            		//$("#product").html("<textarea name='content' id='kindEditor' style='width:100px;height:100px'  ></textarea>");
            		//console.info($("#kindEditor").val());
            	}
            }else{
                bootbox.alert("获取数据失败");
            }
        }
    });
}