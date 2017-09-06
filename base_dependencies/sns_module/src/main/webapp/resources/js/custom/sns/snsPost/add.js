var SnsPostManager = function() {

	var createOrEditSnsPost = function(isCreate) {
		
		var form = $("#snsPostForm");
		if (!form.valid()) {
			return;
		}

		var requestURL = "snsPost/service/createForDashboard";
		if(!isCreate){
			requestURL = "snsPost/service/edit";
		}
		var tips = "增加失败!";
		var param = form.serializeArray();
		debugger;
		param = serializeObject(param);
		param.postImgs = getPostImage();
//		param.moduleId = 1;
		if(param.moduleId <=0){
			bootbox.alert(tips + '原因是:版块选择错误');
			return;
		}
		
		if(param.createTime && param.createTime!=''){
			param.createTime = moment(param.createTime, 'YYYY-MM-DD HH:mm:ss').valueOf();
		}
		
		delete param.imgUrl;
		delete param.postImg;

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
	
	var getPostImage = function(){
		var postImgs = [];
		$.each($("input[name='postImg']"), function(index, input){
			var value = $(input).val();
			postImgs.push({
				imgUrl : $(input).val()
			});
		});
		return postImgs;
		
	}

	// 初始化页面相关按钮事件
	var initEvent = function() {
		if($("#singleAdd")){
			$("#singleAdd").on("click", function() {
				var isCreate = true;
				createOrEditSnsPost(isCreate);
			});
		}
		
		if($("#singleUpdate")){
			$("#singleUpdate").on("click", function() {
				var isCreate = false;
				createOrEditSnsPost(isCreate);
			});
		}
		
	};
	
	var afterImageUploadHandler = function(url){
		debugger;
		var contextPath = basePath.match(/\/(\w+)\/*$/g);
		contextPath = contextPath[0];
		url = url.substr(url.indexOf(contextPath) + contextPath.length);
		var imgId = url.match(/\d+$/g);
//		var host = window.location.host;
//		url = 'http://' + host + url;
		
		var html = '<div class="form-group">' + 
			         '<label class="col-md-3 control-label">上传的图片<span class="required">*</span></label>' +
		             '<div class="col-md-4"><div class="input-group">' +
		               '<input type="text" name="postImg" value="'+ url+'" class="form-control input-inline input-medium" placeholder="选择图片 " readonly="readonly" />' + 
			           '<span class="input-group-btn">' +
			             '<button class="btn green" data-toggle="modal" href="#postImage_'+imgId+'"  type="button">预览图片</button>' +
			             '<button class="btn red" type="button">删除图片</button>' +
			           '</span>' +
		             '</div></div>' +
		             '<div id="postImage_'+imgId+'" class="modal fade modal-scroll" tabindex="-1" data-replace="true">' +
					   '<div class="modal-dialog"><div class="modal-content"><div class="modal-body">' +
					   '<img src="'+basePath + url +'">' +
					   '</div>' +
					   '<div class="modal-footer"><button type="button" data-dismiss="modal" class="btn">关闭</button></div>'+
					   '</div></div>'+
				      '</div>'+
	                '</div>';
		$("#formGroupUploadImage").before($(html));
	}
	
	var handleDatetimePicker = function() {
		$(".form_datetime").datetimepicker({
			format : "yyyy-mm-dd hh:ii:ss",
			autoclose : true,
			todayBtn : true,
			pickerPosition : "bottom-left",
			minuteStep : 1
		});
	};


	return {
		init : function() {
			kindEditorCreate("contentKindEditor", "snsPost");
			imageUpload({
				imageUploadBtn:"uploadImgBtn",
				callback :  afterImageUploadHandler
			});
			handleDatetimePicker();
			initEvent();
		}
	};
}();
