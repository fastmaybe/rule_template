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

 Date: 22/02/2023 11:28:18
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for bsp_rule_operation_log
-- ----------------------------
DROP TABLE IF EXISTS `bsp_rule_operation_log`;
CREATE TABLE `bsp_rule_operation_log`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `tenant_id` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户',
  `rule_id` bigint(20) DEFAULT NULL COMMENT '规则id',
  `operation_type` tinyint(4) DEFAULT NULL COMMENT '某业务域操作类型',
  `operation_data` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci COMMENT '操作的数据',
  `operation_remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
  `operator_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '操作人id',
  `operator_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '操作人名称',
  `gmt_create` bigint(20) DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_operation_type`(`operation_type`) USING BTREE,
  INDEX `idx_tenant_create_tm`(`tenant_id`, `gmt_create`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1542 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '日志记录' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
