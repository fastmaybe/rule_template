package com.sf.saas.bsp.rule.core.service.biz;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.sf.saas.bsp.rule.core.api.IRuleTipContentBizService;
import com.sf.saas.bsp.rule.core.common.constans.BspRuleConstant;
import com.sf.saas.bsp.rule.core.common.constans.CommonStatusEnum;
import com.sf.saas.bsp.rule.core.dao.entity.RuleInfo;
import com.sf.saas.bsp.rule.core.dao.entity.RuleTipContent;
import com.sf.saas.bsp.rule.core.dto.vo.RuleInfoVo;
import com.sf.saas.bsp.rule.core.dto.vo.RuleTipContentVo;
import com.sf.saas.bsp.rule.core.manager.convert.RuleTipContentConvert;
import com.sf.saas.bsp.rule.core.service.crud.IRuleInfoService;
import com.sf.saas.bsp.rule.core.service.crud.IRuleTipContentService;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

/**
 * Description RuleTipContentBizServiceImpl
 *
 * @author suntao(01408885)
 * @since 2023-02-02
 */
@Log4j2
@Service
public class RuleTipContentBizServiceImpl implements IRuleTipContentBizService {

    @Autowired
    private IRuleTipContentService ruleTipContentService;
    @Autowired
    private IRuleInfoService ruleInfoService;

    @Transactional
    @Override
    public void saveTipsContentList(RuleInfoVo vo, List<RuleTipContentVo> vos) {

        ruleTipContentService.deleteByRuleId(vo.getId());

        // 多语言
        if (CollectionUtils.isNotEmpty(vos)) {
            List<RuleTipContent> tipContents = RuleTipContentConvert.vo2doList(vo, vos);
            ruleTipContentService.saveBatch(tipContents);
        }
    }

    @Override
    public void saveCopyTipsContentListFromTemplate(RuleInfoVo vo, List<RuleTipContentVo> ruleTipContentVos, String tenantId) {
        // 多语言
        if (CollectionUtils.isNotEmpty(ruleTipContentVos)) {
            List<RuleTipContent> tipContents = RuleTipContentConvert.vo2doCopyFromTemplateList(vo, ruleTipContentVos, tenantId);

            ruleTipContentService.saveOrUpdateBatch(tipContents);
        }
    }

    @Override
    public void deleteByRuleIds(List<Long> ruleIds) {
        ruleTipContentService.deleteByRuleIds(ruleIds);
    }

    @Override
    public void deleteByTenantId(String tenantId) {
        ruleTipContentService.deleteByTenantId(tenantId);
    }

    @Override
    public void setRuleLang(String ruleIds, String lang, Integer tipType) {
        if (StringUtils.isAnyBlank(ruleIds, lang) || tipType == null) {
            return;
        }
        List<String> ruleIdsList = Arrays.asList(ruleIds.split(BspRuleConstant.SPLIT_COMMON));

        for (String ruleId : ruleIdsList) {
            Long ruleLongId = Long.valueOf(ruleId);

            List<RuleTipContent> rtList = ruleTipContentService.queryByRuleId(ruleLongId);
            if (CollectionUtils.isEmpty(rtList)) {

                RuleInfo ruleInfo = ruleInfoService.getById(ruleLongId);
                if (ruleInfo != null) {
                    RuleTipContent tipContent = new RuleTipContent();
                    tipContent.setRuleId(ruleLongId);
                    tipContent.setLang(lang);
                    String content = "";
                    if (CommonStatusEnum.STATUS_ENABLED.getStatus().equals(tipType)) {
                        // 前端
                        content = "Please enter the correct value：" + ruleInfo.getWebFieldName();
                    } else {
                        content = "Please enter the correct value：" + ruleInfo.getFieldKey();
                    }
                    tipContent.setContent(content);
                    tipContent.setTenantId(ruleInfo.getTenantId());
                    tipContent.setTipType(tipType);
                    tipContent.setCreateBy(BspRuleConstant.EDIT_BY_SYSTEM);
                    tipContent.setModifiedBy(BspRuleConstant.EDIT_BY_SYSTEM);
                    tipContent.setGmtCreate(System.currentTimeMillis());
                    tipContent.setGmtModified(System.currentTimeMillis());
                    ruleTipContentService.save(tipContent);
                    log.info("[setLang_default]ruleId={}",ruleLongId);
                }
            }
        }
    }
}
