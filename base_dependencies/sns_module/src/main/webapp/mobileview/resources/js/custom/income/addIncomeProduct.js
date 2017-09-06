//产品积分列表相关操作
var income_addIncomeProductManager = function () {
    //初始化页面相关按钮事件
    var initEvent = function () {
        $("#singleAdd").on("click", function(){
            var incomeProductForm = $("#incomeProductForm");
            if(!incomeProductForm.valid()){
                return;
            }
            var requestURL = "addIncomeProduct.html";
            var tips = "增加失败!";
            if($("#productId").val() != ''){
                requestURL = "addIncomeProduct.html";
                tips = "修改失败!";
            }
            var param = $("#incomeProductForm").serialize()
            +"&level1="+$("#level1").val()*100
            +"&level2="+$("#level2").val()*100
            +"&level3="+$("#level3").val()*100
            + "&micromallprice="+$("#micromallprice").val()*100
            + "&level4="+$("#level4").val()*100;
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

        var options = {
            errorElement: 'span',
            errorClass: 'help-block',
            focusInvalid: false,
            ignore: "",
            rules: {
            	productid: {
                    required: true
                },
                channelsid: {
                    required: true
                },
                micromallprice: {
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
        $("#incomeProductForm").validate(options);
    	$.ajax({
            type : "post",
            dataType : "json",
            async: false,
            url : "queryProductData.html",
            data: {iDisplayLength:"all"},
            success : function(data){
            	var result = "";
            	var productid = $("#productidhidden").val();
            	//添加页
            	result += "<option value=''>请选择产品</option>";
                $.each(data.queryResult, function(index, value){
                	if(productid == value.productId){
                		result += "<option value="+value.productId+" selected>"+value.productName+"</option>";
                	}else{
                		result += "<option value="+value.productId+">"+value.productName+"</option>";
                	}
                	
                });
                console.log(result);
                $("#productid").empty().append(result).change();
        	}
            	
        });
    };
    
    return {
        init: function () {
            initEvent();
        },
    };
}();




