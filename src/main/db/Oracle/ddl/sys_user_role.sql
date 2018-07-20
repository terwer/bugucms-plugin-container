-- Create table
create table SYS_ROLE_USER
(
  id          NUMBER(11) not null,
  sys_user_id NVARCHAR2(255),
  sys_role_id NVARCHAR2(255)
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
comment on table SYS_ROLE_USER
  is '用户角色映射表';
-- Add comments to the columns
comment on column SYS_ROLE_USER.id
  is '主键ID';
comment on column SYS_ROLE_USER.sys_user_id
  is '用户ID';
comment on column SYS_ROLE_USER.sys_role_id
  is '角色ID';
