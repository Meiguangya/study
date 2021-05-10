package com.cool.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author water33
 */
@Controller
public class HelloController {
    @GetMapping("/index")
    @ResponseBody
    public String index() {
        return "Hello World ~";
    }

    @RequestMapping("/login")
    public String showLogin() {
        return "login.html";
    }

    @GetMapping("/home")
    @ResponseBody
    public String home() {
        return "欢迎来到主页 ~";
    }
}
