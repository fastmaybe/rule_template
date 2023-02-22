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

 Date: 22/02/2023 11:28:44
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for bsp_rule_tip_content
-- ----------------------------
DROP TABLE IF EXISTS `bsp_rule_tip_content`;
CREATE TABLE `bsp_rule_tip_content`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `rule_id` bigint(20) NOT NULL COMMENT 'rule-id',
  `lang` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '语种',
  `content` varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '备注说明',
  `tenant_id` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户',
  `tip_type` tinyint(2) NOT NULL DEFAULT 0 COMMENT '前端/后端提示：0-后端；1-前端',
  `create_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '创建人',
  `modified_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '修改人',
  `gmt_create` bigint(16) DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` bigint(16) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uniq_rule_lang_type`(`rule_id`, `lang`, `tip_type`) USING BTREE,
  INDEX `idx_rule_id`(`rule_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1134 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '规则多语言响应提示' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bsp_rule_tip_content
-- ----------------------------
INSERT INTO `bsp_rule_tip_content` VALUES (433, 197, 'en', 'Necessary parameters should not be null： bspOrderSendPerson[sendCity]', NULL, 0, '', '', 1676449927543, 1676451034186);
INSERT INTO `bsp_rule_tip_content` VALUES (459, 223, 'en-us', 'Necessary parameters should not be null：bspOrderReceivePerson[receiveCountryCode]', NULL, 0, '', '', 1676454347819, 1676454954189);
INSERT INTO `bsp_rule_tip_content` VALUES (460, 222, 'en-us', 'Necessary parameters should not be null：bspOrderReceivePerson[receiveCountry]', NULL, 0, '', '', 1676453882340, 1676455023651);
INSERT INTO `bsp_rule_tip_content` VALUES (461, 224, 'en-us', 'Necessary parameters should not be null：bspOrderReceivePerson[receiveProvince]', NULL, 0, '', '', 1676455603927, 1676455603927);
INSERT INTO `bsp_rule_tip_content` VALUES (462, 225, 'en-us', 'Necessary parameters should not be null：bspOrderReceivePerson[receiveCity]', NULL, 0, '', '', 1676455664349, 1676455664349);
INSERT INTO `bsp_rule_tip_content` VALUES (463, 226, 'en-us', 'bspOrderReceivePerson[receiveCityCode]', NULL, 0, '', '', 1676455708130, 1676455708130);
INSERT INTO `bsp_rule_tip_content` VALUES (504, 227, 'en-us', 'bspOrderReceivePerson[receiveAddress]', NULL, 0, '', '', 1676455745829, 1676514268944);
INSERT INTO `bsp_rule_tip_content` VALUES (567, 313, 'en-us', 'bspOrderSendPerson[sendEmail]', NULL, 0, '', '', 1676529022647, 1676529022647);
INSERT INTO `bsp_rule_tip_content` VALUES (570, 334, 'en-us', 'Please enter correct cargo : totalLength', NULL, 0, '', '', 1676536559165, 1676536559165);
INSERT INTO `bsp_rule_tip_content` VALUES (573, 336, 'en-us', 'Please enter correct cargo : totalWidth', NULL, 0, '', '', 1676536727382, 1676536727382);
INSERT INTO `bsp_rule_tip_content` VALUES (576, 339, 'en-us', 'paymentType must is M or C', NULL, 0, '', '', 1676537405397, 1676537405397);
INSERT INTO `bsp_rule_tip_content` VALUES (582, 342, 'en-us', 'Necessary parameters should not be null：tenantId', NULL, 0, '', '', 1676539813591, 1676539813591);
INSERT INTO `bsp_rule_tip_content` VALUES (584, 343, 'en-us', 'Necessary parameters should not be null：localTime', NULL, 0, '', '', 1676539871488, 1676539932524);
INSERT INTO `bsp_rule_tip_content` VALUES (587, 346, 'en-us', 'Necessary parameters should not be null：limitTypeCode', NULL, 0, '', '', 1676540550037, 1676540550037);
INSERT INTO `bsp_rule_tip_content` VALUES (588, 347, 'en-us', 'Necessary parameters should not be null：parcelTotalVolume', NULL, 0, '', '', 1676540625737, 1676540625737);
INSERT INTO `bsp_rule_tip_content` VALUES (589, 348, 'en-us', 'Necessary parameters should not be null：dynamic6', NULL, 0, '', '', 1676540679866, 1676540679866);
INSERT INTO `bsp_rule_tip_content` VALUES (590, 349, 'en-us', 'Necessary parameters should not be null：isUnderCall', NULL, 0, '', '', 1676540775363, 1676540775363);
INSERT INTO `bsp_rule_tip_content` VALUES (593, 352, 'en-us', 'Necessary parameters should not be null：bspOrderCustoms[sendCountry]', NULL, 0, '', '', 1676541498740, 1676541498740);
INSERT INTO `bsp_rule_tip_content` VALUES (595, 354, 'en-us', 'bspOrderCustoms[receiveCountry]', NULL, 0, '', '', 1676541602957, 1676541602957);
INSERT INTO `bsp_rule_tip_content` VALUES (596, 355, 'en-us', 'Necessary parameters should not be null：bspOrderCustoms[receiveCityCode]', NULL, 0, '', '', 1676541671166, 1676541671166);
INSERT INTO `bsp_rule_tip_content` VALUES (597, 356, 'en-us', 'Necessary parameters should not be null：bspOrderCustoms[originCountry]', NULL, 0, '', '', 1676541740876, 1676541740876);
INSERT INTO `bsp_rule_tip_content` VALUES (600, 325, 'zh-cn', '请输入正确的邮箱号', NULL, 1, '', '', 1676544188185, 1676544188185);
INSERT INTO `bsp_rule_tip_content` VALUES (601, 325, 'en-us', 'Please enter the correct e-mail address', NULL, 1, '', '', 1676544188185, 1676544188185);
INSERT INTO `bsp_rule_tip_content` VALUES (602, 329, 'zh-cn', '请输入正确的邮箱号', NULL, 1, '', '', 1676544433239, 1676544433240);
INSERT INTO `bsp_rule_tip_content` VALUES (603, 329, 'en-us', 'Please enter the correct e-mail address', NULL, 1, '', '', 1676544433240, 1676544433240);
INSERT INTO `bsp_rule_tip_content` VALUES (604, 323, 'zh-cn', '请输入正确的邮箱号', NULL, 1, '', '', 1676544830102, 1676544830102);
INSERT INTO `bsp_rule_tip_content` VALUES (605, 323, 'en-us', 'Please enter the correct e-mail address', NULL, 1, '', '', 1676544830102, 1676544830102);
INSERT INTO `bsp_rule_tip_content` VALUES (677, 258, 'en', 'Necessary parameters should not be null：pricingType', 'AJ', 0, 'st', 'st', 1676458231658, 1676615043223);
INSERT INTO `bsp_rule_tip_content` VALUES (678, 258, 'en-us', 'Necessary parameters should not be null：pricingType(en-us)', 'AJ', 0, 'SYSTEM', 'admin', 1676455951095, 1676615043223);
INSERT INTO `bsp_rule_tip_content` VALUES (679, 435, 'en-us', 'Please enter the correct e-mail address', NULL, 1, 'SYSTEM', 'SYSTEM', 1676603440412, 1676615089155);
INSERT INTO `bsp_rule_tip_content` VALUES (680, 435, 'zh-cn', '请输入正确的邮箱号', NULL, 1, '', '', 1676615089155, 1676615089155);
INSERT INTO `bsp_rule_tip_content` VALUES (681, 470, 'en-us', 'Please enter the correct e-mail address', NULL, 1, 'SYSTEM', 'SYSTEM', 1676603443882, 1676615250713);
INSERT INTO `bsp_rule_tip_content` VALUES (682, 470, 'zh-cn', '请输入正确的邮箱号', NULL, 1, '', '', 1676615250713, 1676615250713);
INSERT INTO `bsp_rule_tip_content` VALUES (683, 446, 'en-us', 'Please enter the correct e-mail address', NULL, 1, 'SYSTEM', 'SYSTEM', 1676603441439, 1676615925918);
INSERT INTO `bsp_rule_tip_content` VALUES (684, 446, 'zh-cn', '请输入正确的邮箱号', NULL, 1, '', '', 1676615925919, 1676615925919);
INSERT INTO `bsp_rule_tip_content` VALUES (685, 439, 'zh-cn', '请输入区/县', NULL, 1, 'SYSTEM', 'SYSTEM', 1676603440585, 1676616553525);
INSERT INTO `bsp_rule_tip_content` VALUES (687, 440, 'zh-cn', '请输入国家', NULL, 1, 'SYSTEM', 'SYSTEM', 1676603440755, 1676616708816);
INSERT INTO `bsp_rule_tip_content` VALUES (688, 445, 'zh-cn', '请输入姓名', NULL, 1, 'SYSTEM', 'SYSTEM', 1676603441268, 1676616757721);
INSERT INTO `bsp_rule_tip_content` VALUES (689, 443, 'zh-cn', '请输入城市', NULL, 1, 'SYSTEM', 'SYSTEM', 1676603441095, 1676616796517);
INSERT INTO `bsp_rule_tip_content` VALUES (690, 442, 'zh-cn', '请输入地址', NULL, 1, 'SYSTEM', 'SYSTEM', 1676603440925, 1676616876223);
INSERT INTO `bsp_rule_tip_content` VALUES (691, 447, 'zh-cn', '请输入省份', NULL, 1, 'SYSTEM', 'SYSTEM', 1676603441608, 1676616896223);
INSERT INTO `bsp_rule_tip_content` VALUES (692, 464, 'zh-cn', '请输入区/县', NULL, 1, 'SYSTEM', 'SYSTEM', 1676603443202, 1676617044620);
INSERT INTO `bsp_rule_tip_content` VALUES (693, 472, 'zh-cn', '请输入地址', NULL, 1, 'SYSTEM', 'SYSTEM', 1676603444211, 1676617162511);
INSERT INTO `bsp_rule_tip_content` VALUES (694, 467, 'zh-cn', '请输入城市', NULL, 1, 'SYSTEM', 'SYSTEM', 1676603443532, 1676617245450);
INSERT INTO `bsp_rule_tip_content` VALUES (695, 471, 'zh-cn', '请输入省份', NULL, 1, 'SYSTEM', 'SYSTEM', 1676603444048, 1676617289514);
INSERT INTO `bsp_rule_tip_content` VALUES (696, 465, 'zh-cn', '请输入国家', NULL, 1, 'SYSTEM', 'SYSTEM', 1676603443366, 1676617320136);
INSERT INTO `bsp_rule_tip_content` VALUES (697, 469, 'zh-cn', '请输入姓名', NULL, 1, 'SYSTEM', 'SYSTEM', 1676603443717, 1676617356917);
INSERT INTO `bsp_rule_tip_content` VALUES (698, 452, 'zh-cn', '请输入币种', NULL, 1, 'SYSTEM', 'SYSTEM', 1676603442790, 1676618352383);
INSERT INTO `bsp_rule_tip_content` VALUES (699, 453, 'zh-cn', '请输入物品名称', NULL, 1, 'SYSTEM', 'SYSTEM', 1676603443031, 1676618422375);
INSERT INTO `bsp_rule_tip_content` VALUES (700, 450, 'zh-cn', '请输入单位', NULL, 1, 'SYSTEM', 'SYSTEM', 1676603442146, 1676618500322);
INSERT INTO `bsp_rule_tip_content` VALUES (701, 451, 'zh-cn', '请输入原产地', NULL, 1, 'SYSTEM', 'SYSTEM', 1676603442364, 1676618542351);
INSERT INTO `bsp_rule_tip_content` VALUES (702, 427, 'zh-cn', '请输入包裹重量', NULL, 1, 'SYSTEM', 'SYSTEM', 1676603439133, 1676618607364);
INSERT INTO `bsp_rule_tip_content` VALUES (703, 477, 'zh-cn', '请输入订单号', NULL, 1, 'SYSTEM', 'SYSTEM', 1676603444548, 1676618716164);
INSERT INTO `bsp_rule_tip_content` VALUES (704, 449, 'zh-cn', '请输入快件类型', NULL, 1, 'SYSTEM', 'SYSTEM', 1676603441976, 1676618766716);
INSERT INTO `bsp_rule_tip_content` VALUES (707, 431, 'zh-cn', '请输入地址', NULL, 1, 'SYSTEM', 'SYSTEM', 1676603439714, 1676619049290);
INSERT INTO `bsp_rule_tip_content` VALUES (708, 429, 'zh-cn', '请输入区/县', NULL, 1, 'SYSTEM', 'SYSTEM', 1676603439368, 1676619081568);
INSERT INTO `bsp_rule_tip_content` VALUES (709, 432, 'zh-cn', '请输入城市', NULL, 1, 'SYSTEM', 'SYSTEM', 1676603439895, 1676619118059);
INSERT INTO `bsp_rule_tip_content` VALUES (710, 434, 'zh-cn', '请输入省份', NULL, 1, 'SYSTEM', 'SYSTEM', 1676603440236, 1676619157701);
INSERT INTO `bsp_rule_tip_content` VALUES (711, 430, 'zh-cn', '请输入国家', NULL, 1, 'SYSTEM', 'SYSTEM', 1676603439544, 1676619200372);
INSERT INTO `bsp_rule_tip_content` VALUES (712, 433, 'zh-cn', '请输入姓名', NULL, 1, 'SYSTEM', 'SYSTEM', 1676603440067, 1676619232730);
INSERT INTO `bsp_rule_tip_content` VALUES (713, 479, 'en', 'Necessary parameters should not be null： bspOrderSendPerson.contactName', 'AJ', 0, 'SYSTEM', 'admin', 1676445048546, 1676861410058);
INSERT INTO `bsp_rule_tip_content` VALUES (714, 480, 'en', 'Necessary parameters should not be null： bspOrderSendPerson.sendCountryCode', 'AJ', 0, 'SYSTEM', 'admin', 1676445260462, 1676861410453);
INSERT INTO `bsp_rule_tip_content` VALUES (715, 481, 'en', 'Necessary parameters should not be null：bspOrderSendPerson.sendCountry', 'AJ', 0, 'SYSTEM', 'admin', 1676445323525, 1676861410545);
INSERT INTO `bsp_rule_tip_content` VALUES (716, 482, 'en', 'Necessary parameters should not be null：bspOrderSendPerson.sendProvince', 'AJ', 0, 'SYSTEM', 'admin', 1676445405447, 1676861410636);
INSERT INTO `bsp_rule_tip_content` VALUES (717, 483, 'en', 'Necessary parameters should not be null： bspOrderSendPerson[sendCity]', 'AJ', 0, 'SYSTEM', 'admin', 1676449927543, 1676861410649);
INSERT INTO `bsp_rule_tip_content` VALUES (718, 484, 'en', 'Necessary parameters should not be null：bspOrderSendPerson[sendCityCode]', 'AJ', 0, 'SYSTEM', 'admin', 1676450126750, 1676861410746);
INSERT INTO `bsp_rule_tip_content` VALUES (719, 485, 'en', 'Necessary parameters should not be null：bspOrderSendPerson[sendAddress]', 'AJ', 0, 'SYSTEM', 'admin', 1676450378253, 1676861410839);
INSERT INTO `bsp_rule_tip_content` VALUES (720, 486, 'en', 'Necessary parameters should not be null：bspOrderReceivePerson[receiveContact]', 'AJ', 0, 'SYSTEM', 'admin', 1676453805886, 1676861410853);
INSERT INTO `bsp_rule_tip_content` VALUES (721, 487, 'en-us', 'Necessary parameters should not be null：bspOrderReceivePerson[receiveCountry]', 'AJ', 0, 'SYSTEM', 'admin', 1676453882340, 1676861410934);
INSERT INTO `bsp_rule_tip_content` VALUES (722, 488, 'en-us', 'Necessary parameters should not be null：bspOrderReceivePerson[receiveCountryCode]', 'AJ', 0, 'SYSTEM', 'admin', 1676454347819, 1676861410946);
INSERT INTO `bsp_rule_tip_content` VALUES (723, 489, 'en-us', 'Necessary parameters should not be null：bspOrderReceivePerson[receiveProvince]', 'AJ', 0, 'SYSTEM', 'admin', 1676455603927, 1676861410960);
INSERT INTO `bsp_rule_tip_content` VALUES (724, 490, 'en-us', 'Necessary parameters should not be null：bspOrderReceivePerson[receiveCity]', 'AJ', 0, 'SYSTEM', 'admin', 1676455664349, 1676861411035);
INSERT INTO `bsp_rule_tip_content` VALUES (725, 491, 'en-us', 'bspOrderReceivePerson[receiveCityCode]', 'AJ', 0, 'SYSTEM', 'admin', 1676455708130, 1676861411050);
INSERT INTO `bsp_rule_tip_content` VALUES (726, 492, 'en-us', 'bspOrderReceivePerson[receiveAddress]', 'AJ', 0, 'SYSTEM', 'admin', 1676455745829, 1676861411065);
INSERT INTO `bsp_rule_tip_content` VALUES (727, 493, 'en-us', 'Please enter correct cargo : parcelTotalWeight', 'AJ', 0, 'SYSTEM', 'admin', 1676535012654, 1676861411144);
INSERT INTO `bsp_rule_tip_content` VALUES (728, 501, 'en-us', 'Please enter correct cargo : parcelQuantity', 'AJ', 0, 'SYSTEM', 'admin', 1676534785954, 1676861411359);
INSERT INTO `bsp_rule_tip_content` VALUES (729, 502, 'en-us', 'Please enter correct cargo : totalLength', 'AJ', 0, 'SYSTEM', 'admin', 1676536559165, 1676861411450);
INSERT INTO `bsp_rule_tip_content` VALUES (730, 503, 'en-us', 'Please enter correct cargo : totalWidth', 'AJ', 0, 'SYSTEM', 'admin', 1676536727382, 1676861411466);
INSERT INTO `bsp_rule_tip_content` VALUES (731, 504, 'en-us', 'Please enter correct cargo : totalHeightgth', 'AJ', 0, 'SYSTEM', 'admin', 1676536863436, 1676861411545);
INSERT INTO `bsp_rule_tip_content` VALUES (733, 506, 'en-us', 'paymentType must is M or C', 'AJ', 0, 'SYSTEM', 'admin', 1676537405397, 1676861411645);
INSERT INTO `bsp_rule_tip_content` VALUES (734, 507, 'en-us', 'Necessary parameters should not be null：referenceNo1 \\n\nreferenceNo1 length cannot exceed 64 bits', 'AJ', 0, 'SYSTEM', 'admin', 1676538064547, 1676861411740);
INSERT INTO `bsp_rule_tip_content` VALUES (736, 509, 'en-us', 'Necessary parameters should not be null：tenantId', 'AJ', 0, 'SYSTEM', 'admin', 1676539813591, 1676861411841);
INSERT INTO `bsp_rule_tip_content` VALUES (737, 510, 'en-us', 'Necessary parameters should not be null：localTime', 'AJ', 0, 'SYSTEM', 'admin', 1676539871488, 1676861411852);
INSERT INTO `bsp_rule_tip_content` VALUES (738, 511, 'en-us', 'Necessary parameters should not be null：timeZone\ntimeZone length cannot exceed 32 bits', 'AJ', 0, 'SYSTEM', 'admin', 1676540208927, 1676861411945);
INSERT INTO `bsp_rule_tip_content` VALUES (740, 513, 'en-us', 'Necessary parameters should not be null：limitTypeCode', 'AJ', 0, 'SYSTEM', 'admin', 1676540550037, 1676861412060);
INSERT INTO `bsp_rule_tip_content` VALUES (741, 514, 'en-us', 'Necessary parameters should not be null：parcelTotalVolume', 'AJ', 0, 'SYSTEM', 'admin', 1676540625737, 1676861412149);
INSERT INTO `bsp_rule_tip_content` VALUES (742, 515, 'en-us', 'Necessary parameters should not be null：dynamic6', 'AJ', 0, 'SYSTEM', 'admin', 1676540679866, 1676861412161);
INSERT INTO `bsp_rule_tip_content` VALUES (743, 516, 'en-us', 'Necessary parameters should not be null：isUnderCall', 'AJ', 0, 'SYSTEM', 'admin', 1676540775363, 1676861412243);
INSERT INTO `bsp_rule_tip_content` VALUES (745, 518, 'en-us', 'Necessary parameters should not be null：bspOrderCustoms[sendZipCode]', 'AJ', 0, 'SYSTEM', 'admin', 1676541443989, 1676861412346);
INSERT INTO `bsp_rule_tip_content` VALUES (746, 519, 'en-us', 'Necessary parameters should not be null：bspOrderCustoms[sendCountry]', 'AJ', 0, 'SYSTEM', 'admin', 1676541498740, 1676861412437);
INSERT INTO `bsp_rule_tip_content` VALUES (747, 520, 'en-us', 'Necessary parameters should not be null：bspOrderCustoms[receiveZipCode]', 'AJ', 0, 'SYSTEM', 'admin', 1676541555512, 1676861412449);
INSERT INTO `bsp_rule_tip_content` VALUES (748, 521, 'en-us', 'bspOrderCustoms[receiveCountry]', 'AJ', 0, 'SYSTEM', 'admin', 1676541602957, 1676861412538);
INSERT INTO `bsp_rule_tip_content` VALUES (749, 522, 'en-us', 'Necessary parameters should not be null：bspOrderCustoms[receiveCityCode]', 'AJ', 0, 'SYSTEM', 'admin', 1676541671166, 1676861412549);
INSERT INTO `bsp_rule_tip_content` VALUES (750, 523, 'en-us', 'Necessary parameters should not be null：bspOrderCustoms[originCountry]', 'AJ', 0, 'SYSTEM', 'admin', 1676541740876, 1676861412559);
INSERT INTO `bsp_rule_tip_content` VALUES (793, 633, 'en', 'Necessary parameters should not be null： bspOrderSendPerson.contactName', 'ts', 0, 'SYSTEM', 'admin', 1676445048546, 1676862009845);
INSERT INTO `bsp_rule_tip_content` VALUES (794, 634, 'en', 'Necessary parameters should not be null： bspOrderSendPerson.sendCountryCode', 'ts', 0, 'SYSTEM', 'admin', 1676445260462, 1676862009857);
INSERT INTO `bsp_rule_tip_content` VALUES (795, 635, 'en', 'Necessary parameters should not be null：bspOrderSendPerson.sendCountry', 'ts', 0, 'SYSTEM', 'admin', 1676445323525, 1676862009938);
INSERT INTO `bsp_rule_tip_content` VALUES (796, 636, 'en', 'Necessary parameters should not be null：bspOrderSendPerson.sendProvince', 'ts', 0, 'SYSTEM', 'admin', 1676445405447, 1676862009946);
INSERT INTO `bsp_rule_tip_content` VALUES (797, 637, 'en', 'Necessary parameters should not be null： bspOrderSendPerson[sendCity]', 'ts', 0, 'SYSTEM', 'admin', 1676449927543, 1676862009952);
INSERT INTO `bsp_rule_tip_content` VALUES (798, 638, 'en', 'Necessary parameters should not be null：bspOrderSendPerson[sendCityCode]', 'ts', 0, 'SYSTEM', 'admin', 1676450126750, 1676862009958);
INSERT INTO `bsp_rule_tip_content` VALUES (799, 639, 'en', 'Necessary parameters should not be null：bspOrderSendPerson[sendAddress]', 'ts', 0, 'SYSTEM', 'admin', 1676450378253, 1676862010040);
INSERT INTO `bsp_rule_tip_content` VALUES (800, 640, 'en', 'Necessary parameters should not be null：bspOrderReceivePerson[receiveContact]', 'ts', 0, 'SYSTEM', 'admin', 1676453805886, 1676862010052);
INSERT INTO `bsp_rule_tip_content` VALUES (801, 641, 'en-us', 'Necessary parameters should not be null：bspOrderReceivePerson[receiveCountry]', 'ts', 0, 'SYSTEM', 'admin', 1676453882340, 1676862010060);
INSERT INTO `bsp_rule_tip_content` VALUES (802, 642, 'en-us', 'Necessary parameters should not be null：bspOrderReceivePerson[receiveCountryCode]', 'ts', 0, 'SYSTEM', 'admin', 1676454347819, 1676862010140);
INSERT INTO `bsp_rule_tip_content` VALUES (803, 643, 'en-us', 'Necessary parameters should not be null：bspOrderReceivePerson[receiveProvince]', 'ts', 0, 'SYSTEM', 'admin', 1676455603927, 1676862010149);
INSERT INTO `bsp_rule_tip_content` VALUES (804, 644, 'en-us', 'Necessary parameters should not be null：bspOrderReceivePerson[receiveCity]', 'ts', 0, 'SYSTEM', 'admin', 1676455664349, 1676862010237);
INSERT INTO `bsp_rule_tip_content` VALUES (805, 645, 'en-us', 'bspOrderReceivePerson[receiveCityCode]', 'ts', 0, 'SYSTEM', 'admin', 1676455708130, 1676862010252);
INSERT INTO `bsp_rule_tip_content` VALUES (806, 646, 'en-us', 'bspOrderReceivePerson[receiveAddress]', 'ts', 0, 'SYSTEM', 'admin', 1676455745829, 1676862010259);
INSERT INTO `bsp_rule_tip_content` VALUES (807, 647, 'en-us', 'Please enter correct cargo : parcelTotalWeight', 'ts', 0, 'SYSTEM', 'admin', 1676535012654, 1676862010337);
INSERT INTO `bsp_rule_tip_content` VALUES (808, 655, 'en-us', 'Please enter correct cargo : parcelQuantity', 'ts', 0, 'SYSTEM', 'admin', 1676534785954, 1676862010390);
INSERT INTO `bsp_rule_tip_content` VALUES (809, 656, 'en-us', 'Please enter correct cargo : totalLength', 'ts', 0, 'SYSTEM', 'admin', 1676536559165, 1676862010442);
INSERT INTO `bsp_rule_tip_content` VALUES (810, 657, 'en-us', 'Please enter correct cargo : totalWidth', 'ts', 0, 'SYSTEM', 'admin', 1676536727382, 1676862010452);
INSERT INTO `bsp_rule_tip_content` VALUES (811, 658, 'en-us', 'Please enter correct cargo : totalHeightgth', 'ts', 0, 'SYSTEM', 'admin', 1676536863436, 1676862010459);
INSERT INTO `bsp_rule_tip_content` VALUES (812, 659, 'en-us', 'payMethod must is 1 or 2 or 3', 'ts', 0, 'SYSTEM', 'admin', 1676537289748, 1676862010467);
INSERT INTO `bsp_rule_tip_content` VALUES (813, 660, 'en-us', 'paymentType must is M or C', 'ts', 0, 'SYSTEM', 'admin', 1676537405397, 1676862010485);
INSERT INTO `bsp_rule_tip_content` VALUES (814, 661, 'en-us', 'Necessary parameters should not be null：referenceNo1 \\n\nreferenceNo1 length cannot exceed 64 bits', 'ts', 0, 'SYSTEM', 'admin', 1676538064547, 1676862010498);
INSERT INTO `bsp_rule_tip_content` VALUES (815, 662, 'en-us', 'referenceNo2 length cannot exceed 64 bits', 'ts', 0, 'SYSTEM', 'admin', 1676539218437, 1676862010542);
INSERT INTO `bsp_rule_tip_content` VALUES (816, 663, 'en-us', 'Necessary parameters should not be null：tenantId', 'ts', 0, 'SYSTEM', 'admin', 1676539813591, 1676862010551);
INSERT INTO `bsp_rule_tip_content` VALUES (817, 664, 'en-us', 'Necessary parameters should not be null：localTime', 'ts', 0, 'SYSTEM', 'admin', 1676539871488, 1676862010561);
INSERT INTO `bsp_rule_tip_content` VALUES (818, 665, 'en-us', 'Necessary parameters should not be null：timeZone\ntimeZone length cannot exceed 32 bits', 'ts', 0, 'SYSTEM', 'admin', 1676540208927, 1676862010638);
INSERT INTO `bsp_rule_tip_content` VALUES (819, 666, 'en-us', 'Necessary parameters should not be null：productCode\nproductCode length cannot exceed 10 bits', 'ts', 0, 'SYSTEM', 'admin', 1676540433064, 1676862010647);
INSERT INTO `bsp_rule_tip_content` VALUES (820, 667, 'en-us', 'Necessary parameters should not be null：limitTypeCode', 'ts', 0, 'SYSTEM', 'admin', 1676540550037, 1676862010657);
INSERT INTO `bsp_rule_tip_content` VALUES (821, 668, 'en-us', 'Necessary parameters should not be null：parcelTotalVolume', 'ts', 0, 'SYSTEM', 'admin', 1676540625737, 1676862010742);
INSERT INTO `bsp_rule_tip_content` VALUES (822, 669, 'en-us', 'Necessary parameters should not be null：dynamic6', 'ts', 0, 'SYSTEM', 'admin', 1676540679866, 1676862010750);
INSERT INTO `bsp_rule_tip_content` VALUES (823, 670, 'en-us', 'Necessary parameters should not be null：isUnderCall', 'ts', 0, 'SYSTEM', 'admin', 1676540775363, 1676862010758);
INSERT INTO `bsp_rule_tip_content` VALUES (824, 671, 'en-us', 'operateType must is 1 or 2', 'ts', 0, 'SYSTEM', 'admin', 1676540874599, 1676862010844);
INSERT INTO `bsp_rule_tip_content` VALUES (825, 672, 'en-us', 'Necessary parameters should not be null：bspOrderCustoms[sendZipCode]', 'ts', 0, 'SYSTEM', 'admin', 1676541443989, 1676862010850);
INSERT INTO `bsp_rule_tip_content` VALUES (826, 673, 'en-us', 'Necessary parameters should not be null：bspOrderCustoms[sendCountry]', 'ts', 0, 'SYSTEM', 'admin', 1676541498740, 1676862010858);
INSERT INTO `bsp_rule_tip_content` VALUES (827, 674, 'en-us', 'Necessary parameters should not be null：bspOrderCustoms[receiveZipCode]', 'ts', 0, 'SYSTEM', 'admin', 1676541555512, 1676862010945);
INSERT INTO `bsp_rule_tip_content` VALUES (828, 675, 'en-us', 'bspOrderCustoms[receiveCountry]', 'ts', 0, 'SYSTEM', 'admin', 1676541602957, 1676862010953);
INSERT INTO `bsp_rule_tip_content` VALUES (829, 676, 'en-us', 'Necessary parameters should not be null：bspOrderCustoms[receiveCityCode]', 'ts', 0, 'SYSTEM', 'admin', 1676541671166, 1676862011037);
INSERT INTO `bsp_rule_tip_content` VALUES (830, 677, 'en-us', 'Necessary parameters should not be null：bspOrderCustoms[originCountry]', 'ts', 0, 'SYSTEM', 'admin', 1676541740876, 1676862011050);
INSERT INTO `bsp_rule_tip_content` VALUES (831, 678, 'en-us', 'Necessary parameters should not be null：bspOrderCustoms[taxPayMethod]', 'ts', 0, 'SYSTEM', 'admin', 1676541817741, 1676862011136);
INSERT INTO `bsp_rule_tip_content` VALUES (832, 679, 'en-us', 'Necessary parameters should not be null：bspOrderCustoms[taxPayType]', 'ts', 0, 'SYSTEM', 'admin', 1676541865724, 1676862011144);
INSERT INTO `bsp_rule_tip_content` VALUES (880, 335, 'af', 'fff', 'SF', 1, '', '', 1676865659537, 1676874833521);
INSERT INTO `bsp_rule_tip_content` VALUES (881, 335, 'ar-eg', 'vff', 'SF', 0, '', '', 1676865717161, 1676874833521);
INSERT INTO `bsp_rule_tip_content` VALUES (882, 335, 'zh-cn', 'rffffff', 'SF', 1, '', '', 1676536674285, 1676874833521);
INSERT INTO `bsp_rule_tip_content` VALUES (883, 335, 'zh-hk', 'ffff', 'SF', 0, '', '', 1676865503743, 1676874833521);
INSERT INTO `bsp_rule_tip_content` VALUES (887, 345, 'en-us', 'Necessary parameters should not be null：productCode\nproductCode length cannot exceed 10 bits', NULL, 0, '', '', 1676540433064, 1676875069466);
INSERT INTO `bsp_rule_tip_content` VALUES (888, 344, 'en-us', 'Necessary parameters should not be null：timeZone\ntimeZone length cannot exceed 32 bits', NULL, 0, '', '', 1676540208927, 1676875106050);
INSERT INTO `bsp_rule_tip_content` VALUES (892, 712, 'af', 'rrr', 'SF', 1, '', '', 1676875412619, 1676875412619);
INSERT INTO `bsp_rule_tip_content` VALUES (897, 512, 'en-us', 'Necessary parameters should not be null：productCode, productCode length cannot exceed 10 bits', 'AJ', 0, 'SYSTEM', 'admin', 1676540433064, 1676876188774);
INSERT INTO `bsp_rule_tip_content` VALUES (903, 338, 'en-us', 'payMethod must is 1 or 2 or 3', NULL, 0, '', '', 1676537289748, 1676877067548);
INSERT INTO `bsp_rule_tip_content` VALUES (904, 199, 'en-us', 'Necessary parameters should not be null：bspOrderSendPerson[sendAddress]', NULL, 0, '', '', 1676450378253, 1676877776212);
INSERT INTO `bsp_rule_tip_content` VALUES (905, 324, 'en-us', 'Necessary parameters should not be null：bspOrderSendPerson[sendCounty]', NULL, 0, '', '', 1676876000087, 1676877782984);
INSERT INTO `bsp_rule_tip_content` VALUES (906, 189, 'en-us', 'Necessary parameters should not be null： bspOrderSendPerson.contactName', NULL, 0, '', '', 1676445048546, 1676878843946);
INSERT INTO `bsp_rule_tip_content` VALUES (907, 192, 'en-us', 'Necessary parameters should not be null：bspOrderSendPerson.sendProvince', NULL, 0, '', '', 1676445405447, 1676880229352);
INSERT INTO `bsp_rule_tip_content` VALUES (908, 191, 'en-us', 'Necessary parameters should not be null：bspOrderSendPerson.sendCountry', NULL, 0, '', '', 1676445323525, 1676880238944);
INSERT INTO `bsp_rule_tip_content` VALUES (909, 198, 'en-us', 'Necessary parameters should not be null：bspOrderSendPerson[sendCityCode]', NULL, 0, '', '', 1676450126750, 1676880248451);
INSERT INTO `bsp_rule_tip_content` VALUES (910, 190, 'en-us', 'Necessary parameters should not be null： bspOrderSendPerson.sendCountryCode', NULL, 0, '', '', 1676445260462, 1676880256038);
INSERT INTO `bsp_rule_tip_content` VALUES (920, 221, 'en-us', 'Necessary parameters should not be null：bspOrderReceivePerson[receiveContact]', NULL, 0, '', '', 1676453805886, 1676883357251);
INSERT INTO `bsp_rule_tip_content` VALUES (923, 544, 'zh-cn', '请选择货币类型', 'AJ', 1, '', '', 1676884197445, 1676884204175);
INSERT INTO `bsp_rule_tip_content` VALUES (924, 497, 'en-us', 'Necessary parameters should not be null：bspOrderCarGo[currency]', 'AJ', 0, '', '', 1676884304435, 1676884304435);
INSERT INTO `bsp_rule_tip_content` VALUES (932, 545, 'zh-cn', '请输入托寄物物品名称', 'AJ', 1, '', '', 1676884954945, 1676884962049);
INSERT INTO `bsp_rule_tip_content` VALUES (933, 500, 'en-us', 'Necessary parameters should not be null：bspOrderCarGo[name]', 'AJ', 0, '', '', 1676884985653, 1676884985653);
INSERT INTO `bsp_rule_tip_content` VALUES (934, 542, 'zh-cn', '请选择托寄物单位', 'AJ', 1, '', '', 1676885026746, 1676885026746);
INSERT INTO `bsp_rule_tip_content` VALUES (935, 494, 'en-us', 'Necessary parameters should not be null：bspOrderCarGo[unit]', 'AJ', 0, '', '', 1676885046235, 1676885046235);
INSERT INTO `bsp_rule_tip_content` VALUES (936, 543, 'zh-cn', '请选择物品原产地国别', 'AJ', 1, '', '', 1676885083182, 1676885083182);
INSERT INTO `bsp_rule_tip_content` VALUES (937, 495, 'en-us', 'Necessary parameters should not be null：bspOrderCarGo[sourceArea]', 'AJ', 0, '', '', 1676885098038, 1676885098038);
INSERT INTO `bsp_rule_tip_content` VALUES (938, 327, 'en-us', 'Necessary parameters should not be null：expressType', NULL, 0, '', '', 1676885140883, 1676885140883);
INSERT INTO `bsp_rule_tip_content` VALUES (939, 314, 'en-us', 'Please enter correct cargo : parcelTotalWeight', NULL, 0, '', '', 1676535012654, 1676885377741);
INSERT INTO `bsp_rule_tip_content` VALUES (941, 505, 'en-us', 'payMethod must is 1 or 2 or 3', 'AJ', 0, 'SYSTEM', 'admin', 1676537289748, 1676886540737);
INSERT INTO `bsp_rule_tip_content` VALUES (942, 531, 'zh-cn', '姓名必填', 'AJ', 1, '', '', 1676886847125, 1676886847125);
INSERT INTO `bsp_rule_tip_content` VALUES (943, 533, 'zh-cn', '电子邮件格式不符合要求', 'AJ', 1, '', '', 1676886881309, 1676886881309);
INSERT INTO `bsp_rule_tip_content` VALUES (944, 532, 'zh-cn', '省份必填', 'AJ', 1, '', '', 1676886896926, 1676886896926);
INSERT INTO `bsp_rule_tip_content` VALUES (945, 528, 'zh-cn', '国家必填', 'AJ', 1, '', '', 1676886913583, 1676886913583);
INSERT INTO `bsp_rule_tip_content` VALUES (946, 530, 'zh-cn', '城市必填', 'AJ', 1, '', '', 1676886926936, 1676886926936);
INSERT INTO `bsp_rule_tip_content` VALUES (947, 529, 'zh-cn', '地址必填', 'AJ', 1, '', '', 1676887100347, 1676887100347);
INSERT INTO `bsp_rule_tip_content` VALUES (948, 527, 'zh-cn', '区/县必填', 'AJ', 1, '', '', 1676887113308, 1676887113308);
INSERT INTO `bsp_rule_tip_content` VALUES (949, 508, 'en-us', 'referenceNo2 length cannot exceed 64 bits', 'AJ', 0, 'SYSTEM', 'admin', 1676539218437, 1676887245810);
INSERT INTO `bsp_rule_tip_content` VALUES (960, 517, 'en-us', 'operateType must is 1 or 2', 'AJ', 0, 'SYSTEM', 'admin', 1676540874599, 1676887934141);
INSERT INTO `bsp_rule_tip_content` VALUES (961, 524, 'en-us', 'Necessary parameters should not be null：bspOrderCustoms[taxPayMethod]', 'AJ', 0, 'SYSTEM', 'admin', 1676541817741, 1676888290545);
INSERT INTO `bsp_rule_tip_content` VALUES (962, 525, 'en-us', 'Necessary parameters should not be null：bspOrderCustoms[taxPayType]', 'AJ', 0, 'SYSTEM', 'admin', 1676541865724, 1676888344457);
INSERT INTO `bsp_rule_tip_content` VALUES (1016, 714, 'af', 'vvvvvvv', 'SF', 0, '', '', 1676887738334, 1676945041293);
INSERT INTO `bsp_rule_tip_content` VALUES (1017, 714, 'af', '231', 'SF', 1, '', '', 1676876288279, 1676945041293);
INSERT INTO `bsp_rule_tip_content` VALUES (1018, 714, 'ar-bh', 'ddd', 'SF', 1, '', '', 1676887723034, 1676945041293);
INSERT INTO `bsp_rule_tip_content` VALUES (1022, 717, 'en-us', 'Necessary parameters should not be null： bspOrderSendPerson.contactName', 'FP', 0, 'SYSTEM', 'admin', 1676445048546, 1676948712020);
INSERT INTO `bsp_rule_tip_content` VALUES (1023, 718, 'en-us', 'Necessary parameters should not be null： bspOrderSendPerson.sendCountryCode', 'FP', 0, 'SYSTEM', 'admin', 1676445260462, 1676948712058);
INSERT INTO `bsp_rule_tip_content` VALUES (1024, 719, 'en-us', 'Necessary parameters should not be null：bspOrderSendPerson.sendCountry', 'FP', 0, 'SYSTEM', 'admin', 1676445323525, 1676948712142);
INSERT INTO `bsp_rule_tip_content` VALUES (1025, 720, 'en-us', 'Necessary parameters should not be null：bspOrderSendPerson.sendProvince', 'FP', 0, 'SYSTEM', 'admin', 1676445405447, 1676948712151);
INSERT INTO `bsp_rule_tip_content` VALUES (1026, 721, 'en', 'Necessary parameters should not be null： bspOrderSendPerson[sendCity]', 'FP', 0, 'SYSTEM', 'admin', 1676449927543, 1676948712159);
INSERT INTO `bsp_rule_tip_content` VALUES (1027, 722, 'en-us', 'Necessary parameters should not be null：bspOrderSendPerson[sendCityCode]', 'FP', 0, 'SYSTEM', 'admin', 1676450126750, 1676948712240);
INSERT INTO `bsp_rule_tip_content` VALUES (1028, 723, 'en-us', 'Necessary parameters should not be null：bspOrderSendPerson[sendAddress]', 'FP', 0, 'SYSTEM', 'admin', 1676450378253, 1676948712248);
INSERT INTO `bsp_rule_tip_content` VALUES (1029, 724, 'en-us', 'Necessary parameters should not be null：bspOrderReceivePerson[receiveContact]', 'FP', 0, 'SYSTEM', 'admin', 1676453805886, 1676948712254);
INSERT INTO `bsp_rule_tip_content` VALUES (1030, 725, 'en-us', 'Necessary parameters should not be null：bspOrderReceivePerson[receiveCountry]', 'FP', 0, 'SYSTEM', 'admin', 1676453882340, 1676948712261);
INSERT INTO `bsp_rule_tip_content` VALUES (1031, 726, 'en-us', 'Necessary parameters should not be null：bspOrderReceivePerson[receiveCountryCode]', 'FP', 0, 'SYSTEM', 'admin', 1676454347819, 1676948712336);
INSERT INTO `bsp_rule_tip_content` VALUES (1032, 727, 'en-us', 'Necessary parameters should not be null：bspOrderReceivePerson[receiveProvince]', 'FP', 0, 'SYSTEM', 'admin', 1676455603927, 1676948712346);
INSERT INTO `bsp_rule_tip_content` VALUES (1033, 728, 'en-us', 'Necessary parameters should not be null：bspOrderReceivePerson[receiveCity]', 'FP', 0, 'SYSTEM', 'admin', 1676455664349, 1676948712355);
INSERT INTO `bsp_rule_tip_content` VALUES (1034, 729, 'en-us', 'bspOrderReceivePerson[receiveCityCode]', 'FP', 0, 'SYSTEM', 'admin', 1676455708130, 1676948712363);
INSERT INTO `bsp_rule_tip_content` VALUES (1035, 730, 'en-us', 'bspOrderReceivePerson[receiveAddress]', 'FP', 0, 'SYSTEM', 'admin', 1676455745829, 1676948712444);
INSERT INTO `bsp_rule_tip_content` VALUES (1036, 731, 'en-us', 'Please enter correct cargo : parcelTotalWeight', 'FP', 0, 'SYSTEM', 'admin', 1676535012654, 1676948712453);
INSERT INTO `bsp_rule_tip_content` VALUES (1037, 734, 'en-us', 'Necessary parameters should not be null：bspOrderSendPerson[sendCounty]', 'FP', 0, 'SYSTEM', 'admin', 1676876000087, 1676948712473);
INSERT INTO `bsp_rule_tip_content` VALUES (1038, 736, 'en-us', 'Necessary parameters should not be null：expressType', 'FP', 0, 'SYSTEM', 'admin', 1676885140883, 1676948712545);
INSERT INTO `bsp_rule_tip_content` VALUES (1039, 737, 'en-us', 'Necessary parameters should not be null：bspOrderReceivePerson[receiveCounty]', 'FP', 0, 'SYSTEM', 'admin', 1676883468158, 1676948712552);
INSERT INTO `bsp_rule_tip_content` VALUES (1040, 739, 'en-us', 'Please enter correct cargo : parcelQuantity', 'FP', 0, 'SYSTEM', 'admin', 1676534785954, 1676948712577);
INSERT INTO `bsp_rule_tip_content` VALUES (1041, 740, 'en-us', 'Please enter correct cargo : totalLength', 'FP', 0, 'SYSTEM', 'admin', 1676536559165, 1676948712643);
INSERT INTO `bsp_rule_tip_content` VALUES (1042, 741, 'en-us', 'Please enter correct cargo : totalWidth', 'FP', 0, 'SYSTEM', 'admin', 1676536727382, 1676948712650);
INSERT INTO `bsp_rule_tip_content` VALUES (1044, 743, 'en-us', 'payMethod must is 1 or 2 or 3', 'FP', 0, 'SYSTEM', 'admin', 1676537289748, 1676948712667);
INSERT INTO `bsp_rule_tip_content` VALUES (1045, 744, 'en-us', 'paymentType must is M or C', 'FP', 0, 'SYSTEM', 'admin', 1676537405397, 1676948712674);
INSERT INTO `bsp_rule_tip_content` VALUES (1046, 745, 'en-us', 'Necessary parameters should not be null：referenceNo1 \\n\nreferenceNo1 length cannot exceed 64 bits', 'FP', 0, 'SYSTEM', 'admin', 1676538064547, 1676948712735);
INSERT INTO `bsp_rule_tip_content` VALUES (1048, 747, 'en-us', 'Necessary parameters should not be null：tenantId', 'FP', 0, 'SYSTEM', 'admin', 1676539813591, 1676948712753);
INSERT INTO `bsp_rule_tip_content` VALUES (1049, 748, 'en-us', 'Necessary parameters should not be null：localTime', 'FP', 0, 'SYSTEM', 'admin', 1676539871488, 1676948712838);
INSERT INTO `bsp_rule_tip_content` VALUES (1050, 749, 'en-us', 'Necessary parameters should not be null：timeZone\ntimeZone length cannot exceed 32 bits', 'FP', 0, 'SYSTEM', 'admin', 1676540208927, 1676948712847);
INSERT INTO `bsp_rule_tip_content` VALUES (1051, 750, 'en-us', 'Necessary parameters should not be null：productCode\nproductCode length cannot exceed 10 bits', 'FP', 0, 'SYSTEM', 'admin', 1676540433064, 1676948712855);
INSERT INTO `bsp_rule_tip_content` VALUES (1052, 751, 'en-us', 'Necessary parameters should not be null：limitTypeCode', 'FP', 0, 'SYSTEM', 'admin', 1676540550037, 1676948712938);
INSERT INTO `bsp_rule_tip_content` VALUES (1053, 752, 'en-us', 'Necessary parameters should not be null：parcelTotalVolume', 'FP', 0, 'SYSTEM', 'admin', 1676540625737, 1676948712950);
INSERT INTO `bsp_rule_tip_content` VALUES (1054, 753, 'en-us', 'Necessary parameters should not be null：dynamic6', 'FP', 0, 'SYSTEM', 'admin', 1676540679866, 1676948713040);
INSERT INTO `bsp_rule_tip_content` VALUES (1055, 754, 'en-us', 'Necessary parameters should not be null：isUnderCall', 'FP', 0, 'SYSTEM', 'admin', 1676540775363, 1676948713051);
INSERT INTO `bsp_rule_tip_content` VALUES (1056, 755, 'en-us', 'operateType must is 1 or 2', 'FP', 0, 'SYSTEM', 'admin', 1676540874599, 1676948713136);
INSERT INTO `bsp_rule_tip_content` VALUES (1057, 756, 'en-us', 'Necessary parameters should not be null：bspOrderCustoms[sendZipCode]', 'FP', 0, 'SYSTEM', 'admin', 1676541443989, 1676948713146);
INSERT INTO `bsp_rule_tip_content` VALUES (1058, 757, 'en-us', 'Necessary parameters should not be null：bspOrderCustoms[sendCountry]', 'FP', 0, 'SYSTEM', 'admin', 1676541498740, 1676948713154);
INSERT INTO `bsp_rule_tip_content` VALUES (1059, 758, 'en-us', 'Necessary parameters should not be null：bspOrderCustoms[receiveZipCode]', 'FP', 0, 'SYSTEM', 'admin', 1676541555512, 1676948713248);
INSERT INTO `bsp_rule_tip_content` VALUES (1060, 759, 'en-us', 'bspOrderCustoms[receiveCountry]', 'FP', 0, 'SYSTEM', 'admin', 1676541602957, 1676948713257);
INSERT INTO `bsp_rule_tip_content` VALUES (1061, 760, 'en-us', 'Necessary parameters should not be null：bspOrderCustoms[receiveCityCode]', 'FP', 0, 'SYSTEM', 'admin', 1676541671166, 1676948713337);
INSERT INTO `bsp_rule_tip_content` VALUES (1062, 761, 'en-us', 'Necessary parameters should not be null：bspOrderCustoms[originCountry]', 'FP', 0, 'SYSTEM', 'admin', 1676541740876, 1676948713346);
INSERT INTO `bsp_rule_tip_content` VALUES (1066, 357, 'en-us', 'taxPayMethod must is 1 or 2 or 3', NULL, 0, '', '', 1676541817741, 1676963749839);
INSERT INTO `bsp_rule_tip_content` VALUES (1067, 358, 'en-us', 'taxPaymentType must is M or C', NULL, 0, '', '', 1676541865724, 1676963792705);
INSERT INTO `bsp_rule_tip_content` VALUES (1068, 340, 'en-us', 'Necessary parameters should not be null：referenceNo1 and referenceNo1 length cannot exceed 64 bits', NULL, 0, '', '', 1676538064547, 1676963894280);
INSERT INTO `bsp_rule_tip_content` VALUES (1070, 337, 'en-us', 'Please enter correct cargo : totalHeightgth', NULL, 0, '', '', 1676536863436, 1676964182986);
INSERT INTO `bsp_rule_tip_content` VALUES (1071, 742, 'en-us', 'Please enter correct cargo : totalHeight', 'FP', 0, 'SYSTEM', 'admin', 1676536863436, 1676966252222);
INSERT INTO `bsp_rule_tip_content` VALUES (1072, 746, 'en-us', 'referenceNo2 length cannot exceed 64 bits', 'FP', 0, 'SYSTEM', 'admin', 1676539218437, 1676966368313);
INSERT INTO `bsp_rule_tip_content` VALUES (1074, 762, 'en-us', 'Necessary parameters should not be null：bspOrderCustoms[taxPayMethod]', 'FP', 0, 'SYSTEM', 'admin', 1676541817741, 1676966406057);
INSERT INTO `bsp_rule_tip_content` VALUES (1075, 763, 'en-us', 'Necessary parameters should not be null：bspOrderCustoms[taxPayType]', 'FP', 0, 'SYSTEM', 'admin', 1676541865724, 1676966428300);
INSERT INTO `bsp_rule_tip_content` VALUES (1081, 794, 'en-us', '非必填', 'TE', 0, '', '', 1676967764120, 1676968312425);
INSERT INTO `bsp_rule_tip_content` VALUES (1082, 795, 'en-us', 'Necessary parameters should not be null： bspOrderSendPerson.contactName', 'CL', 0, 'SYSTEM', 'admin', 1676445048546, 1676971719714);
INSERT INTO `bsp_rule_tip_content` VALUES (1083, 796, 'en-us', 'Necessary parameters should not be null： bspOrderSendPerson.sendCountryCode', 'CL', 0, 'SYSTEM', 'admin', 1676445260462, 1676971719908);
INSERT INTO `bsp_rule_tip_content` VALUES (1084, 797, 'en-us', 'Necessary parameters should not be null：bspOrderSendPerson.sendCountry', 'CL', 0, 'SYSTEM', 'admin', 1676445323525, 1676971719918);
INSERT INTO `bsp_rule_tip_content` VALUES (1085, 798, 'en-us', 'Necessary parameters should not be null：bspOrderSendPerson.sendProvince', 'CL', 0, 'SYSTEM', 'admin', 1676445405447, 1676971720017);
INSERT INTO `bsp_rule_tip_content` VALUES (1086, 799, 'en', 'Necessary parameters should not be null： bspOrderSendPerson[sendCity]', 'CL', 0, 'SYSTEM', 'admin', 1676449927543, 1676971720108);
INSERT INTO `bsp_rule_tip_content` VALUES (1087, 800, 'en-us', 'Necessary parameters should not be null：bspOrderSendPerson[sendCityCode]', 'CL', 0, 'SYSTEM', 'admin', 1676450126750, 1676971720122);
INSERT INTO `bsp_rule_tip_content` VALUES (1088, 801, 'en-us', 'Necessary parameters should not be null：bspOrderSendPerson[sendAddress]', 'CL', 0, 'SYSTEM', 'admin', 1676450378253, 1676971720219);
INSERT INTO `bsp_rule_tip_content` VALUES (1089, 802, 'en-us', 'Necessary parameters should not be null：bspOrderReceivePerson[receiveContact]', 'CL', 0, 'SYSTEM', 'admin', 1676453805886, 1676971720233);
INSERT INTO `bsp_rule_tip_content` VALUES (1090, 803, 'en-us', 'Necessary parameters should not be null：bspOrderReceivePerson[receiveCountry]', 'CL', 0, 'SYSTEM', 'admin', 1676453882340, 1676971720311);
INSERT INTO `bsp_rule_tip_content` VALUES (1091, 804, 'en-us', 'Necessary parameters should not be null：bspOrderReceivePerson[receiveCountryCode]', 'CL', 0, 'SYSTEM', 'admin', 1676454347819, 1676971720321);
INSERT INTO `bsp_rule_tip_content` VALUES (1092, 805, 'en-us', 'Necessary parameters should not be null：bspOrderReceivePerson[receiveProvince]', 'CL', 0, 'SYSTEM', 'admin', 1676455603927, 1676971720330);
INSERT INTO `bsp_rule_tip_content` VALUES (1093, 806, 'en-us', 'Necessary parameters should not be null：bspOrderReceivePerson[receiveCity]', 'CL', 0, 'SYSTEM', 'admin', 1676455664349, 1676971720338);
INSERT INTO `bsp_rule_tip_content` VALUES (1094, 807, 'en-us', 'bspOrderReceivePerson[receiveCityCode]', 'CL', 0, 'SYSTEM', 'admin', 1676455708130, 1676971720409);
INSERT INTO `bsp_rule_tip_content` VALUES (1095, 808, 'en-us', 'bspOrderReceivePerson[receiveAddress]', 'CL', 0, 'SYSTEM', 'admin', 1676455745829, 1676971720418);
INSERT INTO `bsp_rule_tip_content` VALUES (1096, 809, 'en-us', 'Please enter correct cargo : parcelTotalWeight', 'CL', 0, 'SYSTEM', 'admin', 1676535012654, 1676971720709);
INSERT INTO `bsp_rule_tip_content` VALUES (1097, 812, 'en-us', 'Necessary parameters should not be null：bspOrderSendPerson[sendCounty]', 'CL', 0, 'SYSTEM', 'admin', 1676876000087, 1676971720805);
INSERT INTO `bsp_rule_tip_content` VALUES (1098, 814, 'en-us', 'Necessary parameters should not be null：expressType', 'CL', 0, 'SYSTEM', 'admin', 1676885140883, 1676971720825);
INSERT INTO `bsp_rule_tip_content` VALUES (1099, 815, 'en-us', 'Necessary parameters should not be null：bspOrderReceivePerson[receiveCounty]', 'CL', 0, 'SYSTEM', 'admin', 1676883468158, 1676971720833);
INSERT INTO `bsp_rule_tip_content` VALUES (1100, 817, 'en-us', 'Please enter correct cargo : parcelQuantity', 'CL', 0, 'SYSTEM', 'admin', 1676534785954, 1676971720918);
INSERT INTO `bsp_rule_tip_content` VALUES (1101, 818, 'en-us', 'Please enter correct cargo : totalLength', 'CL', 0, 'SYSTEM', 'admin', 1676536559165, 1676971720927);
INSERT INTO `bsp_rule_tip_content` VALUES (1102, 819, 'en-us', 'Please enter correct cargo : totalWidth', 'CL', 0, 'SYSTEM', 'admin', 1676536727382, 1676971721010);
INSERT INTO `bsp_rule_tip_content` VALUES (1103, 820, 'en-us', 'Please enter correct cargo : totalHeightgth', 'CL', 0, 'SYSTEM', 'admin', 1676536863436, 1676971721018);
INSERT INTO `bsp_rule_tip_content` VALUES (1104, 821, 'en-us', 'payMethod must is 1 or 2 or 3', 'CL', 0, 'SYSTEM', 'admin', 1676537289748, 1676971721030);
INSERT INTO `bsp_rule_tip_content` VALUES (1105, 822, 'en-us', 'paymentType must is M or C', 'CL', 0, 'SYSTEM', 'admin', 1676537405397, 1676971721111);
INSERT INTO `bsp_rule_tip_content` VALUES (1106, 823, 'en-us', 'Necessary parameters should not be null：referenceNo1 and referenceNo1 length cannot exceed 64 bits', 'CL', 0, 'SYSTEM', 'admin', 1676538064547, 1676971721120);
INSERT INTO `bsp_rule_tip_content` VALUES (1107, 824, 'en-us', 'referenceNo2 length cannot exceed 64 bits', 'CL', 0, 'SYSTEM', 'admin', 1676539218437, 1676971721210);
INSERT INTO `bsp_rule_tip_content` VALUES (1108, 825, 'en-us', 'Necessary parameters should not be null：tenantId', 'CL', 0, 'SYSTEM', 'admin', 1676539813591, 1676971721219);
INSERT INTO `bsp_rule_tip_content` VALUES (1109, 826, 'en-us', 'Necessary parameters should not be null：localTime', 'CL', 0, 'SYSTEM', 'admin', 1676539871488, 1676971721307);
INSERT INTO `bsp_rule_tip_content` VALUES (1110, 827, 'en-us', 'Necessary parameters should not be null：timeZone\ntimeZone length cannot exceed 32 bits', 'CL', 0, 'SYSTEM', 'admin', 1676540208927, 1676971721317);
INSERT INTO `bsp_rule_tip_content` VALUES (1111, 828, 'en-us', 'Necessary parameters should not be null：productCode\nproductCode length cannot exceed 10 bits', 'CL', 0, 'SYSTEM', 'admin', 1676540433064, 1676971721407);
INSERT INTO `bsp_rule_tip_content` VALUES (1112, 829, 'en-us', 'Necessary parameters should not be null：limitTypeCode', 'CL', 0, 'SYSTEM', 'admin', 1676540550037, 1676971721416);
INSERT INTO `bsp_rule_tip_content` VALUES (1113, 830, 'en-us', 'Necessary parameters should not be null：parcelTotalVolume', 'CL', 0, 'SYSTEM', 'admin', 1676540625737, 1676971721506);
INSERT INTO `bsp_rule_tip_content` VALUES (1114, 831, 'en-us', 'Necessary parameters should not be null：dynamic6', 'CL', 0, 'SYSTEM', 'admin', 1676540679866, 1676971721516);
INSERT INTO `bsp_rule_tip_content` VALUES (1115, 832, 'en-us', 'Necessary parameters should not be null：isUnderCall', 'CL', 0, 'SYSTEM', 'admin', 1676540775363, 1676971721607);
INSERT INTO `bsp_rule_tip_content` VALUES (1116, 833, 'en-us', 'operateType must is 1 or 2', 'CL', 0, 'SYSTEM', 'admin', 1676540874599, 1676971721616);
INSERT INTO `bsp_rule_tip_content` VALUES (1118, 835, 'en-us', 'Necessary parameters should not be null：bspOrderCustoms[sendCountry]', 'CL', 0, 'SYSTEM', 'admin', 1676541498740, 1676971721709);
INSERT INTO `bsp_rule_tip_content` VALUES (1120, 837, 'en-us', 'bspOrderCustoms[receiveCountry]', 'CL', 0, 'SYSTEM', 'admin', 1676541602957, 1676971721804);
INSERT INTO `bsp_rule_tip_content` VALUES (1121, 838, 'en-us', 'Necessary parameters should not be null：bspOrderCustoms[receiveCityCode]', 'CL', 0, 'SYSTEM', 'admin', 1676541671166, 1676971721816);
INSERT INTO `bsp_rule_tip_content` VALUES (1122, 839, 'en-us', 'Necessary parameters should not be null：bspOrderCustoms[originCountry]', 'CL', 0, 'SYSTEM', 'admin', 1676541740876, 1676971721906);
INSERT INTO `bsp_rule_tip_content` VALUES (1123, 840, 'en-us', 'taxPayMethod must is 1 or 2 or 3', 'CL', 0, 'SYSTEM', 'admin', 1676541817741, 1676971721916);
INSERT INTO `bsp_rule_tip_content` VALUES (1124, 841, 'en-us', 'taxPaymentType must is M or C', 'CL', 0, 'SYSTEM', 'admin', 1676541865724, 1676971721925);
INSERT INTO `bsp_rule_tip_content` VALUES (1125, 333, 'en-us', 'Please enter correct cargo : parcelQuantity', NULL, 0, '', '', 1676534785954, 1677032286228);
INSERT INTO `bsp_rule_tip_content` VALUES (1126, 350, 'en-us', 'operateType must is 1', NULL, 0, '', '', 1676540874599, 1677032359101);
INSERT INTO `bsp_rule_tip_content` VALUES (1127, 315, 'en-us', 'Necessary parameters should not be null：bspOrderCarGo[unit]', NULL, 0, '', '', 1677033461707, 1677033461707);
INSERT INTO `bsp_rule_tip_content` VALUES (1128, 330, 'en-us', 'Necessary parameters should not be null：bspOrderCarGo[name]', NULL, 0, '', '', 1677033481445, 1677033481445);
INSERT INTO `bsp_rule_tip_content` VALUES (1129, 326, 'en-us', 'Necessary parameters should not be null：bspOrderCarGo[currency]', NULL, 0, '', '', 1677033504605, 1677033504605);
INSERT INTO `bsp_rule_tip_content` VALUES (1130, 872, 'en-us', 'Necessary parameters should not be null：bspOrderSendPerson[sendTel]', NULL, 0, '', '', 1677035488379, 1677035488379);
INSERT INTO `bsp_rule_tip_content` VALUES (1131, 873, 'en-us', 'Necessary parameters should not be null：bspOrderSendPerson[sendMobile]', NULL, 0, '', '', 1677035601866, 1677035601866);
INSERT INTO `bsp_rule_tip_content` VALUES (1132, 328, 'en-us', 'Necessary parameters should not be null：bspOrderReceivePerson[receiveTel]', NULL, 0, '', '', 1676883468158, 1677035987814);
INSERT INTO `bsp_rule_tip_content` VALUES (1133, 874, 'en-us', 'Necessary parameters should not be null：bspOrderReceivePerson[receiveMobile]', NULL, 0, '', '', 1677036061307, 1677036061307);

SET FOREIGN_KEY_CHECKS = 1;
