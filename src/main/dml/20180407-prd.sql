-- 初始化站点配置
INSERT INTO bg_options (option_group, option_name, option_value)
VALUES ('siteConfig', 'domain', 'www.terwergreen.com'),
  ('siteConfig', 'weburl', 'http://www.terwergreen.com'), ('siteConfig', 'webtheme', 'default'),
  ('siteConfig', 'webname', '远方的灯塔'),
  ('siteConfig', 'webslogen', '专注于服务端技术分享'),
  ('siteConfig', 'keywords', '软件架构、服务端开发、Java、Spring、Dubbo、Zookeeper、微服务'),
  ('siteConfig', 'description',
   '远方的灯塔(特维博客)是关注与分享互联网及服务端开发技术的个人博客，致力于Java后端开发及服务端技术、软件架构、微服务技术分享。同时也记录个人的一路点滴，所蕴含的包括前端、后端、数据库等知识，欢迎您关注我们。'),
  ('siteConfig', 'beianinfo', '粤ICP备18023717号-1');

-- 初始化测试用户
INSERT INTO bg_users (user_name, PASSWORD, nick_name, mobile, user_registered)
VALUES ('B0001', 'e10adc3949ba59abbe56e057f20f883e', '小猫咪', '18888888888', '2018-03-28 16:05:59');
INSERT INTO bg_users (user_name, PASSWORD, nick_name, mobile, user_registered)
VALUES ('B0002', 'e10adc3949ba59abbe56e057f20f883e', '调皮狗', '16666666666', '2018-03-28 16:05:59');

-- 初始化测试文章
INSERT INTO bg_posts(post_author, post_date, post_content, post_title, post_status, comment_status, post_password, post_name, post_modified, post_parent, menu_order, post_type, post_mime_type, comment_count) VALUES ('0', '2018-04-20 01:08:41', '# java-technology-stack\nThe Java Technology Stack\n\n# 基础(Java SE)\n\n## 数据类型(Data Type)\n\nbyte Byte\n\nint Integer\n\nshort Short\n\nlong Long\n\nString\n\n## 面向对象(OOP)\n\nObject(hashCode,equals)\n\nUML,依赖,封装,继承,多态\n\n## 集合(Collection)\n\nArrayList LinkedList Queue Stack Map HashMap TreeMap Set HashSet\n\n## 网络(Network)\n\nURLConnection\n\nhttp,https,ssl证书\n\n## 多线程(Thread)\n\nThread,runnable\n\n## IO\n\nnio\n\n# 虚拟机（JVM）\n\n# 版本控制\n\nsvn,git\n\n# 项目构建\n\nant,maven,gradle\n\n# 算法与数据结构\n\n# Java Web\n\nHTML,JavaScript,CSS\n\nJSP,Servlet\n\nTomcat,jetty\n\n单点登陆\n\n图表（echarts）\n\n负载均衡,共享Session\n\n# Java EE\n\nSpring,Spring MVC,Spring boot,Hibernate,MyBatis\n\n# 操作系统\n\nLinux\n\n#  数据库\n\nMySQL,Oracle\n\n分库分表,垂直拆分,水平拆分\n\n# 设计模式\n\n# 架构\n\n# 大数据\n\nHaddop\n\n# 消息队列\n\nRabbitMQ,ActiveMQ\n\n# 定时任务\n\nQuartz\n\n# 缓存\n\nredis,memcache\n\n# 微服务\n\nDubbo\n\n# 搜索引擎\n\n# 必读书目\n\n## 第一阶段\n\n《Java核心技术》\n\n《Java编程思想》\n\n## 第二阶段\n\n《Effective Java》\n\n《深入理解Java虚拟机》\n\n## 第三阶段\n\n《构建高性能Web站点》\n\n# 参考\n[你离BAT之间，只差这一套Java面试题](https://juejin.im/post/5ad54bae6fb9a028d7011770)   \n[一个两年Java的面试总结](https://juejin.im/post/5a9f5ce86fb9a028de443ed9)       \n[Java面试通关要点汇总集](https://juejin.im/post/5a94a8ca6fb9a0635c049e67)      \n[金三银四跳槽季，Java面试大纲](https://juejin.im/post/5aacad4551882555642bd1b0)   \n', 'Java技术栈', 'publish', 'open', '', 'java-technology-stack', '2018-04-20 01:09:37', '0', '0', 'post', '', '0');
