$(document).ready(function () {
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
                        if(response.code==1){
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
                        if(response.code==2){
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
        if(userNameOK && realNameOK && emailOK && provinceOK && cityOK && passwordOK){
            $.ajax({
                type:"post",
                url:"controller/registerUser.do",
                data:{userInfo:userInfo.join(",")},
                dateType:"json",
                success:function (response) {
                    alert(response.info);
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
});

