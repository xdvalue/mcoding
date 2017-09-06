var queryData=function(data){
	var result = "";
	var productid = $("#productidhidden").val();
	//添加页
	result += "<option value=''>请选择产品</option>";
    $.each(data, function(index, value){
    	if(productid == value.productid){
    		result += "<option value="+value.productid+" selected>"+value.productname+"</option>";
    	}else{
    		result += "<option value="+value.productid+">"+value.productname+"</option>";
    	}
    	
    });
    console.log(result);
    $("#productid").empty().append(result).change();
	/*var result = "";
	// 添加页
	$.each(data,function(index, value) {
		result += "<label class='radio-inline' style='width:160px;margin-left:0px;vertical-align:top'>"
				+ "<input type='radio' name='productid'"
				+ " id='"
				+ value.productid
				+ "' "
				+ " value='"
				+ value.productid
				+ "' class='{required:true}' >"
				+ value.productname
				+ "</label>";

	});
	$("#checkboxGift").empty().append(result);
	var checkedid = $("#checkedGiftId").attr("value");

	$("input[name='productid']").each(function() {
		var value = $(this).attr("value");
		if (checkedid == value) {
			$(this).attr("checked", true);
		}
	});*/
	
}

var queryProductData=function(brandCode){
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
}
//新增翻拍活动相关操作
var mallgame_addGamblingManager = function() {

	// 初始化页面相关按钮事件
	var initEvent = function() {
		$("#singleAdd").on("click", function() {
			var submitForm = $("#submitForm");
			var submitForm1 = $("#submitForm1");
			if (!submitForm.valid() && !submitForm1.valid()) {
				return;
			}
			if (!$("#productid").val()) {
				bootbox.alert("还未选择商品");
			}
			var requestURL = "addGameblingStake.html";
			var tips = "增加失败!";
			if ($("#id").val() != '') {
				requestURL = "addGameblingStake.html";
				tips = "修改失败!";
			}

			var obj = new Object();
			var gambling = serializeObject(submitForm.serializeArray());
			console.log(gambling);
			var gift = serializeObject(submitForm1.serializeArray());
			obj.gambling = gambling;
			obj.gift = gift;

			$.ajax({
				type : "POST",
				url : requestURL,
				async : true,
				global : false,
				dataType : "json",
				contentType : "application/json; charset=utf-8",
				data : JSON.stringify(obj),
				success : function(data) {
					if (data.code == 0) {
						$("#backListPage").click();
					} else {
						bootbox.alert(tips);
					}
				}
			});
		});

		$.ajax({
					type : "get",
					dataType : "json",
					url : "queryGamblingProducts.html",
					data : {
						brandCode : $("#brandcode").val(),
					},
					success : function(data) {
						queryData(data);
					}
				});

		$("#nextAdd").on("click", function() {
			var submitForm = $("#submitForm");
			var submitForm1 = $("submitForm1");
			if (!submitForm.valid()) {
				return;
			}
			if (!submitForm1.valid()) {
				return;
			}
			$.ajax({
				type : "POST",
				url : "addGame.html",
				data : submitForm.serialize(),
				dataType : "json",
				success : function(data) {
					if (data.code == 0) {
						bootbox.alert("增加成功!");
						// 清除页面数据
						$("#submitForm").find('input:type="text":').val('');
					} else {
						bootbox.alert("增加失败!");
					}
				}
			});
		});

		$("#brandCodeBtn").delegate("label","click",function(){ 
			 var brandCode= $(this).find("input").val();
			 $.ajax({
				type : "get",
				dataType : "json",
				url : "queryGamblingProducts.html",
				data : {
					brandCode : brandCode,
				},
				success : function(data) {
					queryData(data);
				}
			});
		});
		
		/*$("#mrmjBtn").on("click",function(){
			$("#brandcode").val("MRMJ");
			$.ajax({
				type : "get",
				dataType : "json",
				url : "queryGamblingProducts.html",
				data : {
					brandCode : "MRMJ",
				},
				success : function(data) {
					queryData(data);
				}
			});
		});*/
		/*$("#brandCodeBtn").on("click", function(){
			alert( $(this).val() );
		});*/
		/*$("#brandCodeBtn").on("click",function(){
			var brandCode = $(this).val();
			alert(brandCode);
			$.ajax({
				type : "get",
				dataType : "json",
				url : "queryGamblingProducts.html",
				data : {
					brandCode : brandCode,
				},
				success : function(data) {
					queryData(data);
				}
			});
		});*/
		
		var options = {
			errorElement : 'span',
			errorClass : 'help-block',
			focusInvalid : false,
			ignore : "",
			rules : {
				gamename : {
					required : true
				},
				gamestarttime : {
					required : true
				},
				gameendtime : {
					required : true
				},
				brandcode : {
					required : true
				},
				gainpoint : {
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
		$("#submitForm").validate(options);
		$("#submitForm1").validate(options);

		// 绑定日历
		if (jQuery().datepicker) {
			$('.date-picker').datepicker({
				format : 'yyyy-mm-dd',
				weekStart : 1,
				todayBtn : 'linked',
				rtl : App.isRTL(),
				language : "zh-CN",
				autoclose : true
			});
		}

		if (jQuery().datetimepicker) {
			$(".datetime_picker").datetimepicker({
				format : 'yyyy-mm-dd hh:ii:ss',
				weekStart : 1,
				todayBtn : 'linked',
				rtl : App.isRTL(),
				language : "zh-CN",
				autoclose : true
			});
		}

	};

	return {
		init : function() {
			initEvent();
		},
	};
}();
