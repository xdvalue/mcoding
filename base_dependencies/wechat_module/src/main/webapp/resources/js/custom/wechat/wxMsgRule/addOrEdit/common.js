
var DataTableList = function() {

	var createOrEdit = function(isCreate) {
		var form = $("#wxMsgRuleForm");
		if (!form.valid()) {
			return;
		}

		var param = form.serializeArray();
		
		var url = $("#nextBtn").attr('nextUrl');
		for(var i=0; i<param.length; i++){
			var value = param[i].value;
			if(!value || value == ''){
				continue;
			}
			
			if(param[i].name == 'content' || param[i].name=='eventKey'){
				
			}
			
			url = url + '&' + param[i].name + '=' + param[i].value;
		}
		$("#ajaxBtn").attr('href', url);
		$("#ajaxBtn").click();
	};

	// 初始化页面相关按钮事件
	var initEvent = function() {
		$("#nextBtn").on("click", function() {
			var isCreate = true;
			createOrEdit(isCreate);
		});
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
			initEvent();
			handleDatetimePicker();
		}
	};
}();
 