var _domain = 'http://' + window.location.host;
var _baidiui;
var _jsonUrl;
var _openId;

//移动端宽高比
document.write('<meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1, user-scalable=no" />');
//禁止ios监听电话号码
document.write('<meta name="format-detection" content="telephone=no" />');

//生产环境
//var baseUrl = 'http://newmou.mcoding.cn/';
//var baseUrl = 'http://newmou.mcoding.cn/test';
if (_domain.indexOf('com') >= 0) {
	console.log('生产环境');
	//baidiui
	_baidiui =  'http://v.gymmaxer.com/sns_module/mobileview/resources/baidiui_v2.0.3/';
	//Jsonurl
	_jsonUrl = _domain + '/sns_module/';
}
//阿里云测试环境
else if (_domain.match('hzywx') ||_domain.match('mobi') || _domain.match('mcoding')) {
	console.log('阿里云测试环境');
	//baidiui
	_baidiui = _domain + '/sns_module/mobileview/resources/baidiui_v2.0.3/';
	//Jsonurl
	_jsonUrl = _domain + '/sns_module/';
}
//测试环境
else {
	console.log('本地测试');
	//baidiui
	_baidiui = '../resources/baidiui_v2.0.3/';
	//Jsonurl
	//		_jsonUrl = 'http://192.168.2.117:8080/EMIS/';
	// _jsonUrl = 'http://v.gymmaxer.com/sns_module/';
	_jsonUrl = 'http://mcoding.cn/sns_module/'
	//openid
	_openId = 'o_3tTs9_DslorAvinN19RIIDZao9';
	/*同步刷新*/
	document.write('<script language="javascript" src="http://127.0.0.1:81/_/js/reloader.js"></script>');
};
//css&js_baidiui
document.write('<link rel="stylesheet" type="text/css" href="' + _baidiui + '/css/baidiui_less.css" />');
document.write('<link rel="stylesheet" type="text/css" href="' + _baidiui + '/css/font-awesome-4.4.0/css/font-awesome.min.css" />');
document.write('<script type="text/javascript" src="' + _baidiui + '/js/jquery-1.11.2.min.js"></script>');
document.write('<script type="text/javascript" src="' + _baidiui + '/js/vue.min.js"></script>');
document.write('<script type="text/javascript" src="' + _baidiui + '/js/baidiui.js"></script>');

//引入微信JSJDK
//引入微信JSJDK
document.write('<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>');
//本案
document.write('<link rel="stylesheet" type="text/css" href="./style.css" />');
document.write('<script type="text/javascript" src="./public.js"></script>');