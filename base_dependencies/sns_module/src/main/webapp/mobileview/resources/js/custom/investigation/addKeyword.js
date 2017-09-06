//产品积分列表相关操作
var Article_AddArticleDefManager = function() {
	
	// 初始化页面相关按钮事件
	var initEvent = function() {
		$("#addFormBtn").on("click",function(){
			$("#recommendDivId").append();
		});
		
		$("#singleAdd").on("click", function() {
			var articleDefForm = $("#articleDefForm");
			
			if (!articleDefForm.valid()) {
				return;
			}
			
			var requestURL = "manager/addKeywordAndRecommond.html";
			var tips = "增加失败!";
			if ($("#id").val() != '') {
				tips = "修改失败!";
			}
			
			var obj=new Object();
			
			var articleDef = serializeObject(articleDefForm.serializeArray());
			obj.keyword = articleDef;
			
			var formArr = new Array();
			for(var i=0;i<18;i++){
				formArr[i] = serializeObject($("#recForm"+i).serializeArray());
			}
			obj.recommends=formArr;
			
			$.ajax({
				type : "POST",
				url : requestURL,
				data : JSON.stringify(obj),
				dataType : "json",
				contentType : "application/json; charset=utf-8",
				success : function(data) {
					if (data.code == 0) {
						$("#backListPage").click();
					} else if(data.code == 1) {
						bootbox.alert(data.msg);
					} else{
						bootbox.alert(tips);
					}
				}
			});
		});

		var options = {
			errorElement : 'span',
			errorClass : 'help-block',
			focusInvalid : false,
			ignore : "",
			rules : {
				keyword : {
					required : true
				},
				company : {
					minlength : 2,
					maxlength : 40,
					required : true
				},
				title : {
					required : true
				},
				headimg : {
					required : true
				},
				qrcode : {
					required : true
				}
			},
			highlight : function(element) {
				$(element).closest('.form-group').addClass('has-error');
			},
			unhighlight : function(element) {
				$(element).closest('.form-group').removeClass('has-error');
			},
			success : function(label) {
				label.closest('.form-group').removeClass('has-error');
			}
		}

		// 验证表单
		$("#articleDefForm").validate(options);
		
	};
	
	//查询产品函数
	var queryProductData=function(){
		var productResult = "";
		$.ajax({
	        type : "post",
	        dataType : "json",
	        async: false,
	        url : "queryProductData.html",
	        data: {	
	        		iDisplayLength:"all",
	        		isSale:0
	        },
	        success : function(data){
	        	var result = "";
	        	result += "<option value=''>请选择推荐产品</option>";
	            $.each(data.queryResult, function(index, value){
	            	result += "<option value="+value.productId+">"+value.productName+"( "+value.productType+"类型 )"+"</option>";
	            });
	            result += "</optgroup>";
	            
	            productResult = result;
	    	}
	        	
	    });
		return productResult;
	}
	
	//初始化推荐产品
	var initRecommendProduct = function(){
		var productResult = queryProductData();
		for(var i=1;i<=18;i++){
			$("#recommendProduct"+i).empty().append(productResult).change();
			var productId = $("#hideRecommendProductId"+i).val();
			$("#recommendProduct"+i).find("option[value="+productId+"]").attr("selected",true);
		}
		
	}
	
	return {
		init : function() {
			initEvent();
			initRecommendProduct();
		},
	};
}();
