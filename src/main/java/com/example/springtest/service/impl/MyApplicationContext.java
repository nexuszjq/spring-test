package com.example.springtest.service.impl;

import com.example.springtest.service.StrategyService;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * description
 *
 * @author junqing.zhang@hand-china.com 2021/12/18 16:12
 */
@Component
public class MyApplicationContext implements ApplicationContextAware {
    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public ApplicationContext getContext() {
        return applicationContext;
    }

    public void getInvoke() {
        System.out.println("=============>" + getStrategy().invoke());
    }


    public StrategyService getStrategy() {
        return applicationContext.getBean("BBB", StrategyService.class);
    }
}
