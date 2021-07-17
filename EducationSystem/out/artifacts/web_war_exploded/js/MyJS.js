//     1,qq号码：纯数字，不以0开头，5-11位     /^[1-9]\d{4,10}$/
//     2,手机号:开头为1，第二个字符是3,4,5,7,8之间，最后9个数字 /^[1][34578]\d{9}$
//     3,Email:  格式为： 数字字母下划线或-或.  @  数字字母下划线-    .  2到3位小写字母
//     /^[0-9a-zA-Z._-]+[@][0-9a-zA-Z_-]+[.][a-z]{2,3}$/
//     4,用户名. 字母数字下划线10位以内, 必须是字母开头
// /^[a-zA-Z][0-9a-zA-Z_]{0,9}$/
//     5,密码. 任意字符, 6-16位	 
// /^.{6,16}$/							
$(function () {
    $("input").change(function () {
        // 获取要添加提示信息的div
        myDiv = $(this).parent().parent().children("div.col-sm-4")
        myDiv2 = $(this).parent().parent()
        var id = $(this).attr("id");
        // id为inpUserName的input框触发change事件
        if (id == "inpUserName") {
            var username = $(this).val();
            var regex = /^[a-zA-Z]\w{0,9}$/
            if (regex.test(username)) {
                //$.post(url,要传输的数据，success(data),返回的数据类型
                $.post("UserServlet", {"method": "isUsernameRepeat", "username": username}, function success(data) {
                   console.log(data)
                    console.log(data.name)
                     if (data == "ok") {
                        myDiv.html(' <p class="glyphicon glyphicon-ok text-success"></p>')
                        s()
                    } else {
                        myDiv.html('<p class="glyphicon glyphicon-remove text-danger"></p> <span class="text-danger">抱歉，此用户名已被使用</span> ')
                        e()
                    }
                }, "text")
            } else {
                myDiv.html('<p class="glyphicon glyphicon-remove text-danger"></p> <span class="text-danger">字母数字下划线10为以内，必须字母开头</span> ')
                e()
            }
            // id为inpPWD的input框触发change事件
        } else if (id == "inpPWD") {
            var a = $(this).val();
            var regex = /^.{6,16}$/
            if (regex.test(a)) {
                myDiv.html(' <p class="glyphicon glyphicon-ok text-success"></p>')
                s()
            } else {
                myDiv.html(
                    '<p class="glyphicon glyphicon-remove text-danger"></p> <span class="text-danger">任意字符, 6-16位</span> '
                )
                e()
            }
        } else if (id == "inpPWD2") {
            var a = $(this).val();
            var b = $("#inpPWD").val();
            if (a == b) {
                myDiv.html(' <p class="glyphicon glyphicon-ok text-success"></p>')
                s()
            } else {
                myDiv.html(
                    '<p class="glyphicon glyphicon-remove text-danger"></p> <span class="text-danger">两次密码输入不一致</span> '
                )
                e()
            }
        } else if (id = "inpYZM") {
            var val = $("#inpYZM").val().toLowerCase();
            var num = show_num.join("");
            if (val == num) {
                myDiv.html(' <p class="glyphicon glyphicon-ok text-success"></p>')
                s()

            } else {
                myDiv.html(
                    '<p class="glyphicon glyphicon-remove text-danger"></p> <span class="text-danger">验证码错误</span> '
                )
                e()
            }
        }
    })
})

function s() {
    myDiv2.addClass("has-success")
    if (myDiv2.hasClass('has-error')) {
        myDiv2.removeClass('has-error')
    }
}

function e() {
    myDiv2.addClass("has-error")
    if (myDiv2.hasClass('has-success')) {
        myDiv2.removeClass('has-success')
    }
}

function go() {
    var b = false;
    var divs = $("div.form-group:not(:last)");
    for (var i = 0; i < divs.length; i++) {
        if (divs.eq(i).hasClass('has-success')) {
            b = true;
        } else {
            b = false;
            break;
        }
    }
    if (b) {
        $("from").submit()
    } else {
        alert("注册失败")
    }
}
