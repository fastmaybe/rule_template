package com.sf.saas.bsp.rule.core.service.rule.biz;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.sf.saas.bsp.rule.core.dto.res.CheckResult;
import com.sf.saas.bsp.rule.core.dto.res.CheckValidResult;
import com.sf.saas.bsp.rule.core.service.rule.*;
import com.sf.saas.bsp.rule.core.service.rule.bo.RuleConfBo;
import com.sf.saas.bsp.rule.core.service.rule.bo.RuleConfPool;
import com.sf.saas.bsp.rule.core.service.rule.bo.RuleParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * @author 01407460
 * @date 2022/10/27 14:47
 */

@Slf4j
@Component
public class ValidatorTemplate implements InitializingBean {


    @Autowired
    private List<PostValidator> postValidatorList;

    private Map<Integer, PostValidator> factory = new HashMap<>();

    @Override
    public void afterPropertiesSet() throws Exception {
        for (PostValidator postValidator : postValidatorList) {
            List<Integer> support = postValidator.support();
            support.forEach(e -> factory.put(e, postValidator));
        }
    }


    public CheckResult validate(RuleConfPool ruleConfPool, JSONObject target, String currentLang) {
        String waybillNo = target.getStr(RuleCondEnum.WAYBILLNO.getField());

        if (Objects.isNull(ruleConfPool) || CollectionUtils.isEmpty(ruleConfPool.getRuleConfList())) {
            return CheckResult.buildDefault(waybillNo);
        }
        CheckResult checkResult = new CheckResult();

        checkResult.setWaybillNo(waybillNo);

        List<CheckValidResult> validResults = new ArrayList<>();

        List<RuleConfBo> ruleConfList = ruleConfPool.getRuleConfList();
        Map<String, Boolean> dp = new HashMap<>(16);

        for (RuleConfBo ruleConfBo : ruleConfList) {

            //todo???????????? ????????????  ??????????????? ??????pass
            try {
                ValidResult result = validate0(ruleConfBo, target, dp);

                if (!result.validate()) {
                    // no pass ??????????????????????????????
                    String tip = ruleConfBo.getTipMap().get(currentLang);
                    validResults.add(CheckValidResult.build(ruleConfBo.getRuleName(),tip,ruleConfBo.getCheckRuleJson(),ruleConfBo.getFieldKey()));
                }
            } catch (Exception e) {
                //???????????? skip ????????????catch,???????????????????????? ???
                // pre,check ?????????catch ????????? ?????????????????????==?????????????????????????????? ?????? ?????????????????????, ???????????????????????????
                log.error("[check-rule] validate0 found error,waybillNo={},ruleConfBo is {} ",waybillNo, com.alibaba.fastjson.JSON.toJSONString(ruleConfBo), e);
            }
        }

        checkResult.setValidResults(validResults);
        checkResult.setPass(CollectionUtils.isEmpty(validResults));

        return checkResult;
    }


    /**
     * @param ruleConfBo
     * @param target
     * @param dp
     * @return ValidResult ????????????
     */
    private ValidResult validate0(RuleConfBo ruleConfBo, JSON target, Map<String, Boolean> dp) {
        // Skip - cond
        boolean skip = matchSkipCond(ruleConfBo.getSkipCond(), target);
        if (skip) {
            return ValidBaseResult.buildPass();
        }


        // step1 Pre-cond
        boolean match = matchPreCond(ruleConfBo.getPreCond(), target, dp);
        if (!match) {
            return ValidBaseResult.buildPass();
        }

        // step 2 ruleCheck
        boolean b = matchPostCond(ruleConfBo, target);

        return ValidBaseResult.build(b);

    }

    /**
     * @param skipCond
     * @param target
     * @return ?????? - ???????????????
     */
    private boolean matchSkipCond(MultiFieldConf skipCond, JSON target) {
        if (Objects.isNull(skipCond) || CollectionUtils.isEmpty(skipCond.getConfigs())) {
            // ????????? ?????????  = ???????????????
            return false;
        }
        // ?????? ????????????
        try {
            return skipCond.isValidate(target);
        } catch (Exception e) {
            log.error("[check-rule] matchSkipCond error so no skip, skipCond is {},target is {}", JSONUtil.toJsonStr(skipCond),JSONUtil.toJsonStr(target),e);
            return false;
        }
    }


    /**
     * @param confRules
     * @param target
     * @param dp
     * @return ?????? ????????? - ????????????
     */
    private boolean matchPreCond(ConfRules confRules, JSON target, Map<String, Boolean> dp) {
        if (Objects.isNull(confRules) || CollectionUtils.isEmpty(confRules.getConfigs())) {
            //???????????????  ??????????????????
            return true;
        }
        List<MultiFieldConf> configs = confRules.getConfigs();


        for (MultiFieldConf config : configs) {
            String md5 = config.getMd5();
            boolean md5NotBlank = StringUtils.isNotBlank(md5);

            if (md5NotBlank && dp.containsKey(md5)) {
                Boolean aBoolean = dp.get(md5);
                if (Boolean.FALSE.equals(aBoolean)){
                    return false;
                }
                continue;
            }

            Boolean validate = config.isValidate(target);
            if (md5NotBlank) {
                dp.put(md5, validate);
            }
            if (Boolean.FALSE.equals(validate)){
                return false;
            }
        }

        return true;
    }

    private boolean matchPostCond(RuleConfBo ruleConfBo, JSON target) {

        Integer type = ruleConfBo.getType();
        PostValidator postValidator = factory.get(type);
        if (Objects.isNull(postValidator)) {
            log.error("[check-rule] ValidatorTemplate-no match postValidator...,rule json  is {}", com.alibaba.fastjson.JSON.toJSONString(ruleConfBo));
            return true;
        }
        RuleParam build = RuleParam.builder().ruleConfBo(ruleConfBo).target(target).build();

        return postValidator.isValidate(build);

    }


}
