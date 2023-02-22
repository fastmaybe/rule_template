package com.sf.saas.bsp.rule.core.service.utils;

import cn.hutool.crypto.digest.DigestUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;
import java.util.TreeMap;

/**
 * Description MD5Util
 *
 * @author suntao(01408885)
 * @since 2022-10-31
 */
public class MD5Util {
    /**
     * <p> md5加密 </p>
     *
     * @param text :加密的内容
     * @return : java.lang.String
     * @date: 2021/3/24 12:53
     **/
    public static String md5(String text) {
        return DigestUtil.md5Hex(text);
    }

    /**
     * treeMap 计算md5
     * @param oneFieldMutCondition
     * @return
     */
    public static String getMd5ValueFromTreeMap(TreeMap<String, String> oneFieldMutCondition) {
        StringBuffer stringBuffer = new StringBuffer();
        for (Map.Entry<String, String> map : oneFieldMutCondition.entrySet()) {
            stringBuffer.append(StringUtils.join(map.getKey(), map.getValue()));
        }
        return md5(stringBuffer.toString());
    }
}
