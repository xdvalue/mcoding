<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>找回密码</title>
<link rel="stylesheet" type="text/css" href="resources/css/forgetCode_style.css"/>
<script type="text/javascript" src="resources/js/jquery/jquery-1.6.2.min.js"></script>

<script type="text/javascript">
//检验两次密码是否输入一致

   function check (){
         var newPwd = $("#password").val();
         var newPwd2=$("#password2").val();
         if(newPwd.replace(/[ ]/g,"")==''){
           $("#checkMessage").html("<font style='color:red;font-size:20px'>密码不能为空</font>");
           event.returnValue=false ;
           }else if(newPwd!=newPwd2){
          $("#checkMessage").html("<font style='color:red;font-size:20px'>密码输入不一致</font>");
           event.returnValue=false ;
        } 
        
        $.ajax({
       							type : "POST",
       							url : "./forgetPwd2.html",
       							data : $("#forgerPwdForm").serialize(),
       							success : function(msg) {
       								var url="./forgetPwd2.html";
						            window.location =url;
       							},
       							error : function(err) {
       								isSignAlert("错误操作");
       							}
       						}); 

       }
</script>
</head>
<body>
<div class="fp_content">
	 <p>找回密码</p>
	 <hr width="375px" style="border:1px dashed #787878; ">  </hr>
	 <form id="forgerPwdForm">
	    <div id="checkMessage"></div>
	 	<input style="width:375px" type="password" class="code" id="password" name="password" placeholder="请输入新密码" />
	 	<input style="width:375px" type="password" class="code"  id="password2" name="password2" placeholder="再次输入新密码"/>
	 	<input  type="hidden"  id="userId" name="userId" value="${userId}"  />
	 <input style="margin-top:173px;" class="btn" type="button" value="提交" onclick="check()"/>
	 </form>
</div>
<div id="footer">
		<p>请使用IE9、IE10/Firefox/Chrome，推荐使用Chrome以获取更佳体验！</p>
		<ul>
			<li><a href="">点击下载</a></li>
		</ul>
		<p>©2014 XingRun Holding All Rights Reserbved</p>
</div>
</body>

</html>