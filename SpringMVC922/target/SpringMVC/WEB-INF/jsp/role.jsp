<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>

<!DOCTYPE html>
<html>
<script src="${pageContext.request.contextPath}/static/js/jquery-3.1.1.min.js"></script>
<head>
    <meta charset="utf-8">
    <title>Title</title>
</head>
<body>
<div style="text-align: center">

        <label>角色名</label>
        <input type="text" id="roleName" name="roleName" placeholder="请输入用户姓名"><br>
        <label>用户管理</label>
        <input type="radio" name="permission" value="用户管理" checked="checked"><br>
        <label>学生管理</label>
        <input type="radio" name="permission" value="学生管理"><br>
        <input type="submit" value="提交" onclick="submit()">

</div>
<script>
    <%--function submit() {--%>
    <%--    var permissionName = $("input[name='permission']").val();--%>
    <%--    var permission ={permissionId:1,permissionName:permissionName};--%>
    <%--    var roleName =$("#roleName").val();--%>
    <%--    var rolevalue = {roleName:roleName,permission:permission}--%>
    <%--    var roleString = JSON.stringify(rolevalue)--%>
    <%--    $.ajax({--%>
    <%--        url:"${pageContext.request.contextPath}/role/toRegister",--%>
    <%--        type:'post',--%>
    <%--        contentType:'application/json',--%>
    <%--        data:roleString,--%>
    <%--        processData:false,--%>
    <%--        dataType:'json',--%>
    <%--        success:function (result) {--%>
    <%--            alert(JSON.stringify(result));--%>

    <%--        },--%>
    <%--        error:function (err) {--%>
    <%--            alert(JSON.stringify(err));--%>

    <%--        }--%>
    <%--    });--%>
    <%--}--%>
</script>
</body>
</html>
