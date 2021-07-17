<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Title</title>
</head>
<body>
    <shiro:authenticated>认证的用户才能看到</shiro:authenticated>
    <shiro:user>以前认证过的用户才能看到</shiro:user>
    <shiro:hasRole name="管理员">管理员才能看到</shiro:hasRole>
    <shiro:hasRole name="普通用户">普通用户才能看到</shiro:hasRole>
    <shiro:guest>游客才能看到</shiro:guest>

</body>
</html>
