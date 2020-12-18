$(() => {

    $('select').change(() => {//监听select选择框
        var province = $('select').val();
        console.log(province);
        $.ajax(//当选择时，发送异步请求
            {
                type:"GET",
                url: "/search/" + province,
                success:function (result){
                    html = '';
                    if(result.length === 0){
                        html = '<span><font color="#FF0000">没有数据!</span>';
                    }
                    result.forEach(function (value,index){//遍历数据，打印各个省份结果
                        html += '<span>'+(index+1)+'.<font color="#0000FF">'+value.name+'</font></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';
                        if((index+1)%3 === 0){
                            html += '<br>';
                        }
                    });
                    $('#university').html(html);
                },
                error:function (error){
                    console.log('发生错误'+error);
                },
            });
    });
});