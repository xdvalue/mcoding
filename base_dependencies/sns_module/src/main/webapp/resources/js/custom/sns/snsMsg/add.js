
var DataTableList = function() {

	var createOrEdit = function(isCreate) {
		
		var form = $("#snsMsgForm");
		if (!form.valid()) {
			return;
		}

		var requestURL = "snsMsg/service/create";
		if(!isCreate){
			requestURL = "snsMsg/service/edit";
		}
		var tips = "增加失败!";
		var param = form.serializeArray();
		param = serializeObject(param);
		
		if(param.enableTime && param.enableTime!=''){
			param.enableTime = moment(param.enableTime, 'YYYY-MM-DD HH:mm:ss').valueOf();
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

	};
	
	var handleDatetimePicker = function() {
		$(".form_datetime").datetimepicker({
			format : "yyyy-mm-dd hh:ii:ss",
			autoclose : true,
			todayBtn : true,
			pickerPosition : "bottom-left",
			minuteStep : 1
		});
	};


	return {
		init : function() {
			kindEditorCreate("contentKindEditor", "snsPost");
			handleDatetimePicker();
			initEvent();
		}
	};
}();
