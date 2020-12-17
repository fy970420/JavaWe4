function over() {
    var download = document.getElementById("download");
    download.style.background = "linear-gradient(to bottom, rgba(255,255,255,0.8),rgba(255,255,255,0.1))";
    download.style.borderBottom = "solid 5px";
    download.style.borderBottomColor = "rgba(255,255,55,0.8)" ;
}

function out() {
    var download = document.getElementById("download");
    download.style.background = "rgba(151, 151, 123, 0)";
    download.style.border = "none";
}

function retMain() {
    location.href = "/JavaWeb/main.jsp";
}

