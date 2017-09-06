var _domain = 'http://' + window.location.host;
var _baidiui,_jsonUrl,N_jsonUrl;
//移动端宽高比
document.write('<meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1, user-scalable=no" />');
//禁止ios监听电话号码
document.write('<meta name="format-detection" content="telephone=no" />');
//外网
if (_domain.indexOf('com') >= 0) {
	console.log('外网');
	_baidiui = './baidiui_v2.0.1/';
	_jsonUrl = 'http://www.dtvalue.com/';
}
//内网
else {
	console.log('内网');
	_baidiui = './baidiui_v2.0.1/';
	_jsonUrl = _domain+"/cms_module";
//	_jsonUrl ='http://www.coding.mobi:18080/cms_module';
	/*同步刷新*/
	//document.write('<script language="javascript" src="http://127.0.0.1:81/_/js/reloader.js"></script>');
};

//css&js_baidiui
document.write('<link rel="stylesheet" type="text/css" href="' + _baidiui + '/css/baidiui_less.css" />');
document.write('<link rel="stylesheet" type="text/css" href="' + _baidiui + '/css/font-awesome-4.4.0/css/font-awesome.min.css" />');
document.write('<link rel="stylesheet" type="text/css" href="' + _baidiui + '/css/agate.css" />');
document.write('<script type="text/javascript" src="' + _baidiui + '/js/jquery-1.11.2.min.js"></script>');
document.write('<script type="text/javascript" src="' + _baidiui + '/js/jquery.lazyload-1.9.5.min.js"></script>');
document.write('<script type="text/javascript" src="' + _baidiui + '/js/swiper3.08.jquery.min.js"></script>');
//document.write('<script type="text/javascript" src="' + _baidiui + '/js/highlight.pack.js"></script>');
//document.write('<script type="text/javascript" src="' + _baidiui + '/js/baidiui-datetimepicker.js"></script>');
document.write('<script type="text/javascript" src="' + _baidiui + '/js/baidiui.js"></script>');

//css&js_baidiuiDemo
document.write('<script type="text/javascript" src="./public.js"></script>');
document.write('<script type="text/javascript" src="./demodata.js"></script>');
document.write('<link rel="stylesheet" type="text/css" href="./style.css" />');