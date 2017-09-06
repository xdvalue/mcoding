var _articleId= buijs_geturl('articleId');     //文章ID
var _pageNo= 1;
var ImgIdA= 0; //记录照片位置
var i=0;
var pictUrlArray=[];
var deleteImgArray= [];
var textareaHistory = "";  //记录发表的文字内容
var imgHtml = "";   //记录发表的图片
var currentUser = '自己';  //当前用户名字
var currentHeadImgUrl = "";	   //当前用户头像
var currentHevelPict = 0;  //当前用户等级
var uploadCtrl = 1;  //图片还在上传不允许再上传图片或者发表
var historyPage = window.sessionStorage.getItem("news_detail_lastType");
var isScrollUpdata = true;
$(function(){
	if (!_articleId) {
		buijs_alert({
			content: "获取信息失败，请返回重新打开页面或刷新"
		})
	}
	getContentDate();
	commentList();
	getMemberInfo();
	wechatSharePublic("健乐多微社区", "健乐多微社区，成就肌肉的力量", 'http://v.gymmaxer.com/sns_module/mobileview/community/index.html', false, '', 'mobileview/community/images/logoShare.jpg');
});

/*获取用户信息*/
function getMemberInfo() {
	$.ajax({
		type: 'get',
		url: _jsonUrl + 'member/front/findCurrentMember',
		dataType: 'json',
		success: function (data){
			currentUser = data.data.name;
			currentHeadImgUrl = data.data.headImgUrl;
//			data.data.levelList && data.data.levelList.map(function(items){
//				if(items.moduleType == "100"){
//					currentHevelPict= items.grade;
//				}
//			});
			
			if(data.data.memberExtInfoMap && data.data.memberExtInfoMap.levelList && data.data.memberExtInfoMap.levelList.mt100){
				currentHevelPict = data.data.memberExtInfoMap.levelList.mt100.grade;
			}
		},
		error: function (data,reo){
			buijs_alert({
				content: "网络超时,请刷新重试。("+data.status+":"+reo+")"
			})
		}
	})
}


/*back按钮事件*/
function backFunction() {
	if (history.length >1) {
		window.history.go(-1);
	}else {
		window.location.href= 'index.html';
	}
	
}

/*点赞帖子*/
function likePost () {
	$.ajax({
		type: "get",
		url: _jsonUrl+ "snsPost/front/likePost?postId="+_articleId+"&isLike=1",
		dataType: 'json',
		contentType: 'application/json',
		success: function (data) {
			if (data.status == "00") {
				buijs_alert({
					content: "点赞成功"
				});
				var lickNum= Number($("#clickLike").text());
				$("#clickLike").html(lickNum+1);
			}else{
				buijs_alert({
					content: data.msg
				});
			}
		},
		error: function (data,reo){
			buijs_alert({
				content: "网络超时,请刷新重试。("+data.status+":"+reo+")"
			})
		}
	})
}

