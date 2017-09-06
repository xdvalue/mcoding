<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta  charset="utf-8" />
<title>找回密码</title>
<link rel="stylesheet" type="text/css" href="${basePath}resources/css/forgetCode_style.css">
<script type="text/javascript" src="${basePath}resources/js/jquery/jquery-1.6.2.min.js"></script>
</head>
<body>
<div class="fp_content">
	 <p>找回密码</p>
	 <hr width=375px style="border:1px dashed #787878; ">
	 <form id="formsss" action="find_password2.html">
	    <div id="checkMessage"></div>
	    <div id="checkMessage2"></div>
	 	<input type="text"  class="username" id="loginName" name="loginName" placeholder="用户名"/>
	 	<input type="text" class="username" id="mobilePhone" name="mobilePhone" placeholder="手机号码"/>
	 	<input type="text" class="code" id="code" name="code" placeholder="验证码">
	 	<input type="button" id="getCode" name="getCode"  class="code hq_code" value="获取验证码" onclick="createCode()"><div id="div1"></div> 
	 	<div id="timeLimit" style="color:red;"></div> 
	 	<input type="hidden"  class="username" id="userId" name="userId" />
	 	<!-- <input id="trueCode"  name="trueCode" type = "hidden" > -->
	 </form>
	 <input class="btn" type="button" value="下一步" onclick="validate()"/>
	 &nbsp; &nbsp; &nbsp;&nbsp;&nbsp;&nbsp;
	 <input class="btn" type="submit" value="返回" onclick="goBack()"/ >
</div>
<div id="footer">
	    <p>请使用IE9、IE10/Firefox/Chrome，推荐使用Chrome以获取更佳体验！</p>
		<ul>
			<li><a href="">点击下载</a></li>
		</ul>
		<p>©2014 XingRun Holding All Rights Reserbved</p>
</div>
</body>

<script type="text/javascript">

var code ; //在全局定义验证码   
var start = 60; //获取验证码按钮锁定秒数
var step = -1; //计时器运算一次减去的数字


   //产生验证码  
   function createCode(){  
     code = "";   
     var codeLength = 6;//验证码的长度  
     //var checkCode = document.getElementById("trueCode");   
     var random = new Array(0,1,2,3,4,5,6,7,8,9);//随机数  
     for(var i = 0; i < codeLength; i++) {//循环操作  
        var index = Math.floor(Math.random()*10);//取得随机数的索引（0~35）  
        code += random[index];//根据索引取得随机数加到code上  
    }  
    //checkCode.value = code;//把code值赋给验证码  
    checkInput(code);
} 
 
//校验所输入的用户存在否
function checkInput(code){
    var params = $("#formsss").serialize() + "&trueCode="+code;;
	$.ajax({
		type : "POST",
		url : "./forgetPwd.html",
		data : params,
		success : function(data) {  
			if(data!=1){ //有数据就发送验证码
			 $("#checkMessage").html("<font style='color:red;font-size:15px'>验证码已发送到手机,"+start+"秒后才可重新获取验证码</font>");
			 disableCode();  //验证码按钮失效60秒
			  $("#userId").attr("value",data);
			}else{
			 $("#checkMessage").html("<font style='color:red;font-size:15px'>你所输入的用户不存在(用户名或手机号不正确)</font>");
			}
		},
		error : function(err) {
			$("#checkMessage").html("<font style='color:red;font-size:15px'>操作失败</font>");
		}
	});
}

//初始化计时函数
function disableCode(){ 
   document.getElementById('getCode').disabled=true;
   start = 60;
   time();
}

//1分钟计时函数
function time(){
 if(start < 1) {
	 document.getElementById('getCode').disabled=false;
	 clearTimeout(time());
 }
     start = start + step ;
	// document.getElementById("timeLimit").innerHTML = start;
    $("#checkMessage").html("<font style='color:red;font-size:15px'>验证码已发送到手机,"+start+"秒后才可重新获取验证码</font>");
     setTimeout("time()",1000);	
}


 //校验验证码  
 function validate(){  
  var inputCode  = document.getElementById("code").value//取得输入的验证码并转化为大写           
   if(inputCode.length <= 0) { //若输入的验证码长度为0  
       $("#checkMessage2").html("<font style='color:red;font-size:15px'>请输入验证码</font>");
    } else if(inputCode != code ) { //若输入的验证码与产生的验证码不一致时  
        $("#checkMessage2").html("<font style='color:red;font-size:15px'>验证码输入有误,请重新输入或重新获取验证码</font>");
      //  createCode();//刷新验证码  
          document.getElementById("code").value = "";//清空文本框  
    }else { //输入正确时  
        var userId = document.getElementById("userId").value;
        var url="./find_password2.html?userId="+userId;
        window.location =url;
    }             

}

//返回按钮事件
function goBack(){

   javascript:window.history.go(-1);

}


</script>

</html>