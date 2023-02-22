package com.sf.saas.bsp.rule.core.service.crud.impl;

import com.sf.saas.bsp.rule.core.dao.entity.RuleFieldCondition;
import com.sf.saas.bsp.rule.core.dao.mapper.RuleFieldConditionMapper;
import com.sf.saas.bsp.rule.core.service.crud.IRuleConditionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 字段规则key与条件关系表 服务实现类
 * </p>
 *
 * @author 01408885
 * @since 2022-10-28
 */
@Service
public class RuleConditionServiceImpl extends ServiceImpl<RuleFieldConditionMapper, RuleFieldCondition> implements IRuleConditionService {

}
