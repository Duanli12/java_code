<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Title</title>
</head>
<body>
<form method="post" action="${pageContext.request.contextPath}/user/register">
    <label>用户名</label>
    <input type="text" name="name" placeholder="请输入用户名"><br>
    <label>账号</label>
    <input type="text" name="username" placeholder="请输入用户账号"><br>
    <label>密码</label>
    <input type="text" name="password" placeholder="请输入密码"><br>
    <input type="submit" value="提交">
</form>

</body>
</html>
