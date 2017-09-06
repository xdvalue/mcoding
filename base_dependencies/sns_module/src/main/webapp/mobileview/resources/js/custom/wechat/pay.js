/**
 * Leiming:支付实例
 */
$(document).ready(function(){
	fee=parseInt(fee)/100;
	$('.fee').html(fee);
	$("#orderNo").html("订单编号:"+orderNo);
});

function callpay() {
	wx.chooseWXPay({
		timestamp : timestamp,
		nonceStr : nonceStr2,
		package : 'prepay_id=' + prepay_id,
		signType : 'MD5', // 签名方式，默认为'SHA1'，使用新版支付需传入'MD5'
		paySign : paySign, // 支付签名
		success : function(res) {
			//根据需求自定义支付成功回调代码
		}
	});
}