package com.sf.saas.bsp.rule.core.manager.config.feign;

import com.sf.saas.api.builder.ClientFactory;
import com.sf.saas.api.builder.RestConfig;
import com.sf.saas.cdm.api.ICdmCustomerRestApi;
import com.sf.saas.cdm.api.ICdmCustomerUserRestApi;
import lombok.Data;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 01407460
 * @date 2022/9/7 9:54
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "feign.rest")
public class FeignConfiguration {

    private int connectTimeoutMillis;
    private int readTimeoutMillis;
    private int maxAttempts;
    private long period;
    private long maxPeriod;

    private RestConfig genConfig(String url) {
        return new RestConfig().restGwPath(url).connectTimeoutMillis(connectTimeoutMillis)
                .readTimeoutMillis(readTimeoutMillis)
                .maxAttempts(maxAttempts).period(period).maxPeriod(maxPeriod);
    }

    @Bean(name = "cdmCustomerUserRestApi")
    public ICdmCustomerUserRestApi cdmCustomerUserRestApi(@Qualifier("thirdSystemConf") ThirdSystemConf conf) {
        return ClientFactory.load(ICdmCustomerUserRestApi.class, genConfig(conf.getCdmSysUrl()));
    }

    @Bean(name = "cdmCustomerRestApi")
    public ICdmCustomerRestApi cdmCustomerRestApi(@Qualifier("thirdSystemConf") ThirdSystemConf conf) {
        return ClientFactory.load(ICdmCustomerRestApi.class, genConfig(conf.getCdmSysUrl()));
    }

}
