//卡券列表相关操作
var card_addCardTypeManager = function () {
    //初始化页面相关按钮事件
    var initEvent = function () {
        $("#singleAdd").on("click", function(){
            var cardTypeForm = $("#cardTypeForm");
            if(!cardTypeForm.valid()){
                return;
            }
            var requestURL = "cardType/createCardType.html";
            var tips = "增加失败!";
            var productList = new Array();
            var productInfo = {
            		productid : '',
            		productName: ''
            }
            productInfo.productName = $("#productid").find("option:selected").text();
            productInfo.productid = $("#productid").val();
            productList.push(productInfo);
            console.log(productList);
        	var data = serializeObject($("#cardTypeForm").serializeArray());
        	data.leastcost = $("#leastcost").val()*100;
        	data.reducecost = $("#reducecost").val()*100;
        	if($("#productid").val()=="" || $("#productid").val()==null){
        	}else{
        		data.productList = productList;
        	}
        	var formJson = JSON.stringify(data);
        	console.log(formJson);
            $.ajax({
                type: "POST",
                url: requestURL,
                data: formJson,
                dataType: "json",
                contentType : "application/json; charset=utf-8",
                success: function (data) {
                    if (data.status == "00") {
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
                name: {
                    required: true
                },
                cardtype: {
                	required: true
                },
                cardcount: {
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
        $("#cardTypeForm").validate(options);
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
                		result += "<option productName='"+value.productName+"' value="+value.productId+" selected>"+value.productName+"</option>";
                	}else{
                		result += "<option productName='"+value.productName+"' value="+value.productId+">"+value.productName+"</option>";
                	}
                });
                $("#productid").empty().append(result).change();
        	}
            	
        });
    	//绑定日历
        if (jQuery().timepicker) {
        	$('.timepicker').timepicker({
        		  template: 'dropdown' ,
                showMeridian: false,
                autoclose: true,
                language: "zh-CN",
                defaultTime:""
            });
        }
    	  
      	
          //绑定日历
          if (jQuery().datepicker) {
          	$('.date-picker').datepicker({
          		format: 'yyyy-mm-dd hh:ii',
                  weekStart: 1,
//                  minuteStep: 10,
                  todayBtn: 'linked',
                  rtl: App.isRTL(),
                  language: "zh-CN",
                  //minView: 'hour',　　　　//日期时间选择器所能够提供的最精确的时间选择视图。
                  autoclose: true
              });
          }

          if (jQuery().datetimepicker) {
          	$(".form_datetime").datetimepicker({
          		format: 'yyyy-mm-dd hh:ii:ss',
          		weekStart: 1,
          		todayBtn: 'linked',
          		rtl: App.isRTL(),
          		language: "zh-CN",
          		autoclose: true
          	});
          }
          
          $("#leastcostDIV").hide();
          $("#reducecostDIV").hide();
          $("#productidDIV").hide();
          $("#codeDIV").hide();
          $("#cardidDIV").hide();
          $("#cardtype").on("change", function(){
              var type = $(this).val();
              if(type==''){
                  return;
              }
              if(type=="CASH"){
           	   $("#leastcostDIV").show();
           	   $("#reducecostDIV").show();
           	   $("#cardidDIV").show();
           	   $("#productidDIV").hide();
           	   $("#codeDIV").hide();
              }else if(type="ACTIVITY"){
           	   $("#leastcostDIV").hide();
           	   $("#cardidDIV").hide();
           	   $("#reducecostDIV").show();
           	   $("#productidDIV").show();
           	   $("#codeDIV").show();
              }
          });
          $("#cardtype").change();
    };
    
    $("#fixedbegintime").prop("disabled",true);
    $("#fixedterm").prop("disabled",true);
    $("[name=timetype]").on("click", function(){
    	var type = $(this).val();
    	if(type==''){
            return;
        }
    	if(type=="FIX_TERM"){
    		$("#begintime").prop("disabled",true);
    	    $("#endtime").prop("disabled",true);
    	    $("#fixedbegintime").prop("disabled",false);
    	    $("#fixedterm").prop("disabled",false);
    	}else{
    		$("#fixedbegintime").prop("disabled",true);
    	    $("#fixedterm").prop("disabled",true);
    	    $("#begintime").prop("disabled",false);
    	    $("#endtime").prop("disabled",false);
    	}
    });
    return {
        init: function () {
            initEvent();
        },
    };
}();




