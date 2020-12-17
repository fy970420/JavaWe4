$(document).ready(function () {
        //发送给服务器的数据
        var userName = "";
        var chrName = "";
        var email = "";
        var province = "";
        var pageSize = parseInt($("#pageSize option:selected").text());      //初始化一下
        var pageNumber = 1;
        var sort = "userName";
        var sortOrder = "asc";

        //服务器返回数据
        var total;
        var pageCount;

        updatePaging();

        // queryParams:
        // {"userName":"admin","chrName":"聂","mail":"qq.com","provinceName":"湖北"}
        // pageParams:
        // {"pageSize":"10","pageNumber":"1","sort":"userName","sortOrder":"desc"}
        //----------五个Search按钮的点击事件----------------
        $("#btSearch").click(function () {
                userName = $("#selectUserName").val();
                chrName = $("#selectChrName").val();
                email = $("#selectEmail").val();
                province = $("#selectProvince").val();
                updatePaging();
        });

        $("#btAdd").click(function () {
                ShowDiv('MyDiv','fade');
                document.getElementById("action").setAttribute("hidden","insert");
                updateOrInsertUser()
                // CloseDiv('MyDiv','fade')
        });

        $("#btClear").click(function () {
                userName = "";
                chrName = "";
                email = "";
                province = "";
                $("#selectUserName").val("");
                $("#selectChrName").val("");
                $("#selectEmail").val("");
                $("#selectProvince").val("");
                updatePaging();
        });

        $("#btDelete").click(function () {
                var checkNum = $("tbody tr input:checkbox:checked").length;
                if(checkNum==0)
                {
                        alert("至少选择一项");
                        return;
                }
                var vals = [];
                $("tbody tr input:checkbox:checked").each(function (index,item) {
                        vals[index] = $(this).val();
                });

                $.ajax({
                        type: "post",
                        url: "controller/deleteUser.do",
                        data: {ids:vals.join("")},
                        dataType: "json",
                        success: function (response) {
                                if(response.code==0){
                                        updatePaging();
                                }
                        }
                });
        });

        //----------Search按钮点击事件结束----------------


        //----------checkall点击事件开始----------------
        $("#ckAll").click(function () {
                if(document.getElementById("ckAll").checked){
                        $("tbody input").attr("checked","checked");
                        $("tbody tr").addClass('tr_select');
                }else {
                        $("tbody input").removeAttr("checked")
                        $("tbody tr").removeClass('tr_select');
                }

        });
        //----------checkall点击事件结束----------------


        //----------每个tr的单独的删除与更新按钮点击事件开始----------------
        $("table").on('click','#btnDel',function () {
                var userName = $(this).attr("value");
                $.ajax({
                        type: "post",
                        url: "controller/deleteUser.do",
                        data: {ids:userName},
                        dataType: "json",
                        success: function (response) {
                                if(response.code==0){
                                        updatePaging();
                                }
                        }
                });
        });

        $("table").on('click','#btnUpdate',function () {
                var userInfoJson = $(this).parents("tr").attr("data");
                var userInfo = JSON.parse(userInfoJson);
                ShowDiv('MyDiv','fade');
                $("#userName").attr("value",userInfo.userName);
                $("#userName").attr("readonly","readonly");
                $("#realName").attr("value",userInfo.chrName);
                $("#email").attr("value",userInfo.email);
                $("#provinces").attr("text",userInfo.province);
                $("#citys").attr("text",userInfo.city);
                $("#password").attr("value",userInfo.password);
                $("#confirmPassword").attr("value",userInfo.password);
                document.getElementById("action").setAttribute("hidden","update");
                updateOrInsertUser()
                // CloseDiv('MyDiv','fade')
        });

        //----------每个tr的单独的删除与更新按钮点击事件结束----------------

        //----------关闭弹出层点击事件开始----------------
        $("#close").click(function () {
                CloseDiv('MyDiv','fade')
        })

        $("#fade").click(function () {
                CloseDiv('MyDiv','fade')
        })
        //----------关闭弹出层点击事件结束----------------


        //----------tr的鼠标悬停和select选中事件开始----------------
        $("tbody").on("mouseover","tr",function () {
                $(this).addClass('tr_hover');
        });
        $("tbody").on("mouseout","tr",function () {
                $(this).removeClass('tr_hover');
        });

        $("tbody").on("click","tr input:checkbox",function () {
                if(this.checked==true) {
                        $(this).parents("tr").addClass('tr_select');
                } else {
                       $(this).parents("tr").removeClass('tr_select');
                }
        });
        //----------tr的鼠标悬停和select选中事件结束----------------


        //----------页面大小select框change事件开始----------------
        $("#pageSize").change(function () {
                pageSize = parseInt($("#pageSize option:selected").text());
                updatePaging();
        });
        //----------页面大小select框change事件结束----------------


        //----------导航栏翻页按钮点击事件----------------
        $("#first,#back,#next,#last").click(function () {
                var state = $(this).attr("name");
                if(state=="first"){
                        pageNumber = 1;
                }
                else if(state=="back"){
                        if(pageNumber!=1) {
                                pageNumber = pageNumber - 1;
                        }
                }
                else if(state=="next"){
                        if(pageNumber!=pageCount){
                                pageNumber = pageNumber + 1
                        }
                }
                else{
                        pageNumber = pageCount;
                }
                document.getElementById("curPage").innerText = pageNumber;
                updatePaging();
        });
        //----------导航栏翻页按钮点击事件结束----------------

        //----------弹出层有关函数开始----------------
        function ShowDiv(show_div,bg_div) {
                document.getElementById(show_div).style.display = "block";
                document.getElementById(bg_div).style.display = "block";
                //弹出层居中
                var windowHeight = $(window).height();
                var windowWidth = $(window).width();
                var popupHeight = $("#"+show_div).height();
                var popupWidth = $("#"+show_div).width();
                var posiTop = (windowHeight - popupHeight) /2;
                var posiLeft = (windowWidth - popupWidth) /2;
                $("#"+show_div).css({"left":posiLeft+"px","top":posiTop+"px","display":"block"});
        }

        function CloseDiv(show_div,bg_div) {
                document.getElementById(show_div).style.display = "none";
                document.getElementById(bg_div).style.display = "none";
        }
        //----------弹出层有关函数结束----------------

        //----------发送ajax请求获取数据，并计算总条数和当前页数，更新paging----------------
        function updatePaging() {
                // queryParams:
                // {"userName":"admin","chrName":"聂","mail":"qq.com","provinceName":"湖北"}
                // pageParams:
                // {"pageSize":"10","pageNumber":"1","sort":"userName","sortOrder":"desc"}
                var queryParamsInfo = new Object();
                queryParamsInfo.userName = userName;
                queryParamsInfo.chrName = chrName;
                queryParamsInfo.email = email;
                queryParamsInfo.province = province;
                var queryParamsJson = JSON.stringify(queryParamsInfo);

                var pageParamsInfo = new Object();
                pageParamsInfo.pageSize = pageSize;
                pageParamsInfo.pageNumber = pageNumber;
                pageParamsInfo.sort = sort;
                pageParamsInfo.sortOrder = sortOrder;
                var pageParamsJson = JSON.stringify(pageParamsInfo);

                $.ajax({
                        type:"post",
                        url:"controller/queryUser.do",
                        data:{"queryParams":queryParamsJson,"pageParams":pageParamsJson},
                        dataType:"json",
                        success:function (response) {
                                var rows = response.rows;
                                total = response.total;
                                pageCount = Math.ceil(total/pageSize);
                                $("#total").text(total);
                                $("#totalPage").text(pageCount);
                                document.getElementById("ckAll").checked=false;
                                $("tbody").empty();
                                $.each(rows,function (index,row) {
                                        var s = JSON.stringify(row);
                                        var str = "<tr data='"+s+"'>";
                                        str = str + '<td><input type ="checkbox" value=' + row.userName +'/></td>';
                                        str = str + '<td>' + row.userName + '</td>>';
                                        str = str + '<td>' + row.chrName + '</td>>';
                                        str = str + '<td>' + row.email + '</td>>';
                                        str = str + '<td>' + row.province + '</td>>';
                                        str = str + '<td>' + row.city + '</td>>';
                                        str = str + '<td><a href="#" id="btnDel" value=' + row.userName +'>删除</a>';
                                        str = str + '<a href="#" id="btnUpdate">修改</a></td>';
                                        str = str + '</tr>';
                                        $("tbody").append(str);
                                });
                                $("tbody tr:even").addClass("tr_even");
                                $("tbody tr:odd").addClass("tr_odd");
                        }
                });
        }
        //----------发送ajax请求获取数据操作结束----------------

        //----------修改用户界面开始---------------------------
        function updateOrInsertUser() {
                var userNameOK;
                var realNameOK;
                var emailOK;
                var provinceOK;
                var cityOK;
                var passwordOK;

                $.ajax({
                        type:"post",
                        url:"controller/loadprovincecity.do",
                        data:"{}",
                        dataType:"json",
                        success:function (response) {
                                $("#provinces").empty();
                                $("#provinces").append($("<option>").val(0).text("请选择省份"));
                                for(index=0; index<response.length; index++){
                                        var option = $("<option>").val(response[index].provinceCode).text(response[index].province);
                                        $("#provinces").append(option);
                                }
                        }
                });

                $("#provinces").change(function () {
                        $("#citys").empty();
                        $("#citys").append($("<option>").val(0).text("请选择城市"));
                        if($("#provinces").val()==0){
                                $("#provincesWarnInfo").text("必须选择省份");
                                return;
                        }
                        provinceOK = true;
                        $.ajax({
                                type:"post",
                                url:"controller/loadprovincecity.do",
                                data:"provinceCode="+$("#provinces").val(),
                                dataType:"json",
                                success:function (response) {
                                        for (index = 0; index < response.length; index++) {
                                                var option = $("<option>").val(response[index].cityCode).text(response[index].city);
                                                $("#citys").append(option);
                                        }
                                }
                        });
                });

                $("#citys").change(function () {
                        if($("#citys").val()==0){
                                $("#citysWarnInfo").text("必须选择城市");
                                return;
                        }
                        cityOK = true;
                });

                $("#userName").blur(function () {
                        var input = $("#userName").val();
                        var warnInfo = $("#userNameWarnInfo");
                        if(input==""){
                                warnInfo.text("用户名不能为空");
                        }
                        else{
                                var reg = /^[a-zA-Z][0-9a-zA-Z-_]{3,14}$/;
                                if(!reg.test(input)){
                                        warnInfo.text("用户名只能使用英文字母和数字，以字母开头，长度为4到15个字符");
                                }
                                else{
                                        $.ajax({
                                                type:"post",
                                                url:"controller/checkUserAndEmail.do",
                                                data:"userName="+input,
                                                dataType:"json",
                                                success:function (response) {
                                                        if(response.code==1 && document.getElementById("action").getAttribute("hidden")!="update"){
                                                                warnInfo.text(response.info);
                                                        }
                                                        else{
                                                                warnInfo.text("该用户名可用");
                                                                userNameOK = true;
                                                        }
                                                }
                                        });
                                }
                        }
                });



                $("#realName").blur(function () {
                        var input = $("#realName").val();
                        var warnInfo = $("#realNameWarnInfo");
                        if(input==""){
                                warnInfo.text("真实姓名不能为空");
                        }
                        else{
                                var reg = /^[\u4e00-\u9fa5]{2,4}$/;
                                if(!reg.test(input)){
                                        warnInfo.text("真实姓名只能是2-4长度的中文");
                                }
                                else{
                                        warnInfo.text("姓名合法");
                                        realNameOK = true;
                                }

                        }
                });

                $("#email").blur(function () {
                        var input = $("#email").val();
                        var warnInfo = $("#emailWarnInfo");
                        if(input==""){
                                warnInfo.text("邮箱不能为空");
                        }
                        else{
                                var reg = /^[0-9a-zA-Z_-]+@[0-9a-zA-Z_-]+\.com$/;
                                if(!reg.test(input)){
                                        warnInfo.text("邮箱格式不正确");
                                }
                                else{
                                        $.ajax({
                                                type:"post",
                                                url:"controller/checkUserAndEmail.do",
                                                data:"email="+input,
                                                dataType:"json",
                                                success:function (response) {
                                                        if(response.code==2 && document.getElementById("action").getAttribute("hidden")!="update"){
                                                                warnInfo.text(response.info);
                                                        }
                                                        else{
                                                                warnInfo.text("该邮箱可用");
                                                                emailOK = true;
                                                        }
                                                }
                                        });
                                }
                        }

                });

                $("#password").blur(function () {
                        var input = $("#password").val();
                        var warnInfo = $("#passwordWarnInfo");
                        if(input==""){
                                warnInfo.text("密码不能为空");
                        }
                        else{
                                var reg = /^.{4,15}$/;
                                if(!reg.test(input)){
                                        warnInfo.text("密码最小长度为4");
                                }
                                else{
                                        warnInfo.text("密码可用");
                                }
                        }
                });

                $("#confirmPassword").blur(function () {
                        var input = $("#confirmPassword").val();
                        var input_password = $("#password").val();
                        var warnInfo = $("#confirmPasswordWarnInfo");
                        if(input==""){
                                warnInfo.text("确认密码不能为空");
                        }
                        else if(input_password!=input){
                                warnInfo.text("确认密码与密码必须一样");
                        }
                        else{
                                warnInfo.text("密码一致")
                                passwordOK = true;
                        }


                });


                $("#registerButton").click(function () {
                        var userInfo = [];
                        userInfo.push($("#userName").val());
                        userInfo.push($("#realName").val());
                        userInfo.push($("#email").val());
                        userInfo.push($("#password").val());
                        userInfo.push($("#provinces option:selected").text());      //获取选中的select的内容
                        userInfo.push($("#provinces").val());
                        userInfo.push($("#citys option:selected").text());
                        userInfo.push($("#citys").val());
                        userInfo.push(document.getElementById("action").getAttribute("hidden"));

                        if(userNameOK && realNameOK && emailOK && provinceOK && cityOK && passwordOK){
                                $.ajax({
                                        type:"post",
                                        url:"controller/registerUser.do",
                                        data:{userInfo:userInfo.join(",")},
                                        dateType:"json",
                                        success:function (response) {
                                                if(response.code==0){
                                                        window.location.href="/JavaWeb/login.html";
                                                }
                                        }
                                });
                        }
                        else{
                                $("#buttonWarnInfo").text("请按照要求填写信息");
                        }
                })
        }
        //----------修改用户界面结束---------------------------
    }
);