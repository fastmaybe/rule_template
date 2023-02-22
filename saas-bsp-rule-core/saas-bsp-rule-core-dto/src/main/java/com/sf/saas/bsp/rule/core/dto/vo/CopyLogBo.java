package com.sf.saas.bsp.rule.core.dto.vo;

import lombok.Data;

/**
 * Description CopyLogBo
 *
 * @author suntao(01408885)
 * @since 2023-02-13
 */
@Data
public class CopyLogBo {
    private Long templateId;
    private RuleInfoVo vo;

    public CopyLogBo(Long templateId, RuleInfoVo vo) {
        this.templateId = templateId;
        this.vo = vo;
    }
}
