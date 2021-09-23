package com.cool.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author meiguangya
 * @Description 测试日志输出到控制台的类
 * @create 2021-09-15 17:01
 */
@RequestMapping("/console")
@RestController
@Slf4j
public class ConsoleLogController {

    @GetMapping("/testLog")
    public void testLog(){
        log.debug("message0");
        log.info("message1");
        log.warn("message2");
        log.error("message3");
    }

    @GetMapping("/errorLog")
    public void errorLog(){
        try{
            int rs = 1/0;
        }catch (Exception e){
//            log.info(e.getMessage());
//            log.info(ExceptionUtil.stacktraceToString(e));
            e.printStackTrace();
        }
    }

}
