
var DataTableList = function() {

	var createOrEdit = function(isCreate) {
		debugger;
		var form = $("#wxMsgAutoReplyForm");
		if (!form.valid()) {
			return;
		}

		var requestURL = "wxMsgAutoReply/service/create";
		if(!isCreate){
			requestURL = "wxMsgAutoReply/service/edit";
		}
		var tips = "增加失败!";
		var param = form.serializeArray();
		param = serializeObject(param);
		
//		param.keywords = JSON.stringify(param.keywords.split(/\s*,\s*/));
		
        App.blockUI({
			target: '.portlet-body form',
			boxed: true
		});
		$.ajax({
			type : "POST",
			url : requestURL,
			data : JSON.stringify(param),
			dataType : "json",
			contentType : "application/json",
			success : function(data) {
				if (!data || data.status != '00') {
				    App.unblockUI('.portlet-body form');
					bootbox.alert(tips + '原因是' + data.msg);
					return;
				}
				
				$("#backListPage").click();
				return;
			},
			error : function() {
			    App.unblockUI('.portlet-body form');
				bootbox.alert(tips);
				return;
			}
		});
	};

	// 初始化页面相关按钮事件
	var initEvent = function() {
		if($("#singleAdd")){
			$("#singleAdd").on("click", function() {
				var isCreate = true;
				createOrEdit(isCreate);
			});
		}
		
		if($("#singleUpdate")){
			$("#singleUpdate").on("click", function() {
				var isCreate = false;
				createOrEdit(isCreate);
			});
		}

	};
	
	var replyTypeChangeHandler = function(input){
		debugger;
		$("#replyNewsBlock").hide();
		$('#replyNewsInput').attr('readOnly', 'true');
		
		var replyType = $(input).val();
		if(replyType == '2'){
			//如果是图文，就显示图文的显示
			$('#replyNewsBlock').show();
			RelyNewsManager.reload();
		}else if(replyType == '1'){
			$('#replyNewsInput').removeAttr('readOnly', 'false');
//			$('#replyNewsInput').val('');
		}
	}


	return {
		init : function() {
			initEvent();
			replyTypeChangeHandler($('#replyType'));
		},
		replyTypeChange: replyTypeChangeHandler
	};
}();