/*获取帖子详情*/
function getContentDate() {
	$.ajax({
		type: 'get',
		url: _jsonUrl+ 'snsPost/front/findPostById?id='+_articleId,
		dataType: 'json',
		contentType: 'application/json',
		success: function(data) {
			if (data.status == '00') {
				wechatSharePublic("健乐多微社区",data.data.title,'http://v.gymmaxer.com/sns_module/mobileview/community/article_detail.html?articleId='+_articleId,false,"", 'mobileview/community/images/logoShare.jpg');
				var tips = "";
				var _text= '';
				var levelPict;
				var imgList = "";
				if (data.data.typeFlag == 3) {
					tips = '<span class="pefctTextTitleRed bui_mr_3">精</span>';
				}
				$("#title").html(tips+data.data.title);
//				data.data.member.levelList && data.data.member.levelList.map(function(item){
//					if(item.moduleType == "100"){
//						levelPict= item.grade;
//					}
//				});
				if(data.data.member.memberExtInfoMap && data.data.member.memberExtInfoMap.levelList && data.data.member.memberExtInfoMap.levelList.mt100){
					levelPict = data.data.member.memberExtInfoMap.levelList.mt100.grade;
				}
				
				$("#level").text(levelPict || 0);
				$("#author").html(data.data.memberName || "未知用户");
				$("#upTime").html(changeDateFormat(data.data.createTime));
				$("#clickLike").html(data.data.snsPostExtInfo.likeNum || 0);
				if (data.data.content) {
					_text+= '<p>'+data.data.content+'</p>';
				};
				if (data.data.postImgs && data.data.postImgs.length> 0) {
					$.map(data.data.postImgs,function(obj,index){
					 	// if ((index%2)) {
					 	// 	imgList+='<img onclick="previewImage([\''+_jsonUrl+obj.imgUrl+'\'])" src="'+_jsonUrl+obj.imgUrl+'" style="width: 48%;height: 100px">'
					 	// }else {
					 	// 	imgList+='<img onclick="previewImage([\''+_jsonUrl+obj.imgUrl+'\'])" src="'+_jsonUrl+obj.imgUrl+'" style="width: 48%;height: 100px;margin-right: 2%">'
					 	// }
					 	imgList+='<div onclick="previewImage([\''+_jsonUrl+obj.imgUrl+'\'])" class="bui_mt_8">'+'<img style="width: 100%" src="'+ _jsonUrl+obj.imgUrl +'">'+'</div>'
					 });
				};
				_text+=	'<div >'
								+		imgList
								+	'</div>'
				$("#contentBox").html(_text);
			}
		},
		error: function (data,reo){
			buijs_alert({
				content: "网络超时,请刷新重试。("+data.status+":"+reo+")"
			})
		}
	})
}

