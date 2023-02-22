package com.sf.saas.bsp.rule.core.service.rule.validators.comparable;



import com.sf.saas.bsp.rule.core.service.rule.FieldValidator;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author 01407460
 * @date 2022/10/26 15:56
 */
public class BetweenIncludeRight implements FieldValidator<Comparable> {



    @Override
    public boolean validate(Comparable fieldValue, List<String> configs) {
        if (fieldValue instanceof Number){
            BigDecimal value = new BigDecimal(fieldValue.toString());
            BigDecimal p1 = getP1Num(configs);
            BigDecimal p2 = getP2Num(configs);
            return  value.compareTo(p1) > 0 &&  value.compareTo(p2) <= 0;
        }

        Comparable p1 = getP1(configs, fieldValue.getClass());
        Comparable p2 = getP2(configs, fieldValue.getClass());
        return  fieldValue.compareTo(p1) > 0 &&  fieldValue.compareTo(p2) <= 0;
    }
}
