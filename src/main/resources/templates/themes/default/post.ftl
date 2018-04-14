<#-- 引入布局模板文件 -->
<#include "layout/defaultLayout.ftl"/>
<#-- 调用布局指令 -->
<#-- 头部 -->
<@htmlHead title="这里是文章标题 - ${siteConfigDTO.webname}">
        <!-- pace -->
        <link rel="stylesheet" type="text/css" href="${basePath}/themes/${siteConfigDTO.webtheme}/css/pace.css">
        <link rel="stylesheet" href="${basePath}/themes/${siteConfigDTO.webtheme}/css/post.css">
</@htmlHead>

<#-- 正文 -->
<@htmlBody>
        <div class="pace  pace-inactive">
            <div class="pace-progress" data-progress-text="100%" data-progress="99"
                 style="transform: translate3d(100%, 0px, 0px);">
                <div class="pace-progress-inner"></div>
            </div>
            <div class="pace-activity"></div>
        </div>
        This is post page.${siteConfigDTO.webname}
        <script src="${request.contextPath}/themes/${siteConfigDTO.webtheme}/js/pace.min.js"></script>
</@htmlBody>
