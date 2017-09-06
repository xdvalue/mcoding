//产品积分列表相关操作
var game_addGameQuestionManager = function() {
	// 初始化页面相关按钮事件
	var initEvent = function() {
		$("#singleAdd").on("click", function() {
			var submitForm = $("#submitForm");
			if (!submitForm.valid()) {
				return;
			}
			var requestURL = "updatesInvestigationQuestionByQuesionId.html";
			var tips = "修改失败!";
			var param = submitForm.serialize();
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

		var options = {
			errorElement : 'span',
			errorClass : 'help-block',
			focusInvalid : false,
			ignore : "",
			rules : {
				questiontitle : {
					required : true
				},
				answer : {
					number : true,
					required : true
				},
				rightreply : {
					required : true
				},
				option1:{
					required : true
				},
				wrongreply : {
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
		};
 
		// 验证表单
		$("#submitForm").validate(options);
		
		var buttonCount=5;
		for(var i=0;i<10;i++){
			$("#childButton"+buttonCount).bind("click", {index: buttonCount}, function(event){
				var c= event.data.index;
				$("#childQuestionId"+c).toggle();
				console.log("#childQuestionId"+c);
			});			
			buttonCount--;
		}
	};
	
	var count=0;
	var initQuestionOptions = function() {
		var result 	= '<div class="form-group">'
					+ 		'<label class="col-md-1 control-label">增加选项</label>'
					+ 		'<div class="col-md-2">'
					+ 			'<input type="text" name="newOption"'
					+ 				'class="form-control input-inline input-medium" placeholder="请输入新增选项">'
					+ 		'</div>'
					+ 		'<label class="col-md-2 control-label">增加关键词</label>'
					+ 		'<div class="col-md-3">'
					+ 			'<input type="text" name="newKey"'
					+ 				'class="form-control input-inline input-medium" placeholder="请输入对应的关键词">'
					+ 		'</div>'
					+ '</div>';
		$("#optDiv").append(result);
	};
	
	var deleteOption = function(optionId){
		$.post("deleteOptionByOptionId.html",
				{
					optionId:optionId
				},
				function(data,status){
					if(data.code==0){
						$("#backListPage").click();
						bootbox.alert("修改成功");
					}else{
						bootbox.alert("修改失败");
					}
				}
		);
	};
	
	return {
		init : function() {
			initEvent();
			$("#moreOption").on("click", function() {
				initQuestionOptions();
			});
			$(".deleteOption").on("click",function(){
				var optionId=$(this).attr("value");
				bootbox.confirm("您确定删除选项 ?",function(result){
					if(result){
						deleteOption(optionId);
					}
				});
				
			});
		},
	};
}();
