-- 用户角色映射表
DROP TABLE IF EXISTS `sys_role_user`;
CREATE TABLE `sys_role_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `sys_user_id` varchar(255) DEFAULT NULL COMMENT '用户ID',
  `sys_role_id` varchar(255) DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COMMENT='用户角色映射表';