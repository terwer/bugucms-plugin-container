<#-- 引入布局模板文件 -->
<#include "layout/defaultLayout.ftl"/>
<#-- 调用布局指令 -->
<#-- 头部 -->
<@htmlHead title="这里是文章标题 - ${siteConfigDTO.webname}">
        <link rel="stylesheet" type="text/css" href="${basePath}/themes/${siteConfigDTO.webtheme}/css/index.css">
</@htmlHead>

<#-- 正文 -->
<@htmlBody>
        <div id="content" class="site-content">
           <div class="clear"></div>
           <div id="primary" class="content-area">
               <main id="main" class="site-main" role="main">
                   <!-- 幻灯 -->
                   <div id="slideshow" class="wow" data-wow-delay="0.3s">
                       <ul class="rslides callbacks" id="slider" style="max-width: 2000px;">
                           <li id="callbacks1_s1" class=""
                               style="display: block; float: none; position: absolute; opacity: 0; z-index: 1; transition: opacity 800ms ease-in-out;">
                               <a href="#" target="_blank" rel="bookmark">
                                   <img src="${siteConfigDTO.weburl}/uploads/images/20180101000000001.jpg"
                                        alt="2018，一个新的起点。"></a>
                               <p class="slider-caption">2018，一个新的起点。</p>
                           </li>
                           <li id="callbacks1_s2"
                               style="float: left; position: relative; opacity: 1; z-index: 2; display: list-item; transition: opacity 800ms ease-in-out;">
                               <a href="#" target="_blank" rel="bookmark">
                                   <img src="${siteConfigDTO.weburl}/uploads/images/20180401000000001.jpg"
                                        alt="远方的灯塔建站七年历程与心得总结"></a>
                               <p class="slider-caption">远方的灯塔建站七年历程与心得总结</p>
                           </li>
                       </ul>
                       <a href="#" class="callbacks_nav callbacks1_nav prev">
                           <i class="fa fa-angle-left"></i>
                       </a>
                       <a href="#" class="callbacks_nav callbacks1_nav next">
                           <i class="fa fa-angle-right"></i>
                       </a>
                       <ul class="callbacks_tabs callbacks1_tabs">
                           <li class="callbacks1_s1">
                               <a href="#" class="callbacks1_s1">1</a>
                           </li>
                           <li class="callbacks1_s2 callbacks_here">
                               <a href="#" class="callbacks1_s2">2</a>
                           </li>
                       </ul>
                   </div>
                   <!-- 置顶 -->
                   <!-- 最新文章 -->
                   <div class="wow" data-wow-delay="0.3s">
                       <article id="post-3094"
                                class="post-3094 post type-post status-publish format-standard hentry category-javascript tag-wepy tag-wepy-com-loadings tag-406 tag-407 zqu">
                           <figure class="thumbnail">
                               <div class="load">
                                   <a href="${siteConfigDTO.weburl}/3094.html"><img
                                           src="${request.contextPath}/themes/${siteConfigDTO.webtheme}/loading.png"
                                           data-original="" alt="基于Wepy开发的微信小程序Loading加载组件正式发布" style="display: block;"></a>
                               </div>
                               <span class="cat"><a
                                       href="${siteConfigDTO.weburl}/category/web/javascript/">JavaScript</a></span>
                           </figure>
                           <header class="entry-header">
                               <h2 class="entry-title"><a href="${siteConfigDTO.weburl}/3094.html"
                                                          rel="bookmark">基于Wepy开发的微信小程序Loading加载组件正式发布</a>
                               </h2></header>
                           <!-- .entry-header -->
                           <div class="entry-content">
                               <div class="archive-content"> 前言
                                   前几天在做微信小程序的时候，由于项目中部分地方需要让用户等待一段时间，如果是要为了用户体验，在其页面加上一段loading的动画会是一个不错的选择，不过无奈于使用的是wepy开发的，而这个在网上也没有找到合适的加载动画组件，于是就自己写了一个，现在将此loading动画加载组件分享给...
                               </div>
                               <span class="title-l"></span> <span class="new-icon">NEW</span> <span class="entry-meta"> <span
                                   class="date">2018年03月23日&nbsp;&nbsp;</span><span class="views">阅读 37</span><span
                                   class="comment">&nbsp;&nbsp;<a
                                   href="${siteConfigDTO.weburl}/3094.html#respond" rel="external nofollow"><i
                                   class="fa fa-comment-o"></i> 发表评论</a></span> </span>
                               <div class="clear"></div>
                           </div>
                           <!-- .entry-content --><span class="entry-more"><a href="${siteConfigDTO.weburl}/3094.html"
                                                                              rel="bookmark">阅读全文</a></span>
                       </article>
                       <!-- #post -->
                   </div>
                   <!-- 图文日志 -->
                   <div class="wow fadeInUp" data-wow-delay="0.3s">
                       <article id="post-3077"
                                class="post-3077 post type-post status-publish format-standard hentry category-java tag-freemarker tag-java tag-shiro tag-springboot zqu">
                           <figure class="thumbnail">
                               <div class="load">
                                   <a href="${siteConfigDTO.weburl}/3077.html"><img
                                           src="${request.contextPath}/themes/${siteConfigDTO.webtheme}/loading.png"
                                           data-original=""
                                           alt="SpringBoot下使用Shiro+Freemaker报错：org.apache.shiro.UnavailableSecurityManagerException"></a>
                               </div>
                               <span class="cat"><a href="${siteConfigDTO.weburl}/category/coding/java/">Java</a></span>
                           </figure>
                           <header class="entry-header">
                               <h2 class="entry-title"><a href="${siteConfigDTO.weburl}/3077.html"
                                                          rel="bookmark">SpringBoot下使用Shiro+Freemaker报错：org.apache.shiro.UnavailableSecurityManagerException</a>
                               </h2></header>
                           <!-- .entry-header -->
                           <div class="entry-content">
                               <div class="archive-content"> 前言
                                   今天在日常敲代码中，给SpringBoot自定义错误页，但是本来一件简单的事情却变得复杂起来了，页面总是空白的而且没有输出，后面新建了一个项目，但是在新项目中一切都是OK的，于是我进行一步步的对比调试，最终确定了是Shiro的FreeMarker
                                   Tag出的错导致页面渲染终止。 解决...
                               </div>
                               <span class="title-l"></span> <span class="entry-meta"> <span class="date">2018年03月18日&nbsp;&nbsp;</span><span
                                   class="views">阅读 48</span><span class="comment">&nbsp;&nbsp;<a
                                   href="${siteConfigDTO.weburl}/3077.html#respond" rel="external nofollow"><i
                                   class="fa fa-comment-o"></i> 发表评论</a></span> </span>
                               <div class="clear"></div>
                           </div>
                           <!-- .entry-content --><span class="entry-more"><a href="${siteConfigDTO.weburl}/3077.html"
                                                                              rel="bookmark">阅读全文</a></span>
                       </article>
                       <!-- #post -->
                   </div>
                   <!-- 广告 -->
                   <div class="wow fadeInUp" data-wow-delay="0.5s"></div>
                   <div class="wow fadeInUp" data-wow-delay="0.3s">
                       <article id="post-3072"
                                class="post-3072 post type-post status-publish format-standard hentry category-resource-sharing tag-98 tag-369 tag-403 zqu">
                           <figure class="thumbnail">
                               <div class="load">
                                   <a href="${siteConfigDTO.weburl}/3072.html"><img
                                           src="${request.contextPath}/themes/${siteConfigDTO.webtheme}/loading.png"
                                           data-original="" alt="腾讯云“云+校园扶持计划”1核2G1M服务器3年只需360元"></a>
                               </div>
                               <span class="cat"><a
                                       href="${siteConfigDTO.weburl}/category/resource-sharing/">资源分享</a></span>
                           </figure>
                           <header class="entry-header">
                               <h2 class="entry-title"><a href="${siteConfigDTO.weburl}/3072.html"
                                                          rel="bookmark">腾讯云“云+校园扶持计划”1核2G1M服务器3年只需360元</a>
                               </h2></header>
                           <!-- .entry-header -->
                           <div class="entry-content">
                               <div class="archive-content"> 前言
                                   自2016年腾讯云开启“云+校园扶持计划”这个活动就优惠不止，可以从16年1月我分享的《腾讯云：1元=免费域名+专享服务器活动分享》中看出来福利还是可以的，目前最新的这个活动已经持续了一段时间了，不过抢购用户貌似有点多，请各位需要购买服务器的玩家请赶快上车！
                                   :idea: 正文 新用...
                               </div>
                               <span class="title-l"></span> <span class="entry-meta"> <span class="date">2018年03月07日&nbsp;&nbsp;</span><span
                                   class="views">阅读 221</span><span class="comment">&nbsp;&nbsp;<a
                                   href="${siteConfigDTO.weburl}/3072.html#comments" rel="external nofollow"><i
                                   class="fa fa-comment-o"></i> 12</a></span> </span>
                               <div class="clear"></div>
                           </div>
                           <!-- .entry-content --><span class="entry-more"><a href="${siteConfigDTO.weburl}/3072.html"
                                                                              rel="bookmark">阅读全文</a></span>
                       </article>
                       <!-- #post -->
                   </div>
                   <div class="wow fadeInUp" data-wow-delay="0.3s">
                       <article id="post-3067"
                                class="post-3067 post type-post status-publish format-standard hentry category-java tag-springboot tag-springcloud tag-zuul zqu">
                           <figure class="thumbnail">
                               <div class="load">
                                   <a href="${siteConfigDTO.weburl}/3067.html"><img
                                           src="${request.contextPath}/themes/${siteConfigDTO.webtheme}/loading.png"
                                           data-original="" alt="SpringCloud使用Zuul出现“Forwarding error”错误解决方法"></a>
                               </div>
                               <span class="cat"><a href="${siteConfigDTO.weburl}/category/coding/java/">Java</a></span>
                           </figure>
                           <header class="entry-header">
                               <h2 class="entry-title"><a href="${siteConfigDTO.weburl}/3067.html"
                                                          rel="bookmark">SpringCloud使用Zuul出现“Forwarding
                                   error”错误解决方法</a></h2></header>
                           <!-- .entry-header -->
                           <div class="entry-content">
                               <div class="archive-content"> 起因
                                   博主在使用zuul的时候，所有的配置都是配置完全了的，但是只要一访问服务就出现500，然后查看控制台爆出com.netflix.zuul.exception.ZuulException:
                                   Forwarding error的错误信息，然后大概查看试了一下发现是zuul部署上物理机后的请...
                               </div>
                               <span class="title-l"></span> <span class="entry-meta"> <span class="date">2018年02月25日&nbsp;&nbsp;</span><span
                                   class="views">阅读 125</span><span class="comment">&nbsp;&nbsp;<a
                                   href="${siteConfigDTO.weburl}/3067.html#comments" rel="external nofollow"><i
                                   class="fa fa-comment-o"></i> 2</a></span> </span>
                               <div class="clear"></div>
                           </div>
                           <!-- .entry-content --><span class="entry-more"><a href="${siteConfigDTO.weburl}/3067.html"
                                                                              rel="bookmark">阅读全文</a></span>
                       </article>
                       <!-- #post -->
                   </div>
                   <div class="wow fadeInUp" data-wow-delay="0.3s">
                       <article id="post-3049"
                                class="post-3049 post type-post status-publish format-standard hentry category-suitan tag-399 tag-400 zqu">
                           <figure class="thumbnail">
                               <div class="load">
                                   <a href="${siteConfigDTO.weburl}/3049.html"><img
                                           src="${request.contextPath}/themes/${siteConfigDTO.webtheme}/loading.png"
                                           data-original="" alt="2018，一个新的起点。"></a>
                               </div>
                               <span class="cat"><a href="${siteConfigDTO.weburl}/category/talk/suitan/">个人随谈</a></span>
                           </figure>
                           <header class="entry-header">
                               <h2 class="entry-title"><a href="${siteConfigDTO.weburl}/3049.html"
                                                          rel="bookmark">2018，一个新的起点。</a></h2>
                           </header>
                           <!-- .entry-header -->
                           <div class="entry-content">
                               <div class="archive-content"> 前言
                                   今天是农历腊月十九，立春，从节气上来说今天狗年的第一天，首先先再次祝贺各位访问本站的朋友新年快乐。我也正好趁着今天，来总结一下自己过去的一年-2017年。 正文
                                   2017年，可以说是我人生中的一个巨大的转折点，因为在17年之前，我的生活几乎都是在学校度过的，而17年是我真正意义上的开...
                               </div>
                               <span class="title-l"></span> <span class="entry-meta"> <span class="date">2018年02月04日&nbsp;&nbsp;</span><span
                                   class="views">阅读 636</span><span class="comment">&nbsp;&nbsp;<a
                                   href="${siteConfigDTO.weburl}/3049.html#comments" rel="external nofollow"><i
                                   class="fa fa-comment-o"></i> 29</a></span> </span>
                               <div class="clear"></div>
                           </div>
                           <!-- .entry-content --><span class="entry-more"><a href="${siteConfigDTO.weburl}/3049.html"
                                                                              rel="bookmark">阅读全文</a></span>
                       </article>
                       <!-- #post -->
                   </div>
                   <div class="wow fadeInUp" data-wow-delay="0.3s">
                       <article id="post-3041"
                                class="post-3041 post type-post status-publish format-standard hentry category-toss-with-record tag-javascript tag-398 zqu">
                           <figure class="thumbnail">
                               <div class="load">
                                   <a href="${siteConfigDTO.weburl}/3041.html"><img
                                           src="${request.contextPath}/themes/${siteConfigDTO.webtheme}/loading.png"
                                           data-original="" alt="使用百度地图+经纬度绘制数据可视化地理分布"></a>
                               </div>
                               <span class="cat"><a
                                       href="${siteConfigDTO.weburl}/category/toss-more-than/toss-with-record/">折腾随记</a></span>
                           </figure>
                           <header class="entry-header">
                               <h2 class="entry-title"><a href="${siteConfigDTO.weburl}/3041.html"
                                                          rel="bookmark">使用百度地图+经纬度绘制数据可视化地理分布</a>
                               </h2></header>
                           <!-- .entry-header -->
                           <div class="entry-content">
                               <div class="archive-content"> 前言
                                   不知不觉，2017年已经过去了，而2018年的第一个月也快要接近尾声了，不得不感叹时间过得真的很快，由于自己最近一直很忙，所以没有时间来更新博客，导致博客短更接近一个多月，在这里给支持我的小伙伴们说声抱歉。
                                   正文 最近，由于业务需求，需要实现数据的海量点的坐标图，来进行数据的可视化展...
                               </div>
                               <span class="title-l"></span> <span class="entry-meta"> <span class="date">2018年01月30日&nbsp;&nbsp;</span><span
                                   class="views">阅读 245</span><span class="comment">&nbsp;&nbsp;<a
                                   href="${siteConfigDTO.weburl}/3041.html#comments" rel="external nofollow"><i
                                   class="fa fa-comment-o"></i> 2</a></span> </span>
                               <div class="clear"></div>
                           </div>
                           <!-- .entry-content --><span class="entry-more"><a href="${siteConfigDTO.weburl}/3041.html"
                                                                              rel="bookmark">阅读全文</a></span>
                       </article>
                       <!-- #post -->
                   </div>
                   <div class="clear"></div>
                   <!-- 单栏小工具 -->
                   <div id="cms-widget-one">
                       <div class="clear"></div>
                   </div>
                   <!-- 单栏分类5篇 -->
                   <!-- 单栏分类10篇 -->
                   <!-- 图片日志 -->
                   <!-- 两栏小工具 -->
                   <div id="cms-widget-two">
                       <div class="clear"></div>
                   </div>
                   <!-- 视频日志 -->
                   <!-- 主体两栏分类 -->
                   <!-- TAB切换 -->
                   <div class="tab-site wow fadeInUp" data-wow-delay="0.3s">
                       <div id="layout-tab" class="tab-product">
                           <h2 class="tab-hd"><span class="tab-hd-con current"><a
                                   href="javascript:">推荐文章</a></span> <span class="tab-hd-con"><a
                                   href="javascript:">折腾不止</a></span> <span class="tab-hd-con"><a
                                   href="javascript:">随便看看</a></span> <span class="tab-hd-con"><a
                                   href="javascript:">谈笑风生</a></span></h2>
                           <ul class="tab-bd dom-display">
                               <div class="tab-bd-con current">
                                   <li class="list-title"><i class="fa fa-angle-right"></i>
                                       <a href="${siteConfigDTO.weburl}/3077.html" rel="bookmark">SpringBoot下使用Shiro+Freemaker报错：org.apache.shiro.UnavailableSecurityManagerException</a>
                                   </li>
                                   <li class="list-title"><i class="fa fa-angle-right"></i>
                                       <a href="${siteConfigDTO.weburl}/3067.html" rel="bookmark">SpringCloud使用Zuul出现“Forwarding
                                           error”错误解决方法
                                       </a>
                                   </li>
                                   <li class="list-title"><i class="fa fa-angle-right"></i>
                                       <a href="${siteConfigDTO.weburl}/3019.html" rel="bookmark">Python批量抓取站酷ZCOOL作品图片并归档</a>
                                   </li>
                                   <li class="list-title"><i class="fa fa-angle-right"></i>
                                       <a href="${siteConfigDTO.weburl}/2958.html" rel="bookmark">Linux-Ubuntu安装Redis并配置认证密码</a>
                                   </li>
                                   <li class="list-title"><i class="fa fa-angle-right"></i>
                                       <a href="${siteConfigDTO.weburl}/2952.html" rel="bookmark">Freemarker自定义标签弥补Shiro没有HasAnyPermissions缺陷</a>
                                   </li>
                                   <li class="list-title"><i class="fa fa-angle-right"></i>
                                       <a href="${siteConfigDTO.weburl}/2947.html" rel="bookmark">SpringBoot+Shiro整合进行登录验证与权限控制</a>
                                   </li>
                                   <li class="list-title"><i class="fa fa-angle-right"></i>
                                       <a href="${siteConfigDTO.weburl}/2940.html" rel="bookmark">Android报错：android.text.SpannableString
                                           cannot be cast to java.lang.String解决方案</a>
                                   </li>
                                   <li class="list-title"><i class="fa fa-angle-right"></i>
                                       <a href="${siteConfigDTO.weburl}/2887.html" rel="bookmark">SpringBoot打包成war包并放置在tomcat运行</a>
                                   </li>
                               </div>
                               <div class="tab-bd-con" style="display: none;">
                                   <li class="list-title"><i class="fa fa-angle-right"></i>
                                       <a href="${siteConfigDTO.weburl}/3041.html"
                                          rel="bookmark">使用百度地图+经纬度绘制数据可视化地理分布</a>
                                   </li>
                                   <li class="list-title"><i class="fa fa-angle-right"></i>
                                       <a href="${siteConfigDTO.weburl}/2999.html"
                                          rel="bookmark">Centos7安装Jenkins配置持续集成</a>
                                   </li>
                                   <li class="list-title"><i class="fa fa-angle-right"></i>
                                       <a href="${siteConfigDTO.weburl}/2977.html" rel="bookmark">Centos
                                           7上使用Docker安装Mysql
                                       </a>
                                   </li>
                                   <li class="list-title"><i class="fa fa-angle-right"></i>
                                       <a href="${siteConfigDTO.weburl}/2923.html" rel="bookmark">Android Studio
                                           真机测试出现“waiting for debugger”信息解决</a>
                                   </li>
                                   <li class="list-title"><i class="fa fa-angle-right"></i>
                                       <a href="${siteConfigDTO.weburl}/2742.html"
                                          rel="bookmark">Jquery禁止Flash播放器右键属性</a>
                                   </li>
                                   <li class="list-title"><i class="fa fa-angle-right"></i>
                                       <a href="${siteConfigDTO.weburl}/2716.html" rel="bookmark">Laravel5.3在Nginx下部署的conf配置</a>
                                   </li>
                                   <li class="list-title"><i class="fa fa-angle-right"></i>
                                       <a href="${siteConfigDTO.weburl}/2708.html" rel="bookmark">解决WordPress开启CDN缓存后文章浏览量不自增的问题</a>
                                   </li>
                                   <li class="list-title"><i class="fa fa-angle-right"></i>
                                       <a href="${siteConfigDTO.weburl}/2701.html" rel="bookmark">Laravel
                                           5中去掉URL中的public路径方法
                                       </a>
                                   </li>
                               </div>
                               <div class="tab-bd-con" style="display: none;">
                                   <li class="list-title"><i class="fa fa-angle-right"></i>
                                       <a href="${siteConfigDTO.weburl}/3072.html" rel="bookmark">腾讯云“云+校园扶持计划”1核2G1M服务器3年只需360元</a>
                                   </li>
                                   <li class="list-title"><i class="fa fa-angle-right"></i>
                                       <a href="${siteConfigDTO.weburl}/2927.html"
                                          rel="bookmark">Fotor懒设计-小白用户的在线平面设计神器</a>
                                   </li>
                                   <li class="list-title"><i class="fa fa-angle-right"></i>
                                       <a href="${siteConfigDTO.weburl}/2656.html"
                                          rel="bookmark">瓦客圈圈助手码字完成已上线并可免费使用</a>
                                   </li>
                                   <li class="list-title"><i class="fa fa-angle-right"></i>
                                       <a href="${siteConfigDTO.weburl}/2050.html" rel="bookmark">一款内置VPN可访被墙网站的浏览器</a>
                                   </li>
                                   <li class="list-title"><i class="fa fa-angle-right"></i>
                                       <a href="${siteConfigDTO.weburl}/2018.html" rel="bookmark">TheRestartPage-还原历史操作系统重启画面</a>
                                   </li>
                                   <li class="list-title"><i class="fa fa-angle-right"></i>
                                       <a href="${siteConfigDTO.weburl}/1817.html" rel="bookmark">Liico-在线ico图标助手</a>
                                   </li>
                                   <li class="list-title"><i class="fa fa-angle-right"></i>
                                       <a href="${siteConfigDTO.weburl}/1552.html" rel="bookmark">视频分享之当全世界都变胖了</a>
                                   </li>
                                   <li class="list-title"><i class="fa fa-angle-right"></i>
                                       <a href="${siteConfigDTO.weburl}/1088.html" rel="bookmark">魔幻环绕音乐震撼你的听觉</a>
                                   </li>
                               </div>
                               <div class="tab-bd-con" style="display: none;">
                                   <li class="list-title"><i class="fa fa-angle-right"></i>
                                       <a href="${siteConfigDTO.weburl}/3049.html" rel="bookmark">2018，一个新的起点。</a>
                                   </li>
                                   <li class="list-title"><i class="fa fa-angle-right"></i>
                                       <a href="${siteConfigDTO.weburl}/2446.html" rel="bookmark">Git使用之Windows下生成SHHKEY值方法</a>
                                   </li>
                                   <li class="list-title"><i class="fa fa-angle-right"></i>
                                       <a href="${siteConfigDTO.weburl}/1928.html" rel="bookmark">推荐一个不错的代码托管平台</a>
                                   </li>
                                   <li class="list-title"><i class="fa fa-angle-right"></i>
                                       <a href="${siteConfigDTO.weburl}/1842.html" rel="bookmark">别让手机夺走了你的人生</a>
                                   </li>
                                   <li class="list-title"><i class="fa fa-angle-right"></i>
                                       <a href="${siteConfigDTO.weburl}/1823.html" rel="bookmark">这九句话，可能毁了你的职场生涯</a>
                                   </li>
                                   <li class="list-title"><i class="fa fa-angle-right"></i>
                                       <a href="${siteConfigDTO.weburl}/1721.html" rel="bookmark">.Blog域名预计今年底前上线</a>
                                   </li>
                                   <li class="list-title"><i class="fa fa-angle-right"></i>
                                       <a href="${siteConfigDTO.weburl}/1632.html" rel="bookmark">万维网联盟正在Github上开发HTML5.1</a>
                                   </li>
                                   <li class="list-title"><i class="fa fa-angle-right"></i>
                                       <a href="${siteConfigDTO.weburl}/1513.html" rel="bookmark">优酷土豆完成私有化正式成为阿里子公司</a>
                                   </li>
                               </div>
                           </ul>
                       </div>
                   </div>
                   <div class="clear"></div>
               </main>
               <!-- .site-main -->
           </div>
           <!-- .content-area -->
           <!-- 侧边小工具 -->
           <div id="sidebar" class="widget-area">
               <div class="wow" data-wow-delay="0.5s">
                   <aside id="about-2" class="widget widget_about wow fadeInUp" data-wow-delay="0.3s">
                       <h3 class="widget-title"><i class="fa fa-bars"></i>关于本站</h3>
                       <div id="feed_widget">
								<span class="feed-about">
                            <div class="about-main">
                                <div class="about-img"> <img src="${request.contextPath}/uploads/images/me.jpg"> </div>
                                <div class="about-name">${siteConfigDTO.webname}</div> <div
                                    class="about-the">${siteConfigDTO.description}</div>
                            </div>
                            <div class="clear"></div>
                        <ul>
                            <li class="weixin"> <span class="tipso_style" id="tip-w"
                                                      data-tipso="&lt;div id=&quot;weixin-qr&quot;&gt;&lt;img src=&quot;http://open.weixin.qq.com/qr/code?username=desgintalk&quot;/&gt;&lt;/div&gt;"><a
                                    title="微信"><i class="fa fa-weixin"></i></a></span> </li>
								<li class="tqq">
									<a target="blank" rel="external nofollow"
                                       href="http://wpa.qq.com/msgrd?V=3&amp;uin=1035136784&amp;Site=QQ&amp;Menu=yes"
                                       title="QQ在线"><i class="fa fa-qq"></i></a>
								</li>
								<li class="tsina">
									<a title="" href="http://www.weibo.com/tyouwei" target="_blank"
                                       rel="external nofollow"><i class="fa fa-weibo"></i></a>
								</li>
								<li class="feed">
									<a title="" href="${siteConfigDTO.weburl}/feed/" target="_blank"
                                       rel="external nofollow"><i class="fa fa-rss"></i></a>
								</li>
								</ul>
								<div class="about-inf"> <span class="about-pn">浏览 470587</span> <span class="about-cn">留言 2566</span> </div>
								</span>
                       </div>
                       <div class="clear"></div>
                   </aside>
                   <aside id="hot_post-2" class="widget widget_hot_post wow" data-wow-delay="0.3s">
                       <h3 class="widget-title"><i class="fa fa-bars"></i>热门文章</h3>
                       <div id="hot_post_widget">
                           <ul>
                               <li><span class="li-icon li-icon-1">1</span>
                                   <a href="${siteConfigDTO.weburl}/2981.html">Vue项目在IE下警告“ReferenceError:‘Promise’未定义”</a>
                               </li>
                               <li><span class="li-icon li-icon-2">2</span>
                                   <a href="${siteConfigDTO.weburl}/2964.html">Vue提示warn：”[vue-router] Named Route
                                       ‘home’ has a default child route…”</a>
                               </li>
                               <li><span class="li-icon li-icon-3">3</span>
                                   <a href="${siteConfigDTO.weburl}/2970.html">Vue中通过render添加的元素实现伪双向绑定功能</a>
                               </li>
                               <li><span class="li-icon li-icon-4">4</span>
                                   <a href="${siteConfigDTO.weburl}/3049.html">2018，一个新的起点。</a>
                               </li>
                               <li><span class="li-icon li-icon-5">5</span>
                                   <a href="${siteConfigDTO.weburl}/3019.html">Python批量抓取站酷ZCOOL作品图片并归档</a>
                               </li>
                               <li><span class="li-icon li-icon-6">6</span>
                                   <a href="${siteConfigDTO.weburl}/2984.html">Vue按钮插件Flash-Button正式版1.1.0发布</a>
                               </li>
                           </ul>
                       </div>
                       <div class="clear"></div>
                   </aside>
                   <aside id="hot_comment-3" class="widget widget_hot_comment wow" data-wow-delay="0.3s">
                       <h3 class="widget-title"><i class="fa fa-bars"></i>热评文章</h3>
                       <div id="hot_comment_widget">
                           <ul>
                               <li><span class="li-icon li-icon-1">1</span>
                                   <a href="${siteConfigDTO.weburl}/3049.html" rel="bookmark" title=" (29条评论)">2018，一个新的起点。</a>
                               </li>
                               <li><span class="li-icon li-icon-2">2</span>
                                   <a href="${siteConfigDTO.weburl}/2984.html" rel="bookmark" title=" (14条评论)">Vue按钮插件Flash-Button正式版1.1.0发布</a>
                               </li>
                               <li><span class="li-icon li-icon-3">3</span>
                                   <a href="${siteConfigDTO.weburl}/3072.html" rel="bookmark" title=" (12条评论)">腾讯云“云+校园扶持计划”1核2G1M服务器3年只需360元</a>
                               </li>
                               <li><span class="li-icon li-icon-4">4</span>
                                   <a href="${siteConfigDTO.weburl}/3019.html" rel="bookmark" title=" (9条评论)">Python批量抓取站酷ZCOOL作品图片并归档</a>
                               </li>
                               <li><span class="li-icon li-icon-5">5</span>
                                   <a href="${siteConfigDTO.weburl}/2993.html" rel="bookmark" title=" (6条评论)">JavaScript实时监听歌曲播放进度显示对应歌词</a>
                               </li>
                               <li><span class="li-icon li-icon-6">6</span>
                                   <a href="${siteConfigDTO.weburl}/2964.html" rel="bookmark" title=" (4条评论)">Vue提示warn："[vue-router]
                                       Named Route 'home' has a default child route..."</a>
                               </li>
                           </ul>
                       </div>
                       <div class="clear"></div>
                   </aside>
                   <aside id="like_most-2" class="widget widget_like_most wow fadeInUp" data-wow-delay="0.3s">
                       <h3 class="widget-title"><i class="fa fa-bars"></i>大家喜欢</h3>
                       <div id="like" class="like_most">
                           <ul>
                               <li><span class="li-icon li-icon-1">1</span>
                                   <a href="${siteConfigDTO.weburl}/2958.html">Linux-Ubuntu安装Redis并配置认证密码</a>
                               </li>
                               <li><span class="li-icon li-icon-2">2</span>
                                   <a href="${siteConfigDTO.weburl}/2964.html">Vue提示warn：”[vue-router] Named Route
                                       ‘home’ has a default child route…”</a>
                               </li>
                               <li><span class="li-icon li-icon-3">3</span>
                                   <a href="${siteConfigDTO.weburl}/2984.html">Vue按钮插件Flash-Button正式版1.1.0发布</a>
                               </li>
                               <li><span class="li-icon li-icon-4">4</span>
                                   <a href="${siteConfigDTO.weburl}/3049.html">2018，一个新的起点。</a>
                               </li>
                               <li><span class="li-icon li-icon-5">5</span>
                                   <a href="${siteConfigDTO.weburl}/2981.html">Vue项目在IE下警告“ReferenceError:‘Promise’未定义”</a>
                               </li>
                               <li><span class="li-icon li-icon-6">6</span>
                                   <a href="${siteConfigDTO.weburl}/3019.html">Python批量抓取站酷ZCOOL作品图片并归档</a>
                               </li>
                           </ul>
                       </div>
                       <div class="clear"></div>
                   </aside>
                   </aside>
                   <aside id="recent_comments-2" class="widget widget_recent_comments wow fadeInUp"
                          data-wow-delay="0.3s">
                       <h3 class="widget-title"><i class="fa fa-bars"></i>近期评论</h3>
                       <div id="message" class="message-widget">
                           <ul>
                               <li>
                                   <a href="${siteConfigDTO.weburl}/3072.html#comment-3033"
                                      title="腾讯云“云+校园扶持计划”1核2G1M服务器3年只需360元" rel="external nofollow"> <img
                                           src="${request.contextPath}/uploads/images/me.jpg" class="avatar avatar-128"
                                           height="128" width="128"> <span class="comment_author"><strong>夏天烤洋芋</strong></span>要认证很多东西，嫌麻烦，然而没学生证
                                   </a>
                               </li>
                               <li>
                                   <a href="${siteConfigDTO.weburl}/3049.html#comment-3030" title="2018，一个新的起点。"
                                      rel="external nofollow"> <img src="${request.contextPath}/uploads/images/me.jpg"
                                                                    class="avatar avatar-128" height="128"
                                                                    width="128"><span class="comment_author"><strong>米粒博客</strong></span>
                                       流弊，我的驾照6月报名的，这3月初才拿到证 </a>
                               </li>
                               <li>
                                   <a href="${siteConfigDTO.weburl}/messaged.html#comment-3029" title="留言板"
                                      rel="external nofollow"> <img src="${request.contextPath}/uploads/images/me.jpg"
                                                                    class="avatar avatar-128" height="128"
                                                                    width="128"><span class="comment_author"><strong>留芳内容</strong></span>
                                       都是些老博客啊。 </a>
                               </li>
                               <li>
                                   <a href="${siteConfigDTO.weburl}/messaged.html#comment-3025" title="留言板"
                                      rel="external nofollow"> <img src="${request.contextPath}/uploads/images/me.jpg"
                                                                    class="avatar avatar-128" height="128"
                                                                    width="128"><span class="comment_author"><strong>米粒博客</strong></span>
                                       2018又重拾激情来一波！ </a>
                               </li>
                               <li>
                                   <a href="${siteConfigDTO.weburl}/3072.html#comment-3024"
                                      title="腾讯云“云+校园扶持计划”1核2G1M服务器3年只需360元" rel="external nofollow"> <img
                                           src="${request.contextPath}/uploads/images/me.jpg" class="avatar avatar-128"
                                           height="128" width="128"> <span class="comment_author"><strong>米粒博客</strong></span>不需要学生证吧？
                                   </a>
                               </li>
                               <li style="border: none;">
                                   <a href="${siteConfigDTO.weburl}/1647.html#comment-3023" title="WordPress仿知更鸟评论等级样式"
                                      rel="external nofollow"> <img src="${request.contextPath}/uploads/images/me.jpg"
                                                                    class="avatar avatar-128" height="128" width="128">
                                       <span class="comment_author"><strong>QQ98789</strong></span> <img
                                               src="${request.contextPath}/uploads/images/icon_rolleyes.gif"
                                               alt=":roll:" class="wp-smiley" style="height: 1em; max-height: 1em;"> 厉害了
                                   </a>
                               </li>
                           </ul>
                       </div>
                       <div class="clear"></div>
                   </aside>
               </div>
           </div>
           <div class="clear"></div>
           <!-- 横向图片滚动 -->
           <!-- 底部分类 -->
           <div class="line-big">
               <div class="xl3 xm3">
                   <div class="cat-box wow fadeInUp" data-wow-delay="0.3s">
                       <h3 class="cat-title"><a
                               href="${siteConfigDTO.weburl}/category/toss-more-than/" title=""><i
                               class="fa fa-bars"></i>折腾不止</a>
                       </h3>
                       <div class="clear"></div>
                       <div class="cat-site">
                           <h2 class="entry-title"><a href="${siteConfigDTO.weburl}/2999.html"
                                                      rel="bookmark">Centos7安装Jenkins配置持续集成</a></h2>
                           <figure class="thumbnail">
                               <div class="load">
                                   <a href="${siteConfigDTO.weburl}/2999.html"><img
                                           src="${request.contextPath}/themes/${siteConfigDTO.webtheme}/loading.png"
                                           data-original="" alt="Centos7安装Jenkins配置持续集成"></a>
                               </div>
                           </figure>
                           <div class="cat-main"> 介绍 何为持续集成？ 持续集成（Continuous Integration）指的是，频繁地（一天多次）将代码集成到主干。
                               持续集成的目的，就是让产品可以快速迭代，同时还能保持高质量。 它的...
                           </div>
                           <div class="clear"></div>
                           <ul class="cat-list"><span class="list-date">10/27</span>
                               <li class="list-title"><i class="fa fa-angle-right"></i>
                                   <a href="${siteConfigDTO.weburl}/2977.html" rel="bookmark">Centos
                                       7上使用Docker安装Mysql</a>
                               </li>
                               <span class="list-date">08/15</span>
                               <li class="list-title"><i class="fa fa-angle-right"></i>
                                   <a href="${siteConfigDTO.weburl}/2923.html" rel="bookmark">Android Studio
                                       真机测试出现“waiting for debugger”信息解决</a>
                               </li>
                               <span class="list-date">02/26</span>
                               <li class="list-title"><i class="fa fa-angle-right"></i>
                                   <a href="${siteConfigDTO.weburl}/2742.html" rel="bookmark">Jquery禁止Flash播放器右键属性</a>
                               </li>
                               <span class="list-date">01/18</span>
                               <li class="list-title"><i class="fa fa-angle-right"></i>
                                   <a href="${siteConfigDTO.weburl}/2716.html"
                                      rel="bookmark">Laravel5.3在Nginx下部署的conf配置</a>
                               </li>
                           </ul>
                       </div>
                   </div>
               </div>
               <div class="xl3 xm3">
                   <div class="cat-box wow fadeInUp" data-wow-delay="0.3s">
                       <h3 class="cat-title"><a
                               href="${siteConfigDTO.weburl}/category/coding/" title="一段Code，分享你我所知的无限精彩！"><i
                               class="fa fa-bars"></i>代码编程</a></h3>
                       <div class="clear"></div>
                       <div class="cat-site">
                           <h2 class="entry-title"><a href="${siteConfigDTO.weburl}/3019.html"
                                                      rel="bookmark">Python批量抓取站酷ZCOOL作品图片并归档</a></h2>
                           <figure class="thumbnail">
                               <div class="load">
                                   <a href="${siteConfigDTO.weburl}/3019.html"><img
                                           src="${request.contextPath}/themes/${siteConfigDTO.webtheme}/loading.png"
                                           data-original="" alt="Python批量抓取站酷ZCOOL作品图片并归档"></a>
                               </div>
                           </figure>
                           <div class="cat-main"> 前言
                               前几天，由于个人有需求，所以就要对站酷网一些类别下的作品的图片进行批量抓取，首先是采用的是NodeJs来写的，但是在运行的途中遇到很多的问题，所以后来就换成了Python，同时使用了多...
                           </div>
                           <div class="clear"></div>
                           <ul class="cat-list"><span class="list-date">10/11</span>
                               <li class="list-title"><i class="fa fa-angle-right"></i>
                                   <a href="${siteConfigDTO.weburl}/2958.html"
                                      rel="bookmark">Linux-Ubuntu安装Redis并配置认证密码</a>
                               </li>
                               <span class="list-date">09/19</span>
                               <li class="list-title"><i class="fa fa-angle-right"></i>
                                   <a href="${siteConfigDTO.weburl}/2952.html" rel="bookmark">Freemarker自定义标签弥补Shiro没有HasAnyPermissions缺陷</a>
                               </li>
                               <span class="list-date">09/10</span>
                               <li class="list-title"><i class="fa fa-angle-right"></i>
                                   <a href="${siteConfigDTO.weburl}/2947.html" rel="bookmark">SpringBoot+Shiro整合进行登录验证与权限控制</a>
                               </li>
                               <span class="list-date">08/22</span>
                               <li class="list-title"><i class="fa fa-angle-right"></i>
                                   <a href="${siteConfigDTO.weburl}/2940.html" rel="bookmark">Android报错：android.text.SpannableString
                                       cannot be cast to java.lang.String解决方案</a>
                               </li>
                           </ul>
                       </div>
                   </div>
               </div>
               <div class="xl3 xm3">
                   <div class="cat-box wow fadeInUp" data-wow-delay="0.3s">
                       <h3 class="cat-title"><a
                               href="${siteConfigDTO.weburl}/category/web/"
                               title="前端对于网站来说，通常是指网站的前台部分，包括网站的表现层和结构层。"><i
                               class="fa fa-bars"></i>前端技术</a></h3>
                       <div class="clear"></div>
                       <div class="cat-site">
                           <h2 class="entry-title"><a href="${siteConfigDTO.weburl}/2993.html"
                                                      rel="bookmark">JavaScript实时监听歌曲播放进度显示对应歌词</a></h2>
                           <figure class="thumbnail">
                               <div class="load">
                                   <a href="${siteConfigDTO.weburl}/2993.html"><img
                                           src="${request.contextPath}/themes/${siteConfigDTO.webtheme}/loading.png"
                                           data-original="" alt="JavaScript实时监听歌曲播放进度显示对应歌词"></a>
                               </div>
                           </figure>
                           <div class="cat-main"> 前言 在之前我就想试试在线的音乐播放器的制作，昨晚动手实现了播放音乐的歌词实时对应显示的组件，下面就来看看其中的解析原理。 正文
                               这里我以李玉刚的《刚好遇见你》为例，首先我们需要获取到音频文...
                           </div>
                           <div class="clear"></div>
                           <ul class="cat-list"><span class="list-date">11/16</span>
                               <li class="list-title"><i class="fa fa-angle-right"></i>
                                   <a href="${siteConfigDTO.weburl}/2984.html" rel="bookmark">Vue按钮插件Flash-Button正式版1.1.0发布</a>
                               </li>
                               <span class="list-date">11/01</span>
                               <li class="list-title"><i class="fa fa-angle-right"></i>
                                   <a href="${siteConfigDTO.weburl}/2981.html" rel="bookmark">Vue项目在IE下警告“ReferenceError:‘Promise’未定义”</a>
                               </li>
                               <span class="list-date">10/23</span>
                               <li class="list-title"><i class="fa fa-angle-right"></i>
                                   <a href="${siteConfigDTO.weburl}/2970.html"
                                      rel="bookmark">Vue中通过render添加的元素实现伪双向绑定功能</a>
                               </li>
                               <span class="list-date">10/13</span>
                               <li class="list-title"><i class="fa fa-angle-right"></i>
                                   <a href="${siteConfigDTO.weburl}/2964.html" rel="bookmark">Vue提示warn：”[vue-router]
                                       Named Route ‘home’ has a default child route…”</a>
                               </li>
                           </ul>
                       </div>
                   </div>
               </div>
               <div class="xl3 xm3">
                   <div class="cat-box wow fadeInUp" data-wow-delay="0.3s">
                       <h3 class="cat-title"><a
                               href="${siteConfigDTO.weburl}/category/resource-sharing/" title=""><i
                               class="fa fa-bars"></i>资源分享</a></h3>
                       <div class="clear"></div>
                       <div class="cat-site">
                           <h2 class="entry-title"><a href="${siteConfigDTO.weburl}/2927.html"
                                                      rel="bookmark">Fotor懒设计-小白用户的在线平面设计神器</a></h2>
                           <figure class="thumbnail">
                               <div class="load">
                                   <a href="${siteConfigDTO.weburl}/2927.html"><img
                                           src="${request.contextPath}/themes/${siteConfigDTO.webtheme}/loading.png"
                                           data-original="" alt="Fotor懒设计-小白用户的在线平面设计神器"></a>
                               </div>
                           </figure>
                           <div class="cat-main"> 憧憬点滴记忆联合Fotor懒设计送福利啦！使用官方邀请码注册，即可获得价值89元的6个月懒精灵VIP特权 :smile: 。
                               官方邀请注册链接：https://www.fotor.com.c...
                           </div>
                           <div class="clear"></div>
                           <ul class="cat-list"><span class="list-date">12/02</span>
                               <li class="list-title"><i class="fa fa-angle-right"></i>
                                   <a href="${siteConfigDTO.weburl}/2656.html" rel="bookmark">瓦客圈圈助手码字完成已上线并可免费使用</a>
                               </li>
                               <span class="list-date">09/05</span>
                               <li class="list-title"><i class="fa fa-angle-right"></i>
                                   <a href="${siteConfigDTO.weburl}/2050.html" rel="bookmark">一款内置VPN可访被墙网站的浏览器</a>
                               </li>
                               <span class="list-date">09/02</span>
                               <li class="list-title"><i class="fa fa-angle-right"></i>
                                   <a href="${siteConfigDTO.weburl}/2018.html" rel="bookmark">TheRestartPage-还原历史操作系统重启画面</a>
                               </li>
                               <span class="list-date">06/22</span>
                               <li class="list-title"><i class="fa fa-angle-right"></i>
                                   <a href="${siteConfigDTO.weburl}/1817.html" rel="bookmark">Liico-在线ico图标助手</a>
                               </li>
                           </ul>
                       </div>
                   </div>
               </div>
               <div class="clear"></div>
           </div>
           <!-- 淘客 -->
           <!-- 下载 -->
           <!-- 产品 -->
           <!-- 无缩略图分类 -->
           <!-- 页脚 -->
           <div class="clear"></div>
           <div id="links" class="wow fadeInUp" data-wow-delay="0.3s">
               <ul>
                   <span class="lx7"><span class="link-f link-name"><a href="http://www.tao54321.com/" target="_blank">letou</a></span></span>
                   <span class="lx7"><span class="link-f link-name"><a href="http://www.snowruin.com/" target="_blank">爱知网</a></span></span>
                   <span class="lx7"><span class="link-f"><li><a href="${siteConfigDTO.weburl}/links.html"
                                                                 target="_blank" title="全部链接">更多链接<i
                           class="icon-li"></i></a></li></span></span>
               </ul>
               <div class="clear"></div>
           </div>
        </div>
        <!-- .site-content -->
</@htmlBody>

<#-- 底部 -->
<@htmlFoot>
        <script type="text/javascript" src="${basePath}/themes/${siteConfigDTO.webtheme}/js/index.js"></script>
</@htmlFoot>