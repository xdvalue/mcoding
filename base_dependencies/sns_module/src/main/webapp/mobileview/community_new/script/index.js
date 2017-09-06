$(document).ready(function() {
	$('[data-bui_img]').buijs_img(); //图片居中处理
	wechatSharePublic("健乐多微社区", "健乐多微社区，成就肌肉的力量", 'http://v.gymmaxer.com/sns_module/mobileview/community/index.html', false, '', 'mobileview/community/images/logoShare.jpg'); //微信页面分享

	//新消息检测
	global_getJsonSetVue('snsMsgInbox/front/getTheCountNumForNewMsg', 'newMsg');
	//主页幻灯片载入
	global_getJsonSetVue('snsBanner/front/findAll', 'indexBanner', null, function() {
		$("[data-bui_swiper]").buijs_swiper({
			btn: null
		});
	});
	//导航菜单载入
	vm.$set('indexNav', [{
		"name": "我的",
		"img": "./images/me.png",
		"url": "my.html"
	}, {
		"name": "精品",
		"img": "./images/module.png",
		"url": "community_list.html?typeName=perfectText"
	}, {
		"name": "签到",
		"img": "./images/sign.png",
		"url": "http://v.gymmaxer.com/gMall/sign.html?gameid=33"
	}, {
		"name": "发帖",
		"img": "./images/post.png",
		"url": "post_message.html"
	}]);
	//分类模块载入
	global_getJsonSetVue('snsModule/front/findAllModules', 'findAllModules');

	//帖子模块载入
	var pageNo = 1;
	vm.$set('postArray', []); //帖子列表空数组
	vm.$set('listPageLoad', true); //读取阻塞
	insetPostArray(pageNo);

	function insetPostArray(pageNo) {
		vueData.listPageLoad = false;
		global_getJsonSetVue('snsPost/front/findPostByPage', 'findPostByPage', {
			pageNo: pageNo,
			pageSize: 5,
			moduleId: 0
		}, function() {
			setTimeout(function() {
				if(vueData.findPostByPage.data.pageNo >= vueData.findPostByPage.data.pageCount) {
					vueData.listPageLoad = false;
				} else {
					vueData.listPageLoad = true;
					$.map(vueData.findPostByPage.data.queryResult, function(data) {
						vueData.postArray.push(data);
					});
					setTimeout(function() {
						$("[data-bui_img]").buijs_img();
					}, 100);
				}
			}, 300);
			$(".bui_mo_b").buijs_scrollTobottom(function() {
				if(vueData.listPageLoad == true) {
					pageNo++
					insetPostArray(pageNo);
				}
			});
		});
	};

	//页面滚动监听
	var scrollTop;
	$(".bui_mo_b").bind('scroll', function() {
		if($(this).scrollTop() >= $("#indexBanner").height()) {
			if($(this).scrollTop() < scrollTop) {
				$("#indexHeaderNav").slideDown(300);
			} else {
				$("#indexHeaderNav").slideUp(300);
			}
		} else {
			$("#indexHeaderNav").slideUp(300);
		};
		scrollTop = $(this).scrollTop();
	});

});