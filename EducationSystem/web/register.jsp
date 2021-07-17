<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title></title>
    <link rel="stylesheet" type="text/css" href="css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="css/style.css"/>
    <script src="js/jquery.js" type="text/javascript" charset="utf-8"></script>
    <script src="js/bootstrap.js" type="text/javascript" charset="utf-8"></script>


</head>


<body>

<div class="container img-rounded">
    <h1 >注册</h1>
    <form class="form-horizontal"  action="<%=basePath%>sc" method="post">
        <input type="hidden" name="method" value="register">
        <div class="form-group ">
            <label for="inpUserName" class="col-sm-2 control-label">用户名：</label>
            <div class="col-sm-6 ">
                <input type="text" class="form-control" id="inpUserName" placeholder="请输入用户名" name="username">
            </div>
            <div class="col-sm-4" id="d1"> </div>
        </div>

        <div class="form-group">
            <label for="inpPWD" class="col-sm-2 control-label">密码：</label>
            <div class="col-sm-6">
                <input type="text" class="form-control" id="inpPWD"  placeholder="请输入密码" name="password">
            </div>
            <div class="col-sm-4"></div>
        </div>

        <div class="form-group">
            <label for="inpPWD2" class="col-sm-2 control-label">确认密码：</label>
            <div class="col-sm-6">
                <input type="text" class="form-control" id="inpPWD2" placeholder="请再次输入密码">
            </div>
            <div class="col-sm-4"></div>
        </div>

        <div class="form-group">
            <label for="inpYZM" class="col-sm-2 control-label">验证码：</label>
            <div class="col-sm-3">
                <input type="text" class="form-control" id="inpYZM" placeholder="请输入验证码">

            </div>
            <div class="col-sm-3">
                 <a id="kanbuq" href="javascript:changeImg();"><img id="im" src="http://localhost:8080/EducationSystem/gc"></a>
            </div>
            <div class="col-sm-4"></div>
        </div>


        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="button" class="btn btn-default" onclick="go()">提交</button>
            </div>
        </div>
    </form>
</div>

<script>
    var i = 0;
    function changeImg() {
        $("#im").attr("src","http://localhost:8080/EducationSystem/gc?i="+i);
        i++;
    }
    function go() {
        var username = $("#inpUserName").val();
        var password = $("#inpPWD2").val();
        $.ajax({
            type:"post",
            url:"<%=basePath%>sc",
            dataType:"text",
            data:{
                username:username,
                password:password,
                op:"register"
            },
            success:function (info) {
                if (info=="SUCCESS"){
                    alert("注册成功！！！");
                    window.location.href = "<%=basePath%>pc?op=register";
                }else if(info=="ERROR"){
                    $("#inpUserName").val("");
                    $("#inpPWD").val("");
                    $("#inpPWD2").val("");
                    $("#inpYZM").val("");
                    changeImg();
                    alert("账户已存在！！！");
                }

            },
            error:function () {
                layer.msg("ajax请求失败",{icon:2})

            }

        })

    }

</script>
</body>
</html>
