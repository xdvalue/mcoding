var Article_AddArticleDefManager = function() {

	//根据品牌查询产品
	var queryProductData=function(brandCode){
		$.ajax({
	        type : "post",
	        dataType : "json",
	        async: false,
	        url : "queryProductData.html",
	        data: {	
	        		iDisplayLength:"all",
	        		brandCode:brandCode
	        },
	        success : function(data){
	        	var arr = new Array();
	        	var productids = $("#recProductIdsId").val();
	        	arr = productids.split(",");
	        	
	        	//添加页
	        	var result = "";
	        	result += "<optgroup label='请选择产品'>";
	            $.each(data.queryResult, function(index, value){
	            	var productIdStr=value.productId.toString();
	            	if(arr.indexOf(productIdStr) != -1 ){
	            		result += "<option value="+value.productId+" selected>"+value.productName+"</option>";
	            	}else{
	            		result += "<option value="+value.productId+">"+value.productName+"</option>";
	            	}
	            });
	            result += "</optgroup>";
	            
	            var recPro1=$("#recProductArrayId").attr("value");
	            
	            $("#recProductArrayId").empty().append(result).change();

	            $("#recProductArrayId option[value=" + recPro1 + "]").attr("selected",
						"selected").change();
	    	}
	        	
	    });
	}
	
	//根据pid查询文章分类
	var queryArticleType = function(pid,title){
		var requestUrl = "";			
		if(title==1){
			requestUrl = "dtApi/getArticleTitleByPid.html";
		}
		if(title==2){
			requestUrl = "nailApi/getArticleTitleByPid.html";
		}
		if(title==3){
			requestUrl = "nmApi/getArticleTitleByPid.html";
		}
		$.ajax({
			type : "GET",
			url : requestUrl,
			data : {
				pId : pid
			},
			dataType : "json",
			success : function(data) {
				var firstLevel = $("#firstLevelId").val();
				var secondLevel = $("#secondLevelId").val();
				
				var articleTypeId = $("#articleType").val();
				var result="";
				if(pid==0){
					result += "<option value=''>请选择一级类别</option>";
					$.each(data.data, function(index, value){
						if(firstLevel==value.id){
							result += "<option value=\"" + value.id + "\" selected>" + value.title + "</option>";
						}else{
							result += "<option value=\"" + value.id + "\">" + value.title + "</option>";								
						}
					});
					$("#articleFirstLevelId").empty().append(result).change();										
				}else{
					$("#articleSecondLevelId").removeAttr("disabled");
					if(data.data.length==0){
						$("#articleSecondLevelId").attr("disabled","disabled");
						$("#articleSecondLevelId").empty().append("<option value=''>无二级类别</option>").change();
					}else{
						$.each(data.data, function(index, value){
							result += "<option value=\"" + value.id + "\">" + value.title + "</option>";
						});
						$("#articleSecondLevelId").empty().append(result).change();
					}
				}
			}
		});
	}
	
	// 初始化页面相关按钮事件
	var initEvent = function() {
		$("#singleAdd").on("click", function() {
			var articleDefForm = $("#articleDefForm");
			var labelSubmitForm1 = $("#labelSubmitForm1");
			var labelSubmitForm2 = $("#labelSubmitForm2");
			var labelSubmitForm3 = $("#labelSubmitForm3");
			
			if(!setArticleDefs()){
				return;
			};
			if (!articleDefForm.valid()) {
				return;
			}
			var requestURL = "addArticleDef.html";
			var tips = "增加失败!";
			if ($("#id").val() != '') {
				requestURL = "addArticleDef.html";
				tips = "修改失败!";
			}
			
			var articleDef = serializeObject(articleDefForm.serializeArray());
			var label1 = serializeObject(labelSubmitForm1.serializeArray());
			var label2 = serializeObject(labelSubmitForm2.serializeArray());
			var label3 = serializeObject(labelSubmitForm3.serializeArray());
			
			var obj=new Object();
			obj.articleDef=articleDef;
			
			var labels=new Array(3);
			labels[0]=label1;
			labels[1]=label2;
			labels[2]=label3;
			
			obj.labels=labels;
			
			$.ajax({
				type : "POST",
				url : requestURL,
				data : JSON.stringify(obj),
				dataType : "json",
				contentType : "application/json; charset=utf-8",
				success : function(data) {
					if (data.code == 0) {
						$("#backListPage").click();
					} else {
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
				title : {
					minlength : 2,
					required : true
				},
				brandCode : {
					required : true
				},
				content : {
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
		
		var tagCount=1;
		$("#tagButton").on("click",function(){
			if(tagCount<3){
				++tagCount;
				var htmlString = 
					"<form action='#' id='labelSubmitForm"+tagCount+"' class='form-horizontal'>"
					+	"<div class='form-group'>" 
					+		"<label class='col-md-3 control-label'>标签"+tagCount+"</label>"
					+			"<div class='col-md-1'>"
					+				"<input type='hidden' id='id' name='id'/>"
					+				"<input type='text' name='label' id='label'"
					+					"class='form-control input-inline input-medium'"
					+					"placeholder='请输入文章标签"+tagCount+"'>"
					+			"</div>";
					+	"</div>";
					+"</form>"
				$("#tagId").append(htmlString);
			}
			
		});
		
		//绑定一级分类触发
		$("#articleFirstLevelId").on("change",function(){
			var firstLevelId = $(this).val();
			queryArticleType(firstLevelId,1);
		});
		
		//绑定品牌触发
		$("#brandCodeBtn").change(function(){
			var bc = $(this).val();
			if("DT"==bc){
				queryArticleType(0,1);
			}else if("NAIL"==bc){
				queryArticleType(0,2);
			}else if("NM"==bc){
				queryArticleType(0,3);
			}
		});
		
		//推荐产品绑定触发
		$("#recProductBCId").change(function(){
			var recCode = $(this).val();
			queryProductData(recCode);
		});
		
	};
	
	//初始化推荐文章
	var initRecommend = function(){
		if($("#id").val() != ''){
			initSelectedArticleDefs();
		}
	}
	
	//初始化推荐产品
	var initProduct = function(){
		var recCode = $("#recProductBCId").val();
		if(recCode != ""){
			queryProductData(recCode);			
		}
	}
	
	//初始化文章类别
	var initArticleTitle = function(){
		var articleType = $("#brandCodeBtn").val();
		if(articleType=="DT"){
			queryArticleType(0,1);
		}else if(articleType=="NAIL"){
			queryArticleType(0,2);
		}else if(articleType=="NM"){
			queryArticleType(0,3);
		}
	}
	
	return {
		init : function() {
			initEvent();
			initRecommend();
			initProduct();
			initArticleTitle();
		},
	};
}();


/**设置分享的文章*/
function setArticleDefs() {
	var str = [];
	$('#articleDefTable' + ' tbody tr .checkboxes:checked').each(function() {
		this.checked = !this.checked;
		str += $(this).val() + ",";
	});
	if (str == "") {
		//alert("请至少选择一篇文章");
		return true;
	} else {
		str = str.substring(0, str.length - 1);
	}
	$("#recommend").val(str);
	return true;
}


/**编辑时带出选中文章的信息*/
function initSelectedArticleDefs(){
	var recommends=$("#recommend").val().split(",");
	$('#articleDefTable'+' tbody tr .checkboxes').each(function() {
		for(var i=0;i<recommends.length;i++){
			if($(this).val()==recommends[i]){
				this.checked="checked";
			}
		}
	});
}
