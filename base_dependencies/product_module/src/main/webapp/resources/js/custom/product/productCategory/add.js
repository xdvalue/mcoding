
var DataTableList = function() {

	var createOrEdit = function(isCreate) {
		
		var form = $("#productCategoryForm");
		if (!form.valid()) {
			return;
		}

		var requestURL = "productCategory/service/create";
		if(!isCreate){
			requestURL = "productCategory/service/edit";
		}
		var tips = "增加失败!";
		var param = form.serializeArray();
		param = serializeObject(param);

		$.ajax({
			type : "POST",
			url : requestURL,
			data : JSON.stringify(param),
			dataType : "json",
			contentType : "application/json",
			success : function(data) {
				if (!data || data.status != '00') {
					bootbox.alert(tips + '原因是' + data.msg);
					return;
				}
				
				$("#backListPage").click();
				return;
			},
			error : function() {
				bootbox.alert(tips);
				return;
			}
		});
	};

	// 初始化页面相关按钮事件
	var initEvent = function() {
		if($("#singleAdd")){
			$("#singleAdd").on("click", function() {
				var isCreate = true;
				createOrEdit(isCreate);
			});

			//查询分类根据parentid
			$.getJSON("productCategory/service/findByParentId?categoryParentId=0", function(data){
				var result = "";
				result += "<option value='0'>顶级分类</option>";
				var parentId = $("#parentIdHidden").val();
				$.each(data.data, function(index, value){
					if(parentId!=0 && parentId == value.id){
						result += "<option value="+value.id+" selected>"+value.categoryName+"</option>";
					}else{
						result += "<option value="+value.id+">"+value.categoryName+"</option>";
					}
				});
				$("#categoryParentId").empty().append(result).change();
			});
		}
		
		if($("#singleUpdate")){
			$("#singleUpdate").on("click", function() {
				var isCreate = false;
				createOrEdit(isCreate);
			});
		}

	};


	return {
		init : function() {
			initEvent();
		}
	};
}();
