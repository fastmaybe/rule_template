package com.sf.saas.bsp.rule.core.manager.config.feign;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Description ThirdSystemUrl
 *
 * @author suntao(01408885)
 * @since 2022-09-21
 */
@Data
@Component("thirdSystemConf")
public class ThirdSystemConf {

    /**
     * 系统编码
     */
    @Value("${http.cdm.query.user.url}")
    private String cdmSysUrl;
}
