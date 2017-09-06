<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta  charset="utf-8" />
<title>授权页面</title>
<link rel="stylesheet" type="text/css" href="${basePath}resources/css/forgetCode_style.css">
<script type="text/javascript" src="${basePath}resources/js/jquery/jquery-1.6.2.min.js"></script>
</head>
<body>
<div class="fp_content">
	 <p>授权页面</p>
	 <hr width=375px style="border:1px dashed #787878; ">
	 <form id="formsss">
	    <div id="checkMessage"></div>
	    <div id="checkMessage2"></div>
	 	<input type="text"  class="username" id="username" name="username" placeholder="PMS项目管理系统账号" value=""/>
	 	<input type="password" class="username" id="password" name="password" placeholder="PMS项目管理系统密码" value=""/>
	 	
	 	<input type="text"  class="username" id="user" name="user" placeholder="附近生活账号" value=""/>
	 	<input type="password" class="username" id="pass" name="pass" placeholder="附近生活密码" value=""/>
	 	
	 </form>
	 <input class="btn" type="button" value="绑定" onclick="checkUser()"/>
	 &nbsp; &nbsp; &nbsp;&nbsp;&nbsp;&nbsp;
	 <a class="btn" href="javascript:history.go(-1);">返回</a>
</div></br>
<div id="footer">
		<p>©2014 XingRun Holding All Rights Reserbved</p>
</div>
</body>

<script type="text/javascript">
//娇艳所输入的用户存在否
function checkUser(code){
    var params = $("#formsss").serialize();
    var param1=$("#username").val();
    var param2=$("#password").val();
    var param3=$("#user").val();
    var param4=$("#pass").val();
    if(param1==""||param2==""||param3==""||param4==""){
    	alert("提示：请补全信息再提交。");
    }else{
    	$.ajax({
    		type : "POST",
    		url : "./checkUser.html",
    		data : params,
    		success : function(data) {  
    			alert("提示："+data.msg);
    			history.back();
    		},
    		error : function(err) {
    			alert("提示："+err.msg);
    		}
    	});
    }
	
}
</script>

</html>