<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>Title</title>
    </head>
    <body>
        <%
            request.setAttribute("code", "<script>alert('helle');</script>");
        %>
        <c:out value="${code}" escapeXml="false"></c:out>
        <c:out value="aaa"/>
        <%
            request.setAttribute("aaa", "翠花");
        %>
        <c:out value="${aaa}"/>
        <c:set var="a" value="123"/>
        <c:if test="${not empty a}">
            a是123
        </c:if>
        <c:if test="${empty b}">
            b为空
        </c:if>
        <c:if test="${2>1}">
            2>1是对的
        </c:if>
        <c:set var="score" value="-10"/>
        <c:choose>
            <c:when test="${score>100||score<0}">
                错误分数：${score}
            </c:when>
            <c:when test="${score>90}">
                A级
            </c:when>
            <c:when test="${score>80}">
                B级
            </c:when><c:when test="${score>60}">
              C级
            </c:when>
            <c:otherwise>
                不合格
            </c:otherwise>
        </c:choose>
    </body>
</html>