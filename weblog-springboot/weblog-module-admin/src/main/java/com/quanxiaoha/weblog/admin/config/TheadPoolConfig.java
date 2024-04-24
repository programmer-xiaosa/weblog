package com.quanxiaoha.weblog.admin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * @author: 木萨·塔布提
 * @url: blog.arnasoft.site
 * @date: 2023/11/9 10:47
 * @description: 线程池配置
 **/
@Configuration
@EnableAsync
public class TheadPoolConfig {

    @Bean("threadPoolTaskExecutor")
    public Executor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10); // 核心线程数
        executor.setMaxPoolSize(20); // 最大线程数
        executor.setQueueCapacity(100); // 队列容量
        executor.setThreadNamePrefix("WeblogThreadPool-"); // 线程名前缀
        executor.initialize();
        return executor;
    }

}
