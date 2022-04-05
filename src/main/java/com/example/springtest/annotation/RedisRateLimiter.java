package com.example.springtest.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

/**
 * 1(时间)分钟（单位）允许某个ip请求的最大次数(max)
 * <p>
 * 如每隔2分钟，单IP限定访问次数不能超过10次
 *
 * @author Nexus
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RedisRateLimiter {

    /**
     * 默认根据IP拦截
     */
    LimitType limitType() default LimitType.IP;

    enum LimitType {
        GENERAL, IP, USERID;
    }

    /**
     * 限制时间长度
     */
    long timeLimitLength() default 1;

    /**
     * 限制时间长度的单位
     */
    TimeUnit timeLimitLengthUnit() default TimeUnit.SECONDS;

    /**
     * 允许时间内最大访问数
     */
    long max() default 1;

    /**
     * redis存储的key
     */
    String storeKey() default "";

}
