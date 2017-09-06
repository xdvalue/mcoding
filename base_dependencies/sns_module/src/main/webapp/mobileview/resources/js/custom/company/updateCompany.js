var mallgame_addGamblingManager = function() {

	var addressCount = 3;

	var initSelect = function() {

		$.ajax({
			type : "GET",
			url : "queryProvinceRegion.html",
			dataType : "json",
			success : function(data) {
				var result = "";
				result += "<option value=''>请选择省份</option>";

				$.each(data, function(index, value) {
					result += "<option id=\"" + value.name + "\" value=\""
							+ value.id + "\">" + value.name + "</option>";
				});

				for (var i = 1; i <= addressCount; i++) {
					var choosedProvince = $("#provinceId" + i).val();
					$("#provinceId" + i).empty().append(result).change();
					if (choosedProvince) {
						$(
								"#provinceId" + i + " option[id="
										+ choosedProvince + "]").attr(
								"selected", "selected").change();
					}
				}
			}
		});

		// 选择省份事件
		for (var i = 1; i <= addressCount; i++) {
			$("#provinceId" + i).on(
					"change",
					function() {
						var regionId = $(this).val();
						var provinceSelectOrder = $(this).attr("id").replace(
								/[^0-9]/ig, "");
						if (regionId == '') {
							return;
						}
						$.ajax({
							type : "post",
							dataType : "json",
							url : "queryRegionByParentId.html",
							data : {
								parentRegionId : regionId
							},
							success : function(data) {
								var result = "";
								result += "<option value=''>请选择城市</option>";
								$.each(data, function(index, value) {
									result += "<option id=\"" + value.name
											+ "\" value=\"" + value.id + "\">"
											+ value.name + "</option>";
								});

								var choosedCity = $(
										"#cityId" + provinceSelectOrder).val();

								$("#cityId" + provinceSelectOrder).empty()
										.append(result).change();

								$(
										"#cityId" + provinceSelectOrder
												+ " option[id=" + choosedCity
												+ "]").attr("selected",
										"selected").change();
							}
						})
					});
		}
		;

		// 选择城市事件
		for (var i = 1; i <= addressCount; i++) {
			$("#cityId" + i).on(
					"change",
					function() {
						var regionId = $(this).val();
						var citySelectOrder = $(this).attr("id").replace(
								/[^0-9]/ig, "");

						if (regionId == '') {
							return;
						}
						$.ajax({
							type : "post",
							dataType : "json",
							url : "queryRegionByParentId.html",
							data : {
								parentRegionId : regionId
							},
							success : function(data) {
								var result = "";
								result += "<option value=''>请选择区域</option>";
								$.each(data, function(index, value) {
									result += "<option id=\"" + value.name
											+ "\" value=\"" + value.name
											+ "\">" + value.name + "</option>";
								});

								var choosedCounty = $(
										"#countyId" + citySelectOrder).val();

								$("#countyId" + citySelectOrder).empty()
										.append(result).change();

								$(
										"#countyId" + citySelectOrder
												+ " option[id=" + choosedCounty
												+ "]").attr("selected",
										"selected").change();
							}
						})
					});
		}
		;

	};

	var initEvent = function() {
		$("#updateBtn").on("click", function() {
			var requestURL = "updateCompany.html";
			var tips = "增加失败!";
			var obj = new Object();

			var submitForm = $("#submitForm");
			var submitForm1 = $("#submitForm1");
			var submitForm2 = $("#submitForm2");
			var submitForm3 = $("#submitForm3");
			var nSubmitForm1 = $("#nSubmitForm1");
			var nSubmitForm2 = $("#nSubmitForm2");

			if (!submitForm.valid()) {
				return;
			}
			if (!submitForm1.valid()) {
				return;
			}
			
			var company = serializeObject(submitForm.serializeArray());
			obj.company = company;

			var addresses = new Array(3)
			var addr1 = serializeObject(submitForm1.serializeArray());
			var addr2 = serializeObject(submitForm2.serializeArray());
			var addr3 = serializeObject(submitForm3.serializeArray());
			addresses[0] = addr1;
			addresses[1] = addr2;
			addresses[2] = addr3;
			obj.addresses = addresses;

			var newAddresses = new Array(2);
			var nAddr1 = serializeObject(nSubmitForm1.serializeArray());
			var nAddr2 = serializeObject(nSubmitForm2.serializeArray());
			newAddresses[0] = nAddr1;
			newAddresses[1] = nAddr2;
			obj.newAddresses = newAddresses;

			$.ajax({
				type : "POST",
				url : requestURL,
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
		
		var options = {
				errorElement : 'span',
				errorClass : 'help-block',
				focusInvalid : false,
				ignore : "",
				rules : {
					companyname : {
						required : true
					},
					province : {
						required : true
					},
					city : {
						required : true
					},
					county : {
						required : true
					},
					town : {
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
		$("#submitForm1").validate(options);
		
	};

	return {
		init : function() {
			initEvent();
			initSelect();
		},
	};
}();
