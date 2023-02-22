package com.sf.saas.bsp.rule.core.manager.convert;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.sf.saas.bsp.rule.core.dao.entity.RuleInfo;
import com.sf.saas.bsp.rule.core.dao.entity.RuleTipContent;
import com.sf.saas.bsp.rule.core.dto.vo.RuleInfoVo;
import com.sf.saas.bsp.rule.core.dto.vo.RuleTipContentVo;
import lombok.extern.log4j.Log4j2;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Description RuleTipContentConvert
 *
 * @author suntao(01408885)
 * @since 2023-02-02
 */
@Log4j2
public class RuleTipContentConvert {
    public static List<RuleTipContent> vo2doList(RuleInfoVo vo, List<RuleTipContentVo> vos) {
        if (CollectionUtils.isNotEmpty(vos)) {
            return vos.stream().map(t -> RuleTipContentConvert.vo2do(vo, t)).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    private static RuleTipContent vo2do(RuleInfoVo ruleInfoVo, RuleTipContentVo vo) {
        if (vo != null) {
            RuleInfo f = new RuleInfo();
            RuleTipContent ruleTipContent = new RuleTipContent();
            ruleTipContent.setId(vo.getId());
            ruleTipContent.setRuleId(ruleInfoVo.getId());
            ruleTipContent.setLang(vo.getLang());
            ruleTipContent.setContent(vo.getContent());
            if (StringUtils.isBlank(vo.getTenantId())) {
                ruleTipContent.setTenantId(ruleInfoVo.getTenantId());
            } else {
                ruleTipContent.setTenantId(vo.getTenantId());
            }
            ruleTipContent.setTipType(vo.getTipType());
            if (StringUtils.isBlank(vo.getCreateBy())) {
                ruleTipContent.setCreateBy(ruleInfoVo.getCreateBy());
            } else {
                ruleTipContent.setCreateBy(vo.getCreateBy());
            }
            if (StringUtils.isBlank(vo.getModifiedBy())) {
                ruleTipContent.setModifiedBy(ruleInfoVo.getModifiedBy());
            } else {
                ruleTipContent.setModifiedBy(vo.getModifiedBy());
            }
            if (vo.getGmtCreate() == null) {
                if (ruleInfoVo.getGmtCreate() != null) {
                    ruleTipContent.setGmtCreate(ruleInfoVo.getGmtCreate());
                } else {
                    ruleTipContent.setGmtCreate(System.currentTimeMillis());
                }
            } else {
                ruleTipContent.setGmtCreate(vo.getGmtCreate());
            }
            ruleTipContent.setGmtModified(System.currentTimeMillis());
            return ruleTipContent;
        }
        return null;
    }

    public static List<RuleTipContent> vo2doCopyFromTemplateList(RuleInfoVo vo, List<RuleTipContentVo> ruleTipContentVos, String tenantId) {
        if (CollectionUtils.isNotEmpty(ruleTipContentVos)) {
            return ruleTipContentVos.stream().map(t -> RuleTipContentConvert.vo2doCopyFromTemplate(vo, t, tenantId)).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    /**
     * 不要主键id  新增保存
     * @param ruleInfoVo
     * @param vo
     * @param tenantId
     * @return
     */
    private static RuleTipContent vo2doCopyFromTemplate(RuleInfoVo ruleInfoVo, RuleTipContentVo vo, String tenantId) {
        if (vo != null) {
            RuleTipContent ruleTipContent = new RuleTipContent();
            ruleTipContent.setRuleId(ruleInfoVo.getId());
            ruleTipContent.setLang(vo.getLang());
            ruleTipContent.setContent(vo.getContent());
            if (StringUtils.isBlank(vo.getTenantId())) {
                ruleTipContent.setTenantId(tenantId);
            } else {
                ruleTipContent.setTenantId(vo.getTenantId());
            }
            ruleTipContent.setTipType(vo.getTipType());
            if (StringUtils.isBlank(vo.getCreateBy())) {
                ruleTipContent.setCreateBy(ruleInfoVo.getCreateBy());
            } else {
                ruleTipContent.setCreateBy(vo.getCreateBy());
            }
            if (StringUtils.isBlank(vo.getModifiedBy())) {
                ruleTipContent.setModifiedBy(ruleInfoVo.getModifiedBy());
            } else {
                ruleTipContent.setModifiedBy(vo.getModifiedBy());
            }
            if (vo.getGmtCreate() == null) {
                if (ruleInfoVo.getGmtCreate() != null) {
                    ruleTipContent.setGmtCreate(ruleInfoVo.getGmtCreate());
                } else {
                    ruleTipContent.setGmtCreate(System.currentTimeMillis());
                }
            } else {
                ruleTipContent.setGmtCreate(vo.getGmtCreate());
            }
            ruleTipContent.setGmtModified(System.currentTimeMillis());
            return ruleTipContent;
        }
        return null;
    }
}
