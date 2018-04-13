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