// var consoleCSS = "color:red;-webkit-background-clip: text;font-size:1rem;";
// console.log('%c ☺ 感谢您访问远方的灯塔！', consoleCSS);
console.log('感谢您访问远方的灯塔！');

//退出登录
$(document).ready(function () {
    //显示欢迎语
    var hello = "早上好，";
    var Guest_Name = $.cookie('cookieAuthor');
    if (Guest_Name != null && Guest_Name != "null" && Guest_Name != "") {
        hello = Guest_Name + ' , ' + hello + ' 欢迎回来。';
    }
    $("#sayHello").html(hello);

    //延时器刷新时间
    setInterval("showTime()", 1000);

    // 退出
    $("#logout").on("click", function () {
        logout_ajax();
    });
});

$(document).keydown(function () {
    if (event.ctrlKey == true && event.keyCode == 83) {
        console.log("You enter Ctrl+S!");
        return false;
    }
});

function logout_ajax() {
    var url = "/user/logout";
    $.ajax({
        type: "post",
        url: url,
        data: {},
        dataType: "json",
        beforeSend: function () {
            showTopContentDialog("logout_loding", "<div class='dialog_loading'>正在退出中，请稍后...</div>", "退出提示", 400, 75);
        },
        complete: function () {
        },
        success: function (data) {
            //退出成功
            if (data.flag == "1") {
                top.dialog.list["logout_loding"].close().remove();
                showTopDialog("show_loading", "<div class='dialog_loading'>正在退出，请稍后...</div>", "温馨提示", 400, 95);
                setTimeout(function () {
                    top.dialog.list["login_loding_error"].close().remove();
                }, 1000);
                //退出成功，跳转到首页
                window.location.reload();
            } else {
                top.dialog.list["login_loding"].close().remove();
                showTopContentDialog("logout_loding_error", "<div class='dialog_error'>" + data.msg + "</div>", "退出出错", 400, 75, false);
                setTimeout(function () {
                    top.dialog.list["logout_loding_error"].close().remove();
                }, 1000);
            }
        },
        error: function () {
            top.dialog.list["logout_loding"].close().remove();
            showTopContentDialog("logout_loding_error", "<div class='dialog_error'>网络连接错误！</div>", "退出出错", 400, 75);
            setTimeout(function () {
                top.dialog.list["logout_loding_error"].close().remove();
            }, 1000);
        }
    });
    //必须有这个组织表单提交
    return false;
}

function sayHello() {
    today = new Date();
    var day;
    var date;
    var hello;
    hour = new Date().getHours();
    if (hour < 6) {
        hello = ' 凌晨好！ ';
    } else if (hour < 9) {
        hello = ' 早上好！';
    } else if (hour < 12) {
        hello = ' 上午好！';
    } else if (hour < 14) {
        hello = ' 中午好！ ';
    } else if (hour < 17) {
        hello = ' 下午好！ ';
    } else if (hour < 19) {
        hello = ' 傍晚好！';
    } else if (hour < 22) {
        hello = ' 晚上好！ ';
    } else {
        hello = '夜深了！ ';
    }
}

function showTime() {
    today = new Date();
    var tdate, year;
    var x = new Array("星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六");
    var MSIE = navigator.userAgent.indexOf("MSIE");
    if (MSIE != -1) {
        year = (today.getFullYear());
    } else {
        year = (today.getYear() + 1900);
    }
    tdate = year + "年" + (today.getMonth() + 1) + "月" + today.getDate() + "日" + " ";

    var h, m, s;
    h = today.getHours();
    m = today.getMinutes();
    s = today.getSeconds();
    if (h >= 1 && h <= 9) {
        h = "0" + h;
    }
    if (m >= 0 && m <= 9) {
        m = "0" + m;
    }

    if (s >= 0 && s <= 9) {
        s = "0" + s;
    }

    ttime = h + ":" + m + ":" + s + " ";
    var now = tdate + ttime + x[today.getDay()];
    //console.log("now:" + now);
    $("#nowTime").html(now);
}
