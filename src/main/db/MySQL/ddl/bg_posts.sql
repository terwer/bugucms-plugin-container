-- 文章表
DROP TABLE IF EXISTS `bg_posts`;
CREATE TABLE `bg_posts` (
  `post_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '文章ID',
  `post_author` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '文章作者ID',
  `post_date` datetime NOT NULL COMMENT '发表时间',
  `post_content` longtext NOT NULL COMMENT '文章内容',
  `post_title` text NOT NULL COMMENT '文章标题',
  `post_status` varchar(20) NOT NULL DEFAULT 'publish' COMMENT '文章状态',
  `comment_status` varchar(20) NOT NULL DEFAULT 'open' COMMENT '评论状态',
  `post_password` varchar(20) NOT NULL DEFAULT '' COMMENT '文章密码',
  `post_name` varchar(200) NOT NULL DEFAULT '' COMMENT '文章别名',
  `post_modified` datetime NOT NULL COMMENT '文章修改时间',
  `post_parent` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '父类文章ID',
  `menu_order` int(11) NOT NULL DEFAULT '0' COMMENT '排序',
  `post_type` varchar(20) NOT NULL DEFAULT 'post' COMMENT '文章类型',
  `post_mime_type` varchar(100) NOT NULL DEFAULT '' COMMENT '文章媒体类型',
  `comment_count` bigint(20) NOT NULL DEFAULT '0' COMMENT '评论数',
  PRIMARY KEY (`post_id`) USING BTREE,
  KEY `post_name` (`post_name`(191)) USING BTREE,
  KEY `type_status_date` (`post_type`,`post_status`,`post_date`,`post_id`) USING BTREE,
  KEY `post_parent` (`post_parent`) USING BTREE,
  KEY `post_author` (`post_author`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COMMENT='文章表';