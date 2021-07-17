<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<!DOCTYPE html>
<html>
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/pintuer.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/bootstrap.css"/>
<script src="${pageContext.request.contextPath}/static/js/jquery-3.1.1.min.js"></script>
<script src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>


<head>
    <meta charset="utf-8">
    <title>用户信息管理</title>
</head>
<body>
<div>
    <button class="button" onclick="addUser()">新增用户</button>
    <select  id="findCondition">
        <option value="name">姓名</option>
        <option value="address">地址</option>
        <option value="phone">电话</option>
    </select>
    <input type="text" id="addressValue"/>
    <button type="button" onclick="findByAddressUser()">搜索</button>
</div>
<div class="margin">
    <table class="table">
        <thead>
        <tr>
            <td>编号</td>
            <td>姓名</td>
            <td>性别</td>
            <td>住址</td>
            <td>电话</td>
            <td>邮箱</td>
            <td>操作</td>
        </tr>
        </thead>
        <tbody id="tb">

        </tbody>
    </table>
</div>
<%-- 分页--%>
<div class="margin">
    每页显示<select id="pageSizeSelect" onchange="onSelectChange()">
    <option value="2">2</option>
    <option value="5">5</option>
    <option value="10">10</option>
</select>条数据，
    当前是第<span id="currentPageSpan"></span>页 ，
    总共<span id="totalPageSpan"></span> 页
    <button class="button" onclick="firstPage()">首页</button>
    <button class="button" onclick="perPage()">上一页</button>
    <button class="button" onclick="nextPage()">下一页</button>
    <button class="button" onclick="lastPage()">尾页</button>
</div>

<%-- 这里的div做一个编辑用户信息的模态框--%>
<div class="modal" id="editModal">
    <%-- 对话模态框--%>
    <div class="modal-dialog">
        <%--加内容--%>
        <div class="modal-content">
            <div class="modal-header">
                <h4>用户信息编辑</h4>
            </div>
            <div class="modal-body">
                <input type="hidden" id="userIdInput">
                <label class="control-label">用户姓名</label>
                <input class="form-control" type="text" id="nameInput" placeholder="请输入用户姓名">
                <label class="control-label">性别</label>
                <select class="form-control" id="sexSelect">
                    <option value="0">男</option>
                    <option value="1">女</option>
                </select>
                <label class="control-label">住址</label>
                <input class="form-control" type="text" id="addressInput" placeholder="请输入用户住址">
                <label class="control-label">电话</label>
                <input class="form-control" type="text" id="phoneInput" placeholder="请输入用户电话">
                <label class="control-label">邮箱</label>
                <input class="form-control" type="text" id="emailInput" placeholder="请输入用户邮箱">
                <label class="control-label">用户账号</label>
                <input class="form-control" type="text" id="usernameInput" placeholder="请输入用户账号">
                <label class="control-label">密码</label>
                <input class="form-control" type="password" id="passwordInput" placeholder="请输入用户住址">
            </div>
            <div class="modal-footer">
                <button class="button" data-dismiss="modal">取消</button>
                <button class="button" onclick="editConfirm()">确定</button>
            </div>
        </div>
    </div>
</div>


