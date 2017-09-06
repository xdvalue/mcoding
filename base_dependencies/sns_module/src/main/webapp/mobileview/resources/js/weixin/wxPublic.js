//短链接
var _shortLink = "";
var _selfDomain = 'http://' + window.location.host+"/";
var _selfUrl="";
if(_selfDomain.indexOf('mobi') >= 0) {
    //测试环境
    var appid = "wx07c01da2e6bcb01d";
    _selfUrl = _selfDomain+"EMIS/";
}else if(_selfDomain.indexOf('com') >= 0) {
    //生产环境
    var appid = "wxc29d38541362f295";
    _selfUrl = _selfDomain;
}else{
    _selfUrl="http://192.168.2.54:8080/EMIS/";
}
/**
 * options配置项
 * shareLink 分享的链接,默认是location.href
 * isNeedOpenid 分享链接是否openid,默认true (分享链接默认带的是shareOpenId字段)
 * fullPath wetchatShare2的接口所需要的参数 默认为 location.href
 * type 分享的来源 默认为空
 * title 分享的题目
 * desc 分享描述
 * imgUrl 分享图片的链接
 * successFun 分享成功后的回调函数
 * */
//TODO 尚未测试，不一定能用
function setWechatShare(options){
    var defaults = {
        shareLink:location.href,
        isNeedOpenid:true,
        fullPath:location.href
    };
    var settings = $.extend(defaults,options);
    _wechatShare(settings);
}
//获取短链接
function _runShortUrl(shareLink) {
    $.ajax({
        type: "get",
        url: _selfUrl + "/front/makeShortUrl.html",
        async: false,
        cache: false,
        dataType: 'json',
        data: {
            longUrl: shareLink
        },
        error: function(data) {
            run_jsonError();
        },
        success: function(data) {
            _shortLink = data.data;
        }
    });
};

/***
 * 微信分享方法
 *
 * **/
function _wechatShare(options) {
    var timestamp = 0; //时间戳
    var nonceStr = ''; //随机串
    var signature = ''; //签名
    var link = '';
    var openid = '';
    $.ajax({
        type: "post",
        url: _selfUrl + "api/wechatShare2.html",
        async: false,
        dataType: "json",
        data: {
            'fullPath': options.fullPath
        },
        success: function(rs) {
            timestamp = rs.data.timestamp;
            nonceStr = rs.data.nonceStr;
            signature = rs.data.signature;
            link = rs.data.link;
            openid = rs.msg;
            if(options.isNeedOpenid){
                if(options.indexOf("?")!=-1){
                    options.shareLink +="&shareOpenId="+openid;
                }else {
                    options.shareLink +="?shareOpenId="+openid;
                }
            }
            _runShortUrl(options.shareLink);
            _configWx(appid, timestamp, nonceStr, signature, link, openid,options);
        }
    });
};


function configWx(appid, timestamp, nonceStr, signature, link, openid,options) {
    var defaults = {
        title:"",
        desc:"",
        imgUrl:"",
        openid:openid
    };
    var settings = $.extend(defaults,options);
    var type= settings.type ? "||"+settings.type:"";
    //var _link = _jsonUrl + '/activity/we_run/guest.html?toOpenid=' + openid;
    var link = "https://open.weixin.qq.com/connect/oauth2/authorize?" +
        "appid=" +appid+
        "&redirect_uri="+_selfUrl+"api/wechatCheckWxUser2.html?brandCode=MRMJ"+type +
        "&response_type=code&scope=snsapi_userinfo" +
        "&state="+settings.shareLink;
    wx.config({
        debug: false,
        appId: appid,
        timestamp: timestamp,
        nonceStr: nonceStr,
        signature: signature,
        jsApiList: ['onMenuShareTimeline',
            'onMenuShareAppMessage'
        ]
    });

    wx.error(function(res) {
        buijs_alert({
            content: res.errMsg
        });
    });

    wx.ready(function() {
        wx.onMenuShareTimeline({
            title: settings.title, // 分享标题
            desc: settings.desc, // 分享描述
            link: link, // 分享链接
            imgUrl: settings.imgUrl, // 分享图标
            success: function() {
                // 用户确认分享后执行的回调函数
                if(settings.successFun &&  (typeof settings.successFun) == "function" ){
                    settings.successFun(settings);
                }
            },
            cancel: function() {
                // 用户取消分享后执行的回调函数
            }
        });

        wx.onMenuShareAppMessage({
            title: settings.title, // 分享标题
            desc: desc, // 分享描述
            link: link, // 分享链接
            imgUrl: imgUrl, // 分享图标
            type: '', // 分享类型,music、video或link，不填默认为link
            dataUrl: '', // 如果type是music或video，则要提供数据链接，默认为空
            success: function() {
                // 用户确认分享后执行的回调函数
                if(settings.successFun &&  (typeof settings.successFun) == "function" ){
                    settings.successFun(settings);
                }
            },
            cancel: function() {
                // 用户取消分享后执行的回调函数
                buijs_alert({
                    content: '已取消分享！'
                });
            }
        });
    });
};