package com.example.dp.context;

import com.example.dp.service.Strategy;

import java.util.HashMap;
import java.util.Map;

/**
 * desc
 *
 * @author nexus 2022/03/12 10:27
 */
public class StrategyContext {
    private static final Map<String, Strategy> registerMap = new HashMap<>();
    // 注册策略
    public static void registerStrategy(String rewardType, Strategy strategy) {
        registerMap.putIfAbsent(rewardType, strategy);
    }
    // 获取策略
    public static Strategy getStrategy(String rewardType) {
        return registerMap.get(rewardType);
    }
}
