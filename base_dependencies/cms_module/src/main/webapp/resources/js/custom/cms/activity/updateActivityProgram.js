//产品积分列表相关操作
var ActivityProgramManager = function() {

	var updateActivityProgram = function() {
		var programForm = $("#programForm");
		if (!programForm.valid()) {
			return;
		}

		var requestURL = "activityProgram/service/edit";
		var tips = "修改失败!";
		var param = programForm.serializeArray();
		param = serializeObject(param);

		var isSmsNotify = param.isSmsNotify;
		if (isSmsNotify === undefined || isSmsNotify == "" || isSmsNotify == 0) {
			param.isSmsNotify = 0;
		} else {
			param.isSmsNotify = 1;
		}

		var isWechatNotify = param.isWechatNotify;
		if (isWechatNotify === undefined || isWechatNotify == ""
				|| isWechatNotify == 0) {
			param.isWechatNotify = 0;
		} else {
			param.isWechatNotify = 1;
		}

		var StrstartTime = param.startTime;
		StrstartTime = StrstartTime.replace(/-/g, '/');
		var dateStart = new Date(StrstartTime);
		var iStartTime = dateStart;
		param.startTime = iStartTime;

		var StrendTime = param.endTime;
		StrendTime = StrendTime.replace(/-/g, '/');
		var dateEnd = new Date(StrendTime);
		var iEndTime = dateEnd;
		param.endTime = iEndTime;

		if (param.endTime <= param.startTime) {
			bootbox.alert("结束时间不能小于开始时间");
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
				$('#backListProgram').click();
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
		$("#btnProgram").on("click", function() {
			updateActivityProgram();
		});
	};

	var handleDatetimePicker = function() {
		$(".form_datetime").datetimepicker({
			format : "yyyy-mm-dd hh:ii:ss",
			autoclose : true,
			todayBtn : true,
			startDate : new Date(),
			pickerPosition : "bottom-left",
			minuteStep : 10
		});
	};

	return {
		init : function() {
			initEvent();
			handleDatetimePicker();
		}
	};
}();
