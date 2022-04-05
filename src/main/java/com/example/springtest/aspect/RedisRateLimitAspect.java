package com.example.springtest.aspect;

import com.example.springtest.annotation.RedisRateLimiter;
import com.example.springtest.utils.IpUtil;
import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
@Aspect
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class RedisRateLimitAspect {

    private final static String REDIS_RATE_LIMIT_KEY_PREFIX = "limit:";
    private final StringRedisTemplate stringRedisTemplate;
    private final RedisScript<Long> limitRedisScript;

    @Pointcut("@annotation(com.example.springtest.annotation.RedisRateLimiter)")
    public void rateLimit() {
    }

    @Before("rateLimit()")
    public void pointCut(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        // 通过 AnnotationUtils.findAnnotation 获取 RateLimiter 注解
        RedisRateLimiter redisRateLimit = AnnotationUtils.findAnnotation(method, RedisRateLimiter.class);

        if (redisRateLimit != null) {

            //获取存储key名称
            String key = redisRateLimit.storeKey();
            //获取时间限制
            long timeLimitLength = redisRateLimit.timeLimitLength();
            //获取时间限制单位
            TimeUnit timeLimitLengthUnit = redisRateLimit.timeLimitLengthUnit();
            //时间单位最大访问数目
            long max = redisRateLimit.max();


            if (StringUtils.isBlank(key)) {
                key = method.getDeclaringClass().getSimpleName() + "." + method.getName();
            }

            HttpServletRequest request
                    = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();

            key = key + ":" + IpUtil.getIpAddr(request);

            //追加统一限流前缀
            key = REDIS_RATE_LIMIT_KEY_PREFIX + key;

            long now = System.currentTimeMillis();
            //将2分钟转化为毫秒时间戳,以获得2分钟前时间
            long limitTimeLengthMills = timeLimitLengthUnit.toMillis(timeLimitLength);

            //应该移除的分值区间
            long removeScore = now - limitTimeLengthMills;

            Long r = stringRedisTemplate.execute(
                    limitRedisScript,
                    Lists.newArrayList(key),
                    "" + now,
                    "" + limitTimeLengthMills,   //设置key的保存时间，该key在2分钟的允许时间内做zadd操作
                    "" + removeScore,     //移除当前时间2分钟前过期的score
                    "" + max);

            if (r != null) {
                if (r == 0) {
                    log.error("【{}】在 " + timeLimitLength + formatTimeUnit(timeLimitLengthUnit) + " 内已达到访问上限，当前接口上限 {}", key, max);
                    throw new RuntimeException("手速太快了，慢点儿吧~");
                } else {
                    log.info("【{}】在 " + timeLimitLength + formatTimeUnit(timeLimitLengthUnit) + " 内访问 {} 次", key, r);
                }
            }

        }


    }

    private String formatTimeUnit(TimeUnit timeUnit) {
        if (timeUnit == TimeUnit.MINUTES) {
            return "分钟";
        } else if (timeUnit == TimeUnit.SECONDS) {
            return "秒";
        } else if (timeUnit == TimeUnit.HOURS) {
            return "小时";
        }
        return "illegal timeUnit args";
    }

}