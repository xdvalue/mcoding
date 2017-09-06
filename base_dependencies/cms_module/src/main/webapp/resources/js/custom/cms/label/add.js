//产品积分列表相关操作
var labelManager = function() {

	var addLabel = function(opType) {
		debugger;
		var labelForm = $("#labelForm");
		if (!labelForm.valid()) {
			return;
		}

		var requestURL = "label/service/create";
		var tips = "增加失败!";
		var param = labelForm.serializeArray();
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
				$("#backLabelListPage").click();
				return;
			},
			error : function() {
				bootbox.alert(tips);
				return;
			}
		});
	};

	var updateLabel = function() {
		var labelForm = $("#labelForm");
		if (!labelForm.valid()) {
			return;
		}

		var requestURL = "label/service/edit";
		var tips = "修改失败!";
		var param = labelForm.serializeArray();
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
				$("#backLabelListPage").click();
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
		$("#labelAdd").on("click", function() {
			addLabel();
		});

		$("#labelUpdate").on("click", function() {
			updateLabel();
		});
	};
	
	return {
		init : function(str) {
			debugger;
			if(str && str == "D"){
				$("#mark").attr("disabled","disabled");
				$("#type").attr("disabled","disabled");
				$("#storeId").attr("disabled","disabled");
				$("#seqNum").attr("disabled","disabled");
			}else{
				$("#mark").attr("disabled",false);
				$("#type").attr("disabled",false);
				$("#storeId").attr("disabled",false);
				$("#seqNum").attr("disabled",false);
			}
			initEvent();
		}
	};
}();
