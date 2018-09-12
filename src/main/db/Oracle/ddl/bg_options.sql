-- Create table
create table BG_OPTIONS
(
  option_id    NUMBER(20) not null,
  option_group NVARCHAR2(64) not null,
  option_name  NVARCHAR2(64) not null,
  option_value NCLOB not null
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
comment on table BG_OPTIONS
  is '站点配置表';
-- Add comments to the columns
comment on column BG_OPTIONS.option_id
  is '配置ID';
comment on column BG_OPTIONS.option_group
  is '配置组';
comment on column BG_OPTIONS.option_name
  is '配置名';
comment on column BG_OPTIONS.option_value
  is '配置值';
