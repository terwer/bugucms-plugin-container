<!DOCTYPE html>
<html lang="zh-CN">

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="renderer" content="webkit">
		<meta name="keywords" content="${siteConfigDTO.keywords}">
		<meta name="description" content="${siteConfigDTO.description}">
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
		<title>注册 - ${siteConfigDTO.webname}</title>
		<link rel="shortcut icon" href="${request.contextPath}/themes/${siteConfigDTO.webtheme}/favicon.ico">
		<!--公共的样式-->
		<link rel="stylesheet" href="${request.contextPath}/css/globle.css">
		<!--页面自定义样式-->
		<link rel="stylesheet" href="${request.contextPath}/css/reg.css">
		<!-- bootstrap -->
		<link rel="stylesheet" href="${request.contextPath}/webjars/bootstrap/css/bootstrap.min.css">
	</head>

	<body>
		<div id="main" class="container-fluid">
			<div id="regHeader" class="text-center">
				<div class="row header-content">
					<div class="col-xs-12">
						<span class="logo-area"> 
						<a href="${request.contextPath}/reg" title="${siteConfigDTO.webname}"> 
							<span class="logo"></span>
						</a>
						</span>
						<span class="page-title"> 
							<a href="${request.contextPath}/reg" title="注册帐号">注册帐号</a>
						</span>
						<span class="switch-login"> <a href="${request.contextPath}/login" title="登录" target="_blank">登录</a></span>
					</div>
				</div>
			</div>

			<div id="regContent">
				<div class="reg-title">注册${siteConfigDTO.webname}帐号</div>
				<div class="uc-sign-up-wrapper uc-wrapper">
					<div class="uc-mod-sign-up uc-clearfix">
						<div class="uc-main">
							<div class="uc-tip-wrapper uc-global-error">
								<p class="uc-tip uc-tip-error">这里是错误提示</p>
							</div>

							<form id="registerForm" class="form-inline" role="form">
								<div class="input-group form-group">
									<span class="input-group-addon">手机号</span>
									<input type="text" class="form-control" name="account" maxlength="11" placeholder="请输入要注册的手机号" />
								</div>
								<span class="uc-tip">手机号错误</span>
								<div class="input-group form-group">
									<span class="input-group-addon">密&nbsp;&nbsp;&nbsp;码</span>
									<input type="password" class="form-control" name="password" placeholder="密码请设置8-20个字符" />
								</div>
								<span class="uc-tip">密码错误</span>
								<div class="input-group form-group">
									<span class="input-group-addon">验证码</span>
									<input type="text" class="form-control" name="captcha" placeholder="请输入验证码" maxlength="4">
									<span class="input-group-addon" style="padding: 0;margin: 0;">									
										<img alt="看不清" title="点击更换" style="height: 32px;" tabindex="99" src="${request.contextPath}/common/captcha" onclick="this.src=this.src+'?'; return false"/>
									</span>
								</div>
								<span class="uc-tip">验证码错误</span>
								<p class="uc-field uc-next-step uc-clearfix">
									<a href="javascript:void(0);" id="btnNext" class="btn btn-default">下一步</a>
								</p>
								<p>
									<span class="uc-sms-tips">短信验证码已发送至<label class="uc-sms-tips-mobile">15986685029</label></span>
								</p>
								<div class="input-group form-group">
									<input type="text" class="form-control" style="width:200px;display:inline;" name="smsCode" placeholder="请输入验证码" maxlength="4">
									<input id="getSmsToken" class="btn btn-danger" type="button" value="免费获取校验码"></p>
								</div>
								<p class="uc-field uc-field-submit">
									<input id="btnRegister" class="btn btn-success" type="button" value="立即注册"></p>
								<p class="uc-field uc-field-smscodetip"><span><a class="uc-link" href="${request.contextPath}/findpwd" target="_blank">短信验证码没收到？</a></span></p>
								<p class="uc-field uc-field-licence"><label><span>点击“下一步”，即表示您已同意并愿意遵守<a class="uc-link" href="javascript:void(0);">《${siteConfigDTO.webname}用户服务条款》</a></span></label></p>
								<p class="uc-login">已有帐号，
									<a href="${request.contextPath}/login" class="uc-link uc-link-login">立即登录</a>
								</p>
							</form>
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
						<p>恭喜！您已完成${siteConfigDTO.webname}帐号注册</p>
						<p>
							您可以用该帐号登录
							<a href="${request.contextPath}/" class="link" target="_blank">${siteConfigDTO.webname}</a>
						</p>
					</div>
				</div>
			</div>

			<div id="ft">
				Copyright©2011-<span id="currentYear">2018</span> ${siteConfigDTO.domain} All Rights Reserved ${siteConfigDTO.webname}
				<p>
					<a target="_blank" href="http://www.miibeian.gov.cn/">${siteConfigDTO.beianinfo}</a>
				</p>
			</div>

		</div>

		<!-- jquery -->
		<script type="text/javascript" src="${request.contextPath}/webjars/jquery/jquery.min.js"></script>
		<!-- bootstrap -->
		<script type="text/javascript" src="${request.contextPath}/webjars/bootstrap/js/bootstrap.min.js"></script>
		<!-- jquery-validate -->
		<script type="text/javascript" src="${request.contextPath}/themes/${siteConfigDTO.webtheme}/lib/jquery-validate/jquery.validate.min.js"></script>
		<script type="text/javascript" src="${request.contextPath}/themes/${siteConfigDTO.webtheme}/lib/jquery-validate/additional-methods.min.js"></script>
		<script type="text/javascript" src="${request.contextPath}/themes/${siteConfigDTO.webtheme}/lib/jquery-validate/jquery.validate.extend.js"></script>
		<script type="text/javascript" src="${request.contextPath}/themes/${siteConfigDTO.webtheme}/lib/jquery-validate/messages_zh.min.js"></script>
		<script type="text/javascript" src="${request.contextPath}/js/reg.js"></script>
		<script type="text/javascript">
		</script>
	</body>

</html>