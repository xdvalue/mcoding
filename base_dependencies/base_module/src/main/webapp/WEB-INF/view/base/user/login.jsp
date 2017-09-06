<%@ page language="java" pageEncoding="UTF-8" %>
<html>
<head>
<meta charset="utf-8" />
<title>熹鼎科技-SCRM运营平台</title>
<link rel="stylesheet" type="text/css" href="resources/css/login_style.css">
<link rel="icon" type="image/png" href="${basePath}resources/images/pagelogo.png"/>
<script type="text/javascript" src="resources/js/jquery/jquery-1.6.2.min.js"></script>

</head>
<body>
<div class="all">
	<div class="mainDiv">
		<div class="header">
			<img src="resources/images/emis_logo2.png" alt=""/>
			<div style="margin-top:20px;"><h1><span style="color:#ff6b00;"></span>&nbsp;SCRM运营平台</h1></div>
		</div>
		<div class="content"style="margin-top:-70px;">
			<!-- <form action="loginForSpringSecurity" method="post" class="loginDiv" id="loginForm"> -->
			<!-- <form  class="loginDiv" id="loginForm"> -->
			<div id="pms">
				<form action="loginForSpringSecurity" method="post" class="loginDiv" id="loginForm">
					<div id="divTip" style="margin-left:140px;">
						<font style="color:red;">${tip}</font>
					</div>
					<ul>
							<li>
								<img src="resources/images/username.png" alt=""/>
								<input id="j_username" name="username" class="username" type="text" placeholder="用户名" />
							</li>
							<li>
								<img src="resources/images/password.png" alt=""/>
								<input id="j_password" name="password" class="password" type="password" placeholder="密码" />
								<input class="loginBtn" type="submit" id="btnLogin" value="登录"/>
							</li>
					</ul>
				</form>
			</div>
			<form  class="loginDiv" id="nearby" style="display:none">
				<div id="divTip1" style="margin-left:140px;">
					<font style="color:red;">${tip}</font>
				</div>
				<!-- <ul>
					<div>
						<li>
							<img src="resources/images/username.png" alt=""/>
							<input id="nearbyName" name="_nearbyName" class="username" type="text" placeholder="您的附近号"/>
						</li>
						<li>
							<img src="resources/images/password.png" alt=""/>
							<input id="nearbyPwd" name="_nearbyPwd" class="password" type="password" placeholder="附近号密码"/>
							<input class="loginBtn" type="button" onclick="nearUserCheck();" value="登录"/>
						</li>
					</div>	
				</ul> -->
			</form>
			<!-- <div class="fjh">
					<label id="fjh_login" class="dl" onclick="change();">附近号登录</label>
				<input type="checkbox" id="fjh_login" class="dl" name="_loginType"/
				<a class="bd" href="./userBind.html">绑定附近号</a>
			</div> -->
			<div class="content_bottom">
				<a class="forgetPsw" href="./find_password.html" id="forgetPsw">忘记密码 ?</a><br>
			</div>
		</div>
	</div>
</div>
<div style=" background:#cccccc;">
	<div id="footer">
			<p>请使用IE9、IE10/Firefox/Chrome，推荐使用Chrome以获取更佳体验！</p>
			<ul>
				<li><a target="_blank" href="http://rj.baidu.com/soft/detail/14744.html?ald">点击下载</a></li>
			</ul>
			<p style="margin-top:-30px;">COPYRIGHT @ mcoding.cn 广州熹鼎信息科技有限公司 | 粤ICP备16003881号 </p>
	</div>
</div>

<!-- 百度网站统计 star -->
<script type="text/javascript" src="resources/js/front/webStatistics.js"></script>
<!-- end -->

<script type="text/javascript">
	var submit_type=1;
	
	function change(){
		if(submit_type==1){
			submit_type=0;
			$("#pms").attr("style","display:none");
			$("#nearby").attr("style","");
			$("#forgetPsw").attr("style","display:none");
		}else{
			submit_type=1;
			$("#pms").attr("style","");
			$("#nearby").attr("style","display:none");
			$("#forgetPsw").attr("style","display:block");
		}
	}
	$(function(){
		$("#fjh_login").click(function(){
			if($(this).hasClass("on")){
				$(this).removeClass("on");
			}else{
				$(this).addClass("on");
			}
		});
		
		if ($("#j_password").focus()) {
			$("#j_password").css("color","#393939");
		};
		if ($("#j_username").focus()) {
			$("#j_username").css("color","#393939");
		};
		if ($("#nearbyName").focus()) {
			$("#nearbyName").css("color","#393939");
		};
		if ($("#nearbyPwd").focus()) {
			$("#nearbyPwd").css("color","#393939");
		};

	});
	function nearUserCheck(){
		// 验证登陆用户名和密码是否为空
		var $nearbyName = $("#nearbyName").val();
		var $nearbyPwd = $("#nearbyPwd").val();
		if($nearbyName == "" ){
			$("#divTip1").html("<font style='color:red;'>用户名不能为空</font>");
			$("#nearbyName").focus();
			return false;
		}
		if($nearbyPwd == ""){
			$("#divTip1").html("<font style='color:red;'>密码不能为空</font>");
			$("#nearbyName").focus();
			return false;
		}
		//附近号登陆校验
		params = "nearUserName="+$nearbyName+"&nearUserPass="+$nearbyPwd;
		$.post("nearLoginCheck.html",params,function(data){
			if(data.code==1){
				$("#j_username").attr("value",data.data.loginName);
				$("#j_password").attr("value",data.data.password);
				$("#loginForm").submit();
				return true;
			}else{
				$("#divTip1").html("<font style='color:red;'>用户或密码错误，请重新填写</font>");
				return false;
			}
		});
	}
</script>
</body>
</html>