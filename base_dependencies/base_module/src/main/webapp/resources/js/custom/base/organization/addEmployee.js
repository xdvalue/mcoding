//产品积分列表相关操作
var ORG_EmpManager = function () {

    //初始化页面相关按钮事件
    var initEvent = function () {
        //选择用户角色
        //$('#roleSelect').multiSelect();


        //当输入框失去焦点时，便会触发Ajax提交查询用户账号
        $("#loginName").blur(function () {
            var loginName = $("#loginName").val();//定义登陆账户
            if($("#userId").val() != '' || $.trim(loginName)==''){
                return;
            }
            $.ajax({
                type: "POST",
                url: "queryUserByLoginName",
                data: {
                    "loginName": loginName
                },
                dataType: "json",
                success: function (data) {
                    if (data != null) {
                        $("#login").find("span").remove();
                        $("#login").append('<span class="help-block" style="color:#b94a48">登陆账户已被使用</span>');
                    }else{
                        $("#login").find("span").remove();
                    }
                }
            });
        });

            
        $("#singleAddEmp").on("click", function(){
            var empForm = $("#employeeForm");
            if(!empForm.valid()){
                return;
            }
            var requestURL = "addEmp";
            var tips = "增加失败!";
            if($("#userId").val() != ''){
                requestURL = "addEmp";
                tips = "修改失败!";
            }
            
            $.ajax({
                type: "POST",
                url: requestURL,
                data: empForm.serialize(),
                dataType: "json",
                success: function (data) {
                    if (data && data.status == '00') {
                        $("#backListPage").click();
                    }else{
                        bootbox.alert(tips);
                    }
                }
            });
        });
        
     

        $("#nextAddEmp").on("click", function(){
            var empForm = $("#employeeForm");
            alert(empForm);
            if(!empForm.valid()){
                return;
            }
            $.ajax({
                type: "POST",
                url: "addEmp",
                data: empForm.serialize(),
                dataType: "json",
                success: function (data) {
                    if (data && data.status == '00') {
                       bootbox.alert("增加成功!");
                        //清除页面数据
                       $("#loginName").val('');
                       $("#nickName").val('');
                       $("#password").val('');
                       $("#email").val('');
                       $("#mobilePhone").val('');
                       $("#qq").val('');
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
                loginName: {
                    minlength: 2,
                    maxlength: 24,
                    required: true
                },
                nickName: {
                    minlength: 2,
                    maxlength: 12,
                    required: true
                },
                password: {
                    minlength: 6,
                    maxlength: 10,
                    required: true
                },
                email: {
                    email : true
                },
                mobilePhone:{
                	maxlength: 11,
                	minlength: 11,
                	digits : true
                },
                qq:{
                    minlength: 6,
                    digits : true
                },
                levelType: {
                    required: true
                },
                levelId: {
                    required: true
                },
                entrytime : {
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

        //验证增加角色表单
        $("#employeeForm").validate(options);
    };

  //绑定日历
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




