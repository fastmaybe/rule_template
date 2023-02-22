package com.sf.saas.bsp.rule.controller.manager;

import com.sf.saas.bsp.rule.core.api.IDictionaryBizService;
import com.sf.saas.bsp.rule.core.dto.base.Response;
import com.sf.saas.bsp.rule.core.dto.vo.FieldConditionSelectInfoVo;
import com.sf.saas.bsp.rule.core.dto.vo.DictionarySelectVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author 01407460
 * @date 2022/9/7 9:28
 */
@Slf4j
@RestController
@RequestMapping("/manage/dictionary")
@Api(tags = "【字典信息】")
public class DictionaryController {

    @Autowired
    private IDictionaryBizService dictionaryBizService;



    @GetMapping("/group")
    @ApiOperation(value = "字典")
    public Response<Map<String, List<DictionarySelectVo>>>  dictionaryGroup (){
       return dictionaryBizService.dictionaryGroup();
    }


    @GetMapping("/conditionInfo")
    @ApiOperation(value = "条件参数详情，可以选的condition列表，参数输入框个数")
    public Response<Map<String, List<FieldConditionSelectInfoVo>>>  conditionInfo (){
       return dictionaryBizService.conditionInfo();
    }


    @PostMapping("/group/reload")
    @ApiOperation(value = "重新加载字典缓存")
    public Response<Boolean> reloadDictionaryGroup() {
        return dictionaryBizService.reloadDictionaryGroup();
    }




}
