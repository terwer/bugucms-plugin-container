<#-- 引入布局模板文件 -->
<#include "layout/defaultLayout.ftl"/>
<#-- 调用布局指令 -->
<#-- 头部 -->
<@htmlHead title="${siteConfigDTO.webname} - ${siteConfigDTO.webslogen}">
        <link rel="stylesheet" type="text/css" href="${basePath}/themes/${siteConfigDTO.webtheme}/css/index.css">
</@htmlHead>

<#-- 正文 -->
<@htmlBody>
        <div id="content" class="site-content">
           <div class="clear"></div>
           <div id="primary" class="content-area">
               <main id="main" class="site-main" role="main">
                   <!-- 幻灯 -->
                   <!-- 置顶 -->
                   <!-- 最新文章 -->
                   <!-- 图文日志 -->
                   <div class="wow fadeInUp" data-wow-delay="0.3s">
                       <article id="post-1"
                                class="post-1 post type-post status-publish format-standard hentry category-java tag-freemarker tag-java tag-shiro tag-springboot zqu">
                           <figure class="thumbnail">
                               <div class="load">
                                   <a href="${siteConfigDTO.weburl}/post/test/hello-world.html"><img
                                           src="${request.contextPath}/themes/${siteConfigDTO.webtheme}/loading.png"
                                           data-original=""
                                           alt="SpringBoot下使用Shiro+Freemaker报错：org.apache.shiro.UnavailableSecurityManagerException"></a>
                               </div>
                               <span class="cat"><a href="${siteConfigDTO.weburl}/category/coding/java/">Java</a></span>
                           </figure>
                           <header class="entry-header">
                               <h2 class="entry-title"><a href="${siteConfigDTO.weburl}/post/test/hello-world.html"
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
                                   href="${siteConfigDTO.weburl}/post/test/hello-world.html#respond" rel="external nofollow"><i
                                   class="fa fa-comment-o"></i> 发表评论</a></span> </span>
                               <div class="clear"></div>
                           </div>
                           <!-- .entry-content --><span class="entry-more"><a href="${siteConfigDTO.weburl}/post/test/hello-world.html"
                                                                              rel="bookmark">阅读全文</a></span>
                       </article>
                       <!-- #post -->
                   </div>
                   <!-- 广告 -->
                   <div class="wow fadeInUp" data-wow-delay="0.3s">
                       <article id="post-2"
                                class="post-2 post type-post status-publish format-standard hentry category-resource-sharing tag-98 tag-369 tag-403 zqu">
                           <figure class="thumbnail">
                               <div class="load">
                                   <a href="${siteConfigDTO.weburl}/post/test/hello-world.html"><img
                                           src="${request.contextPath}/themes/${siteConfigDTO.webtheme}/loading.png"
                                           data-original="" alt="腾讯云“云+校园扶持计划”1核2G1M服务器3年只需360元"></a>
                               </div>
                               <span class="cat"><a
                                       href="${siteConfigDTO.weburl}/category/resource-sharing/">资源分享</a></span>
                           </figure>
                           <header class="entry-header">
                               <h2 class="entry-title"><a href="1${siteConfigDTO.weburl}/post/test/hello-world.html"
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
                                   href="1${siteConfigDTO.weburl}/post/test/hello-world.html#comments" rel="external nofollow"><i
                                   class="fa fa-comment-o"></i> 12</a></span> </span>
                               <div class="clear"></div>
                           </div>
                           <!-- .entry-content --><span class="entry-more"><a href="1${siteConfigDTO.weburl}/post/test/hello-world.html"
                                                                              rel="bookmark">阅读全文</a></span>
                       </article>
                       <!-- #post -->
                   </div>
                   <div class="wow fadeInUp" data-wow-delay="0.3s">
                       <article id="post-3"
                                class="post-3 post type-post status-publish format-standard hentry category-java tag-springboot tag-springcloud tag-zuul zqu">
                           <figure class="thumbnail">
                               <div class="load">
                                   <a href="${siteConfigDTO.weburl}/post/test/hello-world.html"><img
                                           src="${request.contextPath}/themes/${siteConfigDTO.webtheme}/loading.png"
                                           data-original="" alt="SpringCloud使用Zuul出现“Forwarding error”错误解决方法"></a>
                               </div>
                               <span class="cat"><a href="${siteConfigDTO.weburl}/category/coding/java/">Java</a></span>
                           </figure>
                           <header class="entry-header">
                               <h2 class="entry-title"><a href="${siteConfigDTO.weburl}/post/test/hello-world.html"
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
                                   href="${siteConfigDTO.weburl}/post/test/hello-world.html#comments" rel="external nofollow"><i
                                   class="fa fa-comment-o"></i> 2</a></span> </span>
                               <div class="clear"></div>
                           </div>
                           <!-- .entry-content --><span class="entry-more"><a href="${siteConfigDTO.weburl}/post/test/hello-world.html"
                                                                              rel="bookmark">阅读全文</a></span>
                       </article>
                       <!-- #post -->
                   </div>
                   <div class="wow fadeInUp" data-wow-delay="0.3s">
                       <article id="post-4"
                                class="post-4 post type-post status-publish format-standard hentry category-suitan tag-399 tag-400 zqu">
                           <figure class="thumbnail">
                               <div class="load">
                                   <a href="${siteConfigDTO.weburl}/post/test/hello-world.html"><img
                                           src="${request.contextPath}/themes/${siteConfigDTO.webtheme}/loading.png"
                                           data-original="" alt="2018，一个新的起点。"></a>
                               </div>
                               <span class="cat"><a href="${siteConfigDTO.weburl}/category/talk/suitan/">个人随谈</a></span>
                           </figure>
                           <header class="entry-header">
                               <h2 class="entry-title"><a href="${siteConfigDTO.weburl}/post/test/hello-world.html"
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
                                   href="${siteConfigDTO.weburl}/post/test/hello-world.html#comments" rel="external nofollow"><i
                                   class="fa fa-comment-o"></i> 29</a></span> </span>
                               <div class="clear"></div>
                           </div>
                           <!-- .entry-content --><span class="entry-more"><a href="${siteConfigDTO.weburl}/post/test/hello-world.html"
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
               </main>
               <!-- .site-main -->
           </div>
           <!-- .content-area -->
           <!-- 侧边小工具 -->
           <div id="sidebar" class="widget-area">
               <div class="wow" data-wow-delay="0.5s">
                   <aside id="about" class="widget widget_about wow fadeInUp" data-wow-delay="0.3s">
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
               </div>
           </div>
           <div class="clear"></div>
           <!-- 横向图片滚动 -->
           <!-- 底部分类 -->
           <!-- 淘客 -->
           <!-- 下载 -->
           <!-- 产品 -->
           <!-- 无缩略图分类 -->
           <!-- 页脚 -->
           <div class="clear"></div>
           <div id="links" class="wow fadeInUp" data-wow-delay="0.3s">
               <ul>
                   <span class="lx7"><span class="link-f link-name"><a href="http://www.cnblogs.com/tangyouwei" target="_blank">cnblogs</a></span></span>
                   <span class="lx7"><span class="link-f"><li><a href="${siteConfigDTO.weburl}/links.html" target="_blank" title="全部链接">更多链接<i class="icon-li"></i></a></li></span></span>
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