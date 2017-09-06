//产品积分列表相关操作
var Article_AddArticleDefManager = function() {

	// 初始化页面相关按钮事件
	var initEvent = function() {
		// $("#allSearch").hide();
		$("#singleAdd").on("click", function() {
			var articleTitleForm = $("#articleTitleForm");
			
			//if (!articleDefForm.valid()) {
			//	return;
			//}
			var requestURL = "management/addArticleTitle.html";
			var tips = "增加失败!";
			if ($("#id").val() != '') {
				tips = "修改失败!";
			}
			
			var articleTitle = serializeObject(articleTitleForm.serializeArray());
			$.ajax({
				type : "POST",
				url : requestURL,
				data : JSON.stringify(articleTitle),
				dataType : "json",
				contentType : "application/json; charset=utf-8",
				success : function(data) {
					if (data.code == 0) {
						$("#backListPage").click();
					} else {
						bootbox.alert(tips);
					}
				}
			});
		});

		var options = {
			errorElement : 'span',
			errorClass : 'help-block',
			focusInvalid : false,
			ignore : "",
			rules : {
				title : {
					minlength : 2,
					required : true
				},
//				stitle : {
//					minlength : 2,
//					maxlength : 40,
//					required : true
//				},
				brandCode : {
					required : true
				},
//				recommend : {
//					required : false
//				},
				content : {
					required : true
				}
			},
			highlight : function(element) {
				$(element).closest('.form-group').addClass('has-error');
			},
			unhighlight : function(element) {
				$(element).closest('.form-group').removeClass('has-error');
			},
			success : function(label) {
				label.closest('.form-group').removeClass('has-error');
			}
		}
		// 验证表单
		//$("#articleDefForm").validate(options);
	};

	return {
		init : function() {
			initEvent();
		},
	};
}();


