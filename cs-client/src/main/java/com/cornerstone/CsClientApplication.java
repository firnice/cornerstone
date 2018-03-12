package com.cornerstone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: liyl
 * @date: 2018/3/6 下午3:47
 * @since 1.0.0
 */
@SpringBootApplication
@EnableCaching
@RestController
public class CsClientApplication {

    @Autowired
    private RedisClient redisClient;

    public static void main(String[] args) {
        SpringApplication.run(CsClientApplication.class, args);

    }



    @RequestMapping("/set")
    public String set(String key, String value) throws Exception{
        redisClient.set(key, value);
        return "success";
    }

    @RequestMapping("/get")
    public String get(String key) throws Exception {
        return redisClient.get(key);
    }


}
