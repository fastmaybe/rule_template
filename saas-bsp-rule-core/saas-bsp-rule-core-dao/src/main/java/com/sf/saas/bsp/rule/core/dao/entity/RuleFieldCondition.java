package com.sf.saas.bsp.rule.core.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 字段规则key与条件关系表
 * </p>
 *
 * @author 01408885
 * @since 2022-10-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("bsp_rule_field_condition")
public class RuleFieldCondition implements Serializable {


      @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * key
     */
    private String fieldKey;

    /**
     * 条件
     */
    private String conditionName;

    /**
     * 参数输入框个数
     */
    private Integer configSize;

    /**
     * 状态
     */
    private Integer state;

    /**
     * 创建时间
     */
    private Long gmtCreate;

    /**
     * 创建人
     */
    private String createBy;


}
