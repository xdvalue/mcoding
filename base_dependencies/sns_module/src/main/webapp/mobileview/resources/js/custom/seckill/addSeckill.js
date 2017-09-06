//产品积分列表相关操作
var seckill_addSeckillManager = function () {

    //初始化页面相关按钮事件
    var initEvent = function () {
		// $("#allSearch").hide();
		$("#singleAdd").on("click", function() {
			var seckillForm = $("#seckillForm");
			if (!seckillForm.valid()) {
				return;
			}
			var requestURL = "addSeckill.html";
			var tips = "增加失败!";
			if ($("#id").val() != '') {
				requestURL = "addSeckill.html";
				tips = "修改失败!";
			}
			console.log(seckillForm.serialize());
			var param = seckillForm.serialize();
			$.ajax({
				type : "POST",
				url : requestURL,
				data : param,
				dataType : "json",
				success : function(data) {
					if (data.code == 0) {
						$("#backListPage").click();
					} else {
						bootbox.alert(tips);
					}
				}
			});
        });
		
		$("#brandcode").on("change", function() {
        	var productid = $("#productid");
			productid.empty();
			$.ajax({
				type : "POST",
				url : "getAllProduct.html?brandCode=" + $(this).val(),
				dataType : "json",
				success : function(data) {
					if (data.code == 0) {
						var proList = data.data.proList;
						var o = eval(data.data.proList);
						console.log(o[5].productId);
						for(var i = 0;i < o.length;i++){
							productid.append("<option value='"+o[i].productId+"'>"+o[i].productName+"</option>");
						}
					} else {
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
            	sku: {
                    required: true
                },
            	needpoint: {
                    required: true
                },
            	ordernum: {
                    required: true
                },
            	status: {
                    required: true
                },
            	starttime: {
                    required: true
                },
            	endtime: {
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
        $("#seckillForm").validate(options);
        
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
	
	var initProduct = function (){
		var hidBrandCode = $("#hidBrandcode");
		var brandcodeVal = hidBrandCode.val();
        var productid = $("#productid");
		var brandcode = $("#brandcode");
		if(brandcodeVal != ''){
			brandcode.val(brandcodeVal);
			$.ajax({
				type : "POST",
				url : "getAllProduct.html?brandCode=" + brandcodeVal,
				dataType : "json",
				success : function(data) {
					if (data.code == 0) {
						var brandcode = $("#brandcode");
						var proList = data.data.proList;
						var o = eval(data.data.proList);
						console.log(o[5].productId);
						for(var i = 0;i < o.length;i++){
							productid.append("<option value='"+o[i].productId+"'>"+o[i].productName+"</option>");
						}
						var hidProductid = $("#hidProductid").val();
						productid.val(hidProductid);
					} else {
					}
				}
			});
		}
	};

    return {
        init: function () {
            initEvent();
			initProduct();
        },
    };
}();