/*获取该帖子的评论信息*/
function commentList() {
	$.ajax({
		type: "get",
		url: _jsonUrl+ "snsComment/front/findCommentByPage?postId="+_articleId+"&pageNo="+_pageNo+"&pageSize="+"5",
		dataType: 'json',
		success: function(data) {
			var _htmlList='';
			var nowDate= (new Date()).getTime();

			if (!(_pageNo > data.data.pageCount)) {
                $("#communityList_tips").removeClass('active');
            } else {
                $("#communityList_tips").html('<i class="fa fa-check"></i> 已经加载完成');
                isScrollUpdata = false;
                return
            }
            $.map(data.data.queryResult,function(item){
            	var levelPict= 0;
            	var showImgRule= [];
//            	item.member.levelList && item.member.levelList.map(function(items){
//					if(items.moduleType == "100"){
//						levelPict= items.grade;
//					}
//				});
            	
            	if(item.member.memberExtInfoMap && item.member.memberExtInfoMap.levelList && item.member.memberExtInfoMap.levelList.mt100){
					levelPict = item.member.memberExtInfoMap.levelList.mt100.grade;
				}
            	
				var _reqList= "";
				$.map(item.replies,function(pram,index){
					if (index<2) {
						 _reqList+= '<div>'
							+		'<div mks="'+pram.id+'" class="bui_ptb_6" onclick="articleComment(\''+pram.memberName+'\','+pram.parentCommentId+','+pram.memberId+')">'
							+			'<p class="bui_ts_12" style="padding-left: 52px"><span style="color: #259">'+pram.memberName+(pram.parentMemberName? ('回复'+pram.parentMemberName) : "")+'：</span>'+pram.content+'<span class="bui_pl_12 bui_tc_white_d64 bui_ts_10">'+changeDateFormat(pram.createTime)+'</span></p>'
							+		'</div>'
							+	'</div>'
					}else {
						_reqList += '<div mks="'+pram.id+'" reply_conmentId="'+item.id+'" style="display: none">'
							+		'<div class="bui_ptb_6" onclick="articleComment(\''+pram.memberName+'\','+pram.parentCommentId+','+pram.memberId+')">'
							+			'<p class="bui_ts_12" style="padding-left: 52px"><span style="color: #259">'+pram.memberName+(pram.parentMemberName? ('回复'+pram.parentMemberName) : "")+'：</span>'+pram.content+'<span class="bui_pl_12 bui_tc_white_d64 bui_ts_10">'+changeDateFormat(pram.createTime)+'</span></p>'
							+		'</div>'
							+	'</div>'
					};   
				});
				var _imgList = "";
				if (item.postImgs.length>0) {
					$.map(item.postImgs,function(imgurlA){
						showImgRule.push(_jsonUrl + imgurlA.imgUrl)
					})
				}
				$.map(item.postImgs,function(obj,index){
					_imgList += "<li class='bui_p_6' style='position: relative; height: 100px;' onclick='showIMGFUN("+JSON.stringify(showImgRule)+","+index+")'>"
								+        '<div style="height: 100%" data-bui_img=""><img src="'+_jsonUrl+obj.imgUrl+'" id="showImg0" style="width: 100%;"></div>'
	                            +    '</li>'
				})
	            var more = (item.replies.length >2 ? '<span moreBt_commentId="'+item.id+'" onclick="openReply('+item.id+')" style="color: #259;float: right">更多回复...</span>' : '')
				_htmlList+=	'<li class="bui_pt_12" onclick="articleComment(\''+item.memberName+'\','+item.id+',\''+item.memberId+'\',1)">'
							+	'<div class="bui_media">'
							+		'<div class="bui_media_l">'
							+			'<div class="commentBox_face">'
							+				'<img src="'+item.memberImgUrl+'" data-bui_img="">'
							+			'</div>'
							+		'</div>'
							+		'<div class="bui_media_b">'
							+			'<div class="bui_ts_16 bui_inline bui_vm"><span>'+item.memberName+'&nbsp;</span><div class="bui_bgc_red bui_tc_white bui_plr_6 bui_radius bui_inline" style="display: inline-block;"><span class="bui_ts_12 bui_tc_yellow_l24">Lv</span><span class="bui_ts_8">'+(levelPict || 0)+'</span></div></div>'  
							+		'</div>'
							+	'</div>'
							+	'<p class="bui_p_6" style="padding-left: 52px !important;">'+item.content+'</p>'
							+	'<ul class="bui_avg_sm_3 conmmentImgWrap" style="margin-left: 45px;margin-bottom: 10px">'
							+	_imgList
							+	'</ul>'
							+	'<div reply_commentId="'+item.id+'">'
							+	_reqList
							+	'</div>'
							+	'<div>'
							+ 	'<p class="bui_tc_white_d64 bui_ts_12 bui_pb_12">'
							+		'<span id="createTime">'+$.dateDiff(item.createTime,nowDate)+'</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'
							+		'<i class="fa fa-commenting-o"></i>'
							+		'<span id="messageNum"> '+(item.commentNum || 0)+'</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'
							+	more
							+	'</p>'
							+	'</div>'
							+'</li>'
							+'<hr />'
			});

			$("#commentBox").append(_htmlList).find('[data-bui_img]').buijs_img();
			$(".bui_mo_b").checkScrollB(function() {
                if (!$("#communityList_tips").hasClass('active')) {
                    $("#communityList_tips").addClass('active').html('<i class="fa fa-circle-o-notch fa-spin"></i> 加载中...<br />健乐多社区版权所有');
                    _pageNo++;
                    setTimeout(function() {
                        commentList();
                    }, 300);
                }
            });
		},
		error: function (data,reo){
			buijs_alert({
				content: "网络超时,请刷新重试。("+data.status+":"+reo+")"
			})
		}
	})
}

/*展开更多回复*/
function openReply (id){
	event.stopPropagation();
	$('[reply_conmentId="'+id+'"]').show();
	$('[moreBt_commentId="'+id+'"]').hide();
}

/*点赞评论*/
function likeComment (id) {
	event.stopPropagation();
	$.ajax({
		type: 'get',
		url: _jsonUrl + "snsComment/front/likeComment?commentId="+id+"&isLike=1",
		dataType: 'json',
		success: function (data) {
			if (data.status == "00") {
				buijs_alert({
					content: "点赞成功"
				});
				var lickNum= Number($("[commentId="+id+"]").text());
				$("[commentId="+id+"]").html(lickNum+1)
			}else{
				buijs_alert({
					content: data.msg
				});
			}
		},
		error: function (data,reo){
			buijs_alert({
				content: "网络超时,请刷新重试。("+data.status+":"+reo+")"
			})
		}
	})
}

