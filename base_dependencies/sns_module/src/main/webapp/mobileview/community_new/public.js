$(document).ready(function() {
	global_vue(); //全局调用vue框架
	vm.$set('jsonUrl', _jsonUrl); //jsonUrl
	vm.$set('global_nowTime', (new Date()).getTime()); //获取当前时间
	getMemberInfo();
	global_getHash();
});
//监听hash
$(window).bind('hashchange', function() {
	global_getHash();
});

var hg_memberInfo; //有需要的话装载当前会员信息

//全局调用vue框架
var vm,
	vueData = {},
	vueReady = function() {},
	vueMethods = {
		//计算距离当前时间
		dateDiff: function(startDate, endDate) {
			var iDays = parseInt(Math.abs(startDate - endDate) / 1000); //把相差的毫秒数转换为分
			if(iDays < 60) { //少于60秒
				iDays = '1分钟前';
			} else {
				iDays = iDays / 60;
				if(iDays < 60) { //少于 60分钟
					iDays = parseInt(iDays) + '分钟前';
				} else {
					iDays = iDays / 60;
					if(iDays < 24) {
						iDays = parseInt(iDays) + '小时前';
					} else {
						iDays = iDays / 24;
						iDays = parseInt(iDays) + '天前'
					}
				}
			}
			return iDays;
		},
		//清理html标签
		clearHtml: function(html, string) {
			return html.replace(/<[^>]+>/g, "").substring(0, string);
		}
	},
	vueAttached;
//全局参数
var global_vue = function() {
	vm = new Vue({
		el: 'html',
		data: vueData,
		destroyed: vueAttached,
		methods: vueMethods
	});
};
//公用获取数据插入vue
function global_getJsonSetVue(url, vueDataName, postData, callback) {
	$.ajax({
		type: "get",
		url: _jsonUrl + url,
		async: true,
		data: postData ? postData : null,
		dataType: 'json',
		beforeSend: function() {},
		complete: function() {},
		error: function(data) {},
		success: function(data) {
			vm.$set(vueDataName, data);
			callback ? callback() : null;
		}
	});
};
//全局获取hash
function global_getHash(callback) {
	vm.$set('hash', {});
	var urlHash = window.location.hash;
	vueData.hash.action = urlHash.indexOf('#action=') != -1 ? urlHash.split('#action=')[1].split('/')[0] : null; //action
	vueData.hash.id = urlHash.indexOf('#id=') != -1 ? urlHash.split('#id=')[1].split('/')[0] : null; //id
	callback ? callback() : '';
	//展开帖子详情
	if(vueData.hash.action == 'modalArticle') {
		buijs_modal({
			setid: 'modalArticle',
			positions: 'right',
			width: '100%',
			boxClass: 'bui_bgc_white_d12',
			header: '<div class="bui_media bui_vm bui_bgc_white">' +
				'<div class="bui_media_l"><button onclick="global_clearHash()" class="bui_btn bui_btn_sq bui_m_8 bui_bds_0 bui_ts_24_i"><i class="fa fa-angle-left"></i></button></div>' +
				'<div class="bui_media_b"><p class="bui_ta_c">帖子详情</p></div>' +
				'<div class="bui_media_r"><button class="bui_btn bui_btn_sq bui_m_8 bui_bds_0 bui_ts_24_i"><i class="fa fa-share-alt"></i></button></div>' +
				'</div>',

			ajaxload: 'ajax_article.html',
			footer: '<div class="bui_p_8 bui_bgc_white">' +
				'<div class="bui_media bui_vm">' +
				'<div class="bui_media_b"><input type="text" value="" placeholder="说两句吧" class="bui_ipt_48 bui_block bui_ts_16" /></div>' +
				'<div class="bui_media_r"><button class="bui_btn bui_btn_48 bui_ts_16 bui_bgc_red">发表</button></div>' +
				'</div>' +
				'</div>'
		})
	} else {
		buijs_modal_close();
	}
};

//全局清空hash
function global_clearHash() {
	window.location.hash = ''
}

/*全局报错提示刷新页面*/
function global_errorReloadTips(text) {
	buijs_modal({
		width: '85%',
		positions: 'center',
		content: '<div class="bui_p_24 bui_ta_c">' + text + '</div>',
		footer: '<hr/>' +
			'<div class="bui_p_8">' +
			'<button class="bui_btn_48 bui_bgc_red bui_block bui_ts_14" onclick="window.location.reload();">点击重试</button>' +
			'</div>'
	})
};

