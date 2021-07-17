<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <meta name="renderer" content="webkit">
    <title>登录</title>
    <link rel="stylesheet" href="css/pintuer.css">
    <link rel="stylesheet" href="css/admin.css">
    <script src="js/jquery.js"></script>
    <script type="text/javascript" src="layer/2.4/layer.js"></script>
    <link rel="stylesheet" href="layer/2.4/skin/layer.css">

</head>
<body>
<div class="bg"></div>
<div class="container">
    <div class="line bouncein">
        <div class="xs6 xm4 xs3-move xm4-move">
            <div style="height:150px;"></div>
            <div class="media media-y margin-big-bottom">
            </div>
            <form action="UserServlet" method="post">
                <input type="hidden" name="method" value="login">
                <div class="panel loginbox">
                    <div class="text-center margin-big padding-big-top"><h1>后台管理中心</h1></div>
                    <div class="panel-body" style="padding:30px; padding-bottom:10px; padding-top:10px;">
                        <div class="form-group">
                            <div class="field field-icon-right">
                                <input id="name" type="text" class="input input-big" name="username" placeholder="登录账号" data-validate="required:请填写账号" />
                                <span class="icon icon-user margin-small"></span>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="field field-icon-right">
                                <input id="password" type="password" class="input input-big" name="password" placeholder="登录密码" data-validate="required:请填写密码" />
                                <span class="icon icon-key margin-small"></span>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="field">
                                <input type="text" class="input input-big" name="code" id="code" placeholder="填写下方的验证码"  /><br>
                                <img id="im" src="http://localhost:8080/EducationSystem/gc"> <a id="kanbuq" href="javascript:changeImg();">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;看不清，换一张</a>
                            </div>
                        </div>

                        <a href="register.jsp">还没注册？点击注册</a>
                        <span style="color: red">${msg}</span>

                    </div>
                    <div style="padding:30px;"><input type="button" onclick="login()" class="button button-block bg-main text-big input-big" value="登录"></div>
                </div>
            </form>
        </div>
    </div>
</div>

</body>
<script>
    var i = 0;
    function changeImg() {
        $("#im").attr("src","http://localhost:8080/EducationSystem/gc?i="+i);
        i++;
    }
    function login() {
        var code = $("#code").val();
        var username = $("#name").val();
        var password = $("#password").val();

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
            $("#password").focus();
            return;
        }

        $.ajax({
            type:"post",
            url:"<%=basePath%>sc",
            dataType:"text",
            data:{
                code:code,
                username:username,
                password:password,
                op:"login"
            },
            success:function (info) {
                if(info=="SUCCESS"){
                    //alert("登录成功")
                   window.location.href = "<%=basePath%>pc?op=index";

                }else if(info=="ERROR"){
                    $("#code").val("");
                    $("#name").val("");
                    $("#password").val("");
                    changeImg();
                    layer.msg("账号或者密码错误",{icon:2});

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
</script>



</html>
