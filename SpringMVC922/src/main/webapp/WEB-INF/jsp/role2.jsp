<%--
  Created by IntelliJ IDEA.
  User: zjc
  Date: 2020/9/18
  Time: 14:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<script src="${pageContext.request.contextPath}/static/js/jquery-3.1.1.min.js"></script>
<head>
    <title></title>
</head>
<body>
<div>
    <div style="text-align: center">

        <label>角色名</label>
        <input type="text" id="roleName" name="roleName" placeholder="请输入用户姓名"><br>
        <label>用户管理</label>
        <input type="radio" name="permission" value="用户管理" checked="checked"><br>
        <label>学生管理</label>
        <input type="radio" name="permission" value="学生管理"><br>
        <input type="submit" value="提交" onclick="submit()">

    </div>
</div>
<script>
    /* 无文件的提交*/
    function submit() {
        var name = $("#name").val();
        var username = $("#username").val();
        var password = $("#password").val();
        $.ajax({
            url:"${pageContext.request.contextPath}/user/ajaxregister",
            type: 'post',//创文件必须用post
            contentType: 'application/x-www-form-urlencoded',

             data: {name: name,username:username, password:password},

            dataType : 'json', //期望服务器返回一个json格式的字符串
            success: function (result) { //result是一个js对象
                alert(JSON.stringify(result));
            },
            error: function (err) {
                alert(JSON.stringify(err));
            }
        });
    }


</script>



</body>
</html>
