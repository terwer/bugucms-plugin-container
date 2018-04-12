<!DOCTYPE html>
<html lang="zh-CN">

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="renderer" content="webkit">
		<meta name="keywords" content="${siteConfigDTO.keywords}">
		<meta name="description" content="${siteConfigDTO.description}">
		<!-- viewport可以控制用户在手机上两个手指缩放的行为范围，这行代码的意思是宽度为设备宽度，初始缩放为1，最大缩放也是1倍，用户不能缩放 -->
		<meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no">
		<!-- apple这行是告诉iOS这是个应用，就不会像网页一样可以滚动到顶部回弹一下的效果，就是可以看到苹果默认背景灰色部分的那个效果 -->
		<meta name="apple-mobile-web-app-capable" content="yes" />
		<title>登录 - ${siteConfigDTO.webname}</title>
		<link rel="shortcut icon" href="${request.contextPath}/themes/${siteConfigDTO.webtheme}/favicon.ico">
		<!-- artDialog -->
		<link rel="stylesheet" href="${request.contextPath}/themes/${siteConfigDTO.webtheme}/lib/artDialog/css/ui-dialog.css" />
		<!-- poshytip -->
		<link href="${request.contextPath}/themes/${siteConfigDTO.webtheme}/lib/poshytip/tip-yellow/tip-yellow.css" rel="stylesheet" />
		<link rel="stylesheet" href="${request.contextPath}/css/main.css">
		<link rel="stylesheet" href="${request.contextPath}/css/login.css">
	</head>

	<body>
		<div id="main" class="container-fluid">
			<!--固定顶部部分开始-->
			<div class="header clearfix">
				<a href="javascript:void(0);" class="current-nav" style="display: block;"><em class="menu-more-ico"></em><span id="g_mod_name">${siteConfigDTO.webname}</span></a>
				<div class="vh-avator-area">
					<div class="gl-lang-container">
						<span class="help-center" id="g_help_center" onclick="show_help(this);">帮助中心</span>
					</div>
				</div>
			</div>
			<!--固定顶部部分结束-->
			<!-- 登录界面开始 -->
			<div class="layout-login">
				<div class="book-container">
					<div>
						<div class="logo-area">
							<img src="${request.contextPath}/img/logo-uc.png" alt="${siteConfigDTO.webname}">
							<p class="logo-intro" id="logo-intro">${siteConfigDTO.webslogen}</p>
						</div>
						<div class="form-container" id="form_container">
							<div class="ng-form-area show-place" id="form-area">
								<form id="loginForm" action="javascript:void(0);" onsubmit="return false;">
									<div class="shake-area" id="shake_area" style="z-index: 30;">
										<div class="enter-area">
											<input type="text" class="enter-item first-enter-item" id="account" name="account" placeholder="请输入要注册的手机号" >
										</div>
										<div class="enter-area" style="z-index: 20;">
											<input type="password" class="enter-item last-enter-item" id="password" name="password" placeholder="请输入密码">
										</div>
									</div>
									<input id="btnSubmit" class="button orange" type="submit" onsubmit="return false;" title="立即登录" value="立即登录">
								</form>
							</div>
						</div>
					</div>
				</div>
				<div id="other-tips">
					<a href="${request.contextPath}/findpwd" id="forget_pwd"><em class="forget-ico"></em><span>忘记密码？</span></a>
					<a href="${request.contextPath}/reg" id="cloud_register"><em class="register-ico"></em><span>注册</span></a>
				</div>
			</div>
			<!-- 登录界面介绍 -->
		</div>
		<!-- jquery -->
		<script type="text/javascript" src="${request.contextPath}/webjars/jquery/jquery.min.js"></script>
		<!-- artDialog -->
		<script src="${request.contextPath}/themes/${siteConfigDTO.webtheme}/lib/artDialog/dist/dialog-plus-min.js"></script>
		<!-- jquery-validate -->
		<script type="text/javascript" src="${request.contextPath}/themes/${siteConfigDTO.webtheme}/lib/jquery-validate/jquery.validate.min.js"></script>
		<script type="text/javascript" src="${request.contextPath}/themes/${siteConfigDTO.webtheme}/lib/jquery-validate/additional-methods.min.js"></script>
		<script type="text/javascript" src="${request.contextPath}/themes/${siteConfigDTO.webtheme}/lib/jquery-validate/jquery.validate.extend.js"></script>
		<script type="text/javascript" src="${request.contextPath}/themes/${siteConfigDTO.webtheme}/lib/jquery-validate/messages_zh.min.js"></script>
		<!-- poshytip -->
		<script type="text/javascript" src="${request.contextPath}/themes/${siteConfigDTO.webtheme}/lib/poshytip/jquery.poshytip.min.js"></script>
		<!-- cookie -->
		<script src="${request.contextPath}/themes/${siteConfigDTO.webtheme}/lib/cookie/jquery.cookie.js"></script>
		<script src="${request.contextPath}/themes/${siteConfigDTO.webtheme}/lib/function.js"></script>
		<script src="${request.contextPath}/js/login.js"></script>
	</body>

</html>