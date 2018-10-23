package com.cs.console;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
//开启AspectJ 自动代理模式,如果不填proxyTargetClass=true，默认为false，
@EnableAspectJAutoProxy(proxyTargetClass=true)
public class CsConsoleApplication {

    public static void main(String[] args) {
        SpringApplication.run(CsConsoleApplication.class, args);
    }
}
