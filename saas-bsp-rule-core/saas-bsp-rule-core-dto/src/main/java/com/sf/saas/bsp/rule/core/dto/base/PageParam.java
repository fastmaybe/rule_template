package com.sf.saas.bsp.rule.core.dto.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author 01407460
 * @date 2022/9/6 19:09
 */
@Data
@ApiModel(description = "页面分页查询分页参数")
public class PageParam implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "当前页，从1起", example = "1")
    private Integer pageNum = 1;

    @ApiModelProperty(value = "分页数据量，默认10条", example = "10")
    private Integer pageSize = 10;

    @ApiModelProperty(value = "偏移量 后端使用 前端不需要传递",hidden = true)
    private Integer offset;

    @ApiModelProperty(value = "偏移量 后端使用 前端不需要传递",hidden = true)
    private Integer endSet;

    public Integer getOffset() {
        return (this.getPageNum() - 1) * this.getPageSize();
    }

    public Integer getEndSet() {
        return (this.getPageNum()) * this.getPageSize();
    }
}
