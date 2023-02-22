package com.sf.saas.bsp.rule.core.api;

import com.sf.saas.bsp.rule.core.dto.base.Response;
import com.sf.saas.bsp.rule.core.dto.vo.RuleInfoVo;
import com.sf.saas.bsp.rule.core.dto.vo.RuleTipContentVo;

import java.util.List;

/**
 * Description IRuleOperationLogService
 *
 * @author suntao(01408885)
 * @since 2022-11-02
 */
public interface IRuleTenantFromTemplateBizService {
    /**
     * 复制
     * @param tenantId
     */
    void copyFromTemplate(String tenantId);

    boolean hasCopied();


    boolean tenantTemplateSetNotCopy(String tenantId);
}
