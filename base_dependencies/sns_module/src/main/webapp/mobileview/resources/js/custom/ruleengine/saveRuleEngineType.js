//产品积分列表相关操作
var RuleEngine_RuleEngineTypeManager = function () {

    //初始化页面相关按钮事件
    var initEvent = function () {
		// $("#allSearch").hide();
		$("#singleAdd").on("click", function() {
			var ruleEngineTypeForm = $("#ruleEngineTypeForm");
			if (!ruleEngineTypeForm.valid()) {
				return;
			}
			var requestURL = "saveRuleEngineType.html";
			var tips = "增加失败!";
			if ($("#id").val() != '') {
				requestURL = "saveRuleEngineType.html";
				tips = "修改失败!";
			}
			console.log(ruleEngineTypeForm.serialize());
			var param = ruleEngineTypeForm.serialize();
			$.ajax({
				type : "POST",
				url : requestURL,
				data : param,
				dataType : "json",
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
            errorElement: 'span',
            errorClass: 'help-block',
            focusInvalid: false,
            ignore: "",
            rules: {
            	typeDesc: {
                    required: true
                },
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
        $("#ruleEngineTypeForm").validate(options);
        
    };
	

    return {
        init: function () {
            initEvent();
        },
    };
}();