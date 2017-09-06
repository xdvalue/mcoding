//防伪二维码的验证
var scanApp = {
	securityCode: '',  // 防伪码
	SECURITY_CODE_LENGTH: 16,  // 防伪码长度
	BAR_CODE_LENGTH: 13,  // 产品码长度
	MOBILE_NUM_LENGTH: 11, // 手机号码长度
	BAR_CODE_START_CHAR: '69',  // 产品码开头数字
	isNeedInputBarCode: false, // 默认是 false
	isButtonEnable: false,
	init: function(){
		//console.log('init securityCode=='+scanApp.securityCode);
		tcbj.mobile.init('id_login_page');  
		scanApp.securityCode = tcbj.getQueryString('securityCode');
		tcbj.hideLoader();

		if(scanApp.securityCode != null && scanApp.securityCode !== ''){
			$('#id_securityCode_input').val(scanApp.securityCode);
			scanApp.isButtonEnable = true;
			$('#id_submit_btn').removeClass('cls_disable');
		}
		
		if (scanApp.isNeedInputBarCode) {
			$('#id_barCode_block').removeClass('cls_hidden'); // 显示产品码输入选项														
		};
		scanApp.registerButtonListener();
		scanApp.checkSecurityCode();
	}, // End init
	
	//校验防伪码
	checkSecurityCode: function(){
		scanApp.isButtonEnable = false;
		$('#id_submit_btn').addClass('cls_disable');
		$("#cls_tips").html("正在检验防伪码真伪...");
		//校验防伪码真伪
		$.ajax({
            type: "post",
            data : {"SecurityQrcode":$('#id_securityCode_input').val()},
            url: "checkQrcode.html",
            success: function (data) {
               if(data.msg=="00"){
            	   scanApp.isButtonEnable = true;
       			   $('#id_submit_btn').removeClass('cls_disable');
                   $("#cls_tips").html("您所查询的是正品");
                   if(data.data.trim()==""){
                	   //开启产品码
                	   $('#id_barCode_block').removeClass('cls_hidden');
          			   scanApp.isNeedInputBarCode = true;
                   }else{
                	   //隐藏产品码
                	   $('#id_barCode_block').addClass('cls_hidden');
          			   scanApp.isNeedInputBarCode = false;
                	   $("#barCode").val(data.data);
                   }
                }else if(data.msg=="07"){
                	scanApp.isButtonEnable = false;
        			$('#id_submit_btn').addClass('cls_disable');
                	$("#cls_tips").html("您所查询的防伪码有误，谨防假冒");
                    return;
                }else if(data.msg=="06"){
                	scanApp.isButtonEnable = false;
        			$('#id_submit_btn').addClass('cls_disable');
                	$("#cls_tips").html("防伪码查询失败，请重新扫码或刷新页面");
                    return;
                }else{
                	scanApp.isButtonEnable = false;
        			$('#id_submit_btn').addClass('cls_disable');
                	$("#cls_tips").html(data.msg);//$("#cls_tips").html("您所查询的防伪码有误，谨防假冒");
                   return;
                }
            }, error : function(err) {
                 $("#cls_tips").html("查询错误，请重新扫码或输入防伪码");
     			 return;
    		   }               
     	}); 
	}, // End checkSecurityCode
	
	registerButtonListener: function(){
		//console.log('registerButtonListener');

		$("#id_securityCode_input").on('input',function(e){  
			
			//产品码显示与校验的开关
			/*$('#id_barCode_block').removeClass('cls_hidden');
			scanApp.isNeedInputBarCode = true;*/
			
			/*scanApp.isButtonEnable = true;
			$('#id_submit_btn').removeClass('cls_disable');*/
			
			//校验二维码真伪
			scanApp.checkSecurityCode();
			
		}); 
	}, // End registerButtonListener
	submitForm: function(){
		console.log('submitForm isButtonEnable=='+scanApp.isButtonEnable);

		if(!scanApp.isButtonEnable){
			return;
		}
		
		var tempSecurityCode =  $('#id_securityCode_input').val(),
			tempMobileNum = $('#id_mobileNum_input').val();

		//console.log('securityCode=='+tempSecurityCode);
		//console.log('mobileNum=='+tempMobileNum);

		// 检测是否为空值
		if(tempSecurityCode === ''){
			tcbj.alert('防伪码不能为空');
			return;
		}	
		if(tempMobileNum === ''){
			tcbj.alert('手机号码不能为空');
			return;
		}

		// 检测是否是合法的长度
		if(tempSecurityCode.length !== scanApp.SECURITY_CODE_LENGTH){
			tcbj.alert('请输入'+scanApp.SECURITY_CODE_LENGTH+'位的防伪码');
			return;
		}	
		if(tempMobileNum.length !== scanApp.MOBILE_NUM_LENGTH){
			tcbj.alert('请输入'+scanApp.MOBILE_NUM_LENGTH+'位的手机号码');
			return;
		}


		// 检测是否全是数字
		if (!scanApp.checkNum(tempSecurityCode)) {
			tcbj.alert('亲，防伪码是'+scanApp.SECURITY_CODE_LENGTH+'位的数字');
			return;
		};	

		if (!scanApp.checkNum(tempMobileNum)) {
			tcbj.alert('亲，手机号码是'+scanApp.MOBILE_NUM_LENGTH+'位的数字');
			return;
		};
		
		//console.log('checkBarCode =='+scanApp.checkBarCode());
		if (scanApp.isNeedInputBarCode && !scanApp.checkBarCode()) {
			return;																
		};
		
		/*var param = "securityCode="+tempSecurityCode+"&id_barCode_input="+$('#id_barCode_input').val()
					+"&mobilePhone="+tempMobileNum;*/
		var param = $("#id_securityCode_form").serialize();
		tcbj.showLoader();
		//校验防伪码真伪与积分
		$.ajax({
            type: "post",
            data: param,
            url: "securityCodePoint.html",
            success: function (data) {
            	//var url = "http://localhost:8080/EMIS/";
            	if (data.status == 0) {//积分成功
            		window.location.href="result.html?msg="+data.status+"&mobilePhone="+data.data.mobilePhone
            		+"&pointSum="+data.data.pointSum+"&ext1="+data.data.ext1+"&brandCode="+data.data.brandCode
            		+"&productName="+data.data.ext2+"&fullName="+data.data.fullName+"&ext2="+data.data.ext2;
                } else if(data.status == 1){//防伪码为正品，查询不到对应的产品
                	window.location.href="errorPage.html?msg="+data.status+"&brandCode=MRMJ";
                	return;
                }else if(data.status == 2){//产品条形码错误
                	tcbj.alert(data.msg);
                }else if(data.status == 3){//赠品不参与积分
                	window.location.href="result.html?msg="+data.status+"&mobilePhone="+data.data.mobilePhone
                	+"&brandCode="+data.data.brandCode+"&pointSum="+data.data.pointSum;
                	return;
                }else if(data.status == 4){//非该品牌所有产品
                	var brandCode = $("#brandCode").val();
                	if(brandCode =="MRMJ"){
                		window.location.href="brandTipView.html?brandCode=JLD";
                	}else{
                		window.location.href="brandTipView.html?brandCode=MRMJ";
                	}
                	
                	return;
                }else if(data.status == 5){//该防伪码已积过分，不能重复积分
                	window.location.href="result.html?msg="+data.status+"&brandCode="+data.data.brandCode
                	+"&fullName="+data.data.fullName+"&pointSum="+data.data.pointSum+"&mobilePhone="+data.data.mobilePhone
                	+"&ext2="+data.data.ext2;
                	return;
                }else{
                	window.location.href="errorPage.html?msg="+data.status+"&brandCode=MRMJ";
                	return;
                }
            	tcbj.hideLoader();
            }, error : function(err) {
            	window.location.href="errorPage.html?msg=error";
    		}                
     	});
		
		//$('#id_securityCode_form').submit();  // 提交表单

	}, // End submitForm
	
	checkBarCode: function(){
		//console.log('\ncheckBarCode');
		
		var isValid = true,
			tempBarCode = $('#id_barCode_input').val();
		
		//console.log('barCode=='+tempBarCode);
			
		if(tempBarCode === ''){
			tcbj.alert('产品码不能为空');
			return false;
		}	

		if(tempBarCode.length !== scanApp.BAR_CODE_LENGTH){
			tcbj.alert('请输入以'+scanApp.BAR_CODE_START_CHAR+'开头的'+scanApp.BAR_CODE_LENGTH+'位的产品码');
			return false;
		}

		if (!scanApp.checkNum(tempBarCode)) {
			tcbj.alert('亲，产品码是以'+scanApp.BAR_CODE_START_CHAR+'开头的'+scanApp.BAR_CODE_LENGTH+'位的数字');
			return false;
		};

		console.log('tempBarCode.substr(0,2)=='+tempBarCode.substr(0,2));
		if (scanApp.BAR_CODE_START_CHAR !== tempBarCode.substr(0,2)) {
			tcbj.alert('亲，产品码是以'+scanApp.BAR_CODE_START_CHAR+'开头，在瓶身上的，不是在瓶盖哦!');
			return false;
		};

		return isValid;
	},  // End checkNum
	
	checkNum: function(str){
		//console.log('checkNum str=='+str);
		var tempArray = [],
			i = 0,
			len = 0,
			tempValue = 0,
			isValid = true;

		tempArray = str.split('');
		//console.log('tempArray=='+tempArray);

		for(i=0,len=tempArray.length; i<len;i++){
			tempValue = parseInt(tempArray[i], 10);
			if (isNaN(tempValue)) {
				isValid = false;
				break;
			};
		}

		//console.log('checkNum isValid=='+isValid);
		return isValid;
	} // End checkNum
};  // End scanApp

$(document).ready(function() {
	//console.log('ready');

	scanApp.init();
});  // End ready
