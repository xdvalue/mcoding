<%--
  Created by LiBing on 2014-07-28  14:27
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>系统超时</title>
    <style type="text/css">
        div.error {
            width: 260px;
            border: 2px solid red;
            background-color: yellow;
            text-align: center;
        }
    </style>
</head>
<body>
<h1>系统超时</h1>
<hr>
<div class="error">
    系统超时<br>
    ${requestScope['SPRING_SECURITY_403_EXCEPTION'].message}
</div>
<hr>
</body>
</html>
