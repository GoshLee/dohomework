



function test(){
    console.log("点击测试")

    $.ajax({
        type:"get",
        url:"http://localhost/api/stu/test",
        success:function (data) {
            console.log("成功:"+data.data)
        }
    })
}



$("#modal-login").on('submit', function (ev) {
    console.log("点击登录");
    ev.stopPropagation();
    ev.preventDefault();
    var teacherId = $('#teacherId').val()
    var password = $('#password').val()
    console.log(teacherId + '   ' + password)
    $.ajax({
        type: "post",
        url: "http://localhost/api/teacher/login?id=" + teacherId + "&password=" + password,
        dataType:'json',
        // xhrFields:{withCredentials: true}, // 发送cookie
        // crossDomain:true,
        success: function (data,textStatus,request) {
            //console.log("request:"+request.toLocaleString())
            // console.log("headers:"+request.getAllResponseHeaders());
            // console.log("cookie:"+request.getResponseHeader("Set-Cookie"));
            // console.log(data.status);


            if (200 == data.status) {
                // console.log("登录成功")
                // 设置cookie
                var JSESSIONID = $.cookie("JSESSIONID");
                $.cookie("JSESSIONID", JSESSIONID,{path:"/"});

                console.log(data.data);
                // localStorage.setItem("data", data);
                $.cookie("teacherId",data.data);


                // console.log("JSESSIONID:"+JSESSIONID)
                // 登录成功，跳转学生首页
                $(location).attr('href', 'teacher.html');


                // 测试请求
                // $.ajax({
                //     type:"get",
                //     url:"http://localhost/api/stu/myHomework",
                //     success: function (data) {
                //         console.log(data.data)
                //     }
                // })

            } else {
                alert("用户名或密码错误");
                return false;
            }
        },
        error: function (error) {
            console.log(error);
            if (403 == err.status) {
                $(location).attr('href', 'index.html');
            }
            else if(4011 == err.status){
                $(location).attr('href', 'stuLogin.html');
            }
            else if (4012 == err.status){
                $(location).attr('href', 'teachLogin.html');
            }
            else if (4013 == err.status){
                $(location).attr('href', 'adminLogin.html');
            }
            alert("请求失败！");
        }
    })
})



