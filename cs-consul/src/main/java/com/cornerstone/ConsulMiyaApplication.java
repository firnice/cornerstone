package com.cornerstone;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author: liyl
 * @date: 2018/2/12 下午5:27
 * @since 1.0.0
 */

@SpringBootApplication
@EnableDiscoveryClient
public class ConsulMiyaApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(ConsulMiyaApplication.class).web(true).run(args);

    }
}
