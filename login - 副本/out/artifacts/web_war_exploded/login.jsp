<!DOCTYPE html>
<html>
<head >
    <%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
    <title>登录界面</title>
    <style type="text/css">
        body{
            background-position: center;
            background-repeat: no-repeat;
        }
    </style>
</head>
<body>
<div style="text-align:center;margin-top:120px">
    <form action="LoginServlet" method="get">
        <table style="margin-left:40%">
            <tr>
                <td>用户名：</td>
                <td><input type="text" size="21" name="username"/></td>

            </tr>
            <tr>
                <td>密码：</td>
                <td><input type="text" name="password" size="21"/></td>
            </tr>

        </table>
        <input type="submit" value="登录"/>
        <input type="reset" value="重置"/>
    </form>
</div>
</body>
</html>
