//产品积分列表相关操作
var moduleManager = function() {

	var addModule = function(opType) {
		debugger;
		var moduleForm = $("#moduleForm");
		if (!moduleForm.valid()) {
			return;
		}

		var requestURL = "module/service/create";
		var tips = "增加失败!";
		var param = moduleForm.serializeArray();
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
				$("#backModuleListPage").click();
				return;
			},
			error : function() {
				bootbox.alert(tips);
				return;
			}
		});
	};

	var updateModule = function() {
		var moduleForm = $("#moduleForm");
		if (!moduleForm.valid()) {
			return;
		}

		var requestURL = "module/service/edit";
		var tips = "修改失败!";
		var param = moduleForm.serializeArray();
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
				$("#backModuleListPage").click();
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
		$("#moduleAdd").on("click", function() {
			addModule();
		});

		$("#moduleUpdate").on("click", function() {
			updateModule();
		});
	};
	
	var fileUpLoad = function(obj){
		var imgUrl = $(obj).parent().prevAll("input");
		$("#attachment").removeAttr("id");
		$("#attachment").removeAttr("name");
		$(obj).attr("id","attachment");
		$(obj).attr("name","attachment");
	    $.ajaxFileUpload({  
	        url : 'attachment/service/upload',  
	        secureuri : false,//安全协议  
	        fileElementId:'attachment',//id  
	        type : 'POST',  
	        async : false,  
	        dataType : 'text',
	        error : function(data,status,e) { 
	            alert('Operate Failed!');  
	        },  
	        success : function(json) {
	        	data = jQuery.parseJSON(jQuery(json).text());
	            if (data.status!="00"){  
	            	bootbox.alert(data.msg);  
	            }else{
	            	imgUrl.val(location.protocol+"//"+location.host+"/dt_member/attachment/front/displayFile/"+data.data.id);
	            }  
	        }  
	    });  
	   
	};  

	return {
		init : function(str) {
			debugger;
			if(str && str == "D"){
				$("#name").attr("disabled","disabled");
				$("#code").attr("disabled","disabled");
				$("#style").attr("disabled","disabled");
				$("#link").attr("disabled","disabled");
				$("#event").attr("disabled","disabled");
				$("#storeId").attr("disabled","disabled");
				$("#seqNum").attr("disabled","disabled");
				$("#description").attr("disabled","disabled");
				$("#imgUrl").attr("disabled","disabled");
				$('input[type="file"]').parent().hide();
				$("#parentId").attr("disabled","disabled");
			}else{
				$("#name").attr("disabled",false);
				$("#code").attr("disabled",false);
				$("#style").attr("disabled",false);
				$("#link").attr("disabled",false);
				$("#event").attr("disabled",false);
				$("#storeId").attr("disabled",false);
				$("#seqNum").attr("disabled",false);
				$("#description").attr("disabled",false);
				$("#imgUrl").attr("disabled","disabled");
				$('input[type="file"]').parent().show();
				$("#parentId").attr("disabled",false);
			}
			initEvent();
		},
		fileUpLoad : function(obj){
			fileUpLoad(obj);
		}
	};
}();
