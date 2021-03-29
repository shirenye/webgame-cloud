package com.webgame.system.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping
    public String test(){

        return "this is test";
    }

    @GetMapping
    public String test1(){

        return "this is test1";
    }

    @GetMapping
    public String test2(){

        return "this is test2";
    }

}
