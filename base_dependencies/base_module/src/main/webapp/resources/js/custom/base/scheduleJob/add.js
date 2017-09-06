
var DataTableList = function() {

	var createOrEdit = function(isCreate) {
		
		var form = $("#scheduleJobForm");
		if (!form.valid()) {
			return;
		}

		var requestURL = "scheduleJob/service/create";
		if(!isCreate){
			requestURL = "scheduleJob/service/edit";
		}
		var tips = "增加失败!";
		var param = form.serializeArray();
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


	return {
		init : function() {
			initEvent();
		}
	};
}();
