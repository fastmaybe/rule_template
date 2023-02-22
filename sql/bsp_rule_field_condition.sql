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

 Date: 22/02/2023 11:27:13
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for bsp_rule_field_condition
-- ----------------------------
DROP TABLE IF EXISTS `bsp_rule_field_condition`;
CREATE TABLE `bsp_rule_field_condition`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `field_key` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'key',
  `condition_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '条件',
  `config_size` int(4) DEFAULT NULL COMMENT '条件参数个数',
  `state` tinyint(2) NOT NULL DEFAULT 0 COMMENT '是否生效：0-否；1-是',
  `gmt_create` bigint(20) DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '创建人',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_field_key`(`field_key`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 101 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '字段规则key与条件关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bsp_rule_field_condition
-- ----------------------------
INSERT INTO `bsp_rule_field_condition` VALUES (1, 'valueRange', 'LESS', 1, 1, 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_field_condition` VALUES (2, 'valueRange', 'GREATER', 1, 1, 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_field_condition` VALUES (3, 'valueRange', 'BETWEEN_WITH_BOTH', 2, 1, 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_field_condition` VALUES (4, 'senderCountry', 'ISNULL', 0, 1, 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_field_condition` VALUES (5, 'senderCountry', 'NOT_NULL', 0, 1, 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_field_condition` VALUES (6, 'senderCountry', 'EQUALS', 1, 1, 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_field_condition` VALUES (7, 'senderCountry', 'NOT_EQUALS', 1, 1, 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_field_condition` VALUES (8, 'senderCountry', 'IN_CONF', -1, 1, 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_field_condition` VALUES (9, 'senderCountry', 'NOT_IN_CONF', -1, 1, 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_field_condition` VALUES (10, 'senderCountry', 'CONTAINS', 1, 1, 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_field_condition` VALUES (11, 'senderCountry', 'NOT_CONTAINS', 1, 1, 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_field_condition` VALUES (12, 'senderCountry', 'STARTS_WITH', 1, 1, 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_field_condition` VALUES (13, 'senderCountry', 'ENDS_WITH', 1, 1, 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_field_condition` VALUES (14, 'senderCountry', 'MATCH', 1, 1, 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_field_condition` VALUES (15, 'senderCountry', 'NOT_MATCH', 1, 1, 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_field_condition` VALUES (16, 'receiverCountry', 'ISNULL', 0, 1, 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_field_condition` VALUES (17, 'receiverCountry', 'NOT_NULL', 0, 1, 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_field_condition` VALUES (18, 'receiverCountry', 'EQUALS', 1, 1, 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_field_condition` VALUES (19, 'receiverCountry', 'NOT_EQUALS', 1, 1, 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_field_condition` VALUES (20, 'receiverCountry', 'IN_CONF', -1, 1, 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_field_condition` VALUES (21, 'receiverCountry', 'NOT_IN_CONF', -1, 1, 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_field_condition` VALUES (22, 'receiverCountry', 'CONTAINS', 1, 1, 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_field_condition` VALUES (23, 'receiverCountry', 'NOT_CONTAINS', 1, 1, 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_field_condition` VALUES (24, 'receiverCountry', 'STARTS_WITH', 1, 1, 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_field_condition` VALUES (25, 'receiverCountry', 'ENDS_WITH', 1, 1, 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_field_condition` VALUES (26, 'receiverCountry', 'MATCH', 1, 1, 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_field_condition` VALUES (27, 'receiverCountry', 'NOT_MATCH', 1, 1, 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_field_condition` VALUES (28, 'shipmentType', 'ISNULL', 0, 1, 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_field_condition` VALUES (29, 'shipmentType', 'NOT_NULL', 0, 1, 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_field_condition` VALUES (30, 'shipmentType', 'EQUALS', 1, 1, 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_field_condition` VALUES (31, 'shipmentType', 'NOT_EQUALS', 1, 1, 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_field_condition` VALUES (32, 'shipmentType', 'IN_CONF', -1, 1, 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_field_condition` VALUES (33, 'shipmentType', 'NOT_IN_CONF', -1, 1, 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_field_condition` VALUES (34, 'shipmentType', 'CONTAINS', 1, 1, 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_field_condition` VALUES (35, 'shipmentType', 'NOT_CONTAINS', 1, 1, 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_field_condition` VALUES (36, 'shipmentType', 'STARTS_WITH', 1, 1, 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_field_condition` VALUES (37, 'shipmentType', 'ENDS_WITH', 1, 1, 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_field_condition` VALUES (38, 'shipmentType', 'MATCH', 1, 1, 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_field_condition` VALUES (39, 'shipmentType', 'NOT_MATCH', 1, 1, 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_field_condition` VALUES (40, 'reservationInformation', 'ISNULL', 0, 1, 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_field_condition` VALUES (41, 'reservationInformation', 'NOT_NULL', 0, 1, 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_field_condition` VALUES (42, 'reservationInformation', 'EQUALS', 1, 1, 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_field_condition` VALUES (43, 'reservationInformation', 'NOT_EQUALS', 1, 1, 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_field_condition` VALUES (44, 'reservationInformation', 'IN_CONF', -1, 1, 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_field_condition` VALUES (45, 'reservationInformation', 'NOT_IN_CONF', -1, 1, 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_field_condition` VALUES (46, 'reservationInformation', 'CONTAINS', 1, 1, 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_field_condition` VALUES (47, 'reservationInformation', 'NOT_CONTAINS', 1, 1, 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_field_condition` VALUES (48, 'reservationInformation', 'STARTS_WITH', 1, 1, 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_field_condition` VALUES (49, 'reservationInformation', 'ENDS_WITH', 1, 1, 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_field_condition` VALUES (50, 'reservationInformation', 'MATCH', 1, 1, 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_field_condition` VALUES (51, 'reservationInformation', 'NOT_MATCH', 1, 1, 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_field_condition` VALUES (52, 'senderCity', 'ISNULL', 0, 1, 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_field_condition` VALUES (53, 'senderCity', 'NOT_NULL', 0, 1, 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_field_condition` VALUES (54, 'senderCity', 'EQUALS', 1, 1, 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_field_condition` VALUES (55, 'senderCity', 'NOT_EQUALS', 1, 1, 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_field_condition` VALUES (56, 'senderCity', 'IN_CONF', -1, 1, 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_field_condition` VALUES (57, 'senderCity', 'NOT_IN_CONF', -1, 1, 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_field_condition` VALUES (58, 'senderCity', 'CONTAINS', 1, 1, 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_field_condition` VALUES (59, 'senderCity', 'NOT_CONTAINS', 1, 1, 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_field_condition` VALUES (60, 'senderCity', 'STARTS_WITH', 1, 1, 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_field_condition` VALUES (61, 'senderCity', 'ENDS_WITH', 1, 1, 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_field_condition` VALUES (62, 'senderCity', 'MATCH', 1, 1, 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_field_condition` VALUES (63, 'senderCity', 'NOT_MATCH', 1, 1, 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_field_condition` VALUES (64, 'receiverCity', 'ISNULL', 0, 1, 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_field_condition` VALUES (65, 'receiverCity', 'NOT_NULL', 0, 1, 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_field_condition` VALUES (66, 'receiverCity', 'EQUALS', 1, 1, 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_field_condition` VALUES (67, 'receiverCity', 'NOT_EQUALS', 1, 1, 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_field_condition` VALUES (68, 'receiverCity', 'IN_CONF', -1, 1, 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_field_condition` VALUES (69, 'receiverCity', 'NOT_IN_CONF', -1, 1, 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_field_condition` VALUES (70, 'receiverCity', 'CONTAINS', 1, 1, 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_field_condition` VALUES (71, 'receiverCity', 'NOT_CONTAINS', 1, 1, 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_field_condition` VALUES (72, 'receiverCity', 'STARTS_WITH', 1, 1, 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_field_condition` VALUES (73, 'receiverCity', 'ENDS_WITH', 1, 1, 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_field_condition` VALUES (74, 'receiverCity', 'MATCH', 1, 1, 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_field_condition` VALUES (75, 'receiverCity', 'NOT_MATCH', 1, 1, 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_field_condition` VALUES (76, 'paymentMethod', 'ISNULL', 0, 1, 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_field_condition` VALUES (77, 'paymentMethod', 'NOT_NULL', 0, 1, 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_field_condition` VALUES (78, 'paymentMethod', 'EQUALS', 1, 1, 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_field_condition` VALUES (79, 'paymentMethod', 'NOT_EQUALS', 1, 1, 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_field_condition` VALUES (80, 'paymentMethod', 'IN_CONF', -1, 1, 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_field_condition` VALUES (81, 'paymentMethod', 'NOT_IN_CONF', -1, 1, 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_field_condition` VALUES (82, 'paymentMethod', 'CONTAINS', 1, 1, 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_field_condition` VALUES (83, 'paymentMethod', 'NOT_CONTAINS', 1, 1, 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_field_condition` VALUES (84, 'paymentMethod', 'STARTS_WITH', 1, 1, 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_field_condition` VALUES (85, 'paymentMethod', 'ENDS_WITH', 1, 1, 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_field_condition` VALUES (86, 'paymentMethod', 'MATCH', 1, 1, 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_field_condition` VALUES (87, 'paymentMethod', 'NOT_MATCH', 1, 1, 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_field_condition` VALUES (88, 'accountNo', 'ISNULL', 0, 1, 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_field_condition` VALUES (89, 'accountNo', 'NOT_NULL', 0, 1, 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_field_condition` VALUES (90, 'accountNo', 'EQUALS', 1, 1, 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_field_condition` VALUES (91, 'accountNo', 'NOT_EQUALS', 1, 1, 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_field_condition` VALUES (92, 'accountNo', 'IN_CONF', -1, 1, 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_field_condition` VALUES (93, 'accountNo', 'NOT_IN_CONF', -1, 1, 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_field_condition` VALUES (94, 'accountNo', 'CONTAINS', 1, 1, 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_field_condition` VALUES (95, 'accountNo', 'NOT_CONTAINS', 1, 1, 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_field_condition` VALUES (96, 'accountNo', 'STARTS_WITH', 1, 1, 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_field_condition` VALUES (97, 'accountNo', 'ENDS_WITH', 1, 1, 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_field_condition` VALUES (98, 'accountNo', 'MATCH', 1, 1, 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_field_condition` VALUES (99, 'accountNo', 'NOT_MATCH', 1, 1, 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_field_condition` VALUES (100, 'valueRange', 'IN_CONF', -1, 1, 1662463150012, 'SYSTEM');

SET FOREIGN_KEY_CHECKS = 1;
