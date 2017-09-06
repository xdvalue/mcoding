// 菜单配置
var menus= [
		{
			"name": "我的",
			"img": "./images/me.png",
			"url": "my.html"
		},
		{
			"name": "精品",
			"img": "./images/module.png",
			"url": "community_list.html?typeName=perfectText"
		},
		{
			"name": "签到",
			"img": "./images/sign.png",
			"url": "http://v.gymmaxer.com/gMall/member_sign.html"
		},
		{
			"name": "发帖",
			"img": "./images/post.png",
			"url": "post_message.html"
		}
	]
var _pageNo_index= 1;
var oldTop= 0;           //记录前一个top值
$(function(){
	insetIndexBanner();
	insetIndexBtBar();

	//精典 & 活帖子置顶区载入
	// insetPerfectText();
	
    //分类模块区载入
    insetClassifyModule();

    //检查是否有新消息
	haveNewsCheck();

	contentList();

	$('[data-bui_img]').buijs_img();
	$(".bui_mo_b").on("touchmove scroll",function(){
		var top= $(this).scrollTop();
		var isShow= top-oldTop;
		if (top >=170 && isShow<0) {
			$(".indexBtBarFixed").show();
			$("#indexBtBar").css('visibility','hidden');
		}else if (top >=170 && isShow>0){
			$(".indexBtBarFixed").hide();
			$("#indexBtBar").css('visibility','hidden');
		}else if (top <170 ) {
			$(".indexBtBarFixed").hide();
			$("#indexBtBar").css('visibility','');
		}
		oldTop= top;
	});
	wechatSharePublic("健乐多微社区", "健乐多微社区，成就肌肉的力量", 'http://v.gymmaxer.com/sns_module/mobileview/community/index.html', false, '', 'mobileview/community/images/logoShare.jpg');
})


/*查看是否有新消息*/
function haveNewsCheck() {
	$.ajax({
		type: 'get',
		url: _jsonUrl+ 'snsMsgInbox/front/getTheCountNumForNewMsg',
		dataType: 'json',
		success: function (data){
			if (data.data["100"] >0 || data.data["200"] >0) {
				$('#haveNews').show();
			}
		},
		error: function(data,reo) {
			buijs_alert({
				centent: "网络超时,请刷新重试。("+data.status+":"+reo+")"
			})
		}
	})
}

/*分类模块区域载入*/
function insetClassifyModule() {
	$.ajax({
		type: 'get',
		url: _jsonUrl+ 'snsModule/front/findAllModules',
		dataType: 'json',
		success: function(data){
			if (data.status == "00") {
				var _insetClassifyModuleHtml="";
				data.data.map(function (item,key){

					_insetClassifyModuleHtml+= '<div class="bui_col_line_b bui_col_line_r"><a class="bui_media bui_bds_1_r" href="community_list.html?typeName='+encodeURIComponent(item.name)+'&id='+item.id+'">'
					+'<div class="bui_media_l bui_pl_6" style="padding-right: 0;"><img class="bui_mt_6" src="'+item.imgUrl+'" style="width: 32px;height: 32px;" /></div> '
					+'<div class="bui_media_r"><p>'+item.name+'</p><p class="bui_tc_gray bui_ts_12">（帖子：'+(item.postCount || 0)+'）</p></div>'
					+'</a></div>'
				})
				_insetClassifyModuleHtml = '<div class="bui_avg_sm_2 bui_ts_14 bui_row_p_12">'+_insetClassifyModuleHtml+'</div>';
				$("#postingsModule").html(_insetClassifyModuleHtml);
			}else {
				buijs_alert({
					content: data.msg
				})
			}
		},
		error: function (data,reo){
			buijs_alert({
				content: "网络超时,请刷新重试。("+data.status+":"+reo+")"
			})
		}
	})
}

