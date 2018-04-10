-- 用户信息表
CREATE TABLE `bg_users` (
  `user_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `user_name` varchar(60) NOT NULL DEFAULT '' COMMENT '登录名',
  `password` varchar(64) NOT NULL DEFAULT '' COMMENT '密码',
  `nick_name` varchar(50) NOT NULL DEFAULT '' COMMENT '昵称',
  `user_profile` varchar(250) NOT NULL DEFAULT '' COMMENT '简介',
  `user_url` varchar(100) NOT NULL DEFAULT '' COMMENT '首页链接',
  `mobile` varchar(11) NOT NULL DEFAULT '' COMMENT '手机',
  `email` varchar(100) NOT NULL DEFAULT '' COMMENT '邮箱',
  `activation_key` varchar(60) NOT NULL DEFAULT '' COMMENT '激活码',
  `status` int(10) NOT NULL DEFAULT '0' COMMENT '用户状态(1:启用,0:禁用)',
  `user_registered` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间',
  PRIMARY KEY (`user_id`) USING BTREE,
  KEY `user_login_key` (`user_name`) USING BTREE,
  KEY `user_nickname` (`nick_name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='用户信息表';
