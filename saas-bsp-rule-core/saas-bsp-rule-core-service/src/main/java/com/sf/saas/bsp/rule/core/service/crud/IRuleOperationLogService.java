package com.sf.saas.bsp.rule.core.service.crud;

import com.sf.saas.bsp.rule.core.dao.entity.RuleOperationLog;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sf.saas.bsp.rule.core.dto.base.Response;
import com.sf.saas.bsp.rule.core.dto.base.WebPage;
import com.sf.saas.bsp.rule.core.dto.req.LogQueryPageVo;
import com.sf.saas.bsp.rule.core.dto.vo.RuleOperationLogVo;

/**
 * <p>
 * 日志记录 服务类
 * </p>
 *
 * @author 01408885
 * @since 2022-10-26
 */
public interface IRuleOperationLogService extends IService<RuleOperationLog> {

    WebPage<RuleOperationLogVo> queryPage(LogQueryPageVo queryPageVo);
}
