package com.cornerstone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author: liyl
 * @date: 2018/2/3 下午6:51
 * @since 1.0.0
 */
@SpringBootApplication
@EnableAutoConfiguration
//@ComponentScan  //不配置路径就是当前包下
@ComponentScan(basePackages = {"com.cornerstone"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }
}
