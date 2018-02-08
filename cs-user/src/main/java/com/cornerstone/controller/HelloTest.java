package com.cornerstone.controller;

import java.util.ArrayList;
import java.util.Map;

import com.cornerstone.domain.Result;
import com.google.common.collect.Maps;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: liyl
 * @date: 2018/2/4 上午11:21
 * @since 1.0.0
 */
@RestController
@RequestMapping("/api")
public class HelloTest {

    @GetMapping("/hello")
    @ResponseBody
    Map<String,String>hello(){
        Map<String,String> map = Maps.newHashMap();

        //HashSet<Object> objects = Sets.newHashSet();
        map.put("content", "hello freewolf~");
        return map;
    }


    @GetMapping("/users")
    @ResponseBody
    public Result userList(){
        ArrayList<String> users =  new ArrayList<String>(){{
            add("freewolf");
            add("tom");
            add("jerry");
        }};

        return new Result(0,"",users);
    }
}