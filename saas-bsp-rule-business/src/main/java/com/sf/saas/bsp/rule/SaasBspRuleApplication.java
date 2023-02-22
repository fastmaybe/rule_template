package com.sf.saas.bsp.rule;

import cn.hutool.core.net.NetUtil;
import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import com.sf.saas.bsp.rule.core.service.annotation.EnableMybatisPlus;
import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author 01407460
 * @date 2022/9/2 18:52
 */
@Slf4j
@EnableSwagger2
@EnableMybatisPlus
@MapperScan({"com.sf.saas.bsp.rule.core.dao"})
@EnableScheduling
@EnableEncryptableProperties
@EnableApolloConfig
@SpringBootApplication
public class SaasBspRuleApplication {

    public static void main(String[] args) {
        SpringApplication.run(SaasBspRuleApplication.class, args);
        String localHostName = NetUtil.getLocalHostName();
        log.info("springboot run successful... HostName is {}",localHostName);
    }
}
