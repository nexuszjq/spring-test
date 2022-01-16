package com.example.springtest.config;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * description
 *
 * @author junqing.zhang@hand-china.com 2021/12/28 20:21
 */
@Configuration
public class CuratorConfig {

//    @Bean
//    public CuratorFramework getCuratorClient() {
//        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
//        CuratorFramework client = CuratorFrameworkFactory.newClient("127.0.0.1", retryPolicy);
//        client.start();
//        return client;
//    }

}
