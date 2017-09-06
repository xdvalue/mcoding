var Orderform_orderConfirm = function () {

    var initToolBar = function(){
        //保存订单
        $("#saveOrderBut").on("click", function(){
            if($("#J_name").html()=='' || $("#J_phone").html() =='' || $("#J_adres").html() ==''){
            	 if($.trim($("#inputName").val())=='' || $.trim($("#inputPhone").val()) =='' || $.trim($("#inputAdress").val()) ==''){
            		 alert("请您先选择或填写收货地址!");
                     return false;
            	 }else{
            	 	 $('#orderAdress').val($("#inputAdress").val());
	                 $('#name').val($("#inputName").val());
	                 $('#phone').val($("#inputPhone").val());
            	 }
            }
            var param = $("#orderAddForm").serialize();
            $(".floading").fadeIn(10);
        	/*封装商品列表信息*/
        	var itemIds = new Array();
        	var prices = new Array();
        	var nums = new Array();
        	$.each($("input[id='itemIds']"), function(index, item){
        		itemIds.push($(this).val());
        	});
        	$.each($("input[id='prices']"), function(index, item){
        		prices.push($(this).val());
        	});
        	$.each($("input[id='nums']"), function(index, item){
        		nums.push($(this).val());
        	});
        	param = param + "&itemIds="+itemIds + "&prices="+prices + "&nums="+nums;
            $.ajax({
                type: "post",
                data: param,
                url: 'saveOrder.html',
                success: function (data) {
                    if (data.code == 0) {
                    	window.location.href="/front/userOrderPayView.html?orderId="+data.data.outno;
                    } else {
                    	alert(data.msg);
                    }
                    
                }
            });
        });
        
        (function(Z){
	        Z(document).ready(function(){
	            if(navigator.userAgent.indexOf("AlipayClient")!==-1){
	                Ali.showTitle(function(){});
	                Ali.hideToolbar();
	            }
	            function getAbsoluteUrl(url){
	                var a = document.createElement('A');
	                a.href = url; // 设置相对路径给Image, 此时会发送出请求
	                url = a.href; // 此时相对路径已经变成绝对路径
	                return url;
	            }
	
	            Z('a#J_codeExample').click(function(e){
	                var t=this;
	                if(navigator.userAgent.indexOf("AlipayClient")!==-1){
	                    e.preventDefault();
	                    Ali.pushWindow({
	                        url: getAbsoluteUrl(Z(t).attr('href')),
	                        param: {
	                            readTitle: "YES",
	                            showToolBar: "NO"
	                        }
	                    })
	                }
	            });
	            Z('#J_exampleTrigger').click(function(e){
	                e.preventDefault();
	                if(navigator.userAgent.indexOf("AlipayClient")===-1){
	                    alert('请在支付宝钱包内运行');
	                }else{
	                    if((Ali.alipayVersion).slice(0,3)>=8.3){
	                        am.selectAddress(function (data) {
	                            //成功选取收货地址的情况
	                            //data返回对象
	                            //"address": "古荡街道万塘路18号黄龙时代广场B座", 详细地址
	                            //"addressCode": "330106", 地址邮编
	                            //"addressId": "214118119", 地址id
	                            //"area": "西湖区", 区
	                            //"city": "杭州市", 市
	                            //"fullname": "张三", 姓名
	                            //"mobilePhone": "13912345678", 手机号
	                            //"post": "310012",  区域邮编
	                            //"prov": "浙江省" 省份
	                            Z('#J_doing').hide().html('城市获取结果显示在此');
	                            Z('#J_name').html('<p>收&nbsp;&nbsp;货&nbsp;人</p> '+data.fullname).show();
	                            Z('#J_phone').html('<p>手机号码</p> '+data.mobilePhone).show();
	                            Z('#J_adres').html('<p style="font-size: 16px; float:left; height: 50px; margin-right: 20px;">收货地址</p><span style="border: none; padding: 0;">'+data.prov+data.city+data.area+data.address+'</span></p>').show();
                                Z('#orderAdress').val(data.prov+data.city+data.area+data.address);
	                            Z('#name').val(data.fullname);
	                            Z('#phone').val(data.mobilePhone);
	                            Z('#inputAddress').html('');
	                        });
	                    }else{
	                        alert('请在钱包8.3以上版本运行');
	                    }
	                }
	            });
	        });
	    })(Zepto);
    };
    
    return {
        init: function () {
           initToolBar();
        }
    };
}();