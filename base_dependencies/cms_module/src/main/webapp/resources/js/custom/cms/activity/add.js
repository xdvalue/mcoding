//产品积分列表相关操作
var ActivityManager = function() {

	var addActivity = function() {
		var activityForm = $("#activityForm");
		if (!activityForm.valid()) {
			return;
		}

		var requestURL = "activity/service/create";
		var tips = "增加失败!";
		var param = activityForm.serializeArray();
		param = serializeObject(param);

		var executeTimeStr = param.executeTime;
		executeTimeStr = executeTimeStr.replace(/-/g, '/');
		var executeTimeDate = new Date(executeTimeStr);
		param.executeTime = executeTimeDate;

		var registrationDeadlineStr = param.registrationDeadline;
		registrationDeadlineStr = registrationDeadlineStr.replace(/-/g, '/');
		var registrationDeadlineDate = new Date(registrationDeadlineStr);
		param.registrationDeadline = registrationDeadlineDate;

		var publicTimeStr = param.publicTime;
		publicTimeStr = publicTimeStr.replace(/-/g, '/');
		var publicTimeDate = new Date(publicTimeStr);
		param.publicTime = publicTimeDate;

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

	var updateActivity = function() {
		var activityForm = $("#activityForm");
		if (!activityForm.valid()) {
			return;
		}

		var requestURL = "activity/service/edit";
		var tips = "修改失败!";
		var param = activityForm.serializeArray();
		param = serializeObject(param);

		var executeTimeStr = param.executeTime;
		executeTimeStr = executeTimeStr.replace(/-/g, '/');
		var executeTimeDate = new Date(executeTimeStr);
		param.executeTime = executeTimeDate;

		var registrationDeadlineStr = param.registrationDeadline;
		registrationDeadlineStr = registrationDeadlineStr.replace(/-/g, '/');
		var registrationDeadlineDate = new Date(registrationDeadlineStr);
		param.registrationDeadline = registrationDeadlineDate;

		var publicTimeStr = param.publicTime;
		publicTimeStr = publicTimeStr.replace(/-/g, '/');
		var publicTimeDate = new Date(publicTimeStr);
		param.publicTime = publicTimeDate;

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
		$("#singleAdd").on("click", function() {
			addActivity();
		});

		$("#singleUpdate").on("click", function() {
			updateActivity();
		});

		// 省市级联
		$("province").on("click", function() {
			$("#address").citySelect({
				url : "resources/js/cityselect/city.min.js",
				nodata : "none",
				required : false
			});
		});
	};
	
	var initCity = function() {
		var province = $('#provinceValue').val();
		var city = $('#cityValue').val();
		var district = $('#districtValue').val();
		if (province || province != '') {
			if (city || city != '') {
				if (district || district != '') {
					$("#address").citySelect({
						prov:province, 
						city:city, 
						dist:district,
						url : "resources/js/cityselect/city.min.js",
						nodata : "none",
						required : false
						}); 
				}else{
					$("#address").citySelect({
						prov:province, 
						city:city, 
						url : "resources/js/cityselect/city.min.js",
						nodata : "none",
						required : false
						}); 
				}
			}else{			
				$("#address").citySelect({
					prov:province, 
					url : "resources/js/cityselect/city.min.js",
					nodata : "none",
					required : false
					}); 
			}
		}
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
			initCity();
			handleDatetimePicker();
		}
	};
}();
