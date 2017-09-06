//二维码标签
var qrcode_addQRCodeLabelList = function () {
    //初始化页面相关按钮事件
    var initEvent = function () {
        //$("#allSearch").hide();
        $("#singleAdd").on("click", function(){
            var submitForm = $("#submitForm");
            if(!submitForm.valid()){
                return;
            }
           if(!callMyvalidate()){
            	return;
            }
            var requestURL = "addQRCodeLabel.html";
            var tips = "增加失败!";
            if($("#id").val() != ''){
                requestURL = "addQRCodeLabel.html";
                tips = "修改失败!";
            }
            var param = submitForm.serialize();
            $.ajax({
                type: "POST",
                url: requestURL,
                data: param,
                dataType: "json",
                success: function (data) {
                    if (data.code == 0) {
                        $("#backListPage").click();
                    }else{
                        bootbox.alert(tips);
                    }
                }
            });
        });
        
        $("#nextAdd").on("click", function(){
            var submitForm = $("#submitForm");
            if(!submitForm.valid()){
                return;
            }
            $.ajax({
                type: "POST",
                url: "addQRCodeLabel.html",
                data: submitForm.serialize(),
                dataType: "json",
                success: function (data) {
                    if (data.code == 0) {
                       bootbox.alert("增加成功 !");
                        //清除页面数据
                       $("#submitForm").find('input:type="text":').val('');
                    }else{
                        bootbox.alert("增加失败!");
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
            	name: {
                    required: true
                },
                key: {
                    required: true
                },
                imgUrl: {
                	required: true
                },
                ext: {
                	number:true,
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
       $("#submitForm").validate(options);
	
    };
    
    return {
        init: function () {
        	initData();
            initEvent();
        },
    };
}();

/**
 * 初始化页面
 */
function initData(){
	
}

/**校验*/
function callMyvalidate(){
	return true;
}

