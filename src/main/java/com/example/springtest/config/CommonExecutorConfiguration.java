package com.example.springtest.config;

import com.example.springtest.utils.CommonExecutor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * description
 *
 * @author junqing.zhang@hand-china.com 2021/12/01 14:51
 */
@Configuration
@EnableAsync
public class CommonExecutorConfiguration {

    @Bean(
            name = {"CommonAsyncTaskExecutor"}
    )
    @Qualifier("CommonAsyncTaskExecutor")
    public ThreadPoolExecutor commonAsyncTaskExecutor() {
        return CommonExecutor.buildThreadFirstExecutor("commonExecutor");
    }
}
