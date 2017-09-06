//消息回复
var wechat_addWechatReply = function () {
    //初始化页面相关按钮事件
    var initEvent = function () {
        //$("#allSearch").hide();
        $("#singleAdd").on("click", function(){
            var submitForm = $("#submitForm");
            if(!callMyvalidate()){
            	return;
            }
            if(!submitForm.valid()){
                return;
            }
            var requestURL = "addWechatReply.html";
            var tips = "增加失败!";
            if($("#id").val() != ''){
                requestURL = "addWechatReply.html";
                tips = "修改失败!";
            }
            var param = submitForm.serialize();
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
            var submitForm = $("#submitForm");
            if(!submitForm.valid()){
                return;
            }
            $.ajax({
                type: "POST",
                url: "addWechatReply.html",
                data: submitForm.serialize(),
                dataType: "json",
                success: function (data) {
                    if (data.code == 0) {
                       bootbox.alert("增加成功 !");
                        //清除页面数据
                       $("#submitForm").find('input:type="text":').val('');
                    }else{
                        bootbox.alert("增加失败!");
                    }
                }
            });
        });

        //消息类型
        $("#msgType").on("click",function(){
        	var msgType = $("#msgType option:selected").val();
        	if(msgType=="img"){
        		$("#textdiv").hide();
        		$("#newsdiv").hide();
        		$("#imagediv").show();
        		$("#tonews").hide();
        		$("#newsorderdiv").hide();
        		$("#alertdiv").hide();
        	}else if(msgType=="news"){
        		$("#textdiv").hide();
        		$("#imagediv").hide();
        		$("#newsdiv").show();
        		$("#tonews").show();
        		$("#newsorderdiv").show();
        		$("#alertdiv").show();
        	}else{
        		$("#imagediv").hide();
        		$("#newsdiv").hide();
        		$("#textdiv").show();
        		$("#tonews").hide();
        		$("#newsorderdiv").hide();
        		$("#alertdiv").hide();
        	}
        });
        
        /*//新建图文
        $("#new_news").on("click",function(){
        	
        });*/
        //选择图文
        $("#select_news").on("click",function(){
        	
        });
        
        var options = {
            errorElement: 'span',
            errorClass: 'help-block',
            focusInvalid: false,
            ignore: "",
            rules: {
            	keyword: {
                    required: true
                },
                /*title: {
                    required: true
                },
                userid: {
                	required: true
                },*/
                msgType: {
                	required: true
                }/*,
                content: {
                	required: true
                }*/
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
       $("#submitForm").validate(options);
    };
    
    return {
        init: function () {
            initEvent();
            initData();
        },
    };
}();

function initData(){
	//被添加自动回复与消息自动回复关键字和标题不能编辑
	var replyType = $("#replyType").val();
	if(replyType=="added_reply"){
		$("#titlespan").html("被添加自动回复");
		$("#keyword").val("added_reply");
		$("#text_title").val("被添加自动回复");
		$("#keyword").attr("readonly","true");
		$("#text_title").attr("readonly","true");
		$("#matchingType").attr("disabled","disabled");
	}else if(replyType=="auto_reply"){
		$("#titlespan").html("消息自动回复");
		$("#keyword").val("auto_reply");
		$("#text_title").val("消息自动回复");
		$("#keyword").attr("readonly","true");
		$("#text_title").attr("readonly","true");
		$("#matchingType").attr("disabled","disabled");
	}
	if($("#id").val() != ''){
		/*//被添加自动回复与消息自动回复关键字和标题不能编辑
		var replyType = $("#keyword").val();
		if(replyType=="added_reply"){
			$("#titlespan").html("被添加自动回复");
			$("#keyword").attr("disabled","disabled");
			$("#text_title").attr("disabled","disabled");
		}else if(replyType=="auto_reply"){
			$("#titlespan").html("消息自动回复");
			$("#keyword").attr("disabled","disabled");
			$("#text_title").attr("disabled","disabled");
		}*/
		
		var msgType = $("#xxtype").val();
		$("#msgType").find("option[value='"+msgType+"']").attr("selected",true);
		var matchingType = $("#yytype").val();
		$("#matchingType").find("option[value='"+matchingType+"']").attr("selected",true);
		if(msgType=="img"){
    		$("#textdiv").hide();
    		$("#newsdiv").hide();
    		$("#imagediv").show();
    		$("#tonews").hide();
    		$("#newsorderdiv").hide();
    		$("#alertdiv").hide();
    	}else if(msgType=="news"){
    		$("#textdiv").hide();
    		$("#imagediv").hide();
    		$("#newsdiv").show();
    		$("#tonews").show();
    		$("#newsorderdiv").show();
    		$("#alertdiv").show();
    		$("#newsorder").val($("#title").val()+",");
    		
    	}else{
    		$("#imagediv").hide();
    		$("#newsdiv").hide();
    		$("#textdiv").show();
    		$("#tonews").hide();
    		$("#newsorderdiv").hide();
    		$("#alertdiv").hide();
    	}
	}
	//添加图文返回到添加消息时选中图文
	if($("#tuwen").val() == 'tuwen'){
		$("#msgType").find("option[value='news']").attr("selected",true);
		$("#textdiv").hide();
		$("#imagediv").hide();
		$("#newsdiv").show();
		$("#tonews").show();
		$("#newsorderdiv").show();
		$("#alertdiv").show();
		if($("#new_newsid").val()!=""){
			$("#newsorder").val($("#new_newsid").val()+",");
		}
	}
}

function callMyvalidate(){
	var msgType = $("#msgType option:selected").val();
	if(msgType=="img"){
		var image = $("#imageUrl").val().trim();
		if(image!=""){
			return true;
		}else{
			alert("请上传图片");
			return false;
		}
	}else if(msgType=="news"){
		setNews();
	}else{
		var title = $("#text_title").val().trim();
		if(title!=""){
			$("#title").val(title);
		}else{
			alert("请输入标题");
			return false;
		}
		var content = $("#text_content").val().trim();
		if(content!=""){
			$("#content").val(content);
		}else{
			var keyword = $("#keyword").val();
			if(keyword!="added_reply" && keyword!="auto_reply"){
				alert("请输入回复内容");
				return false;
			}
		}
	}
	return true;
}

/**选中素材*/
function setNews() {
	/*var str = [];
	$('#wechatNewsTable' + ' tbody tr .checkboxes:checked').each(function() {
		this.checked = !this.checked;
		str += $(this).val() + ",";
	});*/
	var str = $("#newsorder").val();
	if (str == "") {
		alert("请至少一个图文");
		return false;
	} else {
		str = str.substring(0, str.length - 1);
	}
	$("#title").val(str);
	$("#content").val(str);
	return true;
}