package com.sf.saas.bsp.rule.core.service.crud;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sf.saas.bsp.rule.core.dao.entity.RuleDictionary;
import com.sf.saas.bsp.rule.core.dto.vo.DictionarySelectVo;

import java.util.List;

/**
 * <p>
 * 数据字典表 服务类
 * </p>
 *
 * @author 01407460
 * @since 2022-09-06
 */
public interface IDictionaryService extends IService<RuleDictionary> {

    List<DictionarySelectVo> getDictionarySelectByType(String dictionaryName);
}
