package com.sf.saas.bsp.rule.core.service.crud.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sf.saas.bsp.rule.core.dao.entity.RuleOperationLog;
import com.sf.saas.bsp.rule.core.dao.mapper.RuleOperationLogMapper;
import com.sf.saas.bsp.rule.core.dto.base.Response;
import com.sf.saas.bsp.rule.core.dto.base.WebPage;
import com.sf.saas.bsp.rule.core.dto.req.LogQueryPageVo;
import com.sf.saas.bsp.rule.core.dto.vo.RuleInfoVo;
import com.sf.saas.bsp.rule.core.dto.vo.RuleOperationLogVo;
import com.sf.saas.bsp.rule.core.manager.convert.RuleOperationLogConvert;
import com.sf.saas.bsp.rule.core.service.crud.IRuleOperationLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 日志记录 服务实现类
 * </p>
 *
 * @author 01408885
 * @since 2022-10-26
 */
@Service
public class RuleOperationLogServiceImpl extends ServiceImpl<RuleOperationLogMapper, RuleOperationLog> implements IRuleOperationLogService {

    @Override
    public WebPage<RuleOperationLogVo> queryPage(LogQueryPageVo queryPageVo) {
        if (queryPageVo.getRuleId() == null) {
            return new WebPage<RuleOperationLogVo>(queryPageVo.getPageNum(), queryPageVo.getPageSize(), 0L, null);
        }

        LambdaQueryWrapper<RuleOperationLog> queryWrapper = Wrappers.<RuleOperationLog>lambdaQuery()
                .eq(RuleOperationLog::getRuleId, queryPageVo.getRuleId())
                .orderByDesc(RuleOperationLog::getId);

        Integer selectCount = baseMapper.selectCount(queryWrapper);

        Page<RuleOperationLog> mybatisPage = new Page(queryPageVo.getPageNum(), queryPageVo.getPageSize());

        Page<RuleOperationLog> selectPage = baseMapper.selectPage(mybatisPage, queryWrapper);

        List<RuleOperationLogVo> logVos = RuleOperationLogConvert.convertDo2VoList(selectPage.getRecords());

        return new WebPage<RuleOperationLogVo>(queryPageVo.getPageNum(), queryPageVo.getPageSize(), Long.valueOf(selectCount), logVos);
    }
}