/*主页幻灯片载入*/
function insetIndexBanner() {
	$.ajax({
		type: "get",
		url: _jsonUrl+ "snsBanner/front/findAll",
		async: true,
		dataType: 'json',
		error: function(data,reo) {
			buijs_alert({
				centent: "网络超时,请刷新重试。("+data.status+":"+reo+")"
			})
		},
		success: function(data) {
			var _list = '';
			//有内容
			if (data.data && data.data.length > 0) {
				//判断必须带有图片
				$.map(data.data, function(item) {
					if (item.imgUrl) {
						_list += '<a href="'+item.aUrl+'" class="item touchHove" ><img style="width: 100%;height: auto" src="' + item.imgUrl + '" /></a>';
					};
				});
				$("#indexBanner").html('<div class="box">' + _list + '</div>');
				$("#indexBanner").buijs_swiper();
				$("#indexBanner img").load(function(){
					$("#indexBanner").height($(this).height())
				})
			}
			//无内容
			else {
				$("#indexBanner").hide();
			}
		}
	});
};

/*主页按钮区域载入*/
function insetIndexBtBar () {
	var _htmlBt= '';
	var _htmlBt_top= '';
	$.map(menus,function(menu){
		_htmlBt+= '<li class="bui_col_line_r bui_col_line_b touchHove">'
				+	'<a href="'+menu.url+'" class="bui_ptb_6 bui_block bui_inline">'
				+		'<div class="bui_round" style="width: 32px;height: 32px;"><img src="'+menu.img+'" style="width: 100%"/></div>'
				+		'<p class="bui_block">'+menu.name+'</p>'
				+	'</a>'
				+'</li>'
	});
	$("#indexBtBar").html(_htmlBt);
	$.map(menus,function(menu){
				_htmlBt_top+= '<li class="bui_col_line_b touchHove">'
						+	'<a href="'+menu.url+'" class="bui_ptb_6 bui_block bui_inline">'
						+		'<div class="bui_round" style="width: 32px;height: 32px;"><img src="'+menu.img+'" style="width: 100%"/></div>'
						+		'<p class="bui_block">'+menu.name+'</p>'
						+	'</a>'
						+'</li>'
			});
			_htmlBt_top='<div class="bui_bgc_white indexBtBarFixed" style="display: none">'
					+	'<div class="bui_avg_sm_4 bui_ts_12 bui_fas_16 bui_fac_white bui_ta_c" >'
					+		_htmlBt_top
					+	'</div>'
					+'</div>'
			$(".bui_mo_h").append(_htmlBt_top);
}

/*精典 & 活帖子置顶区*/
function insetPerfectText () {
	var _htmlList= '';
	$.ajax({
		type: 'get',
		url: _jsonUrl+ 'snsPost/front/querySpecialPostByPage?pageNo=1&pageSize=10&typeFlag=0',
		dataType: 'json',
		success: function(data) {
			var _htmlList= '';
			var _htmlList2= '';
			if (data.data && data.data.queryResult && data.data.queryResult.length>0) {
				var length = data.data.queryResult.length;
				$.map(data.data.queryResult,function(item,index){
					_htmlList+= '<a href="javascript:contentPostListFunc('+item.id+');" class="bui_media bui_mlr_12 bui_ptm_6 touchHove action_a">'
								+	'<div class="bui_media_l">'
								+		(item.typeFlag==3?'<div class="pefctTextTitleRed">精</div>':'<div class="pefctTextTitleOrange">活</div>')
								+	'</div>'
								+	'<div class="bui_media_b">'
								+		'<p class="oneSpace">'+(item.title.length >15 ? (item.title.substring(0,15)+"...") : item.title) +'</p>'
								+	'</div>'
								+	'<div class="bui_media_r">'
								+		'<i class="fa fa-angle-right"></i>'
								+	'</div>'
								+ '</a>';
					if (length>3 && index<3) {
						_htmlList2 += '<a href="javascript:contentPostListFunc('+item.id+');" class="bui_media bui_mlr_12 bui_ptm_6 touchHove">'
								+	'<div class="bui_media_l">'
								+		(item.typeFlag==3?'<div class="pefctTextTitleRed">精</div>':'<div class="pefctTextTitleOrange">活</div>')
								+	'</div>'
								+	'<div class="bui_media_b">'
								+		'<p class="oneSpace">'+(item.title.length >15 ? (item.title.substring(0,15)+"...") : item.title) +'</p>'
								+	'</div>'
								+	'<div class="bui_media_r">'
								+		'<i class="fa fa-angle-right"></i>'
								+	'</div>'
								+ '</a>';
					}
				});
				$("#insetPerfectTextContent").html(_htmlList+_htmlList2);
				if (length > 3) {
					setInterval(function(){
						$('#insetPerfectTextContent a').eq(0).css('margin-top','-'+(PerfectTextIndex * 37)+'px');
						PerfectTextIndex++;
						if (PerfectTextIndex == length+1) {
							setTimeout(function(){
								PerfectTextIndex=1;
								return $("#insetPerfectTextContent").html(_htmlList+_htmlList2);
							},2500)
						}
					},5000)
				}
			}
		},
		error: function(data,reo) {
			buijs_alert({
				centent: "网络超时,请刷新重试。("+data.status+":"+reo+")"
			})
		}
	});
	
}

