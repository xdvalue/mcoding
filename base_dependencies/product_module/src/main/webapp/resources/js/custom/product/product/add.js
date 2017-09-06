/**
 * Created by benfu on 2016/12/27.
 */

var i = 0;
var DataTableList = function() {
	var imgList = '';
	var createOrEdit = function(isCreate) {
		var form = $("#productForm");
		if (!form.valid()) {
			return;
		}
		var requestURL = "product/service/create";
		if(!isCreate){
			requestURL = "product/service/edit";
		}
		var tips = "增加失败!";
		var param = form.serializeArray();
		param = serializeObject(param);
		switchBtn_init(param);//开关按钮赋值给param
		App.blockUI({
			boxed: true
		});
		$.ajax({
			type : "POST",
			url : requestURL,
			data : JSON.stringify(param),
			dataType : "json",
			contentType : "application/json",
			success : function(data) {
				if (!data || data.status != '00') {
					App.unblockUI();
					bootbox.alert(tips + '原因是' + data.msg);
					return;
				}
				submit_priceForm(isCreate,data.data);
				submit_imgForm(isCreate,data.data);
			},
			error : function() {
				App.unblockUI();
				bootbox.alert(tips);
				return;
			}
		});
	};

	// 初始化页面相关按钮事件
	var initEvent = function() {
		kindEditor_init("product");
		kindEditor_imageUpload_init();

		if($(".nextBtn")){
			$(".nextBtn").on("click", function() {
				var btnValue = Number($(this).val());
				var tabValue = btnValue+1;
				$("#tab_li_"+btnValue).removeClass("active");
				$("#tab_li_"+tabValue).addClass("active");
				$("#tab_"+btnValue).removeClass("active");
				$("#tab_"+tabValue).addClass("active");
			});
		}

		if($(".preBtn")){
			$(".preBtn").on("click", function() {
				var btnValue = Number($(this).val());
				var tabValue = btnValue-1;
				$("#tab_li_"+btnValue).removeClass("active");
				$("#tab_li_"+tabValue).addClass("active");
				$("#tab_"+btnValue).removeClass("active");
				$("#tab_"+tabValue).addClass("active");
			});
		}

		if($("#singleAdd")){
			$("#singleAdd").on("click", function() {
				var isCreate = true;
				createOrEdit(isCreate);
			});
		}

		if($("#singleUpdate")){
			$("#singleUpdate").on("click", function() {
				var isCreate = false;
				createOrEdit(isCreate);
			});
		}

		$('#addImgBtn').click(function(){
			var inputId = "scrollImg"+i;
			var btnId = "scrollImgBtn"+i;
			var imgList = '<div class="form-group">'+
				'<label class="col-md-3 control-label">商品轮播图</label>'+
				'<div class="col-md-9">'+
				'<input type="text" name="scrollImg" id='+inputId+' value="" readonly="readonly" class="form-control input-inline input-medium"/>'+
				'<input type="button" id='+btnId+' value="选择商品轮播图" class="btn btn-primary kindeditorMageUpload"/>'+
				'</div></div>';
			$('#imgUploadInput').append(imgList);
			i++;
			imageUpload({
				imageUploadBtn : btnId,
				showUrlId :  inputId
			});//图片上传的按钮操作方法
		});

		var id = $("#id").val();
		productImg_init(id);

	};

	function  submit_priceForm(isCreate,data) {

		var form = $("#priceForm");
		if (!form.valid()) {
			return;
		}
		var requestURL = "productPrice/service/create";
		if(!isCreate){
			requestURL = "productPrice/service/edit";
		}
		var tips = "增加失败!";
		var priceObj = {};
		console.log(priceObj);
		$(".touchspin").each(function (i) {
			var priceValue = $(this).val();
			var idValue = $(this).next().val();
			priceObj.value= priceValue;
			priceObj.id= idValue;
			priceObj.productId = data.id;
			if(i==0){
				priceObj.sceneId = 1;
				priceObj.sceneName = '原价';
				priceObj.type = 1;
			}else if(i==1){
				priceObj.sceneId = 2;
				priceObj.sceneName = '销售价';
				priceObj.type = 1;
			}else {
				priceObj.sceneId = 3;
				priceObj.sceneName = '微商价';
				priceObj.type = 3;
			}
			var isContinue = true;
			$.ajax({
				type : "POST",
				url : requestURL,
				data : JSON.stringify(priceObj),
				dataType : "json",
				async: false,
				contentType : "application/json",
				success : function(data) {
					if (!data || data.status != '00') {
						App.unblockUI();
						bootbox.alert(tips + '原因是' + data.msg);
						isContinue = false;
						return;
					}
					/*App.unblockUI();
					 $("#backListPage").click();
					 return;*/
				},
				error : function() {
					App.unblockUI();
					bootbox.alert(tips);
					isContinue = false;
					return;
				}
			});
			return isContinue;
		});
	}

	function  submit_imgForm(isCreate,data) {
		var tips = "增加失败!";
		var imgObj = {};
		console.log(imgObj);
		var urlStr = [];
		$("input[name='scrollImg']").each(function (i) {
			var imgUrl = $(this).val();
			urlStr += imgUrl + ",";
		});
		if(urlStr!=""){
			urlStr = urlStr.substring(0, urlStr.length - 1);
		}
		imgObj.productId = data.id;
		imgObj.type = 1;
		imgObj.urls = urlStr;
		$.ajax({
			type : "POST",
			url : "productImg/service/createOrEditImgs",
			data : JSON.stringify(imgObj),
			dataType : "json",
			async: false,
			contentType : "application/json",
			success : function(data) {
				if (!data || data.status != '00') {
					App.unblockUI();
					bootbox.alert(tips + '原因是' + data.msg);
					isContinue = false;
					return;
				}
				App.unblockUI();
				$("#backListPage").click();
				return;
			},
			error : function() {
				App.unblockUI();
				bootbox.alert(tips);
				isContinue = false;
				return;
			}
		});
		return isContinue;
    }

    function productImg_init(id){

		$.ajax({
			type : "GET",
			url : "productImg/service/findAll",
			data : {"id": id},
			async: false,
			success : function(data) {
				console.log("123");
				$.each(data, function(i, element){
					console.log(element.id);
					console.log(element);
					var inputId ="scrollImg"+ element.id;
					var btnId =  "scrollImgBtn"+element.id;
					var imgList = '<div class="form-group">'+
						'<label class="col-md-3 control-label">商品轮播图</label>'+
						'<div class="col-md-9">'+
						'<input type="text" name="scrollImg" id='+inputId+' value='+element.url+' readonly="readonly" class="form-control input-inline input-large"/>'+
						'<input type="button" id='+btnId+' value="选择商品轮播图" class="btn btn-primary kindeditorMageUpload"/>'+
						'</div></div>';
					$('#imgUploadInput').append(imgList);
					imageUpload({
						imageUploadBtn : btnId,
						showUrlId :  inputId
					});//图片上传的按钮操作方法
				});
			},
			error : function() {
				return;
			}
		});
	}

	return {
		init : function() {
			initEvent();
		}
	};
}();
