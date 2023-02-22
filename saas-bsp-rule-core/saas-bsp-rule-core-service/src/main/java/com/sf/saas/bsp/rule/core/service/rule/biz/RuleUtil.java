package com.sf.saas.bsp.rule.core.service.rule.biz;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.json.JSONUtil;
import com.sf.saas.bsp.rule.core.common.constans.BspRuleConstant;
import com.sf.saas.bsp.rule.core.dao.entity.RuleInfo;
import com.sf.saas.bsp.rule.core.dto.vo.RuleInfoVo;
import com.sf.saas.bsp.rule.core.dto.vo.RuleTipContentVo;
import com.sf.saas.bsp.rule.core.service.rule.*;
import com.sf.saas.bsp.rule.core.service.rule.bo.RuleConfBo;
import com.sf.saas.bsp.rule.core.service.rule.bo.RuleConfPool;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author 01407460
 * @date 2022/11/3 10:02
 */
@Slf4j
public class RuleUtil {

    private RuleUtil() {
    }

    private static final String SKIP_COND_STR = "{\"configs\":[{\"configs\":[{\"condition\":\"IN_CONF\",\"configs\":[]}],\"fieldName\":\"monthCardNoWhite\",\"joiner\":\"AND\"}],\"joiner\":\"AND\"}";
    private static final String PRE_COND_ORDER_CHANNEL = "{\"configs\":[{\"configs\":[{\"condition\":\"IN_CONF\",\"configs\":[]}],\"fieldName\":\"orderChannel\",\"joiner\":\"AND\"}],\"joiner\":\"AND\"}";


    public static RuleConfPool buildPool(List<RuleInfoVo> ruleInfos) {
        RuleConfPool pool = new RuleConfPool();
        ArrayList<RuleConfBo> confBos = new ArrayList<>();
        pool.setRuleConfList(confBos);
        if (CollectionUtils.isEmpty(ruleInfos)) {
            return pool;
        }
        for (RuleInfoVo ruleInfo : ruleInfos) {
            RuleConfBo ruleConfBo = null;
            try {
                ruleConfBo = buildConfBo(ruleInfo);
            } catch (Exception e) {
                log.error("build conf BO error ,rule info is {} ", JSONUtil.toJsonStr(ruleInfo), e);
            }
            confBos.add(ruleConfBo);
        }

        return pool;
    }

    private static RuleConfBo buildConfBo(RuleInfoVo ruleInfo) {
        RuleConfBo confBo = new RuleConfBo();
        confBo.setMsgCode(ruleInfo.getFieldKey());

        confBo.setFieldKey(ruleInfo.getFieldKey());

        confBo.setCheckRuleJson(ruleInfo.getRuleCondition());

        confBo.setType(ruleInfo.getRuleType());

        confBo.setRuleName(ruleInfo.getConfigName());

        confBo.setRuleKey(ruleInfo.getConfigKey());


        buildSkipCond(ruleInfo, confBo);


        buildPreCond(ruleInfo, confBo);


        buildCheckRule(ruleInfo, confBo);

        // todo提示语后端
        buildTipPool(ruleInfo,confBo);


        return confBo;
    }

    private static void buildTipPool(RuleInfoVo ruleInfo, RuleConfBo confBo) {
        Map<String, String> tipPool = new HashMap<>();
        confBo.setTipMap(tipPool);
        List<RuleTipContentVo> tips = ruleInfo.getRuleTipContentVos();
        if (CollectionUtils.isEmpty(tips)){
            return;
        }
        for (RuleTipContentVo tip : tips) {
            tipPool.put(tip.getLang(),tip.getContent());
        }

    }


    /**
     * skip
     *
     * @param ruleInfo
     * @param confBo
     */
    private static void buildSkipCond(RuleInfoVo ruleInfo, RuleConfBo confBo) {
        String monthCardNoWhite = ruleInfo.getMonthCardNoWhite();
        if (StringUtils.isNotBlank(monthCardNoWhite)) {
            String[] split = monthCardNoWhite.split(BspRuleConstant.SPLIT_COMMON);
            List<String> collect = Arrays.stream(split).collect(Collectors.toList());
            MultiFieldConf multiFieldConf = RuleUtil.buildSkipCond(collect);
            confBo.setSkipCond(multiFieldConf);
        }
    }