var PerfectTextIndex = 1;

/*主页帖子展示区*/
function contentList () {
	var _htmlList= '';
	$.ajax({
		type: 'get',
		url: _jsonUrl+ 'snsPost/front/findPostByPage?pageNo='+_pageNo_index+'&pageSize= 5&moduleId=0',
		dataType: 'json',
		contentType: 'application/json',
		success: function(data) {
			var nowTime= (new Date()).getTime();
			var _htmlList= '';
			if (data.status != "00" ) {
				return buijs_alert({
					content:data.msg
				})
			}
			if ( _pageNo_index >  data.data.pageCount) {
				$("#hg_articleList_tips").html('<i class="fa fa-check"></i> 已经加载完成<br />健乐多社区版权所有');
				return
			}else {
				$("#hg_articleList_tips").removeClass('active');

				$.map(data.data.queryResult,function(item){
					var tips = ""; //精品贴提示图标
					var imgList= '';
					var levelPict;
					var showImgRule=[];
//					item.member && item.member.levelList && item.member.levelList.map(function(pram){
//						if(pram.moduleType == "100"){
//							levelPict= pram.grade;
//						}
//					});
					
					if(item.member.memberExtInfoMap && item.member.memberExtInfoMap.levelList && item.member.memberExtInfoMap.levelList.mt100){
                    	levelPict = item.member.memberExtInfoMap.levelList.mt100.grade;
                    }

					if (item.postImgs.length>0) {
						$.map(item.postImgs,function(imgurlA,indexK){
							if(indexK>1) return;
							showImgRule.push(_jsonUrl + imgurlA.imgUrl)
						})
					}
					if (item.typeFlag == 3) {
						tips = '<span class="pefctTextTitleRed bui_mr_3">精</span>';
					}
					 $.map(item.postImgs,function(obj,index){
					 	if(index>1) return;
					 	if ((index==0)) {
					 		imgList+="<li onclick='showIMGFUN("+JSON.stringify(showImgRule)+","+index+")'><div style='width:100%; height: 100px; border:1px solid #e7e7e7;' data-bui_img=''><img src='"+_jsonUrl+obj.imgUrl+"' /></div></li>"
					 	}else {
					 		imgList+="<li onclick='showIMGFUN("+JSON.stringify(showImgRule)+","+index+")'><div style='width:100%; height: 100px;border:1px solid #e7e7e7;' data-bui_img=''><img src='"+_jsonUrl+obj.imgUrl+"' /></div></li>"
					 	}
					 });
					_htmlList+='<div class="bui_bgc_white bui_mt_12 bui_plr_12 bui_ptb_12" onclick="contentPostListFunc('+item.id+',\''+item.title.tranferSpecialWord()+'\',\''+item.memberName.tranferSpecialWord()+'\')">'
								+	'<div class="bui_media">'
								+		'<div class="bui_media_l">'
								+			'<div class="contentPostList_li_face">'
								+				'<img src="'+item.memberImgUrl+'">'
								+			'</div>'
								+		'</div>'
								+		'<div class="bui_media_b">'
								+			'<div class="bui_ts_16 bui_inline bui_vm"><span>'+item.memberName+'</span>&nbsp;<div class="bui_bgc_red bui_tc_white bui_plr_6 bui_radius bui_inline" style="display: inline-block;"><span class="bui_ts_12 bui_tc_yellow_l24">Lv</span><span class="bui_ts_8">'+(levelPict || 0)+'</span></div></div>'
								+			'<div class="bui_ts_10 bui_tc_gray">'+$.dateDiff(item.createTime,nowTime)+'</div>'
								+		'</div>'
								+	'</div>'
								+	'<div class="bui_ptm_6 bui_ts_15">'+tips+item.title+'</div>'
								+	'<div class="bui_ts_13 bui_tc_gray">'+(item.content.length >80 ? (item.content.replace(/<[^>]+>/g,"").substring(0,66)+"..."): item.content)+'</div>'
								+	'<div class="contentPostList_li_Pict bui_avg_sm_2 bui_row_p_6">'+imgList+'</div>'
								+	'<p class="bui_ts_13 bui_tc_gray">'+'<i class="fa fa-eye"></i> '+(item.snsPostExtInfo.viewNum || "0")+'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<i class="fa fa-commenting-o"></i> '+(item.snsPostExtInfo.commentNum || "0")+'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<i class="fa fa-thumbs-o-up"></i> '+(item.snsPostExtInfo.likeNum || "0")+'</div>'
								+'</p>'
				});
				$("#contentPostList").append(_htmlList);
				$("#contentPostList").find('[data-bui_img]').buijs_img();
				//检测滚动到底部
				$(".bui_mo_b").checkScrollB(function() {
					if (!$("#hg_articleList_tips").hasClass('active')) {
						$("#hg_articleList_tips").addClass('active').html('<i class="fa fa-circle-o-notch fa-spin"></i> 加载中...<br />健乐多社区版权所有');
						_pageNo_index++;
						setTimeout(function() {
							contentList();
						}, 300);
					}
				});

			}
		},
		error: function(data,reo,sio) {
			buijs_alert({
				centent: "网络超时,请刷新重试。("+data.status+":"+reo+")"
			})
		}
	});
}

