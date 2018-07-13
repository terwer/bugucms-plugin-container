-- 创建文章表
DROP TABLE IF EXISTS `bg_posts`;
CREATE TABLE `bg_posts`  (
  `post_id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '文章ID',
  `post_author` bigint(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '文章作者ID',
  `post_date` datetime NOT NULL COMMENT '发表时间',
  `post_content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '文章内容',
  `post_title` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '文章标题',
  `post_status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT 'publish' COMMENT '文章状态',
  `comment_status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT 'open' COMMENT '评论状态',
  `post_password` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '文章密码',
  `post_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '文章别名',
  `post_modified` datetime NOT NULL COMMENT '文章修改时间',
  `post_parent` bigint(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '父类文章ID',
  `menu_order` int(11) NOT NULL DEFAULT 0 COMMENT '排序',
  `post_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT 'post' COMMENT '文章类型',
  `post_mime_type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '文章媒体类型',
  `comment_count` bigint(20) NOT NULL DEFAULT 0 COMMENT '评论数',
  PRIMARY KEY (`post_id`) USING BTREE,
  INDEX `post_name`(`post_name`(191)) USING BTREE,
  INDEX `type_status_date`(`post_type`, `post_status`, `post_date`, `post_id`) USING BTREE,
  INDEX `post_parent`(`post_parent`) USING BTREE,
  INDEX `post_author`(`post_author`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '文章表' ROW_FORMAT = Compact;
