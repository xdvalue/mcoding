
var DataTableList = function() {

	var createOrEdit = function(isCreate) {
		debugger;
		var form = $("#memberForm");
		if (!form.valid()) {
			return;
		}

		var requestURL = "member/service/create";
		if(!isCreate){
			requestURL = "member/service/edit";
		}
		var tips = "增加失败!";
		var param = form.serializeArray();
		param = serializeObject(param);
		
		if(!param.gender || (param.gender !=1 && param.gender !=2)){
			bootbox.alert(tips + '请填写完整信息');
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
	};


	return {
		init : function() {
			initEvent();
			imageUpload({
				imageUploadBtn :"imgUrlButton",
				showUrlId :  "imgUrlInput"
			});
		}
	};
}();
