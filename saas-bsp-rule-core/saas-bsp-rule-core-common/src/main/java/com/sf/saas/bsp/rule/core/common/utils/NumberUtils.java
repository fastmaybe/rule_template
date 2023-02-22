package com.sf.saas.bsp.rule.core.common.utils;

import java.security.SecureRandom;


public class NumberUtils {

    /**
     * 生成6位验证码
     * @return
     */
    public static String geneVerificationCode_6() {
        SecureRandom random = new SecureRandom();
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            result = result.append(random.nextInt(10));
        }
        return result.toString();
    }
    /**
     * 生成6位验证码
     * @return
     */
    public static Long geneRandomCode(int len) {
        SecureRandom random = new SecureRandom();
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < len; i++) {
            result = result.append(random.nextInt(10));
        }
        return Long.valueOf(result.toString());
    }
}
