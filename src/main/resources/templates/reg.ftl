<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="renderer" content="webkit">
    <meta name="keywords" content="${keywords}">
    <meta name="description" content="${description}">
    <title>注册 - ${webname}</title>
    <!--公共的样式-->
    <link rel="stylesheet" href="${request.contextPath}/css/base.css">
    <!--页面自定义样式-->
    <link rel="stylesheet" href="${request.contextPath}/css/uc-reg.css">
</head>
<body class="login-page" style="height: 965px;">
<div id="doc" style="background: rgb(245, 245, 245);">
    <div class="reg-page">
        <div id="regHeader">
            <div class="header-content">
                <span class="switch-login"> <a href="${request.contextPath}/login" title="登录" target="_blank">登录</a></span> <a href="${request.contextPath}/reg" title=""> <span class="logo"></span></a> <span class="page-title"> <a href="${request.contextPath}/reg" title="注册帐号">注册帐号</a></span>
            </div>

        </div>
        <div class="reg-content">
            <div id="regWrap" style="display: block;">
                <div class="reg-title">注册特维博客帐号</div>
                <div class="quc-sign-up-wrapper quc-wrapper quc-page">
                    <div class="quc-mod-sign-up quc-clearfix">
                        <div class="quc-main">
                            <div class="quc-tip-wrapper quc-global-error">
                                <p class="quc-tip quc-tip-error"></p>
                            </div>
                            <form class="quc-form" method="post"
                                  action="${request.contextPath}/user/register">
                                <div>
                                    <p class="quc-field quc-field-mobile quc-input-long">
                                        <span class="quc-fixIe6margin"><label class="quc-label"></label></span><span class="quc-input-bg"><input class="quc-input quc-input-mobile" type="tel" name="account" data-name="mobile" maxlength="11" placeholder="请输入要注册的手机号"></span>
                                    </p>
                                    <span class="quc-tip"></span>
                                    <p class="quc-field quc-field-password quc-input-long">
                                        <span class="quc-fixIe6margin"><label class="quc-label"></label></span><span class="quc-input-bg"><input class="quc-input quc-input-password" type="password" name="password" placeholder="密码请设置8-20个字符" maxlength="20"></span>
                                    </p>
                                    <span class="quc-tip"></span>
                                    <p class="quc-field quc-field-captcha quc-input-short" style="display: block;">
                                        <img class="quc-captcha-img quc-captcha-change" alt="验证码" title="点击更换" tabindex="99" src="${request.contextPath}/user/captcha"><span class="quc-input-bg"><input class="quc-input quc-input-captcha" type="text" name="captcha" maxlength="7" placeholder="请输入验证码" autocomplete="off"></span>
                                    </p>
                                    <span class="quc-tip"></span>
                                    <p class="quc-field quc-next-step quc-clearfix">
                                        <a href="#" class="quc-nextAndGet-sms-token">下一步</a>
                                    </p>
                                    <p class="quc-field quc-field-sms-token quc-input-middle quc-clearfix">
                                        <span class="quc-sms-tips">短信验证码已发送至<label class="quc-sms-tips-mobile"></label></span><a href="#" class="quc-get-sms-token">免费获取校验码</a><span class="quc-input-bg"><input class="quc-input quc-input-sms-token" type="text" name="smscode" data-name="smsToken" placeholder="请输入短信验证码" maxlength="6"></span>
                                    </p>
                                </div>
                                <p class="quc-field quc-field-submit">
                                    <input class="quc-button quc-button-sign-up" type="submit" value="立即注册">
                                </p>
                                <p class="quc-field quc-field-smscodetip">
                                    <span><a class="quc-link" href="${request.contextPath}/findpwd" target="_blank">短信验证码没收到？</a></span>
                                </p>
                                <p class="quc-field quc-field-licence" style="display: none;">
                                    <label><span>点击“下一步”，即表示您已同意并愿意遵守<a class="quc-link" href="${request.contextPath}/" target="_blank">《${webname}服务条款》</a></span></label>
                                </p>
                                <p class="quc-login">
                                    <label><span>已有帐号，<a href="${request.contextPath}/login" class="quc-link quc-link-login">立即登录</a></span></label>
                                </p>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="reg-succeed">
            <div id="regWrap" style="display: block;">
                <div class="reg-succeed-title">注册成功</div>
                <div class="quc-sign-up-succeed">
                    <p>
                        <span class="reg-succeed-icon"></span>
                    </p>
                    <p>恭喜！您已完成${webname}帐号注册</p>
                    <p>
                        您可以用该帐号登录<a href="${request.contextPath}/" class="link"
                                    target="_blank">${webname}</a>
                    </p>
                </div>
            </div>
        </div>
    </div>
    <div style="padding: 10px;"></div>
    <div id="ft">
        Copyright©2011-<span id="currentYear">2018</span> ${domain} All
        Rights Reserved ${webname}
        <p>
            <a target="_blank" href="http://www.miibeian.gov.cn/">${beianinfo}</a>
        </p>

    </div>
