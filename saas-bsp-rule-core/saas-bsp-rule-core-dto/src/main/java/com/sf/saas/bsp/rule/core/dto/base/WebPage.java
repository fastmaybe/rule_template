package com.sf.saas.bsp.rule.core.dto.base;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author 01407460
 * @date 2022/9/7 11:26
 */
@Data
public class WebPage<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer pageNum = 1;
    private Integer pageSize = 10;
    private Long total = 0L;
    private Integer totalPage = 1;
    private Boolean hasNextPage = false;
    private List<T> list;

    public WebPage() {
    }

    public WebPage(Integer pageNum, Integer pageSize, Long total, List<T> list) {
        this.pageNum = pageNum;
        this.pageSize = pageSize < 1 ? 10 : pageSize;
        this.total = total;
        this.totalPage = (int)(total + (long)this.pageSize - 1L) / this.pageSize;
        this.hasNextPage = this.pageNum < this.totalPage;
        this.list = list;
    }




}
