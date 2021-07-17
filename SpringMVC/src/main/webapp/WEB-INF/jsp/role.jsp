<%--
  Created by IntelliJ IDEA.
  User: duanli
  Date: 2020/9/21
  Time: 14:16
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
    <center>
    <label>角色名</label>
    <input type="text" id="roleName" name="roleName" placeholder="请输入用户姓名"><br>
    <label>管理员</label>
    <input type="radio" name="permission" value="管理员" ><br>
    <label>普通用户</label>
    <input type="radio" name="permission" value=" 普通用户" ><br>
    <input type="submit" value="提交" onclick="submit()">
    </center>
</div>
<script>
    function submit() {
        var permissionName = $("input[name='permission']:checked").val();
        var permission ={permissionId:1,permissionName:permissionName};
        var roleName =$("#roleName").val();
        var role = {roleName:roleName,permission:permission}
        var roleString = JSON.stringify(role)
        $.ajax({
            url:"${pageContext.request.contextPath}/role/toRegister",
            type:'post',
            contentType:'application/json',
            data:roleString,
            processData:false,
            dataType:'json',
            success:function (result) {
                alert(JSON.stringify(result));

            },
            error:function (err) {
                alert(JSON.stringify(err));

            }
        })
    }
</script>
</body>
</html>
