/*
 Navicat Premium Data Transfer

 Source Server         : 本地测试
 Source Server Type    : MySQL
 Source Server Version : 50717
 Source Host           : localhost:3306
 Source Schema         : apitest

 Target Server Type    : MySQL
 Target Server Version : 50717
 File Encoding         : 65001

 Date: 27/01/2021 18:57:04
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for api_config
-- ----------------------------
DROP TABLE IF EXISTS `test_config`;
CREATE TABLE `test_config`  (
  `id` int(11) NOT NULL,
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `headers` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `delete_flag` int(11) NOT NULL DEFAULT '1' COMMENT '删除标志, 1未删除 0已删除',
  `record_date` date NOT NULL DEFAULT '1000-01-01' COMMENT '创建日期',
  `created_at` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '创建时间',
  `created_by` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'system',
  `updated_at` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '更新时间',
  `updated_by` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for case_detail
-- ----------------------------
DROP TABLE IF EXISTS `case_detail`;
CREATE TABLE `case_detail`  (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL  COMMENT 'url',
  `path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '请求路径',
  `header` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '请求头',
  `method` char(10) CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '请求方式',
  `data` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '请求报文',
  `assertions` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '断言',
  `extract` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '提取参数',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `group_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用例组id',
  `delete_flag` int(11) NOT NULL DEFAULT '1' COMMENT '删除标志, 1未删除 0已删除',
  `record_date` date NOT NULL DEFAULT '1000-01-01' COMMENT '创建日期',
  `created_at` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '创建时间',
  `created_by` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'system',
  `updated_at` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '更新时间',
  `updated_by` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',

  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 37 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for case_group
-- ----------------------------
DROP TABLE IF EXISTS `case_group`;
CREATE TABLE `case_group`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
   `delete_flag` int(11) NOT NULL DEFAULT '1' COMMENT '删除标志, 1未删除 0已删除',
  `record_date` date NOT NULL DEFAULT '1000-01-01' COMMENT '创建日期',
  `created_at` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '创建时间',
  `created_by` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'system',
  `updated_at` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '更新时间',
  `updated_by` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for test_config
-- ----------------------------
DROP TABLE IF EXISTS `test_config`;
CREATE TABLE `test_config`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `base_header` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `base_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `group_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_define_param` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `delete_flag` int(11) NOT NULL DEFAULT '1' COMMENT '删除标志, 1未删除 0已删除',
  `record_date` date NOT NULL DEFAULT '1000-01-01' COMMENT '创建日期',
  `created_at` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '创建时间',
  `created_by` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'system',
  `updated_at` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '更新时间',
  `updated_by` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user_define_param
-- ----------------------------
DROP TABLE IF EXISTS `user_define_param`;
CREATE TABLE `user_define_param`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `case_group_id` int(11) NOT NULL,
  `user_define_parameters` tinytext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `delete_flag` int(11) NOT NULL DEFAULT '1' COMMENT '删除标志, 1未删除 0已删除',
  `record_date` date NOT NULL DEFAULT '1000-01-01' COMMENT '创建日期',
  `created_at` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '创建时间',
  `created_by` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'system',
  `updated_at` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '更新时间',
  `updated_by` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_account` (
  `id` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL,
  `user_id` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL,
  `account` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '帐号即工号',
  `password` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '密码',
  `created_at` datetime(3) DEFAULT CURRENT_TIMESTAMP(3),
  `updated_at` datetime(3) DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
  `record_date` date DEFAULT '1000-01-01',
  `created_by` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '创建人',
  `salt` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '密码加密盐',
  `status` tinyint(4) DEFAULT '1' COMMENT '-1被删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `merp_account_UN` (`account`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

SET FOREIGN_KEY_CHECKS = 1;
