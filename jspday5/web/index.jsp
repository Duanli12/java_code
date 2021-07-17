<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title></title>
  </head>
  <%
    Object o = request.getAttribute("name");
    out.write(o+"");

  %>
  <body>
    <h1>${name}</h1>
    <h1>${name2}</h1>
    <h1>${age}</h1>
    <h1>${sex}</h1>
  </body>
</html>