//产品积分列表相关操作
var ArticleManager = function() {
	
	var articleManager = new RecommendArticleManager();
	
	var tagArray = $('#tag').val().split(/\s*,\s*/);
	tagArray = tagArray.map(function(tag){
		return {
			mark : tag
		}
	});
	
	var tagManger = new TagManger({elementId :'tag', selectTags: tagArray});

	//创建或者编辑文章
	var createOrEditArticle = function(isCreate) {
		var form = $("#articleForm");
		if (!form.valid()) {
			return;
		}
		var detailInfoId = $("#detailInfoId").text();
		var requestURL = "article/service/create";
		if (!isCreate) {
			requestURL = "article/service/edit";
		}
		var tips = "增加失败!";
		var param = form.serializeArray();
		param = serializeObject(param);
		
		var recommendArticleList = articleManager.getRecommendArticleList();
		if(recommendArticleList && recommendArticleList.length>0){
			param.recommendArticleList = recommendArticleList;
		}
		
		var labelList = tagManger.getTags();
		if(labelList && labelList.length>0){
			param.labelList = labelList;
		}
		
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
				if(detailInfoId != undefined && detailInfoId != ""){
					alert("修改成功！");
				}else{
					$("#backListPage").click();
				}
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
		if ($("#singleAdd")) {
			$("#singleAdd").on("click", function() {
				var isCreate = true;
				createOrEditArticle(isCreate);
			});
		}

		if ($("#singleUpdate")) {
			$("#singleUpdate").on("click", function() {
				 var isCreate = false;
				 createOrEditArticle(isCreate);
			});
		}
		
		$("#articleOrigin").change(function(e){
			var value = $(e.target).val();
			if(value != 3){
				$('#originFormGroup').css('display', 'none');
				$('#originAddressFormGroup').css('display', 'none');
			}else{
				$('#originFormGroup').css('display', 'block');
				$('#originAddressFormGroup').css('display', 'block');
			}
		});
	};

	return {
		init : function() {
			kindEditorCreate("contentKindEditor", "article");
			kindEditorCreate("summaryKindEditor", "article");
			imageUpload({
				imageUploadBtn:"imageButton",
				showUrlId :  "coverImage"
			});
			imageUpload({
				imageUploadBtn: "litpicButton", 
				showUrlId :  "thumbnail"
			});
			initEvent();
			
			tagManger.init();
			articleManager.init();

		}
	};
}();
