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
<#-- 定义根路径 -->
<#assign basePath=request.contextPath />
<#macro htmlHead title charset="utf-8" lang="zh-CN">
<!DOCTYPE html>
<html lang="zh-CN">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=${charset}" />
        <meta http-equiv="Content-Language" content="${lang}" />
        <!-- 禁止浏览器缓存 -->
        <meta http-equiv="Cache-Control" content="no-transform">
        <meta http-equiv="Cache-Control" content="no-siteapp">
        <meta name="renderer" content="webkit">
        <meta name="keywords" content="${siteConfigDTO.keywords}">
        <meta name="description" content="${siteConfigDTO.description}">
        <!-- viewport可以控制用户在手机上两个手指缩放的行为范围，这行代码的意思是宽度为设备宽度，初始缩放为1，最大缩放也是1倍，用户不能缩放 -->
        <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no">
        <!-- apple这行是告诉iOS这是个应用，就不会像网页一样可以滚动到顶部回弹一下的效果，就是可以看到苹果默认背景灰色部分的那个效果 -->
        <meta name="apple-mobile-web-app-capable" content="yes" />
        <!-- 百度联盟 -->
        <meta name="baidu_union_verify" content="bd76270fb5a06c40f25bd1703d7d220b">
        <title>${title}</title>
        <link rel="shortcut icon" href="${basePath}/themes/${siteConfigDTO.webtheme}/favicon.ico">
        <link rel="apple-touch-icon" sizes="114x114" href="${basePath}/themes/${siteConfigDTO.webtheme}/favicon.ico">
        <link rel="profile" href="http://gmpg.org/xfn/11">
        <!-- fontico -->
        <link rel="stylesheet" type="text/css" href="${basePath}/themes/${siteConfigDTO.webtheme}/lib/fontico/fontico.css">
        <!-- artDialog -->
        <link rel="stylesheet" type="text/css" href="${basePath}/themes/${siteConfigDTO.webtheme}/lib/artDialog/css/ui-dialog.css" />
        <!-- pace -->
        <link rel="stylesheet" type="text/css" href="${basePath}/themes/${siteConfigDTO.webtheme}/lib/pace/css/pace.css">
        <!-- 自定义页面头部开始 -->
        <link rel="stylesheet" type="text/css" href="${basePath}/themes/${siteConfigDTO.webtheme}/default.css?v=201804192254">
        <#nested>
        <!-- 自定义页面头部结束 -->
    </head>
</#macro>

<#macro htmlBody>
    <body>
        <!-- 进度条开始-->
        <div class="pace  pace-inactive">
            <div class="pace-progress" data-progress-text="100%" data-progress="99" style="transform: translate3d(100%, 0px, 0px);">
                <div class="pace-progress-inner"></div>
            </div>
            <div class="pace-activity"></div>
        </div>
        <!-- 进度条结束-->
        <div id="page" class="hfeed site">
            <!-- 头部内容开始 -->
            <#include "header.ftl">
            <!-- 头部内容结束 -->
            <!-- 自定义正文开始 -->
            <#nested>
            <!-- 自定义正文结束 -->
            <!-- 底部开始 -->
            <#include "footer.ftl">

            <!-- 底部结束 -->
        </div>
</#macro>
<#macro htmlFoot>
        <!-- jquery -->
        <script type="text/javascript" src="${basePath}/webjars/jquery/jquery.min.js"></script>
        <!-- artDialog -->
        <script type="text/javascript" src="${basePath}/themes/${siteConfigDTO.webtheme}/lib/artDialog/dist/dialog-plus-min.js"></script>
        <!-- pace -->
        <script type="text/javascript" src="${basePath}/themes/${siteConfigDTO.webtheme}/lib/pace/js/pace.min.js"></script>
        <!-- jquery.qrcode -->
        <script type="text/javascript" src="${basePath}/themes/${siteConfigDTO.webtheme}/lib/qrcode/jquery.qrcode.min.js"></script>
	    <!-- cookie -->
		<script src="${request.contextPath}/themes/${siteConfigDTO.webtheme}/lib/cookie/jquery.cookie.js"></script>
        <script type="text/javascript" src="${basePath}/themes/${siteConfigDTO.webtheme}/lib/function.js"></script>
        <script type="text/javascript" src="${basePath}/themes/${siteConfigDTO.webtheme}/default.js?v=201804192315"></script>
        <!-- 自定义底部开始 -->
        <#nested>
        <!-- 自定义底部结束 -->
        <script type="text/javascript">
         $(document).ready(function() {
             if(!+[1, ]) {
                 present = "table";
             } else {
                 present = "canvas";
             }
             $('#output').qrcode({
                 render: present,
                 text: window.location.href,
                 width: "150",
                 height: "150"
             });
         });
        </script>
    </body>
</html>
</#macro>
