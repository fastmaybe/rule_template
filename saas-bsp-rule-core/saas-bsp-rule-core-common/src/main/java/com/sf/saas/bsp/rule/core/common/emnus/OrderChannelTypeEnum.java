package com.sf.saas.bsp.rule.core.common.emnus;

import com.sf.saas.bsp.rule.core.common.constans.BspRuleConstant;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * Description DictionaryType
 *
 * @author suntao(01408885)
 * @since 2022-09-07
 */
public enum OrderChannelTypeEnum {
    WEB_SINGLE(1, "WEB单票"),
    WEB_BATCH(2, "WEB导入"),
    JV_GO(3, "jvgo"),
    API_SINGLE(4, "api单个"),
    API_BATCH(5, "api批量");




    private Integer type;
    private String desc;

    OrderChannelTypeEnum(Integer type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * 下单渠道是否合法
     * @param reqOrderChannel
     * @return true:合法；false不合法
     */
    public static boolean containsWithString(String reqOrderChannel) {
        if (StringUtils.isBlank(reqOrderChannel)) {
            return false;
        }
        String[] split = reqOrderChannel.split(BspRuleConstant.SPLIT_COMMON);
        if (ArrayUtils.isEmpty(split)) {
            return false;
        }
        for (String orderChannelStr : split) {
            if (!contains(Integer.valueOf(orderChannelStr.trim()))) {
                return false;
            }
        }
        return true;

    }

    public static boolean contains(Integer orderChannel) {
        if (orderChannel == null) {
            return false;
        }
        for (OrderChannelTypeEnum orderChannelTypeEnum : OrderChannelTypeEnum.values()) {
            if (orderChannelTypeEnum.type.equals(orderChannel)) {
                return true;
            }
        }
        return false;
    }

}
