package com.sf.saas.bsp.rule.core.service.biz;

import com.sf.saas.bsp.rule.core.api.IDictionaryBizService;
import com.sf.saas.bsp.rule.core.common.utils.ResponseHelper;
import com.sf.saas.bsp.rule.core.dto.base.Response;
import com.sf.saas.bsp.rule.core.dto.vo.FieldConditionSelectInfoVo;
import com.sf.saas.bsp.rule.core.dto.vo.DictionarySelectVo;
import com.sf.saas.bsp.rule.core.manager.cache.IFieldConditionCache;
import com.sf.saas.bsp.rule.core.manager.cache.IDictionaryCache;
import com.sf.saas.bsp.rule.core.service.crud.IDictionaryService;
import com.sf.saas.bsp.rule.core.service.utils.CurrentReqInfoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author 01407460
 * @date 2022/9/7 11:16
 */
@Service
public class DictionaryBizServiceImpl implements IDictionaryBizService {



    @Autowired
    private IDictionaryService dictionaryService;

    @Autowired
    private IDictionaryCache dictionaryCache;
    @Autowired
    private IFieldConditionCache conditionCache;

    @Override
    public List<DictionarySelectVo> getDictionarySelectByType(String dictionaryName) {
        return dictionaryService.getDictionarySelectByType(dictionaryName);
    }

    @Override
    public Response<Map<String, List<DictionarySelectVo>>> dictionaryGroup() {
        String currentLang = CurrentReqInfoUtil.currentLang();
        Map<String, List<DictionarySelectVo>> map = dictionaryCache.dictionaryGroupByLang(currentLang);
        return ResponseHelper.buildSuccess(map);
    }

    @Override
    public Response<Boolean> reloadDictionaryGroup() {



        dictionaryCache.cleanCache();
        conditionCache.cleanCache();
        return ResponseHelper.buildSuccess();
    }

    @Override
    public Response<Map<String, List<FieldConditionSelectInfoVo>>> conditionInfo() {
        Map<String, List<FieldConditionSelectInfoVo>> stringListMap = conditionCache.conditionList();
        return ResponseHelper.buildSuccess(stringListMap);
    }
}
