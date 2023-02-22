package com.sf.saas.bsp.rule.core.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 数据字典表
 * </p>
 *
 * @author 01408885
 * @since 2022-10-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("bsp_rule_dictionary")
public class RuleDictionary implements Serializable {


    /**
     * 自增id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 数据字典类型场景
     */
    private String scene;

    /**
     * 字典key
     */
    private String dictKey;

    /**
     * 字典value
     */
    private String dictValue;

    /**
     * 显示排序
     */
    private Integer sort;

    /**
     * 语言：中文-zh-CN；英文-en
     */
    private String lang;

    /**
     * 备注
     */
    private String remark;

    /**
     * 有效状态：0-无效；1-有效
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Long gmtCreate;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 更新时间
     */
    private Long gmtModified;

    /**
     * 修改人
     */
    private String updater;


}
