<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 2020/9/18
  Time: 22:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="model.vo.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>主页</title>
    <link rel="stylesheet" href="/JavaWeb/css/head_foot.css">
    <link rel="stylesheet" href="/JavaWeb/css/personalpage_page.css">
    <script src="/JavaWeb/js/head_foot.js"></script>
</head>


<body>
<div id="main_div">

    <div id="header">

        <div id="header_logo">
            <img src="/JavaWeb/image/logo_pic.png" id="logo" onclick="retMain()">
        </div>

        <ul id="header_tabs">
            <li onmouseover="over()" onmouseout="out()" id="download">
                <a href="/JavaWeb/download.jsp">下载资源</a>
            </li>
            <li >
                <a href="/JavaWeb/downloadmanage.jsp">资源管理</a>
            </li>
            <li>
                <a href="/JavaWeb/usermanage.jsp">用户管理</a>
            </li>
            <li>
                <a href="/JavaWeb/personalpage.jsp">个人中心</a>
            </li>
        </ul>

        <div id="header_userInfo">

            当前用户: ${curUser}
            <a href="controller/safeExit.do">【安全退出】</a>
        </div>

    </div>

    <div id="bodyer">

    </div>

    <div id="footer">
        <div id="statement">
            <p>COPYRIGHT © 1998 - 2020 TENCENT. ALL RIGHTS RESERVED.</p>
            <img src="/JavaWeb/image/gswj.png" class="footer_img"><p>工商网监电子标识 |粤网文[2017]6138-1456号|（总）网出证（粤）字第057号</p>
            <img src="/JavaWeb/image/icplogo.png" class="footer_img"><p>文网游备字[2016]M-CSG 0059 号|新广出审[2017] 6712号|ISBN 978-7-7979-8408-9</p>
            <p>全国文化市场统一举报电话：12318</p>
        </div>
    </div>

</div>
</body>
</html>
