package com.sf.saas.bsp.rule.core.service.crud.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.sf.saas.bsp.rule.core.dao.entity.RuleDictionary;
import com.sf.saas.bsp.rule.core.dao.mapper.RuleDictionaryMapper;
import com.sf.saas.bsp.rule.core.dto.vo.DictionarySelectVo;
import com.sf.saas.bsp.rule.core.manager.convert.DictionaryConvert;
import com.sf.saas.bsp.rule.core.service.crud.IDictionaryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 数据字典表 服务实现类
 * </p>
 *
 * @author 01407460
 * @since 2022-09-06
 */
@Service
public class DictionaryServiceImpl extends ServiceImpl<RuleDictionaryMapper, RuleDictionary> implements IDictionaryService {

    @Override
    public List<DictionarySelectVo> getDictionarySelectByType(String dictionaryName) {
        LambdaQueryWrapper<RuleDictionary> queryWrapper = Wrappers.<RuleDictionary>lambdaQuery()
                .eq(RuleDictionary::getScene, dictionaryName);
        List<RuleDictionary> dictionaries = baseMapper.selectList(queryWrapper);
        return DictionaryConvert.convertDo2VoList(dictionaries);
    }
}
