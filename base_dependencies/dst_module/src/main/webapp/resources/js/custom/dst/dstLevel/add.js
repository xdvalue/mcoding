
var DataTableList = function() {

	var createOrEdit = function(isCreate) {

		var form = $("#dstLevelForm");
		if (!form.valid()) {
			return;
		}

		var requestURL = "dstLevel/service/create";
		if(!isCreate){
			requestURL = "dstLevel/service/edit";
		}
		var tips = "增加失败!";
		var param = form.serializeArray();
		param = serializeObject(param);
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


	return {
		init : function() {
			initEvent();
		}
	};
}();
