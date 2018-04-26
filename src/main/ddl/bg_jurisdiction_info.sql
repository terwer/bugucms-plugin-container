CREATE TABLE `bg_jurisdiction_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `jurisdiction_name` varchar(30) NOT NULL COMMENT '权限名称',
  `stores_id` int(11) DEFAULT NULL,
  `creater` varchar(30) NOT NULL COMMENT '创建者',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_user` varchar(30) DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=91 DEFAULT CHARSET=utf8;
