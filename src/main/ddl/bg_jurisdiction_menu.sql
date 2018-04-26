CREATE TABLE `bg_jurisdiction_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `jurisdiction_id` int(11) NOT NULL COMMENT '对应的权限id',
  `menu_id` int(11) NOT NULL COMMENT '对应的菜单id',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=39737 DEFAULT CHARSET=utf8;
