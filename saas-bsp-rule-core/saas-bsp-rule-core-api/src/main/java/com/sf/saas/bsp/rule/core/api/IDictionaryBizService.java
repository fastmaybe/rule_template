package com.sf.saas.bsp.rule.core.api;

import com.sf.saas.bsp.rule.core.dto.base.Response;
import com.sf.saas.bsp.rule.core.dto.vo.FieldConditionSelectInfoVo;
import com.sf.saas.bsp.rule.core.dto.vo.DictionarySelectVo;

import java.util.List;
import java.util.Map;

/**
 * @author 01407460
 * @date 2022/9/7 11:15
 */
public interface IDictionaryBizService {

    /**
     * 根据字典表name查询字段表，供前端下拉选
     * @param dictionaryName
     * @return
     */
    List<DictionarySelectVo> getDictionarySelectByType(String dictionaryName);

    /**
     * 字典组
     * @return
     */
    Response<Map<String, List<DictionarySelectVo>>> dictionaryGroup();

    /**
     *重新 加载字典组
     * @return
     */
    Response<Boolean> reloadDictionaryGroup();

    /**
     * 每个字段 对应 可选条件及参数个数
     * @return
     */
    Response<Map<String, List<FieldConditionSelectInfoVo>>> conditionInfo();
}
