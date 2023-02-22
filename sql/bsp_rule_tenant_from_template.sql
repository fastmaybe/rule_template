/*
 Navicat Premium Data Transfer

 Source Server         : CDM_WEB_UAT
 Source Server Type    : MySQL
 Source Server Version : 50739
 Source Host           : 10.148.246.66:3306
 Source Schema         : sabsp

 Target Server Type    : MySQL
 Target Server Version : 50739
 File Encoding         : 65001

 Date: 22/02/2023 11:28:32
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for bsp_rule_tenant_from_template
-- ----------------------------
DROP TABLE IF EXISTS `bsp_rule_tenant_from_template`;
CREATE TABLE `bsp_rule_tenant_from_template`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `tenant_id` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户',
  `is_copy` tinyint(2) NOT NULL DEFAULT 0 COMMENT '是否已经从模板copy：0-未copy；1-已copy',
  `create_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '创建人',
  `modified_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '修改人',
  `gmt_create` bigint(16) DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` bigint(16) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '租户模板记录' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
