-- ----------------------------
-- Records of BG_OPTIONS
-- ----------------------------
INSERT INTO "BG"."BG_OPTIONS" VALUES ('1', 'siteConfig', 'domain', 'www.terwergreen.com');
INSERT INTO "BG"."BG_OPTIONS" VALUES ('2', 'siteConfig', 'weburl', 'http://www.terwergreen.com');
INSERT INTO "BG"."BG_OPTIONS" VALUES ('3', 'siteConfig', 'webtheme', 'default');
INSERT INTO "BG"."BG_OPTIONS" VALUES ('4', 'siteConfig', 'webname', '远方的灯塔');
INSERT INTO "BG"."BG_OPTIONS" VALUES ('5', 'siteConfig', 'webslogen', '专注于服务端技术分享');
INSERT INTO "BG"."BG_OPTIONS" VALUES ('6', 'siteConfig', 'keywords', '软件架构、服务端开发、Java、Spring、Dubbo、Zookeeper、微服务、vuejs');
INSERT INTO "BG"."BG_OPTIONS" VALUES ('7', 'siteConfig', 'description', '远方的灯塔是关注与分享互联网及服务端开发技术的个人博客，致力于Java后端开发及服务端技术、软件架构、微服务技术分享。同时也记录个人的一路点滴，所蕴含的包括前端、后端、数据库等知识，欢迎关注。');
INSERT INTO "BG"."BG_OPTIONS" VALUES ('8', 'siteConfig', 'beianinfo', '粤ICP备18023717号-1');
INSERT INTO "BG"."BG_OPTIONS" VALUES ('9', 'siteConfig', 'debug', 'true');
INSERT INTO "BG"."BG_OPTIONS" VALUES ('10', 'siteConfig', 'adminpath', 'a');

-- ----------------------------
-- Checks structure for table BG_OPTIONS
-- ----------------------------
ALTER TABLE "BG"."BG_OPTIONS" ADD CHECK ("OPTION_ID" IS NOT NULL);
ALTER TABLE "BG"."BG_OPTIONS" ADD CHECK ("OPTION_GROUP" IS NOT NULL);
ALTER TABLE "BG"."BG_OPTIONS" ADD CHECK ("OPTION_NAME" IS NOT NULL);
ALTER TABLE "BG"."BG_OPTIONS" ADD CHECK ("OPTION_VALUE" IS NOT NULL);

-- ----------------------------
-- Checks structure for table BG_POSTS
-- ----------------------------
ALTER TABLE "BG"."BG_POSTS" ADD CHECK ("POST_ID" IS NOT NULL);
ALTER TABLE "BG"."BG_POSTS" ADD CHECK ("POST_AUTHOR" IS NOT NULL);
ALTER TABLE "BG"."BG_POSTS" ADD CHECK ("POST_DATE" IS NOT NULL);
ALTER TABLE "BG"."BG_POSTS" ADD CHECK ("POST_CONTENT" IS NOT NULL);
ALTER TABLE "BG"."BG_POSTS" ADD CHECK ("POST_TITLE" IS NOT NULL);
ALTER TABLE "BG"."BG_POSTS" ADD CHECK ("POST_STATUS" IS NOT NULL);
ALTER TABLE "BG"."BG_POSTS" ADD CHECK ("COMMENT_STATUS" IS NOT NULL);
ALTER TABLE "BG"."BG_POSTS" ADD CHECK ("POST_PASSWORD" IS NOT NULL);
ALTER TABLE "BG"."BG_POSTS" ADD CHECK ("POST_NAME" IS NOT NULL);
ALTER TABLE "BG"."BG_POSTS" ADD CHECK ("POST_MODIFIED" IS NOT NULL);
ALTER TABLE "BG"."BG_POSTS" ADD CHECK ("POST_PARENT" IS NOT NULL);
ALTER TABLE "BG"."BG_POSTS" ADD CHECK ("MENU_ORDER" IS NOT NULL);
ALTER TABLE "BG"."BG_POSTS" ADD CHECK ("POST_TYPE" IS NOT NULL);
ALTER TABLE "BG"."BG_POSTS" ADD CHECK ("POST_MINE_TYPE" IS NOT NULL);
ALTER TABLE "BG"."BG_POSTS" ADD CHECK ("COMMENT_COUNT" IS NOT NULL);

-- ----------------------------
-- Records of SYS_ROLE
-- ----------------------------
INSERT INTO "BG"."SYS_ROLE" VALUES ('1', 'ROLE_ADMIN', '超级管理员');
INSERT INTO "BG"."SYS_ROLE" VALUES ('2', 'ROLE_USER', '普通用户');

-- ----------------------------
-- Checks structure for table SYS_ROLE
-- ----------------------------
ALTER TABLE "BG"."SYS_ROLE" ADD CHECK ("ID" IS NOT NULL);

-- ----------------------------
-- Records of SYS_ROLE_USER
-- ----------------------------
INSERT INTO "BG"."SYS_ROLE_USER" VALUES ('1', '1', '1');
INSERT INTO "BG"."SYS_ROLE_USER" VALUES ('2', '2', '2');

-- ----------------------------
-- Checks structure for table SYS_ROLE_USER
-- ----------------------------
ALTER TABLE "BG"."SYS_ROLE_USER" ADD CHECK ("ID" IS NOT NULL);

-- ----------------------------
-- Records of SYS_USER
-- ----------------------------
INSERT INTO "BG"."SYS_USER" VALUES ('1', 'admin', '$2a$10$ya3llY31E0vlNTA0Yhxam.IJowaGi2bCW766IdmquneMeIJLBroUW', '小布', '我是超级管理员', 'http://t.cn/RdPXvCZ', '18888888888', 'youweics@sina.com', 'abcdef', '0', TO_DATE('2018-03-28 16:05:59', 'YYYY-MM-DD HH24:MI:SS'), '这是备注呀');

-- ----------------------------
-- Checks structure for table SYS_USER
-- ----------------------------
ALTER TABLE "BG"."SYS_USER" ADD CHECK ("ID" IS NOT NULL);
ALTER TABLE "BG"."SYS_USER" ADD CHECK ("USER_NAME" IS NOT NULL);
ALTER TABLE "BG"."SYS_USER" ADD CHECK ("PASSWORD" IS NOT NULL);
ALTER TABLE "BG"."SYS_USER" ADD CHECK ("NICK_NAME" IS NOT NULL);
ALTER TABLE "BG"."SYS_USER" ADD CHECK ("USER_PROFILE" IS NOT NULL);
ALTER TABLE "BG"."SYS_USER" ADD CHECK ("USER_URL" IS NOT NULL);
ALTER TABLE "BG"."SYS_USER" ADD CHECK ("MOBILE" IS NOT NULL);
ALTER TABLE "BG"."SYS_USER" ADD CHECK ("EMAIL" IS NOT NULL);
ALTER TABLE "BG"."SYS_USER" ADD CHECK ("ACTIVATION_KEY" IS NOT NULL);
ALTER TABLE "BG"."SYS_USER" ADD CHECK ("STATUS" IS NOT NULL);
ALTER TABLE "BG"."SYS_USER" ADD CHECK ("USER_REGISTERED" IS NOT NULL);
