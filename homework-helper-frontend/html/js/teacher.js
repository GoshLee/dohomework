// 进入该页面后完成初始化
$(function () {
    console.log("执行初始化")
    var stuId  =$.cookie("teacherId");
    var strCookie = document.cookie;
    console.log(stuId);
    console.log(strCookie);
    document.getElementById('user_id').innerText = stuId;
    initWebPage(1);

})

// 渲染页面
function initWebPage(currentPage) {
    console.log("渲染页面")
    $.ajax({
        url: 'http://localhost/api/teacher/homeworkStatistics?currentPage='+currentPage+'&nums=5',
        contentType:"application/json",
        type:'get',
        dataType:'json',
        success:function (data) {

            // console.log("data", data.data.list[0]);
            // 添加翻页信息
            addPageInfo(data)
            // 添加作业数据
            addHomeworkInfo(data)
        },
        error:function(err){
            if (403 == err.status) {
                alert("请求登录")

                $(location).attr('href', 'index.html');
            }
            else if(4011 == err.status){
                alert("请求登录")

                $(location).attr('href', 'stuLogin.html');
            }
            else if (4012 == err.status){
                alert("请求登录")

                $(location).attr('href', 'teachLogin.html');
            }
            else if (4013 == err.status){
                alert("请求登录")

                $(location).attr('href', 'adminLogin.html');
            }
        }
    })
}

// 点击翻页
function  page(e) {
    // 获取page页
    var page = e.id;
    console.log("page:"+page);
    // 将页面的内容清空，然后重新渲染
    $("#start").html("");
    $("#pagination").html("");
    // 重新渲染页面
    initWebPage(page);
}

// 添加作业信息
function addHomeworkInfo(data) {
    // 添加作业数据
    for(var i = 0; i<data.data.list.length; i++){

        var tr;
        tr = '<div>' +
                '<span>'+data.data.list[i].homework.hDesc+'</span>&nbsp;&nbsp;'+
                '<span>'+'已完成人数：'+data.data.list[i].finishPerson +'</span>'+
                '<span>'+'未完成人数：'+data.data.list[i].unFinishPerson +'</span>'+
            ' </div>'
        $("#start").append('<div>'+tr+'</div><br>');

    }
}

// 添加翻页信息
function addPageInfo(data) {
    var totalPage = data.data.totalPage;
    var currentPage = data.data.currentPage;
    console.log("totalPage:"+totalPage);
    console.log("currentPage:"+currentPage);
    var n = Math.floor(currentPage / 5);
    var lastPage = currentPage-1;
    var nextPage = currentPage+1;
    console.log("n:"+n);
    var start = 4*n+1;
    var end = 4*n+4;
    // 添加上一页按钮
    if (lastPage>0){
        $("#pagination").append('<li><a id="'+lastPage+'" href="#" onclick="page(this)" aria-label="Previous" ><span aria-hidden="true">&laquo;</span></a></li>');
    }

    for (var i = start; i <=end && i<=totalPage ; i++) {
        // console.log("i:"+i)
        var li ;
        if (i==currentPage){
            li = '<li class="active"><a id="'+i+'" href="#" >'+i+'</a></li>'
        }else {
            li = '<li><a id="'+i+'" href="#" onclick="page(this)">'+i+'</a></li>'
        }
        $("#pagination").append(li);
    }
    // 最后添加下一页按钮
    if (nextPage<=totalPage){
        $("#pagination").append('<li><a id="'+nextPage+'" href="#" onclick="page(this)" aria-label="Next" ><span aria-hidden="true">&raquo;</span></a></li>');
    }

}




// 注销登录
function logout() {
    console.log("点击注销")
    $.ajax({
        url: 'http://localhost/api/teacher/logout',
        contentType:"application/json",
        type:'get',
        success:function (data) {
            var keys = document.cookie.match(/[^ =;]+(?=\=)/g);
            if(keys) {
                for(var i = keys.length; i--;)
                    document.cookie = keys[i] + '=0;expires=' + new Date(0).toUTCString()
            };
            $(location).attr('href', 'index.html');
        },
        error:function(err){
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
            alert("网络连接失败,稍后重试",err);
        }
    });
}

// 发布新作业
function post() {
    var cId = $('#cId').val();
    var hName = $('#hName').val();
    var hTime = $('#hTime').val();
    console.log(cId, hName, hTime);
    $.ajax({
        type:"post",
        url:"http://localhost/api/teacher/postHomework?cId="+cId+"&hName="+hName+"&hTime="+hTime,
        //data:{username:$('#username').val(),password:$('#password').val()},
        success:function (data)
        {
            if(201==data.status){
                alert("发布成功");
                $(location).attr('href', 'teacher.html');
                return true;
            }
            else  if (400==data.status){
                alert("日期格式错误，例如2019-01-01");
            }
            else  if (404==data.status){
                alert("班级不存在");
            }
            else{
                alert("发布失败");
                return false;
            }
        },
        error:function (error) {
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
}