//配置各环境路径
var _domain = window.location.host;
var urlContent;
var appid;
if (_domain.indexOf('com') >= 0) {
	//正式环境
	urlContent = "http://" + _domain + "/sns_module/";
	//生产环境公众号appid
	appid = "wxc29d38541362f295";
} else if (_domain.indexOf('cn') >= 0) {
	//测试环境_阿里云
	urlContent = "http://" + _domain + "/sns_module/";
	//测试环境公众号appid
	appid = "wx07c01da2e6bcb01d";
} else {
	//测试环境_局域网
	urlContent = "http://v.gymmaxer.com/sns_module/";
};

$(function(){
	config()
})

function config() {
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
			openid = rs.msg;
			wx.config({
				debug: false,
				appId: rs.data.appId,
				timestamp: rs.data.timestamp,
				nonceStr: rs.data.nonceStr,
				signature: rs.data.signature,
				jsApiList: ['chooseImage','uploadImage']
			})
		}
	});
}