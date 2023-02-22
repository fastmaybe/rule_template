package com.sf.saas.bsp.rule.core.service.rule.param.valid;

import com.sf.saas.bsp.rule.core.common.constans.ResponseCodeEnum;
import com.sf.saas.bsp.rule.core.common.utils.ValidCodeMsg;

/**
 * Description IStrategyDimensionValidator
 *
 * @author suntao(01408885)
 * @since 2022-09-14
 */
public interface IRuleConditionParamValueValidator {


    ValidCodeMsg<ResponseCodeEnum> validate(Object configs);

    /**
     * 最小单元的md5（configs的md5，与条件有关，可能不同的条件 md5计算方式不一样）
     * @param configs
     * @return
     */
    String getValueMd5(Object configs);

    String getType();
}
