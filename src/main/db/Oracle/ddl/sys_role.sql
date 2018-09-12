-- Create table
create table SYS_ROLE
(
  id      NUMBER(11) not null,
  name    NVARCHAR2(255),
  cn_name NVARCHAR2(255)
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
comment on table SYS_ROLE
  is '角色表';
-- Add comments to the columns
comment on column SYS_ROLE.id
  is '角色ID';
comment on column SYS_ROLE.name
  is '角色名称';
comment on column SYS_ROLE.cn_name
  is '权限名称';
