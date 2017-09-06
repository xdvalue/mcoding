var _pageNo = 1;    //记录页码

$(function(){

	var lastType = window.sessionStorage.getItem('news_detail_lastType');
	$("#_tab div").on("click",tabContent);
	if (lastType == 'news') {
		$("#comment").removeClass("news_centerTabActive");
		$("#news").addClass("news_centerTabActive");
		window.sessionStorage.removeItem('news_detail_lastType');
		getNewsBySys();
	}else {
		getCommentByMe();
	}
});

/*切换评论我的 & 系统消息*/
function tabContent () {
	var id= $(this).attr("id");
	$("#contentUl").html('');
	$("#hg_articleList_tips").html('<i class="fa fa-circle-o-notch fa-spin"></i> 加载中<br />健乐多社区版权所有');
	if (id == "comment") {
		_pageNo= 1;
		$("#news").removeClass("news_centerTabActive");
		$("#comment").addClass("news_centerTabActive");
		getCommentByMe();
	}
	else if (id == "news") {
		_pageNo= 1;
		$("#comment").removeClass("news_centerTabActive");
		$("#news").addClass("news_centerTabActive");
		getNewsBySys();
	}
}

/*获取评论我的数据*/
function getCommentByMe () {
	$.ajax({
		type: "get",
		url : _jsonUrl+ "snsMsgInbox/front/findCommentMsg?pageNo="+_pageNo+"&pageSize=5",
		dataType: 'json',
		success: function(data) {
			var _html="";
			if ( _pageNo >  data.pageCount) {
				$("#hg_articleList_tips").html('<i class="fa fa-check"></i> 已经加载完成<br />健乐多社区版权所有');
				return
			}else if (_pageNo == data.pageCount){
				$("#hg_articleList_tips").html('<i class="fa fa-check"></i> 已经加载完成<br />健乐多社区版权所有');
			}
			$("#hg_articleList_tips").removeClass('active');
			if (data.queryResult && data.queryResult.length > 0) {
				$.map(data.queryResult,function (_data){
					var levelPict = 0;
					var newTip= "";
					var _imgList = "";
					var showImgRule = [];
					var isImg = false;
					if (_data.isRead == 0) {
						newTip= 'bui_bgc_red';
					}
//					_data.snsComment.member.levelList && _data.snsComment.member.levelList.map(function(pram){
//						if(pram.moduleType == "100"){
//							levelPict= pram.grade;
//						}
//					});
					
					if(_data.snsComment.member.memberExtInfoMap && _data.snsComment.member.memberExtInfoMap.levelList && _data.snsComment.member.memberExtInfoMap.levelList.mt100){
		            	levelPict = _data.snsComment.member.memberExtInfoMap.levelList.mt100.grade;
		            }
					
					if (_data.snsComment.postImgs.length>0) {
						isImg= true;
					}
					_html+= '<li class="bui_pt_6" onclick="goArticleDetail('+_data.snsPost.id+','+_data.id+')">'
						+					'<hr />'
						+					'<div class="bui_bgc_white bui_plr_12 bui_ptb_6 touchHove" style="position: relative;">'
						+						'<div class="bui_media bui_pb_6">'
						+							'<div class="bui_media_l">'
						+								'<div class="contentPostList_li_face">'
						+									'<img src="'+_data.snsComment.memberImgUrl+'">'
						+								'</div>'
						+							'</div>'
						+							'<div class="bui_media_b">'
						+								'<div class="bui_ts_16 bui_inline bui_vm">'
						+									'<span>'+_data.snsComment.memberName+'</span>&nbsp;'
						+									'<div class="bui_bgc_red bui_tc_white bui_plr_6 bui_radius bui_inline" style="display: inline-block;">'
						+										'<span class="bui_ts_12 bui_tc_yellow_l24">Lv</span>'
						+										'<span class="bui_ts_8">'+levelPict+'</span>'
						+									'</div>'
						+								'</div>'
						+								'<div class="bui_ts_10 bui_tc_gray">'+changeDateFormat(_data.createTime)+'</div>'
						+							'</div>'
						+							'<div class="bui_media_r" onclick="reCall(\''+_data.snsComment.memberName+'\',\''+(_data.snsComment.parentCommentId || _data.snsComment.id)+'\',\''+_data.snsComment.memberId+'\',\''+_data.snsPost.id+'\')"><div class="replyBt bui_tc_black_f48" style="border: 1px solid rgba(0,0,0,0.48)">回复</div></div>'
						+						'</div>'
						+						'<p class="bui_ts_14 bui_ptb_6 s_cl2"><span class="bui_ts_10 bui_tc_blue">'+(_data.snsComment.parentMemberName? ("回复"+_data.snsComment.parentMemberName+"："): "")+'</span>'+_data.snsComment.content+'</p><span class="bui_ts_12 bui_tc_white_d72">'+(isImg?"(评论中带有图片请进入查看)": "")+'</span>'
						// +						'<ul class="bui_avg_sm_3 conmmentImgWrap" style="margin-left: 45px;margin-bottom: 10px">'
						// +							_imgList
						// +						'</ul>'
						+						'<div class="bui_media" style="background-color: #F0F0F0;">'
						+							'<div class="bui_media_l">'
						+								'<div class="contentPostList_li_Img" data-bui_img="">'
						+									'<img src="'+((_data.snsPost.postImgs[0] && (_jsonUrl+_data.snsPost.postImgs[0].imgUrl)) || _data.snsPost.memberImgUrl)+'">'
						+								'</div>'
						+							'</div>'
						+							'<div class="bui_media_b bui_pr_6">'
						+								'<div class="bui_ts_16 bui_inline bui_vm s_cl1" style="margin-top: 3px">'
						+									_data.snsPost.title
						+								'</div>'
						+								'<div class="bui_ts_10 bui_tc_gray s_cl2" style="padding-top: 3px">'+_data.snsPost.content.replace(/<[^>]+>/g,"")+'</div>'
						+							'</div>'
						+						'</div>'
						+						'<div class="replyBt bui_tc_white '+newTip+'" style="line-height: 13px;height: 15px;width: 30px;border-radius: 5px;position: absolute;right: 60px;top: 12px;">new</div>'
						+					'</div>'
						+					'<hr />'
						+				'</li>'
				})
			}
			$("#contentUl").append(_html).find('[data-bui_img]').buijs_img();
			
			$(".bui_mo_b").checkScrollB(function() {
				if (!$("#hg_articleList_tips").hasClass('active')) {
					$("#hg_articleList_tips").addClass('active').html('<i class="fa fa-circle-o-notch fa-spin"></i> 加载中...<br />健乐多社区版权所有');
					_pageNo++;
					setTimeout(function() {
						getCommentByMe();
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

/*信息回复按钮事件*/
function reCall(parentMemberName,commentId,parentMemberId,snsPostId){
	event.stopPropagation();   
	var _modalTitle = ''
	var url;
	var _data={};
	if (parentMemberName == '') {
		_modalTitle = '评论帖子';
	} else {
		_modalTitle = '回复'+parentMemberName;
	};



	if (parentMemberName != '') {
		buijs_modal({
			title: _modalTitle,
			positions: 'bottom',
			content: '<textarea maxlength="140" rows="6" placeholder="' + _modalTitle + '" class="bui_ipt_48 bui_block bui_bgc_white bui_ts_16" id="articleCommentTextarea"></textarea><div class=""><ul class="bui_avg_sm_3 imgWrap"></ul></div>',
			footer: '<p style="margin-left: 14px"></p><div class="bui_m_8 bui_avg_sm_2 bui_row_p_12">' +
				'	<li><a href="javascript:window.history.go(-1);" class="bui_btn_48 bui_block bui_bgc_white_d48 bui_atc_white">取消</a></li>' +
				'	<li id="submit"><a href="javascript:void(0);" class="bui_btn_48 bui_block bui_bgc_red bui_atc_white">发表</a></li>' +
				'</div>'
		});
	}else {
		buijs_modal({
			title: _modalTitle,
			positions: 'bottom',
			content: '<textarea maxlength="140" rows="6" placeholder="' + _modalTitle + '" class="bui_ipt_48 bui_block bui_bgc_white bui_ts_16" id="articleCommentTextarea"></textarea><div class=""><ul class="bui_avg_sm_3 imgWrap"></ul></div>',
			footer: '<p style="margin-left: 14px">'+
						'<span id="addPict" style="display: inline-block;width: 30px;text-align: center;"><i class="fa fa-image" style="font-size: 25px"></i><input type="file" id="photo" name="file" onchange="pict4()" accept="image/jpg,image/jpeg,image/png,image/gif,image/bmp" style="position: absolute;width: 25px;height: 25px;left: 15px;opacity: 0;" /></span>'+
						'</p><div class="bui_m_8 bui_avg_sm_2 bui_row_p_12">' +
				'	<li><a href="javascript:void(0);" onclick="clearData()" class="bui_btn_48 bui_block bui_bgc_white_d48 bui_atc_white">取消</a></li>' +
				'	<li id="submit"><a href="javascript:void(0);" class="bui_btn_48 bui_block bui_bgc_red bui_atc_white">发表</a></li>' +
				'</div>'
		});
	}

	$("textarea").val();

	$(".imgWrap").html()

	$("#submit").bind("click",function(){
		var content= $("textarea").val();
		if (buijs_formcheck_length(content, 140, 0)) {
			$.ajax({
				type: 'post',
				url: _jsonUrl+ "snsComment/front/create",
				dataType: 'json',
				contentType: 'application/json',
				data: JSON.stringify({
					content : content,
					postId: snsPostId,
					postImgs: [],
					parentCommentId: (commentId || 0),
					parentMemberName: parentMemberName || "",
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
					window.history.go(-1);
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
}


/*点击列表进入帖子详情*/
function goArticleDetail(postId,id) {
	$.ajax({
		type: 'get',
		url: _jsonUrl+ 'snsMsgInbox/front/setMsgIsReaded?msgInboxId='+id,
		dataType: 'json',
		success: function (data){
			window.sessionStorage.setItem('news_detail_lastType','postList');
			window.location.href= "article_detail.html?articleId="+postId;
		}
	})
	
}

/*获取系统消息的数据*/
function getNewsBySys () {
	$.ajax({
		type: "get",
		url: _jsonUrl+ "snsMsgInbox/front/findSystemMsg?pageNo="+_pageNo+"&pageSize=10",
		dataType: 'json',
		success: function (data){
			var _html="";
			if ( _pageNo >  data.pageCount) {
				$("#hg_articleList_tips").html('<i class="fa fa-check"></i> 已经加载完成<br />健乐多社区版权所有');
				return
			}else if (_pageNo == data.pageCount){
				$("#hg_articleList_tips").html('<i class="fa fa-check"></i> 已经加载完成<br />健乐多社区版权所有');
			}
			$("#hg_articleList_tips").removeClass('active');
			if (data.queryResult.length>0) {
				$.map(data.queryResult,function(item){
					var newTip="";
					if (item.isRead == 0) {
						newTip= 'bui_bgc_red';
					}
					_html+='<li onclick="systemNewClick(\''+item.snsMsg.url+'\','+item.id+')" class="bui_pt_6" >'
						+					'<hr />'
						+					'<div class="bui_bgc_white bui_plr_12 bui_ptb_6 bui_media">'
						+						'<div class="bui_media_b">'
						+							'<p style="font-size: 15px">'+item.snsMsg.title+'</p>'
						+							'<p style="font-size: 12px;color: gray">'+item.snsMsg.summary+'</p>'
						+							'<p class="bui_ts_12 bui_tc_white_d64">'+changeDateFormat(item.createTime)+'</p>'
						+						'</div>'
						+						'<div class="bui_media_r"><div class="replyBt bui_tc_white '+newTip+'">new</div></div>'
						+					'</div>'
						+					'<hr />'
						+				'</li>'
				});
				_html= '<ul>'+_html+'</ul>';
			}else {
				_html= '<div style="text-align: center;padding-top: 150px">'
						+	'<img style="width: 160px" src="./images/nothing.png">'
						+'<div>';
			}
			$("#contentUl").html(_html);

			$(".bui_mo_b").checkScrollB(function() {
				if (!$("#hg_articleList_tips").hasClass('active')) {
					$("#hg_articleList_tips").addClass('active').html('<i class="fa fa-circle-o-notch fa-spin"></i> 加载中...<br />健乐多社区版权所有');
					_pageNo++;
					setTimeout(function() {
						getNewsBySys();
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


/*系统信息点击事件*/
function systemNewClick (url,id) {
	window.sessionStorage.setItem('news_detail_lastType','news');
	$.ajax({
		type: 'get',
		url: _jsonUrl+ 'snsMsgInbox/front/setMsgIsReaded?msgInboxId='+id,
		dataType: 'json',
		success: function (data) {
				if (url != "" && url != "null") {
					window.location.href = url;
				}else {
					window.location.href = "news_detail.html?id="+id;
				}
		},
		error: function (data,reo){
			buijs_alert({
				content: "网络超时,请刷新重试。("+data.status+":"+reo+")"
			})
		}
	})
}