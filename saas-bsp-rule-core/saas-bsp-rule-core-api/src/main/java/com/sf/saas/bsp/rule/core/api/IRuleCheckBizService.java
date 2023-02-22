package com.sf.saas.bsp.rule.core.api;

import com.sf.saas.bsp.rule.core.dto.base.AppResponse;
import com.sf.saas.bsp.rule.core.dto.res.CheckResult;
import cn.hutool.json.JSONObject;

import java.util.List;

/**
 * @author 01407460
 * @date 2022/10/31 11:33
 */
public interface IRuleCheckBizService {


    /**
     * 单票
     * @param jsonObject
     * @return
     */
    AppResponse<CheckResult> checkRuleSingle(JSONObject jsonObject);

    /**
     * 批量
     * @param jsonObjectList
     * @return
     */
    AppResponse<List<CheckResult>> checkRuleBatch(List<JSONObject> jsonObjectList);
}