//弹出评论框   commentPram 值为''空，则表示评论帖子,id为文章id   || 值不会空则表示回复的名称，id为评论id 
function articleComment(parentMemberName,commentId,parentMemberId,reCall) { 
	event.stopPropagation();   
	var _modalTitle = ''
	var url;
	var _data={};
	if (!commentId) {
		_modalTitle = '评论帖子';
	} else {
		_modalTitle = '回复'+parentMemberName;
	};



	if (parentMemberName != '') {
		buijs_modal({
			title: _modalTitle,
			positions: 'bottom',
			content: '<textarea onchange="textareag()" maxlength="140" rows="6" placeholder="' + _modalTitle + '" class="bui_ipt_48 bui_block bui_bgc_white bui_ts_16" id="articleCommentTextarea"></textarea><div class=""><ul class="bui_avg_sm_3 imgWrap"></ul></div>',
			footer: '<p style="margin-left: 14px"></p><div class="bui_m_8 bui_avg_sm_2 bui_row_p_12">' +
				'	<li><a href="javascript:window.history.go(-1);" class="bui_btn_48 bui_block bui_bgc_white_d48 bui_atc_white">取消</a></li>' +
				'	<li id="submit"><a href="javascript:void(0);" class="bui_btn_48 bui_block bui_bgc_red bui_atc_white">发表</a></li>' +
				'</div>'
		});
	}else {
		buijs_modal({
			title: _modalTitle,
			positions: 'bottom',
			content: '<textarea onchange="textareag()" maxlength="140" rows="6" placeholder="' + _modalTitle + '" class="bui_ipt_48 bui_block bui_bgc_white bui_ts_16" id="articleCommentTextarea"></textarea><div class=""><ul class="bui_avg_sm_3 imgWrap"></ul></div>',
			footer: '<p style="margin-left: 14px">'+
						// '<span onclick="pict3()" style="display: inline-block;width: 25px;text-align: center;"><i class="fa fa-camera"></i></span>'+
						'<span id="addPict" style="display: inline-block;width: 30px;text-align: center;"><i class="fa fa-image" style="font-size: 25px"></i><input type="file" id="photo" name="file" onchange="pict4()" accept="image/jpg,image/jpeg,image/png,image/gif,image/bmp" style="position: absolute;width: 25px;height: 25px;left: 15px;opacity: 0;" /></span>'+
						'</p><div class="bui_m_8 bui_avg_sm_2 bui_row_p_12">' +
				'	<li><a href="javascript:void(0);" onclick="clearData()" class="bui_btn_48 bui_block bui_bgc_white_d48 bui_atc_white">取消</a></li>' +
				'	<li id="submit"><a href="javascript:void(0);" class="bui_btn_48 bui_block bui_bgc_red bui_atc_white">发表</a></li>' +
				'</div>'
		});
	}

	$("textarea").val(textareaHistory);

	$(".imgWrap").html(imgHtml)

	$("#submit").bind("click",function(){
		var content= $("textarea").val();
		if (!content.trim() && pictUrlArray.length==0) {
			return buijs_alert({
				content: "请输入评论内容或图片"
			})
		}

		var uploadImgArray=[];
	    for (var i= 0;i<pictUrlArray.length; i++) {
	        var k= true;
	        for (var j= 0;j< deleteImgArray.length; j++) {
	            if (i == deleteImgArray[j]){
	                k= false;
	                break;
	            }
	        }
	        if (k) {
	            uploadImgArray.push(pictUrlArray[i])
	        }
	    }
		if (buijs_formcheck_length(content, 140, 0)) {
			$.ajax({
				type: 'post',
				url: _jsonUrl+ "snsComment/front/create",
				dataType: 'json',
				contentType: 'application/json',
				data: JSON.stringify({
					content : content,
					postId: _articleId,
					postImgs: uploadImgArray,
					parentCommentId: (commentId || 0),
					parentMemberName: (reCall?  "": parentMemberName) || "",
					parentMemberId: parentMemberId || ""
				}),
				beforeSend: function() {
					$("#submit").unbind();
				},
				success: function(data){
					if(data.status != '00'){
						buijs_alert({
							content: data.msg,
							time: 4000
						});
						return;
					}
					
					buijs_alert({
						content: '发表成功',
						time: 2000
					});
					
					clearData();
					if (parentMemberName != '') {
						openReply(commentId);
						$('[reply_commentId = '+commentId+']').append('<div class="bui_ptb_6">'
							+			'<p class="bui_ts_12" style="padding-left: 52px"><span style="color: #259">'+currentUser+(reCall ? "" : ('回复'+parentMemberName))+'：</span>'+content+'<span class="bui_pl_12 bui_tc_white_d64 bui_ts_10">'+changeDateFormat((new Date()).getTime())+'</span></p>'
							+		'</div>'
							+	'</div>');
					}else {
						if (!isScrollUpdata) {
							var _imgList = "";
							var _htmlList= "";
							var showImgRule = [];
							var nowDate= (new Date()).getTime();
							if (uploadImgArray.length>0) {
								$.map(uploadImgArray,function(imgurlA){
									showImgRule.push(_jsonUrl + imgurlA.imgUrl)
								})
							}
							$.map(uploadImgArray,function(imgurlAB,index){
								_imgList += "<li class='bui_p_6' style='position: relative; height: 100px;' onclick='showIMGFUN("+JSON.stringify(showImgRule)+","+index+")'>"
											+        '<div style="height: 100%" data-bui_img=""><img src="'+_jsonUrl+imgurlAB.imgUrl+'" id="showImg0" style="width: 100%;"></div>'
				                            +    '</li>'
							})
							_htmlList+=	'<li class="bui_pt_12">'
										+	'<div class="bui_media">'
										+		'<div class="bui_media_l">'
										+			'<div class="commentBox_face">'
										+				'<img src="'+currentHeadImgUrl+'" data-bui_img="">'
										+			'</div>'
										+		'</div>'
										+		'<div class="bui_media_b">'
										+			'<div class="bui_ts_16 bui_inline bui_vm"><span>'+currentUser+'&nbsp;</span><div class="bui_bgc_red bui_tc_white bui_plr_6 bui_radius bui_inline" style="display: inline-block;"><span class="bui_ts_12 bui_tc_yellow_l24">Lv</span><span class="bui_ts_8">'+(currentHevelPict || 0)+'</span></div></div>'  
										+		'</div>'
										+	'</div>'
										+	'<p class="bui_p_6" style="padding-left: 52px !important;">'+content+'</p>'
										+	'<ul class="bui_avg_sm_3 conmmentImgWrap" style="margin-left: 45px;margin-bottom: 10px">'
										+	_imgList
										+	'</ul>'
										+	'<div>'
										+	'</div>'
										+	'<div>'
										+ 	'<p class="bui_tc_white_d64 bui_ts_12 bui_pb_12">'
										+		'<span id="createTime">'+$.dateDiff(nowDate,nowDate)+'</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'
										+		'<i class="fa fa-commenting-o"></i>'
										+		'<span id="messageNum">0</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'
										+	'</p>'
										+	'</div>'
										+'</li>'
										+'<hr />';
							$("#commentBox").append(_htmlList).find('[data-bui_img]').buijs_img();
						}
					}
				},
				error: function () {
					buijs_alert({
						content: '发表失败',
						time: 2000
					});
					window.history.go(-1);
				}
			})
		}else {
			buijs_alert({
				content: "评论不能为空，且在140字内"
			});
		};
	});
	setTimeout(function() {
		$("#articleCommentTextarea").focus();
	}, 300)

};

