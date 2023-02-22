package com.sf.saas.bsp.rule.core.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 字段规则配置
 * </p>
 *
 * @author 01408885
 * @since 2022-10-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("bsp_rule_info")
public class RuleInfo implements Serializable {


    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 租户
     */
    private String tenantId;

    /**
     * 配置名称
     */
    private String configName;

    /**
     * 字段key
     */
    private String fieldKey;

    /**
     * 前端字段name
     */
    private String webFieldName;

    /**
     * 是否生效：0-否；1-是
     */
    private Integer state;

    /**
     * 备注
     */
    private String remark;

    /**
     * 下单渠道：1-WEB单票；2-WEB导入；3-jvgo；4-api单个；5-api批量，多个逗号分隔
     */
    private String orderChannel;

    /**
     * 规则类型
     */
    private Integer ruleType;

    /**
     * 特殊配置key值
     */
    private String configKey;

    /**
     * 规则条件
     */
    private String ruleCondition;

    /**
     * 基础规则 joiner表示
     */
    private String ruleConditionBackstage;

    /**
     * 前置条件
     */
    private String preCondition;

    /**
     * 前置条件MD5值
     */
    private String preConditionMd5;

    /**
     * 月结卡号白名单，逗号分隔
     */
    private String monthCardNoWhite;

    /**
     * 前端排序
     */
    private Integer webDisplayOrder;

    /**
     * 模板规则 0:非模板 1:模板规则
     */
    private Integer ruleTemplate;

    /**
     * 模板规则 0:非模板 1:模板规则
     */
    private Integer fromTemplate;

    /**
     * 前端是否展示
     */
    private Boolean webDisplay;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 修改人
     */
    private String modifiedBy;

    /**
     * 创建时间
     */
    private Long gmtCreate;

    /**
     * 更新时间
     */
    private Long gmtModified;


}
