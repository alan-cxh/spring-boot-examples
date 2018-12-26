package com.alan.springboothelloworld.helloworld.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloController {

    @RequestMapping("test")
    public String test(){
        return "springboot基础技术";
    }

}
