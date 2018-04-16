<#-- 引入布局模板文件 -->
<#include "layout/defaultLayout.ftl"/>
<#-- 调用布局指令 -->
<#-- 头部 -->
<@htmlHead title="这里是文章标题 - ${siteConfigDTO.webname}">
        <link rel="stylesheet" type="text/css" href="${basePath}/themes/${siteConfigDTO.webtheme}/css/post.css">
</@htmlHead>

<#-- 正文 -->
<@htmlBody>
        <h1 class="post-title">${post.postTitle}</h1>
        <p class="post-content">${post.postContent}</p>
</@htmlBody>

<#-- 底部 -->
<@htmlFoot>
        <script type="text/javascript" src="${basePath}/themes/${siteConfigDTO.webtheme}/js/post.js"></script>
</@htmlFoot>
