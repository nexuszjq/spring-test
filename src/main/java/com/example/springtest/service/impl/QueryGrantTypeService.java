package com.example.springtest.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * description
 *
 * @author junqing.zhang@hand-china.com 2022/01/21 0:03
 */
@Service
public class QueryGrantTypeService {

    @Autowired
    private GrantTypeSerive grantTypeSerive;

    private static final Map<String, Supplier<String>> EVENT_ID_MAP = new HashMap<>();

    /**
     * 初始化业务分派逻辑,代替了if-else部分
     * key: 优惠券类型
     * value: lambda表达式,最终会获得该优惠券的发放方式
     */
    @PostConstruct
    public void dispatcherInit() {
        EVENT_ID_MAP.put("NEW", () -> grantTypeSerive.getNew());
        EVENT_ID_MAP.put("CANCEL", () -> grantTypeSerive.getCancel());
        EVENT_ID_MAP.put("UPDATE", () -> grantTypeSerive.getUpdate());
    }

    public String getResult(String resourceType) {
        //Controller根据 优惠券类型resourceType、编码resourceId 去查询 发放方式grantType
        Supplier<String> result = EVENT_ID_MAP.get(resourceType);
        if (result != null) {
            //传入resourceId 执行这段表达式获得String型的grantType
            return result.get();
        }
        return "no match";
    }
}