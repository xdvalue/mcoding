
var scoreWeight_manager = function () {

    //初始化页面相关按钮事件
    var initEvent = function () {
		$("#singleAdd").on("click", function() {
			var scoreForm = $("#scoreForm");
			
			if (!scoreForm.valid()) {
				return;
			}
			
			var requestURL = "manage/updateScoreWeight.html";
			var tips = "修改失败!";
			
			var param = serializeObject(scoreForm.serializeArray());
			$.ajax({
				type : "POST",
				url : requestURL,
				data : JSON.stringify(param),
				dataType : "json",
				contentType : "application/json; charset=utf-8",
				success : function(data) {
					if (data.code == 0) {
						bootbox.alert("修改成功");
						$("#backListPage").click();
					} else {
						bootbox.alert(data.msg);
					}
				}
			});
        });
		
        var options = {
            errorElement: 'span',
            errorClass: 'help-block',
            focusInvalid: false,
            ignore: "",
            rules: {
            	selfWeight: {
                    required: true
                },
                hairWeight: {
                    required: true
                },
                physicalWeight: {
                    required: true
                }
            },
            highlight: function (element) {
                $(element).closest('.form-group').addClass('has-error');
            },
            unhighlight: function (element) {
                $(element).closest('.form-group').removeClass('has-error');
            },
            success: function (label) {
                label.closest('.form-group').removeClass('has-error');
            }
        }

        //验证表单
        $("#scoreForm").validate(options);
          
    };

    return {
        init: function () {
            initEvent();
        },
    };
}();