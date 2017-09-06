/**Leim对微信分享方法进行封装*/
//配置各环境路径
var _domain = window.location.host;
var urlContent;
var appid;
var jsApiList = [
	'onMenuShareTimeline',
	'onMenuShareAppMessage',
	'previewImage'
	] //

if (_domain.indexOf('com') >= 0) {
	//正式环境
	urlContent = "http://" + _domain + "/sns_module/";
	//生产环境公众号appid
	appid = "wxc29d38541362f295";
} else if (_domain.indexOf('mobi') >= 0) {
	//测试环境_阿里云
	urlContent = "http://" + _domain + "/sns_module/";
	//测试环境公众号appid
	appid = "wx07c01da2e6bcb01d";
} else {
	//测试环境_局域网
	urlContent = "http://mcoding.cn/sns_module/";
	/*F5同步刷新组件*/
	document.write('<script language="javascript" src="http://127.0.0.1:81/_/js/reloader.js"></script>');
};

function addJsAPi(array) {
	array.map(function(item){
		jsApiList.push(item)
	})
}

//title分享标题
//desc分享简述内容
//link点击后的链接地址，""表示当前链接
//getshorturllink为当前链接下是否获取短链接 true是false否
//用于获取短链接的地址，getshorturl为true时需要该参数，为false传空字符串即可
//imageUrl图片地址
function wechatSharePublic(title, desc, link, getshorturl, shorturl, imgUrl) {
	var fullPath = window.location.href;
	var timestamp = 0; //时间戳
	var nonceStr = ''; //随机串
	var signature = ''; //签名
	$.ajax({
		type: "post",
		url: urlContent + "wechatJsSdk/front/getJsConfigParams",
		async: false,
		//global: false,
		dataType: "json",
		data: {
			'url': fullPath
		},
		success: function(rs) {
			console.log(rs);
			timestamp = rs.data.timestamp;
			nonceStr = rs.data.nonceStr;
			signature = rs.data.signature;
			appid = rs.data.appId;
			openid = rs.msg;
			if(link==""){
				link = rs.data.link;
				if(getshorturl){
					runShortUrl(shorturl,openid);
				}
			}
			configWxPublic(appid, timestamp, nonceStr, signature, link, title, desc, getshorturl, imgUrl);
			
		}
	});
}
//获取短链接
function runShortUrl(shorturl, _openid) {
	$.ajax({
		type: "get",
		url: _jsonUrl + "/front/makeShortUrl.html",
		async: false,
		cache: false,
		dataType: 'json',
		data: {
			longUrl: _jsonUrl + shorturl + _openid
		},
		error: function(data) {
			jsonError();
		},
		success: function(data) {
			shortLink = data.data;
		}
	});
};
function configWxPublic(appid, timestamp, nonceStr, signature, link, title, desc, getshorturl, imgUrl) {
	/*var link = "https://open.weixin.qq.com/connect/oauth2/authorize?" +
			"appid=" +appid+
			"&redirect_uri="+urlContent+"/api/wechatCheckWxUser2.html?brandCode=MRMJ" +
			"&response_type=code&scope=snsapi_userinfo" +
			"&state="+urlContent+"/api/wechatTest.html#wechat_redirect";*/
	if(getshorturl){//短链接
		var index = link.indexOf("&state=");
		link = link.substring(0,index)+"&state="+shortLink;
	}
	
	imgUrl = urlContent + imgUrl;
	wx.config({
		debug: false,
		appId: appid,
		timestamp: timestamp,
		nonceStr: nonceStr,
		signature: signature,
		jsApiList: jsApiList
	});

	wx.error(function(res) {
		js_msg({
			'msg': res.errMsg
				//'href':'index.html'
		});
	});

	wx.ready(function() {
		wx.onMenuShareTimeline({
			title: desc, // 分享标题
			desc: title, // 分享描述
			link: link, // 分享链接
			imgUrl: imgUrl, // 分享图标
			success: function() {
				// 用户确认分享后执行的回调函数
			},
			cancel: function() {
				// 用户取消分享后执行的回调函数
			}
		});

		wx.onMenuShareAppMessage({
			title: title, // 分享标题
			desc: desc, // 分享描述
			link: link, // 分享链接
			imgUrl: imgUrl, // 分享图标
			type: '', // 分享类型,music、video或link，不填默认为link
			dataUrl: '', // 如果type是music或video，则要提供数据链接，默认为空
			success: function() {
				// 用户确认分享后执行的回调函数
			},
			cancel: function() {
				// 用户取消分享后执行的回调函数
				js_msg({
					'msg': '已取消分享！'
				});

			}
		});
	});
};

/*使用微信sdk浏览图片*/
function previewImage (urlArray) {
	event.stopPropagation();
	wx.previewImage({
		current: window.location.href,
		urls: urlArray
	});
}