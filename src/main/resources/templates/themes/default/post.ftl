<#-- 引入布局模板文件 -->
<#include "layout/defaultLayout.ftl"/>
<#-- 调用布局指令 -->
<#-- 头部 -->
<@htmlHead title="这里是文章标题 - ${siteConfigDTO.webname}">
        <link rel="stylesheet" href="${basePath}/themes/${siteConfigDTO.webtheme}/css/post.css">
</@htmlHead>

<#-- 正文 -->
<@htmlBody>
        <div>这里是页面内容</div>
</@htmlBody>
