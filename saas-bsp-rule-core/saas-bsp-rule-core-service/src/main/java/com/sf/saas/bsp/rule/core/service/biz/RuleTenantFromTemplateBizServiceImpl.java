package com.sf.saas.bsp.rule.core.service.biz;

import com.sf.saas.bsp.rule.core.api.IRuleTenantFromTemplateBizService;
import com.sf.saas.bsp.rule.core.dto.base.Response;
import com.sf.saas.bsp.rule.core.service.crud.IRuleTenantFromTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Description RuleTenantFromTemplateBizServiceImpl
 *
 * @author suntao(01408885)
 * @since 2023-02-17
 */
@Service
public class RuleTenantFromTemplateBizServiceImpl implements IRuleTenantFromTemplateBizService {

    @Autowired
    private IRuleTenantFromTemplateService ruleTenantFromTemplateService;

    @Override
    public void copyFromTemplate(String tenantId) {

    }

    @Override
    public boolean hasCopied() {
        return false;
    }

    @Override
    public boolean tenantTemplateSetNotCopy(String tenantId) {
        ruleTenantFromTemplateService.tenantTemplateSetNotCopy(tenantId);
        return true;
    }
}
