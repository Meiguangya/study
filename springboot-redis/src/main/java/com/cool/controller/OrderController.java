package com.cool.controller;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author water33
 */

@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
    private RedisTemplate redisTemplate;

    public Lock lock = new ReentrantLock();

    @Autowired
    private Redisson redisson;

    @RequestMapping("/create")
    public String createOrder(@RequestParam("id") Integer id) throws InterruptedException {
        String lock = "order_lock";

//        while (!redisTemplate.opsForValue().setIfAbsent("lock_" + id, -1,1, TimeUnit.SECONDS)) {
//
//        }
        RLock rLock = redisson.getLock(lock);
        rLock.lock(10,TimeUnit.SECONDS);

        int count = (Integer) redisTemplate.opsForValue().get("order_" + id);
        try{
            if (count > 0) {
                int realCount = count - 1;
                redisTemplate.opsForValue().set("order_" + id, realCount);
                System.out.println("购买商品成功，剩余库存" + realCount);
                //redisTemplate.delete("lock_" + id);
                return "success";
            } else {
                System.out.println("库存不足");
                //redisTemplate.delete("lock_" + id);
                return "fail";
            }
        }finally {
            rLock.unlock();
        }

    }
}
