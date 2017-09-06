//产品积分列表相关操作
var ORG_MemberManager = function() {

	var addMember = function() {
		var memberForm = $("#memberForm");
		if (!memberForm.valid()) {
			return;
		}
		var requestURL = "member/service/addMember";
		var tips = "增加失败!";
		var param = memberForm.serializeArray();
		param = serializeObject(param);
		
		var gender = param.gender;
		var birthday = param.birthday;
		// 时间戳转化
		var milliseconds = moment(param.birthday, 'YYYY-MM-DD').valueOf(); 
		
		var province = param.province;
		var city = param.city;
		var district = param.district;
		var address = param.address;
		param.memberExtInfo = {
				gender:gender,
				birthday:milliseconds,
				province:province,
				city:city,
				district:district,
				address:address
				};
		delete param.gender;
		delete param.birthday;
		delete param.province;
		delete param.city;
		delete param.district;
		delete param.address;
		
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
		
		$("#addMemberBtn").on("click", function() {
			addMember();
		});

		$("province").on("click", function() {
			$("#address").citySelect({
				url : "resources/js/cityselect/city.min.js",
				nodata : "none",
				required : false
			});
		});
		
	};
	
	// 省市级联
	var initCity = function() {
		$("#address").citySelect({
			url : "resources/js/cityselect/city.min.js",
			nodata : "none",
			required : false
		});
	};

	var handleDatetimePicker = function() {
		$(".form_datetime").datetimepicker({
			format : "yyyy-mm-dd",
			minView: 2,
			maxView:2,
			autoclose : true,
			todayBtn : true,
			pickerPosition : "bottom-left",
			minuteStep : 10
		});
	};

	return {
		init : function() {
			initEvent();
			handleDatetimePicker();
			initCity();
		}
	};
}();
