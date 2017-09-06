//产品积分列表相关操作
var BannerManager = function() {
	
	//创建或者编辑文章
	var createOrEditBanner = function(isCreate) {
		debugger;
		var form = $("#bannerForm");
		if (!form.valid()) {
			return;
		}
		var requestURL = "banner/service/create";
		if (!isCreate) {
			requestURL = "banner/service/edit";
		}
		var tips = "增加失败!";
		var param = form.serializeArray();
		param = serializeObject(param);
		var imager = param.imgUrl;
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
		if ($("#singleAdd")) {
			$("#singleAdd").on("click", function() {
				var isCreate = true;
				createOrEditBanner(isCreate);
			});
		}

		if ($("#singleUpdate")) {
			$("#singleUpdate").on("click", function() {
				 var isCreate = false;
				 createOrEditBanner(isCreate);
			});
		}
	};

	return {
		init : function() {
			imageUpload({
				imageUploadBtn :"imageButton",
				showUrlId :  "imgUrl"
			});
			initEvent();
		}
	};
}();
