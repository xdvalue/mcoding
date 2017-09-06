<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>用户列表页</title>
    <jsp:include page="../common/header.jsp" />
    <script type="text/javascript" src="${ctx}/resources/js/custom/base/user.js"></script>
    <script type="text/javascript">
        var navigateMenuId = '${navigateMenuId}';
        $(function(){
            User.initPageData();
        });
    </script>
</head>
<body style="padding:0px;background:#EAEEF5;">
    <div id="toptoolbar"></div>
    <div style="background: burlywood">
        <div id="maingrid" style="float: left;width:40%;"></div>
    </div>
</body>
</html>
