package com.sf.saas.bsp.rule.core.dto.bo;

import com.sf.saas.bsp.rule.core.dto.enums.OperationTypeEnum;
import lombok.Data;

/**
 * Description OperationLogBo
 *
 * @author suntao(01408885)
 * @since 2022-11-02
 */
@Data
public class OperationLogBo {

    private String user;
    private String tenantId;
    private Long ruleId;

    private Long gmtModified;

    private OperationTypeEnum typeEnum;

    private Object requestData;
}