/*获取个人信息*/
function getMemberInfo() {
	$.ajax({
		type: 'get',
		url: _jsonUrl + '/member/front/findCurrentMember',
		dataType: 'json',
		async: false,
		beforeSend: function() {
			vm.$set('memberInfo', null);
		},
		complete: function() {
			setTimeout(getMemberInfo, 900000);
		},
		success: function(data) {
			if(!data.data) {
				global_errorReloadTips(data.msg || '获取用户信息异常');
				return
			}
			hg_memberInfo = data.data;
			vm.$set('memberInfo', data.data);
		},
		error: function(err) {
			global_errorReloadTips(err.msg || '获取用户信息异常');
		}
	})
}

/*打开图片预览  by Gzr*/
function showIMGFUN(urlArray, index) {
	event.stopPropagation();
	$("body").append('<div id="imgShow_Wrap" style="height: 100%;width: 100%;position: fixed;top:0px;" class="bui_bgc_black">' +
		'<div id="indexBanner" style="height: 100%;"></div>' +
		'</div>');
	var _list = "";
	$.map(urlArray, function(item) {
		_list += '<a class="item" data-bui_img=\'{"center":"imgShow"}\'><img style="width: 100%;height: auto" src="' + item + '" /></a>';
	});
	$("#imgShow_Wrap").find("#indexBanner").html('<div class="box" style="overflow: scroll">' + _list + '</div>');
	$("#imgShow_Wrap").find("#indexBanner").bui_swiperShowImg({
		nav: false,
		no: index,
		index: true,
		loop: false
	});
	$("html").on("click", function() {
		$("#imgShow_Wrap").remove();
	})
}

/*处理解码后的url参数    by xiaohui*/
function GetQueryString(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if(r != null) {
		var g = r[2];
		g = decodeURIComponent(g);
		return unescape(g);
	} else {
		return null;
	}
};
/*全局展开我的编辑模块  by Gzr*/
function hg_showMyInfo() {
	buijs_side_show({
		setid: 'hg_memberDetailPanel',
		bgc: 'white_d12',
		barbgc: 'white_d12',
		title: '我的信息',
		load: 'ajax_memberdetail_panel.html',
		footer: '<hr/>' +
			'<div class="bui_p_8 bui_bgc_white">' +
			'<a href="javascript:updataMemberDetail();" class="bui_btn_48 bui_block bui_radius" hg_style="btnTrue">保存个人信息</a>' +
			'</div>',
		bgmove: false
	});
	//全局样式
	$("#hg_memberDetailPanel .bui_mo_h").attr('hg_style', 'header tc');
	$("#hg_memberDetailPanel .bui_mo_f").attr('hg_style', 'footer');
}

/*全局展开类别模块编辑  by  Gzr*/
function hg_showModulePanel() {
	buijs_side_show({
		setid: 'hg_memberDetailPanel',
		bgc: 'white_d12',
		barbgc: 'white',
		margin: 'ml_48',
		title: '模块',
		load: 'ajax_module.html',
		bgmove: false
	});
};
/*全局展开类别选择编辑  by xiaohui*/
function hg_showClassfiyModule() {
	buijs_side_show({
		setid: 'hg_classfiyModule',
		bgc: 'white_d12',
		barbgc: 'white',
		margin: 'ml_48',
		title: '选择分类',
		load: 'ajax_classfiy_module.html',
		bgmove: false
	});
};

/*检测到底滚动事件*/
$.fn.checkScrollB = function(callback, id) {
	if(id) {
		var _box = $(this).eq(1);
		_box.wrapInner('<div id="' + id + '"></div>');
		var _InnerBox = $("#" + id + "");
	} else {
		var _box = $(this);
		$(this).wrapInner('<div id="buijs_InnerBox"></div>');
		var _InnerBox = $("#buijs_InnerBox");
	}

	_box.scroll(function() {
		scroll();
	});

	function scroll() {
		var _boxH = _box.height();
		var _boxS = _box.scrollTop();
		var _boxC = _InnerBox.height();
		if(_boxS >= (_boxC - _boxH)) {
			callback();
		};
	};
};

/*计算两个日期的相差天数  by Gzr*/
$.dateDiff = function(startDate, endDate) {
	var iDays = parseInt(Math.abs(startDate - endDate) / 1000); //把相差的毫秒数转换为分
	if(iDays < 60) { //少于60秒
		iDays = '1分钟前';
	} else {
		iDays = iDays / 60;
		if(iDays < 60) { //少于 60分钟
			iDays = parseInt(iDays) + '分钟前';
		} else {
			iDays = iDays / 60;
			if(iDays < 24) {
				iDays = parseInt(iDays) + '小时前';
			} else {
				iDays = iDays / 24;
				iDays = parseInt(iDays) + '天前'
			}
		}
	}
	return iDays;
}

