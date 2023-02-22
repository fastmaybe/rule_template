CREATE TABLE `bsp_rule_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `tenant_id` varchar(4) DEFAULT NULL COMMENT '租户',
  `config_name` varchar(128) DEFAULT NULL COMMENT '配置名称',
  `field_key` varchar(256) DEFAULT NULL COMMENT '字段key',
  `state` tinyint(2) NOT NULL DEFAULT '0' COMMENT '是否生效：0-否；1-是',
  `remark` varchar(300) DEFAULT NULL COMMENT '备注',
  `order_channel` varchar(50) DEFAULT NULL COMMENT '下单渠道：1-WEB单票；2-WEB导入；3-jvgo；4-api单个；5-api批量，多个逗号分隔',
  `rule_type` int(4) DEFAULT NULL COMMENT '规则类型 1 基础 2 组合 3 特殊',
  `config_key` varchar(128) DEFAULT NULL COMMENT '特殊配置key值',
  `rule_condition` text COMMENT '规则条件',
  `rule_condition_backstage` text COMMENT '基础规则对应joiner的json形式',
  `pre_condition` text COMMENT '前置条件',
  `pre_condition_md5` varchar(64) DEFAULT NULL COMMENT '前置条件MD5值',
  `month_card_no_white` text COMMENT '月结卡号白名单，逗号分隔',
  `create_by` varchar(50) DEFAULT '' COMMENT '创建人',
  `modified_by` varchar(50) DEFAULT '' COMMENT '修改人',
  `gmt_create` bigint(16) DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` bigint(16) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_config_name` (`config_name`) USING BTREE,
  KEY `idx_rule_type` (`rule_type`) USING BTREE,
  KEY `idx_field_key` (`field_key`) USING BTREE,
  KEY `idx_gmt_create` (`gmt_create`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='字段规则配置';


CREATE TABLE `bsp_rule_operation_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `tenant_id` varchar(10) DEFAULT NULL COMMENT '租户',
  `rule_id` bigint(20) DEFAULT NULL COMMENT '规则id',
  `operation_type` tinyint(4) DEFAULT NULL COMMENT '某业务域操作类型',
  `operation_data` text COMMENT '操作的数据',
  `operation_remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `operator_id` varchar(64) DEFAULT NULL COMMENT '操作人id',
  `operator_name` varchar(64) DEFAULT NULL COMMENT '操作人名称',
  `gmt_create` bigint(20) DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_operation_type` (`operation_type`) USING BTREE,
  KEY `idx_tenant_create_tm` (`tenant_id`,`gmt_create`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='日志记录';

CREATE TABLE `bsp_rule_field_condition` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `field_key` varchar(128) DEFAULT NULL COMMENT 'key',
  `condition_name` varchar(64) DEFAULT NULL COMMENT '条件',
  `config_size` int(4) DEFAULT NULL COMMENT '条件参数个数',
  `state` tinyint(2) NOT NULL DEFAULT '0' COMMENT '是否生效：0-否；1-是',
  `gmt_create` bigint(20) DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(50) DEFAULT '' COMMENT '创建人',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_field_key` (`field_key`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='字段规则key与条件关系表';


CREATE TABLE `bsp_rule_dictionary` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `scene` varchar(100) NOT NULL COMMENT '数据字典类型场景',
  `dict_key` varchar(100) NOT NULL COMMENT '字典key',
  `dict_value` varchar(4000) NOT NULL COMMENT '字典value',
  `sort` int(11) DEFAULT NULL COMMENT '显示排序',
  `lang` varchar(10) NOT NULL COMMENT '语言：中文-zh-CN；英文-en',
  `remark` varchar(300) DEFAULT NULL COMMENT '备注',
  `status` tinyint(1) NOT NULL COMMENT '有效状态：0-无效；1-有效',
  `gmt_create` bigint(16) DEFAULT NULL COMMENT '创建时间',
  `creator` varchar(50) DEFAULT '' COMMENT '创建人',
  `gmt_modified` bigint(16) DEFAULT NULL COMMENT '更新时间',
  `updater` varchar(50) DEFAULT '' COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uniq_scene_lang_key` (`scene`,`lang`,`dict_key`) USING BTREE,
  KEY `idx_lang` (`lang`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='数据字典表';
