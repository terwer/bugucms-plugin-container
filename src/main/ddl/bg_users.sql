/*
 Navicat Premium Data Transfer

 Source Server         : tg-prd
 Source Server Type    : MySQL
 Source Server Version : 50556
 Source Host           : 39.104.66.135:3306
 Source Schema         : bugucms

 Target Server Type    : MySQL
 Target Server Version : 50556
 File Encoding         : 65001

 Date: 10/04/2018 18:27:46
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for bg_users
-- ----------------------------
DROP TABLE IF EXISTS `bg_users`;
CREATE TABLE `bg_users`  (
  `user_id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `user_name` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '登录名',
  `password` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '密码',
  `nick_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '昵称',
  `user_profile` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '简介',
  `user_url` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '首页链接',
  `mobile` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '手机',
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '邮箱',
  `activation_key` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '激活码',
  `status` int(10) NOT NULL DEFAULT 0 COMMENT '用户状态(1:启用,0:禁用)',
  `user_registered` datetime NOT NULL COMMENT '注册时间',
  PRIMARY KEY (`user_id`) USING BTREE,
  INDEX `user_login_key`(`user_name`) USING BTREE,
  INDEX `user_nickname`(`nick_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户信息表' ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;
