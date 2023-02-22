package com.sf.saas.bsp.rule.core.manager.convert;

import com.sf.saas.bsp.rule.core.common.constans.BspRuleConstant;
import com.sf.saas.bsp.rule.core.common.constans.CommonStatusEnum;
import com.sf.saas.bsp.rule.core.dao.entity.RuleInfo;
import com.sf.saas.bsp.rule.core.dto.vo.RuleInfoVo;

import java.util.stream.Collectors;

/**
 * Description RuleTenantFromTemplateConvert
 *
 * @author suntao(01408885)
 * @since 2023-02-06
 */
public class RuleTenantFromTemplateConvert {

    /**
     * 不要主键id  新增保存
     * @param ri
     * @param tenantId
     * @return
     */
    public static RuleInfo convertRuleInfoCopyFromTemplate2TenantRule(RuleInfoVo ri, String tenantId) {
        RuleInfo ruleInfo = new RuleInfo();
        ruleInfo.setTenantId(tenantId);
        ruleInfo.setConfigName(ri.getConfigName());
        ruleInfo.setFieldKey(ri.getFieldKey());
        ruleInfo.setWebFieldName(ri.getWebFieldName());
        ruleInfo.setState(ri.getState());
        ruleInfo.setRemark(ri.getRemark());
        ruleInfo.setOrderChannel(ri.getOrderChannel());
        ruleInfo.setRuleType(ri.getRuleType());
        ruleInfo.setConfigKey(ri.getConfigKey());
        ruleInfo.setRuleCondition(ri.getRuleCondition());
        ruleInfo.setRuleConditionBackstage(ri.getRuleConditionBackstage());
        ruleInfo.setPreCondition(ri.getPreCondition());
        ruleInfo.setPreConditionMd5(ri.getPreConditionMd5());
        ruleInfo.setMonthCardNoWhite(ri.getMonthCardNoWhite());
        ruleInfo.setWebDisplayOrder(ri.getWebDisplayOrder());
        // 租户的规则  非模板
        ruleInfo.setRuleTemplate(CommonStatusEnum.STATUS_NOT_ENABLED.getStatus());
        // 是否来自模板
        ruleInfo.setFromTemplate(CommonStatusEnum.STATUS_ENABLED.getStatus());
        ruleInfo.setWebDisplay(ri.getWebDisplay());
        ruleInfo.setCreateBy(BspRuleConstant.EDIT_BY_SYSTEM);
        ruleInfo.setModifiedBy(BspRuleConstant.EDIT_BY_SYSTEM);
        ruleInfo.setGmtCreate(System.currentTimeMillis());
        ruleInfo.setGmtModified(System.currentTimeMillis());
        return ruleInfo;
    }
}