/*时间戳转换   by  Gzr*/
function changeDateFormat(dateTime) {
	dateTime = Number(dateTime);
	var date = new Date(dateTime);
	//获取年
	var year = date.getFullYear();
	//获取月
	var month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
	//获取日
	var currentDate = date.getDate() < 10 ? "0" + date.getDate() : date
		.getDate();
	//获取时
	var hours = date.getHours() < 10 ? "0" + date.getHours() : date
		.getHours();
	//获取分
	var minute = date.getMinutes() < 10 ? "0" + date.getMinutes() : date
		.getMinutes();
	//获取秒
	var seconds = date.getSeconds() < 10 ? "0" + date.getSeconds() : date
		.getSeconds();

	var sTime = year + "-" + month + "-" + currentDate + " " + hours + ":" + minute;
	return sTime;
}

/*时间戳转换   by  Gzr*/
function changeDateFormat2(dateTime) {
	dateTime = Number(dateTime);
	var date = new Date(dateTime);
	//获取年
	var year = date.getFullYear();
	//获取月
	var month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
	//获取日
	var currentDate = date.getDate() < 10 ? "0" + date.getDate() : date
		.getDate();
	//获取时
	var hours = date.getHours() < 10 ? "0" + date.getHours() : date
		.getHours();
	//获取分
	var minute = date.getMinutes() < 10 ? "0" + date.getMinutes() : date
		.getMinutes();
	//获取秒
	var seconds = date.getSeconds() < 10 ? "0" + date.getSeconds() : date
		.getSeconds();

	var sTime = year + "-" + month + "-" + currentDate;
	return sTime;
}

/*附件上传*/
function ajaxFileUpload(s) {
	s = $.extend({}, $.ajaxSettings, s);
	var id = s.fileElementId;
	var form = $.createUploadForm(id, s.fileElementId);
	var io = $.createUploadIframe(id, s.secureuri);
	var frameId = 'jUploadFrame' + id;
	var formId = 'jUploadForm' + id;
	// Watch for a new set of requests
	if(s.global && !$.active++) {
		$.event.trigger("ajaxStart");
	}
	var requestDone = false;
	// Create the request object
	var xml = {}
	if(s.global)
		$.event.trigger("ajaxSend", [xml, s]);
	// Wait for a response to come back
	var uploadCallback = function(isTimeout) {
			var io = document.getElementById(frameId);
			try {
				if(io.contentWindow) {
					xml.responseText = io.contentWindow.document.body ? io.contentWindow.document.body.innerHTML : null;
					xml.responseXML = io.contentWindow.document.XMLDocument ? io.contentWindow.document.XMLDocument : io.contentWindow.document;

				} else if(io.contentDocument) {
					xml.responseText = io.contentDocument.document.body ? io.contentDocument.document.body.innerHTML : null;
					xml.responseXML = io.contentDocument.document.XMLDocument ? io.contentDocument.document.XMLDocument : io.contentDocument.document;
				}
			} catch(e) {
				// $.handleError(s, xml, null, e);
			}
			if(xml || isTimeout == "timeout") {
				requestDone = true;
				var status;
				try {
					status = isTimeout != "timeout" ? "success" : "error";
					// Make sure that the request was successful or notmodified
					if(status != "error") {
						// process the data (runs the xml through httpData regardless of callback)
						var data = $.uploadHttpData(xml, s.dataType);
						// If a local callback was specified, fire it and pass it the data
						if(s.success)
							s.success(data, status);

						// Fire the global callback
						if(s.global)
							$.event.trigger("ajaxSuccess", [xml, s]);
					} else {
						// $.handleError(s, xml, status);
					}
				} catch(e) {
					status = "error";
					// $.handleError(s, xml, status, e);
				}

				// The request was completed
				if(s.global)
					$.event.trigger("ajaxComplete", [xml, s]);

				// Handle the global AJAX counter
				if(s.global && !--$.active)
					$.event.trigger("ajaxStop");

				// Process result
				if(s.complete)
					s.complete(xml, status);

				$(io).unbind()

				setTimeout(function() {
					try {
						$(io).remove();
						$(form).remove();

					} catch(e) {
						// $.handleError(s, xml, null, e);
					}

				}, 100)

				xml = null

			}
		}
		// Timeout checker
	if(s.timeout > 0) {
		setTimeout(function() {
			// Check to see if the request is still happening
			if(!requestDone) uploadCallback("timeout");
		}, s.timeout);
	}
	try {
		// var io = $('#' + frameId);
		var form = $('#' + formId);
		$(form).attr('action', s.url);
		$(form).attr('method', 'POST');
		$(form).attr('target', frameId);
		if(form.encoding) {
			form.encoding = 'multipart/form-data';
		} else {
			form.enctype = 'multipart/form-data';
		}
		$(form).submit();

	} catch(e) {
		// $.handleError(s, xml, null, e);
	}
	if(window.attachEvent) {
		document.getElementById(frameId).attachEvent('onload', uploadCallback);
	} else {
		document.getElementById(frameId).addEventListener('load', uploadCallback, false);
	}
	return {
		abort: function() {}
	};

}