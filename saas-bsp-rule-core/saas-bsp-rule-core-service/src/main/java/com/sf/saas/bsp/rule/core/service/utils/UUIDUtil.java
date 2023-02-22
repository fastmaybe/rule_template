package com.sf.saas.bsp.rule.core.service.utils;

import java.util.UUID;

/**
 * @author 01407460
 * @date 2022/9/14 15:26
 */
public class UUIDUtil {

    public static  String uuid(){
      return   UUID.randomUUID().toString().replace("-","");
    }
}
