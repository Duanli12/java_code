<%--
  Created by IntelliJ IDEA.
  User: MACHENIKE
  Date: 2020/9/3
  Time: 10:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%--jsp有四大作用域
application：类似Servlet的ServletContext 作用域：整个项目中都能访问 该对象和ServletContext是同一对象
session:类似Servlet的session  作用域：整个项目中都能访问  该对象和HttpSession是同一对象
request:类似Servlet的request  作用域：在一次请求内  该对象和HttpServletRequest是同一对象
page:作用域只在当前页面

--%>
<head>
    <title>Title</title>
    <%
        //page
        pageContext.setAttribute("name","关羽");

        //request
        request.setAttribute("name2","张飞");

        //session
        session.setAttribute("age",18);
        //System.out.println(session);
        //application
        application.setAttribute("sex","男");
        System.out.println(application);

    %>
</head>
<body>
    ${name}
    ${name2}
</body>
</html>
