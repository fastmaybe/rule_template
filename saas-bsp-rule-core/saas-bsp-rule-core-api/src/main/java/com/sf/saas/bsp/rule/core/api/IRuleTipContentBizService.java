package com.sf.saas.bsp.rule.core.api;

import com.sf.saas.bsp.rule.core.dto.vo.RuleInfoVo;
import com.sf.saas.bsp.rule.core.dto.vo.RuleTipContentVo;

import java.util.List;

/**
 * Description IRuleOperationLogService
 *
 * @author suntao(01408885)
 * @since 2022-11-02
 */
public interface IRuleTipContentBizService {
    /**
     * 保存
     * @param vo
     * @param vos
     */
    void saveTipsContentList(RuleInfoVo vo, List<RuleTipContentVo> vos);

    void saveCopyTipsContentListFromTemplate(RuleInfoVo vo, List<RuleTipContentVo> ruleTipContentVos, String tenantId);

    /**
     * 根据ruleId删除 提示语
     * @param ruleIds
     */
    void deleteByRuleIds(List<Long> ruleIds);

    void deleteByTenantId(String tenantId);

    /**
     * 给规则设定语言
     * @param ruleIds
     * @param lang
     * @param tipType
     */
    void setRuleLang(String ruleIds, String lang, Integer tipType);
}
