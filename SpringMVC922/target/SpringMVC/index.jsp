<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" isELIgnored="false" %>
<!DOCTYPE html>
<html>
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/pintuer.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/bootstrap.css"/>
<script src="${pageContext.request.contextPath}/static/js/jquery-3.1.1.min.js"></script>
<script src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>
<head>
    <meta charset="utf-8">
    <title>Title</title>
</head>
<body>
<%--<form method="post" action="${pageContext.request.contextPath}/user/register">
    <label>用户名</label>
    <input type="text" name="name" placeholder="请输入用户名"><br>
    <label>账号</label>
    <input type="text" name="username" placeholder="请输入用户账号"><br>
    <label>密码</label>
    <input type="text" name="password" placeholder="请输入密码"><br>
    <input type="submit" value="提交">
</form>--%>
<div class="margin" align="center">
    <h3>用户登录</h3>
    <input class="input" style="width: 300px" type="text" id="username" placeholder="请输入用户名">
    <input class="input margin-top" style="width: 300px" type="password" id="password" placeholder="请输入密码">
    <div class="margin">
        <button class="button" onclick="logout()">登出</button> <button class="button" onclick="login()">登录</button>
    </div>
    <div class="margin"><span class="text-red" id="infoSpan"></span> </div>
</div>

<script>
    function login() {
        var username = $("#username").val();
        var password = $("#password").val();
        $.ajax({
            url:"${pageContext.request.contextPath}/user/login",
            type:'post',
            contentType:'application/x-www-form-urlencoded',
            data:{"username":username,"password":password},
            dataType:'json',
            success:function (result) {
                if (result.code == 0){

                }
                $("#infoSpan").html(result.message);
            },
            error:function (err) {
                console.log(JSON.stringify(err));


            }
        })


    }
    function logout() {

        $.ajax({
            url:"${pageContext.request.contextPath}/user/logout",
            type:'get',
            contentType:'application/x-www-form-urlencoded',
            dataType:'json',
            success:function (result) {
                if (result.code == 0){

                }
                $("#infoSpan").html(result.message);
            },
            error:function (err) {
                console.log(JSON.stringify(err));


            }
        })
    }
</script>

</body>
</html>
