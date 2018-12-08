-- 站点配置表
create table bg_options
(
  option_id    bigint unsigned auto_increment comment '配置ID'
    primary key,
  option_group varchar(64) default '' not null comment '配置组',
  option_name  varchar(64) default '' not null comment '配置名',
  option_value longtext               not null comment '配置值',
  constraint option_name
    unique (option_name)
)
  comment '站点配置表';