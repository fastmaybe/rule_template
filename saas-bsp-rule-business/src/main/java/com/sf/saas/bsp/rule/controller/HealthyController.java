package com.sf.saas.bsp.rule.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 01407460
 * @date 2022/9/2 19:03
 */
@RestController
public class HealthyController {

    @GetMapping("/healthyCheck")
    public String healthyCheck(){
        return "ok";
    }
}
