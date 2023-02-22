package com.sf.saas.bsp.rule.core.dao.mapper;

import com.sf.saas.bsp.rule.core.dao.entity.RuleInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sf.saas.bsp.rule.core.dto.req.LoadOrderPageBaseRuleQueryVo;
import com.sf.saas.bsp.rule.core.dto.req.RuleVoQueryReq;
import com.sf.saas.bsp.rule.core.dto.vo.RuleInfoVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 字段规则配置 Mapper 接口
 * </p>
 *
 * @author 01408885
 * @since 2022-10-27
 */
public interface RuleInfoMapper extends BaseMapper<RuleInfo> {

    List<RuleInfoVo> loadWebBaseRule(LoadOrderPageBaseRuleQueryVo loadOrderPageBaseRuleQueryVo);

    List<RuleInfoVo> selectByParam(RuleVoQueryReq query);

    List<RuleInfoVo> selectCheckRulePool(@Param("tenantId") String tenantId, @Param("state")Integer state);

    List<RuleInfoVo> selectTemplateList();

    Integer selectByParamCount(RuleVoQueryReq query);
}