/*主页帖子点击事件->帖子详情页*/
function contentPostListFunc (id,title,name) {
	window.sessionStorage.setItem('article_ID',id);
	buijs_showloading('black_f72');
	$.ajax({
		type: 'get',
		url: 'ajax_articleDetail.html',
		complete: function (){
			buijs_closeloading();
		},
		success: function (data){
			$('.bui_wrap').hide();
			wechatSharePublic("健乐多微社区",title, 'http://v.gymmaxer.com/sns_module/mobileview/community/article_detail.html?articleId='+id, false, '', 'mobileview/community/images/logoShare.jpg');
			$('body').append(data);
		},
		error: function(data,reo) {
			buijs_alert({
				centent: "网络超时,请刷新重试。("+data.status+":"+reo+")"
			})
		}
	})
}



/*无功能按钮建设中提示*/
function creatingTips () {
	buijs_alert({
		content: "该功能正在建设中..."
	});
}

(function tranferSpecialWord(){
	String.prototype.tranferSpecialWord = function(){
		if(!/[\'\+]/.test(this)){
			return this;
		}
		
		var str = this.replace(/'([^'])*'?/g, function(word){
			if(word.charAt(word.length - 1) == "'"){
				return "‘" + word.substr(1, word.length-2) + "’";
			}
			
			return "‘" + word.substr(1, word.length-1);
		});
		
		str = str.replace(/"([^"])*"?/g, function(word){
			if(word.charAt(word.length - 1) == '"'){
				return "“" + word.substr(1, word.length-2) + "”";
			}
			
			return "“" + word.substr(1, word.length-1);
		});
		
		str = str.replace(/\+/g, "\+");
		return str;
	}
})();