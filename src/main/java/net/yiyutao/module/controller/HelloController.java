package net.yiyutao.module.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: masterYI
 * @date: 2018/2/5
 * @time: 17:06
 * Description:
 */
@RestController
@RequestMapping("/hello")
public class HelloController {

    @RequestMapping("/sayhi")
    public String sayhi() {
        return "Hello World";

    }
}
