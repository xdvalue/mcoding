//产品积分列表相关操作
var RuleEngine_AddRuleEngineConfManager = function() {
	// 初始化页面相关按钮事件
	var initEvent = function() {
		
		$("#singleAdd").on("click", function() {
			var ruleEngineConfigForm = $("#ruleEngineConfigForm");
			
			if (!ruleEngineConfigForm.valid()) {
				return;
			}
			
			var requestURL = "saveRuleEngineConfig.html";
			var tips = "增加失败!";
			if ($("#id").val() != '') {
				requestURL = "saveRuleEngineConfig.html";
				tips = "修改失败!";
			}
			var param = ruleEngineConfigForm.serialize();
			$.ajax({
				type : "POST",
				url : requestURL,
				data : param,
				dataType : "json",
				success : function(data) {
					if (data.code == 0) {
						$("#backListPage").click();
					} else {
						bootbox.alert(tips+data.msg);
					}
				}
			});
		});

		$("#nextAdd").on("click", function() {
			var ruleEngineConfigForm = $("#ruleEngineConfigForm");
			if (!ruleEngineConfigForm.valid()) {
				return;
			}
			var param = ruleEngineConfigForm.serialize();
			$.ajax({
				type : "POST",
				url : "saveRuleEngineConfig.html",
				data : param,
				dataType : "json",
				success : function(data) {
					if (data.code == 0) {
						bootbox.alert("增加成功!");
						// 清除页面数据
						document.getElementById("ruleEngineConfigForm").reset(); 
						ruleTypeChangeClearInput();
					} else {
						bootbox.alert("增加失败!"+data.msg);
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
				ruleName : {
					minlength : 2,
					required : true
				},
				productId : {
					validProductId : true
				},
				/*origPrice : {
					required : true
				},*/
				ruleType : {
					required : true
				},
				promoPrice : {
					validPromoPrice : true
				},
				purchaseNum : {
					validPurchaseNum : true
				},
				promoMinLimit : {
					validPromoMinLimit : true
				},
				returnBack : {
					validReturnBack : true
				},
				giftProductId : {
					validGiftProductId : true
				},
				giftNum : {
					validGiftNum : true
				},
				startTimeStr : {
					validTime : true
				},
				endTimeStr : {
					validTime : true
				},
				brandCode : {
					validBrandCode : true
				}
				
				
			},
			messages: {  
	            ruleName: {  
	                required: "规则名称将作为提交订单的优惠信息提示，请认真填写",  
	                minlength: "规则名称将作为提交订单的优惠信息提示，请认真填写" 
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
		
		var positiveIntegerReg = /^[1-9]*[1-9][0-9]*$/;
		//价格（单位：元）正则
		//var priceReg = /^\+?(?:[1-9]\d*(?:\.\d{1,2})?|0\.(?:\d{1,2}))$/;
		//价格（单位：分）正则
		var priceReg = /^[1-9]*[1-9][0-9]*$/;
		
		jQuery.validator.addMethod("validPromoPrice", function(value, element) {
			var ruleType = $("#ruleType").val();
			var reg = priceReg;
			if(ruleType == 3 || ruleType == 5){
				if(value == ""){
					return false;
				}else{
					return this.optional(element) || (reg.test(value));
				}
			}else{
				return true;
			}
		}, "促销价格式错误");
		
		
		jQuery.validator.addMethod("validProductId", function(value, element) {
			var ruleType = $("#ruleType").val();
			if(ruleType != 1 && ruleType != 2 && ruleType != 8){
				if(value == ""){
					return false;
				}else{
					return true;
				}
			}else{
				return true;
			}
		}, "请选择规则对应的商品");
		
		jQuery.validator.addMethod("validBrandCode", function(value, element) {
			var ruleType = $("#ruleType").val();
			if(ruleType == 1 || ruleType == 2 || ruleType == 8){
				if(value == ""){
					return false;
				}else{
					return true;
				}
			}else{
				return true;
			}
		}, "请选择品牌");
		
		jQuery.validator.addMethod("validPurchaseNum", function(value, element) {
			var ruleType = $("#ruleType").val();
			var reg = positiveIntegerReg;
			if(ruleType == 3 || ruleType == 4){
				if(value == ""){
					return false;
				}else{
					return this.optional(element) || (reg.test(value));
				}
			}else{
				return true;
			}
		}, "购买数量输入错误");
		
		jQuery.validator.addMethod("validPromoMinLimit", function(value, element) {
			var ruleType = $("#ruleType").val();
			var reg = priceReg;
			if(ruleType == 1 || ruleType == 2){
				if(value == ""){
					return false;
				}else{
					return this.optional(element) || (reg.test(value));
				}
			}else{
				return true;
			}
		}, "价格格式错误");
		
		jQuery.validator.addMethod("validReturnBack", function(value, element) {
			var ruleType = $("#ruleType").val();
			var reg = priceReg;
			if(ruleType == 1){
				if(value == ""){
					return false;
				}else{
					return this.optional(element) || (reg.test(value));
				}
			}else{
				return true;
			}
		}, "价格格式错误");
		
		jQuery.validator.addMethod("validGiftProductId", function(value, element) {
			var ruleType = $("#ruleType").val();
			if(ruleType == 2 || ruleType == 4 || ruleType == 6){
				if(value == ""){
					return false;
				}else{
					return true;
				}
			}else{
				return true;
			}
		}, "请选择赠品");
		
		jQuery.validator.addMethod("validGiftNum", function(value, element) {
			var ruleType = $("#ruleType").val();
			var reg = positiveIntegerReg;
			if(ruleType == 2 || ruleType == 4 || ruleType == 6){
				if(value == ""){
					return false;
				}else{
					return this.optional(element) || (reg.test(value));
				}
			}else{
				return true;
			}
		}, "赠品数量输入错误");
		
		jQuery.validator.addMethod("validTime", function(value, element) {
			var ruleType = $("#ruleType").val();
			if(ruleType == 5){
				if(value == ""){
					return false;
				}else{
					return true;
				}
			}else{
				return true;
			}
		}, "时间输入错误");

		// 验证表单
		$("#ruleEngineConfigForm").validate(options);

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
        
        $("#ruleType").on("change", function(){
            var type = $(this).val();
            if(type==''){
                return;
            }
            //isClear为false时为编辑页面初始化，不清除原数据，否则重选规则类型时清除之前输入的内容
            if(isClear){
            	ruleTypeChangeClearInput();
            }else{
            	initForm();
            }
            //1:满减，2：满赠，3：买多份促销价，4：买多份送赠品，5：时间段促销价，6:时间段送赠品，7：累加多分送赠品，8：全场满额包邮，9：单品满额包邮
            if(type == 1){
            	$("#returnBackDiv").show();
            	$("#promoMinLimitDiv").show();
//            	$("#brandCodeDiv").show();
            }else if(type == 2){
            	$("#promoMinLimitDiv").show();
            	$("#giftProductDiv").show();
//            	$("#brandCodeDiv").show();
            }else if(type == 3){
            	$("#productIdDiv").show();
            	$("#origPriceDiv").show();
            	$("#promoPriceDiv").show();
            	$("#purchaseNumDiv").show();
            }else if(type == 4){
            	$("#productIdDiv").show();
            	$("#origPriceDiv").show();
            	$("#giftProductDiv").show();
            	$("#purchaseNumDiv").show();
            }else if(type == 5){
            	$("#productIdDiv").show();
            	$("#origPriceDiv").show();
            	$("#promoPriceDiv").show();
            	$("#startEndTimeDiv").show();
            }else if(type == 6){
            	$("#productIdDiv").show();
            	$("#origPriceDiv").show();
            	$("#startEndTimeDiv").show();
            	$("#giftProductDiv").show();
            }else if(type == 7){
            	$("#productIdDiv").show();
            	$("#origPriceDiv").show();
            	$("#giftProductDiv").show();
            	$("#purchaseNumDiv").show();
            }else if(type == 8){
            	$("#freightDiv").show();
            	$("#promoMinLimitDiv").show();
//            	$("#brandCodeDiv").show();
            }else if(type == 9){
            	$("#promoMinLimitDiv").show();
            	$("#productIdDiv").show();
            	$("#origPriceDiv").show();
            	$("#freightDiv").show();
            }else{
            	initForm();
            }
            isClear = true;
        });
        $("#ruleType").change();
        
        //选择商品获取商品价格
        $("#productId").on("change", function(){
            var productId = $(this).val();
            if(productId == '' || productId == null){
                return;
            }
            $.ajax({
        		type: "post",
        		url:  "getProductPriceByProductId.html?productId="+productId,
        		async: true,
        		global: false,
        		dataType: "json",
        		contentType: "application/json; charset=utf-8",
        		success: function(data) {
        			$("#origPrice").val(data);
        		}
        	});
            
        });
        
        $("#productId").change();
        
        //根据品牌更改商品与赠品下拉框选项
        $("#brandCode").on("change", function() {
        	var productId = $("#productId");
    		productId.empty();
    		productId.append("<option value=''>请选择商品...</option>");
    		var giftProductId = $("#giftProductId");
    		giftProductId.empty();
    		giftProductId.append("<option value=''>请选择赠品...</option>");
    		$.ajax({
    			type : "POST",
    			url : "getProductAndGiftListByBrandCode.html?brandCode=" + $(this).val(),
    			dataType : "json",
    			success : function(data) {
    				if (data.code == 0) {
    					$.map(data.data.productList,function(item,index){
    	                    if($("#productIdHidden").val() == item.productId){
    							productId.append("<option value='"+item.productId+"' selected>"+item.productName+"</option>");
    						}else{
    							productId.append("<option value='"+item.productId+"'>"+item.productName+"</option>");
    						}
    	                });
    					$.map(data.data.giftList,function(item,index){
    						if($("#giftProductIdHidden").val() == item.productId){
    							giftProductId.append("<option value='"+item.productId+"' selected>"+item.productName+"</option>");
    						}else{
    							giftProductId.append("<option value='"+item.productId+"'>"+item.productName+"</option>");
    						}
    	                });
    				} else {
    				}
    			}
    		});
    	});
        
        $("#brandCode").change();
	};
	
	
	

	
	var hideDivClearText = function(divId){
		$("#"+divId).hide();
		$("#"+divId).find('input').attr('value','');
		$("#"+divId).find('select').attr('value','');
	}
	
	var hideDiv = function(divId){
		$("#"+divId).hide();
	}
	
	var ruleTypeChangeClearInput = function(){
		hideDivClearText("promoPriceDiv");
		hideDivClearText("purchaseNumDiv");
		hideDivClearText("returnBackDiv");
		hideDivClearText("promoMinLimitDiv");
		hideDivClearText("startEndTimeDiv");
		hideDivClearText("giftProductDiv");
		hideDivClearText("productIdDiv");
//		hideDivClearText("brandCodeDiv");
		hideDivClearText("freightDiv");
		hideDivClearText("origPriceDiv");
	}
	
	var initForm = function(){
		hideDiv("promoPriceDiv");
		hideDiv("purchaseNumDiv");
		hideDiv("returnBackDiv");
		hideDiv("promoMinLimitDiv");
		hideDiv("startEndTimeDiv");
		hideDiv("giftProductDiv");
		hideDiv("productIdDiv");
//		hideDiv("brandCodeDiv");
		hideDiv("freightDiv");
		hideDiv("origPriceDiv");
	}
	
	return {
		init : function() {
			initForm();
			initEvent();
		},
	};
}();
