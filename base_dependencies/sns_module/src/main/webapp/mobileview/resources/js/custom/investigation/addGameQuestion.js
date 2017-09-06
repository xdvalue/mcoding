//产品积分列表相关操作
var game_addGameQuestionManager = function() {
	// 初始化页面相关按钮事件
	var initEvent = function() {
		$("#singleAdd").on("click", function() {
			var submitForm = $("#submitForm");
			if (!submitForm.valid()) {
				return;
			}
			var requestURL = "addInvestigationQuestion.html";
			var tips = "增加失败!";
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

		$("#nextAdd").on("click", function() {
			var submitForm = $("#submitForm");
			if (!submitForm.valid()) {
				return;
			}
			$.ajax({
				type : "POST",
				url : "addInvestigationQuestion.html",
				data : submitForm.serialize(),
				dataType : "json",
				success : function(data) {
					if (data.code == 0) {
						bootbox.alert("增加成功 !");
						// 清除页面输入数据
						$("input").attr("value", "");
					} else {
						bootbox.alert("增加失败!");
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
		}

		// 验证表单
		$("#submitForm").validate(options);

		// 定义所有查找类型，数组
		var searchTypeArray = new Array('need', 'people', 'ingredient',
				'ingredientJLD', 'function');

		for (var i = 0; i < searchTypeArray.length; i++) {
			var type = searchTypeArray[i];
			// 加载按需求查找类型
			$
					.ajax({
						type : "post",
						dataType : "json",
						async : false,
						url : "queryCategoryByType.html",
						data : {
							type : type
						},
						success : function(data) {
							var result = "";
							// 添加页
							$
									.each(
											data,
											function(index, value) {
												result += "<label class='checkbox-inline' style='width:160px;margin-left:0px;'><input type='checkbox' name='productCategoryId"
														+ type
														+ "'"
														+ " id='"
														+ value.categoryId
														+ "' "
														+ " value='"
														+ value.categoryId
														+ "'>"
														+ value.categoryName
														+ "</label>";
											});
							$("#checkboxId" + type).empty().append(result)
									.change();
							$
									.ajax({
										type : "post",
										dataType : "json",
										url : "queryProductCategoryByProductId.html",
										data : {
											productId : $("#productId").val()
										},
										success : function(data2) {
											var categoryId = new Array();
											$
													.each(
															data2,
															function(index2,
																	value2) {
																categoryId
																		.push(value2.categoryId);
															});
											for (var i = 0; i < categoryId.length; i++) {
												$
														.each(
																data,
																function(index,
																		value) {
																	if (categoryId[i] == value.categoryId) {
																		$(
																				"[id = "
																						+ value.categoryId
																						+ "]:checkbox")
																				.attr(
																						"checked",
																						true);
																	}
																});
											}

										}
									});
						}

					});
		}

		$("#MRMJ_productSearch").hide();
		$("#JLD_productSearch").hide();
		// 选择类型事件
		$("#brandCode").on("change", function() {
			var type = $(this).val();
			if (type == '') {
				return;
			}
			if (type == "MRMJ") {
				$("#MRMJ_productSearch").show();
				$("#JLD_productSearch").hide();
			} else {
				$("#MRMJ_productSearch").hide();
				$("#JLD_productSearch").show();
			}

		});
		$("#brandCode").change();
	};

	var count = 0;
	var initQuestionOptions = function() {
		var result = "";
		for (var i = 0; i < 3; i++) {
			count++;
			result += '<div class="form-group">'
					+ 		'<label class="col-md-1 control-label">调查选项'+count+'</label>'
					+ 		'<div class="col-md-2">'
					+ 			'<input type="text" name="option" init="#"'
					+ 				'class="form-control input-inline input-medium" placeholder="请输入调查选项'+count+'">'
					+ 		'</div>'
					+ 		'<label class="col-md-2 control-label">选项'+count+'关键词</label>'
					+ 		'<div class="col-md-3">'
					+ 			'<input type="text" name="key"'
					+ 				'class="form-control input-inline input-medium" placeholder="请输入对应的关键词">'
					+ 		'</div>'
					+ 		'<div class="col-md-3">'
                    + 			'<button type="button" class="btn btn-success" id="childButton'+count+'">'
                    +				'<span class="glyphicon glyphicon-edit" aria-hidden="true"></span>'
                    +					' 编辑子问题'
                    +			'</button>'
                    + 		'</div>'
                    + '</div>'
                    + '<div id="childQuestionId'+count+'" style="display:none">'
					+ 	'<div class="form-group">'
					+ 		'<label class="col-md-2 control-label">跳转题目</label>'
					+ 		'<div class="col-md-3">'
					+ 			'<input type="text" name="cquestion"'
					+ 				'class="form-control input-inline input-medium" placeholder="请输入子题目">'
					+ 		'</div>'
					+ 	'</div>'
				/*	+ 	'<div class="form-group">'
					+ 		'<label class="col-md-2 control-label">子题目说明&nbsp<span>*</span></label>'
					+ 		'<div class="col-md-3">'
					+ 			'<input type="text" name="cdescription" class="form-control input-inline input-small" placeholder="请输入子题目说明">'
					+ 		'</div>'
					+ 	'</div>'
					+	'<div class="form-group">'
					+ 		'<label class="col-md-2 control-label">题目类型 <span class="required">*</span></label>'
					+ 		'<div class="col-md-4">'
					+			'<label class="radio-inline" style="width:160px;margin-left:0px;text-align:top">'
					+   			'<input type="radio" checked="checked" name="cradio" value="0" />单选</label>'
					+   		'<label class="radio-inline" style="width:160px;margin-left:0px;text-align:top">'
					+				'<input type="radio" name="cradio" value="1" />多选</label>'
					+		'</div>'
					+	'</div>'
				*/
					+ 	'<div class="form-group">'
					+ 		'<label class="col-md-2 control-label">调查选项1</label>'
					+ 		'<div class="col-md-2">'
					+ 			'<input type="text" name="coption1" init="#"'
					+ 				'class="form-control input-inline input-medium" placeholder="请输入调查选项1">'
					+ 		'</div>'
					+ 		'<label class="col-md-2 control-label">选项1关键词</label>'
					+ 		'<div class="col-md-3">'
					+ 			'<input type="text" name="ckey1"'
					+ 				'class="form-control input-inline input-medium" placeholder="请输入对应的关键词">'
					+ 		'</div>'
	                + 	'</div>'
	                + 	'<div class="form-group">'
					+ 		'<label class="col-md-2 control-label">调查选项2</label>'
					+ 		'<div class="col-md-2">'
					+ 			'<input type="text" name="coption2" init="#"'
					+ 				'class="form-control input-inline input-medium" placeholder="请输入调查选项2">'
					+ 		'</div>'
					+ 		'<label class="col-md-2 control-label">选项2关键词</label>'
					+ 		'<div class="col-md-3">'
					+ 			'<input type="text" name="ckey2"'
					+ 				'class="form-control input-inline input-medium" placeholder="请输入对应的关键词">'
					+ 		'</div>'
	                + 	'</div>'
	                + 	'<div class="form-group">'
					+ 		'<label class="col-md-2 control-label">调查选项3</label>'
					+ 		'<div class="col-md-2">'
					+ 			'<input type="text" name="coption3" init="#"'
					+ 				'class="form-control input-inline input-medium" placeholder="请输入调查选项3">'
					+ 		'</div>'
					+ 		'<label class="col-md-2 control-label">选项3关键词</label>'
					+ 		'<div class="col-md-3">'
					+ 			'<input type="text" name="ckey3"'
					+ 				'class="form-control input-inline input-medium" placeholder="请输入对应的关键词">'
					+ 		'</div>'
	                + 	'</div>'
	                +'</div>'
		}

		
		$("#optDiv").append(result);

		var buttonCount=count;
		for(var i=0;i<3;i++){
			$("#childButton"+buttonCount).bind("click", {index: buttonCount}, function(event){
				var c= event.data.index;
				$("#childQuestionId"+c).toggle();
				console.log("#childQuestionId"+c);
			});			
			buttonCount--;
		}
		
	};

	return {
		init : function() {
			initEvent();
			initQuestionOptions();

			$("#moreOption").on("click", function() {
				initQuestionOptions();
			});
		},
	};
}();
