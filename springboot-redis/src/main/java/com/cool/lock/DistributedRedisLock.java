package com.cool.lock;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author meiguangya
 * @Description
 * @create 2021-07-29 15:02
 */
@Component
@Slf4j
public class DistributedRedisLock {

    @Autowired
    private RedissonClient redissonClient;

    // 加锁
    public Boolean lock(String lockName) {
        if (redissonClient == null) {
            log.info("DistributedRedisLock redissonClient is null");
            return false;
        }

        try {
            RLock lock = redissonClient.getLock(lockName);
            // 锁10秒后自动释放，防止死锁
            lock.lock(10, TimeUnit.SECONDS);

            log.info("Thread [{}] DistributedRedisLock lock [{}] success", Thread.currentThread().getName(), lockName);
            // 加锁成功
            return true;
        } catch (Exception e) {
            log.error("DistributedRedisLock lock [{}] Exception:", lockName, e);
            return false;
        }
    }

    // 释放锁
    public Boolean unlock(String lockName) {
        if (redissonClient == null) {
            log.info("DistributedRedisLock redissonClient is null");
            return false;
        }

        try {
            RLock lock = redissonClient.getLock(lockName);
            if(lock.isLocked()){ // 是否还是锁定状态
                if(lock.isHeldByCurrentThread()){ // 时候是当前执行线程的锁
                    lock.unlock(); // 释放锁
                }
            log.info("Thread [{}] DistributedRedisLock unlock [{}] success", Thread.currentThread().getName(), lockName);
            // 释放锁成功
            }
            return true;
        } catch (Exception e) {
            log.error("DistributedRedisLock unlock [{}] Exception:", lockName, e);
            return false;
        }
    }

}
