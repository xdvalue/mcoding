
var DataTableList = function() {
	
	var showForMsgType = function(msgType, doNotClear){
		if(!doNotClear){
			$('.unselected input').val('');
			$('.unselected select').val('');
		}
		
		switch(msgType){
		case 'text': return showInput(['fg-content', 'fg-matchType']);
		case 'event': return showInput(['fg-event']);
		default: return showInput([]);
		}
	};
	
	var showForEvent = function(event){
		if(!event || event == '' || event == 'LOCATION'){
			$('#eventKey').val('');
			$('#fg-eventKey').hide();
			$('#fg-matchType').hide();
		}else{
			$('#fg-eventKey').show();
			$('#fg-matchType').show();
		}
	};
	
	var showInput = function(ids){
		$('.unselected').hide();
		$.each(ids, function(index, id){
			$('#'+id).show();
		});
	};

	var createOrEdit = function(isCreate) {
		
		var form = $("#wxMsgRuleForm");
		if (!form.valid()) {
			return;
		}

		var requestURL = "wechatMsgRule/service/create";
		if(!isCreate){
			requestURL = "wechatMsgRule/service/edit";
		}
		var tips = "增加失败!";
		var param = form.serializeArray();
		param = serializeObject(param);
		
		if(param.replyText == '' && param.handlers == ''){
			bootbox.alert(tips + '原因是:回复的文本，和其他规则处理器，至少要填一个');
			return;
		}

		$.ajax({
			type : "POST",
			url : requestURL,
			data : JSON.stringify(param),
			dataType : "json",
			contentType : "application/json",
			success : function(data) {
				if (!data || data.status != '00') {
					bootbox.alert(tips + '原因是' + data.msg);
					return;
				}
				
				$("#backListPage").click();
				return;
			},
			error : function() {
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
		
		$('#msgType').change(function(){
			var msgType = $(this).val();
			showForMsgType(msgType);
    	});
		
		$('#event').change(function(){
			var event = $(this).val();
			showForEvent(event);
    	});
		
	};


	return {
		init : function() {
			showForMsgType($('#msgType').val(), true); 
			if($('#event').val()) showForEvent($('#event').val());
			
			initEvent();
		}
	};
}();
 