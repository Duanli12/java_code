<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Title</title>
</head>
<body>
    ${t}<br/>
<%-- 使用jstl标签里面fmt来格式化后台传来的Date类型--%>
<fmt:formatDate value="${t}" pattern="yyyy-MM-dd HH:mm:ss"/>
<a href="<%=basePath%>dt2?d=<fmt:formatDate value="${t}" pattern="yyyy-MM-dd HH:mm:ss"/>">把时间字符串传到后台</a>

</body>
</html>
