package com.sf.saas.bsp.rule.core.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 日志记录
 * </p>
 *
 * @author 01408885
 * @since 2022-10-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("bsp_rule_operation_log")
public class RuleOperationLog implements Serializable {


    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 租户
     */
    private String tenantId;

    /**
     * 规则id
     */
    private Long ruleId;

    /**
     * 某业务域操作类型
     */
    private Integer operationType;

    /**
     * 操作的数据
     */
    private String operationData;

    /**
     * 备注
     */
    private String operationRemark;

    /**
     * 操作人id
     */
    private String operatorId;

    /**
     * 操作人名称
     */
    private String operatorName;

    /**
     * 操作时间
     */
    private Long gmtCreate;


}