    /**
     * pre
     *
     * @param ruleInfo
     * @param confBo
     */
    private static void buildPreCond(RuleInfoVo ruleInfo, RuleConfBo confBo) {
        String preCondition = ruleInfo.getPreCondition();
        String preConditionMd5 = ruleInfo.getPreConditionMd5();


        ConfRules confRules = RuleUtil.buildPreCond(preCondition, preConditionMd5, ruleInfo.getOrderChannel());
        confBo.setPreCond(confRules);
    }


    /**
     * check
     *
     * @param ruleInfo
     * @param confBo
     */
    private static void buildCheckRule(RuleInfoVo ruleInfo, RuleConfBo confBo) {
        MultiFieldConf fieldConf = JSONUtil.parseObj(ruleInfo.getRuleConditionBackstage()).toBean(MultiFieldConf.class);

        if (Objects.nonNull(fieldConf) && CollUtil.isNotEmpty(fieldConf.getConfigs())) {
            List<FieldConf> configs = fieldConf.getConfigs();
            for (FieldConf config : configs) {
                List<LineConf> lineConfs = Optional.ofNullable(config.getConfigs()).orElseGet(ArrayList::new);

                for (LineConf lineConf : lineConfs) {
                    if (!ConditionEnum.REQUIRED.equals(lineConf.getCondition())) {
                        lineConf.setFlagWhenNull(true);
                    }
                }
            }
        }
        confBo.setCheckRule(fieldConf);
    }

    private static MultiFieldConf buildSkipCond(List<String> monthCardNoWhite) {
        if (CollectionUtils.isEmpty(monthCardNoWhite)) {
            return null;
        }
        MultiFieldConf fieldConf = JSONUtil.parseObj(SKIP_COND_STR).toBean(MultiFieldConf.class);
        List<FieldConf> configs = fieldConf.getConfigs();
        for (FieldConf config : configs) {
            List<LineConf> configs1 = config.getConfigs();
            for (LineConf lineConf : configs1) {
                if (ConditionEnum.IN_CONF.equals(lineConf.getCondition())) {
                    List<String> configs2 = lineConf.getConfigs();
                    configs2.addAll(monthCardNoWhite);
                }
            }
        }
        return fieldConf;
    }


    private static ConfRules buildPreCond(String preCondition, String preConditionMd5, String orderChannel) {
        ConfRules confRules = new ConfRules();
        ArrayList<MultiFieldConf> confRulesConfigs = new ArrayList<>();
        confRules.setConfigs(confRulesConfigs);

        if (StringUtils.isNotBlank(preCondition)) {
            MultiFieldConf fieldConf1 = JSONUtil.parseObj(preCondition).toBean(MultiFieldConf.class);
            fieldConf1.setMd5(preConditionMd5);
            confRulesConfigs.add(fieldConf1);
        }


        if (StringUtils.isNotBlank(orderChannel)) {
            List<String> collect = Arrays.stream(orderChannel.split(BspRuleConstant.SPLIT_COMMON)).collect(Collectors.toList());
            //
            MultiFieldConf fieldConf = JSONUtil.parseObj(PRE_COND_ORDER_CHANNEL).toBean(MultiFieldConf.class);
            List<FieldConf> configs = fieldConf.getConfigs();
            for (FieldConf config : configs) {
                List<LineConf> configs1 = config.getConfigs();
                for (LineConf lineConf : configs1) {
                    if (Objects.equals(ConditionEnum.IN_CONF, lineConf.getCondition())) {
                        List<String> configs2 = lineConf.getConfigs();
                        configs2.addAll(collect);
                    }
                }
            }
            confRulesConfigs.add(fieldConf);
        }
        return confRules;
    }


    public static void openList(Object fieldValue, List<Object> objects) {
        if (fieldValue instanceof Collection) {
            for (Object o : ((Collection) fieldValue)) {
                openList(o, objects);
            }
        } else {
            objects.add(fieldValue);
        }
    }

}
