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

 Date: 22/02/2023 11:27:02
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for bsp_rule_dictionary
-- ----------------------------
DROP TABLE IF EXISTS `bsp_rule_dictionary`;
CREATE TABLE `bsp_rule_dictionary`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `scene` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '数据字典类型场景',
  `dict_key` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '字典key',
  `dict_value` varchar(4000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '字典value',
  `sort` int(11) DEFAULT NULL COMMENT '显示排序',
  `lang` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '语言：中文-zh-CN；英文-en',
  `remark` varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
  `status` tinyint(1) NOT NULL COMMENT '有效状态：0-无效；1-有效',
  `gmt_create` bigint(16) DEFAULT NULL COMMENT '创建时间',
  `creator` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '创建人',
  `gmt_modified` bigint(16) DEFAULT NULL COMMENT '更新时间',
  `updater` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uniq_scene_lang_key`(`scene`, `lang`, `dict_key`) USING BTREE,
  INDEX `idx_lang`(`lang`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 75 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '数据字典表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bsp_rule_dictionary
-- ----------------------------
INSERT INTO `bsp_rule_dictionary` VALUES (1, 'ruleType', '1', '基础规则', 1, 'zh', NULL, 1, 1662463150012, 'SYSTEM', 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_dictionary` VALUES (2, 'ruleType', '2', '复合规则', 2, 'zh', NULL, 1, 1662463150012, 'SYSTEM', 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_dictionary` VALUES (3, 'ruleType', '3', '特殊规则', 3, 'zh', NULL, 0, 1662463150012, 'SYSTEM', 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_dictionary` VALUES (4, 'ruleType', '1', 'Basic Rule', 1, 'en', NULL, 1, 1662463150012, 'SYSTEM', 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_dictionary` VALUES (5, 'ruleType', '2', 'Compound Rule', 2, 'en', NULL, 1, 1662463150012, 'SYSTEM', 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_dictionary` VALUES (6, 'ruleType', '3', 'Special Rule', 3, 'en', NULL, 0, 1662463150012, 'SYSTEM', 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_dictionary` VALUES (7, 'preRuleKey', 'senderCountry', 'sender country', 1, 'en', NULL, 1, 1662463150012, 'SYSTEM', 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_dictionary` VALUES (8, 'preRuleKey', 'receiverCountry', 'receiver country', 2, 'en', NULL, 1, 1662463150012, 'SYSTEM', 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_dictionary` VALUES (9, 'preRuleKey', 'shipmentType', 'shipment type', 3, 'en', NULL, 1, 1662463150012, 'SYSTEM', 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_dictionary` VALUES (10, 'preRuleKey', 'reservationInformation', 'reservation information', 4, 'en', NULL, 1, 1662463150012, 'SYSTEM', 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_dictionary` VALUES (11, 'preRuleKey', 'senderCity', 'sender city', 5, 'en', NULL, 1, 1662463150012, 'SYSTEM', 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_dictionary` VALUES (12, 'preRuleKey', 'receiverCity', 'receiver city', 6, 'en', NULL, 1, 1662463150012, 'SYSTEM', 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_dictionary` VALUES (13, 'preRuleKey', 'paymentMethod', 'payment method', 7, 'en', NULL, 1, 1662463150012, 'SYSTEM', 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_dictionary` VALUES (14, 'preRuleKey', 'accountNo', 'account no', 8, 'en', NULL, 1, 1662463150012, 'SYSTEM', 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_dictionary` VALUES (15, 'orderChannel', '1', 'WEB单票', 1, 'zh', NULL, 1, 1662463150012, 'SYSTEM', 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_dictionary` VALUES (16, 'orderChannel', '2', 'WEB批量', 2, 'zh', NULL, 1, 1662463150012, 'SYSTEM', 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_dictionary` VALUES (17, 'orderChannel', '3', 'JVGO', 3, 'zh', NULL, 1, 1662463150012, 'SYSTEM', 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_dictionary` VALUES (18, 'orderChannel', '4', 'API单个', 4, 'zh', NULL, 1, 1662463150012, 'SYSTEM', 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_dictionary` VALUES (19, 'orderChannel', '5', 'API批量', 5, 'zh', NULL, 1, 1662463150012, 'SYSTEM', 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_dictionary` VALUES (20, 'orderChannel', '1', 'web-single', 1, 'en', NULL, 1, 1662463150012, 'SYSTEM', 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_dictionary` VALUES (21, 'orderChannel', '2', 'web-batch', 2, 'en', NULL, 1, 1662463150012, 'SYSTEM', 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_dictionary` VALUES (22, 'orderChannel', '3', 'jvgo', 3, 'en', NULL, 1, 1662463150012, 'SYSTEM', 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_dictionary` VALUES (23, 'orderChannel', '4', 'api-single', 4, 'en', NULL, 1, 1662463150012, 'SYSTEM', 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_dictionary` VALUES (24, 'orderChannel', '5', 'api-batch', 5, 'en', NULL, 1, 1662463150012, 'SYSTEM', 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_dictionary` VALUES (25, 'condition', 'ISNULL', '为空', 1, 'zh', NULL, 1, 1662463150012, 'SYSTEM', 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_dictionary` VALUES (26, 'condition', 'NOT_NULL', '非空', 2, 'zh', NULL, 1, 1662463150012, 'SYSTEM', 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_dictionary` VALUES (27, 'condition', 'EQUALS', '等于', 3, 'zh', NULL, 1, 1662463150012, 'SYSTEM', 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_dictionary` VALUES (28, 'condition', 'NOT_EQUALS', '不等于', 4, 'zh', NULL, 1, 1662463150012, 'SYSTEM', 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_dictionary` VALUES (29, 'condition', 'IN_CONF', '在集合内', 5, 'zh', NULL, 1, 1662463150012, 'SYSTEM', 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_dictionary` VALUES (30, 'condition', 'NOT_IN_CONF', '不在集合内', 6, 'zh', NULL, 1, 1662463150012, 'SYSTEM', 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_dictionary` VALUES (31, 'condition', 'CONTAINS', '包含', 7, 'zh', NULL, 1, 1662463150012, 'SYSTEM', 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_dictionary` VALUES (32, 'condition', 'NOT_CONTAINS', '不包含', 8, 'zh', NULL, 1, 1662463150012, 'SYSTEM', 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_dictionary` VALUES (33, 'condition', 'STARTS_WITH', '前缀', 9, 'zh', NULL, 1, 1662463150012, 'SYSTEM', 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_dictionary` VALUES (34, 'condition', 'ENDS_WITH', '后缀', 10, 'zh', NULL, 1, 1662463150012, 'SYSTEM', 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_dictionary` VALUES (35, 'condition', 'MATCH', '匹配', 11, 'zh', NULL, 1, 1662463150012, 'SYSTEM', 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_dictionary` VALUES (36, 'condition', 'NOT_MATCH', '不匹配', 12, 'zh', NULL, 1, 1662463150012, 'SYSTEM', 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_dictionary` VALUES (37, 'condition', 'LESS', '小于', 13, 'zh', NULL, 1, 1662463150012, 'SYSTEM', 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_dictionary` VALUES (38, 'condition', 'GREATER', '大于', 14, 'zh', NULL, 1, 1662463150012, 'SYSTEM', 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_dictionary` VALUES (39, 'condition', 'BETWEEN', '值区间', 15, 'zh', NULL, 1, 1662463150012, 'SYSTEM', 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_dictionary` VALUES (40, 'condition', 'LENGTH_BETWEEN_WITH_BOTH', '长度区间', 16, 'zh', NULL, 1, 1662463150012, 'SYSTEM', 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_dictionary` VALUES (41, 'condition', 'REQUIRED', '必填', 17, 'zh', NULL, 1, 1662463150012, 'SYSTEM', 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_dictionary` VALUES (42, 'condition', 'ISNULL', 'Empty', 1, 'en', NULL, 1, 1662463150012, 'SYSTEM', 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_dictionary` VALUES (43, 'condition', 'NOT_NULL', 'Non-empty', 2, 'en', NULL, 1, 1662463150012, 'SYSTEM', 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_dictionary` VALUES (44, 'condition', 'EQUALS', 'Equal', 3, 'en', NULL, 1, 1662463150012, 'SYSTEM', 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_dictionary` VALUES (45, 'condition', 'NOT_EQUALS', 'Not equal', 4, 'en', NULL, 1, 1662463150012, 'SYSTEM', 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_dictionary` VALUES (46, 'condition', 'IN_CONF', 'Within the Collection', 5, 'en', NULL, 1, 1662463150012, 'SYSTEM', 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_dictionary` VALUES (47, 'condition', 'NOT_IN_CONF', 'Not in the Collection', 6, 'en', NULL, 1, 1662463150012, 'SYSTEM', 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_dictionary` VALUES (48, 'condition', 'CONTAINS', 'Include', 7, 'en', NULL, 1, 1662463150012, 'SYSTEM', 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_dictionary` VALUES (49, 'condition', 'NOT_CONTAINS', 'Not include', 8, 'en', NULL, 1, 1662463150012, 'SYSTEM', 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_dictionary` VALUES (50, 'condition', 'STARTS_WITH', 'Prefix', 9, 'en', NULL, 1, 1662463150012, 'SYSTEM', 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_dictionary` VALUES (51, 'condition', 'ENDS_WITH', 'Suffix', 10, 'en', NULL, 1, 1662463150012, 'SYSTEM', 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_dictionary` VALUES (52, 'condition', 'MATCH', 'Match', 11, 'en', NULL, 1, 1662463150012, 'SYSTEM', 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_dictionary` VALUES (53, 'condition', 'NOT_MATCH', 'Not Match', 12, 'en', NULL, 1, 1662463150012, 'SYSTEM', 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_dictionary` VALUES (54, 'condition', 'LESS', 'Less', 13, 'en', NULL, 1, 1662463150012, 'SYSTEM', 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_dictionary` VALUES (55, 'condition', 'GREATER', 'Greater', 14, 'en', NULL, 1, 1662463150012, 'SYSTEM', 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_dictionary` VALUES (56, 'condition', 'BETWEEN', 'Between', 15, 'en', NULL, 1, 1662463150012, 'SYSTEM', 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_dictionary` VALUES (57, 'condition', 'LENGTH_BETWEEN_WITH_BOTH', 'Length between', 16, 'en', NULL, 1, 1662463150012, 'SYSTEM', 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_dictionary` VALUES (58, 'condition', 'REQUIRED', 'required', 17, 'en', NULL, 1, 1662463150012, 'SYSTEM', 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_dictionary` VALUES (59, 'operationType', '1', '新增', 1, 'zh', NULL, 1, 1662463150012, 'SYSTEM', 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_dictionary` VALUES (60, 'operationType', '2', '修改', 2, 'zh', NULL, 1, 1662463150012, 'SYSTEM', 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_dictionary` VALUES (61, 'operationType', '3', '停用', 3, 'zh', NULL, 1, 1662463150012, 'SYSTEM', 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_dictionary` VALUES (62, 'operationType', '4', '启用', 4, 'zh', NULL, 1, 1662463150012, 'SYSTEM', 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_dictionary` VALUES (63, 'operationType', '1', 'Add', 1, 'en', NULL, 1, 1662463150012, 'SYSTEM', 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_dictionary` VALUES (64, 'operationType', '2', 'Update', 2, 'en', NULL, 1, 1662463150012, 'SYSTEM', 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_dictionary` VALUES (65, 'operationType', '3', 'Invalid', 3, 'en', NULL, 1, 1662463150012, 'SYSTEM', 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_dictionary` VALUES (66, 'operationType', '4', 'Valid', 4, 'en', NULL, 1, 1662463150012, 'SYSTEM', 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_dictionary` VALUES (67, 'preRuleKey', 'senderCountry', 'sender country', 1, 'zh', NULL, 1, 1662463150012, 'SYSTEM', 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_dictionary` VALUES (68, 'preRuleKey', 'receiverCountry', 'receiver country', 2, 'zh', NULL, 1, 1662463150012, 'SYSTEM', 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_dictionary` VALUES (69, 'preRuleKey', 'shipmentType', 'shipment type', 3, 'zh', NULL, 1, 1662463150012, 'SYSTEM', 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_dictionary` VALUES (70, 'preRuleKey', 'reservationInformation', 'reservation information', 4, 'zh', NULL, 1, 1662463150012, 'SYSTEM', 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_dictionary` VALUES (71, 'preRuleKey', 'senderCity', 'sender city', 5, 'zh', NULL, 1, 1662463150012, 'SYSTEM', 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_dictionary` VALUES (72, 'preRuleKey', 'receiverCity', 'receiver city', 6, 'zh', NULL, 1, 1662463150012, 'SYSTEM', 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_dictionary` VALUES (73, 'preRuleKey', 'paymentMethod', 'payment method', 7, 'zh', NULL, 1, 1662463150012, 'SYSTEM', 1662463150012, 'SYSTEM');
INSERT INTO `bsp_rule_dictionary` VALUES (74, 'preRuleKey', 'accountNo', 'account no', 8, 'zh', NULL, 1, 1662463150012, 'SYSTEM', 1662463150012, 'SYSTEM');

SET FOREIGN_KEY_CHECKS = 1;
