<!--
/*
____________  _________    _____________  ___      ______      ___  _________    _____________
| __________| / ______ /   |  __________ | \ \     | | \ \     | | | / ______  |  __________ |
    |  |    | |          |    _________   \ \    | |  \ \    | || | |          |  __________
   |  |     | | ______   |  /_________ |  \ \   | |   \ \   | |   | | ______   |  /_________|
  |  |      | |______ | |  | \ \          \ \  | |    \ \  | |    | |______ | |  | \ \
 |  |       | |________ |  |  \ \_______  \ \| |      \ \ |_|     | |________ |  |  \ \_______
|__|        /_/______ | | _|   \______/   \_\_|       \_ |_|      /_/______ | | _|   \______/
*/
-->
<!DOCTYPE html>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="renderer" content="webkit">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <meta http-equiv="Cache-Control" content="no-transform">
    <meta http-equiv="Cache-Control" content="no-siteapp">
    <title>${siteConfigDTO.webname} - ${siteConfigDTO.webslogen}</title>
    <meta name="keywords" content="${siteConfigDTO.keywords}">
    <meta name="description" content="${siteConfigDTO.description}">
    <link rel="shortcut icon" href="${request.contextPath}/themes/${siteConfigDTO.webtheme}/favicon.ico">
    <link rel="apple-touch-icon" sizes="114x114" href="${request.contextPath}/themes/${siteConfigDTO.webtheme}/favicon.ico">
    <link rel="profile" href="http://gmpg.org/xfn/11">
    <!-- pace -->
    <link rel="stylesheet" type="text/css" href="${request.contextPath}/themes/${siteConfigDTO.webtheme}/css/pace.css">
</head>

<body class="pace-done">
<div class="pace  pace-inactive">
    <div class="pace-progress" data-progress-text="100%" data-progress="99"
         style="transform: translate3d(100%, 0px, 0px);">
        <div class="pace-progress-inner"></div>
    </div>
    <div class="pace-activity"></div>
</div>
This is post page.${siteConfigDTO.webname}
<script src="${request.contextPath}/themes/${siteConfigDTO.webtheme}/js/pace.min.js"></script>
</body>
</html>