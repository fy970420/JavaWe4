function changeVcode() {
    var Image = document.getElementById("verifyCode");
    Image.src = "controller/createvcode.do?t="+Math.random();
}



// var xmlHttp;
//
// function ajaxCheckLogin() {
//     var userName = document.getElementById("userName").value;
//     var password = document.getElementById("password").value;
//     var vcode = document.getElementById("vcode").value;
//     var autoLogin = document.getElementById("autoLogin").value;
//     var requestData = "userName="+userName+"&password="+password+"&vcode="+vcode+"&autoLogin="+autoLogin;
//
//
//     createXMLHttpRequest();
//     xmlHttp.open("post","controller/login.do",true);
//     xmlHttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
//     xmlHttp.send(requestData);
//     xmlHttp.onreadystatechange = function () {
//         if(xmlHttp.readyState==4 && xmlHttp.status==200){
//             var response = xmlHttp.responseText;
//             var json = JSON.parse(response);
//             if(json.code==0){
//                 window.location.href="main.jsp";
//             }
//             else{
//                 var checkError = document.getElementById("checkError");
//                 checkError.innerText = json.info;
//             }
//         }
//
//     }
// }
//
// function createXMLHttpRequest() {
//     if(window.XMLHttpRequest){
//         xmlHttp = new XMLHttpRequest();
//     }
//     else{
//         xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
//     }
// }



$("#submitButton").click(function () {
    var userName = $("#userName").val();
    var password = $("#password").val();
    var vcode = $("#vcode").val();
    var autoLogin = $("#autoLogin").val();

    $.ajax({
        type:"post",
        url:"controller/login.do",
        data:"userName="+userName+"&password="+password+"&vcode="+vcode+"&autoLogin="+autoLogin,
        dataType:"json",
        success:function (data) {
            if(data.code==0){
                window.location.href="/JavaWeb/main.jsp";
            }
            else{
                $("#checkError").text(data.info);
            }
        }
    });
});

$("#userName,#password,#vcode").blur(function () {
    var input = $(this).val();
    if(input==""){
        $(this).attr("placeholder","不能为空");
    }
});

$("#register").click(function () {
    window.location.href="/JavaWeb/register.html";
})