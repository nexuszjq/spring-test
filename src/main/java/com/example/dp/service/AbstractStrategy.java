package com.example.dp.service;

import com.example.dp.context.StrategyContext;

/**
 * desc
 *
 * @author nexus 2022/03/12 10:28
 */
public abstract class AbstractStrategy implements Strategy {
    // 类注册方法
    public void register() {
        StrategyContext.registerStrategy(getClass().getSimpleName(), this);
    }
}
