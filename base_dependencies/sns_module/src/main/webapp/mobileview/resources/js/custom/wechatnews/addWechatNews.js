//@authot Leiming
//图文消息
var wechat_addWechatNews = function () {
	var _domain = window.location.host;
	var urlContent;
	if (_domain.indexOf('com') >= 0) {
		//正式环境
		urlContent = "http://" + _domain + "/EMIS";
		//生产环境公众号appid
	} else if (_domain.indexOf('mobi') >= 0) {
		//测试环境_阿里云
		urlContent = "http://" + _domain + "/EMIS";
	} else {
		//测试环境_局域网
		urlContent = "http://www.coding.mobi/EMIS";
	};
    //初始化页面相关按钮事件
    var initEvent = function () {
        //$("#allSearch").hide();
    	$("#singleAdd").on("click", function(){
    		setImageView();
            var submitForm = $("#submitForm");
            if(!submitForm.valid()){
                return;
            }
           if(!callMyvalidate()){
            	return;
            }
            var requestURL = "addWechatNews.html";
            var tips = "增加失败!";
            var tips1 = "增加成功!";
            if($("#id").val() != ''){
                requestURL = "addWechatNews.html";
                tips = "修改失败!";
                tips1 = "修改成功!";
            }
            var param = submitForm.serialize();
            $.ajax({
                type: "POST",
                url: requestURL,
                data: param,
                dataType: "json",
                success: function (data) {
                    if (data.code == 0) {
                    	bootbox.alert(tips1);
                    	setNewsId(data.data);
                    	//$("#backListPage").click();
                    }else{
                        bootbox.alert(tips);
                    }
                }
            });
        });;
       
       $("#forwardAddreply").on("click",function(){
    	   //获取所有的newsId
    	   var newsid = "";
    	  $("input[name='newsid']").each(function(){
    		   var id = $(this).val();
    		   newsid=newsid+id;
    		   newsid=newsid+",";
    	   });
    	   if(newsid!=""){
    		   newsid=newsid.substring(0,newsid.length-1);
    	   }
    	   $("#backListPage").attr("href","backWechatReplyView.html?newsid="+newsid)
    	   $("#backListPage").click();
        });
        
        //各个输入框的失焦
        $("#news_title").on("blur",function(){
        	var news_title = $("#news_title").val().trim();
        	$("div[name='appmsgItem']").each(function(){
        		if($(this).hasClass("current")){
        			var id = $(this).attr("id");
        			var i = id.substring(10,id.length);
        			if(news_title!=""){
                		$("#title_name"+i).text(news_title);
                	}else{
                		$("#title_name"+i).html("标题");
                	}
        		}
        	});
        });
        
        $("#news_content").on("blur",function(){
        	var news_content = $("#news_content").val().trim();
        	$("div[name='appmsgItem']").each(function(){
        		if($(this).hasClass("current")){
        			var id = $(this).attr("id");
        			var i = id.substring(10,id.length);
        			if(news_content!=""){
                		$("#content_name"+i).val(news_content);
                	}else{
                		$("#content_name"+i).val("");
                	}
        		}
        	});
        	
        });
        
        $("input[name='ext1']").bind("click",function(){
    		if($(this).attr("id")=="optionsRadios2"){
    			$("#articlediv").show();
    			$("#urldiv").hide();
    			setArticle();
    			var articleid = $("#article").val();
	        	var news_url = "";
	        	if(articleid!=""){
	        		news_url = urlContent+"/activity/article_share/article_detail.html?id="+articleid;
	        	}
	        	$("div[name='appmsgItem']").each(function(){
	        		if($(this).hasClass("current")){
	        			var id = $(this).attr("id");
	        			var i = id.substring(10,id.length);
	        			$("#url_type"+i).val("1");
	        			if(news_url!=""){
	        				$("#url_name"+i).val(news_url);
	        				$("#url_value").val(news_url);
	        			}else{
	        				$("#url_name"+i).val("");
	        				$("#url_value").val("");
	        			}
	        		}
	        	});
    		}else{
    			$("#urldiv").show();
    			$("#articlediv").hide();
    			var news_url = $("#news_url").val().trim();
            	$("div[name='appmsgItem']").each(function(){
            		if($(this).hasClass("current")){
            			var id = $(this).attr("id");
            			var i = id.substring(10,id.length);
            			$("#url_type"+i).val("0");
            			if(news_url!=""){
                    		$("#url_name"+i).val(news_url);
                    		$("#url_value").val(news_url);
                    	}else{
                    		$("#url_name"+i).val("");
                    		$("#url_value").val("");
                    	}
            		}
            	});
    		}
    	});
        
        $("#news_url").on("blur",function(){
        	var news_url = $("#news_url").val().trim();
        	$("div[name='appmsgItem']").each(function(){
        		if($(this).hasClass("current")){
        			var id = $(this).attr("id");
        			var i = id.substring(10,id.length);
        			$("#url_type"+i).val("0");
        			if(news_url!=""){
                		$("#url_name"+i).val(news_url);
                		$("#url_value").val(news_url);
                	}else{
                		$("#url_name"+i).val("");
                		$("#url_value").val("");
                	}
        		}
        	});
        });
        $("#article").on("change",function(){
        	var articleid = $("#article option:selected").val().trim();
        	var news_url = "";
        	if(articleid!=""){
        		news_url = urlContent+"/activity/article_share/article_detail.html?id="+articleid;
        	}
        	$("div[name='appmsgItem']").each(function(){
        		if($(this).hasClass("current")){
        			var id = $(this).attr("id");
        			var i = id.substring(10,id.length);
        			$("#url_type"+i).val("1");
        			if(news_url!=""){
        				$("#url_name"+i).val(news_url);
        				$("#url_value").val(news_url);
        			}else{
        				$("#url_name"+i).val("");
        				$("#url_value").val("");
        			}
        		}
        	});
        });
        
        //添加下一个
        $("#js_add_appmsg").on("click",function(){
        	addNextNews();
        });
        
        var options = {
            errorElement: 'span',
            errorClass: 'help-block',
            focusInvalid: false,
            ignore: "",
            rules: {
            	title: {
                    required: true
                },
                content: {
                    required: true
                },
                image: {
                	required: true
                }
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
        	initData();
            initEvent();
        },
    };
}();

//初始化右边的值
function initData(){
	$("div[name='appmsgItem']").each(function(){
		if($(this).hasClass("current")){
			var id = $(this).attr("id");
			var i = id.substring(10,id.length);
			var title_name  = $("#title_name"+i).text().trim();
			var content_name  = $("#content_name"+i).val().trim();
			var image_name  = $("#image_name"+i).attr("src").trim();
			var url_name  = $("#url_name"+i).val().trim();
			var url_type  = $("#url_type"+i).val().trim();
			var newsid  = $("#newsid"+i).val().trim();
			$("#news_title").val(title_name);
			$("#news_content").val(content_name);
			$("#imageUrl").val(image_name);
			//$("#news_url").val(url_name);
			$("#id").val(newsid);
			if(url_type=="1"){//文章
				$("input[type='radio'][name='ext1'][value=1]").attr("checked",true);
				$("#articlediv").show();
    			$("#urldiv").hide();
    			if(url_name!=""){
    				var index = url_name.indexOf("?id=");
        			var id = url_name.substring(index+4,url_name.length);
        			setArticle(id);
    			}
			}else{//选中url
				$("input[type='radio'][name='ext1'][value=0]").attr("checked",true);
				$("#urldiv").show();
    			$("#articlediv").hide();
				$("#news_url").val(url_name);
			}
		}
	});
}

function callMyvalidate(){
	return true;
}

var i = 1;
function addNextNews(){
	var autoNews = "<div id='appmsgItem"+i+"' name='appmsgItem' data-fileid='' data-id='"+i+"' class='js_appmsg_item appmsg_item_wrp has_thumb' onclick='setCurrent("+i+")'>"
				+ "<div class='first_appmsg_item' style='display:none;' title='标题'>"
				+ "<div class='cover_appmsg_item'>"
				+ "<h4 class='appmsg_title'><a href='javascript:void(0);' onclick='return false;'>标题</a></h4>"
				+ "<div class='appmsg_thumb_wrp'>"
				+ "<img class='' src=''>"
				+ "<i class='appmsg_thumb default'>封面图片</i>"
				+ "</div></div>"
				+ "<div class='appmsg_edit_mask'>"
				//+ " <a onclick='return false;' class='icon20_common sort_down_white js_down' data-id='1' href='javascript:;' title='下移' style='display: block;'>向下</a>"
				+ "</div></div>"
				+ "<div class='appmsg_item' title='标题'>"
				+ "<img class='js_appmsg_thumb appmsg_thumb' name='image_name' id='image_name"+i+"' src=''>"
				+ "<i class='appmsg_thumb default'>缩略图</i>"
				+ "<h4 class='appmsg_title' id='title_name"+i+"'><a onclick='return false;' href='javascript:void(0);'>标题</a></h4>"
				+ "<input type='hidden' name='content_name' id='content_name"+i+"' value=''/>"
				+ "<input type='hidden' name='url_name' id='url_name"+i+"' value=''/>"
				+ "<input type='hidden' name='url_type' id='url_type"+i+"' value=''/>"
				+ "<input type='hidden' name='newsid' id='newsid"+i+"' value=''/>"
				+ "<div class='appmsg_edit_mask'>"
				//+ "<a onclick='return false;' class='icon20_common sort_up_white   js_up' data-id='1' href='javascript:;' title='上移'>向上</a>"
				//+ "<a onclick='return false;' class='icon20_common sort_down_white js_down' data-id='1' href='javascript:;' title='下移' style='display: block;'>向下</a>"
				+ "<a onclick='deleteNews("+i+")' class='icon20_common del_media_white js_del' data-id='"+i+"' href='javascript:;' title='删除'>删除</a>"
				+ "</div></div></div>"
				$("#js_appmsg_preview").append(autoNews);
	setCurrent(i);
	i++;
}

//删除下标为i的图文，已被选择则删除，否则选中
function deleteNews(i){
	if($("#appmsgItem"+i).hasClass("current")){
		$("#appmsgItem"+i).remove();
	}
}

//设置第i个为选中状态
function setCurrent(i){
	$("div[name='appmsgItem']").each(function(){
		if($(this).hasClass("current")){
			$(this).removeClass("current");
		}
	});
	//点击删除的时候也会触发setCurrent方法，所以要判断这个元素是否存在
	if($("#appmsgItem"+i).length>0){
		$("#appmsgItem"+i).addClass("current");
	}else{
		setCurrent(parseInt(i)-1);
	}
	initData();
}

//图片预览
function setImageView(){
	var imageUrl = $("#imageUrl").val().trim();
	$("div[name='appmsgItem']").each(function(){
		if($(this).hasClass("current")){
			var id = $(this).attr("id");
			var i = id.substring(10,id.length);
			if(imageUrl!=""){
        		$("#image_name"+i).attr("src",imageUrl);
        	}else{
        		$("#image_name"+i).attr("src","")
        	}
		}
	});
}
//设置id的值，用于判断增加或修改
function setNewsId(data){
	$("#id").val(data);
	$("div[name='appmsgItem']").each(function(){
		if($(this).hasClass("current")){
			var id = $(this).attr("id");
			var i = id.substring(10,id.length);
			$("#newsid"+i).val(data);
		}
	});
}

function setArticle(id){
	$.ajax({
        type : "post",
        dataType : "json",
        async: false,
        url : "queryArticleDefData.html",
        data: {iDisplayLength:"all"},
        success : function(data){
        	var result = "";
        	//添加页
        	result += "<option value=''>请选择文章</option>";
            $.each(data.queryResult, function(index, value){
            	if(id == value.id){
            		result += "<option value="+value.id+" selected>"+value.title+"</option>";
            	}else{
            		result += "<option value="+value.id+">"+value.title+"</option>";
            	}
            });
            console.log(result);
            $("#article").empty().append(result).change();
    	}
        	
    });
}