//微信sdk打开照相机  by gongzr
function pict3() {
	wx.chooseImage({
		count: 1, // 默认9
		sizeType: ['original', 'compressed'], // 可以指定是原图还是压缩图，默认二者都有
		sourceType: ['camera', 'album'], // 可以指定来源是相册还是相机，默认二者都有
		success: function(res) {
			// console.log(res);
			var localId = res.localIds[0];
			// $(dom).attr('src', localId);
			//res.localIds 返回选定照片的本地ID列表，localId可以作为img标签的src属性显示图片
			// if (callbackF) callbackF(localId);
			wx.uploadImage({
				localId: localId, // 需要上传的图片的本地ID，由chooseImage接口获得
				isShowProgressTips: 1, // 默认为1，显示进度提示
				success: function(arm) {
					pictUrlArray.push(serverId); // 返回图片的服务器端ID
						// $(".imgWrap").append('<li imgid="0" class=" bui_p_6" id="addImgBox" style="position: relative; height: 121px;">'
 //                            +        '<span id="deleteImg0" style="display: none;position: absolute; z-index: 2; font-size: 20px; line-height: 18px;top: 6px; width: 20px; text-align: center;height: 20px;right: 7px;border-radius: 10px;background-color: red;color: white" onclick="deleteImg('+0+')"><i class="fa fa-close"></i></span>'
 //                            +        '<img src="images/picture.jpg" id="showImg0" style="width: 109px; height: 109px;">'
 //                            +        '<input type="file" id="photo" name="file" class="selectImg" onchange="changeImg()" accept="image/jpeg,image/png,image/gif,image/bmp" style="width: 109px; height: 109px;">'
 //                            +    '</li>')
				}
			});

		},
		error: function (data,reo){
			buijs_alert({
				content: "网络超时,请刷新重试。("+data.status+":"+reo+")"
			})
		}
	});
}

