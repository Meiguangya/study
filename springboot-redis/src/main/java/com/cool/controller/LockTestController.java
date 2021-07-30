package com.cool.controller;

import com.cool.lock.DistributedRedisLock;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author meiguangya
 * @Description
 * @create 2021-07-29 15:03
 */
@Slf4j
@RestController
@RequestMapping("/lock")
public class LockTestController {

    private final String LOCK = "LOCK";

    @Autowired
    private DistributedRedisLock distributedRedisLock;

    @Autowired
    private RedissonClient redissonClient;

    // 测试不释放锁
    @GetMapping("/testLock")
    public void testLock() {
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                distributedRedisLock.lock(LOCK);
            }).start();
        }
    }

    // 实际业务开发使用分布式锁的方式
    @PostMapping("/post")
    public void post() {
        try {
            if (distributedRedisLock.lock(LOCK)) {
                // 业务逻辑
                log.info("开始业务逻辑");
                TimeUnit.SECONDS.sleep(5);
            } else {
                // 处理获取锁失败的逻辑
                log.info("获取锁失败");
            }
        } catch (Exception e) {
            log.error("处理异常：", e);
        } finally {
            distributedRedisLock.unlock(LOCK);
        }
    }

    // 实际业务开发使用分布式锁的方式
    @PostMapping("/post2")
    public String post2(@RequestParam String result) {

        RLock lock = redissonClient.getLock(LOCK);
        long begin = System.currentTimeMillis();
        boolean toTry = true;
        try{
            System.out.println(lock.isLocked());
            while((System.currentTimeMillis()-begin)/1000<=5){
                if(!lock.isLocked()){
                    log.info("Thread [{}] join", Thread.currentThread().getName());
                    lock.lock();
                    log.info("Thread [{}] lock [{}] success", Thread.currentThread().getName(), LOCK);
                    log.info("开始业务逻辑"+result);
                    TimeUnit.SECONDS.sleep(20);
                    lock.unlock();
                    log.info("Thread [{}] unlock [{}] success", Thread.currentThread().getName(), LOCK);
                    return result;
                }
            }
            return "can't get lock...";
        }catch (Exception e) {
            log.error("DistributedRedisLock lock [{}] Exception:", LOCK, e);
            return "yichangle";
        }finally {
            distributedRedisLock.unlock(LOCK);
        }
    }

}
