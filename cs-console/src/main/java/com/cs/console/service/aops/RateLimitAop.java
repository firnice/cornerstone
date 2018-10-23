package com.cs.console.service.aops;


import com.cs.console.infrastructure.annotations.RateLimitAspect;
import com.google.common.collect.Maps;
import com.google.common.util.concurrent.RateLimiter;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
@Scope
@Aspect
public class RateLimitAop {

    private static final Logger logger = LoggerFactory.getLogger(RateLimitAop.class);


    @Autowired
    private HttpServletResponse response;

    Map<String, RateLimiter> rateLimiterMap = new HashMap<>();


    public RateLimiter getRateLimiter(String name, double permitsPerSecond) {
        if (rateLimiterMap.containsKey(name)) {
            return rateLimiterMap.get(name);
        } else {
            RateLimiter rateLimiter = RateLimiter.create(permitsPerSecond);
            rateLimiterMap.put(name, rateLimiter);
            return rateLimiter;
        }
    }


    @Around(value = "@annotation(rateLimitAspect)")
    public Object around(ProceedingJoinPoint joinPoint, RateLimitAspect rateLimitAspect) {
        Boolean flag = getRateLimiter(rateLimitAspect.name(), rateLimitAspect.permitsPerSecond()).tryAcquire();
        Object obj = null;
        try {
            if (flag) {
                obj = joinPoint.proceed();
            } else {
                Map map = Maps.newHashMap();
                map.put("code", 100);
                map.put("message", "failure");
                String result = JSONObject.valueToString(map);
                output(response, result);
            }
        } catch (Throwable e) {
            logger.error(e.getMessage(), e);
        }
        logger.info("flag=" + flag + ",obj=" + obj);
        return obj;
    }

    public void output(HttpServletResponse response, String msg) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        ServletOutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
            outputStream.write(msg.getBytes("UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            outputStream.flush();
            outputStream.close();
        }
    }
}
