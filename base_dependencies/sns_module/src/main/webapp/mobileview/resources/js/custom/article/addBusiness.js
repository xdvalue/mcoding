//产品积分列表相关操作
var Article_AddArticleDefManager = function() {

	// 初始化页面相关按钮事件
	var initEvent = function() {
		// $("#allSearch").hide();
		$("#singleAdd").on("click", function() {
			var articleDefForm = $("#articleDefForm");
			
			if (!articleDefForm.valid()) {
				return;
			}
			
			var requestURL = "management/addDTBusiness.html";
			var tips = "增加失败!";
			if ($("#id").val() != '') {
				tips = "修改失败!";
			}
			
			var articleDef = serializeObject(articleDefForm.serializeArray());
			
			var obj=new Object();
			obj=articleDef;
			
			$.ajax({
				type : "POST",
				url : requestURL,
				data : JSON.stringify(obj),
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
				name : {
					minlength : 2,
					required : true
				},
				company : {
					minlength : 2,
					maxlength : 40,
					required : true
				},
				title : {
					required : true
				},
				headimg : {
					required : true
				},
				qrcode : {
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
		$("#articleDefForm").validate(options);
		
	};
	
	var initRecommend = function(){
		if($("#id").val() != ''){
			initSelectedArticleDefs();
		}
	}

	return {
		init : function() {
			initEvent();
			initRecommend();
		},
	};
}();
