package com.sf.saas.bsp.rule.core.dto.enums;

/**
 * Description LanguageEunm
 *
 * @author suntao(01408885)
 * @since 2022-09-08
 */
public enum LanguageEnum {

    EN("en"),
    CN("zh");

    private String lang;

    LanguageEnum(String lang) {
        this.lang = lang;
    }

    public String getLang() {
        return lang;
    }
}
