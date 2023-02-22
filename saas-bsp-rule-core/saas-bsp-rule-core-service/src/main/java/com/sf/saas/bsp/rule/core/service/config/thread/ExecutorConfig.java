package com.sf.saas.bsp.rule.core.service.config.thread;

import com.sf.saas.sdk.log4j.task.decorate.MdcTaskDecorator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author 01407460
 * @date 2022/9/14 21:40
 */
@Configuration
public class ExecutorConfig {

    /**
     * 策略修改专用 线程池
     * @return
     */
//    @Bean("strategyUpdateExecutor")
    public ThreadPoolTaskExecutor strategyUpdateExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(3);
        executor.setMaxPoolSize(5);

        executor.setQueueCapacity(3000);

        executor.setThreadNamePrefix("strategyUpdateExecutor-");

/*
线程池对拒绝任务的处理策略：这里采用了CallerRunsPolicy策略，
当线程池没有处理能力的时候，该策略会直接在 execute 方法的调用线程中运行被拒绝的任务；
如果执行程序已关闭，则会丢弃该任务
*/
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());

        executor.setWaitForTasksToCompleteOnShutdown(true);

        executor.setAwaitTerminationSeconds(60 * 2);

        executor.setTaskDecorator(new MdcTaskDecorator());

        executor.initialize();
        return executor;
    }


    /**
     * 策略日志记录
     * @return
     */
//    @Bean("matchLogExecutor")
    public ThreadPoolTaskExecutor matchLogExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(30);
        executor.setMaxPoolSize(60);

        executor.setQueueCapacity(2048);

        executor.setThreadNamePrefix("matchLogExecutor-");

        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardOldestPolicy());

        executor.setWaitForTasksToCompleteOnShutdown(true);

        executor.setAwaitTerminationSeconds(60 * 2);

        executor.setKeepAliveSeconds(120);
        executor.setTaskDecorator(new MdcTaskDecorator());

        executor.initialize();
        return executor;
    }

    /**
     *
     * @return
     */
    @Bean("ruleCheckExecutor")
    public ThreadPoolTaskExecutor matchCheckExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(30);
        executor.setMaxPoolSize(60);

        executor.setQueueCapacity(2048);

        executor.setThreadNamePrefix("matchCheckExecutor-");

        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());

        executor.setWaitForTasksToCompleteOnShutdown(true);

        executor.setAwaitTerminationSeconds(60 * 2);

        executor.setKeepAliveSeconds(120);
        executor.setTaskDecorator(new MdcTaskDecorator());

        executor.initialize();
        return executor;
    }
}