</div>
<!--公共需要的全局变量-->
<script type="text/javascript">
    window.QHDomain = {
        'i360': 'http://i.360.cn',
        'login_http': 'http://login.360.cn',
        'login_https': 'https://login.360.cn',
        'js_login': 'http://js.login.360.cn',
        '1360': 'http://www.1360.com',
        'qihoo': 'http://www.qihoo.com',
        'so': 'http://www.so.com',
        'woxihuan': 'http://www.woxihuan.com',
        'yunpan': 'http://yunpan.360.cn',
        'help': 'http://help.360.cn',
        'baike': 'http://baike.360.cn',
        'rdLoginUrl': {
            "qihoo": "http:\/\/login.qihoo.com",
            "woxihuan": "http:\/\/login.woxihuan.com",
            "1360": "http:\/\/login.1360.com",
            "so": "http:\/\/login.so.com",
            "360pay": "http:\/\/login.360pay.cn",
            "leidian": "http:\/\/login.leidian.com",
            "qikoo": "http:\/\/login.qikoo.com"
        },
        'captcha': 'http://i.360.cn/reg/dogetcap?i360',
        'jifen': 'http://jifen.360.cn',
        'src': 'pcw_i360',
        'srcRoot': 'i360'
    };
    window.QHUserInfo = {
        'loginStatus': '',
        /*****1为登录，0为未登录*******/
        'figure': '',
        'qid': '',
        'userName': '',
        'nickName': '',
        'loginEmail': ''
    };
    window.quc_lang = '';
    window.qCrumb = "";
</script>
<!--公共 js-->
<script type="text/javascript" src="${request.contextPath}/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="${request.contextPath}/js/i360utils.js"></script>
<script>
    window.QUC = window.QUC || {};
    QUC.t3 = +new Date();
</script>
<script type="text/javascript" src="${request.contextPath}/js/5.0.3.js"></script>
<script>
    window.QUC = window.QUC || {};
    QUC.t5 = +new Date();
</script>
<script type="text/javascript" src="${request.contextPath}/js/base.js"></script>
<script type="text/javascript">
    $(function () {
        var protocol = location.protocol.slice(0, -1);
        QHPass.init('pcw_i360');
        QHPass.DEBUG = false;

        QHPass.setConfig('domainList', ["360pay.cn", "so.com", "360.cn",
            "360.com"]);

        QUCi360.utils.addRandomParam();
        $('.sign-out').on('click', function () {
            QHPass.signOut(protocol + "://i.360.cn");
        });
    });
</script>
<script type="text/javascript" src="${request.contextPath}/js/uc_reg.js"></script>
<script type="text/javascript">
    $(function () {
        var src = 'pcw_i360';
        var destUrl = "${request.contextPath}";
        $("#doc").css('background', '#f5f5f5');
        var $wrapper = $('.quc-sign-up-wrapper');
        QHPass.setConfig('src', src);
        QHPass.setConfig('signUp.hideNickname', true);
        QHPass.setConfig('signUp.hideUsername', true);
        QHPass.events.on('afterShow.*', function (e, el) {
            $(el).find(".quc-input-mobile").focus();
            $(el).find('.quc-left-bar').remove();
            $(el).find('.quc-login').hide();
        });

        var regType = QUCi360.utils.getParams().regtype;
        if (regType == "email") {
            $('.mobile-sign-up').removeClass('cur');
            $('.email-sign-up').addClass('cur');
            QHPass.setConfig('signUp.types', ['email', 'mobile']);
        }
        QHPass.signUp($wrapper, function () {
            $(".reg-content").hide();
            $(".reg-succeed").show();
            setTimeout(function () {
                location.href = destUrl;
            }, 5000);

        });
        /*QHPass.events.on('changeType.signUp', function(e, type) {
        switch(type) {
        case 'email':
        $('#quc-bd').removeClass('reg-wrapper2').addClass('reg-wrapper1');
        break;
        case 'mobile':
        $('#quc-bd').removeClass('reg-wrapper1').addClass('reg-wrapper2');
        break;
        }
        });

        $('.email-sign-up').on('click', function(e) {
        e.preventDefault();
        $('.mobile-sign-up').removeClass('cur');
        $(this).addClass('cur');
        QHPass.ui.signUp.changeType('email');
        });

        $('.mobile-sign-up').on('click', function(e) {
        e.preventDefault();
        $('.email-sign-up').removeClass('cur');
        $(this).addClass('cur');
        QHPass.ui.signUp.changeType('mobile');
        });*/

        $('.page-title').find('a').on('click', function (e) {
            e.preventDefault();
            location.reload();
        });
        /*
        $('.switch-login').find('a').attr(
                "href",
                QHDomain.i360 + "/login?src=" + src + "&destUrl="
                        + encodeURIComponent(destUrl));
        $(".logo").parent().attr(
                "href",
                QHDomain.i360 + "/reg?src=" + src + "&destUrl="
                        + encodeURIComponent(destUrl));
        */
    });
</script>
</body>