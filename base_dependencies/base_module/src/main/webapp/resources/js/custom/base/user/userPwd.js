var User_PwdMgr = function () {
	//vilidation表单验证
    var handleValidation1 = function() {
    	var options = {
            //$('#changPwdForm').validate({
                errorElement: 'span', //default input error message container
                errorClass: 'help-block', // default input error message class
                focusInvalid: false, // do not focus the last invalid input
                ignore: "",
                rules: {
	                	oldPwd : {
	                		required: true,
	                	},
                	   password: {
                		minlength: 6,
                	    required: true,
                	   },
                	   password2: {
                		   minlength: 6,
                		   equalTo:"#password",
                		   required: true,
                        
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
//                    afterFormValid();
                },
           // });
    	};
    	//验证增加角色表单
        $("#changPwdForm").validate(options);
    };
    
    $("#changPwdBtn").on("click", function(){
    	//验证修改密码
    	 //验证增加角色表单
    	 if(!$('#changPwdForm').valid()){
             return;
         }
        //验证部分
    	 var oldPwd = $("#oldPwd").val();
     	$.ajax({
            type: "post",
            data : {"oldPwd":oldPwd},
            url: "checkOldPwd",
            success: function (data) {
               if(!data || data != 1){
                   $("#checkMessage").html("旧密码输入不正确");
                   return false;
                }else{
                	if(!$('#changPwdForm').valid()){
                        return;
                    }
                    changPwd();
                }
            }, error : function(err) {
                 $("#checkMessage").html("旧密码验证失败");
     			return false;
    		   }               
     	}); 
    });
    
    //修改密码
    var changPwd = function(){
    	$.ajax({
            type: "post",
            data :  $("#changPwdForm").serialize(),
            url: "doChangePwd.html",
            success: function (data) {
//               var url ="./forgetPwd2.html";
//               window.location =url;
            	$("#checkMessage").html("修改成功");
            }, 
            error : function(err) {
     			return false;
			   },               
    	});
    };
	
    return {
        init: function () { 
        	handleValidation1();
        },
    };
}();

