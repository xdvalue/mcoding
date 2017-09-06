//产品类目列表相关操作
var Category_AddCategoryManager = function () {

    //初始化页面相关按钮事件
    var initEvent = function () {
            
        $("#singleAdd").on("click", function(){
            var CategoryForm = $("#CategoryForm");
            if(!CategoryForm.valid()){
                return;
            }
            var requestURL = "addCategory.html";
            var tips = "增加失败!";
            if($("#categoryId").val() != ''){
                requestURL = "addCategory.html";
                tips = "修改失败!";
            }
            
            $.ajax({
                type: "POST",
                url: requestURL,
                data: CategoryForm.serialize(),
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
            var CategoryForm = $("#CategoryForm");
            if(!CategoryForm.valid()){
                return;
            }
            $.ajax({
                type: "POST",
                url: "addCategory.html",
                data: CategoryForm.serialize(),
                dataType: "json",
                success: function (data) {
                    if (data.code == 0) {
                       bootbox.alert("增加成功!");
                        //清除页面数据
                       $("#categoryName").val('');
                       $("#categoryType").val('');
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
            	categoryName: {
                    required: true
                },
                categoryType: {
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

        //验证增加角色表单
        $("#CategoryForm").validate(options);
        
    };

  //绑定日历
    if (jQuery().datepicker) {
    	$('.date-picker').datepicker({
            format: 'yyyy-mm-dd',
            weekStart: 1,
            startDate: new Date(),
            todayBtn: 'linked',
            rtl: App.isRTL(),
            language: "zh-CN",
            autoclose: true
        });
    }
    
    return {
        init: function () {
            initEvent();
        }
    };
}();




