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
    <link rel="stylesheet" href="/JavaWeb/css/usermanage_page.css">
    <script src="/JavaWeb/js/head_foot.js"></script>
    <script src="/JavaWeb/js/jquery-3.5.0.min.js"></script>
    <script src="/JavaWeb/js/usermanage_page.js"></script>
</head>


<body>
<div id="main_div">
    <div id="fade" class="black_overlay"></div>
    <div id="MyDiv" class="white_content">
        <div style="text-align: right; height: 20px">
            <span style="font-size: 24px; cursor:pointer;" title="点击关闭" id="close">点击关闭</span>
        </div>
        <div>
            <h1 id="welcome">用户修改</h1>
            <p>
                <input id="userName" type="text" placeholder="用户名"/>
                <span class="warnInfo" id="userNameWarnInfo"></span>
            </p>
            <p>
                <input id="realName" type="text" placeholder="真实姓名"/>
                <span class="warnInfo" id="realNameWarnInfo"></span>
            </p>
            <p>
                <input id="email" type="text" placeholder="邮箱"/>
                <span class="warnInfo" id="emailWarnInfo"></span>
            </p>
            <p>
                <select id="provinces" ></select>
                <span class="warnInfo" id="provincesWarnInfo"></span>
            </p>
            <p>
                <select id="citys"></select>
                <span class="warnInfo" id="citysWarnInfo"></span>
            </p>
            <p>
                <input id="password" type="password" placeholder="密码"/>
                <span class="warnInfo" id="passwordWarnInfo"></span>
            </p>
            <p>
                <input id="confirmPassword" type="password" placeholder="确认密码"/>
                <span class="warnInfo" id="confirmPasswordWarnInfo"></span>
            </p>
            <p id="registerButton">确定</p>
            <span id="buttonWarnInfo" ></span>
            <input type="text" id="action" name="action" hidden />
        </div>
    </div>

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
            <div id="search">
                <form id="searchForm">
                    <input name="userName" type="text" placeholder="请输入用户名" id="selectUserName" />
                    <input name="name" type="text" placeholder="请输入姓名" id="selectChrName" />
                    <input name="email" type="text" placeholder="请输入邮箱" id="selectEmail" />
                    <input name="province" type="text" placeholder="请输入省份" id="selectProvince" />
                </form>
                <div id="searchBt">
                    <a href="#" id="btSearch">查找</a>
                    <a href="#" id="btClear">清空</a>
                    <a href="#" id="btAdd">添加</a>
                    <a href="#" id="btDelete">删除</a>
                </div>
            </div>

            <table>
                <thead>
                    <tr>
                        <td width="40"><input type="checkbox" id="ckAll" title="选择"/></td>
                        <td class="bg" datatype="userName" id="sortByUserName">用户名</td>
                        <td width="110">中文名</td>
                        <td>邮箱</td>
                        <td width="70" class="bg" datatype="provinceName" id="sortByProvinceName">省份</td>
                        <td width="70">城市</td>
                        <td width="120">操作</td>
                    </tr>
                </thead>
                <tbody>

                </tbody>
            </table>

            <div>
                <div id="paging">
                    <div class="pageSize">
                        每页
                        <select id="pageSize">
                            <option>5</option>
                            <option selected>10</option>
                            <option>15</option>
                        </select>
                        条数据，共<span id="total"></span>条数据，当前页数<span id="curPage">1</span>页/<span id="totalPage"></span>页
                    </div>
                    <div id="pageNav">
                        <a href="#" id="first" name="first">首页</a>
                        <a href="#" id="back" name="back">上一页</a>
                        <a href="#" id="next" name="next">下一页</a>
                        <a href="#" id="last" name="last">尾页</a>
                    </div>
                </div>
                <div>

                </div>
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
</body>
</html>
