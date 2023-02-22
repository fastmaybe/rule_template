package com.sf.saas.bsp.rule.core.dto.enums;

import lombok.extern.log4j.Log4j2;

/**
 * 日志操作类型
 *
 * @author suntao(01408885)
 * @since 2022-11-02
 */
@Log4j2
public enum OperationTypeEnum {
    NEW(1, "新增"),
    UPDATE(2, "修改"),
    DEACTIVATE(3, "停用"),
    ENABLE(4, "启用");

    private Integer type;
    private String desc;

    OperationTypeEnum(Integer type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public static OperationTypeEnum getByType(Integer operationType) {
        for (OperationTypeEnum typeEnum : OperationTypeEnum.values()) {
            if (typeEnum.type.equals(operationType)) {
                return typeEnum;
            }
        }
        return null;
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


}
