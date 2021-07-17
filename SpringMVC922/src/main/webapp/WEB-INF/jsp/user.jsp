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
    <label>用户名</label>
    <input type="text" id="name" name="name" placeholder="请输入用户姓名">
    <br>
    <label>账号</label>
    <input type="text" id="username" name="username" placeholder="请输入用户账号">
    <br>
    <label>密码</label>
    <input type="password" id="password" name="password" placeholder="请输入用户密码">
    <br>
    <input type="file" id="headImage" accept="image/*">
    <br>
    <input type="submit" value="提交" onclick="submit()">
</div>
<script>
    /* 无文件的提交*/
    function submit() {
        var name = $("#name").val();
        var username = $("#username").val();
        var password = $("#password").val();
        $.ajax({
            url:"${pageContext.request.contextPath}/users/ajaxregister",
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

    <script>
        /* 有文件的提交*/
        function submitfile() {
            var name = $("#name").val();
            var username = $("#username").val();
            var password = $("#password").val();
            var headImage =  $("#headImage")[0].files[0] //jquery方法拿headImage这个文件
            var formData = new FormData();
            formData.append("name",name);
            formData.append("username",username);
            formData.append("password",password);
            formData.append("headImage",headImage);
            $.ajax({
                url:"${pageContext.request.contextPath}/user/ajaxregisterfile",
                type: 'post',//创文件必须用post
                //contentType: 'application/x-www-form-urlencoded'，
                contentType:false,
               // data: {name: name,username:username, password:password},
                processData:false,//让ajax自己处理formData这个数据类型
                data:formData,
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