/*图片上传*/
function pict4() {
	if (i >=6) {
		buijs_alert({
			content: "最多上传6张图片"
		});
		return;
	}
	if (!uploadCtrl) return;
	$(".imgWrap").append('<li id="imgUpLoad" class=" bui_p_6"style="position: relative; height: 115px;">'
								+        '<div class="bui_bgc_black_f48" style="height: 100%"><i class="fa fa-circle-o-notch fa-spin bui_fas_24 bui_fac_white bui_p_12" style="position: absolute;left: 50%; margin-left: -25px;top: 30px"></i></div>'
	                            +    '</li>');
    uploadCtrl= false;
    $.ajaxFileUpload({
        url:_jsonUrl+ "/attachment/front/upload",//处理图片脚本
        secureuri :false,
        fileElementId :'photo',//file控件id
        dataType : 'json',
        success :function(data){
        	$("#imgUpLoad").remove();
        	if (data.status == '00') {
        		i++;
        		pictUrlArray.push({imgUrl: data.data.fileUrl,id: data.data.id});
	            $(".imgWrap").append('<li imgid="'+data.data.id+'" class=" bui_p_6" id="addImgBox" style="position: relative; height: 115px;">'
	                            +        '<span id="deleteImg0" style="position: absolute; z-index: 2; font-size: 20px; line-height: 18px;top: 6px; width: 20px; text-align: center;height: 20px;right: 7px;border-radius: 10px;background-color: red;color: white" onclick="deleteImg('+(ImgIdA++)+','+data.data.id+')"><i class="fa fa-close"></i></span>'
	                            +        '<img src="'+(_jsonUrl+data.data.fileUrl)+'" id="showImg0" style="width: 109px; height: 109px;">'
	                            +    '</li>');
	            imgHtml= $(".imgWrap").html();
	            uploadCtrl= true;
        	}
            
        },
        error: function (data,reo){
			buijs_alert({
				content: "网络超时,请刷新重试。("+data.status+":"+reo+")"
			})
		}
    });
}

/*清除发表项数据   by Gzr*/
function clearData() {
	pictUrlArray=[];
	deleteImgArray= [];
	commentPage= 1;
	textareaHistory= "";
	imgHtml= "";
	i=0;
	window.history.go(-1);
}

/*记录删除图片  by Gzr*/
function deleteImg(ImgIdA,imgid) {
	$("[imgid='"+imgid+"']").remove();
	deleteImgArray.push(ImgIdA);
	imgHtml= $(".imgWrap").html();
	i--;
}

/*记录发表文字内容  by Gzr*/
function textareag(){
	textareaHistory= $("textarea").val();
}
