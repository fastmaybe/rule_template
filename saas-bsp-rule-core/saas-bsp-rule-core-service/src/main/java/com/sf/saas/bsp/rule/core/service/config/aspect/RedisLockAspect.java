package com.sf.saas.bsp.rule.core.service.config.aspect;

import com.alibaba.fastjson.JSON;
import com.sf.saas.bsp.rule.core.common.anno.RedisLock;
import com.sf.saas.bsp.rule.core.common.emnus.LockLevel;
import com.sf.saas.bsp.rule.core.common.exception.LockException;
import com.sf.saas.bsp.rule.core.common.exception.SystemException;
import com.sf.saas.bsp.rule.core.common.utils.ConvertUtil;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**
 * redis lock aspect
 *
 * @author 80003774
 * @since 1.8
 */
@Log4j2
@Aspect
@Component
public class RedisLockAspect {

    private static final String LOCK_PRE = "saas:bps:lock:";

    @Autowired
    private RedissonClient redissonClient;


    @Pointcut("@annotation(redisLock)")
    public void lockAspectCut(RedisLock redisLock) {
        // do
    }

    @Around(value = "lockAspectCut(redisLock)", argNames = "joinPoint,redisLock")
    public Object around(ProceedingJoinPoint joinPoint, RedisLock redisLock) throws Throwable {
        log.info(">>> RedisLockAspect run >>> ");
        Class<?> aClass = joinPoint.getTarget().getClass();
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        Object[] args = joinPoint.getArgs();

        LockLevel lockLevel = redisLock.lockLevel();
        String preKey = redisLock.preKey();
        String[] keys = redisLock.keys();
        int expireTime = redisLock.expireTime();
        int waitTime = redisLock.waitTime();

        boolean res = false;
        RLock lock = null;
        Object obj;
        try {
            String lockKey = buildLockKey(aClass, method, args, lockLevel, preKey, keys);
            log.info(">>> RedisLockAspect lock key = [{}]", lockKey);

            lock = redissonClient.getLock(lockKey);
            res = lock.tryLock(waitTime, expireTime, TimeUnit.MILLISECONDS);
            if (res) {
                log.info(">>> RedisLockAspect lock success");
                obj = joinPoint.proceed();
            } else {
                log.info(">>> RedisLockAspect lock failed");
                throw new LockException("Repeat operation, please try again later!");
            }
        } finally {
            if (null != lock && res) {
                lock.unlock();
            }
        }
        return obj;
    }

    private String buildLockKey(Class<?> aClass, Method method, Object[] args,
                                LockLevel lockLevel, String preKey, String[] keys)
            throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        StringBuilder key = new StringBuilder(LOCK_PRE);
        key.append(preKey);
        switch (lockLevel) {
            case INSTANCE:
                checkPreKey(preKey);
                break;
            case CLASS:
                key.append(aClass.getName());
                break;
            case METHOD:
                key.append(aClass.getName()).append(method.getName());
                break;
            case FIELD:
                key.append(aClass.getName()).append(method.getName());
                setKeysItem(args, keys, key);
                break;
            case REQUEST:
                key.append(aClass.getName()).append(method.getName());
                if (null != args) {
                    String paramStr = JSON.toJSONString(args);
                    String md5 = DigestUtils.md5DigestAsHex(paramStr.getBytes());
                    key.append(md5);
                }
                break;
            case GLOBAL:
                checkPreKey(preKey);
                setKeysItem(args, keys, key);
                break;
            default:
                break;
        }
        return key.toString();
    }

    private void checkPreKey(String preKey) {
        if (StringUtils.isEmpty(preKey)) {
            throw new SystemException("The preKey of instance or global lock level is required!");
        }
    }

    private void setKeysItem(Object[] args, String[] keys, StringBuilder key) throws
            NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        if (keys.length > 0) {
            if (null != args && args.length > 0) {
                Object arg = args[0];
                for (String paramKey : keys) {
                    key.append(ConvertUtil.invokeParamVal(arg, paramKey));
                }
            }
        }
    }

}
