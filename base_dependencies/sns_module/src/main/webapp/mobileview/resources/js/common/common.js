function CommonUtils() {
}
/**
 * 打开窗体
 * 
 * @param url
 * @param winName
 * @param width
 * @param height
 */
CommonUtils.openWindow = function(url, winName, width, height) {
	var openWindow;
	var top = (window.screen.height - height) / 2;
	var left = (window.screen.width - width) / 2;
	openWindow = window
			.open(url,
					winName,
					'height='
							+ height
							+ ', width='
							+ width
							+ ', top='
							+ top
							+ ', left='
							+ left
							+ ', toolbar=no, menubar=no, scrollbars=yes, resizable=no,location=n o, status=no');
//	if(openWindow){
//		openWindow.focus();// 让窗口获取焦点
//		openWindows.push(openWindow);
//	}
	return openWindow;
}
/**
 * 初始化校验器
 * 
 * @param cfg
 *            {form : 'InofForm'} //optional
 * @return
 * @example <code>
 //初始化（也可以传递一个具体的Form ID作为参数）
 var validator = null;
 $(function() {
 validator = initValidator();
 }); 

 //form提交时
 if(!validator.form())return;
 </code>
 */
CommonUtils.initValidator = function(cfg) {
	$.metadata.setType("attr", "validate");
	var form = (cfg && cfg.form) ? "#" + cfg.form : "form";
	var validator = $(form)
			.validate(
					{
						onfocusout : function(element) {
							if (!this.checkable(element)
									&& (element.name in this.submitted || !this
											.optional(element))) {
								if (this.element(element)) {
									$(element).removeClass(
											"x-form-text-invalid");
								} else {
									$(element).addClass("x-form-text-invalid");
								}
							}
						},
						onkeyup : function(element) {
							if (element.name in this.submitted
									|| element == this.lastElement) {
								if (this.element(element)) {
									$(element).removeClass(
											"x-form-text-invalid");
								} else {
									$(element).addClass("x-form-text-invalid");
								}
							}
						},
						errorPlacement : function(lable, element) {
							element.addClass("x-form-text-invalid");
							lable
									.css({
										"color" : "red",
										"padding-left" : "18px",
										"background" : 'url("../../images/unchecked.gif") no-repeat 0px 0px'
									});
							if (element.parent().get(0).tagName == "TD") {
								element.parent().append("<div></div>");
								lable.appendTo(element.parent());
							} else {
								lable.appendTo(element.parent().parent());
							}
						},
						submitHandler : function() {
							$("input", $(form)).removeClass(
									"x-form-text-invalid");
							$("textarea", $(form)).removeClass(
									"x-form-text-invalid");
						}
					});
	//$(form).ligerForm();
	return validator;
}
/**
 * 基于jquery组件同步取数据，要使用该方法，必须导入 TC.js
 * 
 * @param url
 * @return
 */
CommonUtils.getData = function(url) {
	var tmp;
	$.json(url, function(json) {
		tmp = json;
	}, true);
	return tmp;
}
CommonUtils.close = function() {
	parent.opener.openWindow.close();
}
/**
 * 根据浏览器的不同选择alert方式
 */
CommonUtils.alert = function(data) {
	var alertType;
	if ($.browser.msie || $.browser.compatible) {// IE
		alertType = alert(data);
	} else if ($.browser.opera) {// opera
		alertType = $.ligerDialog.alert(data);
	} else if ($.browser.gecko) {// mozilla
		alertType = $.ligerDialog.alert(data);
	} else {// others
		alertType = alert(data);
	}
	return alertType;
}




/**
 * 获取随机数
 */
r = function() {
	return Math.floor(Math.random() * 99999999 + 1);// 0-23
}

$(function() {
	$(".l-cuz-btn_").mouseover(function() {
		$(this).addClass("l-cuz-btn-over_");
	}).mouseout(function() {
		$(this).removeClass("l-cuz-btn-over_");
	});
});

/**
 * 获取3位随机数
 */
r2 = function(){
	var no = Math.floor(Math.random() * 999 + 1);// 100-999
	if(r2>=1000 || r2<=100){
		return r2();
	}
	return no+"";
}

getEnterNo = function(){
	var no = r2();
	var date = new Date();
	return date.getTime() + "" + no;
}

function openTab(name, zTree) {
	var nodes = zTree.getNodes();
	for ( var i = 0; i < nodes.length; i++) {
		var node = nodes[i];
		checkNodeEq(zTree, node, name);
	}
}

