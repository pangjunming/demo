package com.example.client.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author pangjunming
 * @Description:
 * @date 2020/7/19
 */
@Controller
public class HelloController {

    @RequestMapping("hello")
    @ResponseBody
    public String hello(String name){
        return "hello， " + name;
    }
}
