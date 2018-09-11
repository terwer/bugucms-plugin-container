-- 创建用户表
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `user_name` varchar(60) NOT NULL DEFAULT '' COMMENT '登录名',
  `password` varchar(64) NOT NULL DEFAULT '' COMMENT '密码',
  `nick_name` varchar(50) NOT NULL DEFAULT '' COMMENT '昵称',
  `user_profile` varchar(250) NOT NULL DEFAULT '' COMMENT '简介',
  `user_url` varchar(100) NOT NULL DEFAULT '' COMMENT '首页链接',
  `mobile` varchar(11) NOT NULL DEFAULT '' COMMENT '手机',
  `email` varchar(100) NOT NULL DEFAULT '' COMMENT '邮箱',
  `activation_key` varchar(60) NOT NULL DEFAULT '' COMMENT '激活码',
  `status` int(10) NOT NULL DEFAULT '0' COMMENT '用户状态(1:启用,0:禁用)',
  `user_registered` datetime NOT NULL COMMENT '注册时间',
  `user_desc` varchar(255) DEFAULT '' COMMENT '用户备注',
  PRIMARY KEY (`id`),
  KEY `user_login_key` (`user_name`) USING BTREE,
  KEY `user_nickname` (`nick_name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COMMENT='用户信息表';