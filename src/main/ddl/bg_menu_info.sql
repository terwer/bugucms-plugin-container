CREATE TABLE `bg_menu_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `menu_name` varchar(100) NOT NULL COMMENT '菜单名称',
  `menu_url` varchar(100) DEFAULT NULL COMMENT '菜单指向url',
  `menu_level` char(1) NOT NULL COMMENT '菜单等级',
  `parent_id` int(11) NOT NULL COMMENT '父级菜单id',
  `sort` smallint(6) NOT NULL DEFAULT '1' COMMENT '排序',
  `state` smallint(1) NOT NULL DEFAULT '1' COMMENT '是否有效：0无效，1有效',
  `creater` varchar(50) DEFAULT NULL COMMENT '菜单创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user` varchar(50) DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `class_info` varchar(50) DEFAULT NULL COMMENT '对应的class样式',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=157 DEFAULT CHARSET=utf8;
