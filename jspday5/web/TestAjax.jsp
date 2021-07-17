<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>

<!DOCTYPE html>
<%
    String path = request.getContextPath();
    //request.getScheme()获取
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<%--
学习ajax技术：局部刷新技术（属于前端技术）
 因为原生ajax 写法比较烦躁，所以此刻我使用jQuery封装的ajax 来使用
 --%>

<html>
<head>
    <meta charset="utf-8">
    <title>Title</title>
    <script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script>
</head>
<script>
    $.ajax({
        //设置请求方式
        type:"get",
        //设置请求的url
        url:"<%=basePath%>tt",
        //设置返回值类型
        dateType:"text",
        //发送数据（数据也是键值对的形式）
        data:{//在这里面name和age都是字符串
            name:"zs",
            age:"18"
        },
        //成功的回调函数（返回成功后，服务器的内容就赋值给info）
        success:function (info) {
            alert(info)

        },
        //失败的回调函数
        error:function () {
            alert("ajax请求失败！！！")
        }
    })
</script>
<body>


</body>
</html>
