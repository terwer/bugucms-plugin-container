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
						<span class="switch-login"> <a href="${request.contextPath}/login" title="登录" target="_blank">登录</a></span>
						<a href="${request.contextPath}/reg" title=""> <span class="logo"></span></a> <span class="page-title"> <a href="${request.contextPath}/reg" title="注册帐号">注册帐号</a></span>
					</div>

				</div>
				<div class="reg-content">
					<div id="regWrap" style="display: block;">
						<div class="reg-title">注册${webname}帐号</div>
						<div class="uc-sign-up-wrapper uc-wrapper uc-page">
							<div class="uc-mod-sign-up uc-clearfix">
								<div class="uc-main">
									<div class="uc-tip-wrapper uc-global-error">
										<p class="uc-tip uc-tip-error"></p>
									</div>
									<form class="uc-form" method="post" action="${request.contextPath}/user/register">
										<div>
											<p class="uc-field uc-field-mobile uc-input-long">
												<span class="uc-fixIe6margin"><label class="uc-label"></label></span>
												<span class="uc-input-bg"><input class="uc-input uc-input-mobile" type="tel" name="account" data-name="mobile" maxlength="11" placeholder="请输入要注册的手机号"></span>
											</p>
											<span class="uc-tip"></span>
											<p class="uc-field uc-field-password uc-input-long">
												<span class="uc-fixIe6margin"><label class="uc-label"></label></span>
												<span class="uc-input-bg"><input class="uc-input uc-input-password" type="password" name="password" placeholder="密码请设置8-20个字符" maxlength="20"></span>
											</p>
											<span class="uc-tip"></span>
											<p class="uc-field uc-field-captcha uc-input-short" style="display: block;"><img class="uc-captcha-img uc-captcha-change" alt="验证码" title="点击更换" tabindex="99" src="${request.contextPath}/user/captcha">
												<span class="uc-input-bg"><input class="uc-input uc-input-captcha" type="text" name="captcha" maxlength="7" placeholder="请输入验证码" autocomplete="off"></span>
											</p>
											<span class="uc-tip"></span>
											<p class="uc-field uc-next-step uc-clearfix">
												<a href="#" class="uc-nextAndGet-sms-token">下一步</a>
											</p>
											<p class="uc-field uc-field-sms-token uc-input-middle uc-clearfix">
												<span class="uc-sms-tips">短信验证码已发送至<label class="uc-sms-tips-mobile"></label></span>
												<a href="#" class="uc-get-sms-token">免费获取校验码</a>
												<span class="uc-input-bg">
                                            <input class="uc-input uc-input-sms-token" type="text" name="smscode" data-name="smsToken" placeholder="请输入短信验证码" maxlength="6"></span>
											</p>
										</div>
										<p class="uc-field uc-field-submit"><input class="uc-button uc-button-sign-up" type="submit" value="立即注册"></p>
										<p class="uc-field uc-field-smscodetip"><span><a class="uc-link" href="${request.contextPath}/findpwd" target="_blank">短信验证码没收到？</a></span></p>
										<p class="uc-field uc-field-licence"><label><span>点击“下一步”，即表示您已同意并愿意遵守<a class="uc-link" href="#" target="_blank">《${webname}用户服务条款》</a></span></label></p>
<p class="uc-login">已有帐号，
											<a href="${request.contextPath}/login" class="uc-link uc-link-login">立即登录</a>
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
						<div class="uc-sign-up-succeed">
							<p>
								<span class="reg-succeed-icon"></span>
							</p>
							<p>恭喜！您已完成${webname}帐号注册</p>
							<p>
								您可以用该帐号登录
								<a href="${request.contextPath}/" class="link" target="_blank">${webname}</a>
							</p>
						</div>
					</div>
				</div>
			</div>
			<div style="padding: 10px;"></div>
			<div id="ft">
				Copyright©2011-<span id="currentYear">2018</span> ${domain} All Rights Reserved ${webname}
				<p>
					<a target="_blank" href="http://www.miibeian.gov.cn/">${beianinfo}</a>
				</p>

			</div>
		</div>
		<!-- jquery -->
		<script type="text/javascript" src="${request.contextPath}/webjars/jquery/1.10.1/jquery.js"></script>
		<script type="text/javascript" src="${request.contextPath}/js/uc_reg.js"></script>
		<script type="text/javascript">
		</script>
	</body>