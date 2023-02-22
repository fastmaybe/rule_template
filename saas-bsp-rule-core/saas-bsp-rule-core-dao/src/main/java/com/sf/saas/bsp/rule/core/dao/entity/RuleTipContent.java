package com.sf.saas.bsp.rule.core.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 规则多语言响应提示
 * </p>
 *
 * @author 01408885
 * @since 2023-02-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("bsp_rule_tip_content")
public class RuleTipContent implements Serializable {


    /**
     * 自增id
     */
      @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 策略id
     */
    private Long ruleId;

    /**
     * 语种
     */
    private String lang;

    /**
     * 备注说明
     */
    private String content;

    /**
     * 租户
     */
    private String tenantId;

    /**
     * 前端/后端提示：0-后端；1-前端
     */
    private Integer tipType;

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
