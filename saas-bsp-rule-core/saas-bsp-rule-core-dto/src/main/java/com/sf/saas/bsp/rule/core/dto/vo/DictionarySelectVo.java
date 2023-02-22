package com.sf.saas.bsp.rule.core.dto.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Description DictionarySelectVo
 *
 * @author suntao(01408885)
 * @since 2022-09-07
 */
@Data
public class DictionarySelectVo {

    private static final long serialVersionUID = -4908651535967246713L;

    /**
     * 数据字典类型标识
     */
    @ApiModelProperty(value = "数据字典类型标识")
    private String scene;

    /**
     * 字典key
     */
    @ApiModelProperty(value = "字典key")
    private String dictKey;

    /**
     * 字典value
     */
    @ApiModelProperty(value = "字典value")
    private String dictValue;

    /**
     * 显示排序
     */
    @ApiModelProperty(value = "显示排序")
    private Integer sort;

    @ApiModelProperty(value = "语言")
    private String lang;

    @ApiModelProperty(value = "remark")
    private String remark;

}
