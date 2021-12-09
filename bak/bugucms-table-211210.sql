/*
 Navicat Premium Data Transfer

 Source Server         : aliyun
 Source Server Type    : MySQL
 Source Server Version : 50732
 Source Host           : rm-wz9w2id84p6w14i8zqo.mysql.rds.aliyuncs.com:3306
 Source Schema         : bugucms

 Target Server Type    : MySQL
 Target Server Version : 50732
 File Encoding         : 65001

 Date: 10/12/2021 03:49:58
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for bg_options
-- ----------------------------
DROP TABLE IF EXISTS `bg_options`;
CREATE TABLE `bg_options` (
  `option_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '配置ID',
  `option_group` varchar(64) NOT NULL DEFAULT '' COMMENT '配置组',
  `option_name` varchar(64) NOT NULL DEFAULT '' COMMENT '配置名',
  `option_value` longtext NOT NULL COMMENT '配置值',
  PRIMARY KEY (`option_id`),
  UNIQUE KEY `option_name` (`option_name`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='站点配置表';

-- ----------------------------
-- Table structure for bg_postmeta
-- ----------------------------
DROP TABLE IF EXISTS `bg_postmeta`;
CREATE TABLE `bg_postmeta` (
  `meta_key` varchar(255) DEFAULT NULL,
  `meta_value` varchar(255) DEFAULT NULL,
  `post_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for bg_posts
-- ----------------------------
DROP TABLE IF EXISTS `bg_posts`;
CREATE TABLE `bg_posts` (
  `post_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '文章ID',
  `post_author` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '文章作者ID',
  `post_date` datetime NOT NULL COMMENT '发表时间',
  `post_content` longtext NOT NULL COMMENT '文章内容',
  `post_title` text COMMENT '文章标题',
  `post_status` varchar(20) NOT NULL DEFAULT 'publish' COMMENT '文章状态',
  `comment_status` varchar(20) NOT NULL DEFAULT 'open' COMMENT '评论状态',
  `post_password` varchar(20) NOT NULL DEFAULT '' COMMENT '文章密码',
  `post_name` varchar(200) NOT NULL DEFAULT '' COMMENT '文章别名',
  `post_modified` datetime NOT NULL COMMENT '文章修改时间',
  `post_parent` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '父类文章ID',
  `menu_order` int(11) NOT NULL DEFAULT '0' COMMENT '排序',
  `post_type` varchar(20) NOT NULL DEFAULT 'post' COMMENT '文章类型',
  `post_mime_type` varchar(100) NOT NULL DEFAULT '' COMMENT '文章媒体类型',
  `comment_count` bigint(20) NOT NULL DEFAULT '0' COMMENT '评论数',
  `post_finished` int(11) DEFAULT '1',
  PRIMARY KEY (`post_id`),
  KEY `post_name` (`post_name`(191)),
  KEY `type_status_date` (`post_type`,`post_status`,`post_date`,`post_id`),
  KEY `post_parent` (`post_parent`),
  KEY `post_author` (`post_author`)
) ENGINE=InnoDB AUTO_INCREMENT=7935 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='文章表';

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `name` varchar(255) DEFAULT NULL COMMENT '角色名称',
  `cn_name` varchar(255) DEFAULT '' COMMENT '权限名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='角色表';

-- ----------------------------
-- Table structure for sys_role_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_user`;
CREATE TABLE `sys_role_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `sys_user_id` varchar(255) DEFAULT NULL COMMENT '用户ID',
  `sys_role_id` varchar(255) DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='用户角色映射表';

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `user_name` varchar(60) NOT NULL DEFAULT '' COMMENT '登录名',
  `password` varchar(64) NOT NULL DEFAULT '' COMMENT '密码',
  `nick_name` varchar(50) NOT NULL DEFAULT '' COMMENT '昵称',
  `user_profile` varchar(250) NOT NULL DEFAULT '' COMMENT '简介',
  `user_url` varchar(100) NOT NULL DEFAULT '' COMMENT '首页链接',
  `mobile` varchar(11) NOT NULL DEFAULT '' COMMENT '手机',
  `email` varchar(100) NOT NULL DEFAULT '' COMMENT '邮箱',
  `activation_key` varchar(60) NOT NULL DEFAULT '' COMMENT '激活码',
  `status` int(10) NOT NULL DEFAULT '0' COMMENT '用户状态(1:启用,0:禁用)',
  `user_registered` datetime NOT NULL COMMENT '注册时间',
  `user_desc` varchar(255) DEFAULT '' COMMENT '用户备注',
  PRIMARY KEY (`id`),
  KEY `user_login_key` (`user_name`),
  KEY `user_nickname` (`nick_name`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='用户信息表';

SET FOREIGN_KEY_CHECKS = 1;
