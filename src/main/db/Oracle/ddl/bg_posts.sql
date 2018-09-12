-- Create table
create table BG_POSTS
(
  post_id        NUMBER(20) not null,
  post_author    NUMBER(20) not null,
  post_date      DATE not null,
  post_content   NCLOB not null,
  post_title     NCLOB not null,
  post_status    NVARCHAR2(20) not null,
  comment_status NVARCHAR2(20) not null,
  post_password  NVARCHAR2(20) not null,
  post_name      NVARCHAR2(200) not null,
  post_modified  DATE not null,
  post_parent    NUMBER(20) not null,
  menu_order     NUMBER(11) not null,
  post_type      NVARCHAR2(20) not null,
  post_mine_type NVARCHAR2(100) not null,
  comment_count  NUMBER(20) not null
)
tablespace BGDATA
  pctfree 10
  initrans 1
  maxtrans 255;
-- Add comments to the table
comment on table BG_POSTS
  is '文章表';
-- Add comments to the columns
comment on column BG_POSTS.post_id
  is '文章ID';
comment on column BG_POSTS.post_author
  is '文章作者ID';
comment on column BG_POSTS.post_date
  is '发表时间';
comment on column BG_POSTS.post_content
  is '文章内容';
comment on column BG_POSTS.post_title
  is '文章标题';
comment on column BG_POSTS.post_status
  is '文章状态';
comment on column BG_POSTS.comment_status
  is '评论状态';
comment on column BG_POSTS.post_password
  is '文章密码';
comment on column BG_POSTS.post_name
  is '文章别名';
comment on column BG_POSTS.post_modified
  is '文章修改时间';
comment on column BG_POSTS.post_parent
  is '父类文章ID';
comment on column BG_POSTS.menu_order
  is '排序';
comment on column BG_POSTS.post_type
  is '文章类型';
comment on column BG_POSTS.post_mine_type
  is '文章媒体类型';
comment on column BG_POSTS.comment_count
  is '评论数';
