--  创建站点选项表
DROP TABLE IF EXISTS `bg_options`;
CREATE TABLE `bg_options`  (
  `option_id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '配置ID',
  `option_group` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '配置组',
  `option_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '配置名',
  `option_value` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '配置值',
  PRIMARY KEY (`option_id`) USING BTREE,
  UNIQUE INDEX `option_name`(`option_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '站点配置表' ROW_FORMAT = Compact;
