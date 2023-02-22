package com.sf.saas.bsp.rule.core.common.anno;

import com.sf.saas.bsp.rule.core.common.emnus.LockLevel;
import org.springframework.core.annotation.Order;

import java.lang.annotation.*;

/**
 * Redis lock
 *
 * @author 80003774
 * @since 1.8
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Order(10)
public @interface RedisLock {

    /**
     * 锁定级别，默认参数部分字段锁定，keys[]配置空为方法锁定，完整入参锁定REQUEST级别
     *
     * @return
     */
    LockLevel lockLevel() default LockLevel.FIELD;

    /**
     * 自定义KEY前缀
     *
     * @return
     */
    String preKey() default "";

    /**
     * 锁定参数
     *
     * @return
     */
    String[] keys() default {};

    /**
     * 自动过期时间 10000 毫秒
     *
     * @return
     */
    int expireTime() default 10000;

    /**
     * 自旋等待时间 1000 毫秒
     *
     * @return
     */
    int waitTime() default 5000;

}
