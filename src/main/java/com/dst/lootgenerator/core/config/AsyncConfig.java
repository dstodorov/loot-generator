package com.dst.lootgenerator.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
@EnableAsync
public class AsyncConfig {
    @Bean(name = "taskExecutor")
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5); // Брой на основните нишки
        executor.setMaxPoolSize(10); // Максимален брой нишки
        executor.setQueueCapacity(25); // Капацитет на опашката
        executor.setThreadNamePrefix("LogThread-"); // Префикс на имената на нишките
        executor.initialize();
        return executor;
    }
}