<script>
    //当前页码
    var currentPage = 1;
    //每页的记录个数
    var pageSize = 2;
    //总共有多少页
    var totalPage;

    $(function () {
        /* loadList();*/
        loadListByPage();

    });

    //用户修改的每页的记录数
    function onSelectChange() {
        pageSize = $("#pageSizeSelect").val();
        loadListByPage();
    }

    //首页的回调函数
    function firstPage() {
        currentPage = 1;
        loadListByPage();
    }

    //上一页的回调函数
    function perPage() {
        currentPage--;
        if (currentPage < 1) {
            currentPage = 1;
        }
        loadListByPage();

    }

    //下一页的回调函数
    function nextPage() {
        currentPage++;
        if (currentPage > totalPage) {
            currentPage = totalPage;
        }
        loadListByPage();
    }

    //尾页的回调函数
    function lastPage() {
        currentPage = totalPage;
        loadListByPage();
    }

    function showList(userList) {
        console.log(JSON.stringify(userList));
        var html = '';
        //遍历userList数组
        for (var i = 0; i < userList.length; i++) {
            var item = userList[i];
            console.log(JSON.stringify(item))
            html += "<tr>";
            html += "<td>" + item.userId + "</td>";//显示用户ID
            html += "<td>" + item.name + "</td>";//显示用户姓名
            if (item.sex == 1) {
                html += "<td>女</td>";
            } else {
                html += "<td>男</td>";
            }
            html += "<td>" + item.address + "</td>";
            html += "<td>" + item.phone + "</td>";
            html += "<td>" + item.email + "</td>";
            html += "<td><button class='button' onclick='getUserDetail(\"" + item.userId + "\")'>编辑" +
                "</button><button class='button' onclick='deleteOneUser(\"" + item.userId + "\")'>删除</button></td>";
            html += "</tr>";
        }
        $("#tb").html(html);

    }

    //加载用户列表数据
    function loadList() {
        $.ajax({
            url: "${pageContext.request.contextPath}/user/getList",
            type: 'get',
            dataType: 'json',
            success: function (result) {
                if (result.code == 0) {
                    showList(result.userList);
                }

            },
            error: function (err) {
                alert(JSON.stringify(err));
            }
        })

    }

    //分页加载用户列表数据
    function loadListByPage() {
        $.ajax({
            url: "${pageContext.request.contextPath}/user/getListByPage",
            type: 'get',
            data: {"currentPage": currentPage, "pageSize": pageSize},
            dataType: 'json',
            success: function (result) {
                if (result.code == 0) {
                    showList(result.userList);
                    totalPage = result.totalPage;
                    $("#totalPageSpan").html(totalPage);
                    $("#currentPageSpan").html(currentPage);
                }

            },
            error: function (err) {
                alert(JSON.stringify(err));
            }
        })

    }

    function addUser() {
        //显示用户添加的模态款
       /* $("#editModal").modal("show");*/
        showEditModal();

    }

    //获取用户的详细信息
    function getUserDetail(userId) {
        $.ajax({
            url: "${pageContext.request.contextPath}/user/getOne",
            type: 'get',
            data: {"userId": userId},//传递用户id
            dataType: 'json',
            success: function (result) {
                if (result.code == 0) {
                    showEditModal(result.user);
                } else {
                    alert(result.message);
                }

            },
            error: function (err) {
                alert(JSON.stringify(err));
            }
        })

    }

    function showEditModal(user) {
        if (user!=null){
            $("#userIdInput").val(user.userId);//传递userId
            $("#nameInput").val(user.name);//编辑用户姓名
            $("#sexSelect").val(user.sex);//根据sex的值选中select的option
            $("#addressInput").val(user.address);
            $("#phoneInput").val(user.phone);
            $("#emailInput").val(user.email);
            $("#usernameInput").val(user.username);
            $("#passwordInput").val(user.password);
            $("#usernameInput").attr("disabled", "disabled");//使用户名不能修改
            $("#editModal").modal("show");
        }else {
            //数据归0
            $("#userIdInput").val(0);//传递userId
            $("#nameInput").val("");//编辑用户姓名
            $("#sexSelect").val(0);//根据sex的值选中select的option
            $("#addressInput").val("");
            $("#phoneInput").val("");
            $("#emailInput").val("");
            $("#usernameInput").val("");
            $("#passwordInput").val("");
            $("#usernameInput").removeAttr("disabled");//去掉输入框的禁用状态

            $("#editModal").modal("show");
        }
    }

    function editConfirm() {
        var userId = $("#userIdInput").val();
        var name = $("#nameInput").val();
        var sex = $("#sexSelect").val();
        var address = $("#addressInput").val();
        var phone = $("#phoneInput").val();
        var email = $("#emailInput").val();
        var username = $("#usernameInput").val();
        var password = $("#passwordInput").val();
        var data = {userId:userId,name:name,sex:sex,address:address,
            phone:phone,email:email,username:username,password:password}
        $.ajax({
            url: "${pageContext.request.contextPath}/user/edit",
            type: 'post',//使用post提交大量数据
            contentType:'application/x-www-form-urlencoded',
            data:data,//传递用户编辑后的信息
            dataType: 'json',
            success: function (result) {
                if (result.code == 0) {
                    //再次去分页加载数据显示到页面上
                    $("#editModal").modal("hide");//关闭模态款
                    loadListByPage();

                } else {
                    alert(result.message);
                }

            },
            error: function (err) {
                alert(JSON.stringify(err));
            }
        })


    }
    
    function deleteOneUser(userId) {
        $.ajax({
            url: "${pageContext.request.contextPath}/user/delete",
            type: 'get',//使用post提交大量数据
            contentType:'application/x-www-form-urlencoded',
            data:{"userId":userId},//传递用户编辑后的信息
            dataType: 'json',
            success: function (result) {
                if (result.code == 0) {
                    //再次去分页加载数据显示到页面上
                    loadListByPage();

                } else {
                    alert(result.message);
                }
                alert(result.message);

            },
            error: function (err) {
                alert(JSON.stringify(err));
            }
        })


    }
    function findByAddressUser() {

        var condition = $("#findCondition").val();
        var address = $("#addressValue").val();
        $.ajax({
            url: "${pageContext.request.contextPath}/user/search",
            type: 'get',
            data:{"column":condition,"value":address},
            dataType: 'json',
            success: function (result) {
                if (result.code == 0) {
                    showList(result.userList)

                }else {
                    alert(result.message);
                }
            },
            error: function (err) {
                alert(JSON.stringify(err));
            }
        })



    }


</script>
</body>
</html>
