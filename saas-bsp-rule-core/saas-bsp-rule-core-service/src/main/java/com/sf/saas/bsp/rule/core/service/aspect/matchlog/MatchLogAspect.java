package com.sf.saas.bsp.rule.core.service.aspect.matchlog;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * @author 01407460
 * @date 2022/9/20 14:22
 */

//@Slf4j
//@Aspect
//@Component
public class MatchLogAspect {

//    @Autowired
    private ThreadPoolTaskExecutor matchLogExecutor;

//    @Autowired
//    private IStrategyMatchLogService strategyMatchLogService


    @Pointcut("@annotation(com.sf.saas.bsp.rule.core.service.aspect.matchlog.MatchLog)")
    public void pointCut() {
        // Do Nothing
    }

    @Around("pointCut() && @annotation(matchLog)")
    public Object around(ProceedingJoinPoint joinPoint, MatchLog matchLog) throws Throwable {

        Object result = joinPoint.proceed();

        matchLogExecutor.execute(() -> saveLog(joinPoint, matchLog, result, null));

        return result;
    }

    @AfterThrowing(pointcut = "pointCut() && @annotation(matchLog)", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, MatchLog matchLog, Throwable e) {
        matchLogExecutor.execute(() -> saveLog(joinPoint, matchLog, null, e));
    }


    private void saveLog(JoinPoint joinPoint, MatchLog matchLog, Object result, Throwable e) {
//        StrategyMatchLog log = new StrategyMatchLog();
//        Object[] args = joinPoint.getArgs();
//
//        long now = System.currentTimeMillis();
//        log.setTraceId(MDC.get(BspRuleConstant.TRACE_ID));
//        log.setCreateTime(now);
//        log.setUpdateTime(now);
//        log.setRequestType(matchLog.value().getValue());
//        log.setRequest(JSON.toJSONString(args));
//
//        String res = e == null ? JSON.toJSONString(result) : e.getMessage();
//        log.setResponse(res);
//
//        if (args.length > 1 && args[0] instanceof JSONObject) {
//            JSONObject jsonObject = (JSONObject) args[0];
////            log.setWaybillNo(jsonObject.getString(StrategyDimension.WAYBILL_NO.getKey()));
//            log.setTenantId(jsonObject.getString(BspRuleConstant.HEADER_TENANT_ID));
//        }
//        strategyMatchLogService.save(log);
    }
}

