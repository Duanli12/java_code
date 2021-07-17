<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!DOCTYPE HTML>
<html>
<head>
    <base href="<%=basePath%>">
    <meta charset="utf-8">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
    <meta http-equiv="Cache-Control" content="no-siteapp" />

    <script type="text/javascript" src="lib/html5shiv.js"></script>
    <script type="text/javascript" src="lib/respond.min.js"></script>

    <link href="static/h-ui/css/H-ui.min.css" rel="stylesheet" type="text/css" />
    <link href="static/h-ui.admin/css/H-ui.login.css" rel="stylesheet" type="text/css" />
    <link href="static/h-ui.admin/css/style.css" rel="stylesheet" type="text/css" />
    <link href="lib/Hui-iconfont/1.0.8/iconfont.css" rel="stylesheet" type="text/css" />

    <script type="text/javascript" src="lib/DD_belatedPNG_0.0.8a-min.js" ></script>

    <script>DD_belatedPNG.fix('*');</script>

    <title>后台登录 - H-ui.admin v3.1</title>
    <meta name="keywords" content="H-ui.admin v3.1,H-ui网站后台模版,后台模版下载,后台管理系统模版,HTML后台模版下载">
    <meta name="description" content="H-ui.admin v3.1，是一款由国人开发的轻量级扁平化网站后台模板，完全免费开源的网站后台管理系统模版，适合中小型CMS后台系统。">
</head>

<body>
<input type="hidden" id="TenantId" name="TenantId" value="" />

<div class="loginWraper">
    <br><br><br>

    <center> <h1>xxx管理系统</h1></center>
    <div id="loginform" class="loginBox">
        <form class="form form-horizontal" action="index.html" method="post">
            <div class="row cl">
                <label class="form-label col-xs-3"><i class="Hui-iconfont">&#xe60d;</i></label>
                <div class="formControls col-xs-8">
                    <input id="name" name="" type="text" placeholder="账户" class="input-text size-L" value="">
                </div>
            </div>
            <div class="row cl">
                <label class="form-label col-xs-3"><i class="Hui-iconfont">&#xe60e;</i></label>
                <div class="formControls col-xs-8">
                    <input id="pwd" name="" type="password" placeholder="密码" class="input-text size-L" value="">
                </div>
            </div>
            <div class="row cl">
                <div class="formControls col-xs-8 col-xs-offset-3">
                    <input id="in" class="input-text size-L" type="text" placeholder="验证码"  style="width:150px;">
                    <img id="im" src="http://localhost:8080/jspday5/gc"> <a id="kanbuq" href="javascript:changeImg();">看不清，换一张</a> </div>
            </div>
            <div class="row cl">
                <div class="formControls col-xs-8 col-xs-offset-3">
                    <label id="online">
                        <input type="checkbox" name="online" id="check" value="">
                        使我保持登录状态</label>
                </div>
            </div>
            <div class="row cl">
                <div class="formControls col-xs-8 col-xs-offset-3">
                    <input name="" type="button" onclick="checkLogin();" class="btn btn-success radius size-L" value="&nbsp;登&nbsp;&nbsp;&nbsp;&nbsp;录&nbsp;">
                    <input name="" type="reset" class="btn btn-default radius size-L" value="&nbsp;取&nbsp;&nbsp;&nbsp;&nbsp;消&nbsp;">
                </div>
            </div>
        </form>
    </div>
</div>
<div class="footer">Copyright 你的公司名称 by H-ui.admin v3.1</div>
<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="lib/layer/2.4/layer.js"></script>
<script>
    var i=0
   function changeImg() {
       $("#im").attr("src","http://localhost:8080/jspday5/gc?i="+i);
       i++;
   }
    function checkLogin() {
        var code = $("#in").val();
        var username = $("#name").val();
        var password = $("#pwd").val();

        if(code.length==0){
            layer.msg("验证码不能为空！！！",{icon:2});
            $("#in").focus(); //把鼠标焦点聚焦到某个元素上面
            return;
        }
        if(username.length==0){
            layer.msg("账号不能为空！！！",{icon:2});
            $("#name").focus(); //把鼠标焦点聚焦到某个元素上面
            return;
        }
        if(password.length==0){
            layer.msg("密码不能为空！！！",{icon:2});
            $("#pwd").focus();
            return;
        }
        //判断记住密码的复选框是否被选中
        var check = $("#check").prop("checked");
        var remember = "no";
        if(check){
            remember = "yes";
        }
        $.ajax({
            type:"post" ,
            url:"<%=basePath%>uc",
            dataType:"text",
            data:{ //在这里面 name 和 age都是字符串
                code:code,
                op:"checkLogin",
                us:username,
                pwd:password,
                rm:remember
            },
            success:function (info) {
               if(info=="SUCCESS"){
                   window.location.href = "<%=basePath%>pc?op=index";

               }else if(info=="ERROR"){
                   $("#in").val("");
                   $("#name").val("");
                   $("#pwd").val("");
                   changeImg();
                   layer.msg("账号或者密码错误",{icon:2});//把鼠标焦点聚焦到某个元素上

               }else {
                   $("#in").val("");
                   changeImg();
                   layer.msg("验证码输入错误",{icon:2});
               }
            },
            //失败的回调函数
            error: function () {
                layer.msg("ajax请求失败",{icon:2})
            }
        })

    }
    $(function () {
        $.ajax({
            type:"post",
            url:"<%=basePath%>uc",
            dataType:"text",
            data:{
               op:"queryCookieByAjax"
            },
            success:function (info) {
                var user = JSON.parse(info);
                $("#name").val(user.username);
                $("#pwd").val(user.password);
                if(user.username.length>0){
                    $("#check").prop("checked",true);
                }
            },
            error:function () {
                layer.msg("验证失败",{icon:2});
            }


        })
    })

</script>

</body>

</html>
