//产品积分列表相关操作
var Product_AddProductManager = function () {

    //初始化页面相关按钮事件
    var initEvent = function () {
        //$("#allSearch").hide();
        $("#singleAdd").on("click", function(){
        	
        	setPreferredProductTableProduct();
        	
        	if(!myValidate()){
        		return;
        	}
        	if($("#giftPointMoney").val()!=""){
        		$("#addMoneyHidden1").val(Math.round($("#giftPointMoney").val()*100));
        	}
        	if($("#discountPointMoney").val()!=""){
        		$("#addMoneyHidden2").val(Math.round($("#discountPointMoney").val()*100));
        	}
        	if(document.getElementById('isSale').checked==true){
            	$("#isSale").val('').val("0");
            }else{
            	$("#isSale").val('').val("1");
            }
        	if(document.getElementById('isSet').checked==true){
        		$("#isSet").val('').val("yes");
        	}else{
        		$("#isSet").val('').val("no");
        	}
            var productForm = $("#productForm");
            if(!productForm.valid()){
                return;
            }
            var requestURL = "addProduct.html";
            var tips = "增加失败!";
            if($("#productId").val() != ''){
                requestURL = "addProduct.html";
                tips = "修改失败!";
            }
            console.log($("#isSale").val());
            console.log($("#isSet").val());
            console.log(productForm.serialize());
//            var param = productForm.serialize()+$("#incomeProductForm").serialize() +"&"+ $("#micromallProductForm").serialize()
            var param = productForm.serialize()+"&"+ $("#micromallProductForm").serialize()
            +"&"+ $("#giftForm").serialize()
            +"&originalPrice="+$("#originalPrice").val()*100
            + "&discountPrice="+$("#discountPrice").val()*100
            + "&isSale="+$("#isSale").val()
            + "&isSet="+$("#isSet").val();
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
        	setPreferredProductTableProduct();
            var productForm = $("#productForm");
            var micromallProductForm = $("#micromallProductForm").serialize();
            var giftForm = $("#giftForm").serialize();
            if(!productForm.valid()){
                return;
            }
            var param = productForm.serialize()+"&"+micromallProductForm+"&"+giftForm;
            $.ajax({
                type: "POST",
                url: "addProduct.html",
                data: param,
                dataType: "json",
                success: function (data) {
                    if (data.code == 0) {
                       bootbox.alert("增加成功!");
                        //清除页面数据
                       $("#productName").val('');
                       $("#productCode").val('');
                       $("#productPoint").val('');
                       $("#barCode").val('');
                       $("#productIntroduce").val('');
                       $("#slogan").val('');
                    }else{
                        bootbox.alert("增加失败!");
                    }
                }
            });
        });

        //增加产品删除滚动图片
        $("#deleteRollimg1").on("click",function(){
        	$("#microproductrollimg1").val("");
        });
        //增加产品删除滚动图片
        $("#deleteRollimg2").on("click",function(){
        	$("#microproductrollimg2").val("");
        });
        //增加产品删除滚动图片
        $("#deleteRollimg3").on("click",function(){
        	$("#microproductrollimg3").val("");
        });
        //增加产品删除滚动图片
        $("#deleteRollimg4").on("click",function(){
        	$("#microproductrollimg4").val("");
        });
        //增加产品删除滚动图片
        $("#deleteRollimg5").on("click",function(){
        	$("#microproductrollimg5").val("");
        });
        
        //积分加钱购
        $("#giftPointMoneyGO").on("click",function(){
        	if($("#giftPointMoneyGO").val()=="开启积分加钱购"){
        		$("#addMoneyGo1").show();
            	$("#giftPointMoneyGO").val("关闭积分加钱购");
        	}else{
        		$("#addMoneyGo1").hide();
        		$("#giftPointMoney").val("");
        		$("#addMoneyHidden1").val("");
            	$("#giftPointMoneyGO").val("开启积分加钱购");
        	}
        });
        $("#discountPointMoneyGO").on("click",function(){
        	if($("#discountPointMoneyGO").val()=="开启积分加钱购"){
        		$("#addMoneyGo2").show();
            	$("#discountPointMoneyGO").val("关闭积分加钱购");
        	}else{
        		$("#addMoneyGo2").hide();
        		$("#discountPointMoney").val("");
        		$("#addMoneyHidden2").val("");
            	$("#discountPointMoneyGO").val("开启积分加钱购");
        	}
        });

        var options = {
            errorElement: 'span',
            errorClass: 'help-block',
            focusInvalid: false,
            ignore: "",
            rules: {
                productName: {
                    minlength: 2,
                    required: true
                },
                productCode: {
                    minlength: 2,
                    maxlength: 4,
                    required: true
                },
                barCode: {
                	minlength: 2,
                	maxlength: 15,
                	required: true
                },
                brandCode: {
                    required: true
                },
                productPoint: {
                	number:true,
                    required: true
                },
                originalPrice: {
                	number:true,
                	required: true
                },
                discountPrice: {
                	number:true,
                	required: true
                },
                giftPoint: {
                	number:true
                },
                discountPoint: {
                	number:true
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
        $("#productForm").validate(options);
        
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
//                	console.log(type);
                    $.each(data, function(index, value){
                    	result += "<label class='checkbox-inline' style='width:160px;margin-left:0px;'><input type='checkbox' name='productCategoryId"+type+"'" +
                    			" id='"+value.categoryId+"' " +
                    					" value='"+value.categoryId+"'>"+value.categoryName+"</label>";
                    	//result += "<label class='checkbox-inline'><input type='checkbox' name='productCategoryId"+type+"' id='"+value.categoryId+"' style='margin-left:-10px;' value='"+value.categoryId+"'>"+value.categoryName+"</label>";
                    });
//                    console.log(result);
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
       $("#sequenceTypeZeroSpan").hide();
       $("#sequenceTypeOneSpan").hide();
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
       $("[name='productAdType'][checked]").each(function(){
//    	   alert($(this).val());
       });
       $("#productAdTypeZero").click( function(){
    	   if($(this).attr("checked") == "checked"){ 
    		   $("#sequenceTypeZeroSpan").show();
           }else{
        	   $("#sequenceTypeZeroSpan").hide();
        	   $("#sequenceTypeZero").val();
           }
           
       });
       $("#productAdTypeOne").on("change", function(){
    	   if($(this).attr("checked") == "checked"){ 
        	   $("#sequenceTypeOneSpan").show();
           }else{
        	   $("#sequenceTypeOneSpan").hide();
        	   $("#sequenceTypeOne").val("");
           }
       });
       
//       $("productAdType").change();
       
       $.ajax({
           url: "getProdSeqByProductId.html?productId="+$("#productId").val(),
           type:"get",
           success:function(rs){
               if(rs.code==0){
                   $.map(rs.data.list,function(item,index){
                	   if(item.productAdType == 0){
                		   $("[name='productAdType'][value='0']").attr('checked','true');
                		   $("#sequenceTypeZeroSpan").show();
                		   $("#sequenceTypeZero").val(item.sequence);
                	   }
                	   if(item.productAdType == 1){
                		   $("[name='productAdType'][value='1']").attr('checked','true');
                		   $("#sequenceTypeOneSpan").show();
                		   $("#sequenceTypeOne").val(item.sequence);
                	   }
                   });
                   
               }
           }
       });
    };
    

    return {
        init: function () {
            initEvent();
            initData();
        },
    };
}();

/**设置推荐产品*/
function setPreferredProductTableProduct() {
	var str = [];
	$('#preferredProductTable' + ' tbody tr .checkboxes:checked').each(function() {
		this.checked = !this.checked;
		str += $(this).val() + ",";
	});
	if(str!=""){
		str = str.substring(0, str.length - 1);
	}
	$("#preferredProductId").val(str);
}

function myValidate(){
	//var ex = /^\d+$/;
	var giftPointMoney = $("#giftPointMoney").val();
	var discountPointMoney = $("#discountPointMoney").val();
	if(discountPointMoney!="" && isNaN(discountPointMoney)){
		alert("会员日优惠积分加钱购必须为数字");
		return false;
	}
	if(giftPointMoney!="" && isNaN(giftPointMoney)){
		alert("兑换礼品积分加钱购必须为数字");
		return false;
	}
	return true;
}

function initData(){
	if($("#productId").val() != ''){
		if($("#addMoneyHidden1").val()!=""){
			$("#addMoneyGo1").show();
			var a = $("#addMoneyHidden1").val()/100;
			$("#giftPointMoney").val(a);
	    	$("#giftPointMoneyGO").val("关闭积分加钱购");
		}
		if($("#addMoneyHidden2").val()!=""){
			$("#addMoneyGo2").show();
			var b = $("#addMoneyHidden2").val()/100;
			$("#discountPointMoney").val(b);
			$("#discountPointMoneyGO").val("关闭积分加钱购");
		}
		
	}
}