function checkNodeEq(zTree, node, name) {
	if (node.name == name) {
		// alert("#"+node.tId+"_span");
		// $("#"+node.tId+"_span").click();
		zTree.selectNode(node);
		parent.menuClick(window.event, zTree.setting.treeId, node);
		return true;
	}
	if (node.children && node.children.length >= 1) {
		for ( var i = 0; i < node.children.length; i++) {
			var node1 = node.children[i];
			if (checkNodeEq(zTree, node1, name))
				break;
		}
	}
	return false;
}

function request(paras) {
	var url = location.href;
	var paraString = url.substring(url.indexOf("?") + 1, url.length).split("&");
	var paraObj = {}
	for (i = 0; j = paraString[i]; i++) {
		paraObj[j.substring(0, j.indexOf("=")).toLowerCase()] = j.substring(j
				.indexOf("=") + 1, j.length);
	}
	var returnValue = paraObj[paras.toLowerCase()];
	if (typeof (returnValue) == "undefined") {
		return "";
	} else {
		return returnValue;
	}
}

function getRdValu(name){
	return $("input[\'name=\""+name+"\'\"][checked]").val();
}
function isHnairEmail(str){
	var reg =/^([a-zA-Z0-9_-])+@hnair.com+/;
	return reg.test(str);
}


//订单状态转换
var convertProcessStatus = function(processStatus){
    var html = '';
    if(processStatus == "-1"){
        html = "<label class='label label-info'>已取消</label>";
    }else if(processStatus == "0"){
        html = "<label class='label label-info'>未支付</label>";
    }else if(processStatus == "1"){
        html = "<label class='label label-success'>已支付</label>";
    }else if(processStatus == "2"){
        html = "<label class='label label-success'>已完成</label>";
    }else if(processStatus == "3"){
        html = "<label class='label label-success'>已退款</label>";
    }else if(processStatus == "4"){
        html = "<label class='label label-success'>取消订单</label>";
    }else{
        html = "<label class='label label-primary'>暂无状态</label>";
    }
    return html;
};


//付款状态转换
var convertPayStatus = function(payStatus){
    var html = '';
    if(payStatus == "0"){
        html = "<label class='label label-info'>未付款</label>";
    }else if(payStatus == "1"){
        html = "<label class='label label-success'>已付款</label>";
    }else if(payStatus == "2"){
        html = "<label class='label label-danger'>已退款</label>";
    }else{
        html = "<label class='label label-primary'>未支付，已自动取消</label>";
    }
    return html;
};

//付款类型转换
var convertPayType = function(payType){
    var html = '';
    if(payType == "微信支付"){
        html = "<label class='label label-danger'>微信支付</label>";
    }else if(payType == "支付宝"){
        html = "<label class='label label-danger'>支付宝</label>";
    }else{
        html = "<label class='label label-primary'>暂无支付方式</label>";
    }
    return html;
};

//订单来源
var convertOrderSource = function(orderSource){
	var html = '';
	if(orderSource == "微信"){
		html = "<label>微信公众平台</label>";
	}else if(orderSource == "支付宝"){
		html = "<label>支付宝钱包服务窗</label>";
	}else{
		html = "<label>无</label>";
	}
	return html;
};

//订单品牌
var convertOrderBrand = function(orderBrand){
	var html = '';
	if(orderBrand == "MRMJ"){
		html = "<label>每日每加</label>";
	}else if(orderBrand == "JLD"){
		html = "<label>健乐多</label>";
	}else{
		html = "<label>无</label>";
	}
	return html;
};

//商城类型
var convertMalltype = function(malltype){
	var html = '';
	if(malltype == "wMall"){
		html = "每日每加微商城";
	}else if(malltype == "giftMall"){
		html = "每日每加积分商城";
	}else if(malltype == "gMall"){
		html = "健乐多微商城";
	}else if(malltype == "giftMall_gmx"){
		html = "健乐多积分商城";
	}else{
		html = "无";
	}
	return html;
};
//订单类型
var convertOrdertype = function(ordertype){
	var html = '';
	if(ordertype == "giftgameorder"){
		html = "翻牌游戏";
	}else if(ordertype == "stakegameorder"){
		html = "押宝游戏";
	}
	else{
		html = "";
	}
	return html;
};

//优惠类型
var convertOrderType = function(orderType){
	var html = '';
	if(orderType == "exchange_ticket"){
		html = "<label>兑换礼品券</label>";
	}else{
		html = "<label>无</label>";
	}
	return html;
};
