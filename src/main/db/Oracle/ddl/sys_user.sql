-- Create table
create table SYS_USER
(
  id              NUMBER(20) not null,
  user_name       NVARCHAR2(60) not null,
  password        NVARCHAR2(64) not null,
  nick_name       NVARCHAR2(50) not null,
  user_profile    NVARCHAR2(250) not null,
  user_url        NVARCHAR2(100) not null,
  mobile          NVARCHAR2(11) not null,
  email           NVARCHAR2(100) not null,
  activation_key  NVARCHAR2(60) not null,
  status          NUMBER(11) not null,
  user_registered DATE not null,
  user_desc       NVARCHAR2(255)
)
tablespace BGDATA
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
-- Add comments to the table
comment on table SYS_USER
  is '用户信息表';
-- Add comments to the columns
comment on column SYS_USER.id
  is '用户ID';
comment on column SYS_USER.user_name
  is '登录名';
comment on column SYS_USER.password
  is '密码';
comment on column SYS_USER.nick_name
  is '昵称';
comment on column SYS_USER.user_profile
  is '简介';
comment on column SYS_USER.user_url
  is '首页链接';
comment on column SYS_USER.mobile
  is '手机';
comment on column SYS_USER.email
  is '邮箱';
comment on column SYS_USER.activation_key
  is '激活码';
comment on column SYS_USER.status
  is '用户状态(1:启用,0:禁用)';
comment on column SYS_USER.user_registered
  is '注册时间';
comment on column SYS_USER.user_desc
  is '用户备注';
