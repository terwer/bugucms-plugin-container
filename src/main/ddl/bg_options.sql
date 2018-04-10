-- 站点配置表
CREATE TABLE bg_options (
  option_id bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '配置ID',
  option_group varchar(64) NOT NULL DEFAULT '' COMMENT '配置组',
  option_name varchar(64) NOT NULL DEFAULT '' COMMENT '配置名',
  option_value longtext NOT NULL COMMENT '配置值',
  PRIMARY KEY (option_id) USING BTREE,
  UNIQUE KEY option_name (option_name) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='站点配置表';

