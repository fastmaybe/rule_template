package com.sf.saas.bsp.rule.core.service.rule;

import cn.hutool.json.JSON;

import java.util.List;
import java.util.function.BiFunction;

/**
 * @author 01407460
 * @date 2022/10/27 14:16
 */
@SuppressWarnings("all")
public enum Joiner {


    AND(((validators, target) -> {
        for (PreValidator valid : validators) {
            if (!valid.isValidate(target)) {
                return false;
            }
        }
        return true;
    })),

    OR(((validators, target) -> {
        for (PreValidator valid : validators) {
            if (valid.isValidate(target)) {
                return true;
            }
        }
        return false;
    }))
    ;


    private BiFunction<List<? extends PreValidator>, JSON, Boolean> validator;

    Joiner(BiFunction<List<? extends PreValidator>, JSON, Boolean> validator) {
        this.validator = validator;
    }

    public boolean isValidate(List<? extends PreValidator> validators, JSON target) {
        return validator.apply(validators, target);
    }


    public static boolean constainsJoinerStr(String joinerStr) {
        for (Joiner joiner : Joiner.values()) {
            if (joiner.name().equals(joinerStr)) {
                return true;
            }
        }
        return false;
    }
}
