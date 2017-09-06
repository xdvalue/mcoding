/**
 * Leiming:微信支付公共代码
 * orderNo：订单号
 * fee：支付金额
 * orderType：订单类型
 * gift：
 * 以上参数拼接在后台controller中传入该js
 * controller:/api/wechatYespay2.html
 */
var orderNo;
var fee;
var orderType;
var gift;
//配置各环境路径
var _domain = window.location.host;
var urlContent;
var appid;
if (_domain.indexOf('com') >= 0) {
	//正式环境
	urlContent = "http://" + _domain;
	//生产环境公众号appid
	appid = "wxc29d38541362f295";
} else if (_domain.indexOf('mobi') >= 0) {
	//测试环境_阿里云
	urlContent = "http://" + _domain + "/EMIS";
	//测试环境公众号appid
	appid = "wx07c01da2e6bcb01d";
} else {
	//测试环境_局域网
	urlContent = "http://www.coding.mobi/EMIS/";
	/*F5同步刷新组件*/
	document.write('<script language="javascript" src="http://127.0.0.1:81/_/js/reloader.js"></script>');
};
var timestamp;
var nonceStr2;
var prepay_id;
var paySign;
var nonceStr;
var appId;
var signature;
var payProductName;
$(document).ready(function() {

	orderNo = getUrlString('orderNo');
	fee = getUrlString('fee');
	orderType = getUrlString('orderType');
	gift = getUrlString('gift');
	payProductName = getUrlString('payProductName');
	$.ajax({
		type: "post",
		url: urlContent + "/api/wechatYespay3.html?orderNo=" + orderNo + "&fee=" + fee + "&gift=" + gift + "&orderType=" + orderType + "&payProductName=" + payProductName,
		async: false,
		//global: false,
		dataType: "json",
		/*data: {
			'orderNo': orderNo
		},*/
		success: function(rs) {
			if (rs.data) {
				timestamp = rs.data.timestamp;
				nonceStr2 = rs.data.nonceStr2;
				prepay_id = rs.data.prepay_id;
				paySign = rs.data.paySign;
				nonceStr = rs.data.nonceStr;
				appId = rs.data.appId;
				signature = rs.data.signature;
				wx.config({
					debug: false,
					appId: appId,
					timestamp: timestamp,
					nonceStr: nonceStr,
					signature: signature,
					jsApiList: ['chooseWXPay',
						'onMenuShareAppMessage'
					]
				});
			}
		}
	});


});

function getUrlString(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); // 构造一个含有目标参数的正则表达式对象
	var r = window.location.search.substr(1).match(reg); // 匹配目标参数
	if (r != null) {
		return unescape(r[2]);
	}
	return ''; // 返回参数值
}