package com.example.springtest.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * description
 *
 * @author junqing.zhang@hand-china.com 2022/01/21 0:03
 */
@Service
public class QueryGrantTypeService {

    @Autowired
    private GrantTypeSerive grantTypeSerive;

    private Map<String, Function<String, String>> grantTypeMap = new HashMap<>();

    /**
     * 初始化业务分派逻辑,代替了if-else部分
     * key: 优惠券类型
     * value: lambda表达式,最终会获得该优惠券的发放方式
     */
    @PostConstruct
    public void dispatcherInit() {
        grantTypeMap.put("funcA", resourceId -> grantTypeSerive.funcA(resourceId));
        grantTypeMap.put("funcB", resourceId -> grantTypeSerive.funcB(resourceId));
        grantTypeMap.put("funcC", resourceId -> grantTypeSerive.funcC(resourceId));
    }

    public String getResult(String resourceType, String resourceId) {
        //Controller根据 优惠券类型resourceType、编码resourceId 去查询 发放方式grantType
        Function<String, String> result = grantTypeMap.get(resourceType);
        if (result != null) {
            //传入resourceId 执行这段表达式获得String型的grantType
            return result.apply(resourceId);
        }
        return "no match";
    }
}