<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 2020/9/18
  Time: 22:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="model.vo.User" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.vo.DownloadList" %>
<%@ page import="model.dao.Download" %>
<%@ page import="model.dao.UserLogin" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="/controller/show.do"></jsp:include>

<html>
<head>
    <meta charset="UTF-8">
    <title>下载</title>
    <link rel="stylesheet" href="css/head_foot.css">
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
            <a href="/JavaWeb/controller/safeExit.do">【安全退出】</a>
        </div>

    </div>

    <div id="bodyer">
        <div id="show_download">
            <c:forEach items="${downloadLists}" var="download">
                <div class="downloadList">
                    <p class="downloadList_title">${download.name}</p>
                    <img src="${download.image}" class="downloadList_img"/>
                    <p class="downloadList_description">${download.description}</p>
                    <a class="downloadList_button" href="controller/download.do?id=${download.id}">点击下载</a>
                    <p class="downloadList_info">时间：${download.time}</p>
                    <p class="downloadList_info">大小：<fmt:formatNumber value="${download.size/1024/1024}" pattern="0.00"/>MB</p>
                    <p class="downloadList_info">星级：</p>
                    <div id="star_div" style="background-position-x: ${(5-download.star)*(-30)}px">
                    </div>
                </div>
            </c:forEach>


        </div>
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
<link rel="stylesheet" href="/JavaWeb/css/download_page.css">
<script src="/JavaWeb/js/download_page.js"></script>
</body>
</html>
