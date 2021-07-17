<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    //request.getScheme()获取
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Title</title>
    <script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script>
    <script>
        $(function () {
            //模拟获取批量删除的所有Id，保存到数组
            var array1 = [1,2,3,4]
            $.ajax({
                type:'post',
                url:'<%=basePath%>testArray',
                dataType:'json',
                data:{
                    array1:array1
                },
                success:function (data) {
                   /* alert(data.info);
                    alert(data.info1);*/
                    for (var i = 0; i <data.length ; i++) {
                        console.log(data[i]);

                    }

                },
                error:function (data) {
                    console.log(data.msg);

                },
            });

        })
    </script>
</head>
<body>


</body>
</html>
