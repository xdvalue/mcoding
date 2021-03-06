var workSpace = {
	domain: 'http://' + window.location.host,
	jsonUrl: null,
	jsonUrlDemo: './json/',
	uiPublicPath: null,
};
//生产环境
if(workSpace.domain.indexOf('.com') >= 0) {
	console.log('生产环境');
	//baidiui
	workSpace.uiPublicPath = 'http://www.baidiui.com/resources';
	//Jsonurl
	workSpace.jsonUrl = 'http://www.nensahealth.com/';

}
//测试环境
else {
	console.log('本地测试环境')
	//baidiui
	workSpace.uiPublicPath = 'http://baidiui/resources';
	//Jsonurl
	workSpace.jsonUrl = 'http://scrm.mcoding.cn/';
};

document.write(
	'<meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1, user-scalable=no" />' + //移动端宽高比
	'<meta name="format-detection" content="telephone=no" />' + //禁止ios监听电话号码
	'<link rel="stylesheet" type="text/css" href="' + workSpace.uiPublicPath + '/baidiui_v2.0.4/css/baidiui_scss.css" />' + //buicss
	'<link rel="stylesheet" type="text/css" href="' + workSpace.uiPublicPath + '/plug/font-awesome/4.7.0/css/font-awesome.min.css" />' + //font-awesome
	'<link rel="stylesheet" type="text/css" href="' + workSpace.uiPublicPath + '/plug/bicon/1.0.0/style.min.css" />' + //bicon
	'<link rel="stylesheet" type="text/css" href="' + workSpace.uiPublicPath + '/plug/font-awesome/4.7.0/css/font-awesome.min.css" />' + //bicon
	'<script type="text/javascript" src="' + workSpace.uiPublicPath + '/plug/jquery/3.1.1/jquery.min.js"></script>' + //jquery
	'<script type="text/javascript" src="' + workSpace.uiPublicPath + '/plug/vue/1.0.28/vue.min.js"></script>' + //vue
	'<script type="text/javascript" src="' + workSpace.uiPublicPath + '/plug/moment/2.18.1/moment.min.js"></script>' + //moment
	'<script type="text/javascript" src="' + workSpace.uiPublicPath + '/baidiui_v2.0.4/js/baidiui.js"></script>' + //buijs
	'<link rel="stylesheet" type="text/css" href="./css/style.css" />' + //本案样式
	'<script type="text/javascript" src="./js/public.js"></script>' + //本案公用js
	'<script type="text/javascript" src="http://echarts.baidu.com/dist/echarts.min.js"></script>' + //echart
	'<script type="text/javascript" src="http://echarts.baidu.com/asset/map/js/world.js"></script>' + //echart地图
	'' //本案公用js
);