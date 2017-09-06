//产品积分列表相关操作
var templateManager = function() {

	var addTemplate = function(opType) {
		debugger;
		var templateForm = $("#templateForm");
		if (!templateForm.valid()) {
			return;
		}

		var requestURL = "template/service/create";
		var tips = "增加失败!";
		var param = templateForm.serializeArray();
		param = serializeObject(param);

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
				$("#backTemplateListPage").click();
				return;
			},
			error : function() {
				bootbox.alert(tips);
				return;
			}
		});
	};

	var updateTemplate = function() {
		var templateForm = $("#templateForm");
		if (!templateForm.valid()) {
			return;
		}

		var requestURL = "template/service/edit";
		var tips = "修改失败!";
		var param = templateForm.serializeArray();
		param = serializeObject(param);

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
				$("#backTemplateListPage").click();
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
		$("#templateAdd").on("click", function() {
			addTemplate();
		});

		$("#templateUpdate").on("click", function() {
			updateTemplate();
		});
	};
	
	return {
		init : function(str) {
			if(str && str == "D"){
				$("#templateName").attr("disabled","disabled");
				$("#templateUrl").attr("disabled","disabled");
				$("#storeId").attr("disabled","disabled");
				$("#description").attr("disabled","disabled");
			}else{
				$("#templateName").attr("disabled",false);
				$("#templateUrl").attr("disabled",false);
				$("#storeId").attr("disabled",false);
				$("#description").attr("disabled",false);
			}
			initEvent();
		}
	};
}();
