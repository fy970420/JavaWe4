var start = "我们见面的时间还剩"
var count = "10";
var end = "秒，不要太想我!"

document.getElementById("autoExit").innerHTML = start+count+end;

var state = document.getElementById("state").textContent

function countDown() {
    count--;
    document.getElementById("autoExit").innerHTML = start+count+end;
    if(count==0){
        clearInterval(t);
        if(state=="权限情况")
            location.href="/JavaWeb/main.jsp";
        else
            location.href="/JavaWeb/login.html";

    }
}
var t = setInterval(countDown,1000);

function jumpToOtherPage() {
    if(state=="权限情况")
        location.href="/JavaWeb/main.jsp";
    else
        location.href="/JavaWeb/login.html";
}