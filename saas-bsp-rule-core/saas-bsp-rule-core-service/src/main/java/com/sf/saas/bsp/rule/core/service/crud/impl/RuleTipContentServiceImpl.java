package com.sf.saas.bsp.rule.core.service.crud.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.sf.saas.bsp.rule.core.dao.entity.RuleTipContent;
import com.sf.saas.bsp.rule.core.dao.mapper.RuleTipContentMapper;
import com.sf.saas.bsp.rule.core.service.crud.IRuleTipContentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * <p>
 * 规则多语言响应提示 服务实现类
 * </p>
 *
 * @author 01408885
 * @since 2023-02-02
 */
@Service
public class RuleTipContentServiceImpl extends ServiceImpl<RuleTipContentMapper, RuleTipContent> implements IRuleTipContentService {


    @Override
    public void deleteByRuleId(Long ruleId) {
        if (ruleId == null) {
            return;
        }

        LambdaQueryWrapper<RuleTipContent> wrapper = Wrappers.<RuleTipContent>lambdaQuery()
                .eq(RuleTipContent::getRuleId, ruleId);

        baseMapper.delete(wrapper);
    }

    @Override
    public void deleteByRuleIds(List<Long> ruleIds) {
        if (CollectionUtils.isEmpty(ruleIds)) {
            return;
        }

        LambdaQueryWrapper<RuleTipContent> wrapper = Wrappers.<RuleTipContent>lambdaQuery()
                .in(RuleTipContent::getRuleId, ruleIds);

        baseMapper.delete(wrapper);
    }

    @Override
    public void deleteByTenantId(String tenantId) {
        if (StringUtils.isBlank(tenantId)) {
            return;
        }

        LambdaQueryWrapper<RuleTipContent> wrapper = Wrappers.<RuleTipContent>lambdaQuery()
                .eq(RuleTipContent::getTenantId, tenantId);

        baseMapper.delete(wrapper);
    }

    @Override
    public List<RuleTipContent> queryByRuleId(Long ruleId) {
        if (ruleId == null) {
            return Collections.EMPTY_LIST;
        }

        LambdaQueryWrapper<RuleTipContent> wrapper = Wrappers.<RuleTipContent>lambdaQuery()
                .eq(RuleTipContent::getRuleId, ruleId);

        return baseMapper.selectList(wrapper);
    }
}
