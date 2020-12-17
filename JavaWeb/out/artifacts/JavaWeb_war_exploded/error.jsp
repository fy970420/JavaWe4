<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 2020/9/18
  Time: 22:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<head>
    <meta charset="UTF-8">
    <title>出错了</title>
    <link rel="stylesheet" type="text/css" href="/JavaWeb/css/error_page.css">
</head>

<body>
    <div id="main_div">
        <div id="img_div">
            <img src="/JavaWeb/image/error_pic.jpg">
        </div>

        <div id="errorInfo_div">
            <p class="polite">悄悄告诉你，你的</p>
            <p class="errorState" id="state">${ state }</p>
            <p class="polite">有点问题哦~</p><br>
            <p class="polite" id="autoExit"></p>
            <br>
            <br>
            <br>
            <br>
            <p class="polite">不想见到我吗?</p><br>
            <p class="polite">赶紧拿着这张<span onclick="jumpToOtherPage()" id="jump">机票</span>走的远远的！</p>
        </div>
    </div>
    <script src="/JavaWeb/js/error_page.js"></script>

</body>

</html>
