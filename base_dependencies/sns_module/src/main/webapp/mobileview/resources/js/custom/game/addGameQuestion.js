//产品积分列表相关操作
var game_addGameQuestionManager = function () {

    //初始化页面相关按钮事件
    var initEvent = function () {
        //$("#allSearch").hide();
        $("#singleAdd").on("click", function(){
            var submitForm = $("#submitForm");
            if(!submitForm.valid()){
                return;
            }
            var requestURL = "addGameQuestion.html";
            var tips = "增加失败!";
            if($("#id").val() != ''){
                requestURL = "addGameQuestion.html";
                tips = "修改失败!";
            }
            var param = submitForm.serialize();
            console.log(param);
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
                url: "addGameQuestion.html",
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
            	questiontitle: {
                    required: true
                },
                option1: {
                    required: true
                },
                answer: {
                	number:true,
                	required: true
                },
                rightreply: {
                	required: true
                },
                wrongreply: {
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
        
        //定义所有查找类型，数组
        var searchTypeArray = new Array('need', 'people', 'ingredient', 'ingredientJLD', 'function');
        
       for (var i = 0; i < searchTypeArray.length; i++) {
        	var type = searchTypeArray[i];
        	//加载按需求查找类型
        	$.ajax({
                type : "post",
                dataType : "json",
                async: false,
                url : "queryCategoryByType.html",
                data : {type : type},
                success : function(data){
                	var result = "";
                	//添加页
                	console.log(type);
                    $.each(data, function(index, value){
                    	result += "<label class='checkbox-inline' style='width:160px;margin-left:0px;'><input type='checkbox' name='productCategoryId"+type+"'" +
                    			" id='"+value.categoryId+"' " +
                    					" value='"+value.categoryId+"'>"+value.categoryName+"</label>";
                    	//result += "<label class='checkbox-inline'><input type='checkbox' name='productCategoryId"+type+"' id='"+value.categoryId+"' style='margin-left:-10px;' value='"+value.categoryId+"'>"+value.categoryName+"</label>";
                    });
                    console.log(result);
                    $("#checkboxId"+type).empty().append(result).change();
            		$.ajax({
                        type : "post",
                        dataType : "json",
                        url : "queryProductCategoryByProductId.html",
                        data : {productId : $("#productId").val()},
                        success : function(data2){
                        	var categoryId = new Array();
                        	$.each(data2, function(index2, value2){
                        		categoryId.push(value2.categoryId);
                            });
                        	for (var i=0;i<categoryId.length;i++){
                        		$.each(data, function(index, value){
                           		 if(categoryId[i] == value.categoryId){
                           			 $("[id = "+value.categoryId+"]:checkbox").attr("checked", true);
                                	}
                                });
                        	}
                        	
                        }
                    });
            	}
                	
            });
		}
       
       $("#MRMJ_productSearch").hide();
	   $("#JLD_productSearch").hide();
       //选择类型事件
       $("#brandCode").on("change", function(){
           var type = $(this).val();
           if(type==''){
               return;
           }
           if(type=="MRMJ"){
        	   $("#MRMJ_productSearch").show();
        	   $("#JLD_productSearch").hide();
           }else{
        	   $("#MRMJ_productSearch").hide();
        	   $("#JLD_productSearch").show();
           }
           
       });
       $("#brandCode").change();
    };
    

    return {
        init: function () {
            initEvent();
        },
    };
}();




