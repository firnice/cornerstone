package com.cornerstone;

import java.security.Principal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: liyl
 * @date: 2018/3/6 下午3:52
 * @since 1.0.0
 */
@SpringBootApplication
@RestController
public class CsUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(CsUserApplication.class, args);

    }


    @RequestMapping("/user")
    public Principal user(Principal user) {
        return user;
    }
}
