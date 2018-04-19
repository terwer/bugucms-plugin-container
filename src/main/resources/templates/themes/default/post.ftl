<#-- 引入布局模板文件 -->
<#include "layout/defaultLayout.ftl"/>
<#-- 调用布局指令 -->
<#-- 头部 -->
<@htmlHead title="${post.postTitle} - ${siteConfigDTO.webname}">
        <!--
        // 1.Segmentfault.com默认的solarized_light风格
        https://apps.bdimg.com/libs/highlight.js/9.1.0/styles/solarized_light.min.css
        // 2.sublime主题风格
        https://apps.bdimg.com/libs/highlight.js/9.1.0/styles/monokai-sublime.min.css
        // 3.默认风格
        http://apps.bdimg.com/libs/highlight.js/9.1.0/styles/default.min.css
        -->
        <link rel="stylesheet" type="text/css" href="https://apps.bdimg.com/libs/highlight.js/9.1.0/styles/atelier-forest-light.min.css">
        <link rel="stylesheet" type="text/css" href="${basePath}/themes/${siteConfigDTO.webtheme}/css/post.css?v=201804192240">
</@htmlHead>

<#-- 正文 -->
<@htmlBody>
        <div class="post-title"><h1>${post.postTitle}</h1></div>
        <div class="post-content">${post.postContent}</div>
</@htmlBody>

<#-- 底部 -->
<@htmlFoot>
        <!-- hilightjs -->
        <script type="text/javascript" src="http://apps.bdimg.com/libs/highlight.js/9.1.0/highlight.min.js"></script>
        <script type="text/javascript" src="${basePath}/themes/${siteConfigDTO.webtheme}/js/post.js"></script>
</@htmlFoot>
