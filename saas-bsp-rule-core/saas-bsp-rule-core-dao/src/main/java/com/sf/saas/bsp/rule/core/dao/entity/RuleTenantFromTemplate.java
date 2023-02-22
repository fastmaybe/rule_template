package com.sf.saas.bsp.rule.core.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 租户模板记录
 * </p>
 *
 * @author 01408885
 * @since 2023-02-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("bsp_rule_tenant_from_template")
public class RuleTenantFromTemplate implements Serializable {


    /**
     * 自增id
     */
      @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 租户
     */
    private String tenantId;

    /**
     * 是否已经从模板copy：0-未copy；1-已copy
     */
    private Integer isCopy;

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
