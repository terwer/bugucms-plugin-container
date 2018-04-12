<!DOCTYPE html>
<html lang="zh-CN">

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="renderer" content="webkit">
		<meta name="keywords" content="${siteConfigDTO.keywords}">
		<meta name="description" content="${siteConfigDTO.description}">
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
		<title>登录 - ${siteConfigDTO.webname}</title>
		<link rel="shortcut icon" href="${request.contextPath}/themes/${siteConfigDTO.webtheme}/favicon.ico">
		<!-- artDialog -->
		<link href="${request.contextPath}/themes/${siteConfigDTO.webtheme}/lib/artDialog/css/ui-dialog.css" rel="stylesheet" />
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
		</div>
		<!-- jquery -->
		<script type="text/javascript" src="${request.contextPath}/webjars/jquery/jquery.min.js"></script>
		<!-- artDialog -->
		<script src="${request.contextPath}/themes/${siteConfigDTO.webtheme}/lib/artDialog/dist/dialog-plus-min.js"></script>
		<script src="${request.contextPath}/themes/${siteConfigDTO.webtheme}/lib/function.js"></script>
		<script src="${request.contextPath}/js/login.js"></script>
	</body>

</html>