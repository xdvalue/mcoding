//产品积分列表相关操作
var Product_AddProductSet = function () {
	var productListTxt = '';
	var brandCode = '';
	
    //初始化页面相关按钮事件
    var initEvent = function () { 
        $("#singleAdd").on("click", function(){
        	setProductSetTableProduct();
            var productSetForm = $("#productSetForm");
			var requestURL = "product/updateProductSet.html";
            var param = productSetForm.serialize();
            $.ajax({	
                type: "POST",
                url: requestURL,
                data: param,
                dataType: "json",
                success: function (data) {
                    if (data.code == 0) {
                        $("#backListPage").click();
                    }else{
                        bootbox.alert("系统出错!");
                    }
                }
            });
        });
        
        $("#brandCode").on("change", function(){
			initProductList();
        });
		
		$("#addSetProduct").on("click", function(){
			$("#set_div").append(productListTxt);
		});
		
		$("#delSetProduct").on("click", function(){
			$("div[name='add_div']").last().remove();
		});

    };
	
	    //初始化页面相关按钮事件
    var initProductList = function () {
		if(brandCode == null || brandCode == ''){
			brandCode = $("#brandCode").val();
		}
		$.ajax({
			type : "POST",
			url : "getAllProduct.html?brandCode=" + brandCode + "&isSet=no",
			dataType : "json",
			async : false,
			success : function(data) {
				if (data.code == 0) {
					var proList = data.data.proList;
					var o = eval(data.data.proList);
					productListTxt = "<div name='add_div'><select name='setId' style='margin-bottom:10px; width:90px;' class='form-control input-medium select2me'>"
					productListTxt += "<option value=''>套餐子产品</option>";
					for(var i = 0;i < o.length;i++){
						productListTxt += "<option value='"+o[i].productId+"'>"+o[i].productName+"</option>";
					}
					productListTxt += "</select>";
					productListTxt += "<input type='number' value='1' name='setNum' class='form-control input-small' placeholder='数量'><hr></div>";
				}
			}
		}); 
	};
	
	var initSetLi = function (){
    	var ids = $("#productSetId").val().split(",");
    	var nums = $("#productSetNum").val().split(",");
		initProductList();
		for(var i = 0; i < ids.length; i++){
			$("#set_div").append(productListTxt);
		}
		var idx = 0;
		$("select[name='setId']").each(function(){
			$(this).val(ids[idx]);
			idx ++;
		});
		idx = 0;
		$("input[name='setNum']").each(function(){
			$(this).val(nums[idx]);
			idx ++;
		});
    };

    return {
        init: function () {
			initSetLi();
            initEvent();
			initProductList();
        },
    };
}();


/**设置套餐产品*/
function setProductSetTableProduct() {
	var idStr = [];
	var numStr = [];
	$("select[name=setId]").each(function(){
		idStr += $(this).val() + ",";
	});
	$("input[name=setNum]").each(function(){
		numStr += $(this).val() + ",";
	});
	if(idStr!=""){
		idStr = idStr.substring(0, idStr.length - 1);
	}
	if(numStr!=""){
		numStr = numStr.substring(0, numStr.length - 1);
	}
	$("#productSetId").val(idStr);
	$("#productSetNum").val(numStr);
}