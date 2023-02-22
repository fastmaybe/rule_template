package com.sf.saas.bsp.rule.core.service.rule;


import cn.hutool.core.lang.Assert;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import com.sf.saas.bsp.rule.core.service.rule.biz.RuleUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author 01407460
 * @date 2022/10/26 15:58
 */
@Slf4j
@Data
public class FieldConf implements Serializable, PreValidator {


    private static final long serialVersionUID = 1L;

    /**
     * 字段名
     */
    private String fieldName;

    /**
     * 条件配置
     */
    private List<LineConf> configs;

    /**
     * 连接符
     */
    private Joiner joiner = Joiner.OR;

    @Override
    public Boolean isValidate(JSON target) {
        Assert.notBlank(fieldName, "字段名路径为空");
        Assert.notEmpty(configs, "配置列表为空");
        Object fieldValue = JSONUtil.getByPath(target, fieldName);

        List<LineConfValidatorAdapter> validatorAdapterList = new ArrayList<>();
        if (fieldValue  instanceof  Collection){
            List<Object> objects = new ArrayList<>();
            RuleUtil.openList(fieldValue,objects);
            if (CollectionUtils.isEmpty(objects)){

                //todo目标不存在 校验
                objects.add(null);

//                return false
            }
            for (Object realObject : objects) {
                List<LineConfValidatorAdapter> list = configs.stream().map(f -> new LineConfValidatorAdapter(realObject, f)).collect(Collectors.toList());
                validatorAdapterList.addAll(list);
            }

            return joiner.isValidate(validatorAdapterList, target);
        }else {
            List<LineConfValidatorAdapter> list = configs.stream().map(f -> new LineConfValidatorAdapter(fieldValue, f)).collect(Collectors.toList());
            return joiner.isValidate(list, target);
        }
    }


    @Data
    @AllArgsConstructor
    static class LineConfValidatorAdapter implements PreValidator {

        private Object fieldValue;

        private LineConf lineConf;

        @Override
        public Boolean isValidate(JSON target) {
            Assert.notNull(target, "待校验的对象为 null");
            Assert.notNull(lineConf.getCondition(), "校验器为 null");
            int size = lineConf.getCondition().getConfigSize();
            List<String> configs = lineConf.getConfigs();

            boolean flagWhenNull = lineConf.isFlagWhenNull();

            if (size > 0) {
                Assert.notEmpty(configs, "条件:{} 要求配置数量:{} ，实际为空", lineConf.getCondition().getLabel(), size);
                Assert.isTrue(configs.size() == size, "条件:{} 要求配置数量:{}, 实际数量:{}", lineConf.getCondition().getLabel(), size, configs.size());
            }
            // 当需要与配置对比，但是字段值为 null 的情况下，默认就是 false
            if (Objects.isNull(fieldValue) && size != 0) {
                //需要具体对比 的直接返回
                return flagWhenNull;
            }
            if (Objects.nonNull(fieldValue) && !lineConf.getCondition().getSupportType().isInstance(fieldValue)) {
                // 类型不正确，返回 false
                log.warn("field type not support {},so return false ", lineConf.getCondition().name());
                return false;
            }
            return lineConf.getCondition().getValidator().validate(fieldValue, lineConf.getConfigs());
        }
    }
}
