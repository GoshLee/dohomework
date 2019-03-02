// 进入该页面后完成初始化
$(function () {
    console.log("执行初始化")
    var stuId  =$.cookie("stuId");
    var strCookie = document.cookie;
    console.log(stuId);
    console.log(strCookie);
    document.getElementById('user_id').innerText = stuId;
    var currentPage = 1;
    initWebPage(currentPage);

})

// 渲染页面
function initWebPage(currentPage) {
    console.log("----init----")
    $.ajax({
        url: 'http://localhost/api/stu/homework?currentPage='+currentPage+'&nums=5',
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
        error:function (data) {
            console.log("请求失败：" +data.status)
            if (403 == data.status) {
                alert("请求登录")
                $(location).attr('href', 'index.html');
            }
            else if(4011 == data.status){
                alert("请求登录")

                $(location).attr('href', 'stuLogin.html');
            }
            else if (4012 == data.status){
                alert("请求登录")

                $(location).attr('href', 'teachLogin.html');
            }
            else if (4013 == data.status){
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
        var finish = "";
        if (data.data.list[i].sFinish==1){
            finish="已提交";
        } else {
            finish = "未完成";
        }
        var tr;
        tr='<form id="upload'+data.data.list[i].hId+'" enctype="multipart/form-data" method="post" ><span id="span_name_'+i+'"></span>&nbsp;&nbsp;&nbsp;<span id="span_finish_'+i+'"></span><div class="form-group col-center-block" ><input  style="position: absolute;left: 30%;" type="file" name="file" id="file" class="form-control-file"/><button type="button" class="btn btn-primary btn-xl js-scroll-trigger" onclick="upLoadFile(this);" style="background-color:#bbd4d1" id="'+data.data.list[i].hId+'">上传</button></div></form>\n'
        $("#start").append('<div>'+tr+'</div><br>');
        document.getElementById('span_name_'+i).innerText = "作业名："+data.data.list[i].hDesc;
        document.getElementById('span_finish_'+i).innerText = finish;
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
        url: 'http://localhost/api/stu/logout',
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
        error:function (data) {
            console.log("请求失败：" +data.status)
            if (403 == data.status) {
                $(location).attr('href', 'index.html');
            }
            else if(4011 == data.status){
                $(location).attr('href', 'stuLogin.html');
            }
            else if (4012 == data.status){
                $(location).attr('href', 'teachLogin.html');
            }
            else if (4013 == data.status){
                $(location).attr('href', 'adminLogin.html');
            }
        }
    });
}

// 提交作业
function upLoadFile(e) {
    var hId = e.id;
    var form = document.getElementById('upload'+hId);
    file = new FormData(form);
    $.ajax({
        url:"http://localhost/api/stu/upHomework/"+hId,
        type:"post",
        data:file,
        processData:false,
        contentType:false,
        success:function(res){

            if(res.status==201){
                alert("上传成功！");
                $(location).attr('href', 'stu.html');
            }else {
                alert("上传失败")
            }
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

    })
}