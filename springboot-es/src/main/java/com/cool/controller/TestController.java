package com.cool.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author meiguangya
 * @Description
 * @create 2021-09-15 17:01
 */
@RequestMapping
@RestController
@Slf4j
public class TestController {

    @GetMapping("/testLog")
    public void testLog(){
        log.debug("message0");
        log.info("message1");
        log.warn("message2");
        log.error("message3");
    }

    @GetMapping("/errorLog")
    public void errorLog(){
        int rs = 1/0;
    }

}
