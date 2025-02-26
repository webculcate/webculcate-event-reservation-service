package com.webculcate.event.reservation.service.core.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

import static com.webculcate.event.reservation.service.core.constant.ServiceConstant.DEFAULT_TASK_EXECUTOR;
import static com.webculcate.event.reservation.service.core.constant.ServiceConstant.DEFAULT_TASK_EXECUTOR_PREFIX;

@EnableAsync
@Configuration
public class AsynchronousConfiguration {

    /*
     * Ideally these values should be derived from
     * some sort of external configuration which can be
     * changed dynamically without making changes in the
     * application code
     */
    @Bean(DEFAULT_TASK_EXECUTOR)
    public Executor createExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(4);
        executor.setMaxPoolSize(8);
        executor.setQueueCapacity(200);
        executor.setThreadNamePrefix(DEFAULT_TASK_EXECUTOR_PREFIX);
        executor.initialize();
        return executor;
    }

}
