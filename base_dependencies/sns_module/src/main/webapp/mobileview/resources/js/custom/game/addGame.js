﻿//产品积分列表相关操作
var game_addGameManager = function () {

    //初始化页面相关按钮事件
    var initEvent = function () {
        $("#singleAdd").on("click", function(){
        	var obj = new Object();
            var submitForm = $("#submitForm");
            var submitForm1 = $("#submitForm1");
            var submitForm2 = $("#submitForm2");
            var submitForm3 = $("#submitForm3");
            if(!submitForm.valid()){
                return;
            }
            var requestURL = "addGame.html";
            var tips = "增加失败!";
            if($("#id").val() != ''){
                requestURL = "addGame.html";
                tips = "修改失败!";
            }
            var game = JSON.stringify(serializeObject(submitForm.serializeArray()));
            var arr=new Array();
            var obj = new Object();
            var game = serializeObject(submitForm.serializeArray());
            console.log(submitForm3.serializeArray().length);
            /*if(submitForm1.serializeArray().length>0){
            	
            }*/
            arr.push(serializeObject(submitForm1.serializeArray()));
            arr.push(serializeObject(submitForm2.serializeArray()));
            arr.push(serializeObject(submitForm3.serializeArray()));
            var gamePrizes = arr;
            obj.game = game;
            obj.gamePrizes = gamePrizes;
           $.ajax({
                type: "POST",
                url: requestURL,
                async: true,
        		global: false,
        		dataType: "json",
        		contentType: "application/json; charset=utf-8",
                data: JSON.stringify(obj),
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
                url: "addGame.html",
                data: submitForm.serialize(),
                dataType: "json",
                success: function (data) {
                    if (data.code == 0) {
                       bootbox.alert("增加成功!");
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
            	gamename: {
                    required: true
                },
                gamestarttime: {
                    required: true
                },
                gameendtime: {
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
        
      //绑定日历
        if (jQuery().datepicker) {
        	$('.date-picker').datepicker({
        		format: 'yyyy-mm-dd',
                weekStart: 1,
                todayBtn: 'linked',
                rtl: App.isRTL(),
                language: "zh-CN",
                autoclose: true
            });
        }
        
        if (jQuery().datetimepicker) {
        	$(".datetime_picker").datetimepicker({
        		format: 'yyyy-mm-dd hh:ii:ss',
        		weekStart: 1,
        		todayBtn: 'linked',
        		rtl: App.isRTL(),
        		language: "zh-CN",
        		autoclose: true
        	});
        }
        
    };
    

    return {
        init: function () {
            initEvent();
        },
    };
}();




